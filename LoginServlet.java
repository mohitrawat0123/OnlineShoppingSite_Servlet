import java.io.*;
import java.sql.*;
import javax.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet(urlPatterns={"/login"})
public class LoginServlet extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String userId=req.getParameter("userId");
		String pass=req.getParameter("pass");
		try{
			HttpSession session=req.getSession();
			session.setAttribute("userId",userId);
			session.setAttribute("pass",pass);
		}catch(Exception e){
			out.println(e);
			System.out.println(e);
		}
	}
}
