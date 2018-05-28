package com.mudi.main;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.mudi.bean.AnType;
import com.mudi.bean.Animals;
import com.mudi.dao.AnTypeDAO;
import com.mudi.dao.AnimalsDAO;
import com.mudi.util.BaseDAO;

public class TestMain {

	public static void main(String[] args) {
		//测试：具体每个表的查询方法
		
		AnimalsDAO ad = new AnimalsDAO();
		/*
		ArrayList<Animals> al = ad.getList();
		for (Animals animals : al) {
			System.out.println(animals.getName());
		}
		
		AnTypeDAO atd = new AnTypeDAO();
		ArrayList<AnType> atl = atd.getList();
		for (AnType at : atl) {
			System.out.println(at.getAnName());
		}
		 
		 //insert()
		Animals an = new Animals();
		an.setAge(10);
		an.setName("Moye");
		an.setAnId(1);
		System.out.println(ad.insert(an));
		*/
		
		
		
		
		
		//测试：BaseDAO,getList()
		/*
		BaseDAO baseDAO = new BaseDAO();
		ArrayList<AnType> animals = baseDAO.getList(AnType.class);
		for (AnType an : animals) {
			System.out.println("anid:" + an.getAnId() + " name:" + an.getAnName());
		}
		*/
		
		//测试：BaseDAO,getObjectById()
		/*
		BaseDAO baseDAO = new BaseDAO();
		Animals animal = (Animals)baseDAO.getObjectById(Animals.class, 1);
		System.out.println(animal.getName());
		*/
		
		//测试：BaseDAO,getListByCondition()
		/*
		BaseDAO baseDAO = new BaseDAO();
		ArrayList<Animals> animals = baseDAO.getListByCondition(Animals.class, "age", 12);
		for (Animals an : animals) {
			System.out.println("id:"+an.getId()+" name:"+an.getName()+" age:"+an.getAge());
		}
		*/
		
		//测试：BaseDAO,insert()
		BaseDAO baseDAO = new BaseDAO();
		Animals an = new Animals();
		an.setAge(15);
		an.setName("JiuGui");
		an.setAnId(1);
		System.out.println(baseDAO.insert(an));
	}

}
