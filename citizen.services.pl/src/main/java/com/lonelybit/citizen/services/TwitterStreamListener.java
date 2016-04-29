/**
 * 
 */
package com.lonelybit.citizen.services;

/**
 * @author prafulljoshi
 *
 */
public class TwitterStreamListener {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("keys");
		String consumerkey = resourceBundle.getString("consumer.key");
    	String consumerSecret = resourceBundle.getString("consumer.secret");
    	String accessToken = resourceBundle.getString("access.token");
    	String accessTokenSecret = resourceBundle.getString("access.token.secret");

    	try {
			TwitterStreamListener.run(consumerkey, consumerSecret, accessToken, accessTokenSecret);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void run(String consumerKey, String consumerSecret, String token, String secret) throws InterruptedException {
	    // Create an appropriately sized blocking queue
	    BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);

	    // Define our endpoint: By default, delimited=length is set (we need this for our processor)
	    // and stall warnings are on.
	    StatusesSampleEndpoint endpoint = new StatusesSampleEndpoint();
	    endpoint.stallWarnings(false);

	    Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);
	    //Authentication auth = new com.twitter.hbc.httpclient.auth.BasicAuth(username, password);

	    // Create a new BasicClient. By default gzip is enabled.
	    BasicClient client = new ClientBuilder()
	            .name("TwitterStreamListener")
	            .hosts(Constants.STREAM_HOST)
	            .endpoint(endpoint)
	            .authentication(auth)
	            .processor(new StringDelimitedProcessor(queue))
	            .build();

	    // Establish a connection
	    client.connect();

	    // Do whatever needs to be done with messages
	    for (int msgRead = 0; msgRead < 1000; msgRead++) {
	      if (client.isDone()) {
	        System.out.println("Client connection closed unexpectedly: " + client.getExitEvent().getMessage());
	        break;
	      }

	      String msg = queue.poll(10, TimeUnit.SECONDS);
	      if (msg == null) {
	        System.out.println("Did not receive a message in 10 seconds");
	      } else {
	        System.out.println(msg);
	      }
	    }

	    client.stop();

	    // Print some stats
	    System.out.printf("The client read %d messages!\n", client.getStatsTracker().getNumMessages());
	  }
}
