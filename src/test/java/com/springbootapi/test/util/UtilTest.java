package com.springbootapi.test.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.springbootapi.util.Util;

public class UtilTest {

	@Test
	public void testGetCoordinatesFromIp() {
		Object[] obj = Util.getCoordinatesFromIp("8.8.8.8");
		assertEquals(obj[0], "37.38600");
		assertEquals(obj[1], "-122.08380");
	}
	
	@Test
	public void testGetCoordinatesFromInvalidIp() {
		Object[] obj = Util.getCoordinatesFromIp("127.0.0.0");
		assertEquals(obj[0], "");
		assertEquals(obj[1], "");
	}
	
	@Test
	public void testGetWoeidFromLatLon() {
		assertEquals("2455920", Util.getPositionOnEarthByCoordinates("37.38600", "-122.08380"));
	}
	
	@Test
	public void testGetMaxTempMinTemp() {
		Object[] obj = Util.getWheater("2455920");
		assertNotNull(obj);
	}
}
