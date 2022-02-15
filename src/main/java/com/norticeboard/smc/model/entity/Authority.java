package com.norticeboard.smc.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

	@Id
	@Column(name = "user_id")
	String userId;
	
	@Column(name = "user_name")
	String userName;
	
	@Column(name = "authority")
	String authority;
}
