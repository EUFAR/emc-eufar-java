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
import com.google.gwt.user.client.ui.SuggestBox;
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

	
	public static HashMap<String, String> countryCodeList() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Afghanistan","AF");
		map.put("Aland Islands","AX");
		map.put("Albania","AL");
		map.put("Algeria","DZ");
		map.put("American Samoa","AS");
		map.put("Andorra","AD");
		map.put("Angola","AO");
		map.put("Anguilla","AI");
		map.put("Antarctica","AQ");
		map.put("Antigua And Barbuda","AG");
		map.put("Argentina","AR");
		map.put("Armenia","AM");
		map.put("Aruba","AW");
		map.put("Australia","AU");
		map.put("Austria","AT");
		map.put("Azerbaijan","AZ");
		map.put("Bahamas","BS");
		map.put("Bahrain","BH");
		map.put("Bangladesh","BD");
		map.put("Barbados","BB");
		map.put("Belarus","BY");
		map.put("Belgium","BE");
		map.put("Belize","BZ");
		map.put("Benin","BJ");
		map.put("Bermuda","BM");
		map.put("Bhutan","BT");
		map.put("Bolivia","BO");
		map.put("Bonaire","BQ");
		map.put("Bosnia And Herzegovina","BA");
		map.put("Botswana","BW");
		map.put("Bouvet Island","BV");
		map.put("Brazil","BR");
		map.put("British Indian Ocean Territory","IO");
		map.put("Brunei","BN");
		map.put("Bulgaria","BG");
		map.put("Burkina Faso","BF");
		map.put("Burundi","BI");
		map.put("Cabo Verde","CV");
		map.put("Cambodia","KH");
		map.put("Cameroon","CM");
		map.put("Canada","CA");
		map.put("Cayman Islands","KY");
		map.put("Central African Republic","CF");
		map.put("Chad","TD");
		map.put("Chile","CL");
		map.put("China","CN");
		map.put("Christmas Island","CX");
		map.put("Cocos Islands","CC");
		map.put("Colombia","CO");
		map.put("Comoros","KM");
		map.put("Congo, Democratic Republic","CD");
		map.put("Congo, Republic","CG");
		map.put("Cook Islands","CK");
		map.put("Costa Rica","CR");
		map.put("Cote d'Ivoire","CI");
		map.put("Croatia","HR");
		map.put("Cuba","CU");
		map.put("Curacao","CW");
		map.put("Cyprus","CY");
		map.put("Czech Republic","CZ");
		map.put("Denmark","DK");
		map.put("Djibouti","DJ");
		map.put("Dominica","DM");
		map.put("Dominican Republic","DO");
		map.put("Ecuador","EC");
		map.put("Egypt","EG");
		map.put("El Salvador","SV");
		map.put("Equatorial Guinea","GQ");
		map.put("Eritrea","ER");
		map.put("Estonia","EE");
		map.put("Ethiopia","ET");
		map.put("Falkland Islands","KF");
		map.put("Faroe Islands","FO");
		map.put("Fiji","FJ");
		map.put("Finland","FI");
		map.put("France","FR");
		map.put("French Guiana","GF");
		map.put("French Polynesia","PF");
		map.put("French Southern Territories","TF");
		map.put("Gabon","GA");
		map.put("Gambia","GM");
		map.put("Georgia, Republic","GE");
		map.put("Germany","DE");
		map.put("Ghana","GH");
		map.put("Gibraltar","GI");
		map.put("Greece","GR");
		map.put("Greenland","GL");
		map.put("Grenada","GD");
		map.put("Guadeloupe","GP");
		map.put("Guam","GU");
		map.put("Guatemala","GT");
		map.put("Guernsey","GG");
		map.put("Guinea","GN");
		map.put("Guinea-Bissau","GW");
		map.put("Guyana","GY");
		map.put("Haiti","HT");
		map.put("Heard Island and McDonald","HM");
		map.put("Holy See","VA");
		map.put("Honduras","HN");
		map.put("Hong Kong","HK");
		map.put("Hungary","HU");
		map.put("Iceland","IS");
		map.put("India","IN");
		map.put("Indonesia","ID");
		map.put("Iran","IR");
		map.put("Iraq","IQ");
		map.put("Ireland","IE");
		map.put("Isle of Man","IM");
		map.put("Israel","IL");
		map.put("Italy","IT");
		map.put("Jamaica","JM");
		map.put("Japan","JP");
		map.put("Jersey","JE");
		map.put("Jordan","JO");
		map.put("Kazakhstan","KZ");
		map.put("Kenya","KE");
		map.put("Kiribati","KI");
		map.put("Korea (the Democratic People's Republic of)","KP");
		map.put("Korea (the Republic of)","KR");
		map.put("Kuwait","KW");
		map.put("Kyrgyzstan","KG");
		map.put("Lao People's Democratic Republic (the)","LA");
		map.put("Latvia","LV");
		map.put("Lebanon","LB");
		map.put("Lesotho","LS");
		map.put("Liberia","LR");
		map.put("Libya","LY");
		map.put("Liechtenstein","LI");
		map.put("Lithuania","LT");
		map.put("Luxembourg","LU");
		map.put("Macao","MO");
		map.put("Macedonia","MK");
		map.put("Madagascar","MG");
		map.put("Malawi","MW");
		map.put("Malaysia","MY");
		map.put("Maldives","MV");
		map.put("Mali","ML");
		map.put("Malta","MT");
		map.put("Marshall Islands","MH");
		map.put("Martinique","MQ");
		map.put("Mauritania","MR");
		map.put("Mauritius","MU");
		map.put("Mayotte","YT");
		map.put("Mexico","MX");
		map.put("Micronesia","FM");
		map.put("Moldova","MD");
		map.put("Monaco","MC");
		map.put("Mongolia","MN");
		map.put("Montenegro","ME");
		map.put("Montserrat","MS");
		map.put("Morocco","MA");
		map.put("Mozambique","MZ");
		map.put("Myanmar","MM");
		map.put("Namibia","NA");
		map.put("Nauru","NR");
		map.put("Nepal","NP");
		map.put("Netherlands","NL");
		map.put("New Caledonia","NC");
		map.put("New Zealand","NZ");
		map.put("Nicaragua","NI");
		map.put("Niger","NE");
		map.put("Nigeria","NG");
		map.put("Niue","NU");
		map.put("Norfolk Island","NF");
		map.put("Northern Mariana Islands","MP");
		map.put("Norway","NO");
		map.put("Oman","OM");
		map.put("Pakistan","PK");
		map.put("Palau","PW");
		map.put("Palestine","PS");
		map.put("Panama","PA");
		map.put("Papua New Guinea","PG");
		map.put("Paraguay","PY");
		map.put("Peru","PE");
		map.put("Philippines","PH");
		map.put("Pitcairn","PN");
		map.put("Poland","PL");
		map.put("Portugal","PT");
		map.put("Puerto Rico","PR");
		map.put("Qatar","QA");
		map.put("Reunion","RE");
		map.put("Romania","RO");
		map.put("Russia","RU");
		map.put("Rwanda","RW");
		map.put("Saint Helena, Ascension and Tristan da Cunha","SH");
		map.put("Saint Kitts and Nevis","KN");
		map.put("Saint Lucia","LC");
		map.put("Saint Martin","MF");
		map.put("Saint Pierre and Miquelon","PM");
		map.put("Saint Vincent and the Grenadines","VC");
		map.put("Samoa","WS");
		map.put("San Marino","SM");
		map.put("Sao Tome And Principe","ST");
		map.put("Saudi Arabia","SA");
		map.put("Senegal","SN");
		map.put("Serbia","RS");
		map.put("Seychelles","SC");
		map.put("Sierra Leone","SL");
		map.put("Singapore","SG");
		map.put("Sint Maarten","SX");
		map.put("Slovakia","SK");
		map.put("Slovenia","SI");
		map.put("Solomon Islands","SB");
		map.put("Somalia","SO");
		map.put("South Africa","ZA");
		map.put("South Georgia and the South Sandwich Island","GS");
		map.put("South Sudan","SS");
		map.put("Spain","ES");
		map.put("Sri Lanka","LK");
		map.put("Sudan","SD");
		map.put("Suriname","SR");
		map.put("Svalbard And Jan Mayen","SJ");
		map.put("Swaziland","SZ");
		map.put("Sweden","SE");
		map.put("Switzerland","CH");
		map.put("Syria","SY");
		map.put("Taiwan","TW");
		map.put("Tajikistan","TJ");
		map.put("Tanzania","TZ");
		map.put("Thailand","TH");
		map.put("Timor-Leste","TL");
		map.put("Togo","TG");
		map.put("Tokelau","TK");
		map.put("Tonga","TO");
		map.put("Trinidad And Tobago","TT");
		map.put("Tunisia","TN");
		map.put("Turkey","TR");
		map.put("Turkmenistan","TM");
		map.put("Turks And Caicos Islands","TC");
		map.put("Tuvalu","TV");
		map.put("Uganda","UG");
		map.put("Ukraine","UA");
		map.put("United Arab Emirates","AE");
		map.put("United Kingdom","GB");
		map.put("United States Minor Outlying Islands","UM");
		map.put("United States of America","US");
		map.put("Uruguay","UY");
		map.put("Uzbekistan","UZ");
		map.put("Vanuatu","VU");
		map.put("Venezuela","VE");
		map.put("Vietnam","VN");
		map.put("Virgin Islands (British)","VG");
		map.put("Virgin Islands (U.S.)","VI");
		map.put("Wallis And Futuna","WF");
		map.put("Western Sahara","EH");
		map.put("Yemen","YE");
		map.put("Zambia","ZM");
		map.put("Zimbabwe","ZW");
		return map;
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
	
	
	public static ArrayList<SuggestBox> allRequiredSuggestboxes() {
		ArrayList<SuggestBox> list = new ArrayList<SuggestBox>();
		list.add(Emc_eufar.identIdentifierBox);
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
	
	
	public static ArrayList<String> allCorrectFields2() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("string");
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
	
	
	public static String[][] newInstrumentInfo() {
		String[][] string = {{"1.108","Grimm Technologies Inc."},
				{"1.109","Grimm Technologies Inc."},
				{"1011C","Buck Research Instruments L.L.C."},
				{"1201","Rosemount"},
				{"1221","Rosemount"},
				{"2D-C","Particle Measuring Systems"},
				{"2D-P","Particle Measuring Systems"},
				{"2D-S","Stratton Park Engineering Company"},
				{"2D2C","Particle Measuring Systems"},
				{"2DGB2","Particle Measuring Systems"},
				{"3150","ICSensors / Measurement Specialties"},
				{"4PI-SR","METEO-CONSULT"},
				{"5-hole Turbulence Probe","Other"},
				{"858AJ28","Rosemount"},
				{"AA-300","Sperry"},
				{"AADC II","Scintrex Ltd"},
				{"AARP","University of Manchester"},
				{"ADA-100-PDPA","TSI Inc."},
				{"AE-42","Magee Scientific"},
				{"AE-52 Aethelometer","Magee Scientific"},
				{"AHV16","Thales"},
				{"AHV8","TRT"},
				{"AIMMS-20","Aventech Inc."},
				{"AIRINS","iXBlue"},
				{"AL5002","Aero-Laser Gmbh"},
				{"AL5003","Aero-Laser Gmbh"},
				{"ALS-450","Leosphere"},
				{"ALS50","Leica Geosystems"},
				{"ARIES","Met Office / ABB Bomem"},
				{"AT4","Javad GNSS Inc."},
				{"AVAPS","Vaisala"},
				{"Airborne Hyperspectral Scanner","Argon ST"},
				{"Airborne Prism EXperiment","ESA"},
				{"Airborne Thematic Mapper","DLR"},
				{"Airborne Visibility Meter","HSS Inc."},
				{"Aircraft Systems","Other"},
				{"AisaDUAL","SPECIM"},
				{"AisaEAGLE 1K","SPECIM"},
				{"AisaEAGLE","SPECIM"},
				{"AisaFENIX 1K","SPECIM"},
				{"AisaFENIX","SPECIM"},
				{"AisaOWL","SPECIM"},
				{"B270","Setra Systems"},
				{"BAT","NOAA / Airborne Research Australia"},
				{"BCP","Droplet Measurement Technologies"},
				{"CAPS - LWC","Droplet Measurement Technologies"},
				{"CAPS","Droplet Measurement Technologies"},
				{"CAS Spectrometer","Baumgardner et al., 2001"},
				{"CASI","ITRES Research Ltd"},
				{"CASI-1500i","ITRES Research Ltd"},
				{"CASI-550","ITRES Research Ltd"},
				{"CCN Counter","Droplet Measurement Technologies"},
				{"CCNS4","IGI mbH"},
				{"CDP-2","Droplet Measurement Technologies"},
				{"CEV","INTA"},
				{"CGR-4","Kipp & Zonen"},
				{"CIP","Droplet Measurement Technologies"},
				{"CIP-100","Droplet Measurement Technologies"},
				{"CIP-15","Droplet Measurement Technologies"},
				{"CMP-22","Kipp & Zonen"},
				{"COPAS","University of Mainz"},
				{"CPI","Stratton Park Engineering Company"},
				{"CR-1","Buck Research Instruments L.L.C."},
				{"CR-2","Buck Research Instruments L.L.C."},
				{"CRISTA New Frontiers","ForschungsZentrum Juelich"},
				{"CW-QCLAS","Aerodyne Research Inc."},
				{"Campbell Q7.1","Campbell Scientific"},
				{"Cloud Imaging Probe","Other"},
				{"Counterflow Virtual Impactor","Met Office"},
				{"DEIMOS","Met Office"},
				{"Dewtrak-137","EdgeTech Instruments"},
				{"Dewtrak-200","EdgeTech Instruments"},
				{"dIGIcam K14","IGI mbH"},
				{"E-SAR","DLR"},
				{"ERS microjoule Lidar EMB-ER 1/2","ERS"},
				{"Eppley PS Pyranometer","The Eppley Laboratory Inc."},
				{"Eppley Pyrgeometer","The Eppley Laboratory Inc."},
				{"Everest 4000.4ZL","Everest Interscience Inc."},
				{"F-SAR","DLR"},
				{"FAGE","University of Leeds"},
				{"FLIR M40","FLIR Systems Inc."},
				{"FLIR SC3000","FLIR Systems Inc."},
				{"FS2 Relative Humidity","Aerodata GmbH"},
				{"FSSP-100","Particle Measuring Systems"},
				{"FSSP-100ER","Particle Measuring Systems"},
				{"FSSP-300","Particle Measuring Systems"},
				{"FTIR","Midac Inc."},
				{"FUBISS","Free University of Berlin"},
				{"FUBISS-ASA2","Free University Berlin"},
				{"Filter Sampler","Other"},
				{"Flask Array Sampler","Atmospheric Observing Systems Inc."},
				{"Flask Sampling System","MetAir"},
				{"Flourescence Water Vapour Sensor","Met Office"},
				{"Formaldehyde Detectors","Other"},
				{"GASCOD-A","FISBAT-CNR"},
				{"GE-1011B","General Eastern"},
				{"GE-1011C","General Eastern"},
				{"GR-DV400","JVC"},
				{"Garmin GPS","Garmin"},
				{"Gyro-4","Javad GNSS Inc."},
				{"HAGAR","University of Frankfurt"},
				{"HALOX","Forschungszentrum Julich GmbH"},
				{"HMP","Vaisala / Rosemount"},
				{"HMP233","Vaisala"},
				{"HRSC AX","DLR"},
				{"HVPS","Stratton Park Engineering Company"},
				{"High Accuracy INS","SAGEM"},
				{"Hyperspectral Imagery Sensor","DLR"},
				{"HySpecScan","Analytical Spectral Devices Inc."},
				{"IR Gas analyzer","Edinburgh Sensors"},
				{"IR/UV Line Scanner","OPTIMARE Systems GmbH"},
				{"IRCAM","Jenoptik AG"},
				{"IS-2","Interspect Ltd."},
				{"IS-3","Interspect Ltd."},
				{"IS-4","Interspect Ltd."},
				{"IS2+","Interspect Ltd."},
				{"Ice Nucleus Counter","University of Manchester"},
				{"J-W LWC","Johnson-Williams"},
				{"JO1D / JNO2 Photometer","Unknown"},
				{"KA-131","Bendix King"},
				{"KT-15","Heitronics Infrarot Messtechnik"},
				{"King Probe","Particle Measuring Systems"},
				{"Kistler 3 axes accelerometers","Kistler Instrumente AG"},
				{"LASAIR-5295","Particle Measurement Systems"},
				{"LD90","Riegl Laser Measurement Systems GmbH"},
				{"LGR 907","Los Gatos Research Inc."},
				{"LGR DLT100","Los Gatos Research Inc."},
				{"LI-COR 6262","LI-COR"},
				{"LI-COR 7500","LI-COR"},
				{"LI-COR Pyranometer","LI-COR"},
				{"LI-COR Quantum Sensor","LI-COR"},
				{"LMS-Q280","Riegl Laser Measurement Systems GmbH"},
				{"LMS-Q560","Riegl Laser Measurement Systems GmbH"},
				{"LMS-Q680i","Riegl Laser Measurement Systems GmbH"},
				{"LTC 0600","Philipps"},
				{"LTN 90-100","Litton"},
				{"Lasernav YG1761B","Honeywell"},
				{"Lyman-alpha HMS-2","Buck Research Instruments L.L.C."},
				{"Lyman-alpha L-5","Buck Research Instruments L.L.C."},
				{"MARSS","Met Office"},
				{"MS4100","Duncantech"},
				{"MetAir NOxTOy","MetAir AG"},
				{"Meteorological Sensor Package","DLR"},
				{"Mobile Flux Platform","IBIMET CNR"},
				{"NO CLD","DLR"},
				{"Nevzorov IVO-2a","Sky Tech Research Inc."},
				{"Nevzorov","Sky Tech Research Inc."},
				{"OAP-200X","Particle Measuring Systems"},
				{"OAP-230X","Particle Measuring Systems"},
				{"OAP-230Y","Particle Measuring Systems"},
				{"OEM4-G2","NovAtel Inc."},
				{"ORAC","University of Leeds"},
				{"Others","Other"},
				{"Ozone Lidar EXperiment","DLR"},
				{"Ozone Monitor 202","2B Technologies"},
				{"Ozone Monitor","2B Technologies"},
				{"PAN GC","Ai Qualitek Ltd"},
				{"PCASP-100X","Droplet Measurement Technologies"},
				{"PCASP-SPP200","Droplet Measurement Technologies"},
				{"PELICAN","ONERA"},
				{"PERCA","University of Leceister"},
				{"POS AV 510","Applanix"},
				{"POS AV-410","Applanix"},
				{"PRT 6","Barnes"},
				{"PSAP","Radiance Research Inc."},
				{"PSI-O3","Paul Scherrer Institut"},
				{"PT","Rosemount"},
				{"PT100","Rosemount"},
				{"PT102AL","Rosemount"},
				{"PT102BL","Rosemount"},
				{"PT102BW","Rosemount"},
				{"PT102DB1 AG","Rosemount"},
				{"PT102E2 AL","Rosemount"},
				{"PT102E4 AL","Rosemount"},
				{"PT50","Rosemount"},
				{"PT500","Rosemount"},
				{"PTR-MS","University of East Anglia"},
				{"PVM-100","Gerber Scientific Inc."},
				{"Peroxide Detectors","Other"},
				{"Pitot Probe","Other"},
				{"Polifemo M21","SUPERELECTRIC s.r.l."},
				{"Portable Lidar System","University of Munich"},
				{"R4903","Met One Instruments"},
				{"RALI","LATMOS"},
				{"RALI","LATMOS"},
				{"RCD105","Leica-Geosystems"},
				{"RDR-4B","Honeywell"},
				{"RMK A 30/23","Zeiss"},
				{"RMK A","Zeiss"},
				{"RR-0150","RadianceResearch"},
				{"RT3102","Oxford Technical Solutions"},
				{"Radar Echo Sounding System","AWI"},
				{"S-5002","Sistemas Instalaciones Redes S.A."},
				{"S-5006","Sistemas Instalaciones Redes S.A."},
				{"S-5012","Sistemas Instalaciones Redes S.A."},
				{"S3000","Michell Instruments GmbH"},
				{"S56","Micro-g LaCoste"},
				{"SA 41M","Environment S.A."},
				{"SETHI","ONERA"},
				{"SFIM 3 axes accelerometers","SFIM"},
				{"SID-2","University of Hertfordshire"},
				{"SIELETERS","ONERA"},
				{"SIOUX","DLR"},
				{"SMPS","Grimm Technologies Inc."},
				{"SP-2","Droplet Measurement Technologies"},
				{"SPEC Hawkeye","Stratton Park Engineering Company"},
				{"SWS","Met Office"},
				{"Solent HS","Gill Instruments Ltd"},
				{"Solent HS-100","Gill Instruments Ltd"},
				{"Solent HS-50","Gill Instruments Ltd"},
				{"Sony Video Camera","Sony"},
				{"Sundstrand 3 axes accelerometers","Sundstrand"},
				{"TABI320","ITRES Research Ltd"},
				{"TAFTS","Imperial College London"},
				{"TASI-600","ITRES Research Ltd"},
				{"TDLAS","Imperial College London"},
				{"TDLAS","National Physical Laboratory"},
				{"TECO 42C","ThermoScientific"},
				{"TECO 42C","ThermoScientific"},
				{"TECO 42S","ThermoScientific"},
				{"TECO 43C","ThermoScientific"},
				{"TECO 48","ThermoScientific"},
				{"TECO 48CS","ThermoScientific"},
				{"TECO 49PS","ThermoScientific"},
				{"TECO 49S","ThermoScientific"},
				{"TOF-AMS","Aerodyne Research Inc."},
				{"TP3-S","Meteolabor AG"},
				{"TS9260","NEC Corporation"},
				{"TSI-3007","TSI Inc."},
				{"TSI-3010","TSI Inc."},
				{"TSI-3022","TSI Inc."},
				{"TSI-3025","TSI Inc."},
				{"TSI-3563","TSI Inc."},
				{"TSI-3776","TSI Inc."},
				{"TSI-3786","TSI Inc."},
				{"Total Water Content","Met Office"},
				{"Trimble 2101","Trimble Navigation"},
				{"Trimble 4000 SSI","Trimble Navigation"},
				{"Trimble TNL 2000","Trimble Navigation"},
				{"UHSAS-A","Droplet Measurement Technologies"},
				{"ULISS 45i","SAGEM"},
				{"ULS","Laser Technology Inc."},
				{"UMP40","Sextant"},
				{"VACC","University of Leeds"},
				{"VIS Line Scanner","OPTIMARE Systems GmbH"},
				{"VIS/NIR Spectrometer ","Other"},
				{"VOC GC","Airmotec AG"},
				{"Video Camera","Other"},
				{"WIND","DLR / CNRS / INSU"},
				{"WVSS-II","SpectraSensors Inc."},
				{"Walz UV Pyranometer","Heinz Walz Company"},
				{"Whole Air Sampling System","University of York"},
				{"XR5","Navstar Electronics"}};
		return string;
	}

	
	public static Image popupImage(final String string) {
		final Image image = new Image("icons/" + string + "_popup_icon.png");
		return image;
	}
	
	
	public static Image smallImage(final String string) {
		final Image image = new Image("icons/" + string + "_icon.png");
		return image;
	}
	
	
	public static String[][] newAircraftInfo() {
		String[][] string = {{"AWI","Basler Turbo Conversions, BT-67","C-GAWI","DE","http://www.eufar.net/media/uploads/aircrafts/AWI_Polar5_-_2_692x288.jpg"},
				{"CNR-ISAFoM","3I / Magnaghi Aeronautica, Sky Arrow 650 TCNS","I-AMMO","IT","http://www.eufar.net/media/uploads/aircrafts/ISAFOM_SkyArrow_-_1_692x288.jpg"},
				{"CNR-IMAA","Partenavia / Vulcanair, P-68 Observer 2","I-OBPC","IT","http://www.eufar.net/media/uploads/aircrafts/IMMA_Partenavia_-_1_692x288_1.jpg"},
				{"CzechGlobe","Cessna Aircraft Company, C-208 B Grand Caravan","OK-CZG","CZ","http://www.eufar.net/media/uploads/aircrafts/CzechGlobe_C208_-_5_692x288_1.jpg"},
				{"DLR","Cessna Aircraft Company, C-208 B Grand Caravan","D-FDLR","DE","http://www.eufar.net/media/uploads/aircrafts/DLR_C208_Grand_Caravan_-_1_692x288.jpg"},
				{"DLR","Dornier Flugzeugwerke, Do 228 - 212","D-CFFU","DE","http://www.eufar.net/media/uploads/aircrafts/DLR_Dornier_Do228_D-CFFU_-_1_692x288_1.jpg"},
				{"DLR","Dornier Flugzeugwerke, Do 228 - 101","D-CODE","DE","http://www.eufar.net/media/uploads/aircrafts/DLR_Dornier_Do228_D-CODE_-_3_692x288_1.jpg"},
				{"DLR","Dassault Aviation, Mystere / Falcon 20 E-5","D-CMET","DE","http://www.eufar.net/media/uploads/aircrafts/DLR_Falcon_20_-_1_692x288.jpg"},
				{"Enviscope","Partenavia / Vulcanair, P-68B","D-GERY","DE","http://www.eufar.net/media/uploads/aircrafts/ENVISCOPE_Partenavia_-_1_692x288.jpg"},
				{"Enviscope","Learjet / Bombardier Aerospace, 35A","D-CGFD","DE","http://www.eufar.net/media/uploads/aircrafts/ENVISCOPE_Learjet_35A_-_1_692x288.jpg"},
				{"FAAM","BAE Systems, BAe146-301","G-LUXE","GB","http://www.eufar.net/media/uploads/aircrafts/FAAM_BAe146_-_3_692x288.jpg"},
				{"FUB","Cessna Aircraft Company, T207A Turbo Skywagon","D-EAFU","DE","http://www.eufar.net/media/uploads/aircrafts/FUB_C_207_-_1.jpg"},
				{"INTA","Construcciones Aeronauticas S.A, C-212-200","EC-DTV","ES","http://www.eufar.net/media/uploads/aircrafts/INTA_CASA_212_AR_-_1_692x288.jpg"},
				{"INTA","Construcciones Aeronauticas S.A, C-212-200","EC-DUQ","ES","http://www.eufar.net/media/uploads/aircrafts/CASA_212_RS_692x288.jpg"},
				{"KIT, IMK-IFU","Ultraleichtflug Schmidtler, Enduro","D-MIFU","DE","http://www.eufar.net/media/uploads/aircrafts/KIT_ENDURO_-_2_692x288.jpg"},
				{"NERC","De Havilland Canada, DHC-6 Twin Otter","VP-FAZ","GB","http://www.eufar.net/media/uploads/aircrafts/BAS_DHC6_-_2_692x288.jpg"},
				{"NERC","De Havilland Canada, DHC-6 Twin Otter","VP-FBL","GB","http://www.eufar.net/media/uploads/aircrafts/Twin_Otter_BAS_VP-FBL_-_2.jpg"},
				{"SAFIRE","ATR, ATR42-320","F-HMTO","FR","http://www.eufar.net/media/uploads/aircrafts/SAFIRE_ATR42_-_2_692x288.jpg"},
				{"SAFIRE","Piper Aircraft, PA23-250 Aztec","F-BLEB","FR","http://www.eufar.net/media/uploads/aircrafts/SAFIRE_PIPER_AZTEC_-_2_692x288.jpg"},
				{"SAFIRE","Dassault Aviation, Mystere / Falcon 20 GF","F-GBTM","FR","http://www.eufar.net/media/uploads/aircrafts/SAFIRE_FA20_-_1_692x288.jpg"},
				{"UEDIN","Diamond Aircraft, HK36 TTC ECO Dimona","G-GEOS","GB","http://www.eufar.net/media/uploads/aircrafts/UEDIN_HK36_-_2_692x288.jpg"}};
		return string;
	}
	
	
	public static interface Resources extends ClientBundle {
        
        @Source("images/icons/earth_globe.png")
        ImageResource earth();

        @Source("images/icons/menu_tab_arrow_v3.png")
        ImageResource menu();
        
        @Source("images/icons/logo_eufar_emc_v2.jpg")
        ImageResource eufarLogo();
        
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
	
	
	public static HashMap<String, String> monthMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Jan.", "01");
		map.put("Feb.", "02");
		map.put("March", "03");
		map.put("April", "04");
		map.put("May", "05");
		map.put("June", "06");
		map.put("July", "07");
		map.put("Aug.", "08");
		map.put("Sept.", "09");
		map.put("Oct.", "10");
		map.put("Nov.", "11");
		map.put("Dec.", "12");
		return map;
	}
	
	
}