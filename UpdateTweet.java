package socialNetworkApp;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.*;


@SuppressWarnings("serial")
public class UpdateTweet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/plain");
		String key = request.getParameter("id");
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key tweetKey = KeyFactory.createKey("Tweet", Long.parseLong(key));
		if (tweetKey != null) {
			try {
				Entity tweet = ds.get(tweetKey);			
				// update tweet count, add 1 per time	
				tweet.setProperty("count", (Long) tweet.getProperty("count") + 1);
				ds.put(tweet);
				
				request.setAttribute("tweet", tweet);
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("TweetView.jsp").forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
