package com.library.BussinessModelSetter;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.library.IBussinessModelSetter.IMusicSetter;
import com.library.businessModels.Music;

public class MusicSetter implements IMusicSetter{

	@Override
	public Music mapMusic(ResultSet resultSet) {
		
		Music music = new Music();
		try {
			music.setTitle(resultSet.getString("Title"));
			music.setCategory(resultSet.getString("Category"));
			music.setRecordLabel(resultSet.getString("Record_Label"));
			music.setArtist(resultSet.getString("Artist"));
			music.setAvailability(resultSet.getInt("Availability"));
			music.setItemID(resultSet.getInt("Item_ID"));
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		
		return music;
	}
}
