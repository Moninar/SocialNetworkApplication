package socialNetworkApp;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.*;


@SuppressWarnings("serial")
public class DeleteTweet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/plain");
		
		String key = request.getParameter("key");
		Key tweetKey = KeyFactory.createKey("Tweet", Long.parseLong(key));
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		ds.delete(tweetKey);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}
