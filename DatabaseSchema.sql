CREATE TABLE `customers` (
  `idcustomers` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(70) NOT NULL,
  `city` varchar(45) NOT NULL,
  `street` varchar(45) NOT NULL,
  `streetnumber` varchar(45) NOT NULL,
  `phonenumber` varchar(15) NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`idcustomers`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `unitPrice` decimal(10,0) NOT NULL DEFAULT '0',
  `description` varchar(150) DEFAULT 'brak',
  `category` varchar(40) DEFAULT 'brak',
  `unitsInStock` int(11) DEFAULT '0',
  `unitsInOrder` int(11) DEFAULT '0',
  `withdrawn` varchar(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

CREATE TABLE `products_in_order` (
  `idproducts_in_order` int(11) NOT NULL AUTO_INCREMENT,
  `shoporder` int(11) NOT NULL,
  `product_in_order` int(11) NOT NULL,
  `product_quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`idproducts_in_order`),
  KEY `products_in_order_product_idx` (`product_in_order`),
  KEY `products_in_order_order_idx` (`shoporder`),
  CONSTRAINT `products_in_order_order` FOREIGN KEY (`shoporder`) REFERENCES `shoporder` (`idorder`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `products_in_order_product` FOREIGN KEY (`product_in_order`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

CREATE TABLE `shoporder` (
  `idorder` int(11) NOT NULL AUTO_INCREMENT,
  `customer` int(11) NOT NULL,
  `orderdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idorder`),
  KEY `customer_order_fk_idx` (`customer`),
  CONSTRAINT `customer_order_fk` FOREIGN KEY (`customer`) REFERENCES `customers` (`idcustomers`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
