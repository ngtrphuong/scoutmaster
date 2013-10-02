package au.org.scoutmaster.domain.accounting;

import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import au.org.scoutmaster.domain.BaseEntity_;
import au.org.scoutmaster.domain.Contact;

@Generated(value="Dali", date="2013-09-19T09:12:29.097+1000")
@StaticMetamodel(Invoice.class)
public class Invoice_ extends BaseEntity_ {
	public static volatile SingularAttribute<Invoice, Date> created;
	public static volatile SingularAttribute<Invoice, String> invoiceNo;
	public static volatile SingularAttribute<Invoice, Contact> billTo;
	public static volatile SingularAttribute<Invoice, Date> invoiceDate;
	public static volatile SingularAttribute<Invoice, Integer> terms;
	public static volatile ListAttribute<Invoice, InvoiceLine> invoiceLines;
	public static volatile ListAttribute<Invoice, Payment> payments;
	public static volatile SingularAttribute<Invoice, String> notes;
}
