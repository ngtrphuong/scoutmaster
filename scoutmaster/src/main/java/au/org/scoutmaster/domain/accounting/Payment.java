package au.org.scoutmaster.domain.accounting;

import java.sql.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import au.org.scoutmaster.domain.BaseEntity;

@Entity
@Table(name="Payment")
@Access(AccessType.FIELD)
public class Payment extends BaseEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The date the payment was made.
	 */
	Date paymentDate;

	/**
	 * The set of invoices this payment applies to.
	 */
	@ManyToMany(targetEntity=Invoice.class)
	List<Invoice> invoices;

	/**
	 * The amount paid
	 */
	MoneyWithTax amount;

	/**
	 * A reference for the payment. Typically a cheque no. or a bank transaction
	 * id
	 */
	String reference;

	/**
	 * A note that optionally describes the purpose of the payment.
	 */
	String note;

	@Override
	public String getName()
	{
		return paymentDate.toString() + note;
	}
}
