package ho;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBRetrieve {

    public String url = "jdbc:mysql://localhost:3306/ho";
    public String user="root";
    public String password = "";
    public String query = "SELECT * FROM product_sale";
    public List<Product> retreive() throws SQLException{
        List<Product> res = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
        ) {

            while(rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setDate(rs.getDate("date"));
                product.setRegion(rs.getString("region"));
                product.setProduct(rs.getString("product"));
                product.setQty(rs.getInt("qty"));
                product.setCost(rs.getFloat("cost"));
                product.setAmt(rs.getDouble("amt"));
                product.setTax(rs.getFloat("tax"));
                product.setTotal(rs.getDouble("total"));
                res.add(product);
            }

            return res;
        }

    }
}
