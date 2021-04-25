package bo;

//

import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static java.lang.Integer.parseInt;

class MyFrame
        extends JFrame
        implements ActionListener {

    public static DBInsert dbInsertService;
    public static DBRetrieveAll dbRetrieveAll;
    public static List<Product> productList;
    public static List<String> columns;
    public static List<String[]> values;
    public static DefaultTableModel  tableModel;
    public static JTable jt;


    // Components of the Form
    private Container c;

    private final JLabel title;
    private final JLabel name;
    private final JTextField tname;
    private final JLabel reg;
    private final JTextField treg;
    private final JLabel qty;
    private JTextField tqty;
    private JLabel cost;
    private JTextField tcost;

    private final JLabel amt;
    private final JTextField tamt;
    private final JLabel tax;
    private final JTextField ttax;
    private final JLabel total;
    private final JTextField ttotal;

    private final JLabel dob;
    private final JComboBox date;
    private final JComboBox month;
    private final JComboBox year;

    private final JCheckBox term;
    private final JButton sub;
    private final JButton reset;
    private final JTextArea tout;
    private  JLabel res;

    private  JLabel resp;
    private final JTextArea resadd;


    private String dates[]
            = {"1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31"};
    private String months[]
            = {"Jan", "feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sup", "Oct", "Nov", "Dec"};
    private String years[]
            = {"1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019", "2020", "2021"};

    // constructor, to initialize the components
    // with default values.
    public MyFrame() throws SQLException {


        setTitle("Registration Form");
        setBounds(600, 90, 1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setResizable(false);

        c = getContentPane();
//        c.setLayout(null);


        dbRetrieveAll = new DBRetrieveAll();
        productList = dbRetrieveAll.retreive();

        columns = new ArrayList<String>();
        values = new ArrayList<String[]>();

        for (int i = 0; i < productList.size(); i++) {

            values.add(new String[]{
                    "" + productList.get(i).getId() + "",
                    "" + productList.get(i).getDate() + "",
                    "" + productList.get(i).getRegion() + "",
                    "" + productList.get(i).getProduct() + "",
                    "" + productList.get(i).getQty() + "",
                    "" + productList.get(i).getCost() + "",
                    "" + productList.get(i).getAmt() + "",
                    "" + productList.get(i).getTax() + "",
                    "" + productList.get(i).getTotal() + ""
            });
        }


        columns.add("ID");
        columns.add("Date");
        columns.add("Region");
        columns.add("Product");
        columns.add("Qty");
        columns.add("Cost");
        columns.add("Amt");
        columns.add("Tax");
        columns.add("Total");


        tableModel = new DefaultTableModel(values.toArray(new Object[][]{}), columns.toArray());

        jt = new JTable(tableModel);
        tableModel = (DefaultTableModel)jt.getModel();


        jt.setBounds(0, 20, 500, 400);
        jt.setFont(new Font("Arial", Font.PLAIN, 13));


        jt.setFillsViewportHeight(true);
        c.setLayout(new BorderLayout());
        c.add(jt.getTableHeader(), BorderLayout.PAGE_START);
        c.add(jt, BorderLayout.CENTER);



        title = new JLabel("Registration Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(600, 30);
        c.add(title);

        name = new JLabel("Product Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(200, 20);
        name.setLocation(550, 100);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(700, 100);
        c.add(tname);

        reg = new JLabel("Region");
        reg.setFont(new Font("Arial", Font.PLAIN, 20));
        reg.setSize(100, 20);
        reg.setLocation(550, 150);
        c.add(reg);

        treg = new JTextField();
        treg.setFont(new Font("Arial", Font.PLAIN, 15));
        treg.setSize(150, 20);
        treg.setLocation(700, 150);
        c.add(treg);


        dob = new JLabel("Date");
        dob.setFont(new Font("Arial", Font.PLAIN, 20));
        dob.setSize(100, 20);
        dob.setLocation(550, 200);
        c.add(dob);

        date = new JComboBox(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(50, 20);
        date.setLocation(700, 200);
        c.add(date);

        month = new JComboBox(months);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(60, 20);
        month.setLocation(750, 200);
        c.add(month);

        year = new JComboBox(years);
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(60, 20);
        year.setLocation(820, 200);
        c.add(year);


        qty = new JLabel("Quantity");
        qty.setFont(new Font("Arial", Font.PLAIN, 20));
        qty.setSize(100, 20);
        qty.setLocation(550, 250);
        c.add(qty);

        tqty = new JTextField();
        tqty.setFont(new Font("Arial", Font.PLAIN, 15));
        tqty.setSize(150, 20);
        tqty.setLocation(700, 250);
        c.add(tqty);

        cost = new JLabel("Cost");
        cost.setFont(new Font("Arial", Font.PLAIN, 20));
        cost.setSize(100, 20);
        cost.setLocation(550, 300);
        c.add(cost);

        tcost = new JTextField();
        tcost.setFont(new Font("Arial", Font.PLAIN, 15));
        tcost.setSize(150, 20);
        tcost.setLocation(700, 300);
        c.add(tcost);

        amt = new JLabel("Amount");
        amt.setFont(new Font("Arial", Font.PLAIN, 20));
        amt.setSize(100, 20);
        amt.setLocation(550, 350);
        c.add(amt);

        tamt = new JTextField();
        tamt.setFont(new Font("Arial", Font.PLAIN, 15));
        tamt.setSize(150, 20);
        tamt.setLocation(700, 350);
        c.add(tamt);

        tax = new JLabel("Tax");
        tax.setFont(new Font("Arial", Font.PLAIN, 20));
        tax.setSize(100, 20);
        tax.setLocation(550, 400);
        c.add(tax);

        ttax = new JTextField();
        ttax.setFont(new Font("Arial", Font.PLAIN, 15));
        ttax.setSize(150, 20);
        ttax.setLocation(700, 400);
        c.add(ttax);

        total = new JLabel("Total");
        total.setFont(new Font("Arial", Font.PLAIN, 20));
        total.setSize(100, 20);
        total.setLocation(550, 450);
        c.add(total);

        ttotal = new JTextField();
        ttotal.setFont(new Font("Arial", Font.PLAIN, 15));
        ttotal.setSize(150, 20);
        ttotal.setLocation(700, 450);
        c.add(ttotal);


        term = new JCheckBox("Accept Terms And Conditions.");
        term.setFont(new Font("Arial", Font.PLAIN, 15));
        term.setSize(250, 20);
        term.setLocation(600, 500);
        c.add(term);

        resp = new JLabel("");
        resp.setFont(new Font("Arial", Font.PLAIN, 20));
        resp.setSize(500, 20);
        resp.setLocation(550, 580);
        c.add(resp);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(650, 550);
        sub.addActionListener(this);
        c.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(770, 550);
        reset.addActionListener(this);
        c.add(reset);


        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(150, 25);
        res.setLocation(500, 550);
        c.add(res);



        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(300, 400);
        tout.setLocation(700, 700);
        tout.setLineWrap(true);
        tout.setEditable(false);
//        c.add(tout);

        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(700, 700);
        resadd.setLineWrap(true);
//        c.add(resadd);

        setVisible(true);
    }

    // method actionPerformed()
    // to get the action performed
    // by the BO and act accordingly
    @SneakyThrows
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            if (term.isSelected()) {
                String data1;
                String data
                        = "Name : "
                        + tname.getText() + "\n"
                        + "Mobile : "
                        + treg.getText() + "\n";

                String data2
                        = "DOB : "
                        + (String) date.getSelectedItem()
                        + "/" + (String) month.getSelectedItem()
                        + "/" + (String) year.getSelectedItem()
                        + "\n";

                String data3 = "Address : " + tamt.getText();
                tout.setText(data + data2 + data3);
                tout.setEditable(false);

                Date d = new Date(parseInt((String) year.getSelectedItem()), month.getSelectedIndex(), parseInt((String) date.getSelectedItem()));
                d.setTime(2019);
                Product p = new Product(d,
                        treg.getText(),
                        tname.getText(),
                        parseInt(tqty.getText()),
                        Float.parseFloat(tcost.getText()),
                        Double.parseDouble(tamt.getText()),
                        Float.parseFloat(ttax.getText()),
                        Double.parseDouble(ttotal.getText())

                );
                System.out.println(p);
                dbInsertService = new DBInsert();
                dbInsertService.insert(p);

                resp.setText("Registration Successfully Completed ..");

                tableModel.fireTableDataChanged();
                jt.addNotify();




                productList.add(p);
                values.add(new String[]{
                        "" + p.getId() + "",
                        "" + p.getDate() + "",
                        "" + p.getRegion() + "",
                        "" + p.getProduct() + "",
                        "" + p.getQty() + "",
                        "" + p.getCost() + "",
                        "" + p.getAmt() + "",
                        "" + p.getTax() + "",
                        "" + p.getTotal() + ""
                });



            } else {
                tout.setText("");
                resadd.setText("");
                resp.setText("Please accept the" + " terms & conditions..");
            }
        } else if (e.getSource() == reset) {
            String def = "";
            tname.setText(def);
            treg.setText(def);
            tqty.setText(def);
            tcost.setText(def);
            tamt.setText(def);
            ttax.setText(def);
            ttotal.setText(def);
            resp.setText(def);
            tout.setText(def);
            term.setSelected(false);
            date.setSelectedIndex(0);
            month.setSelectedIndex(0);
            year.setSelectedIndex(0);
            resadd.setText(def);
        }
    }
}

// Driver Code
class Registration {

    public static void main(String[] args) throws Exception {

        MyFrame f = new MyFrame();


    }
}
