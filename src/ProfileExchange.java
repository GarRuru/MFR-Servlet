//�ӤH��Ƶe��:�ǰe�ϥΪ�Profile������

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendFile
 */
@WebServlet("/SendFile")
public class ProfileExchange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileExchange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//Client�ݥ�X�@�Ӧr��:UID,Server������

		response.setContentType("text/html;charset=UTF-8");
		//1.���A������r��(�άO���ɮ�?)
		String idfromClient = request.getParameter("str");
		//2.�}���ɮ�
		FileReader fileReader = new FileReader(idfromClient + ".");
		//3.�ѪR�ɮפ��e(���X)
		BufferedReader br = new BufferedReader(fileReader);
		while(br.ready())
		{
			String idTag = br.readLine();
		}
		fileReader.close();
		//�b�o��إߤ@��JSON�榡�ɮסA�^��
		
		//FreeRideData FRD = new FreeRideData();
		Gson gson = new Gson();
		EvaluateData EDD= new EvaluateData(1,1,2);
		response.setContentType("application/json");
		//PrintWriter out = response.getWriter();
		String jj = gson.toJson(EDD);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
