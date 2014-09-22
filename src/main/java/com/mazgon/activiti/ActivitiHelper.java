package com.mazgon.activiti;

import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper class for converting Activiti User to HashedUser
 *
 * @author lovro.mazgon
 * Created on 22.09.2014
 */
public class ActivitiHelper {
	public static List<User> convertToHashedUser(List<User> users) {
		if (users == null) {
			return null;
		}
		return users
				.stream()
				.map(ActivitiHelper::convertToHashedUser)
				.collect(Collectors.toList());
	}

	public static User convertToHashedUser(User user) {
		if (user == null) {
			return null;
		} else if (user instanceof HashedUser) {
			return user;
		} else if (user instanceof UserEntity) {
			return new HashedUser((UserEntity) user);
		} else {
			throw new IllegalArgumentException("Unknown user type " + user.getClass());
		}
	}
}
