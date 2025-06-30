package com.thulasi.easyway.model;

import com.thulasi.easyway.type.MeasurementUnit;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.util.List;

@StaticMetamodel(Product.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Product_ extends com.thulasi.easyway.model.Auditable_ {

	public static final String IMAGES = "images";
	public static final String SUB_CATEGORY = "subCategory";
	public static final String MEASUREMENT_VALUE = "measurementValue";
	public static final String DESCRIPTION = "description";
	public static final String HERO_IMAGE = "heroImage";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";
	public static final String NAME_TRANSLATIONS = "nameTranslations";
	public static final String MEASUREMENT_UNIT = "measurementUnit";

	
	/**
	 * @see com.thulasi.easyway.model.Product#images
	 **/
	public static volatile SingularAttribute<Product, List<String>> images;
	
	/**
	 * @see com.thulasi.easyway.model.Product#subCategory
	 **/
	public static volatile SingularAttribute<Product, SubCategory> subCategory;
	
	/**
	 * @see com.thulasi.easyway.model.Product#measurementValue
	 **/
	public static volatile SingularAttribute<Product, BigDecimal> measurementValue;
	
	/**
	 * @see com.thulasi.easyway.model.Product#description
	 **/
	public static volatile SingularAttribute<Product, String> description;
	
	/**
	 * @see com.thulasi.easyway.model.Product#heroImage
	 **/
	public static volatile SingularAttribute<Product, String> heroImage;
	
	/**
	 * @see com.thulasi.easyway.model.Product#id
	 **/
	public static volatile SingularAttribute<Product, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.Product#isActive
	 **/
	public static volatile SingularAttribute<Product, Boolean> isActive;
	
	/**
	 * @see com.thulasi.easyway.model.Product
	 **/
	public static volatile EntityType<Product> class_;
	
	/**
	 * @see com.thulasi.easyway.model.Product#nameTranslations
	 **/
	public static volatile ListAttribute<Product, ProductName> nameTranslations;
	
	/**
	 * @see com.thulasi.easyway.model.Product#measurementUnit
	 **/
	public static volatile SingularAttribute<Product, MeasurementUnit> measurementUnit;

}

