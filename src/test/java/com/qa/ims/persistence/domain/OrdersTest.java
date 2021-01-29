package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrdersTest {
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Orders.class).verify();
	}

}
