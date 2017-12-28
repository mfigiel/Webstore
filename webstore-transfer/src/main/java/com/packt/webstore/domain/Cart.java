package com.packt.webstore.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.packt.webstore.domain.Products.OrderedProduct;

public class Cart {

	private List<CartItem> cartItemList = new ArrayList<CartItem>();
	private BigDecimal totalPrice;

	public Cart(List<CartItem> cartItemList, BigDecimal totalPrice) {
		super();
		this.cartItemList = cartItemList;
		this.totalPrice = totalPrice;
	}

	public Cart() {
		setTotalPrice(new BigDecimal(0));
	}

	public Set<OrderedProduct> getOrderedProducts() {
		Set<OrderedProduct> orderedProducts =  new HashSet<OrderedProduct>();
		for (CartItem item : cartItemList) {	
			OrderedProduct orderedProduct = new OrderedProduct();
			orderedProduct.setProduct(item.getCartProduct());
			orderedProduct.setQuantity(item.getQuantity());
			orderedProducts.add(orderedProduct);
		}
		return orderedProducts;
	}
	public List<CartItem> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public void addCartItemToList(CartItem cartItem) {
		for (CartItem item : cartItemList) {
			if (item.getCartProduct().equals(cartItem.getCartProduct())) {
				item.setQuantity(item.getQuantity() + 1);
				setTotalPrice(getTotalPrice().add(cartItem.getPrice()));
				return;
			}		
		}
		setTotalPrice(getTotalPrice().add(cartItem.getPrice()));
		this.cartItemList.add(cartItem);
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void removeCartItemFromList(CartItem cartItem) {
		for (CartItem item : cartItemList) {
			if (item.getCartProduct().equals(cartItem.getCartProduct())) {
				if (item.getQuantity() > 1) {
					item.setQuantity(item.getQuantity() - 1);
					setTotalPrice(getTotalPrice().subtract(cartItem.getPrice()));
				} else {
					this.cartItemList.remove(item);
					setTotalPrice(getTotalPrice().subtract(cartItem.getPrice()));
					return;
				}
			}
		}
	}

}
