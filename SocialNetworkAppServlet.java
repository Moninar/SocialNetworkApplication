package socialNetworkApp;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.*;


@SuppressWarnings("serial")
public class SocialNetworkAppServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
//		Entity book = new Entity("Book");
//		book.setProperty("AB", 1);
//		book.setProperty("QQQ", 4);
//		book.setProperty("CD", 2);
//		book.setProperty("GGG",3);
//		ds.put(book);
//		Key key = book.getKey();
//		ds.delete(key);
		
		Entity tweet = new Entity("Tweet");
		tweet.setProperty("name", "Zoey");
		tweet.setProperty("profile_image_id", 123456);
		tweet.setProperty("content", "This is a test.");
		Date time = new Date(0);
		tweet.setProperty("time", time.toString());
		tweet.setProperty("count", 0);
		ds.put(tweet);
		
//		Entity tweet2 = new Entity("Tweet");
//		tweet2.setProperty("nameID", 456);
//		tweet2.setProperty("birthday", "1991-08");
//		ds.put(tweet2);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doGet(req, resp);
	}
}
