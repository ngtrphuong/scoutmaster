package au.org.scoutmaster.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import au.org.scoutmaster.domain.security.SecurityRole;
import au.org.scoutmaster.security.eSecurityRole;

/**
 * Permissions are used to define what features a given Role has access to.
 *
 * This class is used to import GroupSetup from an xml file.
 *
 *
 *
 * @author bsutton
 *
 */
@Root(name = "SecurityRole")
public class XMLSecurityRole
{

	@Attribute(name = "name") // xml descriptor for GroupSetup resource file
	String name;

	public String getName()
	{
		return this.name;
	}

	public SecurityRole createSecurityRole()
	{
		return new SecurityRole(eSecurityRole.valueOf(this.name));
	}
}
