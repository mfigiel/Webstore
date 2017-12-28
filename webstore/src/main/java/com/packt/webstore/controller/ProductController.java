package com.packt.webstore.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.packt.webstore.domain.Products.Product;
import com.packt.webstore.service.IProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private IProductService productService;

	@RequestMapping
	public String list(Model model, HttpServletRequest request) {
		String productsOrdered = request.getParameter("ProductsOrdered");
		model.addAttribute("productsOrdered", productsOrdered);
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping("/filter/{ByCriteria}")
	public String getProductsByFilter(@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams,
			Model model) {

		Set<Product> productsByName = new HashSet<Product>();
		Set<Product> productsByCategory = new HashSet<Product>();
		Set<String> criterias = filterParams.keySet();
		if (criterias.contains("name")) {
			for (String name : filterParams.get("name")) {
				productsByName.addAll(productService.getProductsByParameter("name", name));
			}
		}
		if (criterias.contains("category")) {
			for (String category : filterParams.get("category")) {
				productsByCategory.addAll(productService.getProductsByCategory(category));
			}
		}
		productsByCategory.retainAll(productsByName);

		model.addAttribute("products", productsByCategory);
		return "products";
	}

	@RequestMapping("/{category}")
	public String getProductByCategory(Model model, @PathVariable("category") String productCategory) {
		model.addAttribute("products", productService.getProductsByCategory(productCategory));
		return "products";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "addProduct";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product productToBeAdded,
			BindingResult result) {

		if (result.hasErrors()) {
			return "addProduct";
		}
		productService.addProduct(productToBeAdded);
		return "redirect:/products";
	}

	@RequestMapping(value = "/details-{id}", method = RequestMethod.GET)
	public String getProductDetailsForm(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("product", productService.getProductById(String.valueOf(id)));
		return "productDetails";
	}

	@RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
	public String getEditProductForm(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("editedProduct", productService.getProductById(String.valueOf(id)));
		return "editProduct";
	}

	@RequestMapping(value = "/edit-{id}", method = RequestMethod.POST)
	public String processEditProductForm(@PathVariable("id") Integer id,
			@ModelAttribute("editedProduct") @Valid Product editedProduct, BindingResult result) {
		editedProduct.setProductId(id.toString());

		if (result.hasErrors()) {
			return "editProduct";
		}

		productService.editProduct(editedProduct);
		return "redirect:/products";
	}

	@RequestMapping(value = "/remove-{id}", method = RequestMethod.GET)
	public String withdrawProduct(@PathVariable("id") Integer deletedProductId) {
		productService.withdrawProduct(deletedProductId);
		return "redirect:/products";
	}

	@RequestMapping(value = "/order-{id}", method = RequestMethod.GET)
	public String orderProduct(Model model, @PathVariable("id") Integer ordereddProductId) {
		model.addAttribute("orderedProduct", productService.getProductById(String.valueOf(ordereddProductId)));
		return "orderProduct";
	}
}
