package com.eufar.emc.client;

import static com.google.gwt.query.client.GQuery.$;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;


public class Emc_eufar implements EntryPoint {
		
		// few items initialization
		public enum infoEnum{IDTITLE, IDABSTRACT, IDTYPE, KEYWORDS, IDLOCATOR, IDIDENTIFIER, IDLANGUAGE, CATEGORIES,
			AIAIRCRAFT, AIINSTRUMENT, GILOCATION, GIBOX, GIUNIT, TRPUBLICATION, TRREVISION, TRCREATION, TRPHASE, AUCONDITIONS, AULIMITATIONS,
			ROPARTY, ROEMAIL, ROROLE, MMDATE, MMLANGUAGE, MMPARTY;}
		private TreeMap<String, String> languageMap = new TreeMap<String, String>();
		private TreeMap<String, String> categoriesMap = new TreeMap<String, String>();
		private TreeMap<String, String> keywordsMap = new TreeMap<String, String>();
		private TreeMap<String, String> roleMap = new TreeMap<String, String>();
		private TreeMap<String, String> operatorMap = new TreeMap<String, String>();
		private TreeMap<String, String> typeMap = new TreeMap<String, String>();
		private HashMap<TextBoxBase, Label> requiredField = new HashMap<TextBoxBase, Label>();
		private HashMap<TextBoxBase, String> correctField = new HashMap<TextBoxBase, String>();
		private String emcVersion = new String("v0.9.3");
		private String xmlVersion = new String("v1.0a");
		private String inspireVersion = new String("v1.3");
		
	
		// Main window items initialization
		private MenuBar mainMenu = new MenuBar();
		private MenuBar aboutMenu = new MenuBar(true);
		private MenuBar fileMenu = new MenuBar(true);
		private DockLayoutPanel dockPanel = new DockLayoutPanel(Unit.PX);
		private DockLayoutPanel subDockPanel = new DockLayoutPanel(Unit.PX);
		private StackLayoutPanel stackPanel = new StackLayoutPanel(Unit.EM);
		private String myFileName = new String("");
		private String imageNew = "<img src='icons/new_icon_menu.png'>  New...</img>";
		private String imageOpen = "<img src='icons/open_icon_menu.png'>  Open...</img>";
		private String imageSave = "<img src='icons/save_icon_menu.png'>  Save...</img>";
		private String imageAbout = "<img src='icons/about_icon_menu.png'>  EUFAR Metadata Creator...</img>";
		private String imageStandard = "<img src='icons/inspire_icon_menu.png'>  INSPIRE XML Standard...</img>";
		private String imageEufar = "<img src='icons/eufar_icon_menu.png'>  EUFAR N7SP...</img>";
		private String imageExit = "<img src='icons/exit_icon_menu.png'>  Exit...</img>";
		
		
		// Identification items
		private HorizontalPanel horizontalPanel01 = new HorizontalPanel();
		private HorizontalPanel horizontalPanel02 = new HorizontalPanel();
		private HorizontalPanel horizontalPanel03 = new HorizontalPanel();
		private HorizontalPanel horizontalPanel04 = new HorizontalPanel();
		private HorizontalPanel horizontalPanel05 = new HorizontalPanel();
		private HorizontalPanel horizontalPanel06 = new HorizontalPanel();
		private Label identTitleLab = new Label("Resource title:");
		private Label identAbstractLab = new Label("Resource abstract:");
		private Label identTypeLab = new Label("Resource type:");
		private Label identLocatorLab = new Label("Resource locator:");
		private Label identIdentifierLab = new Label("Unique resource identifier:");
		private Label identLanguageLab = new Label("Resource language:");
		private TextBox identTitleBox = new TextBox();
		private TextArea identAbstractAre = new TextArea();
		private ListBox identTypeLst = new ListBox();
		private ArrayList<String> typeList = new ArrayList<String>();
		private TextBox identLocatorBox = new TextBox();
		private TextBox identIdentifierBox = new TextBox();
		private ListBox identLanguageLst = new ListBox();
		private ArrayList<String> languageList = new ArrayList<String>();
		private FlexTable idGrid = new FlexTable();
		private ScrollPanel idScroll = new ScrollPanel(idGrid);
		
		
		// Classification items
		private VerticalPanel verticalPanel01 = new VerticalPanel();
		private VerticalPanel verticalPanel02 = new VerticalPanel();
		private HorizontalPanel horizontalPanel07 = new HorizontalPanel();
		private Label classCategoriesLab = new Label("Topic Categories:");
		private CheckBox classBiotaCheck = new CheckBox("Biota");
		private CheckBox classBoundariesCheck = new CheckBox("Boundaries");
		private CheckBox classClimatologyCheck = new CheckBox("Climatology / Meteorology / Atmosphere");
		private CheckBox classEconomyCheck = new CheckBox("Economy");
		private CheckBox classElevationCheck = new CheckBox("Elevation");
		private CheckBox classEnvironmentCheck = new CheckBox("Environment");
		private CheckBox classFarmingCheck = new CheckBox("Farming");
		private CheckBox classInformationCheck = new CheckBox("Geoscientific Information");
		private CheckBox classHealthCheck = new CheckBox("Health");
		private CheckBox classImageryCheck = new CheckBox("Imagery / Base Maps / Earth Cover");
		private CheckBox classIntelligenceCheck = new CheckBox("Intelligence / Military");
		private CheckBox classWatersCheck = new CheckBox("Inland Waters");
		private CheckBox classLocationCheck = new CheckBox("Location");
		private CheckBox classOceansCheck = new CheckBox("Oceans");
		private CheckBox classPlanningCheck = new CheckBox("Planning / Cadastre");
		private CheckBox classSocietyCheck = new CheckBox("Society");
		private CheckBox classStructureCheck = new CheckBox("Structure");
		private CheckBox classTransportationCheck = new CheckBox("Transportation");
		private CheckBox classCommunicationCheck = new CheckBox("Utilities / Communication");
		private ScrollPanel clScroll = new ScrollPanel(horizontalPanel07);
		
		
		// Keywords items
		private HorizontalPanel horizontalPanel08 = new HorizontalPanel();
		private VerticalPanel verticalPanel03 = new VerticalPanel();
		private VerticalPanel verticalPanel04 = new VerticalPanel();
		private VerticalPanel verticalPanel05 = new VerticalPanel();
		private VerticalPanel verticalPanel06 = new VerticalPanel();
		private Label keyAgricultureLab = new Label("Agriculture:");
		private Label keyAtmosphereLab = new Label("Atmosphere:");
		private Label keyBiosphereLab = new Label("Biosphere:");
		private Label keyCryosphereLab = new Label("Cryosphere:");
		private Label keySurfaceLab = new Label("Land Surface:");
		private Label keyOceanLab = new Label("Ocean:");
		private Label keyEarthLab = new Label("Solid Earth:");
		private Label keySpectralLab = new Label("Spectral / Engineering:");
		private Label keyInteractionsLab = new Label("Sun-Earth Interactions:");
		private Label keyHydrosphereLab = new Label("Terrestrial Hydrosphere:");
		private CheckBox classAgEngineeringCheck = new CheckBox("Agricultural engineering");
		private CheckBox classAgPlantCheck = new CheckBox("Agricultural plant science");
		private CheckBox classAgFoodCheck = new CheckBox("Food science");
		private CheckBox classAgForestCheck = new CheckBox("Forest science");
		private CheckBox classAgSoilsCheck = new CheckBox("Soils");
		private CheckBox classAtAerosolsCheck = new CheckBox("Aerosols");
		private CheckBox classAtAirCheck = new CheckBox("Air quality");
		private CheckBox classAtAltitudeCheck = new CheckBox("Altitude");
		private CheckBox classAtChemistryCheck = new CheckBox("Atmospheric chemistry");
		private CheckBox classAtElectricityCheck = new CheckBox("Atmospheric electricity");
		private CheckBox classAtPhenomenaCheck = new CheckBox("Atmospheric phenomena");
		private CheckBox classAtPressureCheck = new CheckBox("Atmospheric pressure");
		private CheckBox classAtRadiationCheck = new CheckBox("Atmospheric radiation");
		private CheckBox classAtTemperatureCheck = new CheckBox("Atmospheric temperature");
		private CheckBox classAtVapourCheck = new CheckBox("Atmospheric water vapor");
		private CheckBox classAtWindsCheck = new CheckBox("Atmospheric winds");
		private CheckBox classAtCloudsCheck = new CheckBox("Clouds");
		private CheckBox classAtPrecipitationCheck = new CheckBox("Precipitation");
		private CheckBox classBiDynamicsCheck = new CheckBox("Ecological dynamics");
		private CheckBox classBiEcosystemsCheck = new CheckBox("Terrestrial ecosystems");
		private CheckBox classBiVegetationCheck = new CheckBox("Vegetation");
		private CheckBox classCrGroundCheck = new CheckBox("Frozen ground");
		private CheckBox classCrGlaciersCheck = new CheckBox("Glaciers / Ice sheet");
		private CheckBox classCrIceCheck = new CheckBox("Sea ice");
		private CheckBox classCrSnowCheck = new CheckBox("Snow / Ice");
		private CheckBox classLsErosionCheck = new CheckBox("Erosion / Sedimentation");
		private CheckBox classLsGeomorphologyCheck = new CheckBox("Geomorphology");
		private CheckBox classLsTemperatureCheck = new CheckBox("Land temperature");
		private CheckBox classLsCoverCheck = new CheckBox("Land use / Land cover");
		private CheckBox classLsLandscapeCheck = new CheckBox("Landscape");
		private CheckBox classLsSurfaceCheck = new CheckBox("Surface radiative properties");
		private CheckBox classLsTopographyCheck = new CheckBox("Topography");
		private CheckBox classOcBathymetryCheck = new CheckBox("Bathymetry");
		private CheckBox classOcProcessesCheck = new CheckBox("Coastal processes");
		private CheckBox classOcEnvironmentCheck = new CheckBox("Marine environment");
		private CheckBox classOcGeophysicsCheck = new CheckBox("Marine geophysics");
		private CheckBox classOcWavesCheck = new CheckBox("Ocean waves");
		private CheckBox classOcWindsCheck = new CheckBox("Ocean winds");
		private CheckBox classOcTopographyCheck = new CheckBox("Sea surface topography");
		private CheckBox classOcTidesCheck = new CheckBox("Tides");
		private CheckBox classOcQualityCheck = new CheckBox("Water quality");
		private CheckBox classSeGeodeticsCheck = new CheckBox("Geodetics");
		private CheckBox classSeGeomagnetismCheck = new CheckBox("Geomagnetism");
		private CheckBox classSeLandformsCheck = new CheckBox("Geomorphic landforms");
		private CheckBox classSeGravityCheck = new CheckBox("Gravity");
		private CheckBox classSpGammaCheck = new CheckBox("Gamma ray");
		private CheckBox classSpInfraredCheck = new CheckBox("Infrared wavelengths");
		private CheckBox classSpLidarCheck = new CheckBox("LIDAR");
		private CheckBox classSpMicrowaveCheck = new CheckBox("Microwave");
		private CheckBox classSpRadarCheck = new CheckBox("RADAR");
		private CheckBox classSpRadioCheck = new CheckBox("Radio wave");
		private CheckBox classSpUltravioletCheck = new CheckBox("Ultraviolet wavelengths");
		private CheckBox classSpVisibleCheck = new CheckBox("Visible wavelengths");
		private CheckBox classSpXrayCheck = new CheckBox("X-ray");
		private CheckBox classInIonosphereCheck = new CheckBox("Ionosphere / Magnetosphere");
		private CheckBox classInActivityCheck = new CheckBox("Solar activity");
		private CheckBox classInParticleCheck = new CheckBox("Solar energetic particle");
		private CheckBox classThGroundCheck = new CheckBox("Ground water");
		private CheckBox classThSurfaceCheck = new CheckBox("Surface water");
		private CheckBox classThChemistryCheck = new CheckBox("Water quality / chemistry");
		private ScrollPanel kwScroll = new ScrollPanel(horizontalPanel08);
		
		
		// Aircraft and insutruments items
	    private VerticalPanel verticalPanel08 = new VerticalPanel();
	    private VerticalPanel verticalPanel09 = new VerticalPanel();
	    private VerticalPanel verticalPanel10 = new VerticalPanel();
	    private HorizontalPanel horizontalPanel09 = new HorizontalPanel();
	    private HorizontalPanel horizontalPanel10 = new HorizontalPanel();
	    private HorizontalPanel horizontalPanel11 = new HorizontalPanel();
	    private HorizontalPanel horizontalPanel12 = new HorizontalPanel();
	    private FlexTable airInformationTable = new FlexTable();
		private Label airAircraftLab = new Label("Aircraft:");
		private Label airManufacturerLab = new Label("Manufacturer:");
		private Label airManufacturerInfo = new Label("");
		private Label airTypeLab = new Label("Aircraft type:");
		private Label airTypeInfo = new Label("");
		private Label airOperatorLab = new Label("Operator:");
		private Label airOperatorInfo = new Label("");
		private Label airCountryLab = new Label("Country:");
		private Label airCountryInfo = new Label("");
		private Label airRegistrationLab = new Label("Registration number:");
		private Label airRegistrationInfo = new Label("");
		private Label airCopyrightInfo = new Label("EUFAR");
		private Image airCopyrightImage = new Image("icons/copyright_icon_small.png");
		private ListBox airAircraftLst = new ListBox();
		private ArrayList<String> aircraftList = new ArrayList<String>();
		public Image airAircraftImg = new Image("icons/eufar_aircrafts/logo_eufar_emc.png");
		private String[][] airAircraftInfo = {
				{"Do your choice...", " ", " ", " ", " ", " ", "logo_eufar_emc.png", "EUFAR"},
				{"AWI - POLAR 5", "Basler Turbo Conversions", "BT-67", "Alfred Wegener Institute (AWI)", "Germany", "C-GAWI", "POLAR5-AWI.png",
						"Alfred Wegener Institute"},
				{"CNR - ERA Skyarrow", "3I / Magnaghi Aeronautica", "Sky Arrow 650", "Consiglio Nazionale delle Ricerche (CNR) - ISAFoM", "Italy",
						"I-AMMO", "ERA-ISAFM.png", "CNR, Istituto per i Sistemi Agricoli e Forestali del Mediterraneo"},
				{"CNR - Partenavia", "Partenavia / Vulcanair", "P-68", "Consiglio Nazionale delle Ricerche (CNR) - IMAA", "Italy", "I-ALTM", 
						"PARTENAVIA-CNR.png", "CNR, Istituto di Metodologie per l'Analisi Ambientale"},
				{"DLR - C 208", "Cessna Aircraft Company", "Cessna 208", "Deutsches Zentrum fur Luft- und Raumfahrt (DLR)", "Germany", "D-FDLR", 
						"C208-DLR.png", "DLR, CC-BY 3.0"},
				{"DLR - DO 228 D-CFFU", "Dornier Flugzeugwerke", "DO-228", "Deutsches Zentrum fur Luft- und Raumfahrt (DLR)", "Germany", "D-CFFU", 
						"DO228-DLR-1.png", "DLR, CC-BY 3.0"},
				{"DLR - DO 228 D-CODE", "Dornier Flugzeugwer	ke", "DO-228", "Deutsches Zentrum fur Luft- und Raumfahrt (DLR)", "Germany", 
						"D-CODE", "DLR - DO228-2.png", "DLR, CC-BY 3.0"},
				{"DLR - Falcon 20", "Dassault Aviation", "Mystere/Falcon 20", "Deutsches Zentrum fur Luft- und Raumfahrt (DLR)", "Germany", 
						"D-CMET", "FALCON20-DLR.png", "DLR, CC-BY 3.0"},
				{"ENVISCOPE - Learjet 35", "Learjet Aircraft Company", "Learjet 35", "ENVISCOPE", "Germany", "D-CGFD", "LEARJET25-ENVISCOPE-3.png", 
						"ENVISCOPE GmbH"},
				{"ENVISCOPE - Partenavia", "Partenavia / Vulcanair", "P-68", "ENVISCOPE", "Germany", "D-GERY", "PARTENAVIA68-ENVISCOPE-2.png", 
						"ENVISCOPE GmbH"},
				{"FAAM - BAe 146", "BAE Systems", "BAe-146", "Facility for Airborne Atmospheric Measurements (FAAM)", "United Kingdom", "G-LUXE", 
						"BAE146-FAAM.png", "NERC, Facility for Airborne Atmospheric Measurements"},
				{"FUB - C 207", "Cessna Aircraft Company", "Cessna 207", "Freie Universitat Berlin (FUB) - ISS", "Germany", "D-EAFU", 
						"C207-FUB.png", "Institut fur Weltraumwissenschaften"},
				{"INTA - CASA 212 AR", "Construcciones Aeronauticas S.A.", "CASA-212", "Instituto Nacional de Tecnica Aeroespacial (INTA)", 
						"Spain", "EC-DTV", "CASA212-INTA.png", "Instituto Nacional de Tecnica Aeroespacial"},
				{"INTA - CASA 212 RS", "Construcciones Aeronauticas S.A.", "CASA-212", "Instituto Nacional de Tecnica Aeroespacial (INTA)", 
						"Spain", "EC-DUQ", "CASA212-INTA.png", "Instituto Nacional de Tecnica Aeroespacial"},
				{"KIT - Enduro", "Ultraleichtflug Schmidtler", "Enduro", "Karlsruhe Institute of Technology (KIT) - IMK-IFU", "Germany", 
						"D-MIFU", "ENDURO-KIT.png","KIT, Institute of Meteorology and Climate Research"},
				{"NERC - DO 228", "Dornier Flugzeugwerke", "DO-228", "Natural Environment Research Council (NERC) - ARSF", "United Kingdom", 
						"EC-DUQ", "DO228-NERC.png", "NERC, Airborne Research and Survey Facility"},
				{"NERC - Twin Otter", "De Havilland Canada", "DHC-6", "Natural Environment Research Council (NERC) - BAS", "United Kingdom", 
						"VP-FAZ", "TWINOTTER-NERC.png", "NERC, British Antarctic Survey"},
				{"SAFIRE - ATR 42", "ATR", "ATR-42", "Service des Avions Francais Instrumentés pour la Recherche en Environnement (SAFIRE)", "France", "F-HMTO", "ATR42-SAFIRE.png", "SAFIRE"},
				{"SAFIRE - Falcon 20", "Dassault Aviation", "Mystere/Falcon 20", "Service des Avions Francais Instrumentés pour la Recherche en Environnement (SAFIRE)", "France", "F-GBTM", "FALCON20-SAFIRE-3.png", 
						"Collection Saspozatlse"},
				{"SAFIRE - PIPER Aztec", "Piper Aircraft", "PA23", "Service des Avions Francais Instrumentés pour la Recherche en Environnement (SAFIRE)", "France", "F-BLEB", "PIPERAZTEC-SAFIRE.png", "SAFIRE"},
				{"UEDIN - ECO Dimona", "Diamond Aircraft Industries", "HK36TTC ECO Dimona", "The University of Edinburgh (UEDIN) - IAES", 
						"United Kingdom", "G-GEOS", "DIAMOND-UEDIN-1.png", "UEDIN, Airborne GeoSciences"},
		};
		
