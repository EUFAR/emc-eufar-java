package com.eufar.emc.client;

import java.util.ArrayList;
import java.util.logging.Level;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

public class XmlOpen {
	
	// read the result of the servlet output to populate all fields from the xml code
	public static void readXml(String stringXml) {
		Emc_eufar.rootLogger.log(Level.INFO, "Reading in progress...");
		
		try {
			Document doc = XMLParser.parse(stringXml);
		
			// Indentification Info section
			Node identificationInfo = doc.getElementsByTagName("identificationinfo").item(0);
			
			//// project title
			try {
				NodeList projectTitle = ((Element) identificationInfo).getElementsByTagName("title");
				Emc_eufar.identTitleBox.setText(((Element) projectTitle.item(0)).getElementsByTagName("characterstring").
						item(0).getFirstChild().getNodeValue());
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'title' failed: " + ex.getMessage());
			}
			
			//// identifier
			try {
				NodeList identifier = ((Element) identificationInfo).getElementsByTagName("code");
				Emc_eufar.identIdentifierBox.setText(((Element) identifier.item(0)).getElementsByTagName("characterstring").
						item(0).getFirstChild().getNodeValue());
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'identifier' failed: " + ex.getMessage());
			}
			
			//// dates
			NodeList citation = ((Element) identificationInfo).getElementsByTagName("citation");
			NodeList dates = ((Element) citation.item(0)).getElementsByTagName("ci_date");
			for (int loop = 0; loop < dates.getLength(); loop++) {
				String value = ((Element) dates.item(loop)).getElementsByTagName("ci_datetypecode").
						item(0).getFirstChild().getNodeValue();
				String dateValue = dates.item(loop).getFirstChild().getFirstChild().getFirstChild().getNodeValue();
				if (value == "revision") {
					Emc_eufar.refRevisionDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(dateValue));
				} else if (value == "creation") {
					Emc_eufar.refCreationDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(dateValue));
				}
			}
			
			//// project abstract
			try {
				NodeList projectAbstract = ((Element) identificationInfo).getElementsByTagName("abstract");
				Emc_eufar.identAbstractAre.setText(((Element) projectAbstract.item(0)).getElementsByTagName("characterstring").
						item(0).getFirstChild().getNodeValue());
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'abstract' failed: " + ex.getMessage());
			}
			
			//// project language
			try {
				NodeList projectLanguage = ((Element) identificationInfo).getElementsByTagName("languagecode");
				Utilities.checkList(Emc_eufar.languageMap, projectLanguage.item(0).getFirstChild().getNodeValue(), Emc_eufar.identLanguageLst);
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'language' failed: " + ex.getMessage());
			}
			
			//// topic categories
			try {
				NodeList categories = ((Element) identificationInfo).getElementsByTagName("md_topiccategorycode");
				for (int loop = 0; loop < categories.getLength(); loop++) {
					Utilities.checkBox(Emc_eufar.clScroll, Emc_eufar.categoriesMap, categories.item(loop).getFirstChild().getNodeValue());
				}
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'topic categories' failed: " + ex.getMessage());
			}

			//// keywords
			try {
				NodeList keywords = ((Element) identificationInfo).getElementsByTagName("keyword");
				for (int loop = 0; loop < keywords.getLength(); loop++) {
					Utilities.checkBox(Emc_eufar.kwScroll, Emc_eufar.keywordsMap, keywords.item(loop).
							getFirstChild().getFirstChild().getNodeValue());
				}
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'keyword' failed: " + ex.getMessage());
			}
			
