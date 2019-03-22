package com.library.IBussinessModelSetter;

import java.sql.ResultSet;

import com.library.businessModels.Music;

public interface IMusicSetter {
	public Music mapMusic(ResultSet resultSet);
}
