package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(RecurringOrderItem.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class RecurringOrderItem_ {

	public static final String PRODUCT = "product";
	public static final String QUANTITY = "quantity";
	public static final String RECURRING_ORDER = "recurringOrder";
	public static final String ID = "id";

	
	/**
	 * @see com.thulasi.easyway.model.RecurringOrderItem#product
	 **/
	public static volatile SingularAttribute<RecurringOrderItem, Product> product;
	
	/**
	 * @see com.thulasi.easyway.model.RecurringOrderItem#quantity
	 **/
	public static volatile SingularAttribute<RecurringOrderItem, BigDecimal> quantity;
	
	/**
	 * @see com.thulasi.easyway.model.RecurringOrderItem#recurringOrder
	 **/
	public static volatile SingularAttribute<RecurringOrderItem, RecurringOrder> recurringOrder;
	
	/**
	 * @see com.thulasi.easyway.model.RecurringOrderItem#id
	 **/
	public static volatile SingularAttribute<RecurringOrderItem, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.RecurringOrderItem
	 **/
	public static volatile EntityType<RecurringOrderItem> class_;

}

