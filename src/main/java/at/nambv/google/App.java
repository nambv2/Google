package at.nambv.google;

import java.util.Arrays;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.blogger.Blogger;
import com.google.api.services.blogger.Blogger.Posts.Get;
import com.google.api.services.blogger.BloggerScopes;
import com.google.api.services.blogger.model.Post;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	// The BlogId for the http://buzz.blogger.com/ blog.
    	String BUZZ_BLOG_ID = "6616750693444969522";

    	// The PostId for a buzz post with comments.
    	String BUZZ_POST_ID = "8204492164026074197";

    	// Configure the Java API Client for Installed Native App

    	// Configure the Installed App OAuth2 flow.
    	//Credential credential = OAuth2Native.authorize(Arrays.asList(BloggerScopes.BLOGGER));

    	/*// Construct the Blogger API access facade object.
    	Blogger blogger = new Blogger.Builder(HTTP_TRANSPORT, JSON_FACTORY,credential)
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
    	System.out.println("Content: " + post.getContent());*/
    }
}
