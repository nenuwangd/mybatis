package com.wangdi.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.wangdi.dao.UserDao;
import com.wangdi.domain.User;

public class UserDaoImpl implements UserDao {
	SqlSessionFactory sessionFactory;
	
	public UserDaoImpl() throws Exception{

		String resource="sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		sessionFactory = new SqlSessionFactoryBuilder().build(in );
	}

	@Override
	public List<User> findByName(String username) {
		SqlSession sqlSession = sessionFactory.openSession();
		List<User> userList = sqlSession.selectList("user.findUserByName",username);
		return userList;
	}

}
