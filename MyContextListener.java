import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebListener;
@WebListener
public class MyContextListener implements ServletContextListener{
	Connection c;
	public void contextInitialized(ServletContextEvent e){
		try{
			ServletContext ctx=e.getServletContext();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","ODB10g");
			ctx.setAttribute("con",c);
			System.out.println("context created");
		}catch(Exception ex){}
	}
	public void contextDestroyed(ServletContextEvent e){
		try{
			c.close();
		}catch(Exception ex){}
	}
}