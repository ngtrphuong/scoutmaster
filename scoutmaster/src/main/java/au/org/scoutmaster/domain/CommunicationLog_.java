package au.org.scoutmaster.domain;

import au.org.scoutmaster.domain.security.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-03-11T22:34:18.873+1100")
@StaticMetamodel(CommunicationLog.class)
public class CommunicationLog_ extends BaseEntity_ {
	public static volatile SingularAttribute<CommunicationLog, String> guid;
	public static volatile SingularAttribute<CommunicationLog, CommunicationType> type;
	public static volatile SingularAttribute<CommunicationLog, User> addedBy;
	public static volatile SingularAttribute<CommunicationLog, Contact> withContact;
	public static volatile SingularAttribute<CommunicationLog, Date> activityDate;
	public static volatile SingularAttribute<CommunicationLog, String> subject;
	public static volatile SingularAttribute<CommunicationLog, String> details;
}
