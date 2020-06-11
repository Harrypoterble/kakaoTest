package com.coupons.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coupons.mainProcess;
import com.coupons.putCoupons;

public class putCouponsPayTest {

	@Test(timeout=2000)
	public void testPayCoupon() {
		mainProcess mp = new mainProcess();
		mp.processCoupons("P",3);
	}

}
