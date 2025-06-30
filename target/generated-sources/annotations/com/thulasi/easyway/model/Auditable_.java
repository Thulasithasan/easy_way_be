package com.thulasi.easyway.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.MappedSuperclassType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(Auditable.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Auditable_ {

	public static final String CREATED_DATE = "createdDate";
	public static final String CREATED_BY = "createdBy";
	public static final String LAST_MODIFIED_DATE = "lastModifiedDate";
	public static final String LAST_MODIFIED_BY = "lastModifiedBy";

	
	/**
	 * @see com.thulasi.easyway.model.Auditable#createdDate
	 **/
	public static volatile SingularAttribute<Auditable, LocalDateTime> createdDate;
	
	/**
	 * @see com.thulasi.easyway.model.Auditable#createdBy
	 **/
	public static volatile SingularAttribute<Auditable, Object> createdBy;
	
	/**
	 * @see com.thulasi.easyway.model.Auditable#lastModifiedDate
	 **/
	public static volatile SingularAttribute<Auditable, LocalDateTime> lastModifiedDate;
	
	/**
	 * @see com.thulasi.easyway.model.Auditable#lastModifiedBy
	 **/
	public static volatile SingularAttribute<Auditable, Object> lastModifiedBy;
	
	/**
	 * @see com.thulasi.easyway.model.Auditable
	 **/
	public static volatile MappedSuperclassType<Auditable> class_;

}

