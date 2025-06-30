package com.thulasi.easyway.util.event;

import com.thulasi.easyway.model.User;

public class UserDeactivatedEvent extends UserEvent {

	public UserDeactivatedEvent(Object source, User user) {
		super(source, user);
	}

}
