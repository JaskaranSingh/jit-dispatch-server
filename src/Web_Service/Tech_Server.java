package Web_Service;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Tech_Server
 */
@WebServlet("/Tech_Server")
public class Tech_Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
	java.sql.Statement st;
    String query;
    ResultSet rs;
    Customer_Server obj= new Customer_Server();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tech_Server() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void updateTechLocation(int tech_id, double lat, double lng) {
    	try {
			st = obj.con.createStatement();
			query ="update tech set lat="+lat+" and lng="+ lng +" where tech_id="+tech_id;
			rs = st.executeQuery(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int tech_id = Integer.parseInt(request.getParameter("tech_id"));
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lng = Double.parseDouble(request.getParameter("lng"));
		
		System.out.println(tech_id);
		System.out.println(lat);
		System.out.println(lng);
		//updateTechLocation(tech_id, lat,lng);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
