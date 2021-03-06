package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDao;
import com.qa.ims.persistence.dao.OrdersDao;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.JavaUtilities;

public class OrdersController implements ICrudController<Orders> {

    public static final Logger LOGGER = LogManager.getLogger();

    private OrdersDao ordersDao;
    private JavaUtilities javaUtilities;

    public OrdersController(OrdersDao ordersDao, JavaUtilities javaUtilities) {
        super();
        this.ordersDao = ordersDao;
        this.javaUtilities = javaUtilities;
    }

    @Override
    public List<Orders> readAll() {
        List<Orders> order = ordersDao.readAll();
        for (Orders orders : order) {
            LOGGER.info(orders);
            List<Items> item = ordersDao.readAll2(orders.getOid());
            for(Items itemList : item) {
            	
            	LOGGER.info(itemList);
            	
            }
        }
        return order;
    }

    @Override
    public Orders create() {
        LOGGER.info("Please enter a customer id");
        Long f_cid = javaUtilities.getLong();
        CustomerDao customerDao = new CustomerDao();
        Orders orders = ordersDao.create(new Orders(customerDao.read(f_cid)));
        LOGGER.info("Order created");
        return orders;
    }

    @Override
    public Orders update() {
        LOGGER.info("Please enter the id of the order you would like to update");
        Long oid = javaUtilities.getLong();
        
        LOGGER.info("Would you like to (add or remove) an item from the order?");
        String choice = javaUtilities.getString();
        
        if(choice.contentEquals("add"))
        {
        	LOGGER.info("Please enter an item id that you would like to add to this order");
            Long f_iid = javaUtilities.getLong();
        	return ordersDao.AddOrderItem(oid, f_iid);
        }else if(choice.equals("remove")) {
        	LOGGER.info("Please enter an item id that you would like to remove from this order");
        	Long f_iid = javaUtilities.getLong();
        	ordersDao.DeleteOrderItem(f_iid);
        }
        
        LOGGER.info("Order Updated");
		return null;
    }

    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the order you would like to delete");
        Long oid = javaUtilities.getLong();
        return ordersDao.delete(oid);
    }

}
