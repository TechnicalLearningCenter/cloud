package com.pankaj.aws.lambda;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by pankaj on 12/13/2018.
 */
public class SQSMessageSender {

    AmazonSQSClient sqsClient = new AmazonSQSClient(new AWSCredentialsProvider() {
        public AWSCredentials getCredentials() {
            try {
                JSONParser parser = new JSONParser();
                JSONObject obj = (JSONObject) parser.parse(new FileReader("d://temp//aws.json"));
                String userName = (String) obj.get("userName");
                String password = (String) obj.get("password");
                System.out.println(userName);
                System.out.println(password);
                return new BasicAWSCredentials(userName, password);
            } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException");
                return new BasicAWSCredentials("", "");
            } catch (ParseException e) {
                System.out.println("ParseException");
                return new BasicAWSCredentials("", "");
            } catch (IOException e) {
                System.out.println("IOException");
                return new BasicAWSCredentials("", "");
            }

        }

        public void refresh() {
        }
    });

    public static void main(String[] args) throws JsonProcessingException {
        String sqsMessage = new ObjectMapper().writeValueAsString(new Request(101, "Hello message"));
        new SQSMessageSender().send(new Message(sqsMessage, "text message"));
    }

    public void send(Message message) throws AmazonClientException {
        sqsClient.setRegion(Region.getRegion(Regions.US_EAST_1));

        SendMessageRequest request = new SendMessageRequest();
        request.setQueueUrl("https://sqs.us-east-1.amazonaws.com/292661884468/sales-order");
        request.setMessageBody(message.getTxtMessage());
        System.out.println(sqsClient.sendMessage(request).getMessageId());
    }
}