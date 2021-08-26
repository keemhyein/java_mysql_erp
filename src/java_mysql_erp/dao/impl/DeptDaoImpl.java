package java_mysql_erp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java_mysql_erp.dao.DeptDao;
import java_mysql_erp.dto.Department;
import java_mysql_erp.util.jdbcUtil;

public class DeptDaoImpl implements DeptDao {
	private static final DeptDaoImpl instance = new DeptDaoImpl();
	
	private DeptDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static DeptDaoImpl getInstance() {
		return instance;
	}

	@Override
	public ArrayList<Department> selectDeptByAll() {
		String sql = "select code, name, floor from department"; 
		ArrayList<Department> list = new ArrayList<>();
		
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			while(rs.next()) {
				list.add(getDept(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private Department getDept(ResultSet rs) throws SQLException {
		int code = rs.getInt("code");
		String name = rs.getString("name");
		int floor = rs.getInt("floor");
		return new Department(code, name, floor);
	}

	@Override
	public Department selectDeptByCode(Department department) {
		String sql = "select code, name, floor from department where code = ?";
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			pstmt.setInt(1, department.getCode());
			System.out.println("pstmt >> " + pstmt);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					return getDept(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertDept(Department department) {
		String sql = "insert into department values (?, ?, ?)";
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, department.getCode());
			pstmt.setString(2, department.getName());
			pstmt.setInt(3, department.getFloor());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateDept(Department department) {
		String sql = "update department set name = ? where code = ?";
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, department.getName());
			pstmt.setInt(2, department.getCode());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteDept(Department department) {
		String sql = "delete from department where code = ?";
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, department.getCode());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
