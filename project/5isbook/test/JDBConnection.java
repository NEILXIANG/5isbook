

import java.sql.SQLException; 
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;

public class JDBConnection {
 String UserId = "root";
 String UserPassword = "123";
 String url="jdbc:mysql://localhost:3306/5isbook";
 String dri="com.mysql.jdbc.Driver";

 ResultSet rs = null;
 Connection conect = null;
 public JDBConnection() {
     try {
     Class.forName(dri);
   }
   catch (java.lang.ClassNotFoundException e) {
     System.err.println(e.getMessage());
   }
 }

 
 public ResultSet executeQuery(String sql) {
   try {
     conect = DriverManager.getConnection(url, UserId,UserPassword);
     Statement stmt = conect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
     rs = stmt.executeQuery(sql);
   }
   catch (SQLException ex) {
     System.err.println(ex.getMessage());
   }
   return rs;
 }

 public int executeUpdate(String sql) {
   int result = 0;
   try {
	 conect = DriverManager.getConnection(url, UserId,UserPassword);
     Statement stmt = conect.createStatement();
     result = stmt.executeUpdate(sql);
   }
   catch (SQLException er) {
     System.err.println(er.getMessage());
   }
   return result;
 }

 public void close() {
   try {
     if (conect != null) {
       conect.close();
     }
   }
   catch (Exception e) {
     System.out.println(e);
   }
   }
 
 public static void main(String args[]) 
	{ 
	 JDBConnection jdb = new JDBConnection();
	 String sql = "select * from document";
	 ResultSet rs = jdb.executeQuery(sql);
	 try {
		  while (rs.next()) 
		   System.out.println(rs.getString(2));
	 }
      catch (SQLException ex) {
         ex.printStackTrace();
		  }
    }
 
}


