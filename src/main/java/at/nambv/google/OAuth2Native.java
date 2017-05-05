/**
 * 
 */
package at.nambv.google;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.FileCredentialStore;
import com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.blogger.Blogger;
import com.google.api.services.blogger.Blogger.Posts.Get;
import com.google.api.services.blogger.model.Post;
import com.google.api.services.oauth2.Oauth2;

/**
 * @author nambv
 *
 * May 4, 2017
 */
public class OAuth2Native {
	/*public static Credential authorize(HttpTransport transport, JsonFactory jsonFactory, 
		      VerificationCodeReceiver receiver, Iterable<String> scopes) throws Exception {
			File cred = new File("G:/credentials.dat"); 
	       
	      FileCredentialStore fcs = new FileCredentialStore(cred , jsonFactory);
		  // load client secrets
		  GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory,
		      new InputStreamReader(OAuth2Native.class.getResourceAsStream("src/client_secrets.json")));
		  // set up authorization code flow
		  GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				  transport, jsonFactory, clientSecrets,(Collection<String>) scopes).setCredentialStore(
		    		  fcs).build();
		  // authorize
		  VerificationCodeReceiver r = new LocalServerReceiver();
		  return new AuthorizationCodeInstalledApp(flow, r).authorize("user");
		}*/
	 /**
	   * Be sure to specify the name of your application. If the application name is {@code null} or
	   * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
	   */
	  private static final String APPLICATION_NAME = "Blogger-PostsGet-Snippet/1.0";

	  /** Directory to store user credentials. */
	  private static final java.io.File DATA_STORE_DIR =
	      new java.io.File(System.getProperty("user.home"), ".store/oauth2_sample");
	  
	  
	  /**
	   * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
	   * globally shared instance across your application.
	   */
	  private static FileDataStoreFactory dataStoreFactory;

	  /** Global instance of the HTTP transport. */
	  private static HttpTransport httpTransport;

	  /** Global instance of the JSON factory. */
	  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	  /** OAuth 2.0 scopes. */
	  private static final List<String> SCOPES = Arrays.asList(
	      "https://www.googleapis.com/auth/userinfo.profile",
	      "https://www.googleapis.com/auth/userinfo.email");

	  private static Oauth2 oauth2;
	  private static GoogleClientSecrets clientSecrets;
	/** Authorizes the installed application to access user's protected data. */
	  public static Credential authorize() throws Exception {
	    // load client secrets
		dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
	    clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
	        new InputStreamReader(OAuth2Native.class.getResourceAsStream("/client_secrets.json")));
	    if (clientSecrets.getDetails().getClientId().startsWith("Enter")
	        || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
	      System.out.println("Enter Client ID and Secret from https://code.google.com/apis/console/ "
	          + "into oauth2-cmdline-sample/src/main/resources/client_secrets.json");
	      System.exit(1);
	    }
	    System.out.println("test");
	    // set up authorization code flow
	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	        httpTransport, JSON_FACTORY, clientSecrets, SCOPES).setDataStoreFactory(
	        dataStoreFactory).build();
	    // authorize
	    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	  }
	  
	  public static void main(String[] args) {
		// The BlogId for the http://buzz.blogger.com/ blog.
	    	String BUZZ_BLOG_ID = "6616750693444969522";

	    	// The PostId for a buzz post with comments.
	    	String BUZZ_POST_ID = "8204492164026074197";
		    try {
		      httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		      dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
		      // authorization
		      Credential credential = authorize();
		      // set up global Oauth2 instance
		      /*oauth2 = new Oauth2.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(
		          APPLICATION_NAME).build();*/
		      // success!
		      Blogger blogger = new Blogger.Builder(httpTransport, JSON_FACTORY,credential)
  	        .setApplicationName("Blogger-PostsGet-Snippet/1.0")
  	        .build();
		   // The request action.
		    	Get postsGetAction = blogger.posts().get(BUZZ_BLOG_ID, BUZZ_POST_ID);

		    	// Restrict the result content to just the data we need.
		    	postsGetAction.setFields("author/displayName,content,published,title,url");

		    	// This step sends the request to the server.
		    	Post post = postsGetAction.execute();

		    	// Now we can navigate the response.
		    	System.out.println("Title: " + post.getTitle());
		    	System.out.println("Author: " + post.getAuthor().getDisplayName());
		    	System.out.println("Published: " + post.getPublished());
		    	System.out.println("URL: " + post.getUrl());
		    	System.out.println("Content: " + post.getContent());
		    } catch (IOException e) {
		      System.err.println(e.getMessage());
		    } catch (Throwable t) {
		      t.printStackTrace();
		    }
		  } 
}
