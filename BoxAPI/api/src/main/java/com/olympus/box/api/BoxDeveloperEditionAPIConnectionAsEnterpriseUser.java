package com.olympus.box.api;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxUser;
import com.box.sdk.DeveloperEditionEntityType;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;

public final class BoxDeveloperEditionAPIConnectionAsEnterpriseUser {

    private static final String USER_ID = "3739731538";
    private static final int MAX_DEPTH = 1;
    private static final int MAX_CACHE_ENTRIES = 100;

    private static BoxDeveloperEditionAPIConnection api;

    private BoxDeveloperEditionAPIConnectionAsEnterpriseUser() { }

    public static void main(String[] args) throws IOException {
        // Turn off logging to prevent polluting the output.
        Logger.getLogger("com.box.sdk").setLevel(Level.SEVERE);

        //It is a best practice to use an access token cache to prevent unneeded requests to Box for access tokens.
        //For production applications it is recommended to use a distributed cache like Memcached or Redis, and to
        //implement IAccessTokenCache to store and retrieve access tokens appropriately for your environment.
        IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(MAX_CACHE_ENTRIES);

        Reader reader = new FileReader("D:\\\\Box\\\\config.json");
        BoxConfig boxConfig = BoxConfig.readFrom(reader);

        api = new BoxDeveloperEditionAPIConnection(USER_ID, DeveloperEditionEntityType.USER, boxConfig,
            accessTokenCache);
        //api.asUser(USER_ID);
        BoxUser.Info userInfo = BoxUser.getCurrentUser(api).getInfo();
        System.out.format("Welcome, %s!\n\n", userInfo.getName());   
        System.out.format("UserId: %s!\n\n", userInfo.getID());
        System.out.format("ExternalUserId: %s!\n\n", userInfo.getExternalAppUserId());
        System.out.format("LoginId: %s!\n\n", userInfo.getLogin());
        
        String refreshToken = api.getRefreshToken();
	    String accessToken = api.getAccessToken();
	    
	    System.out.format("Access Token: %s!\n\n", accessToken);   
	    System.out.format("Refresh Token: %s!\n\n", refreshToken); 
        
        
    }
}