import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet(urlPatterns={"/register"})
public class RegisterServlet extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		out.println("<html><body>");
		out.println("<h1>User already Exists !<br>Try Agian !</h1><br>");
		out.println("<h2>Registration Page Reloading....</h2>");
		out.println("</body></html>");
		res.setHeader("Refresh","4;register.html");
		out.println("</body></html>");
	}
}