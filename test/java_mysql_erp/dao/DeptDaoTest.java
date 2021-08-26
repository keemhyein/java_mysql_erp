package java_mysql_erp.dao;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java_mysql_erp.dao.impl.DeptDaoImpl;
import java_mysql_erp.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeptDaoTest {
	private DeptDao dao;
	
	@Before
	public void setUp() throws Exception {
		dao = DeptDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
		dao = null;
	}

	@Test
	public void test01SelectDeptByAll() {
		System.out.println("test01SelectDeptByAll()");
		ArrayList<Department> list = dao.selectDeptByAll();
		Assert.assertNotEquals(0, list.size());
		list.stream().forEach(s -> System.out.println(s));
	}

	@Test
	public void test02SelectDeptByCode() {
		System.out.println("test02SelectDeptByCode()");
		Department selectDept = dao.selectDeptByCode(new Department(3));
		Assert.assertNotNull(selectDept);
		System.out.println(selectDept);
	}

	@Test
	public void test03InsertDept() {
		System.out.println("test03InsertDept()");
		Department newDept = new Department(4, "영업", 6);
		int res = dao.insertDept(newDept);
		Assert.assertEquals(1, res);
		
		test01SelectDeptByAll();
	}

	@Test
	public void test04UpdateDept() {
		System.out.println("test04UpdateDept()");
		
		Department selectedDept = dao.selectDeptByCode(new Department(4));
		selectedDept.setName("개발");
		
		int res = dao.updateDept(selectedDept);
		Assert.assertEquals(1, res);
		
		test01SelectDeptByAll();		
	}

	@Test
	public void test05DeleteDept() {
		System.out.println("test05DeleteDept()");
		int res = dao.deleteDept(new Department(4));
		Assert.assertEquals(1, res);
		
		
		test01SelectDeptByAll();
	}

}
