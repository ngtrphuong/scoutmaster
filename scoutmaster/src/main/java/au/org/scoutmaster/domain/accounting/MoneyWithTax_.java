package au.org.scoutmaster.domain.accounting;

import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.joda.money.Money;

@Generated(value="Dali", date="2013-09-19T09:12:29.109+1000")
@StaticMetamodel(MoneyWithTax.class)
public class MoneyWithTax_ {
	public static volatile SingularAttribute<MoneyWithTax, BigDecimal> taxPercentage;
	public static volatile SingularAttribute<MoneyWithTax, Money> money;
}
