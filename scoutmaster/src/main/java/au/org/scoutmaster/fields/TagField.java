package au.org.scoutmaster.fields;import java.util.ArrayList;import java.util.Hashtable;import org.vaadin.tokenfield.TokenField;import au.org.scoutmaster.dao.TagDao;import au.org.scoutmaster.domain.Tag;import au.org.scoutmaster.editors.DialogListener;import au.org.scoutmaster.editors.TagEditor;import au.org.scoutmaster.filter.EntityManagerProvider;import com.vaadin.addon.jpacontainer.JPAContainer;import com.vaadin.addon.jpacontainer.JPAContainerFactory;import com.vaadin.shared.ui.combobox.FilteringMode;import com.vaadin.ui.Button;import com.vaadin.ui.Notification;/** * Displays a Tag field which allows the user to select one or more tags Once * complete you can retrieve a list of the selected Tags. *  * If the TagField is marked as writable the new Tags can be persisted back to * the Tag entity. *  * If the Tag field is marked as readonly then only tags which already exist in * the Tag entity may be selected. *  * @author bsutton *  */public class TagField extends TokenField{	private static final long serialVersionUID = 1L;	private JPAContainer<Tag> tagContainer;	/**	 * The list of tags selected in the TagField.	 * The key is the tokens entity id.	 */	private Hashtable<Long, Tag> tags = new Hashtable<>();	private boolean readonly;	private TagChangeListener changeListener;	@SuppressWarnings("unchecked")	public TagField(String fieldLabel, boolean readonly)	{		super(fieldLabel);		tagContainer = JPAContainerFactory.make(Tag.class, EntityManagerProvider.INSTANCE.getEntityManager());		//this.setStyleName(TokenField.STYLE_BUTTON_EMPHAZISED);		this.setInputPrompt("");		this.setContainerDataSource(tagContainer);		this.setConverter(Tag.class);		this.readonly = readonly;				this.setStyleName(TokenField.STYLE_TOKENFIELD); // remove fake															// textfield look		this.setWidth("100%"); // width...		this.setInputWidth("100%"); // and input width separately		this.setFilteringMode(FilteringMode.CONTAINS); // suggest		this.setTokenCaptionPropertyId("name"); // use container item property													// "name" in input		this.setInputPrompt("Enter one or more comma separated tags");		this.setRememberNewTokens(false); // we can opt to do this ourselves		this.setImmediate(true);	}	/**	 * Called when the user hits 'enter' or the 'tab' key to indicate that they	 * have finished entering tags. The tokenId may contain one or more comma	 * separated tags or tag ids.	 * 	 * The tokenId can either be a Long in which case it is the id of an	 * existing tag entity or can be a string in which case it is the text the	 * user typed in as the token meaning that the tag does not already exist.	 */	@Override	protected void onTokenInput(Object tokenId)	{		boolean updated = false;		if (tokenId instanceof String)		{			String[] tokenNames = ((String) tokenId).split(",");			for (String tokenName : tokenNames)			{				tokenName = tokenName.trim();				if (tokenName.length() > 0)				{					final TagDao daoTag = new TagDao();					// Does the tag exists					Tag tag = daoTag.findByName(tokenName);					if (tag != null)					{						// Now check if the Tag is already in our list						if (tags.contains(tag.getName()))							Notification.show(getTokenCaption(tag.getId()) + " is already in the list");						else						{							tags.put(tag.getId(), tag);							this.addToken(tag.getId());							updated = true;						}					}					else					{						if (readonly)						{							Notification.show("Only existing tags may be entered. The tag " + tokenName									+ " does not exist.");						}						else						{							// Tag doesn't exist so lets create one.							tag = new Tag(tokenName);							TagEditor editor = new TagEditor(tag, new DialogListener<Tag>()							{								@Override								public void confirmed(Tag tag)								{									// We MUST get our own DAO as this callback									// will happen in a different thread									// to the surrounding class.									final TagDao daoTag = new TagDao();									// user confirmed that they want to add the									// tag.									daoTag.persist(tag);									daoTag.flush();									TagField.this.addToken(tag.getId());									// we have to notify directly as this is processed in a different request/thread.									notifyListener();								}								@Override								public void declined()								{									// no-op								}							});							getUI().addWindow(editor);						}					}				}			}		}		else		{			// The token Id is an Entity Id of type Long.			final TagDao daoTag = new TagDao();			Long tagId = (Long) tokenId;			Tag tag = daoTag.findById(tagId);			if (tag != null)			{								// Now check if the Tag is already in our list				if (tags.contains(tag.getName()))					Notification.show(getTokenCaption(tag.getId()) + " is already in the list");				else				{					tags.put(tag.getId(), tag);					this.addToken(tag.getId());					updated = true;				}			}			else				throw new IllegalStateException("Tag with id=" + tagId + " is missing from db.");		}		if (updated)			notifyListener();	}	private void notifyListener()	{		if (this.changeListener != null)			this.changeListener.onTagListChanged(new ArrayList<Tag>(tags.values()));			}	@Override	protected void rememberToken(String tokenId)	{		String[] tokens = ((String) tokenId).split(",");		for (String token : tokens)		{			token = token.trim();			if (token.length() > 0)			{				super.rememberToken(token);			}		}	}	protected void configureTokenButton(Object tokenId, Button button)	{		// super.configureTokenButton(tokenId, button);		// custom caption		if (tokenId instanceof Tag)		{			Tag tag = (Tag) tokenId;			button.setCaption(tag.getName() + " x");			button.setIcon(getTokenIcon(tokenId));			button.setDescription("Click to remove");			// width			// button.setWidth("100%");			//button.addStyleName(TokenField.STYLE_TOKENFIELD);		}		else if (tokenId instanceof Long)		{			Long tagid = (Long) tokenId;			TagDao daoTag = new TagDao();			Tag tag = daoTag.findById(tagid);			button.setCaption(tag.getName() + " x");			button.setIcon(getTokenIcon(tokenId));			button.setDescription("Click to remove");			// width			// button.setWidth("100%");			//button.addStyleName(TokenField.STYLE_BUTTON_EMPHAZISED);		}		else			throw new IllegalArgumentException("Expected tokenId to be a Tag instead found a "					+ tokenId.getClass().getName());		// button.setStyleName(Reindeer.BUTTON_LINK);	}	/**	 * The users has clicked the token which we treat as a delete.	 */	@Override	protected void onTokenClick(Object tokenId)	{		Tag tag = (Tag)tokenId;		// remove the tag from our list		tags.remove(tag.getId());		super.onTokenClick(tag);		notifyListener();	}	public void addChangeListener(TagChangeListener listener)	{		this.changeListener = listener;			}}