package tools;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import entity.UserDTO;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class TokenChecker {
    /**
     * Global instance of the HTTP transport.
     */
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /**
     * Global instance of the JSON factory.
     */
    static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private final static String CLIENT_WEB_ID = "407580017200-0hu9t079pn3476scpk39n3gs9m29q59f.apps.googleusercontent.com";
    private final static String CLIENT_ANDROID_ID = "1004804260320-a7cvfc02hck8rikflvbhjg8um3appmf7.apps.googleusercontent.com";
    private String endUserFlag="WEB";



    public UserDTO tokenExtraction(String idTokenString) throws GeneralSecurityException, IOException {
        System.out.println("here in token extracxtion");
        UserDTO userDTO=null;
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(HTTP_TRANSPORT, JSON_FACTORY)
                .setAudience(Collections.singletonList(CLIENT_WEB_ID))
                .build();
        GoogleIdToken idToken = verifier.verify(idTokenString);

        if(idToken == null){
            GoogleIdTokenVerifier verifierForAndroid = new GoogleIdTokenVerifier.Builder(HTTP_TRANSPORT, JSON_FACTORY)
                    .setAudience(Collections.singletonList(CLIENT_ANDROID_ID))
                    .build();
           idToken = verifierForAndroid.verify(idTokenString);
            endUserFlag="Android";
        }

        if (idToken != null) {
            userDTO =new UserDTO();
            userDTO.setEndUserFlag(this.endUserFlag);
            GoogleIdToken.Payload payload = idToken.getPayload();
            // Print user identifier
            String userId = payload.getSubject();
            userDTO.setSerialnumber(payload.getSubject());
            System.out.println("User ID: " + userId);
            userDTO.setName((String) payload.get("name"));
            System.out.println("User name: " + (String) payload.get("name"));
            userDTO.setFamily((String) payload.get("family_name"));
            System.out.println("User family: " + (String) payload.get("family_name"));
            userDTO.setEmail(payload.getEmail());
            System.out.println("User email: " + payload.getEmail());
       
//            System.out.println("serial number: "+payload.get("sub"));

        } else {
            System.out.println("Invalid ID token.");
        }
    return userDTO;
    }
}