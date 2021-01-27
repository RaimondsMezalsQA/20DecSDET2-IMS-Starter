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

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DatabaseUtilities;

public class OrdersDao implements IDomainDao<Orders> {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final ItemsDao itemsClass = new ItemsDao();

	@Override
	public Orders create(Orders orders) {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO orders(f_cid) VALUES (" + orders.getOCustomer().getId() + ")");) {
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Orders read(Long oid) {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE oid = ?");) {
			statement.setLong(1, oid);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public List<Items> readAll2(Long orderId) {

		List<Items> itemsList = new ArrayList<>();

		try (Connection connection = DatabaseUtilities.getInstance().getConnection();

				PreparedStatement statement = connection.prepareStatement(
						"SELECT items.item_name, items.iid, items.price FROM items JOIN orderline ON items.iid = f_iid WHERE orderline.f_oid = ? ");) {
			statement.setLong(1, orderId);
			ResultSet resultSet2 = statement.executeQuery();
			while (resultSet2.next()) {
				ItemsDao itemsDao = new ItemsDao();
				itemsList.add(itemsDao.modelFromResultSet(resultSet2));
			}
			return itemsList;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}

		return null;
	}

	@Override
	public List<Orders> readAll() {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Orders> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Orders readLatest() {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY oid DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Orders update(Orders orders) {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orderline SET f_cid = ? WHERE oid = ?");) {
			statement.setLong(1, orders.getOCustomer().getId());
			statement.executeUpdate();
			return read(orders.getOid());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long oid) {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE FROM orderline WHERE f_oid = " + oid);
			return statement.executeUpdate(" DELETE FROM orders WHERE oid = " + oid);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	public Orders AddOrderItem(Orders orders, Long oid, Long iid) {

		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orderline (f_oid, f_iid) VALUES (?,?)");) {
			statement.setLong(1, oid);
			statement.setLong(2, iid);
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public int DeleteOrderItem(Long iid) {

		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("delete from orderline where f_iid = " + iid);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	public Double calcValue(Long f_oid) {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orderline WHERE f_oid = ?");) {
			statement.setLong(1, f_oid);
			ResultSet resultSet = statement.executeQuery();
			Double price = 0.0;
			while (resultSet.next()) {
				price += itemsClass.read(resultSet.getLong("f_iid")).getPrice();
			}
			return price;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0.0;
	}

	@Override
	public Orders modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long oid = resultSet.getLong("oid");
		Long f_cid = resultSet.getLong("f_cid");
		CustomerDao customerDao = new CustomerDao();
		Customer customer = customerDao.read(f_cid);
		Double value = calcValue(oid);
		;
		Orders itemList = new Orders();
		List<Items> oItems = itemList.getOItems();

		return new Orders(oid, customer, value, oItems);
	}

}