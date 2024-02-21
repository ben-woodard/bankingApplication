package com.coderscampus.assignment13.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity // Class name = User, DB Table name = user
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@SuperBuilder
@Table(name = "users")
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String username;
	private String password;
	private String name;
	private LocalDate createdDate;
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinTable(name = "user_account",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "account_id"))
	private List<Account> accounts = new ArrayList<>();
	@OneToOne(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private Address address;

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", accounts=" + accounts + ", address=" + address + "]";
	}
}
