package au.org.scoutmaster.domain;

import au.org.scoutmaster.domain.access.User;
import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-16T17:00:13.884+1100")
@StaticMetamodel(ForgottenPasswordReset.class)
public class ForgottenPasswordReset_ extends BaseEntity_ {
	public static volatile SingularAttribute<ForgottenPasswordReset, Date> expires;
	public static volatile SingularAttribute<ForgottenPasswordReset, User> userWithBadMemory;
	public static volatile SingularAttribute<ForgottenPasswordReset, String> resetid;
}
