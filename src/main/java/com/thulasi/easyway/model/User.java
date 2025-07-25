package com.thulasi.easyway.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "province")
	private String province;

	@Column(name = "district")
	private String district;

	@Column(name = "city")
	private String city;

	@Column(name = "password")
	private String password;

	@Column(name = "previous_passwords")
	private String previousPasswords;

	@Builder.Default
	@Column(name = "is_password_changed")
	private Boolean isPasswordChangedForTheFirstTime = false;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;

	@Builder.Default
	@Column(name = "is_active", nullable = false)
	private Boolean isActive = true;

	public List<String> getPreviousPasswordsList() {
		if (previousPasswords == null || previousPasswords.isEmpty()) {
			return new ArrayList<>();
		}
		return new ArrayList<>(List.of(previousPasswords.split(",")));
	}

	public void addPreviousPassword(String password) {
		List<String> previousPasswordList = getPreviousPasswordsList();
		previousPasswordList.add(password);
		this.previousPasswords = String.join(",", previousPasswordList);
	}

}
