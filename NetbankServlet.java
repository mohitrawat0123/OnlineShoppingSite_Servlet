import java.io.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.annotation.Resource;
@WebServlet(urlPatterns={"/bank"})
public class NetbankServlet extends HttpServlet{
	@Resource(mappedName="tindey")
	DataSource ds;
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String cardno=req.getParameter("cardno");
		String bankname=req.getParameter("bankname");
		String name=req.getParameter("name");
		ServletContext ctx=getServletContext();
		out.println("<html><body>");
		try{
			String conn=ctx.getInitParameter("ctxconn");
			Connection c;
			if(conn.equals("no"))	c=ds.getConnection();
			else	c=(Connection)ctx.getAttribute("con");
			Statement s=c.createStatement();
			ResultSet rs=s.executeQuery("SELECT * FROM bank WHERE cardno='"+cardno+"' and bankname='"+bankname+"'");
			if(rs.next()){
				//out.println("Credentials matched");
				out.println("<html><body>");
				out.println("<h1>Thank You for Buying.</h1><br>");
				out.println("<h2>See you agian !"+name+"</h2><br>");
				out.println("</body></html>");
				res.setHeader("Refresh","4;welcome.html");
			}else{
				out.println("<html><body>");
				out.println("<h1>XX--INVALID Bank Credentials--XX</h1><br>");
				out.println("</body></html>");
				res.setHeader("Refresh","3;netbank.html");
			}
			if(conn.equals("no"))	c.close();
		}catch(Exception e){
			out.println(e);
		}
		out.println("</body></html>");
	}
}