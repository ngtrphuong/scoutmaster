package au.org.scoutmaster.domain;

import au.org.scoutmaster.domain.accounting.Money;
import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-06-29T20:00:48.667+1000")
@StaticMetamodel(RaffleBook.class)
public class RaffleBook_ extends BaseEntity_ {
	public static volatile SingularAttribute<RaffleBook, Raffle> raffle;
	public static volatile SingularAttribute<RaffleBook, RaffleAllocation> raffleAllocation;
	public static volatile SingularAttribute<RaffleBook, Integer> ticketCount;
	public static volatile SingularAttribute<RaffleBook, Integer> firstNo;
	public static volatile SingularAttribute<RaffleBook, Integer> ticketsReturned;
	public static volatile SingularAttribute<RaffleBook, Money> amountReturned;
	public static volatile SingularAttribute<RaffleBook, Date> dateReturned;
	public static volatile SingularAttribute<RaffleBook, Contact> collectedBy;
	public static volatile SingularAttribute<RaffleBook, Boolean> receiptIssued;
	public static volatile SingularAttribute<RaffleBook, String> notes;
}
