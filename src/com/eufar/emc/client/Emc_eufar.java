package com.eufar.emc.client;

import static com.google.gwt.query.client.GQuery.$;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.eufar.emc.client.ScrollableTabLayoutPanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Navigator;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
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
import com.google.gwt.user.datepicker.client.DateBox;


public class Emc_eufar implements EntryPoint {

	static Logger rootLogger = Logger.getLogger("");
	
	// few items initialization
	public static HashMap<String, String> languageMap = Resources.languageMap();
	public static HashMap<String, String> categoriesMap = Resources.categoriesMap();
	public static HashMap<String, String> keywordsMap = Resources.keywordsMap();
	public static HashMap<String, String> roleMap = Resources.roleMap();
	public static HashMap<String, String> operatorMap = Resources.operatorMap();
	public static HashMap<String, String> typeMap = Resources.typeMap();
	public static HashMap<String, String> instrumentMap = Resources.instrumentMap();
	public static HashMap<String, String> unitMap = Resources.unitMap();
	private HashMap<TextBoxBase, Label> requiredField = Resources.requiredField();
	public static HashMap<TextBoxBase, String> correctField = Resources.correctField();
	public static HashMap<DateBox, Label> correctDate = Resources.correctDate();
	public static HashMap<HorizontalPanel, Label> requiredCheckbox = Resources.requiredCheck();
	private String emcVersion = new String("v1.0.0 (2015-09-28)");
	private String gwtVersion = new String("2.7.0");
	private String eclipseVersion = new String("4.5.0");
	private String javaVersion = new String("1.7.0.79");
	private String inspireVersion = new String("v1.3");
	public static Boolean tabLayout = new Boolean(false);
	public static Boolean isModified = new Boolean(false);
	public static String titleString = new String("EUFAR Metadata Creator");

	public static String emcPath = new String(GWT.getHostPageBaseURL()); // for Tomcat7 Server
	//public static String emcPath = new String(""); // for Eclipse Dev Mode
	

	// Main window items initialization
	public static MenuBar mainMenu = new MenuBar();
	private MenuBar aboutMenu = new MenuBar(true);
	private MenuBar fileMenu = new MenuBar(true);
	private DockLayoutPanel dockPanel = new DockLayoutPanel(Unit.PX);
	public static DockLayoutPanel subDockPanel = new DockLayoutPanel(Unit.PX);
	public static StackLayoutPanel stackPanel = new StackLayoutPanel(Unit.PX);
	public static ScrollableTabLayoutPanel tabPanel = new ScrollableTabLayoutPanel(30, Unit.PX);
	public static String myFileName = new String("");
	private String imageNew = "<img src='icons/new_menu_icon.png'/>";
	private String imageOpen = "<img src='icons/open_menu_icon.png'/>";
	private String imageSave = "<img src='icons/save_menu_icon.png'/>";
	private String imageAbout = "<img src='icons/about_emc_menu_icon.png'/>";
	private String imageStandard = "<img src='icons/inspire_menu_icon.png'/>";
	private String imageEufar = "<img src='icons/eufar_menu_icon.png'/>";
	private String imageExit = "<img src='icons/exit_menu_icon.png'/>";
	private String imageConf = "<img src='icons/preferences_menu_icon.png'/>";
	private String imageLog = "<img src='icons/changelog_menu_icon.png'/>";
	private String imageReload = "<img src='icons/reload_menu_icon.png'/>";
	private MenuBar confMenu = new MenuBar(true);


	// Identification items
	private HorizontalPanel horizontalPanel01 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel02 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel03 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel04 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel05 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel06 = new HorizontalPanel();
	public static Label identTitleLab = new Label("Project acronym:");
	public static Label identAbstractLab = new Label("Project abstract:");
	private Label identTypeLab = new Label("Resource type:");
	public static Label identLocatorLab = new Label("Resource locator:");
	public static Label identIdentifierLab = new Label("Unique resource identifier:");
	private Label identLanguageLab = new Label("Resource language:");
	public static TextArea identAbstractAre = new TextArea();
	public static TextBox identTitleBox = new TextBox();
	public static TextBox identLocatorBox = new TextBox();
	public static TextBox identIdentifierBox = new TextBox();
	public static ListBox identLanguageLst = new ListBox();
	public static ListBox identTypeLst = new ListBox();
	public static ArrayList<String> languageList = Resources.languageList();
	public static ArrayList<String> typeList = Resources.typeList();
	public static FlexTable idGrid = new FlexTable();
	private PushButton idTitleInfo = Elements.addInfoButton("idTitle");
	private PushButton idAbstractInfo = Elements.addInfoButton("idAbstract");
	private PushButton idTypeInfo = Elements.addInfoButton("idType");
	private PushButton idLocatorInfo = Elements.addInfoButton("idLocator");
	private PushButton idIdentifierInfo = Elements.addInfoButton("idIdentifier");
	private PushButton idLanguageInfo = Elements.addInfoButton("idLanguage");
	public static ScrollPanel idScroll = new ScrollPanel(idGrid);


	// Classification items
	private VerticalPanel verticalPanel01 = new VerticalPanel();
	private VerticalPanel verticalPanel02 = new VerticalPanel();
	public static HorizontalPanel horizontalPanel07 = new HorizontalPanel();
	private PushButton classCategoriesInfo = Elements.addInfoButton("Categories");
	private Label classCategoriesLab = new Label("Topic Categories:");
	private HorizontalPanel classBiotaCheck = Elements.checkBox("Biota");
	private HorizontalPanel classBoundariesCheck = Elements.checkBox("Boundaries");
	private HorizontalPanel classClimatologyCheck = Elements.checkBox("Climatology / Meteorology / Atmosphere");
	private HorizontalPanel classEconomyCheck = Elements.checkBox("Economy");
	private HorizontalPanel classElevationCheck = Elements.checkBox("Elevation");
	private HorizontalPanel classEnvironmentCheck = Elements.checkBox("Environment");
	private HorizontalPanel classFarmingCheck = Elements.checkBox("Farming");
	private HorizontalPanel classInformationCheck = Elements.checkBox("Geoscientific Information");
	private HorizontalPanel classHealthCheck = Elements.checkBox("Health");
	private HorizontalPanel classImageryCheck = Elements.checkBox("Imagery / Base Maps / Earth Cover");
	private HorizontalPanel classIntelligenceCheck = Elements.checkBox("Intelligence / Military");
	private HorizontalPanel classWatersCheck = Elements.checkBox("Inland Waters");
	private HorizontalPanel classLocationCheck = Elements.checkBox("Location");
	private HorizontalPanel classOceansCheck = Elements.checkBox("Oceans");
	private HorizontalPanel classPlanningCheck = Elements.checkBox("Planning / Cadastre");
	private HorizontalPanel classSocietyCheck = Elements.checkBox("Society");
	private HorizontalPanel classStructureCheck = Elements.checkBox("Structure");
	private HorizontalPanel classTransportationCheck = Elements.checkBox("Transportation");
	private HorizontalPanel classCommunicationCheck = Elements.checkBox("Utilities / Communication");
	public static ScrollPanel clScroll = new ScrollPanel(horizontalPanel07);


