package bo2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.Timer;
import java.util.TimerTask;






public class Bo2 extends TimerTask {
    public final static String QUEUE_NAME="product_sale";
    public static DBRetrieve dbRetrieveService;
    public static DBUpdate dbUpdateService;

    public static MyFrame f ;

    public Bo2() throws SQLException, JsonProcessingException {
        dbRetrieveService = new DBRetrieve();
        dbUpdateService = new DBUpdate();


        List<Product> productList = dbRetrieveService.retreive();

        String message = serialize(productList);


        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try(Connection connection = connectionFactory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);

            channel.basicPublish("",QUEUE_NAME,null, message.getBytes());
            System.out.println(" [x] sent '"+message+" '");

            dbUpdateService.update(productList);

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    public void run() {
        Bo2 bo2 = new Bo2();
        System.out.println("BO is updated");
    }

    public static String serialize(List<Product> productList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(productList);
    }





    public static void main(String[] args) throws IOException, TimeoutException, SQLException {

        f = new MyFrame();

        Timer timer = new Timer();

        Bo2 bo2 = new Bo2();

        timer.schedule(bo2, 1000,3000);
    }
}
