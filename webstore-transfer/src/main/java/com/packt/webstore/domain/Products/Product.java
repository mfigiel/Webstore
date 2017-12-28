package com.packt.webstore.domain.Products;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.SafeHtml;

@XmlRootElement(name = "Product")
public class Product implements Serializable {
		
	private static final long serialVersionUID = 1L;
	private String productId;
	@SafeHtml
	@Size(min=3,max=50, message="{Product.incorrectSizeOfProductName}")
	private String name;
	@Digits(integer=8,fraction=2, message="{Product.incorrectPriceFormat}")
	@NotNull(message="{Product.incorrectEmptyPrice}")
	private BigDecimal unitPrice;
	@SafeHtml
	private String description;
	@SafeHtml
	private String category;
	@Min(value=1, message="{Product.incorrectNegativeProductsNumber}")
	@NotNull(message="{Product.incorrectEmptyProductsNumber}")
	private Long unitsInStock;
	@Min(value=0, message="{Product.incorrectEmptyNumberOfOrders}")
	@NotNull(message="{Product.incorrectEmptyNumberOfOrders}")
	private Long unitsInOrder;
	private boolean discontinued;
	@SafeHtml
	private String condition;
	private String withdrawn;
	
	public Product() {
		this.setUnitsInOrder((long) 0);
		this.setWithdrawn("N");
	}
	
	public Product(String productId, String name, BigDecimal unitPrice){
		this.setProductId(productId);
		this.setName(name);
		this.setUnitPrice(unitPrice);
		this.setUnitsInOrder((long) 0);
		this.setWithdrawn("N");
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}
		
	@Override
	public String toString()	{
		return "Produkt [productId=" + productId + ",nazwa=" + name +"]";
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice2) {
		this.unitPrice = unitPrice2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(Long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public Long getUnitsInOrder() {
		return unitsInOrder;
	}

	public void setUnitsInOrder(Long unitsInOrder) {
		this.unitsInOrder = unitsInOrder;
	}

	public boolean isDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getWithdrawn() {
		return withdrawn;
	}

	public void setWithdrawn(String withdrawn) {
		this.withdrawn = withdrawn;
	}
}
