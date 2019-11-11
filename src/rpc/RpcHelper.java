package rpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import entity.Item;

public class RpcHelper {
	public static void writeJsonArray(HttpServletResponse response, JSONArray array) throws IOException{
		response.setContentType("application/json");
		response.getWriter().print(array);
	}
	
	public static void writeJsonObject(HttpServletResponse response, JSONObject obj) throws IOException{
		response.setContentType("application/json");
		response.getWriter().print(obj);

	}
	
	public static JSONObject readJSONObject(HttpServletRequest request) {
	  	   StringBuilder sBuilder = new StringBuilder();
	  	   try (BufferedReader reader = request.getReader()) {
	  		 String line = null;
	  		 while((line = reader.readLine()) != null) {
	  			 sBuilder.append(line);
	  		 }
	  		 return new JSONObject(sBuilder.toString());
	  		
	  	   } catch (Exception e) {
	  		 e.printStackTrace();
	  	   }
	  	
	  	  return new JSONObject();
	  }
	
	public static JSONArray getJsonArray(List<Item> items) {
		JSONArray result = new JSONArray();
		try {
			for (Item item : items) {
				result.put(item.toJSONObject());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

}
