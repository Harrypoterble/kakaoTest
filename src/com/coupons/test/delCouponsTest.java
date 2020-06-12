package com.coupons.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.coupons.mainProcess;

public class delCouponsTest {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USER = "sa"; 
    private static final String DB_PASSWORD = ""; 
    private String cpn;
    
	@Test
	public void testCancelCoupon() {
		
		try {
	        cpn = makeData(); //지급완료되고 사용된 쿠폰번호 1건 추출    
	        System.out.println(cpn==null?"대상건이 없습니다. ":cpn +" 쿠폰이 사용취소 되었습니다.");
			}catch (Exception e) {System.out.println("fail to make a test data");}
		
		mainProcess mp = new mainProcess();
		mp.processCoupons("C",0,cpn);
	}
	
	public String makeData() throws SQLException{
		
		Connection connection = getDBConnection();
        Statement stmt = null;
        String subCpn = null;
        try {
        	
            stmt = connection.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT CPN1||'-'||CPN2||'-'||CPN3 AS CPN "
            							   + "FROM COUPONS "
            							   + "WHERE PAYYN='Y' AND USEYN='Y' AND ROWNUM=1");
            while(rs.next()) {
            subCpn = rs.getString("CPN");
            }
			
            stmt.close();
            
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        
        return subCpn;
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
