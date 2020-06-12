package com.coupons.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.coupons.mainProcess;

public class getCouponListExpTest {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USER = "sa"; 
    private static final String DB_PASSWORD = "";  
    
	@Test(timeout=5000)
	public void testExpCouponList() {
		try {
        makeData(); //당일만료 데이터 1건 생성   
		}catch (Exception e) {System.out.println("fail to make a test data");}
        
		mainProcess mp = new mainProcess();
		mp.processCoupons("E",0,null);
	}
	public void makeData() throws SQLException{
		
		Connection connection = getDBConnection();
        Statement stmt = null;
        
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();

			stmt.execute("UPDATE COUPONS " + 
						 "SET ENDDT=TO_CHAR(SYSDATE,'YYYYMMDD'), CHGDT=TO_CHAR(SYSDATE,'YYYYMMDD'), CHGID='UNITTEST' " + 
						 "WHERE ENDDT <> TO_CHAR(SYSDATE,'YYYYMMDD') AND ROWNUM = 1 "
						);
			
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
