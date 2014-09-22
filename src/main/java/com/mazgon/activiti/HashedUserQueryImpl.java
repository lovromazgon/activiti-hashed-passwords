package com.mazgon.activiti;

import org.activiti.engine.identity.User;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;

import java.util.List;

import static com.mazgon.activiti.ActivitiHelper.convertToHashedUser;

/**
 * Extends the UserQueryImpl object and returns instances of HashedUser
 *
 * @author lovro.mazgon
 * Created on 22.09.2014
 */
public class HashedUserQueryImpl extends UserQueryImpl {
	public HashedUserQueryImpl() {
		super();
	}

	public HashedUserQueryImpl(CommandContext commandContext) {
		super(commandContext);
	}

	public HashedUserQueryImpl(CommandExecutor commandExecutor) {
		super(commandExecutor);
	}

	@Override
	public List<User> executeList(CommandContext commandContext, Page page) {
		return convertToHashedUser(super.executeList(commandContext, page));
	}

	@Override
	public List<User> list() {
		return convertToHashedUser(super.list());
	}

	@Override
	public List<User> listPage(int firstResult, int maxResults) {
		return convertToHashedUser(super.listPage(firstResult, maxResults));
	}

	@Override
	public User singleResult() {
		return convertToHashedUser(super.singleResult());
	}

	@Override
	public User executeSingleResult(CommandContext commandContext) {
		return convertToHashedUser(super.executeSingleResult(commandContext));
	}
}
