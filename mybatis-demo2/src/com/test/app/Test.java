package com.test.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.User;

public class Test {

	public static void main(String[] args) {
		String resource="mybatis-config.xml";
		InputStream inputStream =null;
		SqlSession sqlSession=null;
		try{
			inputStream= Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
			//2.得到执行sql的对象
			sqlSession = sqlSessionFactory.openSession();
			//3.执行sql语句
			//3.1使用单参数（用户名）查询
			//List<Map> list = sqlSession.selectList("findUserByUsername","jack");
			//3.2使用多参数（用户名和密码）查询，利用map传递参数
			//Map<String,Object> map=new HashMap<String,Object>();
			//map.put("username", "张三");
			//map.put("password", "123");
			//List<Map> list=sqlSession.selectList("findUserByUsernameAndPassword",map);
			//3.3使用多参数（用户名和密码）查询，利用实体类user传递
			User user=new User();
			user.setUsername("张三");
			user.setPassword("123");
			List<Map> list=sqlSession.selectList("findUserByUser",user);
			//4.输出结果
			for (Map map1 : list) {
			System.out.println(map1);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
		//5.关闭数据库
			sqlSession.close();
		}
	}

}
