package com.thulasi.easyway.service;

import com.thulasi.easyway.model.User;
import com.thulasi.easyway.payload.response.ResponseEntityDto;

import java.util.Optional;

public interface UserService {

	User getCurrentUser();

	Optional<User> getOptionalCurrentUser();

	ResponseEntityDto getCurrentUserDetails();

}
