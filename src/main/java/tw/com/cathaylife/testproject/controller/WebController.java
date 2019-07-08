package tw.com.cathaylife.testproject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.cathaylife.testproject.module.DBModule;

@Controller
public class WebController {

    @Autowired
    DBModule dbmodule;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/execLocal")
    @ResponseBody
    public Map<String, Object> execLocal(@RequestParam String table_name, @RequestParam String exec_count) {
        return dbmodule.queryLocal(table_name, Integer.parseInt(exec_count));
    }

    @RequestMapping("/execMySQL")
    @ResponseBody
    public Map<String, Object> execMySQL(@RequestParam String table_name, @RequestParam String exec_count) {
        return dbmodule.queryMySQL(table_name, Integer.parseInt(exec_count));
    }

    @RequestMapping("/execMyCat")
    @ResponseBody
    public Map<String, Object> execMyCat(@RequestParam String table_name, @RequestParam String exec_count) {
        return dbmodule.queryMyCat(table_name, Integer.parseInt(exec_count));
    }

    @RequestMapping("/execDB2")
    @ResponseBody
    public Map<String, Object> execDB2(@RequestParam String table_name, @RequestParam String exec_count) {
        return dbmodule.queryDB2(table_name, Integer.parseInt(exec_count));
    }
}