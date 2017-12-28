package com.packt.webstore.utils;

import javax.servlet.http.HttpServletRequest;

import com.packt.webstore.domain.Cart;

public class CartUtils {
	 
    public static Cart getCartInSession(HttpServletRequest request) {
 
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        
        if (cart == null) {
            cart = new Cart();
            
            request.getSession().setAttribute("cart", cart);
        }
 
        return cart;
    }
 
    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("cart");
    }
 
    public static void storeLastOrderedCartInSession(HttpServletRequest request, Cart cart) {
        request.getSession().setAttribute("lastOrderedCart", cart);
    }
    
    public static Cart getLastOrderedCartInSession(HttpServletRequest request) {
        return (Cart) request.getSession().getAttribute("lastOrderedCart");
    }
 
}