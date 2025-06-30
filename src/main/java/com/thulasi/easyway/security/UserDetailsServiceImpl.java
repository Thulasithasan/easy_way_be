package com.thulasi.easyway.security;

import com.thulasi.easyway.constant.CommonMessageConstant;
import com.thulasi.easyway.exception.ModuleException;
import com.thulasi.easyway.model.User;
import com.thulasi.easyway.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	private final AuthorityService authorityService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email)
			.map(this::createUserDetails)
			.orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_USER_NOT_FOUND));
	}

	private UserDetails createUserDetails(User user) {
		return DckapUserDetails.builder()
			.username(user.getEmail())
			.password(user.getPassword())
			.enabled(user.getIsActive())
			.authorities(authorityService.getAuthorities(user))
			.build();
	}

}
