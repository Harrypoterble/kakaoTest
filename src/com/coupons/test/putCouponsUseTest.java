package com.coupons.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coupons.mainProcess;

public class putCouponsUseTest {

	@Test
	public void testUseCoupon() {
		mainProcess mp = new mainProcess();
		mp.processCoupons("U",0,"58904-b08fa2-f4e94927");
	}

}
