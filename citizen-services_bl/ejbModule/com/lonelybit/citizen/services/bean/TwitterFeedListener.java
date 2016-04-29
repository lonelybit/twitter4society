package com.lonelybit.citizen.services.bean;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: TwitterFeedListener
 */
@MessageDriven(
		activationConfig = {
				@ActivationConfigProperty(propertyName="destination", propertyValue="queue/TwitterFeedListener"),
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		})
public class TwitterFeedListener implements MessageListener {

    /**
     * Default constructor.
     */
    public TwitterFeedListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	System.out.println("----------------");
		System.out.println("Received message : ");
		try {
			System.out.println(((TextMessage) message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
		System.out.println("----------------");

    }

}
