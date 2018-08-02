package servlet;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;

import api.DiscoveryAPI;
import data.Configuration;
//import utility.Combiner;



@WebServlet("/api")
public class ReplyResults extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected Configuration config=new Configuration();
	
  
	public boolean authenticate(String authorization, String user, String password) {
		 
		if (!config.authenticate) return true;
		else{
			try {		
			    if (authorization != null && authorization.startsWith("Basic")) {
			        // Authorization: Basic base64credentials
			    	String base64Credentials = authorization.substring("Basic".length()).trim();
			        String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
			        			  
			        return credentials.equals((user + ":" + password));
			        }
			    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
		return false;
	}

	protected String getParameter(HttpServletRequest request, String name) {
		String value="";
    	try {
			value = request.getParameter(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	protected boolean toBoolean(String value) {
		boolean result=false;
    	try {
			result = Boolean.parseBoolean(value);
		} catch (Exception e) {
		}
		return result;
	}
	
	protected int toInt(String value) {
		int result=10;
    	try {
			result = Integer.parseInt(value);
		} catch (Exception e) {
		}
		return result;
	}      
	
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           	    	
    	response.setContentType("text/html");
    	System.out.println("Authorization:" + config.authenticate);
    	
		if (authenticate(request.getHeader("Authorization"), config.user, config.password)){
		
    	String query = request.getParameter("query"); 
    	String natural_language_query = getParameter(request, "natural_language_query"); 
    	String filter = getParameter(request, "filter");
    	int limit = toInt(getParameter(request, "limit"));
    	boolean passages = toBoolean(getParameter(request, "passages"));
    	boolean deduplicate = toBoolean(getParameter(request, "deduplicate"));; 
    	boolean highlight = toBoolean(getParameter(request, "highlight"));;
    	
    	
    	DiscoveryAPI discovery=new DiscoveryAPI(config, config.collections.get(0));
    	response.getWriter().print(discovery.query(query, natural_language_query, limit, filter, passages, deduplicate, highlight));
     	
     	}else{
     		response.getWriter().print("access denied !");
		}
		
		
		
    }

}
