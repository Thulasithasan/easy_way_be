package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductName.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class ProductName_ {

	public static final String PRODUCT = "product";
	public static final String NAME = "name";
	public static final String LANGUAGE = "language";
	public static final String ID = "id";

	
	/**
	 * @see com.thulasi.easyway.model.ProductName#product
	 **/
	public static volatile SingularAttribute<ProductName, Product> product;
	
	/**
	 * @see com.thulasi.easyway.model.ProductName#name
	 **/
	public static volatile SingularAttribute<ProductName, String> name;
	
	/**
	 * @see com.thulasi.easyway.model.ProductName#language
	 **/
	public static volatile SingularAttribute<ProductName, String> language;
	
	/**
	 * @see com.thulasi.easyway.model.ProductName#id
	 **/
	public static volatile SingularAttribute<ProductName, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.ProductName
	 **/
	public static volatile EntityType<ProductName> class_;

}

