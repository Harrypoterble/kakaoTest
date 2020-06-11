package com.coupons.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coupons.mainProcess;

public class mainProcessTest {

	@Test
	public void testProcessCoupons() {
		mainProcess mp = new mainProcess();
		mp.processCoupons("I", 5);
	}

}
