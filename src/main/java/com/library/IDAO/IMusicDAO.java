package com.library.IDAO;

import java.util.LinkedList;
import java.util.List;

import com.library.businessModels.Movie;
import com.library.businessModels.Music;
import com.library.search.IMovieSearchRequestDetails;
import com.library.search.IMusicSearchRequestDetails;

public interface IMusicDAO {
	public Music getMusicById(int itemID);
	public List<Music> getMusicByCategory(String category);
	public Boolean createMusic(Music music);
	public Boolean updateMusic(Music music);
	public Boolean deleteMusic(Music music); 
	public LinkedList<Music> getMusicBySearchTerms(IMusicSearchRequestDetails searchRequestDetails); 
}
