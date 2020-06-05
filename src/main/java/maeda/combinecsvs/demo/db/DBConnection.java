package maeda.combinecsvs.demo.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DBConnection {


    private Connection connection;
    private String dbName;


    public static boolean DEBUG = true;


    public DBConnection(String connectionFile) throws IOException{
        DBConnectionString connStr = fromFile(connectionFile);
        this.dbName = connStr.getDbname();
        this.connection = createDbConnection(connStr.hostname, connStr.port+"", connStr.dbname, connStr.username, connStr.password);
    }

    public DBConnection(String connectionFile, String dbname) throws IOException{
        DBConnectionString connStr = fromFile(connectionFile);
        this.dbName = dbname;
        this.connection = createDbConnection(connStr.hostname, connStr.port+"", dbname, connStr.username, connStr.password);
    }

    public Connection getConnection() {
        return connection;
    }

    public DBConnectionString fromFile(String path) throws IOException {
        InputStream connectionStringFileStream = new FileInputStream(path);
        return new ObjectMapper().readValue(connectionStringFileStream, DBConnectionString.class);
    }

    public Connection createDbConnection(String hostname, String port, String dbname, String username, String password) {
        Connection connection = null;
        if (port.length() > 0) port = ":" + port;
        if (username.length() == 0) username = null;
        if (password.length() == 0) password = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + hostname + port + "/" + dbname, username, password);
        } catch (ClassNotFoundException  e) {
            e.printStackTrace();
        }catch ( SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        if(DEBUG) {
            System.out.println("******************************************************************************************************************************************");
            System.out.println(pstmt.toString());
        }
        return pstmt;
    }

    public ResultSet executeQuery(PreparedStatement pstmt) throws SQLException {
        long stTime = System.currentTimeMillis();

        ResultSet res = pstmt.executeQuery();

        long endTime = System.currentTimeMillis();

        if(DEBUG) {
            System.out.println("Time: "+(endTime-stTime)+" ms");
        }
        return  res;
    }

    public void executeUpdate(PreparedStatement pstmt) throws SQLException {
        long stTime = System.currentTimeMillis();

        pstmt.executeUpdate();
        pstmt.close();

        long endTime = System.currentTimeMillis();

        if(DEBUG){
            System.out.println("Time: "+(endTime-stTime)+" ms");
        }

    }

    public String getDbName() {
        return this.dbName;
    }



}