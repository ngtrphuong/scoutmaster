package au.org.scoutmaster.domain;

import au.org.scoutmaster.domain.RelationshipType.Type;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-20T22:06:36.967+1100")
@StaticMetamodel(RelationshipType.class)
public class RelationshipType_ extends BaseEntity_ {
	public static volatile SingularAttribute<RelationshipType, String> lhs;
	public static volatile SingularAttribute<RelationshipType, Type> lhsType;
	public static volatile SingularAttribute<RelationshipType, String> rhs;
	public static volatile SingularAttribute<RelationshipType, Type> rhsType;
}
