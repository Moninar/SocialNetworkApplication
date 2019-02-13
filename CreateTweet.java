package socialNetworkApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.*;


@SuppressWarnings("serial")
public class CreateTweet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setContentType("text/plain");
		res.getWriter().println("create Tweets");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/plain");
		
		String userID = request.getParameter("userID");
		String userName = request.getParameter("userName");
		String userImageURL = request.getParameter("userImageURL");
		String tweet = request.getParameter("tweet");
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("Tweet");
		
		entity.setProperty("userID", userID);
		entity.setProperty("userName", userName);
		entity.setProperty("userImageURL", userImageURL);
		entity.setProperty("tweet", tweet);
		entity.setProperty("count", 0);
//		entity.setProperty("createTime", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())).toString());
		
		ds.put(entity);

		response.getWriter().println("{\"success\": 1, \"message\": \"create Tweets successfully\"}");
		
//		String redirectJSP = "Tweet.jsp";
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(redirectJSP);
//		dispatcher.forward(request, response);
	}
}
