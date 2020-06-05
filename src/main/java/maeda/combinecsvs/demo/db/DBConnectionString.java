package maeda.combinecsvs.demo.db;

public class DBConnectionString {
    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String hostname;
    Integer port;
    String dbname;
    String username;
    String password;

    public DBConnectionString() {

    }

    public DBConnectionString(String hostname, Integer port, String dbname, String username, String password) {
        this.hostname = hostname;
        this.port = port;
        this.dbname = dbname;
        this.username = username;
        this.password = password;
    }
}
