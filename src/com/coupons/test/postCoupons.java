package com.coupons.test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class postCoupons {

	String cpn = null;
	String cpn1 = null;
	String cpn2 = null;
	String cpn3 = null;
	static final int expDay = 10; //expire days
	
	public void createCoupons(Statement stmt) {
		try {
			UUID num = UUID.randomUUID();
			cpn = num.toString().replace("-", "");
			cpn1 = cpn.substring(0,5);
			cpn2 = cpn.substring(5,11);
			cpn3 = cpn.substring(11,19);
			System.out.println(cpn);
			System.out.println(cpn1);
			System.out.println(cpn2);
			System.out.println(cpn3);
			
			stmt.execute("INSERT INTO COUPONS VALUES"
					+ " ('"+cpn1+"','"+cpn2+"','"+cpn3+"',"+"'N','N',to_char(sysdate+"+expDay+",'yyyymmdd'),to_char(sysdate,'yyyymmdd'),'Jack',null,null)"
//					+ ",(2, 'OskarDevelopers');"
					);

		} catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
}
