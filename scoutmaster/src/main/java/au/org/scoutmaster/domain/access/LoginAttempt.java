package au.org.scoutmaster.domain.access;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.constraints.NotBlank;

import au.org.scoutmaster.domain.BaseEntity;

@Entity
@Table(name="LoginAttempt")
@NamedQueries(
{
		@NamedQuery(name = LoginAttempt.FIND_BY_NAME, query = "SELECT loginAttempt FROM LoginAttempt loginAttempt WHERE loginAttempt.user.username = :username"),
		@NamedQuery(name = LoginAttempt.FIND_LAST_ATTEMPTS, query = "SELECT loginAttempt FROM LoginAttempt loginAttempt WHERE loginAttempt.user.username = :username order by loginAttempt.dateOfAttempt desc"),
		@NamedQuery(name = LoginAttempt.FIND_FIVE_MINUTE_ATTEMPTS, query = "SELECT loginAttempt FROM LoginAttempt loginAttempt "
				+ "WHERE loginAttempt.user.username = :username and loginAttempt.dateOfAttempt > :fiveMinutesAgo order by loginAttempt.dateOfAttempt desc"),
		})
public class LoginAttempt extends BaseEntity
{
	
	private static final long serialVersionUID = 1L;

	@Transient
	private static final Logger logger = LogManager.getLogger(LoginAttempt.class);

	
	public static final String FIND_BY_NAME = "LoginAttempt.findByName";

	public static final String FIND_LAST_ATTEMPTS = "LoginAttempt.findLastAttempt";

	public static final String FIND_FIVE_MINUTE_ATTEMPTS = "LoginAttempt.findFiveMinuteAttempts";

	/**
	 * The user that attempted to login.
	 */
	@ManyToOne
	private User user;

	/**
	 * The date time that the user attempted to login.
	 */
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dateOfAttempt = new Date();
	
	/**
	 * True if the login attempt was successful.
	 */
	private boolean succeeded;
	
	/**
	 * For jpa.
	 */
	LoginAttempt()
	{
	}
	
	public LoginAttempt(User user, boolean succeeded)
	{
		this.user = user;
		this.succeeded = succeeded;
	}

	public Date getDateOfAttempt()
	{
		return dateOfAttempt;
	}

	public boolean isSucceeded()
	{
		return succeeded;
	}

	@Override
	public String getName()
	{
		return user.getName() + " attempt: " + dateOfAttempt.toString() + " succeeded:" + succeeded;
	}

	

}

