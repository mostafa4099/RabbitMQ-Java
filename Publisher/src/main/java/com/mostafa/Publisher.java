package com.mostafa;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @File com.mostafa.Publisher.java: RabbitMQ-Producer
 * @CreationDate 7/26/2022 3:07 PM
 */
public class Publisher {
    private static final String EXCHANGE = "MyExchange";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE, "fanout");
            Scanner input = new Scanner(System.in);
            String message;
            do {
                System.out.println("Enter message: ");
                message = input.nextLine();
                channel.basicPublish(EXCHANGE, "", null, message.getBytes());
            } while (!message.equalsIgnoreCase("Quit"));
        }
    }
}
