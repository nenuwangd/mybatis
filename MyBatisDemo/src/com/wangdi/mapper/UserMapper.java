package com.wangdi.mapper;

import java.util.List;

import com.wangdi.domain.User;

public interface UserMapper {
	public List<User> findUserByName(String username);
	public User findUserByIdAndSex(Integer id,String sex);
}
