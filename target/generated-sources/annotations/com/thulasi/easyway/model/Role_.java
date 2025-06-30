package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Role_ {

	public static final String ROLE_PERMISSIONS = "rolePermissions";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

	
	/**
	 * @see com.thulasi.easyway.model.Role#rolePermissions
	 **/
	public static volatile SetAttribute<Role, RolePermission> rolePermissions;
	
	/**
	 * @see com.thulasi.easyway.model.Role#name
	 **/
	public static volatile SingularAttribute<Role, String> name;
	
	/**
	 * @see com.thulasi.easyway.model.Role#description
	 **/
	public static volatile SingularAttribute<Role, String> description;
	
	/**
	 * @see com.thulasi.easyway.model.Role#id
	 **/
	public static volatile SingularAttribute<Role, Long> id;
	
	/**
	 * @see com.thulasi.easyway.model.Role
	 **/
	public static volatile EntityType<Role> class_;

}

