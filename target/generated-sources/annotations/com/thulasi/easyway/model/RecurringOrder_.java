package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(RecurringOrder.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class RecurringOrder_ {

	public static final String NOTE = "note";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String USER_ID = "userId";
	public static final String ITEMS = "items";

	
	/**
	 * @see com.thulasi.easyway.model.RecurringOrder#note
	 **/
	public static volatile SingularAttribute<RecurringOrder, String> note;
	
	/**
	 * @see com.thulasi.easyway.model.RecurringOrder#name
	 **/
	public static volatile SingularAttribute<RecurringOrder, String> name;
	
	/**
	 * @see com.thulasi.easyway.model.RecurringOrder#id
	 **/
	public static volatile SingularAttribute<RecurringOrder, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.RecurringOrder
	 **/
	public static volatile EntityType<RecurringOrder> class_;
	
	/**
	 * @see com.thulasi.easyway.model.RecurringOrder#userId
	 **/
	public static volatile SingularAttribute<RecurringOrder, Long> userId;
	
	/**
	 * @see com.thulasi.easyway.model.RecurringOrder#items
	 **/
	public static volatile ListAttribute<RecurringOrder, RecurringOrderItem> items;

}

