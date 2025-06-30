package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.List;

@StaticMetamodel(RolePermission.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class RolePermission_ {

	public static final String ROLE = "role";
	public static final String SUB_PERMISSIONS = "subPermissions";
	public static final String PERMISSION = "permission";
	public static final String ID = "id";

	
	/**
	 * @see com.thulasi.easyway.model.RolePermission#role
	 **/
	public static volatile SingularAttribute<RolePermission, Role> role;
	
	/**
	 * @see com.thulasi.easyway.model.RolePermission#subPermissions
	 **/
	public static volatile SingularAttribute<RolePermission, List<String>> subPermissions;
	
	/**
	 * @see com.thulasi.easyway.model.RolePermission#permission
	 **/
	public static volatile SingularAttribute<RolePermission, Permission> permission;
	
	/**
	 * @see com.thulasi.easyway.model.RolePermission#id
	 **/
	public static volatile SingularAttribute<RolePermission, RolePermissionId> id;
	
	/**
	 * @see com.thulasi.easyway.model.RolePermission
	 **/
	public static volatile EntityType<RolePermission> class_;

}