	// Keywords items
	public static HorizontalPanel horizontalPanel08 = new HorizontalPanel();
	private VerticalPanel verticalPanel03 = new VerticalPanel();
	private VerticalPanel verticalPanel04 = new VerticalPanel();
	private VerticalPanel verticalPanel05 = new VerticalPanel();
	private VerticalPanel verticalPanel06 = new VerticalPanel();
	private PushButton keyInfoButton = Elements.addInfoButton("Keywords");
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
	private HorizontalPanel classAgEngineeringCheck = Elements.checkBox("Agricultural engineering");
	private HorizontalPanel classAgPlantCheck = Elements.checkBox("Agricultural plant science");
	private HorizontalPanel classAgFoodCheck = Elements.checkBox("Food science");
	private HorizontalPanel classAgForestCheck = Elements.checkBox("Forest science");
	private HorizontalPanel classAgSoilsCheck = Elements.checkBox("Soils");
	private HorizontalPanel classAtAerosolsCheck = Elements.checkBox("Aerosols");
	private HorizontalPanel classAtAirCheck = Elements.checkBox("Air quality");
	private HorizontalPanel classAtAltitudeCheck = Elements.checkBox("Altitude");
	private HorizontalPanel classAtChemistryCheck = Elements.checkBox("Atmospheric chemistry");
	private HorizontalPanel classAtElectricityCheck = Elements.checkBox("Atmospheric electricity");
	private HorizontalPanel classAtPhenomenaCheck = Elements.checkBox("Atmospheric phenomena");
	private HorizontalPanel classAtPressureCheck = Elements.checkBox("Atmospheric pressure");
	private HorizontalPanel classAtRadiationCheck = Elements.checkBox("Atmospheric radiation");
	private HorizontalPanel classAtTemperatureCheck = Elements.checkBox("Atmospheric temperature");
	private HorizontalPanel classAtVapourCheck = Elements.checkBox("Atmospheric water vapor");
	private HorizontalPanel classAtWindsCheck = Elements.checkBox("Atmospheric winds");
	private HorizontalPanel classAtCloudsCheck = Elements.checkBox("Clouds");
	private HorizontalPanel classAtPrecipitationCheck = Elements.checkBox("Precipitation");
	private HorizontalPanel classBiDynamicsCheck = Elements.checkBox("Ecological dynamics");
	private HorizontalPanel classBiEcosystemsCheck = Elements.checkBox("Terrestrial ecosystems");
	private HorizontalPanel classBiVegetationCheck = Elements.checkBox("Vegetation");
	private HorizontalPanel classCrGroundCheck = Elements.checkBox("Frozen ground");
	private HorizontalPanel classCrGlaciersCheck = Elements.checkBox("Glaciers / Ice sheet");
	private HorizontalPanel classCrIceCheck = Elements.checkBox("Sea ice");
	private HorizontalPanel classCrSnowCheck = Elements.checkBox("Snow / Ice");
	private HorizontalPanel classLsErosionCheck = Elements.checkBox("Erosion / Sedimentation");
	private HorizontalPanel classLsGeomorphologyCheck = Elements.checkBox("Geomorphology");
	private HorizontalPanel classLsTemperatureCheck = Elements.checkBox("Land temperature");
	private HorizontalPanel classLsCoverCheck = Elements.checkBox("Land use / Land cover");
	private HorizontalPanel classLsLandscapeCheck = Elements.checkBox("Landscape");
	private HorizontalPanel classLsSurfaceCheck = Elements.checkBox("Surface radiative properties");
	private HorizontalPanel classLsTopographyCheck = Elements.checkBox("Topography");
	private HorizontalPanel classOcBathymetryCheck = Elements.checkBox("Bathymetry");
	private HorizontalPanel classOcProcessesCheck = Elements.checkBox("Coastal processes");
	private HorizontalPanel classOcEnvironmentCheck = Elements.checkBox("Marine environment");
	private HorizontalPanel classOcGeophysicsCheck = Elements.checkBox("Marine geophysics");
	private HorizontalPanel classOcWavesCheck = Elements.checkBox("Ocean waves");
	private HorizontalPanel classOcWindsCheck = Elements.checkBox("Ocean winds");
	private HorizontalPanel classOcTopographyCheck = Elements.checkBox("Sea surface topography");
	private HorizontalPanel classOcTidesCheck = Elements.checkBox("Tides");
	private HorizontalPanel classOcQualityCheck = Elements.checkBox("Water quality");
	private HorizontalPanel classSeGeodeticsCheck = Elements.checkBox("Geodetics");
	private HorizontalPanel classSeGeomagnetismCheck = Elements.checkBox("Geomagnetism");
	private HorizontalPanel classSeLandformsCheck = Elements.checkBox("Geomorphic landforms");
	private HorizontalPanel classSeGravityCheck = Elements.checkBox("Gravity");
	private HorizontalPanel classSpGammaCheck = Elements.checkBox("Gamma ray");
	private HorizontalPanel classSpInfraredCheck = Elements.checkBox("Infrared wavelengths");
	private HorizontalPanel classSpLidarCheck = Elements.checkBox("LIDAR");
	private HorizontalPanel classSpMicrowaveCheck = Elements.checkBox("Microwave");
	private HorizontalPanel classSpRadarCheck = Elements.checkBox("RADAR");
	private HorizontalPanel classSpRadioCheck = Elements.checkBox("Radio wave");
	private HorizontalPanel classSpUltravioletCheck = Elements.checkBox("Ultraviolet wavelengths");
	private HorizontalPanel classSpVisibleCheck = Elements.checkBox("Visible wavelengths");
	private HorizontalPanel classSpXrayCheck = Elements.checkBox("X-ray");
	private HorizontalPanel classInIonosphereCheck = Elements.checkBox("Ionosphere / Magnetosphere");
	private HorizontalPanel classInActivityCheck = Elements.checkBox("Solar activity");
	private HorizontalPanel classInParticleCheck = Elements.checkBox("Solar energetic particle");
	private HorizontalPanel classThGroundCheck = Elements.checkBox("Ground water");
	private HorizontalPanel classThSurfaceCheck = Elements.checkBox("Surface water");
	private HorizontalPanel classThChemistryCheck = Elements.checkBox("Water quality / chemistry");
	public static ScrollPanel kwScroll = new ScrollPanel(horizontalPanel08);


	// Aircraft and insutruments items
	private VerticalPanel verticalPanel08 = new VerticalPanel();
	private VerticalPanel verticalPanel09 = new VerticalPanel();
	public static VerticalPanel verticalPanel10 = new VerticalPanel();
	public static VerticalPanel verticalPanel20 = new VerticalPanel();
	private HorizontalPanel horizontalPanel09 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel10 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel11 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel12 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel60 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel61 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel62 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel63 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel64 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel65 = new HorizontalPanel();
	public static FlexTable airInstrumentTable = new FlexTable();
	private PushButton airPlatformInfo = Elements.addInfoButton("aiAircraft");
	private PushButton airInstrumentInfo = Elements.addInfoButton("aiInstrument");
	private PushButton airPlusButton = Elements.plusButton("airInstrument");
	private static Label airAircraftLab = new Label("Aircraft:");
	public static Label airManufacturerLab = new Label("Manufacturer:");
	public static Label airManufacturerInfo = new Label("");
	public static Label airTypeLab = new Label("Aircraft type:");
	public static Label airTypeInfo = new Label("");
	public static Label airOperatorLab = new Label("Operator:");
	public static Label airOperatorInfo = new Label("");
	public static Label airCountryLab = new Label("Country:");
	public static Label airCountryInfo = new Label("");
	public static Label airRegistrationLab = new Label("Registration number:");
	public static Label airRegistrationInfo = new Label("");
	public static Label airCopyrightInfo = new Label("EUFAR");
	public static Label airInstNameLab = new Label("Name:");
	public static Label airInstManufacturerLab = new Label("Manufacturer:");
	private static Label airInstrumentLab = new Label("Instrument:");
	public static TextBox airManufacturerBox = new TextBox();
	public static TextBox airTypeBox = new TextBox();
	public static TextBox airOperatorBox = new TextBox();
	public static ListBox airCountryLst = new ListBox();
	public static TextBox airRegistrationBox = new TextBox();
	public static TextBox airInstNameBox = new TextBox();
	public static TextBox airInstManufacturerBox = new TextBox();
	private Image airCopyrightImage = new Image("icons/copyright_icon_small.png");
	public static ListBox airAircraftLst = new ListBox();
	public static ListBox airInstrumentLst = new ListBox();
	private ArrayList<String> aircraftList = Resources.aircraftList();
	public static ArrayList<String> instrumentTabList = new ArrayList<String>();
	public static ArrayList<String> manufacturerTabList = new ArrayList<String>();
	public static Image airAircraftImg = new Image("eufar_aircraft/logo_eufar_emc.png");
	public static String[][] airAircraftInfo = Resources.aircraftInfo();
	public static ScrollPanel aiScroll = new ScrollPanel(verticalPanel10);


	// Geographic information items
	public static VerticalPanel verticalPanel11 = new VerticalPanel();
	private HorizontalPanel horizontalPanel13 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel16 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel17 = new HorizontalPanel();
	private PushButton geoLocationInfo = Elements.addInfoButton("giLocation");
	private PushButton geoCoordInfo = Elements.addInfoButton("giBox"); 
	private PushButton geoUnitInfo = Elements.addInfoButton("giUnit");
	private static Label geoLocationLab = new Label("Location:");
	public static Label geoBoundingLab = new Label("Geographic bounding box:");
	public static Label geoResolutionLab = new Label("Spatial resolution:");
	public static Label geoUnitLab = new Label("Unit:");
	public static ListBox geoLocationLst = new ListBox();
	public static ListBox geoDetailLst = new ListBox();
	public static ListBox geoResolutionLst = new ListBox();
	public static ListBox geoUnitLst = new ListBox();
	public static ArrayList<String> countryList = Resources.countryList();
	public static ArrayList<String> continentList = Resources.continentList();
	public static ArrayList<String> oceanList = Resources.oceanList();
	public static ArrayList<String> regionList = Resources.regionList();
	private ArrayList<String> locationList = Resources.locationList();
	private ArrayList<String> resolutionList = Resources.resolutionList();
	private ArrayList<String> unitList = Resources.unitList();
	public static TextBox geoNorthBox = new TextBox();
	public static TextBox geoSouthBox = new TextBox();
	public static TextBox geoEastBox = new TextBox();
	public static TextBox geoWestBox = new TextBox();
	public static TextBox geoResolutionBox = new TextBox();
	private Image geoFollowImage = new Image("icons/fwd_arrow_small.png");
	private Image geoCoordImage = new Image("icons/EMC_globe_terrestre.png");
	private FlexTable geoCoordTab = new FlexTable();
	public static ScrollPanel giScroll = new ScrollPanel(verticalPanel11);


	// Temporal Reference items
	public static VerticalPanel verticalPanel13 = new VerticalPanel();
	private HorizontalPanel horizontalPanel21 = new HorizontalPanel();
	private PushButton refPublicationInfo = Elements.addInfoButton("trPublication");
	private PushButton refRevisionInfo = Elements.addInfoButton("trRevision");
	private PushButton refCreationInfo = Elements.addInfoButton("trCreation");
	private PushButton refPlusButton = Elements.plusButton("tempRef");
	private PushButton refPlusInfo = Elements.addInfoButton("trPhase");
	public static Label refPublicationLab = new Label("Date of publication:");
	public static Label refCreationLab = new Label("Date of creation:");
	public static Label refRevisionLab = new Label("Date of last revision:");
	public static Label refExtentLab = new Label("Temporal extent:");
	public static Label refPhaseLab = new Label("Phase 1:");
	public static DateBox refPublicationDat = new DateBox();
	public static DateBox refRevisionDat = new DateBox();
	public static DateBox refCreationDat = new DateBox();
	public static DateBox refStartDat = new DateBox();
	public static DateBox refEndDat = new DateBox();
	public static ArrayList<DateBox> refStartLst = new ArrayList<DateBox>();
	public static ArrayList<DateBox> refEndLst = new ArrayList<DateBox>();
	public static FlexTable refDateTab = new FlexTable();
	public static FlexTable refPhaseTab = new FlexTable();
	public static PushButton refDelButton = new PushButton(new Image("icons/empty_icon_small.png"));
	public static ScrollPanel trScroll = new ScrollPanel(verticalPanel13);


