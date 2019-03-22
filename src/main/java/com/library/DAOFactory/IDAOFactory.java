package com.library.DAOFactory;

import com.library.IDAO.*;

public interface IDAOFactory {
	
	public IBookDAO makeBookDAO();
	public ICoverDAO makeCoverDAO();
	public ILibraryItemDAO makeLibraryItemDAO();
	public IMovieDAO makeMovieDAO();
	public IMusicDAO makeMusicDAO();
	public IUserDAO makeUserDAO();
	public IUserItemDAO makeUserItemDAO();

}
