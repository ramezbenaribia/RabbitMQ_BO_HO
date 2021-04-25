package ho;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeoutException;





public class Ho {
    public final static String QUEUE_NAME="product";

    public static void main(String[] args) throws IOException, TimeoutException, SQLException {

        DBInsertService dbInsertService = new DBInsertService();

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String receivedMessage = new String(delivery.getBody(),"UTF-8");
            System.out.println("message" + receivedMessage);
            List<Product> productList = deserialize(receivedMessage);
            System.out.println(productList);
            try {
                dbInsertService.insert(productList);
            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }
            try {
                new Table();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        };


        channel.basicConsume(QUEUE_NAME,false,deliverCallback,consumerTag -> {
            System.out.println("ERROR");
        });

    }

    public static List<Product> deserialize(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(message, new TypeReference<List<Product>>(){});

    }
}