	// Quality and Validity items
	public static VerticalPanel verticalPanel14 = new VerticalPanel();
	public static VerticalPanel verticalPanel17 = new VerticalPanel();
	public static VerticalPanel verticalPanel18 = new VerticalPanel();
	public static VerticalPanel verticalPanel19 = new VerticalPanel();
	public static HorizontalPanel horizontalPanel30 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel31 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel32 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel33 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel34 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel35 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel36 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel37 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel38 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel39 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel40 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel41 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel42 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel43 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel44 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel45 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel46 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel47 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel48 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel49 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel50 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel51 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel52 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel53 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel54 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel55 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel56 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel57 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel58 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel59 = new HorizontalPanel();
	public static HorizontalPanel imageRad = Elements.radioButton("radioGrp1","Earth observation/Remote sensing data");
	public static HorizontalPanel insituRad  = Elements.radioButton("radioGrp1","Atmospheric/In-situ data");
	public static HorizontalPanel insituChk01Y = Elements.radioButton("radioGrp19","Yes");
	public static HorizontalPanel insituChk01N = Elements.radioButton("radioGrp19","No");
	public static HorizontalPanel insituChk04 = Elements.radioButton("radioGrp2","NetCDF");
	public static HorizontalPanel insituChk05 = Elements.radioButton("radioGrp2","HDF");
	public static HorizontalPanel insituChk06 = Elements.radioButton("radioGrp2","NASA/Ames");
	public static HorizontalPanel insituChk07 = Elements.radioButton("radioGrp2","Other");
	public static HorizontalPanel imageChk10Y = Elements.radioButton("radioGrp3","Yes");
	public static HorizontalPanel imageChk10N = Elements.radioButton("radioGrp3","No");
	public static HorizontalPanel imageChk11Y = Elements.radioButton("radioGrp4","Yes");
	public static HorizontalPanel imageChk11N = Elements.radioButton("radioGrp4","No");
	public static HorizontalPanel imageChk12Y = Elements.radioButton("radioGrp5","Yes");
	public static HorizontalPanel imageChk12N = Elements.radioButton("radioGrp5","No");
	public static HorizontalPanel imageChk13Y = Elements.radioButton("radioGrp6","Yes");
	public static HorizontalPanel imageChk13N = Elements.radioButton("radioGrp6","No");
	public static HorizontalPanel imageChk14Y = Elements.radioButton("radioGrp7","Yes");
	public static HorizontalPanel imageChk14N = Elements.radioButton("radioGrp7","No");
	public static HorizontalPanel imageChk15Y = Elements.radioButton("radioGrp8","Yes");
	public static HorizontalPanel imageChk15N = Elements.radioButton("radioGrp8","No");
	public static HorizontalPanel imageChk16Y = Elements.radioButton("radioGrp9","Yes");
	public static HorizontalPanel imageChk16N = Elements.radioButton("radioGrp9","No");
	public static HorizontalPanel imageChk17Y = Elements.radioButton("radioGrp10","Yes");
	public static HorizontalPanel imageChk17N = Elements.radioButton("radioGrp10","No");
	public static HorizontalPanel imageChk18Y = Elements.radioButton("radioGrp11","Yes");
	public static HorizontalPanel imageChk18N = Elements.radioButton("radioGrp11","No");
	public static HorizontalPanel imageChk19Y = Elements.radioButton("radioGrp12","Yes");
	public static HorizontalPanel imageChk19N = Elements.radioButton("radioGrp12","No");
	public static HorizontalPanel imageChk20Y = Elements.radioButton("radioGrp13","Yes");
	public static HorizontalPanel imageChk20N = Elements.radioButton("radioGrp13","No");
	public static HorizontalPanel imageChk21Y = Elements.radioButton("radioGrp14","Yes");
	public static HorizontalPanel imageChk21N = Elements.radioButton("radioGrp14","No");
	public static HorizontalPanel imageChk22Y = Elements.radioButton("radioGrp15","Yes");
	public static HorizontalPanel imageChk22N = Elements.radioButton("radioGrp15","No");
	public static HorizontalPanel imageChk23Y = Elements.radioButton("radioGrp16","Yes");
	public static HorizontalPanel imageChk23N = Elements.radioButton("radioGrp16","No");
	public static HorizontalPanel imageChk24Y = Elements.radioButton("radioGrp17","Yes");
	public static HorizontalPanel imageChk24N = Elements.radioButton("radioGrp17","No");
	public static HorizontalPanel imageChk25Y = Elements.radioButton("radioGrp18","Yes");
	public static HorizontalPanel imageChk25N = Elements.radioButton("radioGrp18","No");
	public static PushButton qvInfoButton01 = Elements.addInfoButton("qvLineage1");
	public static PushButton qvInfoButton02 = Elements.addInfoButton("qvLineage2");
	public static PushButton qvInfoButton03 = Elements.addInfoButton("qvLineage3");
	public static PushButton qvInfoButton04 = Elements.addInfoButton("qvLineage4");
	public static PushButton qvInfoButton05 = Elements.addInfoButton("qvLineage5");
	public static PushButton qvInfoButton06 = Elements.addInfoButton("qvLineage6");
	public static PushButton qvInfoButton07 = Elements.addInfoButton("qvLineage7");
	public static PushButton qvInfoButton08 = Elements.addInfoButton("qvLineage8");
	public static PushButton qvInfoButton09 = Elements.addInfoButton("qvLineage9");
	public static PushButton qvInfoButton10 = Elements.addInfoButton("qvLineage10");
	public static PushButton qvInfoButton11 = Elements.addInfoButton("qvLineage11");
	public static PushButton qvInfoButton12 = Elements.addInfoButton("qvLineage12");
	public static PushButton qvInfoButton13 = Elements.addInfoButton("qvLineage13");
	public static PushButton qvInfoButton14 = Elements.addInfoButton("qvLineage14");
	public static PushButton qvInfoButton15 = Elements.addInfoButton("qvLineage15");
	public static PushButton qvInfoButton16 = Elements.addInfoButton("qvLineage16");
	public static PushButton qvInfoButton17 = Elements.addInfoButton("qvLineage17");
	public static PushButton qvInfoButton18 = Elements.addInfoButton("qvLineage18");
	public static Label insituCalLab = new Label("Operator's standard calibration procedures applied to raw digital data:");
	public static Label insituGeoLab = new Label("Conversion to geophysical units:");
	public static Label insituOutLab = new Label("Output in standardized format:");
	public static Label insituFlaLab = new Label("Quality-control flagging applied to individual data points:");
	public static Label insituAssLab = new Label("Assumption:");
	public static Label insituLinkLab = new Label("Link to the procedure's description:");
	public static Label insituConstLab = new Label("Source of calibration constants:");
	public static Label insituMatLab = new Label("Source of calibration materials:");
	public static Label insituFlagLab = new Label("Description or link to the operator's standard procedure:");
	public static Label imageCalLab = new Label("Calibration information");
	public static Label imageAcqLab = new Label("Acquisition information");
	public static Label imageProLab = new Label("Processing information");
	public static Label imageLayLab = new Label("Data Quality Layers");
	public static Label imageNameLab = new Label("Name of calibration laboratory:");
	public static Label imageRadLab = new Label("Date of radiometric calibration:");
	public static Label imageSpeLab = new Label("Date of spectral calibration:");
	public static Label imageBanLab = new Label("Number of spectral bands (spectral mode):");
	public static Label imageDirLab = new Label("Overall heading / fligh direction (dd):");
	public static Label imageAltLab = new Label("Overall altitude / average height ASL (m):");
	public static Label imageZenLab = new Label("Solar zenith (dd):");
	public static Label imageAziLab = new Label("Solar azimuth (dd):");
	public static Label imageAnoLab = new Label("Report anomalies in data acquisition:");
	public static Label imageLevLab = new Label("Processing level:");
	public static Label imageDCLab = new Label("Dark current (DC) correction ?");
	public static Label imageCalcorrLab = new Label("Sensor calibration and system correction:");
	public static Label imageErrLab = new Label("Image data artefacts and processing errors:");
	public static Label imageErrcorrLab = new Label("GPS/IMU related errors, geometric correction:");
	public static Label imageCorrconLab = new Label("Atmospheric correction and atmospheric conditions:");
	public static Label imageIntPixel = new Label("Aggregated interpolated pixel mask ('corrected pixels') ?");
	public static Label imageBadPixel = new Label("Aggregated bad pixel mask ('not corrected pixels') ?");
	public static Label imageSatPixel = new Label("Saturated pixels / overflow ?");
	public static Label imageAffPixel = new Label("Pixels affected by saturation in spatial/spectral neighbourhood ?");
	public static Label imagePosInfo = new Label("Problems with position information / Interpolated position information ?");
	public static Label imageAttInfo = new Label("Problems with attitude information / Interpolated attitude information ?");
	public static Label imageSyncProblem = new Label("Synchronization problems ?");
	public static Label imageIntGeocoding = new Label("Interpolated pixels during geocoding ?");
	public static Label imageAtmCorrection = new Label("Failure of atmospheric correction ?");
	public static Label imageCloudMask = new Label("Cloud mask ?");
	public static Label imageShadMask = new Label("Cloud shadow mask ?");
	public static Label imageHazeMask = new Label("Haze mask ?");
	public static Label imageRouMeasure = new Label("Critical terrain correction based on DEM roughness measure ?");
	public static Label imageIllAngle = new Label("Critical terrain correction based on slope/local illumination angle ?");
	public static Label imageBRDFGeometry = new Label("Critical BRDF geometry based on sun-sensor-terrain geometry ?");
	public static TextBox insituLinkBox = new TextBox();
	public static TextBox insituConstBox = new TextBox();
	public static TextBox insituMatBox = new TextBox();
	public static TextBox insituOtherBox = new TextBox();
	public static TextBox imageCalBox = new TextBox();
	public static TextBox imageBanBox = new TextBox();
	public static TextBox imageDirBox = new TextBox();
	public static TextBox imageAltBox = new TextBox();
	public static TextBox imageZenBox = new TextBox();
	public static TextBox imageAziBox = new TextBox();
	public static TextBox imageAnoBox = new TextBox();
	public static DateBox imageRadDat = new DateBox();
	public static DateBox imageSpeDat = new DateBox();
	public static ListBox imageLevLst = new ListBox();
	public static ListBox imageDCLst = new ListBox();
	public static ArrayList<String> levelList = Resources.levelList();
	public static ArrayList<String> DCList = Resources.DCList();
	public static FlexTable imageCalTab = new FlexTable();
	public static FlexTable imageAcqTab = new FlexTable();
	public static FlexTable imageProTab = new FlexTable();
	public static FlexTable imageCalcorrTab = new FlexTable();
	public static FlexTable imageErrTab = new FlexTable();
	public static FlexTable imageErrcorrTab = new FlexTable();
	public static FlexTable imageCorrconTab = new FlexTable();
	public static Image insituImage = new Image("icons/fwd_arrow_small.png");
	public static TextArea insituFlagAre = new TextArea();
	public static TextArea insituAssumptionAre = new TextArea();
	public static FlexTable insituCalTab = new FlexTable();
	public static FlexTable insituFlagTab = new FlexTable();
	public static ScrollPanel qvScroll = new ScrollPanel(verticalPanel14);


