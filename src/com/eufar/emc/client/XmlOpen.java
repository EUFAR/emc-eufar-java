package com.eufar.emc.client;

import java.util.logging.Level;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
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
								useLimitation.item(loop).getFirstChild().getFirstChild().getNodeValue(), Emc_eufar.auDelButton1);
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
								otherConstraints.item(loop).getFirstChild().getFirstChild().getNodeValue(), Emc_eufar.auDelButton2);
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
			
			// Data Quality section
			try {
			String lineageText = doc.getElementsByTagName("dq_dataquality").item(0).getFirstChild().getFirstChild()
					.getFirstChild().getFirstChild().getFirstChild().getNodeValue();
				if (lineageText.contains("Atmospheric/In-situ measurements")) {
					((CheckBox) Emc_eufar.insituRad.getWidget(0)).setValue(true);
					GuiModification.changeTarget("Atmospheric/In-situ data");
					String linkText = new String("Link to the procedure's description: ");
					try {
						int linkIndex01 = lineageText.indexOf(linkText);
						int linkIndex02 = lineageText.indexOf("|", linkIndex01 + linkText.length());
						Emc_eufar.insituLinkBox.setValue(lineageText.substring(linkIndex01 + linkText.length(), linkIndex02));
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'URLlink' failed: " + ex.getMessage());}
					String constantsText = new String("Source of calibration constants: ");
					try {
						int linkIndex01 = lineageText.indexOf(constantsText);
						int linkIndex02 = lineageText.indexOf("|", linkIndex01 + constantsText.length());
						Emc_eufar.insituConstBox.setValue(lineageText.substring(linkIndex01 + constantsText.length(), linkIndex02));
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Constantslink' failed: " + ex.getMessage());}
					String materialsText = new String("Source of calibration materials: ");
					try {
						int linkIndex01 = lineageText.indexOf(materialsText);
						int linkIndex02 = lineageText.indexOf("|", linkIndex01 + materialsText.length());
						Emc_eufar.insituMatBox.setValue(lineageText.substring(linkIndex01 + materialsText.length(), linkIndex02));
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Materialslink' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Data converted to geophysical units: ", Emc_eufar.insituChk01Y, Emc_eufar.insituChk01N);	
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'geophysicalUnits' failed: " + ex.getMessage());}
					String formatText = new String("Output format: ");
					try {
						int linkIndex01 = lineageText.indexOf(formatText);
						int linkIndex02 = lineageText.indexOf("|", linkIndex01 + formatText.length());
						if (lineageText.substring(linkIndex01 + formatText.length(), linkIndex02) == "NetCDF") {
							((CheckBox) Emc_eufar.insituChk04.getWidget(0)).setValue(true);
						} else if (lineageText.substring(linkIndex01 + formatText.length(), linkIndex02) == "HDF") {
							((CheckBox) Emc_eufar.insituChk05.getWidget(0)).setValue(true);
						} else if (lineageText.substring(linkIndex01 + formatText.length(), linkIndex02) == "NASA/Ames") {
							((CheckBox) Emc_eufar.insituChk06.getWidget(0)).setValue(true);
						}else if (lineageText.substring(linkIndex01 + formatText.length(), linkIndex02).contains("Other")) {
							((CheckBox) Emc_eufar.insituChk07.getWidget(0)).setValue(true);
							Emc_eufar.horizontalPanel33.add(Emc_eufar.insituImage);
							Emc_eufar.horizontalPanel33.add(Emc_eufar.insituOtherBox);
							Emc_eufar.insituImage.getElement().setAttribute("style", "margin-left: 23px; margin-top: -5px;");
							Emc_eufar.insituOtherBox.setStyleName("identTextBox");
							Emc_eufar.insituOtherBox.getElement().setAttribute("style", "margin-left: 24px !important; margin-top: -5px !important; "
									+ "margin-bottom: 0px !important;");
							Emc_eufar.insituOtherBox.setText(lineageText.substring(linkIndex01 + formatText.length() + 6, linkIndex02));
						}
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'outputFormat' failed: " + ex.getMessage());}
					String flagText = new String("Quality-control flagging applied to individual data points: ");
					try {
						int linkIndex01 = lineageText.indexOf(flagText);
						int linkIndex02 = lineageText.indexOf("|", linkIndex01 + flagText.length());
						Emc_eufar.insituFlagAre.setText(lineageText.substring(linkIndex01 + flagText.length(), linkIndex02));
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'flag' failed: " + ex.getMessage());}
					String assumptionText = new String("Assumption: ");
					try {
						int linkIndex01 = lineageText.indexOf(assumptionText);
						int linkIndex02 = lineageText.indexOf("|", linkIndex01 + assumptionText.length());
						Emc_eufar.insituAssumptionAre.setText(lineageText.substring(linkIndex01 + assumptionText.length(), linkIndex02));
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'assumption' failed: " + ex.getMessage());}
				} else if (lineageText.contains("Earth observation/Remote sensing data")) {
					GuiModification.changeTarget("Earth observation/Remote sensing data");
					((CheckBox) Emc_eufar.imageRad.getWidget(0)).setValue(true);
					String linkText = new String("Name of calibration laboratory: ");
					try {
						int linkIndex01 = lineageText.indexOf(linkText);
						int linkIndex02 = lineageText.indexOf("|", linkIndex01 + linkText.length());
						Emc_eufar.imageCalBox.setValue(lineageText.substring(linkIndex01 + linkText.length(), linkIndex02));
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Laboratory Name' failed: " + ex.getMessage());}
					String radText = new String("Date of radiometric calibration: ");
					try {
						int linkIndex01 = lineageText.indexOf(radText);
						int linkIndex02 = lineageText.indexOf("|", linkIndex01 + radText.length());
						Emc_eufar.imageRadDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(lineageText.substring(linkIndex01 + radText.
								length(), linkIndex02)));
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Radiometric Calibration' failed: " + ex.getMessage());}
					String speText = new String("Date of spectral calibration: ");
					try {
						int linkIndex01 = lineageText.indexOf(speText);
						int linkIndex02 = lineageText.indexOf("|", linkIndex01 + speText.length());
						Emc_eufar.imageSpeDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(lineageText.substring(linkIndex01 + speText.
								length(), linkIndex02)));
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Spectral Calibration' failed: " + ex.getMessage());}
					String bandText = new String("Number of spectral bands: ");
					try {
						int linkIndex01 = lineageText.indexOf(bandText);
						int linkIndex02 = lineageText.indexOf("|", linkIndex01 + bandText.length());
						Emc_eufar.imageBanBox.setValue(lineageText.substring(linkIndex01 + bandText.length(), linkIndex02));
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Spectral Bands' failed: " + ex.getMessage());}
					String headText = new String("Overall heading / fligh direction (dd): ");
					try {
						int linkIndex01 = lineageText.indexOf(headText);
						int linkIndex02 = lineageText.indexOf("|", linkIndex01 + headText.length());
						Emc_eufar.imageDirBox.setValue(lineageText.substring(linkIndex01 + headText.length(), linkIndex02));
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Flight Heading' failed: " + ex.getMessage());}
					String altText = new String("Overall altitude / average height ASL (m): ");
					try {
						int linkIndex01 = lineageText.indexOf(altText);
						int linkIndex02 = lineageText.indexOf("|", linkIndex01 + altText.length());
						Emc_eufar.imageAltBox.setValue(lineageText.substring(linkIndex01 + altText.length(), linkIndex02));
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Flight Altitude' failed: " + ex.getMessage());}
					String zenText = new String("Solar zenith (dd): ");
					try {
						int linkIndex01 = lineageText.indexOf(zenText);
						int linkIndex02 = lineageText.indexOf("|", linkIndex01 + zenText.length());
						Emc_eufar.imageZenBox.setValue(lineageText.substring(linkIndex01 + zenText.length(), linkIndex02));
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Solar Zenith' failed: " + ex.getMessage());}
					String aziText = new String("Solar azimuth (dd): ");
					try {
						int linkIndex01 = lineageText.indexOf(aziText);
						int linkIndex02 = lineageText.indexOf("|", linkIndex01 + aziText.length());
						Emc_eufar.imageAziBox.setValue(lineageText.substring(linkIndex01 + aziText.length(), linkIndex02));
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Solar Azimuth' failed: " + ex.getMessage());}
					String anoText = new String("Report anomalies in data acquisition: ");
					try {
						int linkIndex01 = lineageText.indexOf(anoText);
						int linkIndex02 = lineageText.indexOf("|", linkIndex01 + anoText.length());
						Emc_eufar.imageAnoBox.setValue(lineageText.substring(linkIndex01 + anoText.length(), linkIndex02));
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Anomalies' failed: " + ex.getMessage());}
					String lvlText = new String("Processing level: ");
					try {
						int linkIndex01 = lineageText.indexOf(lvlText);
						int linkIndex02 = lineageText.indexOf("|", linkIndex01 + lvlText.length());
						int indexToFind = -1;
						for (int i = 0; i < Emc_eufar.imageLevLst.getItemCount(); i++) {
							if (Emc_eufar.imageLevLst.getItemText(i).equals(lineageText.substring(linkIndex01 + lvlText.length(), linkIndex02))) {
								indexToFind = i;
								break;
							}
						}
						Emc_eufar.imageLevLst.setSelectedIndex(indexToFind);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Processing Level' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Dark current (DC) correction: ", Emc_eufar.imageChk10Y, Emc_eufar.imageChk10N);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Dark Current' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Aggregated interpolated pixel mask: ", Emc_eufar.imageChk11Y, Emc_eufar.imageChk11N);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Interpolated Pixel' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Aggregated bad pixel mask: ", Emc_eufar.imageChk12Y, Emc_eufar.imageChk12N);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Bad Pixel' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Saturated pixels / overflow: ", Emc_eufar.imageChk13Y, Emc_eufar.imageChk13N);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Saturation' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Pixels affected by saturation in spatial/spectral neighbourhood: ", Emc_eufar.imageChk14Y, 
								Emc_eufar.imageChk14N);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Saturation Neighbourhood' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Problems with position information / Interpolated position information: ", Emc_eufar.imageChk15Y,
								Emc_eufar.imageChk15N);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Position information' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Problems with attitude information / Interpolated attitude information: ", Emc_eufar.imageChk16Y,
								Emc_eufar.imageChk16N);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Attitude information' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Synchronization problems: ", Emc_eufar.imageChk17Y, Emc_eufar.imageChk17N);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Synchronization' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Interpolated pixels during geocoding: ", Emc_eufar.imageChk18Y, Emc_eufar.imageChk18N);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Geocoding' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Failure of atmospheric correction: ", Emc_eufar.imageChk19Y, Emc_eufar.imageChk19N);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Atmospheric Correction' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Cloud mask: ", Emc_eufar.imageChk20Y, Emc_eufar.imageChk20N);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Cloud Mask' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Cloud shadow mask: ", Emc_eufar.imageChk21Y, Emc_eufar.imageChk21N);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Cloud Shadow Mask' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Haze mask: ", Emc_eufar.imageChk22Y, Emc_eufar.imageChk22N);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Haze Mask' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Critical terrain correction based on DEM roughness measure: ", Emc_eufar.imageChk23Y, Emc_eufar.imageChk23N);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'DEM' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Critical terrain correction based on slope/local illumination angle: ", Emc_eufar.imageChk24Y, 
								Emc_eufar.imageChk24N);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'Illumination' failed: " + ex.getMessage());}
					try {
						Utilities.getPosition(lineageText, "Critical BRDF geometry based on sun-sensor-terrain geometry: ", Emc_eufar.imageChk25Y, 
								Emc_eufar.imageChk25N);
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'BRDF' failed: " + ex.getMessage());}
				}
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'dataqualityinfo' failed: " + ex.getMessage());
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
				String aircraftReg = doc.getElementsByTagName("platformregistration").item(0).getFirstChild().getFirstChild().getNodeValue();
				int index = -1;
				for (int i = 0 ; i < Emc_eufar.airAircraftInfo.length ; i++) {
					if (Emc_eufar.airAircraftInfo[i][5].equals(aircraftReg)) {
						index = i;
						break;
					}
				}
				if (index != -1) {
					Emc_eufar.airAircraftLst.setSelectedIndex(index+1);
					GuiModification.aircraftInformation(index+1);
					
				} else {
					String aircraftMan = doc.getElementsByTagName("platformmanufacturer").item(0).getFirstChild().getFirstChild().getNodeValue();
					String aircraftTyp = doc.getElementsByTagName("platformtype").item(0).getFirstChild().getFirstChild().getNodeValue();
					String aircraftOp = doc.getElementsByTagName("platformoperator").item(0).getFirstChild().getFirstChild().getNodeValue();
					String aircraftCount = doc.getElementsByTagName("platformcountry").item(0).getFirstChild().getFirstChild().getNodeValue();
					Emc_eufar.airAircraftLst.setSelectedIndex(1);
					GuiModification.aircraftInformation(1);
					Emc_eufar.airManufacturerBox.setText(aircraftMan);
					Emc_eufar.airTypeBox.setText(aircraftTyp);
					Emc_eufar.airOperatorBox.setText(aircraftOp);
					Emc_eufar.airRegistrationBox.setText(aircraftReg);
					for (int i = 0; i < Emc_eufar.airCountryLst.getItemCount(); i++) {
						if (aircraftCount == Emc_eufar.airCountryLst.getItemText(i)) {
							Emc_eufar.airCountryLst.setSelectedIndex(i);
							break;
						}
					}
				}
			} catch (Exception ex) {
				Emc_eufar.rootLogger.log(Level.WARNING, "Element 'aircraft' failed: " + ex.getMessage());
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
