package ua.itea.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.itea.dao.ProductDAO;
import ua.itea.models.Product;

public class MySQLProductDAO implements ProductDAO {

	private final String SELECT_ALL = "SELECT * FROM products";
	private final String SELECT_CATEGORY = "SELECT * FROM products WHERE category = ?";
	private final String SELECT_ONE = "SELECT * FROM products WHERE id = ?";

	@Override
	public Product getProductById(long id) {
		try (Connection conn = MySQLDAOFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(SELECT_ONE)) {
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return makeProduct(rs);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return null;
	}

	@Override
	public List<Product> getProductList() {
		List<Product> productList = new ArrayList<Product>();
		try (
			Connection conn = MySQLDAOFactory.getConnection();
			Statement selectStmt = conn.createStatement();
			ResultSet rs = selectStmt.executeQuery(SELECT_ALL);
				) {
			while (rs.next()) {
				productList.add(makeProduct(rs));
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return productList;
	}

	@Override
	public List<Product> getProductListByCategory(long category) {
		List<Product> productList = new ArrayList<Product>();
		try (Connection conn = MySQLDAOFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(SELECT_CATEGORY)) {
			ps.setLong(1, category);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				productList.add(makeProduct(rs));
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return productList;
	}
	
	private Product makeProduct(ResultSet rs) throws SQLException {
		Product p = new Product();
		p.setId(rs.getLong("id"));
		p.setName(rs.getString("name"));
		p.setDescription(rs.getString("description"));
		p.setPrice(rs.getLong("price"));
		p.setCategory(rs.getLong("category"));
		return p;
	}

}
