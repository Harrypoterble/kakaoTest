package com.coupons.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coupons.mainProcess;

public class getCouponListPaidTest {

	@Test
	public void testPaidCouponList() {
		mainProcess mp = new mainProcess();
		mp.processCoupons("S",0,null);
	}

}