	// Access and Use Constraints items
	public static VerticalPanel verticalPanel15 = new VerticalPanel();
	public static Label useConditionsLab = new Label("Conditions applying to access and use:");
	public static Label useLimitationsLab = new Label("Limitations on public access:");
	public static TextArea useConditionsBox = new TextArea();
	public static TextArea useLimitationsBox = new TextArea();
	private FlexTable useConditionsTab = new FlexTable();
	public static FlexTable useConditionsAddTab = new FlexTable();
	private FlexTable useLimitationsTab = new FlexTable();
	public static FlexTable useLimitationsAddTab = new FlexTable();
	public static ArrayList<TextArea> useConditionsLst = new ArrayList<TextArea>();
	public static ArrayList<TextArea> useLimitationsLst = new ArrayList<TextArea>();
	public static Image auEmptyImage1 = new Image("icons/empty_icon_small.png");
	public static Image auEmptyImage2 = new Image("icons/empty_icon_small.png");
	public static PushButton auDelButton1 = new PushButton(new Image("icons/empty_icon_small.png"));
	public static PushButton auDelButton2 = new PushButton(new Image("icons/empty_icon_small.png"));
	public static PushButton usePlusButton2 = Elements.plusButton("limitation");
	public static PushButton usePlusButton1 = Elements.plusButton("condition");
	public static ScrollPanel auScroll = new ScrollPanel(verticalPanel15);


	// Responsible Organisations items
	public static HorizontalPanel horizontalPanel27 = new HorizontalPanel();
	private PushButton roPartyInfo = Elements.addInfoButton("roParty");
	private PushButton roEmailInfo = Elements.addInfoButton("roEmail");
	private PushButton roRoleInfo = Elements.addInfoButton("roRole");
	private PushButton orgPlusButton = Elements.plusButton("respOrg");
	public static Label orgPartyLab = new Label("Responsible party:");
	public static Label orgEmailLab = new Label("Responsible party e-mail:");
	private Label orgRoleLab = new Label("Responsible party role:");
	public static TextBox orgPartyBox = new TextBox();
	public static TextBox orgEmailBox = new TextBox();
	public static ListBox orgRoleLst = new ListBox();
	public static ArrayList<String> roleList = Resources.roleList();
	public static ArrayList<TextBox> orgPartyLst = new ArrayList<TextBox>();
	public static ArrayList<ListBox> orgRole2Lst = new ArrayList<ListBox>();
	public static ArrayList<TextBox> orgEmailLst = new ArrayList<TextBox>();
	public static FlexTable orgPartyTab = new FlexTable();
	public static FlexTable orgAddTab = new FlexTable();
	public static PushButton orgDelButton = new PushButton(new Image("icons/empty_icon_small.png"));
	public static ScrollPanel roScroll = new ScrollPanel(orgAddTab);


