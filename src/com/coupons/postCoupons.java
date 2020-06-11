package com.coupons;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class postCoupons {
	UUID rn;
	String cpn = null;
	String cpn1 = null;
	String cpn2 = null;
	String cpn3 = null;
	static final int expDay = 10; //expire days
	
	public void createCoupons(Statement stmt, int num) {
		try {
			for (int i=0;i<num;i++) {
			rn = UUID.randomUUID();
			cpn = rn.toString().replace("-", "");
			cpn1 = cpn.substring(0,5);
			cpn2 = cpn.substring(5,11);
			cpn3 = cpn.substring(11,19);
			
			stmt.execute("INSERT INTO COUPONS VALUES"
					+ " ('"+cpn1+"','"+cpn2+"','"+cpn3+"',"+"'N','N',TO_CHAR(SYSDATE+"+expDay+",'YYYYMMDD'),TO_CHAR(SYSDATE,'YYYYMMDD'),'SYSTEM',NULL,NULL)"
					);
			}
			
		} catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
}
