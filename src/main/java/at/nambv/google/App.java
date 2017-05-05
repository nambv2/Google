package at.nambv.google;

import java.io.IOException;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.blogger.Blogger;
import com.google.api.services.blogger.Blogger.Posts.Get;
import com.google.api.services.blogger.Blogger.Posts.Insert;
import com.google.api.services.blogger.model.Post;

/**
 * Hello world!
 *
 */
public class App {
	private static HttpTransport HTTP_TRANSPORT;
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	public static void main(String[] args) {
		String BLOG_ID = "6616750693444969522";
		String POST_ID = "8204492164026074197";
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			Credential credential = OAuth2Native.authorize(HTTP_TRANSPORT, JSON_FACTORY);
			Blogger blogger = new Blogger.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
					.setApplicationName("Blogger-PostsGet-Snippet/1.0").build();
			
			
			/*Get postsGetAction = blogger.posts().get(BLOG_ID, POST_ID);
			postsGetAction.setFields("author/displayName,content,published,title,url");
			Post post = postsGetAction.execute();*/
			// Construct a post to insert
			Post content = new Post();
			content.setTitle("A test post");
			content.setContent("With <code>HTML</code> content");

			// The request action.
			Insert postsInsertAction = blogger.posts()
			        .insert(BLOG_ID, content);

			// Restrict the result content to just the data we need.
			postsInsertAction.setFields("author/displayName,content,published,title,url");

			// This step sends the request to the server.
			Post post = postsInsertAction.execute();

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
