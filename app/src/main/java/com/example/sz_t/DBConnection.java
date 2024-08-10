package com.example.sz_t;

import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
    public String txtid=null;
    public String txtpwd=null;
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    //5.0.x特用是这样，如果是用的jdbc8.0.x，则是“com.mysql.cj.jdbc.Driver”

    //login是前面所讲的数据库名
    private static final String DBUSER = "root";
    //数据用户名
    private static final String DBPASSWORD = "84667896";
    //数据库用户密码
    private static final String tag="DBConnection";
    //设置日志所用，非关键代码
    public Connection conn=null;
    //数据库连接对象
    public boolean th=false;

    public String DBURL;
    //判断登录状况
    public boolean tp=false;
    public String resconn="null";
    //判断注册状况
    //第一个参数用来上传id、password，请求
    public DBConnection() {

    }
    //获取数据库连接 conn，并返回是否连接成功
    public String getConnection(String ip) {

        try { // 加载数据库驱动类
            Class.forName(DBDRIVER);
            resconn = "驱动加载成功,数据库连接失败！";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            DBURL = "jdbc:mysql://"+ip+"/login?useUnicode=true&useSSL=false&serverTimezone=GMT&sallowPublicKeyRetrieval=true&allowPublicKeyRetrieval=true";
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
            resconn = "驱动加载成功,数据库连接成功！";
        }catch (SQLException e) {
            //其他异常处理逻辑
        }catch(Exception e){
            System.out.println("Exception");}
        catch(Throwable e) {
            e.printStackTrace();
        }
        /*
        catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        /*
        try { // 通过访问数据库的URL获取数据库连接对象
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
            resconn  ="数据库连接成功!";
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return resconn;
    }
    public  boolean Login_GO(String txtid, String txtpwd) {
        PreparedStatement stmt = null;
        try {
            //getConnection();
            String sql = "select * from user where id = ? and password = ?";
            Log.d("he",sql);
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,txtid);
            preparedStatement.setString(2,txtpwd);
            ResultSet rs = preparedStatement.executeQuery();
            //取得操作结果，true或false
            th=rs.next();
            //打印
            rs.close();
            preparedStatement.close();;
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public  boolean RegiSql(String txtid, String txtpwd){
        int u=0;
        try {
            String sql = "insert into user values(?,?)";
            Log.d(tag,sql);
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,txtid);//id
            pst.setString(2,txtpwd);//密码
            pst.execute();//类似于结束，提交请求
            tp=true;
            Log.d("OK","插入成功啦");
            pst.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
