package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class DepartmentDaoJDBC implements DepartmentDao {
	
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("insert into department (name) values (?)"
					, Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			
			st.getConnection().setAutoCommit(false);
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeSResultSet(rs);
			}
			else throw new DbException("Unexpected error! No rows affected!");
			st.getConnection().commit();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"UPDATE department "+
					"SET name = ? "+
					"WHERE id = ?");
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
			st.getConnection().setAutoCommit(false);
			st.executeUpdate();
			st.getConnection().commit();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("delete from department WHERE id = ?");
			st.setInt(1, id);
			
			st.getConnection().setAutoCommit(false);
			st.executeUpdate();
			st.getConnection().commit();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select * from department where id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Department department = instantiateDepartment(rs);

				return department;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}	
		finally {
			DB.closeStatement(st);
			DB.closeSResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select * from department");
			rs = st.executeQuery();
			
			List<Department> ldp = new ArrayList<Department>();
			
			while (rs.next()) {
				Department dp = instantiateDepartment(rs);	
				ldp.add(dp);
			}
			return ldp;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}	
		finally {
			DB.closeStatement(st);
			DB.closeSResultSet(rs);
		}
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dp = new Department();
		dp.setId(rs.getInt("id"));
		dp.setName(rs.getString("name"));
		return dp;
	}
}