		private Label insInstrumentLab = new Label("Instrument:");
	    private ScrollPanel aiScroll = new ScrollPanel(verticalPanel10);
		
		
	    // Geographic information items
		private VerticalPanel verticalPanel11 = new VerticalPanel();
		private HorizontalPanel horizontalPanel13 = new HorizontalPanel();
		private HorizontalPanel horizontalPanel16 = new HorizontalPanel();
		private HorizontalPanel horizontalPanel17 = new HorizontalPanel();
		private Label geoLocationLab = new Label("Location:");
		private Label geoBoundingLab = new Label("Geographic bounding box:");
		private Label geoResolutionLab = new Label("Spatial resolution:");
		private Label geoUnitLab = new Label("Unit:");
		private ListBox geoLocationLst = new ListBox();
		private ListBox geoDetailLst = new ListBox();
		private ListBox geoResolutionLst = new ListBox();
		private ArrayList<String> countryList = new ArrayList<String>();
		private ArrayList<String> continentList = new ArrayList<String>();
		private ArrayList<String> oceanList = new ArrayList<String>();
		private ArrayList<String> regionList = new ArrayList<String>();
		private ArrayList<String> locationList = new ArrayList<String>();
		private ArrayList<String> resolutionList = new ArrayList<String>();
		private TextBox geoNorthBox = new TextBox();
		private TextBox geoSouthBox = new TextBox();
		private TextBox geoEastBox = new TextBox();
		private TextBox geoWestBox = new TextBox();
		private TextBox geoResolutionBox = new TextBox();
		private TextBox geoUnitBox = new TextBox();
		private Image geoFollowImage = new Image("icons/fwd_arrow_small.png");
		private Image geoCoordImage = new Image("icons/rose_des_vents_small.png");
		private FlexTable geoCoordTab = new FlexTable();
		private ScrollPanel giScroll = new ScrollPanel(verticalPanel11);
		
		
		// Temporal Reference items
		private VerticalPanel verticalPanel13 = new VerticalPanel();
		private HorizontalPanel horizontalPanel18 = new HorizontalPanel();
		private HorizontalPanel horizontalPanel19 = new HorizontalPanel();
		private HorizontalPanel horizontalPanel20 = new HorizontalPanel();
		private HorizontalPanel horizontalPanel21 = new HorizontalPanel();
		private Label refPublicationLab = new Label("Date of publication:");
		private Label refCreationLab = new Label("Date of creation:");
		private Label refRevisionLab = new Label("Date of last revision:");
		private Label refExtentLab = new Label("Temporal extent:");
		private Label refPhaseLab = new Label("Phase 1:");
		private DateBox refPublicationDat = new DateBox();
		private DateBox refRevisionDat = new DateBox();
		private DateBox refCreationDat = new DateBox();
		private DateBox refStartDat = new DateBox();
		private DateBox refEndDat = new DateBox();
		private ArrayList<DateBox> refStartLst = new ArrayList<DateBox>();
		private ArrayList<DateBox> refEndLst = new ArrayList<DateBox>();
		private FlexTable refDateTab = new FlexTable();
		private FlexTable refPhaseTab = new FlexTable();
		private Image refEmptyImage = new Image("icons/empty_icon_small.png");
		private ScrollPanel trScroll = new ScrollPanel(verticalPanel13);
		
		
		// Quality and Validity items
		private VerticalPanel verticalPanel14 = new VerticalPanel();
		private Label valInfoLab = new Label ("This tab needs to be deeply reworked");
		private ScrollPanel qvScroll = new ScrollPanel(verticalPanel14);
		
		
		// Access and Use Constraints items
		private VerticalPanel verticalPanel15 = new VerticalPanel();
		private Label useConditionsLab = new Label("Conditions applying to access and use:");
		private Label useLimitationsLab = new Label("Limitations on public access:");
		private TextArea useConditionsBox = new TextArea();
		private TextArea useLimitationsBox = new TextArea();
		private FlexTable useConditionsTab = new FlexTable();
		private FlexTable useConditionsAddTab = new FlexTable();
		private FlexTable useLimitationsTab = new FlexTable();
		private FlexTable useLimitationsAddTab = new FlexTable();
		private ArrayList<TextArea> useConditionsLst = new ArrayList<TextArea>();
		private ArrayList<TextArea> useLimitationsLst = new ArrayList<TextArea>();
		private Image auEmptyImage1 = new Image("icons/empty_icon_small.png");
		private Image auEmptyImage2 = new Image("icons/empty_icon_small.png");
		private ScrollPanel auScroll = new ScrollPanel(verticalPanel15);
		
		
		// Responsible Organisations items
		private HorizontalPanel horizontalPanel24 = new HorizontalPanel();
		private HorizontalPanel horizontalPanel25 = new HorizontalPanel();
		private HorizontalPanel horizontalPanel26 = new HorizontalPanel();
		private HorizontalPanel horizontalPanel27 = new HorizontalPanel();
		private Label orgPartyLab = new Label("Responsible party:");
		private Label orgEmailLab = new Label("Responsible party e-mail:");
		private Label orgRoleLab = new Label("Responsible party role:");
		private TextBox orgPartyBox = new TextBox();
		private TextBox orgEmailBox = new TextBox();
		private ListBox orgRoleLst = new ListBox();
		private ArrayList<String> roleList = new ArrayList<String>();
		private ArrayList<TextBox> orgPartyLst = new ArrayList<TextBox>();
		private ArrayList<ListBox> orgRole2Lst = new ArrayList<ListBox>();
		private ArrayList<TextBox> orgEmailLst = new ArrayList<TextBox>();
		private FlexTable orgPartyTab = new FlexTable();
		private FlexTable orgAddTab = new FlexTable();
		private Image orgEmptyImage = new Image("icons/empty_icon_small.png");
		private ScrollPanel roScroll = new ScrollPanel(orgAddTab);
		
		
		// Metadata on Metadata items
		private VerticalPanel verticalPanel16 = new VerticalPanel();
		private HorizontalPanel horizontalPanel28 = new HorizontalPanel();
		private HorizontalPanel horizontalPanel29 = new HorizontalPanel();
		private Label metDateLab = new Label("Metadata date:");
		private Label metLanguageLab = new Label("Metadata Language:");
		private Label metContactLab = new Label("Metadata point of contact:");
		private Label metNameLab = new Label("Name:");
		private Label metEmailLab = new Label("E-mail:");
		private DateBox metDateDat = new DateBox();
		private ListBox metLanguageLst = new ListBox();
		private TextBox metNameBox = new TextBox();
		private TextBox metEmailBox = new TextBox();
		private FlexTable metMetadataTab = new FlexTable();
		private FlexTable metContactTab = new FlexTable();
		private FlexTable metPartyTab = new FlexTable();
		private FlexTable metAddTab = new FlexTable();
		private ArrayList<TextBox> metNameLst = new ArrayList<TextBox>();
		private ArrayList<TextBox> metEmailLst = new ArrayList<TextBox>();
		private Image metEmptyImage = new Image("icons/empty_icon_small.png");
		private ScrollPanel mmScroll = new ScrollPanel(verticalPanel16);
		
		
		public void onModuleLoad() {
				
				// Preparation of few objects
				typeList.add("Dataset");
				typeList.add("Series");
				
				languageList.add("Bulgarian");
				languageList.add("Czech");
				languageList.add("Danish");
				languageList.add("Dutch");
				languageList.add("English");
				languageList.add("Estonian");
				languageList.add("Finnish");
				languageList.add("French");
				languageList.add("German");
				languageList.add("Greek");
				languageList.add("Hungarian");
				languageList.add("Irish");
				languageList.add("Italian");
				languageList.add("Latvian");
				languageList.add("Lithuanian");
				languageList.add("Maltese");
				languageList.add("Polish");
				languageList.add("Portuguese");
				languageList.add("Romanian");
				languageList.add("Slovak");
				languageList.add("Slovenian");
				languageList.add("Spanish");
				languageList.add("Swedish");
				
				aircraftList.add("Do your choice...");
				aircraftList.add("AWI - POLAR 5");
				aircraftList.add("CNR - ERA Skyarrow");
				aircraftList.add("CNR - Partenavia");
				aircraftList.add("DLR - C 208");
				aircraftList.add("DLR - DO 228 D-CFFU");
				aircraftList.add("DLR - DO 228 D-CODE");
				aircraftList.add("DLR - Falcon 20");
				aircraftList.add("ENVISCOPE - Learjet 35");
				aircraftList.add("ENVISCOPE - Partenavia");
				aircraftList.add("FAAM - BAe 146");
				aircraftList.add("FUB - C 207");
				aircraftList.add("INTA - CASA 212 AR");
				aircraftList.add("INTA - CASA 212 RS");
				aircraftList.add("KIT - Enduro");
				aircraftList.add("NERC - DO 228");
				aircraftList.add("NERC - Twin Otter");
				aircraftList.add("SAFIRE - ATR 42");
				aircraftList.add("SAFIRE - Falcon 20");
				aircraftList.add("SAFIRE - PIPER Aztec");
				aircraftList.add("UEDIN - ECO Dimona");
				
				locationList.add("Do your choice...");
				locationList.add("Continents");
				locationList.add("Countries");
				locationList.add("Oceans");
				locationList.add("Regions");
				
				continentList.add("Do your choice...");
				continentList.add("Africa");
				continentList.add("Antarctica");
				continentList.add("Asia");
				continentList.add("Oceania");
				continentList.add("Europe");
				continentList.add("North America");
				continentList.add("South America");
				
				oceanList.add("Do your choice...");
				oceanList.add("Atlantic Ocean");
				oceanList.add("Arctic Ocean");
				oceanList.add("Indian Ocean");
				oceanList.add("Pacific Ocean");
				oceanList.add("Southern Ocean");
				
				regionList.add("Do your choice...");
				regionList.add("Arctic Region");
				regionList.add("Atlantic Ocean Islands");
				regionList.add("Central Africa");
				regionList.add("Central America");
				regionList.add("Central Europe");
				regionList.add("Eastern Africa");
				regionList.add("Eastern Asia");
				regionList.add("Eastern Europe");
				regionList.add("Indian Ocean Islands");
				regionList.add("Middle East");
				regionList.add("North America");
				regionList.add("Northern Africa");
				regionList.add("Northern Europe");
				regionList.add("Pacific Islands");
				regionList.add("South America");
				regionList.add("Southcentral Asia");
				regionList.add("Southern Asia");
				regionList.add("Southern Europe");
				regionList.add("Western Africa");
				regionList.add("Western Asia");
				regionList.add("Western Europe");
				
				countryList.add("Do your choice...");
				countryList.add("Afghanistan");
				countryList.add("Albania");
				countryList.add("Algeria");
				countryList.add("Amazonia");
				countryList.add("American Samoa");
				countryList.add("Amsterdam And St. Paul Islands");
				countryList.add("Andaman Islands");
				countryList.add("Andorra");
				countryList.add("Angola");
				countryList.add("Anguilla");
				countryList.add("Antigua And Barbuda");
				countryList.add("Argentina");
				countryList.add("Armenia");
				countryList.add("Aruba");
				countryList.add("Ascension Island");
				countryList.add("Australia");
				countryList.add("Austria");
				countryList.add("Azerbaijan");
				countryList.add("Bahamas");
				countryList.add("Bahrain");
				countryList.add("Baker Island");
				countryList.add("Bangladesh");
				countryList.add("Barbados");
				countryList.add("Belarus");
				countryList.add("Belgium");
				countryList.add("Belize");
				countryList.add("Benin");
				countryList.add("Bhutan");
				countryList.add("Bolivia");
				countryList.add("Bonaire");
				countryList.add("Bosnia And Herzegovina");
				countryList.add("Botswana");
				countryList.add("Bouvet Island");
				countryList.add("Brazil");
				countryList.add("British Isles");
				countryList.add("Brunei");
				countryList.add("Bulgaria");
				countryList.add("Burkina Faso");
				countryList.add("Burma");
				countryList.add("Burundi");
				countryList.add("Cambodia");
				countryList.add("Cameroon");
				countryList.add("Canada");
				countryList.add("Caribbean");
				countryList.add("Cayman Islands");
				countryList.add("Central African Republic");
				countryList.add("Ceuta");
				countryList.add("Chad");
				countryList.add("Channel Islands");
				countryList.add("Chile");
				countryList.add("China");
				countryList.add("Christmas Island");
				countryList.add("Cocos Islands");
				countryList.add("Colombia");
				countryList.add("Comoros");
				countryList.add("Congo, Democratic Republic");
				countryList.add("Congo, Republic");
				countryList.add("Cook Islands");
				countryList.add("Corsica");
				countryList.add("Costa Rica");
				countryList.add("Cote D'ivoire");
				countryList.add("Croatia");
				countryList.add("Crozet Islands");
				countryList.add("Cuba");
				countryList.add("Curacao");
				countryList.add("Cyprus");
				countryList.add("Czech Republic");
				countryList.add("Denmark");
				countryList.add("Dhekelia");
				countryList.add("Djibouti");
				countryList.add("Dominica");
				countryList.add("Dominican Republic");
				countryList.add("Ecuador");
				countryList.add("Egypt");
				countryList.add("El Salvador");
				countryList.add("Equatorial Guinea");
				countryList.add("Eritrea");
				countryList.add("Estonia");
				countryList.add("Ethiopia");
				countryList.add("Faeroe Islands");
				countryList.add("Falkland Islands");
				countryList.add("Fiji");
				countryList.add("Finland");
				countryList.add("France");
				countryList.add("French Guiana");
				countryList.add("French Polynesia");
				countryList.add("Gabon");
				countryList.add("Galapagos Islands");
				countryList.add("Gambia");
				countryList.add("Gaza Strip");
				countryList.add("Georgia, Republic");
				countryList.add("Germany");
				countryList.add("Ghana");
				countryList.add("Gibraltar");
				countryList.add("Gough Island");
				countryList.add("Greece");
				countryList.add("Greenland");
				countryList.add("Grenada");
				countryList.add("Guadeloupe");
				countryList.add("Guam");
				countryList.add("Guatemala");
				countryList.add("Guinea");
				countryList.add("Guinea-Bissau");
				countryList.add("Guyana");
				countryList.add("Haiti");
				countryList.add("Hawaii Island");
				countryList.add("Hawaiian Islands");
				countryList.add("Honduras");
				countryList.add("Hong Kong");
				countryList.add("Howland Island");
				countryList.add("Hungary");
				countryList.add("Iceland");
				countryList.add("India");
				countryList.add("Indonesia");
				countryList.add("Iran");
				countryList.add("Iraq");
				countryList.add("Ireland");
				countryList.add("Israel");
				countryList.add("Italy");
				countryList.add("Jamaica");
				countryList.add("Japan");
				countryList.add("Jarvis Island");
				countryList.add("Johnston Atoll");
				countryList.add("Jordan");
				countryList.add("Kahoolawe");
				countryList.add("Kauai");
				countryList.add("Kazakhstan");
				countryList.add("Kenya");
				countryList.add("Kerguelen Islands");
				countryList.add("Kingman Reef");
				countryList.add("Kiribati");
				countryList.add("Kosovo");
				countryList.add("Kuwait");
				countryList.add("Kyrgyzstan");
				countryList.add("Laeso");
				countryList.add("Lake Chad");
				countryList.add("Lake Malawi");
				countryList.add("Lake Tanganyika");
				countryList.add("Lake Victoria");
				countryList.add("Lanai");
				countryList.add("Laos");
				countryList.add("Latvia");
				countryList.add("Lebanon");
				countryList.add("Lesotho");
				countryList.add("Liberia");
				countryList.add("Libya");
				countryList.add("Liechtenstein");
				countryList.add("Lithuania");
				countryList.add("Luxembourg");
				countryList.add("Macau");
				countryList.add("Macedonia");
				countryList.add("Madagascar");
				countryList.add("Madeira");
				countryList.add("Malawi");
				countryList.add("Malaysia");
				countryList.add("Maldives");
				countryList.add("Mali");
				countryList.add("Malta");
				countryList.add("Marshall Islands");
				countryList.add("Martinique");
				countryList.add("Maui");
				countryList.add("Mauritania");
				countryList.add("Mauritius");
				countryList.add("Melanesia");
				countryList.add("Mexico");
				countryList.add("Micronesia");
				countryList.add("Midway Atoll");
				countryList.add("Moldova");
				countryList.add("Molokai");
				countryList.add("Monaco");
				countryList.add("Mongolia");
				countryList.add("Montenegro");
				countryList.add("Montserrat");
				countryList.add("Morocco");
				countryList.add("Mozambique");
				countryList.add("Namibia");
				countryList.add("Nauru");
				countryList.add("Navassa Island");
				countryList.add("Nepal");
				countryList.add("Netherlands Antilles");
				countryList.add("Netherlands");
				countryList.add("New Caledonia");
				countryList.add("New Zealand");
				countryList.add("Nicaragua");
				countryList.add("Nicobar Islands");
				countryList.add("Niger");
				countryList.add("Nigeria");
				countryList.add("Niihau");
				countryList.add("Niue");
				countryList.add("Norfolk Island");
				countryList.add("North Korea");
				countryList.add("Northern Mariana Islands");
				countryList.add("Norway");
				countryList.add("Oahu");
				countryList.add("Okinawa");
				countryList.add("Oman");
				countryList.add("Pakistan");
				countryList.add("Palau");
				countryList.add("Palestine");
				countryList.add("Palmyra Atoll");
				countryList.add("Panama");
				countryList.add("Papua New Guinea");
				countryList.add("Paraguay");
				countryList.add("Peru");
				countryList.add("Philippines");
				countryList.add("Pitcairn Island");
				countryList.add("Pitcairn Islands");
				countryList.add("Poland");
				countryList.add("Polynesia");
				countryList.add("Portugal");
				countryList.add("Puerto Rico");
				countryList.add("Qatar");
				countryList.add("Red Sea");
				countryList.add("Reunion");
				countryList.add("Romania");
				countryList.add("Russia");
				countryList.add("Rwanda");
				countryList.add("Saba");
				countryList.add("Sable Island");
				countryList.add("Samoa");
				countryList.add("San Marino");
				countryList.add("Sao Tome And Principe");
				countryList.add("Sardinia");
				countryList.add("Saudi Arabia");
				countryList.add("Scandinavia");
				countryList.add("Senegal");
				countryList.add("Serbia");
				countryList.add("Seychelles");
				countryList.add("Sicily");
				countryList.add("Sierra Leone");
				countryList.add("Singapore");
				countryList.add("Slovakia");
				countryList.add("Slovenia");
				countryList.add("Solomon Islands");
				countryList.add("Somalia");
				countryList.add("South Africa");
				countryList.add("South Georgia Island");
				countryList.add("South Korea");
				countryList.add("South Orkney Islands");
				countryList.add("South Sandwich Islands");
				countryList.add("Spain");
				countryList.add("Spratly Islands");
				countryList.add("Sri Lanka");
				countryList.add("St Barthelemy");
				countryList.add("St Eustatius");
				countryList.add("St Helena");
				countryList.add("St Kitts And Nevis");
				countryList.add("St Lucia");
				countryList.add("St Maarten");
				countryList.add("St Martin");
				countryList.add("St Pierre And Miquelon");
				countryList.add("St Vincent And The Grenadines");
				countryList.add("Sudan");
				countryList.add("Suriname");
				countryList.add("Svalbard And Jan Mayen");
				countryList.add("Swaziland");
				countryList.add("Sweden");
				countryList.add("Switzerland");
				countryList.add("Syria");
				countryList.add("Taiwan");
				countryList.add("Tajikistan");
				countryList.add("Tanzania");
				countryList.add("Thailand");
				countryList.add("Togo");
				countryList.add("Tokelau");
				countryList.add("Tonga");
				countryList.add("Trinidad And Tobago");
				countryList.add("Tristan Da Cunha");
				countryList.add("Tunisia");
				countryList.add("Turkey");
				countryList.add("Turkmenistan");
				countryList.add("Turks And Caicos Islands");
				countryList.add("Tuvalu");
				countryList.add("Uganda");
				countryList.add("Ukraine");
				countryList.add("United Arab Emirates");
				countryList.add("United Kingdom");
				countryList.add("United States of America");
				countryList.add("Uruguay");
				countryList.add("Uzbekistan");
				countryList.add("Vanuatu");
				countryList.add("Vatican City");
				countryList.add("Venezuela");
				countryList.add("Vietnam");
				countryList.add("Virgin Islands");
				countryList.add("Wake Island");
				countryList.add("Wallis And Futuna Islands");
				countryList.add("Yemen");
				countryList.add("Zambia");
				countryList.add("Zanzibar");
				countryList.add("Zimbabwe");
				
				resolutionList.add("Scale");
				resolutionList.add("Distance");
				
				roleList.add("Author");
				roleList.add("Custodian");
				roleList.add("Distributor");
				roleList.add("Originator");
				roleList.add("Owner");
				roleList.add("Point of Contact");
				roleList.add("Principal Investigator");
				roleList.add("Processor");
				roleList.add("Publisher");
				roleList.add("Resource Provider");
				roleList.add("User");

				languageMap.put("Bulgarian","bul");
				languageMap.put("Czech","ces");
				languageMap.put("Danish","dan");
				languageMap.put("Dutch","dut");
				languageMap.put("English","eng");
				languageMap.put("Estonian","est");
				languageMap.put("Finnish","fin");
				languageMap.put("French","fra");
				languageMap.put("German","ger");
				languageMap.put("Greek","gre");
				languageMap.put("Hungarian","hun");
				languageMap.put("Irish","gle");
				languageMap.put("Italian","ita");
				languageMap.put("Latvian","lav");
				languageMap.put("Lithuanian","lit");
				languageMap.put("Maltese","mlt");
				languageMap.put("Polish","pol");
				languageMap.put("Portuguese","por");
				languageMap.put("Romanian","rum");
				languageMap.put("Slovak","slo");
				languageMap.put("Slovenian", "slv");
				languageMap.put("Spanish","spa");
				languageMap.put("Swedish","swe");
				
				categoriesMap.put("Biota","biota");
				categoriesMap.put("Boundaries","boundaries");
				categoriesMap.put("Climatology / Meteorology / Atmosphere","climatologyMeteorologyAtmosphere");
				categoriesMap.put("Economy","economy");
				categoriesMap.put("Elevation","elevation");
				categoriesMap.put("Environment","environment");
				categoriesMap.put("Farming","farming");
				categoriesMap.put("Geoscientific Information","geoscientificInformation");
				categoriesMap.put("Health","health");
				categoriesMap.put("Imagery / Base Maps / Earth Cover","imageryBaseMapsEarthCover");
				categoriesMap.put("Intelligence / Military","intelligenceMilitary");
				categoriesMap.put("Inland Waters","inlandWaters");
				categoriesMap.put("Location","location");
				categoriesMap.put("Oceans","oceans");
				categoriesMap.put("Planning / Cadastre","planningCadastre");
				categoriesMap.put("Society","society");
				categoriesMap.put("Structure","structure");
				categoriesMap.put("Transportation","transportation");
				categoriesMap.put("Utilities / Communication","utilitiesCommunication");
				
				/*keywordsMap.put("Agricultural engineering","agriculturalEngineering");
				keywordsMap.put("Agricultural plant science","agriculturalPlantScience");
				keywordsMap.put("Food science","foodScience");
				keywordsMap.put("Forest science","forestScience");
				keywordsMap.put("Soils","soils");
				keywordsMap.put("Aerosols","aerosols");
				keywordsMap.put("Air Quality","airQuality");
				keywordsMap.put("Altitude","altitude");
				keywordsMap.put("Atmospheric chemistry","atmosphericChemistry");
				keywordsMap.put("Atmospheric electricity","atmosphericElectricity");
				keywordsMap.put("Atmospheric phenomena","atmosphericPhenomena");
				keywordsMap.put("Atmospheric pressure","atmosphericPressur");
				keywordsMap.put("Atmospheric radiation","atmosphericRadiation");
				keywordsMap.put("Atmospheric temperature","atmosphericTemperature");
				keywordsMap.put("Atmospheric water vapor","atmosphericVapor");
				keywordsMap.put("Atmospheric winds","atmosphericWinds");
				keywordsMap.put("Clouds","clouds");
				keywordsMap.put("Precipitation","precipitation");
				keywordsMap.put("Ecological dynamics","ecologicalDynamics");
				keywordsMap.put("Terrestrial ecosystems","terrestrialEcosystems");
				keywordsMap.put("Vegetation","vegetation");
				keywordsMap.put("Frozen ground","frozenGround");
				keywordsMap.put("Glaciers / Ice sheets","glaciersIcesheet");
				keywordsMap.put("Sea ice","seaIce");
				keywordsMap.put("Snow / Ice","snowIce");
				keywordsMap.put("Erosion / Sedimentation","erosionSedimentation");
				keywordsMap.put("Geomorphology","geomorphology");
				keywordsMap.put("Land temperature","landTemperature");
				keywordsMap.put("Land use / Land cover","landCover");
				keywordsMap.put("Landscape","landscape");
				keywordsMap.put("Surface radiative properties","surfaceProperties");
				keywordsMap.put("Topography","topography");
				keywordsMap.put("Bathymetry / Seafloor topography","bathymetry");
				keywordsMap.put("Coastal processes","coastalProcesses");
				keywordsMap.put("Marine environment monitoring","marineEnvironment");
				keywordsMap.put("Marine geophysics","marineGeophysics");
				keywordsMap.put("Ocean waves","oceanWaves");
				keywordsMap.put("Ocean winds","oceanWinds");
				keywordsMap.put("Sea surface topography","seaTopography");
				keywordsMap.put("Tides","seaTides");
				keywordsMap.put("Water quality","waterQuality");
				keywordsMap.put("Geodetics","geodetics");
				keywordsMap.put("Geomagnetism","geomagnetism");
				keywordsMap.put("Geomorphic landforms / processes","geomorphicLandforms");
				keywordsMap.put("Gravity / gravitational field","gravity");
				keywordsMap.put("Gamma Ray","gammaRay");
				keywordsMap.put("Infrared wavelengths","irWavelengths");
				keywordsMap.put("LIDAR","lidar");
				keywordsMap.put("Microwave","microwave");
				keywordsMap.put("RADAR","radar");
				keywordsMap.put("Radio wave","radioWave");
				keywordsMap.put("Ultraviolet wavelengths","uvWavelentghs");
				keywordsMap.put("Visible wavelengths","visWavelentghs");
				keywordsMap.put("X-ray","xRay");
				keywordsMap.put("Ionosphere / Magnetosphere dynamics","ionosphereMagnetosphere");
				keywordsMap.put("Solar activity","solarActivity");
				keywordsMap.put("Solar energetic particle","solarParticle");
				keywordsMap.put("Ground Water","groundWater");
				keywordsMap.put("Surface water","surfaceWater");
				keywordsMap.put("Water quality / Water chemistry","waterQuality");*/
				
				keywordsMap.put("Agricultural engineering","Agricultural engineering");
				keywordsMap.put("Agricultural plant science","Agricultural plant science");
				keywordsMap.put("Food science","Food science");
				keywordsMap.put("Forest science","Forest science");
				keywordsMap.put("Soils","Soils");
				keywordsMap.put("Aerosols","Aerosols");
				keywordsMap.put("Air quality","Air quality");
				keywordsMap.put("Altitude","Altitude");
				keywordsMap.put("Atmospheric chemistry","Atmospheric chemistry");
				keywordsMap.put("Atmospheric electricity","Atmospheric electricity");
				keywordsMap.put("Atmospheric phenomena","Atmospheric phenomena");
				keywordsMap.put("Atmospheric pressure","Atmospheric pressure");
				keywordsMap.put("Atmospheric radiation","Atmospheric radiation");
				keywordsMap.put("Atmospheric temperature","Atmospheric temperature");
				keywordsMap.put("Atmospheric water vapor","Atmospheric water vapor");
				keywordsMap.put("Atmospheric winds","Atmospheric winds");
				keywordsMap.put("Clouds","Clouds");
				keywordsMap.put("Precipitation","Precipitation");
				keywordsMap.put("Ecological dynamics","Ecological dynamics");
				keywordsMap.put("Terrestrial ecosystems","Terrestrial ecosystems");
				keywordsMap.put("Vegetation","Vegetation");
				keywordsMap.put("Frozen ground","Frozen ground");
				keywordsMap.put("Glaciers / Ice sheet","Glaciers / Ice sheet");
				keywordsMap.put("Sea ice","Sea ice");
				keywordsMap.put("Snow / Ice","Snow / Ice");
				keywordsMap.put("Erosion / Sedimentation","Erosion / Sedimentation");
				keywordsMap.put("Geomorphology","Geomorphology");
				keywordsMap.put("Land temperature","Land temperature");
				keywordsMap.put("Land use / Land cover","Land use / Land cover");
				keywordsMap.put("Landscape","Landscape");
				keywordsMap.put("Surface radiative properties","Surface radiative properties");
				keywordsMap.put("Topography","Topography");
				keywordsMap.put("Bathymetry","Bathymetry / Seafloor topography");
				keywordsMap.put("Coastal processes","Coastal processes");
				keywordsMap.put("Marine environment","Marine environment");
				keywordsMap.put("Marine geophysics","Marine geophysics");
				keywordsMap.put("Ocean waves","Ocean waves");
				keywordsMap.put("Ocean winds","Ocean winds");
				keywordsMap.put("Sea surface topography","Sea surface topography");
				keywordsMap.put("Tides","Tides");
				keywordsMap.put("Water quality","Water quality");
				keywordsMap.put("Geodetics","Geodetics");
				keywordsMap.put("Geomagnetism","Geomagnetism");
				keywordsMap.put("Geomorphic landforms","Geomorphic landforms / processes");
				keywordsMap.put("Gravity","Gravity / gravitational field");
				keywordsMap.put("Gamma ray","Gamma ray");
				keywordsMap.put("Infrared wavelengths","Infrared wavelengths");
				keywordsMap.put("LIDAR","LIDAR");
				keywordsMap.put("Microwave","Microwave");
				keywordsMap.put("RADAR","RADAR");
				keywordsMap.put("Radio wave","Radio wave");
				keywordsMap.put("Ultraviolet wavelengths","Ultraviolet wavelengths");
				keywordsMap.put("Visible wavelengths","Visible wavelengths");
				keywordsMap.put("X-ray","X-ray");
				keywordsMap.put("Ionosphere / Magnetosphere","Ionosphere / Magnetosphere dynamics");
				keywordsMap.put("Solar activity","Solar activity");
				keywordsMap.put("Solar energetic particle","Solar energetic particle");
				keywordsMap.put("Ground water","Ground water");
				keywordsMap.put("Surface water","Surface water");
				keywordsMap.put("Water quality / chemistry","Water quality / chemistry");
				
				operatorMap.put("","");
				operatorMap.put("Alfred Wegener Institute (AWI)","AWI");
				operatorMap.put("Consiglio Nazionale delle Ricerche (CNR) - ISAFoM","CNR-ISAFoM");
				operatorMap.put("Consiglio Nazionale delle Ricerche (CNR) - IMAA","CNR-IMAA");
				operatorMap.put("Deutsches Zentrum fur Luft- und Raumfahrt (DLR)","DLR");
				operatorMap.put("ENVISCOPE","Enviscope");
				operatorMap.put("Facility for Airborne Atmospheric Measurements (FAAM)","FAAM");
				operatorMap.put("Freie Universitat Berlin (FUB) - ISS","FUB-ISS");
				operatorMap.put("Instituto Nacional de Tecnica Aeroespacial (INTA)","INTA");
				operatorMap.put("Karlsruhe Institute of Technology (KIT) - IMK-IFU","KIT-IMK-IFU");
				operatorMap.put("Natural Environment Research Council (NERC) - ARSF","NERC-ARSF");
				operatorMap.put("Natural Environment Research Council (NERC) - BAS","NERC-BAS");
				operatorMap.put("Service des Avions Francais Instrumentés pour la Recherche en Environnement (SAFIRE)","SAFIRE");
				operatorMap.put("The University of Edinburgh (UEDIN) - IAES","UEDIN-IAES");
				
				roleMap.put("Author","author");
				roleMap.put("Custodian","custodian");
				roleMap.put("Distributor","distributor");
				roleMap.put("Originator","originator");
				roleMap.put("Owner","owner");
				roleMap.put("Point of Contact","pointOfContact");
				roleMap.put("Principal Investigator","principalInvestigator");
				roleMap.put("Processor","processor");
				roleMap.put("Publisher","publisher");
				roleMap.put("Resource Provider","resourceProvider");
				roleMap.put("User","user");
				
				typeMap.put("Dataset","dataset");
				typeMap.put("Series","series");
				
				requiredField.put(identTitleBox,identTitleLab);
				requiredField.put(identAbstractAre,identAbstractLab);
				requiredField.put(identLocatorBox,identLocatorLab);
				requiredField.put(identIdentifierBox,identIdentifierLab);
				requiredField.put(geoNorthBox,geoBoundingLab);
				requiredField.put(geoWestBox,geoBoundingLab);
				requiredField.put(geoEastBox,geoBoundingLab);
				requiredField.put(geoSouthBox,geoBoundingLab);
				requiredField.put(geoResolutionBox,geoResolutionLab);
				requiredField.put(useConditionsBox,useConditionsLab);
				requiredField.put(useLimitationsBox,useLimitationsLab);
				requiredField.put(orgPartyBox,orgPartyLab);
				requiredField.put(orgEmailBox,orgEmailLab);
				requiredField.put(metNameBox,metNameLab);
				requiredField.put(metEmailBox,metEmailLab);

				correctField.put(identTitleBox,"string");
				correctField.put(identAbstractAre,"string");
				correctField.put(identLocatorBox,"string");
				correctField.put(identIdentifierBox,"string");
				correctField.put(geoNorthBox,"number");
				correctField.put(geoWestBox,"number");
				correctField.put(geoEastBox,"number");
				correctField.put(geoSouthBox,"number");
				correctField.put(geoResolutionBox,"number");
				correctField.put(geoUnitBox,"string");
				correctField.put(useConditionsBox,"string");
				correctField.put(useLimitationsBox,"string");
				correctField.put(orgPartyBox,"string");
				correctField.put(orgEmailBox,"email");
				correctField.put(metNameBox,"string");
				correctField.put(metEmailBox,"email");
				
				
				
			
				// Commands in the menu bar
			  	Command aboutWindow = new Command() {
			  			public void execute() {
			  					aboutWindow();
			  			}
				};
		  	
				Command aboutStandard = new Command() {
		  				public void execute() {
		  						Window.open("http://inspire.ec.europa.eu/", "_blank", "");
		  				}
				};
				
				Command newFile = new Command() {
						public void execute() {
								newFile();
						}
				};
				
				Command openFile = new Command() {
						public void execute() {
								openFile();
						}
				};
				
				Command saveFile = new Command() {
						public void execute() {
								runCheck();
								
						}
				};
				
				Command launchN7SPPage = new Command()  {
						public void execute() {
								Window.open("http://www.eufar.net/N6SP", "_blank", "");
						}
				};
				
				Command exitFile = new Command()  {
						public void execute() {
								Window.open("http://www.eufar.net", "_self", "");
						}
				};
			

				// Assemble the Menu panel
				aboutMenu.addItem(new MenuItem(imageAbout,true,aboutWindow));
				aboutMenu.addItem(new MenuItem(imageStandard,true,aboutStandard));
				aboutMenu.addItem(new MenuItem(imageEufar,true,launchN7SPPage));
				fileMenu.addItem(new MenuItem(imageNew,true,newFile));
				fileMenu.addItem(new MenuItem(imageOpen,true,openFile));
				fileMenu.addItem(new MenuItem(imageSave,true,saveFile));
				fileMenu.addItem(new MenuItem(imageExit,true,exitFile));
				mainMenu.addItem("File", fileMenu);
				mainMenu.addItem("About", aboutMenu);
				
				
				// Assemble Identification panel
				horizontalPanel01.add(identTitleBox);
			    horizontalPanel01.add(addInfoButton("idTitle"));
			    horizontalPanel02.add(identAbstractAre);
			    horizontalPanel02.add(addInfoButton("idAbstract"));
			    horizontalPanel03.add(identTypeLst);
			    horizontalPanel03.add(addInfoButton("idType"));
			    horizontalPanel04.add(identLocatorBox);
			    horizontalPanel04.add(addInfoButton("idLocator"));
			    horizontalPanel05.add(identIdentifierBox);
			    horizontalPanel05.add(addInfoButton("idIdentifier"));
			    horizontalPanel06.add(identLanguageLst);
			    horizontalPanel06.add(addInfoButton("idLanguage"));
			    idGrid.setWidget(0, 0, identTitleLab);
			    idGrid.setWidget(0, 1, horizontalPanel01);
			    idGrid.setWidget(1, 0, identAbstractLab);
			    idGrid.setWidget(1, 1, horizontalPanel02);
			    idGrid.setWidget(2, 0, identTypeLab);
			    idGrid.setWidget(2, 1, horizontalPanel03);
			    idGrid.setWidget(3, 0, identLocatorLab);
			    idGrid.setWidget(3, 1, horizontalPanel04);
			    idGrid.setWidget(4, 0, identIdentifierLab);
			    idGrid.setWidget(4, 1, horizontalPanel05);
			    idGrid.setWidget(5, 0, identLanguageLab);
			    idGrid.setWidget(5, 1, horizontalPanel06);
			    populateListBox(identTypeLst, typeList, 0);
			    populateListBox(identLanguageLst, languageList, 4);
			    identTitleBox.setStyleName("identTextBox");
			    identLocatorBox.setStyleName("identTextBox");
			    identIdentifierBox.setStyleName("identTextBox");
			    identTypeLst.setStyleName("identTextList");
			    identLanguageLst.setStyleName("identTextList");
			    identAbstractAre.setStyleName("identTextArea");
			    identTitleLab.setStyleName("identTextLabel");
			    identAbstractLab.setStyleName("identTextLabel");
			    identTypeLab.setStyleName("identTextLabel");
			    identLocatorLab.setStyleName("identTextLabel");
			    identIdentifierLab.setStyleName("identTextLabel");
			    identLanguageLab.setStyleName("identTextLabel");
			    idGrid.getElement().setAttribute("style", "margin-left: 20px !important; margin-top: 10px !important;");
			    
			    
			    // Assemble Classification panel
			    horizontalPanel07.add(classCategoriesLab);
			    verticalPanel01.add(classBiotaCheck);
			    verticalPanel01.add(classBoundariesCheck);
			    verticalPanel01.add(classClimatologyCheck);
			    verticalPanel01.add(classEconomyCheck);
			    verticalPanel01.add(classElevationCheck);
			    verticalPanel01.add(classEnvironmentCheck);
			    verticalPanel01.add(classFarmingCheck);
			    verticalPanel01.add(classInformationCheck);
			    verticalPanel01.add(classHealthCheck);
			    verticalPanel01.add(classImageryCheck);
			    verticalPanel02.add(classIntelligenceCheck);
			    verticalPanel02.add(classWatersCheck);
			    verticalPanel02.add(classLocationCheck);
			    verticalPanel02.add(classOceansCheck);
			    verticalPanel02.add(classPlanningCheck);
			    verticalPanel02.add(classSocietyCheck);
			    verticalPanel02.add(classStructureCheck);
			    verticalPanel02.add(classTransportationCheck);
			    verticalPanel02.add(classCommunicationCheck);
			    horizontalPanel07.add(verticalPanel01);
			    horizontalPanel07.add(verticalPanel02);
			    PushButton classInfoButton = addInfoButton("Categories");
			    horizontalPanel07.add(classInfoButton);
			    classCategoriesLab.setStyleName("classTitleTextLabel");
			    classBiotaCheck.setStyleName("classCheckBox");
			    classBoundariesCheck.setStyleName("classCheckBox");
			    classClimatologyCheck.setStyleName("classCheckBox");
			    classEconomyCheck.setStyleName("classCheckBox");
			    classElevationCheck.setStyleName("classCheckBox");
			    classEnvironmentCheck.setStyleName("classCheckBox");
			    classFarmingCheck.setStyleName("classCheckBox");
			    classInformationCheck.setStyleName("classCheckBox");
			    classHealthCheck.setStyleName("classCheckBox");
			    classImageryCheck.setStyleName("classCheckBox");
			    classIntelligenceCheck.setStyleName("classCheckBox");
			    classWatersCheck.setStyleName("classCheckBox");
			    classLocationCheck.setStyleName("classCheckBox");
			    classOceansCheck.setStyleName("classCheckBox");
			    classSocietyCheck.setStyleName("classCheckBox");
			    classStructureCheck.setStyleName("classCheckBox");
			    classTransportationCheck.setStyleName("classCheckBox");
			    classCommunicationCheck.setStyleName("classCheckBox");
			    classPlanningCheck.setStyleName("classCheckBox");
			    verticalPanel01.setSpacing(10);
			    verticalPanel02.setSpacing(10);
			    verticalPanel02.setStyleName("classVerticalPanel");
			    classInfoButton.getElement().setAttribute("style", "margin-left: 80px !important; margin-top: 150px !important;");
			    
			    
			    // Assemble Keywords panel
			    verticalPanel03.add(keyAgricultureLab);
			    verticalPanel03.add(classAgEngineeringCheck);
			    verticalPanel03.add(classAgPlantCheck);
			    verticalPanel03.add(classAgFoodCheck);
			    verticalPanel03.add(classAgForestCheck);
			    verticalPanel03.add(classAgSoilsCheck);
			    verticalPanel03.add(keyAtmosphereLab);
			    verticalPanel03.add(classAtAerosolsCheck);
			    verticalPanel03.add(classAtAirCheck);
			    verticalPanel03.add(classAtAltitudeCheck);
			    verticalPanel03.add(classAtChemistryCheck);
			    verticalPanel03.add(classAtElectricityCheck);
			    verticalPanel03.add(classAtPhenomenaCheck);
			    verticalPanel03.add(classAtPressureCheck);
			    verticalPanel03.add(classAtRadiationCheck);
			    verticalPanel03.add(classAtTemperatureCheck);
			    verticalPanel03.add(classAtVapourCheck);
			    verticalPanel03.add(classAtWindsCheck);
			    verticalPanel03.add(classAtCloudsCheck);
			    verticalPanel04.add(classAtPrecipitationCheck);
			    verticalPanel04.add(keyBiosphereLab);
			    verticalPanel04.add(classBiDynamicsCheck);
			    verticalPanel04.add(classBiEcosystemsCheck);
			    verticalPanel04.add(classBiVegetationCheck);
			    verticalPanel04.add(keyCryosphereLab);
			    verticalPanel04.add(classCrGroundCheck);
			    verticalPanel04.add(classCrGlaciersCheck);
			    verticalPanel04.add(classCrIceCheck);
			    verticalPanel04.add(classCrSnowCheck);
			    verticalPanel04.add(keySurfaceLab);
			    verticalPanel04.add(classLsErosionCheck);
			    verticalPanel04.add(classLsGeomorphologyCheck);
			    verticalPanel04.add(classLsTemperatureCheck);
			    verticalPanel04.add(classLsCoverCheck);
			    verticalPanel04.add(classLsLandscapeCheck);
			    verticalPanel04.add(classLsSurfaceCheck);
			    verticalPanel04.add(classLsTopographyCheck);
			    verticalPanel05.add(keyOceanLab);
			    verticalPanel05.add(classOcBathymetryCheck);
			    verticalPanel05.add(classOcProcessesCheck);
			    verticalPanel05.add(classOcEnvironmentCheck);
			    verticalPanel05.add(classOcGeophysicsCheck);
			    verticalPanel05.add(classOcWavesCheck);
			    verticalPanel05.add(classOcWindsCheck);
			    verticalPanel05.add(classOcTopographyCheck);
			    verticalPanel05.add(classOcTidesCheck);
			    verticalPanel05.add(classOcQualityCheck);
			    verticalPanel05.add(keyEarthLab);
			    verticalPanel05.add(classSeGeodeticsCheck);
			    verticalPanel05.add(classSeGeomagnetismCheck);
			    verticalPanel05.add(classSeLandformsCheck);
			    verticalPanel05.add(classSeGravityCheck);
			    verticalPanel05.add(keySpectralLab);
			    verticalPanel05.add(classSpGammaCheck);
			    verticalPanel05.add(classSpInfraredCheck);
			    verticalPanel06.add(classSpLidarCheck);
			    verticalPanel06.add(classSpMicrowaveCheck);
			    verticalPanel06.add(classSpRadarCheck);
			    verticalPanel06.add(classSpRadioCheck);
			    verticalPanel06.add(classSpUltravioletCheck);
			    verticalPanel06.add(classSpVisibleCheck);
			    verticalPanel06.add(classSpXrayCheck);
			    verticalPanel06.add(keyInteractionsLab);
			    verticalPanel06.add(classInIonosphereCheck);
			    verticalPanel06.add(classInActivityCheck);
			    verticalPanel06.add(classInParticleCheck);
			    verticalPanel06.add(keyHydrosphereLab);
			    verticalPanel06.add(classThGroundCheck);
			    verticalPanel06.add(classThSurfaceCheck);
			    verticalPanel06.add(classThChemistryCheck);
			    keyAgricultureLab.setStyleName("keyTitleTextLabel");
				keyAtmosphereLab.setStyleName("keyTitleTextLabel");
				keyBiosphereLab.setStyleName("keyTitleTextLabel");
				keyCryosphereLab.setStyleName("keyTitleTextLabel");
				keySurfaceLab.setStyleName("keyTitleTextLabel");
				keyOceanLab.setStyleName("keyTitleTextLabel");
				keyEarthLab.setStyleName("keyTitleTextLabel");
				keySpectralLab.setStyleName("keyTitleTextLabel");
				keyInteractionsLab.setStyleName("keyTitleTextLabel");
				keyHydrosphereLab.setStyleName("keyTitleTextLabel");
			    verticalPanel04.setStyleName("classVerticalPanel");
			    verticalPanel05.setStyleName("classVerticalPanel");
			    verticalPanel06.setStyleName("classVerticalPanel");
			    verticalPanel03.setSpacing(3);
			    verticalPanel04.setSpacing(3);
			    verticalPanel05.setSpacing(3);
			    verticalPanel06.setSpacing(3);
			    PushButton keyInfoButton = addInfoButton("Keywords");
			    horizontalPanel07.add(keyInfoButton);
			    keyInfoButton.getElement().setAttribute("style", "margin-left: 40px !important; margin-top: 240px !important;");
			    horizontalPanel08.add(verticalPanel03);
			    horizontalPanel08.add(verticalPanel04);
			    horizontalPanel08.add(verticalPanel05);
			    horizontalPanel08.add(verticalPanel06);
			    horizontalPanel08.add(keyInfoButton);
			    
			    
			    // Assemble Aircraft and instruments panel
			    horizontalPanel09.add(airAircraftLab);
			    horizontalPanel09.add(airAircraftLst);
			    populateListBox(airAircraftLst, aircraftList, 0);
			    horizontalPanel09.add(addInfoButton("aiAircraft"));
			    verticalPanel08.add(horizontalPanel09);
			    airInformationTable.setWidget(0, 0, airManufacturerLab);
			    airInformationTable.setWidget(0, 1, airManufacturerInfo);
			    airInformationTable.setWidget(1, 0, airTypeLab);
			    airInformationTable.setWidget(1, 1, airTypeInfo);
			    airInformationTable.setWidget(2, 0, airOperatorLab);
			    airInformationTable.setWidget(2, 1, airOperatorInfo);
			    airInformationTable.setWidget(3, 0, airCountryLab);
			    airInformationTable.setWidget(3, 1, airCountryInfo);
			    airInformationTable.setWidget(4, 0, airRegistrationLab);
			    airInformationTable.setWidget(4, 1, airRegistrationInfo);
			    airInformationTable.setStyleName("airFlexTable");
			    airInformationTable.getColumnFormatter().setWidth(0, "200px");
			    airInformationTable.getColumnFormatter().setWidth(1, "500px");
			    airManufacturerLab.setStyleName("airFlexTableLabel1");
			    airTypeLab.setStyleName("airFlexTableLabel1");
			    airOperatorLab.setStyleName("airFlexTableLabel1");
			    airCountryLab.setStyleName("airFlexTableLabel1");
			    airRegistrationLab.setStyleName("airFlexTableLabel1");
			    airManufacturerInfo.setStyleName("airFlexTableLabel2");
			    airTypeInfo.setStyleName("airFlexTableLabel2");
			    airOperatorInfo.setStyleName("airFlexTableLabel2");
			    airCountryInfo.setStyleName("airFlexTableLabel2");
			    airRegistrationInfo.setStyleName("airFlexTableLabel2");
			    horizontalPanel10.add(airCopyrightImage);
			    horizontalPanel10.add(airCopyrightInfo);
			    horizontalPanel10.setStyleName("airHorizontalPanel");
			    airAircraftImg.setSize("400px","217px");
			    verticalPanel09.add(airAircraftImg);
			    verticalPanel09.add(horizontalPanel10);
			    horizontalPanel11.add(airInformationTable);
			    horizontalPanel11.add(verticalPanel09);
			    verticalPanel08.add(horizontalPanel11);
			    airAircraftLab.setStyleName("airTitleTextLabel");
			    airAircraftLst.setStyleName("airTextList");
			    verticalPanel10.add(verticalPanel08);
			    verticalPanel10.setStyleName("airVerticalPanel");
			    verticalPanel10.add(new HTML("<br />"));
			    verticalPanel10.add(new HTML("<hr />"));
			    horizontalPanel12.add(insInstrumentLab);
			    verticalPanel10.add(horizontalPanel12);
			    insInstrumentLab.setStyleName("airTitleTextLabel");
			    
			    airAircraftLst.addChangeHandler(new ChangeHandler() {
		    		@Override
		    		public void onChange(ChangeEvent event) {
		    				airInformationSet(airAircraftLst.getSelectedIndex());
		    		}
			    });
			    
			    
			    // Assemble Geographic Information panel
			    PushButton coordInfoButton = addInfoButton("giBox"); 
			    geoCoordTab.setWidget(1,0,geoBoundingLab);
			    geoCoordTab.setWidget(0,2,geoNorthBox);
			    geoCoordTab.setWidget(1,1,geoWestBox);
			    geoCoordTab.setWidget(1,2,geoCoordImage);
			    geoCoordTab.setWidget(1,3,geoEastBox);
			    geoCoordTab.setWidget(2,2,geoSouthBox);
			    geoCoordTab.setWidget(1,4,coordInfoButton);
			    FlexCellFormatter cellFormatter = geoCoordTab.getFlexCellFormatter();
			    cellFormatter.setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);
			    cellFormatter.setHorizontalAlignment(2, 2, HasHorizontalAlignment.ALIGN_CENTER);
			    horizontalPanel13.add(geoLocationLab);
			    horizontalPanel13.add(geoLocationLst);
			    horizontalPanel13.add(geoFollowImage);
			    horizontalPanel13.add(geoDetailLst);
			    horizontalPanel13.add(addInfoButton("giLocation"));
			    populateListBox(geoLocationLst, locationList, 0);
			    geoLocationLab.setStyleName("geoTitleTextLabel");
			    geoLocationLst.setStyleName("geoTextList");
			    geoDetailLst.setStyleName("geoTextList");
			    geoDetailLst.setEnabled(false);
			    geoFollowImage.getElement().setAttribute("style", "margin-left: 20px !important;");
			    verticalPanel11.add(horizontalPanel13);
			    verticalPanel11.add(geoCoordTab);
			    horizontalPanel16.add(geoResolutionLab);
			    horizontalPanel16.add(geoResolutionLst);
			    populateListBox(geoResolutionLst, resolutionList, 0);
			    horizontalPanel16.add(horizontalPanel17);
			    horizontalPanel17.add(geoResolutionBox);
			    horizontalPanel16.add(addInfoButton("giUnit")); 
			    geoResolutionLab.setStyleName("geoTitleTextLabel");
			    geoResolutionLst.setStyleName("geoTextList");
			    geoResolutionBox.setStyleName("geoTextBox2");
			    verticalPanel11.add(horizontalPanel16);
			    verticalPanel11.setStyleName("geoVerticalPanel");
			    coordInfoButton.getElement().setAttribute("style", "margin-left: 40px !important;");
			    geoBoundingLab.setStyleName("geoTitleTextLabel2");
			    geoNorthBox.setStyleName("geoTextBox");
			    geoSouthBox.setStyleName("geoTextBox");
			    geoEastBox.setStyleName("geoTextBox");
			    geoWestBox.setStyleName("geoTextBox");
			    geoCoordImage.getElement().setAttribute("style", "margin-top: 5px !important; width: 250px !important; height: 250px !important;");
			    geoCoordTab.getElement().setAttribute("style", "margin-top: 20px !important; margin-bottom: 30px !important;");
			    
			    
			    geoResolutionLst.addChangeHandler(new ChangeHandler() {
		    		@Override
		    		public void onChange(ChangeEvent event) {
		    				geoResolutionSet(geoResolutionLst.getSelectedIndex());
		    		}
			    });
			    
