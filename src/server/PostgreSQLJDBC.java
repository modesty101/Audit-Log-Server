package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLXML;

public class PostgreSQLJDBC {

	public static void main(String[] args) throws Exception {
		  BufferedReader br = new BufferedReader(new FileReader("TLS_Cert.xml"));
		  PreparedStatement ps = null;
		  Connection c = null;
	      String xml = null;
	      String input = null;
	     
	      while((xml = br.readLine()) != null) {
	    	  input = xml;
	      }
	      input = input.toString();
	      System.out.println(input);
	 
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://192.168.214.138:5432/niko",
	            "postgres", "1234");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         
	         String sql = "INSERT INTO Log (info) "
	               + "VALUES (XML(?));";
	         
	         ps = c.prepareStatement(sql);
	         
	         SQLXML sqlxml = c.createSQLXML();
	         sqlxml.setString(input);
	         
	         ps.setSQLXML(1, sqlxml);
	         ps.executeUpdate();
	         
	        /*
	         ps = c.prepareStatement(sql);
	         ps.setString(1, input);
	         ps.executeUpdate();
	         
	         String xmlSql = "select xmlcomment(?)";
	         ps=c.prepareStatement(xmlSql);
	         ps.setString(1, input);
	         System.out.println(xmlSql);
	         //ps.executeUpdate();
	         */
	         c.commit();
	         c.close();
	      } catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Records created successfully");
	   }
			
		/*
		/**
		 * 로그 테이블 생성
		 *  __________________
		 * | NO | DATE | INFO |
		 *  ------------------
		 * |	|	   |	  |
		 * |____|______|______|
		 
		Connection c=null;
		Statement stmt = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			c= DriverManager.getConnection("jdbc:postgresql://192.168.214.138:5432/niko","postgres","1234");
			System.out.println("Opened Database successfully!!");
			
			stmt = c.createStatement();
			String sql = "create table Log " +
							"( No serial not null," +
							"Date timestamp default current_timestamp not null," +
							"Info xml not null)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Log table created successfully..!");
		*/
}
