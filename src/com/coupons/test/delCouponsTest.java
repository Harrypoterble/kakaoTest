package com.coupons.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coupons.delCoupons;
import com.coupons.mainProcess;

public class delCouponsTest {

	@Test
	public void testCancelCoupon() {
		mainProcess mp = new mainProcess();
		mp.processCoupons("C",0,"58904-b08fa2-f4e94927");
	}

}
