package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Category.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Category_ extends com.thulasi.easyway.model.Auditable_ {

	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";
	public static final String SUB_CATEGORIES = "subCategories";

	
	/**
	 * @see com.thulasi.easyway.model.Category#name
	 **/
	public static volatile SingularAttribute<Category, String> name;
	
	/**
	 * @see com.thulasi.easyway.model.Category#description
	 **/
	public static volatile SingularAttribute<Category, String> description;
	
	/**
	 * @see com.thulasi.easyway.model.Category#id
	 **/
	public static volatile SingularAttribute<Category, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.Category#isActive
	 **/
	public static volatile SingularAttribute<Category, Boolean> isActive;
	
	/**
	 * @see com.thulasi.easyway.model.Category
	 **/
	public static volatile EntityType<Category> class_;
	
	/**
	 * @see com.thulasi.easyway.model.Category#subCategories
	 **/
	public static volatile ListAttribute<Category, SubCategory> subCategories;

}

