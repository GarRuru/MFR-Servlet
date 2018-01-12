
import com.google.gson.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
//import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class ReceiveFile
 */
@WebServlet("/ReceiveFile")
public class ReceiveFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String firstID = null;
	String PassengerID = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReceiveFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/json;charset=UTF-8");
			int length = request.getContentLength();
			String ID = null;
			JSONObject obj = new JSONObject();
			byte[] input = new byte[length];
			ServletInputStream sin = request.getInputStream();
			int c, cnt = 0;
			while ((c = sin.read(input, cnt, input.length - cnt)) != -1) {
				cnt += c;
			}
			sin.close();
			String receivedString = new String(input);
			if (receivedString.length() > 20) {
				firstID = receivedString.substring(0, 21);
			}
			// System.out.println(receivedString.substring(receivedString.length()-8,
			// receivedString.length()));
			System.out.println(receivedString + "Test");
			// System.out.println(receivedString.substring(receivedString.length() - 6,
			// receivedString.length()));
			// receivedString.length() - 10));
			// System.out.println(receivedString.substring(receivedString.length() - 9,
			// receivedString.length()));
			// String tesT = receivedString.substring(receivedString.length() - 6,
			// receivedString.length());
			// System.out.println(tesT);
			File f = new File("C:\\\\Users\\\\MFR\\\\Desktop\\\\MFRServ\\" + receivedString + ".json");
			File f2 = new File("C:\\\\Users\\\\MFR\\\\Desktop\\\\MFRServ\\" + firstID + ".json");
			File f3 = new File("C:\\Users\\MFR\\Desktop\\MFRServ\\Request\\" + firstID + ".json");
			// System.out.println(f.exists());
			if (!f2.exists()) {
				response.getWriter().print("true");
				firstID = receivedString;
				FileWriter file = new FileWriter("C:\\Users\\MFR\\Desktop\\MFRServ\\" + receivedString + ".json");
				file.close();
			} else if (receivedString.substring(receivedString.length() - 6, receivedString.length())
					.equals("Create")) {
				obj = new JSONObject();
				receivedString = receivedString.substring(21, receivedString.length() - 6);
				System.out.println(receivedString);
				JSONArray array = new JSONArray('[' + receivedString + ']');
				for (int i = 0; i < array.length(); i++) {
					JSONObject jsonObj = array.getJSONObject(i);
					ID = jsonObj.getString("ID");
					obj.put("FirstName", jsonObj.getString("FirstName"));
					obj.put("LastName", jsonObj.getString("LastName"));
					obj.put("PhoneNumber", jsonObj.getString("PhoneNumber"));
					obj.put("Identify", jsonObj.getString("Identify"));
					obj.put("ID", jsonObj.getString("ID"));
					obj.put("CarType", jsonObj.getString("CarType"));
					obj.put("CarNumber", jsonObj.getString("CarNumber"));
					obj.put("Sex", jsonObj.getString("Sex"));
					obj.put("Email", jsonObj.getString("Email"));
					obj.put("Account", jsonObj.getString("AccountType"));
					obj.put("Count", jsonObj.getString("Count"));
					obj.put("Score", jsonObj.getString("Score"));
					obj.put("Now", jsonObj.getString("Now"));
				}
				FileWriter file = new FileWriter("C:\\Users\\MFR\\Desktop\\MFRServ\\" + ID + ".json");
				file.write(obj.toString());
				file.flush();
				file.close();
				firstID = null;
			} else if (receivedString.substring(receivedString.length() - 5, receivedString.length()).equals("InCar")) {
				firstID = receivedString.substring(0, 21);
				JsonParser parser = new JsonParser();
				JsonObject object = (JsonObject) parser
						.parse(new FileReader("C:\\\\Users\\\\MFR\\\\Desktop\\\\MFRServ\\" + firstID + ".json"));
				String Now = object.get("Now").getAsString();
				response.getWriter().print(Now);
				firstID = null;
			} else if (receivedString.substring(receivedString.length() - 5, receivedString.length()).equals("Check")) {
				firstID = receivedString.substring(0, 21);
				JsonParser parser = new JsonParser();
				JsonObject object = (JsonObject) parser
						.parse(new FileReader("C:\\\\Users\\\\MFR\\\\Desktop\\\\MFRServ\\" + firstID + ".json"));
				String Identify = object.get("Identify").getAsString();
				response.getWriter().print(Identify);
				firstID = null;
			} else if (receivedString.substring(receivedString.length() - 6, receivedString.length())
					.equals("addCar")) {
				obj = new JSONObject();
				firstID = receivedString.substring(0, 21);
				String temp = receivedString.substring(21, receivedString.length() - 6);
				JSONObject object = new JSONObject(temp);
				JsonParser parser = new JsonParser();
				JsonObject jsonObj = (JsonObject) parser
						.parse(new FileReader("C:\\\\Users\\\\MFR\\\\Desktop\\\\MFRServ\\" + firstID + ".json"));
				obj.put("FirstName", jsonObj.get("FirstName").getAsString());
				obj.put("LastName", jsonObj.get("LastName").getAsString());
				obj.put("PhoneNumber", jsonObj.get("PhoneNumber").getAsString());
				obj.put("Identify", "owner");
				obj.put("ID", jsonObj.get("ID").getAsString());
				obj.put("Sex", jsonObj.get("Sex").getAsString());
				obj.put("Email", jsonObj.get("Email").getAsString());
				obj.put("Account", jsonObj.get("Account").getAsString());
				obj.put("Count", jsonObj.get("Count").getAsString());
				obj.put("Score", jsonObj.get("Score").getAsString());
				obj.put("Now", jsonObj.get("Now").getAsString());
				obj.put("CarType", object.getString("CarType"));
				obj.put("CarNumber", object.getString("CarNumber"));
				FileWriter file = new FileWriter("C:\\Users\\MFR\\Desktop\\MFRServ\\" + firstID + ".json");
				file.write(obj.toString());
				file.flush();
				file.close();
				response.getWriter().print("OK");
			} else if (receivedString.substring(receivedString.length() - 6, receivedString.length())
					.equals("Change")) {
				String Attr = "";
				String Stat = "false";
				obj = new JSONObject();
				if (receivedString.length() > 42) {
					Attr = receivedString.substring(42, receivedString.length() - 6);
				} else {
					Attr = receivedString.substring(21, receivedString.length() - 6);
				}
				JsonParser parser = new JsonParser();
				if (Attr.equals("multipleAgree")) {
					String Owner = receivedString.substring(0, 21);
					String Passenger = receivedString.substring(21, 42);
					System.out.println("HI");
					JsonObject jsonObj = (JsonObject) parser
							.parse(new FileReader("C:\\Users\\MFR\\Desktop\\MFRServ\\" + Owner + ".json"));
					System.out.println(jsonObj);
					obj.put("FirstName", jsonObj.get("FirstName").getAsString());
					obj.put("LastName", jsonObj.get("LastName").getAsString());
					obj.put("PhoneNumber", jsonObj.get("PhoneNumber").getAsString());
					obj.put("Sex", jsonObj.get("Sex").getAsString());
					obj.put("Identify", jsonObj.get("Identify").getAsString());
					obj.put("CarType", jsonObj.get("CarType").getAsString());
					obj.put("CarNumber", jsonObj.get("CarNumber").getAsString());
					System.out.println(jsonObj.get("CarType").getAsString()+ "　"+ jsonObj.get("CarNumber").getAsString());
					obj.put("ID", jsonObj.get("ID").getAsString());
					obj.put("Email", jsonObj.get("Email").getAsString());
					obj.put("Account", jsonObj.get("Account").getAsString());
					obj.put("Count", jsonObj.get("Count").getAsString());
					obj.put("Score", jsonObj.get("Score").getAsString());
					obj.put("Now", "InCar");
					FileWriter file = new FileWriter("C:\\Users\\MFR\\Desktop\\MFRServ\\" + Owner + ".json");
					file.write(obj.toString());
					file.flush();
					file.close();
					jsonObj = (JsonObject) parser
							.parse(new FileReader("C:\\Users\\MFR\\Desktop\\MFRServ\\" + Passenger + ".json"));
					obj.put("FirstName", jsonObj.get("FirstName").getAsString());
					obj.put("LastName", jsonObj.get("LastName").getAsString());
					obj.put("PhoneNumber", jsonObj.get("PhoneNumber").getAsString());
					obj.put("CarType", jsonObj.get("CarType").getAsString());
					obj.put("CarNumber", jsonObj.get("CarNumber").getAsString());
					obj.put("Identify", jsonObj.get("Identify").getAsString());
					obj.put("ID", jsonObj.get("ID").getAsString());
					obj.put("Sex", jsonObj.get("Sex").getAsString());
					obj.put("Email", jsonObj.get("Email").getAsString());
					obj.put("Account", jsonObj.get("Account").getAsString());
					obj.put("Count", jsonObj.get("Count").getAsString());
					obj.put("Score", jsonObj.get("Score").getAsString());
					obj.put("Now", "InCar");
					file = new FileWriter("C:\\Users\\MFR\\Desktop\\MFRServ\\" + Passenger + ".json");
					file.write(obj.toString());
					file.flush();
					file.close();
					response.getWriter().print("WillDrive");
				} else if (Attr.equals("Identify")) {
					String changeID = receivedString.substring(0, 21);
					JsonObject jsonObj = (JsonObject) parser
							.parse(new FileReader("C:\\Users\\MFR\\Desktop\\MFRServ\\" + changeID + ".json"));
					if (jsonObj.get("Now").getAsString().equals("NoCar")) {
						obj.put("Now", jsonObj.get("Now").getAsString());
						obj.put("FirstName", jsonObj.get("FirstName").getAsString());
						obj.put("LastName", jsonObj.get("LastName").getAsString());
						obj.put("PhoneNumber", jsonObj.get("PhoneNumber").getAsString());
						obj.put("ID", jsonObj.get("ID").getAsString());
						obj.put("Email", jsonObj.get("Email").getAsString());
						obj.put("Account", jsonObj.get("Account").getAsString());
						obj.put("Count", jsonObj.get("Count").getAsString());
						obj.put("Score", jsonObj.get("Score").getAsString());
						obj.put("Sex", jsonObj.get("Sex").getAsString());
						obj.put("Now", jsonObj.get("Now").getAsString());
						System.out.println(obj);
						System.out.println(jsonObj.get("Identify").getAsString() +jsonObj.get("CarType").getAsString() + jsonObj.get("CarNumber").getAsString());
						if (jsonObj.get("Identify").getAsString().equals("passenger")) {
							System.out.println(jsonObj.get("Identify").getAsString() +jsonObj.get("CarType").getAsString() + jsonObj.get("CarNumber").getAsString());
							if (jsonObj.get("CarType").getAsString().equals("")
									|| jsonObj.get("CarNumber").getAsString().equals("")) {
								Stat = "NoCar";
							} else {
								obj.put("CarType", jsonObj.get("CarType").getAsString());
								obj.put("CarNumber", jsonObj.get("CarNumber").getAsString());
								obj.put("Identify", "owner");
								FileWriter file = new FileWriter(
										"C:\\Users\\MFR\\Desktop\\MFRServ\\" + changeID + ".json");
								file.write(obj.toString());
								file.flush();
								file.close();
								Stat = "true";
							}
						} else {
							obj.put("CarType", jsonObj.get("CarType").getAsString());
							obj.put("CarNumber", jsonObj.get("CarNumber").getAsString());
							obj.put("Identify", "passenger");
							FileWriter file = new FileWriter("C:\\Users\\MFR\\Desktop\\MFRServ\\" + changeID + ".json");
							file.write(obj.toString());
							file.flush();
							file.close();
							Stat = "true";
						}
					}
				}
				response.getWriter().print(Stat);
			} else if (receivedString.substring(receivedString.length() - 6, receivedString.length())
					.equals("Accept")) {
				System.gc();
				String OwnerID = "";
				firstID = receivedString.substring(0, 21);
				ArrayList<JSONObject> templist = new ArrayList<JSONObject>();
				receivedString = receivedString.substring(21, receivedString.length() - 6);
				try {
					File file = new File("C:\\Users\\MFR\\Desktop\\MFRServ\\Request\\" + firstID + ".json");
					if (!file.delete()) {
						System.out.println("Cannot delete");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				obj = new JSONObject();
				JSONArray array = new JSONArray('[' + receivedString + ']');
				for (int i = 0; i < array.length(); i++) {
					JSONObject jsonObj = array.getJSONObject(i);
					OwnerID = firstID;
					obj.put("Owner ID", firstID);
					firstID = jsonObj.getString("ID");
					PassengerID = firstID;
					obj.put("ID", firstID);
					obj.put("Description", jsonObj.getString("Description"));
					obj.put("FirstName", jsonObj.getString("FirstName"));
					obj.put("LastName", jsonObj.getString("LastName"));
					obj.put("PhoneNumber", jsonObj.getString("PhoneNumber"));
					obj.put("Identify", jsonObj.getString("Identify"));
					obj.put("Email", jsonObj.getString("Email"));
					obj.put("Count", jsonObj.getString("Count"));
					obj.put("Score", jsonObj.getString("Score"));
					obj.put("CarryPlace", jsonObj.getString("CarryPlace"));
					obj.put("CarryPlaceLongitude", jsonObj.getString("CarryPlaceLongitude"));
					obj.put("CarryPlaceLatitude", jsonObj.getString("CarryPlaceLatitude"));
					obj.put("FinishPlace", jsonObj.getString("FinishPlace"));
					obj.put("FinishPlaceLongitude", jsonObj.getString("FinishPlaceLongitude"));
					obj.put("FinishPlaceLatitude", jsonObj.getString("FinishPlaceLatitude"));
				}
				// file = new File("C:\\Users\\MFR\\Desktop\\MFRServ\\Accept\\" + firstID +
				// ".json");
				FileWriter filewrite = new FileWriter("C:\\Users\\MFR\\Desktop\\MFRServ\\Accept\\" + firstID + ".json");
				templist.add(obj);
				JsonParser parser = new JsonParser();
				JsonObject jsonObj = (JsonObject) parser
						.parse(new FileReader("C:\\Users\\MFR\\Desktop\\MFRServ\\Register\\" + OwnerID + ".json"));
				obj = new JSONObject();
				obj.put("Hour", jsonObj.get("Hour").getAsString());
				obj.put("FirstName", jsonObj.get("FirstName").getAsString());
				obj.put("LastName", jsonObj.get("LastName").getAsString());
				obj.put("PhoneNumber", jsonObj.get("PhoneNumber").getAsString());
				obj.put("longitude", jsonObj.get("longitude").getAsString());
				obj.put("ID", jsonObj.get("ID").getAsString());
				obj.put("Count", jsonObj.get("Count").getAsString());
				obj.put("Description", jsonObj.get("Description").getAsString());
				obj.put("Score", jsonObj.get("Score").getAsString());
				obj.put("start", jsonObj.get("start").getAsString());
				obj.put("Identify", jsonObj.get("Identify").getAsString());
				obj.put("Month", jsonObj.get("Month").getAsString());
				obj.put("Year", jsonObj.get("Year").getAsString());
				obj.put("Minute", jsonObj.get("Minute").getAsString());
				obj.put("Date", jsonObj.get("Date").getAsString());
				obj.put("latitude", jsonObj.get("latitude").getAsString());
				templist.add(obj);
				filewrite.write(templist.toString());
				filewrite.flush();
				filewrite.close();
				FileWriter fr = new FileWriter("C:\\Users\\MFR\\Desktop\\MFRServ\\Register\\" + OwnerID + ".json");
				fr.write("empty");
				fr.flush();
				fr.close();
				response.getWriter().print(firstID);
			} else if (receivedString.substring(receivedString.length() - 11, receivedString.length())
					.equals("GetForEvery")) {
				System.out.println(PassengerID + "kgsgmpMg");
				JsonParser parser = new JsonParser();
				JsonArray jobj = (JsonArray) parser
						.parse(new FileReader("C:\\Users\\MFR\\Desktop\\MFRServ\\Accept\\" + PassengerID + ".json"));
				response.getWriter().print(jobj.toString());
			} else if (receivedString.substring(receivedString.length() - 6, receivedString.length())
					.equals("Refuse")) {
				int index;
				ArrayList<JsonObject> list = new ArrayList<JsonObject>();
				String Owner = receivedString.substring(0, 21);
				String Passenger = receivedString.substring(21, 42);
				JsonParser parser = new JsonParser();
				JsonArray jobj = (JsonArray) parser
						.parse(new FileReader("C:\\Users\\MFR\\Desktop\\MFRServ\\Request\\" + Owner + ".json"));
				for (int i = 0; i < jobj.size(); i++) {
					JsonObject object = (JsonObject) jobj.get(i);
					String temp = object.get("ID").getAsString();
					if (!temp.equals(Passenger)) {
						list.add(object);
					}
				}
				FileWriter file = new FileWriter("C:\\Users\\MFR\\Desktop\\MFRServ\\Request\\" + Owner + ".json");
				file.write(list.toString());
				file.flush();
				file.close();
				response.getWriter().print("true");

			} else if (receivedString.substring(receivedString.length() - 6, receivedString.length())
					.equals("Commit")) {
				String Stat = "Fail";
				int count = 0;
				JSONObject temp = new JSONObject(receivedString.substring(21, receivedString.length() - 6));
				System.out.println(temp.toString());
				String Score = temp.getString("Score");
				String Ident = temp.getString("Ident");
				String Commit = temp.getString("Commit");
				System.out.println(firstID + Score + Ident + Commit);
				if (Ident.equals("Owner")) {
					String[] fname;
					String Data = "";
					File file = new File("C:\\Users\\MFR\\Desktop\\MFRServ\\Accept");
					if (file.isDirectory()) {
						fname = file.list(new FilenameFilter() {
							public boolean accept(File dir, String name) {
								if (name != null && name.endsWith(".json")) {
									System.out.println("ture");
									return true;
								} else {
									System.out.println("false");
									return false;
								}
							}
						});
						obj = new JSONObject();
						ArrayList<JsonObject> tempList = new ArrayList<JsonObject>();
						JsonParser parser = new JsonParser();
						for (int i = 0; i < fname.length; i++) {
							JsonArray jobj = (JsonArray) parser
									.parse(new FileReader("C:\\Users\\MFR\\Desktop\\MFRServ\\Accept\\" + fname[i]));
							JsonObject job = jobj.get(1).getAsJsonObject();
							if (job.get("ID").getAsString().equals(firstID)) {
								Data = fname[i];
								break;
							}
						}
						System.out.println(Data);
						JsonObject get = (JsonObject) parser
								.parse(new FileReader("C:\\Users\\MFR\\Desktop\\MFRServ\\" + Data));
						FileWriter fi = new FileWriter("C:\\Users\\MFR\\Desktop\\MFRServ\\" + Data);
						count = get.get("Count").getAsInt();
						if (count == 0) {
							obj.put("Score", Score);
							count += 1;
							obj.put("Count", Integer.toString(count));
						} else {
							float score = get.get("Score").getAsFloat();
							score *= count;
							score += Float.valueOf(Score);
							count += 1;
							score /= count;
							obj.put("Score", Float.toString(score));
							obj.put("Count", Integer.toString(count));
						}
						obj.put("Account", get.get("Account").getAsString());
						obj.put("Email", get.get("Email").getAsString());
						obj.put("FirstName", get.get("FirstName").getAsString());
						obj.put("Sex", get.get("Sex").getAsString());
						obj.put("LastName", get.get("LastName").getAsString());
						obj.put("CarType", get.get("CarType").getAsString());
						obj.put("CarNumber", get.get("CarNumber").getAsString());
						obj.put("ID", get.get("ID").getAsString());
						obj.put("Identify", get.get("Identify").getAsString());
						obj.put("Now", "NoCar");
						obj.put("PhoneNumber", get.get("PhoneNumber").getAsString());
						System.out.println(obj.toString());
						fi.write(obj.toString());
						fi.flush();
						fi.close();
						Stat = "OK";
					}
				} else if (Ident.equals("Passenger")) {
					System.out.println("123");
					String Data = "";
					JsonParser parser = new JsonParser();
					JsonArray jobj = (JsonArray) parser
							.parse(new FileReader("C:\\Users\\MFR\\Desktop\\MFRServ\\Accept\\" + firstID + ".json"));
					JsonObject jb = new JsonObject();
					jb = jobj.get(1).getAsJsonObject();
					Data = jb.get("ID").getAsString();
					System.out.println(Data);
					JsonObject get = (JsonObject) parser
							.parse(new FileReader("C:\\Users\\MFR\\Desktop\\MFRServ\\" + Data + ".json"));
					FileWriter fi = new FileWriter("C:\\Users\\MFR\\Desktop\\MFRServ\\" + Data + ".json");
					System.out.println(Data);
					count = get.get("Count").getAsInt();
					if (count == 0) {
						obj.put("Score", Score);
						count += 1;
						obj.put("Count", Integer.toString(count));
					} else {
						float score = get.get("Score").getAsFloat();
						score *= count;
						score += Float.valueOf(Score);
						count += 1;
						score /= count;
						obj.put("Score", Float.toString(score));
						obj.put("Count", Integer.toString(count));
					}
					obj.put("Account", get.get("Account").getAsString());
					obj.put("Email", get.get("Email").getAsString());
					obj.put("FirstName", get.get("FirstName").getAsString());
					obj.put("LastName", get.get("LastName").getAsString());
					obj.put("Sex", get.get("Sex").getAsString());
					obj.put("CarType", get.get("CarType").getAsString());
					obj.put("CarNumber", get.get("CarNumber").getAsString());
					obj.put("ID", get.get("ID").getAsString());
					obj.put("Identify", get.get("Identify").getAsString());
					obj.put("Now", "NoCar");
					obj.put("PhoneNumber", get.get("PhoneNumber").getAsString());
					fi.write(obj.toString());
					fi.flush();
					fi.close();
					Stat = "OK";
				}
				response.getWriter().print(Stat);
			} else if (receivedString.substring(receivedString.length() - 9, receivedString.length())
					.equals("ALL_ISend")) {
				String[] fname;
				File file = new File("C:\\Users\\MFR\\Desktop\\MFRServ\\Request");
				if (file.isDirectory()) {
					fname = file.list(new FilenameFilter() {
						public boolean accept(File dir, String name) {
							if (name != null && name.endsWith(".json")) {
								System.out.println("ture");
								return true;
							} else {
								System.out.println("false");
								return false;
							}
						}
					});
					ArrayList<JsonObject> tempList = new ArrayList<JsonObject>();
					JsonParser parser = new JsonParser();
					for (int i = 0; i < fname.length; i++) {
						JsonArray jobj = (JsonArray) parser
								.parse(new FileReader("C:\\Users\\MFR\\Desktop\\MFRServ\\Request\\" + fname[i]));
						// System.out.println(jobj.get("start").getAsString());
						// System.out.println("i");
						for (int j = 0; j < jobj.size(); j++) {
							JsonObject jsonOB = (JsonObject) jobj.get(j);
							// System.out.println(jsonOB+" "+firstID+" "+jsonOB.get(ID).getAsString());
							// if(jsonOB.get(ID).getAsString().equals(firstID)) {
							tempList.add(jsonOB);
							// }
						}
						// JsonObject jobj = (JsonObject) parser
						// .parse(new FileReader("C:\\Users\\MFR\\Desktop\\MFRServ\\Request\\" +
						// fname[i]));
						// tempList.add(jobj);
					}
					System.out.println(tempList);
					response.getWriter().print(tempList.toString());
				}
			} else if (receivedString.substring(receivedString.length() - 9, receivedString.length())
					.equals("addMarker")) {
				System.out.println("Hi");
				String[] fname;
				File file = new File("C:\\Users\\MFR\\Desktop\\MFRServ\\Register");
				if (file.isDirectory()) {
					System.out.println("dir");
					fname = file.list(new FilenameFilter() {
						public boolean accept(File dir, String name) {
							if (name != null && name.endsWith(".json")) {
								System.out.println("ture");
								return true;
							} else {
								System.out.println("false");
								return false;
							}
						}
					});
					ArrayList<JsonObject> tempList = new ArrayList<JsonObject>();
					Gson gson = new GsonBuilder().create();
					JsonParser parser = new JsonParser();
					for (int i = 0; i < fname.length; i++) {
						if (new File("C:\\Users\\MFR\\Desktop\\MFRServ\\Register\\" + fname[i]).length() > 10) {
							JsonObject jobj = (JsonObject) parser
									.parse(new FileReader("C:\\Users\\MFR\\Desktop\\MFRServ\\Register\\" + fname[i]));
							// System.out.println(jobj.get("start").getAsString());
							// System.out.println("i");
							tempList.add(jobj);
						}
					}

					response.getWriter().print(tempList.toString());
				}
			} else if (receivedString.substring(receivedString.length() - 10, receivedString.length())
					.equals("GetRequest")) {
				if (receivedString.substring(receivedString.length() - 15, receivedString.length() - 10)
						.equals("Owner")) {
					obj = new JSONObject();
					System.out.println("IIIII");
					JsonParser parser = new JsonParser();
					firstID = receivedString.substring(0, 21);
					JsonObject object = (JsonObject) parser
							.parse(new FileReader("C:\\Users\\MFR\\Desktop\\MFRServ\\Register\\" + firstID + ".json"));
					System.out.println(object.toString());
					response.getWriter().print(object.toString());
					parser = null;
					object = null;
					firstID = null;
				} else {
					obj = new JSONObject();
					JsonParser parser = new JsonParser();
					firstID = receivedString.substring(0, 21);
					JsonArray object = (JsonArray) parser
							.parse(new FileReader("C:\\Users\\MFR\\Desktop\\MFRServ\\Request\\" + firstID + ".json"));
					response.getWriter().print(object.toString());
					parser = null;
					object = null;
					firstID = null;
				}
			} else if (receivedString.substring(receivedString.length() - 11, receivedString.length())
					.equals("SendRequest")) {
				obj = new JSONObject();
				JsonArray temp = new JsonArray();
				JsonParser parser = new JsonParser();
				ArrayList<JSONObject> tempList = new ArrayList<JSONObject>();
				firstID = receivedString.substring(0, 21);
				receivedString = receivedString.substring(21, receivedString.length() - 11);
				if (f3.exists()) {
					temp = (JsonArray) parser
							.parse(new FileReader("C:\\Users\\MFR\\Desktop\\MFRServ\\Request\\" + firstID + ".json"));
				}
				JSONArray array = new JSONArray('[' + receivedString + ']');
				for (int i = 0; i < array.length(); i++) {
					JSONObject jsonObj = array.getJSONObject(i);
					obj.put("ID", jsonObj.getString("ID"));
					obj.put("Description", jsonObj.getString("Description"));
					obj.put("FirstName", jsonObj.getString("FirstName"));
					obj.put("LastName", jsonObj.getString("LastName"));
					obj.put("PhoneNumber", jsonObj.getString("PhoneNumber"));
					obj.put("Identify", jsonObj.getString("Identify"));
					obj.put("Email", jsonObj.getString("Email"));
					obj.put("Count", jsonObj.getString("Count"));
					obj.put("Score", jsonObj.getString("Score"));
					obj.put("CarryPlace", jsonObj.getString("CarryPlace"));
					obj.put("CarryPlaceLongitude", jsonObj.getString("CarryPlaceLongitude"));
					obj.put("CarryPlaceLatitude", jsonObj.getString("CarryPlaceLatitude"));
					obj.put("FinishPlace", jsonObj.getString("FinishPlace"));
					obj.put("FinishPlaceLongitude", jsonObj.getString("FinishPlaceLongitude"));
					obj.put("FinishPlaceLatitude", jsonObj.getString("FinishPlaceLatitude"));
					obj.put("Owner ID", jsonObj.get("Owner ID"));
				}
				FileWriter file = new FileWriter("C:\\Users\\MFR\\Desktop\\MFRServ\\Request\\" + firstID + ".json");
				if (f3.exists()) {
					for (int i = 0; i < temp.size(); i++) {
						JsonObject ject = (JsonObject) temp.get(i);
						tempList.add(new JSONObject((ject).toString()));
					}
					// tempList.add(new JSONObject(temp));
				}
				tempList.add(obj);
				// System.out.print(total);
				file.write(tempList.toString());
				file.flush();
				file.close();
				temp = null;
				parser = null;
				f3 = null;
				response.getWriter().print("true");
				firstID = null;
			} else if (receivedString.substring(receivedString.length() - 10, receivedString.length())
					.equals("GetProfile")) {
				obj = new JSONObject();
				JsonParser parser = new JsonParser();
				firstID = receivedString.substring(0, 21);
				JsonObject object = (JsonObject) parser
						.parse(new FileReader("C:\\\\Users\\\\MFR\\\\Desktop\\\\MFRServ\\" + firstID + ".json"));
				response.getWriter().print(object.toString());
				firstID = null;
			} else if (receivedString.substring(receivedString.length() - 8, receivedString.length())
					.equals("Register")) {
				obj = new JSONObject();
				firstID = receivedString.substring(0, 21);
				JsonParser parser = new JsonParser();
				receivedString = receivedString.substring(21, receivedString.length() - 8);
				JsonObject object = (JsonObject) parser
						.parse(new FileReader("C:\\\\Users\\\\MFR\\\\Desktop\\\\MFRServ\\" + firstID + ".json"));
				// System.out.println(receivedString);
				// String latitude,longitude;
				String firstName = object.get("FirstName").getAsString();
				String lastName = object.get("LastName").getAsString();
				String phoneNum = object.get("PhoneNumber").getAsString();
				String score = object.get("Score").getAsString();
				String count = object.get("Count").getAsString();
				String identify = object.get("Identify").getAsString();
				JSONArray array = new JSONArray('[' + receivedString + ']');
				for (int i = 0; i < array.length(); i++) {
					JSONObject jsonObj = array.getJSONObject(i);
					obj.put("Year", jsonObj.getString("Year"));
					obj.put("Month", jsonObj.getString("Month"));
					obj.put("Date", jsonObj.getString("Date"));
					obj.put("Hour", jsonObj.getString("Hour"));
					obj.put("Minute", jsonObj.getString("Minute"));
					obj.put("start", jsonObj.getString("Start"));
					obj.put("latitude", jsonObj.getString("Latitude"));
					obj.put("Description", jsonObj.getString("Description"));
					obj.put("longitude", jsonObj.getString("Longitude"));
				}
				obj.put("FirstName", firstName);
				obj.put("LastName", lastName);
				obj.put("PhoneNumber", phoneNum);
				obj.put("Score", score);
				obj.put("Count", count);
				obj.put("Identify", identify);
				obj.put("ID", firstID);
				FileWriter file = new FileWriter("C:\\Users\\MFR\\Desktop\\MFRServ\\Register\\" + firstID + ".json");
				file.write(obj.toString());
				file.flush();
				file.close();
				object = null;
				parser = null;
				response.getWriter().print(
						"{\"Stat\":\"Success\",\"FirstName\":\"" + firstName + "\",\"LastName\":\"" + lastName + "\"}");
				firstID = null;
			} else if (receivedString.length() == 28) {
				// Profile Access
				firstID = receivedString.substring(0, 21);
				JsonParser parser = new JsonParser();
				JsonObject object = (JsonObject) parser
						.parse(new FileReader("C:\\\\Users\\\\MFR\\\\Desktop\\\\MFRServ\\" + firstID + ".json"));
				firstID = null;
			}
		} catch (IOException e) {

			try {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().print(e.getMessage());
				response.getWriter().close();
			} catch (IOException ioe) {
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