			//// geographic extent - region
			try {
				String description = ((Element) identificationInfo).getElementsByTagName("description").
						item(0).getFirstChild().getFirstChild().getNodeValue();
				if (Emc_eufar.continentList.contains(description)) {
					int index = Emc_eufar.continentList.indexOf(description);
					Utilities.geoLocationSet(1);
					Emc_eufar.geoDetailLst.setSelectedIndex(index);
					Emc_eufar.geoLocationLst.setSelectedIndex(1);
				} else if (Emc_eufar.countryList.contains(description)) {
					int index = Emc_eufar.countryList.indexOf(description);
					Utilities.geoLocationSet(2);
					Emc_eufar.geoDetailLst.setSelectedIndex(index);
					Emc_eufar.geoLocationLst.setSelectedIndex(2);
				} else if (Emc_eufar.oceanList.contains(description)) {
					int index = Emc_eufar.oceanList.indexOf(description);
					Utilities.geoLocationSet(3);
					Emc_eufar.geoDetailLst.setSelectedIndex(index);
					Emc_eufar.geoLocationLst.setSelectedIndex(3);
				} else if (Emc_eufar.regionList.contains(description)) {
					int index = Emc_eufar.regionList.indexOf(description);
					Utilities.geoLocationSet(4);
					Emc_eufar.geoDetailLst.setSelectedIndex(index);
					Emc_eufar.geoLocationLst.setSelectedIndex(4);
				}
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'extent - region' failed: " + ex.getMessage());
			}
			
			//// geographic extent - coordinates
			try {
				Emc_eufar.geoWestBox.setText(((Element) identificationInfo).getElementsByTagName("westboundlongitude")
						.item(0).getFirstChild().getFirstChild().getNodeValue());
					
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'extent - west coord' failed: " + ex.getMessage());
			}
			try {
				Emc_eufar.geoEastBox.setText(((Element) identificationInfo).getElementsByTagName("eastboundlongitude")
						.item(0).getFirstChild().getFirstChild().getNodeValue());
					
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'extent - east coord' failed: " + ex.getMessage());
			}	
			try {
				Emc_eufar.geoNorthBox.setText(((Element) identificationInfo).getElementsByTagName("northboundlatitude")
						.item(0).getFirstChild().getFirstChild().getNodeValue());
					
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'extent - north coord' failed: " + ex.getMessage());
			}	
			try {
				Emc_eufar.geoSouthBox.setText(((Element) identificationInfo).getElementsByTagName("southboundlatitude")
						.item(0).getFirstChild().getFirstChild().getNodeValue());
					
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'extent - south coord' failed: " + ex.getMessage());
			}	
				
