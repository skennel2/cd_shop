package org.almansa.app.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.almansa.app.domain.Album;
import org.almansa.app.domain.AlbumType;
import org.almansa.app.domain.Artist;
import org.almansa.app.domain.CategoryTag;
import org.almansa.app.domain.Lable;
import org.almansa.app.domain.PersonBase;
import org.almansa.app.domain.Producer;
import org.almansa.app.domain.Song;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class DummyDataMaker {

	@PersistenceContext
	private EntityManager em;
	
	public void makeDummies() {
		CategoryTag tag1 = new CategoryTag();
		tag1.setName("HipHop");
		tag1.setCreationDate(new Date());
		em.persist(tag1);
		
		CategoryTag tag2 = new CategoryTag();
		tag2.setName("Show me the Money");
		tag2.setCreationDate(new Date());
		em.persist(tag2);	
		
		CategoryTag tag3 = new CategoryTag();
		tag3.setName("album of the year");
		tag3.setCreationDate(new Date());
		em.persist(tag3);			
		
		Lable jm = new Lable();
		jm.changeName("just musick");
		em.persist(jm);
		
		Lable illionaire = new Lable();
		illionaire.changeName("Illionaire");
		em.persist(illionaire);		
		
		Lable aomg = new Lable();
		aomg.changeName("AOMG");
		em.persist(aomg);				
		
		Producer nochang = new Producer();
		nochang.changeName("nochang");
		nochang.setLable(jm);
		em.persist(nochang);
		
		Producer chachamalon = new Producer();
		chachamalon.changeName("cha cha malon");
		chachamalon.setLable(aomg);
		em.persist(chachamalon);		
		
		Artist swings = new Artist();
		swings.changeName("swings");
		swings.changeLable(jm);
		em.persist(swings);
		
		Artist theQ = new Artist();
		theQ.changeName("the quiett");
		theQ.changeLable(illionaire);
		theQ.setBornDate(1996, 1, 3);		
		em.persist(theQ);	
		
		Artist jayPark = new Artist();
		jayPark.changeName("jay park");
		jayPark.changeLable(aomg);
		jayPark.setBornDate(1988, 3, 4);
		em.persist(jayPark);			

		Artist kidMilli = new Artist();
		kidMilli.changeName("kid milli");
		kidMilli.changeLable(null);
		em.persist(kidMilli);	
		
		Artist dok2 = new Artist();
		dok2.changeName("dok2");
		dok2.changeLable(illionaire);
		em.persist(dok2);			
		
		Song song = new Song();
		song.setName("holy");
		song.setOwnerArtist(swings);
		song.setProducer(nochang);
		em.persist(song);
		
		Song song2 = new Song();
		song2.setName("shit is real");
		song2.setOwnerArtist(swings);
		song2.setProducer(chachamalon); 
		List<PersonBase> list =  new ArrayList<PersonBase>();
		list.add(theQ);
		list.add(kidMilli);
		song2.setArtists(list);
		em.persist(song2);

		Song song3 = new Song();
		song3.setName("intro");
		song3.setOwnerArtist(swings);
		song3.setProducer(chachamalon); 
		em.persist(song3);		
		
		Album album = new Album();
		album.setAlbumArtist(swings);
		album.setName("upgrade3");
		List<Song> list2 =  new ArrayList<Song>();
		list2.add(song);
		list2.add(song2);		
		album.setSongs(list2);
		album.setReleaseDate(new Date());
		album.setAlbumType(AlbumType.LP);
		album.addCategory(tag1);
		album.addCategory(tag2);
		em.persist(album);	
		
		Album album2 = new Album();
		album2.setAlbumArtist(swings);
		album2.setName("shit is real single");		
		album2.setReleaseDate(new Date());
		album2.setAlbumType(AlbumType.Single);
		em.persist(album2);			
	}
	
	public Song getSong(Long id) {
		return em.find(Song.class, id);
	}	
}
