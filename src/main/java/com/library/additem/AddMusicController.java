package com.library.additem;

import com.library.DAOFactory.DAOFactory;
import com.library.DAOFactory.IDAOFactory;
import com.library.IDAO.IMusicDAO;
import com.library.businessModels.Music;

public class AddMusicController {
	
	IDAOFactory factory;

	public AddMusicController() {
		factory = new DAOFactory();
	}

	public void addMusicRecordInDatabase(Music music) {
		IMusicDAO iMusicDAO = factory.makeMusicDAO();
		iMusicDAO.createMusic(music);
	}


}
