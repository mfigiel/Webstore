package com.packt.webstore.service;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.packt.webstore.domain.Products.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

@WebService
public interface IProductService {
	
	@WebMethod
	List <Product> getAllProducts();
	@WebMethod
	Product getProductById(String productId);
	@WebMethod
	void addProduct(Product newProduct);
	@WebMethod
	void withdrawProduct(int deletedProductId);
	@WebMethod
	void editProduct(Product editedProduct);
	@WebMethod
	List<Product> getProductsByCategory(String category);
	@WebMethod
	List<Product> getProductsByParameter(String parameter,String value);
}
