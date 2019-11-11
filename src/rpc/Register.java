package rpc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import db.DBConnection;
import db.DBConnectionFactory;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBConnection connection = DBConnectionFactory.getConnection();
		try {
			JSONObject object = RpcHelper.readJSONObject(request);
			String userId = object.getString("user_id");
			String password = object.getString("password");
			String firstname = object.getString("first_name");
			String lastname = object.getString("last_name");
			
			JSONObject result = new JSONObject();
			if (connection.registerUser(userId, password, firstname, lastname)) {
				result.put("status", "OK");
			} else {
				result.put("status", "User Already Exists");
			}
			RpcHelper.writeJsonObject(response, result);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			connection.close();
		}
	}

}
