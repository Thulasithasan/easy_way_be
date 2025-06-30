package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(Invoice.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Invoice_ extends com.thulasi.easyway.model.Auditable_ {

	public static final String PAYMENTS = "payments";
	public static final String INVOICE_AMOUNT = "invoiceAmount";
	public static final String ID = "id";
	public static final String SALES_ORDER = "salesOrder";
	public static final String INVOICE_REF_NO = "invoiceRefNo";

	
	/**
	 * @see com.thulasi.easyway.model.Invoice#payments
	 **/
	public static volatile ListAttribute<Invoice, Payment> payments;
	
	/**
	 * @see com.thulasi.easyway.model.Invoice#invoiceAmount
	 **/
	public static volatile SingularAttribute<Invoice, BigDecimal> invoiceAmount;
	
	/**
	 * @see com.thulasi.easyway.model.Invoice#id
	 **/
	public static volatile SingularAttribute<Invoice, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.Invoice
	 **/
	public static volatile EntityType<Invoice> class_;
	
	/**
	 * @see com.thulasi.easyway.model.Invoice#salesOrder
	 **/
	public static volatile SingularAttribute<Invoice, SalesOrder> salesOrder;
	
	/**
	 * @see com.thulasi.easyway.model.Invoice#invoiceRefNo
	 **/
	public static volatile SingularAttribute<Invoice, String> invoiceRefNo;

}

