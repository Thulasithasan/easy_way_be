package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Permission.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Permission_ {

	public static final String ROLE_PERMISSIONS = "rolePermissions";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

	
	/**
	 * @see com.thulasi.easyway.model.Permission#rolePermissions
	 **/
	public static volatile SetAttribute<Permission, RolePermission> rolePermissions;
	
	/**
	 * @see com.thulasi.easyway.model.Permission#name
	 **/
	public static volatile SingularAttribute<Permission, String> name;
	
	/**
	 * @see com.thulasi.easyway.model.Permission#description
	 **/
	public static volatile SingularAttribute<Permission, String> description;
	
	/**
	 * @see com.thulasi.easyway.model.Permission#id
	 **/
	public static volatile SingularAttribute<Permission, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.Permission
	 **/
	public static volatile EntityType<Permission> class_;

}

