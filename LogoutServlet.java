import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet(urlPatterns={"/logout"})
public class LogoutServlet extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		out.println("<html><body>");
		HttpSession session=req.getSession(false);
		if(session!=null) session.invalidate();
		RequestDispatcher rd=req.getRequestDispatcher("/index.html");
		//make the status of user false
		rd.forward(req,res);
		out.println("</body></html>");
	}
}
