package au.org.scoutmaster.views.messagingWizard;

import org.vaadin.teemu.wizards.WizardStep;

import au.org.scoutmaster.views.MessagingWizardView;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class TransmissionComplete implements WizardStep
{

	@SuppressWarnings("unused")
	private MessagingWizardView messagingWizardView;

	public TransmissionComplete(MessagingWizardView messagingWizardView)
	{
		this.messagingWizardView = messagingWizardView;
	}

	@Override
	public String getCaption()
	{
		return "Transmission Complete";
	}

	@Override
	public Component getContent()
	{
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(new Label("Transmission complete."));
		layout.setMargin(true);
		return layout;
	}

	@Override
	public boolean onAdvance()
	{
		return true;
	}

	@Override
	public boolean onBack()
	{
		return false;
	}

}
