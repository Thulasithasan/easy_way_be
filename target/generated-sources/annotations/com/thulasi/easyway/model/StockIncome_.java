package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(StockIncome.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class StockIncome_ extends com.thulasi.easyway.model.Auditable_ {

	public static final String AVAILABLE_QUANTITY = "availableQuantity";
	public static final String UNIT_BUYING_PRICE = "unitBuyingPrice";
	public static final String QUANTITY = "quantity";
	public static final String PURCHASE_BILL = "purchaseBill";
	public static final String TOTAL_BUYING_AMOUNT = "totalBuyingAmount";
	public static final String ID = "id";
	public static final String STOCK = "stock";

	
	/**
	 * @see com.thulasi.easyway.model.StockIncome#availableQuantity
	 **/
	public static volatile SingularAttribute<StockIncome, BigDecimal> availableQuantity;
	
	/**
	 * @see com.thulasi.easyway.model.StockIncome#unitBuyingPrice
	 **/
	public static volatile SingularAttribute<StockIncome, BigDecimal> unitBuyingPrice;
	
	/**
	 * @see com.thulasi.easyway.model.StockIncome#quantity
	 **/
	public static volatile SingularAttribute<StockIncome, BigDecimal> quantity;
	
	/**
	 * @see com.thulasi.easyway.model.StockIncome#purchaseBill
	 **/
	public static volatile SingularAttribute<StockIncome, PurchaseBill> purchaseBill;
	
	/**
	 * @see com.thulasi.easyway.model.StockIncome#totalBuyingAmount
	 **/
	public static volatile SingularAttribute<StockIncome, BigDecimal> totalBuyingAmount;
	
	/**
	 * @see com.thulasi.easyway.model.StockIncome#id
	 **/
	public static volatile SingularAttribute<StockIncome, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.StockIncome#stock
	 **/
	public static volatile SingularAttribute<StockIncome, Stock> stock;
	
	/**
	 * @see com.thulasi.easyway.model.StockIncome
	 **/
	public static volatile EntityType<StockIncome> class_;

}

