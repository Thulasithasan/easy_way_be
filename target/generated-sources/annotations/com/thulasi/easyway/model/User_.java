package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class User_ extends com.thulasi.easyway.model.Auditable_ {

	public static final String LAST_NAME = "lastName";
	public static final String ADDRESS = "address";
	public static final String ROLE = "role";
	public static final String CITY = "city";
	public static final String IS_ACTIVE = "isActive";
	public static final String FIRST_NAME = "firstName";
	public static final String PASSWORD = "password";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String PROVINCE = "province";
	public static final String DISTRICT = "district";
	public static final String PREVIOUS_PASSWORDS = "previousPasswords";
	public static final String IS_PASSWORD_CHANGED_FOR_THE_FIRST_TIME = "isPasswordChangedForTheFirstTime";
	public static final String ID = "id";
	public static final String EMAIL = "email";

	
	/**
	 * @see com.thulasi.easyway.model.User#lastName
	 **/
	public static volatile SingularAttribute<User, String> lastName;
	
	/**
	 * @see com.thulasi.easyway.model.User#address
	 **/
	public static volatile SingularAttribute<User, String> address;
	
	/**
	 * @see com.thulasi.easyway.model.User#role
	 **/
	public static volatile SingularAttribute<User, Role> role;
	
	/**
	 * @see com.thulasi.easyway.model.User#city
	 **/
	public static volatile SingularAttribute<User, String> city;
	
	/**
	 * @see com.thulasi.easyway.model.User#isActive
	 **/
	public static volatile SingularAttribute<User, Boolean> isActive;
	
	/**
	 * @see com.thulasi.easyway.model.User#firstName
	 **/
	public static volatile SingularAttribute<User, String> firstName;
	
	/**
	 * @see com.thulasi.easyway.model.User#password
	 **/
	public static volatile SingularAttribute<User, String> password;
	
	/**
	 * @see com.thulasi.easyway.model.User#phoneNumber
	 **/
	public static volatile SingularAttribute<User, String> phoneNumber;
	
	/**
	 * @see com.thulasi.easyway.model.User#province
	 **/
	public static volatile SingularAttribute<User, String> province;
	
	/**
	 * @see com.thulasi.easyway.model.User#district
	 **/
	public static volatile SingularAttribute<User, String> district;
	
	/**
	 * @see com.thulasi.easyway.model.User#previousPasswords
	 **/
	public static volatile SingularAttribute<User, String> previousPasswords;
	
	/**
	 * @see com.thulasi.easyway.model.User#isPasswordChangedForTheFirstTime
	 **/
	public static volatile SingularAttribute<User, Boolean> isPasswordChangedForTheFirstTime;
	
	/**
	 * @see com.thulasi.easyway.model.User#id
	 **/
	public static volatile SingularAttribute<User, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.User
	 **/
	public static volatile EntityType<User> class_;
	
	/**
	 * @see com.thulasi.easyway.model.User#email
	 **/
	public static volatile SingularAttribute<User, String> email;

}

