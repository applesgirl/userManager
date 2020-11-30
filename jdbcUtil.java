package cn.cyp.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class jdbcUtil {
    //    工具类的抽取
//    定义成员们变量DataSource
    private static DataSource ds;

    static {
        Properties pro = null;
        try {
            //1.加载配置文件
            pro = new Properties();
            InputStream is = jdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
            //2.获取DataSource
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() throws Exception {
        return ds.getConnection();
    }

    //    释放资源
    public static void close(Connection conn, Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;//gc垃圾回收机制会优先处理这些对象
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
                conn = null;//gc垃圾回收机制会优先处理这些对象
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;//gc垃圾回收机制会优先处理这些对象
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;//gc垃圾回收机制会优先处理这些对象
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
                conn = null;//gc垃圾回收机制会优先处理这些对象
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接池的办法
    public static DataSource getDataSource() {
        return ds;
    }
}
