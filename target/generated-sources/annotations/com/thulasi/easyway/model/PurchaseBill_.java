package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.LocalDate;

@StaticMetamodel(PurchaseBill.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class PurchaseBill_ extends com.thulasi.easyway.model.Auditable_ {

	public static final String SUPPLIER_NAME = "supplierName";
	public static final String TOTAL_AMOUNT = "totalAmount";
	public static final String NOTE = "note";
	public static final String BILL_DATE = "billDate";
	public static final String STOCK_INCOMES = "stockIncomes";
	public static final String ID = "id";
	public static final String BILL_NUMBER = "billNumber";
	public static final String BILL_IMAGE_URL = "billImageUrl";

	
	/**
	 * @see com.thulasi.easyway.model.PurchaseBill#supplierName
	 **/
	public static volatile SingularAttribute<PurchaseBill, String> supplierName;
	
	/**
	 * @see com.thulasi.easyway.model.PurchaseBill#totalAmount
	 **/
	public static volatile SingularAttribute<PurchaseBill, BigDecimal> totalAmount;
	
	/**
	 * @see com.thulasi.easyway.model.PurchaseBill#note
	 **/
	public static volatile SingularAttribute<PurchaseBill, String> note;
	
	/**
	 * @see com.thulasi.easyway.model.PurchaseBill#billDate
	 **/
	public static volatile SingularAttribute<PurchaseBill, LocalDate> billDate;
	
	/**
	 * @see com.thulasi.easyway.model.PurchaseBill#stockIncomes
	 **/
	public static volatile ListAttribute<PurchaseBill, StockIncome> stockIncomes;
	
	/**
	 * @see com.thulasi.easyway.model.PurchaseBill#id
	 **/
	public static volatile SingularAttribute<PurchaseBill, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.PurchaseBill#billNumber
	 **/
	public static volatile SingularAttribute<PurchaseBill, String> billNumber;
	
	/**
	 * @see com.thulasi.easyway.model.PurchaseBill
	 **/
	public static volatile EntityType<PurchaseBill> class_;
	
	/**
	 * @see com.thulasi.easyway.model.PurchaseBill#billImageUrl
	 **/
	public static volatile SingularAttribute<PurchaseBill, String> billImageUrl;

}

