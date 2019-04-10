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
			//2.�õ�ִ��sql�Ķ���
			sqlSession = sqlSessionFactory.openSession();
			//3.ִ��sql���
			//3.1ʹ�õ��������û�������ѯ
			//List<Map> list = sqlSession.selectList("findUserByUsername","jack");
			//3.2ʹ�ö�������û��������룩��ѯ������map���ݲ���
			//Map<String,Object> map=new HashMap<String,Object>();
			//map.put("username", "����");
			//map.put("password", "123");
			//List<Map> list=sqlSession.selectList("findUserByUsernameAndPassword",map);
			//3.3ʹ�ö�������û��������룩��ѯ������ʵ����user����
			User user=new User();
			user.setUsername("����");
			user.setPassword("123");
			List<Map> list=sqlSession.selectList("findUserByUser",user);
			//4.������
			for (Map map1 : list) {
			System.out.println(map1);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
		//5.�ر����ݿ�
			sqlSession.close();
		}
	}

}
