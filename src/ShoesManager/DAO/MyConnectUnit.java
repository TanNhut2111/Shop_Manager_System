package ShoesManager.DAO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Kết nối SQL nâng cao (SQL Server)
 */
public class MyConnectUnit {
    private SQLServerConnection connect;

    // Kết nối mặc định với SQL Server (sa/12345)
    public MyConnectUnit() {
        connect = new SQLServerConnection("localhost", "sa", "12345", "qlcuahanggiaydb");
    }

    // Khởi tạo với tham số
    public MyConnectUnit(String Host, String Username, String Password, String Database) {
        connect = new SQLServerConnection(Host, Username, Password, Database);
    }

    // Select * from Table where Condition order by OrderBy
    public ResultSet Select(String TableName, String Condition, String OrderBy) throws Exception {
        StringBuilder query = new StringBuilder("SELECT * FROM " + TableName);
        this.AddCondition(query, Condition);
        this.AddOrderBy(query, OrderBy);
        query.append(";");
        return this.connect.excuteQuery(query.toString());
    }

    public ResultSet Select(String TableName, String Condition) throws Exception {
        return this.Select(TableName, Condition, null);
    }

    public ResultSet Select(String TableName) throws Exception {
        return this.Select(TableName, null, null);
    }

    private void AddCondition(StringBuilder query, String Condition) {
        if (Condition != null) {
            query.append(" WHERE " + Condition);
        }
    }

    private void AddOrderBy(StringBuilder query, String OrderBy) {
        if (OrderBy != null) {
            query.append(" ORDER BY " + OrderBy);
        }
    }

    public boolean Insert(String TableName, HashMap<String, Object> ColumnValues) throws Exception {
        StringBuilder query = new StringBuilder("INSERT INTO " + TableName);
        StringBuilder valueInsert = new StringBuilder();

        query.append("(");
        for (String key : ColumnValues.keySet()) {
            Object value = ColumnValues.get(key);
            query.append(key + ",");
            if (value instanceof String) {
                // thêm N trước chuỗi Unicode
                valueInsert.append("N'" + value.toString().replace("'", "''") + "',");
            } else {
                valueInsert.append(value + ",");
            }
        }
        query.deleteCharAt(query.length() - 1);
        valueInsert.deleteCharAt(valueInsert.length() - 1);

        query.append(") VALUES(" + valueInsert.toString() + ")");
        query.append(";");
        System.out.println(query);
        return this.connect.executeUpdate(query.toString()) > 0;
    }


    public boolean Update(String TableName, HashMap<String, Object> ColumnValues, String Condition) throws Exception {
        StringBuilder query = new StringBuilder("UPDATE " + TableName + " SET ");
        for (String key : ColumnValues.keySet()) {
            Object val = ColumnValues.get(key);
            query.append(key + " = ");
            if (val instanceof String) {
                query.append("N'" + val.toString().replace("'", "''") + "',");
            } else {
                query.append(val + ",");
            }
        }
        query.deleteCharAt(query.length() - 1);
        this.AddCondition(query, Condition);
        query.append(";");
        System.out.println(query);
        
        return this.connect.executeUpdate(query.toString()) > 0;
    }

    public boolean Delete(String TableName, String Condition) throws Exception {
        StringBuilder query = new StringBuilder("DELETE FROM " + TableName);
        this.AddCondition(query, Condition);
        query.append(";");
        System.out.println(query);
        return this.connect.executeUpdate(query.toString()) > 0;
    }

    public static int getColumnCount(ResultSet result) throws SQLException {
        return result.getMetaData().getColumnCount();
    }

    public static String[] getColumnName(ResultSet result) throws SQLException {
        ResultSetMetaData rsMetaData = result.getMetaData();
        int ColumnCount = rsMetaData.getColumnCount();
        String[] list = new String[ColumnCount];
        for (int i = 0; i < ColumnCount; i++) {
            list[i] = rsMetaData.getColumnName(i + 1); // SQL Server index từ 1
        }
        return list;
    }

    public void Close() throws Exception {
        this.connect.Close();
    }

    public ResultSet excuteQuery(String query) throws Exception {
    return this.connect.excuteQuery(query);
}
}
