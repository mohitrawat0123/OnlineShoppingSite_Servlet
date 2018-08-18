import java.io.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.annotation.Resource;
import javax.servlet.annotation.WebFilter;
@WebFilter(urlPatterns={"/register"})
public class RegisterFilter implements Filter{
	FilterConfig config;
	public void init(FilterConfig config){
		this.config=config;
	}
	@Resource(mappedName="tindey")
	DataSource ds;
	public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain)throws ServletException,IOException{
		PrintWriter out=res.getWriter();
		String userId=req.getParameter("userId");
		String pass=req.getParameter("pass");
		String repass=req.getParameter("repass");
		if(pass.equals(repass)){
			ServletContext ctx=config.getServletContext();
			try{
				String conn=ctx.getInitParameter("ctxconn");
				Connection c;
				if(conn.equals("no"))	c=ds.getConnection();
				else	c=(Connection)ctx.getAttribute("con");
				Statement s=c.createStatement();
				ResultSet rs=s.executeQuery("SELECT * FROM cust WHERE userId='"+userId+"' and pass='"+pass+"'");
				if(rs.next())	chain.doFilter(req,res);
				else{
					PreparedStatement ps=c.prepareStatement("INSERT INTO cust VALUES(?,?,'F')");
					ps.setString(1,userId);
					ps.setString(2,pass);
					ps.executeQuery();
					out.println("<html><body>");
					out.println("<h1>Registered Succesfully ! Now you may login</h1><br>");
					out.println("<h2>Please Wait.Login Page Reloading....");
					HttpServletResponse hres=(HttpServletResponse)res;
					hres.setHeader("Refresh","4;index.html");
					//RequestDispatcher rd=req.getRequestDispatcher("/registerd");
					//rd.forward(req,res);
				}
				if(conn.equals("no"))	c.close();
			}catch(Exception e){
				out.println(e);
			}
		}else{
			out.println("<html><body>");
			out.println("<h1>XX--Password and Re-Password did not match--XX</h1><br><h2>TRY AGAIN !</h2><br>");
			out.println("<h2>Registration Page Reloading....</h2>");
			out.println("</body></html>");
			HttpServletResponse hres=(HttpServletResponse)res;
			hres.setHeader("Refresh","4;register.html");
		}
	}
	public void destroy(){}
}