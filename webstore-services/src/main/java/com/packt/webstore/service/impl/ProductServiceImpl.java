package com.packt.webstore.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.packt.webstore.domain.Products.Product;
import com.packt.webstore.domain.repository.IProductRepository;
import com.packt.webstore.service.IProductService;

@Component
@WebService(endpointInterface = "com.packt.webstore.service.IProductService", serviceName = "productService")
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}

	public Product getProductById(String productID) {
		return productRepository.getProductById(productID);
	}

	public void addProduct(Product newProduct) {
		productRepository.addProduct(newProduct);
	}

	public void withdrawProduct(int deletedProductId) {
		productRepository.withdrawProduct(deletedProductId);
	}

	public void editProduct(Product editedProduct) {
		productRepository.editProduct(editedProduct);
	}

	public List<Product> getProductsByCategory(String category) {
		return productRepository.getProductsByCategory(category);

	}

	public List<Product> getProductsByParameter(String parameter, String value) {
		return productRepository.getProductsByParameter(parameter, value);
	}
}