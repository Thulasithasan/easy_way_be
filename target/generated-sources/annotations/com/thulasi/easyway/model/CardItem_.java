package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(CardItem.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class CardItem_ extends com.thulasi.easyway.model.Auditable_ {

	public static final String PRODUCT = "product";
	public static final String QUANTITY = "quantity";
	public static final String ID = "id";
	public static final String USER_ID = "userId";

	
	/**
	 * @see com.thulasi.easyway.model.CardItem#product
	 **/
	public static volatile SingularAttribute<CardItem, Product> product;
	
	/**
	 * @see com.thulasi.easyway.model.CardItem#quantity
	 **/
	public static volatile SingularAttribute<CardItem, BigDecimal> quantity;
	
	/**
	 * @see com.thulasi.easyway.model.CardItem#id
	 **/
	public static volatile SingularAttribute<CardItem, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.CardItem
	 **/
	public static volatile EntityType<CardItem> class_;
	
	/**
	 * @see com.thulasi.easyway.model.CardItem#userId
	 **/
	public static volatile SingularAttribute<CardItem, Long> userId;

}

