package com.packt.webstore.domain;

import java.math.BigDecimal;

import com.packt.webstore.domain.Products.Product;

public class CartItem {

	private Product cartProduct;
	private int quantity;
	private BigDecimal price;

	public CartItem(Product cartProduct, int quantity, BigDecimal price) {
		super();
		this.cartProduct = cartProduct;
		this.quantity = quantity;
		this.price = cartProduct.getUnitPrice().multiply(new BigDecimal(quantity));
	}

	public CartItem(Product cartProduct, BigDecimal price) {
		super();
		this.cartProduct = cartProduct;
		this.quantity = 1;
		this.price = cartProduct.getUnitPrice().multiply(new BigDecimal(quantity));
	}

	public CartItem(Product cartProduct) {
		super();
		this.cartProduct = cartProduct;
		this.quantity = 1;
		this.price = cartProduct.getUnitPrice().multiply(new BigDecimal(quantity));
	}

	public Product getCartProduct() {
		return cartProduct;
	}

	public void setCartProduct(Product cartProduct) {
		this.cartProduct = cartProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price.multiply(new BigDecimal(quantity));
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
