package socialNetworkApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;


@SuppressWarnings("serial")
public class GetTweets extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setContentType("text/plain");
		res.getWriter().println("get Tweets");
	}
	
	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/plain");
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		String userID = request.getParameter("userID");
		String userName = request.getParameter("userName");
		String userImageURL = request.getParameter("userImageURL");
		Query q = new Query("Tweet");
		q.addFilter("userID", Query.FilterOperator.EQUAL, userID);
		PreparedQuery pq = ds.prepare(q);
		
		JSONObject object = new JSONObject();
		int len = 0;
		String html = "<div id=\"tw_user\">";
			   html += "<img src='" + userImageURL + "' />";
			   html += "<span>These are " + userName + "\'s tweets on SocialNetworkApp</span>";
			   html += "</div>";
			   html += "<ul class=\"tw-content\">";
		for (Entity result : pq.asIterable()) {			
			html += "<li data=\"" + result.getKey().getId() + "\">";
			html += (String) result.getProperty("tweet") + "</li>";
					
			len++;
		}
		html += "</ul>";
		
		try {
			object.put("data", html);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().println(object);
	}
}
