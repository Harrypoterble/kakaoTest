package com.coupons.test;

import org.h2.tools.DeleteDbFiles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class couponsMain {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/testdb";
    private static final String DB_USER = "sa"; 
    private static final String DB_PASSWORD = "";              
    	
	public boolean processCoupons(String act, int num) {
		try {
            DeleteDbFiles.execute("~", "testdb", true); 
            initDB(act, num);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

    private void initDB(String act, int num) throws SQLException {
        Connection connection = getDBConnection();
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();

            // create COUPONS table
			stmt.execute("CREATE TABLE IF NOT EXISTS COUPONS " + 
					" ( cpn1 varchar2(5) NOT NULL,		" + 
					"   cpn2 varchar2(6) NOT NULL, 		" + 
					"   cpn3 varchar2(8) NOT NULL, 		" + 
					"   payYn varchar2(1) NOT NULL,		" + 
					"   useYn varchar2(1) NOT NULL, 	" + 
					"   endDt varchar2(8) NOT NULL, 	" + 
					"   regDt varchar2(8) NOT NULL, 	" + 
					"   regNm varchar2(10) NOT NULL,	" + 
					"   chgDt varchar2(8), 				" + 
					"   chgNm varchar2(10),				" + 
					"PRIMARY KEY (cpn1, cpn2, cpn3)		" + 
					");");            
            
            // insert random coupon number values into COUPONS table
			if ("I".equals(act)) {
				postCoupons pc = new postCoupons();
	            pc.createCoupons(stmt,num);
			}
			
            
             
            // get result by using SELECT query
            ResultSet rs = stmt.executeQuery("SELECT * FROM COUPONS;");
            while (rs.next()) {
                System.out.println("cpn : " + rs.getString("cpn1") + "-" + rs.getString("cpn2") + "-"  + rs.getString("cpn3"));
                System.out.println("payYn : " + rs.getString("payYn"));
                System.out.println("payYn : " + rs.getString("payYn"));
                System.out.println("useYn : " + rs.getString("useYn"));
                System.out.println("endDt : " + rs.getString("endDt"));
                System.out.println("regDt : " + rs.getString("regDt"));
                System.out.println("regNm : " + rs.getString("regNm"));
                System.out.println("chgDt : " + rs.getString("chgDt"));
                System.out.println("chgNm : " + rs.getString("chgNm"));
            }
            
            stmt.close();
            connection.commit();
            
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
    
    private Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
