package com.wangdi.demo1;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.Reader;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.wangdi.dao.UserDao;
import com.wangdi.dao.impl.UserDaoImpl;
import com.wangdi.domain.User;
import com.wangdi.mapper.UserMapper;

public class MyBatisDemo1 {

	@Test
	/**
	 * 根据id查询用户
	 * @throws Exception
	 */
	public void testMyBatis1() throws Exception {
		
		String resource="sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in );
		SqlSession sqlSession = sessionFactory.openSession();
		User user = sqlSession.selectOne("user.findUserById",28);
		System.out.println(user);
	}
	@Test
	//增加用户
	public void testMyBatis2() throws Exception {
		
		String resource="sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in );
		SqlSession sqlSession = sessionFactory.openSession();
		User user =  new User();
		user.setUsername("朝六");
		user.setAddress("HongKong");
		user.setBirthday(new Date());
		user.setSex("1");
		int affectedRows = sqlSession.insert("user.addOne",user);
		sqlSession.commit();
		System.out.println(affectedRows);
		System.out.println("生成的id是"+user.getId());
	}
	@Test
	//删除用户
	public void testMyBatis3() throws Exception {
		
		String resource="sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in );
		SqlSession sqlSession = sessionFactory.openSession();
		int affectedRows = sqlSession.delete("user.deleteOne", 26);
		sqlSession.commit();
		System.out.println(affectedRows);
	}
	@Test
	//修改用户
	public void testMyBatis4() throws Exception {
		
		String resource="sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in );
		SqlSession sqlSession = sessionFactory.openSession();
		User user =  new User();
		user.setId(30);
		user.setUsername("头七");
		user.setAddress("HongKong");
		user.setBirthday(new Date());
		user.setSex("0");
		int update = sqlSession.update("user.updateById",user);
		sqlSession.commit();
		System.out.println(update);
	}
	@Test
	//模糊查询用户
	public void testMyBatis5() throws Exception {
		
		String resource="sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in );
		SqlSession sqlSession = sessionFactory.openSession();
		List<User> userList = sqlSession.selectList("user.findUserByName", "明");
		for (User user : userList) {
			System.out.println(user);
		}
	}
	@Test
	public void testUserDao() throws Exception {
		UserDao dao = new UserDaoImpl();
		List<User> list = dao.findByName("明");
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testUserMapper() throws Exception {
		
		String resource="sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in );
		SqlSession sqlSession = sessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> userList = userMapper.findUserByName("明");
		for (User user : userList) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testUserMapper2() throws Exception {
		
		String resource="sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in );
		SqlSession sqlSession = sessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserByIdAndSex(28, "1");
		System.out.println(user);
	}
	
}
