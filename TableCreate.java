import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;
import javax.servlet.ServletContext;
public class TableCreate{
	public static void createTab(String path,InitialContext ctx,Connection con){
		try{
			FileInputStream fin=new FileInputStream(path);
			byte[] br=new byte[fin.available()];
			fin.read(br);
			fin.close();
			String data=new String(br);
			StringTokenizer str=new StringTokenizer(data,"/");
			Connection c=null;
			Statement s=null;
			if(con==null){
				DataSource ds=(DataSource)ctx.lookup("tindey");
				c=ds.getConnection();
				s=c.createStatement();
				System.out.println("Table is being created via ConnectionPool");
			}else if(ctx==null){
				s=con.createStatement();
				System.out.println("Table is being created via ServletContext");
			}
			while(str.hasMoreTokens()){
				String query=str.nextToken();
				if(query.trim().equals("stop"))	break;
				else{
					System.out.println(query);
					s.executeQuery(query);
				}
				
			}
			if(con==null)	c.close();
		}catch(Exception e){}
	}
}