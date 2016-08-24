package com.eufar.emc.client;

import static com.google.gwt.query.client.GQuery.$;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.XMLParser;

public class XmlSave {
	
	/// create the string containing all xml code
	public static String createXml() {

		Emc_eufar.rootLogger.log(Level.INFO, "Creation of the XML code in progress...");
		String xmlString = null;
		try {
			Document doc = XMLParser.createDocument();
			Element rootElement = doc.createElement("gmd:MD_Metadata");
			rootElement.setAttribute("xmlns:gmd","http://www.isotc211.org/2005/gmd");
			rootElement.setAttribute("xmlns:gco","http://www.isotc211.org/2005/gco");
			rootElement.setAttribute("xmlns:gml","http://www.opengis.net/gml");
			rootElement.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
			rootElement.setAttribute("xmlns:srv","http://www.isotc211.org/2005/srv");
			rootElement.setAttribute("xmlns:xlink","http://www.w3.org/1999/xlink");


			///////////////////////////
			/// Identification Info ///
			///////////////////////////
			/// Citation ///
			////////////////
			Element identInfo = Elements.addElement(doc, "gmd:identificationInfo", rootElement);
			Element identInfoMD = Elements.addElement(doc, "gmd:MD_DataIdentification", identInfo);
			Element identCitation = Elements.addElement(doc, "gmd:citation", identInfoMD);
			Element identCitationCI = Elements.addElement(doc, "gmd:CI_Citation", identCitation);
			Element identTitle = Elements.addElement(doc, "gmd:title", identCitationCI);
			Elements.addElement(doc, "gco:CharacterString", identTitle, Emc_eufar.identTitleBox.getText());
			Element identIdentifier = Elements.addElement(doc, "gmd:identifier", identCitationCI);
			Element identIdentifierRS = Elements.addElement(doc, "gmd:RS_Identifier", identIdentifier);
			Element identCode = Elements.addElement(doc, "gmd:code", identIdentifierRS);
			Elements.addElement(doc, "gco:CharacterString", identCode, Emc_eufar.identIdentifierBox.getText());
			Element identPublication = Elements.addElement(doc, "gmd:date", identCitationCI);
			Element identPublicationCI = Elements.addElement(doc, "gmd:CI_Date", identPublication);
			Element identPubDate = Elements.addElement(doc, "gmd:date", identPublicationCI);
			Elements.addElement(doc, "gco:Date", identPubDate, DateTimeFormat.getFormat("yyyy-MM-dd").format(Emc_eufar.refPublicationDat.getValue()));
			Element identPubType = Elements.addElement(doc, "gmd:dateType", identPublicationCI);
			Element identPubTypeCI = Elements.addElement(doc, "gmd:CI_DateTypeCode", identPubType, "publication");
			identPubTypeCI.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/resources/codelist/"
					+ "ML_gmxCodelists.xml#CI_DateTypeCode");
			identPubTypeCI.setAttribute("codeListValue","publication");
			Element identRevision = Elements.addElement(doc, "gmd:date", identCitationCI);
			Element identRevisionCI = Elements.addElement(doc, "gmd:CI_Date", identRevision);
			Element identRevDate = Elements.addElement(doc, "gmd:date", identRevisionCI);
			Elements.addElement(doc, "gco:Date", identRevDate, DateTimeFormat.getFormat("yyyy-MM-dd").format(Emc_eufar.refRevisionDat.getValue()));
			Element identRevType = Elements.addElement(doc, "gmd:dateType", identRevisionCI);
			Element identRevTypeCI = Elements.addElement(doc, "gmd:CI_DateTypeCode", identRevType, "revision");
			identRevTypeCI.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/resources/codelist/"
					+ "ML_gmxCodelists.xml#CI_DateTypeCode");
			identRevTypeCI.setAttribute("codeListValue","revision");
			Element identCreation = Elements.addElement(doc, "gmd:date", identCitationCI);
			Element identCreationCI = Elements.addElement(doc, "gmd:CI_Date", identCreation);
			Element identCreaDate = Elements.addElement(doc, "gmd:date", identCreationCI);
			Elements.addElement(doc, "gco:Date", identCreaDate, DateTimeFormat.getFormat("yyyy-MM-dd").format(Emc_eufar.refCreationDat.getValue()));
			Element identCreaType = Elements.addElement(doc, "gmd:dateType", identCreationCI);
			Element identCreaTypeCI = Elements.addElement(doc, "gmd:CI_DateTypeCode", identCreaType, "creation");
			identCreaTypeCI.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/resources/codelist/"
					+ "ML_gmxCodelists.xml#CI_DateTypeCode");
			identCreaTypeCI.setAttribute("codeListValue","creation");			


			////////////////
			/// Abstract ///
			////////////////
			Element identAbstract = Elements.addElement(doc, "gmd:abstract", identInfoMD);
			Elements.addElement(doc, "gco:CharacterString", identAbstract, Emc_eufar.identAbstractAre.getText());


			//////////////
			/// Topics ///
			//////////////
			List<CheckBox> allCheckBox = $("*", Emc_eufar.clScroll).widgets(CheckBox.class);
			for (int i = 0; i < allCheckBox.size(); i = i + 2) {
				if (allCheckBox.get(i).getValue()) {
					String stringCode = Emc_eufar.categoriesMap.get(allCheckBox.get(i).getName());
					Element identTopic = Elements.addElement(doc, "gmd:topicCategory", identInfoMD);
					Elements.addElement(doc, "gmd:MD_TopicCategoryCode", identTopic, stringCode);
				}
			}


			////////////////
			/// Keywords ///
			////////////////
			Element identDescripKeyword = Elements.addElement(doc, "gmd:descriptiveKeywords", identInfoMD);
			Element identDescripKeywordMD = Elements.addElement(doc, "gmd:MD_Keywords", identDescripKeyword);
			allCheckBox = $("*", Emc_eufar.kwScroll).widgets(CheckBox.class);
			for (int i = 0; i < allCheckBox.size(); i = i + 2) {
				if (allCheckBox.get(i).getValue()) {
					String stringCode = Emc_eufar.keywordsMap.get(allCheckBox.get(i).getName());
					Element identKeyword = Elements.addElement(doc, "gmd:keyword", identDescripKeywordMD);
					Elements.addElement(doc, "gco:CharacterString", identKeyword, stringCode);
				}
			}
			Element identThesaurusName = Elements.addElement(doc, "gmd:thesaurusName", identDescripKeywordMD);
			Element identThesaurusCitation = Elements.addElement(doc, "gmd:CI_Citation", identThesaurusName);
			Element identThesaurusTitle = Elements.addElement(doc, "gmd:title", identThesaurusCitation);
			Elements.addElement(doc, "gco:CharacterString", identThesaurusTitle, "NASA/Global Change Master Directory (GCMD) Earth Science Keywords. Version "
					+ "8.0.0.0.0");
			Element identThesaurusDate01 = Elements.addElement(doc, "gmd:date", identThesaurusCitation);
			Element identThesaurusDateCI = Elements.addElement(doc, "gmd:CI_Date", identThesaurusDate01);
			Element identThesaurusDate02 = Elements.addElement(doc, "gmd:date", identThesaurusDateCI);
			Elements.addElement(doc, "gco:Date", identThesaurusDate02, "2015-02-20");
			Element identThesaurusDateType = Elements.addElement(doc, "gmd:dateType", identThesaurusDateCI);
			Element identThesaurusDateTypeCI = Elements.addElement(doc, "gmd:CI_DateTypeCode", identThesaurusDateType, "revision");
			identThesaurusDateTypeCI.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/resources/"
					+ "codelist/ML_gmxCodelists.xml#CI_DateTypeCode");
			identThesaurusDateTypeCI.setAttribute("codeListValue","revision");		


			///////////////////////
			/// Location Extent ///
			///////////////////////
			Element identExtent = Elements.addElement(doc, "gmd:extent", identInfoMD);
			Element identExtentEX = Elements.addElement(doc, "gmd:EX_Extent", identExtent);
			Element identExtDescription = Elements.addElement(doc, "gmd:description", identExtentEX);
			if (Emc_eufar.geoDetailLst.getSelectedItemText() != null) {
				Elements.addElement(doc, "gco:CharacterString", identExtDescription, Emc_eufar.geoDetailLst.getSelectedItemText());
			} else {
				Elements.addElement(doc, "gco:CharacterString", identExtDescription, "");
			}
			Element identExtElement = Elements.addElement(doc, "gmd:geographicElement", identExtentEX);
			Element identExtBox = Elements.addElement(doc, "gmd:EX_GeographicBoundingBox", identExtElement);
			Element identExtWest = Elements.addElement(doc, "gmd:westBoundLongitude", identExtBox);
			Elements.addElement(doc, "gco:Decimal", identExtWest, Emc_eufar.geoWestBox.getText());
			Element identExtEast = Elements.addElement(doc, "gmd:eastBoundLongitude", identExtBox);
			Elements.addElement(doc, "gco:Decimal", identExtEast, Emc_eufar.geoEastBox.getText());
			Element identExtNorth = Elements.addElement(doc, "gmd:northBoundLatitude", identExtBox);
			Elements.addElement(doc, "gco:Decimal", identExtNorth, Emc_eufar.geoNorthBox.getText());
			Element identExtSouth = Elements.addElement(doc, "gmd:southBoundLatitude", identExtBox);
			Elements.addElement(doc, "gco:Decimal", identExtSouth, Emc_eufar.geoSouthBox.getText());


			///////////////////////
			/// Temporal Extent ///
			///////////////////////
			for (int i = 0; i < Emc_eufar.refEndLst.size(); i++) {
				Element identExtTemporal = Elements.addElement(doc, "gmd:temporalElement", identExtentEX);
				Element identExtTemporalEX = Elements.addElement(doc, "gmd:EX_TemporalExtent", identExtTemporal);
				Element identExtTempExtent = Elements.addElement(doc, "gmd:extent", identExtTemporalEX);
				Element identExtTempPeriod = Elements.addElement(doc, "gml:TimePeriod", identExtTempExtent);
				identExtTempPeriod.setAttribute("gml:id","extent" + Integer.toString(i)); 
				Elements.addElement(doc, "gml:beginPosition", identExtTempPeriod, DateTimeFormat.getFormat("yyyy-MM-dd").format(Emc_eufar.refStartLst.get(i).getValue()));
				Elements.addElement(doc, "gml:endPosition", identExtTempPeriod, DateTimeFormat.getFormat("yyyy-MM-dd").format(Emc_eufar.refEndLst.get(i).getValue()));
			}


			//////////////////////////
			/// Spatial Resolution ///
			//////////////////////////
			Element identResolution = Elements.addElement(doc, "gmd:spatialResolution", identInfoMD);
			Element identResolutionMD = Elements.addElement(doc, "gmd:MD_Resolution", identResolution);
			if (Emc_eufar.geoResolutionLst.getSelectedItemText() == "Distance") {
				Element IdentDistance1 = Elements.addElement(doc, "gmd:distance", identResolutionMD);
				Element IdentDistance2 = Elements.addElement(doc, "gco:Distance", IdentDistance1, Emc_eufar.geoResolutionBox.getText());
				IdentDistance2.setAttribute("uom",Emc_eufar.unitMap.get(Emc_eufar.geoUnitLst.getSelectedItemText()));
			}
			else if (Emc_eufar.geoResolutionLst.getSelectedItemText() == "Scale") {
				Element identScale = Elements.addElement(doc, "gmd:equivalentScale", identResolutionMD);
				Element identScaleFraction = Elements.addElement(doc, "gmd:MD_RepresentativeFraction", identScale);
				Element identScaleDenominator = Elements.addElement(doc, "gmd:denominator", identScaleFraction);
				Elements.addElement(doc, "gco:Integer", identScaleDenominator, Emc_eufar.geoResolutionBox.getText());
			}


			////////////////
			/// Language ///
			////////////////
			String languageCode = Emc_eufar.languageMap.get(Emc_eufar.identLanguageLst.getSelectedItemText());
			Element identLanguage = Elements.addElement(doc, "gmd:language", identInfoMD);
			Element identLanguageCode = Elements.addElement(doc, "gmd:LanguageCode", identLanguage, languageCode);
			identLanguageCode.setAttribute("codeList", "http://www.loc.gov/standards/iso639-2/");
			identLanguageCode.setAttribute("codeListValue", languageCode);


			////////////////////////////
			/// Resource Constraints ///
			////////////////////////////
			Element identConstraint = Elements.addElement(doc, "gmd:resourceConstraints", identInfoMD);
			Element identConstraintMD = Elements.addElement(doc, "gmd:MD_Constraints", identConstraint);
			for (int i = 0; i < Emc_eufar.useConditionsLst.size(); i++) {
				if (i > 0) {
					if (Emc_eufar.useConditionsLst.get(i).getText() != "") {
						Element identConditionsUse = Elements.addElement(doc, "gmd:useLimitation", identConstraintMD);
						Elements.addElement(doc, "gco:CharacterString", identConditionsUse, Emc_eufar.useConditionsLst.get(i).getText());
					}
				} else {
					Element identConditionsUse = Elements.addElement(doc, "gmd:useLimitation", identConstraintMD);
					Elements.addElement(doc, "gco:CharacterString", identConditionsUse, Emc_eufar.useConditionsLst.get(i).getText());
				}
			}
			Element identLegalConstraint = Elements.addElement(doc, "gmd:resourceConstraints", identInfoMD);
			Element identLegalConstraintMD = Elements.addElement(doc, "gmd:MD_LegalConstraints", identLegalConstraint);
			Element identLegalAccess = Elements.addElement(doc, "gmd:accessConstraints", identLegalConstraintMD);
			Element identLegalAccessMD = Elements.addElement(doc, "gmd:MD_RestrictionCode", identLegalAccess, "otherRestrictions");
			identLegalAccessMD.setAttribute("codeList","http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/resources/"
					+ "codelist/gmxCodelists.xml#MD_RestrictionCode");
			identLegalAccessMD.setAttribute("codeListValue","otherRestrictions");
			for (int i = 0; i < Emc_eufar.useLimitationsLst.size(); i++) {
				if (i > 0) {
					if (Emc_eufar.useLimitationsLst.get(i).getText() != "") {
						Element identLimitationsUse = Elements.addElement(doc, "gmd:otherConstraints", identLegalConstraintMD);
						Elements.addElement(doc, "gco:CharacterString", identLimitationsUse, Emc_eufar.useLimitationsLst.get(i).getText());
					}
				} else {
					Element identLimitationsUse = Elements.addElement(doc, "gmd:otherConstraints", identLegalConstraintMD);
					Elements.addElement(doc, "gco:CharacterString", identLimitationsUse, Emc_eufar.useLimitationsLst.get(i).getText());
				}
			}


			/////////////////////////
			/// Resource Contacts ///
			/////////////////////////
			for (int i = 0; i < Emc_eufar.orgPartyLst.size(); i++) {
				if (i > 0) {
					if (Emc_eufar.orgPartyLst.get(i).getText() !="" || Emc_eufar.orgEmailLst.get(i).getText() !="") {
						String roleCode = Emc_eufar.roleMap.get(Emc_eufar.orgRole2Lst.get(i).getSelectedItemText());
						Element identContact = Elements.addElement(doc, "gmd:pointOfContact", identInfoMD);
						Element identContactCI = Elements.addElement(doc, "gmd:CI_ResponsibleParty", identContact);
						Element identName = Elements.addElement(doc, "gmd:organisationName", identContactCI);
						Elements.addElement(doc, "gco:CharacterString", identName, Emc_eufar.orgPartyLst.get(i).getText());
						Element identContactInfo = Elements.addElement(doc, "gmd:contactInfo", identContactCI);
						Element identContactInfoCI = Elements.addElement(doc, "gmd:CI_Contact", identContactInfo);
						Element identContactAdress = Elements.addElement(doc, "gmd:address", identContactInfoCI);
						Element identContactAdressCI = Elements.addElement(doc, "gmd:CI_Address", identContactAdress);
						Element identContactEmail = Elements.addElement(doc, "gmd:electronicMailAddress", identContactAdressCI);
						Elements.addElement(doc, "gco:CharacterString", identContactEmail, Emc_eufar.orgEmailLst.get(i).getText());
						Element identContactRole = Elements.addElement(doc, "gmd:role",identContactCI);
						Element identContactRoleCode = Elements.addElement(doc, "gmd:CI_RoleCode", identContactRole, roleCode);
						identContactRoleCode.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas"
								+ "/resources/codelist/gmxCodelists.xml#CI_RoleCode");
						identContactRoleCode.setAttribute("codeListValue", roleCode);
					}
				} else {
					String roleCode = Emc_eufar.roleMap.get(Emc_eufar.orgRole2Lst.get(i).getSelectedItemText());
					Element identContact = Elements.addElement(doc, "gmd:pointOfContact", identInfoMD);
					Element identContactCI = Elements.addElement(doc, "gmd:CI_ResponsibleParty", identContact);
					Element identName = Elements.addElement(doc, "gmd:organisationName", identContactCI);
					Elements.addElement(doc, "gco:CharacterString", identName, Emc_eufar.orgPartyLst.get(i).getText());
					Element identContactInfo = Elements.addElement(doc, "gmd:contactInfo", identContactCI);
					Element identContactInfoCI = Elements.addElement(doc, "gmd:CI_Contact", identContactInfo);
					Element identContactAdress = Elements.addElement(doc, "gmd:address", identContactInfoCI);
					Element identContactAdressCI = Elements.addElement(doc, "gmd:CI_Address", identContactAdress);
					Element identContactEmail = Elements.addElement(doc, "gmd:electronicMailAddress", identContactAdressCI);
					Elements.addElement(doc, "gco:CharacterString", identContactEmail, Emc_eufar.orgEmailLst.get(i).getText());
					Element identContactRole = Elements.addElement(doc, "gmd:role",identContactCI);
					Element identContactRoleCode = Elements.addElement(doc, "gmd:CI_RoleCode", identContactRole, roleCode);
					identContactRoleCode.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas"
								+ "/resources/codelist/gmxCodelists.xml#CI_RoleCode");
					identContactRoleCode.setAttribute("codeListValue", roleCode);
				}
			}


			///////////////////////
			/// Hierarchy Level ///
			///////////////////////
			Element hierarchyLevel = Elements.addElement(doc, "gmd:hierarchyLevel", rootElement);
			Element scopeLevelMD = Elements.addElement(doc, "gmd:MD_ScopeCode", hierarchyLevel, Emc_eufar.identTypeLst.getSelectedItemText().toLowerCase());
			scopeLevelMD.setAttribute("codeList","http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/resources/codelist/"
					+ "gmxCodelists.xml#MD_ScopeCode");
			scopeLevelMD.setAttribute("codeListValue", Emc_eufar.identTypeLst.getSelectedItemText().toLowerCase());


			/////////////////////////
			/// Distribution Info ///
			/////////////////////////
			Element distributionInfo = Elements.addElement(doc, "gmd:distributionInfo", rootElement);
			Element distributionInfoMD = Elements.addElement(doc, "gmd:MD_Distribution", distributionInfo);
			Element transferInfo = Elements.addElement(doc, "gmd:transferOptions", distributionInfoMD);
			Element transferInfoMD = Elements.addElement(doc, "gmd:MD_DigitalTransferOptions", transferInfo);
			Element onlineInfo = Elements.addElement(doc, "gmd:onLine", transferInfoMD);
			Element onlineInfoCI = Elements.addElement(doc, "gmd:CI_OnlineResource", onlineInfo);
			Element linkageInfo = Elements.addElement(doc, "gmd:linkage", onlineInfoCI);
			Elements.addElement(doc, "gmd:URL", linkageInfo, Emc_eufar.identLocatorBox.getText());


			/////////////////////
			/// Language Info ///
			/////////////////////
			String languageCode2 = Emc_eufar.languageMap.get(Emc_eufar.metLanguageLst.getSelectedItemText());
			Element metaLanguage = Elements.addElement(doc, "gmd:language", rootElement);
			Element metaLanguageCode = Elements.addElement(doc, "gmd:LanguageCode", metaLanguage, languageCode2);
			metaLanguageCode.setAttribute("codeList","http://www.loc.gov/standards/iso639-2/");
			metaLanguageCode.setAttribute("codeListValue", languageCode2);


			////////////////////
			/// Data Quality ///
			////////////////////
			String lineageString = new String("");
			if (((RadioButton) Emc_eufar.imageRad.getWidget(0)).getValue() == true) {
				lineageString = lineageString + "Earth observation/Remote sensing data|";
				lineageString = lineageString + "Name of calibration laboratory: " + Emc_eufar.imageCalBox.getText() + "|Date of radiometric calibration: "
						+ DateTimeFormat.getFormat("yyyy-MM-dd").format(Emc_eufar.imageRadDat.getValue()) + "|Date of spectral calibration: " + 
						DateTimeFormat.getFormat("yyyy-MM-dd").format(Emc_eufar.imageSpeDat.getValue()) + "|Number of spectral bands: " + 
						Emc_eufar.imageBanBox.getText() + "|Overall heading / fligh direction (dd): " + Emc_eufar.imageDirBox.getText() + "|Overall altitude / "
						+ "average height ASL (m): " + Emc_eufar.imageAltBox.getText() + "|Solar zenith (dd): " + Emc_eufar.imageZenBox.getText() + "|Solar azimuth "
						+ "(dd): " + Emc_eufar.imageAziBox.getText() + "|Report anomalies in data acquisition: " + Emc_eufar.imageAnoBox.getText();
				String imageLstAnswer = new String("");
				if (Emc_eufar.imageLevLst.getSelectedValue() != "Make a choice...") {
					imageLstAnswer = Emc_eufar.imageLevLst.getSelectedValue();
				}
				lineageString = lineageString + "|Processing level: " + imageLstAnswer;
				lineageString = lineageString + "|Dark current (DC) correction: " + Utilities.getAnswer(Emc_eufar.imageChk10Y, Emc_eufar.imageChk10N) + "|Aggregated "
						+ "interpolated pixel mask: " + Utilities.getAnswer(Emc_eufar.imageChk11Y, Emc_eufar.imageChk11N) + "|Aggregated bad pixel mask: " + 
						Utilities.getAnswer(Emc_eufar.imageChk12Y, Emc_eufar.imageChk12N) + "|Saturated pixels / overflow: " + Utilities.getAnswer(Emc_eufar.imageChk13Y, Emc_eufar.imageChk13N) + "|Pixels "
						+ "affected by saturation in spatial/spectral neighbourhood: " + Utilities.getAnswer(Emc_eufar.imageChk14Y, Emc_eufar.imageChk14N) + "|Problems with "
						+ "position information / Interpolated position information: " + Utilities.getAnswer(Emc_eufar.imageChk15Y, Emc_eufar.imageChk15N) + "|Problems with "
						+ "attitude information / Interpolated attitude information: " + Utilities.getAnswer(Emc_eufar.imageChk16Y, Emc_eufar.imageChk16N) + "|Synchronization "
						+ "problems: " + Utilities.getAnswer(Emc_eufar.imageChk17Y, Emc_eufar.imageChk17N) + "|Interpolated pixels during geocoding: " + 
						Utilities.getAnswer(Emc_eufar.imageChk18Y, Emc_eufar.imageChk18N) + "|Failure of atmospheric correction: " + Utilities.getAnswer(Emc_eufar.imageChk19Y, Emc_eufar.imageChk19N) + 
						"|Cloud mask: " + Utilities.getAnswer(Emc_eufar.imageChk20Y, Emc_eufar.imageChk20N) + "|Cloud shadow mask: " + Utilities.getAnswer(Emc_eufar.imageChk21Y, Emc_eufar.imageChk21N) + 
						"|Haze mask: " + Utilities.getAnswer(Emc_eufar.imageChk22Y, Emc_eufar.imageChk22N) + "|Critical terrain correction based on DEM roughness measure: " + 
						Utilities.getAnswer(Emc_eufar.imageChk23Y, Emc_eufar.imageChk23N) + "|Critical terrain correction based on slope/local illumination angle: " + 
						Utilities.getAnswer(Emc_eufar.imageChk24Y, Emc_eufar.imageChk24N) + "|Critical BRDF geometry based on sun-sensor-terrain geometry: " + 
						Utilities.getAnswer(Emc_eufar.imageChk25Y, Emc_eufar.imageChk25N) + "|";
			} else if (((RadioButton) Emc_eufar.insituRad.getWidget(0)).getValue() == true) {
				lineageString = lineageString + "Atmospheric/In-situ measurements|";
				lineageString = lineageString + "Link to the procedure's description: " + Emc_eufar.insituLinkBox.getText()+ "|Source of calibration " + 
						"constants: " + Emc_eufar.insituConstBox.getText()+ "|Source of calibration materials: " + Emc_eufar.insituMatBox.getText()+ "|";
				lineageString = lineageString + "Data converted to geophysical units: " + Utilities.getAnswer(Emc_eufar.insituChk01Y, Emc_eufar.insituChk01N) + "|";
				ArrayList<String> listTemp = new ArrayList<String>();
				String insituChk03Answer = new String("");
				if (((CheckBox) Emc_eufar.insituChk04.getWidget(0)).getValue() == true) {
					listTemp.add("NetCDF");
				}
				if (((CheckBox) Emc_eufar.insituChk05.getWidget(0)).getValue() == true) {
					listTemp.add("HDF");
				}
				if (((CheckBox) Emc_eufar.insituChk06.getWidget(0)).getValue() == true) {
					listTemp.add("NASA/Ames");
				}
				if (((CheckBox) Emc_eufar.insituChk07.getWidget(0)).getValue() == true) {
					listTemp.add("Other/" + Emc_eufar.insituOtherBox.getText());
				}
				if (listTemp.size() > 1) {
					for (int i = 0; i < listTemp.size(); i++) {
						insituChk03Answer = insituChk03Answer + listTemp.get(i) + "; ";
					}
					insituChk03Answer = insituChk03Answer.substring(0,insituChk03Answer.lastIndexOf(';'));
				} else if (listTemp.size() == 1) {
					insituChk03Answer = listTemp.get(0);
				}
				lineageString = lineageString + "Output format: " + insituChk03Answer + "|";
				lineageString = lineageString + "Quality-control flagging applied to individual data points: " + Emc_eufar.insituFlagAre.getText() + "|";
				lineageString = lineageString + "Assumption: " + Emc_eufar.insituAssumptionAre.getText() + "|";
			}
			Element dataQualityInfo1 = Elements.addElement(doc, "gmd:dataQualityInfo", rootElement);
			Element dataQualityDQ = Elements.addElement(doc, "gmd:DQ_DataQuality", dataQualityInfo1);
			Element lineageQuality = Elements.addElement(doc, "gmd:lineage", dataQualityDQ);
			Element lineageQualityLI = Elements.addElement(doc, "gmd:LI_Lineage", lineageQuality);
			Element statementQuality1 = Elements.addElement(doc, "gmd:statement", lineageQualityLI);
			Elements.addElement(doc, "gco:CharacterString", statementQuality1, lineageString);
			Element reportQuality = Elements.addElement(doc, "gmd:report", dataQualityDQ);
			Element domainConsistencyDQ = Elements.addElement(doc, "gmd:DQ_DomainConsistency", reportQuality);
			Element resultQuality = Elements.addElement(doc, "gmd:result", domainConsistencyDQ);
			Element conformanceResultDQ = Elements.addElement(doc, "gmd:DQ_ConformanceResult", resultQuality);
			Element specificationQuality = Elements.addElement(doc, "gmd:specification", conformanceResultDQ);
			Element citationQualityCI = Elements.addElement(doc, "gmd:CI_Citation", specificationQuality);
			Element titleQuality = Elements.addElement(doc, "gmd:title", citationQualityCI);
			Elements.addElement(doc, "gco:CharacterString", titleQuality, "COMMISSION REGULATION (EC) No 1205/2008 of 3 December 2008 implementing Directive "
					+ "2007/2/EC of the European Parliament and of the Council as regards metadata");
			Element dateQuality = Elements.addElement(doc, "gmd:date", citationQualityCI);
			Element dateQualityCI = Elements.addElement(doc, "gmd:CI_Date", dateQuality);
			Element dateQuality2 = Elements.addElement(doc, "gmd:date", dateQualityCI);
			Elements.addElement(doc, "gco:Date", dateQuality2, "2008-12-04");
			Element dateType = Elements.addElement(doc, "gmd:dateType", dateQualityCI);
			Element dateTypeCode = Elements.addElement(doc, "gmd:CI_DateTypeCode", dateType, "publication");
			dateTypeCode.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/resources/codelist/"
					+ "ML_gmxCodelists.xml#CI_DateTypeCode");	
			dateTypeCode.setAttribute("codeListValue", "publication");
			Element passQuality = Elements.addElement(doc, "gmd:pass", conformanceResultDQ);
			Elements.addElement(doc, "gco:Boolean", passQuality, "True");


			////////////////////////////////
			/// Aircraft and Instruments ///
			////////////////////////////////
			Element acquisitionInfo = Elements.addElement(doc, "gmd:acquisitionInfo", rootElement);
			if (Emc_eufar.aircraftTabList.size() > 0) {
				for (int i = 0; i < Emc_eufar.aircraftTabList.size(); i++) {
					Element aircraftInfo = Elements.addElement(doc, "gmd:platformInfo", acquisitionInfo);
					Element aircraftInfoAI = Elements.addElement(doc, "gmd:PI_PlatformInfo", aircraftInfo);
					Element aircraftManufacturer = Elements.addElement(doc, "gmd:platformManufacturer", aircraftInfoAI);
					Element aircraftType = Elements.addElement(doc, "gmd:platformType", aircraftInfoAI);
					Element aircraftOperator = Elements.addElement(doc, "gmd:platformOperator", aircraftInfoAI);
					Element aircraftCountry = Elements.addElement(doc, "gmd:platformCountry", aircraftInfoAI);
					Element aircraftRegistration = Elements.addElement(doc, "gmd:platformRegistration", aircraftInfoAI);
					Elements.addElement(doc, "gco:CharacterString", aircraftManufacturer, Emc_eufar.manufacturairTabList.get(i));
					Elements.addElement(doc, "gco:CharacterString", aircraftType, Emc_eufar.aircraftTabList.get(i));
					Elements.addElement(doc, "gco:CharacterString", aircraftOperator, Emc_eufar.operatorTabList.get(i));
					Elements.addElement(doc, "gco:CharacterString", aircraftCountry, Emc_eufar.countryTabList.get(i));
					Elements.addElement(doc, "gco:CharacterString", aircraftRegistration, Emc_eufar.identificationTabList.get(i));
				}
			}
			if (Emc_eufar.instrumentTabList.size() > 0) {
				for (int i = 0; i < Emc_eufar.instrumentTabList.size(); i++) {
					Element instrumentInfo = Elements.addElement(doc, "gmd:instrumentInfo", acquisitionInfo);
					Element instrumentInfoAI = Elements.addElement(doc, "gmd:II_InstrumentInfo", instrumentInfo);
					Element instrumentManufacturer = Elements.addElement(doc, "gmd:instrumentManufacturer", instrumentInfoAI);
					Element instrumentType = Elements.addElement(doc, "gmd:instrumentType", instrumentInfoAI);
					Elements.addElement(doc, "gco:CharacterString", instrumentManufacturer, Emc_eufar.manufacturerTabList.get(i));
					Elements.addElement(doc, "gco:CharacterString", instrumentType, Emc_eufar.instrumentTabList.get(i));
				}
			}
			

			////////////////////
			/// Contact Info ///
			////////////////////
			for (int i = 0; i < Emc_eufar.metNameLst.size(); i++) {
				if (i > 0) {
					if (Emc_eufar.metNameLst.get(i).getText() != "" || Emc_eufar.metEmailLst.get(i).getText() != "") {
						Element xmlfileContact = Elements.addElement(doc, "gmd:contact", rootElement);
						Element responsiblePartyInfoCI = Elements.addElement(doc, "gmd:CI_ResponsibleParty", xmlfileContact);
						Element nameContact = Elements.addElement(doc, "gmd:organisationName", responsiblePartyInfoCI);
						Elements.addElement(doc, "gco:CharacterString", nameContact, Emc_eufar.metNameLst.get(i).getText());
						Element infoContact = Elements.addElement(doc, "gmd:contactInfo", responsiblePartyInfoCI);
						Element infoContactCI = Elements.addElement(doc, "gmd:CI_Contact", infoContact);
						Element addressContact = Elements.addElement(doc, "gmd:address", infoContactCI);
						Element addressContactCI = Elements.addElement(doc, "gmd:CI_Address", addressContact);
						Element emailContact = Elements.addElement(doc, "gmd:electronicMailAddress", addressContactCI);
						Elements.addElement(doc, "gco:CharacterString", emailContact, Emc_eufar.metEmailLst.get(i).getText());
						Element roleContact = Elements.addElement(doc, "gmd:role", responsiblePartyInfoCI);
						Element roleCodeContact = Elements.addElement(doc, "gmd:CI_RoleCode", roleContact, "pointOfContact");
						roleCodeContact.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/"
								+ "resources/codelist/gmxCodelists.xml#CI_RoleCode");
						roleCodeContact.setAttribute("codeListValue", "pointOfContact");
					}
				} else {
					Element xmlfileContact = Elements.addElement(doc, "gmd:contact", rootElement);
					Element responsiblePartyInfoCI = Elements.addElement(doc, "gmd:CI_ResponsibleParty", xmlfileContact);
					Element nameContact = Elements.addElement(doc, "gmd:organisationName", responsiblePartyInfoCI);
					Elements.addElement(doc, "gco:CharacterString", nameContact, Emc_eufar.metNameLst.get(i).getText());
					Element infoContact = Elements.addElement(doc, "gmd:contactInfo", responsiblePartyInfoCI);
					Element infoContactCI = Elements.addElement(doc, "gmd:CI_Contact", infoContact);
					Element addressContact = Elements.addElement(doc, "gmd:address", infoContactCI);
					Element addressContactCI = Elements.addElement(doc, "gmd:CI_Address", addressContact);
					Element emailContact = Elements.addElement(doc, "gmd:electronicMailAddress", addressContactCI);
					Elements.addElement(doc, "gco:CharacterString", emailContact, Emc_eufar.metEmailLst.get(i).getText());
					Element roleContact = Elements.addElement(doc, "gmd:role", responsiblePartyInfoCI);
					Element roleCodeContact = Elements.addElement(doc, "gmd:CI_RoleCode", roleContact, "pointOfContact");
					roleCodeContact.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/"
							+ "resources/codelist/gmxCodelists.xml#CI_RoleCode");
					roleCodeContact.setAttribute("codeListValue", "pointOfContact");
				}
			}


			/////////////////////
			/// Metadata Date ///
			/////////////////////
			Element dateStamp = Elements.addElement(doc, "gmd:dateStamp", rootElement);
			Elements.addElement(doc, "gco:Date", dateStamp, DateTimeFormat.getFormat("yyyy-MM-dd").format(Emc_eufar.metDateDat.getValue()));


			doc.appendChild(rootElement);
			xmlString = "<?xml version='1.0' encoding='UTF-8'?>" + doc.toString();
		} catch (Exception ex) {
			Emc_eufar.rootLogger.log(Level.SEVERE, "A problem occured: ", ex);
		}
		return xmlString;
	}
}
