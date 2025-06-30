package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Branch.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Branch_ extends com.thulasi.easyway.model.Auditable_ {

	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String ADDRESS = "address";
	public static final String PROVINCE = "province";
	public static final String CITY = "city";
	public static final String DISTRICT = "district";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String IS_ACTIVE = "isActive";

	
	/**
	 * @see com.thulasi.easyway.model.Branch#phoneNumber
	 **/
	public static volatile SingularAttribute<Branch, String> phoneNumber;
	
	/**
	 * @see com.thulasi.easyway.model.Branch#address
	 **/
	public static volatile SingularAttribute<Branch, String> address;
	
	/**
	 * @see com.thulasi.easyway.model.Branch#province
	 **/
	public static volatile SingularAttribute<Branch, String> province;
	
	/**
	 * @see com.thulasi.easyway.model.Branch#city
	 **/
	public static volatile SingularAttribute<Branch, String> city;
	
	/**
	 * @see com.thulasi.easyway.model.Branch#district
	 **/
	public static volatile SingularAttribute<Branch, String> district;
	
	/**
	 * @see com.thulasi.easyway.model.Branch#name
	 **/
	public static volatile SingularAttribute<Branch, String> name;
	
	/**
	 * @see com.thulasi.easyway.model.Branch#id
	 **/
	public static volatile SingularAttribute<Branch, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.Branch#isActive
	 **/
	public static volatile SingularAttribute<Branch, Boolean> isActive;
	
	/**
	 * @see com.thulasi.easyway.model.Branch
	 **/
	public static volatile EntityType<Branch> class_;

}

