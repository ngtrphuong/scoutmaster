package au.org.scoutmaster.domain;

import au.org.scoutmaster.domain.security.User;
import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-08-07T16:53:32.891+1000")
@StaticMetamodel(ForgottenPasswordReset.class)
public class ForgottenPasswordReset_ extends BaseEntity_ {
	public static volatile SingularAttribute<ForgottenPasswordReset, Date> expires;
	public static volatile SingularAttribute<ForgottenPasswordReset, User> userWithBadMemory;
	public static volatile SingularAttribute<ForgottenPasswordReset, String> resetid;
}
