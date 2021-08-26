package java_mysql_erp.dao;

import java.util.ArrayList;

import java_mysql_erp.dto.Department;

public interface DeptDao {
	
	ArrayList<Department> selectDeptByAll();
	
	Department selectDeptByCode(Department department);
	
	int insertDept(Department department);
	
	int updateDept(Department department);
	
	int deleteDept(Department department);

}
