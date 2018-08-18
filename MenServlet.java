import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet(urlPatterns={"/men"})
public class MenServlet extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		HttpSession session=req.getSession(false);
		if(session!=null){
			String[] mens=req.getParameterValues("men");
			session.setAttribute("mens",mens);
			RequestDispatcher rd=req.getRequestDispatcher("/welcome.html");
			rd.forward(req,res);
		}else{
			RequestDispatcher rd=req.getRequestDispatcher("/index.html");
			rd.forward(req,res);
		}
	}
}
