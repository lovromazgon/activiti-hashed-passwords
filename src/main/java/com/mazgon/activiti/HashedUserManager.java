package com.mazgon.activiti;

import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static com.mazgon.activiti.ActivitiHelper.convertToHashedUser;

/**
 * User manager which returns instances of HashedUser and HashedUserQueryImpl.
 * Also the check password method compares the hashes instead of plain passwords.
 *
 * @author lovro.mazgon
 * Created on 22.09.2014
 */
public class HashedUserManager extends UserEntityManager {

	@Override
	public UserQuery createNewUserQuery() {
		return new HashedUserQueryImpl(Context.getProcessEngineConfiguration().getCommandExecutor());
	}

	@Override
	public User createNewUser(String userId) {
		return convertToHashedUser(super.createNewUser(userId));
	}

	@Override
	public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
		return convertToHashedUser(super.findUserByQueryCriteria(query, page));
	}

	@Override
	public User findUserById(String userId) {
		return convertToHashedUser(super.findUserById(userId));
	}

	@Override
	public List<User> findPotentialStarterUsers(String proceDefId) {
		return convertToHashedUser(super.findPotentialStarterUsers(proceDefId));
	}

	@Override
	public List<User> findUsersByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
		return convertToHashedUser(super.findUsersByNativeQuery(parameterMap, firstResult, maxResults));
	}

	@Override
	public void updateUser(User user) {
		User updateUser = user;
		if (user instanceof HashedUser) {
			HashedUser hashedUser = (HashedUser) user;
			hashedUser.hashPassword();
			updateUser = hashedUser.getUserEntity();
		}
		super.updateUser(updateUser);
	}

	@Override
	public void insertUser(User user) {
		User insertUser = user;
		if (user instanceof HashedUser) {
			HashedUser hashedUser = (HashedUser) user;
			hashedUser.hashPassword();
			insertUser = hashedUser.getUserEntity();
		}
		super.insertUser(insertUser);
	}

	@Override
	public Boolean checkPassword(String userId, String password) {
		User user = findUserById(userId);

		if (user != null && password != null) {
			return HashUtil.validatePassword(password, user.getPassword());
		}

		return false;
	}
}
