package com.packt.webstore.domain.repository;

import java.util.List;

import com.packt.webstore.domain.Products.Product;

public interface IProductRepository {

	List<Product> getAllProducts();

	Product getProductById(String productId);

	void addProduct(Product newProduct);

	void withdrawProduct(int deletedProductId);

	void editProduct(Product editedProduct);

	List<Product> getProductsByCategory(String category);

	List<Product> getProductsByParameter(String parameter, String value);

}
