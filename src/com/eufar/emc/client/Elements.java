package com.eufar.emc.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;

public class Elements {
	
	public enum infoEnum{TEMPREF, CONDITION, LIMITATION, RESPORG, METADATA, AIRINSTRUMENT};
	
	
	// checkbox hack to align vertically text
	public static HorizontalPanel checkBox(final String string) {
		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		final InlineLabel inlineLabel = new InlineLabel(string);
		final CheckBox box = new CheckBox();
		box.setName(string);
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		inlineLabel.setStyleName("checkBoxLabel");
		horizontalPanel.add(box);
		horizontalPanel.add(inlineLabel);
		return horizontalPanel;
	}


	// radiobutton hack to align vertically text
	public static HorizontalPanel radioButton(final String group, final String text) {
		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		final InlineLabel inlineLabel = new InlineLabel(text);
		final RadioButton box = new RadioButton(group, "");
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		inlineLabel.setStyleName("checkBoxLabel");
		horizontalPanel.add(box);
		horizontalPanel.add(inlineLabel);
		box.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				GuiModification.changeTarget(inlineLabel.getText());
			}
		});
		return horizontalPanel;
	}
	
	
	// add easily an element in an xml file
	public static Element addElement(Document doc, String string, Element parent, String value) {
		Element new_child = doc.createElement(string);
		new_child.appendChild(doc.createTextNode(value));
		parent.appendChild(new_child);
		return new_child;
	}


	// add easily an element in an xml file
	public static Element addElement(Document doc, String string, Element parent) {
		Element new_child = doc.createElement(string);
		parent.appendChild(new_child);
		return new_child;
	}
	
	
	// create all the Info buttons in the different tabs
	public static PushButton addInfoButton(final String context) {
		final Image image = new Image("icons/info_icon.png");
		final PushButton infoButton = new PushButton(image);
		infoButton.setPixelSize(25, 25);
		infoButton.setStyleName("infoButton");
		infoButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				PopupMessages.infoPanel(context, infoButton);
			}
		});
		return infoButton;
	}
	
	
	// add a new widget in the GUI based on user demand
	public static PushButton plusButton(final String string) {
		final Image image = new Image("icons/plus_icon.png");
		final PushButton plusButton = new PushButton(image);
		plusButton.setPixelSize(25, 25);
		plusButton.setStyleName("infoButton");
		plusButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				switch (infoEnum.valueOf(string.toUpperCase())) {
				case TEMPREF:
					GuiModification.addRefPlus();
					break;
				case CONDITION:
					GuiModification.addUsePlus(Emc_eufar.useConditionsAddTab, Emc_eufar.useConditionsLst, Emc_eufar.auDelButton1);
					break;
				case LIMITATION:
					GuiModification.addUsePlus(Emc_eufar.useLimitationsAddTab, Emc_eufar.useLimitationsLst, Emc_eufar.auDelButton2);
					break;
				case RESPORG:
					GuiModification.addOrgPlus();
					break;
				case METADATA:
					GuiModification.addMetPlus();
					break;
				case AIRINSTRUMENT:
					if (Emc_eufar.airInstrumentLst.getSelectedItemText() == "Make a choice...") {
						break;
					}
					GuiModification.addInstPlus();
					break;
				default:
					break;
				}
			}
		});
		return plusButton;
	}
}
