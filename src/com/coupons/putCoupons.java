package com.coupons;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class putCoupons {
	String cpn;
	
	public String payCoupon(Statement stmt) {// 생성한 쿠폰중 하나를 사용자에게 지급 
		try {
			
			ResultSet rs = stmt.executeQuery("SELECT CPN1,CPN2,CPN3 FROM COUPONS WHERE PAYYN='N' AND USEYN='N' AND ROWNUM=1");
            while (rs.next()) {
            	cpn = rs.getString("CPN1")+rs.getString("CPN2")+rs.getString("CPN3");
            }
            
			stmt.execute("UPDATE COUPONS " + 
					"SET PAYYN = 'Y', CHGDT=TO_CHAR(SYSDATE,'YYYYMMDD'), CHGID='SYSTEM' " + 
					"WHERE CPN1||CPN2||CPN3 = '"+cpn+"' "
					);			
			return cpn==null ? "없음" : cpn.substring(0,5)+"-"+cpn.substring(5,11)+"-"+cpn.substring(11,19);
		} catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
		return "없음";
	}
	
	public void useCoupon(Statement stmt,String cpn) {// 지급된 쿠폰중 하나를 사용
		String rcpn = cpn.replace("-", ""); 
		try {
			stmt.execute("UPDATE COUPONS " + 
					"SET USEYN = 'Y', CHGDT=TO_CHAR(SYSDATE,'YYYYMMDD'), CHGID='SYSTEM' " + 
					"WHERE PAYYN = 'Y' AND CPN1||CPN2||CPN3 = '"+rcpn+"' "
					);
		} catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
