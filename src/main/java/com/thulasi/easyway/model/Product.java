package com.thulasi.easyway.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.thulasi.easyway.type.MeasurementUnit;
import com.thulasi.easyway.util.converter.StringListConverter;
import com.thulasi.easyway.util.converter.StringMapConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<ProductName> nameTranslations = new ArrayList<>();

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "measurement_value", nullable = false)
	private BigDecimal measurementValue;

	@Enumerated(EnumType.STRING)
	@Column(name = "measurement_unit", nullable = false)
	private MeasurementUnit measurementUnit;

	@Column(name = "hero_image")
	private String heroImage;

	@Convert(converter = StringListConverter.class)
	@Column(name = "images", columnDefinition = "text")
	private List<String> images;

	@ManyToOne
	@JoinColumn(name = "sub_category_id", nullable = false)
	@JsonBackReference
	private SubCategory subCategory;

	@Builder.Default
	@Column(name = "is_active", nullable = false)
	private Boolean isActive = true;

}
