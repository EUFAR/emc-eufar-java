package com.eufar.emc.client;

import static com.google.gwt.query.client.GQuery.$;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.datepicker.client.DateBox;

public class GuiModification {
	
	
	// change the resolution type, scale or distance, in theGeographic Information tab
	protected static void geoResolutionSet(final int index) {
		Emc_eufar.rootLogger.log(Level.INFO, "Resolution change invoked...");
		Emc_eufar.horizontalPanel17.clear();
		if (index == 0) {
			Emc_eufar.horizontalPanel17.add(Emc_eufar.geoResolutionBox);
			Emc_eufar.geoResolutionBox.setStyleName("geoTextBox2");
			Emc_eufar.rootLogger.log(Level.INFO, "Changed to scale.");
		} else {
			Emc_eufar.horizontalPanel17.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
			Emc_eufar.horizontalPanel17.add(Emc_eufar.geoResolutionBox);
			Emc_eufar.horizontalPanel17.add(Emc_eufar.geoUnitLab);
			Emc_eufar.horizontalPanel17.add(Emc_eufar.geoUnitLst);
			Emc_eufar.geoResolutionBox.setStyleName("geoTextBox3");
			Emc_eufar.geoUnitLst.setStyleName("geoTextList");
			Emc_eufar.geoUnitLab.setStyleName("geoTitleTextLabel3");
			Emc_eufar.rootLogger.log(Level.INFO, "Changed to distance.");
		}
	}
	
	
	// add a textbox to the format when "other" is selected in the QV section
	public static void otherFormat() {
		if (((CheckBox) Emc_eufar.insituChk07.getWidget(0)).getValue() == true) {
			Emc_eufar.horizontalPanel33.add(Emc_eufar.insituImage);
			Emc_eufar.horizontalPanel33.add(Emc_eufar.insituOtherBox);
			Emc_eufar.insituImage.getElement().setAttribute("style", "margin-left: 23px; margin-top: -5px;");
			Emc_eufar.insituOtherBox.setStyleName("qv_otherBox");
		} else {
			Emc_eufar.horizontalPanel33.clear();
		}
	}
	
	
	// change quality and validity target
	public static void changeTarget(final String string) {
		if (string == "Earth observation/Remote sensing data") {
			Emc_eufar.verticalPanel17.clear();
			Emc_eufar.horizontalPanel39.add(Emc_eufar.imageCalLab);
			Emc_eufar.horizontalPanel39.add(Emc_eufar.qvInfoButton06);
			Emc_eufar.horizontalPanel40.add(Emc_eufar.imageBanBox);
			Emc_eufar.horizontalPanel40.add(Emc_eufar.qvInfoButton07);
			Emc_eufar.horizontalPanel41.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
			Emc_eufar.horizontalPanel41.add(Emc_eufar.imageChk10Y);
			Emc_eufar.horizontalPanel41.add(Emc_eufar.imageChk10N);
			Emc_eufar.horizontalPanel41.add(Emc_eufar.qvInfoButton08);
			Emc_eufar.horizontalPanel42.add(Emc_eufar.imageLayLab);
			Emc_eufar.horizontalPanel42.add(Emc_eufar.qvInfoButton09);
			Emc_eufar.horizontalPanel43.add(Emc_eufar.imageErrcorrLab);
			Emc_eufar.horizontalPanel43.add(Emc_eufar.qvInfoButton14);
			Emc_eufar.horizontalPanel44.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
			Emc_eufar.horizontalPanel44.add(Emc_eufar.imageChk11Y);
			Emc_eufar.horizontalPanel44.add(Emc_eufar.imageChk11N);
			Emc_eufar.horizontalPanel44.add(Emc_eufar.qvInfoButton10);
			Emc_eufar.horizontalPanel45.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
			Emc_eufar.horizontalPanel45.add(Emc_eufar.imageChk12Y);
			Emc_eufar.horizontalPanel45.add(Emc_eufar.imageChk12N);
			Emc_eufar.horizontalPanel45.add(Emc_eufar.qvInfoButton11);
			Emc_eufar.horizontalPanel46.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
			Emc_eufar.horizontalPanel46.add(Emc_eufar.imageChk13Y);
			Emc_eufar.horizontalPanel46.add(Emc_eufar.imageChk13N);
			Emc_eufar.horizontalPanel46.add(Emc_eufar.qvInfoButton12);
			Emc_eufar.horizontalPanel47.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
			Emc_eufar.horizontalPanel47.add(Emc_eufar.imageChk14Y);
			Emc_eufar.horizontalPanel47.add(Emc_eufar.imageChk14N);
			Emc_eufar.horizontalPanel47.add(Emc_eufar.qvInfoButton13);
			Emc_eufar.horizontalPanel41.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
			Emc_eufar.horizontalPanel48.add(Emc_eufar.imageChk15Y);
			Emc_eufar.horizontalPanel48.add(Emc_eufar.imageChk15N);
			Emc_eufar.horizontalPanel49.add(Emc_eufar.imageChk16Y);
			Emc_eufar.horizontalPanel49.add(Emc_eufar.imageChk16N);
			Emc_eufar.horizontalPanel50.add(Emc_eufar.imageChk17Y);
			Emc_eufar.horizontalPanel50.add(Emc_eufar.imageChk17N);
			Emc_eufar.horizontalPanel51.add(Emc_eufar.imageChk18Y);
			Emc_eufar.horizontalPanel51.add(Emc_eufar.imageChk18N);
			Emc_eufar.horizontalPanel52.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
			Emc_eufar.horizontalPanel52.add(Emc_eufar.imageChk19Y);
			Emc_eufar.horizontalPanel52.add(Emc_eufar.imageChk19N);
			Emc_eufar.horizontalPanel52.add(Emc_eufar.qvInfoButton15);
			Emc_eufar.horizontalPanel53.add(Emc_eufar.imageChk20Y);
			Emc_eufar.horizontalPanel53.add(Emc_eufar.imageChk20N);
			Emc_eufar.horizontalPanel54.add(Emc_eufar.imageChk21Y);
			Emc_eufar.horizontalPanel54.add(Emc_eufar.imageChk21N);
			Emc_eufar.horizontalPanel55.add(Emc_eufar.imageChk22Y);
			Emc_eufar.horizontalPanel55.add(Emc_eufar.imageChk22N);
			Emc_eufar.horizontalPanel56.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
			Emc_eufar.horizontalPanel56.add(Emc_eufar.imageChk23Y);
			Emc_eufar.horizontalPanel56.add(Emc_eufar.imageChk23N);
			Emc_eufar.horizontalPanel56.add(Emc_eufar.qvInfoButton16);
			Emc_eufar.horizontalPanel57.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
			Emc_eufar.horizontalPanel57.add(Emc_eufar.imageChk24Y);
			Emc_eufar.horizontalPanel57.add(Emc_eufar.imageChk24N);
			Emc_eufar.horizontalPanel57.add(Emc_eufar.qvInfoButton17);
			Emc_eufar.horizontalPanel58.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
			Emc_eufar.horizontalPanel58.add(Emc_eufar.imageChk25Y);
			Emc_eufar.horizontalPanel58.add(Emc_eufar.imageChk25N);
			Emc_eufar.horizontalPanel58.add(Emc_eufar.qvInfoButton18);
			Emc_eufar.imageCalTab.setWidget(0, 0, Emc_eufar.imageNameLab);
			Emc_eufar.imageCalTab.setWidget(0, 1, Emc_eufar.imageCalBox);
			Emc_eufar.imageCalTab.setWidget(1, 0, Emc_eufar.imageRadLab);
			Emc_eufar.imageCalTab.setWidget(1, 1, Emc_eufar.imageRadDat);
			Emc_eufar.imageCalTab.setWidget(2, 0, Emc_eufar.imageSpeLab);
			Emc_eufar.imageCalTab.setWidget(2, 1, Emc_eufar.imageSpeDat);
			Emc_eufar.imageAcqTab.setWidget(0, 0, Emc_eufar.imageBanLab);
			Emc_eufar.imageAcqTab.setWidget(0, 1, Emc_eufar.horizontalPanel40);
			Emc_eufar.imageAcqTab.setWidget(1, 0, Emc_eufar.imageDirLab);
			Emc_eufar.imageAcqTab.setWidget(1, 1, Emc_eufar.imageDirBox);
			Emc_eufar.imageAcqTab.setWidget(2, 0, Emc_eufar.imageAltLab);
			Emc_eufar.imageAcqTab.setWidget(2, 1, Emc_eufar.imageAltBox);
			Emc_eufar.imageAcqTab.setWidget(3, 0, Emc_eufar.imageZenLab);
			Emc_eufar.imageAcqTab.setWidget(3, 1, Emc_eufar.imageZenBox);
			Emc_eufar.imageAcqTab.setWidget(4, 0, Emc_eufar.imageAziLab);
			Emc_eufar.imageAcqTab.setWidget(4, 1, Emc_eufar.imageAziBox);
			Emc_eufar.imageAcqTab.setWidget(5, 0, Emc_eufar.imageAnoLab);
			Emc_eufar.imageAcqTab.setWidget(5, 1, Emc_eufar.imageAnoBox);
			Emc_eufar.imageProTab.setWidget(0, 0, Emc_eufar.imageLevLab);
			Emc_eufar.imageProTab.setWidget(0, 1, Emc_eufar.imageLevLst);
			Emc_eufar.imageProTab.setWidget(1, 0, Emc_eufar.imageDCLab);
			Emc_eufar.imageProTab.setWidget(1, 1, Emc_eufar.horizontalPanel41);
			Emc_eufar.imageCalcorrTab.setWidget(0, 0, Emc_eufar.imageIntPixel);
			Emc_eufar.imageCalcorrTab.setWidget(0, 1, Emc_eufar.horizontalPanel44);
			Emc_eufar.imageCalcorrTab.setWidget(1, 0, Emc_eufar.imageBadPixel);
			Emc_eufar.imageCalcorrTab.setWidget(1, 1, Emc_eufar.horizontalPanel45);
			Emc_eufar.imageErrTab.setWidget(0, 0, Emc_eufar.imageSatPixel);
			Emc_eufar.imageErrTab.setWidget(0, 1, Emc_eufar.horizontalPanel46);
			Emc_eufar.imageErrTab.setWidget(1, 0, Emc_eufar.imageAffPixel);
			Emc_eufar.imageErrTab.setWidget(1, 1, Emc_eufar.horizontalPanel47);
			Emc_eufar.imageErrcorrTab.setWidget(0, 0, Emc_eufar.imagePosInfo);
			Emc_eufar.imageErrcorrTab.setWidget(0, 1, Emc_eufar.horizontalPanel48);
			Emc_eufar.imageErrcorrTab.setWidget(1, 0, Emc_eufar.imageAttInfo);
			Emc_eufar.imageErrcorrTab.setWidget(1, 1, Emc_eufar.horizontalPanel49);
			Emc_eufar.imageErrcorrTab.setWidget(2, 0, Emc_eufar.imageSyncProblem);
			Emc_eufar.imageErrcorrTab.setWidget(2, 1, Emc_eufar.horizontalPanel50);
			Emc_eufar.imageErrcorrTab.setWidget(3, 0, Emc_eufar.imageIntGeocoding);
			Emc_eufar.imageErrcorrTab.setWidget(3, 1, Emc_eufar.horizontalPanel51);
			Emc_eufar.imageCorrconTab.setWidget(0, 0, Emc_eufar.imageAtmCorrection);
			Emc_eufar.imageCorrconTab.setWidget(0, 1, Emc_eufar.horizontalPanel52);
			Emc_eufar.imageCorrconTab.setWidget(1, 0, Emc_eufar.imageCloudMask);
			Emc_eufar.imageCorrconTab.setWidget(1, 1, Emc_eufar.horizontalPanel53);
			Emc_eufar.imageCorrconTab.setWidget(2, 0, Emc_eufar.imageShadMask);
			Emc_eufar.imageCorrconTab.setWidget(2, 1, Emc_eufar.horizontalPanel54);
			Emc_eufar.imageCorrconTab.setWidget(3, 0, Emc_eufar.imageHazeMask);
			Emc_eufar.imageCorrconTab.setWidget(3, 1, Emc_eufar.horizontalPanel55);
			Emc_eufar.imageCorrconTab.setWidget(4, 0, Emc_eufar.imageRouMeasure);
			Emc_eufar.imageCorrconTab.setWidget(4, 1, Emc_eufar.horizontalPanel56);
			Emc_eufar.imageCorrconTab.setWidget(5, 0, Emc_eufar.imageIllAngle);
			Emc_eufar.imageCorrconTab.setWidget(5, 1, Emc_eufar.horizontalPanel57);
			Emc_eufar.imageCorrconTab.setWidget(6, 0, Emc_eufar.imageBRDFGeometry);
			Emc_eufar.imageCorrconTab.setWidget(6, 1, Emc_eufar.horizontalPanel58);
			Emc_eufar.verticalPanel17.add(Emc_eufar.horizontalPanel39);
			Emc_eufar.verticalPanel17.add(Emc_eufar.imageCalTab);
			Emc_eufar.verticalPanel17.add(Emc_eufar.imageAcqLab);
			Emc_eufar.verticalPanel17.add(Emc_eufar.imageAcqTab);
			Emc_eufar.verticalPanel17.add(Emc_eufar.imageProLab);
			Emc_eufar.verticalPanel17.add(Emc_eufar.imageProTab);
			Emc_eufar.verticalPanel17.add(Emc_eufar.horizontalPanel42);
			Emc_eufar.verticalPanel17.add(Emc_eufar.imageCalcorrLab);
			Emc_eufar.verticalPanel17.add(Emc_eufar.imageCalcorrTab);
			Emc_eufar.verticalPanel17.add(Emc_eufar.imageErrLab);
			Emc_eufar.verticalPanel17.add(Emc_eufar.imageErrTab);
			Emc_eufar.verticalPanel17.add(Emc_eufar.horizontalPanel43);
			Emc_eufar.verticalPanel17.add(Emc_eufar.imageErrcorrTab);
			Emc_eufar.verticalPanel17.add(Emc_eufar.imageCorrconLab);
			Emc_eufar.verticalPanel17.add(Emc_eufar.imageCorrconTab);
			Emc_eufar.imageCalLab.setStyleName("qv_imageLabelTitle");
			Emc_eufar.imageAcqLab.setStyleName("qv_imageLabelTitle");
			Emc_eufar.imageProLab.setStyleName("qv_imageLabelTitle");
			Emc_eufar.imageNameLab.setStyleName("qv_imageLabel");
			Emc_eufar.imageRadLab.setStyleName("qv_imageLabel");
			Emc_eufar.imageSpeLab.setStyleName("qv_imageLabel");
			Emc_eufar.imageBanLab.setStyleName("qv_imageLabel");
			Emc_eufar.imageDirLab.setStyleName("qv_imageLabel");
			Emc_eufar.imageAltLab.setStyleName("qv_imageLabel");
			Emc_eufar.imageZenLab.setStyleName("qv_imageLabel");
			Emc_eufar.imageAziLab.setStyleName("qv_imageLabel");
			Emc_eufar.imageAnoLab.setStyleName("qv_imageLabel");
			Emc_eufar.imageLevLab.setStyleName("qv_imageLabel");
			Emc_eufar.imageDCLab.setStyleName("qv_imageLabel");
			Emc_eufar.imageCalBox.setStyleName("qv_imageBox");
			Emc_eufar.imageBanBox.setStyleName("qv_imageBox2");
			Emc_eufar.imageDirBox.setStyleName("qv_imageBox2");
			Emc_eufar.imageAltBox.setStyleName("qv_imageBox2");
			Emc_eufar.imageZenBox.setStyleName("qv_imageBox2");
			Emc_eufar.imageAziBox.setStyleName("qv_imageBox2");
			Emc_eufar.imageAnoBox.setStyleName("qv_imageBox");
			Emc_eufar.imageLevLst.setStyleName("qv_imageList");
			Emc_eufar.imageLayLab.setStyleName("qv_imageLabelTitle");
			Emc_eufar.imageCalcorrLab.setStyleName("qv_imageLabelTitle2");
			Emc_eufar.imageErrLab.setStyleName("qv_imageLabelTitle2");
			Emc_eufar.imageErrcorrLab.setStyleName("qv_imageLabelTitle2");
			Emc_eufar.imageCorrconLab.setStyleName("qv_imageLabelTitle2");
			Emc_eufar.imageIntPixel.setStyleName("qv_imageLabel2");
			Emc_eufar.imageBadPixel.setStyleName("qv_imageLabel2");
			Emc_eufar.imageSatPixel.setStyleName("qv_imageLabel2");
			Emc_eufar.imageAffPixel.setStyleName("qv_imageLabel2");
			Emc_eufar.imagePosInfo.setStyleName("qv_imageLabel2");
			Emc_eufar.imageAttInfo.setStyleName("qv_imageLabel2");
			Emc_eufar.imageSyncProblem.setStyleName("qv_imageLabel2");
			Emc_eufar.imageIntGeocoding.setStyleName("qv_imageLabel2");
			Emc_eufar.imageAtmCorrection.setStyleName("qv_imageLabel2");
			Emc_eufar.imageCloudMask.setStyleName("qv_imageLabel2");
			Emc_eufar.imageShadMask.setStyleName("qv_imageLabel2");
			Emc_eufar.imageHazeMask.setStyleName("qv_imageLabel2");
			Emc_eufar.imageRouMeasure.setStyleName("qv_imageLabel2");
			Emc_eufar.imageIllAngle.setStyleName("qv_imageLabel2");
			Emc_eufar.imageBRDFGeometry.setStyleName("qv_imageLabel2");
			Emc_eufar.qvInfoButton06.getElement().setAttribute("style", "margin-top: 5px !important;");
			Emc_eufar.qvInfoButton09.getElement().setAttribute("style", "margin-top: 5px !important;");
			Emc_eufar.qvInfoButton14.getElement().setAttribute("style", "margin-top: 5px !important;");
			Emc_eufar.rootLogger.log(Level.INFO, "RadioButton: imageRad");
		} else if (string == "Atmospheric/In-situ data") {
			Emc_eufar.verticalPanel17.clear();
			Emc_eufar.horizontalPanel35.add(Emc_eufar.insituCalLab);
			Emc_eufar.horizontalPanel35.add(Emc_eufar.qvInfoButton02);
			Emc_eufar.horizontalPanel36.add(Emc_eufar.insituOutLab);
			Emc_eufar.horizontalPanel36.add(Emc_eufar.qvInfoButton03);
			Emc_eufar.horizontalPanel37.add(Emc_eufar.insituFlaLab);
			Emc_eufar.horizontalPanel37.add(Emc_eufar.qvInfoButton04);
			Emc_eufar.horizontalPanel38.add(Emc_eufar.insituAssLab);
			Emc_eufar.horizontalPanel38.add(Emc_eufar.qvInfoButton05);
			Emc_eufar.horizontalPanel59.add(Emc_eufar.insituGeoLab);
			Emc_eufar.horizontalPanel59.add(Emc_eufar.insituChk01Y);
			Emc_eufar.horizontalPanel59.add(Emc_eufar.insituChk01N);
			Emc_eufar.verticalPanel17.add(Emc_eufar.horizontalPanel35);
			Emc_eufar.insituCalTab.setWidget(0, 0, Emc_eufar.insituLinkLab);
			Emc_eufar.insituCalTab.setWidget(0, 1, Emc_eufar.insituLinkBox);
			Emc_eufar.insituCalTab.setWidget(1, 0, Emc_eufar.insituConstLab);
			Emc_eufar.insituCalTab.setWidget(1, 1, Emc_eufar.insituConstBox);
			Emc_eufar.insituCalTab.setWidget(2, 0, Emc_eufar.insituMatLab);
			Emc_eufar.insituCalTab.setWidget(2, 1, Emc_eufar.insituMatBox);
			Emc_eufar.verticalPanel17.add(Emc_eufar.insituCalTab);
			Emc_eufar.verticalPanel17.add(Emc_eufar.horizontalPanel59);
			Emc_eufar.verticalPanel17.add(new HTML("<br />"));
			Emc_eufar.verticalPanel17.add(Emc_eufar.horizontalPanel36);
			Emc_eufar.verticalPanel18.add(Emc_eufar.insituChk04);
			Emc_eufar.verticalPanel18.add(Emc_eufar.insituChk05);
			Emc_eufar.verticalPanel19.add(Emc_eufar.insituChk06);
			Emc_eufar.verticalPanel19.add(Emc_eufar.horizontalPanel32);
			Emc_eufar.horizontalPanel34.add(Emc_eufar.verticalPanel18);
			Emc_eufar.horizontalPanel34.add(Emc_eufar.verticalPanel19);
			Emc_eufar.verticalPanel17.add(Emc_eufar.horizontalPanel34);
			Emc_eufar.verticalPanel17.add(new HTML("<br />"));
			Emc_eufar.horizontalPanel32.add(Emc_eufar.insituChk07);
			((FocusWidget) Emc_eufar.insituChk07.getWidget(0)).addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {GuiModification.otherFormat();}
			});
			((FocusWidget) Emc_eufar.insituChk06.getWidget(0)).addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {GuiModification.otherFormat();}
			});
			((FocusWidget) Emc_eufar.insituChk05.getWidget(0)).addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {GuiModification.otherFormat();}
			});
			((FocusWidget) Emc_eufar.insituChk04.getWidget(0)).addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {GuiModification.otherFormat();}
			});
			Emc_eufar.horizontalPanel32.add(Emc_eufar.horizontalPanel33);
			Emc_eufar.verticalPanel17.add(Emc_eufar.horizontalPanel37);
			Emc_eufar.verticalPanel17.add(Emc_eufar.insituFlagAre);
			Emc_eufar.verticalPanel17.add(Emc_eufar.horizontalPanel38);
			Emc_eufar.verticalPanel17.add(Emc_eufar.insituAssumptionAre);
			Emc_eufar.insituLinkLab.setStyleName("qv_insituLabel");
			Emc_eufar.insituConstLab.setStyleName("qv_insituLabel");
			Emc_eufar.insituMatLab.setStyleName("qv_insituLabel");
			Emc_eufar.insituLinkBox.setStyleName("qv_insituBox");
			Emc_eufar.insituConstBox.setStyleName("qv_insituBox");
			Emc_eufar.insituMatBox.setStyleName("qv_insituBox");
			Emc_eufar.insituFlagAre.setStyleName("qv_insituTextAre");
			Emc_eufar.insituAssumptionAre.setStyleName("qv_insituTextAre");
			Emc_eufar.insituCalLab.setStyleName("qv_insituLabelTitle");
			Emc_eufar.insituOutLab.setStyleName("qv_insituLabelTitle3");
			Emc_eufar.insituFlaLab.setStyleName("qv_insituLabelTitle3");
			Emc_eufar.insituAssLab.setStyleName("qv_insituLabelTitle2");
			Emc_eufar.insituGeoLab.setStyleName("qv_insituLabelTitle2");
			Emc_eufar.insituChk01Y.getElement().setAttribute("style","margin-left: 20px !important; margin-top: 10px !important;");
			Emc_eufar.insituChk01N.getElement().setAttribute("style","margin-top: 10px !important;");
			Emc_eufar.insituChk04.getElement().setAttribute("style","margin-left: 40px !important;");
			Emc_eufar.insituChk05.getElement().setAttribute("style","margin-left: 40px !important;");
			Emc_eufar.insituChk06.getElement().setAttribute("style","margin-left: 40px !important;");
			Emc_eufar.insituChk07.getElement().setAttribute("style","margin-left: 40px !important;");
			Emc_eufar.qvInfoButton02.getElement().setAttribute("style", "margin-top: 5px !important;");
			Emc_eufar.qvInfoButton03.getElement().setAttribute("style", "margin-top: -5px !important;");
			Emc_eufar.qvInfoButton04.getElement().setAttribute("style", "margin-top: -5px !important;");
			Emc_eufar.qvInfoButton05.getElement().setAttribute("style", "margin-top: 5px !important;");
			Emc_eufar.rootLogger.log(Level.INFO, "RadioButton: insituRad");
		}

	}
	
	
	// add a new period in the Temporal Reference panel
	public static void addRefPlus() {
		Emc_eufar.rootLogger.log(Level.INFO, "Temporal ref add in progress...");
		int row = Emc_eufar.refPhaseTab.getRowCount();
		final Image image = new Image("icons/del_icon.png");
		final PushButton delButton = new PushButton(image);
		final Label label = new Label("Phase " + Integer.toString(row + 1) + ":");
		DateBox dateBox1 = new DateBox();
		DateBox dateBox2 = new DateBox();
		dateBox1.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {Utilities.docIsModified();}
		});
		dateBox2.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {Utilities.docIsModified();}
		});
		if (row == 1) {
			Emc_eufar.refDelButton.setEnabled(true);
			Emc_eufar.refDelButton.setHTML("<img border='0' src='icons/del_icon.png' />");
			Emc_eufar.refDelButton.setStyleName("infoButton");
		}
		dateBox1.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		dateBox1.setValue(new Date());
		dateBox2.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		dateBox2.setValue(new Date());
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		label.setStyleName("refTextLabel");
		Emc_eufar.refPhaseTab.insertRow(row);
		Emc_eufar.refPhaseTab.setWidget(row, 0, label);
		Emc_eufar.refPhaseTab.setWidget(row, 1, dateBox1);
		Emc_eufar.refPhaseTab.setWidget(row, 2, dateBox2);
		Emc_eufar.refPhaseTab.setWidget(row, 3, delButton);
		Emc_eufar.refStartLst.add(dateBox1);
		Emc_eufar.refEndLst.add(dateBox2);
		delButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = Emc_eufar.refPhaseTab.getCellForEvent(event).getRowIndex();
				Emc_eufar.refPhaseTab.removeRow(rowIndex);
				Emc_eufar.refStartLst.remove(rowIndex);
				Emc_eufar.refEndLst.remove(rowIndex);
				int row = Emc_eufar.refPhaseTab.getRowCount();
				List<Label> allLabel = $("*", Emc_eufar.refPhaseTab).widgets(Label.class);
				int id = 0;
				for (Object o : allLabel) {
					((Label) o).setText("Phase " + Integer.toString(id + 1) + ":");
					id++;
					
				}
				if (row == 1) {
					Emc_eufar.refDelButton.setEnabled(false);
					Emc_eufar.refDelButton.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
					Emc_eufar.refDelButton.setStyleName("emptyButton");
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Temporal ref added.");
	}
	
	
	// allow the program to create new periods based on the reading of an xml file
	public static void addRefRead(final String dateStart, final String dateEnd) {
		Emc_eufar.rootLogger.log(Level.INFO, "Temporal ref add in progress...");
		int row = Emc_eufar.refPhaseTab.getRowCount();
		final Image image = new Image("icons/del_icon.png");
		final PushButton delButton = new PushButton(image);
		final Label label = new Label("Phase " + Integer.toString(row + 1) + ":");
		DateBox dateBox1 = new DateBox();
		DateBox dateBox2 = new DateBox();
		dateBox1.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {Utilities.docIsModified();}
		});
		dateBox2.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {Utilities.docIsModified();}
		});
		if (row == 1) {
			Emc_eufar.refDelButton.setEnabled(true);
			Emc_eufar.refDelButton.setHTML("<img border='0' src='icons/del_icon.png' />");
			Emc_eufar.refDelButton.setStyleName("infoButton");
		}
		dateBox1.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		dateBox1.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(dateStart));
		dateBox2.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		dateBox2.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(dateEnd));
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		label.setStyleName("refTextLabel");
		Emc_eufar.refPhaseTab.insertRow(row);
		Emc_eufar.refPhaseTab.setWidget(row, 0, label);
		Emc_eufar.refPhaseTab.setWidget(row, 1, dateBox1);
		Emc_eufar.refPhaseTab.setWidget(row, 2, dateBox2);
		Emc_eufar.refPhaseTab.setWidget(row, 3, delButton);
		Emc_eufar.refStartLst.add(dateBox1);
		Emc_eufar.refEndLst.add(dateBox2);
		delButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = Emc_eufar.refPhaseTab.getCellForEvent(event).getRowIndex();
				Emc_eufar.refPhaseTab.removeRow(rowIndex);
				Emc_eufar.refStartLst.remove(rowIndex);
				Emc_eufar.refEndLst.remove(rowIndex);
				int row = Emc_eufar.refPhaseTab.getRowCount();
				if (row == 1) {
					Emc_eufar.refDelButton.setEnabled(false);
					Emc_eufar.refDelButton.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
					Emc_eufar.refDelButton.setStyleName("emptyButton");
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Temporal ref added.");
	}


	// add new text areas in the Access and Use Constraints tabs
	public static void addUsePlus(final FlexTable table, final ArrayList<TextArea> arrayList, final PushButton pushButton) {
		Emc_eufar.rootLogger.log(Level.INFO, "Constraints A/L add in progress...");
		int row = table.getRowCount();
		final Image image = new Image("icons/del_icon.png");
		final PushButton delButton = new PushButton(image);
		final TextArea textArea = new TextArea();
		textArea.setStyleName("useTextArea");
		textArea.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {Utilities.docIsModified();}
		});
		if (row == 1) {
			pushButton.setEnabled(true);
			pushButton.setHTML("<img border='0' src='icons/del_icon.png' />");
			pushButton.setStyleName("infoButton");
		}
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
				int row = table.getRowCount();
				if (row == 1) {
					pushButton.setEnabled(false);
					pushButton.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
					pushButton.setStyleName("emptyButton");
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Constraints A/L ref added.");
	}


	// allow the program to create new text areas based on the reading of an xml file
	public static void addUseRead(final FlexTable table, final ArrayList<TextArea> arrayList, final String string, final PushButton pushButton) {
		Emc_eufar.rootLogger.log(Level.INFO, "Constraints A/L add in progress...");
		int row = table.getRowCount();
		final Image image = new Image("icons/del_icon.png");
		final PushButton delButton = new PushButton(image);
		final TextArea textArea = new TextArea();
		textArea.setText(string);
		textArea.setStyleName("useTextArea");
		textArea.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {Utilities.docIsModified();}
		});
		if (row == 1) {
			pushButton.setEnabled(true);
			pushButton.setHTML("<img border='0' src='icons/del_icon.png' />");
			pushButton.setStyleName("infoButton");
		}
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
				int row = table.getRowCount();
				if (row == 1) {
					pushButton.setEnabled(false);
					pushButton.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
					pushButton.setStyleName("emptyButton");
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Constraints A/L ref added.");
	}


	// add new text boxes in the Responsible Organisations tab
	public static void addOrgPlus() {
		Emc_eufar.rootLogger.log(Level.INFO, "Contact Org add in progress...");
		final VerticalPanel verticalPanel01 = new VerticalPanel();
		final HorizontalPanel horizontalPanel04 = new HorizontalPanel();
		final Label orgPartyLab = new Label("Responsible party:");
		final Label orgEmailLab = new Label("Responsible party e-mail:");
		final Label orgRoleLab = new Label("Responsible party role:");
		final TextBox orgPartyBox = new TextBox();
		final TextBox orgEmailBox = new TextBox();
		final ListBox orgRoleLst = new ListBox();
		final FlexTable orgPartyTab = new FlexTable();
		final Image image = new Image("icons/del_icon.png");
		final PushButton roPartyInfo = Elements.addInfoButton("roParty");
		final PushButton roEmailInfo = Elements.addInfoButton("roEmail");
		final PushButton roRoleInfo = Elements.addInfoButton("roRole");
		final PushButton delButton = new PushButton(image);
		orgPartyBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {Utilities.docIsModified();}
		});
		orgEmailBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {Utilities.docIsModified();}
		});
		orgRoleLst.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {Utilities.docIsModified();}
		});
		verticalPanel01.add(new HTML("<hr  style=\"width:475px;height:10px;background:#0098d9;border:0px;margin-top:30px;"
				+ "margin-bottom:30px;\" />"));
		Utilities.populateListBox(orgRoleLst, Emc_eufar.roleList, 5);
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
		horizontalPanel04.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel04.add(orgPartyTab);
		orgPartyLab.setStyleName("orgTextLabel");
		orgEmailLab.setStyleName("orgTextLabel");
		orgRoleLab.setStyleName("orgTextLabel");
		orgPartyBox.setStyleName("orgTextBox");
		orgEmailBox.setStyleName("orgTextBox");
		orgRoleLst.setStyleName("orgTextList");
		Emc_eufar.orgPartyLst.add(orgPartyBox);
		Emc_eufar.orgRole2Lst.add(orgRoleLst);
		Emc_eufar.orgEmailLst.add(orgEmailBox);
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		horizontalPanel04.add(delButton);
		verticalPanel01.add(horizontalPanel04);
		int row = Emc_eufar.orgAddTab.getRowCount();
		if (row == 1) {
			Emc_eufar.orgDelButton.setEnabled(true);
			Emc_eufar.orgDelButton.setHTML("<img border='0' src='icons/del_icon.png' />");
			Emc_eufar.orgDelButton.setStyleName("infoButton");
		}
		Emc_eufar.orgAddTab.insertRow(row);
		Emc_eufar.orgAddTab.setWidget(row, 0, verticalPanel01);
		delButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = Emc_eufar.orgAddTab.getCellForEvent(event).getRowIndex();
				Emc_eufar.orgAddTab.removeRow(rowIndex);
				Emc_eufar.orgPartyLst.remove(rowIndex);
				Emc_eufar.orgRole2Lst.remove(rowIndex);
				Emc_eufar.orgEmailLst.remove(rowIndex);
				int row = Emc_eufar.orgAddTab.getRowCount();
				if (row == 1) {
					Emc_eufar.orgDelButton.setEnabled(false);
					Emc_eufar.orgDelButton.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
					Emc_eufar.orgDelButton.setStyleName("emptyButton");
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Contact Org added.");
	}


	// allow the program to create new text boxes based on the reading of an xml file
	public static void addOrgRead(final String partyName, final String partyEmail, final String partyRole) {
		Emc_eufar.rootLogger.log(Level.INFO, "Contact Org add in progress...");
		final VerticalPanel verticalPanel01 = new VerticalPanel();
		final HorizontalPanel horizontalPanel04 = new HorizontalPanel();
		final Label orgPartyLab = new Label("Responsible party:");
		final Label orgEmailLab = new Label("Responsible party e-mail:");
		final Label orgRoleLab = new Label("Responsible party role:");
		final TextBox orgPartyBox = new TextBox();
		final TextBox orgEmailBox = new TextBox();
		final ListBox orgRoleLst = new ListBox();
		final FlexTable orgPartyTab = new FlexTable();
		final Image image = new Image("icons/del_icon.png");
		final PushButton delButton = new PushButton(image);
		final PushButton roPartyInfo = Elements.addInfoButton("roParty");
		final PushButton roEmailInfo = Elements.addInfoButton("roEmail");
		final PushButton roRoleInfo = Elements.addInfoButton("roRole");
		orgPartyBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {Utilities.docIsModified();}
		});
		orgEmailBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {Utilities.docIsModified();}
		});
		orgRoleLst.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {Utilities.docIsModified();}
		});
		orgPartyBox.setText(partyName);
		orgEmailBox.setText(partyEmail);
		verticalPanel01.add(new HTML("<hr  style=\"width:475px;height:10px;background:#0098d9;border:0px;margin-top:30px;"
				+ "margin-bottom:30px;\" />"));
		Utilities.populateListBox(orgRoleLst, Emc_eufar.roleList, 5);
		Utilities.checkList(Emc_eufar.roleMap, partyRole, orgRoleLst);
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
		horizontalPanel04.add(orgPartyTab);
		orgPartyLab.setStyleName("orgTextLabel");
		orgEmailLab.setStyleName("orgTextLabel");
		orgRoleLab.setStyleName("orgTextLabel");
		orgPartyBox.setStyleName("orgTextBox");
		orgEmailBox.setStyleName("orgTextBox");
		orgRoleLst.setStyleName("orgTextList");
		Emc_eufar.orgPartyLst.add(orgPartyBox);
		Emc_eufar.orgRole2Lst.add(orgRoleLst);
		Emc_eufar.orgEmailLst.add(orgEmailBox);
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		delButton.getElement().setAttribute("style","margin-top: 45px !important;");
		horizontalPanel04.add(delButton);
		verticalPanel01.add(horizontalPanel04);
		int row = Emc_eufar.orgAddTab.getRowCount();
		if (row == 1) {
			Emc_eufar.orgDelButton.setEnabled(true);
			Emc_eufar.orgDelButton.setHTML("<img border='0' src='icons/del_icon.png' />");
			Emc_eufar.orgDelButton.setStyleName("infoButton");
		}
		Emc_eufar.orgAddTab.insertRow(row);
		Emc_eufar.orgAddTab.setWidget(row, 0, verticalPanel01);
		delButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = Emc_eufar.orgAddTab.getCellForEvent(event).getRowIndex();
				Emc_eufar.orgAddTab.removeRow(rowIndex);
				Emc_eufar.orgPartyLst.remove(rowIndex);
				Emc_eufar.orgRole2Lst.remove(rowIndex);
				Emc_eufar.orgEmailLst.remove(rowIndex);
				int row = Emc_eufar.orgAddTab.getRowCount();
				if (row == 1) {
					Emc_eufar.orgDelButton.setEnabled(false);
					Emc_eufar.orgDelButton.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
					Emc_eufar.orgDelButton.setStyleName("emptyButton");
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Contact Org added.");
	}


	// add new text boxes in the Metadata on Metadata tab
	public static void addMetPlus() {
		Emc_eufar.rootLogger.log(Level.INFO, "Contact Met add in progress...");
		final VerticalPanel verticalPanel01 = new VerticalPanel();
		final HorizontalPanel horizontalPanel01 = new HorizontalPanel();
		final Label metNameLab = new Label("Name:");
		final Label metEmailLab = new Label("E-mail:");
		final TextBox metNameBox = new TextBox();
		final TextBox metEmailBox = new TextBox();
		final FlexTable metPartyTab = new FlexTable();
		final Image image = new Image("icons/del_icon.png");
		final PushButton delButton = new PushButton(image);
		verticalPanel01.add(new HTML("<hr  style=\"width:300px;height:10px;background:#0098d9;border:0px;margin-top:30px;"
				+ "margin-bottom:30px;\" />"));
		metNameBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {Utilities.docIsModified();}
		});
		metEmailBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {Utilities.docIsModified();}
		});
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		metPartyTab.setWidget(0, 0, metNameLab);
		metPartyTab.setWidget(0, 1, metNameBox);
		metPartyTab.setWidget(1, 0, metEmailLab);
		metPartyTab.setWidget(1, 1, metEmailBox);
		Emc_eufar.metNameLst.add(metNameBox);
		Emc_eufar.metEmailLst.add(metEmailBox);
		horizontalPanel01.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel01.add(metPartyTab);
		horizontalPanel01.add(delButton);
		verticalPanel01.add(horizontalPanel01);
		metNameLab.setStyleName("metTextLabel");
		metEmailLab.setStyleName("metTextLabel");
		metNameBox.setStyleName("metTextBox");
		metEmailBox.setStyleName("metTextBox");
		int row = Emc_eufar.metAddTab.getRowCount();
		if (row == 1) {
			Emc_eufar.mmDelButton.setEnabled(true);
			Emc_eufar.mmDelButton.setHTML("<img border='0' src='icons/del_icon.png' />");
			Emc_eufar.mmDelButton.setStyleName("infoButton");
		}
		Emc_eufar.metAddTab.insertRow(row);
		Emc_eufar.metAddTab.setWidget(row, 0, verticalPanel01);
		delButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = Emc_eufar.metAddTab.getCellForEvent(event).getRowIndex();
				Emc_eufar.metAddTab.removeRow(rowIndex);
				Emc_eufar.metNameLst.remove(rowIndex);
				Emc_eufar.metEmailLst.remove(rowIndex);
				int row = Emc_eufar.metAddTab.getRowCount();
				if (row == 1) {
					Emc_eufar.mmDelButton.setEnabled(false);
					Emc_eufar.mmDelButton.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
					Emc_eufar.mmDelButton.setStyleName("emptyButton");
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Contact Met added.");
	}


	// allow the program to create new text boxes based on the reading of an xml file
	public static void addMetRead(final String metadataName, final String metadataEmail) {
		Emc_eufar.rootLogger.log(Level.INFO, "Contact Met add in progress...");
		final VerticalPanel verticalPanel01 = new VerticalPanel();
		final HorizontalPanel horizontalPanel01 = new HorizontalPanel();
		final Label metNameLab = new Label("Name:");
		final Label metEmailLab = new Label("E-mail:");
		final TextBox metNameBox = new TextBox();
		final TextBox metEmailBox = new TextBox();
		final FlexTable metPartyTab = new FlexTable();
		final Image image = new Image("icons/del_icon.png");
		final PushButton delButton = new PushButton(image);
		verticalPanel01.add(new HTML("<hr  style=\"width:300px;height:10px;background:#0098d9;border:0px;margin-top:30px;"
				+ "margin-bottom:30px;\" />"));
		metNameBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {Utilities.docIsModified();}
		});
		metEmailBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {Utilities.docIsModified();}
		});
		metNameBox.setText(metadataName);
		metEmailBox.setText(metadataEmail);
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		metPartyTab.setWidget(0, 0, metNameLab);
		metPartyTab.setWidget(0, 1, metNameBox);
		metPartyTab.setWidget(1, 0, metEmailLab);
		metPartyTab.setWidget(1, 1, metEmailBox);
		Emc_eufar.metNameLst.add(metNameBox);
		Emc_eufar.metEmailLst.add(metEmailBox);
		horizontalPanel01.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel01.add(metPartyTab);
		horizontalPanel01.add(delButton);
		verticalPanel01.add(horizontalPanel01);
		metNameLab.setStyleName("metTextLabel");
		metEmailLab.setStyleName("metTextLabel");
		metNameBox.setStyleName("metTextBox");
		metEmailBox.setStyleName("metTextBox");
		int row = Emc_eufar.metAddTab.getRowCount();
		if (row == 1) {
			Emc_eufar.mmDelButton.setEnabled(true);
			Emc_eufar.mmDelButton.setHTML("<img border='0' src='icons/del_icon.png' />");
			Emc_eufar.mmDelButton.setStyleName("infoButton");
		}
		Emc_eufar.metAddTab.insertRow(row);
		Emc_eufar.metAddTab.setWidget(row, 0, verticalPanel01);
		delButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = Emc_eufar.metAddTab.getCellForEvent(event).getRowIndex();
				Emc_eufar.metAddTab.removeRow(rowIndex);
				Emc_eufar.metNameLst.remove(rowIndex);
				Emc_eufar.metEmailLst.remove(rowIndex);
				int row = Emc_eufar.metAddTab.getRowCount();
				if (row == 1) {
					Emc_eufar.mmDelButton.setEnabled(false);
					Emc_eufar.mmDelButton.setHTML("<img border='0' src='icons/empty_icon_small.png' />");
					Emc_eufar.mmDelButton.setStyleName("emptyButton");
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Contact Met added.");
	}
	
	
	// add an instrument
	public static void addInstPlus() {
		Emc_eufar.rootLogger.log(Level.INFO, "Instrument add in progress...");
		Utilities.docIsModified();
		int row = Emc_eufar.airInstrumentTable.getRowCount();
		final Image image = new Image("icons/del_icon.png");
		final PushButton delButton = new PushButton(image);
		final Label name = new Label();
		final Label manufacturer = new Label();
		if (Emc_eufar.airInstrumentLst.getSelectedItemText() == "Other...") {
			name.setText(Emc_eufar.airInstNameBox.getText());
			manufacturer.setText(Emc_eufar.airInstManufacturerBox.getText());
		} else {
			final String string = Emc_eufar.airInstrumentLst.getSelectedItemText();
			final int offset = string.lastIndexOf(" - ");
			manufacturer.setText(string.substring(0, offset));
			name.setText(string.substring(offset + 3));
		}
		for (int i = 0; i < Emc_eufar.instrumentTabList.size(); i++) {
			if (name.getText() == Emc_eufar.instrumentTabList.get(i) && manufacturer.getText() == Emc_eufar.manufacturerTabList.get(i)) {
				Window.alert("It is not possible to add more than once the same instrument.");
				return;
			}
		}
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		if (row == 0) {
			final Label nameTitle = new Label("Instrument:");
			final Label manufacturerTitle = new Label("Manufacturer:");
			Emc_eufar.airInstrumentTable.insertRow(row);
			Emc_eufar.airInstrumentTable.setWidget(row, 0, nameTitle);
			Emc_eufar.airInstrumentTable.setWidget(row, 1, manufacturerTitle);
			row++;
			nameTitle.setStyleName("airTitleTextLabel");
			manufacturerTitle.setStyleName("airTitleTextLabel");
		}
		Emc_eufar.airInstrumentTable.insertRow(row);
		Emc_eufar.airInstrumentTable.setWidget(row, 0, name);
		Emc_eufar.airInstrumentTable.setWidget(row, 1, manufacturer);
		Emc_eufar.airInstrumentTable.setWidget(row, 2, delButton);
		FlexCellFormatter cellFormatter = Emc_eufar.airInstrumentTable.getFlexCellFormatter();
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		cellFormatter.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		cellFormatter.setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_CENTER);
		cellFormatter.setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_CENTER);
		name.setStyleName("airFlexTableLabel1");
		manufacturer.setStyleName("airFlexTableLabel1");
		Emc_eufar.instrumentTabList.add(name.getText());
		Emc_eufar.manufacturerTabList.add(manufacturer.getText());
		delButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = Emc_eufar.airInstrumentTable.getCellForEvent(event).getRowIndex();
				Emc_eufar.airInstrumentTable.removeRow(rowIndex);
				Emc_eufar.instrumentTabList.remove(rowIndex-1);
				Emc_eufar.manufacturerTabList.remove(rowIndex-1);
				int row = Emc_eufar.airInstrumentTable.getRowCount();
				if (row == 1) {
					Emc_eufar.airInstrumentTable.removeRow(row-1);
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Instrument added.");
	}
	
	
	// same as above but dedicated to the read function
	public static void addInstRead(final String nameStr, final String manufacturerStr) {
		Emc_eufar.rootLogger.log(Level.INFO, "Instrument add in progress...");
		int row = Emc_eufar.airInstrumentTable.getRowCount();
		final Image image = new Image("icons/del_icon.png");
		final PushButton delButton = new PushButton(image);
		final Label name = new Label(nameStr);
		final Label manufacturer = new Label(manufacturerStr);
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		if (row == 0) {
			final Label nameTitle = new Label("Instrument:");
			final Label manufacturerTitle = new Label("Manufacturer:");
			Emc_eufar.airInstrumentTable.insertRow(row);
			Emc_eufar.airInstrumentTable.setWidget(row, 0, nameTitle);
			Emc_eufar.airInstrumentTable.setWidget(row, 1, manufacturerTitle);
			FlexCellFormatter cellFormatter = Emc_eufar.airInstrumentTable.getFlexCellFormatter();
			cellFormatter.setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_CENTER);
			cellFormatter.setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_CENTER);
			row++;
			nameTitle.setStyleName("airTitleTextLabel");
			manufacturerTitle.setStyleName("airTitleTextLabel");
		}
		Emc_eufar.airInstrumentTable.insertRow(row);
		Emc_eufar.airInstrumentTable.setWidget(row, 0, name);
		Emc_eufar.airInstrumentTable.setWidget(row, 1, manufacturer);
		Emc_eufar.airInstrumentTable.setWidget(row, 2, delButton);
		FlexCellFormatter cellFormatter = Emc_eufar.airInstrumentTable.getFlexCellFormatter();
		cellFormatter.setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_CENTER);
		cellFormatter.setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_CENTER);
		Emc_eufar.instrumentTabList.add(name.getText());
		Emc_eufar.manufacturerTabList.add(manufacturer.getText());
		delButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = Emc_eufar.airInstrumentTable.getCellForEvent(event).getRowIndex();
				Emc_eufar.airInstrumentTable.removeRow(rowIndex);
				Emc_eufar.instrumentTabList.remove(rowIndex-1);
				Emc_eufar.manufacturerTabList.remove(rowIndex-1);
				int row = Emc_eufar.airInstrumentTable.getRowCount();
				if (row == 1) {
					Emc_eufar.airInstrumentTable.removeRow(row-1);
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Instrument added.");
	}
	
	
	// update all Aircraft information when a particular aircraft is selected in the Aircraft and Instruments tab
	public static void aircraftInformation(final int index) {
		Emc_eufar.rootLogger.log(Level.INFO, "Aircraft information function invoked...");
		if (Emc_eufar.airAircraftLst.getSelectedItemText() == "Make a choice...") {
			Emc_eufar.horizontalPanel60.remove(1);
			Emc_eufar.horizontalPanel61.remove(1);
			Emc_eufar.horizontalPanel62.remove(1);
			Emc_eufar.horizontalPanel63.remove(1);
			Emc_eufar.horizontalPanel64.remove(1);
			Emc_eufar.horizontalPanel60.add(Emc_eufar.airManufacturerInfo);
			Emc_eufar.horizontalPanel61.add(Emc_eufar.airTypeInfo);
			Emc_eufar.horizontalPanel62.add(Emc_eufar.airOperatorInfo);
			Emc_eufar.horizontalPanel63.add(Emc_eufar.airCountryInfo);
			Emc_eufar.horizontalPanel64.add(Emc_eufar.airRegistrationInfo);
			Emc_eufar.airManufacturerInfo.setText("");
			Emc_eufar.airTypeInfo.setText("");
			Emc_eufar.airOperatorInfo.setText("");
			Emc_eufar.airCountryInfo.setText("");
			Emc_eufar.airRegistrationInfo.setText("");
			Emc_eufar.airAircraftImg.setUrl("eufar_aircraft/logo_eufar_emc.png");
			Emc_eufar.airCopyrightInfo.setText("EUFAR");
			Emc_eufar.rootLogger.log(Level.INFO, "Make a choice... loaded.");
		} else if (Emc_eufar.airAircraftLst.getSelectedItemText() == "Other...") {
			Utilities.populateListBox(Emc_eufar.airCountryLst, Emc_eufar.countryList, 0);
			Emc_eufar.airAircraftImg.setUrl("eufar_aircraft/logo_eufar_emc.png");
			Emc_eufar.airCopyrightInfo.setText("EUFAR");
			Emc_eufar.airManufacturerInfo.setText("");
			Emc_eufar.airTypeInfo.setText("");
			Emc_eufar.airOperatorInfo.setText("");
			Emc_eufar.airCountryInfo.setText("");
			Emc_eufar.airRegistrationInfo.setText("");
			Emc_eufar.horizontalPanel60.remove(1);
			Emc_eufar.horizontalPanel61.remove(1);
			Emc_eufar.horizontalPanel62.remove(1);
			Emc_eufar.horizontalPanel63.remove(1);
			Emc_eufar.horizontalPanel64.remove(1);
			Emc_eufar.horizontalPanel60.add(Emc_eufar.airManufacturerBox);
			Emc_eufar.horizontalPanel61.add(Emc_eufar.airTypeBox);
			Emc_eufar.horizontalPanel62.add(Emc_eufar.airOperatorBox);
			Emc_eufar.horizontalPanel63.add(Emc_eufar.airCountryLst);
			Emc_eufar.horizontalPanel64.add(Emc_eufar.airRegistrationBox);
			Emc_eufar.airManufacturerBox.setStyleName("airTextBox3");
			Emc_eufar.airTypeBox.setStyleName("airTextBox4");
			Emc_eufar.airOperatorBox.setStyleName("airTextBox5");
			Emc_eufar.airCountryLst.setStyleName("airTextList3");
			Emc_eufar.airRegistrationBox.setStyleName("airTextBox");
		} else {
			Emc_eufar.horizontalPanel60.remove(1);
			Emc_eufar.horizontalPanel61.remove(1);
			Emc_eufar.horizontalPanel62.remove(1);
			Emc_eufar.horizontalPanel63.remove(1);
			Emc_eufar.horizontalPanel64.remove(1);
			Emc_eufar.horizontalPanel60.add(Emc_eufar.airManufacturerInfo);
			Emc_eufar.horizontalPanel61.add(Emc_eufar.airTypeInfo);
			Emc_eufar.horizontalPanel62.add(Emc_eufar.airOperatorInfo);
			Emc_eufar.horizontalPanel63.add(Emc_eufar.airCountryInfo);
			Emc_eufar.horizontalPanel64.add(Emc_eufar.airRegistrationInfo);
			Emc_eufar.airManufacturerInfo.setText(Emc_eufar.airAircraftInfo[index-1][1]);
			Emc_eufar.airTypeInfo.setText(Emc_eufar.airAircraftInfo[index-1][2]);
			Emc_eufar.airOperatorInfo.setText(Emc_eufar.airAircraftInfo[index-1][3]);
			Emc_eufar.airCountryInfo.setText(Emc_eufar.airAircraftInfo[index-1][4]);
			Emc_eufar.airRegistrationInfo.setText(Emc_eufar.airAircraftInfo[index-1][5]);
			Emc_eufar.airCopyrightInfo.setText(Emc_eufar.airAircraftInfo[index-1][7]);
			String aircraftImage = Emc_eufar.airAircraftInfo[index-1][6];
			Emc_eufar.airAircraftImg.setUrl("eufar_aircraft/" + aircraftImage);
			Emc_eufar.rootLogger.log(Level.INFO, "Aircraft information loaded.");
		}
	}
	
	
	// add textbox if the user selected "Other..." in the instrument panel
	public static void otherInstrument() {
		if (Emc_eufar.airInstrumentLst.getSelectedItemText() == "Other...") {
			Emc_eufar.horizontalPanel65.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
			Emc_eufar.horizontalPanel65.add(Emc_eufar.airInstNameLab);
			Emc_eufar.horizontalPanel65.add(Emc_eufar.airInstNameBox);
			Emc_eufar.horizontalPanel65.add(Emc_eufar.airInstManufacturerLab);
			Emc_eufar.horizontalPanel65.add(Emc_eufar.airInstManufacturerBox);
			Emc_eufar.horizontalPanel12.insert(Emc_eufar.horizontalPanel65, 2);
			Emc_eufar.airInstNameBox.setStyleName("airTextBox6");
			Emc_eufar.airInstManufacturerBox.setStyleName("airTextBox6");
			Emc_eufar.airInstNameLab.setStyleName("airTitleTextLabel2");
			Emc_eufar.airInstManufacturerLab.setStyleName("airTitleTextLabel2");
			Emc_eufar.horizontalPanel65.getElement().setAttribute("style","margin-top: 13px;");
		} else {
			if (Emc_eufar.horizontalPanel12.getWidgetCount() == 5) {
				Emc_eufar.horizontalPanel12.remove(2);
			}
		}
	}
}
