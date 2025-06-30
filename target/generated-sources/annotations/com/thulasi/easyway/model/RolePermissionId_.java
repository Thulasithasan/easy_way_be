package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(RolePermissionId.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class RolePermissionId_ {

	public static final String PERMISSION_ID = "permissionId";
	public static final String ROLE_ID = "roleId";

	
	/**
	 * @see com.thulasi.easyway.model.RolePermissionId#permissionId
	 **/
	public static volatile SingularAttribute<RolePermissionId, Long> permissionId;
	
	/**
	 * @see com.thulasi.easyway.model.RolePermissionId#roleId
	 **/
	public static volatile SingularAttribute<RolePermissionId, Long> roleId;
	
	/**
	 * @see com.thulasi.easyway.model.RolePermissionId
	 **/
	public static volatile EmbeddableType<RolePermissionId> class_;

}

