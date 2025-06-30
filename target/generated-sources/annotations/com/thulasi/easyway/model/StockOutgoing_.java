package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(StockOutgoing.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class StockOutgoing_ extends com.thulasi.easyway.model.Auditable_ {

	public static final String UNIT_BUYING_PRICE = "unitBuyingPrice";
	public static final String UNIT_SELLING_PRICE = "unitSellingPrice";
	public static final String QUANTITY = "quantity";
	public static final String PROFIT_AMOUNT = "profitAmount";
	public static final String TOTAL_BUYING_AMOUNT = "totalBuyingAmount";
	public static final String ID = "id";
	public static final String STOCK = "stock";
	public static final String SALES_ORDER = "salesOrder";
	public static final String TOTAL_SELLING_AMOUNT = "totalSellingAmount";

	
	/**
	 * @see com.thulasi.easyway.model.StockOutgoing#unitBuyingPrice
	 **/
	public static volatile SingularAttribute<StockOutgoing, BigDecimal> unitBuyingPrice;
	
	/**
	 * @see com.thulasi.easyway.model.StockOutgoing#unitSellingPrice
	 **/
	public static volatile SingularAttribute<StockOutgoing, BigDecimal> unitSellingPrice;
	
	/**
	 * @see com.thulasi.easyway.model.StockOutgoing#quantity
	 **/
	public static volatile SingularAttribute<StockOutgoing, BigDecimal> quantity;
	
	/**
	 * @see com.thulasi.easyway.model.StockOutgoing#profitAmount
	 **/
	public static volatile SingularAttribute<StockOutgoing, BigDecimal> profitAmount;
	
	/**
	 * @see com.thulasi.easyway.model.StockOutgoing#totalBuyingAmount
	 **/
	public static volatile SingularAttribute<StockOutgoing, BigDecimal> totalBuyingAmount;
	
	/**
	 * @see com.thulasi.easyway.model.StockOutgoing#id
	 **/
	public static volatile SingularAttribute<StockOutgoing, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.StockOutgoing#stock
	 **/
	public static volatile SingularAttribute<StockOutgoing, Stock> stock;
	
	/**
	 * @see com.thulasi.easyway.model.StockOutgoing
	 **/
	public static volatile EntityType<StockOutgoing> class_;
	
	/**
	 * @see com.thulasi.easyway.model.StockOutgoing#salesOrder
	 **/
	public static volatile SingularAttribute<StockOutgoing, SalesOrder> salesOrder;
	
	/**
	 * @see com.thulasi.easyway.model.StockOutgoing#totalSellingAmount
	 **/
	public static volatile SingularAttribute<StockOutgoing, BigDecimal> totalSellingAmount;

}

