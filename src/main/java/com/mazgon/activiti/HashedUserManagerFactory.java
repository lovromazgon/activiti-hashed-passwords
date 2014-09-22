package com.mazgon.activiti;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;

/**
 * Session factory for creating HashedUserManager as the UserIdentityManager
 *
 * @author lovro.mazgon
 * Created on 22.09.2014
 */
public class HashedUserManagerFactory implements SessionFactory {
	@Override
	public Class<?> getSessionType() {
		return UserIdentityManager.class;
	}

	@Override
	public Session openSession() {
		return new HashedUserManager();
	}
}
