package java_mysql_erp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java_mysql_erp.dao.TitleDao;
import java_mysql_erp.dto.Title;
import java_mysql_erp.util.jdbcUtil;

public class TitleDaoImpl implements TitleDao {
	private static final TitleDaoImpl instance = new TitleDaoImpl();
	
	private TitleDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public static TitleDaoImpl getInstance() {
		return instance;
	}

	@Override
	public ArrayList<Title> selectTitleByAll() {
		String sql = "select code, name from title";
		ArrayList<Title> list =  new ArrayList<>();
		
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			while(rs.next()) {
				list.add(getTitle(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list; //size가 0이거나 그렇지 않은 경우
	}

	private Title getTitle(ResultSet rs) throws SQLException {
		int code = rs.getInt("code");
		String name = rs.getString("name");
		return new Title(code, name);
	}

	@Override
	public Title selectTitleByCode(Title title) {
		String sql = "select code, name from title where code = ?";
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setInt(1, title.getCode());
			System.out.println("pstmt >> " + pstmt);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					return getTitle(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertTitle(Title title) {
		String sql = "insert into title values (?, ?)";
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1, title.getCode());
			pstmt.setString(2, title.getName());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateTitle(Title title) {
		String sql = "update title set name = ? where code = ?";
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, title.getName());
			pstmt.setInt(2, title.getCode());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteTitle(Title title) {
		String sql = "delete from title where code = ?";
		try(Connection con = jdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1, title.getCode());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
