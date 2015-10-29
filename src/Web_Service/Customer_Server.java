package Web_Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class Customer_Server
 */
@WebServlet("/Customer_Server")
public class Customer_Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con=null;
    java.sql.Statement st;
    String query;
    ResultSet rs;
    double customerLat;
    double customerLng;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customer_Server() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void dbConnect() {
    	
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://113.128.164.167:3306/jit_dispatcher","root","root");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public static float distFrom(float lat1, float lng1, float lat2, float lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);

        return dist;
        }
    
    public void filterTechnicians(int cust_id, int product_id) {
		
    	ArrayList<String> l = new ArrayList<>();
    	try {
			st = con.createStatement();
			query ="select lat,lng from customer where cust_id ="+cust_id;
			rs = st.executeQuery(query);
			
			while(rs.next()){
				customerLat=rs.getDouble(1);
				customerLng=rs.getDouble(2);	
			}
			
			query = "select * from tech where status='available' and tech_id in(select tech_id from tech_prod where prod_id = "+product_id+")";
			rs = st.executeQuery(query);
			
			while(rs.next()){
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(l);
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println(request.getParameter("custID"));
		System.out.println(request.getParameter("type"));
		
		response.setContentType("text/plain");
		response.getWriter().println("Jaskaran");
		
		int cust_id = Integer.parseInt(request.getParameter("custID")); 
		int product_id= Integer.parseInt(request.getParameter("type"));
		dbConnect();
		
		filterTechnicians(cust_id, product_id);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
