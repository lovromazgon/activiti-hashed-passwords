package com.mazgon.activiti;

import org.activiti.engine.identity.Picture;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Adapter for Activiti UserEntity to enable hashed passwords
 *
 * @author lovro.mazgon
 * Created on 22.09.2014
 */
public class HashedUser extends UserEntity {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private UserEntity userEntity;

	private boolean passwordChanged = false;
	private boolean passwordHashed = true;

	public HashedUser(UserEntity user) {
		this.userEntity = user;
	}

	@Override
	public void setPassword(String password) {
		userEntity.setPassword(password);
		passwordChanged = true;
		passwordHashed = false;
	}

	public void hashPassword() {
		if (passwordChanged && !passwordHashed) {
			logger.debug("Hashing user password");
			userEntity.setPassword(HashUtil.createHash(userEntity.getPassword()));
			passwordHashed = true;
		}
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	@Override
	public void delete() {
		userEntity.delete();
	}

	@Override
	public Object getPersistentState() {
		return userEntity.getPersistentState();
	}

	@Override
	public int getRevisionNext() {
		return userEntity.getRevisionNext();
	}

	@Override
	public Picture getPicture() {
		return userEntity.getPicture();
	}

	@Override
	public void setPicture(Picture picture) {
		userEntity.setPicture(picture);
	}

	@Override
	public String getId() {
		return userEntity.getId();
	}

	@Override
	public void setId(String id) {
		userEntity.setId(id);
	}

	@Override
	public String getFirstName() {
		return userEntity.getFirstName();
	}

	@Override
	public void setFirstName(String firstName) {
		userEntity.setFirstName(firstName);
	}

	@Override
	public String getLastName() {
		return userEntity.getLastName();
	}

	@Override
	public void setLastName(String lastName) {
		userEntity.setLastName(lastName);
	}

	@Override
	public String getEmail() {
		return userEntity.getEmail();
	}

	@Override
	public void setEmail(String email) {
		userEntity.setEmail(email);
	}

	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}

	@Override
	public int getRevision() {
		return userEntity.getRevision();
	}

	@Override
	public void setRevision(int revision) {
		userEntity.setRevision(revision);
	}
}
