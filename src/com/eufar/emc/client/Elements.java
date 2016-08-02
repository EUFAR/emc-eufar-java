package com.eufar.emc.client;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;

public class Elements {
	
	public enum infoEnum{TEMPREF, CONDITION, LIMITATION, RESPORG, METADATA, AIRINSTRUMENT, AIRCRAFT};
	
	
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
	public static SimplePanel addInfoButton(final String context) {
		final Image image = new Image("icons/info_icon_v2.png");
		final SimplePanel infoButton = new SimplePanel(image);
		infoButton.setPixelSize(25, 25);
		infoButton.setStyleName("infoButton");
		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				PopupMessages.infoPanel(context, infoButton);
			}
		});
		return infoButton;
	}
	
	
	// add a new widget in the GUI based on user demand
	public static SimplePanel plusButton(final String string) {
		final Image image = new Image("icons/plus_icon_v2.png");
		final SimplePanel plusButton = new SimplePanel(image);
		plusButton.setPixelSize(25, 25);
		plusButton.setStyleName("infoButton");
		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				switch (infoEnum.valueOf(string.toUpperCase())) {
				case TEMPREF:
					GuiModification.addRefPlus();
					break;
				case CONDITION:
					GuiModification.addUsePlus(Emc_eufar.useConditionsAddTab, Emc_eufar.useConditionsLst, Emc_eufar.useDelImage1);
					break;
				case LIMITATION:
					GuiModification.addUsePlus(Emc_eufar.useLimitationsAddTab, Emc_eufar.useLimitationsLst, Emc_eufar.useDelImage2);
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
				case AIRCRAFT:
					if (Emc_eufar.airAircraftLst.getSelectedItemText() == "Make a choice...") {
						break;
					}
					GuiModification.addAircraftPlus();
					break;
				default:
					break;
				}
			}
		});
		return plusButton;
	}
}
