package com.wangdi.dao;

import java.util.List;

import com.wangdi.domain.User;

public interface UserDao {
	public List<User> findByName(String username);
}
