package com.coupons;

import org.h2.tools.DeleteDbFiles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mainProcess {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USER = "sa"; 
    private static final String DB_PASSWORD = "";              
    	
	public boolean processCoupons(String act, int num, String cpn) {
		try {
//            DeleteDbFiles.execute("~", "test", true); //table drop
            initDB(act, num, cpn);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
		
	}

    private void initDB(String act, int num, String cpn) throws SQLException {
        Connection connection = getDBConnection();
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();

            // create COUPONS table
			stmt.execute("CREATE TABLE IF NOT EXISTS COUPONS " + 
					" ( CPN1 varchar2(5) NOT NULL,		" + 
					"   CPN2 varchar2(6) NOT NULL, 		" + 
					"   CPN3 varchar2(8) NOT NULL, 		" + 
					"   PAYYN varchar2(1) NOT NULL,		" + 
					"   USEYN varchar2(1) NOT NULL, 	" + 
					"   ENDDT varchar2(8) NOT NULL, 	" + 
					"   REGDT varchar2(8) NOT NULL, 	" + 
					"   REGID varchar2(10) NOT NULL,	" + 
					"   CHGDT varchar2(8), 			    " + 
					"   CHGID varchar2(10),				" + 
					"PRIMARY KEY (CPN1, CPN2, CPN3)		" + 
					");");            
            // create COUPONS_IDX1 index
			stmt.execute("CREATE INDEX IF NOT EXISTS COUPONS_IDX1 ON COUPONS(PAYYN, USEYN)");
           
			if ("I".equals(act)) {// 랜덤한 코드의 쿠폰을 N개 생성하여 데이터 베이스에 보관
				if (num > 0) {
				postCoupons pc = new postCoupons();
	            pc.createCoupons(stmt,num);
				}
			}else if ("P".equals(act)) {// 생성한 쿠폰중 하나를 사용자에게 지급 
				putCoupons ptc = new putCoupons();
				System.out.println("쿠폰번호("+ptc.payCoupon(stmt)+")");				
			}else if ("S".equals(act)) {// 사용자에게 지급된 쿠폰을 조회
				getCouponList gtc = new getCouponList();
				System.out.println(gtc.paidCouponList(stmt));				
			}else if ("U".equals(act)) {// 지급된 쿠폰중 하나를 사용
				if (cpn != null) {
				putCoupons ptc = new putCoupons();
				System.out.println(ptc.useCoupon(stmt,cpn));	
				}
			}else if ("C".equals(act)) {// 지급된 쿠폰중 하나를 사용 취소
				if (cpn != null) {
				delCoupons dc = new delCoupons();
				dc.cancelCoupon(stmt, cpn);	
				}
			}else if ("E".equals(act)) {//발급된 쿠폰중 당일 만료된 전체 쿠폰 목록을 조회
				getCouponList gtc = new getCouponList();
				System.out.println(gtc.expCouponList(stmt));
			}            
             
            // 테스트용 테이블 데이터 전체출력 
            ResultSet rs = stmt.executeQuery("SELECT * FROM COUPONS;");
            while (rs.next()) {
                System.out.println("cpn : " + rs.getString("cpn1") + "-" + rs.getString("cpn2") + "-"  + rs.getString("cpn3"));
                System.out.println("payYn : " + rs.getString("payYn"));
                System.out.println("useYn : " + rs.getString("useYn"));
                System.out.println("endDt : " + rs.getString("endDt"));
                System.out.println("regDt : " + rs.getString("regDt"));
                System.out.println("regID : " + rs.getString("regID"));
                System.out.println("chgDt : " + rs.getString("chgDt"));
                System.out.println("chgID : " + rs.getString("chgID"));
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
