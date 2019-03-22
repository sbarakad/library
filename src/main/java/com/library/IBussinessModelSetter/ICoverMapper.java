package com.library.IBussinessModelSetter;
	import java.sql.ResultSet;
	import com.library.businessModels.Cover;

public interface ICoverMapper {
	public Cover mapCover(ResultSet resultSet);
}