			    geoLocationLst.addChangeHandler(new ChangeHandler() {
		    		@Override
		    		public void onChange(ChangeEvent event) {
		    				geoLocationSet(geoLocationLst.getSelectedIndex());
		    		}
			    });
			    
			    
			    // Assemble Temporal Reference panel
			    horizontalPanel18.add(refPublicationDat);
			    refPublicationDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
			    refPublicationDat.setValue(new Date());
			    horizontalPanel18.add(addInfoButton("trPublication"));
			    horizontalPanel19.add(refRevisionDat);
			    refRevisionDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
			    refRevisionDat.setValue(new Date());
			    horizontalPanel19.add(addInfoButton("trRevision")); 
			    horizontalPanel20.add(refCreationDat);
			    refCreationDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
			    refCreationDat.setValue(new Date());
			    horizontalPanel20.add(addInfoButton("trCreation"));
			    refDateTab.setWidget(0, 0, refPublicationLab);
			    refDateTab.setWidget(0, 1, horizontalPanel18);
			    refDateTab.setWidget(1, 0, refRevisionLab);
			    refDateTab.setWidget(1, 1, horizontalPanel19);
			    refDateTab.setWidget(2, 0, refCreationLab);
			    refDateTab.setWidget(2, 1, horizontalPanel20);
			    refPublicationLab.setStyleName("refTextLabel");
			    refRevisionLab.setStyleName("refTextLabel");
			    refCreationLab.setStyleName("refTextLabel");
			    horizontalPanel18.setStyleName("refHorizontalPanel");
			    horizontalPanel19.setStyleName("refHorizontalPanel");
			    horizontalPanel20.setStyleName("refHorizontalPanel");
			    verticalPanel13.add(refDateTab);
			    verticalPanel13.getElement().setAttribute("style", "margin-left: 20px !important; margin-top: 10px !important;");
			    verticalPanel13.add(new HTML("<br />"));
			    verticalPanel13.add(new HTML("<hr />"));
			    verticalPanel13.add(new HTML("<br />"));
			    horizontalPanel21.add(refExtentLab);
			    refPhaseTab.setWidget(0, 0, refPhaseLab);
			    refPhaseTab.setWidget(0, 1, refStartDat);
			    refPhaseTab.setWidget(0, 2, refEndDat);
			    refPhaseTab.setWidget(0, 3, refEmptyImage);
			    refStartDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
			    refStartDat.setValue(new Date());
			    refEndDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
			    refEndDat.setValue(new Date());
			    refStartLst.add(refStartDat);
				refEndLst.add(refEndDat);
			    horizontalPanel21.add(refPhaseTab);
			    PushButton refPlusButton = addRefPlusButton();
			    horizontalPanel21.add(refPlusButton);
			    horizontalPanel21.add(addInfoButton("trPhase"));
			    refExtentLab.setStyleName("refTitleTextLabel");
			    refEmptyImage.setStyleName("emptyButton");
			    refPlusButton.getElement().setAttribute("style", "margin-left: 20px !important");
			    verticalPanel13.add(horizontalPanel21);
			    refPhaseLab.setStyleName("refTextLabel");
			    
			    
			    // Quality and Validity panel
			    verticalPanel14.add(valInfoLab);
			    
			    
			    // Access and Use Constraints panel
			    useConditionsAddTab.setWidget(0, 0, useConditionsBox);
			    useConditionsAddTab.setWidget(0, 1, auEmptyImage1);
			    useConditionsTab.setWidget(0, 0, useConditionsLab);
			    useConditionsTab.setWidget(0, 1, useConditionsAddTab);
			    PushButton usePlusButton1 = addUsePlusButton(useConditionsAddTab, useConditionsLst);
			    useConditionsTab.setWidget(0, 2, usePlusButton1);
			    useConditionsTab.setWidget(0, 3, addInfoButton("auConditions"));
			    verticalPanel15.add(useConditionsTab);
			    verticalPanel15.add(new HTML("<br />"));
			    verticalPanel15.add(new HTML("<hr />"));
			    verticalPanel15.add(new HTML("<br />"));
			    useLimitationsAddTab.setWidget(0, 0, useLimitationsBox);
			    useLimitationsAddTab.setWidget(0, 1, auEmptyImage2);
			    useLimitationsTab.setWidget(0, 0, useLimitationsLab);
			    useLimitationsTab.setWidget(0, 1, useLimitationsAddTab);
			    PushButton usePlusButton2 = addUsePlusButton(useLimitationsAddTab, useLimitationsLst);
			    useLimitationsTab.setWidget(0, 2, usePlusButton2);
			    useLimitationsTab.setWidget(0, 3, addInfoButton("auLimitations")); 
			    verticalPanel15.add(useLimitationsTab);
			    verticalPanel15.setStyleName("useVerticalPanel");
			    useConditionsLab.setStyleName("useTextLabel");
			    useLimitationsLab.setStyleName("useTextLabel");
			    useConditionsBox.setStyleName("useTextArea");
			    useLimitationsBox.setStyleName("useTextArea");
			    auEmptyImage1.setStyleName("emptyButton");
			    auEmptyImage2.setStyleName("emptyButton");
			    useConditionsLst.add(useConditionsBox);
				useLimitationsLst.add(useLimitationsBox);
			    
			    
			    // Assemble Responsible Organisations panel
			    horizontalPanel24.add(orgPartyBox);
			    horizontalPanel24.add(addInfoButton("roParty"));
			    horizontalPanel25.add(orgEmailBox);
			    horizontalPanel25.add(addInfoButton("roEmail")); 
			    horizontalPanel26.add(orgRoleLst);
			    populateListBox(orgRoleLst, roleList, 4);
			    horizontalPanel26.add(addInfoButton("roRole"));
			    orgPartyTab.setWidget(0, 0, orgPartyLab);
			    orgPartyTab.setWidget(0, 1, horizontalPanel24);
			    orgPartyTab.setWidget(1, 0, orgEmailLab);
			    orgPartyTab.setWidget(1, 1, horizontalPanel25);
			    orgPartyTab.setWidget(2, 0, orgRoleLab);
			    orgPartyTab.setWidget(2, 1, horizontalPanel26);
			    horizontalPanel27.add(orgPartyTab);
			    horizontalPanel27.add(orgEmptyImage);
			    PushButton orgPlusButton = addOrgPlusButton();
			    horizontalPanel27.add(orgPlusButton);
			    orgAddTab.setWidget(0, 0, horizontalPanel27);
			    orgAddTab.setStyleName("orgFlexTable");
			    orgPartyLab.setStyleName("orgTextLabel");
			    orgEmailLab.setStyleName("orgTextLabel");
			    orgRoleLab.setStyleName("orgTextLabel");
			    orgPartyBox.setStyleName("orgTextBox");
			    orgEmailBox.setStyleName("orgTextBox");
			    orgRoleLst.setStyleName("orgTextList");
			    orgEmptyImage.setStyleName("emptyButton");
			    orgPlusButton.getElement().setAttribute("style","margin-top: 45px !important;");
			    orgPartyLst.add(orgPartyBox);
				orgRole2Lst.add(orgRoleLst);
				orgEmailLst.add(orgEmailBox);
			    
			    
			    // Assemble Metadata on Metadata panel
			    horizontalPanel28.add(metDateDat);
			    metDateDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
			    metDateDat.setValue(new Date());
			    horizontalPanel28.add(addInfoButton("mmDate"));
			    horizontalPanel29.add(metLanguageLst);
			    populateListBox(metLanguageLst, languageList, 4);
			    horizontalPanel29.add(addInfoButton("mmLanguage")); 
			    metMetadataTab.setWidget(0, 0, metDateLab);
			    metMetadataTab.setWidget(0, 1, horizontalPanel28);
			    metMetadataTab.setWidget(1, 0, metLanguageLab);
			    metMetadataTab.setWidget(1, 1, horizontalPanel29);
			    verticalPanel16.add(metMetadataTab);
			    verticalPanel16.add(new HTML("<hr />"));
			    verticalPanel16.add(new HTML("<br />"));
			    metContactTab.setWidget(0, 0, metContactLab);
			    metPartyTab.setWidget(0, 0, metNameLab);
			    metPartyTab.setWidget(0, 1, metNameBox);
			    metPartyTab.setWidget(1, 0, metEmailLab);
			    metPartyTab.setWidget(1, 1, metEmailBox);
			    metAddTab.setWidget(0, 0, metPartyTab);
			    metAddTab.setWidget(0, 1, metEmptyImage);
			    metContactTab.setWidget(0, 1, metAddTab);
			    PushButton metPlusButton = addMetPlusButton();
			    metPlusButton.getElement().setAttribute("style", "margin-top: -5px !important;");
			    metContactTab.setWidget(0, 2, metPlusButton);
			    PushButton metInfoButton = addInfoButton("mmParty");
			    metInfoButton.getElement().setAttribute("style", "margin-top: -5px !important;");
			    metContactTab.setWidget(0, 3, metInfoButton);
			    verticalPanel16.add(metContactTab);
			    metDateLab.setStyleName("metTextLabel");
			    metLanguageLab.setStyleName("metTextLabel");
			    metLanguageLst.setStyleName("metTextList");
			    horizontalPanel28.setStyleName("metHorizontalPanel");
			    horizontalPanel29.setStyleName("metHorizontalPanel");
			    verticalPanel16.setStyleName("metVerticalPanel");
			    metContactLab.setStyleName("metTitleTextLabel");
			    metNameLab.setStyleName("metTextLabel");
			    metEmailLab.setStyleName("metTextLabel2");
			    metNameBox.setStyleName("metTextBox");
			    metEmailBox.setStyleName("metTextBox2");
			    metEmptyImage.setStyleName("emptyButton");
			    metNameLst.add(metNameBox);
				metEmailLst.add(metEmailBox);

			    
			    // Associate to stack panel
			    stackPanel.add(idScroll,"Identification", 2);
			    stackPanel.add(clScroll,"Classification", 2);
			    stackPanel.add(kwScroll,"Keywords", 2);
			    stackPanel.add(aiScroll,"Aircraft and Instruments", 2);
			    stackPanel.add(giScroll,"Geographic Information", 2);
			    stackPanel.add(trScroll,"Temporal Reference", 2);
			    stackPanel.add(qvScroll,"Quality and Validity", 2);
			    stackPanel.add(auScroll,"Access and Use Constraints", 2);
			    stackPanel.add(roScroll,"Responsible Organisations", 2);
			    stackPanel.add(mmScroll,"Metadata on Metadata", 2);
			
			
				// Associate the Main panel with the HTML host page.
			    int screen_width = Window.getClientWidth();
			    int screen_height = Window.getClientHeight();
			    int expandPanel = 0;
			    expandPanel = (screen_width - 1166)/2;
			    if (expandPanel < 0) {
			    		expandPanel = 0;
			    }
			    subDockPanel.addNorth(new HTML("<img src='icons/emc_top.jpg' alt='EUFAR Metadata Creator' height='80px' width='1166px'>"), 80);
			    subDockPanel.addNorth(mainMenu, 30);
			    dockPanel.addEast(new HTML("<img src='icons/emc_shadowr.png' alt='ASMM Creator' width='30px' height='" + screen_height 
			    		+ "' align='left'>Development version of EMC, online version " + emcVersion), expandPanel);
			    dockPanel.addWest(new HTML("<img src='icons/emc_shadowl.png' alt='ASMM Creator' width='30px' height='" + screen_height 
			    		+ "' align='right'>Development version of EMC, online version " + emcVersion), expandPanel);
			    dockPanel.setStyleName("dockPanel");
			    subDockPanel.add(stackPanel);
			    dockPanel.add(subDockPanel);
			    RootLayoutPanel rp=RootLayoutPanel.get();
			    rp.add(dockPanel);
			
		}
		
		
		/// update all Aircraft information when a particular aircraft is selected in the Aircraft and Instruments tab
		private void airInformationSet(final int index) {
			    airManufacturerInfo.setText(airAircraftInfo[index][1]);
			    airTypeInfo.setText(airAircraftInfo[index][2]);
			    airOperatorInfo.setText(airAircraftInfo[index][3]);
			    airCountryInfo.setText(airAircraftInfo[index][4]);
			    airRegistrationInfo.setText(airAircraftInfo[index][5]);
			    airCopyrightInfo.setText(airAircraftInfo[index][7]);
				String aircraftImage = airAircraftInfo[index][6];
			    airAircraftImg.setUrl("icons/eufar_aircrafts/" + aircraftImage);
		}
		
		
		/// change the resolution type, scale or distance, in theGeographic Information tab
		private void geoResolutionSet(final int index) {
				horizontalPanel17.clear();
				if (index == 0) {
						horizontalPanel17.add(geoResolutionBox);
						geoResolutionBox.setStyleName("geoTextBox2");
				}
				else {
						horizontalPanel17.add(geoResolutionBox);
						horizontalPanel17.add(geoUnitLab);
						horizontalPanel17.add(geoUnitBox);
						geoResolutionBox.setStyleName("geoTextBox3");
						geoUnitBox.setStyleName("geoTextBox3");
						geoUnitLab.setStyleName("geoTitleTextLabel");
						geoUnitLab.getElement().setAttribute("style", "margin-left: 20px !important;");
				}
		}
		
		
		/// let the user choose the location in the Geographic Information tab
		private void geoLocationSet(final int index) {
				if (index == 0) {
						geoDetailLst.clear();
						geoDetailLst.setEnabled(false);
				}
				else {
						geoDetailLst.clear();
						geoDetailLst.setEnabled(true);
						if (index == 1) {
								populateListBox(geoDetailLst, continentList, 0);
						}
						else if (index == 2) {
								populateListBox(geoDetailLst, countryList, 0);
						}
						else if (index == 3) {
								populateListBox(geoDetailLst, oceanList, 0);
						}
						else if (index == 4) {
								populateListBox(geoDetailLst, regionList, 0);
						}
				}
		}
		
		
		/// create all the Info buttons in the different tabs
		private PushButton addInfoButton(final String context) {
				Image image = new Image("icons/info_icon_small.png");
			    final PushButton infoButton = new PushButton(image);
			    infoButton.setPixelSize(25, 25);
			    infoButton.setStyleName("infoButton");
			    infoButton.addClickHandler(new ClickHandler() {
			    		public void onClick(ClickEvent event) {
			    				final DialogBox infoDialog = new DialogBox();
			    				infoDialog.setGlassEnabled(true);
			    				final VerticalPanel vpanel = new VerticalPanel();
			    				final HorizontalPanel hpanel = new HorizontalPanel();
			    				Image xImage = new Image("icons/info_icon_popup.png");
			    			    hpanel.add(xImage);
			    				hpanel.add(addInfoText(context));
			    				vpanel.add(hpanel);
			    				final Button button = new Button("Ok", new ClickHandler() {			
										@Override
										public void onClick(ClickEvent event) {
												infoDialog.hide();
										}
			    				});
			    				vpanel.add(button);
			    				button.getElement().setAttribute("style", "margin-left: 120px !important; font-family: DroidSansFallback !important;"
			    						+ " font-weight: bold !important; margin-top: 20px !important;");
			    				infoDialog.add(vpanel);
			    				infoDialog.setSize( "300px", "170px" );
			    				infoDialog.setModal(true);
			    				infoDialog.center();
							    infoDialog.setStyleName("myInfoBox");
							    infoDialog.showRelativeTo(infoButton);
			    		}
			    });
				return infoButton;
		}
		
		
		/// return the text asked for a particular Info button
		private Label addInfoText(final String context) {
				HTML infoLabel = new HTML();
				infoLabel.setStyleName("myInfoBoxText");
				switch (infoEnum.valueOf(context.toUpperCase())) {
				case IDTITLE:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>This is a characteristic, and often a unique name identifying the "
								+ "resource. The value domain of this metadata element is free text.</p><u>EUFAR:</u><br>Project acronym -- "
								+ "survey request form.");
						break;
				case IDABSTRACT:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>This is a brief narrative summary of the content of the resource. "
								+ "The value domain of this metadata element is free text.</p><u>EUFAR:</u><br>Abstract -- survey request form.");
						break;
				case IDTYPE:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>This is the type of resource being described by the metadata. The "
								+ "value domain of this metadata element is defined in Part D.1.</p><u>EUFAR:</u><br>Dataset or series.");
						break;
						
				case IDLOCATOR:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>The resource locator defines the link(s) to the resource and/or "
								+ "the link to additional information about the resource. The value domain of this metadata element is a "
								+ "character string, commonly expressed as uniform resource locator (URL).</p><u>EUFAR:</u><br>Link to data in "
								+ "EUFAR N8-DB or http://www.eufar.net.");
						break;
				case IDIDENTIFIER:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>A value uniquely identifying the resource. The value domain of "
								+ "this metadata element is a mandatory character string code, generally assigned by the data owner, and a "
								+ "character string namespace uniquely identifying the context of the identifier code (for example, the data "
								+ "owner).</p><u>EUFAR:</u><br>N8-DB file name specification.");
						break;
				case IDLANGUAGE:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>The language(s) used within the resource. The value domain of "
								+ "this metadata element is limited to the languages defined in ISO 639-2.</p><u>EUFAR:</u><br>English.");
						break;
				case CATEGORIES:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>The topic category is a high-level classification scheme to "
								+ "facilitate the grouping and topic-based search of available spatial data resources. The value domain of this "
								+ "metadata element is defined in Part D.2.</p><u>EUFAR:</u><br>Main scientific field -- survey request form.");
						break;
				case KEYWORDS:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>The keyword value is a commonly used word, formalised word or "
								+ "phrase used to describe the subject. While the topic category is too obscure for detailed queries, keywords "
								+ "can help narrowing a full text search and allow for structured keyword searches. The value domain of this "
								+ "metadata element is free text.</p><p align=justify><u>EUFAR:</u><br>Keywords are from NASA/Global Change "
								+ "Master Directory (GCMD) Earth Science Keywords.</p>");
						break;
				case AIAIRCRAFT:
						infoLabel.setHTML("<p align=justify><u>Specific to EUFAR:</u><br>This is the aircraft used to acquire the actual data "
								+ "and metadata.</p>");
						break;
				case AIINSTRUMENT:
						infoLabel.setHTML("<p align=justify><u>Specific to EUFAR:</u><br>This(these) is(are) the instrument(s) used to acquire "
								+ "the actual data and metadata.</p>");
						break;
				case GILOCATION:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>This is the name of the geographic space covered by the dataset."
								+ "</p><p align=justify><u>EUFAR:</u><br>Name of the geographic space -- survey request form.</p>");
						break;
				case GIBOX:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>This is the extent of the resource in the geographic space, given "
								+ "as a bounding box. The bounding box shall be expressed with westbound and eastbound longitudes, and southbound "
								+ "and northbound latitudes in decimal degrees, with a precision of at least two decimals.</p><p align=justify>"
								+ "<u>EUFAR:</u><br>Result of data processing plus information on country and region (survey request form).</p>");
						break;
				case GIUNIT:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>Spatial resolution refers to the level of detail of the data set. "
								+ "It shall be expressed as a set of zero up to many resolution distances (typically for gridded data and "
								+ "imagery-derived products) or equivalent scales (typically for maps or map-derived products). An equivalent "
								+ "scale is generally expressed as an integer value expressing the scale denominator. A resolution distance "
								+ "shall be expressed as a numerical value associated with a unit of length.</p><p align=justify><u>EUFAR:</u>"
								+ "<br>Result of data processing.</p>");
						break;
				case TRPUBLICATION:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>This is the date of publication of the resource when available, "
								+ "or the date of entry into force. There may be more than one date of publication.</p><p align=justify><u>"
								+ "EUFAR:</u><br>Date of entry into EUFAR N8-DB.</p>");
						break;
				case TRREVISION:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>This is the date of last revision of the resource, in the case "
								+ "that the resource has been revised. There shall not be more than one date of last revision.</p><p align="
								+ "justify><u>EUFAR:</u><br>Automatically created within EUFAR N8-DB (date stamp).</p>");
						break;
				case TRCREATION:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>This is the date of creation of the resource. There shall not "
								+ "be more than one date of creation.</p><p align=justify><u>EUFAR:</u><br>Date of data processing (related to "
								+ "product, which is stored in EUFAR N8-DB).</p>");
						break;
				case TRPHASE:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>The temporal extent defines the time period covered by the "
								+ "content of the resource. This time period may be expressed as any of the following: an individual date, an "
								+ "interval of dates expressed represented by the starting date and end date of the interval, or a mix of "
								+ "individual dates and intervals of dates.</p><p align=justify><u>EUFAR:</u><br>Date of data acquisition.</p>");
						break;
				case AUCONDITIONS:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>This metadata element defines the conditions for access and use "
								+ "of spatial data sets and services, and where applicable, corresponding fees as required by Article 5(2)(b) "
								+ "and Article 11(2)(f) of Directive 2007/2/EC. The value domain of this metadata element is free text. The "
								+ "element must have values. If no conditions apply to the access and use of the resource, 'no conditions apply'"
								+ " shall be used. If conditions are unknown, 'conditions unknown' shall be used. This element shall also "
								+ "provide information on any fees necessary to access and use the resource, if applicable, or refer to a "
								+ "uniform resource locator (URL) where information on fees is available.</p><p align=justify><u>EUFAR:</u><br>"
								+ "Consortium agreement for access usage restrictions.</p>");
						break;
				case AULIMITATIONS:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>When Member States limit public access to spatial data sets and "
								+ "spatial data services under Article 13 of Directive 2007/2/EC, the metadata element shall provide information "
								+ "on the limitations and the reasons for this. If there are no limitations on public access, the metadata "
								+ "element shall indicate this. The value domain of the metadata element is free text.</p><p align=justify><u>"
								+ "EUFAR:</u><br>Consortium agreement for public access limitations.</p>");
						break;
				case ROPARTY:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>This is the description of the organisation responsible for the "
								+ "establishment, management, maintenance and distribution of the resource. This description shall include:<ul>"
								+ "<li>the name of the organisation as free text</li><li>a contact e-mail address as a character string</li></ul>"
								+ "<p align=justify><u>EUFAR:</u><br>Party, who created the resource/data provider.</p>");
						break;
				case ROEMAIL:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>This is the description of the organisation responsible for the "
								+ "establishment, management, maintenance and distribution of the resource. This description shall include:<ul>"
								+ "<li>the name of the organisation as free text</li><li>a contact e-mail address as a character string</li></ul>"
								+ "<p align=justify><u>EUFAR:</u><br>Party, who created the resource/data provider.</p>");
						break;
				case ROROLE:
						infoLabel.setHTML("'<p align=justify><u>INSPIRE:</u><br>This is the role of the responsible organisation. The value domain "
								+ "of this metadata element is defined in Part D.</p><p align=justify><u>EUFAR:</u><br>pointOfContact and "
								+ "principalInvestigator (two different entries).</p>");
						break;
				case MMDATE:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>The date which specifies when the metadata record was created or "
								+ "updated. This date shall be expressed in conformity with ISO 8601.</p><p align=justify><u>EUFAR:</u><br>Date "
								+ "when the metadata record was created or updated (result of data processing or entry into DB).</p>");
						break;
				case MMLANGUAGE:
						infoLabel.setHTML("<p align=justify><u>INSPIRE:</u><br>This is the language in which the metadata elements are "
								+ "expressed. The value domain of this metadata element is limited to the official languages of the Community "
								+ "and is expressed in conformity with ISO 639-2.</p><p align=justify><u>EUFAR:</u><br>English.</p>");
						break;
				case MMPARTY:
						infoLabel.setHTML("<p align=justify><u>INSPIRE & EUFAR:</u><br>This is the name and the contact e-mail of the party "
								+ "responsible for the data set.</p>");
						break;
				default:
						infoLabel.setHTML("No information for this item");
						break;
					
				}
				infoLabel.setWordWrap(true);
				return infoLabel;
		}

		
		/// populate easily each List Box
		private void populateListBox(ListBox listBox, ArrayList<String> list, int defaultItem) {
			for (int i=0; i<list.size(); i++) { listBox.addItem(list.get(i)); }
			listBox.setItemSelected(defaultItem, true);
		}
		
		
		/// add a new period in the Temporal Reference panel
		private PushButton addRefPlusButton() {
				Image image = new Image("icons/plus_icon_small.png");
			    final PushButton plusButton = new PushButton(image);
			    plusButton.setPixelSize(25, 25);
			    plusButton.setStyleName("infoButton");
			    plusButton.addClickHandler(new ClickHandler() {
			    		public void onClick(ClickEvent event) {
			    				int row = refPhaseTab.getRowCount();
			    				DateBox dateBox1 = new DateBox();
			    				DateBox dateBox2 = new DateBox();
			    				dateBox1.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
			    				dateBox1.setValue(new Date());
			    				dateBox2.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
			    				dateBox2.setValue(new Date());
			    				Image image = new Image("icons/del_icon_small.png");
							    final PushButton delButton = new PushButton(image);
							    delButton.setPixelSize(25, 25);
							    delButton.setStyleName("infoButton");
							    Label label = new Label("Phase " + Integer.toString(row + 1) + ":");
							    label.setStyleName("refTextLabel");
							    refPhaseTab.insertRow(row);
							    refPhaseTab.setWidget(row, 0, label);
							    refPhaseTab.setWidget(row, 1, dateBox1);
							    refPhaseTab.setWidget(row, 2, dateBox2);
							    refPhaseTab.setWidget(row, 3, delButton);
							    refStartLst.add(dateBox1);
								refEndLst.add(dateBox2);
							    delButton.addClickHandler(new ClickHandler() {
							    		public void onClick(ClickEvent event) {
							    				int rowIndex = refPhaseTab.getCellForEvent(event).getRowIndex();
							    				refPhaseTab.removeRow(rowIndex);
							    				refStartLst.remove(rowIndex);
												refEndLst.remove(rowIndex);
							    		}
							    });	
			    		}
			    });
				return plusButton;
		}
		
		
		/// allow the program to create new periods based on the reading of an xml file
		private void addRefRead(final String dateStart, final String dateEnd) {
				int row = refPhaseTab.getRowCount();
				DateBox dateBox1 = new DateBox();
				DateBox dateBox2 = new DateBox();
				dateBox1.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
				dateBox1.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(dateStart));
				dateBox2.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
				dateBox2.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(dateEnd));
				Image image = new Image("icons/del_icon_small.png");
			    final PushButton delButton = new PushButton(image);
			    delButton.setPixelSize(25, 25);
			    delButton.setStyleName("infoButton");
			    Label label = new Label("Phase " + Integer.toString(row + 1) + ":");
			    label.setStyleName("refTextLabel");
			    refPhaseTab.insertRow(row);
			    refPhaseTab.setWidget(row, 0, label);
			    refPhaseTab.setWidget(row, 1, dateBox1);
			    refPhaseTab.setWidget(row, 2, dateBox2);
			    refPhaseTab.setWidget(row, 3, delButton);
			    refStartLst.add(dateBox1);
				refEndLst.add(dateBox2);
			    delButton.addClickHandler(new ClickHandler() {
			    		public void onClick(ClickEvent event) {
			    				int rowIndex = refPhaseTab.getCellForEvent(event).getRowIndex();
			    				refPhaseTab.removeRow(rowIndex);
			    				refStartLst.remove(rowIndex);
								refEndLst.remove(rowIndex);
			    		}
			    });	
		}
		
		
		/// add new text areas in the Access and Use Constraints tabs
		private PushButton addUsePlusButton(final FlexTable table, final ArrayList<TextArea> arrayList) {
				Image image = new Image("icons/plus_icon_small.png");
			    final PushButton plusButton = new PushButton(image);
			    plusButton.setPixelSize(25, 25);
			    plusButton.setStyleName("infoButton");
			    plusButton.addClickHandler(new ClickHandler() {
			    		public void onClick(ClickEvent event) {
			    				int row = table.getRowCount();
			    				TextArea textArea = new TextArea();
			    				textArea.setStyleName("useTextArea");
			    				Image image = new Image("icons/del_icon_small.png");
							    final PushButton delButton = new PushButton(image);
							    delButton.setPixelSize(25, 25);
							    delButton.setStyleName("infoButton");
							    table.insertRow(row);
							    table.setWidget(row, 0, textArea);
							    table.setWidget(row, 1, delButton);
							    arrayList.add(textArea);
							    delButton.addClickHandler(new ClickHandler() {
							    		public void onClick(ClickEvent event) {
							    				int rowIndex = table.getCellForEvent(event).getRowIndex();
							    				table.removeRow(rowIndex);
							    				arrayList.remove(rowIndex);
							    		}
							    });	
			    		}
			    });
				return plusButton;
		}
		
		
		/// allow the program to create new text areas based on the reading of an xml file
		private void addUseRead(final FlexTable table, final ArrayList<TextArea> arrayList, final String string) {
				int row = table.getRowCount();
				TextArea textArea = new TextArea();
				textArea.setText(string);
				textArea.setStyleName("useTextArea");
				Image image = new Image("icons/del_icon_small.png");
			    final PushButton delButton = new PushButton(image);
			    delButton.setPixelSize(25, 25);
			    delButton.setStyleName("infoButton");
			    table.insertRow(row);
			    table.setWidget(row, 0, textArea);
			    table.setWidget(row, 1, delButton);
			    arrayList.add(textArea);
			    delButton.addClickHandler(new ClickHandler() {
			    		public void onClick(ClickEvent event) {
			    				int rowIndex = table.getCellForEvent(event).getRowIndex();
			    				table.removeRow(rowIndex);
			    				arrayList.remove(rowIndex);
			    		}
			    });	
		}
		
		
		/// add new text boxes in the Responsible Organisations tab
		private PushButton addOrgPlusButton() {
				Image image = new Image("icons/plus_icon_small.png");
			    final PushButton plusButton = new PushButton(image);
			    plusButton.setPixelSize(25, 25);
			    plusButton.setStyleName("infoButton");
			    plusButton.addClickHandler(new ClickHandler() {
			    		public void onClick(ClickEvent event) {
			    				VerticalPanel verticalPanel01 = new VerticalPanel();
			    				HorizontalPanel horizontalPanel01 = new HorizontalPanel();
			    				HorizontalPanel horizontalPanel02 = new HorizontalPanel();
			    				HorizontalPanel horizontalPanel03 = new HorizontalPanel();
			    				HorizontalPanel horizontalPanel04 = new HorizontalPanel();
			    				Label orgPartyLab = new Label("Responsible party:");
			    				Label orgEmailLab = new Label("Responsible party e-mail:");
			    				Label orgRoleLab = new Label("Responsible party role:");
			    				TextBox orgPartyBox = new TextBox();
			    				TextBox orgEmailBox = new TextBox();
			    				ListBox orgRoleLst = new ListBox();
			    				FlexTable orgPartyTab = new FlexTable();
			    				verticalPanel01.add(new HTML("<hr />"));
			    				verticalPanel01.add(new HTML("<br />"));	
				    			horizontalPanel01.add(orgPartyBox);
							    horizontalPanel01.add(addInfoButton("roParty"));
							    horizontalPanel02.add(orgEmailBox);
							    horizontalPanel02.add(addInfoButton("roEmail"));
							    horizontalPanel03.add(orgRoleLst);
							    populateListBox(orgRoleLst, roleList, 4);
							    horizontalPanel03.add(addInfoButton("roRole"));
							    orgPartyTab.setWidget(0, 0, orgPartyLab);
							    orgPartyTab.setWidget(0, 1, horizontalPanel01);
							    orgPartyTab.setWidget(1, 0, orgEmailLab);
							    orgPartyTab.setWidget(1, 1, horizontalPanel02);
							    orgPartyTab.setWidget(2, 0, orgRoleLab);
							    orgPartyTab.setWidget(2, 1, horizontalPanel03);
							    horizontalPanel04.add(orgPartyTab);
							    orgPartyLab.setStyleName("orgTextLabel");
							    orgEmailLab.setStyleName("orgTextLabel");
							    orgRoleLab.setStyleName("orgTextLabel");
							    orgPartyBox.setStyleName("orgTextBox");
							    orgEmailBox.setStyleName("orgTextBox");
							    orgRoleLst.setStyleName("orgTextList");
							    orgPartyLst.add(orgPartyBox);
								orgRole2Lst.add(orgRoleLst);
								orgEmailLst.add(orgEmailBox);
							    Image image = new Image("icons/del_icon_small.png");
							    final PushButton delButton = new PushButton(image);
							    delButton.setPixelSize(25, 25);
							    delButton.setStyleName("infoButton");
							    delButton.getElement().setAttribute("style","margin-top: 45px !important;");
							    horizontalPanel04.add(delButton);
							    verticalPanel01.add(horizontalPanel04);
			    				int row = orgAddTab.getRowCount();
							    orgAddTab.insertRow(row);
							    orgAddTab.setWidget(row, 0, verticalPanel01);
							    delButton.addClickHandler(new ClickHandler() {
							    		public void onClick(ClickEvent event) {
							    				int rowIndex = orgAddTab.getCellForEvent(event).getRowIndex();
							    				orgAddTab.removeRow(rowIndex);
							    				orgPartyLst.remove(rowIndex);
												orgRole2Lst.remove(rowIndex);
												orgEmailLst.remove(rowIndex);
							    		}
							    });	
			    		}
			    });
				return plusButton;
		}
		
		
		/// allow the program to create new text boxes based on the reading of an xml file
		private void addOrgRead(final String partyName, final String partyEmail, final String partyRole) {
				VerticalPanel verticalPanel01 = new VerticalPanel();
				HorizontalPanel horizontalPanel01 = new HorizontalPanel();
				HorizontalPanel horizontalPanel02 = new HorizontalPanel();
				HorizontalPanel horizontalPanel03 = new HorizontalPanel();
				HorizontalPanel horizontalPanel04 = new HorizontalPanel();
				Label orgPartyLab = new Label("Responsible party:");
				Label orgEmailLab = new Label("Responsible party e-mail:");
				Label orgRoleLab = new Label("Responsible party role:");
				TextBox orgPartyBox = new TextBox();
				TextBox orgEmailBox = new TextBox();
				ListBox orgRoleLst = new ListBox();
				orgPartyBox.setText(partyName);
				orgEmailBox.setText(partyEmail);
				FlexTable orgPartyTab = new FlexTable();
				verticalPanel01.add(new HTML("<hr />"));
				verticalPanel01.add(new HTML("<br />"));	
				horizontalPanel01.add(orgPartyBox);
			    horizontalPanel01.add(addInfoButton("roParty"));
			    horizontalPanel02.add(orgEmailBox);
			    horizontalPanel02.add(addInfoButton("roEmail"));
			    horizontalPanel03.add(orgRoleLst);
			    populateListBox(orgRoleLst, roleList, 4);
			    checkList(roleMap, partyRole, orgRoleLst);
			    horizontalPanel03.add(addInfoButton("roRole"));
			    orgPartyTab.setWidget(0, 0, orgPartyLab);
			    orgPartyTab.setWidget(0, 1, horizontalPanel01);
			    orgPartyTab.setWidget(1, 0, orgEmailLab);
			    orgPartyTab.setWidget(1, 1, horizontalPanel02);
			    orgPartyTab.setWidget(2, 0, orgRoleLab);
			    orgPartyTab.setWidget(2, 1, horizontalPanel03);
			    horizontalPanel04.add(orgPartyTab);
			    orgPartyLab.setStyleName("orgTextLabel");
			    orgEmailLab.setStyleName("orgTextLabel");
			    orgRoleLab.setStyleName("orgTextLabel");
			    orgPartyBox.setStyleName("orgTextBox");
			    orgEmailBox.setStyleName("orgTextBox");
			    orgRoleLst.setStyleName("orgTextList");
			    orgPartyLst.add(orgPartyBox);
				orgRole2Lst.add(orgRoleLst);
				orgEmailLst.add(orgEmailBox);
			    Image image = new Image("icons/del_icon_small.png");
			    final PushButton delButton = new PushButton(image);
			    delButton.setPixelSize(25, 25);
			    delButton.setStyleName("infoButton");
			    delButton.getElement().setAttribute("style","margin-top: 45px !important;");
			    horizontalPanel04.add(delButton);
			    verticalPanel01.add(horizontalPanel04);
				int row = orgAddTab.getRowCount();
			    orgAddTab.insertRow(row);
			    orgAddTab.setWidget(row, 0, verticalPanel01);
			    delButton.addClickHandler(new ClickHandler() {
			    		public void onClick(ClickEvent event) {
			    				int rowIndex = orgAddTab.getCellForEvent(event).getRowIndex();
			    				orgAddTab.removeRow(rowIndex);
			    				orgPartyLst.remove(rowIndex);
								orgRole2Lst.remove(rowIndex);
								orgEmailLst.remove(rowIndex);
			    		}
			    });
		}
		
		
		/// add new text boxes in the Metadata on Metadata tab
		private PushButton addMetPlusButton() {
				Image image = new Image("icons/plus_icon_small.png");
			    final PushButton plusButton = new PushButton(image);
			    plusButton.setPixelSize(25, 25);
			    plusButton.setStyleName("infoButton");
			    plusButton.addClickHandler(new ClickHandler() {
			    		public void onClick(ClickEvent event) {
			    				VerticalPanel verticalPanel = new VerticalPanel();
			    				verticalPanel.add(new HTML("<br />"));
			    				verticalPanel.add(new HTML("<hr />"));
			    				verticalPanel.add(new HTML("<br />"));
				    			Label metNameLab = new Label("Name:");
				    			Label metEmailLab = new Label("E-mail:");
				    			TextBox metNameBox = new TextBox();
				    			TextBox metEmailBox = new TextBox();
				    			FlexTable metPartyTab = new FlexTable();
				    			Image image = new Image("icons/del_icon_small.png");
							    final PushButton delButton = new PushButton(image);
							    delButton.setPixelSize(25, 25);
							    delButton.setStyleName("infoButton");
							    delButton.getElement().setAttribute("style","margin-top: 20px !important;");
							    metPartyTab.setWidget(0, 0, metNameLab);
							    metPartyTab.setWidget(0, 1, metNameBox);
							    metPartyTab.setWidget(1, 0, metEmailLab);
							    metPartyTab.setWidget(1, 1, metEmailBox);
							    metNameLst.add(metNameBox);
								metEmailLst.add(metEmailBox);
							    verticalPanel.add(metPartyTab);
							    metNameLab.setStyleName("metTextLabel");
							    metEmailLab.setStyleName("metTextLabel");
							    metNameBox.setStyleName("metTextBox");
							    metEmailBox.setStyleName("metTextBox");
			    				int row = metAddTab.getRowCount();
			    				metAddTab.insertRow(row);
			    				metAddTab.setWidget(row, 0, verticalPanel);
			    				metAddTab.setWidget(row, 1, delButton);
							    delButton.addClickHandler(new ClickHandler() {
							    		public void onClick(ClickEvent event) {
							    				int rowIndex = metAddTab.getCellForEvent(event).getRowIndex();
							    				metAddTab.removeRow(rowIndex);
							    				metNameLst.remove(rowIndex);
							    				metEmailLst.remove(rowIndex);
							    		}
							    });	
			    		}
			    });
				return plusButton;
		}
		
		
		/// allow the program to create new text boxes based on the reading of an xml file
		private void addMetRead(final String metadataName, final String metadataEmail) {
				VerticalPanel verticalPanel = new VerticalPanel();
				verticalPanel.add(new HTML("<br />"));
				verticalPanel.add(new HTML("<hr />"));
				verticalPanel.add(new HTML("<br />"));
				Label metNameLab = new Label("Name:");
				Label metEmailLab = new Label("E-mail:");
				TextBox metNameBox = new TextBox();
				TextBox metEmailBox = new TextBox();
				metNameBox.setText(metadataName);
				metEmailBox.setText(metadataEmail);
				FlexTable metPartyTab = new FlexTable();
				Image image = new Image("icons/del_icon_small.png");
			    final PushButton delButton = new PushButton(image);
			    delButton.setPixelSize(25, 25);
			    delButton.setStyleName("infoButton");
			    delButton.getElement().setAttribute("style","margin-top: 20px !important;");
			    metPartyTab.setWidget(0, 0, metNameLab);
			    metPartyTab.setWidget(0, 1, metNameBox);
			    metPartyTab.setWidget(1, 0, metEmailLab);
			    metPartyTab.setWidget(1, 1, metEmailBox);
			    metNameLst.add(metNameBox);
				metEmailLst.add(metEmailBox);
			    verticalPanel.add(metPartyTab);
			    metNameLab.setStyleName("metTextLabel");
			    metEmailLab.setStyleName("metTextLabel");
			    metNameBox.setStyleName("metTextBox");
			    metEmailBox.setStyleName("metTextBox");
				int row = metAddTab.getRowCount();
				metAddTab.insertRow(row);
				metAddTab.setWidget(row, 0, verticalPanel);
				metAddTab.setWidget(row, 1, delButton);
			    delButton.addClickHandler(new ClickHandler() {
			    		public void onClick(ClickEvent event) {
			    				int rowIndex = metAddTab.getCellForEvent(event).getRowIndex();
			    				metAddTab.removeRow(rowIndex);
			    				metNameLst.remove(rowIndex);
			    				metEmailLst.remove(rowIndex);
			    		}
			    });	
		}
		
		
		/// build and launch an About window with a dedicated text to present EMC
		private void aboutWindow() {
			  	final DialogBox infoDialog = new DialogBox();
				infoDialog.setGlassEnabled(true);
				final VerticalPanel vpanel = new VerticalPanel();
				vpanel.getElement().setAttribute("style", "margin-left: 10px !important; margin-top: 10px !important; margin-right: 10px !important;");
				final HorizontalPanel hpanel = new HorizontalPanel();
				Image image = new Image("icons/about_icon_popup.png");
			    hpanel.add(image);
				final HTML label = new HTML("<p align=justify>This is the EUFAR Metadata Creator " + emcVersion + ". It was developed by EUFAR "
						+ "using Java and Google Web Toolkit. The goal of the EUFAR Metadata Creator is to produce metadata to facilitate data "
						+ "storage and searches for Airborne Scientific Campaigns. XML files generated by this version conform to " + inspireVersion
						+ " of the INSPIRE metadata and XML Standard.</p><p>For any questions, more information, or to submit a bug report, please "
						+ "contact <a href=\"mailto:xxxxxxxxxxxx@xxxxxxx\"><span style=\" text-decoration: underline; color:#0000ff;\">"
						+ "xxxxxxxxxxxxxxxxxxxxx</span></a>.</p><p>The latest version and source code of the EUFAR Metadata Creator can be found at <a "
						+ "href=https://github.com/eufarn7sp/emc-eufar-java><span style=\" text-decoration: underline; color:#0000ff;\">"
						+ "https://github.com/eufarn7sp/emc-eufar-java</a></p>");
				hpanel.add(label);
				vpanel.add(hpanel);
				final Button button = new Button("Ok", new ClickHandler() {			
						@Override
						public void onClick(ClickEvent event) {
								infoDialog.hide();
						}
				});
				vpanel.add(button);
				label.getElement().setAttribute("style", "margin-left: 10px !important;");
				button.getElement().setAttribute("style", "margin-left: 170px !important; font-family: DroidSansFallback !important;"
						+ " font-weight: bold !important; margin-top: 10px !important;");
				infoDialog.add(vpanel);
				infoDialog.setSize( "400px", "200px" );
				infoDialog.setModal(true);
				infoDialog.center();
			    infoDialog.setStyleName("myUploadBox");
			    infoDialog.show();
		}
		
		
		/// clean all fields to allow the creation of a new file
		private void newFile() {
				List<Label> allLabel = $("*", subDockPanel).widgets(Label.class);
			  	for (int i = 0; i < allLabel.size(); i++) {
			  			allLabel.get(i).getElement().setAttribute("style","color: black !important;");
				}
			  	List<TextBox> allTextBox = $("*", subDockPanel).widgets(TextBox.class);
			  	for (int i = 0; i < allTextBox.size(); i++) {
			  			allTextBox.get(i).setText("");
				}
			  	List<TextArea> allTextArea = $("*", subDockPanel).widgets(TextArea.class);
			  	for (int i = 0; i < allTextArea.size(); i++) {
			  			allTextArea.get(i).setText("");
				}
			  	List<CheckBox> allCheckBox = $("*", subDockPanel).widgets(CheckBox.class);
			  	for (int i = 0; i < allCheckBox.size(); i = i + 1) {
			  			allCheckBox.get(i).setValue(false);
				}
			  	List<DateBox> allDateBox = $("*", subDockPanel).widgets(DateBox.class);
			  	for (int i = 0; i < allDateBox.size(); i = i + 1) {
			  			allDateBox.get(i).setValue(new Date());
				}
			  	identTypeLst.setSelectedIndex(0);
			  	identLanguageLst.setSelectedIndex(4);
			  	airAircraftLst.setSelectedIndex(0);
			  	airInformationSet(0);
			  	geoLocationLst.setSelectedIndex(0);
			  	geoLocationSet(0);
			  	geoResolutionLst.setSelectedIndex(0);
			  	geoResolutionSet(0);
			  	orgRoleLst.setSelectedIndex(4);
			  	metLanguageLst.setSelectedIndex(4);
			  	useConditionsAddTab.removeAllRows();
			  	useConditionsAddTab.setWidget(0, 0, useConditionsBox);
			    useConditionsAddTab.setWidget(0, 1, auEmptyImage1);
			    useLimitationsAddTab.removeAllRows();
			  	useLimitationsAddTab.setWidget(0, 0, useLimitationsBox);
			    useLimitationsAddTab.setWidget(0, 1, auEmptyImage2);
			    refPhaseTab.removeAllRows();
			    refPhaseTab.setWidget(0, 0, refPhaseLab);
			    refPhaseTab.setWidget(0, 1, refStartDat);
			    refPhaseTab.setWidget(0, 2, refEndDat);
			    refPhaseTab.setWidget(0, 3, refEmptyImage);
			    orgAddTab.removeAllRows();
			    orgAddTab.setWidget(0, 0, horizontalPanel27);
			    metAddTab.removeAllRows();
			    metAddTab.setWidget(0, 0, metPartyTab);
			    metAddTab.setWidget(0, 1, metEmptyImage);
		}
		
		
		/// save the current work in an xml file (a new or existing one)
		private void saveFile() {
				final DialogBox myUploadDialog = new DialogBox();
				final VerticalPanel verticalPanel01 = new VerticalPanel();
				final VerticalPanel verticalPanel02 = new VerticalPanel();
				final VerticalPanel verticalPanel03 = new VerticalPanel();
				final HorizontalPanel horizontalPanel01 = new HorizontalPanel();
				final HorizontalPanel horizontalPanel02 = new HorizontalPanel();
				final Label label = new Label("Please, enter a name to save the XML file.");
				final TextBox fileName = new TextBox();
				final TextBox fileBox = new TextBox();
				final Image image = new Image("icons/save_icon_popup.png");
				final TextArea xmlCode = new TextArea();
				final FormPanel myForm = new FormPanel();
				myUploadDialog.setGlassEnabled(true);
				verticalPanel01.add(horizontalPanel01);
			    horizontalPanel01.add(image);
			    horizontalPanel01.add(verticalPanel02);
			    verticalPanel02.add(label);
			    verticalPanel02.add(fileName);
			    xmlCode.setText(createXml());
			    xmlCode.setName("xmltree");
				verticalPanel03.add(xmlCode);
			    fileBox.setName("filename");
			    verticalPanel03.add(fileBox);
				/////////////////////////////////////////
				myForm.setAction(GWT.getHostPageBaseURL() + "/download"); // for Tomcat7 Server
				//myForm.setAction("/download"); // for Eclipse Dev Mode
				/////////////////////////////////////////
				myForm.setMethod(FormPanel.METHOD_POST);
				myForm.add(verticalPanel03);
				myForm.setVisible(false);
				verticalPanel02.add(myForm);
			    final Button cancelButton = new Button("Cancel/Close", new ClickHandler () {
						@Override
						public void onClick(ClickEvent event) {
								myUploadDialog.hide();			
						}
				});
				final Button submitButton = new Button("Ok", new ClickHandler() {			
						@Override
						public void onClick(ClickEvent event) {
								myFileName = fileName.getText();
								if (!myFileName.toLowerCase().endsWith(".xml")) {
									    myFileName = myFileName + ".xml";
								};
								fileBox.setText(myFileName);
								myForm.submit();	
							}
				});
			    horizontalPanel02.add(submitButton);
			    horizontalPanel02.add(cancelButton);
			    verticalPanel02.add(horizontalPanel02);
			    verticalPanel01.setStyleName("saveVerticalPanel");
			    label.setStyleName("saveTextLabel");
			    fileName.setStyleName("saveTextBox");
				submitButton.getElement().setAttribute("style", "margin-left:19px !important; font-family: DroidSansFallback "
			    		+ "!important; font-weight: bold !important;");
			    cancelButton.getElement().setAttribute("style", "margin-left:65px !important; font-family: DroidSansFallback "
			    		+ "!important; font-weight: bold !important;");
			    myUploadDialog.add(verticalPanel01);	
				myUploadDialog.setSize( "350px", "170px" );
				myUploadDialog.setModal(true);
				myUploadDialog.center();
				myUploadDialog.setStyleName("myUploadBox");	
		}
		
		
		/// create the string containing all xml code
		private String createXml() {
				String xmlString = null;
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
				Element identInfo = add_element(doc, "gmd:identificationInfo", rootElement);
					Element identInfoMD = add_element(doc, "gmd:MD_DataIdentification", identInfo);
						Element identCitation = add_element(doc, "gmd:citation", identInfoMD);
							Element identCitationCI = add_element(doc, "gmd:CI_Citation", identCitation);
								Element identTitle = add_element(doc, "gmd:title", identCitationCI);
									add_element(doc, "gco:CharacterString", identTitle, identTitleBox.getText());
								Element identIdentifier = add_element(doc, "gmd:identifier", identCitationCI);
									Element identIdentifierRS = add_element(doc, "gmd:RS_Identifier", identIdentifier);
										Element identCode = add_element(doc, "gmd:code", identIdentifierRS);
											add_element(doc, "gco:CharacterString", identCode, identIdentifierBox.getText());
								Element identPublication = add_element(doc, "gmd:date", identCitationCI);
									Element identPublicationCI = add_element(doc, "gmd:CI_Date", identPublication);
										Element identPubDate = add_element(doc, "gmd:date", identPublicationCI);
											add_element(doc, "gco:Date", identPubDate, DateTimeFormat.getFormat("yyyy-MM-dd").
													format(refPublicationDat.getValue()));
										Element identPubType = add_element(doc, "gmd:dateType", identPublicationCI);
											Element identPubTypeCI = add_element(doc, "gmd:CI_DateTypeCode", identPubType, "publication");
											identPubTypeCI.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailableStandards/"
													+ "ISO_19139_Schemas/resources/codelist/ML_gmxCodelists.xml#CI_DateTypeCode");
											identPubTypeCI.setAttribute("codeListValue","publication");
								Element identRevision = add_element(doc, "gmd:date", identCitationCI);
									Element identRevisionCI = add_element(doc, "gmd:CI_Date", identRevision);
										Element identRevDate = add_element(doc, "gmd:date", identRevisionCI);
											add_element(doc, "gco:Date", identRevDate, DateTimeFormat.getFormat("yyyy-MM-dd").
													format(refRevisionDat.getValue()));
										Element identRevType = add_element(doc, "gmd:dateType", identRevisionCI);
											Element identRevTypeCI = add_element(doc, "gmd:CI_DateTypeCode", identRevType, "revision");
											identRevTypeCI.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailableStandards/"
													+ "ISO_19139_Schemas/resources/codelist/ML_gmxCodelists.xml#CI_DateTypeCode");
											identRevTypeCI.setAttribute("codeListValue","revision");
								Element identCreation = add_element(doc, "gmd:date", identCitationCI);
									Element identCreationCI = add_element(doc, "gmd:CI_Date", identCreation);
										Element identCreaDate = add_element(doc, "gmd:date", identCreationCI);
											add_element(doc, "gco:Date", identCreaDate, DateTimeFormat.getFormat("yyyy-MM-dd").
													format(refCreationDat.getValue()));
										Element identCreaType = add_element(doc, "gmd:dateType", identCreationCI);
											Element identCreaTypeCI = add_element(doc, "gmd:CI_DateTypeCode", identCreaType, "creation");
											identCreaTypeCI.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailableStandards/"
													+ "ISO_19139_Schemas/resources/codelist/ML_gmxCodelists.xml#CI_DateTypeCode");
											identCreaTypeCI.setAttribute("codeListValue","creation");			
				
				
						////////////////
						/// Abstract ///
						////////////////
						Element identAbstract = add_element(doc, "gmd:abstract", identInfoMD);
							add_element(doc, "gco:CharacterString", identAbstract, identAbstractAre.getText());
				
				
						//////////////
						/// Topics ///
						//////////////
						List<CheckBox> allCheckBox = $("*", clScroll).widgets(CheckBox.class);
					  	for (int i = 0; i < allCheckBox.size(); i = i + 2) {
					  			if (allCheckBox.get(i).getValue()) {
					  					String stringCode = categoriesMap.get(allCheckBox.get(i).getText());
					  					Element identTopic = add_element(doc, "gmd:topicCategory", identInfoMD);
					  					add_element(doc, "gmd:MD_TopicCategoryCode", identTopic, stringCode);
					  			}
						}

				
			  			////////////////
						/// Keywords ///
						////////////////
					  	Element identDescripKeyword = add_element(doc, "gmd:descriptiveKeywords", identInfoMD);
					  		Element identDescripKeywordMD = add_element(doc, "gmd:MD_Keywords", identDescripKeyword);
						  		allCheckBox = $("*", kwScroll).widgets(CheckBox.class);
							  	for (int i = 0; i < allCheckBox.size(); i = i + 2) {
							  			if (allCheckBox.get(i).getValue()) {
							  					String stringCode = keywordsMap.get(allCheckBox.get(i).getText());
							  					Element identKeyword = add_element(doc, "gmd:keyword", identDescripKeywordMD);
							  					add_element(doc, "gco:CharacterString", identKeyword, stringCode);
							  			}
								}
							  	Element identThesaurusName = add_element(doc, "gmd:thesaurusName", identDescripKeywordMD);
							  		Element identThesaurusCitation = add_element(doc, "gmd:CI_Citation", identThesaurusName);
							  			Element identThesaurusTitle = add_element(doc, "gmd:title", identThesaurusCitation);
							  				add_element(doc, "gco:CharacterString", identThesaurusTitle, "NASA/Global Change Master Directory "
							  						+ "(GCMD) Earth Science Keywords. Version 8.0.0.0.0");
							  			Element identThesaurusDate01 = add_element(doc, "gmd:date", identThesaurusCitation);
							  				Element identThesaurusDateCI = add_element(doc, "gmd:CI_Date", identThesaurusDate01);
							  					Element identThesaurusDate02 = add_element(doc, "gmd:date", identThesaurusDateCI);
							  						add_element(doc, "gco:Date", identThesaurusDate02, "2015-02-20");
							  					Element identThesaurusDateType = add_element(doc, "gmd:dateType", identThesaurusDateCI);
							  						Element identThesaurusDateTypeCI = add_element(doc, "gmd:CI_DateTypeCode", identThesaurusDateType, 
							  								"revision");
							  						identThesaurusDateTypeCI.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailable"
							  								+ "Standards/ISO_19139_Schemas/resources/codelist/ML_gmxCodelists.xml#CI_DateTypeCode");
							  						identThesaurusDateTypeCI.setAttribute("codeListValue","revision");		
					  	
							  						
						///////////////////////
						/// Location Extent ///
						///////////////////////
					    Element identExtent = add_element(doc, "gmd:extent", identInfoMD);
						    Element identExtentEX = add_element(doc, "gmd:EX_Extent", identExtent);
							    Element identExtDescription = add_element(doc, "gmd:description", identExtentEX);
							    	if (geoDetailLst.getSelectedItemText() != null) {
							    		add_element(doc, "gco:CharacterString", identExtDescription, geoDetailLst.getSelectedItemText());
							    	} else {
							    		add_element(doc, "gco:CharacterString", identExtDescription, "");
							    	}
							    Element identExtElement = add_element(doc, "gmd:geographicElement", identExtentEX);
								    Element identExtBox = add_element(doc, "gmd:EX_GeographicBoundingBox", identExtElement);
									    Element identExtWest = add_element(doc, "gmd:westBoundLongitude", identExtBox);
									    	add_element(doc, "gco:Decimal", identExtWest, geoWestBox.getText());
									    Element identExtEast = add_element(doc, "gmd:eastBoundLongitude", identExtBox);
									    	add_element(doc, "gco:Decimal", identExtEast, geoEastBox.getText());
									    Element identExtNorth = add_element(doc, "gmd:northBoundLatitude", identExtBox);
									    	add_element(doc, "gco:Decimal", identExtNorth, geoNorthBox.getText());
									    Element identExtSouth = add_element(doc, "gmd:southBoundLatitude", identExtBox);
									    	add_element(doc, "gco:Decimal", identExtSouth, geoSouthBox.getText());
							  						
						
						///////////////////////
						/// Temporal Extent ///
						///////////////////////
							    for (int i = 0; i < refEndLst.size(); i++) {
							    	Element identExtTemporal = add_element(doc, "gmd:temporalElement", identExtentEX);
										Element identExtTemporalEX = add_element(doc, "gmd:EX_TemporalExtent", identExtTemporal);
									    	Element identExtTempExtent = add_element(doc, "gmd:extent", identExtTemporalEX);
									    		Element identExtTempPeriod = add_element(doc, "gml:TimePeriod", identExtTempExtent);
									    		identExtTempPeriod.setAttribute("gml:id","extent" + Integer.toString(i)); 
									    			add_element(doc, "gml:beginPosition", identExtTempPeriod, DateTimeFormat.getFormat(
									    				"yyyy-MM-dd").format(refStartLst.get(i).getValue()));
									    			add_element(doc, "gml:endPosition", identExtTempPeriod, DateTimeFormat.getFormat(
									    				"yyyy-MM-dd").format(refEndLst.get(i).getValue()));
							    }
							    
							    
						//////////////////////////
						/// Spatial Resolution ///
						//////////////////////////
					    Element identResolution = add_element(doc, "gmd:spatialResolution", identInfoMD);
					    	Element identResolutionMD = add_element(doc, "gmd:MD_Resolution", identResolution);
						    if (geoResolutionLst.getSelectedItemText() == "Distance") {
						    	Element IdentDistance1 = add_element(doc, "gmd:distance", identResolutionMD);
						    		Element IdentDistance2 = add_element(doc, "gco:Distance", IdentDistance1, geoResolutionBox.getText());
						    		IdentDistance2.setAttribute("uom",geoUnitBox.getText());
						    }
						    else if (geoResolutionLst.getSelectedItemText() == "Scale") {
						    	Element identScale = add_element(doc, "gmd:equivalentScale", identResolutionMD);
						    		Element identScaleFraction = add_element(doc, "gmd:MD_RepresentativeFraction", identScale);
						    			Element identScaleDenominator = add_element(doc, "gmd:denominator", identScaleFraction);
						    			add_element(doc, "gco:Integer", identScaleDenominator, geoResolutionBox.getText());
						    }

						    
						////////////////
						/// Language ///
						////////////////
						String languageCode = languageMap.get(identLanguageLst.getSelectedItemText());
						Element identLanguage = add_element(doc, "gmd:language", identInfoMD);
							Element identLanguageCode = add_element(doc, "gmd:LanguageCode", identLanguage, languageCode);
							identLanguageCode.setAttribute("codeList", "http://www.loc.gov/standards/iso639-2/");
							identLanguageCode.setAttribute("codeListValue", languageCode);
						    
						    
						////////////////////////////
						/// Resource Constraints ///
						////////////////////////////
					    Element identConstraint = add_element(doc, "gmd:resourceConstraints", identInfoMD);
					    	Element identConstraintMD = add_element(doc, "gmd:MD_Constraints", identConstraint);
						    for (int i = 0; i < useConditionsLst.size(); i++) {
						    		if (i > 0) {
							    			if (useConditionsLst.get(i).getText() != "") {
								    				Element identConditionsUse = add_element(doc, "gmd:useLimitation", identConstraintMD);
									    				add_element(doc, "gco:CharacterString", identConditionsUse, useConditionsLst.get(i).
									    						getText());
							    			}
						    		} else {
						    				Element identConditionsUse = add_element(doc, "gmd:useLimitation", identConstraintMD);
						    					add_element(doc, "gco:CharacterString", identConditionsUse, useConditionsLst.get(i).getText());
						    		}
						    }
					    Element identLegalConstraint = add_element(doc, "gmd:resourceConstraints", identInfoMD);
					    	Element identLegalConstraintMD = add_element(doc, "gmd:MD_LegalConstraints", identLegalConstraint);
					    		Element identLegalAccess = add_element(doc, "gmd:accessConstraints", identLegalConstraintMD);
					    			Element identLegalAccessMD = add_element(doc, "gmd:MD_RestrictionCode", identLegalAccess, "otherRestrictions");
					    			identLegalAccessMD.setAttribute("codeList","http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_"
					    					+ "19139_Schemas/resources/Codelist/gmxCodelists.xml#MD_RestrictionCode");
					    			identLegalAccessMD.setAttribute("codeListValue","otherRestrictions");
					    
									for (int i = 0; i < useLimitationsLst.size(); i++) {
											if (i > 0) {
													if (useLimitationsLst.get(i).getText() != "") {
															Element identLimitationsUse = add_element(doc, "gmd:otherConstraints", 
																	identLegalConstraintMD);
											    			add_element(doc, "gco:CharacterString", identLimitationsUse, useLimitationsLst.
											    					get(i).getText());
													}
											} else {
										    		Element identLimitationsUse = add_element(doc, "gmd:otherConstraints", identLegalConstraintMD);
										    			add_element(doc, "gco:CharacterString", identLimitationsUse, useLimitationsLst.get(i).
										    					getText());
											}
									}
						

					    /////////////////////////
						/// Resource Contacts ///
						/////////////////////////
						for (int i = 0; i < orgPartyLst.size(); i++) {
								if (i > 0) {
										if (orgPartyLst.get(i).getText() !="" || orgEmailLst.get(i).getText() !="") {
												String roleCode = roleMap.get(orgRole2Lst.get(i).getSelectedItemText());
											    Element identContact = add_element(doc, "gmd:pointOfContact", identInfoMD);
											    	Element identContactCI = add_element(doc, "gmd:CI_ResponsibleParty", identContact);
											    		Element identName = add_element(doc, "gmd:organisationName", identContactCI);
											    			add_element(doc, "gco:CharacterString", identName, orgPartyLst.get(i).getText());
											    		Element identContactInfo = add_element(doc, "gmd:contactInfo", identContactCI);
											    			Element identContactInfoCI = add_element(doc, "gmd:CI_Contact", identContactInfo);
											    				Element identContactAdress = add_element(doc, "gmd:address", identContactInfoCI);
											    					Element identContactAdressCI = add_element(doc, "gmd:CI_Address", identContactAdress);
											    						Element identContactEmail = add_element(doc, "gmd:electronicMailAddress", identContactAdressCI);
											    							add_element(doc, "gco:CharacterString", identContactEmail, orgEmailLst.get(i).getText());
											    		Element identContactRole = add_element(doc, "gmd:role",identContactCI);
											    			Element identContactRoleCode = add_element(doc, "gmd:CI_RoleCode", identContactRole, roleCode);
											    			identContactRoleCode.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailableStandards/"
											    					+ "ISO_19139_Schemas/resources/Codelist/gmxCodelists.xml#CI_RoleCode");
											    			identContactRoleCode.setAttribute("codeListValue", roleCode);
										}
								} else {
										String roleCode = roleMap.get(orgRole2Lst.get(i).getSelectedItemText());
									    Element identContact = add_element(doc, "gmd:pointOfContact", identInfoMD);
									    	Element identContactCI = add_element(doc, "gmd:CI_ResponsibleParty", identContact);
									    		Element identName = add_element(doc, "gmd:organisationName", identContactCI);
									    			add_element(doc, "gco:CharacterString", identName, orgPartyLst.get(i).getText());
									    		Element identContactInfo = add_element(doc, "gmd:contactInfo", identContactCI);
									    			Element identContactInfoCI = add_element(doc, "gmd:CI_Contact", identContactInfo);
									    				Element identContactAdress = add_element(doc, "gmd:address", identContactInfoCI);
									    					Element identContactAdressCI = add_element(doc, "gmd:CI_Address", identContactAdress);
									    						Element identContactEmail = add_element(doc, "gmd:electronicMailAddress", identContactAdressCI);
									    							add_element(doc, "gco:CharacterString", identContactEmail, orgEmailLst.get(i).getText());
									    		Element identContactRole = add_element(doc, "gmd:role",identContactCI);
									    			Element identContactRoleCode = add_element(doc, "gmd:CI_RoleCode", identContactRole, roleCode);
									    			identContactRoleCode.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailableStandards/"
									    					+ "ISO_19139_Schemas/resources/Codelist/gmxCodelists.xml#CI_RoleCode");
									    			identContactRoleCode.setAttribute("codeListValue", roleCode);
								}
						}
					    

				///////////////////////
			    /// Hierarchy Level ///
			    ///////////////////////
			    Element hierarchyLevel = add_element(doc, "gmd:hierarchyLevel", rootElement);
			    	Element scopeLevelMD = add_element(doc, "gmd:MD_ScopeCode", hierarchyLevel, identTypeLst.getSelectedItemText().toLowerCase());
			    	scopeLevelMD.setAttribute("codeList","http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/resources/"
			    			+ "Codelist/gmxCodelists.xml#MD_ScopeCode");
			    	scopeLevelMD.setAttribute("codeListValue", identTypeLst.getSelectedItemText().toLowerCase());
				
			    
				/////////////////////////
			    /// Distribution Info ///
			    /////////////////////////
			    Element distributionInfo = add_element(doc, "gmd:distributionInfo", rootElement);
			    	Element distributionInfoMD = add_element(doc, "gmd:MD_Distribution", distributionInfo);
			    		Element transferInfo = add_element(doc, "gmd:transferOptions", distributionInfoMD);
			    			Element transferInfoMD = add_element(doc, "gmd:MD_DigitalTransferOptions", transferInfo);
			    				Element onlineInfo = add_element(doc, "gmd:onLine", transferInfoMD);
			    					Element onlineInfoCI = add_element(doc, "gmd:CI_OnlineResource", onlineInfo);
			    						Element linkageInfo = add_element(doc, "gmd:linkage", onlineInfoCI);
			    						add_element(doc, "gmd:URL", linkageInfo, identLocatorBox.getText());
				

				/////////////////////
			    /// Language Info ///
			    /////////////////////
				String languageCode2 = languageMap.get(metLanguageLst.getSelectedItemText());
			    Element metaLanguage = add_element(doc, "gmd:language", rootElement);
			    	Element metaLanguageCode = add_element(doc, "gmd:LanguageCode", metaLanguage, languageCode2);
			    	metaLanguageCode.setAttribute("codeList","http://www.loc.gov/standards/iso639-2/");
			    	metaLanguageCode.setAttribute("codeListValue", languageCode2);

				
				////////////////////
			    /// Data Quality ///
			    ////////////////////
			    Element dataQualityInfo1 = add_element(doc, "gmd:dataQualityInfo", rootElement);
			    	Element dataQualityDQ = add_element(doc, "gmd:DQ_DataQuality", dataQualityInfo1);
			    		Element lineageQuality = add_element(doc, "gmd:lineage", dataQualityDQ);
			    			Element lineageQualityLI = add_element(doc, "gmd:LI_Lineage", lineageQuality);
			    				Element statementQuality1 = add_element(doc, "gmd:statement", lineageQualityLI);
			    					add_element(doc, "gco:CharacterString", statementQuality1, "validated");
			    		Element reportQuality = add_element(doc, "gmd:report", dataQualityDQ);
			    			Element domainConsistencyDQ = add_element(doc, "gmd:DQ_DomainConsistency", reportQuality);
			    				Element resultQuality = add_element(doc, "gmd:result", domainConsistencyDQ);
			    					Element conformanceResultDQ = add_element(doc, "gmd:DQ_ConformanceResult", resultQuality);
			    						Element specificationQuality = add_element(doc, "gmd:specification", conformanceResultDQ);
			    							Element citationQualityCI = add_element(doc, "gmd:CI_Citation", specificationQuality);
			    								Element titleQuality = add_element(doc, "gmd:title", citationQualityCI);
			    									add_element(doc, "gco:CharacterString", titleQuality, "COMMISSION REGULATION (EC) No 1205/"
			    											+ "2008 of 3 December 2008 implementing Directive 2007/2/EC of the European Parliam"
			    											+ "ent and of the Council as regards metadata");
			    								Element dateQuality = add_element(doc, "gmd:date", citationQualityCI);
			    									Element dateQualityCI = add_element(doc, "gmd:CI_Date", dateQuality);
			    										Element dateQuality2 = add_element(doc, "gmd:date", dateQualityCI);
			    											add_element(doc, "gco:Date", dateQuality2, "2008-12-04");
			    										Element dateType = add_element(doc, "gmd:dateType", dateQualityCI);
			    											Element dateTypeCode = add_element(doc, "gmd:CI_DateTypeCode", dateType, "publication");
			    											dateTypeCode.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailab"
			    													+ "leStandards/ISO_19139_Schemas/resources/codelist/ML_gmxCodelists.xml#CI_"
			    													+ "DateTypeCode");	
			    											dateTypeCode.setAttribute("codeListValue", "publication");
			    						Element passQuality = add_element(doc, "gmd:pass", conformanceResultDQ);
			    							add_element(doc, "gco:Boolean", passQuality, "True");
				
			    							
			    ////////////////////////////////
			    /// Aircraft and Instruments ///
			    ////////////////////////////////
			    Element acquisitionInfo = add_element(doc, "gmd:acquisitionInfo", rootElement);
			    	Element aircraftInfo = add_element(doc, "gmd:platformInfo", acquisitionInfo);
			    		Element aircraftInfoAI = add_element(doc, "gmd:PI_PlatformInfo", aircraftInfo);
			    			Element aircraftManufacturer = add_element(doc, "gmd:PlatformManufacturer", aircraftInfoAI);
			    				add_element(doc, "gco:CharacterString", aircraftManufacturer, airManufacturerInfo.getText());
			    			Element aircraftType = add_element(doc, "gmd:platformType", aircraftInfoAI);
			    				add_element(doc, "gco:CharacterString", aircraftType, airTypeInfo.getText());
			    			Element aircraftOperator = add_element(doc, "gmd:platformOperator", aircraftInfoAI);
			    				add_element(doc, "gco:CharacterString", aircraftOperator, operatorMap.get(airOperatorInfo.getText()));
			    			Element aircraftRegistration = add_element(doc, "gmd:platformRegistration", aircraftInfoAI);
			    				add_element(doc, "gco:CharacterString", aircraftRegistration, airRegistrationInfo.getText());
				
				
				////////////////////
			    /// Contact Info ///
			    ////////////////////
				for (int i = 0; i < metNameLst.size(); i++) {
						
						identTitleBox.setText(Integer.toString(i));
					
						if (i > 0) {
								if (metNameLst.get(i).getText() != "" || metEmailLst.get(i).getText() != "") {
									    Element xmlfileContact = add_element(doc, "gmd:contact", rootElement);
									    	Element responsiblePartyInfoCI = add_element(doc, "gmd:CI_ResponsibleParty", xmlfileContact);
									    		Element nameContact = add_element(doc, "gmd:organisationName", responsiblePartyInfoCI);
									    			add_element(doc, "gco:CharacterString", nameContact, metNameLst.get(i).getText());
									    		Element infoContact = add_element(doc, "gmd:contactInfo", responsiblePartyInfoCI);
									    			Element infoContactCI = add_element(doc, "gmd:CI_Contact", infoContact);
									    				Element addressContact = add_element(doc, "gmd:address", infoContactCI);
									    					Element addressContactCI = add_element(doc, "gmd:CI_Address", addressContact);
									    						Element emailContact = add_element(doc, "gmd:electronicMailAddress", addressContactCI);
									    							add_element(doc, "gco:CharacterString", emailContact, metEmailLst.get(i).getText());
									    		Element roleContact = add_element(doc, "gmd:role", responsiblePartyInfoCI);
									    			Element roleCodeContact = add_element(doc, "gmd:CI_RoleCode", roleContact, "pointOfContact");
									    			roleCodeContact.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_"
									    					+ "Schemas/resources/Codelist/gmxCodelists.xml#CI_RoleCode");
									    			roleCodeContact.setAttribute("codeListValue", "pointOfContact");
								}
						} else {
								Element xmlfileContact = add_element(doc, "gmd:contact", rootElement);
						    	Element responsiblePartyInfoCI = add_element(doc, "gmd:CI_ResponsibleParty", xmlfileContact);
						    		Element nameContact = add_element(doc, "gmd:organisationName", responsiblePartyInfoCI);
						    			add_element(doc, "gco:CharacterString", nameContact, metNameLst.get(i).getText());
						    		Element infoContact = add_element(doc, "gmd:contactInfo", responsiblePartyInfoCI);
						    			Element infoContactCI = add_element(doc, "gmd:CI_Contact", infoContact);
						    				Element addressContact = add_element(doc, "gmd:address", infoContactCI);
						    					Element addressContactCI = add_element(doc, "gmd:CI_Address", addressContact);
						    						Element emailContact = add_element(doc, "gmd:electronicMailAddress", addressContactCI);
						    							add_element(doc, "gco:CharacterString", emailContact, metEmailLst.get(i).getText());
						    		Element roleContact = add_element(doc, "gmd:role", responsiblePartyInfoCI);
						    			Element roleCodeContact = add_element(doc, "gmd:CI_RoleCode", roleContact, "pointOfContact");
						    			roleCodeContact.setAttribute("codeList", "http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_"
						    					+ "Schemas/resources/Codelist/gmxCodelists.xml#CI_RoleCode");
						    			roleCodeContact.setAttribute("codeListValue", "pointOfContact");
						}
				}
				
				
				/////////////////////
			    /// Metadata Date ///
			    /////////////////////
			    Element dateStamp = add_element(doc, "gmd:dateStamp", rootElement);
			    	add_element(doc, "gco:Date", dateStamp, DateTimeFormat.getFormat("yyyy-MM-dd").format(metDateDat.getValue()));
				

				doc.appendChild(rootElement);
				xmlString = "<?xml version='1.0' encoding='UTF-8'?>" + doc.toString();
				return xmlString;
		}
		
		
		/// open a file containing xml code
		private void openFile() {
				final DialogBox myUploadDialog = new DialogBox();
				final VerticalPanel verticalPanel01 = new VerticalPanel();
				final VerticalPanel verticalPanel02 = new VerticalPanel();
				final HorizontalPanel horizontalPanel01 = new HorizontalPanel();
				final HorizontalPanel horizontalPanel02 = new HorizontalPanel();
				final Label label = new Label("Please, select the file you want to modify");
				final FileUpload myFileUpload = new FileUpload();
				final FormPanel myUploadForm = new FormPanel();
				final Image image = new Image("icons/open_icon_popup.png");
				myUploadDialog.setGlassEnabled(true);
				myUploadDialog.add(verticalPanel01);
				verticalPanel01.add(horizontalPanel01);
				horizontalPanel01.add(image);
				horizontalPanel01.add(verticalPanel02);
				verticalPanel02.add(label);
				myFileUpload.setName("uploadFormElement");
				myFileUpload.getElement().setId("uploadFormElement");
				myFileUpload.getElement().setId("myFile");
				verticalPanel02.add(myFileUpload);
				///////////////////////////////////////////////
				myUploadForm.setAction(GWT.getHostPageBaseURL() + "/upload"); // for Tomcat7 Server
				//myUploadForm.setAction("/upload"); // for Eclipse Dev Mode
				///////////////////////////////////////////////
				myUploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
				myUploadForm.setMethod(FormPanel.METHOD_POST);
				myUploadForm.addSubmitHandler(new FormPanel.SubmitHandler() {
						@Override
						public void onSubmit(SubmitEvent event) {
							  	if (myFileUpload.getFilename().length() == 0) {
									  	Window.alert("Please, select a file.");
								        event.cancel();
							  	}		 
							  	else {
								  		newFile();
								  		myFileName = myFileUpload.getFilename();
							  	}        
						}
				});
				myUploadForm.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
						@Override
						public void onSubmitComplete(SubmitCompleteEvent event) {
								myUploadDialog.hide();
								String ret = event.getResults();
								ret = ret.replaceAll("\t", "").replace("\n", "");
								readXml(ret);
						}			
				});		
				final Button submitButton = new Button("Ok", new ClickHandler() {			
						@Override
						public void onClick(ClickEvent event) {
								myUploadForm.submit();
						}
				});
				final Button cancelButton = new Button("Cancel", new ClickHandler() {			
						@Override
						public void onClick(ClickEvent event) {
							myUploadDialog.hide();			
						}
				});
				myUploadForm.add(verticalPanel01);
				horizontalPanel02 .add(submitButton);
				horizontalPanel02 .add(cancelButton);
			    verticalPanel02.add(horizontalPanel02);
				myUploadDialog.add(myUploadForm);
				myUploadDialog.setSize( "350px", "170px" );
				myUploadDialog.setModal(true);
				myUploadDialog.center();
				myUploadDialog.setStyleName("myUploadBox");
				verticalPanel01.setStyleName("saveVerticalPanel");
				myFileUpload.setStyleName("openTextBox");
				submitButton.getElement().setAttribute("style", "margin-left:19px !important; font-family: DroidSansFallback "
			    		+ "!important; font-weight: bold !important;");
			    cancelButton.getElement().setAttribute("style", "margin-left:65px !important; font-family: DroidSansFallback "
			    		+ "!important; font-weight: bold !important;");
				label.setStyleName("saveTextLabel");
		}
		
		
		/// read the result of the servlet output to populate all fields from the xml code
		private void readXml(String stringXml) {
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
																		try {
																				identTitleBox.setText(citation.item(cc).getFirstChild().
																						getFirstChild().getNodeValue());
																		}
																		catch (Exception ex) {}
																}
																if (citation.item(cc).getNodeName().startsWith("gmd:identifier")) {
																		try {
																				identIdentifierBox.setText(citation.item(cc).getFirstChild().
																						getFirstChild().getFirstChild().getFirstChild().getNodeValue());
																		}
																		catch (Exception ex) {}
																}
																if (citation.item(cc).getNodeName().startsWith("gmd:date")) {
																		Node dateType = citation.item(cc).getFirstChild().getLastChild().
																				getFirstChild();
																		String dateValue = citation.item(cc).getFirstChild().getFirstChild().
																				getFirstChild().getFirstChild().getNodeValue();
																		String dateTypeCode = ((Element)dateType).getAttribute("codelistvalue");
																		
																		if (dateTypeCode == "revision") {
																				refRevisionDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").
																						parse(dateValue));
																		}
																		else if (dateTypeCode == "creation") {
																				refCreationDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").
																						parse(dateValue));
																		}
																		else if (dateTypeCode =="publication") {
																				refPublicationDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").
																						parse(dateValue));
																		}
																}
														}
												}
												if (dataIdentification.item(bb).getNodeName().startsWith("gmd:abstract")) {
														try {
																identAbstractAre.setText(dataIdentification.item(bb).getFirstChild().
																		getFirstChild().getNodeValue());
														}
														catch (Exception ex) {}
												}
												if (dataIdentification.item(bb).getNodeName().startsWith("gmd:language")) {
														checkList(languageMap, dataIdentification.item(bb).getFirstChild().getFirstChild().
																getNodeValue(), identLanguageLst);
												}
												if (dataIdentification.item(bb).getNodeName().startsWith("gmd:topiccategory")) {
														checkBox(clScroll, categoriesMap, dataIdentification.item(bb).getFirstChild().
																getFirstChild().getNodeValue());
												
												}
												if (dataIdentification.item(bb).getNodeName().startsWith("gmd:descriptivekeywords")) {
														NodeList keywords = dataIdentification.item(bb).getChildNodes().item(0).getChildNodes();
														for (int dd = 0; dd < keywords.getLength(); dd++) {
																if (keywords.item(dd).getNodeName().startsWith("gmd:keyword")) {
																		checkBox(kwScroll, keywordsMap, keywords.item(dd).getFirstChild().
																				getFirstChild().getNodeValue());
																}
														}
												}
												if (dataIdentification.item(bb).getNodeName().startsWith("gmd:extent")) {
														NodeList extents = dataIdentification.item(bb).getChildNodes().item(0).getChildNodes();
														for (int ee = 0; ee < extents.getLength(); ee++) {
																if (extents.item(ee).getNodeName().startsWith("gmd:description")) {
																		try {
																				String description = extents.item(ee).getFirstChild().
																						getFirstChild().getNodeValue();
																				for (int i = 0; i < continentList.size(); i++) {
																						if (description == continentList.get(i)) {
																								geoLocationSet(1);
																								geoDetailLst.setSelectedIndex(i);
																								geoLocationLst.setSelectedIndex(1);
																								break;
																						}
																				}
																				for (int i = 0; i < countryList.size(); i++) {
																						if (description == countryList.get(i)) {
																								geoLocationSet(2);
																								geoDetailLst.setSelectedIndex(i);
																								geoLocationLst.setSelectedIndex(2);
																								break;
																						}
																				}
																				for (int i = 0; i < oceanList.size(); i++) {
																						if (description == oceanList.get(i)) {
																								geoLocationSet(3);
																								geoDetailLst.setSelectedIndex(i);
																								geoLocationLst.setSelectedIndex(3);
																								break;
																						}
																				}
																				for (int i = 0; i < regionList.size(); i++) {
																						if (description == regionList.get(i)) {
																								geoLocationSet(4);
																								geoDetailLst.setSelectedIndex(i);
																								geoLocationLst.setSelectedIndex(4);
																								break;
																						}
																				}			
																		}
																		catch (Exception ex) {}
																}
																if (extents.item(ee).getNodeName().startsWith("gmd:geographicelement")) {
																		NodeList geoExtents = extents.item(ee).getChildNodes().item(0).
																				getChildNodes();
																		for (int ff = 0; ff < geoExtents.getLength(); ff++) {
																				if (geoExtents.item(ff).getNodeName().startsWith("gmd:"
																						+ "westboundlongitude")) {
																						try {
																								geoWestBox.setText(geoExtents.item(ff).
																										getFirstChild().getFirstChild().
																										getNodeValue());
																						}
																						catch (Exception ex) {}
																				}
																				if (geoExtents.item(ff).getNodeName().startsWith("gmd:"
																						+ "eastboundlongitude")) {
																						try {
																								geoEastBox.setText(geoExtents.item(ff).
																										getFirstChild().getFirstChild().
																										getNodeValue());
																						}
																						catch (Exception ex) {}
																				}
																				if (geoExtents.item(ff).getNodeName().startsWith("gmd:"
																						+ "northboundlatitude")) {
																						
																						try {
																								geoNorthBox.setText(geoExtents.item(ff).
																										getFirstChild().getFirstChild().
																										getNodeValue());
																						}
																						catch (Exception ex) {}
																				}
																				if (geoExtents.item(ff).getNodeName().startsWith("gmd:"
																						+ "southboundlatitude")) {
																						try {
																								geoSouthBox.setText(geoExtents.item(ff).
																										getFirstChild().getFirstChild().
																										getNodeValue());
																						}
																						catch (Exception ex) {}
																				}
																		}
																}
																if (extents.item(ee).getNodeName().startsWith("gmd:temporalelement")) {
																		periodNumber++;
																		String periodStart = extents.item(ee).getFirstChild().getFirstChild().
																				getFirstChild().getFirstChild().getFirstChild().getNodeValue();
																		String periodEnd = extents.item(ee).getFirstChild().getFirstChild().
																				getFirstChild().getLastChild().getFirstChild().getNodeValue();
																		if (periodNumber == 1) {
																				refStartDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").
																					parse(periodStart));
																				refEndDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").
																						parse(periodEnd));
																		}
																		else {
																				addRefRead(periodStart, periodEnd);
																		}
																}
														}
												}
												if (dataIdentification.item(bb).getNodeName().startsWith("gmd:spatialresolution")) {
														if (dataIdentification.item(bb).getFirstChild().getFirstChild().getNodeName().
																startsWith("gmd:equivalentscale")) {
																try {
																		geoResolutionBox.setText(dataIdentification.item(bb).getFirstChild().
																				getFirstChild().getFirstChild().getFirstChild().getFirstChild().
																				getFirstChild().getNodeValue());
																}
																catch (Exception ex) {}
														}
														else if (dataIdentification.item(bb).getFirstChild().getFirstChild().getNodeName().
																startsWith("gmd:distance")) {
																try {
																		geoResolutionLst.setSelectedIndex(1);
																		geoResolutionSet(1);
																		
																		geoResolutionBox.setText(dataIdentification.item(bb).getFirstChild().
																				getFirstChild().getFirstChild().getFirstChild().getNodeValue());
																	
																		geoUnitBox.setText(((Element)dataIdentification.item(bb).getFirstChild().
																				getFirstChild().getFirstChild()).getAttribute("uom"));
																}
																catch (Exception ex) {}
														}
												}
												if (dataIdentification.item(bb).getNodeName().startsWith("gmd:resourceconstraints")) {
														NodeList useConditions = dataIdentification.item(bb).getFirstChild().getChildNodes();
														for (int gg = 0; gg < useConditions.getLength(); gg++) {
																if (useConditions.item(gg).getNodeName().startsWith("gmd:uselimitation")) {
																		useConditionNumber++;
																		if (useConditionNumber == 1) {
																				try {
																						useConditionsBox.setText(useConditions.item(gg).
																								getFirstChild().getFirstChild().getNodeValue());
																				}
																				catch (Exception ex) {}
																		}
																		else {
																				addUseRead(useConditionsAddTab, useConditionsLst, useConditions.
																						item(gg).getFirstChild().getFirstChild().getNodeValue());
																		}
																}
																if (useConditions.item(gg).getNodeName().startsWith("gmd:otherconstraints")) {
																		accessLimitationNumber++;
																		if (accessLimitationNumber == 1) {
																				try {
																						useLimitationsBox.setText(useConditions.item(gg).
																								getFirstChild().getFirstChild().getNodeValue());
																				}
																				catch (Exception ex) {}
																		}
																		else {
																				addUseRead(useLimitationsAddTab, useLimitationsLst, useConditions.
																						item(gg).getFirstChild().getFirstChild().getNodeValue());
																		}
																}
														}
												}
												if (dataIdentification.item(bb).getNodeName().startsWith("gmd:pointofcontact")) {
														responsibleParty++;
														NodeList responsiblePartyItems = dataIdentification.item(bb).getFirstChild().
																getChildNodes();
														if (responsibleParty == 1) {
																try {
																		orgPartyBox.setText(responsiblePartyItems.item(0).getFirstChild().
																				getFirstChild().getNodeValue());
																		orgEmailBox.setText(responsiblePartyItems.item(1).getFirstChild().
																				getFirstChild().getFirstChild().getFirstChild().getFirstChild().
																				getFirstChild().getNodeValue());
																		checkList(roleMap, responsiblePartyItems.item(2).getFirstChild().
																				getFirstChild().getNodeValue(), orgRoleLst);
																}
																catch (Exception ex) {}
														} else {
																addOrgRead(responsiblePartyItems.item(0).getFirstChild().getFirstChild().
																		getNodeValue(), responsiblePartyItems.item(1).getFirstChild().
																		getFirstChild().getFirstChild().getFirstChild().getFirstChild().
																		getFirstChild().getNodeValue(), responsiblePartyItems.item(2).
																		getFirstChild().getFirstChild().getNodeValue());
														}	
												}	
										}	
								}
								if (docChildNodes.item(aa).getNodeName().startsWith("gmd:hierarchylevel")) {
										checkList(typeMap, docChildNodes.item(aa).getFirstChild().getFirstChild().getNodeValue(), identTypeLst);
								}
								if (docChildNodes.item(aa).getNodeName().startsWith("gmd:distributioninfo")) {
										try {
												identLocatorBox.setText(docChildNodes.item(aa).getFirstChild().getFirstChild().getFirstChild().
														getFirstChild().getFirstChild().getFirstChild().getFirstChild().getFirstChild().getNodeValue());
										}
										catch (Exception ex) {}
								}
								if (docChildNodes.item(aa).getNodeName().startsWith("gmd:language")) {
										checkList(languageMap, docChildNodes.item(aa).getFirstChild().getFirstChild().getNodeValue(), 
												metLanguageLst);
								}
								if (docChildNodes.item(aa).getNodeName().startsWith("gmd:datestamp")) {
										metDateDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(docChildNodes.item(aa).getFirstChild().
												getFirstChild().getNodeValue()));
								}
								if (docChildNodes.item(aa).getNodeName().startsWith("gmd:dataqualityinfo")) {
										/// No statement yet
								}
								if (docChildNodes.item(aa).getNodeName().startsWith("gmd:contact")) {
										metadataContact++;
										if (metadataContact == 1) {
												try {
														NodeList contactNodes = docChildNodes.item(aa).getFirstChild().getChildNodes();
														metNameBox.setText(contactNodes.item(0).getFirstChild().getFirstChild().getNodeValue());
														metEmailBox.setText(contactNodes.item(1).getFirstChild().getFirstChild().getFirstChild().
																getFirstChild().getFirstChild().getFirstChild().getNodeValue());
												}
												catch (Exception ex) {}
										} else {
												NodeList contactNodes = docChildNodes.item(aa).getFirstChild().getChildNodes();
												addMetRead(contactNodes.item(0).getFirstChild().getFirstChild().getNodeValue(),contactNodes.
														item(1).getFirstChild().getFirstChild().getFirstChild().getFirstChild().getFirstChild().
														getFirstChild().getNodeValue());
										}
								}
								if (docChildNodes.item(aa).getNodeName().startsWith("gmd:acquisitioninfo")) {
										String registrationNumber = docChildNodes.item(aa).getFirstChild().getFirstChild().getLastChild().
												getFirstChild().getFirstChild().getNodeValue();
										int index = -1;
										for (int i = 0 ; i < airAircraftInfo.length ; i++) {
											    if (airAircraftInfo[i][5].equals(registrationNumber)) {
												        index = i;
												        break;
											    }
										}
										airInformationSet(index);
										airAircraftLst.setSelectedIndex(index);	
								}	
						}	
				} catch (DOMException e) {
						Window.alert("Could not parse XML document.");
				}
		}
		
		
		// add easily an element in an xml file
		private Element add_element(Document doc, String string, Element parent, String value) {
				Element new_child = doc.createElement(string);
				new_child.appendChild(doc.createTextNode(value));
			    parent.appendChild(new_child);
			    return new_child;
		}
		
		
		// add easily an element in an xml file
		private Element add_element(Document doc, String string, Element parent) {
				Element new_child = doc.createElement(string);
			    parent.appendChild(new_child);
			    return new_child;
		}
		
		
		// find the correct text and index in a List Box from an entry in an xml file
		private void checkList(TreeMap<String, String> treeMap, String string, ListBox listBox) {
				String key = new String();
				for (Entry<String, String> entry : treeMap.entrySet()) {
		            	if (entry.getValue().equals(string)) {key = entry.getKey(); break;}
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
		private void checkBox(ScrollPanel scrollPanel, TreeMap<String, String> treeMap, String string) {
			  	List<CheckBox> allCheckBox = $("*", scrollPanel).widgets(CheckBox.class);
				String key = new String();
				for (Entry<String, String> entry : treeMap.entrySet()) {
		            	if (entry.getValue().equals(string)) {key = entry.getKey(); break;}
		        }
				for (int i = 0; i < allCheckBox.size(); i = i + 2) {
						if (allCheckBox.get(i).getText() == key) {allCheckBox.get(i).setValue(true);}
				}
		}
		
		
		/// check if all fields have been filled in before saving it
		private void runCheck() {
				int notCompleted = 0;
				int widgetIndex = -1;
				boolean result = true;
				List<Label> allLabel = $("*", subDockPanel).widgets(Label.class);
			  	for (int i = 0; i < allLabel.size(); i++) {
			  			allLabel.get(i).getElement().setAttribute("style","color: black !important;");
				}
			  	List<TextBoxBase> allBox = $("*", subDockPanel).widgets(TextBoxBase.class);
			  	for (int i = 0; i < allBox.size(); i++) {
			  			allBox.get(i).getElement().setAttribute("style","border-color: #ccc !important;");
				}
			  	for (int i = 0; i < 10; i++) {
			  			stackPanel.getHeaderWidget(i).asWidget().setStylePrimaryName("textcompleted");
			  	}
			  	airAircraftLst.getElement().setAttribute("style","border-color: #ccc !important;");
			  	geoLocationLst.getElement().setAttribute("style","border-color: #ccc !important;");
			  	geoDetailLst.getElement().setAttribute("style","border-color: #ccc !important;");
			  	geoResolutionLst.getElement().setAttribute("style","border-color: #ccc !important;");
			  	geoUnitLab.getElement().setAttribute("style", "margin-left: 20px !important;");
			  	geoWestBox.getElement().setAttribute("style", "margin-left: 40px !important;");
			  	ArrayList<Widget> requiredParents = new ArrayList<Widget>();
			  	Widget parent;
				for (Map.Entry<TextBoxBase, Label> entry : requiredField.entrySet()) {
						Label label = entry.getValue();
						TextBoxBase textBox = entry.getKey();
						parent = label.getParent();
						while (!(parent instanceof ScrollPanel)) {
								parent = parent.getParent();
						}
						if (textBox.getText() == "") {
								if (label.getText() == "Spatial resolution:") {} else {
										notCompleted++;
										label.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
										textBox.getElement().setAttribute("style","border-color: rgb(200,0,0) !important;");
										requiredParents.add(parent);
										widgetIndex = stackPanel.getWidgetIndex(parent);
										stackPanel.getHeaderWidget(widgetIndex).asWidget().setStylePrimaryName("textrequired");
								}
						
						} else {
								if (!runCorrect(textBox)) {
									notCompleted++;
									label.getElement().setAttribute("style","color: rgb(0,0,200) !important;");
									requiredParents.add(parent);
									widgetIndex = stackPanel.getWidgetIndex(parent);
									stackPanel.getHeaderWidget(widgetIndex).asWidget().setStylePrimaryName("textuncorrect");
								}
						}
				}
				List<CheckBox> allClaCheck = $("*", clScroll).widgets(CheckBox.class);
				int allClaCheckNumber = 0;
			  	for (int i = 0; i < allClaCheck.size(); i++) {
			  			if  (allClaCheck.get(i).getValue() == true) {
			  					allClaCheckNumber++;
			  			}
				}
			  	if (allClaCheckNumber == 0) {
			  			notCompleted++;
			  			stackPanel.getHeaderWidget(1).asWidget().setStylePrimaryName("textrequired");
			  	}
			  	List<CheckBox> allKeyCheck = $("*", kwScroll).widgets(CheckBox.class);
				int allKeyCheckNumber = 0;
			  	for (int i = 0; i < allKeyCheck.size(); i++) {
			  			if  (allKeyCheck.get(i).getValue() == true) {
			  					allKeyCheckNumber++;
			  			}
				}
			  	if (allKeyCheckNumber == 0) {
			  			notCompleted++;
		  				stackPanel.getHeaderWidget(2).asWidget().setStylePrimaryName("textrequired");
			  	}
			  	if (airAircraftLst.getSelectedIndex() == 0) {
			  			notCompleted++;
			  			stackPanel.getHeaderWidget(3).asWidget().setStylePrimaryName("textrequired");
			  			airAircraftLab.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
			  			airAircraftLst.getElement().setAttribute("style","border-color: rgb(200,0,0) !important;");
			  	}
				if (geoLocationLst.getSelectedIndex() == 0 || geoDetailLst.getSelectedIndex() == 0 || geoDetailLst.isEnabled() == false) {
						geoLocationLab.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
						if (geoLocationLst.getSelectedIndex() == 0) {
								geoLocationLst.getElement().setAttribute("style","border-color: rgb(200,0,0) !important;");
						}
						if (geoDetailLst.getSelectedIndex() == 0 || geoDetailLst.isEnabled() == false) {
								geoDetailLst.getElement().setAttribute("style","border-color: rgb(200,0,0) !important;");
						}
						notCompleted++;
			  			stackPanel.getHeaderWidget(4).asWidget().setStylePrimaryName("textrequired");
				}
				// exception for spatial resolution which exists for hyperspectral data but not for some in-situ data
				if (geoResolutionLst.getSelectedIndex() == 1) {
						if (geoUnitBox.getText() != "") {
								if (!runCorrect(geoUnitBox)) {
										notCompleted++;
								}
						}
				}
				if (notCompleted > 0) {
						result = false;
				}
				if (!result) {
						final DialogBox infoDialog = new DialogBox();
						infoDialog.setGlassEnabled(true);
						final VerticalPanel vpanel = new VerticalPanel();
						vpanel.getElement().setAttribute("style", "margin-left: 10px !important; margin-top: 10px !important; margin-right: 10px !important;");
						final HorizontalPanel hpanel = new HorizontalPanel();
						Image image = new Image("icons/warning_icon_popup.png");
					    hpanel.add(image);
					    final HorizontalPanel hpanel2 = new HorizontalPanel();
					    final HTML label = new HTML("<p align=\"justify\">All mandatory fields have not been filled in, or have been incorrectly "
					    		+ "filled in. You can save your file if you want to complete/correct it later. All fields which have not been "
					    		+ "completely filled in are indicated in <span style=\" font-weight:600; color:#c80000;\">red</span>, and in "
					    		+ "<span style=\" font-weight:600; color:#0000c8;\">blue</span> for those incorrectly filled in. <span style=\" font-weight:600;\">Do not use an incomplete/incorrect xml file for "
					    		+ "storage and/or sql queries.</span></p>");
						hpanel.add(label);
						vpanel.add(hpanel);
						final Button buttonOk = new Button("Save", new ClickHandler() {			
								@Override
								public void onClick(ClickEvent event) {
										infoDialog.hide();
										saveFile();
								}
						});
						final Button buttonCancel = new Button("Cancel", new ClickHandler() {			
							@Override
							public void onClick(ClickEvent event) {
									infoDialog.hide();
							}
						});
						hpanel2.add(buttonCancel);
						hpanel2.add(buttonOk);
						vpanel.add(hpanel2);
						label.getElement().setAttribute("style", "margin-left: 10px !important;");
						buttonOk.getElement().setAttribute("style", "margin-left: 105px !important; font-family: DroidSansFallback !important;"
								+ " font-weight: bold !important; margin-top: 10px !important;");
						buttonCancel.getElement().setAttribute("style", "margin-left: 120px !important; font-family: DroidSansFallback !important;"
								+ " font-weight: bold !important; margin-top: 10px !important;");
						infoDialog.add(vpanel);
						infoDialog.setSize( "400px", "150px" );
						infoDialog.setModal(true);
						infoDialog.center();
					    infoDialog.setStyleName("myUploadBox");
					    infoDialog.show();
				} else {
					saveFile();
				}
		}
		
		
		/// check if all fields have been correctly filled in before saving it
		private boolean runCorrect(final TextBoxBase textBox) {
				boolean result = true;
				for (Map.Entry<TextBoxBase, String> entry : correctField.entrySet()) {
						String string = entry.getValue();
						TextBoxBase textBoxE = entry.getKey();
						if (textBoxE == textBox) {
								switch (string) {
										case "number":
												try {
										            	Double.parseDouble(textBox.getText());
										        } catch (NumberFormatException e) {
											        	textBox.getElement().setAttribute("style","border-color: blue !important;");
											        	result = false;
										        }
												break;
										case "string":
												try {
											            Double.parseDouble(textBox.getText());
											            textBox.getElement().setAttribute("style","border-color: blue !important;");
											        	result = false;
										        } catch (NumberFormatException e) {}
												break;
										case "email":
											boolean isEmail = false;
											for (char ch: textBox.getText().toCharArray()) {
													if (ch == '@') {
															isEmail = true;
													}
											}
											if (!isEmail) {
													textBox.getElement().setAttribute("style","border-color: blue !important;");
													result = false;
											}
											break;
								}
						}
				}
				return result;
		}
}

