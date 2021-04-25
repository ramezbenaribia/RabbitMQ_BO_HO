package bo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Bo {
    public final static String QUEUE_NAME="product";
    public static DBRetrieve dbRetrieveService;
    public static DBUpdate dbUpdateService;
    public static Registration registration;

    public static void main(String[] args) throws IOException, TimeoutException, SQLException {
        dbRetrieveService = new DBRetrieve();
        dbUpdateService = new DBUpdate();

       MyFrame f = new MyFrame();

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

    public static String serialize(List<Product> productList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(productList);
    }
}
