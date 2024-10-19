import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Query {

    public static void insert(int id, String name, String lastname, String phone, String email, String street, String city, String state, int zipcode) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/clients?user=root&password=");
            Statement st = con.createStatement();
            String query = "insert into customers values (%d, %s, %s, %s, %s, %s, %s, %s, %d);";
            st.execute(String.format(query, id, name, lastname, phone, email, street, city, state, zipcode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void get() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/clients?user=root&password=");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from customers");
            while (rs.next()) {
                for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                    System.out.print(rs.getObject(i) + "  ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
