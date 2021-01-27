package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DatabaseUtilities;

public class OrdersDAOTest {

	private final OrdersDao DAO = new OrdersDao(new ItemsDao(), new CustomerDao());
	
    @Before
    public void setup() {
        DatabaseUtilities.connect();
        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }
    
    @Test
    public void testCreate() {
    	final Customer cId = new Customer(2L, "Jony", "Silverhand");
        final Orders created = new Orders(2L, cId);
        assertEquals(created, DAO.create(created));
    }

}
