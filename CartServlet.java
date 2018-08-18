import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet(urlPatterns={"/cart"})
public class CartServlet extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		HttpSession session=req.getSession(false);
		if(session!=null){
			String[] watches=(String[])session.getAttribute("watches");
			String[] cars=(String[])session.getAttribute("cars");
			String[] mens=(String[])session.getAttribute("mens");
			String[] womens=(String[])session.getAttribute("womens");
			String[] mobiles=(String[])session.getAttribute("mobiles");
			String[] laptops=(String[])session.getAttribute("laptops");
			out.println("<html><body>");
			out.println("<table bgcolor='yellow' border=1 width=400>");
			out.println("<th>Item</th>");
			out.println("<th>Price</th>");
			long total=0;
			if(mens!=null){
				for(int i=0;i<mens.length;i++){
					String[] sp=mens[i].split("/");
					out.println("<tr>");
					for(int j=0;j<sp.length;j++){
						if(j==1) total+=Long.parseLong(sp[j]);
						out.println("<td>"+sp[j]+"</td>");
					}
					out.println("</tr>");
				}
				
			}
			if(womens!=null){
				
				for(int i=0;i<womens.length;i++){
					String[] sp=womens[i].split("/");
					out.println("<tr>");
					for(int j=0;j<sp.length;j++){
						if(j==1) total+=Long.parseLong(sp[j]);
						out.println("<td>"+sp[j]+"</td>");
					}
						out.println("</tr>");
				}
			}
			if(laptops!=null){
				for(int i=0;i<laptops.length;i++){
					String[] sp=laptops[i].split("/");
					out.println("<tr>");
					for(int j=0;j<sp.length;j++){
						if(j==1) total+=Long.parseLong(sp[j]);
						out.println("<td>"+sp[j]+"</td>");
					}
					out.println("</tr>");
				}
			}
			if(mobiles!=null){
				for(int i=0;i<mobiles.length;i++){
					String[] sp=mobiles[i].split("/");
					out.println("<tr>");
					for(int j=0;j<sp.length;j++){
						if(j==1) total+=Long.parseLong(sp[j]);
						out.println("<td>"+sp[j]+"</td>");
					}
					out.println("</tr>");
				}
			}
			if(watches!=null){
				for(int i=0;i<watches.length;i++){
					String[] sp=watches[i].split("/");
					out.println("<tr>");
					for(int j=0;j<sp.length;j++){
						if(j==1) total+=Long.parseLong(sp[j]);
						out.println("<td>"+sp[j]+"</td>");
					}
					out.println("</tr>");
				}
			}
			if(cars!=null){
				for(int i=0;i<cars.length;i++){
					String[] sp=cars[i].split("/");
					out.println("<tr>");
					for(int j=0;j<sp.length;j++){
						if(j==1) total+=Long.parseLong(sp[j]);
						out.println("<td>"+sp[j]+"</td>");
					}
					out.println("</tr>");
				}
			}
			out.println("<tr><td>Total</td>");
			out.println("<td>Rs "+total+"</td></tr>");
			out.println("</table><br><br>");
			out.println("<a href='netbank.html'>");
			out.println("<input type='SUBMIT' value='Buy Now'></a><br><br>");
			out.println("<a href='welcome.html'>");
			out.println("<input type='SUBMIT' value='Back'></a>");
		}else{
			RequestDispatcher rd=req.getRequestDispatcher("/index.html");
			rd.forward(req,res);
		}
	}
}
