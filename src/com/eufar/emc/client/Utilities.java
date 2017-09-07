package com.eufar.emc.client;

import static com.google.gwt.query.client.GQuery.$;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.datepicker.client.DateBox;

public class Utilities {
	
	
	// let the user choose the location in the Geographic Information tab
	public static void geoLocationSet(final int index) {
		Emc_eufar.rootLogger.log(Level.INFO, "Selection of a localisation in progress...");
		if (index == 0) {
			Emc_eufar.geoDetailLst.clear();
			Emc_eufar.geoDetailLst.setEnabled(false);
		} else {
			Emc_eufar.geoDetailLst.clear();
			Emc_eufar.geoDetailLst.setEnabled(true);
			if (index == 1) {
				populateListBox(Emc_eufar.geoDetailLst, Emc_eufar.continentList, 0);
			} else if (index == 2) {
				populateListBox(Emc_eufar.geoDetailLst, Emc_eufar.countryList, 0);
			} else if (index == 3) {
				populateListBox(Emc_eufar.geoDetailLst, Emc_eufar.oceanList, 0);
			} else if (index == 4) {
				populateListBox(Emc_eufar.geoDetailLst, Emc_eufar.regionList, 0);
			}
			Emc_eufar.rootLogger.log(Level.INFO, "Localisation selected.");
		}
	}
	
	
	// populate easily each List Box - 1
	public static void populateListBox(ListBox listBox, ArrayList<String> list, int defaultItem) {
		Emc_eufar.rootLogger.log(Level.INFO, "Populating " + listBox.getName() + " in progress...");
		for (int i=0; i<list.size(); i++) {
			listBox.addItem(list.get(i));
		}
		listBox.setItemSelected(defaultItem, true);
		Emc_eufar.rootLogger.log(Level.INFO, "Populating finished");
	}
	
	
	// populate easily each List Box - 2
	public static void populateListBox(ListBox listBox, HashMap<String, String> map, int defaultItem) {
		Emc_eufar.rootLogger.log(Level.INFO, "Populating " + listBox.getName() + " in progress...");
		ArrayList<String> list = new ArrayList<String>();
		for (Entry<String, String> entry : map.entrySet()) {
			list.add(entry.getKey());
		}
		Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
		listBox.addItem("Make a choice...");
		for (int i = 0; i < list.size(); i++) {
			listBox.addItem(list.get(i));
		}
		listBox.setItemSelected(defaultItem, true);
		Emc_eufar.rootLogger.log(Level.INFO, "Populating finished");
	}
	
	
	// populate easily instrument List Box
	public static void populateAircraftListBox(ListBox listBox, String[][] string, int defaultItem) {
		Emc_eufar.rootLogger.log(Level.INFO, "Populating " + listBox.getName() + " in progress...");
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < string.length; i++) {
			String fullAircraft = string[i][1];
			try {
				if (fullAircraft == string[i - 1][1] & string[i][0] == string[i - 1][0]) {
					fullAircraft = fullAircraft + " - " + string[i][2];
				}
			} catch (Exception e) {}
			try {
				if (fullAircraft == string[i + 1][1] & string[i][0] == string[i + 1][0]) {
					fullAircraft = fullAircraft + " - " + string[i][2];
				}
			} catch (Exception e) {}
			list.add(string[i][0] + " - " + fullAircraft.substring(fullAircraft.indexOf(", ") + 2));
		}
		Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
		listBox.addItem("Make a choice...");
		listBox.addItem("Other...");
		for (int i = 0; i < list.size(); i++) {
			listBox.addItem(list.get(i));
		}
		listBox.setItemSelected(defaultItem, true);
		Emc_eufar.rootLogger.log(Level.INFO, "Populating finished");
	}
	
	
	// populate easily instrument List Box
	public static void populateInstrumentListBox(ListBox listBox, String[][] string, int defaultItem) {
		Emc_eufar.rootLogger.log(Level.INFO, "Populating " + listBox.getName() + " in progress...");
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < string.length; i++) {
			list.add(string[i][1] + " - " + string[i][0]);
		}
		Collections.sort(list);
		listBox.addItem("Make a choice...");
		listBox.addItem("Other...");
		for (int i = 0; i < list.size(); i++) {
			listBox.addItem(list.get(i));
		}
		listBox.setItemSelected(defaultItem, true);
		Emc_eufar.rootLogger.log(Level.INFO, "Populating finished");
	}
	
	
	// get answer from RadioButtons
	public static String getAnswer(final HorizontalPanel panel01, final HorizontalPanel panel02) {
		String string = new String("");
		if (((CheckBox) panel01.getWidget(0)).getValue() == true) {
			string = "yes";
		} else if (((CheckBox) panel02.getWidget(0)).getValue() == true) {
			string = "no";
		}
		return string;
	}
	
	
	// read position of RadioButton in XML file - 1
	public static void getPosition(final String string, final String subString, final HorizontalPanel panel01, final HorizontalPanel panel02) {
		int linkIndex01 = string.indexOf(subString);
		int linkIndex02 = string.indexOf("|", linkIndex01 + subString.length());
		if (string.substring(linkIndex01 + subString.length(), linkIndex02) == "yes") {
			((CheckBox) panel01.getWidget(0)).setValue(true);
		} else if (string.substring(linkIndex01 + subString.length(), linkIndex02) == "no") {
			((CheckBox) panel02.getWidget(0)).setValue(true);
		}
	}
	
	
	// read position of RadioButton in XML file - 2
	public static void getPosition(final String string, final HorizontalPanel panel01, final HorizontalPanel panel02) {
		if (string == "yes") {
			((CheckBox) panel01.getWidget(0)).setValue(true);
		} else if (string == "no") {
			((CheckBox) panel02.getWidget(0)).setValue(true);
		}
	}
	

	// find the correct text and index in a List Box from an entry in an xml file
	public static void checkList(HashMap<String, String> roleMap, String string, ListBox listBox) {
		Emc_eufar.rootLogger.log(Level.INFO, "Link between listbox and treeMap invoked...");
		String key = new String();
		for (Entry<String, String> entry : roleMap.entrySet()) {
			if (entry.getValue().equals(string)) {
				key = entry.getKey();
				break;
			}
		}
		int indexToFind = -1;
		for (int i = 0; i < listBox.getItemCount(); i++) {
			if (listBox.getItemText(i).equals(key)) {
				indexToFind = i;
				break;
			}
		}
		listBox.setSelectedIndex(indexToFind);
	}
	
	
	public static void checkList(String string, ListBox listBox) {
		Emc_eufar.rootLogger.log(Level.INFO, "Link between listbox and string invoked...");
		for (int i = 0; i < listBox.getItemCount(); i++) {
			if (listBox.getItemText(i).equals(string)) {
				listBox.setSelectedIndex(i);
				break;
			}
		}
	}


	// find the correct check box in a panel, and tick or untick it, from an entry in an xml file
	public static void checkBox(ScrollPanel scrollPanel, HashMap<String, String> categoriesMap, String string) {
		Emc_eufar.rootLogger.log(Level.INFO, "Link between checkbox and treeMap invoked...");
		List<CheckBox> allCheckBox = $("*", scrollPanel).widgets(CheckBox.class);
		String key = new String();
		for (Entry<String, String> entry : categoriesMap.entrySet()) {
			if (entry.getValue().equals(string)) {
				key = entry.getKey(); 
				break;
			}
		}
		for (int i = 0; i < allCheckBox.size(); i = i + 2) {
			if (allCheckBox.get(i).getName() == key) {
				allCheckBox.get(i).setValue(true);
			}
		}
	}
	
	
	/// put all labels and text to default color and position
	public static void runCheckDefault() {
		Emc_eufar.rootLogger.log(Level.INFO, "Default display before check in progress...");
		boolean otherInstrumentState = Emc_eufar.airInstNameLab.isVisible();
		boolean otherAircraftState = Emc_eufar.airTypeBox.isVisible();
		boolean labelAircraftState = Emc_eufar.airManufacturerInfo.isVisible();
		boolean otherUnitState = Emc_eufar.geoUnitLab.isVisible();
		ArrayList<TextBoxBase> allHiddenTextBoxes = new ArrayList<TextBoxBase>();
		ArrayList<ListBox> allHiddenListBoxes = new ArrayList<ListBox>();
		for (int i = 0; i < Emc_eufar.allTextBoxes.size(); i++) {
			if (!Emc_eufar.allTextBoxes.get(i).isVisible()) {
				allHiddenTextBoxes.add(Emc_eufar.allTextBoxes.get(i));
			}
		}
		for (int i = 0; i < Emc_eufar.allListBoxes.size(); i++) {
			if (!Emc_eufar.allListBoxes.get(i).isVisible()) {
				allHiddenListBoxes.add(Emc_eufar.allListBoxes.get(i));
			}
		}
		List<Label> allLabel = $("*", Emc_eufar.subDockPanel).widgets(Label.class);
		List<ListBox> allListBox = $("*", Emc_eufar.subDockPanel).widgets(ListBox.class);
		for (int i = 0; i < allLabel.size(); i++) {
			String style = allLabel.get(i).getStylePrimaryName();
			allLabel.get(i).getElement().setAttribute("style","");
			allLabel.get(i).setStyleName(style);
		}
		for (int i = 0; i < Emc_eufar.allTextBoxes.size(); i++) {
			String style = Emc_eufar.allTextBoxes.get(i).getStylePrimaryName();
			Emc_eufar.allTextBoxes.get(i).getElement().setAttribute("style","");
			Emc_eufar.allTextBoxes.get(i).setStyleName(style);
		}
		for (int i = 0; i < Emc_eufar.allSuggestBoxes.size(); i++) {
			String style = Emc_eufar.allSuggestBoxes.get(i).getStylePrimaryName();
			Emc_eufar.allSuggestBoxes.get(i).getElement().setAttribute("style","");
			Emc_eufar.allSuggestBoxes.get(i).setStyleName(style);
		}
		for (int i = 0; i < Emc_eufar.allDateBoxes.size(); i++) {
			String style = Emc_eufar.allDateBoxes.get(i).getStylePrimaryName();
			Emc_eufar.allDateBoxes.get(i).getElement().setAttribute("style","");
			Emc_eufar.allDateBoxes.get(i).setStyleName(style);
		}
		for (int i = 0; i < allListBox.size(); i++) {
			String style = allListBox.get(i).getStylePrimaryName();
			allListBox.get(i).getElement().setAttribute("style","");
			allListBox.get(i).setStyleName(style);
		}
		for (int i = 0; i < 9; i++) {
			Emc_eufar.tabPanel.getTabWidget(i).getElement().setAttribute("style","color: white !important;");
		}
		for (int i = 0; i < Emc_eufar.qvTabPanel.getWidgetCount(); i++) {
			Emc_eufar.qvTabPanel.getTabWidget(i).getElement().setAttribute("style","color: white !important;");
		}
		Emc_eufar.airInstNameLab.setVisible(otherInstrumentState);
		Emc_eufar.airInstNameBox.setVisible(otherInstrumentState);
		Emc_eufar.airInstManufacturerLab.setVisible(otherInstrumentState);
		Emc_eufar.airInstManufacturerBox.setVisible(otherInstrumentState);
		Emc_eufar.airManufacturerBox.setVisible(otherAircraftState);
		Emc_eufar.airTypeBox.setVisible(otherAircraftState);
		Emc_eufar.airOperatorBox.setVisible(otherAircraftState);
		Emc_eufar.airCountryLst.setVisible(otherAircraftState);
		Emc_eufar.airRegistrationBox.setVisible(otherAircraftState);
		Emc_eufar.airManufacturerInfo.setVisible(labelAircraftState);
		Emc_eufar.airTypeInfo.setVisible(labelAircraftState);
		Emc_eufar.airOperatorInfo.setVisible(labelAircraftState);
		Emc_eufar.airCountryInfo.setVisible(labelAircraftState);
		Emc_eufar.airRegistrationInfo.setVisible(labelAircraftState);
		Emc_eufar.geoUnitLab.setVisible(otherUnitState);
		Emc_eufar.geoUnitLst.setVisible(otherUnitState);
		for (int i = 0; i < allHiddenTextBoxes.size(); i++) {
			allHiddenTextBoxes.get(i).setVisible(false);
		}
		for (int i = 0; i < allHiddenListBoxes.size(); i++) {
			allHiddenListBoxes.get(i).setVisible(false);
		}
		for (int i = 0; i < Emc_eufar.allRadioButtons.size(); i++) {
			Emc_eufar.allRadioButtons.get(i).getElement().setAttribute("style","border: 1px solid white !important;");
		}
		for (int i = 0; i < Emc_eufar.allCheckBoxes.size(); i++) {
			HorizontalPanel horizontalPanel = Emc_eufar.allCheckBoxes.get(i);
			horizontalPanel.getElement().setAttribute("style","border: 1px solid white !important;");
		}
		Emc_eufar.rootLogger.log(Level.INFO, "Default display set.");
	}
	
	
	// modify the window title to add " - modified"
	public static void docIsModified() {
		Emc_eufar.isModified = true;
		if (!Emc_eufar.titleString.contains("modified")) {
			Emc_eufar.titleString = Emc_eufar.titleString + " - modified";
		}
		Window.setTitle(Emc_eufar.titleString);
	}


	// modify the window title to remove " - modified"
	public static void docNotModified() {
		Emc_eufar.isModified = false;
		if (Emc_eufar.titleString.contains("modified")) {
			Emc_eufar.titleString = Emc_eufar.titleString.substring(0, 22);
		}
		Window.setTitle(Emc_eufar.titleString);
	}


	// obtain screen width
	public static native int getScreenWidth()/*-{
		return screen.width;
	}-*/;
	
	
	// clear all fields
	public static void clearFields() {
		Emc_eufar.rootLogger.log(Level.INFO, "Cleaning all fields in progress...");
		List<Label> allLabel = $("*", Emc_eufar.subDockPanel).widgets(Label.class);
		for (int i = 0; i < allLabel.size(); i++) {
			allLabel.get(i).getElement().setAttribute("style","color: black !important;");
		}
		List<TextBox> allTextBox = $("*", Emc_eufar.subDockPanel).widgets(TextBox.class);
		for (int i = 0; i < allTextBox.size(); i++) {
			allTextBox.get(i).setText("");
		}
		List<TextArea> allTextArea = $("*", Emc_eufar.subDockPanel).widgets(TextArea.class);
		for (int i = 0; i < allTextArea.size(); i++) {
			allTextArea.get(i).setText("");
		}
		List<CheckBox> allCheckBox = $("*", Emc_eufar.subDockPanel).widgets(CheckBox.class);
		for (int i = 0; i < allCheckBox.size(); i++) {
			allCheckBox.get(i).setValue(false);
		}
		List<DateBox> allDateBox = $("*", Emc_eufar.subDockPanel).widgets(DateBox.class);
		for (int i = 0; i < allDateBox.size(); i++) {
			allDateBox.get(i).setValue(new Date());
		}
		Emc_eufar.identTypeLst.setSelectedIndex(0);
		Emc_eufar.identLanguageLst.setSelectedIndex(5);
		GuiModification.newAircraftInformation("Make a choice...", "");
		Emc_eufar.airInstrumentLst.setSelectedIndex(0);
		GuiModification.otherInstrument();
		Emc_eufar.geoLocationLst.setSelectedIndex(0);
		Utilities.geoLocationSet(0);
		Emc_eufar.geoResolutionLst.setSelectedIndex(0);
		GuiModification.geoResolutionSet(0);
		Emc_eufar.orgRoleLst.setSelectedIndex(5);
		Emc_eufar.metLanguageLst.setSelectedIndex(5);
		Emc_eufar.useConditionsAddTab.removeAllRows();
		Emc_eufar.useConditionsAddTab.setWidget(0, 0, Emc_eufar.useConditionsBox);
		Emc_eufar.useConditionsAddTab.setWidget(0, 1, Emc_eufar.auDelButton1);
		Emc_eufar.useLimitationsAddTab.removeAllRows();
		Emc_eufar.useLimitationsAddTab.setWidget(0, 0, Emc_eufar.useLimitationsBox);
		Emc_eufar.useLimitationsAddTab.setWidget(0, 1, Emc_eufar.auDelButton2);
		Emc_eufar.useConditionsLst.clear();
		Emc_eufar.useLimitationsLst.clear();
		Emc_eufar.useConditionsLst.add(Emc_eufar.useConditionsBox);
		Emc_eufar.useLimitationsLst.add(Emc_eufar.useLimitationsBox);
		Emc_eufar.useCondCorrectLst.clear();
		Emc_eufar.useLimCorrectLst.clear();
		Emc_eufar.useCondCorrectLst.add("string");
		Emc_eufar.useLimCorrectLst.add("string");
		Emc_eufar.orgPartyLst.clear();
		Emc_eufar.orgEmailLst.clear();
		Emc_eufar.orgPartyLst.add(Emc_eufar.orgPartyBox);
		Emc_eufar.orgEmailLst.add(Emc_eufar.orgEmailBox);
		Emc_eufar.orgPartyCorrectLst.clear();
		Emc_eufar.orgEmailCorrectLst.clear();
		Emc_eufar.orgPartyCorrectLst.add("string");
		Emc_eufar.orgEmailCorrectLst.add("email");
		Emc_eufar.metNameLst.clear();
		Emc_eufar.metEmailLst.clear();
		Emc_eufar.metNameLst.add(Emc_eufar.metNameBox);
		Emc_eufar.metEmailLst.add(Emc_eufar.metEmailBox);
		Emc_eufar.metNameCorrectLst.clear();
		Emc_eufar.metEmailCorrectLst.clear();
		Emc_eufar.metNameCorrectLst.add("string");
		Emc_eufar.metEmailCorrectLst.add("email");
		Emc_eufar.refPhaseTab.removeAllRows();
		Emc_eufar.refPhaseTab.setWidget(0, 0, Emc_eufar.refPhaseLab);
		Emc_eufar.refPhaseTab.setWidget(0, 1, Emc_eufar.refStartDat);
		Emc_eufar.refPhaseTab.setWidget(0, 2, Emc_eufar.refEndDat);
		Emc_eufar.refPhaseTab.setWidget(0, 3, Emc_eufar.refDelButton);
		Emc_eufar.refDelImage.setVisible(false);
		Emc_eufar.orgAddTab.removeAllRows();
		Emc_eufar.orgAddTab.setWidget(0, 0, Emc_eufar.horizontalPanel27);
		Emc_eufar.metAddTab.removeAllRows();
		Emc_eufar.metAddTab.setWidget(0, 0, Emc_eufar.horizontalPanel66);
		Emc_eufar.metDelImage.setVisible(false);
		Emc_eufar.orgDelImage.setVisible(false);
		Emc_eufar.useDelImage1.setVisible(false);
		Emc_eufar.useDelImage2.setVisible(false);
		Emc_eufar.refDelButton.setStyleName("emptyButton");
		Emc_eufar.airInstrumentTable.removeAllRows();
		Emc_eufar.airAircraftTable.removeAllRows();
		Emc_eufar.aircraftTabList.clear();
		Emc_eufar.operatorTabList.clear();
		Emc_eufar.manufacturairTabList.clear();
		Emc_eufar.countryTabList.clear();
		Emc_eufar.identificationTabList.clear();
		Emc_eufar.instrumentTabList.clear();
		Emc_eufar.manufacturerTabList.clear();
		Emc_eufar.qvTabPanel.clear();
		Emc_eufar.simplePanel01.clear();
		Emc_eufar.insituNum = 0;
		Emc_eufar.imageryNum = 0;
		Emc_eufar.qvInsituMap.clear();
		Emc_eufar.qvImageryMap.clear();
		runCheckDefault();
		Emc_eufar.useConditionsBox.setText("As EUFAR is an EU-funded project, data in the EUFAR archive are available to everyone. All users are "
				+ "requiered to acknowledge the data providers in any publication based on EUFAR data.");
		Emc_eufar.useLimitationsBox.setText("No limitations");
		Emc_eufar.identLocatorBox.setText("http://browse.ceda.ac.uk/browse/badc/eufar/docs/00eufararchivecontents.html");
		Emc_eufar.rootLogger.log(Level.INFO, "Cleaning all fields finished.");
	}
	
	
	// remove point from date string if it exists
	public static String dateStringCorrection(final String string) {
		String splitString[] = string.split(" ");
		String year = splitString[2];
		String day = splitString[1].substring(0, splitString[1].length() - 1);
		String month = Emc_eufar.monthMap.get(splitString[0]);
		return year + "-" + month + "-" + day;
	}
}
