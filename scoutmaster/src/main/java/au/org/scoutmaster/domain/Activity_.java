package au.org.scoutmaster.domain;

import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import au.org.scoutmaster.domain.access.User;

@Generated(value="Dali", date="2013-09-19T09:12:29.121+1000")
@StaticMetamodel(Activity.class)
public class Activity_ extends BaseEntity_ {
	public static volatile SingularAttribute<Activity, ActivityType> type;
	public static volatile SingularAttribute<Activity, User> addedBy;
	public static volatile SingularAttribute<Activity, Contact> withContact;
	public static volatile SingularAttribute<Activity, Date> activityDate;
	public static volatile SingularAttribute<Activity, String> subject;
	public static volatile SingularAttribute<Activity, String> details;
}
