package ShoesManager.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Kết nối SQL Server cơ bản
 */
public class SQLServerConnection {
    String Host = "localhost";
    String Username = "sa";
    String Password = "12345";
    String Database = "qlcuahanggiaydb";
    int Port = 1433; 

    Connection connect = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public SQLServerConnection(String Host, String Username, String Password, String Database) {
        this.Host = Host;
        this.Username = Username;
        this.Password = Password;
        this.Database = Database;
    }

    protected void driveTest() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new Exception("SQL Server JDBC Driver not found");
        }
    }

    protected Connection getConnect() throws Exception {
        if (this.connect == null) {
            // kiểm tra Driver
            driveTest();

            // Tạo url kết nối SQL Server
            String url = "jdbc:sqlserver://" + this.Host + ":" + this.Port
                    + ";databaseName=" + this.Database
                    + ";encrypt=false;trustServerCertificate=true"; 
            try {
                this.connect = DriverManager.getConnection(url, this.Username, this.Password);
            } catch (SQLException e) {
                throw new Exception("Không thể kết nối tới Database: " + url + " - " + e.getMessage());
            }
        }
        return this.connect;
    }

    protected Statement getStatement() throws Exception {
        if (this.statement == null) {
            this.statement = getConnect().createStatement();
        } else {
            if (this.statement.isClosed()) {
                this.statement = getConnect().createStatement();
            }
        }
        return this.statement;
    }

    public ResultSet excuteQuery(String Query) throws Exception {
        try {
            this.resultSet = getStatement().executeQuery(Query);
        } catch (Exception e) {
            throw new Exception("Error excuteQuery " + e.getMessage());
        }
        return this.resultSet;
    }

    public int executeUpdate(String Query) throws Exception {
        int res = Integer.MIN_VALUE;
        try {
            res = getStatement().executeUpdate(Query);
        } catch (Exception e) {
            throw new Exception("Error " + e.getMessage());
        }
        return res;
    }

    public void Close() throws Exception {
        if (this.resultSet != null && !this.resultSet.isClosed()) {
            this.resultSet.close();
            this.resultSet = null;
        }
        if (this.statement != null && !this.statement.isClosed()) {
            this.statement.close();
            this.statement = null;
        }
        if (this.connect != null && !this.connect.isClosed()) {
            this.connect.close();
            this.connect = null;
        }
    }
}
