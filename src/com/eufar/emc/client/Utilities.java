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
	
	
	// populate easily each List Box
	public static void populateListBox(ListBox listBox, ArrayList<String> list, int defaultItem) {
		Emc_eufar.rootLogger.log(Level.INFO, "Populating " + listBox.getName() + " in progress...");
		for (int i=0; i<list.size(); i++) {
			listBox.addItem(list.get(i));
		}
		listBox.setItemSelected(defaultItem, true);
		Emc_eufar.rootLogger.log(Level.INFO, "Populating finished");
	}
	
	
	public static void populateListBox(ListBox listBox, HashMap<String, String> map, int defaultItem) {
		Emc_eufar.rootLogger.log(Level.INFO, "Populating " + listBox.getName() + " in progress...");
		ArrayList<String> test = new ArrayList<String>();
		for (Entry<String, String> entry : map.entrySet()) {
			test.add(entry.getValue() + " - " + entry.getKey());
		}
		Collections.sort(test);
		listBox.addItem("Make a choice...");
		listBox.addItem("Other...");
		for (int i = 0; i < test.size(); i++) {
			listBox.addItem(test.get(i));
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
	
	
	// read position of RadioButton in XML file
	public static void getPosition(final String string, final String subString, final HorizontalPanel panel01, final HorizontalPanel panel02) {
		int linkIndex01 = string.indexOf(subString);
		int linkIndex02 = string.indexOf("|", linkIndex01 + subString.length());
		if (string.substring(linkIndex01 + subString.length(), linkIndex02) == "yes") {
			((CheckBox) panel01.getWidget(0)).setValue(true);
		} else if (string.substring(linkIndex01 + subString.length(), linkIndex02) == "no") {
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
		List<Label> allLabel = $("*", Emc_eufar.subDockPanel).widgets(Label.class);
		List<TextBoxBase> allBox = $("*", Emc_eufar.subDockPanel).widgets(TextBoxBase.class);
		List<DateBox> allDateBox = $("*", Emc_eufar.subDockPanel).widgets(DateBox.class);
		List<ListBox> allListBox = $("*", Emc_eufar.subDockPanel).widgets(ListBox.class);
		for (int i = 0; i < allLabel.size(); i++) {
			String style = allLabel.get(i).getStylePrimaryName();
			allLabel.get(i).getElement().setAttribute("style","");
			allLabel.get(i).setStyleName(style);
		}
		for (int i = 0; i < allBox.size(); i++) {
			String style = allBox.get(i).getStylePrimaryName();
			allBox.get(i).getElement().setAttribute("style","");
			allBox.get(i).setStyleName(style);
		}
		for (int i = 0; i < allDateBox.size(); i++) {
			String style = allDateBox.get(i).getStylePrimaryName();
			allDateBox.get(i).getElement().setAttribute("style","");
			allDateBox.get(i).setStyleName(style);
		}
		for (int i = 0; i < allListBox.size(); i++) {
			String style = allListBox.get(i).getStylePrimaryName();
			allListBox.get(i).getElement().setAttribute("style","");
			allListBox.get(i).setStyleName(style);
		}
		if (!Emc_eufar.tabLayout) {
			for (int i = 0; i < 10; i++) {
				Emc_eufar.stackPanel.getHeaderWidget(i).asWidget().setStylePrimaryName("textcompleted");
			}
		} else {
			for (int i = 0; i < 10; i++) {
				Emc_eufar.tabPanel.getTabWidget(i).getElement().setAttribute("style","color: Black !important;");
			}
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


	// obtain screen height
	public static native int getScreenHeight()/*-{
		return screen.height;
	}-*/;
	
	
	// function to reload the page
	public static native void reloadGUI() /*-{
		$wnd.location.reload();
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
		for (int i = 0; i < allCheckBox.size(); i = i + 1) {
			allCheckBox.get(i).setValue(false);
		}
		List<DateBox> allDateBox = $("*", Emc_eufar.subDockPanel).widgets(DateBox.class);
		for (int i = 0; i < allDateBox.size(); i = i + 1) {
			allDateBox.get(i).setValue(new Date());
		}
		Emc_eufar.identTypeLst.setSelectedIndex(0);
		Emc_eufar.identLanguageLst.setSelectedIndex(4);
		Emc_eufar.airAircraftLst.setSelectedIndex(0);
		GuiModification.aircraftInformation(0 + 1);
		Emc_eufar.airInstrumentLst.setSelectedIndex(0);
		GuiModification.otherInstrument();
		Emc_eufar.geoLocationLst.setSelectedIndex(0);
		Utilities.geoLocationSet(0);
		Emc_eufar.geoResolutionLst.setSelectedIndex(0);
		GuiModification.geoResolutionSet(0);
		Emc_eufar.orgRoleLst.setSelectedIndex(5);
		Emc_eufar.metLanguageLst.setSelectedIndex(4);
		Emc_eufar.useConditionsAddTab.removeAllRows();
		Emc_eufar.useConditionsAddTab.setWidget(0, 0, Emc_eufar.useConditionsBox);
		Emc_eufar.useConditionsAddTab.setWidget(0, 1, Emc_eufar.auDelButton1);
		Emc_eufar.useLimitationsAddTab.removeAllRows();
		Emc_eufar.useLimitationsAddTab.setWidget(0, 0, Emc_eufar.useLimitationsBox);
		Emc_eufar.useLimitationsAddTab.setWidget(0, 1, Emc_eufar.auDelButton2);
		Emc_eufar.refPhaseTab.removeAllRows();
		Emc_eufar.refPhaseTab.setWidget(0, 0, Emc_eufar.refPhaseLab);
		Emc_eufar.refPhaseTab.setWidget(0, 1, Emc_eufar.refStartDat);
		Emc_eufar.refPhaseTab.setWidget(0, 2, Emc_eufar.refEndDat);
		Emc_eufar.refPhaseTab.setWidget(0, 3, Emc_eufar.refDelButton);
		Emc_eufar.orgAddTab.removeAllRows();
		Emc_eufar.orgAddTab.setWidget(0, 0, Emc_eufar.horizontalPanel27);
		Emc_eufar.metAddTab.removeAllRows();
		Emc_eufar.metAddTab.setWidget(0, 0, Emc_eufar.horizontalPanel66);
		Emc_eufar.mmDelButton.setEnabled(false);
		Emc_eufar.mmDelButton.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
		Emc_eufar.mmDelButton.setStyleName("emptyButton");
		Emc_eufar.orgDelButton.setEnabled(false);
		Emc_eufar.orgDelButton.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
		Emc_eufar.orgDelButton.setStyleName("emptyButton");
		Emc_eufar.auDelButton1.setEnabled(false);
		Emc_eufar.auDelButton1.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
		Emc_eufar.auDelButton1.setStyleName("emptyButton");
		Emc_eufar.auDelButton1.setEnabled(false);
		Emc_eufar.auDelButton1.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
		Emc_eufar.auDelButton1.setStyleName("emptyButton");
		Emc_eufar.refDelButton.setEnabled(false);
		Emc_eufar.refDelButton.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
		Emc_eufar.refDelButton.setStyleName("emptyButton");
		Emc_eufar.airInstrumentTable.removeAllRows();
		Emc_eufar.instrumentTabList.clear();
		Emc_eufar.manufacturerTabList.clear();
		Utilities.runCheckDefault();
		Emc_eufar.verticalPanel17.clear();
		Emc_eufar.useConditionsBox.setText("As EUFAR is an EU-funded project, data in the EUFAR archive are available to everyone. All users are "
				+ "requiered to acknowledge the data providers in any publication based on EUFAR data.");
		Emc_eufar.useLimitationsBox.setText("No limitations");
		Emc_eufar.rootLogger.log(Level.INFO, "Cleaning all fields finished.");
	}
}