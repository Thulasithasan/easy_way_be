package com.thulasi.easyway.model;

import com.thulasi.easyway.type.SalesOrderStatus;
import com.thulasi.easyway.type.SalesOrderType;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(SalesOrder.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class SalesOrder_ extends com.thulasi.easyway.model.Auditable_ {

	public static final String DELIVERY_PERSON = "deliveryPerson";
	public static final String TOTAL_BUYING_AMOUNT = "totalBuyingAmount";
	public static final String TYPE = "type";
	public static final String TOTAL_SELLING_AMOUNT = "totalSellingAmount";
	public static final String ORDER_REF_NO = "orderRefNo";
	public static final String TOTAL_QUANTITY = "totalQuantity";
	public static final String TOTAL_WEIGHT = "totalWeight";
	public static final String TOTAL_PROFIT_AMOUNT = "totalProfitAmount";
	public static final String ID = "id";
	public static final String SALES_ORDER_ITEMS = "salesOrderItems";
	public static final String SALES_PERSON = "salesPerson";
	public static final String CUSTOMER = "customer";
	public static final String STATUS = "status";

	
	/**
	 * @see com.thulasi.easyway.model.SalesOrder#deliveryPerson
	 **/
	public static volatile SingularAttribute<SalesOrder, User> deliveryPerson;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrder#totalBuyingAmount
	 **/
	public static volatile SingularAttribute<SalesOrder, BigDecimal> totalBuyingAmount;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrder#type
	 **/
	public static volatile SingularAttribute<SalesOrder, SalesOrderType> type;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrder#totalSellingAmount
	 **/
	public static volatile SingularAttribute<SalesOrder, BigDecimal> totalSellingAmount;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrder#orderRefNo
	 **/
	public static volatile SingularAttribute<SalesOrder, String> orderRefNo;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrder#totalQuantity
	 **/
	public static volatile SingularAttribute<SalesOrder, BigDecimal> totalQuantity;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrder#totalWeight
	 **/
	public static volatile SingularAttribute<SalesOrder, BigDecimal> totalWeight;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrder#totalProfitAmount
	 **/
	public static volatile SingularAttribute<SalesOrder, BigDecimal> totalProfitAmount;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrder#id
	 **/
	public static volatile SingularAttribute<SalesOrder, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrder#salesOrderItems
	 **/
	public static volatile ListAttribute<SalesOrder, SalesOrderItem> salesOrderItems;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrder#salesPerson
	 **/
	public static volatile SingularAttribute<SalesOrder, User> salesPerson;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrder
	 **/
	public static volatile EntityType<SalesOrder> class_;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrder#customer
	 **/
	public static volatile SingularAttribute<SalesOrder, User> customer;
	
	/**
	 * @see com.thulasi.easyway.model.SalesOrder#status
	 **/
	public static volatile SingularAttribute<SalesOrder, SalesOrderStatus> status;

}

