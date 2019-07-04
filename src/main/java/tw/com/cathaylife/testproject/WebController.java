package tw.com.cathaylife.testproject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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

    @RequestMapping("/execLocal")
    @ResponseBody
    public Map<String, Object> execLocal(@RequestParam String mysql_sql, @RequestParam String db2_sql,
            @RequestParam String exec_count) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1/mycat?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("cathaylife");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List test_data = jdbcTemplate.queryForList("select POLICY_NO from dtab0001_ta limit " + exec_count);

        long start = System.currentTimeMillis();
        Iterator it = test_data.iterator();
        int count = 0;
        while (it.hasNext()) {
            Map<String, String> map = (Map<String, String>) it.next();
            String POLICY_NO = map.get("POLICY_NO");
            System.err.println("POLICY_NO = " + POLICY_NO);
            count += jdbcTemplate.queryForList(mysql_sql, new Object[] {POLICY_NO}).size();
        }
        long end = System.currentTimeMillis();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("exec_time", (end - start) + " ms");
        resultMap.put("result", "總筆數：" + count);
        return resultMap;
    }

    @RequestMapping("/execMySQL")
    @ResponseBody
    public Map<String, Object> execMySQL(@RequestParam String mysql_sql, @RequestParam String db2_sql,
            @RequestParam String exec_count) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://mysql-stateful-0.mysql.test-namespace.svc.cluster.local/mycat?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("cathaylife");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List test_data = jdbcTemplate.queryForList("select POLICY_NO from dtab0001_ta limit " + exec_count);

        long start = System.currentTimeMillis();
        Iterator it = test_data.iterator();
        int count = 0;
        while (it.hasNext()) {
            Map<String, String> map = (Map<String, String>) it.next();
            String POLICY_NO = map.get("POLICY_NO");
            System.err.println("POLICY_NO = " + POLICY_NO);
            count += jdbcTemplate.queryForList(mysql_sql, new Object[] {POLICY_NO}).size();
        }
        long end = System.currentTimeMillis();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("exec_time", (end - start) + " ms");
        resultMap.put("result", "總筆數：" + count);
        return resultMap;
    }

    @RequestMapping("/execMyCat")
    @ResponseBody
    public Map<String, Object> execMyCat(@RequestParam String mysql_sql, @RequestParam String db2_sql,
            @RequestParam String exec_count) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://mycat-service.test-namespace.svc.cluster.local:8066/mycat?useSSL=false");
        dataSource.setUsername("ebaf");
        dataSource.setPassword("ebaf");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List test_data = jdbcTemplate.queryForList("select POLICY_NO from dtab0001_ta limit " + exec_count);

        long start = System.currentTimeMillis();
        Iterator it = test_data.iterator();
        int count = 0;
        while (it.hasNext()) {
            Map<String, String> map = (Map<String, String>) it.next();
            String POLICY_NO = map.get("POLICY_NO");
            System.err.println("POLICY_NO = " + POLICY_NO);
            count += jdbcTemplate.queryForList(mysql_sql, new Object[] {POLICY_NO}).size();
        }
        long end = System.currentTimeMillis();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("exec_time", (end - start) + " ms");
        resultMap.put("result", "總筆數：" + count);
        return resultMap;
    }

    @RequestMapping("/execDB2")
    @ResponseBody
    public Map<String, String> execDB2(@RequestParam String mysql_sql, @RequestParam String db2_sql,
            @RequestParam String exec_count) {
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("exec_time", "32.000");
        return resultMap;
    }

    @RequestMapping("/execTest")
    @ResponseBody
    public Map<String, String> execTest(@RequestParam String mysql_sql, @RequestParam String db2_sql,
            @RequestParam String exec_count) {
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("exec_time", "33.000");
        return resultMap;
    }
}