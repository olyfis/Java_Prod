package com.olympus.box.api;

import java.io.IOException;

import com.box.sdk.BoxAPIConnection;

public class BoxAuth {

	public static String CLIENT_ID = "jwg191xpjewy82dnb201dt5gra4dxak9";
	public static String CLIENT_SECRET = "iUOkLW4vzGhSCHofBu3CPfNyu9OXVQ3w";
	public static String ACCESS_TOKEN = "TmPGpeehcCjhd686SZKYFeMtrVXLRUjg";
	public static String REFRESH_TOKEN = "nNIpgmuZyNA57EEarSCzrwlkP8518TKYjTMGDwHKHprxz03pzxDiIGpU0z6V0eij";
	
	//public static String redirect_uri = "http://0.0.0.0:4567/return";
	//public static String box_redirect = "https://account.box.com/api/oauth2/authorize";
	
	
	public static void main(String[] args) throws IOException {
		
		BoxAPIConnection api = new BoxAPIConnection(CLIENT_ID, CLIENT_SECRET, ACCESS_TOKEN, REFRESH_TOKEN);
	    String refreshToken = api.getRefreshToken();
	    String accessToken = api.getAccessToken();
	    
	    System.out.format("Access Token: %s!\n\n", accessToken);   
	    System.out.format("Refresh Token: %s!\n\n", refreshToken); 

	}
}
