package au.org.scoutmaster.domain;

import java.sql.Time;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-20T22:06:36.984+1100")
@StaticMetamodel(SectionMeetingDefaults.class)
public class SectionMeetingDefaults_ extends BaseEntity_ {
	public static volatile SingularAttribute<SectionMeetingDefaults, Section> section;
	public static volatile SingularAttribute<SectionMeetingDefaults, Night> meetingNight;
	public static volatile SingularAttribute<SectionMeetingDefaults, Time> startTime;
	public static volatile SingularAttribute<SectionMeetingDefaults, Time> EndTime;
	public static volatile SingularAttribute<SectionMeetingDefaults, String> meetingSubject;
	public static volatile SingularAttribute<SectionMeetingDefaults, String> meetingDetails;
	public static volatile SingularAttribute<SectionMeetingDefaults, Address> location;
}
