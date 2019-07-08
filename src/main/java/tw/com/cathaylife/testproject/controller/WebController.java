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
}