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

public class xmlOpen {
	

	// read the result of the servlet output to populate all fields from the xml code
	public static void readXml(String stringXml) {
		Emc_eufar.rootLogger.log(Level.INFO, "Reading in progress in progress...");
		int periodNumber = 0;
		int useConditionNumber = 0;
		int accessLimitationNumber = 0;
		int responsibleParty = 0;
		int metadataContact = 0;
		try {
			Document doc = XMLParser.parse(stringXml);
			NodeList docChildNodes = doc.getChildNodes().item(0).getChildNodes();
			for (int aa = 0; aa < docChildNodes.getLength(); aa++) {
				if (docChildNodes.item(aa).getNodeName().startsWith("gmd:identificationinfo")) {
					Node dataIdentificationMD = docChildNodes.item(aa);
					NodeList dataIdentification = dataIdentificationMD.getChildNodes().item(0).getChildNodes();
					for (int bb = 0; bb < dataIdentification.getLength(); bb++) {
						if (dataIdentification.item(bb).getNodeName().startsWith("gmd:citation")) {
							NodeList citation = dataIdentification.item(bb).getChildNodes().item(0).getChildNodes();
							for (int cc = 0; cc < citation.getLength(); cc++) {
								if (citation.item(cc).getNodeName().startsWith("gmd:title")) {
									try {Emc_eufar.identTitleBox.setText(citation.item(cc).getFirstChild().getFirstChild().getNodeValue());}
									catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'title' failed: " + ex.getMessage());}
								}
								if (citation.item(cc).getNodeName().startsWith("gmd:identifier")) {
									try {
										Emc_eufar.identIdentifierBox.setText(citation.item(cc).getFirstChild().getFirstChild().getFirstChild().
												getFirstChild().getNodeValue());
									} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'identifier' failed: " + ex.getMessage());}
								}
								if (citation.item(cc).getNodeName().startsWith("gmd:date")) {
									Node dateType = citation.item(cc).getFirstChild().getLastChild().getFirstChild();
									String dateValue = citation.item(cc).getFirstChild().getFirstChild().getFirstChild().getFirstChild().getNodeValue();
									String dateTypeCode = ((Element)dateType).getAttribute("codelistvalue");
									if (dateTypeCode == "revision") {
										Emc_eufar.refRevisionDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(dateValue));
									} else if (dateTypeCode == "creation") {
										Emc_eufar.refCreationDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(dateValue));
									} else if (dateTypeCode =="publication") {
										Emc_eufar.refPublicationDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(dateValue));
									}
								}
							}
						}
						if (dataIdentification.item(bb).getNodeName().startsWith("gmd:abstract")) {
							try {Emc_eufar.identAbstractAre.setText(dataIdentification.item(bb).getFirstChild().getFirstChild().getNodeValue());}
							catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'abstract' failed: " + ex.getMessage());}
						}
						if (dataIdentification.item(bb).getNodeName().startsWith("gmd:language")) {
							Utilities.checkList(Emc_eufar.languageMap, dataIdentification.item(bb).getFirstChild().getFirstChild().getNodeValue(), Emc_eufar.identLanguageLst);
						}
						if (dataIdentification.item(bb).getNodeName().startsWith("gmd:topiccategory")) {
							Utilities.checkBox(Emc_eufar.clScroll, Emc_eufar.categoriesMap, dataIdentification.item(bb).getFirstChild().getFirstChild().getNodeValue());
						}
						if (dataIdentification.item(bb).getNodeName().startsWith("gmd:descriptivekeywords")) {
							NodeList keywords = dataIdentification.item(bb).getChildNodes().item(0).getChildNodes();
							for (int dd = 0; dd < keywords.getLength(); dd++) {
								if (keywords.item(dd).getNodeName().startsWith("gmd:keyword")) {
									Utilities.checkBox(Emc_eufar.kwScroll, Emc_eufar.keywordsMap, keywords.item(dd).getFirstChild().getFirstChild().getNodeValue());
								}
							}
						}
						if (dataIdentification.item(bb).getNodeName().startsWith("gmd:extent")) {
							NodeList extents = dataIdentification.item(bb).getChildNodes().item(0).getChildNodes();
							for (int ee = 0; ee < extents.getLength(); ee++) {
								if (extents.item(ee).getNodeName().startsWith("gmd:description")) {
									try {
										String description = extents.item(ee).getFirstChild().getFirstChild().getNodeValue();
										for (int i = 0; i < Emc_eufar.continentList.size(); i++) {
											if (description == Emc_eufar.continentList.get(i)) {
												Utilities.geoLocationSet(1);
												Emc_eufar.geoDetailLst.setSelectedIndex(i);
												Emc_eufar.geoLocationLst.setSelectedIndex(1);
												break;
											}
										}
										for (int i = 0; i < Emc_eufar.countryList.size(); i++) {
											if (description == Emc_eufar.countryList.get(i)) {
												Utilities.geoLocationSet(2);
												Emc_eufar.geoDetailLst.setSelectedIndex(i);
												Emc_eufar.geoLocationLst.setSelectedIndex(2);
												break;
											}
										}
										for (int i = 0; i < Emc_eufar.oceanList.size(); i++) {
											if (description == Emc_eufar.oceanList.get(i)) {
												Utilities.geoLocationSet(3);
												Emc_eufar.geoDetailLst.setSelectedIndex(i);
												Emc_eufar.geoLocationLst.setSelectedIndex(3);
												break;
											}
										}
										for (int i = 0; i < Emc_eufar.regionList.size(); i++) {
											if (description == Emc_eufar.regionList.get(i)) {
												Utilities.geoLocationSet(4);
												Emc_eufar.geoDetailLst.setSelectedIndex(i);
												Emc_eufar.geoLocationLst.setSelectedIndex(4);
												break;
											}
										}			
									} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'extent' failed: " + ex.getMessage());}
								}
								if (extents.item(ee).getNodeName().startsWith("gmd:geographicelement")) {
									NodeList geoExtents = extents.item(ee).getChildNodes().item(0).getChildNodes();
									for (int ff = 0; ff < geoExtents.getLength(); ff++) {
										if (geoExtents.item(ff).getNodeName().startsWith("gmd:westboundlongitude")) {
											try {Emc_eufar.geoWestBox.setText(geoExtents.item(ff).getFirstChild().getFirstChild().getNodeValue());}
											catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'westboundlongitude' failed: " + ex.getMessage());}
										}
										if (geoExtents.item(ff).getNodeName().startsWith("gmd:eastboundlongitude")) {
											try {Emc_eufar.geoEastBox.setText(geoExtents.item(ff).getFirstChild().getFirstChild().getNodeValue());}
											catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'eastboundlongitude' failed: " + ex.getMessage());}
										}
										if (geoExtents.item(ff).getNodeName().startsWith("gmd:northboundlatitude")) {
											try {Emc_eufar.geoNorthBox.setText(geoExtents.item(ff).getFirstChild().getFirstChild().getNodeValue());}
											catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'northboundlatitude' failed: " + ex.getMessage());}
										}
										if (geoExtents.item(ff).getNodeName().startsWith("gmd:southboundlatitude")) {
											try {Emc_eufar.geoSouthBox.setText(geoExtents.item(ff).getFirstChild().getFirstChild().getNodeValue());}
											catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'southboundlatitude' failed: " + ex.getMessage());}
										}
									}
								}
								if (extents.item(ee).getNodeName().startsWith("gmd:temporalelement")) {
									periodNumber++;
									String periodStart = extents.item(ee).getFirstChild().getFirstChild().getFirstChild().getFirstChild().
											getFirstChild().getNodeValue();
									String periodEnd = extents.item(ee).getFirstChild().getFirstChild().getFirstChild().getLastChild().
											getFirstChild().getNodeValue();
									if (periodNumber == 1) {
										Emc_eufar.refStartDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(periodStart));
										Emc_eufar.refEndDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(periodEnd));
									}
									else {GuiModification.addRefRead(periodStart, periodEnd);}
								}
							}
						}
						if (dataIdentification.item(bb).getNodeName().startsWith("gmd:spatialresolution")) {
							if (dataIdentification.item(bb).getFirstChild().getFirstChild().getNodeName().
									startsWith("gmd:equivalentscale")) {
								try {
									Emc_eufar.geoResolutionBox.setText(dataIdentification.item(bb).getFirstChild().getFirstChild().getFirstChild().
											getFirstChild().getFirstChild().getFirstChild().getNodeValue());
								} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'spatialresolution' failed: " + ex.getMessage());}
							}
							else if (dataIdentification.item(bb).getFirstChild().getFirstChild().getNodeName().startsWith("gmd:distance")) {
								try {
									Emc_eufar.geoResolutionLst.setSelectedIndex(1);
									GuiModification.geoResolutionSet(1);
									Emc_eufar.geoResolutionBox.setText(dataIdentification.item(bb).getFirstChild().getFirstChild().getFirstChild().
											getFirstChild().getNodeValue());
									Utilities.checkList(Emc_eufar.unitMap, ((Element)dataIdentification.item(bb).getFirstChild().getFirstChild().
											getFirstChild()).getAttribute("uom"), Emc_eufar.geoUnitLst);
								} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'distance' failed: " + ex.getMessage());}
							}
						}
						if (dataIdentification.item(bb).getNodeName().startsWith("gmd:resourceconstraints")) {
							NodeList useConditions = dataIdentification.item(bb).getFirstChild().getChildNodes();
							for (int gg = 0; gg < useConditions.getLength(); gg++) {
								if (useConditions.item(gg).getNodeName().startsWith("gmd:uselimitation")) {
									useConditionNumber++;
									if (useConditionNumber == 1) {
										try {Emc_eufar.useConditionsBox.setText(useConditions.item(gg).getFirstChild().getFirstChild().getNodeValue());}
										catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'uselimitation' failed: " + ex.getMessage());}
									} else {
										GuiModification.addUseRead(Emc_eufar.useConditionsAddTab, Emc_eufar.useConditionsLst, useConditions.item(gg).getFirstChild().getFirstChild().
												getNodeValue(), Emc_eufar.auDelButton1);
									}
								}
								if (useConditions.item(gg).getNodeName().startsWith("gmd:otherconstraints")) {
									accessLimitationNumber++;
									if (accessLimitationNumber == 1) {
										try {Emc_eufar.useLimitationsBox.setText(useConditions.item(gg).getFirstChild().getFirstChild().getNodeValue());}
										catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'otherconstraints' failed: " + ex.getMessage());}
									} else {
										GuiModification.addUseRead(Emc_eufar.useLimitationsAddTab, Emc_eufar.useLimitationsLst, useConditions.item(gg).getFirstChild().
												getFirstChild().getNodeValue(), Emc_eufar.auDelButton2);
									}
								}
							}
						}
						if (dataIdentification.item(bb).getNodeName().startsWith("gmd:pointofcontact")) {
							responsibleParty++;
							NodeList responsiblePartyItems = dataIdentification.item(bb).getFirstChild().getChildNodes();
							if (responsibleParty == 1) {
								try {
									Emc_eufar.orgPartyBox.setText(responsiblePartyItems.item(0).getFirstChild().getFirstChild().getNodeValue());
									Emc_eufar.orgEmailBox.setText(responsiblePartyItems.item(1).getFirstChild().getFirstChild().getFirstChild().
											getFirstChild().getFirstChild().getFirstChild().getNodeValue());
									Utilities.checkList(Emc_eufar.roleMap, responsiblePartyItems.item(2).getFirstChild().getFirstChild().getNodeValue(), Emc_eufar.orgRoleLst);
								} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'pointofcontact' failed: " + ex.getMessage());}
							} else {
								GuiModification.addOrgRead(responsiblePartyItems.item(0).getFirstChild().getFirstChild().getNodeValue(), responsiblePartyItems.
										item(1).getFirstChild().getFirstChild().getFirstChild().getFirstChild().getFirstChild().getFirstChild().
										getNodeValue(), responsiblePartyItems.item(2).getFirstChild().getFirstChild().getNodeValue());
							}	
						}	
					}	
				}
				if (docChildNodes.item(aa).getNodeName().startsWith("gmd:hierarchylevel")) {
					Utilities.checkList(Emc_eufar.typeMap, docChildNodes.item(aa).getFirstChild().getFirstChild().getNodeValue(), Emc_eufar.identTypeLst);
				}
				if (docChildNodes.item(aa).getNodeName().startsWith("gmd:distributioninfo")) {
					try {
						Emc_eufar.identLocatorBox.setText(docChildNodes.item(aa).getFirstChild().getFirstChild().getFirstChild().getFirstChild().
								getFirstChild().getFirstChild().getFirstChild().getFirstChild().getNodeValue());
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'distributioninfo' failed: " + ex.getMessage());}
				}
				if (docChildNodes.item(aa).getNodeName().startsWith("gmd:language")) {
					Utilities.checkList(Emc_eufar.languageMap, docChildNodes.item(aa).getFirstChild().getFirstChild().getNodeValue(), Emc_eufar.metLanguageLst);
				}
				if (docChildNodes.item(aa).getNodeName().startsWith("gmd:datestamp")) {
					Emc_eufar.metDateDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(docChildNodes.item(aa).getFirstChild().getFirstChild().
							getNodeValue()));
				}
				if (docChildNodes.item(aa).getNodeName().startsWith("gmd:dataqualityinfo")) {
					try {
						String lineageText = docChildNodes.item(aa).getFirstChild().getFirstChild().getFirstChild().getFirstChild().getFirstChild().
								getFirstChild().getNodeValue();
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
					} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'dataqualityinfo' failed: " + ex.getMessage());}	
				}
				if (docChildNodes.item(aa).getNodeName().startsWith("gmd:contact")) {
					metadataContact++;
					if (metadataContact == 1) {
						try {
							NodeList contactNodes = docChildNodes.item(aa).getFirstChild().getChildNodes();
							Emc_eufar.metNameBox.setText(contactNodes.item(0).getFirstChild().getFirstChild().getNodeValue());
							Emc_eufar.metEmailBox.setText(contactNodes.item(1).getFirstChild().getFirstChild().getFirstChild().getFirstChild().
									getFirstChild().getFirstChild().getNodeValue());
						} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'contactmet' failed: " + ex.getMessage());}
					} else {
						try {
							NodeList contactNodes = docChildNodes.item(aa).getFirstChild().getChildNodes();
							GuiModification.addMetRead(contactNodes.item(0).getFirstChild().getFirstChild().getNodeValue(),contactNodes.item(1).getFirstChild().
									getFirstChild().getFirstChild().getFirstChild().getFirstChild().getFirstChild().getNodeValue());
						} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'contactmet' failed: " + ex.getMessage());}
					}
				}
				if (docChildNodes.item(aa).getNodeName().startsWith("gmd:acquisitioninfo")) {
					NodeList acquisition = docChildNodes.item(aa).getChildNodes();
					for (int dd = 0; dd < acquisition.getLength(); dd++) {
						if (dd == 0) {
							try {
								NodeList aircraftInfo = docChildNodes.item(aa).getFirstChild().getFirstChild().getChildNodes();
								int index = -1;
								for (int i = 0 ; i < Emc_eufar.airAircraftInfo.length ; i++) {
									if (Emc_eufar.airAircraftInfo[i][5].equals(aircraftInfo.item(3).getFirstChild().getFirstChild().getNodeValue())) {
										index = i;
										break;
									}
								}
								if (index != -1) {
									GuiModification.aircraftInformation(index+1);
									Emc_eufar.airAircraftLst.setSelectedIndex(index+1);
								} else {
									Emc_eufar.airAircraftLst.setSelectedIndex(1);
									GuiModification.aircraftInformation(1);
									Emc_eufar.airManufacturerBox.setText(aircraftInfo.item(0).getFirstChild().getFirstChild().getNodeValue());
									Emc_eufar.airTypeBox.setText(aircraftInfo.item(1).getFirstChild().getFirstChild().getNodeValue());
									Emc_eufar.airOperatorBox.setText(aircraftInfo.item(2).getFirstChild().getFirstChild().getNodeValue());
									Emc_eufar.airRegistrationBox.setText(aircraftInfo.item(3).getFirstChild().getFirstChild().getNodeValue());
									for (int i = 0; i < Emc_eufar.airCountryLst.getItemCount(); i++) {
										if (aircraftInfo.item(4).getFirstChild().getFirstChild().getNodeValue() == Emc_eufar.airCountryLst.
												getItemText(i)) {
											Emc_eufar.airCountryLst.setSelectedIndex(i);
											break;
										}
									}
								}
							} catch (Exception ex) {Emc_eufar.rootLogger.log(Level.WARNING, "Element 'acquisitioninfo' failed: " + ex.getMessage());}
						} else {
							try {
								String instrumentName = acquisition.item(dd).getFirstChild().getLastChild().getFirstChild().getFirstChild()
										.getNodeValue();
								String manufacturerName = acquisition.item(dd).getFirstChild().getFirstChild().getFirstChild().getFirstChild()
										.getNodeValue();
								if (instrumentName != "") {
									GuiModification.addInstRead(instrumentName, manufacturerName);
								}
							} catch (Exception ex) {
								Emc_eufar.rootLogger.log(Level.WARNING, "Element 'acquisitioninfo' failed: " + ex.getMessage());
							}
						}
					}
				}
			}	
		} catch (DOMException ex) {
			Emc_eufar.rootLogger.log(Level.SEVERE, "A problem occured during the loading of the file...", ex);
			Window.alert("Could not parse XML document. A log has been saved on the server for debugging.");
		}
	}
	
	
	
}

