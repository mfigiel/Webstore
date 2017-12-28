package com.packt.webstore.domain.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Products.Product;
import com.packt.webstore.domain.repository.IProductRepository;

@Repository
public class ProductRepository implements IProductRepository {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	private List<Map<String, Object>> listOfProductsMap = new ArrayList<Map<String, Object>>();

	public void withdrawProduct(int deletedProductId) {
		this.jdbcTemplate.update("update products set withdrawn = 'Y' where id = ?", deletedProductId);
	}

	public void addProduct(Product newProduct) {
		this.jdbcTemplate.update("insert into products values(null,?,?,?,?,?,?)", newProduct.getName(),
				newProduct.getUnitPrice(), newProduct.getDescription(), newProduct.getCategory(),
				newProduct.getUnitsInStock(), newProduct.getUnitsInOrder());
	}

	public void editProduct(Product editedProduct) {
		this.jdbcTemplate.update(
				"update products set name = ?,unitPrice = ?, description = ?, category = ? , unitsInStock = ?,unitsInOrder = ? where id = ?",
				editedProduct.getName(), editedProduct.getUnitPrice(), editedProduct.getDescription(),
				editedProduct.getCategory(), editedProduct.getUnitsInStock(), editedProduct.getUnitsInOrder(),
				editedProduct.getProductId());
	}

	public List<Product> getAllProductsSql() {
		String sql = "Select id,name,description,category,unitPrice,unitsInStock,unitsInOrder from products";

		List<Product> listOfProducts = new ArrayList<Product>();
		listOfProductsMap = getJdbcTemplate().queryForList(sql);
		for (Map<String, Object> row : listOfProductsMap) {
			Product product = new Product();
			product.setProductId((String.valueOf((row.get("id")))));
			product.setName((String) (row.get("name")));
			product.setDescription((String) (row.get("description")));
			product.setCategory((String) (row.get("category")));
			product.setUnitPrice(new BigDecimal(String.valueOf((row.get("unitPrice"))).replaceAll(",", "")));
			product.setUnitsInStock(Long.parseLong(String.valueOf((row.get("unitsInStock")))));
			product.setUnitsInOrder(Long.parseLong(String.valueOf((row.get("unitsInOrder")))));
			listOfProducts.add(product);
		}
		return listOfProducts;
	}

	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		Set<Product> productsByName = new HashSet<Product>();
		Set<Product> productsByCategory = new HashSet<Product>();
		Set<String> criterias = filterParams.keySet();
		if (criterias.contains("name")) {
			for (String name : filterParams.get("name")) {
				productsByName.addAll(getProductsByParameter("name", name));
			}
		}
		if (criterias.contains("category")) {
			for (String category : filterParams.get("category")) {
				productsByCategory.addAll(getProductsByCategory(category));
			}
		}
		productsByCategory.retainAll(productsByName);
		return productsByCategory;

	}

	public List<Product> getProductsByCategory(String category) {
		String sql = "Select id,name,description,category,unitPrice,unitsInStock,unitsInOrder from products where LOWER(category)=LOWER('"
				+ category + "')";

		List<Product> listOfProductsByCategory = new ArrayList<Product>();
		listOfProductsMap = getJdbcTemplate().queryForList(sql);
		for (Map<String, Object> row : listOfProductsMap) {
			Product product = new Product();
			product.setProductId((String.valueOf((row.get("id")))));
			product.setName((String) (row.get("name")));
			product.setDescription((String) (row.get("description")));
			product.setCategory((String) (row.get("category")));
			product.setUnitPrice(new BigDecimal(String.valueOf((row.get("unitPrice"))).replaceAll(",", "")));
			product.setUnitsInStock(Long.parseLong(String.valueOf((row.get("unitsInStock")))));
			product.setUnitsInOrder(Long.parseLong(String.valueOf((row.get("unitsInOrder")))));
			listOfProductsByCategory.add(product);
		}
		return listOfProductsByCategory;
	}

	public List<Product> getProductsByParameter(String parameter, String value) {
		String sql = "Select id,name,description,category,unitPrice,unitsInStock,unitsInOrder from products where LOWER("
				+ parameter + ")=LOWER('" + value + "')";

		List<Product> listOfProductsByParameter = new ArrayList<Product>();
		listOfProductsMap = getJdbcTemplate().queryForList(sql);
		for (Map<String, Object> row : listOfProductsMap) {
			Product product = new Product();
			product.setProductId((String.valueOf((row.get("id")))));
			product.setName((String) (row.get("name")));
			product.setDescription((String) (row.get("description")));
			product.setCategory((String) (row.get("category")));
			product.setUnitPrice(new BigDecimal(String.valueOf((row.get("unitPrice"))).replaceAll(",", "")));
			product.setUnitsInStock(Long.parseLong(String.valueOf((row.get("unitsInStock")))));
			product.setUnitsInOrder(Long.parseLong(String.valueOf((row.get("unitsInOrder")))));
			listOfProductsByParameter.add(product);
		}
		return listOfProductsByParameter;
	}

	public Product getProductById(String productId) {
		for (Product product : getAllProducts()) {
			if (product != null && product.getProductId() != null && product.getProductId().equals(productId)) {
				return product;
			}
		}	
		throw new IllegalArgumentException("Brak produktu o wskazanym id:" + productId);
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	public List<Product> getAllProducts() {
		return getAllProductsSql();
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
