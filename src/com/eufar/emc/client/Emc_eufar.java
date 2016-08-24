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
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
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
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
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
	private HashMap<TextBoxBase, String> correctField = Resources.correctField();
	public static HashMap<HorizontalPanel, Label> requiredCheckbox = Resources.requiredCheck();
	private HashMap<DateBox, Label> correctDate = Resources.correctDate();
	private String emcVersion = new String("v1.2.1 (2016-08-24)");
	private String gwtVersion = new String("2.7.0");
	private String eclipseVersion = new String("4.6");
	private String javaVersion = new String("1.7.0.79");
	private String inspireVersion = new String("v1.3");
	public static Boolean isModified = new Boolean(false);
	public static String titleString = new String("EUFAR Metadata Creator");
	public static String emcPath = new String("");
	

	// Main window items initialization
	public static MenuBar mainMenu = new MenuBar();
	private MenuBar emcMenu = new MenuBar(true);
	private MenuBar aboutMenu = new MenuBar(true);
	private MenuBar fileMenu = new MenuBar(true);
	private String imageEMC = "<img src='icons/menu_icon.png' height='16' width='20'/>";
	public static DockLayoutPanel subDockPanel = new DockLayoutPanel(Unit.PX);
	public static StackLayoutPanel stackPanel = new StackLayoutPanel(Unit.PX);
	public static ScrollableTabLayoutPanel tabPanel = new ScrollableTabLayoutPanel(42, Unit.PX);
	public static String myFileName = new String("");
	private VerticalPanel verticalPanel101 = new VerticalPanel();
	private HorizontalPanel horizontalPanel90 = new HorizontalPanel();
	private int clouds_heading_width = 3840;
	private int clouds_heading_height = 322;
	private int screen_width = Utilities.getScreenWidth();
	private int menu_width = screen_width / 10;
	private float ratio = (float) clouds_heading_height / clouds_heading_width;
	private float new_clouds_heading_height = screen_width * ratio;
	
	
	// Header items
	private HorizontalPanel horizontalPanel87 = new HorizontalPanel();
	private Label hdTitleLab = new Label("European Facility For Airborne Research");

	
	// Footer items
	private HorizontalPanel horizontalPanel112 = new HorizontalPanel();
	private HorizontalPanelOver horizontalPanel113 = new HorizontalPanelOver();
	private HorizontalPanelOver horizontalPanel114 = new HorizontalPanelOver();
	private HorizontalPanelOver horizontalPanel115 = new HorizontalPanelOver();
	private InlineHTML centerHtml = new InlineHTML("<i class='fa fa-home fa-lg' aria-hidden='true' style='"
			+ "color:#616265;"
			+ "padding: 8px 8px 8px 8px;"
			+ "'></i>");
	private InlineHTML centerHtml2 = new InlineHTML("<i class='fa fa-home fa-lg' aria-hidden='true' style='"
			+ "color:#F9A12A;"
			+ "padding: 8px 8px 8px 8px;"
			+ "'></i>");
	private InlineHTML leftHtml = new InlineHTML("<i class='fa fa-arrow-left fa-lg' aria-hidden='true' style='"
			+ "color:#616265;"
			+ "padding: 8px 8px 8px 8px;"
			+ "'></i>");
	private InlineHTML leftHtml2 = new InlineHTML("<i class='fa fa-arrow-left fa-lg' aria-hidden='true' style='"
			+ "color:#F9A12A;"
			+ "padding: 8px 8px 8px 8px;"
			+ "'></i>");
	private InlineHTML rightHtml = new InlineHTML("<i class='fa fa-arrow-right fa-lg' aria-hidden='true' style='"
			+ "color:#616265;"
			+ "padding: 8px 8px 8px 8px;"
			+ "'></i>");
	private InlineHTML rightHtml2 = new InlineHTML("<i class='fa fa-arrow-right fa-lg' aria-hidden='true' style='"
			+ "color:#F9A12A;"
			+ "padding: 8px 8px 8px 8px;"
			+ "'></i>");
	private Label centerLabel = new Label("BACK TO HOME");
	private Label leftLabel = new Label("PREVIOUS");
	private Label rightLabel = new Label("NEXT");
	

	// Identification items
	private HorizontalPanel horizontalPanel01 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel02 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel03 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel04 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel05 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel06 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel67 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel68 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel91 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel92 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel93 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel94 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel95 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel96 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel99 = new HorizontalPanel();
	public static VerticalPanel verticalPanel21 = new VerticalPanel();
	private Label identStarLab01 = new Label("*");
	private Label identStarLab02 = new Label("*");
	private Label identStarLab03 = new Label("*");
	private Label identStarLab04 = new Label("*");
	private Label identStarLab05 = new Label("*");
	private Label identStarLab06 = new Label("*");
	private Label identMainLab = new Label("EUFAR METADATA CREATOR");
	private Label identMainLab2 = new Label("EUFAR METADATA CREATOR");
	private Label identPathLab = new Label(">");
	private Label identPathLab2 = new Label("Identification");
	public static Label identTitleLab = new Label("Project title");
	public static Label identAbstractLab = new Label("Project abstract");
	private Label identTypeLab = new Label("Resource type");
	public static Label identLocatorLab = new Label("Resource locator");
	public static Label identIdentifierLab = new Label("Project acronym");
	private Label identLanguageLab = new Label("Resource language");
	public static TextArea identAbstractAre = new TextArea();
	public static TextBox identTitleBox = new TextBox();
	public static TextBox identLocatorBox = new TextBox();
	public static TextBox identIdentifierBox = new TextBox();
	public static ListBox identLanguageLst = new ListBox();
	public static ListBox identTypeLst = new ListBox();
	public static ArrayList<String> languageList = Resources.languageList();
	public static ArrayList<String> typeList = Resources.typeList();
	public static FlexTable idGrid = new FlexTable();
	private SimplePanel idTitleInfo = Elements.addInfoButton("idTitle");
	private SimplePanel idAbstractInfo = Elements.addInfoButton("idAbstract");
	private SimplePanel idTypeInfo = Elements.addInfoButton("idType");
	private SimplePanel idLocatorInfo = Elements.addInfoButton("idLocator");
	private SimplePanel idIdentifierInfo = Elements.addInfoButton("idIdentifier");
	private SimplePanel idLanguageInfo = Elements.addInfoButton("idLanguage");
	public static ScrollPanel idScroll = new ScrollPanel(verticalPanel21);


	// Classification items
	public static VerticalPanel verticalPanel01 = new VerticalPanel();
	public static VerticalPanel verticalPanel02 = new VerticalPanel();
	public static VerticalPanel verticalPanel22 = new VerticalPanel();
	public static HorizontalPanel horizontalPanel07 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel69 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel70 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel97 = new HorizontalPanel();
	private Label classStarLab07 = new Label("*");
	private Label classMainLab = new Label("EUFAR METADATA CREATOR");
	private Label classMainLab2 = new Label("EUFAR METADATA CREATOR");
	private Label classPathLab = new Label(">");
	private Label classPathLab2 = new Label("Classification");
	private SimplePanel classCategoriesInfo = Elements.addInfoButton("Categories");
	public static Label classCategoriesLab = new Label("Topic Categories");
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
	public static ScrollPanel clScroll = new ScrollPanel(verticalPanel22);


	// Keywords items
	private VerticalPanel verticalPanel03 = new VerticalPanel();
	private VerticalPanel verticalPanel04 = new VerticalPanel();
	private VerticalPanel verticalPanel05 = new VerticalPanel();
	private VerticalPanel verticalPanel06 = new VerticalPanel();
	public static VerticalPanel verticalPanel23 = new VerticalPanel();
	private HorizontalPanel horizontalPanel71 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel72 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel08 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel98 = new HorizontalPanel();
	private Label keyStarLab01 = new Label("*");
	private Label keyMainLab2 = new Label("EUFAR METADATA CREATOR");
	private Label keyMainLab = new Label("EUFAR METADATA CREATOR");
	private Label keyPathLab = new Label(">");
	private Label keyPathLab2 = new Label("Keywords");
	private SimplePanel keyInfoButton = Elements.addInfoButton("Keywords");
	public static Label keyKeywordsLab = new Label("Keywords");
	public static Label keyAgricultureLab = new Label("Agriculture:");
	private Label keyAtmosphereLab = new Label("Atmosphere:");
	private Label keyBiosphereLab = new Label("Biosphere:");
	private Label keyCryosphereLab = new Label("Cryosphere:");
	private Label keySurfaceLab = new Label("Land Surface:");
	public static Label keyOceanLab = new Label("Ocean:");
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
	public static ScrollPanel kwScroll = new ScrollPanel(verticalPanel23);


	// Aircraft and instruments items
	private VerticalPanel verticalPanel08 = new VerticalPanel();
	private VerticalPanel verticalPanel09 = new VerticalPanel();
	public static VerticalPanel verticalPanel10 = new VerticalPanel();
	public static VerticalPanel verticalPanel20 = new VerticalPanel();
	public static VerticalPanel verticalPanel24 = new VerticalPanel();
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
	private HorizontalPanel horizontalPanel73 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel74 = new HorizontalPanel();
	private Label aiStarLab01 = new Label("*");
	private Label aiStarLab02 = new Label("*");
	public static Label aiStarLab03 = new Label("*");
	public static Label aiStarLab04 = new Label("*");
	public static Label aiStarLab05 = new Label("*");
	public static Label aiStarLab06 = new Label("*");
	public static Label aiStarLab07 = new Label("*");
	private Label aiMainLab2 = new Label("EUFAR METADATA CREATOR");
	private Label aiMainLab = new Label("EUFAR METADATA CREATOR");
	private Label aiPathLab = new Label(">");
	private Label aiPathLab2 = new Label("Aircraft & Instruments");
	public static FlexTable airInstrumentTable = new FlexTable();
	public static FlexTable airAircraftTable = new FlexTable();
	private SimplePanel airPlatformInfo = Elements.addInfoButton("aiAircraft");
	private SimplePanel airInstrumentInfo = Elements.addInfoButton("aiInstrument");
	private SimplePanel airPlusButton01 = Elements.plusButton("aircraft");
	private SimplePanel airPlusButton02 = Elements.plusButton("airInstrument");
	public static Label airAircraftLab = new Label("Aircraft");
	public static Label airManufacturerLab = new Label("Manufacturer");
	public static Label airManufacturerInfo = new Label("");
	public static Label airTypeLab = new Label("Aircraft type");
	public static Label airTypeInfo = new Label("");
	public static Label airOperatorLab = new Label("Operator");
	public static Label airOperatorInfo = new Label("");
	public static Label airCountryLab = new Label("Country");
	public static Label airCountryInfo = new Label("");
	public static Label airRegistrationLab = new Label("Registration number");
	public static Label airRegistrationInfo = new Label("");
	public static Label airCopyrightInfo = new Label("EUFAR");
	public static Label airInstNameLab = new Label("Name");
	public static Label airInstManufacturerLab = new Label("Manufacturer");
	public static Label airInstrumentLab = new Label("Instrument");
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
	public static ArrayList<String> aircraftTabList = new ArrayList<String>();
	public static ArrayList<String> operatorTabList = new ArrayList<String>();
	public static ArrayList<String> manufacturairTabList = new ArrayList<String>();
	public static ArrayList<String> countryTabList = new ArrayList<String>();
	public static ArrayList<String> identificationTabList = new ArrayList<String>();
	public static ArrayList<String> instrumentTabList = new ArrayList<String>();
	public static ArrayList<String> manufacturerTabList = new ArrayList<String>();
	public static Image airAircraftImg = new Image("eufar_aircraft/logo_eufar_emc_v2.png");
	public static String[][] airAircraftInfo = Resources.aircraftInfo();
	public static ScrollPanel aiScroll = new ScrollPanel(verticalPanel24);


	// Geographic information items
	public static VerticalPanel verticalPanel11 = new VerticalPanel();
	public static VerticalPanel verticalPanel25 = new VerticalPanel();
	private HorizontalPanel horizontalPanel13 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel16 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel17 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel75 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel76 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel100 = new HorizontalPanel();
	private Label geoStarLab01 = new Label("*");
	private Label geoStarLab02 = new Label("*");
	private Label geoMainLab2 = new Label("EUFAR METADATA CREATOR");
	private Label geoMainLab = new Label("EUFAR METADATA CREATOR");
	private Label geoPathLab = new Label(">");
	private Label geoPathLab2 = new Label("Geographic Information");
	private SimplePanel geoLocationInfo = Elements.addInfoButton("giLocation");
	private SimplePanel geoCoordInfo = Elements.addInfoButton("giBox"); 
	private SimplePanel geoUnitInfo = Elements.addInfoButton("giUnit");
	public static Label geoLocationLab = new Label("Location");
	public static Label geoBoundingLab = new Label("Geographic bounding box");
	public static Label geoResolutionLab = new Label("Spatial resolution");
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
	public static ScrollPanel giScroll = new ScrollPanel(verticalPanel25);


	// Temporal Reference items
	public static VerticalPanel verticalPanel13 = new VerticalPanel();
	public static VerticalPanel verticalPanel26 = new VerticalPanel();
	private HorizontalPanel horizontalPanel21 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel77 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel78 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel103 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel104 = new HorizontalPanel();
	private Label refStarLab01 = new Label("*");
	private Label refStarLab02 = new Label("*");
	private Label refStarLab03 = new Label("*");
	private Label refMainLab2 = new Label("EUFAR METADATA CREATOR");
	private Label refMainLab = new Label("EUFAR METADATA CREATOR");
	private Label refPathLab = new Label(">");
	private Label refPathLab2 = new Label("Temporal Reference");
	private SimplePanel refRevisionInfo = Elements.addInfoButton("trRevision");
	private SimplePanel refCreationInfo = Elements.addInfoButton("trCreation");
	private SimplePanel refPlusButton = Elements.plusButton("tempRef");
	private SimplePanel refPlusInfo = Elements.addInfoButton("trPhase");
	public static Label refPublicationLab = new Label("Date of publication");
	public static Label refCreationLab = new Label("Date of creation");
	public static Label refRevisionLab = new Label("Date of last revision");
	public static Label refExtentLab = new Label("Temporal extent");
	public static Label refPhaseLab = new Label("Phase 1");
	public static DateBox refPublicationDat = new DateBox();
	public static DateBox refRevisionDat = new DateBox();
	public static DateBox refCreationDat = new DateBox();
	public static DateBox refStartDat = new DateBox();
	public static DateBox refEndDat = new DateBox();
	public static ArrayList<DateBox> refStartLst = new ArrayList<DateBox>();
	public static ArrayList<DateBox> refEndLst = new ArrayList<DateBox>();
	public static FlexTable refDateTab = new FlexTable();
	public static FlexTable refPhaseTab = new FlexTable();
	public static Image refDelImage = new Image("icons/del_icon_v2.png");
	public static SimplePanel refDelButton = new SimplePanel(refDelImage);
	public static ScrollPanel trScroll = new ScrollPanel(verticalPanel26);

	
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
	private HorizontalPanel horizontalPanel79 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel80 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel116 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel117 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel118 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel119 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel120 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel121 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel122 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel123 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel124 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel125 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel126 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel127 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel128 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel129 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel130 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel131 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel132 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel133 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel134 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel135 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel136 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel137 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel138 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel139 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel140 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel141 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel142 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel143 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel144 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel145 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel146 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel147 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel148 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel149 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel150 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel151 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel152 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel153 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel154 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel155 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel156 = new HorizontalPanel();
	public static VerticalPanel verticalPanel27 = new VerticalPanel();
	private Label qvMainLab2 = new Label("EUFAR METADATA CREATOR");
	private Label qvMainLab = new Label("EUFAR METADATA CREATOR");
	private Label qvPathLab = new Label(">");
	private Label qvPathLab2 = new Label("Quality and Validity");
	public static HorizontalPanel imageRad = Elements.radioButton("radioGrp1","Earth observation/Remote sensing data");
	public static HorizontalPanel insituRad  = Elements.radioButton("radioGrp1","Atmospheric/In-situ data");
	public static HorizontalPanel insituChk01Y = Elements.radioButton("radioGrp19","Yes");
	public static HorizontalPanel insituChk01N = Elements.radioButton("radioGrp19","No");
	public static HorizontalPanel insituChk04 = Elements.checkBox("NetCDF");
	public static HorizontalPanel insituChk05 = Elements.checkBox("HDF");
	public static HorizontalPanel insituChk06 = Elements.checkBox("NASA/Ames");
	public static HorizontalPanel insituChk07 = Elements.checkBox("Other");
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
	public static SimplePanel qvInfoButton01 = Elements.addInfoButton("qvLineage1");
	public static SimplePanel qvInfoButton02 = Elements.addInfoButton("qvLineage2");
	public static SimplePanel qvInfoButton03 = Elements.addInfoButton("qvLineage3");
	public static SimplePanel qvInfoButton04 = Elements.addInfoButton("qvLineage4");
	public static SimplePanel qvInfoButton05 = Elements.addInfoButton("qvLineage5");
	public static SimplePanel qvInfoButton06 = Elements.addInfoButton("qvLineage6");
	public static SimplePanel qvInfoButton07 = Elements.addInfoButton("qvLineage7");
	public static SimplePanel qvInfoButton08 = Elements.addInfoButton("qvLineage8");
	public static SimplePanel qvInfoButton09 = Elements.addInfoButton("qvLineage9");
	public static SimplePanel qvInfoButton10 = Elements.addInfoButton("qvLineage10");
	public static SimplePanel qvInfoButton11 = Elements.addInfoButton("qvLineage11");
	public static SimplePanel qvInfoButton12 = Elements.addInfoButton("qvLineage12");
	public static SimplePanel qvInfoButton13 = Elements.addInfoButton("qvLineage13");
	public static SimplePanel qvInfoButton14 = Elements.addInfoButton("qvLineage14");
	public static SimplePanel qvInfoButton15 = Elements.addInfoButton("qvLineage15");
	public static SimplePanel qvInfoButton16 = Elements.addInfoButton("qvLineage16");
	public static SimplePanel qvInfoButton17 = Elements.addInfoButton("qvLineage17");
	public static SimplePanel qvInfoButton18 = Elements.addInfoButton("qvLineage18");
	private Label insituStarLab01 = new Label("*");
	public static Label insituStarLab02 = new Label("*");
	public static Label insituStarLab03 = new Label("*");
	public static Label insituStarLab04 = new Label("*");
	public static Label insituStarLab05 = new Label("*");
	public static Label insituStarLab06 = new Label("*");
	public static Label insituStarLab07 = new Label("*");
	public static Label insituStarLab08 = new Label("*");
	private Label imageStarLab01 = new Label("*");
	public static Label imageStarLab02 = new Label("*");
	public static Label imageStarLab03 = new Label("*");
	public static Label imageStarLab04 = new Label("*");
	public static Label imageStarLab05 = new Label("*");
	public static Label imageStarLab06 = new Label("*");
	public static Label imageStarLab07 = new Label("*");
	public static Label imageStarLab08 = new Label("*");
	public static Label imageStarLab09 = new Label("*");
	public static Label imageStarLab10 = new Label("*");
	public static Label imageStarLab11 = new Label("*");
	public static Label imageStarLab12 = new Label("*");
	public static Label imageStarLab13 = new Label("*");
	public static Label imageStarLab14 = new Label("*");
	public static Label imageStarLab15 = new Label("*");
	public static Label imageStarLab16 = new Label("*");
	public static Label imageStarLab17 = new Label("*");
	public static Label imageStarLab18 = new Label("*");
	public static Label imageStarLab19 = new Label("*");
	public static Label imageStarLab20 = new Label("*");
	public static Label imageStarLab21 = new Label("*");
	public static Label imageStarLab22 = new Label("*");
	public static Label imageStarLab23 = new Label("*");
	public static Label imageStarLab24 = new Label("*");
	public static Label imageStarLab25 = new Label("*");
	public static Label imageStarLab26 = new Label("*");
	public static Label imageStarLab27 = new Label("*");
	public static Label insituCalLab = new Label("Operator's standard calibration procedures applied to raw digital data");
	public static Label insituGeoLab = new Label("Conversion to geophysical units");
	public static Label insituOutLab = new Label("Output in standardized format");
	public static Label insituFlaLab = new Label("Quality-control flagging applied to individual data points");
	public static Label insituAssLab = new Label("Assumption");
	public static Label insituLinkLab = new Label("Link to the procedure's description");
	public static Label insituConstLab = new Label("Source of calibration constants");
	public static Label insituMatLab = new Label("Source of calibration materials");
	public static Label insituFlagLab = new Label("Description or link to the operator's standard procedure");
	public static Label imageCalLab = new Label("Calibration information");
	public static Label imageAcqLab = new Label("Acquisition information");
	public static Label imageProLab = new Label("Processing information");
	public static Label imageLayLab = new Label("Data Quality Layers");
	public static Label imageNameLab = new Label("Name of calibration laboratory");
	public static Label imageRadLab = new Label("Date of radiometric calibration");
	public static Label imageSpeLab = new Label("Date of spectral calibration");
	public static Label imageBanLab = new Label("Number of spectral bands (spectral mode)");
	public static Label imageDirLab = new Label("Overall heading / fligh direction (dd)");
	public static Label imageAltLab = new Label("Overall altitude / average height ASL (m)");
	public static Label imageZenLab = new Label("Solar zenith (dd)");
	public static Label imageAziLab = new Label("Solar azimuth (dd)");
	public static Label imageAnoLab = new Label("Report anomalies in data acquisition");
	public static Label imageLevLab = new Label("Processing level");
	public static Label imageDCLab = new Label("Dark current (DC) correction ?");
	public static Label imageCalcorrLab = new Label("Sensor calibration and system correction");
	public static Label imageErrLab = new Label("Image data artefacts and processing errors");
	public static Label imageErrcorrLab = new Label("GPS/IMU related errors, geometric correction");
	public static Label imageCorrconLab = new Label("Atmospheric correction and atmospheric conditions");
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
	public static TextArea insituLinkBox = new TextArea();
	public static TextArea insituConstBox = new TextArea();
	public static TextArea insituMatBox = new TextArea();
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
	public static FlexTable imageCalAcqProTab = new FlexTable();
	public static FlexTable imageQuaLayTab = new FlexTable();
	public static FlexTable imageCalcorrTab = new FlexTable();
	public static FlexTable imageErrTab = new FlexTable();
	public static FlexTable imageErrcorrTab = new FlexTable();
	public static FlexTable imageCorrconTab = new FlexTable();
	public static Image insituImage = new Image("icons/fwd_arrow_small.png");
	public static TextArea insituFlagAre = new TextArea();
	public static TextArea insituAssumptionAre = new TextArea();
	public static FlexTable insituCalTab = new FlexTable();
	public static FlexTable insituFlagTab = new FlexTable();
	public static ScrollPanel qvScroll = new ScrollPanel(verticalPanel27);
	

	// Access and Use Constraints items
	public static VerticalPanel verticalPanel15 = new VerticalPanel();
	public static VerticalPanel verticalPanel28 = new VerticalPanel();
	private HorizontalPanel horizontalPanel81 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel82 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel101 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel102 = new HorizontalPanel();
	private Label useStarLab01 = new Label("*");
	private Label useStarLab02 = new Label("*");
	private Label useMainLab2 = new Label("EUFAR METADATA CREATOR");
	private Label useMainLab = new Label("EUFAR METADATA CREATOR");
	private Label usePathLab = new Label(">");
	private Label usePathLab2 = new Label("Access and Use Constraints");
	public static Label useConditionsLab = new Label("Conditions applying to access and use");
	public static Label useLimitationsLab = new Label("Limitations on public access");
	public static TextArea useConditionsBox = new TextArea();
	public static TextArea useLimitationsBox = new TextArea();
	private FlexTable useConditionsTab = new FlexTable();
	public static FlexTable useConditionsAddTab = new FlexTable();
	private FlexTable useLimitationsTab = new FlexTable();
	public static FlexTable useLimitationsAddTab = new FlexTable();
	public static ArrayList<TextArea> useConditionsLst = new ArrayList<TextArea>();
	public static ArrayList<TextArea> useLimitationsLst = new ArrayList<TextArea>();
	public static Image useDelImage1 = new Image("icons/del_icon_v2.png");
	public static Image useDelImage2 = new Image("icons/del_icon_v2.png");
	public static SimplePanel auDelButton1 = new SimplePanel(useDelImage1);
	public static SimplePanel auDelButton2 = new SimplePanel(useDelImage2);
	public static SimplePanel usePlusButton2 = Elements.plusButton("limitation");
	public static SimplePanel usePlusButton1 = Elements.plusButton("condition");
	public static ScrollPanel auScroll = new ScrollPanel(verticalPanel28);


	// Responsible Organisations items
	public static HorizontalPanel horizontalPanel27 = new HorizontalPanel();
	public static VerticalPanel verticalPanel29 = new VerticalPanel();
	public static VerticalPanel verticalPanel35 = new VerticalPanel();
	private HorizontalPanel horizontalPanel83 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel84 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel105 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel106 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel107 = new HorizontalPanel();
	private Label orgStarLab01 = new Label("*");
	private Label orgStarLab02 = new Label("*");
	private Label orgStarLab03 = new Label("*");
	private Label orgMainLab2 = new Label("EUFAR METADATA CREATOR");
	private Label orgMainLab = new Label("EUFAR METADATA CREATOR");
	private Label orgPathLab = new Label(">");
	private Label orgPathLab2 = new Label("Responsible Organisations");
	private SimplePanel roPartyInfo = Elements.addInfoButton("roParty");
	private SimplePanel roEmailInfo = Elements.addInfoButton("roEmail");
	private SimplePanel roRoleInfo = Elements.addInfoButton("roRole");
	private SimplePanel orgPlusButton = Elements.plusButton("respOrg");
	public static Label orgPartyLab = new Label("Responsible party");
	public static Label orgEmailLab = new Label("Responsible party e-mail");
	private Label orgRoleLab = new Label("Responsible party role");
	public static TextBox orgPartyBox = new TextBox();
	public static TextBox orgEmailBox = new TextBox();
	public static ListBox orgRoleLst = new ListBox();
	public static ArrayList<String> roleList = Resources.roleList();
	public static ArrayList<TextBox> orgPartyLst = new ArrayList<TextBox>();
	public static ArrayList<ListBox> orgRole2Lst = new ArrayList<ListBox>();
	public static ArrayList<TextBox> orgEmailLst = new ArrayList<TextBox>();
	public static FlexTable orgPartyTab = new FlexTable();
	public static FlexTable orgAddTab = new FlexTable();
	public static Image orgDelImage = new Image("icons/del_icon_v2.png");
	public static SimplePanel orgDelButton = new SimplePanel(orgDelImage);
	public static ScrollPanel roScroll = new ScrollPanel(verticalPanel29);


	// Metadata on Metadata items
	public static VerticalPanel verticalPanel16 = new VerticalPanel();
	public static VerticalPanel verticalPanel30 = new VerticalPanel();
	private HorizontalPanel horizontalPanel28 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel29 = new HorizontalPanel();
	public static HorizontalPanel horizontalPanel66 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel85 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel86 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel108 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel109 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel110 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel111 = new HorizontalPanel();
	private Label metStarLab01 = new Label("*");
	private Label metStarLab02 = new Label("*");
	private Label metStarLab03 = new Label("*");
	private Label metStarLab04 = new Label("*");
	private Label metMainLab2 = new Label("EUFAR METADATA CREATOR");
	private Label metMainLab = new Label("EUFAR METADATA CREATOR");
	private Label metPathLab = new Label(">");
	private Label metPathLab2 = new Label("Metadata on Metadata");
	private SimplePanel metDateInfo = Elements.addInfoButton("mmDate");
	private SimplePanel metLanguageInfo = Elements.addInfoButton("mmLanguage");
	private SimplePanel metPlusButton = Elements.plusButton("metadata");
	private SimplePanel metPartyInfo = Elements.addInfoButton("mmParty");
	private Label metDateLab = new Label("Metadata date");
	private Label metLanguageLab = new Label("Metadata language");
	public static Label metContactLab = new Label("EMC xml file has been created by");
	public static Label metNameLab = new Label("Name");
	public static Label metEmailLab = new Label("E-mail");
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
	public static Image metDelImage = new Image("icons/del_icon_v2.png");
	public static SimplePanel mmDelButton = new SimplePanel(metDelImage);
	public static ScrollPanel mmScroll = new ScrollPanel(verticalPanel30);
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
		rootLogger.log(Level.INFO, "***************************************************************");
		rootLogger.log(Level.INFO, "EMC has started on: " + Navigator.getUserAgent());
		if (GWT.getHostPageBaseURL() != "http://127.0.0.1:8888/") {
			emcPath = GWT.getHostPageBaseURL();
		}
		rootLogger.log(Level.INFO, "PATH: " + emcPath);

		
		// Commands in the menu bar
		Command aboutWindow = new Command() {
			public void execute() {
				rootLogger.log(Level.INFO, "About ASMM invoked...");
				PopupMessages.aboutEmc(emcVersion, eclipseVersion, gwtVersion, javaVersion, inspireVersion);
			}
		};
		
		Command aboutStandard = new Command() {
			public void execute() {
				rootLogger.log(Level.INFO, "About INSPIRE invoked...");
				PopupMessages.aboutInspire();
			}
		};
		
		Command newFile = new Command() {
			public void execute() {
				newFile();
			}
		};
		
		Command openFile = new Command() {
			public void execute() {
				PopupMessages.openFile();
			}
		};
		
		Command saveFile = new Command() {
			public void execute() {
				runCheck("");
			}
		};
		
		Command launchN7SPPage = new Command() {
			public void execute() {
				Window.open("http://www.eufar.net/cms/standards-and-protocols/", "_blank", "");
			}
		};
		
		Command launchHelpPage = new Command()  {
			public void execute() {
				Window.open("http://www.eufar.net/cms/eufar-metadata-creator-help/", "_blank", "");
			}
		};
		
		Command exitFile = new Command() {
			public void execute() {
				Window.open("http://www.eufar.net", "_self", "");
			}
		};
		
		
		Command displayLog = new Command() {
			public void execute() {
				rootLogger.log(Level.INFO, "Changelog invoked...");
				PopupMessages.changelogPanel();
			}
		};

		
		Command goIdentification = new Command() {
			public void execute() {
				tabPanel.selectTab(0);
			}
		};
		
		
		Command goClassification = new Command() {
			public void execute() {
				tabPanel.selectTab(1);
			}
		};
		
		
		Command goKeywords = new Command() {
			public void execute() {
				tabPanel.selectTab(2);
			}
		};
		
		
		Command goAircraft = new Command() {
			public void execute() {
				tabPanel.selectTab(3);
			}
		};
		
		
		Command goGeographic = new Command() {
			public void execute() {
				tabPanel.selectTab(4);
			}
		};
		
		
		Command goQuality = new Command() {
			public void execute() {
				tabPanel.selectTab(6);
			}
		};
		
		
		Command goTemporal = new Command() {
			public void execute() {
				tabPanel.selectTab(5);
			}
		};
		
		
		Command goAccess = new Command() {
			public void execute() {
				tabPanel.selectTab(7);
			}
		};
		
		
		Command goOrganisation = new Command() {
			public void execute() {
				tabPanel.selectTab(8);
			}
		};
		
		
		Command goMetadata = new Command() {
			public void execute() {
				tabPanel.selectTab(9);
			}
		};
		
		
		// Assemble the header
		horizontalPanel87.add(hdTitleLab);
		horizontalPanel87.setStyleName("headerHorizontalPanel");
		horizontalPanel87.getElement().setAttribute("style", "background-position: " + screen_width/6 + "px;");
		hdTitleLab.setStyleName("headerTextLabel");

		
		// Assemble the footer
		horizontalPanel112.add(horizontalPanel113);
		horizontalPanel112.add(horizontalPanel114);
		horizontalPanel112.add(horizontalPanel115);
		horizontalPanel113.add(leftHtml);
		horizontalPanel113.add(leftLabel);
		horizontalPanel114.add(centerHtml);
		horizontalPanel114.add(centerLabel);
		horizontalPanel115.add(rightHtml);
		horizontalPanel115.add(rightLabel);
		horizontalPanel113.setStyleName("footerLeft");
		horizontalPanel114.setStyleName("footerCenter");
		horizontalPanel115.setStyleName("footerRight");
		horizontalPanel113.getElement().setAttribute("style", "margin-left: " + menu_width + "px !important;");
		horizontalPanel114.getElement().setAttribute("style", "margin-left: 460px !important;");
		horizontalPanel115.getElement().setAttribute("style", "margin-left: 490px !important;");
		leftLabel.setStyleName("footerLeftLabel");
		rightLabel.setStyleName("footerRightLabel");
		centerLabel.setStyleName("footerCenterLabel");
		horizontalPanel113.addMouseOverHandler(new MouseOverHandler() {
            public void onMouseOver(MouseOverEvent event) {
            	leftLabel.getElement().setAttribute("style", "color: #F9A12A;");
            	horizontalPanel113.getElement().setAttribute("style", "cursor: pointer; !important; "
            			+ "margin-left: " + menu_width + "px !important;");
            	horizontalPanel113.remove(0);
            	horizontalPanel113.insert(leftHtml2, 0);
            }
        });
		horizontalPanel113.addMouseOutHandler(new MouseOutHandler() {
            public void onMouseOut(MouseOutEvent event) {
            	leftLabel.getElement().setAttribute("style", "color: #616265;");
            	horizontalPanel113.remove(0);
            	horizontalPanel113.insert(leftHtml, 0);
            }
        });
		horizontalPanel113.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent event) {
		    	navigateTab(-1);
		    }
		});
		horizontalPanel114.addMouseOverHandler(new MouseOverHandler() {
            public void onMouseOver(MouseOverEvent event) {
            	centerLabel.getElement().setAttribute("style", "color: #F9A12A;");
            	horizontalPanel114.getElement().setAttribute("style", "cursor: pointer; margin-left: 460px !important;");
            	horizontalPanel114.remove(0);
            	horizontalPanel114.insert(centerHtml2, 0);
            }
        });
		horizontalPanel114.addMouseOutHandler(new MouseOutHandler() {
            public void onMouseOut(MouseOutEvent event) {
            	centerLabel.getElement().setAttribute("style", "color: #616265;");
            	horizontalPanel114.remove(0);
            	horizontalPanel114.insert(centerHtml, 0);
            }
        });
		horizontalPanel114.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent event) {
		    	tabPanel.selectTab(0);
		    }
		});
		horizontalPanel115.addMouseOverHandler(new MouseOverHandler() {
            public void onMouseOver(MouseOverEvent event) {
            	rightLabel.getElement().setAttribute("style", "color: #F9A12A;");
            	horizontalPanel115.getElement().setAttribute("style", "cursor: pointer; margin-left: 490px !important;");
            	horizontalPanel115.remove(0);
            	horizontalPanel115.insert(rightHtml2, 0);
            }
        });
		horizontalPanel115.addMouseOutHandler(new MouseOutHandler() {
            public void onMouseOut(MouseOutEvent event) {
            	rightLabel.getElement().setAttribute("style", "color: #616265;");
            	horizontalPanel115.remove(0);
            	horizontalPanel115.insert(rightHtml, 0);
            }
        });
		horizontalPanel115.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent event) {
		    	navigateTab(1);
		    	
		    }
		});
		

		// Assemble the Menu panel
		mainMenu.addItem(new MenuItem(imageEMC,true,emcMenu));
		emcMenu.addItem("Identification", goIdentification);
		emcMenu.addItem("Classification", goClassification);
		emcMenu.addItem("Keywords", goKeywords);
		emcMenu.addItem("Aircraft & Instruments", goAircraft);
		emcMenu.addItem("Geographic Information", goGeographic);
		emcMenu.addItem("Temporal Reference", goTemporal);
		emcMenu.addItem("Quality and Validity", goQuality);
		emcMenu.addItem("Access and Use Constraints", goAccess);
		emcMenu.addItem("Responsible Organisations", goOrganisation);
		emcMenu.addItem("Metadata on Metadata", goMetadata);
		emcMenu.addSeparator();
		emcMenu.addItem("File", fileMenu);
		emcMenu.addItem("About", aboutMenu);
		emcMenu.addItem("Help", launchHelpPage);
		aboutMenu.addItem("EUFAR Metadata Creator",aboutWindow);
		aboutMenu.addItem("INSPIRE XML standard",aboutStandard);
		aboutMenu.addItem("EUFAR N7SP",launchN7SPPage);
		aboutMenu.addSeparator();
		aboutMenu.addItem("Changelog",displayLog);
		fileMenu.addItem("New",newFile);
		fileMenu.addItem("Open",openFile);
		fileMenu.addItem("Save",saveFile);
		fileMenu.addItem("Exit",exitFile);
		mainMenu.getElement().setAttribute("style", "padding-left: " + (menu_width - 70) + "px !important;");
		rootLogger.log(Level.INFO, "Menu initialized");
		
		
		// Assemble Identification panel
		horizontalPanel01.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel02.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel03.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel04.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel05.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel06.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel91.add(identTitleLab);
		horizontalPanel91.add(identStarLab01);
		horizontalPanel92.add(identAbstractLab);
		horizontalPanel92.add(identStarLab02);
		horizontalPanel93.add(identTypeLab);
		horizontalPanel93.add(identStarLab03);
		horizontalPanel94.add(identLocatorLab);
		horizontalPanel94.add(identStarLab04);
		horizontalPanel95.add(identIdentifierLab);
		horizontalPanel95.add(identStarLab05);
		horizontalPanel96.add(identLanguageLab);
		horizontalPanel96.add(identStarLab06);
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
		horizontalPanel67.add(identMainLab);
		horizontalPanel67.add(identPathLab);
		horizontalPanel67.add(identPathLab2);
		horizontalPanel68.add(new HTML("<hr  style=\"width:1200px;height:10px;background:#c0c0c0;border:0px;\" />"));
		verticalPanel21.add(identMainLab2);
		verticalPanel21.add(horizontalPanel67);
		verticalPanel21.add(horizontalPanel68);
		verticalPanel21.add(horizontalPanel99);
		horizontalPanel99.add(idGrid);
		idGrid.setWidget(0, 0, horizontalPanel91);
		idGrid.setWidget(0, 1, horizontalPanel01);
		idGrid.setWidget(1, 0, horizontalPanel95);
		idGrid.setWidget(1, 1, horizontalPanel05);
		idGrid.setWidget(2, 0, horizontalPanel92);
		idGrid.setWidget(2, 1, horizontalPanel02);
		idGrid.setWidget(3, 0, horizontalPanel93);
		idGrid.setWidget(3, 1, horizontalPanel03);
		idGrid.setWidget(4, 0, horizontalPanel94);
		idGrid.setWidget(4, 1, horizontalPanel04);
		idGrid.setWidget(5, 0, horizontalPanel96);
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
		horizontalPanel99.setStyleName("identGrid");
		identMainLab.setStyleName("identMainText");
		identMainLab2.setStyleName("identMainText2");
		identStarLab01.setStyleName("identStarLabel");
		identStarLab02.setStyleName("identStarLabel");
		identStarLab03.setStyleName("identStarLabel");
		identStarLab04.setStyleName("identStarLabel");
		identStarLab05.setStyleName("identStarLabel");
		identStarLab06.setStyleName("identStarLabel");
		verticalPanel21.getElement().setAttribute("style", 
				"margin-top: " + (new_clouds_heading_height - 31) + "px !important;");
		idScroll.getElement().setAttribute("style", 
				"background-image: url(icons/eufar_heading_clouds.jpg) !important; "
				+ "background-repeat: no-repeat !important;" 
				+ "background-size: " + screen_width + "px " + new_clouds_heading_height + "px !important;"
				+ "background-position: -" + menu_width + "px 0px !important;"
				+ "overflow: auto !important;");
		identPathLab.setStyleName("identPathText");
		identPathLab2.setStyleName("identPathText2");
		horizontalPanel68.setStyleName("identLine");
		identLanguageLst.setName("identLanguageList");
		identTypeLst.setName("identTypeList");
		identLocatorBox.setText("http://browse.ceda.ac.uk/browse/badc/eufar/docs/00eufararchivecontents.html");
		rootLogger.log(Level.INFO, "Identification panel initialized");


		// Assemble Classification panel
		horizontalPanel69.add(classMainLab);
		horizontalPanel69.add(classPathLab);
		horizontalPanel69.add(classPathLab2);
		horizontalPanel70.add(new HTML("<hr  style=\"width:1200px;height:10px;background:#c0c0c0;border:0px;\" />"));
		verticalPanel22.add(classMainLab2);
		verticalPanel22.add(horizontalPanel69);
		verticalPanel22.add(horizontalPanel70);
		verticalPanel22.add(horizontalPanel07);
		horizontalPanel07.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel07.setSpacing(20);
		horizontalPanel07.add(horizontalPanel97);
		horizontalPanel97.add(classCategoriesLab);
		horizontalPanel97.add(classStarLab07);
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
		horizontalPanel07.setStyleName("classHorizontalPanel");
		classCategoriesInfo.getElement().setAttribute("style", "margin-left: 40px !important;");
		classMainLab.setStyleName("identMainText");
		classMainLab2.setStyleName("identMainText2");
		classPathLab.setStyleName("identPathText");
		classPathLab2.setStyleName("identPathText2");
		horizontalPanel70.setStyleName("identLine");
		classStarLab07.setStyleName("identStarLabel");
		verticalPanel22.getElement().setAttribute("style", 
				"margin-top: " + (new_clouds_heading_height - 31) + "px !important;");
		clScroll.getElement().setAttribute("style", 
				"background-image: url(icons/eufar_heading_clouds.jpg) !important; "
				+ "background-repeat: no-repeat !important;" 
				+ "background-size: " + screen_width + "px " + new_clouds_heading_height + "px !important;"
				+ "background-position: -" + menu_width + "px 0px !important;"
				+ "overflow: auto !important;");
		rootLogger.log(Level.INFO, "Classification panel initialized");


		// Assemble Keywords panel
		horizontalPanel71.add(keyMainLab);
		horizontalPanel71.add(keyPathLab);
		horizontalPanel71.add(keyPathLab2);
		horizontalPanel72.add(new HTML("<hr  style=\"width:1200px;height:10px;background:#c0c0c0;border:0px;\" />"));
		verticalPanel23.add(keyMainLab2);
		verticalPanel23.add(horizontalPanel71);
		verticalPanel23.add(horizontalPanel72);
		verticalPanel23.add(horizontalPanel08);
		horizontalPanel98.add(keyKeywordsLab);
		horizontalPanel98.add(keyStarLab01);
		horizontalPanel08.add(horizontalPanel98);
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
		classAgEngineeringCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAgPlantCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAgFoodCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAgForestCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAgSoilsCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAtAerosolsCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAtAirCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAtAltitudeCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAtChemistryCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAtElectricityCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAtPhenomenaCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAtPressureCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAtRadiationCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAtTemperatureCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAtVapourCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAtWindsCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAtCloudsCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classAtPrecipitationCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classBiDynamicsCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classBiEcosystemsCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classBiVegetationCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classCrGroundCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classCrGlaciersCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classCrIceCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classCrSnowCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classLsErosionCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classLsGeomorphologyCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classLsTemperatureCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classLsCoverCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classLsLandscapeCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classLsSurfaceCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classLsTopographyCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classOcBathymetryCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classOcProcessesCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classOcEnvironmentCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classOcGeophysicsCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classOcWavesCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classOcWindsCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classOcTopographyCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classOcTidesCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classOcQualityCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classSeGeodeticsCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classSeGeomagnetismCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classSeLandformsCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classSeGravityCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classSpGammaCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classSpInfraredCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classSpLidarCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classSpMicrowaveCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classSpRadarCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classSpRadioCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classSpUltravioletCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classSpVisibleCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classSpXrayCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classInIonosphereCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classInActivityCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classInParticleCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classThGroundCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classThSurfaceCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		classThChemistryCheck.getElement().setAttribute("style", "margin-left: 40px !important; color: #4f4f4f !important;");
		keyAgricultureLab.setStyleName("keyTitleTextLabel2");
		keyAtmosphereLab.setStyleName("keyTitleTextLabel");
		keyBiosphereLab.setStyleName("keyTitleTextLabel");
		keyCryosphereLab.setStyleName("keyTitleTextLabel");
		keySurfaceLab.setStyleName("keyTitleTextLabel");
		keyOceanLab.setStyleName("keyTitleTextLabel2");
		keyEarthLab.setStyleName("keyTitleTextLabel");
		keySpectralLab.setStyleName("keyTitleTextLabel");
		keyInteractionsLab.setStyleName("keyTitleTextLabel");
		keyHydrosphereLab.setStyleName("keyTitleTextLabel");
		keyInfoButton.getElement().setAttribute("style", "margin-left: 40px !important; margin-top: 200px !important;");
		keyKeywordsLab.setStyleName("keyTitleTextLabel3");
		keyMainLab2.setStyleName("identMainText2");
		keyStarLab01.setStyleName("identStarLabel");
		verticalPanel23.getElement().setAttribute("style", 
				"margin-top: " + (new_clouds_heading_height - 31) + "px !important;");
		kwScroll.getElement().setAttribute("style", 
				"background-image: url(icons/eufar_heading_clouds.jpg) !important; "
				+ "background-repeat: no-repeat !important;" 
				+ "background-size: " + screen_width + "px " + new_clouds_heading_height + "px !important;"
				+ "background-position: -" + menu_width + "px 0px !important;"
				+ "overflow: auto !important;");
		horizontalPanel08.setStyleName("keyMainPanel");
		verticalPanel03.setSpacing(2);
		verticalPanel04.setSpacing(2);
		verticalPanel05.setSpacing(2);
		verticalPanel06.setSpacing(2);
		horizontalPanel08.add(verticalPanel03);
		horizontalPanel08.add(verticalPanel04);
		horizontalPanel08.add(verticalPanel05);
		horizontalPanel08.add(verticalPanel06);
		horizontalPanel08.add(keyInfoButton);
		horizontalPanel08.setSpacing(20);
		horizontalPanel98.setStyleName("keyHorizontalPanel");
		keyMainLab.setStyleName("identMainText");
		keyPathLab.setStyleName("identPathText");
		keyPathLab2.setStyleName("identPathText2");
		horizontalPanel72.setStyleName("identLine");
		rootLogger.log(Level.INFO, "Keywords panel initialized");


		// Assemble Aircraft and instruments panel
		horizontalPanel73.add(aiMainLab);
		horizontalPanel73.add(aiPathLab);
		horizontalPanel73.add(aiPathLab2);
		horizontalPanel74.add(new HTML("<hr  style=\"width:1200px;height:10px;background:#c0c0c0;border:0px;\" />"));
		verticalPanel24.add(aiMainLab2);
		verticalPanel24.add(horizontalPanel73);
		verticalPanel24.add(horizontalPanel74);
		verticalPanel24.add(verticalPanel10);
		Utilities.populateListBox(airAircraftLst, aircraftList, 0);
		Utilities.populateListBox(airInstrumentLst, instrumentMap, 0);
		horizontalPanel09.add(airAircraftLab);
		horizontalPanel09.add(aiStarLab01);
		horizontalPanel09.add(airAircraftLst);
		horizontalPanel09.add(airPlusButton01);
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
		verticalPanel10.add(airAircraftTable);
		verticalPanel10.add(new HTML("<hr  style=\"width:1150px;height:10px;background:#0098d9;border:0px;margin-top:30px;"
				+ "margin-bottom:20px;\" />"));
		horizontalPanel12.add(airInstrumentLab);
		horizontalPanel12.add(aiStarLab02);
		horizontalPanel12.add(airInstrumentLst);
		horizontalPanel12.add(Emc_eufar.airInstNameLab);
		horizontalPanel12.add(Emc_eufar.airInstNameBox);
		horizontalPanel12.add(Emc_eufar.airInstManufacturerLab);
		horizontalPanel12.add(Emc_eufar.airInstManufacturerBox);
		horizontalPanel12.add(airPlusButton02);
		horizontalPanel12.add(airInstrumentInfo);
		verticalPanel10.add(horizontalPanel12);
		verticalPanel10.add(airInstrumentTable);
		airInstrumentTable.setCellSpacing(10);
		Emc_eufar.airInstNameBox.setStyleName("airTextBox6");
		Emc_eufar.airInstManufacturerBox.setStyleName("airTextBox6");
		Emc_eufar.airInstNameLab.setStyleName("airTitleTextLabel2");
		Emc_eufar.airInstManufacturerLab.setStyleName("airTitleTextLabel2");
		Emc_eufar.horizontalPanel65.getElement().setAttribute("style","margin-top: 13px;");
		verticalPanel10.setStyleName("airVerticalPanel");
		airAircraftLab.setStyleName("airTitleTextLabel");
		airAircraftLst.setStyleName("airTextList");
		horizontalPanel09.setStyleName("airHorizontalPanel2");
		horizontalPanel10.setStyleName("airHorizontalPanel");
		horizontalPanel12.setStyleName("airHorizontalPanel3");
		airManufacturerLab.setStyleName("airFlexTableLabel1");
		airTypeLab.setStyleName("airFlexTableLabel1");
		airOperatorLab.setStyleName("airFlexTableLabel1");
		airCountryLab.setStyleName("airFlexTableLabel1");
		airRegistrationLab.setStyleName("airFlexTableLabel1");
		airManufacturerInfo.setStyleName("airFlexTableLabel2");
		airTypeInfo.setStyleName("airFlexTableLabel3");
		airOperatorInfo.setStyleName("airFlexTableLabel4");
		airCountryInfo.setStyleName("airFlexTableLabel5");
		airRegistrationInfo.setStyleName("airFlexTableLabel6");
		airInstrumentTable.getElement().setAttribute("style","margin-left: 40px;");
		airInstrumentLst.setStyleName("airTextList2");
		airInstrumentLab.setStyleName("airTitleTextLabel3");
		aiStarLab01.setStyleName("airStarLabel");
		aiStarLab02.setStyleName("airStarLabel");
		aiMainLab2.setStyleName("identMainText2");
		aiMainLab.setStyleName("identMainText");
		aiPathLab.setStyleName("identPathText");
		aiPathLab2.setStyleName("identPathText2");
		horizontalPanel74.setStyleName("identLine");
		airCopyrightInfo.setStyleName("airCopyrightInfo");
		verticalPanel24.getElement().setAttribute("style", 
				"margin-top: " + (new_clouds_heading_height - 31) + "px !important;");
		aiScroll.getElement().setAttribute("style", 
				"background-image: url(icons/eufar_heading_clouds.jpg) !important; "
				+ "background-repeat: no-repeat !important;" 
				+ "background-size: " + screen_width + "px " + new_clouds_heading_height + "px !important;"
				+ "background-position: -" + menu_width + "px 0px !important;"
				+ "overflow: auto !important;");
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
		Emc_eufar.airInstNameLab.setVisible(false);
		Emc_eufar.airInstNameBox.setVisible(false);
		Emc_eufar.airInstManufacturerLab.setVisible(false);
		Emc_eufar.airInstManufacturerBox.setVisible(false);
		rootLogger.log(Level.INFO, "Aircraft and instruments panel initialized");


		// Assemble Geographic Information panel
		horizontalPanel75.add(geoMainLab);
		horizontalPanel75.add(geoPathLab);
		horizontalPanel75.add(geoPathLab2);
		horizontalPanel76.add(new HTML("<hr style=\"width:1200px;height:10px;background:#c0c0c0;border:0px;\" />"));
		verticalPanel25.add(geoMainLab2);
		verticalPanel25.add(horizontalPanel75);
		verticalPanel25.add(horizontalPanel76);
		verticalPanel25.add(verticalPanel11);
		Utilities.populateListBox(geoUnitLst, unitList, 0);
		horizontalPanel100.add(geoBoundingLab);
		horizontalPanel100.add(geoStarLab02);
		geoCoordTab.setWidget(1,0,horizontalPanel100);
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
		horizontalPanel13.add(geoStarLab01);
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
		horizontalPanel76.setStyleName("identLine");
		geoMainLab.setStyleName("identMainText");
		geoPathLab.setStyleName("identPathText");
		geoPathLab2.setStyleName("identPathText2");
		verticalPanel25.getElement().setAttribute("style", 
				"margin-top: " + (new_clouds_heading_height - 31) + "px !important;");
		giScroll.getElement().setAttribute("style", 
				"background-image: url(icons/eufar_heading_clouds.jpg) !important; "
				+ "background-repeat: no-repeat !important;" 
				+ "background-size: " + screen_width + "px " + new_clouds_heading_height + "px !important;"
				+ "background-position: -" + menu_width + "px 0px !important;"
				+ "overflow: auto !important;");
		geoStarLab01.setStyleName("identStarLabel");
		geoStarLab02.setStyleName("geoStarLabel");
		geoMainLab2.setStyleName("identMainText2");
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
		horizontalPanel103.add(refCreationLab);
		horizontalPanel103.add(refStarLab02);
		horizontalPanel104.add(refRevisionLab);
		horizontalPanel104.add(refStarLab03);
		horizontalPanel77.add(refMainLab);
		horizontalPanel77.add(refPathLab);
		horizontalPanel77.add(refPathLab2);
		horizontalPanel78.add(new HTML("<hr style=\"width:1200px;height:10px;background:#c0c0c0;border:0px;\" />"));
		verticalPanel26.add(refMainLab2);
		verticalPanel26.add(horizontalPanel77);
		verticalPanel26.add(horizontalPanel78);
		verticalPanel26.add(verticalPanel13);
		refPublicationDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		refPublicationDat.setValue(new Date());
		refRevisionDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		refRevisionDat.setValue(new Date());
		refCreationDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		refCreationDat.setValue(new Date());
		refDateTab.setWidget(1, 0, horizontalPanel104);
		refDateTab.setWidget(1, 1, refRevisionDat);
		refDateTab.setWidget(1, 2, refRevisionInfo);
		refDateTab.setWidget(0, 0, horizontalPanel103);
		refDateTab.setWidget(0, 1, refCreationDat);
		refDateTab.setWidget(0, 2, refCreationInfo);
		refDateTab.setCellSpacing(10);
		verticalPanel13.add(refDateTab);
		verticalPanel13.add(new HTML("<hr  style=\"width:1150px;height:10px;background:#0098d9;border:0px;margin-top:30px;"
				+ "margin-bottom:30px;\" />"));
		horizontalPanel21.add(refExtentLab);
		horizontalPanel21.add(refStarLab01);
		refPhaseTab.setWidget(0, 0, refPhaseLab);
		refPhaseTab.setWidget(0, 1, refStartDat);
		refPhaseTab.setWidget(0, 2, refEndDat);
		refPhaseTab.setWidget(0, 3, refDelButton);
		refStartDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		refStartDat.setValue(new Date());
		refEndDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		refEndDat.setValue(new Date());
		refStartLst.add(refStartDat);
		refEndLst.add(refEndDat);
		horizontalPanel21.add(refPhaseTab);
		horizontalPanel21.add(refPlusButton);
		horizontalPanel21.add(refPlusInfo);
		refStartDat.setStyleName("refDatebox");
		refEndDat.setStyleName("refDatebox");
		refRevisionDat.setStyleName("refDatebox");
		refCreationDat.setStyleName("refDatebox");
		refExtentLab.setStyleName("refTitleTextLabel");
		refDelButton.setStyleName("infoButton");
		refPhaseLab.setStyleName("refTextLabel");
		refPublicationLab.setStyleName("refTextLabel");
		refRevisionLab.setStyleName("refTextLabel");
		refCreationLab.setStyleName("refTextLabel");
		refPlusButton.getElement().setAttribute("style", "margin-left: 20px !important; margin-top: 5px !important;");
		refPlusInfo.getElement().setAttribute("style", "margin-left: 20px !important; margin-top: 5px !important;");
		verticalPanel13.setStyleName("refVerticalPanel");
		verticalPanel13.add(horizontalPanel21);
		horizontalPanel78.setStyleName("identLine");
		refMainLab.setStyleName("identMainText");
		refPathLab.setStyleName("identPathText");
		refPathLab2.setStyleName("identPathText2");
		verticalPanel26.getElement().setAttribute("style", 
				"margin-top: " + (new_clouds_heading_height - 31) + "px !important;");
		trScroll.getElement().setAttribute("style", 
				"background-image: url(icons/eufar_heading_clouds.jpg) !important; "
				+ "background-repeat: no-repeat !important;" 
				+ "background-size: " + screen_width + "px " + new_clouds_heading_height + "px !important;"
				+ "background-position: -" + menu_width + "px 0px !important;"
				+ "overflow: auto !important;");
		refStarLab01.setStyleName("refStarLabel");
		refStarLab02.setStyleName("refStarLabel2");
		refStarLab03.setStyleName("refStarLabel2");
		refMainLab2.setStyleName("identMainText2");
		HTMLTable.CellFormatter formatter = refDateTab.getCellFormatter();
		formatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		formatter.setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		Emc_eufar.refDelImage.setVisible(false);
		Emc_eufar.refDelImage.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				refStartDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(
						DateTimeFormat.getFormat("yyyy-MM-dd").format(refStartLst.get(1).getValue())));
				refEndDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(
						DateTimeFormat.getFormat("yyyy-MM-dd").format(refEndLst.get(1).getValue())));
				refPhaseTab.removeRow(1);
				refStartLst.remove(1);
				refEndLst.remove(1);
				List<Label> allLabel = $("*", Emc_eufar.refPhaseTab).widgets(Label.class);
				int id = 0;
				for (Object o : allLabel) {
					((Label) o).setText("Phase " + Integer.toString(id + 1));
					id++;
				}
				int row = refPhaseTab.getRowCount();
				if (row == 1) {
					Emc_eufar.refDelImage.setVisible(false);
				}
			}
		});
		rootLogger.log(Level.INFO, "Temporal Reference panel initialized");

		
		// Quality and Validity panel
		horizontalPanel79.add(qvMainLab);
		horizontalPanel79.add(qvPathLab);
		horizontalPanel79.add(qvPathLab2);
		horizontalPanel80.add(new HTML("<hr style=\"width:1200px;height:10px;background:#c0c0c0;border:0px;\" />"));
		verticalPanel27.add(qvMainLab2);
		verticalPanel27.add(horizontalPanel79);
		verticalPanel27.add(horizontalPanel80);
		verticalPanel27.add(verticalPanel14);
		Utilities.populateListBox(imageLevLst, levelList, 0);
		Utilities.populateListBox(imageDCLst, DCList, 0);
		imageRadDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		imageRadDat.setValue(new Date());
		imageSpeDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		imageSpeDat.setValue(new Date());
		horizontalPanel117.add(imageRad);
		horizontalPanel117.add(imageStarLab01);
		horizontalPanel30.add(horizontalPanel117);
		horizontalPanel116.add(insituRad);
		horizontalPanel116.add(insituStarLab01);
		horizontalPanel30.add(horizontalPanel116);
		horizontalPanel30.add(qvInfoButton01);
		qvInfoButton01.getElement().setAttribute("style","margin-left: 200px !important;");
		horizontalPanel30.getElement().setAttribute("style", "margin-bottom: 20px;");
		verticalPanel14.add(horizontalPanel30);
		verticalPanel14.add(verticalPanel17);
		insituStarLab01.setStyleName("qvStarLabel");
		imageStarLab01.setStyleName("qvStarLabel");
		imageRad.setStyleName("qv_imageRad");
		insituRad.setStyleName("qv_insituRad");
		verticalPanel14.setStyleName("qvVerticalPanel");
		horizontalPanel80.setStyleName("identLine");
		qvMainLab.setStyleName("identMainText");
		qvPathLab.setStyleName("identPathText");
		qvPathLab2.setStyleName("identPathText2");
		qvMainLab2.setStyleName("identMainText2");
		verticalPanel27.getElement().setAttribute("style", 
				"margin-top: " + (new_clouds_heading_height - 31) + "px !important;");
		qvScroll.getElement().setAttribute("style", 
				"background-image: url(icons/eufar_heading_clouds.jpg) !important; "
				+ "background-repeat: no-repeat !important;" 
				+ "background-size: " + screen_width + "px " + new_clouds_heading_height + "px !important;"
				+ "background-position: -" + menu_width + "px 0px !important;");
		rootLogger.log(Level.INFO, "Quality and Validity panel initialized");
		

		// Access and Use Constraints panel
		horizontalPanel81.add(useMainLab);
		horizontalPanel81.add(usePathLab);
		horizontalPanel81.add(usePathLab2);
		horizontalPanel82.add(new HTML("<hr style=\"width:1200px;height:10px;background:#c0c0c0;border:0px;\" />"));
		verticalPanel28.add(useMainLab2);
		verticalPanel28.add(horizontalPanel81);
		verticalPanel28.add(horizontalPanel82);
		verticalPanel28.add(verticalPanel15);
		useConditionsAddTab.setWidget(0, 0, useConditionsBox);
		useConditionsAddTab.setWidget(0, 1, auDelButton1);
		horizontalPanel101.add(useConditionsLab);
		horizontalPanel101.add(useStarLab01);
		useConditionsTab.setWidget(0, 0, horizontalPanel101);
		useConditionsTab.setWidget(0, 1, useConditionsAddTab);
		SimplePanel useInfoButton1 = Elements.addInfoButton("auConditions");
		useConditionsTab.setWidget(0, 2, usePlusButton1);
		useConditionsTab.setWidget(0, 3, useInfoButton1);
		FlexCellFormatter auCellFormatter1 = useConditionsTab.getFlexCellFormatter();
		auCellFormatter1.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		auCellFormatter1.setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		auCellFormatter1.setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_TOP);
		auCellFormatter1.setVerticalAlignment(0, 3, HasVerticalAlignment.ALIGN_TOP);
		verticalPanel15.add(useConditionsTab);
		verticalPanel15.add(new HTML("<hr  style=\"width:1150px;height:10px;background:#0098d9;border:0px;margin-top:30px;"
				+ "margin-bottom:30px;\" />"));
		useLimitationsAddTab.setWidget(0, 0, useLimitationsBox);
		useLimitationsAddTab.setWidget(0, 1, auDelButton2);
		horizontalPanel102.add(useLimitationsLab);
		horizontalPanel102.add(useStarLab02);
		useLimitationsTab.setWidget(0, 0, horizontalPanel102);
		useLimitationsTab.setWidget(0, 1, useLimitationsAddTab);
		useLimitationsTab.setWidget(0, 2, usePlusButton2);
		SimplePanel useInfoButton2 = Elements.addInfoButton("auLimitations");
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
		auDelButton1.setStyleName("infoButton");
		auDelButton2.setStyleName("infoButton");
		usePlusButton1.getElement().setAttribute("style", "margin-top: 46px !important;");
		useInfoButton1.getElement().setAttribute("style", "margin-top: 46px !important;");
		usePlusButton2.getElement().setAttribute("style", "margin-top: 46px !important;");
		useInfoButton2.getElement().setAttribute("style", "margin-top: 46px !important;");
		useConditionsLst.add(useConditionsBox);
		useLimitationsLst.add(useLimitationsBox);
		useConditionsBox.setText("As EUFAR is an EU-funded project, data in the EUFAR archive are available to everyone. All users are "
				+ "requiered to acknowledge the data providers in any publication based on EUFAR data.");
		useLimitationsBox.setText("No limitations");
		horizontalPanel82.setStyleName("identLine");
		useMainLab.setStyleName("identMainText");
		usePathLab.setStyleName("identPathText");
		usePathLab2.setStyleName("identPathText2");
		verticalPanel28.getElement().setAttribute("style", 
				"margin-top: " + (new_clouds_heading_height - 31) + "px !important;");
		auScroll.getElement().setAttribute("style", 
				"background-image: url(icons/eufar_heading_clouds.jpg) !important; "
				+ "background-repeat: no-repeat !important;" 
				+ "background-size: " + screen_width + "px " + new_clouds_heading_height + "px !important;"
				+ "background-position: -" + menu_width + "px 0px !important;"
				+ "overflow: auto !important;");
		useMainLab2.setStyleName("identMainText2");
		useStarLab01.setStyleName("useStarLabel");
		useStarLab02.setStyleName("useStarLabel");
		Emc_eufar.useDelImage1.setVisible(false);
		Emc_eufar.useDelImage2.setVisible(false);
		Emc_eufar.useDelImage1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				useConditionsBox.setText(useConditionsLst.get(1).getText());
				useConditionsAddTab.removeRow(1);
				useConditionsLst.remove(1);
				int row = Emc_eufar.useConditionsAddTab.getRowCount();
				if (row == 1) {
					Emc_eufar.useDelImage1.setVisible(false);
				}
			}
		});
		Emc_eufar.useDelImage2.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				useLimitationsBox.setText(useLimitationsLst.get(1).getText());
				useLimitationsAddTab.removeRow(1);
				useLimitationsLst.remove(1);
				int row = Emc_eufar.useLimitationsAddTab.getRowCount();
				if (row == 1) {
					Emc_eufar.useDelImage2.setVisible(false);
				}
			}
		});
		rootLogger.log(Level.INFO, "Access and Use Constraints panel initialized");


		// Assemble Responsible Organisations panel
		horizontalPanel105.add(orgPartyLab);
		horizontalPanel105.add(orgStarLab01);
		horizontalPanel106.add(orgEmailLab);
		horizontalPanel106.add(orgStarLab02);
		horizontalPanel107.add(orgRoleLab);
		horizontalPanel107.add(orgStarLab03);
		horizontalPanel83.add(orgMainLab);
		horizontalPanel83.add(orgPathLab);
		horizontalPanel83.add(orgPathLab2);
		horizontalPanel84.add(new HTML("<hr  style=\"width:1200px;height:10px;background:#c0c0c0;border:0px;\" />"));
		verticalPanel29.add(orgMainLab2);
		verticalPanel29.add(horizontalPanel83);
		verticalPanel29.add(horizontalPanel84);
		verticalPanel29.add(verticalPanel35);
		verticalPanel35.add(orgAddTab);
		Utilities.populateListBox(orgRoleLst, roleList, 5);
		orgRoleLst.setName("orgRoleList");
		orgPartyTab.setWidget(0, 0, horizontalPanel105);
		orgPartyTab.setWidget(0, 1, orgPartyBox);
		orgPartyTab.setWidget(0, 2, roPartyInfo);
		orgPartyTab.setWidget(1, 0, horizontalPanel106);
		orgPartyTab.setWidget(1, 1, orgEmailBox);
		orgPartyTab.setWidget(1, 2, roEmailInfo);
		orgPartyTab.setWidget(2, 0, horizontalPanel107);
		orgPartyTab.setWidget(2, 1, orgRoleLst);
		orgPartyTab.setWidget(2, 2, roRoleInfo);
		orgPartyTab.setCellSpacing(10);
		horizontalPanel27.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel27.add(orgPartyTab);
		horizontalPanel27.add(orgDelButton);
		horizontalPanel27.add(orgPlusButton);
		orgDelImage.setVisible(false);
		orgAddTab.setWidget(0, 0, horizontalPanel27);
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
		horizontalPanel84.setStyleName("identLine");
		orgMainLab.setStyleName("identMainText");
		orgPathLab.setStyleName("identPathText");
		orgPathLab2.setStyleName("identPathText2");
		verticalPanel35.setStyleName("orgVerticalPanel");
		verticalPanel29.getElement().setAttribute("style", 
				"margin-top: " + (new_clouds_heading_height - 31) + "px !important;");
		roScroll.getElement().setAttribute("style", 
				"background-image: url(icons/eufar_heading_clouds.jpg) !important; "
				+ "background-repeat: no-repeat !important;" 
				+ "background-size: " + screen_width + "px " + new_clouds_heading_height + "px !important;"
				+ "background-position: -" + menu_width + "px 0px !important;"
				+ "overflow: auto !important;");
		orgMainLab2.setStyleName("identMainText2");
		orgStarLab01.setStyleName("orgStarLabel");
		orgStarLab02.setStyleName("orgStarLabel");
		orgStarLab03.setStyleName("orgStarLabel");
		FlexCellFormatter orgCellFormatter = orgPartyTab.getFlexCellFormatter();
		orgCellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		orgCellFormatter.setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		orgCellFormatter.setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		Emc_eufar.orgDelImage.addClickHandler(new ClickHandler() {
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
					Emc_eufar.orgDelImage.setVisible(false);
				}
			}
		});
		rootLogger.log(Level.INFO, "Responsible Organisations panel initialized");


		// Assemble Metadata on Metadata panel
		horizontalPanel108.add(metDateLab);
		horizontalPanel108.add(metStarLab01);
		horizontalPanel109.add(metLanguageLab);
		horizontalPanel109.add(metStarLab02);
		horizontalPanel110.add(metNameLab);
		horizontalPanel110.add(metStarLab03);
		horizontalPanel111.add(metEmailLab);
		horizontalPanel111.add(metStarLab04);
		horizontalPanel85.add(metMainLab);
		horizontalPanel85.add(metPathLab);
		horizontalPanel85.add(metPathLab2);
		horizontalPanel86.add(new HTML("<hr  style=\"width:1200px;height:10px;background:#c0c0c0;border:0px;\" />"));
		verticalPanel30.add(metMainLab2);
		verticalPanel30.add(horizontalPanel85);
		verticalPanel30.add(horizontalPanel86);
		verticalPanel30.add(verticalPanel16);
		metDateDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		metDateDat.setValue(new Date());
		Utilities.populateListBox(metLanguageLst, languageList, 4);
		metLanguageLst.setName("metLanguageList");
		metMetadataTab.setWidget(0, 0, horizontalPanel108);
		metMetadataTab.setWidget(0, 1, metDateDat);
		metMetadataTab.setWidget(0, 2, metDateInfo);
		metMetadataTab.setWidget(1, 0, horizontalPanel109);
		metMetadataTab.setWidget(1, 1, metLanguageLst);
		metMetadataTab.setWidget(1, 2, metLanguageInfo);
		metMetadataTab.setCellSpacing(10);
		verticalPanel16.add(metMetadataTab);
		verticalPanel16.add(new HTML("<hr  style=\"width:1150px;height:10px;background:#0098d9;border:0px;margin-top:30px;"
				+ "margin-bottom:30px;\" />"));
		metContactTab.setWidget(0, 0, metContactLab);
		metPartyTab.setWidget(0, 0, horizontalPanel110);
		metPartyTab.setWidget(0, 1, metNameBox);
		metPartyTab.setWidget(1, 0, horizontalPanel111);
		metPartyTab.setWidget(1, 1, metEmailBox);
		horizontalPanel66.add(metPartyTab);
		horizontalPanel66.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel66.add(mmDelButton);
		metDelImage.setVisible(false);
		metAddTab.setWidget(0, 0, horizontalPanel66);
		metContactTab.setWidget(0, 1, metAddTab);
		metContactTab.setWidget(0, 2, metPlusButton);
		metContactTab.setWidget(0, 3, metPartyInfo);
		FlexCellFormatter metCellFormatter = metContactTab.getFlexCellFormatter();
		metCellFormatter.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		metCellFormatter.setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		metCellFormatter.setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_TOP);
		metCellFormatter.setVerticalAlignment(0, 3, HasVerticalAlignment.ALIGN_TOP);
		metPlusButton.getElement().setAttribute("style", "margin-top: 21px !important;");
		metPartyInfo.getElement().setAttribute("style", "margin-top: 21px !important;");
		verticalPanel16.add(metContactTab);
		metDateLab.setStyleName("metTextLabel");
		metLanguageLab.setStyleName("metTextLabel");
		metLanguageLst.setStyleName("metTextList");
		horizontalPanel28.setStyleName("metHorizontalPanel");
		horizontalPanel29.setStyleName("metHorizontalPanel");
		verticalPanel16.setStyleName("metVerticalPanel");
		metContactLab.setStyleName("metTitleTextLabel2");
		metNameLab.setStyleName("metTextLabel");
		metEmailLab.setStyleName("metTextLabel");
		metNameBox.setStyleName("metTextBox");
		metEmailBox.setStyleName("metTextBox2");
		mmDelButton.setStyleName("emptyButton");
		metNameLst.add(metNameBox);
		metEmailLst.add(metEmailBox);
		horizontalPanel86.setStyleName("identLine");
		metMainLab.setStyleName("identMainText");
		metPathLab.setStyleName("identPathText");
		metPathLab2.setStyleName("identPathText2");
		verticalPanel30.getElement().setAttribute("style", 
				"margin-top: " + (new_clouds_heading_height - 31) + "px !important;");
		mmScroll.getElement().setAttribute("style", 
				"background-image: url(icons/eufar_heading_clouds.jpg) !important; "
				+ "background-repeat: no-repeat !important;" 
				+ "background-size: " + screen_width + "px " + new_clouds_heading_height + "px !important;"
				+ "background-position: -" + menu_width + "px 0px !important;"
				+ "overflow: auto !important;");
		metMainLab2.setStyleName("identMainText2");
		metStarLab01.setStyleName("metStarLabel");
		metStarLab02.setStyleName("metStarLabel");
		metStarLab03.setStyleName("metStarLabel");
		metStarLab04.setStyleName("metStarLabel");
		metDateDat.setStyleName("metDatebox");
		FlexCellFormatter metCellFormatter2 = metPartyTab.getFlexCellFormatter();
		metCellFormatter2.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		metCellFormatter2.setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		FlexCellFormatter metCellFormatter3 = metMetadataTab.getFlexCellFormatter();
		metCellFormatter3.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		metCellFormatter3.setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		Emc_eufar.metDelImage.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				metNameBox.setText(Emc_eufar.metNameLst.get(1).getText());
				metEmailBox.setText(Emc_eufar.metEmailLst.get(1).getText());
				Emc_eufar.metAddTab.removeRow(1);
				Emc_eufar.metNameLst.remove(1);
				Emc_eufar.metEmailLst.remove(1);
				int row = Emc_eufar.metAddTab.getRowCount();
				if (row == 1) {
					Emc_eufar.metDelImage.setVisible(false);
				}
			}
		});
		rootLogger.log(Level.INFO, "Metadata on Metadata panel initialized");

		
		// Assemble
		tabPanel.add(Emc_eufar.idScroll,"Identification");
		tabPanel.add(Emc_eufar.clScroll,"Classification");
		tabPanel.add(Emc_eufar.kwScroll,"Keywords");
		tabPanel.add(Emc_eufar.aiScroll,"Aircraft and Instruments");
		tabPanel.add(Emc_eufar.giScroll,"Geographic Information");
		tabPanel.add(Emc_eufar.trScroll,"Temporal Reference");
		tabPanel.add(Emc_eufar.qvScroll,"Quality and Validity");
		tabPanel.add(Emc_eufar.auScroll,"Access and Use Constraints");
		tabPanel.add(Emc_eufar.roScroll,"Responsible Organisations");
		tabPanel.add(Emc_eufar.mmScroll,"Metadata on Metadata");
		subDockPanel.addNorth(horizontalPanel87, 80);
		subDockPanel.addSouth(horizontalPanel112, 40);
		verticalPanel101.add(Emc_eufar.mainMenu);
		verticalPanel101.add(horizontalPanel90);
		horizontalPanel90.getElement().setAttribute("style", "height: " + (new_clouds_heading_height) + "px;" +
				  "background-image: url(icons/eufar_heading_clouds.jpg); background-repeat: no-repeat;" + 
				  "background-size: " + screen_width + "px " + new_clouds_heading_height + "px; background-position: "+ 
				  "left top; width: " + menu_width + "px;");
		subDockPanel.addWest(verticalPanel101, menu_width);
		subDockPanel.add(Emc_eufar.tabPanel);
		subDockPanel.getElement().setAttribute("style", "background: rgb(238,238,238) !important;");
		RootLayoutPanel rp=RootLayoutPanel.get();
		rp.clear();
		rp.add(subDockPanel);
		Window.setTitle(titleString);
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
			final VerticalPanel verticalPanel02 = new VerticalPanel();
			final HorizontalPanel horizontalPanel01 = new HorizontalPanel();
			final HorizontalPanel horizontalPanel02 = new HorizontalPanel();
			final Image image = new Image("icons/warning_popup_icon.png");
			final Label label = new Label("The actual document has been modified. Changes will be lost if the document is not saved.");
			final Label label2 = new Label("Do you want to save your changes?");
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
			horizontalPanel01.add(image);
			verticalPanel02.add(label);
			verticalPanel02.add(label2);
			horizontalPanel01.add(verticalPanel02);
			verticalPanel01.add(horizontalPanel01);
			horizontalPanel02.add(saveButton);
			horizontalPanel02.add(cancelButton);
			horizontalPanel02.add(createButton);
			verticalPanel01.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
			verticalPanel01.add(horizontalPanel02);
			infoDialog.add(verticalPanel01);
			infoDialog.setStyleName("newFilePanel");
			label.setStyleName("newFileText");
			label2.setStyleName("newFileText2");
			saveButton.addStyleName("newFileButton");
			cancelButton.addStyleName("newFileButton2");
			createButton.addStyleName("newFileButton3");
			infoDialog.setSize("350px","50px");
			infoDialog.setModal(true);
			infoDialog.center();
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
			if (!runCorrect(allDateBox.get(i))) {
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
						textBox.getElement().setAttribute("style","border-color: #ED1C24 !important;");
						
						widgetIndex = tabPanel.getWidgetIndex(parent);
						tabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: #ED1C24 !important;");
					} else if (label.getText() == "Spatial resolution:") {} 
					else {
						notCompleted++;
						textBox.getElement().setAttribute("style","border-color: #ED1C24 !important;");
						widgetIndex = tabPanel.getWidgetIndex(parent);
						tabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: #ED1C24 !important;");
					}
				} else {
					if (!runCorrect(textBox)) {
						notCompleted++;
						widgetIndex = tabPanel.getWidgetIndex(parent);
						tabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: #ED1C24 !important;");
					}
				}
			} catch (Exception ex) {
				rootLogger.log(Level.WARNING, "the widget has no parent: " + ex.getMessage());
			}
		}
		List<CheckBox> allClaCheck = $("*", clScroll).widgets(CheckBox.class);
		for (int i = 0; i < allClaCheck.size(); i++) {
			if  (allClaCheck.get(i).getValue() == true) {
				allClaCheckNumber++;
			}
		}
		if (allClaCheckNumber == 0) {
			notCompleted++;
			tabPanel.getTabWidget(1).getElement().setAttribute("style","color: #ED1C24 !important;");
		}
		List<CheckBox> allKeyCheck = $("*", kwScroll).widgets(CheckBox.class);
		for (int i = 0; i < allKeyCheck.size(); i++) {
			if (allKeyCheck.get(i).getValue() == true) {
				allKeyCheckNumber++;
			}
		}
		if (allKeyCheckNumber == 0) {
			notCompleted++;
			tabPanel.getTabWidget(2).getElement().setAttribute("style","color: #ED1C24 !important;");
		}
		if (aircraftTabList.size() == 0) {
			notCompleted++;
			tabPanel.getTabWidget(3).getElement().setAttribute("style","color: #ED1C24 !important;");
			airAircraftLst.getElement().setAttribute("style","border-color: #ED1C24 !important;");
			if (airAircraftLst.getSelectedIndex() == 1) {
				if (Emc_eufar.airManufacturerBox.getText() == "") {
					Emc_eufar.airManufacturerBox.getElement().setAttribute("style", "border-color: #ED1C24 !important;");
				}
				if (Emc_eufar.airTypeBox.getText() == "") {
					Emc_eufar.airTypeBox.getElement().setAttribute("style", "border-color: #ED1C24 !important;");
				}
				if (Emc_eufar.airOperatorBox.getText() == "") {
					Emc_eufar.airOperatorBox.getElement().setAttribute("style", "border-color: #ED1C24 !important;");
				}
				if (Emc_eufar.airCountryLst.getSelectedItemText() == "Make a choice...") {
					Emc_eufar.airCountryLst.getElement().setAttribute("style", "border-color: #ED1C24 !important;");
				}
				if (Emc_eufar.airRegistrationBox.getText() == "") {
					Emc_eufar.airRegistrationBox.getElement().setAttribute("style", "border-color: #ED1C24 !important;");
				}
			}
		}
		if (instrumentTabList.size() == 0) {
			notCompleted++;
			tabPanel.getTabWidget(3).getElement().setAttribute("style","color: #ED1C24 !important;");
			airInstrumentLst.getElement().setAttribute("style","border-color: #ED1C24 !important;");
		}
		if (geoLocationLst.getSelectedIndex() == 0 || geoDetailLst.getSelectedIndex() == 0 || geoDetailLst.isEnabled() == false) {
			if (geoLocationLst.getSelectedIndex() == 0) {
				geoLocationLst.getElement().setAttribute("style","border-color: #ED1C24 !important;");
			}
			if (geoDetailLst.getSelectedIndex() == 0 || geoDetailLst.isEnabled() == false) {
				geoDetailLst.getElement().setAttribute("style","border-color: #ED1C24 !important;");
			}
			notCompleted++;
			tabPanel.getTabWidget(4).getElement().setAttribute("style","color: #ED1C24 !important;");
		}
		if (((CheckBox) insituRad.getWidget(0)).getValue() == false && ((CheckBox) imageRad.getWidget(0)).getValue() == false) {
			notCompleted++;
			tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
		} else if (((CheckBox) insituRad.getWidget(0)).getValue() == true && ((CheckBox) imageRad.getWidget(0)).getValue() == false) {
			if (((CheckBox) insituChk04.getWidget(0)).getValue() == false && ((CheckBox) insituChk05.getWidget(0)).getValue() == false && 
					((CheckBox) insituChk06.getWidget(0)).getValue() == false && ((CheckBox) insituChk07.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel34.getElement().setAttribute("style","border: 1px solid #ED1C24;");
				notCompleted++;
			} else if (((CheckBox) insituChk07.getWidget(0)).getValue() == true && insituOtherBox.getText() == "") {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				insituOtherBox.getElement().setAttribute("style","border-color: #ED1C24 !important;");
				notCompleted++;
			}
			if (((CheckBox) insituChk01Y.getWidget(0)).getValue() == false && ((CheckBox) insituChk01N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel121.getElement().setAttribute("style","border: 1px solid #ED1C24;");
				notCompleted++;
			}
		} else if (((CheckBox) insituRad.getWidget(0)).getValue() == false && ((CheckBox) imageRad.getWidget(0)).getValue() == true) {
			if (imageLevLst.getSelectedValue() == "Make a choice...") {
				imageLevLst.getElement().setAttribute("style","border-color: #ED1C24 !important;");
			}
			if (((CheckBox) imageChk10Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk10N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel148.getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk11Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk11N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel149.getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk12Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk12N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel150.getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk13Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk13N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel151.getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk14Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk14N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel152.getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk15Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk15N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel48.getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk16Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk16N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel49.getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk17Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk17N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel50.getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk18Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk18N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel51.getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk19Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk19N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel153.getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk20Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk20N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel53.getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk21Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk21N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel54.getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk22Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk22N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel55.getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk23Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk23N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel154.getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk24Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk24N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel155.getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
				notCompleted++;
			}
			if (((CheckBox) imageChk25Y.getWidget(0)).getValue() == false && ((CheckBox) imageChk25N.getWidget(0)).getValue() == false) {
				Emc_eufar.tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
				Emc_eufar.horizontalPanel156.getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
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
	
	
	// check if all TextBoxes have been correctly filled in before saving it
	private boolean runCorrect(final TextBoxBase textBox) {
		Emc_eufar.rootLogger.log(Level.INFO, "Check of text in all fields in progress...");
		boolean result = true;
		for (Map.Entry<TextBoxBase, String> entry : correctField.entrySet()) {
			String string = entry.getValue();
			TextBoxBase textBoxE = entry.getKey();
			if (textBoxE == textBox) {
				switch (string) {
				case "number":
					try { 
						Double.parseDouble(textBox.getText());
					} 
					catch (NumberFormatException e) {
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
		rootLogger.log(Level.INFO, "Check of text in all fields finished.");
		return result;
	}
	
	
	// check if all DateBoxes have been correctly set before saving it
	private boolean runCorrect(final DateBox dateBox) {
		rootLogger.log(Level.INFO, "Check of a datebox in progress...");
		boolean result = true;		
		Date actualDate = new Date();
		Date boxDate = dateBox.getValue();
		Widget parent;
		int widgetIndex = -1;
		if (boxDate.after(actualDate)) {
			result = false;
			dateBox.getElement().setAttribute("style","border-color: blue !important;");
			parent = dateBox.getParent();
			while (!(parent instanceof ScrollPanel)) {
				parent = parent.getParent();
			}
			widgetIndex = tabPanel.getWidgetIndex(parent);
			tabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: rgb(0,0,200) !important;");
			if (refStartLst.contains(dateBox) || refEndLst.contains(dateBox)) {
				for (int row = 0; row < refPhaseTab.getRowCount(); row++) {
					for (int col = 0; col < refPhaseTab.getCellCount(row); col++) {
						Widget w = refPhaseTab.getWidget(row, col);
						if (w == dateBox) {
							refPhaseTab.getWidget(row, 0).getElement().setAttribute("style","color: rgb(0,0,200) !important;");
						}
					}
				}
			} else {
				for (Map.Entry<DateBox, Label> entry : correctDate.entrySet()) {
					Label label = entry.getValue();
					DateBox dateBoxE = entry.getKey();
					if (dateBoxE == dateBox) {
						label.getElement().setAttribute("style","color: rgb(0,0,200) !important;");
					}
				}
			}
		}
		rootLogger.log(Level.INFO, "Check of a datebox finished.");
		return result;
	}
	
	
	// functions to navigate between tabs
	private void navigateTab(final int direction) {
		int selected_tab = tabPanel.getSelectedIndex();
		int new_tab = selected_tab + direction;
		if (new_tab < 0) {
			new_tab = 8;
		} else if (new_tab > 8) {
			new_tab = 0;
		}
		tabPanel.selectTab(new_tab);
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
	
	
	// new panel with click and mouse over events
	class HorizontalPanelOver extends HorizontalPanel {
	    public HorizontalPanelOver() {
	        super();
	    }
	    public HandlerRegistration addMouseOverHandler(MouseOverHandler handlerOver) {
	         return addDomHandler(handlerOver, MouseOverEvent.getType());
	    }
	    public HandlerRegistration addMouseOutHandler(MouseOutHandler handlerOut) {
	         return addDomHandler(handlerOut, MouseOutEvent.getType());
	    }
	    public HandlerRegistration addClickHandler(ClickHandler handler) {
	         return addDomHandler(handler, ClickEvent.getType());
	    }
	}
}
