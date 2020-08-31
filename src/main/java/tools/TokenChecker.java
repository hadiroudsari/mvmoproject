package tools;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
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

    private final String CLIENT_ID = "407580017200-0hu9t079pn3476scpk39n3gs9m29q59f.apps.googleusercontent.com";


    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(HTTP_TRANSPORT, JSON_FACTORY)
            .setAudience(Collections.singletonList(CLIENT_ID))
            .build();

    public void tokenExtraction(String idTokenString) throws GeneralSecurityException, IOException {
        System.out.println("here in token extracxtion");
        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);
            System.out.println("User name: " + (String) payload.get("name"));
            System.out.println("User family: " + (String) payload.get("family_name"));
            System.out.println("User email: " + payload.getEmail());

        } else {
            System.out.println("Invalid ID token.");
        }

    }
}