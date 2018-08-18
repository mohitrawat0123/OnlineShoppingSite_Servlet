import java.io.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.annotation.Resource;
import javax.servlet.annotation.WebFilter;
@WebFilter(urlPatterns={"/login"})
public class LoginFilter implements Filter{
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
		ServletContext ctx=config.getServletContext();
		try{
			String conn=ctx.getInitParameter("ctxconn");
			Connection c;
			if(conn.equals("no"))	c=ds.getConnection();
			else c=(Connection)ctx.getAttribute("con");
			Statement s=c.createStatement();
			ResultSet rs=s.executeQuery("SELECT * FROM cust WHERE userId='"+userId+"' and pass='"+pass+"'");
			if(rs.next()){
				if(rs.getString("loginStatus").equals("F")){
					//s.executeQuery("UPDATE cust set loginStatus='T' WHERE userId='"+userId+"' and pass='"+pass+"'");
					chain.doFilter(req,res);
					HttpServletResponse hres=(HttpServletResponse)res;
					hres.setHeader("Refresh","1;welcome.html");
				}else{
					out.println("<html><body><h2>User already Login</h2></body></html>");
					HttpServletResponse hres=(HttpServletResponse)res;
					hres.setHeader("Refresh","4;index.html");
					}
			}else{
				out.println("<h2>Invalid Credentials !</h2>");
				HttpServletResponse hres=(HttpServletResponse)res;
				hres.setHeader("Refresh","2;index.html");
			}
			if(conn.equals("no"))	c.close();
		}catch(Exception e){
			out.println(e);
		}
	}
	public void destroy(){}
}