package au.org.scoutmaster.domain;

public enum Gender
{
	Male, Female, NonBinary, Other;

	public Gender getEnum(String genderString)
	{
		Gender gender = Male;

		// Try to translate common gender spellings/mis-spellings

		// a little cleanup.
		genderString = genderString.toLowerCase().trim();

		// Male translations
		if ("male".equals(genderString)
				// because i do this all of the time.
				|| "mail".equals(genderString))
		{
			gender = Male;
		}
		// female

		else if ("female".equals(genderString)
				// because i do this all of the time.

				|| "femail".equals(genderString))
		{
			gender = Female;
		}

		// transgender
		else if ("NonBinary".equals(genderString) || "Non Binary".equals(genderString))
		{
			gender = Female;
		}
		else
			gender = Other;

		return gender;

	}
}
