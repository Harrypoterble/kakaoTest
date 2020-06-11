package com.coupons.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coupons.mainProcess;

public class postCouponsTest {

	@Test(timeout=2000)
	public void testProcessCoupons() {
		mainProcess mp = new mainProcess();
		mp.processCoupons("I",1,null);
	}

}
