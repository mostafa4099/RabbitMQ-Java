package com.mostafa;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @File com.mostafa.Producer.java: RabbitMQ-Producer
 * @CreationDate 7/26/2022 2:43 PM
 */
public class Producer {
    private static String QUEUE = "MyFirstQueue";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE, false, false, false, null);

            Scanner input = new Scanner(System.in);
            String message;
            do {
                System.out.println("Enter message: ");
                message = input.nextLine();
                channel.basicPublish("", QUEUE, null, message.getBytes());
            } while (!message.equalsIgnoreCase("Quit"));
        }
    }
}
