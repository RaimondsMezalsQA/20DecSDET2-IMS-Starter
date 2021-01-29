package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemsDao;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.JavaUtilities;

public class ItemsController implements ICrudController<Items> {

    public static final Logger LOGGER = LogManager.getLogger();

    private ItemsDao itemsDao;
    private JavaUtilities javaUtilities;

    public ItemsController(ItemsDao itemsDao, JavaUtilities javaUtilities) {
        super();
        this.itemsDao = itemsDao;
        this.javaUtilities = javaUtilities;
    }

    @Override
    public List<Items> readAll() {
        List<Items> allItems = itemsDao.readAll();
        for (Items items : allItems) {
            LOGGER.info(items);
        }
        return allItems;
    }

    @Override
    public Items create() {
        LOGGER.info("Please enter an item name");
        String item_name = javaUtilities.getString();
        LOGGER.info("Please enter a price");
        Double price = javaUtilities.getDouble();
        Items items = itemsDao.create(new Items(item_name, price));
        LOGGER.info("Item created");
        return items;
    }

    @Override
    public Items update() {
        LOGGER.info("Please enter the iid of the item you would like to update");
        Long iid = javaUtilities.getLong();
        LOGGER.info("Please enter an item name");
        String item_name = javaUtilities.getString();
        LOGGER.info("Please enter a price");
        Double price = javaUtilities.getDouble();
        Items items = itemsDao.update(new Items(iid, item_name, price));
        LOGGER.info("Item Updated");
        return items;
    }

    @Override
    public int delete() {
        LOGGER.info("Please enter the iid of the item you would like to delete");
        Long iid = javaUtilities.getLong();
        return itemsDao.delete(iid);
    }

}
