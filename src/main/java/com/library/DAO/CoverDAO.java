package com.library.DAO;

import java.sql.Blob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.library.BussinessModelSetter.CoverMapper;
import com.library.IDAO.ICoverDAO;
import com.library.businessModels.Cover;
import com.library.dbConnection.DatabaseConnection;

public class CoverDAO implements ICoverDAO {
	
	private final String SELECT_COVER_BY_ITEM_ID_QUERY = "SELECT * FROM covers WHERE Item_ID = ?";
	private final String INSERT_COVER_BY_ITEM_ID_QUERY = "INSERT INTO covers (Item_ID,Cover_Blob,File_Extension) Values (?,?,?)";
	private final String DELETE_COVER_BY_ITEM_ID_QUERY = "DELETE FROM covers WHERE Item_ID = ?";
	private PreparedStatement preparedStatement;
	private Connection dbConnection;
	private CoverMapper coverMapper = new CoverMapper();
	
	public CoverDAO() {
			DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
			this.dbConnection = databaseConnection.getConnection();
	}
	
	@Override
	public Cover getCoverByID(int itemID) {		
		try {
			preparedStatement = dbConnection.prepareStatement(SELECT_COVER_BY_ITEM_ID_QUERY);
			preparedStatement.setInt(1, itemID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return coverMapper.mapCover(resultSet);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(null != preparedStatement) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public boolean createCoverByID(int itemID, Blob coverBlob, String fileExtension) {
		try {
			preparedStatement = dbConnection.prepareStatement(INSERT_COVER_BY_ITEM_ID_QUERY);
			preparedStatement.setInt(1, itemID);
			preparedStatement.setBlob(2, coverBlob);
			preparedStatement.setString(3, fileExtension);
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(null != preparedStatement) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean deleteBlobByID(int itemID) {
		try {
			preparedStatement = dbConnection.prepareStatement(DELETE_COVER_BY_ITEM_ID_QUERY);
			preparedStatement.setInt(1, itemID);
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(null != preparedStatement) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}
}

