package com.coupons;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class getCouponList {
	static final String paidRslt = "지급없음";
	static final String expRslt = "만료없음";

	public String paidCouponList(Statement stmt) {//사용자에게 지급된 쿠폰을 조회
		try {
			JSONObject jso;
			JSONArray  jsa = new JSONArray();
			
				ResultSet rs = stmt.executeQuery("SELECT CPN1||'-'||CPN2||'-'||CPN3 AS CPN"
												+ ",PAYYN "
												+ "FROM COUPONS "
												+ "WHERE PAYYN='Y'");
	            while (rs.next()) {
	            	jso = new JSONObject();
	            	jso.put("CPN", rs.getString("CPN"));
	            	jso.put("PAYYN", rs.getString("PAYYN"));
	            	
	            	jsa.add(jso);
	            }
	            
				return jsa==null ? paidRslt : jsa.toJSONString();
			} catch (SQLException e) {
	            System.out.println("Exception Message " + e.getLocalizedMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return paidRslt;
	}
	
	public String expCouponList(Statement stmt) {//발급된 쿠폰중 당일 만료된 전체 쿠폰 목록을 조회
		try {
			JSONObject jso;
			JSONArray  jsa = new JSONArray();
			
				ResultSet rs = stmt.executeQuery("SELECT CPN1||'-'||CPN2||'-'||CPN3 AS CPN"
												+ ",ENDDT "
												+ "FROM COUPONS "
												+ "WHERE ENDDT=TO_CHAR(SYSDATE,'YYYYMMDD')");
	            while (rs.next()) {
	            	jso = new JSONObject();
	            	jso.put("CPN", rs.getString("CPN"));
	            	jso.put("ENDDT", rs.getString("ENDDT"));
	            	
	            	jsa.add(jso);
	            }
	            
				return jsa.toJSONString();
			} catch (SQLException e) {
	            System.out.println("Exception Message " + e.getLocalizedMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return expRslt;
	}
}
