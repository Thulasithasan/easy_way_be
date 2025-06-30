package com.thulasi.easyway.util.event;

import com.thulasi.easyway.model.User;

public class UserCreatedEvent extends UserEvent {

	public UserCreatedEvent(Object source, User user) {
		super(source, user);
	}

}
