package com.thulasi.easyway.model;

import com.thulasi.easyway.payload.response.BuyingPriceDetail;
import com.thulasi.easyway.type.MeasurementUnit;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.util.List;

@StaticMetamodel(SalesOrderItem.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class SalesOrderItem_ extends com.thulasi.easyway.model.Auditable_ {

	public static final String UNIT_SELLING_PRICE = "unitSellingPrice";
	public static final String QUANTITY = "quantity";
	public static final String UNIT_BUYING_PRICE_DETAILS = "unitBuyingPriceDetails";
	public static final String TOTAL_BUYING_AMOUNT = "totalBuyingAmount";
	public static final String TOTAL_PROFIT_AMOUNT = "totalProfitAmount";
	public static final String ID = "id";
	public static final String STOCK = "stock";
	public static final String SALES_ORDER = "salesOrder";
	public static final String MEASUREMENT_UNIT = "measurementUnit";
	public static final String TOTAL_SELLING_AMOUNT = "totalSellingAmount";

	
	/**
	 * @see com.thulasi.easyway.model.SalesOrderItem#unitSellingPrice
	 **/
	public static volatile SingularAttribute<SalesOrderItem, BigDecimal> unitSellingPrice;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrderItem#quantity
	 **/
	public static volatile SingularAttribute<SalesOrderItem, BigDecimal> quantity;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrderItem#unitBuyingPriceDetails
	 **/
	public static volatile SingularAttribute<SalesOrderItem, List<BuyingPriceDetail>> unitBuyingPriceDetails;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrderItem#totalBuyingAmount
	 **/
	public static volatile SingularAttribute<SalesOrderItem, BigDecimal> totalBuyingAmount;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrderItem#totalProfitAmount
	 **/
	public static volatile SingularAttribute<SalesOrderItem, BigDecimal> totalProfitAmount;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrderItem#id
	 **/
	public static volatile SingularAttribute<SalesOrderItem, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrderItem#stock
	 **/
	public static volatile SingularAttribute<SalesOrderItem, Stock> stock;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrderItem
	 **/
	public static volatile EntityType<SalesOrderItem> class_;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrderItem#salesOrder
	 **/
	public static volatile SingularAttribute<SalesOrderItem, SalesOrder> salesOrder;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrderItem#measurementUnit
	 **/
	public static volatile SingularAttribute<SalesOrderItem, MeasurementUnit> measurementUnit;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrderItem#totalSellingAmount
	 **/
	public static volatile SingularAttribute<SalesOrderItem, BigDecimal> totalSellingAmount;

}

