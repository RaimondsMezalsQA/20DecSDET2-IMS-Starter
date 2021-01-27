package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemsTest {
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Items.class).verify();
	}

}
