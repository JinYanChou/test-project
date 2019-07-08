package tw.com.cathaylife.testproject.module;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DBModule {

    @Autowired
    @Qualifier("localJdbcTemplate")
    JdbcTemplate localJdbcTemplate;

    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    JdbcTemplate mysqlJdbcTemplate;

    @Autowired
    @Qualifier("mycatJdbcTemplate")
    JdbcTemplate mycatJdbcTemplate;

    @Autowired
    @Qualifier("db2JdbcTemplate")
    JdbcTemplate db2JdbcTemplate;

    public Map<String, Object> queryLocal(String table_name, int exec_count) {
        String sql_count = "select POLICY_NO from " + table_name + " limit " + exec_count;
        String sql = "select POLICY_NO from " + table_name + " where POLICY_NO = ?";
        return this.query(localJdbcTemplate, sql_count, sql);
    }

    public Map<String, Object> queryMySQL(String table_name, int exec_count) {
        String sql_count = "select POLICY_NO from " + table_name + " limit " + exec_count;
        String sql = "select POLICY_NO from " + table_name + " where POLICY_NO = ?";
        return this.query(mysqlJdbcTemplate, sql_count, sql);
    }

    public Map<String, Object> queryMyCat(String table_name, int exec_count) {
        String sql_count = "select POLICY_NO from " + table_name + " limit " + exec_count;
        String sql = "select POLICY_NO from " + table_name + " where POLICY_NO = ?";
        return this.query(mycatJdbcTemplate, sql_count, sql);
    }

    public Map<String, Object> queryDB2(String table_name, int exec_count) {
        String sql_count = "select POLICY_NO from dbab." + table_name + " fetch first " + exec_count + " rows only";
        String sql = "select POLICY_NO from dbab." + table_name + " where POLICY_NO = ?";
        return this.query(db2JdbcTemplate, sql_count, sql);
    }

    private Map<String, Object> query(JdbcTemplate jdbcTemplate, String sql_count, String sql) {
        List test_data = jdbcTemplate.queryForList(sql_count);

        long start = System.currentTimeMillis();
        Iterator it = test_data.iterator();
        int count = 0;
        while (it.hasNext()) {
            Map<String, Object> map = (Map<String, Object>) it.next();
            Object POLICY_NO = map.get("POLICY_NO");
            count += jdbcTemplate.queryForList(sql, new Object[] { POLICY_NO }).size();
        }
        long end = System.currentTimeMillis();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("exec_time", (end - start) + " ms");
        resultMap.put("result", "總筆數：" + count);

        return resultMap;
    }
}