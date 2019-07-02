package tw.com.cathaylife.testproject;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/execMySQL")
    @ResponseBody
    public Map<String, String> execMySQL(@RequestParam String mysql_sql, @RequestParam String db2_sql, @RequestParam String exec_count) {
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("exec_time", "32.000");
        return resultMap;
    }

    @RequestMapping("/execMyCat")
    @ResponseBody
    public Map<String, String> execMyCat(@RequestParam String mysql_sql, @RequestParam String db2_sql, @RequestParam String exec_count) {
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("exec_time", "32.000");
        return resultMap;
    }

    @RequestMapping("/execDB2")
    @ResponseBody
    public Map<String, String> execDB2(@RequestParam String mysql_sql, @RequestParam String db2_sql, @RequestParam String exec_count) {
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("exec_time", "32.000");
        return resultMap;
    }

    @RequestMapping("/execTest")
    @ResponseBody
    public Map<String, String> execTest(@RequestParam String mysql_sql, @RequestParam String db2_sql, @RequestParam String exec_count) {
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("exec_time", "32.000");
        return resultMap;
    }
}