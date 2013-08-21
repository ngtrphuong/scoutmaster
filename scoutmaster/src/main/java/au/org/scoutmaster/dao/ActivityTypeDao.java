package au.org.scoutmaster.dao;

import java.util.List;

import javax.persistence.EntityManager;

import au.org.scoutmaster.domain.ActivityType;

public class ActivityTypeDao extends JpaBaseDao<ActivityType, Long> implements Dao<ActivityType, Long>
{

	public ActivityTypeDao()
	{
		// inherit the default per request em. 
	}
	public ActivityTypeDao(EntityManager em)
	{
		super(em);
	}

	@Override
	public List<ActivityType> findAll()
	{
		return super.findAll(ActivityType.FIND_ALL);
	}
	
	public ActivityType findByName(String name)
	{
		return super.findSingleBySingleParameter(ActivityType.FIND_BY_NAME, "name", name);
	}
}
