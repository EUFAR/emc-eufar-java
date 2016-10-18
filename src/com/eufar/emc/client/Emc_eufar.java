package com.eufar.emc.client;

import static com.google.gwt.query.client.GQuery.$;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.eufar.emc.client.ScrollableTabLayoutPanel;
import com.eufar.emc.client.Materials.Resources;
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
import com.google.gwt.resources.client.ImageResource;
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
	public static Date actualDate = new Date();
	public static HashMap<String, String> languageMap = Materials.languageMap();
	public static HashMap<String, String> categoriesMap = Materials.categoriesMap();
	public static HashMap<String, String> keywordsMap = Materials.keywordsMap();
	public static HashMap<String, String> roleMap = Materials.roleMap();
	public static HashMap<String, String> operatorMap = Materials.operatorMap();
	public static HashMap<String, String> typeMap = Materials.typeMap();
	public static HashMap<String, String> instrumentMap = Materials.instrumentMap();
	public static HashMap<String, String> unitMap = Materials.unitMap();
	public static ArrayList<String> allCorrectFields = Materials.allCorrectFields();
	public static ArrayList<TextBoxBase> allTextBoxes = new ArrayList<TextBoxBase>();
	public static ArrayList<String> allTextboxFields = new ArrayList<String>();
	public static ArrayList<ListBox> allListBoxes = new ArrayList<ListBox>();
	public static ArrayList<HorizontalPanel> allRadioButtons = new ArrayList<HorizontalPanel>();
	public static ArrayList<HorizontalPanel> allCheckBoxes = new ArrayList<HorizontalPanel>();
	public static ArrayList<DateBox> allDateBoxes = new ArrayList<DateBox>();
	private String emcVersion = new String("v1.3.0 (2016-10-06)");
	private String gwtVersion = new String("2.7.0");
	private String eclipseVersion = new String("4.6");
	private String javaVersion = new String("1.8.0");
	private String inspireVersion = new String("v1.3");
	public static Boolean isModified = new Boolean(false);
	public static String titleString = new String("EUFAR Metadata Creator");
	public static String emcPath = new String("");
	public static Resources resources = GWT.create(Materials.Resources.class);
	

	// Main window items initialization
	public static MenuBar mainMenu = new MenuBar();
	private MenuBar emcMenu = new MenuBar(true);
	private MenuBar aboutMenu = new MenuBar(true);
	private MenuBar fileMenu = new MenuBar(true);
	private String imageEMC = "<i class='fa fa-bars' aria-hidden='true' style='color: white; font-size: 1.7em;'></i>";
	public static DockLayoutPanel subDockPanel = new DockLayoutPanel(Unit.PX);
	public static StackLayoutPanel stackPanel = new StackLayoutPanel(Unit.PX);
	public static ScrollableTabLayoutPanel tabPanel = new ScrollableTabLayoutPanel(42, Unit.PX);
	public static String myFileName = new String("");
	private VerticalPanel verticalPanel101 = new VerticalPanel();
	private HorizontalPanel horizontalPanel90 = new HorizontalPanel();
	private int clouds_heading_width = 3840;
	private int clouds_heading_height = 322;
	private int screen_width = Utilities.getScreenWidth();
	private int menu_width = (screen_width) / 10;
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
	public static ArrayList<String> languageList = Materials.languageList();
	public static ArrayList<String> typeList = Materials.typeList();
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
	public static HorizontalPanel classBiotaCheck = Elements.checkBox("Biota");
	public static HorizontalPanel classBoundariesCheck = Elements.checkBox("Boundaries");
	public static HorizontalPanel classClimatologyCheck = Elements.checkBox("Climatology / Meteorology / Atmosphere");
	public static HorizontalPanel classEconomyCheck = Elements.checkBox("Economy");
	public static HorizontalPanel classElevationCheck = Elements.checkBox("Elevation");
	public static HorizontalPanel classEnvironmentCheck = Elements.checkBox("Environment");
	public static HorizontalPanel classFarmingCheck = Elements.checkBox("Farming");
	public static HorizontalPanel classInformationCheck = Elements.checkBox("Geoscientific Information");
	public static HorizontalPanel classHealthCheck = Elements.checkBox("Health");
	public static HorizontalPanel classImageryCheck = Elements.checkBox("Imagery / Base Maps / Earth Cover");
	public static HorizontalPanel classIntelligenceCheck = Elements.checkBox("Intelligence / Military");
	public static HorizontalPanel classWatersCheck = Elements.checkBox("Inland Waters");
	public static HorizontalPanel classLocationCheck = Elements.checkBox("Location");
	public static HorizontalPanel classOceansCheck = Elements.checkBox("Oceans");
	public static HorizontalPanel classPlanningCheck = Elements.checkBox("Planning / Cadastre");
	public static HorizontalPanel classSocietyCheck = Elements.checkBox("Society");
	public static HorizontalPanel classStructureCheck = Elements.checkBox("Structure");
	public static HorizontalPanel classTransportationCheck = Elements.checkBox("Transportation");
	public static HorizontalPanel classCommunicationCheck = Elements.checkBox("Utilities / Communication");
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
	public static HorizontalPanel classAgEngineeringCheck = Elements.checkBox("Agricultural engineering");
	public static HorizontalPanel classAgPlantCheck = Elements.checkBox("Agricultural plant science");
	public static HorizontalPanel classAgFoodCheck = Elements.checkBox("Food science");
	public static HorizontalPanel classAgForestCheck = Elements.checkBox("Forest science");
	public static HorizontalPanel classAgSoilsCheck = Elements.checkBox("Soils");
	public static HorizontalPanel classAtAerosolsCheck = Elements.checkBox("Aerosols");
	public static HorizontalPanel classAtAirCheck = Elements.checkBox("Air quality");
	public static HorizontalPanel classAtAltitudeCheck = Elements.checkBox("Altitude");
	public static HorizontalPanel classAtChemistryCheck = Elements.checkBox("Atmospheric chemistry");
	public static HorizontalPanel classAtElectricityCheck = Elements.checkBox("Atmospheric electricity");
	public static HorizontalPanel classAtPhenomenaCheck = Elements.checkBox("Atmospheric phenomena");
	public static HorizontalPanel classAtPressureCheck = Elements.checkBox("Atmospheric pressure");
	public static HorizontalPanel classAtRadiationCheck = Elements.checkBox("Atmospheric radiation");
	public static HorizontalPanel classAtTemperatureCheck = Elements.checkBox("Atmospheric temperature");
	public static HorizontalPanel classAtVapourCheck = Elements.checkBox("Atmospheric water vapor");
	public static HorizontalPanel classAtWindsCheck = Elements.checkBox("Atmospheric winds");
	public static HorizontalPanel classAtCloudsCheck = Elements.checkBox("Clouds");
	public static HorizontalPanel classAtPrecipitationCheck = Elements.checkBox("Precipitation");
	public static HorizontalPanel classBiDynamicsCheck = Elements.checkBox("Ecological dynamics");
	public static HorizontalPanel classBiEcosystemsCheck = Elements.checkBox("Terrestrial ecosystems");
	public static HorizontalPanel classBiVegetationCheck = Elements.checkBox("Vegetation");
	public static HorizontalPanel classCrGroundCheck = Elements.checkBox("Frozen ground");
	public static HorizontalPanel classCrGlaciersCheck = Elements.checkBox("Glaciers / Ice sheet");
	public static HorizontalPanel classCrIceCheck = Elements.checkBox("Sea ice");
	public static HorizontalPanel classCrSnowCheck = Elements.checkBox("Snow / Ice");
	public static HorizontalPanel classLsErosionCheck = Elements.checkBox("Erosion / Sedimentation");
	public static HorizontalPanel classLsGeomorphologyCheck = Elements.checkBox("Geomorphology");
	public static HorizontalPanel classLsTemperatureCheck = Elements.checkBox("Land temperature");
	public static HorizontalPanel classLsCoverCheck = Elements.checkBox("Land use / Land cover");
	public static HorizontalPanel classLsLandscapeCheck = Elements.checkBox("Landscape");
	public static HorizontalPanel classLsSurfaceCheck = Elements.checkBox("Surface radiative properties");
	public static HorizontalPanel classLsTopographyCheck = Elements.checkBox("Topography");
	public static HorizontalPanel classOcBathymetryCheck = Elements.checkBox("Bathymetry");
	public static HorizontalPanel classOcProcessesCheck = Elements.checkBox("Coastal processes");
	public static HorizontalPanel classOcEnvironmentCheck = Elements.checkBox("Marine environment");
	public static HorizontalPanel classOcGeophysicsCheck = Elements.checkBox("Marine geophysics");
	public static HorizontalPanel classOcWavesCheck = Elements.checkBox("Ocean waves");
	public static HorizontalPanel classOcWindsCheck = Elements.checkBox("Ocean winds");
	public static HorizontalPanel classOcTopographyCheck = Elements.checkBox("Sea surface topography");
	public static HorizontalPanel classOcTidesCheck = Elements.checkBox("Tides");
	public static HorizontalPanel classOcQualityCheck = Elements.checkBox("Water quality");
	public static HorizontalPanel classSeGeodeticsCheck = Elements.checkBox("Geodetics");
	public static HorizontalPanel classSeGeomagnetismCheck = Elements.checkBox("Geomagnetism");
	public static HorizontalPanel classSeLandformsCheck = Elements.checkBox("Geomorphic landforms");
	public static HorizontalPanel classSeGravityCheck = Elements.checkBox("Gravity");
	public static HorizontalPanel classSpGammaCheck = Elements.checkBox("Gamma ray");
	public static HorizontalPanel classSpInfraredCheck = Elements.checkBox("Infrared wavelengths");
	public static HorizontalPanel classSpLidarCheck = Elements.checkBox("LIDAR");
	public static HorizontalPanel classSpMicrowaveCheck = Elements.checkBox("Microwave");
	public static HorizontalPanel classSpRadarCheck = Elements.checkBox("RADAR");
	public static HorizontalPanel classSpRadioCheck = Elements.checkBox("Radio wave");
	public static HorizontalPanel classSpUltravioletCheck = Elements.checkBox("Ultraviolet wavelengths");
	public static HorizontalPanel classSpVisibleCheck = Elements.checkBox("Visible wavelengths");
	public static HorizontalPanel classSpXrayCheck = Elements.checkBox("X-ray");
	public static HorizontalPanel classInIonosphereCheck = Elements.checkBox("Ionosphere / Magnetosphere");
	public static HorizontalPanel classInActivityCheck = Elements.checkBox("Solar activity");
	public static HorizontalPanel classInParticleCheck = Elements.checkBox("Solar energetic particle");
	public static HorizontalPanel classThGroundCheck = Elements.checkBox("Ground water");
	public static HorizontalPanel classThSurfaceCheck = Elements.checkBox("Surface water");
	public static HorizontalPanel classThChemistryCheck = Elements.checkBox("Water quality / chemistry");
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
	private Label aiMainLab2 = new Label("EUFAR METADATA CREATOR");
	private Label aiMainLab = new Label("EUFAR METADATA CREATOR");
	private Label aiPathLab = new Label(">");
	private Label aiPathLab2 = new Label("Aircraft & Instruments");
	public static FlexTable airInstrumentTable = new FlexTable();
	public static FlexTable airAircraftTable = new FlexTable();
	private SimplePanel airPlatformInfo = Elements.addInfoButton("aiAircraft");
	private SimplePanel airInstrumentInfo = Elements.addInfoButton("aiInstrument");
	public static Label airPlusButton01 = new Label("Add this aircraft to the list");
	private Label airPlusButton02 = new Label("Add this instrument to the list");
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
	private Image airCopyrightImage = new Image(Emc_eufar.resources.copyright().getSafeUri());
	public static ListBox airAircraftLst = new ListBox();
	public static ListBox airInstrumentLst = new ListBox();
	private ArrayList<String> aircraftList = Materials.aircraftList();
	public static ArrayList<String> aircraftTabList = new ArrayList<String>();
	public static ArrayList<String> operatorTabList = new ArrayList<String>();
	public static ArrayList<String> manufacturairTabList = new ArrayList<String>();
	public static ArrayList<String> countryTabList = new ArrayList<String>();
	public static ArrayList<String> identificationTabList = new ArrayList<String>();
	public static ArrayList<String> instrumentTabList = new ArrayList<String>();
	public static ArrayList<String> manufacturerTabList = new ArrayList<String>();
	public static Image airAircraftImg = new Image(Emc_eufar.resources.eufarLogo());
	public static String[][] airAircraftInfo = Materials.aircraftInfo();
	public static ArrayList<ImageResource> airAircraftImages = Materials.aircraftImages();
	public static ScrollPanel aiScroll = new ScrollPanel(verticalPanel24);
	public static FlexTable aiFlexTable = new FlexTable();


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
	public static ArrayList<String> countryList = Materials.countryList();
	public static ArrayList<String> continentList = Materials.continentList();
	public static ArrayList<String> oceanList = Materials.oceanList();
	public static ArrayList<String> regionList = Materials.regionList();
	private ArrayList<String> locationList = Materials.locationList();
	private ArrayList<String> resolutionList = Materials.resolutionList();
	private ArrayList<String> unitList = Materials.unitList();
	public static TextBox geoNorthBox = new TextBox();
	public static TextBox geoSouthBox = new TextBox();
	public static TextBox geoEastBox = new TextBox();
	public static TextBox geoWestBox = new TextBox();
	public static TextBox geoResolutionBox = new TextBox();
	private Image geoFollowImage = new Image(Emc_eufar.resources.forward().getSafeUri());
	private Image geoCoordImage = new Image(Emc_eufar.resources.earth());
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
	public static Image refDelImage = new Image(Emc_eufar.resources.delete().getSafeUri());
	public static SimplePanel refDelButton = new SimplePanel(refDelImage);
	public static ScrollPanel trScroll = new ScrollPanel(verticalPanel26);

	
	// Quality and Validity items
	public static ScrollableTabLayoutPanel qvTabPanel = new ScrollableTabLayoutPanel(42, Unit.PX);
	public static VerticalPanel verticalPanel14 = new VerticalPanel();
	public static SimplePanel simplePanel01 = new SimplePanel();
	public static VerticalPanel verticalPanel27 = new VerticalPanel();
	private HorizontalPanel horizontalPanel30 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel79 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel80 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel116 = new HorizontalPanel();
	private HorizontalPanel horizontalPanel117 = new HorizontalPanel();
	private Label qvMainLab2 = new Label("EUFAR METADATA CREATOR");
	private Label qvMainLab = new Label("EUFAR METADATA CREATOR");
	private Label qvPathLab = new Label(">");
	private Label qvPathLab2 = new Label("Quality and Validity");
	private Label insituStarLab01 = new Label("*");
	private Label imageStarLab01 = new Label("*");
	private Label qvAddInsituLab = new Label("Atmospheric/In-situ data");
	private Label qvAddImageryLab = new Label("Earth observation/Remote sensing data");
	public static SimplePanel qvInfoButton01 = Elements.addInfoButton("qvLineage1");
	private SimplePanel qvAddInsituTabBut = Elements.plusButton("qvAddInsituTab");
	private SimplePanel qvAddImageryTabBut = Elements.plusButton("qvAddImageryTab");
	public static int insituNum = 0;
	public static int imageryNum = 0;
	public static int activeTab = 0;
	public static ScrollPanel qvScroll = new ScrollPanel(verticalPanel27);
	@SuppressWarnings("rawtypes")
	public static ArrayList<ArrayList> qvInsituMap = new ArrayList<ArrayList>();
	@SuppressWarnings("rawtypes")
	public static ArrayList<ArrayList> qvImageryMap = new ArrayList<ArrayList>();


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
	public static ArrayList<String> useCondCorrectLst = new ArrayList<String>();
	public static ArrayList<String> useLimCorrectLst = new ArrayList<String>();
	public static Image useDelImage1 = new Image(Emc_eufar.resources.delete().getSafeUri());
	public static Image useDelImage2 = new Image(Emc_eufar.resources.delete().getSafeUri());
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
	public static ArrayList<String> roleList = Materials.roleList();
	public static ArrayList<TextBox> orgPartyLst = new ArrayList<TextBox>();
	public static ArrayList<ListBox> orgRole2Lst = new ArrayList<ListBox>();
	public static ArrayList<TextBox> orgEmailLst = new ArrayList<TextBox>();
	public static ArrayList<String> orgPartyCorrectLst = new ArrayList<String>();
	public static ArrayList<String> orgEmailCorrectLst = new ArrayList<String>();
	public static FlexTable orgPartyTab = new FlexTable();
	public static FlexTable orgAddTab = new FlexTable();
	public static Image orgDelImage = new Image(Emc_eufar.resources.delete().getSafeUri());
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
	public static ArrayList<String> metNameCorrectLst = new ArrayList<String>();
	public static ArrayList<String> metEmailCorrectLst = new ArrayList<String>();
	public static Image metDelImage = new Image(Emc_eufar.resources.delete().getSafeUri());
	public static SimplePanel mmDelButton = new SimplePanel(metDelImage);
	public static ScrollPanel mmScroll = new ScrollPanel(verticalPanel30);
	
	
	// Preparing object lists for checking purpose
	public static ArrayList<TextBoxBase> allRequiredTextboxes = Materials.allRequiredTextboxes();
	public static ArrayList<DateBox> allRequiredDateboxes = Materials.allRequiredDateboxes();
	public static ArrayList<ListBox> allRequiredListboxes = Materials.allRequiredListboxes();
	public static ArrayList<HorizontalPanel> allRequiredClassCheckboxes = Materials.allRequiredClassCheckboxes();
	public static ArrayList<HorizontalPanel> allRequiredKeyCheckboxes = Materials.allRequiredKeyCheckboxes();
	
	
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
		horizontalPanel60.add(airManufacturerInfo);
		horizontalPanel60.add(airManufacturerBox);
		horizontalPanel61.add(airTypeInfo);
		horizontalPanel61.add(airTypeBox);
		horizontalPanel62.add(airOperatorInfo);
		horizontalPanel62.add(airOperatorBox);
		horizontalPanel63.add(airCountryInfo);
		horizontalPanel63.add(airCountryLst);
		horizontalPanel64.add(airRegistrationInfo);
		horizontalPanel64.add(airRegistrationBox);
		airManufacturerBox.setVisible(false);
		airTypeBox.setVisible(false);
		airOperatorBox.setVisible(false);
		airCountryLst.setVisible(false);
		airRegistrationBox.setVisible(false);
		aiFlexTable.setWidget(0, 0, airManufacturerLab);
		aiFlexTable.setWidget(0, 1, horizontalPanel60);
		aiFlexTable.setWidget(1, 0, airTypeLab);
		aiFlexTable.setWidget(1, 1, horizontalPanel61);
		aiFlexTable.setWidget(2, 0, airOperatorLab);
		aiFlexTable.setWidget(2, 1, horizontalPanel62);
		aiFlexTable.setWidget(3, 0, airCountryLab);
		aiFlexTable.setWidget(3, 1, horizontalPanel63);
		aiFlexTable.setWidget(4, 0, airRegistrationLab);
		aiFlexTable.setWidget(4, 1, horizontalPanel64);
		verticalPanel08.add(aiFlexTable);
		aiFlexTable.setStyleName("airFlexTable");
		aiFlexTable.getColumnFormatter().setWidth(0, "130px");
		aiFlexTable.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		aiFlexTable.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		aiFlexTable.getFlexCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		aiFlexTable.getFlexCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		aiFlexTable.getFlexCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		Emc_eufar.airAircraftImg.setSize("550px","302px");
		
		airCopyrightImage.setSize("12px","12px");
		airCopyrightImage.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 12px; width: 12px;");
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
		horizontalPanel12.add(airInstNameLab);
		horizontalPanel12.add(airInstNameBox);
		horizontalPanel12.add(airInstManufacturerLab);
		horizontalPanel12.add(airInstManufacturerBox);
		horizontalPanel12.add(airPlusButton02);
		horizontalPanel12.add(airInstrumentInfo);
		verticalPanel10.add(horizontalPanel12);
		verticalPanel10.add(airInstrumentTable);
		airInstrumentTable.setCellSpacing(10);
		airInstNameBox.setStyleName("airTextBox6");
		airInstNameBox.setName("instrument");
		airInstManufacturerBox.setName("instrument");
		airManufacturerBox.setName("aircraft");
		airTypeBox.setName("aircraft");
		airOperatorBox.setName("aircraft");
		airCountryLst.setName("aircraft");
		airRegistrationBox.setName("aircraft");
		airInstManufacturerBox.setStyleName("airTextBox6");
		airInstNameLab.setStyleName("airTitleTextLabel2");
		airInstManufacturerLab.setStyleName("airTitleTextLabel2");
		horizontalPanel65.getElement().setAttribute("style","margin-top: 13px;");
		airPlusButton01.setStyleName("airPushButton1");
		airPlusButton02.setStyleName("airPushButton1");
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
		airTypeInfo.setStyleName("airFlexTableLabel2");
		airOperatorInfo.setStyleName("airFlexTableLabel2");
		airCountryInfo.setStyleName("airFlexTableLabel2");
		airRegistrationInfo.setStyleName("airFlexTableLabel2");
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
		airPlusButton01.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (Emc_eufar.airAircraftLst.getSelectedItemText() != "Make a choice...") {
					GuiModification.addAircraftPlus();
				}
				
			}
		});
		airPlusButton02.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (Emc_eufar.airInstrumentLst.getSelectedItemText() != "Make a choice...") {
					GuiModification.addInstPlus();
				}
				
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

		geoFollowImage.setStyleName("geoForwardImage");
		
		verticalPanel11.add(horizontalPanel13);
		verticalPanel11.add(geoCoordTab);
		horizontalPanel16.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel16.add(geoResolutionLab);
		horizontalPanel16.add(geoResolutionLst);
		Utilities.populateListBox(geoResolutionLst, resolutionList, 0);
		geoResolutionLst.setName("geoResolutionList");
		horizontalPanel16.add(geoResolutionBox);
		horizontalPanel16.add(geoUnitLab);
		horizontalPanel16.add(geoUnitLst);
		horizontalPanel16.add(geoUnitInfo); 
		geoResolutionLab.setStyleName("geoTitleTextLabel");
		geoResolutionLst.setStyleName("geoTextList");
		geoResolutionBox.setStyleName("geoTextBox2");
		geoUnitLab.setStyleName("geoTitleTextLabel3");
		geoUnitLst.setStyleName("geoTextList");
		verticalPanel11.add(horizontalPanel16);
		verticalPanel11.setStyleName("geoVerticalPanel");
		geoCoordInfo.getElement().setAttribute("style", "margin-left: 40px !important;");
		geoBoundingLab.setStyleName("geoTitleTextLabel2");
		geoNorthBox.setStyleName("geoTextBox4");
		geoSouthBox.setStyleName("geoTextBox4");
		geoEastBox.setStyleName("geoTextBox");
		geoWestBox.setStyleName("geoTextBox");
		geoCoordImage.getElement().setAttribute("style", "margin-top: 5px !important; height: 300px !important; width: 298px !important;");
		geoCoordTab.getElement().setAttribute("style", "margin-top: 20px !important; margin-bottom: 30px !important;");
		geoUnitInfo.getElement().setAttribute("style", "margin-top: 1px !important;");
		horizontalPanel76.setStyleName("identLine");
		geoMainLab.setStyleName("identMainText");
		geoPathLab.setStyleName("identPathText");
		geoPathLab2.setStyleName("identPathText2");
		geoUnitLab.setVisible(false);
		geoUnitLst.setVisible(false);
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
		refDelImage.setSize("21px","21px");
		refDelImage.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		refDelImage.setVisible(false);
		refDelImage.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				refStartDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(
						DateTimeFormat.getFormat("yyyy-MM-dd").format(refStartLst.get(1).getValue())));
				refEndDat.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(
						DateTimeFormat.getFormat("yyyy-MM-dd").format(refEndLst.get(1).getValue())));
				refPhaseTab.removeRow(1);
				refStartLst.remove(1);
				refEndLst.remove(1);
				List<Label> allLabel = $("*", refPhaseTab).widgets(Label.class);
				int id = 0;
				for (Object o : allLabel) {
					((Label) o).setText("Phase " + Integer.toString(id + 1));
					id++;
				}
				int row = refPhaseTab.getRowCount();
				if (row == 1) {
					refDelImage.setVisible(false);
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
		horizontalPanel116.add(qvAddImageryLab);
		horizontalPanel116.add(imageStarLab01);
		horizontalPanel117.add(qvAddInsituLab);
		horizontalPanel117.add(insituStarLab01);
		horizontalPanel30.add(qvAddImageryTabBut);
		horizontalPanel30.add(horizontalPanel116);
		horizontalPanel30.add(qvAddInsituTabBut);
		horizontalPanel30.add(horizontalPanel117);
		horizontalPanel30.add(qvInfoButton01);
		verticalPanel14.add(horizontalPanel30);
		verticalPanel14.add(simplePanel01);
		verticalPanel27.add(verticalPanel14);
		imageStarLab01.setStyleName("qvStarLabel");
		insituStarLab01.setStyleName("qvStarLabel");
		qvAddImageryLab.setStyleName("qv_imageLabMain");
		qvAddInsituLab.setStyleName("qv_insituLabMain");
		horizontalPanel80.setStyleName("identLine");
		verticalPanel14.setStyleName("qvVerticalPanel");
		simplePanel01.getElement().setAttribute("style", "margin: 10px !important; margin-bottom: 0px !important;");
		qvMainLab.setStyleName("identMainText");
		qvPathLab.setStyleName("identPathText");
		qvPathLab2.setStyleName("identPathText2");
		qvMainLab2.setStyleName("identMainText2");
		horizontalPanel30.getElement().setAttribute("style", "margin-left: 200px;");
		horizontalPanel116.getElement().setAttribute("style", "margin-left: 10px; margin-top:3px; margin-right: 100px;");
		horizontalPanel117.getElement().setAttribute("style", "margin-left: 10px; margin-top:3px; margin-right: 100px;");
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
		useCondCorrectLst.add("string");
		useLimCorrectLst.add("string");
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
		useDelImage1.setSize("21px","21px");
		useDelImage1.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		useDelImage2.setSize("21px","21px");
		useDelImage2.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		useDelImage1.setVisible(false);
		useDelImage2.setVisible(false);
		useDelImage1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				useConditionsBox.setText(useConditionsLst.get(1).getText());
				useConditionsAddTab.removeRow(1);
				useConditionsLst.remove(1);
				useCondCorrectLst.remove(1);
				int row = Emc_eufar.useConditionsAddTab.getRowCount();
				if (row == 1) {
					Emc_eufar.useDelImage1.setVisible(false);
				}
			}
		});
		useDelImage2.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				useLimitationsBox.setText(useLimitationsLst.get(1).getText());
				useLimitationsAddTab.removeRow(1);
				useLimitationsLst.remove(1);
				useLimCorrectLst.remove(1);
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
		orgDelImage.setSize("21px","21px");
		orgDelImage.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
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
		orgPartyCorrectLst.add("string");
		orgEmailCorrectLst.add("email");
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
		orgDelImage.addClickHandler(new ClickHandler() {
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
				Emc_eufar.orgPartyCorrectLst.remove(1);
				Emc_eufar.orgEmailCorrectLst.remove(1);
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
		metDelImage.setSize("21px","21px");
		metDelImage.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
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
		metNameCorrectLst.add("string");
		metEmailCorrectLst.add("email");
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
		metDelImage.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				metNameBox.setText(Emc_eufar.metNameLst.get(1).getText());
				metEmailBox.setText(Emc_eufar.metEmailLst.get(1).getText());
				Emc_eufar.metAddTab.removeRow(1);
				Emc_eufar.metNameLst.remove(1);
				Emc_eufar.metEmailLst.remove(1);
				metNameCorrectLst.remove(1);
				metEmailCorrectLst.remove(1);
				int row = Emc_eufar.metAddTab.getRowCount();
				if (row == 1) {
					metDelImage.setVisible(false);
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
				public void onChange(ChangeEvent event) {
					Utilities.docIsModified();
				}
			});
		}


		// Associate checkboxes with an eventHandler
		List<CheckBox> allCheckBoxes = $("*", subDockPanel).widgets(CheckBox.class);
		for (int i = 0; i < allCheckBoxes.size(); i++) {
			allCheckBoxes.get(i).addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Utilities.docIsModified();
				}
			});
		}


		// Associate listboxes with an evenHandler
		List<ListBox> allListBoxes = $("*", subDockPanel).widgets(ListBox.class);
		for (int i = 0; i < allListBoxes.size(); i++) {
			allListBoxes.get(i).addChangeHandler(new ChangeHandler() {
				@Override
				public void onChange(ChangeEvent event) {
					Utilities.docIsModified();
				}
			});
		}


		// Associate dateboxes with an eventHandler
		List<DateBox> allDateBoxes = $("*", subDockPanel).widgets(DateBox.class);
		for (int i = 0; i < allDateBoxes.size(); i++) {
			allDateBoxes.get(i).addValueChangeHandler(new ValueChangeHandler<Date>() {
				@Override
				public void onValueChange(ValueChangeEvent<Date> event) {
					Utilities.docIsModified();
				}
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
			final Image image = new Image(Emc_eufar.resources.warningPopup().getSafeUri());
			image.setSize("68px", "68px");
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static
	void runCheck(final String string) {
		rootLogger.log(Level.INFO, "Check of all fields in progress...");
		int notCompleted = 0;
		int widgetIndex = -1;
		Widget parent;
		allTextBoxes.clear();
		allTextboxFields.clear();
		allRadioButtons.clear();
		allCheckBoxes.clear();
		allListBoxes.clear();
		allDateBoxes.clear();
		allTextBoxes.addAll(allRequiredTextboxes);
		allTextboxFields.addAll(allCorrectFields);
		allDateBoxes.addAll(allRequiredDateboxes);
		allDateBoxes.addAll(refStartLst.subList(1, refStartLst.size()));
		allDateBoxes.addAll(refEndLst.subList(1, refEndLst.size()));
		allTextBoxes.addAll(useConditionsLst.subList(1, useConditionsLst.size()));
		allTextBoxes.addAll(useLimitationsLst.subList(1, useLimitationsLst.size()));
		allTextboxFields.addAll(useCondCorrectLst.subList(1, useConditionsLst.size()));
		allTextboxFields.addAll(useLimCorrectLst.subList(1, useLimitationsLst.size()));
		allTextBoxes.addAll(orgPartyLst.subList(1, orgPartyLst.size()));
		allTextBoxes.addAll(orgEmailLst.subList(1, orgEmailLst.size()));
		allTextboxFields.addAll(orgPartyCorrectLst.subList(1, orgPartyCorrectLst.size()));
		allTextboxFields.addAll(orgEmailCorrectLst.subList(1, orgEmailCorrectLst.size()));
		allTextBoxes.addAll(metNameLst.subList(1, metNameLst.size()));
		allTextBoxes.addAll(metEmailLst.subList(1, metEmailLst.size()));
		allTextboxFields.addAll(metNameCorrectLst.subList(1, metNameCorrectLst.size()));
		allTextboxFields.addAll(metEmailCorrectLst.subList(1, metNameCorrectLst.size()));
		allListBoxes.addAll(allRequiredListboxes);
		if (qvTabPanel.getWidgetCount() >= 1) {
			for (int i = 0; i < qvInsituMap.size(); i++) {
				ArrayList<ArrayList> insituAllLists = qvInsituMap.get(i);
				allTextBoxes.addAll(insituAllLists.get(0));
				allRadioButtons.addAll(insituAllLists.get(1));
				allCheckBoxes.addAll(insituAllLists.get(2));
				allListBoxes.addAll(insituAllLists.get(3));
				allTextboxFields.addAll(insituAllLists.get(4));
			}
			for (int i = 0; i < qvImageryMap.size(); i++) {
				ArrayList<ArrayList> imageryAllLists = qvImageryMap.get(i);
				allTextBoxes.addAll(imageryAllLists.get(0));
				allRadioButtons.addAll(imageryAllLists.get(1));
				allListBoxes.addAll(imageryAllLists.get(2));
				allDateBoxes.addAll(imageryAllLists.get(3));
				allTextboxFields.addAll(imageryAllLists.get(4));
			}
		}
		Utilities.runCheckDefault();
		rootLogger.log(Level.INFO, "Checking textboxes ...");
		for (int i = 0; i < allTextBoxes.size(); i++) {
			if (allTextBoxes.get(i).isVisible()) {
				if (allTextBoxes.get(i).getText() == "") {
					parent = allTextBoxes.get(i).getParent();
					try {
						while (!(parent instanceof SimplePanel)) {
							parent = parent.getParent();
						}
						widgetIndex = qvTabPanel.getWidgetIndex(parent);
						qvTabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: #ED1C24 !important;");
						tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
					} catch (AssertionError|IndexOutOfBoundsException ex) {
						while (!(parent instanceof ScrollPanel)) {
							parent = parent.getParent();
						}
						widgetIndex = tabPanel.getWidgetIndex(parent);
						if (allTextBoxes.get(i).getName() == "aircraft") {
							if (aircraftTabList.size() == 0) {
								tabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: #ED1C24 !important;");
							}
						} else if (allTextBoxes.get(i).getName() == "instrument") {
							if (instrumentTabList.size() == 0) {
								tabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: #ED1C24 !important;");
							}
						} else {
							tabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: #ED1C24 !important;");
						}
					}
					if (allTextBoxes.get(i).getName() == "aircraft") {
						if (aircraftTabList.size() == 0) {
							notCompleted++;
							allTextBoxes.get(i).getElement().setAttribute("style","border-color: #ED1C24 !important;");
						}
					} else if (allTextBoxes.get(i).getName() == "instrument") {
						if (instrumentTabList.size() == 0) {
							notCompleted++;
							allTextBoxes.get(i).getElement().setAttribute("style","border-color: #ED1C24 !important;");
						}
					} else {
						notCompleted++;
						allTextBoxes.get(i).getElement().setAttribute("style","border-color: #ED1C24 !important;");
					}
				} else {
					boolean textboxCorrect = false;
					if (allTextBoxes.get(i).getName() == "aircraft") {
						if (aircraftTabList.size() == 0) {
							textboxCorrect = runCorrect(allTextBoxes.get(i), allTextboxFields.get(i));
						}
					} else if (allTextBoxes.get(i).getName() == "instrument") {
						if (instrumentTabList.size() == 0) {
							textboxCorrect = runCorrect(allTextBoxes.get(i), allTextboxFields.get(i));
						}
					} else {
						textboxCorrect = runCorrect(allTextBoxes.get(i), allTextboxFields.get(i));
					}
					if (!textboxCorrect) {
						parent = allTextBoxes.get(i).getParent();
						try {
							while (!(parent instanceof SimplePanel)) {
								parent = parent.getParent();
							}
							widgetIndex = qvTabPanel.getWidgetIndex(parent);
							if (qvTabPanel.getTabWidget(widgetIndex).getElement().getStyle().getColor() != "rgb(237, 28, 36)") {
								qvTabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: rgb(0,0,200) !important;");
							}
							if (tabPanel.getTabWidget(6).getElement().getStyle().getColor() != "rgb(237, 28, 36)") {
								tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(0,0,200) !important;");
							}
						} catch (AssertionError|IndexOutOfBoundsException ex) {
							while (!(parent instanceof ScrollPanel)) {
								parent = parent.getParent();
							}
							widgetIndex = tabPanel.getWidgetIndex(parent);
							if (tabPanel.getTabWidget(widgetIndex).getElement().getStyle().getColor() != "rgb(237, 28, 36)") {
								tabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: rgb(0,0,200) !important;");
							}
						}
						notCompleted++;
					}
				}
			}
		}
		rootLogger.log(Level.INFO, "Checking textboxes finished...");
		rootLogger.log(Level.INFO, "Checking dateboxes ...");
		for (int i = 0; i < allDateBoxes.size(); i++) {
			if (allDateBoxes.get(i).getValue().after(actualDate)) {
				allDateBoxes.get(i).getElement().setAttribute("style","border-color: rgb(0,0,200) !important;");
				parent = allDateBoxes.get(i).getParent();
				try {
					while (!(parent instanceof SimplePanel)) {
						parent = parent.getParent();
					}
					widgetIndex = qvTabPanel.getWidgetIndex(parent);
					if (qvTabPanel.getTabWidget(widgetIndex).getElement().getStyle().getColor() != "rgb(237, 28, 36)") {
						qvTabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: rgb(0,0,200) !important;");
					}
					if (tabPanel.getTabWidget(6).getElement().getStyle().getColor() != "rgb(237, 28, 36)") {
						tabPanel.getTabWidget(6).getElement().setAttribute("style","color: rgb(0,0,200) !important;");
					}
				} catch (AssertionError|IndexOutOfBoundsException ex) {
					while (!(parent instanceof ScrollPanel)) {
						parent = parent.getParent();
					}
					widgetIndex = tabPanel.getWidgetIndex(parent);
					if (tabPanel.getTabWidget(widgetIndex).getElement().getStyle().getColor() != "rgb(237, 28, 36)") {
						tabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: rgb(0,0,200) !important;");
					}
				}
				notCompleted++;
			}
		}
		rootLogger.log(Level.INFO, "Checking dateboxes finished...");
		rootLogger.log(Level.INFO, "Checking listboxes ...");
		for (int i = 0; i < allListBoxes.size(); i++) {
			if (allListBoxes.get(i).isVisible() && allListBoxes.get(i).isEnabled()) {
				if (allListBoxes.get(i).getSelectedItemText() == "Make a choice...") {
					parent = allListBoxes.get(i).getParent();
					try {
						while (!(parent instanceof SimplePanel)) {
							parent = parent.getParent();
						}
						widgetIndex = qvTabPanel.getWidgetIndex(parent);
						qvTabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: #ED1C24 !important;");
						tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
					} catch (AssertionError|IndexOutOfBoundsException ex) {
						while (!(parent instanceof ScrollPanel)) {
							parent = parent.getParent();
						}
						widgetIndex = tabPanel.getWidgetIndex(parent);
						if (widgetIndex == 3) {
							if (aircraftTabList.size() == 0) {
								tabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: #ED1C24 !important;");
							}
						} else {
							tabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: #ED1C24 !important;");
						}
					}
					if (widgetIndex == 3) {
						if (aircraftTabList.size() == 0) {
							notCompleted++;
							allListBoxes.get(i).getElement().setAttribute("style","border-color: #ED1C24 !important;");
						}
					} else {
						notCompleted++;
						allListBoxes.get(i).getElement().setAttribute("style","border-color: #ED1C24 !important;");
					}
				}
			}
		}
		rootLogger.log(Level.INFO, "Checking listboxes finished...");
		rootLogger.log(Level.INFO, "Checking checkboxes ...");
		boolean oneCheckboxChecked = false;
		for (int i = 0; i < allRequiredClassCheckboxes.size(); i++) {
			if (((CheckBox) allRequiredClassCheckboxes.get(i).getWidget(0)).getValue() == true) {
				oneCheckboxChecked = true;
				break;
			}
		}
		if (oneCheckboxChecked == false) {
			notCompleted++;
			tabPanel.getTabWidget(1).getElement().setAttribute("style","color: #ED1C24 !important;");
		}
		
		oneCheckboxChecked = false;
		for (int i = 0; i < allRequiredKeyCheckboxes.size(); i++) {
			if (((CheckBox) allRequiredKeyCheckboxes.get(i).getWidget(0)).getValue() == true) {
				oneCheckboxChecked = true;
				break;
			}
		}
		if (oneCheckboxChecked == false) {
			notCompleted++;
			tabPanel.getTabWidget(2).getElement().setAttribute("style","color: #ED1C24 !important;");
		}
		rootLogger.log(Level.INFO, "Checking checkboxes finished...");
		rootLogger.log(Level.INFO, "Checking aircraft and instrument lists ...");
		if (aircraftTabList.size() == 0) {
			notCompleted++;
			tabPanel.getTabWidget(3).getElement().setAttribute("style","color: #ED1C24 !important;");
			airAircraftLst.getElement().setAttribute("style","border-color: #ED1C24 !important;");
		}
		if (instrumentTabList.size() == 0) {
			notCompleted++;
			tabPanel.getTabWidget(3).getElement().setAttribute("style","color: #ED1C24 !important;");
			airInstrumentLst.getElement().setAttribute("style","border-color: #ED1C24 !important;");
		}
		rootLogger.log(Level.INFO, "Checking aircraft and instrument lists finished...");
		rootLogger.log(Level.INFO, "Checking QV forms ...");
		if (qvTabPanel.getWidgetCount() < 1) {
			notCompleted++;
			tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
		} else {
			for (int i = 0; i < allCheckBoxes.size(); i++) {
				VerticalPanel verticalPanel01 = (VerticalPanel) allCheckBoxes.get(i).getWidget(0);
				VerticalPanel verticalPanel02 = (VerticalPanel) allCheckBoxes.get(i).getWidget(1);
				HorizontalPanel netcdfCheckBox = (HorizontalPanel) verticalPanel01.getWidget(0);
				HorizontalPanel hdfCheckBox = (HorizontalPanel) verticalPanel01.getWidget(1);
				HorizontalPanel amesCheckBox = (HorizontalPanel) verticalPanel02.getWidget(0);
				HorizontalPanel otherPanel = (HorizontalPanel) verticalPanel02.getWidget(1);
				HorizontalPanel otherCheckBox = (HorizontalPanel) otherPanel.getWidget(0);
				if (((CheckBox) netcdfCheckBox.getWidget(0)).getValue() == false 
						&& ((CheckBox) hdfCheckBox.getWidget(0)).getValue() == false
						&& ((CheckBox) amesCheckBox.getWidget(0)).getValue() == false
						&& ((CheckBox) otherCheckBox.getWidget(0)).getValue() == false) {
					parent = allCheckBoxes.get(i).getParent();
					while (!(parent instanceof SimplePanel)) {
						parent = parent.getParent();
					}
					widgetIndex = qvTabPanel.getWidgetIndex(parent);
					allCheckBoxes.get(i).getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
					qvTabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: #ED1C24 !important;");
					tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
					notCompleted++;
				}
			}
			for (int i = 0; i < allRadioButtons.size(); i++) {
				HorizontalPanel yesCheckBox = (HorizontalPanel) allRadioButtons.get(i).getWidget(0);
				HorizontalPanel noCheckBox = (HorizontalPanel) allRadioButtons.get(i).getWidget(1);
				if (((CheckBox) yesCheckBox.getWidget(0)).getValue() == false 
						&& ((CheckBox) noCheckBox.getWidget(0)).getValue() == false) {
					parent = allRadioButtons.get(i).getParent();
					while (!(parent instanceof SimplePanel)) {
						parent = parent.getParent();
					}
					widgetIndex = qvTabPanel.getWidgetIndex(parent);
					allRadioButtons.get(i).getElement().setAttribute("style","border: 1px solid #ED1C24 !important;");
					qvTabPanel.getTabWidget(widgetIndex).getElement().setAttribute("style","color: #ED1C24 !important;");
					tabPanel.getTabWidget(6).getElement().setAttribute("style","color: #ED1C24 !important;");
					notCompleted++;
				}
			}
		}
		rootLogger.log(Level.INFO, "Checking QV forms finished...");
		rootLogger.log(Level.INFO, "Check of all fields finished.");
		if (notCompleted > 0) {
			PopupMessages.notcompletePanel(string);
		} else {
			PopupMessages.saveFile(string);
		}
	}
	
	
	// check if all TextBoxes have been correctly filled in before saving it - 2
	private static boolean runCorrect(final TextBoxBase textBox, final String string) {
		Emc_eufar.rootLogger.log(Level.INFO, "Check of text in textbox in progress...");
		boolean result = true;
			switch (string) {
				case "number":
					if (!textBox.getText().matches("^[-+]?\\d+(\\.\\d+)?$")) {
						textBox.getElement().setAttribute("style","border-color: rgb(0,0,200) !important;");
						result = false;
					}
					break;
				case "string":
					if (textBox.getText().matches("^[-+]?\\d+(\\.\\d+)?$")) {
						textBox.getElement().setAttribute("style","border-color: rgb(0,0,200) !important;");
						result = false;
					}
					break;
				case "email":
					if (!textBox.getText().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
						textBox.getElement().setAttribute("style","border-color: rgb(0,0,200) !important;");
						result = false;
					}
					break;
			}
		rootLogger.log(Level.INFO, "Check of text in textbox finished.");
		return result;
	}
	

	// functions to navigate between tabs
	private void navigateTab(final int direction) {
		rootLogger.log(Level.INFO, "Navigation by arrow invoked.");
		int selected_tab = tabPanel.getSelectedIndex();
		int new_tab = selected_tab + direction;
		if (new_tab < 0) {
			new_tab = 9;
		} else if (new_tab > 9) {
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
