package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(FavouriteItem.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class FavouriteItem_ extends com.thulasi.easyway.model.Auditable_ {

	public static final String PRODUCT = "product";
	public static final String ID = "id";
	public static final String USER_ID = "userId";

	
	/**
	 * @see com.thulasi.easyway.model.FavouriteItem#product
	 **/
	public static volatile SingularAttribute<FavouriteItem, Product> product;
	
	/**
	 * @see com.thulasi.easyway.model.FavouriteItem#id
	 **/
	public static volatile SingularAttribute<FavouriteItem, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.FavouriteItem
	 **/
	public static volatile EntityType<FavouriteItem> class_;
	
	/**
	 * @see com.thulasi.easyway.model.FavouriteItem#userId
	 **/
	public static volatile SingularAttribute<FavouriteItem, Long> userId;

}

