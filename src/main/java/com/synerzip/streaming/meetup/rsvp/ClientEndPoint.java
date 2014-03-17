/**
 * 
 */
package com.synerzip.streaming.meetup.rsvp;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

public class ClientEndPoint {
	private static CountDownLatch messageLatch;

	public static void main(String[] args) {
		try {
			messageLatch = new CountDownLatch(100);

			final ClientEndpointConfig cec = ClientEndpointConfig.Builder
					.create().build();

			ClientManager client = ClientManager.createClient();
			client.connectToServer(new Endpoint() {

				@Override
				public void onOpen(Session session, EndpointConfig config) {
					try {
						session.addMessageHandler(new MessageHandler.Whole<String>() {

							public void onMessage(String message) {
								System.out.println("Received message: "
										+ message);
								messageLatch.countDown();
							}
						});
						session.getBasicRemote().sendText("");
						System.out.println("Send Blank");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}, cec, new URI("ws://stream.meetup.com/2/rsvps"));
			messageLatch.await(10, TimeUnit.SECONDS);
			System.out.println("Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
