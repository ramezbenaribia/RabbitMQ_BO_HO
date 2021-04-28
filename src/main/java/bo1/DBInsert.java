package bo1;

import java.sql.*;

public class DBInsert {
    public String url = "jdbc:mysql://localhost:3306/bo1";
    public String user="root";
    public String password = "";
    public String query = "INSERT INTO product_sale(date, region, product, qty, cost, amt, tax, total ,sent) values(?,?,?,?,?,?,?,?,0)";

    public void insert(Product p) throws SQLException {
        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(query)
        ){

                pst.setDate(1, new Date(p.getDate().getTime()));
                pst.setString(2, p.getRegion());
                pst.setString(3, p.getProduct());
                pst.setInt(4, p.getQty());
                pst.setFloat(5, p.getCost());
                pst.setDouble(6, p.getAmt());
                pst.setFloat(7, p.getTax());
                pst.setDouble(8, p.getTotal());
                pst.executeUpdate();

        }
    }
}
