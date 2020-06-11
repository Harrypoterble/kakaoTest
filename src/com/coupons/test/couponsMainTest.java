package com.coupons.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class couponsMainTest {

	@Test
	public void testProcessCoupons() {
		couponsMain cm = new couponsMain();
		cm.processCoupons("I", 5);
	}

}
