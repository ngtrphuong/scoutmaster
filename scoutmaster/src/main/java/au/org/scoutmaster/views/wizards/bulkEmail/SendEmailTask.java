package au.org.scoutmaster.views.wizards.bulkEmail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.activation.DataSource;
import javax.persistence.EntityManager;

import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.ui.Notification.Type;

import au.com.vaadinutils.dao.EntityManagerProvider;
import au.com.vaadinutils.listener.CancelListener;
import au.com.vaadinutils.util.ProgressBarTask;
import au.com.vaadinutils.util.ProgressTaskListener;
import au.org.scoutmaster.dao.CommunicationLogDao;
import au.org.scoutmaster.dao.CommunicationTypeDao;
import au.org.scoutmaster.dao.ContactDao;
import au.org.scoutmaster.dao.DaoFactory;
import au.org.scoutmaster.dao.SMTPSettingsDao;
import au.org.scoutmaster.dao.TagDao;
import au.org.scoutmaster.dao.Transaction;
import au.org.scoutmaster.domain.CommunicationLog;
import au.org.scoutmaster.domain.CommunicationType;
import au.org.scoutmaster.domain.SMTPServerSettings;
import au.org.scoutmaster.domain.Tag;
import au.org.scoutmaster.domain.access.User;
import au.org.scoutmaster.forms.EmailAddressType;
import au.org.scoutmaster.util.SMNotification;
import au.org.scoutmaster.util.VelocityFormatException;

public class SendEmailTask extends ProgressBarTask<EmailTransmission> implements CancelListener
{
	Logger logger = LogManager.getLogger(SendEmailTask.class);
	private final Message message;
	private final List<EmailTransmission> transmissions;
	private final User user;
	private final HashSet<? extends DataSource> attachedFiles;
	private boolean cancel = false;

	public SendEmailTask(final ProgressTaskListener<EmailTransmission> listener, final User user, final Message message,
			final ArrayList<EmailTransmission> transmissions, final HashSet<? extends DataSource> attachedFiles)
	{
		super(listener);
		this.message = message;
		this.transmissions = transmissions;
		this.user = user;
		this.attachedFiles = attachedFiles;

	}

	@Override
	public void run()
	{
		try
		{
			sendMessages(this.user, this.transmissions, this.message);
		}
		catch (final Exception e)
		{
			this.logger.error(e, e);
			super.taskException(e);
		}

	}

	private void sendMessages(final User user, final List<EmailTransmission> targets, final Message message)
			throws IOException
	{

		final EntityManager em = EntityManagerProvider.createEntityManager();
		int sent = 0;

		try (Transaction t = new Transaction(em))
		{
			// We are in a background thread so we have to get our own entity
			// manager.
			EntityManagerProvider.setCurrentEntityManager(em);

			final SMTPSettingsDao daoSMTPSettings = new DaoFactory().getSMTPSettingsDao();
			final SMTPServerSettings settings = daoSMTPSettings.findSettings();

			for (final EmailTransmission transmission : targets)
			{
				if (this.cancel == true)
				{
					break;
				}

				try
				{
					final String expandedBody = message.expandBody(user, transmission.getContact());
					final StringBuffer expandedSubject = message.expandSubject(user, transmission.getContact());
					daoSMTPSettings.sendEmail(settings, message.getSenderEmailAddress(),
							new SMTPSettingsDao.EmailTarget(EmailAddressType.To, transmission.getRecipient()),
							expandedSubject.toString(), expandedBody.toString(), this.attachedFiles);

					// Log the activity
					final CommunicationLogDao daoActivity = new DaoFactory().getCommunicationLogDao();
					final CommunicationTypeDao daoActivityType = new DaoFactory().getActivityTypeDao();
					final CommunicationType type = daoActivityType.findByName(CommunicationType.BULK_EMAIL);
					final CommunicationLog activity = new CommunicationLog();
					activity.setAddedBy(user);
					activity.setWithContact(transmission.getContact());
					activity.setSubject(message.getSubject());
					activity.setDetails(message.getBody());
					activity.setType(type);
					daoActivity.persist(activity);

					// Tag the contact
					final ContactDao daoContact = new DaoFactory().getContactDao();
					final TagDao daoTag = new DaoFactory().getTagDao();
					for (Tag tag : transmission.getActivityTags())
					{
						tag = daoTag.merge(tag);
						daoContact.attachTag(transmission.getContact(), tag);
					}
					daoContact.merge(transmission.getContact());
					sent++;
					super.taskProgress(sent, targets.size(), transmission);
					SMNotification.show("Email sent to " + transmission.getContactName(), Type.TRAY_NOTIFICATION);

					// SMNotification.show("Message sent",
					// Type.TRAY_NOTIFICATION);
				}
				catch (final EmailException e)
				{
					this.logger.error(e, e);
					transmission.setException(e);
					super.taskItemError(transmission);
					SMNotification.show(e, Type.ERROR_MESSAGE);
				}
				catch (final VelocityFormatException e)
				{
					SMNotification.show(e, Type.ERROR_MESSAGE);
				}

			}

			t.commit();
		}
		finally
		{
			super.taskComplete(sent);

			// Reset the entity manager
			EntityManagerProvider.setCurrentEntityManager(null);
		}
	}

	@Override
	public void cancel()
	{
		this.cancel = true;

	}

}
