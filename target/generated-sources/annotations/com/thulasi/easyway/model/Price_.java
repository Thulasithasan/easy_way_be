package com.thulasi.easyway.model;

import com.thulasi.easyway.type.MeasurementUnit;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(Price.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Price_ extends com.thulasi.easyway.model.Auditable_ {

	public static final String UNIT = "unit";
	public static final String IS_DEFAULT = "isDefault";
	public static final String QUANTITY = "quantity";
	public static final String DISCOUNT_PERCENT = "discountPercent";
	public static final String ACTUAL_PRICE = "actualPrice";
	public static final String DISCOUNT_AMOUNT = "discountAmount";
	public static final String FINAL_PRICE = "finalPrice";
	public static final String ID = "id";
	public static final String STOCK = "stock";
	public static final String IS_ACTIVE = "isActive";

	
	/**
	 * @see com.thulasi.easyway.model.Price#unit
	 **/
	public static volatile SingularAttribute<Price, MeasurementUnit> unit;
	
	/**
	 * @see com.thulasi.easyway.model.Price#isDefault
	 **/
	public static volatile SingularAttribute<Price, Boolean> isDefault;
	
	/**
	 * @see com.thulasi.easyway.model.Price#quantity
	 **/
	public static volatile SingularAttribute<Price, BigDecimal> quantity;
	
	/**
	 * @see com.thulasi.easyway.model.Price#discountPercent
	 **/
	public static volatile SingularAttribute<Price, BigDecimal> discountPercent;
	
	/**
	 * @see com.thulasi.easyway.model.Price#actualPrice
	 **/
	public static volatile SingularAttribute<Price, BigDecimal> actualPrice;
	
	/**
	 * @see com.thulasi.easyway.model.Price#discountAmount
	 **/
	public static volatile SingularAttribute<Price, BigDecimal> discountAmount;
	
	/**
	 * @see com.thulasi.easyway.model.Price#finalPrice
	 **/
	public static volatile SingularAttribute<Price, BigDecimal> finalPrice;
	
	/**
	 * @see com.thulasi.easyway.model.Price#id
	 **/
	public static volatile SingularAttribute<Price, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.Price#stock
	 **/
	public static volatile SingularAttribute<Price, Stock> stock;
	
	/**
	 * @see com.thulasi.easyway.model.Price#isActive
	 **/
	public static volatile SingularAttribute<Price, Boolean> isActive;
	
	/**
	 * @see com.thulasi.easyway.model.Price
	 **/
	public static volatile EntityType<Price> class_;

}

