package com.thulasi.easyway.security;

import com.thulasi.easyway.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface AuthorityService {

	List<GrantedAuthority> getAuthorities(User user);

}
