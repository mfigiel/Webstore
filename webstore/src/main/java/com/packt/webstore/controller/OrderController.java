package com.packt.webstore.controller;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.domain.CartItem;
import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.Order;
import com.packt.webstore.domain.Products.OrderedProduct;
import com.packt.webstore.domain.Products.Product;
import com.packt.webstore.service.ICustomerService;
import com.packt.webstore.service.IOrderService;
import com.packt.webstore.service.IProductService;
import com.packt.webstore.utils.CartUtils;

@Controller
public class OrderController {

	@Autowired
	private IProductService productService;

	@Autowired
	private IOrderService orderService;

	@Autowired
	private ICustomerService customerService;

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String getCartInfo(Model model, HttpServletRequest request) {
		Cart cart = CartUtils.getCartInSession(request);
		model.addAttribute("cartItemList", cart.getCartItemList());
		model.addAttribute("totalPrice", cart.getTotalPrice());
		return "cartInfo";
	}

	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String initializePaymentForm(Model model, HttpServletRequest request) {
		Cart cart = CartUtils.getCartInSession(request);
		if (cart.getTotalPrice().compareTo(BigDecimal.ZERO) == 0) {
			return "redirect:/cart";
		}

		Customer customer = new Customer();
		model.addAttribute("totalPrice", cart.getTotalPrice());
		model.addAttribute("cartItemList", cart.getCartItemList());
		model.addAttribute("customer", customer);
		return "orderProducts";
	}

	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public String AddProductsToSend(Model model, HttpServletRequest request,
			@ModelAttribute("customer") @Valid Customer customer, BindingResult result) {

		Cart cart = CartUtils.getCartInSession(request);
		if (cart.getTotalPrice().compareTo(BigDecimal.ZERO) == 0) {
			return "redirect:/cart";
		}

		if (result.hasErrors()) {
			model.addAttribute("totalPrice", cart.getTotalPrice());
			return "orderProducts";
		}

		if (customer.getEmail().trim().equals("")) {
			customer.setEmail(null);
		}
		
		customer.setId(customerService.addCustomer(customer));
		Order newOrder = new Order(customer);
		Set<OrderedProduct> orderedProducts = new 
				HashSet<OrderedProduct>(cart.getOrderedProducts());
		newOrder.setProducts(orderedProducts);
		orderService.addOrder(newOrder);
		updateProductUnitsInStock(request);
		CartUtils.removeCartInSession(request);
		model.addAttribute("ProductsOrdered", "true");
		return "redirect:/products";
	}

	@RequestMapping(value = "/products/addtocart-{id}", method = RequestMethod.GET)
	public String addToCart(Model model, @PathVariable("id") Integer id, HttpServletRequest request) {
		Cart cart = CartUtils.getCartInSession(request);
		CartItem cartItem = new CartItem(productService.getProductById(String.valueOf(id)));
		cart.addCartItemToList(cartItem);
		return "redirect:/products";
	}

	@RequestMapping(value = "/deleteProductFromCart-{id}", method = RequestMethod.GET)
	public String removeProductFromCart(Model model, @PathVariable("id") Integer id, HttpServletRequest request) {
		Cart cart = CartUtils.getCartInSession(request);
		CartItem cartItem = new CartItem(productService.getProductById(String.valueOf(id)));
		cart.removeCartItemFromList(cartItem);
		return "redirect:/cart";
	}

	private void updateProductUnitsInStock(HttpServletRequest request) {
		Cart cart = CartUtils.getCartInSession(request);
		List<CartItem> orderedProducts = cart.getCartItemList();
		for (int i = 0; i < orderedProducts.size(); i++) {
			Product orderedProduct = orderedProducts.get(i).getCartProduct();
			orderedProduct.setUnitsInStock(orderedProduct.getUnitsInStock() - orderedProducts.get(i).getQuantity());
			productService.editProduct(orderedProduct);
		}
	}
}
