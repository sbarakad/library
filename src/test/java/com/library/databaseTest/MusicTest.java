package com.library.databaseTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.library.DAO.MusicDAO;
import com.library.IDAO.IMusicDAO;
import com.library.businessModels.Music;

public class MusicTest {

	IMusicDAO iMusicDAO = new MusicDAO();

	@Test
	public void getMusicByIdTest() {
		Music music = iMusicDAO.getMusicById(3001);
		assertEquals("Despacito", music.getTitle());
	}

	@Test
	public void getMusicByCategoryTest() {
		List<Music> listOfMusicsByCategory = iMusicDAO.getMusicByCategory("Pop");
		assertEquals("Despacito", listOfMusicsByCategory.get(0).getTitle());
		assertEquals("Thunder", listOfMusicsByCategory.get(1).getTitle());

	}

	@Test
	public void deleteMusicTest() {
		Music music = new Music();
		music.setItemID(3005);
		assertTrue(iMusicDAO.deleteMusic(music));
	}
}
