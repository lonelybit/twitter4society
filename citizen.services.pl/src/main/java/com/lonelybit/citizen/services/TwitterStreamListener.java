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

		System.out.println("Total Argumants - " + resourceBundle.keySet().size());
		for(String arg : resourceBundle.keySet())
			System.out.println(arg);
	}

}
