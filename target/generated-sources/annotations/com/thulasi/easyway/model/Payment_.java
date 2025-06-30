package com.thulasi.easyway.model;

import com.thulasi.easyway.type.PaymentMethod;
import com.thulasi.easyway.type.PaymentStatus;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(Payment.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Payment_ extends com.thulasi.easyway.model.Auditable_ {

	public static final String PAYMENT_METHOD = "paymentMethod";
	public static final String ID = "id";
	public static final String INVOICE = "invoice";
	public static final String PAYMENT_REF_NO = "paymentRefNo";
	public static final String PAYMENT_AMOUNT = "paymentAmount";
	public static final String PAYMENT_STATUS = "paymentStatus";

	
	/**
	 * @see com.thulasi.easyway.model.Payment#paymentMethod
	 **/
	public static volatile SingularAttribute<Payment, PaymentMethod> paymentMethod;
	
	/**
	 * @see com.thulasi.easyway.model.Payment#id
	 **/
	public static volatile SingularAttribute<Payment, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.Payment#invoice
	 **/
	public static volatile SingularAttribute<Payment, Invoice> invoice;
	
	/**
	 * @see com.thulasi.easyway.model.Payment
	 **/
	public static volatile EntityType<Payment> class_;
	
	/**
	 * @see com.thulasi.easyway.model.Payment#paymentRefNo
	 **/
	public static volatile SingularAttribute<Payment, String> paymentRefNo;
	
	/**
	 * @see com.thulasi.easyway.model.Payment#paymentAmount
	 **/
	public static volatile SingularAttribute<Payment, BigDecimal> paymentAmount;
	
	/**
	 * @see com.thulasi.easyway.model.Payment#paymentStatus
	 **/
	public static volatile SingularAttribute<Payment, PaymentStatus> paymentStatus;

}

