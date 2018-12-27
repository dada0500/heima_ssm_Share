package test;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.SysLogService;
import com.itheima.ssm.service.impl.SysLogServiceImpl;
import com.itheima.ssm.utils.DateUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

public class TestN {

    @Test
    public void nTest(){
        BCryptPasswordEncoder bpe = new BCryptPasswordEncoder ();
        String user = bpe.encode ("user");
        System.out.println (user);
        System.out.println (bpe.encode ("admin"));
    }

    @Test
    public void nTimeTest() throws Exception {
        Properties props = new Properties();
        InputStream in = TestN.class.getClassLoader ().getResourceAsStream("c3p0.properties");
        props.load(in);
        in.close();

        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass(props.getProperty("driverClass"));
        cpds.setJdbcUrl(props.getProperty("jdbcUrl"));
        cpds.setUser(props.getProperty("user"));
        cpds.setPassword(props.getProperty("password"));
        cpds.setInitialPoolSize(3);
        cpds.setMaxPoolSize(5);
        cpds.setMaxIdleTime(1000);

        JdbcTemplate jdbcTemplate = new JdbcTemplate (cpds);
        List<SysLog> sysLogs = jdbcTemplate.query ("select * from syslog", new BeanPropertyRowMapper<> (SysLog.class));


        Timestamp visitTime = sysLogs.get (0).getVisitTime ();
        String v  = DateUtils.date2String (visitTime,"yyyy-MM-dd HH:mm:ss");
        System.out.println ("------");
        System.out.println (v);
    }



}
