package com.norticeboard.smc.auth;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.norticeboard.smc.model.entity.Authority;


public interface AuthoritiesRepository extends JpaRepository<Authority, Integer>{
	List<Authority> findByUserName(String userName);
}
