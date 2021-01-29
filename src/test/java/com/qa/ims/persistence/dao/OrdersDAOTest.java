package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DatabaseUtilities;

public class OrdersDAOTest {

	private final OrdersDao DAO = new OrdersDao(new ItemsDao(), new CustomerDao());
	
	private Orders testOrders;
	
    @Before
    public void setup() {
        DatabaseUtilities.connect();
        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
        Customer cust = new Customer(1L, "jordan", "harrison");
    	List<Items> listItems = new ArrayList<>();
    	listItems.add(new Items(1L, "bread", 13.21));
    	Double value = 13.21;
    	testOrders = (new Orders(1L, cust, value, listItems));
    }
    
    @Test
    public void testCreate() {
    	Customer cust = new Customer(1L, "jordan", "harrison");
    	List<Items> listItems = new ArrayList<>();
    	Double value = 0.0;
        final Orders created = new Orders(2L, cust, value, listItems);
        assertEquals(created, DAO.create(created));
    }

    @Test
    public void testReadAll() {
    	List<Orders> listOrders = new ArrayList<>();
    	listOrders.add(testOrders);
        assertEquals(listOrders.toString(), DAO.readAll().toString());
    }
    
    @Test
    public void testReadLatest() {
        assertEquals(testOrders.toString(), DAO.readLatest().toString());
    }

    @Test
    public void testRead() {
        assertEquals(testOrders.toString(), DAO.read(1L).toString());
    }

    @Test
    public void addOrderItemTest() {
    	
    	Customer cust = new Customer(1L, "jordan", "harrison");
    	List<Items> listItems = new ArrayList<>();
    	listItems.add(new Items(1L, "bread", 13.21));
    	Double value = 26.42;
    	testOrders = (new Orders(1L, cust, value, listItems));
    	assertEquals(testOrders.toString(), DAO.AddOrderItem(1L, 1L).toString());
    }
    
    @Test
    public void deleteOrderItemTest() {
    	assertEquals(1, DAO.DeleteOrderItem(1L));
    }

    @Test
    public void testDelete() {
        assertEquals(1, DAO.delete(1));
    }
    
    @Test
    public void testReadAll2() {
    	List<Items> listItems = new ArrayList<>();
    	listItems.add(new Items(1L, "bread", 13.21));
        assertEquals(listItems.toString(), DAO.readAll2(1L).toString());
    }
    
}