			//// temporal extent
			try {
				NodeList periodStart = ((Element) identificationInfo).getElementsByTagName("beginposition");
				NodeList periodEnd = ((Element) identificationInfo).getElementsByTagName("endposition");
				for (int loop = 0; loop < periodStart.getLength(); loop++) {
					if (loop == 0) {
						Emc_eufar.refStartDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(periodStart
								.item(loop).getFirstChild().getNodeValue()));
						Emc_eufar.refEndDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(periodEnd
								.item(loop).getFirstChild().getNodeValue()));
					} else {
						GuiModification.addRefRead(periodStart.item(loop).getFirstChild().getNodeValue(), periodEnd
								.item(loop).getFirstChild().getNodeValue());
					}
				}
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'extent - temporal' failed: " + ex.getMessage());
			}
			
			//// spatial resolution
			try {
				NodeList resolution = ((Element) identificationInfo).getElementsByTagName("md_resolution");
				if (resolution.item(0).getFirstChild().getNodeName().startsWith("gmd:equivalentscale")) {
					Emc_eufar.geoResolutionBox.setText(resolution.item(0).getFirstChild().getFirstChild()
							.getFirstChild().getFirstChild().getFirstChild().getNodeValue());
				} else if (resolution.item(0).getFirstChild().getNodeName().startsWith("gmd:distance")) {
					Emc_eufar.geoResolutionLst.setSelectedIndex(1);
					GuiModification.geoResolutionSet(1);
					Emc_eufar.geoResolutionBox.setText(resolution.item(0).getFirstChild().getFirstChild().getFirstChild().getNodeValue());
					Utilities.checkList(Emc_eufar.unitMap, ((Element) resolution.item(0).getFirstChild().getFirstChild())
							.getAttribute("uom"), Emc_eufar.geoUnitLst);
				} 
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'spatialresolution' failed: " + ex.getMessage());
			}
			
			//// resource constraints
			try {
				NodeList useLimitation = ((Element) identificationInfo).getElementsByTagName("uselimitation");
				for (int loop = 0; loop < useLimitation.getLength(); loop++) {
					if (loop == 0) {
						Emc_eufar.useConditionsBox.setText(useLimitation.item(loop).getFirstChild().getFirstChild().getNodeValue());
					} else {
						GuiModification.addUseRead(Emc_eufar.useConditionsAddTab, Emc_eufar.useConditionsLst, Emc_eufar.useCondCorrectLst,
								useLimitation.item(loop).getFirstChild().getFirstChild().getNodeValue(), Emc_eufar.useDelImage1);
					}
				}
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'resource constraints' failed: " + ex.getMessage());
			}
			try {
				NodeList otherConstraints = ((Element) identificationInfo).getElementsByTagName("otherconstraints");
				for (int loop = 0; loop < otherConstraints.getLength(); loop++) {
					if (loop == 0) {
						Emc_eufar.useLimitationsBox.setText(otherConstraints.item(loop).getFirstChild().getFirstChild().getNodeValue());
					} else {
						GuiModification.addUseRead(Emc_eufar.useLimitationsAddTab, Emc_eufar.useLimitationsLst, Emc_eufar.useLimCorrectLst,
								otherConstraints.item(loop).getFirstChild().getFirstChild().getNodeValue(), Emc_eufar.useDelImage2);
					}
				}
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'resource constraints' failed: " + ex.getMessage());
			}
			
			//// Point of contact - data
			try {
				NodeList responsibleParty = ((Element) identificationInfo).getElementsByTagName("ci_responsibleparty");
				for (int loop = 0; loop < responsibleParty.getLength(); loop++) {
					String organisationName = ((Element) responsibleParty.item(loop)).getElementsByTagName("organisationname").
							item(0).getFirstChild().getFirstChild().getNodeValue();
					String electronicEmail = ((Element) responsibleParty.item(loop)).getElementsByTagName("electronicmailaddress").
							item(0).getFirstChild().getFirstChild().getNodeValue();
					String roleCode = ((Element) responsibleParty.item(loop)).getElementsByTagName("ci_rolecode").
							item(0).getFirstChild().getNodeValue();
					if (loop == 0) {
						Emc_eufar.orgPartyBox.setText(organisationName);
						Emc_eufar.orgEmailBox.setText(electronicEmail);
						Utilities.checkList(Emc_eufar.roleMap, roleCode, Emc_eufar.orgRoleLst);
					} else {
						GuiModification.addOrgRead(organisationName, electronicEmail, roleCode);
					}
				}
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'pointofcontact - data' failed: " + ex.getMessage());
			}
			
			// Indentification Info section
			try {
				Node hierarchyLevel = doc.getElementsByTagName("hierarchylevel").item(0);
				Utilities.checkList(Emc_eufar.typeMap, hierarchyLevel.getFirstChild().getFirstChild().
						getNodeValue(), Emc_eufar.identTypeLst);
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'hierarchylevel' failed: " + ex.getMessage());
			}
			
			// Distribution Info section
			try {
				Node distributionInfo = doc.getElementsByTagName("ci_onlineresource").item(0);
				Emc_eufar.identLocatorBox.setText(distributionInfo.getFirstChild().getFirstChild().getFirstChild()
						.getNodeValue());
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'distributioninfo' failed: " + ex.getMessage());
			}
			
			// Language section
			try {
				String language = doc.getElementsByTagName("languagecode").item(1).getFirstChild().getNodeValue();
				Utilities.checkList(Emc_eufar.languageMap, language, Emc_eufar.metLanguageLst);
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'distributioninfo' failed: " + ex.getMessage());
			}
			
			// Date section
			try {
				String date = doc.getElementsByTagName("datestamp").item(0).getFirstChild().getFirstChild().getNodeValue();
				Emc_eufar.metDateDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(date));
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'date' failed: " + ex.getMessage());
			}
			
			
			// Contact - metadata section
			try {
				NodeList contact = doc.getElementsByTagName("contact");
				int id = 0;
				for (int loop = 0; loop < contact.getLength(); loop++) {
					if (contact.item(loop).getParentNode().getNodeName() == "gmd:md_metadata") {
						String organisationName = ((Element) contact.item(loop)).getElementsByTagName("organisationname")
								.item(0).getFirstChild().getFirstChild().getNodeValue();
						String eletronicMail = ((Element) contact.item(loop)).getElementsByTagName("electronicmailaddress")
								.item(0).getFirstChild().getFirstChild().getNodeValue();
						if (id == 0) {
							Emc_eufar.metNameBox.setText(organisationName);
							Emc_eufar.metEmailBox.setText(eletronicMail);
							id++;
						} else {
							GuiModification.addMetRead(organisationName, eletronicMail);
						}
					}
				}
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'contact - metadata' failed: " + ex.getMessage());
			}
			
			// Aircraft section
			try {
				NodeList manufacturers = doc.getElementsByTagName("platformmanufacturer");
				NodeList types = doc.getElementsByTagName("platformtype");
				NodeList operators = doc.getElementsByTagName("platformoperator");
				NodeList countries = doc.getElementsByTagName("platformcountry");
				NodeList registrations = doc.getElementsByTagName("platformregistration");
				for (int loop = 0; loop < manufacturers.getLength(); loop++) {
					String aircraftMan = manufacturers.item(loop).getFirstChild().getFirstChild().getNodeValue();
					String aircraftTyp = types.item(loop).getFirstChild().getFirstChild().getNodeValue();
					String aircraftOpe = operators.item(loop).getFirstChild().getFirstChild().getNodeValue();
					String aircraftCnt = countries.item(loop).getFirstChild().getFirstChild().getNodeValue();
					String aircraftReg = registrations.item(loop).getFirstChild().getFirstChild().getNodeValue();
					GuiModification.addAircraftRead(aircraftTyp, aircraftMan, aircraftOpe, aircraftCnt, aircraftReg);
				}
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'platform' failed: " + ex.getMessage());
			}

			
			// Instrument section
			try {
				NodeList manufacturers = doc.getElementsByTagName("instrumentmanufacturer");
				NodeList types = doc.getElementsByTagName("instrumenttype");
				for (int loop = 0; loop < manufacturers.getLength(); loop++) {
					String instrumentMan = manufacturers.item(loop).getFirstChild().getFirstChild().getNodeValue();
					String instrumentTyp = types.item(loop).getFirstChild().getFirstChild().getNodeValue();
					if (instrumentTyp != "") {
						GuiModification.addInstRead(instrumentTyp, instrumentMan);
					}
				}
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'instrument' failed: " + ex.getMessage());
			}
			
			
			// Data Quality section
			boolean oldQV = false;
			try {
				String lineageText = doc.getElementsByTagName("dq_dataquality").item(0).getFirstChild().getFirstChild()
						.getFirstChild().getFirstChild().getFirstChild().getNodeValue();
				ArrayList<String> stringList = new ArrayList<String>();
				int indexStart = lineageText.indexOf("[");
				int indexEnd = lineageText.indexOf("]");
				stringList.add(lineageText.substring(indexStart + 1, indexEnd));
				if (indexEnd == -1) {
					oldQV = true;
				} else {
					while (indexEnd >= 0) {
						indexStart = lineageText.indexOf("[", indexStart + 1);
						indexEnd = lineageText.indexOf("]", indexEnd + 1);
						stringList.add(lineageText.substring(indexStart + 1, indexEnd));
					}
				}
				stringList.remove(stringList.size() - 1);
				if (oldQV == true) {
					if (lineageText.contains("Atmospheric")) {
						GuiModification.addQvTab("insitu");
						readInsituCode_Old(lineageText);
					} else if (lineageText.contains("Earth")) {
						GuiModification.addQvTab("imagery");
						readImageryCode_Old(lineageText);
					}
				} else {
					int insituIndex = 0;
					int imageryIndex = 0;
					for (int i = 0; i < stringList.size(); i++) {
						String subString = stringList.get(i);
						if (subString.contains("Atmospheric")) {
							GuiModification.addQvTab("insitu");
							readInsituCode(subString, insituIndex);
							insituIndex++;
						} else if (subString.contains("Earth")) {
							GuiModification.addQvTab("imagery");
							readImageryCode(subString, imageryIndex);
							imageryIndex++;
						}
					}
				}
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'dataqualityinfo' failed: " + ex.getMessage());
			}
			if (oldQV == true) {
				PopupMessages.oldQVPanel();
			}
		} catch (DOMException ex) {
			Emc_eufar.rootLogger.log(Level.SEVERE, "A problem occured during the loading of the file...", ex);
			Window.alert("Could not parse XML document. A log has been saved on the server for debugging.");
		}
	}
	
	/// read the string containing all QV xml code for in-situ and imagery data
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void readInsituCode(String string, int mapIndex) {
		Emc_eufar.rootLogger.log(Level.INFO, "Reading of the In-situ string invoked");
		ArrayList<ArrayList> insituAllLists = Emc_eufar.qvInsituMap.get(mapIndex);
		ArrayList<TextBoxBase> allTextBoxes = insituAllLists.get(0);
		ArrayList<ListBox> allListBoxes = insituAllLists.get(3);
		ArrayList<HorizontalPanel> allRadioButtons = insituAllLists.get(1);
		ArrayList<HorizontalPanel> allCheckBoxes = insituAllLists.get(2);
		ArrayList<Image> allImages = insituAllLists.get(5);
		
		/// collect all substrings
		ArrayList<String> stringList = new ArrayList<String>();
		int indexStart = string.indexOf(":");
		int indexEnd = string.indexOf("|", indexStart);
		stringList.add(string.substring(indexStart + 2, indexEnd));
		while (indexEnd >= 0) {
			indexStart = string.indexOf(":", indexStart + 1);
			indexEnd = string.indexOf("|", indexEnd + 1);
			stringList.add(string.substring(indexStart + 2, indexEnd));
		}
		stringList.remove(stringList.size() - 1);
		stringList.add(string.substring(indexStart + 2, string.length()));

		/// instrument
		if (stringList.get(0) != "") {
			for (int i = 0; i < allListBoxes.get(0).getItemCount(); i++) {
				if (stringList.get(0) == allListBoxes.get(0).getItemText(i)) {
					allListBoxes.get(0).setSelectedIndex(i);
					break;
				}
			}
		}
		
		/// procedures
		allTextBoxes.get(0).setText(stringList.get(1));
		allTextBoxes.get(1).setText(stringList.get(2));
		allTextBoxes.get(2).setText(stringList.get(3));
		
		/// geophysical units
		Utilities.getPosition(stringList.get(4), 
				(HorizontalPanel) allRadioButtons.get(0).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(0).getWidget(1));
		
		/// output format
		String outputString = stringList.get(5);
		VerticalPanel verticalPanel01 = (VerticalPanel) allCheckBoxes.get(0).getWidget(0);
		VerticalPanel verticalPanel02 = (VerticalPanel) allCheckBoxes.get(0).getWidget(1);
		HorizontalPanel netcdfCheckBox = (HorizontalPanel) verticalPanel01.getWidget(0);
		HorizontalPanel hdfCheckBox = (HorizontalPanel) verticalPanel01.getWidget(1);
		HorizontalPanel amesCheckBox = (HorizontalPanel) verticalPanel02.getWidget(0);
		HorizontalPanel otherPanel = (HorizontalPanel) verticalPanel02.getWidget(1);
		HorizontalPanel otherCheckBox = (HorizontalPanel) otherPanel.getWidget(0);
		if (outputString.contains("NetCDF")) {
			((CheckBox) netcdfCheckBox.getWidget(0)).setValue(true);
		}
		if (outputString.contains("HDF")) {
			((CheckBox) hdfCheckBox.getWidget(0)).setValue(true);
		}
		if (outputString.contains("NASA/Ames")) {
			((CheckBox) amesCheckBox.getWidget(0)).setValue(true);
		}
		if (outputString.contains("Other/")) {
			((CheckBox) otherCheckBox.getWidget(0)).setValue(true);
			allImages.get(0).setVisible(true);
			allImages.get(0).setPixelSize(20, 12);
			allTextBoxes.get(3).setVisible(true);
			int index = outputString.indexOf("Other/");
			allTextBoxes.get(3).setText(outputString.substring(index + 6));
		}
		
		/// flag
		allTextBoxes.get(4).setText(stringList.get(6));
		
		/// assumption
		allTextBoxes.get(5).setText(stringList.get(7));
		Emc_eufar.rootLogger.log(Level.INFO, "Reading of the In-situ string finished");
	}
	
	
	/// read the string containing all QV xml code for in-situ and imagery data
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void readImageryCode(String string, int mapIndex) {
		Emc_eufar.rootLogger.log(Level.INFO, "Reading of the Imagery string invoked");
		ArrayList<ArrayList> imageryAllLists = Emc_eufar.qvImageryMap.get(mapIndex);
		ArrayList<TextBoxBase> allTextBoxes = imageryAllLists.get(0);
		ArrayList<ListBox> allListBoxes = imageryAllLists.get(2);
		ArrayList<HorizontalPanel> allRadioButtons = imageryAllLists.get(1);
		ArrayList<DateBox> allDateBoxes = imageryAllLists.get(3);
		
		/// collect all substrings
		ArrayList<String> stringList = new ArrayList<String>();
		int indexStart = string.indexOf(":");
		int indexEnd = string.indexOf("|", indexStart);
		stringList.add(string.substring(indexStart + 2, indexEnd));
		while (indexEnd >= 0) {
			indexStart = string.indexOf(":", indexStart + 1);
			indexEnd = string.indexOf("|", indexEnd + 1);
			stringList.add(string.substring(indexStart + 2, indexEnd));
		}
		stringList.remove(stringList.size() - 1);
		stringList.add(string.substring(indexStart + 2, string.length()));
				
		/// instrument
		if (stringList.get(0) != "") {
			for (int i = 0; i < allListBoxes.get(0).getItemCount(); i++) {
				if (stringList.get(0) == allListBoxes.get(0).getItemText(i)) {
					allListBoxes.get(0).setSelectedIndex(i);
					break;
				}
			}
		}
		
		/// calibration information
		allTextBoxes.get(0).setText(stringList.get(1));
		allDateBoxes.get(0).setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(stringList.get(2)));
		allDateBoxes.get(1).setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(stringList.get(3)));
		
		/// acquisition information
		allTextBoxes.get(1).setText(stringList.get(4));
		allTextBoxes.get(2).setText(stringList.get(5));
		allTextBoxes.get(3).setText(stringList.get(6));
		allTextBoxes.get(4).setText(stringList.get(7));
		allTextBoxes.get(5).setText(stringList.get(8));
		allTextBoxes.get(6).setText(stringList.get(9));
		
		/// processing information
		if (stringList.get(10) != "") {
			for (int i = 0; i < allListBoxes.get(1).getItemCount(); i++) {
				if (stringList.get(10) == allListBoxes.get(1).getItemText(i)) {
					allListBoxes.get(1).setSelectedIndex(i);
					break;
				}
			}
		}
		Utilities.getPosition(stringList.get(11), 
				(HorizontalPanel) allRadioButtons.get(0).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(0).getWidget(1));
		
		/// data quality layers
		Utilities.getPosition(stringList.get(12), 
				(HorizontalPanel) allRadioButtons.get(1).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(1).getWidget(1));
		Utilities.getPosition(stringList.get(13), 
				(HorizontalPanel) allRadioButtons.get(2).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(2).getWidget(1));
		Utilities.getPosition(stringList.get(14), 
				(HorizontalPanel) allRadioButtons.get(3).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(3).getWidget(1));
		Utilities.getPosition(stringList.get(15), 
				(HorizontalPanel) allRadioButtons.get(4).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(4).getWidget(1));
		Utilities.getPosition(stringList.get(16), 
				(HorizontalPanel) allRadioButtons.get(5).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(5).getWidget(1));
		Utilities.getPosition(stringList.get(17), 
				(HorizontalPanel) allRadioButtons.get(6).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(6).getWidget(1));
		Utilities.getPosition(stringList.get(18), 
				(HorizontalPanel) allRadioButtons.get(7).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(7).getWidget(1));
		Utilities.getPosition(stringList.get(19), 
				(HorizontalPanel) allRadioButtons.get(8).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(8).getWidget(1));
		Utilities.getPosition(stringList.get(20), 
				(HorizontalPanel) allRadioButtons.get(9).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(9).getWidget(1));
		Utilities.getPosition(stringList.get(21), 
				(HorizontalPanel) allRadioButtons.get(10).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(10).getWidget(1));
		Utilities.getPosition(stringList.get(22), 
				(HorizontalPanel) allRadioButtons.get(11).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(11).getWidget(1));
		Utilities.getPosition(stringList.get(23), 
				(HorizontalPanel) allRadioButtons.get(12).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(12).getWidget(1));
		Utilities.getPosition(stringList.get(24), 
				(HorizontalPanel) allRadioButtons.get(13).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(13).getWidget(1));
		Utilities.getPosition(stringList.get(25), 
				(HorizontalPanel) allRadioButtons.get(14).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(14).getWidget(1));
		Utilities.getPosition(stringList.get(26), 
				(HorizontalPanel) allRadioButtons.get(15).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(15).getWidget(1));
		Emc_eufar.rootLogger.log(Level.INFO, "Reading of the Imagery string finished");
	}
	
	
	/// read the string containing all QV xml code for in-situ and imagery data
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void readInsituCode_Old(String string) {
		Emc_eufar.rootLogger.log(Level.INFO, "Reading of the old In-situ string invoked");
		ArrayList<ArrayList> insituAllLists = Emc_eufar.qvInsituMap.get(0);
		ArrayList<TextBoxBase> allTextBoxes = insituAllLists.get(0);
		ArrayList<HorizontalPanel> allRadioButtons = insituAllLists.get(1);
		ArrayList<HorizontalPanel> allCheckBoxes = insituAllLists.get(2);
		ArrayList<Image> allImages = insituAllLists.get(5);
		
		/// collect all substrings
		ArrayList<String> stringList = new ArrayList<String>();
		int indexStart = string.indexOf(":");
		int indexEnd = string.indexOf("|", indexStart);
		stringList.add(string.substring(indexStart + 2, indexEnd));
		while (indexEnd >= 0) {
			indexStart = string.indexOf(":", indexStart + 1);
			indexEnd = string.indexOf("|", indexEnd + 1);
			stringList.add(string.substring(indexStart + 2, indexEnd));
		}
		stringList.remove(stringList.size() - 1);
		stringList.add(string.substring(indexStart + 2, string.length()));
		
		/// procedures
		allTextBoxes.get(0).setText(stringList.get(0));
		allTextBoxes.get(1).setText(stringList.get(1));
		allTextBoxes.get(2).setText(stringList.get(2));
				
		/// geophysical units
		Utilities.getPosition(stringList.get(3), 
				(HorizontalPanel) allRadioButtons.get(0).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(0).getWidget(1));
				
		/// output format
		String outputString = stringList.get(4);
		VerticalPanel verticalPanel01 = (VerticalPanel) allCheckBoxes.get(0).getWidget(0);
		VerticalPanel verticalPanel02 = (VerticalPanel) allCheckBoxes.get(0).getWidget(1);
		HorizontalPanel netcdfCheckBox = (HorizontalPanel) verticalPanel01.getWidget(0);
		HorizontalPanel hdfCheckBox = (HorizontalPanel) verticalPanel01.getWidget(1);
		HorizontalPanel amesCheckBox = (HorizontalPanel) verticalPanel02.getWidget(0);
		HorizontalPanel otherPanel = (HorizontalPanel) verticalPanel02.getWidget(1);
		HorizontalPanel otherCheckBox = (HorizontalPanel) otherPanel.getWidget(0);
		if (outputString.contains("NetCDF")) {
			((CheckBox) netcdfCheckBox.getWidget(0)).setValue(true);
		}
		if (outputString.contains("HDF")) {
			((CheckBox) hdfCheckBox.getWidget(0)).setValue(true);
		}
		if (outputString.contains("NASA/Ames")) {
			((CheckBox) amesCheckBox.getWidget(0)).setValue(true);
		}
		if (outputString.contains("Other/")) {
			((CheckBox) otherCheckBox.getWidget(0)).setValue(true);
			allImages.get(0).setVisible(true);
			allImages.get(0).setPixelSize(20, 12);
			allTextBoxes.get(3).setVisible(true);
			int index = outputString.indexOf("Other/");
			allTextBoxes.get(3).setText(outputString.substring(index + 6));
		}
				
		/// flag
		allTextBoxes.get(4).setText(stringList.get(5));
				
		/// assumption
		allTextBoxes.get(5).setText(stringList.get(6));
		Emc_eufar.rootLogger.log(Level.INFO, "Reading of old the In-situ string finished");
	}
	
	
	/// read the string containing all QV xml code for in-situ and imagery data
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void readImageryCode_Old(String string) {
		Emc_eufar.rootLogger.log(Level.INFO, "Reading of the old Imagery string invoked");
		ArrayList<ArrayList> imageryAllLists = Emc_eufar.qvImageryMap.get(0);
		ArrayList<TextBoxBase> allTextBoxes = imageryAllLists.get(0);
		ArrayList<ListBox> allListBoxes = imageryAllLists.get(2);
		ArrayList<HorizontalPanel> allRadioButtons = imageryAllLists.get(1);
		ArrayList<DateBox> allDateBoxes = imageryAllLists.get(3);

		/// collect all substrings
		ArrayList<String> stringList = new ArrayList<String>();
		int indexStart = string.indexOf(":");
		int indexEnd = string.indexOf("|", indexStart);
		stringList.add(string.substring(indexStart + 2, indexEnd));
		while (indexEnd >= 0) {
			indexStart = string.indexOf(":", indexStart + 1);
			indexEnd = string.indexOf("|", indexEnd + 1);
			stringList.add(string.substring(indexStart + 2, indexEnd));
		}
		stringList.remove(stringList.size() - 1);
		stringList.add(string.substring(indexStart + 2, string.length()));
			
		/// calibration information
		allTextBoxes.get(0).setText(stringList.get(0));
		allDateBoxes.get(0).setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(stringList.get(1)));
		allDateBoxes.get(1).setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(stringList.get(2)));
			
		/// acquisition information
		allTextBoxes.get(1).setText(stringList.get(3));
		allTextBoxes.get(2).setText(stringList.get(4));
		allTextBoxes.get(3).setText(stringList.get(5));
		allTextBoxes.get(4).setText(stringList.get(6));
		allTextBoxes.get(5).setText(stringList.get(7));
		allTextBoxes.get(6).setText(stringList.get(8));
			
		/// processing information
		if (stringList.get(9) != "") {
			for (int i = 0; i < allListBoxes.get(1).getItemCount(); i++) {
				if (stringList.get(9) == allListBoxes.get(1).getItemText(i)) {
					allListBoxes.get(1).setSelectedIndex(i);
					break;
				}
			}
		}
		Utilities.getPosition(stringList.get(10), 
				(HorizontalPanel) allRadioButtons.get(0).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(0).getWidget(1));
			
		/// data quality layers
		Utilities.getPosition(stringList.get(11), 
				(HorizontalPanel) allRadioButtons.get(1).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(1).getWidget(1));
		Utilities.getPosition(stringList.get(12), 
				(HorizontalPanel) allRadioButtons.get(2).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(2).getWidget(1));
		Utilities.getPosition(stringList.get(13), 
				(HorizontalPanel) allRadioButtons.get(3).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(3).getWidget(1));
		Utilities.getPosition(stringList.get(14), 
				(HorizontalPanel) allRadioButtons.get(4).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(4).getWidget(1));
		Utilities.getPosition(stringList.get(15), 
				(HorizontalPanel) allRadioButtons.get(5).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(5).getWidget(1));
		Utilities.getPosition(stringList.get(16), 
				(HorizontalPanel) allRadioButtons.get(6).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(6).getWidget(1));
		Utilities.getPosition(stringList.get(17), 
				(HorizontalPanel) allRadioButtons.get(7).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(7).getWidget(1));
		Utilities.getPosition(stringList.get(18), 
				(HorizontalPanel) allRadioButtons.get(8).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(8).getWidget(1));
		Utilities.getPosition(stringList.get(19), 
				(HorizontalPanel) allRadioButtons.get(9).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(9).getWidget(1));
		Utilities.getPosition(stringList.get(20), 
				(HorizontalPanel) allRadioButtons.get(10).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(10).getWidget(1));
		Utilities.getPosition(stringList.get(21), 
				(HorizontalPanel) allRadioButtons.get(11).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(11).getWidget(1));
		Utilities.getPosition(stringList.get(22), 
				(HorizontalPanel) allRadioButtons.get(12).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(12).getWidget(1));
		Utilities.getPosition(stringList.get(23), 
				(HorizontalPanel) allRadioButtons.get(13).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(13).getWidget(1));
		Utilities.getPosition(stringList.get(24), 
				(HorizontalPanel) allRadioButtons.get(14).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(14).getWidget(1));
		Utilities.getPosition(stringList.get(25), 
				(HorizontalPanel) allRadioButtons.get(15).getWidget(0), 
				(HorizontalPanel) allRadioButtons.get(15).getWidget(1));
		Emc_eufar.rootLogger.log(Level.INFO, "Reading of the old Imagery string finished");
	}
}