	// Metadata on Metadata items
	public static VerticalPanel verticalPanel16 = new VerticalPanel();
	private HorizontalPanel horizontalPanel28 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel29 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel66 = new HorizontalPanel();
	private PushButton metDateInfo = Elements.addInfoButton("mmDate");
	private PushButton metLanguageInfo = Elements.addInfoButton("mmLanguage");
	private PushButton metPlusButton = Elements.plusButton("metadata");
	private PushButton metPartyInfo = Elements.addInfoButton("mmParty");
	private Label metDateLab = new Label("Metadata date:");
	private Label metLanguageLab = new Label("Metadata language:");
	public static Label metContactLab = new Label("Metadata point of contact:");
	public static Label metNameLab = new Label("Name:");
	public static Label metEmailLab = new Label("E-mail:");
	public static DateBox metDateDat = new DateBox();
	public static ListBox metLanguageLst = new ListBox();
	public static TextBox metNameBox = new TextBox();
	public static TextBox metEmailBox = new TextBox();
	private FlexTable metMetadataTab = new FlexTable();
	private FlexTable metContactTab = new FlexTable();
	public static FlexTable metPartyTab = new FlexTable();
	public static FlexTable metAddTab = new FlexTable();
	public static ArrayList<TextBox> metNameLst = new ArrayList<TextBox>();
	public static ArrayList<TextBox> metEmailLst = new ArrayList<TextBox>();
	public static PushButton mmDelButton = new PushButton(new Image("icons/empty_icon_small.png"));
	public static ScrollPanel mmScroll = new ScrollPanel(verticalPanel16);

	
	public void onModuleLoad() {
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable e) {
				Throwable unwrapped = unwrap(e);
				rootLogger.log(Level.SEVERE, "An uncaught exception occured: ", unwrapped);
			}
		});
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				onModuleLoad2();
			}
		});
	}
	

	public void onModuleLoad2() {
		
		rootLogger.log(Level.INFO, "EMC has started on: " + Navigator.getUserAgent());
		rootLogger.log(Level.INFO, "PATH: " + emcPath);

		// Commands in the menu bar
		Command aboutWindow = new Command() {public void execute() {
			rootLogger.log(Level.INFO, "About ASMM invoked...");
			PopupMessages.aboutEmc(emcVersion, eclipseVersion, gwtVersion, javaVersion, inspireVersion);
		}};
		
		Command aboutStandard = new Command() {public void execute() {
			rootLogger.log(Level.INFO, "About INSPIRE invoked...");
			PopupMessages.aboutInspire();
		}};
		
		Command newFile = new Command() {public void execute() {
			newFile();
		}};
		
		Command openFile = new Command() {public void execute() {
			PopupMessages.openFile();
		}};
		
		Command saveFile = new Command() {public void execute() {
			runCheck("");
		}};
		
		Command launchN7SPPage = new Command()  {public void execute() {
			Window.open("http://www.eufar.net/N6SP", "_blank", "");
		}};
		
		Command exitFile = new Command()  {public void execute() {
			Window.open("http://www.eufar.net", "_self", "");
		}};
		
		Command confLayout = new Command() {public void execute() {
			if (!tabLayout) {
				PopupMessages.layoutPanel();
			} else {
				rootLogger.log(Level.INFO, "Layout already changed");
			}
		}};
		
		Command displayLog = new Command() {public void execute() {
			rootLogger.log(Level.INFO, "Changelog invoked...");
			PopupMessages.changelogPanel();
		}};
		
		Command reload = new Command() {public void execute() {
			Utilities.reloadGUI();
		}};


		// Assemble the Menu panel
		aboutMenu.addItem(new MenuItem(imageAbout,true,aboutWindow));
		aboutMenu.addItem(new MenuItem(imageStandard,true,aboutStandard));
		aboutMenu.addItem(new MenuItem(imageEufar,true,launchN7SPPage));
		aboutMenu.addSeparator();
		aboutMenu.addItem(new MenuItem(imageLog,true,displayLog));
		fileMenu.addItem(new MenuItem(imageNew,true,newFile));
		fileMenu.addItem(new MenuItem(imageOpen,true,openFile));
		fileMenu.addItem(new MenuItem(imageSave,true,saveFile));
		fileMenu.addItem(new MenuItem(imageExit,true,exitFile));
		confMenu.addItem(new MenuItem(imageConf,true,confLayout));
		confMenu.addItem(new MenuItem(imageReload,true,reload));
		mainMenu.addItem("File", fileMenu);
		mainMenu.addItem("GUI", confMenu);
		mainMenu.addItem("About", aboutMenu);
		rootLogger.log(Level.INFO, "Menu initialized");
		
		
		// Assemble Identification panel
		horizontalPanel01.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel02.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel03.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel04.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel05.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel06.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel01.add(identTitleBox);
		horizontalPanel01.add(idTitleInfo);
		horizontalPanel02.add(identAbstractAre);
		horizontalPanel02.add(idAbstractInfo);
		horizontalPanel03.add(identTypeLst);
		horizontalPanel03.add(idTypeInfo);
		horizontalPanel04.add(identLocatorBox);
		horizontalPanel04.add(idLocatorInfo);
		horizontalPanel05.add(identIdentifierBox);
		horizontalPanel05.add(idIdentifierInfo);
		horizontalPanel06.add(identLanguageLst);
		horizontalPanel06.add(idLanguageInfo);
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
		idGrid.setCellSpacing(10);
		Utilities.populateListBox(identTypeLst, typeList, 0);
		Utilities.populateListBox(identLanguageLst, languageList, 4);
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
		idGrid.setStyleName("identGrid");
		identLanguageLst.setName("identLanguageList");
		identTypeLst.setName("identTypeList");
		identLocatorBox.setText("http://browse.ceda.ac.uk/browse/badc/eufar/docs/00eufararchivecontents.html");
		rootLogger.log(Level.INFO, "Identification panel initialized");


		// Assemble Classification panel
		horizontalPanel07.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel07.setSpacing(20);
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
		horizontalPanel07.add(classCategoriesInfo);
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
		classCategoriesInfo.getElement().setAttribute("style", "margin-left: 40px !important;");
		rootLogger.log(Level.INFO, "Classification panel initialized");


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
		verticalPanel05.add(classSpLidarCheck);
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
		classAgEngineeringCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAgPlantCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAgFoodCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAgForestCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAgSoilsCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAtAerosolsCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAtAirCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAtAltitudeCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAtChemistryCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAtElectricityCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAtPhenomenaCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAtPressureCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAtRadiationCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAtTemperatureCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAtVapourCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAtWindsCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAtCloudsCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classAtPrecipitationCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classBiDynamicsCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classBiEcosystemsCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classBiVegetationCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classCrGroundCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classCrGlaciersCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classCrIceCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classCrSnowCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classLsErosionCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classLsGeomorphologyCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classLsTemperatureCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classLsCoverCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classLsLandscapeCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classLsSurfaceCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classLsTopographyCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classOcBathymetryCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classOcProcessesCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classOcEnvironmentCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classOcGeophysicsCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classOcWavesCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classOcWindsCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classOcTopographyCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classOcTidesCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classOcQualityCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classSeGeodeticsCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classSeGeomagnetismCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classSeLandformsCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classSeGravityCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classSpGammaCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classSpInfraredCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classSpLidarCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classSpMicrowaveCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classSpRadarCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classSpRadioCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classSpUltravioletCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classSpVisibleCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classSpXrayCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classInIonosphereCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classInActivityCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classInParticleCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classThGroundCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classThSurfaceCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		classThChemistryCheck.getElement().setAttribute("style", "margin-left: 40px !important;");
		keyAgricultureLab.setStyleName("keyTitleTextLabel");
		keyAgricultureLab.getElement().setAttribute("style", "margin-top: 0px !important;");
		keyAtmosphereLab.setStyleName("keyTitleTextLabel");
		keyBiosphereLab.setStyleName("keyTitleTextLabel");
		keyCryosphereLab.setStyleName("keyTitleTextLabel");
		keySurfaceLab.setStyleName("keyTitleTextLabel");
		keyOceanLab.setStyleName("keyTitleTextLabel");
		keyOceanLab.getElement().setAttribute("style", "margin-top: 0px !important;");
		keyEarthLab.setStyleName("keyTitleTextLabel");
		keySpectralLab.setStyleName("keyTitleTextLabel");
		keyInteractionsLab.setStyleName("keyTitleTextLabel");
		keyHydrosphereLab.setStyleName("keyTitleTextLabel");
		keyInfoButton.getElement().setAttribute("style", "margin-left: 40px !important;");
		verticalPanel03.setSpacing(3);
		verticalPanel04.setSpacing(3);
		verticalPanel05.setSpacing(3);
		verticalPanel06.setSpacing(3);
		horizontalPanel08.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel08.add(verticalPanel03);
		horizontalPanel08.add(verticalPanel04);
		horizontalPanel08.add(verticalPanel05);
		horizontalPanel08.add(verticalPanel06);
		horizontalPanel08.add(keyInfoButton);
		horizontalPanel08.setSpacing(20);
		rootLogger.log(Level.INFO, "Keywords panel initialized");


		// Assemble Aircraft and instruments panel
		Utilities.populateListBox(airAircraftLst, aircraftList, 0);
		Utilities.populateListBox(airInstrumentLst, instrumentMap, 0);
		horizontalPanel09.add(airAircraftLab);
		horizontalPanel09.add(airAircraftLst);
		horizontalPanel09.add(airPlatformInfo);
		verticalPanel08.add(horizontalPanel09);
		horizontalPanel60.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel61.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel62.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel63.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel64.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel20.add(horizontalPanel60);
		verticalPanel20.add(horizontalPanel61);
		verticalPanel20.add(horizontalPanel62);
		verticalPanel20.add(horizontalPanel63);
		verticalPanel20.add(horizontalPanel64);
		verticalPanel20.getElement().setAttribute("style", "margin-top: 10px !important;");
		verticalPanel20.setSize("550px", "250px");
		horizontalPanel60.add(airManufacturerLab);
		horizontalPanel60.add(airManufacturerInfo);
		horizontalPanel61.add(airTypeLab);
		horizontalPanel61.add(airTypeInfo);
		horizontalPanel62.add(airOperatorLab);
		horizontalPanel62.add(airOperatorInfo);
		horizontalPanel63.add(airCountryLab);
		horizontalPanel63.add(airCountryInfo);
		horizontalPanel64.add(airRegistrationLab);
		horizontalPanel64.add(airRegistrationInfo);
		verticalPanel08.add(verticalPanel20);
		Emc_eufar.airAircraftImg.setSize("550px","302px");
		horizontalPanel10.add(airCopyrightImage);
		horizontalPanel10.add(airCopyrightInfo);
		verticalPanel09.add(airAircraftImg);
		verticalPanel09.add(horizontalPanel10);
		horizontalPanel11.add(verticalPanel08);
		horizontalPanel11.add(verticalPanel09);
		verticalPanel10.add(horizontalPanel11);
		verticalPanel10.add(new HTML("<hr>"));
		horizontalPanel12.add(airInstrumentLab);
		horizontalPanel12.add(airInstrumentLst);
		horizontalPanel12.add(airPlusButton);
		horizontalPanel12.add(airInstrumentInfo);
		verticalPanel10.add(horizontalPanel12);
		verticalPanel10.add(airInstrumentTable);
		airInstrumentTable.setCellSpacing(10);
		verticalPanel10.setStyleName("airVerticalPanel");
		airAircraftLab.setStyleName("airTitleTextLabel");
		airAircraftLst.setStyleName("airTextList");
		horizontalPanel10.setStyleName("airHorizontalPanel");
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
		airAircraftLst.setName("airAircraftList");
		airInstrumentTable.getElement().setAttribute("style","margin-left: 40px;");
		airInstrumentLst.setStyleName("airTextList2");
		airInstrumentLab.setStyleName("airTitleTextLabel");
		airManufacturerInfo.getElement().setAttribute("style", "margin-left: 20px !important;");
		airTypeInfo.getElement().setAttribute("style", "margin-left: 20px !important;");
		airOperatorInfo.getElement().setAttribute("style", "margin-left: 20px !important; height: 15px; !important;");
		airCountryInfo.getElement().setAttribute("style", "margin-left: 20px !important;");
		airCountryInfo.getElement().setAttribute("style", "margin-left: 20 px !important;");
		airRegistrationInfo.getElement().setAttribute("style", "margin-left: 20px !important;");
		airAircraftLst.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				GuiModification.aircraftInformation(airAircraftLst.getSelectedIndex());
			}
		});
		airInstrumentLst.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				GuiModification.otherInstrument();
			}
		});
		
		
		
		rootLogger.log(Level.INFO, "Aircraft and instruments panel initialized");


		// Assemble Geographic Information panel
		Utilities.populateListBox(geoUnitLst, unitList, 0);
		geoCoordTab.setWidget(1,0,geoBoundingLab);
		geoCoordTab.setWidget(0,2,geoNorthBox);
		geoCoordTab.setWidget(1,1,geoWestBox);
		geoCoordTab.setWidget(1,2,geoCoordImage);
		geoCoordTab.setWidget(1,3,geoEastBox);
		geoCoordTab.setWidget(2,2,geoSouthBox);
		geoCoordTab.setWidget(1,4,geoCoordInfo);
		FlexCellFormatter cellFormatter2 = geoCoordTab.getFlexCellFormatter();
		cellFormatter2.setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);
		cellFormatter2.setHorizontalAlignment(2, 2, HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel13.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel13.add(geoLocationLab);
		horizontalPanel13.add(geoLocationLst);
		horizontalPanel13.add(geoFollowImage);
		horizontalPanel13.add(geoDetailLst);
		horizontalPanel13.add(geoLocationInfo);
		geoLocationLst.setName("geoLocationList");
		geoDetailLst.setName("geoDetailList");
		Utilities.populateListBox(geoLocationLst, locationList, 0);
		geoLocationLab.setStyleName("geoTitleTextLabel");
		geoLocationLst.setStyleName("geoTextList");
		geoDetailLst.setStyleName("geoTextList");
		geoDetailLst.setEnabled(false);
		geoFollowImage.getElement().setAttribute("style", "margin-left: 20px !important;");
		verticalPanel11.add(horizontalPanel13);
		verticalPanel11.add(geoCoordTab);
		horizontalPanel16.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel16.add(geoResolutionLab);
		horizontalPanel16.add(geoResolutionLst);
		Utilities.populateListBox(geoResolutionLst, resolutionList, 0);
		geoResolutionLst.setName("geoResolutionList");
		horizontalPanel16.add(horizontalPanel17);
		horizontalPanel17.add(geoResolutionBox);
		horizontalPanel16.add(geoUnitInfo); 
		geoResolutionLab.setStyleName("geoTitleTextLabel");
		geoResolutionLst.setStyleName("geoTextList");
		geoResolutionBox.setStyleName("geoTextBox2");
		verticalPanel11.add(horizontalPanel16);
		verticalPanel11.setStyleName("geoVerticalPanel");
		geoCoordInfo.getElement().setAttribute("style", "margin-left: 40px !important;");
		geoBoundingLab.setStyleName("geoTitleTextLabel2");
		geoNorthBox.setStyleName("geoTextBox4");
		geoSouthBox.setStyleName("geoTextBox4");
		geoEastBox.setStyleName("geoTextBox");
		geoWestBox.setStyleName("geoTextBox");
		geoCoordImage.getElement().setAttribute("style", "margin-top: 5px !important; height: 300px !important; width: 298 px !important;");
		geoCoordTab.getElement().setAttribute("style", "margin-top: 20px !important; margin-bottom: 30px !important;");
		geoUnitInfo.getElement().setAttribute("style", "margin-top: 1px !important;");
		geoResolutionLst.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				GuiModification.geoResolutionSet(geoResolutionLst.getSelectedIndex());
			}
		});
		geoLocationLst.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				Utilities.geoLocationSet(geoLocationLst.getSelectedIndex());
			}
		});
		rootLogger.log(Level.INFO, "Geographic Information panel initialized");


		// Assemble Temporal Reference panel
		refPublicationDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		refPublicationDat.setValue(new Date());
		refRevisionDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		refRevisionDat.setValue(new Date());
		refCreationDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		refCreationDat.setValue(new Date());
		refDateTab.setWidget(0, 0, refPublicationLab);
		refDateTab.setWidget(0, 1, refPublicationDat);
		refDateTab.setWidget(0, 2, refPublicationInfo);
		refDateTab.setWidget(1, 0, refRevisionLab);
		refDateTab.setWidget(1, 1, refRevisionDat);
		refDateTab.setWidget(1, 2, refRevisionInfo);
		refDateTab.setWidget(2, 0, refCreationLab);
		refDateTab.setWidget(2, 1, refCreationDat);
		refDateTab.setWidget(2, 2, refCreationInfo);
		refDateTab.setCellSpacing(10);
		verticalPanel13.add(refDateTab);
		verticalPanel13.add(new HTML("<br>"));
		verticalPanel13.add(new HTML("<hr>"));
		verticalPanel13.add(new HTML("<br>"));
		horizontalPanel21.add(refExtentLab);
		refPhaseTab.setWidget(0, 0, refPhaseLab);
		refPhaseTab.setWidget(0, 1, refStartDat);
		refPhaseTab.setWidget(0, 2, refEndDat);
		refPhaseTab.setWidget(0, 3, refDelButton);
		refDelButton.setEnabled(false);
		refStartDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		refStartDat.setValue(new Date());
		refEndDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		refEndDat.setValue(new Date());
		refStartLst.add(refStartDat);
		refEndLst.add(refEndDat);
		horizontalPanel21.add(refPhaseTab);
		horizontalPanel21.add(refPlusButton);
		horizontalPanel21.add(refPlusInfo);
		refExtentLab.setStyleName("refTitleTextLabel");
		refDelButton.setStyleName("emptyButton");
		refPhaseLab.setStyleName("refTextLabel");
		refPublicationLab.setStyleName("refTextLabel");
		refRevisionLab.setStyleName("refTextLabel");
		refCreationLab.setStyleName("refTextLabel");
		refPlusButton.getElement().setAttribute("style", "margin-left: 20px !important; margin-top: 5px !important;");
		refPlusInfo.getElement().setAttribute("style", "margin-left: 20px !important; margin-top: 5px !important;");
		verticalPanel13.getElement().setAttribute("style", "margin-left: 20px !important; margin-top: 10px !important;");
		verticalPanel13.add(horizontalPanel21);
		Emc_eufar.refDelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				refStartDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(
						DateTimeFormat.getFormat("yyyy-MM-dd").format(refStartLst.get(1).getValue())));
				refEndDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(
						DateTimeFormat.getFormat("yyyy-MM-dd").format(refEndLst.get(1).getValue())));
				refPhaseTab.removeRow(1);
				refStartLst.remove(1);
				refEndLst.remove(1);
				int row = refPhaseTab.getRowCount();
				if (row == 1) {
					Emc_eufar.refDelButton.setEnabled(false);
					Emc_eufar.refDelButton.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
					Emc_eufar.refDelButton.setStyleName("emptyButton");
				}
			}
		});
		rootLogger.log(Level.INFO, "Temporal Reference panel initialized");


		// Quality and Validity panel
		Utilities.populateListBox(imageLevLst, levelList, 0);
		Utilities.populateListBox(imageDCLst, DCList, 0);
		imageRadDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		imageRadDat.setValue(new Date());
		imageSpeDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		imageSpeDat.setValue(new Date());
		horizontalPanel30.add(imageRad);
		horizontalPanel30.add(insituRad);
		horizontalPanel30.add(qvInfoButton01);
		qvInfoButton01.getElement().setAttribute("style","margin-left: 200px !important;");
		horizontalPanel30.getElement().setAttribute("style", "margin-bottom: 20px;");
		verticalPanel14.add(horizontalPanel30);
		verticalPanel14.add(verticalPanel17);
		imageRad.setStyleName("qv_imageRad");
		insituRad.setStyleName("qv_insituRad");
		verticalPanel14.getElement().setAttribute("style", "margin: 20px 20px 20px 20px;");
		rootLogger.log(Level.INFO, "Quality and Validity panel initialized");


		// Access and Use Constraints panel
		useConditionsAddTab.setWidget(0, 0, useConditionsBox);
		useConditionsAddTab.setWidget(0, 1, auDelButton1);
		auDelButton1.setEnabled(false);
		useConditionsTab.setWidget(0, 0, useConditionsLab);
		useConditionsTab.setWidget(0, 1, useConditionsAddTab);
		PushButton useInfoButton1 = Elements.addInfoButton("auConditions");
		useConditionsTab.setWidget(0, 2, usePlusButton1);
		useConditionsTab.setWidget(0, 3, useInfoButton1);
		FlexCellFormatter auCellFormatter1 = useConditionsTab.getFlexCellFormatter();
		auCellFormatter1.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		auCellFormatter1.setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		auCellFormatter1.setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_TOP);
		auCellFormatter1.setVerticalAlignment(0, 3, HasVerticalAlignment.ALIGN_TOP);
		verticalPanel15.add(useConditionsTab);
		verticalPanel15.add(new HTML("<br />"));
		verticalPanel15.add(new HTML("<hr />"));
		verticalPanel15.add(new HTML("<br />"));
		useLimitationsAddTab.setWidget(0, 0, useLimitationsBox);
		useLimitationsAddTab.setWidget(0, 1, auDelButton2);
		auDelButton2.setEnabled(false);
		useLimitationsTab.setWidget(0, 0, useLimitationsLab);
		useLimitationsTab.setWidget(0, 1, useLimitationsAddTab);
		useLimitationsTab.setWidget(0, 2, usePlusButton2);
		PushButton useInfoButton2 = Elements.addInfoButton("auLimitations");
		useLimitationsTab.setWidget(0, 3, useInfoButton2); 
		FlexCellFormatter auCellFormatter2 = useLimitationsTab.getFlexCellFormatter();
		auCellFormatter2.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		auCellFormatter2.setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		auCellFormatter2.setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_TOP);
		auCellFormatter2.setVerticalAlignment(0, 3, HasVerticalAlignment.ALIGN_TOP);
		verticalPanel15.add(useLimitationsTab);
		verticalPanel15.setStyleName("useVerticalPanel");
		useConditionsLab.setStyleName("useTextLabel");
		useLimitationsLab.setStyleName("useTextLabel");
		useConditionsBox.setStyleName("useTextArea");
		useLimitationsBox.setStyleName("useTextArea");
		auDelButton1.setStyleName("emptyButton");
		auDelButton2.setStyleName("emptyButton");
		usePlusButton1.getElement().setAttribute("style", "margin-top: 43px !important;");
		useInfoButton1.getElement().setAttribute("style", "margin-top: 43px !important;");
		useConditionsLab.getElement().setAttribute("style", "margin-top: 43px !important;");
		usePlusButton2.getElement().setAttribute("style", "margin-top: 42px !important;");
		useInfoButton2.getElement().setAttribute("style", "margin-top: 42px !important;");
		useLimitationsLab.getElement().setAttribute("style", "margin-top: 42px !important;");
		useConditionsLst.add(useConditionsBox);
		useLimitationsLst.add(useLimitationsBox);
		useConditionsBox.setText("As EUFAR is an EU-funded project, data in the EUFAR archive are available to everyone. All users are "
				+ "requiered to acknowledge the data providers in any publication based on EUFAR data.");
		useLimitationsBox.setText("No limitations");
		Emc_eufar.auDelButton1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				useConditionsBox.setText(useConditionsLst.get(1).getText());
				useConditionsAddTab.removeRow(1);
				useConditionsLst.remove(1);
				int row = Emc_eufar.useConditionsAddTab.getRowCount();
				if (row == 1) {
					Emc_eufar.auDelButton1.setEnabled(false);
					Emc_eufar.auDelButton1.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
					Emc_eufar.auDelButton1.setStyleName("emptyButton");
				}
			}
		});
		Emc_eufar.auDelButton2.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				useLimitationsBox.setText(useLimitationsLst.get(1).getText());
				useLimitationsAddTab.removeRow(1);
				useLimitationsLst.remove(1);
				int row = Emc_eufar.useLimitationsAddTab.getRowCount();
				if (row == 1) {
					Emc_eufar.auDelButton2.setEnabled(false);
					Emc_eufar.auDelButton2.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
					Emc_eufar.auDelButton2.setStyleName("emptyButton");
				}
			}
		});
		rootLogger.log(Level.INFO, "Access and Use Constraints panel initialized");


		// Assemble Responsible Organisations panel
		Utilities.populateListBox(orgRoleLst, roleList, 5);
		orgRoleLst.setName("orgRoleList");
		orgPartyTab.setWidget(0, 0, orgPartyLab);
		orgPartyTab.setWidget(0, 1, orgPartyBox);
		orgPartyTab.setWidget(0, 2, roPartyInfo);
		orgPartyTab.setWidget(1, 0, orgEmailLab);
		orgPartyTab.setWidget(1, 1, orgEmailBox);
		orgPartyTab.setWidget(1, 2, roEmailInfo);
		orgPartyTab.setWidget(2, 0, orgRoleLab);
		orgPartyTab.setWidget(2, 1, orgRoleLst);
		orgPartyTab.setWidget(2, 2, roRoleInfo);
		orgPartyTab.setCellSpacing(10);
		horizontalPanel27.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel27.add(orgPartyTab);
		horizontalPanel27.add(orgDelButton);
		horizontalPanel27.add(orgPlusButton);
		orgDelButton.setEnabled(false);
		orgAddTab.setWidget(0, 0, horizontalPanel27);
		orgAddTab.setStyleName("orgFlexTable");
		orgPartyLab.setStyleName("orgTextLabel");
		orgEmailLab.setStyleName("orgTextLabel");
		orgRoleLab.setStyleName("orgTextLabel");
		orgPartyBox.setStyleName("orgTextBox");
		orgEmailBox.setStyleName("orgTextBox");
		orgRoleLst.setStyleName("orgTextList");
		orgDelButton.setStyleName("emptyButton");
		orgPartyLst.add(orgPartyBox);
		orgRole2Lst.add(orgRoleLst);
		orgEmailLst.add(orgEmailBox);
		Emc_eufar.orgDelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				orgPartyBox.setText(orgPartyLst.get(1).getText());
				orgEmailBox.setText(orgEmailLst.get(1).getText());
				for (int i = 0; i < orgRoleLst.getItemCount(); i++) {
					if (orgRoleLst.getItemText(i) == orgRole2Lst.get(1).getSelectedItemText()) {
						orgRoleLst.setSelectedIndex(i);
						break;
					}
				}
				Emc_eufar.orgAddTab.removeRow(1);
				Emc_eufar.orgPartyLst.remove(1);
				Emc_eufar.orgRole2Lst.remove(1);
				Emc_eufar.orgEmailLst.remove(1);
				int row = Emc_eufar.orgAddTab.getRowCount();
				if (row == 1) {
					Emc_eufar.orgDelButton.setEnabled(false);
					Emc_eufar.orgDelButton.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
					Emc_eufar.orgDelButton.setStyleName("emptyButton");
				}
			}
		});
		rootLogger.log(Level.INFO, "Responsible Organisations panel initialized");


		// Assemble Metadata on Metadata panel
		metDateDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		metDateDat.setValue(new Date());
		Utilities.populateListBox(metLanguageLst, languageList, 4);
		metLanguageLst.setName("metLanguageList");
		metMetadataTab.setWidget(0, 0, metDateLab);
		metMetadataTab.setWidget(0, 1, metDateDat);
		metMetadataTab.setWidget(0, 2, metDateInfo);
		metMetadataTab.setWidget(1, 0, metLanguageLab);
		metMetadataTab.setWidget(1, 1, metLanguageLst);
		metMetadataTab.setWidget(1, 2, metLanguageInfo);
		metMetadataTab.setCellSpacing(10);
		verticalPanel16.add(metMetadataTab);
		verticalPanel16.add(new HTML("<hr />"));
		verticalPanel16.add(new HTML("<br />"));
		metContactTab.setWidget(0, 0, metContactLab);
		metPartyTab.setWidget(0, 0, metNameLab);
		metPartyTab.setWidget(0, 1, metNameBox);
		metPartyTab.setWidget(1, 0, metEmailLab);
		metPartyTab.setWidget(1, 1, metEmailBox);
		horizontalPanel66.add(metPartyTab);
		horizontalPanel66.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel66.add(mmDelButton);
		mmDelButton.setEnabled(false);
		metAddTab.setWidget(0, 0, horizontalPanel66);
		metContactTab.setWidget(0, 1, metAddTab);
		metContactTab.setWidget(0, 2, metPlusButton);
		metContactTab.setWidget(0, 3, metPartyInfo);
		FlexCellFormatter metCellFormatter = metContactTab.getFlexCellFormatter();
		metCellFormatter.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		metCellFormatter.setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		metCellFormatter.setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_TOP);
		metCellFormatter.setVerticalAlignment(0, 3, HasVerticalAlignment.ALIGN_TOP);
		metPlusButton.getElement().setAttribute("style", "margin-top: 22px !important;");
		metPartyInfo.getElement().setAttribute("style", "margin-top: 22px !important;");
		metContactLab.getElement().setAttribute("style", "margin-top: 22px !important;");
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
		mmDelButton.setStyleName("emptyButton");
		metNameLst.add(metNameBox);
		metEmailLst.add(metEmailBox);
		Emc_eufar.mmDelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				metNameBox.setText(Emc_eufar.metNameLst.get(1).getText());
				metEmailBox.setText(Emc_eufar.metEmailLst.get(1).getText());
				Emc_eufar.metAddTab.removeRow(1);
				Emc_eufar.metNameLst.remove(1);
				Emc_eufar.metEmailLst.remove(1);
				int row = Emc_eufar.metAddTab.getRowCount();
				if (row == 1) {
					Emc_eufar.mmDelButton.setEnabled(false);
					Emc_eufar.mmDelButton.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
					Emc_eufar.mmDelButton.setStyleName("emptyButton");
				}
			}
		});
		rootLogger.log(Level.INFO, "Metadata on Metadata panel initialized");

		
		// Screen size detection
		int screenHeight = Utilities.getScreenHeight();
		int screenWidth = Utilities.getScreenWidth();
		float ratio = (float) screenWidth / screenHeight;
		if (screenHeight < 1050 & ratio > 1.5 & ratio < 1.8) {
			GuiModification.changeLayout();
		} 
		else {

			// Associate to stack panel
			stackPanel.add(idScroll,"Identification", 25);
			stackPanel.add(clScroll,"Classification", 25);
			stackPanel.add(kwScroll,"Keywords", 25);
			stackPanel.add(aiScroll,"Aircraft and Instruments", 25);
			stackPanel.add(giScroll,"Geographic Information", 25);
			stackPanel.add(trScroll,"Temporal Reference", 25);
			stackPanel.add(qvScroll,"Quality and Validity", 25);
			stackPanel.add(auScroll,"Access and Use Constraints", 25);
			stackPanel.add(roScroll,"Responsible Organisations", 25);
			stackPanel.add(mmScroll,"Metadata on Metadata", 25);
			stackPanel.addSelectionHandler(new SelectionHandler<Integer>() {
				@Override
				public void onSelection(SelectionEvent<Integer> event) {
					int selectedWidgetIndex = stackPanel.getVisibleIndex();
					rootLogger.log(Level.INFO, "Selected tab index: " + Integer.toString(selectedWidgetIndex));
					
				}
			});
	
	
			// Associate the Main panel with the HTML host page.
			int screen_width = Window.getClientWidth();
			int screen_height = Window.getClientHeight();
			int expandPanel = 0;
			expandPanel = (screen_width - 1166)/2;
			if (expandPanel < 0) {expandPanel = 0;}
			subDockPanel.addNorth(new HTML("<img src='icons/emc_top.jpg' alt='EUFAR Metadata Creator' height='80px' width='1166px'>"), 80);
			subDockPanel.addNorth(mainMenu, 30);
			dockPanel.addEast(new HTML("<img src='icons/emc_shadowr.png' alt='EUFAR Metadata Creator' width='30px' height='" + screen_height + "' align='left'"
					+ ">"), expandPanel);
			dockPanel.addWest(new HTML("<img src='icons/emc_shadowl.png' alt='EUFAR Metadata Creator' width='30px' height='" + screen_height + "' align='right'"
					+ ">"), expandPanel);
			dockPanel.setStyleName("dockPanel");
			subDockPanel.add(stackPanel);
			dockPanel.add(subDockPanel);
			RootLayoutPanel rp=RootLayoutPanel.get();
			rp.add(dockPanel);
			Window.setTitle(titleString);
		}
		rootLogger.log(Level.INFO, "Main panel initialized");


		// Associate textboxes with an eventHandler
		List<TextBoxBase> allTextBox = $("*", subDockPanel).widgets(TextBoxBase.class);
		for (int i = 0; i < allTextBox.size(); i++) {
			allTextBox.get(i).addChangeHandler(new ChangeHandler() {
				@Override
				public void onChange(ChangeEvent event) {Utilities.docIsModified();}
			});
		}


		// Associate checkboxes with an eventHandler
		List<CheckBox> allCheckBoxes = $("*", subDockPanel).widgets(CheckBox.class);
		for (int i = 0; i < allCheckBoxes.size(); i++) {
			allCheckBoxes.get(i).addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {Utilities.docIsModified();}
			});
		}


		// Associate listboxes with an evenHandler
		List<ListBox> allListBoxes = $("*", subDockPanel).widgets(ListBox.class);
		for (int i = 0; i < allListBoxes.size(); i++) {
			allListBoxes.get(i).addChangeHandler(new ChangeHandler() {
				@Override
				public void onChange(ChangeEvent event) {Utilities.docIsModified();}
			});
		}


		// Associate dateboxes with an eventHandler
		List<DateBox> allDateBoxes = $("*", subDockPanel).widgets(DateBox.class);
		for (int i = 0; i < allDateBoxes.size(); i++) {
			allDateBoxes.get(i).addValueChangeHandler(new ValueChangeHandler<Date>() {
				@Override
				public void onValueChange(ValueChangeEvent<Date> event) {Utilities.docIsModified();}
			});
		}
		rootLogger.log(Level.INFO, "ASMM GUI fully initialized...");
		
		
		// Internet Explorer warning
		if (Navigator.getUserAgent().toLowerCase().contains("msie") || Navigator.getUserAgent().toLowerCase().contains("trident")) {
			rootLogger.log(Level.INFO, "IE detected. Internet Explorer panel invoked...");
			PopupMessages.explorerPanel();
		}
		
		
		// question before exiting if the document has been modified
		Window.addWindowClosingHandler(new Window.ClosingHandler() {
			public void onWindowClosing(Window.ClosingEvent closingEvent) {
				if (isModified) {
					closingEvent.setMessage("Are you sure?");
				}
			}
		});
	}

	
	// run a function to check if fields are empty and clean all fields to allow the creation of a new file
	private void newFile() {
		Emc_eufar.rootLogger.log(Level.INFO, "Cleaning function invoked... ");
		if (Emc_eufar.isModified) {
			final DialogBox infoDialog = new DialogBox();
			final VerticalPanel verticalPanel01 = new VerticalPanel();
			final HorizontalPanel horizontalPanel01 = new HorizontalPanel();
			final HorizontalPanel horizontalPanel02 = new HorizontalPanel();
			final Image image = new Image("icons/warning_popup_icon.png");
			final HTML label = new HTML("<p align=justify>The actual document has been modified. Changes will be lost if the document is not "
					+ "saved.<br/></p><p><span style=\" font-weight:600;\">Do you want to save your changes ?</span></p>");
			final Button saveButton = new Button("Save", new ClickHandler() {			
				@Override
				public void onClick(ClickEvent event) {
					infoDialog.hide();
					runCheck("clear");
				}
			});
			final Button cancelButton = new Button("Cancel", new ClickHandler() {			
				@Override
				public void onClick(ClickEvent event) {
					infoDialog.hide();
				}
			});
			final Button createButton = new Button("Clear without saving", new ClickHandler() {			
				@Override
				public void onClick(ClickEvent event) {
					infoDialog.hide();
					Utilities.docNotModified();
					Utilities.clearFields();
				}
			});
			infoDialog.setGlassEnabled(true);
			verticalPanel01.getElement().setAttribute("style", "margin-left: 10px !important; margin-top: 10px !important; margin-right: 10px !important;");
			horizontalPanel01.add(image);
			horizontalPanel01.add(label);
			verticalPanel01.add(horizontalPanel01);
			horizontalPanel02.add(saveButton);
			horizontalPanel02.add(cancelButton);
			horizontalPanel02.add(createButton);
			verticalPanel01.add(horizontalPanel02);
			label.getElement().setAttribute("style", "margin-left: 10px !important;");
			saveButton.getElement().setAttribute("style", "margin-left: 20px !important; font-family: DroidSansFallback !important; font-weight: "
					+ "bold !important; margin-top: 10px !important;");
			cancelButton.getElement().setAttribute("style", "margin-left: 40px !important; font-family: DroidSansFallback !important;"
					+ " font-weight: bold !important; margin-top: 10px !important;");
			createButton.getElement().setAttribute("style", "margin-left: 20px !important; font-family: DroidSansFallback !important;"
					+ " font-weight: bold !important; margin-top: 10px !important; height: 30px !important; width: 180px !important");
			infoDialog.add(verticalPanel01);
			infoDialog.setSize( "400px", "150px" );
			infoDialog.setModal(true);
			infoDialog.center();
			infoDialog.setStyleName("myUploadBox");
			infoDialog.show();
		} else {
			Utilities.clearFields();
		}
	}
	

	// check if all fields have been filled in before saving it
	private void runCheck(final String string) {
		rootLogger.log(Level.INFO, "Check of all fields in progress...");
		int notCompleted = 0;
		int widgetIndex = -1;
		int allClaCheckNumber = 0;
		int allKeyCheckNumber = 0;
		Utilities.runCheckDefault();
		List<DateBox> allDateBox = $("*", subDockPanel).widgets(DateBox.class);
		for (int i = 0; i < allDateBox.size(); i++) {
			if (!Utilities.runCorrect(allDateBox.get(i))) {
				notCompleted++;
			}
		}
		Widget parent;
		for (Map.Entry<TextBoxBase, Label> entry : requiredField.entrySet()) {
			Label label = entry.getValue();
			TextBoxBase textBox = entry.getKey();
			parent = textBox.getParent();
			try {
				while (!(parent instanceof ScrollPanel)) {
					parent = parent.getParent();
				}
				if (textBox.getText() == "") {
					if (label == null) {
						textBox.getElement().setAttribute("style","border-color: rgb(200,0,0) !important;");
						if (!tabLayout) {
							widgetIndex = stackPanel.getWidgetIndex(parent);
							stackPanel.getHeaderWidget(widgetIndex).asWidget().setStylePrimaryName("textrequired");
						} else {
							widgetIndex = tabPanel.getWidgetIndex(parent);
							tabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
						}
					} else if (label.getText() == "Spatial resolution:") {} 
					else {
						notCompleted++;
						label.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
						textBox.getElement().setAttribute("style","border-color: rgb(200,0,0) !important;");
						if (!tabLayout) {
							widgetIndex = stackPanel.getWidgetIndex(parent);
							stackPanel.getHeaderWidget(widgetIndex).asWidget().setStylePrimaryName("textrequired");
						} else {
							widgetIndex = tabPanel.getWidgetIndex(parent);
							tabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
						}
					}
				} else {
					if (!Utilities.runCorrect(textBox)) {
						notCompleted++;
						label.getElement().setAttribute("style","color: rgb(0,0,200) !important;");
						if (!tabLayout) {
							widgetIndex = stackPanel.getWidgetIndex(parent);
							stackPanel.getHeaderWidget(widgetIndex).asWidget().setStylePrimaryName("textuncorrect");
						} else {
							widgetIndex = tabPanel.getWidgetIndex(parent);
							tabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: rgb(0,0,200) !important;");
						}
					}
				}
			} catch (Exception ex) {rootLogger.log(Level.WARNING, "the widget has no parent: " + ex.getMessage());}
		}
		List<CheckBox> allClaCheck = $("*", clScroll).widgets(CheckBox.class);
		for (int i = 0; i < allClaCheck.size(); i++) {
			if  (allClaCheck.get(i).getValue() == true) {
				allClaCheckNumber++;
			}
		}
		if (allClaCheckNumber == 0) {
			notCompleted++;
			if (!tabLayout) {
				stackPanel.getHeaderWidget(1).asWidget().setStylePrimaryName("textrequired");
			} else {
				tabPanel.getTabWidget(1).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
			}
		}
		List<CheckBox> allKeyCheck = $("*", kwScroll).widgets(CheckBox.class);
		for (int i = 0; i < allKeyCheck.size(); i++) {
			if (allKeyCheck.get(i).getValue() == true) {
				allKeyCheckNumber++;
			}
		}
		if (allKeyCheckNumber == 0) {
			notCompleted++;
			if (!tabLayout) {
				stackPanel.getHeaderWidget(2).asWidget().setStylePrimaryName("textrequired");
			} else {
				tabPanel.getTabWidget(2).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
			}
		}
		if (airAircraftLst.getSelectedIndex() == 0) {
			notCompleted++;
			if (!tabLayout) {
				stackPanel.getHeaderWidget(3).asWidget().setStylePrimaryName("textrequired");
			} else {
				tabPanel.getTabWidget(3).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
			}
			airAircraftLab.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
			airAircraftLst.getElement().setAttribute("style","border-color: rgb(200,0,0) !important;");
		} else if (airAircraftLst.getSelectedIndex() == 1) {
			int manufacturerSize = Emc_eufar.airManufacturerLab.getElement().getClientWidth();
			int typeSize = Emc_eufar.airTypeLab.getElement().getClientWidth();
			int operatorSize = Emc_eufar.airOperatorLab.getElement().getClientWidth();
			int countrySize = Emc_eufar.airCountryLab.getElement().getClientWidth();
			int registrationSize = Emc_eufar.airRegistrationLab.getElement().getClientWidth();
			if (Emc_eufar.airManufacturerBox.getText() == "") {
				Emc_eufar.airManufacturerBox.getElement().setAttribute("style", "margin-left: " + Integer.toString(registrationSize - 
						manufacturerSize + 20) + "px !important; border-color: rgb(200,0,0) !important;");
			}
			if (Emc_eufar.airTypeBox.getText() == "") {
			Emc_eufar.airTypeBox.getElement().setAttribute("style", "margin-left: " + Integer.toString(registrationSize - typeSize + 20) + "px "
					+ "!important; border-color: rgb(200,0,0) !important;");
			}
			if (Emc_eufar.airOperatorBox.getText() == "") {
			Emc_eufar.airOperatorBox.getElement().setAttribute("style", "margin-left: " + Integer.toString(registrationSize - operatorSize + 20) 
						+ "px !important; height: 15px important!; !important; border-color: rgb(200,0,0) !important;");
			}
			if (Emc_eufar.airCountryLst.getSelectedItemText() == "Make a choice...") {
				Emc_eufar.airCountryLst.getElement().setAttribute("style", "margin-left: " + Integer.toString(registrationSize - countrySize + 
						20) + "px !important; border-color: rgb(200,0,0) !important;");
				Emc_eufar.airCountryLab.getElement().setAttribute("style", "color: rgb(200,0,0) !important;");
			}
			if (Emc_eufar.airRegistrationBox.getText() == "") {
				Emc_eufar.airRegistrationBox.getElement().setAttribute("style", "margin-left: 20px !important; border-color: rgb(200,0,0) "
						+ "!important;");
			}
		}
		if (instrumentTabList.size() == 0) {
			notCompleted++;
			if (!tabLayout) {
				stackPanel.getHeaderWidget(3).asWidget().setStylePrimaryName("textrequired");
			} else {
				tabPanel.getTabWidget(3).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
			}
			airInstrumentLab.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
			airInstrumentLst.getElement().setAttribute("style","border-color: rgb(200,0,0) !important;");
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
			if (!tabLayout) {
				stackPanel.getHeaderWidget(4).asWidget().setStylePrimaryName("textrequired");
			} else {
				tabPanel.getTabWidget(4).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
			}
		}
		if (((CheckBox) insituRad.getWidget(0)).getValue() == false && ((CheckBox) imageRad.getWidget(0)).getValue() == false) {
			notCompleted++;
			if (!tabLayout) {
				stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
			} else {
				tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
			}
		} else if (((CheckBox) insituRad.getWidget(0)).getValue() == true && ((CheckBox) imageRad.getWidget(0)).getValue() == false) {
			if (((CheckBox) insituChk04.getWidget(0)).getValue() == false && ((CheckBox) insituChk05.getWidget(0)).getValue() == false && 
					((CheckBox) insituChk06.getWidget(0)).getValue() == false && ((CheckBox) insituChk07.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				insituOutLab.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			} else if (((CheckBox) insituChk07.getWidget(0)).getValue() == true && insituOtherBox.getText() == "") {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				insituOutLab.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				insituOtherBox.getElement().setAttribute("style","border-color: rgb(200,0,0) !important; margin-left: 24px !important; "
						+ "margin-top: -5px !important; margin-bottom: -0px !important;");
				notCompleted++;
			}
			if (((CheckBox) insituChk01Y.getWidget(0)).getValue() == false && ((CheckBox) insituChk01N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				insituGeoLab.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
		} else if (((CheckBox) insituRad.getWidget(0)).getValue() == false && ((CheckBox) imageRad.getWidget(0)).getValue() == true) {
			if (imageLevLst.getSelectedValue() == "Make a choice...") {
				imageLevLab.getElement().setAttribute("style","color: rgb(200,0,0) !important; font-family: DroidSansFallback "
						+ "!important;");
				imageLevLst.getElement().setAttribute("style","border-color: rgb(200,0,0) !important;");
			}
			if (((CheckBox) imageChk10Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk10N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				imageDCLab.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk11Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk11N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				imageIntPixel.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk12Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk12N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				imageBadPixel.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk13Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk13N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				imageSatPixel.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk14Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk14N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				imageAffPixel.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk15Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk15N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				imagePosInfo.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk16Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk16N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				imageAttInfo.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk17Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk17N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				imageSyncProblem.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk18Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk18N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				imageIntGeocoding.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk19Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk19N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				imageAtmCorrection.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk20Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk20N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				imageCloudMask.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk21Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk21N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				imageShadMask.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk22Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk22N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				imageHazeMask.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk23Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk23N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				imageRouMeasure.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk24Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk24N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				imageIllAngle.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk25Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk25N.getWidget(0)).getValue() == false) {
				if (!Emc_eufar.tabLayout) {
					Emc_eufar.stackPanel.getHeaderWidget(6).asWidget().setStylePrimaryName("textrequired");
				} else {
					Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				}
				imageBRDFGeometry.getElement().setAttribute("style","color: rgb(200,0,0) !important;");
				notCompleted++;
			}
		}

		rootLogger.log(Level.INFO, "Check of all fields finished.");
		if (notCompleted > 0) {
			PopupMessages.notcompletePanel(string);
		} else {
			PopupMessages.saveFile(string);
		}
	}
		
	
	// capture all uncaught exceptions
	public Throwable unwrap(Throwable e) {
		if(e instanceof UmbrellaException) {
			UmbrellaException ue = (UmbrellaException) e;
			if(ue.getCauses().size() == 1) {
				return unwrap(ue.getCauses().iterator().next());
			}
		}
		return e;
	}
}
