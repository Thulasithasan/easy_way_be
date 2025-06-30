package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SubCategory.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class SubCategory_ {

	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";
	public static final String CATEGORY = "category";
	public static final String PRODUCTS = "products";

	
	/**
	 * @see com.thulasi.easyway.model.SubCategory#name
	 **/
	public static volatile SingularAttribute<SubCategory, String> name;
	
	/**
	 * @see com.thulasi.easyway.model.SubCategory#description
	 **/
	public static volatile SingularAttribute<SubCategory, String> description;
	
	/**
	 * @see com.thulasi.easyway.model.SubCategory#id
	 **/
	public static volatile SingularAttribute<SubCategory, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.SubCategory#isActive
	 **/
	public static volatile SingularAttribute<SubCategory, Boolean> isActive;
	
	/**
	 * @see com.thulasi.easyway.model.SubCategory#category
	 **/
	public static volatile SingularAttribute<SubCategory, Category> category;
	
	/**
	 * @see com.thulasi.easyway.model.SubCategory
	 **/
	public static volatile EntityType<SubCategory> class_;
	
	/**
	 * @see com.thulasi.easyway.model.SubCategory#products
	 **/
	public static volatile ListAttribute<SubCategory, Product> products;

}

