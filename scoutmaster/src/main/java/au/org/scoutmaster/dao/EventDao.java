package au.org.scoutmaster.dao;

import javax.persistence.EntityManager;

import au.com.vaadinutils.dao.JpaBaseDao;
import au.org.scoutmaster.domain.Event;

import com.vaadin.addon.jpacontainer.JPAContainer;

public class EventDao extends JpaBaseDao<Event, Long> implements Dao<Event, Long>
{

	public EventDao()
	{
		// inherit the default per request em. 
	}
	
	public EventDao(EntityManager em)
	{
		super(em);
	}
	
	@Override
	public JPAContainer<Event> createVaadinContainer()
	{
		return super.createVaadinContainer();
	}
}
