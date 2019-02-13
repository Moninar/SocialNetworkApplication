package socialNetworkApp;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.*;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;


@SuppressWarnings("serial")
public class TopTweets extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/plain");
		String userID = request.getParameter("userID");
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
//		Query q = new Query("Tweet");
//		q.addFilter("userID", Query.FilterOperator.EQUAL, userID);
//		PreparedQuery pq = ds.prepare(q);
		
//		Query q = new Query("Tweet");
//		q.addFilter("userID", Query.FilterOperator.EQUAL, userID);
//		PreparedQuery pq = ds.prepare(q);
//		List<Entity> list = new ArrayList<Entity>();
//		Query q = new Query("Tweet");
//		
//		q.addFilter("userID", Query.FilterOperator.EQUAL, userID);
//		
//		PreparedQuery results = ds.prepare(q);
		
		Query query = new Query("Tweet").addSort("count", Query.SortDirection.DESCENDING);		
		List<Entity> tweets = ds.prepare(query).asList(FetchOptions.Builder.withLimit(10));
//		PreparedQuery pq = ds.prepare(query);
//		Iterable<Entity> results = pq.asIterable(FetchOptions.Builder.withLimit(10));
		
		String html = "";
		int len = 0;
		for (Entity result : tweets) {	
			html += "<div class=\"tv-container\">";
			html += "<div class=\"tv-intro\"><span>-----------NO." + (++len) + "--------------</span></div>";
			html += "<div class=\"tv-user\">";
			html +=	"<span class=\"tv-post\">Posted by: </span>";
			html +=	"<img src=\"" + (String)result.getProperty("userImageURL") + "\" /> <label>" + (String)result.getProperty("userName") + "</label>";
			html += "</div>";
			html += "<div class=\"tv-tweet\">";
			html +=	"<span>Tweet Content: </span>";
			html +=	"<div class=\"tv-content\">" + (String)result.getProperty("tweet") + "</div>";
			html += "</div>";
			html += "<div class=\"tv-count\">";
			html +=	"<span>Visited Counter: </span><label>" + (Long)result.getProperty("count") + "</label>";
			html += "</div></div>";
		}
		JSONObject object = new JSONObject();
		try {
			object.put("data", html);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().println(object);
		
//		request.setAttribute("tweets", list);
//		response.getWriter().println("{\"success\": 1, \"message\": \"create Tweets successfully\"}");
//		request.getRequestDispatcher("TopTweetsView.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
