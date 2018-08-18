import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet(urlPatterns="/women")
public class WomenServlet extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		HttpSession session=req.getSession(false);
		if(session!=null){
			String[] womens=req.getParameterValues("women");
			session.setAttribute("womens",womens);
			RequestDispatcher rd=req.getRequestDispatcher("/welcome.html");
			rd.forward(req,res);
		}else{
			RequestDispatcher rd=req.getRequestDispatcher("/index.html");
			rd.forward(req,res);
		}
	}
}
