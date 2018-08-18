import java.io.*;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet(urlPatterns={"/table"},loadOnStartup=1)
public class ApplicationInitializer extends HttpServlet{
	public void init(javax.servlet.ServletConfig sc)throws javax.servlet.ServletException{
		Connection con=null;
		InitialContext ictx=null;
		ServletContext ctx=sc.getServletContext();
		String val=ctx.getInitParameter("oracletable");
		String conn=ctx.getInitParameter("ctxconn");
		if(conn.equals("yes"))	con=(Connection)ctx.getAttribute("con");
		else{
			try{
				ictx=new InitialContext();
			}catch(NamingException ne){}
			
		}
		String oracle=ctx.getRealPath("WEB-INF//dbtable//oracletable.sql");
		try{
			if(val.equals("yes"))	TableCreate.createTab(oracle,ictx,con);			
		}catch(Exception e){}
		
	}
}
