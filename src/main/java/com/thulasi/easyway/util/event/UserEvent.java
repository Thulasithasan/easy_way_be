package com.thulasi.easyway.util.event;

import com.thulasi.easyway.model.User;
import org.springframework.context.ApplicationEvent;

public abstract class UserEvent extends ApplicationEvent {

	private final transient User user;

	protected UserEvent(Object source, User user) {
		super(source);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
