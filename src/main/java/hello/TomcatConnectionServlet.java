package hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kpc.dao.MemberDao;
import kr.or.kpc.dto.MemberDto;

/**
 * Servlet implementation class TomcatConnectionServlet
 */
@WebServlet("/tcon")
public class TomcatConnectionServlet extends HttpServlet {
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>member servlet</title>");
		out.println("</head>");
		out.println("<body>");
		
		MemberDao dao = MemberDao.getInstance();
		ArrayList<MemberDto> list = dao.select(0,10);
		
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>번호</th><th>이름</th><th>주소</th>");
		out.println("</tr>");
		
		for(MemberDto dto : list) {
			int num = dto.getNum();
			String name = dto.getName();
			String addr = dto.getAddr();
			out.println("<tr>");
			out.println("<td>"+num+"</td>");
			out.println("<td>"+name+"</td>");
			out.println("<td>"+addr+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	
	}

}
