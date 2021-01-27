package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DatabaseUtilities;

public class ItemsDao implements IDomainDao<Items> {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Items create(Items items) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO items(item_name, price) VALUES (?, ?)");) {
            statement.setString(1, items.getItemName());
            statement.setDouble(2, items.getPrice());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public Items read(Long iid) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE iid = ?");) {
            statement.setLong(1, iid);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        } 
        return null;
    }

    @Override
    public List<Items> readAll() {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
            List<Items> items = new ArrayList<>();
            while (resultSet.next()) {
                items.add(modelFromResultSet(resultSet));
            }
            return items;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Items readLatest() {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY iid DESC LIMIT 1");) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Items update(Items items) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("UPDATE items SET item_name = ?, price = ? WHERE iid = ?");) {
            statement.setString(1, items.getItemName());
            statement.setDouble(2, items.getPrice());
            statement.setLong(3, items.getIId());
            statement.executeUpdate();
            return read(items.getIId());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public int delete(long iid) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                Statement statement = connection.createStatement();) {
            return statement.executeUpdate("DELETE FROM items WHERE iid = " + iid);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public Items modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long iid = resultSet.getLong("iid");
        String item_name = resultSet.getString("item_name");
        Double price = resultSet.getDouble("price");
        return new Items(iid, item_name, price);
    }

}