package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(Stock.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Stock_ extends com.thulasi.easyway.model.Auditable_ {

	public static final String PRODUCT = "product";
	public static final String QUANTITY = "quantity";
	public static final String STOCK_OUTGOINGS = "stockOutgoings";
	public static final String RESERVED_QUANTITY = "reservedQuantity";
	public static final String STOCK_INCOMES = "stockIncomes";
	public static final String ID = "id";
	public static final String PRICES = "prices";
	public static final String IS_ACTIVE = "isActive";

	
	/**
	 * @see com.thulasi.easyway.model.Stock#product
	 **/
	public static volatile SingularAttribute<Stock, Product> product;
	
	/**
	 * @see com.thulasi.easyway.model.Stock#quantity
	 **/
	public static volatile SingularAttribute<Stock, BigDecimal> quantity;
	
	/**
	 * @see com.thulasi.easyway.model.Stock#stockOutgoings
	 **/
	public static volatile ListAttribute<Stock, StockOutgoing> stockOutgoings;
	
	/**
	 * @see com.thulasi.easyway.model.Stock#reservedQuantity
	 **/
	public static volatile SingularAttribute<Stock, BigDecimal> reservedQuantity;
	
	/**
	 * @see com.thulasi.easyway.model.Stock#stockIncomes
	 **/
	public static volatile ListAttribute<Stock, StockIncome> stockIncomes;
	
	/**
	 * @see com.thulasi.easyway.model.Stock#id
	 **/
	public static volatile SingularAttribute<Stock, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.Stock#prices
	 **/
	public static volatile ListAttribute<Stock, Price> prices;
	
	/**
	 * @see com.thulasi.easyway.model.Stock#isActive
	 **/
	public static volatile SingularAttribute<Stock, Boolean> isActive;
	
	/**
	 * @see com.thulasi.easyway.model.Stock
	 **/
	public static volatile EntityType<Stock> class_;

}

