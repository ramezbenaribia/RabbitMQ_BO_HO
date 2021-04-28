package ho;

import java.sql.SQLException;
import java.util.List;

import java.util.ArrayList;

import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;



public class Table {
    JFrame f;
    public static DBRetrieve dbRetrieve;
    private DefaultTableModel tableModel;
    private JTable jt;
    Table() throws SQLException {
        dbRetrieve = new DBRetrieve();
        List<Product> productList = dbRetrieve.retreive();


        f=new JFrame("HO Database");

        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();

        for (int i = 0; i < productList.size(); i++) {

            values.add(new String[] {
                    ""+productList.get(i).getDate()+"",
                    ""+productList.get(i).getRegion()+"",
                    ""+productList.get(i).getProduct()+"" ,
                    ""+productList.get(i).getQty()+"" ,
                    ""+productList.get(i).getCost()+"" ,
                    ""+productList.get(i).getAmt()+"" ,
                    ""+productList.get(i).getTax()+"" ,
                    ""+productList.get(i).getTotal()+""
            });
        }


        columns.add("Date");
        columns.add("Region");
        columns.add("Product");
        columns.add("Qty");
        columns.add("Cost");
        columns.add("Amt");
        columns.add("Tax");
        columns.add("Total");



        tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        jt=new JTable(tableModel);



        jt.setBounds(30,40,800,800);
        JScrollPane sp = new JScrollPane(jt);

        f.add(sp);

        f.setSize(800,800);
        f.setVisible(true);
    }
     public void AddRows( List<Product> productList){
         for(int i=0; i<productList.size(); i++) {
             Product p = productList.get(i);
             String[] rowData = {
                     "" + p.getDate() + "" ,
                     "" + p.getRegion() + "",
                     "" + p.getProduct() + "",
                     "" + p.getQty() + "",
                     "" + p.getCost() + "",
                     "" + p.getAmt() + "",
                     "" + p.getTax() + "",
                     "" + p.getTotal() + ""
             } ;

             tableModel.addRow(rowData);
             jt.validate();
         }


    }

    public static void main(String[] args) throws SQLException {
        new Table();
    }}