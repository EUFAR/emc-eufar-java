package com.eufar.emc.client;



import java.util.logging.Level;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
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
				if (value == "publication") {
					Emc_eufar.refPublicationDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(dateValue));
				} else if (value == "revision") {
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
						GuiModification.addUseRead(Emc_eufar.useConditionsAddTab, Emc_eufar.useConditionsLst, 
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
						GuiModification.addUseRead(Emc_eufar.useLimitationsAddTab, Emc_eufar.useLimitationsLst, 
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
		} catch (DOMException ex) {
			Emc_eufar.rootLogger.log(Level.SEVERE, "A problem occured during the loading of the file...", ex);
			Window.alert("Could not parse XML document. A log has been saved on the server for debugging.");
		}
	}
}
