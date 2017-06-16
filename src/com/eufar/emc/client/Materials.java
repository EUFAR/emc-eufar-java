package com.eufar.emc.client;


import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.DataResource.MimeType;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.datepicker.client.DateBox;

public class Materials {
	
	
	public static ArrayList<String> controlList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Make a choice...");
		list.add("Copy all form content to a new tab");
		list.add("Copy all form content to an existing tab");
		return list;
	}
	
	
	public static ArrayList<String> levelList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Make a choice...");
		list.add("Level 1");
		list.add("Level 2 geo");
		list.add("Level 2 atm");
		list.add("Level 2");
		return list;
	}
	
	
	public static ArrayList<String> DCList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Make a choice...");
		list.add("Yes");
		list.add("No");
		return list;
	}
	
	
	public static ArrayList<String> unitList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Make a choice...");
		list.add("Centimetre(s)");
		list.add("Decimal degree(s)");
		list.add("Kilometre(s)");
		list.add("Metre(s)");
		return list;
	}
	
	
	public static ArrayList<String> typeList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Dataset");
		list.add("Series");
		return list;
	}

	
	public static ArrayList<String> languageList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Bulgarian");
		list.add("Czech");
		list.add("Danish");
		list.add("Dutch");
		list.add("English");
		list.add("Estonian");
		list.add("Finnish");
		list.add("French");
		list.add("German");
		list.add("Greek");
		list.add("Hungarian");
		list.add("Irish");
		list.add("Italian");
		list.add("Latvian");
		list.add("Lithuanian");
		list.add("Maltese");
		list.add("Polish");
		list.add("Portuguese");
		list.add("Romanian");
		list.add("Slovak");
		list.add("Slovenian");
		list.add("Spanish");
		list.add("Swedish");
		return list;
	}

	
	public static ArrayList<String> aircraftList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Make a choice...");
		list.add("Other...");
		list.add("AWI - POLAR 5");
		list.add("CNR - ERA Skyarrow");
		list.add("CNR - Partenavia");
		list.add("CzechGlobe - C 208");
		list.add("DLR - C 208");
		list.add("DLR - DO 228 D-CFFU");
		list.add("DLR - DO 228 D-CODE");
		list.add("DLR - Falcon 20");
		list.add("ENVISCOPE - Learjet 35");
		list.add("ENVISCOPE - Partenavia");
		list.add("FAAM - BAe 146");
		list.add("FUB - C 207");
		list.add("INTA - CASA 212 AR");
		list.add("INTA - CASA 212 RS");
		list.add("KIT - Enduro");
		list.add("NERC - Twin Otter VP-FAZ");
		list.add("NERC - Twin Otter VP-FBL");
		list.add("SAFIRE - ATR 42");
		list.add("SAFIRE - Falcon 20");
		list.add("SAFIRE - PIPER Aztec");
		list.add("UEDIN - ECO Dimona");
		return list;
	}

	
	public static ArrayList<String> locationList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Make a choice...");
		list.add("Continents");
		list.add("Countries");
		list.add("Oceans");
		list.add("Regions");
		return list;
	}

	
	public static ArrayList<String> continentList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Make a choice...");
		list.add("Africa");
		list.add("Antarctica");
		list.add("Asia");
		list.add("Oceania");
		list.add("Europe");
		list.add("North America");
		list.add("South America");
		return list;
	}

	
	public static ArrayList<String> oceanList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Make a choice...");
		list.add("Atlantic Ocean");
		list.add("Arctic Ocean");
		list.add("Indian Ocean");
		list.add("Pacific Ocean");
		list.add("Southern Ocean");
		return list;
	}

	
	public static ArrayList<String> regionList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Make a choice...");
		list.add("Arctic Region");
		list.add("Atlantic Ocean Islands");
		list.add("Central Africa");
		list.add("Central America");
		list.add("Central Europe");
		list.add("Eastern Africa");
		list.add("Eastern Asia");
		list.add("Eastern Europe");
		list.add("Indian Ocean Islands");
		list.add("Middle East");
		list.add("North America");
		list.add("Northern Africa");
		list.add("Northern Europe");
		list.add("Pacific Islands");
		list.add("South America");
		list.add("Southcentral Asia");
		list.add("Southern Asia");
		list.add("Southern Europe");
		list.add("Western Africa");
		list.add("Western Asia");
		list.add("Western Europe");
		return list;
	}

	
	public static ArrayList<String> countryList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Make a choice...");
		list.add("Afghanistan");
		list.add("Albania");
		list.add("Algeria");
		list.add("Amazonia");
		list.add("American Samoa");
		list.add("Amsterdam And St. Paul Islands");
		list.add("Andaman Islands");
		list.add("Andorra");
		list.add("Angola");
		list.add("Anguilla");
		list.add("Antigua And Barbuda");
		list.add("Argentina");
		list.add("Armenia");
		list.add("Aruba");
		list.add("Ascension Island");
		list.add("Australia");
		list.add("Austria");
		list.add("Azerbaijan");
		list.add("Bahamas");
		list.add("Bahrain");
		list.add("Baker Island");
		list.add("Bangladesh");
		list.add("Barbados");
		list.add("Belarus");
		list.add("Belgium");
		list.add("Belize");
		list.add("Benin");
		list.add("Bhutan");
		list.add("Bolivia");
		list.add("Bonaire");
		list.add("Bosnia And Herzegovina");
		list.add("Botswana");
		list.add("Bouvet Island");
		list.add("Brazil");
		list.add("British Isles");
		list.add("Brunei");
		list.add("Bulgaria");
		list.add("Burkina Faso");
		list.add("Burma");
		list.add("Burundi");
		list.add("Cambodia");
		list.add("Cameroon");
		list.add("Canada");
		list.add("Caribbean");
		list.add("Cayman Islands");
		list.add("Central African Republic");
		list.add("Ceuta");
		list.add("Chad");
		list.add("Channel Islands");
		list.add("Chile");
		list.add("China");
		list.add("Christmas Island");
		list.add("Cocos Islands");
		list.add("Colombia");
		list.add("Comoros");
		list.add("Congo, Democratic Republic");
		list.add("Congo, Republic");
		list.add("Cook Islands");
		list.add("Corsica");
		list.add("Costa Rica");
		list.add("Cote D'ivoire");
		list.add("Croatia");
		list.add("Crozet Islands");
		list.add("Cuba");
		list.add("Curacao");
		list.add("Cyprus");
		list.add("Czech Republic");
		list.add("Denmark");
		list.add("Dhekelia");
		list.add("Djibouti");
		list.add("Dominica");
		list.add("Dominican Republic");
		list.add("Ecuador");
		list.add("Egypt");
		list.add("El Salvador");
		list.add("Equatorial Guinea");
		list.add("Eritrea");
		list.add("Estonia");
		list.add("Ethiopia");
		list.add("Faeroe Islands");
		list.add("Falkland Islands");
		list.add("Fiji");
		list.add("Finland");
		list.add("France");
		list.add("French Guiana");
		list.add("French Polynesia");
		list.add("Gabon");
		list.add("Galapagos Islands");
		list.add("Gambia");
		list.add("Gaza Strip");
		list.add("Georgia, Republic");
		list.add("Germany");
		list.add("Ghana");
		list.add("Gibraltar");
		list.add("Gough Island");
		list.add("Greece");
		list.add("Greenland");
		list.add("Grenada");
		list.add("Guadeloupe");
		list.add("Guam");
		list.add("Guatemala");
		list.add("Guinea");
		list.add("Guinea-Bissau");
		list.add("Guyana");
		list.add("Haiti");
		list.add("Hawaii Island");
		list.add("Hawaiian Islands");
		list.add("Honduras");
		list.add("Hong Kong");
		list.add("Howland Island");
		list.add("Hungary");
		list.add("Iceland");
		list.add("India");
		list.add("Indonesia");
		list.add("Iran");
		list.add("Iraq");
		list.add("Ireland");
		list.add("Israel");
		list.add("Italy");
		list.add("Jamaica");
		list.add("Japan");
		list.add("Jarvis Island");
		list.add("Johnston Atoll");
		list.add("Jordan");
		list.add("Kahoolawe");
		list.add("Kauai");
		list.add("Kazakhstan");
		list.add("Kenya");
		list.add("Kerguelen Islands");
		list.add("Kingman Reef");
		list.add("Kiribati");
		list.add("Kosovo");
		list.add("Kuwait");
		list.add("Kyrgyzstan");
		list.add("Laeso");
		list.add("Lake Chad");
		list.add("Lake Malawi");
		list.add("Lake Tanganyika");
		list.add("Lake Victoria");
		list.add("Lanai");
		list.add("Laos");
		list.add("Latvia");
		list.add("Lebanon");
		list.add("Lesotho");
		list.add("Liberia");
		list.add("Libya");
		list.add("Liechtenstein");
		list.add("Lithuania");
		list.add("Luxembourg");
		list.add("Macau");
		list.add("Macedonia");
		list.add("Madagascar");
		list.add("Madeira");
		list.add("Malawi");
		list.add("Malaysia");
		list.add("Maldives");
		list.add("Mali");
		list.add("Malta");
		list.add("Marshall Islands");
		list.add("Martinique");
		list.add("Maui");
		list.add("Mauritania");
		list.add("Mauritius");
		list.add("Melanesia");
		list.add("Mexico");
		list.add("Micronesia");
		list.add("Midway Atoll");
		list.add("Moldova");
		list.add("Molokai");
		list.add("Monaco");
		list.add("Mongolia");
		list.add("Montenegro");
		list.add("Montserrat");
		list.add("Morocco");
		list.add("Mozambique");
		list.add("Namibia");
		list.add("Nauru");
		list.add("Navassa Island");
		list.add("Nepal");
		list.add("Netherlands Antilles");
		list.add("Netherlands");
		list.add("New Caledonia");
		list.add("New Zealand");
		list.add("Nicaragua");
		list.add("Nicobar Islands");
		list.add("Niger");
		list.add("Nigeria");
		list.add("Niihau");
		list.add("Niue");
		list.add("Norfolk Island");
		list.add("North Korea");
		list.add("Northern Mariana Islands");
		list.add("Norway");
		list.add("Oahu");
		list.add("Okinawa");
		list.add("Oman");
		list.add("Pakistan");
		list.add("Palau");
		list.add("Palestine");
		list.add("Palmyra Atoll");
		list.add("Panama");
		list.add("Papua New Guinea");
		list.add("Paraguay");
		list.add("Peru");
		list.add("Philippines");
		list.add("Pitcairn Island");
		list.add("Pitcairn Islands");
		list.add("Poland");
		list.add("Polynesia");
		list.add("Portugal");
		list.add("Puerto Rico");
		list.add("Qatar");
		list.add("Red Sea");
		list.add("Reunion");
		list.add("Romania");
		list.add("Russia");
		list.add("Rwanda");
		list.add("Saba");
		list.add("Sable Island");
		list.add("Samoa");
		list.add("San Marino");
		list.add("Sao Tome And Principe");
		list.add("Sardinia");
		list.add("Saudi Arabia");
		list.add("Scandinavia");
		list.add("Senegal");
		list.add("Serbia");
		list.add("Seychelles");
		list.add("Sicily");
		list.add("Sierra Leone");
		list.add("Singapore");
		list.add("Slovakia");
		list.add("Slovenia");
		list.add("Solomon Islands");
		list.add("Somalia");
		list.add("South Africa");
		list.add("South Georgia Island");
		list.add("South Korea");
		list.add("South Orkney Islands");
		list.add("South Sandwich Islands");
		list.add("Spain");
		list.add("Spratly Islands");
		list.add("Sri Lanka");
		list.add("St Barthelemy");
		list.add("St Eustatius");
		list.add("St Helena");
		list.add("St Kitts And Nevis");
		list.add("St Lucia");
		list.add("St Maarten");
		list.add("St Martin");
		list.add("St Pierre And Miquelon");
		list.add("St Vincent And The Grenadines");
		list.add("Sudan");
		list.add("Suriname");
		list.add("Svalbard And Jan Mayen");
		list.add("Swaziland");
		list.add("Sweden");
		list.add("Switzerland");
		list.add("Syria");
		list.add("Taiwan");
		list.add("Tajikistan");
		list.add("Tanzania");
		list.add("Thailand");
		list.add("Togo");
		list.add("Tokelau");
		list.add("Tonga");
		list.add("Trinidad And Tobago");
		list.add("Tristan Da Cunha");
		list.add("Tunisia");
		list.add("Turkey");
		list.add("Turkmenistan");
		list.add("Turks And Caicos Islands");
		list.add("Tuvalu");
		list.add("Uganda");
		list.add("Ukraine");
		list.add("United Arab Emirates");
		list.add("United Kingdom");
		list.add("United States of America");
		list.add("Uruguay");
		list.add("Uzbekistan");
		list.add("Vanuatu");
		list.add("Vatican City");
		list.add("Venezuela");
		list.add("Vietnam");
		list.add("Virgin Islands");
		list.add("Wake Island");
		list.add("Wallis And Futuna Islands");
		list.add("Yemen");
		list.add("Zambia");
		list.add("Zanzibar");
		list.add("Zimbabwe");
		return list;
	}

	
	public static ArrayList<String> resolutionList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Scale");
		list.add("Distance");
		return list;
	}

	
	public static ArrayList<String> roleList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Author");
		list.add("Custodian");
		list.add("Distributor");
		list.add("Originator");
		list.add("Owner");
		list.add("Point of Contact");
		list.add("Principal Investigator");
		list.add("Processor");
		list.add("Publisher");
		list.add("Resource Provider");
		list.add("User");
		return list;
	}
	
	
	public static HashMap<String, String> languageMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Bulgarian", "bul");
		map.put("Czech", "ces");
		map.put("Danish", "dan");
		map.put("Dutch", "dut");
		map.put("English", "eng");
		map.put("Estonian", "est");
		map.put("Finnish", "fin");
		map.put("French", "fre");
		map.put("German", "ger");
		map.put("Greek", "gre");
		map.put("Hungarian", "hun");
		map.put("Irish", "gle");
		map.put("Italian", "ita");
		map.put("Latvian", "lav");
		map.put("Lithuanian", "lit");
		map.put("Maltese", "mlt");
		map.put("Polish", "pol");
		map.put("Portuguese", "por");
		map.put("Romanian", "rum");
		map.put("Slovak", "slo");
		map.put("Slovenian", "slv");
		map.put("Spanish", "spa");
		map.put("Swedish", "swe");
		return map;
	}

	
	public static HashMap<String, String> categoriesMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Biota", "biota");
		map.put("Boundaries", "boundaries");
		map.put("Climatology / Meteorology / Atmosphere", "climatologyMeteorologyAtmosphere");
		map.put("Economy", "economy");
		map.put("Elevation", "elevation");
		map.put("Environment", "environment");
		map.put("Farming", "farming");
		map.put("Geoscientific Information", "geoscientificInformation");
		map.put("Health", "health");
		map.put("Imagery / Base Maps / Earth Cover", "imageryBaseMapsEarthCover");
		map.put("Intelligence / Military", "intelligenceMilitary");
		map.put("Inland Waters", "inlandWaters");
		map.put("Location", "location");
		map.put("Oceans", "oceans");
		map.put("Planning / Cadastre", "planningCadastre");
		map.put("Society", "society");
		map.put("Structure", "structure");
		map.put("Transportation", "transportation");
		map.put("Utilities / Communication", "utilitiesCommunication");
		return map;
	}

	
	public static HashMap<String, String> keywordsMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Agricultural engineering", "Agricultural engineering");
		map.put("Agricultural plant science", "Agricultural plant science");
		map.put("Food science", "Food science");
		map.put("Forest science", "Forest science");
		map.put("Soils", "Soils");
		map.put("Aerosols", "Aerosols");
		map.put("Air quality", "Air quality");
		map.put("Altitude", "Altitude");
		map.put("Atmospheric chemistry", "Atmospheric chemistry");
		map.put("Atmospheric electricity", "Atmospheric electricity");
		map.put("Atmospheric phenomena", "Atmospheric phenomena");
		map.put("Atmospheric pressure", "Atmospheric pressure");
		map.put("Atmospheric radiation", "Atmospheric radiation");
		map.put("Atmospheric temperature", "Atmospheric temperature");
		map.put("Atmospheric water vapor", "Atmospheric water vapor");
		map.put("Atmospheric winds", "Atmospheric winds");
		map.put("Clouds", "Clouds");
		map.put("Precipitation", "Precipitation");
		map.put("Ecological dynamics", "Ecological dynamics");
		map.put("Terrestrial ecosystems", "Terrestrial ecosystems");
		map.put("Vegetation", "Vegetation");
		map.put("Frozen ground", "Frozen ground");
		map.put("Glaciers / Ice sheet", "Glaciers / Ice sheet");
		map.put("Sea ice", "Sea ice");
		map.put("Snow / Ice", "Snow / Ice");
		map.put("Erosion / Sedimentation", "Erosion / Sedimentation");
		map.put("Geomorphology", "Geomorphology");
		map.put("Land temperature", "Land temperature");
		map.put("Land use / Land cover", "Land use / Land cover");
		map.put("Landscape", "Landscape");
		map.put("Surface radiative properties", "Surface radiative properties");
		map.put("Topography", "Topography");
		map.put("Bathymetry", "Bathymetry / Seafloor topography");
		map.put("Coastal processes", "Coastal processes");
		map.put("Marine environment", "Marine environment");
		map.put("Marine geophysics", "Marine geophysics");
		map.put("Ocean waves", "Ocean waves");
		map.put("Ocean winds", "Ocean winds");
		map.put("Sea surface topography", "Sea surface topography");
		map.put("Tides", "Tides");
		map.put("Water quality", "Water quality");
		map.put("Geodetics", "Geodetics");
		map.put("Geomagnetism", "Geomagnetism");
		map.put("Geomorphic landforms", "Geomorphic landforms / processes");
		map.put("Gravity", "Gravity / gravitational field");
		map.put("Gamma ray", "Gamma ray");
		map.put("Infrared wavelengths", "Infrared wavelengths");
		map.put("LIDAR", "LIDAR");
		map.put("Microwave", "Microwave");
		map.put("RADAR", "RADAR");
		map.put("Radio wave", "Radio wave");
		map.put("Ultraviolet wavelengths", "Ultraviolet wavelengths");
		map.put("Visible wavelengths", "Visible wavelengths");
		map.put("X-ray", "X-ray");
		map.put("Ionosphere / Magnetosphere", "Ionosphere / Magnetosphere dynamics");
		map.put("Solar activity", "Solar activity");
		map.put("Solar energetic particle", "Solar energetic particle");
		map.put("Ground water", "Ground water");
		map.put("Surface water", "Surface water");
		map.put("Water quality / chemistry", "Water quality / chemistry");
		return map;
	}

	
	public static HashMap<String, String> operatorMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("","");
		map.put("Alfred Wegener Institute (AWI)", "AWI");
		map.put("Consiglio Nazionale delle Ricerche (CNR) - ISAFoM", "CNR-ISAFoM");
		map.put("Consiglio Nazionale delle Ricerche (CNR) - IMAA", "CNR-IMAA");
		map.put("Deutsches Zentrum fur Luft- und Raumfahrt (DLR)", "DLR");
		map.put("ENVISCOPE", "Enviscope");
		map.put("Facility for Airborne Atmospheric Measurements (FAAM)", "FAAM");
		map.put("Freie Universitat Berlin (FUB) - ISS", "FUB-ISS");
		map.put("Instituto Nacional de Tecnica Aeroespacial (INTA)", "INTA");
		map.put("Karlsruhe Institute of Technology (KIT) - IMK-IFU", "KIT-IMK-IFU");
		map.put("Natural Environment Research Council (NERC) - ARSF", "NERC-ARSF");
		map.put("Natural Environment Research Council (NERC) - BAS", "NERC-BAS");
		map.put("Service des Avions Francais Instrumentés pour la Recherche en Environnement (SAFIRE)", "SAFIRE");
		map.put("The University of Edinburgh (UEDIN) - IAES", "UEDIN-IAES");
		return map;
	}

	
	public static HashMap<String, String> roleMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Author", "author");
		map.put("Custodian", "custodian");
		map.put("Distributor", "distributor");
		map.put("Originator", "originator");
		map.put("Owner", "owner");
		map.put("Point of Contact", "pointOfContact");
		map.put("Principal Investigator", "principalInvestigator");
		map.put("Processor", "processor");
		map.put("Publisher", "publisher");
		map.put("Resource Provider", "resourceProvider");
		map.put("User", "user");
		return map;
	}

	
	public static HashMap<String, String> typeMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Dataset", "dataset");
		map.put("Series", "series");
		return map;
	}

	
	public static HashMap<String, String> unitMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Metre(s)", "m");
		map.put("Centimetre(s)", "cm");
		map.put("Kilometre(s)", "km");
		map.put("Decimal degree(s)", "dd");
		return map;
	}
	
	
	public static ArrayList<TextBoxBase> allRequiredTextboxes() {
		ArrayList<TextBoxBase> list = new ArrayList<TextBoxBase>();
		list.add(Emc_eufar.identTitleBox);
		list.add(Emc_eufar.identAbstractAre);
		list.add(Emc_eufar.identLocatorBox);
		list.add(Emc_eufar.identIdentifierBox);
		list.add(Emc_eufar.airManufacturerBox);
		list.add(Emc_eufar.airTypeBox);
		list.add(Emc_eufar.airOperatorBox);
		list.add(Emc_eufar.airRegistrationBox);
		list.add(Emc_eufar.airInstNameBox);
		list.add(Emc_eufar.airInstManufacturerBox);
		list.add(Emc_eufar.geoNorthBox);
		list.add(Emc_eufar.geoWestBox);
		list.add(Emc_eufar.geoEastBox);
		list.add(Emc_eufar.geoSouthBox);
		list.add(Emc_eufar.useConditionsBox);
		list.add(Emc_eufar.useLimitationsBox);
		list.add(Emc_eufar.orgPartyBox);
		list.add(Emc_eufar.orgEmailBox);
		list.add(Emc_eufar.metNameBox);
		list.add(Emc_eufar.metEmailBox);
		return list;
	}
	
	
	public static ArrayList<String> allCorrectFields() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("string");
		list.add("string");
		list.add("string");
		list.add("string");
		list.add("string");
		list.add("string");
		list.add("string");
		list.add("string");
		list.add("string");
		list.add("string");
		list.add("number");
		list.add("number");
		list.add("number");
		list.add("number");
		list.add("string");
		list.add("string");
		list.add("string");
		list.add("email");
		list.add("string");
		list.add("email");
		return list;
	}
	
	
	public static ArrayList<DateBox> allRequiredDateboxes() {
		ArrayList<DateBox> list = new ArrayList<DateBox>();
		list.add(Emc_eufar.refCreationDat);
		list.add(Emc_eufar.refRevisionDat);
		list.add(Emc_eufar.refStartDat);
		list.add(Emc_eufar.refEndDat);
		return list;
	}
	
	
	public static ArrayList<ListBox> allRequiredListboxes() {
		ArrayList<ListBox> list = new ArrayList<ListBox>();
		list.add(Emc_eufar.identLanguageLst);
		list.add(Emc_eufar.identTypeLst);
		list.add(Emc_eufar.airCountryLst);
		list.add(Emc_eufar.geoLocationLst);
		list.add(Emc_eufar.geoDetailLst);
		list.add(Emc_eufar.geoResolutionLst);
		list.add(Emc_eufar.geoUnitLst);
		list.add(Emc_eufar.orgRoleLst);
		list.add(Emc_eufar.metLanguageLst);
		return list;
	}
	
	
	public static ArrayList<HorizontalPanel> allRequiredClassCheckboxes() {
		ArrayList<HorizontalPanel> list = new ArrayList<HorizontalPanel>();
		list.add(Emc_eufar.classBiotaCheck);
		list.add(Emc_eufar.classBoundariesCheck);
		list.add(Emc_eufar.classClimatologyCheck);
		list.add(Emc_eufar.classEconomyCheck);
		list.add(Emc_eufar.classElevationCheck);
		list.add(Emc_eufar.classEnvironmentCheck);
		list.add(Emc_eufar.classFarmingCheck);
		list.add(Emc_eufar.classInformationCheck);
		list.add(Emc_eufar.classHealthCheck);
		list.add(Emc_eufar.classImageryCheck);
		list.add(Emc_eufar.classIntelligenceCheck);
		list.add(Emc_eufar.classWatersCheck);
		list.add(Emc_eufar.classLocationCheck);
		list.add(Emc_eufar.classOceansCheck);
		list.add(Emc_eufar.classPlanningCheck);
		list.add(Emc_eufar.classSocietyCheck);
		list.add(Emc_eufar.classStructureCheck);
		list.add(Emc_eufar.classTransportationCheck);
		list.add(Emc_eufar.classCommunicationCheck);
		return list;
	}
	
	
	public static ArrayList<HorizontalPanel> allRequiredKeyCheckboxes() {
		ArrayList<HorizontalPanel> list = new ArrayList<HorizontalPanel>();
		list.add(Emc_eufar.classAgEngineeringCheck);
		list.add(Emc_eufar.classAgPlantCheck);
		list.add(Emc_eufar.classAgFoodCheck);
		list.add(Emc_eufar.classAgForestCheck);
		list.add(Emc_eufar.classAgSoilsCheck);
		list.add(Emc_eufar.classAtAerosolsCheck);
		list.add(Emc_eufar.classAtAirCheck);
		list.add(Emc_eufar.classAtAltitudeCheck);
		list.add(Emc_eufar.classAtChemistryCheck);
		list.add(Emc_eufar.classAtElectricityCheck);
		list.add(Emc_eufar.classAtPhenomenaCheck);
		list.add(Emc_eufar.classAtPressureCheck);
		list.add(Emc_eufar.classAtRadiationCheck);
		list.add(Emc_eufar.classAtTemperatureCheck);
		list.add(Emc_eufar.classAtVapourCheck);
		list.add(Emc_eufar.classAtWindsCheck);
		list.add(Emc_eufar.classAtCloudsCheck);
		list.add(Emc_eufar.classAtPrecipitationCheck);
		list.add(Emc_eufar.classBiDynamicsCheck);
		list.add(Emc_eufar.classBiEcosystemsCheck);
		list.add(Emc_eufar.classBiVegetationCheck);
		list.add(Emc_eufar.classCrGroundCheck);
		list.add(Emc_eufar.classCrGlaciersCheck);
		list.add(Emc_eufar.classCrIceCheck);
		list.add(Emc_eufar.classCrSnowCheck);
		list.add(Emc_eufar.classLsErosionCheck);
		list.add(Emc_eufar.classLsGeomorphologyCheck);
		list.add(Emc_eufar.classLsTemperatureCheck);
		list.add(Emc_eufar.classLsCoverCheck);
		list.add(Emc_eufar.classLsLandscapeCheck);
		list.add(Emc_eufar.classLsSurfaceCheck);
		list.add(Emc_eufar.classLsTopographyCheck);
		list.add(Emc_eufar.classOcBathymetryCheck);
		list.add(Emc_eufar.classOcProcessesCheck);
		list.add(Emc_eufar.classOcEnvironmentCheck);
		list.add(Emc_eufar.classOcGeophysicsCheck);
		list.add(Emc_eufar.classOcWavesCheck);
		list.add(Emc_eufar.classOcWindsCheck);
		list.add(Emc_eufar.classOcTopographyCheck);
		list.add(Emc_eufar.classOcTidesCheck);
		list.add(Emc_eufar.classOcQualityCheck);
		list.add(Emc_eufar.classSeGeodeticsCheck);
		list.add(Emc_eufar.classSeGeomagnetismCheck);
		list.add(Emc_eufar.classSeLandformsCheck);
		list.add(Emc_eufar.classSeGravityCheck);
		list.add(Emc_eufar.classSpGammaCheck);
		list.add(Emc_eufar.classSpInfraredCheck);
		list.add(Emc_eufar.classSpLidarCheck);
		list.add(Emc_eufar.classSpMicrowaveCheck);
		list.add(Emc_eufar.classSpRadarCheck);
		list.add(Emc_eufar.classSpRadioCheck);
		list.add(Emc_eufar.classSpUltravioletCheck);
		list.add(Emc_eufar.classSpVisibleCheck);
		list.add(Emc_eufar.classSpXrayCheck);
		list.add(Emc_eufar.classInIonosphereCheck);
		list.add(Emc_eufar.classInActivityCheck);
		list.add(Emc_eufar.classInParticleCheck);
		list.add(Emc_eufar.classThGroundCheck);
		list.add(Emc_eufar.classThSurfaceCheck);
		list.add(Emc_eufar.classThChemistryCheck);
		return list;
	}
	
	
	public static HashMap<String, String> instrumentMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("1.108","Grimm Technologies Inc.");
        map.put("1.109","Grimm Technologies Inc.");
        map.put("1011C","Buck Research Instruments L.L.C.");
        map.put("1201","Rosemount");
        map.put("1221","Rosemount");
        map.put("2D-C","Particle Measuring Systems");
        map.put("2D-P","Particle Measuring Systems");
        map.put("2D-S","Stratton Park Engineering Company");
        map.put("2D2C","Particle Measuring Systems");
        map.put("2DGB2","Particle Measuring Systems");
        map.put("3150","ICSensors / Measurement Specialties");
        map.put("4PI-SR","METEO-CONSULT");
        map.put("5-hole Turbulence Probe","Other");
        map.put("858AJ28","Rosemount");
        map.put("AA-300","Sperry");
        map.put("AADC II","Scintrex Ltd");
        map.put("AARP","University of Manchester");
        map.put("ADA-100-PDPA","TSI Inc.");
        map.put("AE-42","Magee Scientific");
        map.put("AE-52 Aethelometer","Magee Scientific");
        map.put("AHV16","Thales");
        map.put("AHV8","TRT");
        map.put("AIMMS-20","Aventech Inc.");
        map.put("AIRINS","iXBlue");
        map.put("AL5002","Aero-Laser Gmbh");
        map.put("AL5003","Aero-Laser Gmbh");
        map.put("ALS-450","Leosphere");
        map.put("ALS50","Leica Geosystems");
        map.put("ARIES","Met Office / ABB Bomem");
        map.put("AT4","Javad GNSS Inc.");
        map.put("AVAPS","Vaisala");
        map.put("Airborne Hyperspectral Scanner","Argon ST");
        map.put("Airborne Prism EXperiment","ESA");
        map.put("Airborne Thematic Mapper","DLR");
        map.put("Airborne Visibility Meter","HSS Inc.");
        map.put("Aircraft Systems","Other");
        map.put("AisaDUAL","SPECIM");
        map.put("AisaEAGLE 1K","SPECIM");
        map.put("AisaEAGLE","SPECIM");
        map.put("AisaFENIX 1K","SPECIM");
        map.put("AisaFENIX","SPECIM");
        map.put("AisaOWL","SPECIM");
        map.put("B270","Setra Systems");
        map.put("BAT","NOAA / Airborne Research Australia");
        map.put("BCP","Droplet Measurement Technologies");
        map.put("CAPS - LWC","Droplet Measurement Technologies");
        map.put("CAPS","Droplet Measurement Technologies");
        map.put("CAS Spectrometer","Baumgardner et al., 2001");
        map.put("CASI","ITRES Research Ltd");
        map.put("CASI-1500i","ITRES Research Ltd");
        map.put("CASI-550","ITRES Research Ltd");
        map.put("CCN Counter","Droplet Measurement Technologies");
        map.put("CCNS4","IGI mbH");
        map.put("CDP-2","Droplet Measurement Technologies");
        map.put("CEV","INTA");
        map.put("CGR-4","Kipp & Zonen");
        map.put("CIP","Droplet Measurement Technologies");
        map.put("CIP-100","Droplet Measurement Technologies");
        map.put("CIP-15","Droplet Measurement Technologies");
        map.put("CMP-22","Kipp & Zonen");
        map.put("COPAS","University of Mainz");
        map.put("CPI","Stratton Park Engineering Company");
        map.put("CR-1","Buck Research Instruments L.L.C.");
        map.put("CR-2","Buck Research Instruments L.L.C.");
        map.put("CRISTA New Frontiers","ForschungsZentrum Juelich");
        map.put("CW-QCLAS","Aerodyne Research Inc.");
        map.put("Campbell Q7.1","Campbell Scientific");
        map.put("Cloud Imaging Probe","Other");
        map.put("Counterflow Virtual Impactor","Met Office");
        map.put("DEIMOS","Met Office");
        map.put("Dewtrak-137","EdgeTech Instruments");
        map.put("Dewtrak-200","EdgeTech Instruments");
        map.put("dIGIcam K14","IGI mbH");
        map.put("E-SAR","DLR");
        map.put("ERS microjoule Lidar EMB-ER 1/2","ERS");
        map.put("Eppley PS Pyranometer","The Eppley Laboratory Inc.");
        map.put("Eppley Pyrgeometer","The Eppley Laboratory Inc.");
        map.put("Everest 4000.4ZL","Everest Interscience Inc.");
        map.put("F-SAR","DLR");
        map.put("FAGE","University of Leeds");
        map.put("FLIR M40","FLIR Systems Inc.");
        map.put("FLIR SC3000","FLIR Systems Inc.");
        map.put("FS2 Relative Humidity","Aerodata GmbH");
        map.put("FSSP-100","Particle Measuring Systems");
        map.put("FSSP-100ER","Particle Measuring Systems");
        map.put("FSSP-300","Particle Measuring Systems");
        map.put("FTIR","Midac Inc.");
        map.put("FUBISS","Free University of Berlin");
        map.put("FUBISS-ASA2","Free University Berlin");
        map.put("Filter Sampler","Other");
        map.put("Flask Array Sampler","Atmospheric Observing Systems Inc.");
        map.put("Flask Sampling System","MetAir");
        map.put("Flourescence Water Vapour Sensor","Met Office");
        map.put("Formaldehyde Detectors","Other");
        map.put("GASCOD-A","FISBAT-CNR");
        map.put("GE-1011B","General Eastern");
        map.put("GE-1011C","General Eastern");
        map.put("GR-DV400","JVC");
        map.put("Garmin GPS","Garmin");
        map.put("Gyro-4","Javad GNSS Inc.");
        map.put("HAGAR","University of Frankfurt");
        map.put("HALOX","Forschungszentrum Julich GmbH");
        map.put("HMP","Vaisala / Rosemount");
        map.put("HMP233","Vaisala");
        map.put("HRSC AX","DLR");
        map.put("HVPS","Stratton Park Engineering Company");
        map.put("High Accuracy INS","SAGEM");
        map.put("Hyperspectral Imagery Sensor","DLR");
        map.put("HySpecScan","Analytical Spectral Devices Inc.");
        map.put("IR Gas analyzer","Edinburgh Sensors");
        map.put("IR/UV Line Scanner","OPTIMARE Systems GmbH");
        map.put("IRCAM","Jenoptik AG");
        map.put("IS-2","Interspect Ltd.");
        map.put("IS-3","Interspect Ltd.");
        map.put("IS-4","Interspect Ltd.");
        map.put("IS2+","Interspect Ltd.");
        map.put("Ice Nucleus Counter","University of Manchester");
        map.put("J-W LWC","Johnson-Williams");
        map.put("JO1D / JNO2 Photometer","Unknown");
        map.put("KA-131","Bendix King");
        map.put("KT-15","Heitronics Infrarot Messtechnik");
        map.put("King Probe","Particle Measuring Systems");
        map.put("Kistler 3 axes accelerometers","Kistler Instrumente AG");
        map.put("LASAIR-5295","Particle Measurement Systems");
        map.put("LD90","Riegl Laser Measurement Systems GmbH");
        map.put("LGR 907","Los Gatos Research Inc.");
        map.put("LGR DLT100","Los Gatos Research Inc.");
        map.put("LI-COR 6262","LI-COR");
        map.put("LI-COR 7500","LI-COR");
        map.put("LI-COR Pyranometer","LI-COR");
        map.put("LI-COR Quantum Sensor","LI-COR");
        map.put("LMS-Q280","Riegl Laser Measurement Systems GmbH");
        map.put("LMS-Q560","Riegl Laser Measurement Systems GmbH");
        map.put("LMS-Q680i","Riegl Laser Measurement Systems GmbH");
        map.put("LTC 0600","Philipps");
        map.put("LTN 90-100","Litton");
        map.put("Lasernav YG1761B","Honeywell");
        map.put("Lyman-alpha HMS-2","Buck Research Instruments L.L.C.");
        map.put("Lyman-alpha L-5","Buck Research Instruments L.L.C.");
        map.put("MARSS","Met Office");
        map.put("MS4100","Duncantech");
        map.put("MetAir NOxTOy","MetAir AG");
        map.put("Meteorological Sensor Package","DLR");
        map.put("Mobile Flux Platform","IBIMET CNR");
        map.put("NO CLD","DLR");
        map.put("Nevzorov IVO-2a","Sky Tech Research Inc.");
        map.put("Nevzorov","Sky Tech Research Inc.");
        map.put("OAP-200X","Particle Measuring Systems");
        map.put("OAP-230X","Particle Measuring Systems");
        map.put("OAP-230Y","Particle Measuring Systems");
        map.put("OEM4-G2","NovAtel Inc.");
        map.put("ORAC","University of Leeds");
        map.put("Others","Other");
        map.put("Ozone Lidar EXperiment","DLR");
        map.put("Ozone Monitor 202","2B Technologies");
        map.put("Ozone Monitor","2B Technologies");
        map.put("PAN GC","Ai Qualitek Ltd");
        map.put("PCASP-100X","Droplet Measurement Technologies");
        map.put("PCASP-SPP200","Droplet Measurement Technologies");
        map.put("PELICAN","ONERA");
        map.put("PERCA","University of Leceister");
        map.put("POS AV 510","Applanix");
        map.put("POSAV-410","Applanix");
        map.put("PRT 6","Barnes");
        map.put("PSAP","Radiance Research Inc.");
        map.put("PSI-O3","Paul Scherrer Institut");
        map.put("PT","Rosemount");
        map.put("PT100","Rosemount");
        map.put("PT102AL","Rosemount");
        map.put("PT102BL","Rosemount");
        map.put("PT102BW","Rosemount");
        map.put("PT102DB1 AG","Rosemount");
        map.put("PT102E2 AL","Rosemount");
        map.put("PT102E4 AL","Rosemount");
        map.put("PT50","Rosemount");
        map.put("PT500","Rosemount");
        map.put("PTR-MS","University of East Anglia");
        map.put("PVM-100","Gerber Scientific Inc.");
        map.put("Peroxide Detectors","Other");
        map.put("Pitot Probe","Other");
        map.put("Polifemo M21","SUPERELECTRIC s.r.l.");
        map.put("Portable Lidar System","University of Munich");
        map.put("R4903","Met One Instruments");
        map.put("RALI","LATMOS");
        map.put("RALI","LATMOS");
        map.put("RCD105","Leica-Geosystems");
        map.put("RDR-4B","Honeywell");
        map.put("RMK A 30/23","Zeiss");
        map.put("RMK A","Zeiss");
        map.put("RR-0150","RadianceResearch");
        map.put("RT3102","Oxford Technical Solutions");
        map.put("Radar Echo Sounding System","AWI");
        map.put("S-5002","Sistemas Instalaciones Redes S.A.");
        map.put("S-5006","Sistemas Instalaciones Redes S.A.");
        map.put("S-5012","Sistemas Instalaciones Redes S.A.");
        map.put("S3000","Michell Instruments GmbH");
        map.put("S56","Micro-g LaCoste");
        map.put("SA 41M","Environment S.A.");
        map.put("SETHI","ONERA");
        map.put("SFIM 3 axes accelerometers","SFIM");
        map.put("SID-2","University of Hertfordshire");
        map.put("SIELETERS","ONERA");
        map.put("SIOUX","DLR");
        map.put("SMPS","Grimm Technologies Inc.");
        map.put("SP-2","Droplet Measurement Technologies");
        map.put("SPEC Hawkeye","Stratton Park Engineering Company");
        map.put("SWS","Met Office");
        map.put("Solent HS","Gill Instruments Ltd");
        map.put("Solent HS-100","Gill Instruments Ltd");
        map.put("Solent HS-50","Gill Instruments Ltd");
        map.put("Sony Video Camera","Sony");
        map.put("Sundstrand 3 axes accelerometers","Sundstrand");
        map.put("TABI320","ITRES Research Ltd");
        map.put("TAFTS","Imperial College London");
        map.put("TASI-600","ITRES Research Ltd");
        map.put("TDLAS","Imperial College London");
        map.put("TDLAS","National Physical Laboratory");
        map.put("TECO 42C","ThermoScientific");
        map.put("TECO 42C","ThermoScientific");
        map.put("TECO 42S","ThermoScientific");
        map.put("TECO 43C","ThermoScientific");
        map.put("TECO 48","ThermoScientific");
        map.put("TECO 48CS","ThermoScientific");
        map.put("TECO 49PS","ThermoScientific");
        map.put("TECO 49S","ThermoScientific");
        map.put("TOF-AMS","Aerodyne Research Inc.");
        map.put("TP3-S","Meteolabor AG");
        map.put("TS9260","NEC Corporation");
        map.put("TSI-3007","TSI Inc.");
        map.put("TSI-3010","TSI Inc.");
        map.put("TSI-3022","TSI Inc.");
        map.put("TSI-3025","TSI Inc.");
        map.put("TSI-3563","TSI Inc.");
        map.put("TSI-3776","TSI Inc.");
        map.put("TSI-3786","TSI Inc.");
        map.put("Total Water Content","Met Office");
        map.put("Trimble 2101","Trimble Navigation");
        map.put("Trimble 4000 SSI","Trimble Navigation");
        map.put("Trimble TNL 2000","Trimble Navigation");
        map.put("UHSAS-A","Droplet Measurement Technologies");
        map.put("ULISS 45i","SAGEM");
        map.put("ULS","Laser Technology Inc.");
        map.put("UMP40","Sextant");
        map.put("VACC","University of Leeds");
        map.put("VIS Line Scanner","OPTIMARE Systems GmbH");
        map.put("VIS/NIR Spectrometer ","Other");
        map.put("VOC GC","Airmotec AG");
        map.put("Video Camera","Other");
        map.put("WIND","DLR / CNRS / INSU");
        map.put("WVSS-II","SpectraSensors Inc.");
        map.put("Walz UV Pyranometer","Heinz Walz Company");
        map.put("Whole Air Sampling System","University of York");
        map.put("XR5","Navstar Electronics");
		return map;
	}

	
	public static Image popupImage(final String string) {
		final Image image = new Image("icons/" + string + "_popup_icon.png");
		return image;
	}
	
	
	public static Image smallImage(final String string) {
		final Image image = new Image("icons/" + string + "_icon.png");
		return image;
	}
	
	
	public static String[][] aircraftInfo() {
		String[][] string = {
			{"Do your choice...", " ", " ", " ", " ", " ", "EUFAR"},
			{"AWI - POLAR 5", "Basler Turbo Conversions", "BT-67", "Alfred Wegener Institute (AWI)", "Germany", "C-GAWI",
			"Alfred Wegener Institute"},
			{"CNR - ERA Skyarrow", "3I / Magnaghi Aeronautica", "Sky Arrow 650", "Consiglio Nazionale delle Ricerche (CNR) - ISAFoM", "Italy",
				"CNR, Istituto per i Sistemi Agricoli e Forestali del Mediterraneo"},
			{"CNR - Partenavia", "Partenavia / Vulcanair", "P-68", "Consiglio Nazionale delle Ricerche (CNR) - IMAA", "Italy", "I-ALTM", 
				"CNR, Istituto di Metodologie per l'Analisi Ambientale"},
			{"CzechGlobe - C 208", "Cessna Aircraft Company", "Cessna 208", "Ústav výzkumu globální změny AV ČR", "Czech Republic", 
					"OK-CZG", "CzechGlobe"},
			{"DLR - C 208", "Cessna Aircraft Company", "Cessna 208", "Deutsches Zentrum fur Luft- und Raumfahrt (DLR)", "Germany", "D-FDLR", 
				"DLR, CC-BY 3.0"},
			{"DLR - DO 228 D-CFFU", "Dornier Flugzeugwerke", "DO-228", "Deutsches Zentrum fur Luft- und Raumfahrt (DLR)", "Germany", "D-CFFU", 
				"DLR, CC-BY 3.0"},
			{"DLR - DO 228 D-CODE", "Dornier Flugzeugwerke", "DO-228", "Deutsches Zentrum fur Luft- und Raumfahrt (DLR)", "Germany", 
				"D-CODE", "DLR, CC-BY 3.0"},
			{"DLR - Falcon 20", "Dassault Aviation", "Mystere/Falcon 20", "Deutsches Zentrum fur Luft- und Raumfahrt (DLR)", "Germany", 
				"D-CMET", "DLR, CC-BY 3.0"},
			{"ENVISCOPE - Learjet 35", "Learjet Aircraft Company", "Learjet 35", "ENVISCOPE", "Germany", "D-CGFD", "ENVISCOPE GmbH"},
			{"ENVISCOPE - Partenavia", "Partenavia / Vulcanair", "P-68", "ENVISCOPE", "Germany", "D-GERY", "ENVISCOPE GmbH"},
			{"FAAM - BAe 146", "BAE Systems", "BAe-146", "Facility for Airborne Atmospheric Measurements (FAAM)", "United Kingdom", "G-LUXE", 
				"NERC, Facility for Airborne Atmospheric Measurements"},
			{"FUB - C 207", "Cessna Aircraft Company", "Cessna 207", "Freie Universitat Berlin (FUB) - ISS", "Germany", "D-EAFU", 
				"Institut fur Weltraumwissenschaften"},
			{"INTA - CASA 212 AR", "Construcciones Aeronauticas S.A.", "CASA-212", "Instituto Nacional de Tecnica Aeroespacial (INTA)", "Spain", 
				"EC-DTV", "Instituto Nacional de Tecnica Aeroespacial"},
			{"INTA - CASA 212 RS", "Construcciones Aeronauticas S.A.", "CASA-212", "Instituto Nacional de Tecnica Aeroespacial (INTA)", "Spain", 
				"EC-DUQ", "Instituto Nacional de Tecnica Aeroespacial"},
			{"KIT - Enduro", "Ultraleichtflug Schmidtler", "Enduro", "Karlsruhe Institute of Technology (KIT) - IMK-IFU", "Germany", "D-MIFU", 
				"KIT, Institute of Meteorology and Climate Research"},
			{"NERC - Twin Otter VP-FAZ", "De Havilland Canada", "DHC-6", "Natural Environment Research Council (NERC) - BAS", "United Kingdom", 
				"VP-FAZ", "NERC, British Antarctic Survey"},
			{"NERC - Twin Otter VP-FBL", "De Havilland Canada", "DHC-6", "Natural Environment Research Council (NERC) - BAS", "United Kingdom", 
				"VP-FBL", "NERC, British Antarctic Survey"},
			{"SAFIRE - ATR 42", "ATR", "ATR-42", "Service des Avions Francais Instrumentés pour la Recherche en Environnement (SAFIRE)", 
				"France", "F-HMTO", "SAFIRE"},
			{"SAFIRE - Falcon 20", "Dassault Aviation", "Mystere/Falcon 20", "Service des Avions Francais Instrumentés pour la Recherche en "
				+ "Environnement (SAFIRE)", "France", "F-GBTM","Collection Saspozatlse"},
			{"SAFIRE - PIPER Aztec", "Piper Aircraft", "PA23", "Service des Avions Francais Instrumentés pour la Recherche en Environnement "
				+ "(SAFIRE)", "France", "F-BLEB", "SAFIRE"},
			{"UEDIN - ECO Dimona", "Diamond Aircraft Industries", "HK36TTC ECO Dimona", "The University of Edinburgh (UEDIN) - IAES", 
				"United Kingdom", "G-GEOS", "UEDIN, Airborne GeoSciences"},
		};
		return string;
	}
	
	
	public static ArrayList<ImageResource> aircraftImages() {
		ArrayList<ImageResource> list = new ArrayList<ImageResource>();
		list.add(Emc_eufar.resources.eufarLogo());
		list.add(Emc_eufar.resources.polarAwi());
		list.add(Emc_eufar.resources.eraIsafom());
		list.add(Emc_eufar.resources.partenaviaCnr());
		list.add(Emc_eufar.resources.cessnaCzechglobe());
		list.add(Emc_eufar.resources.cessnaDlr());
		list.add(Emc_eufar.resources.do1Dlr());
		list.add(Emc_eufar.resources.do2Dlr());
		list.add(Emc_eufar.resources.falconDlr());
		list.add(Emc_eufar.resources.learjetEnviscope());
		list.add(Emc_eufar.resources.partenaviaEnviscope());
		list.add(Emc_eufar.resources.baeFaam());
		list.add(Emc_eufar.resources.cessnaFub());
		list.add(Emc_eufar.resources.casaInta());
		list.add(Emc_eufar.resources.casaInta());
		list.add(Emc_eufar.resources.enduroKit());
		list.add(Emc_eufar.resources.twinotterNerc());
		list.add(Emc_eufar.resources.twinotter2Nerc());
		list.add(Emc_eufar.resources.atrSafire());
		list.add(Emc_eufar.resources.falconSafire());
		list.add(Emc_eufar.resources.aztecSafire());
		list.add(Emc_eufar.resources.diamondUedin());
		return list;
	}
	
	
	public static interface Resources extends ClientBundle {
        
        @Source("images/icons/earth_globe.png")
        ImageResource earth();

        @Source("images/icons/menu_tab_arrow_v3.png")
        ImageResource menu();
        
        @Source("images/eufar_aircraft/logo_eufar_emc_v2.png")
        ImageResource eufarLogo();
        
        @Source("images/eufar_aircraft/POLAR5-AWI.png")
        ImageResource polarAwi();
        
        @Source("images/eufar_aircraft/ERA-ISAFM.png")
        ImageResource eraIsafom();
        
        @Source("images/eufar_aircraft/PARTENAVIA-CNR.png")
        ImageResource partenaviaCnr();
        
        @Source("images/eufar_aircraft/C208-DLR.png")
        ImageResource cessnaDlr();
        
        @Source("images/eufar_aircraft/DO228-DLR-1.png")
        ImageResource do1Dlr();
        
        @Source("images/eufar_aircraft/DLR - DO228-2.png")
        ImageResource do2Dlr();
        
        @Source("images/eufar_aircraft/FALCON20-DLR.png")
        ImageResource falconDlr();
        
        @Source("images/eufar_aircraft/LEARJET25-ENVISCOPE-3.png")
        ImageResource learjetEnviscope();
        
        @Source("images/eufar_aircraft/PARTENAVIA68-ENVISCOPE-2.png")
        ImageResource partenaviaEnviscope();
        
        @Source("images/eufar_aircraft/BAE146-FAAM.png")
        ImageResource baeFaam();
        
        @Source("images/eufar_aircraft/C207-FUB.png")
        ImageResource cessnaFub();
        
        @Source("images/eufar_aircraft/CASA212-INTA.png")
        ImageResource casaInta();
        
        @Source("images/eufar_aircraft/ENDURO-KIT.png")
        ImageResource enduroKit();
        
        @Source("images/eufar_aircraft/TWINOTTER-NERC.png")
        ImageResource twinotterNerc();
        
        @Source("images/eufar_aircraft/TWINOTTER2-NERC.png")
        ImageResource twinotter2Nerc();
        
        @Source("images/eufar_aircraft/C208-CZECHGLOBE.png")
        ImageResource cessnaCzechglobe();
        
        @Source("images/eufar_aircraft/ATR42-SAFIRE.png")
        ImageResource atrSafire();
        
        @Source("images/eufar_aircraft/FALCON20-SAFIRE-3.png")
        ImageResource falconSafire();
        
        @Source("images/eufar_aircraft/PIPERAZTEC-SAFIRE.png")
        ImageResource aztecSafire();
        
        @Source("images/eufar_aircraft/DIAMOND-UEDIN-1.png")
        ImageResource diamondUedin();
        
        @Source("images/icons/info_icon.svg")
    	@MimeType("image/svg+xml")
    	DataResource info();
        
        @Source("images/icons/fwd_tab_arrow_v3.svg")
    	@MimeType("image/svg+xml")
    	DataResource next();
        
        @Source("images/icons/bwd_tab_arrow_v3.svg")
    	@MimeType("image/svg+xml")
    	DataResource back();
        
        @Source("images/icons/plus_icon.svg")
        @MimeType("image/svg+xml")
        DataResource plus();
        
        @Source("images/icons/del_icon.svg")
        @MimeType("image/svg+xml")
        DataResource delete();
        
        @Source("images/icons/fwdarrow_icon.svg")
        @MimeType("image/svg+xml")
        DataResource forward();
        
        @Source("images/icons/about_popup_icon.svg")
        @MimeType("image/svg+xml")
        DataResource aboutPopup();

        @Source("images/icons/info_popup_icon.svg")
        @MimeType("image/svg+xml")
        DataResource infoPopup();

        @Source("images/icons/save_popup_icon.svg")
        @MimeType("image/svg+xml")
        DataResource savePopup();
        
        @Source("images/icons/changelog_popup_icon.svg")
        @MimeType("image/svg+xml")
        DataResource changelogPopup();
        
        @Source("images/icons/open_popup_icon.svg")
        @MimeType("image/svg+xml")
        DataResource openPopup();
        
        @Source("images/icons/warning_popup_icon.svg")
        @MimeType("image/svg+xml")
        DataResource warningPopup();
        
        @Source("images/icons/inspire_popup_icon.svg")
        @MimeType("image/svg+xml")
        DataResource inspirePopup();
        
        @Source("images/icons/copyright_icon_small.svg")
        @MimeType("image/svg+xml")
        DataResource copyright();
        
        @Source("images/icons/continue_icon.svg")
        @MimeType("image/svg+xml")
        DataResource transfert();
        
    }
}