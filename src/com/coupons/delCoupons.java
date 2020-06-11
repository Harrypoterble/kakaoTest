package com.coupons;

import java.sql.SQLException;
import java.sql.Statement;

public class delCoupons {
	
	public void cancelCoupon(Statement stmt,String cpn) {// 지급된 쿠폰중 하나를 사용 취소
		String rcpn = cpn.replace("-", ""); 
		try {
			stmt.execute("UPDATE COUPONS " + 
					"SET USEYN = 'N', CHGDT=TO_CHAR(SYSDATE,'YYYYMMDD'), CHGID='SYSTEM' " + 
					"WHERE PAYYN = 'Y' AND USEYN = 'Y' AND CPN1||CPN2||CPN3 = '"+rcpn+"' "
					);
		} catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
