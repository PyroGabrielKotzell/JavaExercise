import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLTalker {
    private String database;
    private String table;
    private String values;
    private ArrayList<Integer> autoIncKeys;

    public SQLTalker() {
    }

    public SQLTalker(String database) {
        this.database = database;
    }

    public SQLTalker(String database, String table) {
        this.database = database;
        this.table = table;
        this.autoIncKeys = new ArrayList<>();
        gatherValues();
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setTable(String table) {
        this.table = table;
        this.autoIncKeys = new ArrayList<>();
        gatherValues();
    }

    public String getValues() {
        return values;
    }

    public String[] getFormattedValues() {
        String tabVar = getValues();
        String[] values = tabVar.substring(1, tabVar.length() - 1).split(",");
        return values;
    }

    public void addAutoIncKey(int... keyslots) {
        for (int i : keyslots) {
            autoIncKeys.add(i);
        }
        gatherValues();
    }

    private void gatherValues() {
        values = "";
        try {
            Connection con = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/" + database + "?user=root&password=");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from " + table + " limit 1;");
            ResultSetMetaData rsmt = rs.getMetaData();
            values += '(';
            int count = rsmt.getColumnCount();
            for (int i = 1; i < count + 1; i++) {
                if (autoIncKeys.contains(i))
                    continue;
                values += rsmt.getColumnName(i) + ",";
            }
            values = values.substring(0, values.length() - 1) + ')';
        } catch (Exception e) {
            System.out.println("Failed to gather values, table doesn't exist");
            e.printStackTrace();
        }
    }

    public ResultSet select(String fields, String values) {
        try {
            Connection con = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/" + database + "?user=root&password=");
            Statement st = con.createStatement();
            String query = "select " + fields + " from " + table + " where " + values + ";";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                    System.out.print(rs.getObject(i) + "  ");
                }
                System.out.println();
            }
            return rs;
        } catch (Exception e) {
            //System.out.println("Wrong argument: " + e.getLocalizedMessage());
            return null;
        }
    }

    public boolean insert(String args) {
        try {
            Connection con = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/" + database + "?user=root&password=");
            Statement st = con.createStatement();
            String query = "insert into " + table + values + " values (" + args + ");";
            return st.execute(query);
        } catch (Exception e) {
            //System.out.println("Wrong argument: " + e.getLocalizedMessage());
            return false;
        }
    }

    public boolean update(int row, String args) {
        try {
            Connection con = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/" + database + "?user=root&password=");
            Statement st = con.createStatement();
            String formattedSet = "";
            for (Integer integer : autoIncKeys) {
                
            }
            String query = "update " + table + " set " + values + " values " + args + ";";
            return st.execute(query);
        } catch (Exception e) {
            //System.out.println("Wrong argument: " + e.getLocalizedMessage());
            return false;
        }
    }
}
