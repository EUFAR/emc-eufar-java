package com.eufar.emc.client;


import static com.google.gwt.query.client.GQuery.$;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.datepicker.client.DateBox;

public class GuiModification {
	
	
	// change the resolution type, scale or distance, in theGeographic Information tab
	protected static void geoResolutionSet(final int index) {
		Emc_eufar.rootLogger.log(Level.INFO, "Resolution change invoked.");
		if (index == 0) {
			Emc_eufar.geoUnitLab.setVisible(false);
			Emc_eufar.geoUnitLst.setVisible(false);
			Emc_eufar.geoResolutionBox.setStyleName("geoTextBox2");
			Emc_eufar.rootLogger.log(Level.INFO, "Changed to scale.");
		} else {
			Emc_eufar.geoUnitLab.setVisible(true);
			Emc_eufar.geoUnitLst.setVisible(true);
			Emc_eufar.geoResolutionBox.setStyleName("geoTextBox3");
			Emc_eufar.rootLogger.log(Level.INFO, "Changed to distance.");
		}
	}
	
	
	// add a new period in the Temporal Reference panel
	public static void addRefPlus() {
		Emc_eufar.rootLogger.log(Level.INFO, "Temporal ref add in progress...");
		int row = Emc_eufar.refPhaseTab.getRowCount();
		final Image image = new Image(Emc_eufar.resources.delete().getSafeUri());
		image.setSize("21px","21px");
		image.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		final SimplePanel delButton = new SimplePanel(image);
		final Label label = new Label("Phase " + Integer.toString(row + 1));
		DateBox dateBox1 = new DateBox();
		DateBox dateBox2 = new DateBox();
		dateBox1.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Utilities.docIsModified();
			}
		});
		dateBox2.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Utilities.docIsModified();
			}
		});
		if (row == 1) {
			Emc_eufar.refDelImage.setVisible(true);
		}
		dateBox1.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		dateBox1.setValue(new Date());
		dateBox2.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		dateBox2.setValue(new Date());
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		label.setStyleName("refTextLabel");
		dateBox1.setStyleName("refDatebox");
		dateBox2.setStyleName("refDatebox");
		Emc_eufar.refPhaseTab.insertRow(row);
		Emc_eufar.refPhaseTab.setWidget(row, 0, label);
		Emc_eufar.refPhaseTab.setWidget(row, 1, dateBox1);
		Emc_eufar.refPhaseTab.setWidget(row, 2, dateBox2);
		Emc_eufar.refPhaseTab.setWidget(row, 3, delButton);
		Emc_eufar.refStartLst.add(dateBox1);
		Emc_eufar.refEndLst.add(dateBox2);
		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = Emc_eufar.refPhaseTab.getCellForEvent(event).getRowIndex();
				Emc_eufar.refPhaseTab.removeRow(rowIndex);
				Emc_eufar.refStartLst.remove(rowIndex);
				Emc_eufar.refEndLst.remove(rowIndex);
				int row = Emc_eufar.refPhaseTab.getRowCount();
				List<Label> allLabel = $("*", Emc_eufar.refPhaseTab).widgets(Label.class);
				int id = 0;
				for (Object o : allLabel) {
					((Label) o).setText("Phase " + Integer.toString(id + 1));
					id++;
				}
				if (row == 1) {
					Emc_eufar.refDelImage.setVisible(false);
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Temporal ref added.");
	}
	
	
	// allow the program to create new periods based on the reading of an xml file
	public static void addRefRead(final String dateStart, final String dateEnd) {
		Emc_eufar.rootLogger.log(Level.INFO, "Temporal ref add in progress...");
		int row = Emc_eufar.refPhaseTab.getRowCount();
		final Image image = new Image(Emc_eufar.resources.delete().getSafeUri());
		image.setSize("21px","21px");
		image.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		final SimplePanel delButton = new SimplePanel(image);
		final Label label = new Label("Phase " + Integer.toString(row + 1));
		DateBox dateBox1 = new DateBox();
		DateBox dateBox2 = new DateBox();
		if (row == 1) {
			Emc_eufar.refDelImage.setVisible(true);
		}
		dateBox1.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Utilities.docIsModified();
			}
		});
		dateBox2.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Utilities.docIsModified();
			}
		});
		dateBox1.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		dateBox1.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(dateStart));
		dateBox2.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		dateBox2.setValue(DateTimeFormat.getFormat("yyyy-MM-dd").parse(dateEnd));
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		label.setStyleName("refTextLabel");
		dateBox1.setStyleName("refDatebox");
		dateBox2.setStyleName("refDatebox");
		Emc_eufar.refPhaseTab.insertRow(row);
		Emc_eufar.refPhaseTab.setWidget(row, 0, label);
		Emc_eufar.refPhaseTab.setWidget(row, 1, dateBox1);
		Emc_eufar.refPhaseTab.setWidget(row, 2, dateBox2);
		Emc_eufar.refPhaseTab.setWidget(row, 3, delButton);
		Emc_eufar.refStartLst.add(dateBox1);
		Emc_eufar.refEndLst.add(dateBox2);
		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = Emc_eufar.refPhaseTab.getCellForEvent(event).getRowIndex();
				Emc_eufar.refPhaseTab.removeRow(rowIndex);
				Emc_eufar.refStartLst.remove(rowIndex);
				Emc_eufar.refEndLst.remove(rowIndex);
				int row = Emc_eufar.refPhaseTab.getRowCount();
				if (row == 1) {
					Emc_eufar.refDelImage.setVisible(false);
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Temporal ref added.");
	}


	// add new text areas in the Access and Use Constraints tabs
	public static void addUsePlus(final FlexTable table, final ArrayList<TextArea> arrayList01, 
			final ArrayList<String> arrayList02, final Image firstImage) {
		Emc_eufar.rootLogger.log(Level.INFO, "Constraints A/L add in progress...");
		int row = table.getRowCount();
		final Image image = new Image(Emc_eufar.resources.delete().getSafeUri());
		image.setSize("21px","21px");
		image.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		final SimplePanel delButton = new SimplePanel(image);
		final TextArea textArea = new TextArea();
		textArea.setStyleName("useTextArea");
		textArea.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				Utilities.docIsModified();
			}
		});
		if (row == 1) {
			firstImage.setVisible(true);
		}
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		table.insertRow(row);
		table.setWidget(row, 0, textArea);
		table.setWidget(row, 1, delButton);
		arrayList01.add(textArea);
		arrayList02.add("string");
		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = table.getCellForEvent(event).getRowIndex();
				table.removeRow(rowIndex);
				arrayList01.remove(rowIndex);
				arrayList02.remove(rowIndex);
				int row = table.getRowCount();
				if (row == 1) {
					firstImage.setVisible(false);
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Constraints A/L ref added.");
	}


	// allow the program to create new text areas based on the reading of an xml file
	public static void addUseRead(final FlexTable table, final ArrayList<TextArea> arrayList01, 
			final ArrayList<String> arrayList02, final String string, final Image firstImage) {
		Emc_eufar.rootLogger.log(Level.INFO, "Constraints A/L add in progress...");
		int row = table.getRowCount();
		final Image image = new Image(Emc_eufar.resources.delete().getSafeUri());
		image.setSize("21px","21px");
		image.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		final SimplePanel delButton = new SimplePanel(image);
		final TextArea textArea = new TextArea();
		textArea.setText(string);
		textArea.setStyleName("useTextArea");
		textArea.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				Utilities.docIsModified();
			}
		});
		if (row == 1) {
			firstImage.setVisible(true);
		}
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		table.insertRow(row);
		table.setWidget(row, 0, textArea);
		table.setWidget(row, 1, delButton);
		arrayList01.add(textArea);
		arrayList02.add("string");
		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = table.getCellForEvent(event).getRowIndex();
				table.removeRow(rowIndex);
				arrayList01.remove(rowIndex);
				int row = table.getRowCount();
				if (row == 1) {
					firstImage.setVisible(false);
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
		final HorizontalPanel horizontalPanel05 = new HorizontalPanel();
		final HorizontalPanel horizontalPanel06 = new HorizontalPanel();
		final HorizontalPanel horizontalPanel07 = new HorizontalPanel();
		final Label orgStarLab01 = new Label("*");
		final Label orgStarLab02 = new Label("*");
		final Label orgStarLab03 = new Label("*");
		final Label orgPartyLab = new Label("Responsible party");
		final Label orgEmailLab = new Label("Responsible party e-mail");
		final Label orgRoleLab = new Label("Responsible party role");
		final TextBox orgPartyBox = new TextBox();
		final TextBox orgEmailBox = new TextBox();
		final ListBox orgRoleLst = new ListBox();
		final FlexTable orgPartyTab = new FlexTable();
		final Image image = new Image(Emc_eufar.resources.delete().getSafeUri());
		image.setSize("21px","21px");
		image.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		final SimplePanel roPartyInfo = Elements.addInfoButton("roParty");
		final SimplePanel roEmailInfo = Elements.addInfoButton("roEmail");
		final SimplePanel roRoleInfo = Elements.addInfoButton("roRole");
		final SimplePanel delButton = new SimplePanel(image);
		orgPartyBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				Utilities.docIsModified();
			}
		});
		orgEmailBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				Utilities.docIsModified();
			}
		});
		orgRoleLst.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				Utilities.docIsModified();
			}
		});
		verticalPanel01.add(new HTML("<hr  style=\"width:482px;height:10px;background:#0098d9;border:0px;margin-top:30px;"
				+ "margin-bottom:30px;\" />"));
		Utilities.populateListBox(orgRoleLst, Emc_eufar.roleList, 5);
		horizontalPanel05.add(orgPartyLab);
		horizontalPanel05.add(orgStarLab01);
		horizontalPanel06.add(orgEmailLab);
		horizontalPanel06.add(orgStarLab02);
		horizontalPanel07.add(orgRoleLab);
		horizontalPanel07.add(orgStarLab03);
		orgPartyTab.setWidget(0, 0, horizontalPanel05);
		orgPartyTab.setWidget(0, 1, orgPartyBox);
		orgPartyTab.setWidget(0, 2, roPartyInfo);
		orgPartyTab.setWidget(1, 0, horizontalPanel06);
		orgPartyTab.setWidget(1, 1, orgEmailBox);
		orgPartyTab.setWidget(1, 2, roEmailInfo);
		orgPartyTab.setWidget(2, 0, horizontalPanel07);
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
		orgStarLab01.setStyleName("orgStarLabel");
		orgStarLab02.setStyleName("orgStarLabel");
		orgStarLab03.setStyleName("orgStarLabel");
		Emc_eufar.orgPartyLst.add(orgPartyBox);
		Emc_eufar.orgRole2Lst.add(orgRoleLst);
		Emc_eufar.orgEmailLst.add(orgEmailBox);
		Emc_eufar.orgPartyCorrectLst.add("string");
		Emc_eufar.orgEmailCorrectLst.add("email");
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		horizontalPanel04.add(delButton);
		verticalPanel01.add(horizontalPanel04);
		FlexCellFormatter cellFormatter = orgPartyTab.getFlexCellFormatter();
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter.setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter.setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		int row = Emc_eufar.orgAddTab.getRowCount();
		if (row == 1) {
			Emc_eufar.orgDelImage.setVisible(true);
		}
		Emc_eufar.orgAddTab.insertRow(row);
		Emc_eufar.orgAddTab.setWidget(row, 0, verticalPanel01);
		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = Emc_eufar.orgAddTab.getCellForEvent(event).getRowIndex();
				Emc_eufar.orgAddTab.removeRow(rowIndex);
				Emc_eufar.orgPartyLst.remove(rowIndex);
				Emc_eufar.orgRole2Lst.remove(rowIndex);
				Emc_eufar.orgEmailLst.remove(rowIndex);
				Emc_eufar.orgPartyCorrectLst.remove(rowIndex);
				Emc_eufar.orgEmailCorrectLst.remove(rowIndex);
				int row = Emc_eufar.orgAddTab.getRowCount();
				if (row == 1) {
					Emc_eufar.orgDelImage.setVisible(false);
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
		final HorizontalPanel horizontalPanel05 = new HorizontalPanel();
		final HorizontalPanel horizontalPanel06 = new HorizontalPanel();
		final HorizontalPanel horizontalPanel07 = new HorizontalPanel();
		final Label orgStarLab01 = new Label("*");
		final Label orgStarLab02 = new Label("*");
		final Label orgStarLab03 = new Label("*");
		final Label orgPartyLab = new Label("Responsible party");
		final Label orgEmailLab = new Label("Responsible party e-mail");
		final Label orgRoleLab = new Label("Responsible party role");
		final TextBox orgPartyBox = new TextBox();
		final TextBox orgEmailBox = new TextBox();
		final ListBox orgRoleLst = new ListBox();
		final FlexTable orgPartyTab = new FlexTable();
		final Image image = new Image(Emc_eufar.resources.delete().getSafeUri());
		image.setSize("21px","21px");
		image.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		final SimplePanel delButton = new SimplePanel(image);
		final SimplePanel roPartyInfo = Elements.addInfoButton("roParty");
		final SimplePanel roEmailInfo = Elements.addInfoButton("roEmail");
		final SimplePanel roRoleInfo = Elements.addInfoButton("roRole");
		orgPartyBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				Utilities.docIsModified();
			}
		});
		orgEmailBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				Utilities.docIsModified();
			}
		});
		orgRoleLst.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				Utilities.docIsModified();
			}
		});
		orgPartyBox.setText(partyName);
		orgEmailBox.setText(partyEmail);
		verticalPanel01.add(new HTML("<hr style=\"width:482px;height:10px;background:#0098d9;border:0px;margin-top:30px;"
				+ "margin-bottom:30px;\" />"));
		Utilities.populateListBox(orgRoleLst, Emc_eufar.roleList, 5);
		Utilities.checkList(Emc_eufar.roleMap, partyRole, orgRoleLst);
		horizontalPanel05.add(orgPartyLab);
		horizontalPanel05.add(orgStarLab01);
		horizontalPanel06.add(orgEmailLab);
		horizontalPanel06.add(orgStarLab02);
		horizontalPanel07.add(orgRoleLab);
		horizontalPanel07.add(orgStarLab03);
		orgPartyTab.setWidget(0, 0, horizontalPanel05);
		orgPartyTab.setWidget(0, 1, orgPartyBox);
		orgPartyTab.setWidget(0, 2, roPartyInfo);
		orgPartyTab.setWidget(1, 0, horizontalPanel06);
		orgPartyTab.setWidget(1, 1, orgEmailBox);
		orgPartyTab.setWidget(1, 2, roEmailInfo);
		orgPartyTab.setWidget(2, 0, horizontalPanel07);
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
		orgStarLab01.setStyleName("orgStarLabel");
		orgStarLab02.setStyleName("orgStarLabel");
		orgStarLab03.setStyleName("orgStarLabel");
		Emc_eufar.orgPartyLst.add(orgPartyBox);
		Emc_eufar.orgRole2Lst.add(orgRoleLst);
		Emc_eufar.orgEmailLst.add(orgEmailBox);
		Emc_eufar.orgPartyCorrectLst.add("string");
		Emc_eufar.orgEmailCorrectLst.add("email");
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		delButton.getElement().setAttribute("style","margin-top: 45px !important;");
		horizontalPanel04.add(delButton);
		verticalPanel01.add(horizontalPanel04);
		FlexCellFormatter cellFormatter = orgPartyTab.getFlexCellFormatter();
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter.setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter.setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		int row = Emc_eufar.orgAddTab.getRowCount();
		if (row == 1) {
			Emc_eufar.orgDelImage.setVisible(true);
		}
		Emc_eufar.orgAddTab.insertRow(row);
		Emc_eufar.orgAddTab.setWidget(row, 0, verticalPanel01);
		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = Emc_eufar.orgAddTab.getCellForEvent(event).getRowIndex();
				Emc_eufar.orgAddTab.removeRow(rowIndex);
				Emc_eufar.orgPartyLst.remove(rowIndex);
				Emc_eufar.orgRole2Lst.remove(rowIndex);
				Emc_eufar.orgEmailLst.remove(rowIndex);
				Emc_eufar.orgPartyCorrectLst.remove(rowIndex);
				Emc_eufar.orgEmailCorrectLst.remove(rowIndex);
				int row = Emc_eufar.orgAddTab.getRowCount();
				if (row == 1) {
					Emc_eufar.orgDelImage.setVisible(false);
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
		final HorizontalPanel horizontalPanel02 = new HorizontalPanel();
		final HorizontalPanel horizontalPanel03 = new HorizontalPanel();
		final Label metStarLab01 = new Label("*");
		final Label metStarLab02 = new Label("*");
		final Label metNameLab = new Label("Name");
		final Label metEmailLab = new Label("E-mail");
		final TextBox metNameBox = new TextBox();
		final TextBox metEmailBox = new TextBox();
		final FlexTable metPartyTab = new FlexTable();
		final Image image = new Image(Emc_eufar.resources.delete().getSafeUri());
		image.setSize("21px","21px");
		image.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		final SimplePanel delButton = new SimplePanel(image);
		verticalPanel01.add(new HTML("<hr  style=\"width:310px;height:10px;background:#0098d9;border:0px;margin-top:30px;"
				+ "margin-bottom:30px;\" />"));
		metNameBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				Utilities.docIsModified();
			}
		});
		metEmailBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				Utilities.docIsModified();
			}
		});
		horizontalPanel02.add(metNameLab);
		horizontalPanel02.add(metStarLab01);
		horizontalPanel03.add(metEmailLab);
		horizontalPanel03.add(metStarLab02);
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		metPartyTab.setWidget(0, 0, horizontalPanel02);
		metPartyTab.setWidget(0, 1, metNameBox);
		metPartyTab.setWidget(1, 0, horizontalPanel03);
		metPartyTab.setWidget(1, 1, metEmailBox);
		Emc_eufar.metNameLst.add(metNameBox);
		Emc_eufar.metEmailLst.add(metEmailBox);
		Emc_eufar.metNameCorrectLst.add("string");
		Emc_eufar.metEmailCorrectLst.add("email");
		horizontalPanel01.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel01.add(metPartyTab);
		horizontalPanel01.add(delButton);
		verticalPanel01.add(horizontalPanel01);
		metNameLab.setStyleName("metTextLabel");
		metEmailLab.setStyleName("metTextLabel");
		metNameBox.setStyleName("metTextBox");
		metEmailBox.setStyleName("metTextBox");
		metStarLab01.setStyleName("metStarLabel");
		metStarLab02.setStyleName("metStarLabel");
		int row = Emc_eufar.metAddTab.getRowCount();
		if (row == 1) {
			Emc_eufar.metDelImage.setVisible(true);
		}
		Emc_eufar.metAddTab.insertRow(row);
		Emc_eufar.metAddTab.setWidget(row, 0, verticalPanel01);
		FlexCellFormatter metCellFormatter = metPartyTab.getFlexCellFormatter();
		metCellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		metCellFormatter.setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = Emc_eufar.metAddTab.getCellForEvent(event).getRowIndex();
				Emc_eufar.metAddTab.removeRow(rowIndex);
				Emc_eufar.metNameLst.remove(rowIndex);
				Emc_eufar.metEmailLst.remove(rowIndex);
				Emc_eufar.metNameCorrectLst.remove(rowIndex);
				Emc_eufar.metEmailCorrectLst.remove(rowIndex);
				int row = Emc_eufar.metAddTab.getRowCount();
				if (row == 1) {
					Emc_eufar.metDelImage.setVisible(false);
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
		final HorizontalPanel horizontalPanel02 = new HorizontalPanel();
		final HorizontalPanel horizontalPanel03 = new HorizontalPanel();
		final Label metStarLab01 = new Label("*");
		final Label metStarLab02 = new Label("*");
		final Label metNameLab = new Label("Name");
		final Label metEmailLab = new Label("E-mail");
		final TextBox metNameBox = new TextBox();
		final TextBox metEmailBox = new TextBox();
		final FlexTable metPartyTab = new FlexTable();
		final Image image = new Image(Emc_eufar.resources.delete().getSafeUri());
		image.setSize("21px","21px");
		image.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		final SimplePanel delButton = new SimplePanel(image);
		verticalPanel01.add(new HTML("<hr style=\"width:310px;height:10px;background:#0098d9;border:0px;margin-top:30px;"
				+ "margin-bottom:30px;\" />"));
		metNameBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				Utilities.docIsModified();
			}
		});
		metEmailBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				Utilities.docIsModified();
			}
		});
		metNameBox.setText(metadataName);
		metEmailBox.setText(metadataEmail);
		horizontalPanel02.add(metNameLab);
		horizontalPanel02.add(metStarLab01);
		horizontalPanel03.add(metEmailLab);
		horizontalPanel03.add(metStarLab02);
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		metPartyTab.setWidget(0, 0, horizontalPanel02);
		metPartyTab.setWidget(0, 1, metNameBox);
		metPartyTab.setWidget(1, 0, horizontalPanel03);
		metPartyTab.setWidget(1, 1, metEmailBox);
		Emc_eufar.metNameLst.add(metNameBox);
		Emc_eufar.metEmailLst.add(metEmailBox);
		Emc_eufar.metNameCorrectLst.add("string");
		Emc_eufar.metEmailCorrectLst.add("email");
		horizontalPanel01.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel01.add(metPartyTab);
		horizontalPanel01.add(delButton);
		verticalPanel01.add(horizontalPanel01);
		metNameLab.setStyleName("metTextLabel");
		metEmailLab.setStyleName("metTextLabel");
		metNameBox.setStyleName("metTextBox");
		metEmailBox.setStyleName("metTextBox");
		metStarLab01.setStyleName("metStarLabel");
		metStarLab02.setStyleName("metStarLabel");
		int row = Emc_eufar.metAddTab.getRowCount();
		if (row == 1) {
			Emc_eufar.metDelImage.setVisible(true);
		}
		Emc_eufar.metAddTab.insertRow(row);
		Emc_eufar.metAddTab.setWidget(row, 0, verticalPanel01);
		FlexCellFormatter metCellFormatter = metPartyTab.getFlexCellFormatter();
		metCellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		metCellFormatter.setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = Emc_eufar.metAddTab.getCellForEvent(event).getRowIndex();
				Emc_eufar.metAddTab.removeRow(rowIndex);
				Emc_eufar.metNameLst.remove(rowIndex);
				Emc_eufar.metEmailLst.remove(rowIndex);
				Emc_eufar.metNameCorrectLst.remove(rowIndex);
				Emc_eufar.metEmailCorrectLst.remove(rowIndex);
				int row = Emc_eufar.metAddTab.getRowCount();
				if (row == 1) {
					Emc_eufar.metDelImage.setVisible(false);
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
		final Image image = new Image(Emc_eufar.resources.delete().getSafeUri());
		image.setSize("21px","21px");
		image.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		final SimplePanel delButton = new SimplePanel(image);
		final Label name = new Label();
		final Label manufacturer = new Label();
		int instrumentBoxMissing = 0;
		if (Emc_eufar.airInstrumentLst.getSelectedItemText() == "Other...") {
			name.setText(Emc_eufar.airInstNameBox.getText());
			manufacturer.setText(Emc_eufar.airInstManufacturerBox.getText());
			if (manufacturer.getText() == "" || name.getText() == "") {
				instrumentBoxMissing = 1;
			}
		} else {
			final String string = Emc_eufar.airInstrumentLst.getSelectedItemText();
			final int offset = string.lastIndexOf(" - ");
			manufacturer.setText(string.substring(0, offset));
			name.setText(string.substring(offset + 3));
		}
		int instrumentAlreadyEntered = 0;
		for (int i = 0; i < Emc_eufar.instrumentTabList.size(); i++) {
			if (name.getText() == Emc_eufar.instrumentTabList.get(i) && manufacturer.getText() == Emc_eufar.manufacturerTabList.get(i)) {
				instrumentAlreadyEntered = 1;
			}
		}
		if (instrumentAlreadyEntered == 0 && instrumentBoxMissing == 0) {
			delButton.setPixelSize(25, 25);
			delButton.setStyleName("infoButton");
			if (row == 0) {
				final Label nameTitle = new Label("Instrument:");
				final Label manufacturerTitle = new Label("Manufacturer:");
				Emc_eufar.airInstrumentTable.insertRow(row);
				Emc_eufar.airInstrumentTable.setWidget(row, 0, nameTitle);
				Emc_eufar.airInstrumentTable.setWidget(row, 1, manufacturerTitle);
				row++;
				nameTitle.setStyleName("airTitleTextLabel3");
				manufacturerTitle.setStyleName("airTitleTextLabel3");
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
			name.setStyleName("airFlexTableLabel7");
			manufacturer.setStyleName("airFlexTableLabel7");
			Emc_eufar.instrumentTabList.add(name.getText());
			Emc_eufar.manufacturerTabList.add(manufacturer.getText());
			Emc_eufar.airInstrumentTable.getElement().setAttribute("style", ""
					+ "margin-left: 65px;"
					+ "margin-top: 10px;"
					+ "width: 700px;");
			image.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					int rowIndex = Emc_eufar.airInstrumentTable.getCellForEvent(event).getRowIndex();
					Emc_eufar.airInstrumentTable.removeRow(rowIndex);
					Emc_eufar.instrumentTabList.remove(rowIndex-1);
					Emc_eufar.manufacturerTabList.remove(rowIndex-1);
					int row = Emc_eufar.airInstrumentTable.getRowCount();
					if (row == 1) {
						Emc_eufar.airInstrumentTable.removeRow(row-1);
					}
					if (Emc_eufar.qvTabPanel.getWidgetCount() >= 1) {
						updateInstrumentListbox();
					}
				}
			});
			if (Emc_eufar.qvTabPanel.getWidgetCount() >= 1) {
				updateInstrumentListbox();
			}
			Emc_eufar.rootLogger.log(Level.INFO, "Instrument added.");
		}
	}
	
	
	// same as above but dedicated to the read function
	public static void addInstRead(final String nameStr, final String manufacturerStr) {
		Emc_eufar.rootLogger.log(Level.INFO, "Instrument add in progress...");
		int row = Emc_eufar.airInstrumentTable.getRowCount();
		final Image image = new Image(Emc_eufar.resources.delete().getSafeUri());
		image.setSize("21px","21px");
		image.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		final SimplePanel delButton = new SimplePanel(image);
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
			nameTitle.setStyleName("airTitleTextLabel3");
			manufacturerTitle.setStyleName("airTitleTextLabel3");
		}
		Emc_eufar.airInstrumentTable.insertRow(row);
		Emc_eufar.airInstrumentTable.setWidget(row, 0, name);
		Emc_eufar.airInstrumentTable.setWidget(row, 1, manufacturer);
		Emc_eufar.airInstrumentTable.setWidget(row, 2, delButton);
		FlexCellFormatter cellFormatter = Emc_eufar.airInstrumentTable.getFlexCellFormatter();
		cellFormatter.setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_CENTER);
		cellFormatter.setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_CENTER);
		name.setStyleName("airFlexTableLabel7");
		manufacturer.setStyleName("airFlexTableLabel7");
		Emc_eufar.instrumentTabList.add(name.getText());
		Emc_eufar.manufacturerTabList.add(manufacturer.getText());
		Emc_eufar.airInstrumentTable.getElement().setAttribute("style", ""
				+ "margin-left: 65px;"
				+ "margin-top: 10px;"
				+ "width: 700px;");
		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = Emc_eufar.airInstrumentTable.getCellForEvent(event).getRowIndex();
				Emc_eufar.airInstrumentTable.removeRow(rowIndex);
				Emc_eufar.instrumentTabList.remove(rowIndex-1);
				Emc_eufar.manufacturerTabList.remove(rowIndex-1);
				int row = Emc_eufar.airInstrumentTable.getRowCount();
				if (row == 1) {
					Emc_eufar.airInstrumentTable.removeRow(row-1);
				}
				if (Emc_eufar.qvTabPanel.getWidgetCount() >= 1) {
					updateInstrumentListbox();
				}
			}
		});
		if (Emc_eufar.qvTabPanel.getWidgetCount() >= 1) {
			updateInstrumentListbox();
		}
		Emc_eufar.rootLogger.log(Level.INFO, "Instrument added.");
	}
	
	
	// add an instrument
	public static void addAircraftPlus() {
		Emc_eufar.rootLogger.log(Level.INFO, "Aircraft add in progress...");
		Utilities.docIsModified();
		int row = Emc_eufar.airAircraftTable.getRowCount();
		final Image image = new Image(Emc_eufar.resources.delete().getSafeUri());
		image.setSize("21px","21px");
		image.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		final SimplePanel delButton = new SimplePanel(image);
		final Label name = new Label();
		final Label operator = new Label();
		final Label manufacturer = new Label();
		final Label country = new Label();
		final Label identification = new Label();
		int aircraftBoxMissing = 0;
		if (Emc_eufar.airAircraftLst.getSelectedItemText() == "Other...") {
			manufacturer.setText(Emc_eufar.airManufacturerBox.getText());
			name.setText(Emc_eufar.airTypeBox.getText());
			operator.setText(Emc_eufar.airOperatorBox.getText());
			country.setText(Emc_eufar.airCountryLst.getSelectedItemText());
			identification.setText(Emc_eufar.airRegistrationBox.getText());
			if (manufacturer.getText() == "" || name.getText() == "" || operator.getText() == "" || country.getText() == "" || 
					identification.getText() == "") {
				aircraftBoxMissing = 1;
			}
		} else {
			name.setText(Emc_eufar.airTypeInfo.getText());
			operator.setText(Emc_eufar.airOperatorInfo.getText());
			manufacturer.setText(Emc_eufar.airManufacturerInfo.getText());
			country.setText(Emc_eufar.airCountryInfo.getText());
			identification.setText(Emc_eufar.airRegistrationInfo.getText());
		}
		int aircraftAlreadyEntered = 0;
		for (int i = 0; i < Emc_eufar.aircraftTabList.size(); i++) {
			if (name.getText() == Emc_eufar.aircraftTabList.get(i) && operator.getText() == Emc_eufar.operatorTabList.get(i)) {
				aircraftAlreadyEntered = 1;
			}
		}
		if (aircraftAlreadyEntered == 0 && aircraftBoxMissing == 0) {
			delButton.setPixelSize(25, 25);
			delButton.setStyleName("infoButton");
			if (row == 0) {
				final Label nameTitle = new Label("Aircraft");
				final Label manufacturerTitle = new Label("Operator");
				Emc_eufar.airAircraftTable.insertRow(row);
				Emc_eufar.airAircraftTable.setWidget(row, 0, nameTitle);
				Emc_eufar.airAircraftTable.setWidget(row, 1, manufacturerTitle);
				row++;
				nameTitle.setStyleName("airTitleTextLabel3");
				manufacturerTitle.setStyleName("airTitleTextLabel3");
			}
			Emc_eufar.airAircraftTable.insertRow(row);
			Emc_eufar.airAircraftTable.setWidget(row, 0, name);
			Emc_eufar.airAircraftTable.setWidget(row, 1, operator);
			Emc_eufar.airAircraftTable.setWidget(row, 2, delButton);
			FlexCellFormatter cellFormatter = Emc_eufar.airAircraftTable.getFlexCellFormatter();
			cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
			cellFormatter.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
			cellFormatter.setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_CENTER);
			cellFormatter.setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_CENTER);
			name.setStyleName("airFlexTableLabel7");
			operator.setStyleName("airFlexTableLabel7");
			Emc_eufar.aircraftTabList.add(name.getText());
			Emc_eufar.operatorTabList.add(operator.getText());
			Emc_eufar.manufacturairTabList.add(manufacturer.getText());
			Emc_eufar.countryTabList.add(country.getText());
			Emc_eufar.identificationTabList.add(identification.getText());
			Emc_eufar.airAircraftTable.getElement().setAttribute("style", ""
					+ "margin-left: 65px;"
					+ "margin-top: 10px;"
					+ "width: 700px;");
			image.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					int rowIndex = Emc_eufar.airAircraftTable.getCellForEvent(event).getRowIndex();
					Emc_eufar.airAircraftTable.removeRow(rowIndex);
					Emc_eufar.aircraftTabList.remove(rowIndex-1);
					Emc_eufar.operatorTabList.remove(rowIndex-1);
					int row = Emc_eufar.airAircraftTable.getRowCount();
					if (row == 1) {
						Emc_eufar.airAircraftTable.removeRow(row-1);
					}
				}
			});
			Emc_eufar.rootLogger.log(Level.INFO, "Aircraft added.");
		}
	}
	
	
	// add an instrument
	public static void addAircraftRead(final String nameStr, final String manufacturerStr, final String operatorStr,
			final String countryStr, final String registrationStr) {
		Emc_eufar.rootLogger.log(Level.INFO, "Aircraft add in progress...");
		int row = Emc_eufar.airAircraftTable.getRowCount();
		final Image image = new Image(Emc_eufar.resources.delete().getSafeUri());
		image.setSize("21px","21px");
		image.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		final SimplePanel delButton = new SimplePanel(image);
		final Label name = new Label(nameStr);
		final Label operator = new Label(operatorStr);
		final Label manufacturer = new Label(manufacturerStr);
		final Label country = new Label(countryStr);
		final Label identification = new Label(registrationStr);
		delButton.setPixelSize(25, 25);
		delButton.setStyleName("infoButton");
		if (row == 0) {
			final Label nameTitle = new Label("Aircraft");
			final Label manufacturerTitle = new Label("Operator");
			Emc_eufar.airAircraftTable.insertRow(row);
			Emc_eufar.airAircraftTable.setWidget(row, 0, nameTitle);
			Emc_eufar.airAircraftTable.setWidget(row, 1, manufacturerTitle);
			row++;
			nameTitle.setStyleName("airTitleTextLabel3");
			manufacturerTitle.setStyleName("airTitleTextLabel3");
		}
		Emc_eufar.airAircraftTable.insertRow(row);
		Emc_eufar.airAircraftTable.setWidget(row, 0, name);
		Emc_eufar.airAircraftTable.setWidget(row, 1, operator);
		Emc_eufar.airAircraftTable.setWidget(row, 2, delButton);
		FlexCellFormatter cellFormatter = Emc_eufar.airAircraftTable.getFlexCellFormatter();
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		cellFormatter.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		cellFormatter.setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_CENTER);
		cellFormatter.setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_CENTER);
		name.setStyleName("airFlexTableLabel7");
		operator.setStyleName("airFlexTableLabel7");
		Emc_eufar.aircraftTabList.add(name.getText());
		Emc_eufar.operatorTabList.add(operator.getText());
		Emc_eufar.manufacturairTabList.add(manufacturer.getText());
		Emc_eufar.countryTabList.add(country.getText());
		Emc_eufar.identificationTabList.add(identification.getText());
		Emc_eufar.airAircraftTable.getElement().setAttribute("style", ""
				+ "margin-left: 65px;"
				+ "margin-top: 10px;"
				+ "width: 700px;");
		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = Emc_eufar.airAircraftTable.getCellForEvent(event).getRowIndex();
				Emc_eufar.airAircraftTable.removeRow(rowIndex);
				Emc_eufar.aircraftTabList.remove(rowIndex-1);
				Emc_eufar.operatorTabList.remove(rowIndex-1);
				int row = Emc_eufar.airAircraftTable.getRowCount();
				if (row == 1) {
					Emc_eufar.airAircraftTable.removeRow(row-1);
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Aircraft added.");
	}
	
	
	// update all Aircraft information when a particular aircraft is selected in the Aircraft and Instruments tab
	public static void aircraftInformation(final int index) {
		Emc_eufar.rootLogger.log(Level.INFO, "Aircraft information function invoked...");
		if (Emc_eufar.airAircraftLst.getSelectedItemText() == "Make a choice...") {
			Emc_eufar.airManufacturerInfo.setVisible(true);
			Emc_eufar.airTypeInfo.setVisible(true);
			Emc_eufar.airOperatorInfo.setVisible(true);
			Emc_eufar.airCountryInfo.setVisible(true);
			Emc_eufar.airRegistrationInfo.setVisible(true);
			Emc_eufar.airManufacturerBox.setVisible(false);
			Emc_eufar.airTypeBox.setVisible(false);
			Emc_eufar.airOperatorBox.setVisible(false);
			Emc_eufar.airCountryLst.setVisible(false);
			Emc_eufar.airRegistrationBox.setVisible(false);
			Emc_eufar.airManufacturerInfo.setText("");
			Emc_eufar.airTypeInfo.setText("");
			Emc_eufar.airOperatorInfo.setText("");
			Emc_eufar.airCountryInfo.setText("");
			Emc_eufar.airRegistrationInfo.setText("");
			Emc_eufar.airAircraftImg.setResource(Emc_eufar.airAircraftImages.get(0));
			Emc_eufar.airCopyrightInfo.setText("EUFAR");
			Emc_eufar.rootLogger.log(Level.INFO, "Make a choice... loaded.");
		} else if (Emc_eufar.airAircraftLst.getSelectedItemText() == "Other...") {
			Emc_eufar.airManufacturerInfo.setVisible(false);
			Emc_eufar.airTypeInfo.setVisible(false);
			Emc_eufar.airOperatorInfo.setVisible(false);
			Emc_eufar.airCountryInfo.setVisible(false);
			Emc_eufar.airRegistrationInfo.setVisible(false);
			Emc_eufar.airManufacturerBox.setText("");
			Emc_eufar.airTypeBox.setText("");
			Emc_eufar.airOperatorBox.setText("");
			Emc_eufar.airCountryLst.setSelectedIndex(0);
			Emc_eufar.airRegistrationBox.setText("");
			Utilities.populateListBox(Emc_eufar.airCountryLst, Emc_eufar.countryList, 0);
			Emc_eufar.airAircraftImg.setResource(Emc_eufar.airAircraftImages.get(0));
			Emc_eufar.airCopyrightInfo.setText("EUFAR");
			Emc_eufar.airManufacturerInfo.setText("");
			Emc_eufar.airTypeInfo.setText("");
			Emc_eufar.airOperatorInfo.setText("");
			Emc_eufar.airCountryInfo.setText("");
			Emc_eufar.airRegistrationInfo.setText("");
			Emc_eufar.airManufacturerBox.setVisible(true);
			Emc_eufar.airTypeBox.setVisible(true);
			Emc_eufar.airOperatorBox.setVisible(true);
			Emc_eufar.airCountryLst.setVisible(true);
			Emc_eufar.airRegistrationBox.setVisible(true);
			Emc_eufar.airManufacturerBox.setStyleName("airTextBox");
			Emc_eufar.airTypeBox.setStyleName("airTextBox");
			Emc_eufar.airOperatorBox.setStyleName("airTextBox");
			Emc_eufar.airCountryLst.setStyleName("airTextList3");
			Emc_eufar.airRegistrationBox.setStyleName("airTextBox");
		} else {
			Emc_eufar.airManufacturerInfo.setVisible(true);
			Emc_eufar.airTypeInfo.setVisible(true);
			Emc_eufar.airOperatorInfo.setVisible(true);
			Emc_eufar.airCountryInfo.setVisible(true);
			Emc_eufar.airRegistrationInfo.setVisible(true);
			Emc_eufar.airManufacturerBox.setVisible(false);
			Emc_eufar.airTypeBox.setVisible(false);
			Emc_eufar.airOperatorBox.setVisible(false);
			Emc_eufar.airCountryLst.setVisible(false);
			Emc_eufar.airRegistrationBox.setVisible(false);
			Emc_eufar.airManufacturerInfo.setText(Emc_eufar.airAircraftInfo[index-1][1]);
			Emc_eufar.airTypeInfo.setText(Emc_eufar.airAircraftInfo[index-1][2]);
			Emc_eufar.airOperatorInfo.setText(Emc_eufar.airAircraftInfo[index-1][3]);
			Emc_eufar.airCountryInfo.setText(Emc_eufar.airAircraftInfo[index-1][4]);
			Emc_eufar.airRegistrationInfo.setText(Emc_eufar.airAircraftInfo[index-1][5]);
			Emc_eufar.airCopyrightInfo.setText(Emc_eufar.airAircraftInfo[index-1][6]);
			Emc_eufar.airAircraftImg.setResource(Emc_eufar.airAircraftImages.get(index - 1));
			Emc_eufar.rootLogger.log(Level.INFO, "Aircraft information loaded.");
		}
	}
	
	
	// add textbox if the user selected "Other..." in the instrument panel
	public static void otherInstrument() {
		if (Emc_eufar.airInstrumentLst.getSelectedItemText() == "Other...") {
			Emc_eufar.airInstNameLab.setVisible(true);
			Emc_eufar.airInstNameBox.setVisible(true);
			Emc_eufar.airInstManufacturerLab.setVisible(true);
			Emc_eufar.airInstManufacturerBox.setVisible(true);
		} else {
			Emc_eufar.airInstNameLab.setVisible(false);
			Emc_eufar.airInstNameBox.setVisible(false);
			Emc_eufar.airInstManufacturerLab.setVisible(false);
			Emc_eufar.airInstManufacturerBox.setVisible(false);
		}
	}
	
	
	// add a tab to the QV panel
	public static void addQvTab(String domain) {
		Emc_eufar.rootLogger.log(Level.INFO, "Adding a QV form to the GUI - " + domain);
		final int tabNum = Emc_eufar.qvTabPanel.getWidgetCount();
		final PushButton pushButton = new PushButton("");
		if (domain == "insitu") {
			if (tabNum == 0) {
				Emc_eufar.simplePanel01.add(Emc_eufar.qvTabPanel);
				Emc_eufar.qvTabPanel.setHeight("710px");
			}
			Emc_eufar.insituNum++;
			pushButton.setText("Atmospheric/In-situ data " + Integer.toString(Emc_eufar.insituNum));
			SimplePanel insituPanel = insituMainPanel(Emc_eufar.insituNum);
			Emc_eufar.qvTabPanel.add(insituPanel, pushButton);
			updateControlListbox(domain);
		} else if (domain == "imagery") {
			if (tabNum == 0) {
				Emc_eufar.simplePanel01.add(Emc_eufar.qvTabPanel);
				Emc_eufar.qvTabPanel.setHeight("1200px");
			}
			Emc_eufar.imageryNum++;
			pushButton.setText("Earth observation/Remote sensing data " + Integer.toString(Emc_eufar.imageryNum));
			SimplePanel imageryPanel = imageryMainPanel(Emc_eufar.imageryNum);
			Emc_eufar.qvTabPanel.add(imageryPanel, pushButton);
			updateControlListbox("imagery");
		}
		pushButton.setStyleName("delTabButton");
		pushButton.addMouseUpHandler(new MouseUpHandler() {
			@Override
			public void onMouseUp(MouseUpEvent event) {
				int posx = pushButton.getAbsoluteLeft();
				int posy = pushButton.getAbsoluteTop();
				int sizex = pushButton.getOffsetWidth();
				int sizey = pushButton.getOffsetHeight();
				int mousePosX = event.getClientX();
				int mousePosY = event.getClientY();
				if (mousePosX >= (posx + sizex - 15) && mousePosX <= (posx + sizex) && mousePosY >= posy && mousePosY <= posy + sizey) {
					Emc_eufar.rootLogger.log(Level.INFO, "Removing QV form from the GUI");
					int tabNum = Emc_eufar.qvTabPanel.getWidgetCount();
					Widget eachButton;
					String buttonText = "";
					int tabIndex = -1;
					for (int i = 0; i < tabNum; i++) {
						eachButton = Emc_eufar.qvTabPanel.getTabWidget(i);
						if (eachButton == pushButton) {
							tabIndex = i;
							buttonText = ((PushButton) eachButton).getText();
							break;
						}
					}
					if (buttonText.contains("Atmospheric")) {
						int num = Integer.parseInt(buttonText.substring(25));
						Emc_eufar.insituNum--;
						Emc_eufar.qvInsituMap.remove(num - 1);
					} else if (buttonText.contains("Earth")) {
						int num = Integer.parseInt(buttonText.substring(38));
						Emc_eufar.imageryNum--;
						Emc_eufar.qvImageryMap.remove(num - 1);
					}
					Emc_eufar.qvTabPanel.remove(tabIndex);
					int actualTab = 0;
					try {
						actualTab = Emc_eufar.qvTabPanel.getSelectedIndex();
						if (((PushButton) Emc_eufar.qvTabPanel.getTabWidget(actualTab)).getText().contains("Atmospheric")) {
							Emc_eufar.qvTabPanel.setHeight("710px");
						} else if (((PushButton) Emc_eufar.qvTabPanel.getTabWidget(actualTab)).getText().contains("Earth")) {
							Emc_eufar.qvTabPanel.setHeight("1200px");
						}
					} catch (AssertionError | IndexOutOfBoundsException e) {}
					tabNum = Emc_eufar.qvTabPanel.getWidgetCount();
					if (tabNum == 0) {
						Emc_eufar.simplePanel01.remove(Emc_eufar.qvTabPanel);
					} else {
						int cleanInsituNum = 1;
						int cleanimageryNum = 1;
						for (int i = 0; i < tabNum; i++) {
							PushButton tmpPushButton = (PushButton) Emc_eufar.qvTabPanel.getTabWidget(i);
							if (tmpPushButton.getText().contains("Atmospheric")) {
								tmpPushButton.setText("Atmospheric/In-situ data " + Integer.toString(cleanInsituNum));
								cleanInsituNum++;
							} else if (tmpPushButton.getText().contains("Earth")) {
								tmpPushButton.setText("Earth observation/Remote sensing data " + Integer.toString(cleanimageryNum));
								cleanimageryNum++;
							}
						}
						updateControlListbox("insitu");
						updateControlListbox("imagery");
					}
					Emc_eufar.rootLogger.log(Level.INFO, "QV form removed from the GUI");
				} else if (mousePosX >= (posx) && mousePosX <= (posx + sizex - 16) && mousePosY >= posy && mousePosY <= posy + sizey){
					int tabNum = Emc_eufar.qvTabPanel.getWidgetCount();
					Widget eachButton;
					int tabIndex = -1;
					for (int i = 0; i < tabNum; i++) {
						eachButton = Emc_eufar.qvTabPanel.getTabWidget(i);
						if (eachButton == pushButton) {
							tabIndex = i;
							break;
						}
					}
					if (((PushButton) Emc_eufar.qvTabPanel.getTabWidget(tabIndex)).getText().contains("Atmospheric")) {
						Emc_eufar.qvTabPanel.setHeight("710px");
					} else if (((PushButton) Emc_eufar.qvTabPanel.getTabWidget(tabIndex)).getText().contains("Earth")) {
						Emc_eufar.qvTabPanel.setHeight("1200px");
					}
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "QV form '" + domain + "' added to the GUI");
	}
	
	
	// creating insitu widgets in panel
	@SuppressWarnings("rawtypes")
	public static SimplePanel insituMainPanel(final Integer tabNum) {
		Emc_eufar.rootLogger.log(Level.INFO, "Building of the In-situ QV form");
		SimplePanel simplePanel = new SimplePanel();
		VerticalPanel mainPanel = new VerticalPanel();
		simplePanel.add(mainPanel);
		VerticalPanel VerticalPanel01 = new VerticalPanel();
		VerticalPanel VerticalPanel02 = new VerticalPanel();
		HorizontalPanel horizontalPanel01 = new HorizontalPanel();
		HorizontalPanel horizontalPanel02 = new HorizontalPanel();
		HorizontalPanel horizontalPanel03 = new HorizontalPanel();
		HorizontalPanel horizontalPanel04 = new HorizontalPanel();
		HorizontalPanel horizontalPanel05 = new HorizontalPanel();
		HorizontalPanel horizontalPanel06 = new HorizontalPanel();
		HorizontalPanel horizontalPanel07 = new HorizontalPanel();
		HorizontalPanel horizontalPanel08 = new HorizontalPanel();
		HorizontalPanel horizontalPanel09 = new HorizontalPanel();
		HorizontalPanel horizontalPanel10 = new HorizontalPanel();
		HorizontalPanel horizontalPanel11 = new HorizontalPanel();
		final HorizontalPanel horizontalPanel12 = new HorizontalPanel();
		HorizontalPanel horizontalPanel13 = new HorizontalPanel();
		FlexTable insituCalTab = new FlexTable();
		TextBox insituLinkBox = new TextBox();
		TextBox insituConstBox = new TextBox();
		TextBox insituMatBox = new TextBox();
		final TextBox insituOtherBox = new TextBox();
		TextArea insituFlagAre = new TextArea();
		TextArea insituAssumptionAre = new TextArea();
		final ListBox insituControlLst01 = new ListBox();
		final ListBox insituControlLst02 = new ListBox();
		final ListBox insituInstrumentLst = new ListBox();
		ArrayList<String> controlList = new ArrayList<String>();
		ArrayList<String> instrumentList = new ArrayList<String>();
 		final HorizontalPanel insituChk01Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "1","Yes");
		final HorizontalPanel insituChk01N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "1","No");
		final HorizontalPanel insituChk04 = Elements.checkBox("NetCDF");
		final HorizontalPanel insituChk05 = Elements.checkBox("HDF");
		final HorizontalPanel insituChk06 = Elements.checkBox("NASA/Ames");
		final HorizontalPanel insituChk07 = Elements.checkBox("Other");
		SimplePanel qvInfoButton02 = Elements.addInfoButton("qvLineage2");
		SimplePanel qvInfoButton03 = Elements.addInfoButton("qvLineage3");
		SimplePanel qvInfoButton04 = Elements.addInfoButton("qvLineage4");
		SimplePanel qvInfoButton05 = Elements.addInfoButton("qvLineage5");
		SimplePanel qvInfoButton06 = Elements.addInfoButton("qvLineage19");
		SimplePanel qvInfoButton07 = Elements.addInfoButton("qvLineage20");
		Label insituStarLab02 = new Label("*");
		Label insituStarLab03 = new Label("*");
		Label insituStarLab04 = new Label("*");
		Label insituStarLab05 = new Label("*");
		Label insituStarLab06 = new Label("*");
		Label insituStarLab07 = new Label("*");
		Label insituStarLab08 = new Label("*");
		Label insituStarLab09 = new Label("*");
		Label insituCalLab = new Label("Operator's standard calibration procedures applied to raw digital data");
		Label insituGeoLab = new Label("Conversion to geophysical units");
		Label insituOutLab = new Label("Output in standardized format");
		Label insituFlaLab = new Label("Quality-control flagging applied to individual data points");
		Label insituAssLab = new Label("Assumption");
		Label insituLinkLab = new Label("Link to the procedure's description");
		Label insituConstLab = new Label("Source of calibration constants");
		Label insituMatLab = new Label("Source of calibration materials");
		Label insituControlLab = new Label("Tab controls");
		Label insituInstrumentLab = new Label("The current form is linked to the following instrument");
		final Image insituImage = new Image(Emc_eufar.resources.forward().getSafeUri());
		Image image01 = new Image(Emc_eufar.resources.transfert().getSafeUri());
		image01.setSize("21px","21px");
		image01.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		final Image image02 = new Image(Emc_eufar.resources.forward().getSafeUri());
		image02.setStyleName("qv_insituForwardImage2");
		SimplePanel insituControlButton = new SimplePanel(image01);
		controlList.add("Make a choice...");
		controlList.add("Copy all form content to a new tab");
		controlList.add("Copy all form content to an existing tab");
		Utilities.populateListBox(insituControlLst01, controlList, 0);
		instrumentList.add("Make a choice...");
		for (int i = 0; i < Emc_eufar.instrumentTabList.size(); i++) {
			instrumentList.add(Emc_eufar.manufacturerTabList.get(i) + " - " + Emc_eufar.instrumentTabList.get(i));
		}
		Utilities.populateListBox(insituInstrumentLst, instrumentList, 0);
		insituControlButton.setPixelSize(25, 25);
		insituControlButton.setStyleName("infoButton");
		horizontalPanel12.add(insituControlLab);
		horizontalPanel12.add(insituControlLst01);
		horizontalPanel12.add(image02);
		horizontalPanel12.add(insituControlLst02);
		horizontalPanel12.add(insituControlButton);
		horizontalPanel12.add(qvInfoButton06);
		horizontalPanel13.add(insituInstrumentLab);
		horizontalPanel13.add(insituStarLab09);
		horizontalPanel13.add(insituInstrumentLst);
		horizontalPanel13.add(qvInfoButton07);
		horizontalPanel01.add(insituCalLab);
		horizontalPanel01.add(qvInfoButton02);
		horizontalPanel02.add(insituOutLab);
		horizontalPanel02.add(insituStarLab06);
		horizontalPanel02.add(qvInfoButton03);
		horizontalPanel03.add(insituFlaLab);
		horizontalPanel03.add(insituStarLab07);
		horizontalPanel03.add(qvInfoButton04);
		horizontalPanel04.add(insituAssLab);
		horizontalPanel04.add(insituStarLab08);
		horizontalPanel04.add(qvInfoButton05);
		horizontalPanel05.add(insituGeoLab);
		horizontalPanel05.add(insituStarLab05);
		horizontalPanel06.add(insituLinkLab);
		horizontalPanel06.add(insituStarLab02);
		horizontalPanel07.add(insituConstLab);
		horizontalPanel07.add(insituStarLab03);
		horizontalPanel08.add(insituMatLab);
		horizontalPanel08.add(insituStarLab04);
		horizontalPanel09.add(insituChk01Y);
		horizontalPanel09.add(insituChk01N);
		VerticalPanel01.add(insituChk04);
		VerticalPanel01.add(insituChk05);
		VerticalPanel02.add(insituChk06);
		VerticalPanel02.add(horizontalPanel11);
		horizontalPanel10.add(VerticalPanel01);
		horizontalPanel10.add(VerticalPanel02);
		horizontalPanel11.add(insituChk07);
		horizontalPanel11.add(insituImage);
		horizontalPanel11.add(insituOtherBox);
		insituCalTab.setWidget(0, 0, horizontalPanel06);
		insituCalTab.setWidget(0, 1, insituLinkBox);
		insituCalTab.setWidget(1, 0, horizontalPanel07);
		insituCalTab.setWidget(1, 1, insituConstBox);
		insituCalTab.setWidget(2, 0, horizontalPanel08);
		insituCalTab.setWidget(2, 1, insituMatBox);
		FlexCellFormatter cellFormatter = insituCalTab.getFlexCellFormatter();
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter.setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter.setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		mainPanel.add(horizontalPanel12);
		mainPanel.add(horizontalPanel13);
		mainPanel.add(horizontalPanel01);
		mainPanel.add(insituCalTab);
		mainPanel.add(horizontalPanel05);
		mainPanel.add(horizontalPanel09);
		mainPanel.add(horizontalPanel02);
		mainPanel.add(horizontalPanel10);
		mainPanel.add(horizontalPanel03);
		mainPanel.add(insituFlagAre);
		mainPanel.add(horizontalPanel04);
		mainPanel.add(insituAssumptionAre);
		insituInstrumentLab.setStyleName("qv_insituLabel3");
		insituInstrumentLst.setStyleName("qv_insituTextList");
		qvInfoButton07.getElement().setAttribute("style", "margin-top: 0px !important;");
		insituChk05.getElement().setAttribute("style","margin-top: 10px !important;");
		insituChk06.getElement().setAttribute("style","margin-left: 40px !important;");
		insituChk07.getElement().setAttribute("style","margin-left: 40px !important;");
		qvInfoButton02.getElement().setAttribute("style", "margin-top: 5px !important;");
		qvInfoButton03.getElement().setAttribute("style", "margin-top: -5px !important;");
		qvInfoButton04.getElement().setAttribute("style", "margin-top: -5px !important;");
		qvInfoButton05.getElement().setAttribute("style", "margin-top: -5px !important;");
		qvInfoButton06.getElement().setAttribute("style", "margin-left: 20px !important;");
		horizontalPanel11.getElement().setAttribute("style","margin-top: 10px;");
		horizontalPanel12.getElement().setAttribute("style", "margin-left: 350px; margin-top: 10px; margin-bottom: 15px;");
		horizontalPanel13.getElement().setAttribute("style", "margin-top: 20px; margin-bottom: 20px;");
		insituStarLab02.setStyleName("qvStarLabel");
		insituStarLab03.setStyleName("qvStarLabel");
		insituStarLab04.setStyleName("qvStarLabel");
		insituStarLab05.setStyleName("qvStarLabel2");
		insituStarLab06.setStyleName("qvStarLabel");
		insituStarLab07.setStyleName("qvStarLabel");
		insituStarLab08.setStyleName("qvStarLabel2");
		insituStarLab09.setStyleName("qvStarLabel");
		insituControlLab.setStyleName("qv_insituLabel2");
		insituLinkLab.setStyleName("qv_insituLabel");
		insituConstLab.setStyleName("qv_insituLabel");
		insituMatLab.setStyleName("qv_insituLabel");
		insituLinkBox.setStyleName("qv_insituBox");
		insituConstBox.setStyleName("qv_insituBox");
		insituMatBox.setStyleName("qv_insituBox");
		//insituImage.getElement().setAttribute("style", "margin-left: 23px; margin-top: 7px;");
		
		insituImage.setStyleName("qv_insituForwardImage");
		
		insituOtherBox.setStyleName("qv_otherBox");
		insituFlagAre.setStyleName("qv_insituTextAre");
		insituAssumptionAre.setStyleName("qv_insituTextAre");
		insituCalLab.setStyleName("qv_insituLabelTitle");
		insituOutLab.setStyleName("qv_insituLabelTitle2");
		insituFlaLab.setStyleName("qv_insituLabelTitle2");
		insituAssLab.setStyleName("qv_insituLabelTitle2");
		insituGeoLab.setStyleName("qv_insituLabelTitle2");
		horizontalPanel05.setStyleName("qvHorizontalPanel1");
		horizontalPanel09.setStyleName("qvHorizontalPanel2");
		horizontalPanel02.setStyleName("qvHorizontalPanel1");
		horizontalPanel10.setStyleName("qvHorizontalPanel3");
		horizontalPanel03.setStyleName("qvHorizontalPanel1");
		horizontalPanel04.setStyleName("qvHorizontalPanel1");
		insituControlLst01.setStyleName("qv_insituTextList");
		insituControlLst02.setStyleName("qv_insituTextList");
		insituImage.setVisible(false);
		insituOtherBox.setVisible(false);
		image02.setVisible(false);
		insituControlLst02.setVisible(false);
		simplePanel.getElement().setAttribute("style", "height: 100%; width: 100%;");
		mainPanel.getElement().setAttribute("style", "background: white; height: 100%; width: 100%; margin-top: -6px; margin-left: -6px;");
		ArrayList<ArrayList> insituAllLists = new ArrayList<ArrayList>();
		final ArrayList<TextBoxBase> insituAllTextBoxes = new ArrayList<TextBoxBase>();
		insituAllTextBoxes.add(insituLinkBox);
		insituAllTextBoxes.add(insituConstBox);
		insituAllTextBoxes.add(insituMatBox);
		insituAllTextBoxes.add(insituOtherBox);
		insituAllTextBoxes.add(insituFlagAre);
		insituAllTextBoxes.add(insituAssumptionAre);
		ArrayList<String> insituTextBoxFields = new ArrayList<String>();
		insituTextBoxFields.add("string");
		insituTextBoxFields.add("string");
		insituTextBoxFields.add("string");
		insituTextBoxFields.add("string");
		insituTextBoxFields.add("string");
		insituTextBoxFields.add("string");
		ArrayList<HorizontalPanel> insituAllRadioButtons = new ArrayList<HorizontalPanel>();
		insituAllRadioButtons.add(horizontalPanel09);
		ArrayList<HorizontalPanel> insituAllCheckBoxes = new ArrayList<HorizontalPanel>();
		insituAllCheckBoxes.add(horizontalPanel10);
		ArrayList<ListBox> insituAllListBoxes = new ArrayList<ListBox>();
		insituAllListBoxes.add(insituInstrumentLst);
		insituAllListBoxes.add(insituControlLst02);
		ArrayList<Image> insituAllImages = new ArrayList<Image>();
		insituAllImages.add(insituImage);
		insituAllLists.add(insituAllTextBoxes);
		insituAllLists.add(insituAllRadioButtons);
		insituAllLists.add(insituAllCheckBoxes);
		insituAllLists.add(insituAllListBoxes);
		insituAllLists.add(insituTextBoxFields);
		insituAllLists.add(insituAllImages);
		Emc_eufar.qvInsituMap.add(insituAllLists);
		for (int i = 0; i < insituAllTextBoxes.size(); i++) {
			insituAllTextBoxes.get(i).addChangeHandler(new ChangeHandler() {
				@Override
				public void onChange(ChangeEvent event) {
					Utilities.docIsModified();
				}
			});
		}
		CheckBox check1 = (CheckBox) insituChk04.getWidget(0);
		CheckBox check2 = (CheckBox) insituChk05.getWidget(0);
		CheckBox check3 = (CheckBox) insituChk06.getWidget(0);
		CheckBox check4 = (CheckBox) insituChk07.getWidget(0);
		RadioButton radioY = (RadioButton) insituChk01Y.getWidget(0);
		RadioButton radioN = (RadioButton) insituChk01N.getWidget(0);
		radioY.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Utilities.docIsModified();
			}
		});
		radioN.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Utilities.docIsModified();
			}
		});
		check1.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Utilities.docIsModified();
			}
		});
		check2.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Utilities.docIsModified();
			}
		});
		check3.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Utilities.docIsModified();
			}
		});
		check4.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Utilities.docIsModified();
			}
		});
		image01.addClickHandler(new ClickHandler() {
			@SuppressWarnings("unchecked")
			@Override
			public void onClick(ClickEvent event) {
				if (insituControlLst01.getSelectedItemText() == "Copy all form content to a new tab") {
					String string = XmlSave.createInsituCode(Emc_eufar.qvInsituMap.get(tabNum - 1), tabNum);
					GuiModification.addQvTab("insitu");
					XmlOpen.readInsituCode(string.substring(1, string.length() - 1), Emc_eufar.insituNum - 1);
				} else if (insituControlLst01.getSelectedItemText() == "Copy all form content to an existing tab") {
					if (insituControlLst02.getSelectedItemText() != "Make a choice..." && insituControlLst02.getSelectedItemText() != "") {
						int num = Integer.parseInt(insituControlLst02.getSelectedItemText().substring(25));
						String string = XmlSave.createInsituCode(Emc_eufar.qvInsituMap.get(tabNum - 1), tabNum);
						XmlOpen.readInsituCode(string.substring(1, string.length() - 1), num - 1);
					}
				}
			}
		});
		((FocusWidget) insituChk07.getWidget(0)).addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (((CheckBox) insituChk07.getWidget(0)).getValue() == true) {
					insituImage.setVisible(true);
					insituOtherBox.setVisible(true);
					//insituImage.setPixelSize(20, 12);
				} else {
					insituImage.setVisible(false);
					insituOtherBox.setVisible(false);
					//insituImage.setPixelSize(20, 12);
				}
			}
		});
		insituControlLst01.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				if (insituControlLst01.getSelectedItemText() == "Copy all form content to an existing tab") {
					image02.setVisible(true);
					insituControlLst02.setVisible(true);
					updateControlListbox("insitu");
				} else {
					image02.setVisible(false);
					insituControlLst02.setVisible(false);
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "In-situ QV form built");
		return simplePanel;
	}
	
	
	// creating insitu widgets in panel
	@SuppressWarnings("rawtypes")
	public static SimplePanel imageryMainPanel(final int tabNum) {
		Emc_eufar.rootLogger.log(Level.INFO, "Building of the Imagery QV form");
		SimplePanel simplePanel = new SimplePanel();
		VerticalPanel mainPanel = new VerticalPanel();
		simplePanel.add(mainPanel);
		SimplePanel qvInfoButton06 = Elements.addInfoButton("qvLineage6");
		SimplePanel qvInfoButton07 = Elements.addInfoButton("qvLineage7");
		SimplePanel qvInfoButton08 = Elements.addInfoButton("qvLineage8");
		SimplePanel qvInfoButton09 = Elements.addInfoButton("qvLineage9");
		SimplePanel qvInfoButton10 = Elements.addInfoButton("qvLineage10");
		SimplePanel qvInfoButton11 = Elements.addInfoButton("qvLineage11");
		SimplePanel qvInfoButton12 = Elements.addInfoButton("qvLineage12");
		SimplePanel qvInfoButton13 = Elements.addInfoButton("qvLineage13");
		SimplePanel qvInfoButton14 = Elements.addInfoButton("qvLineage14");
		SimplePanel qvInfoButton15 = Elements.addInfoButton("qvLineage15");
		SimplePanel qvInfoButton16 = Elements.addInfoButton("qvLineage16");
		SimplePanel qvInfoButton17 = Elements.addInfoButton("qvLineage17");
		SimplePanel qvInfoButton18 = Elements.addInfoButton("qvLineage18");
		SimplePanel qvInfoButton19 = Elements.addInfoButton("qvLineage19");
		SimplePanel qvInfoButton20 = Elements.addInfoButton("qvLineage20");
		HorizontalPanel horizontalPanel01 = new HorizontalPanel();
		HorizontalPanel horizontalPanel02 = new HorizontalPanel();
		HorizontalPanel horizontalPanel03 = new HorizontalPanel();
		HorizontalPanel horizontalPanel04 = new HorizontalPanel();
		HorizontalPanel horizontalPanel05 = new HorizontalPanel();
		HorizontalPanel horizontalPanel06 = new HorizontalPanel();
		HorizontalPanel horizontalPanel07 = new HorizontalPanel();
		HorizontalPanel horizontalPanel08 = new HorizontalPanel();
		HorizontalPanel horizontalPanel09 = new HorizontalPanel();
		HorizontalPanel horizontalPanel10 = new HorizontalPanel();
		HorizontalPanel horizontalPanel11 = new HorizontalPanel();
		HorizontalPanel horizontalPanel12 = new HorizontalPanel();
		HorizontalPanel horizontalPanel13 = new HorizontalPanel();
		HorizontalPanel horizontalPanel14 = new HorizontalPanel();
		HorizontalPanel horizontalPanel15 = new HorizontalPanel();
		HorizontalPanel horizontalPanel16 = new HorizontalPanel();
		HorizontalPanel horizontalPanel17 = new HorizontalPanel();
		HorizontalPanel horizontalPanel18 = new HorizontalPanel();
		HorizontalPanel horizontalPanel19 = new HorizontalPanel();
		HorizontalPanel horizontalPanel20 = new HorizontalPanel();
		HorizontalPanel horizontalPanel21 = new HorizontalPanel();
		HorizontalPanel horizontalPanel22 = new HorizontalPanel();
		HorizontalPanel horizontalPanel23 = new HorizontalPanel();
		HorizontalPanel horizontalPanel24 = new HorizontalPanel();
		HorizontalPanel horizontalPanel25 = new HorizontalPanel();
		HorizontalPanel horizontalPanel26 = new HorizontalPanel();
		HorizontalPanel horizontalPanel27 = new HorizontalPanel();
		HorizontalPanel horizontalPanel28 = new HorizontalPanel();
		HorizontalPanel horizontalPanel29 = new HorizontalPanel();
		HorizontalPanel horizontalPanel30 = new HorizontalPanel();
		HorizontalPanel horizontalPanel31 = new HorizontalPanel();
		HorizontalPanel horizontalPanel32 = new HorizontalPanel();
		HorizontalPanel horizontalPanel33 = new HorizontalPanel();
		HorizontalPanel horizontalPanel34 = new HorizontalPanel();
		HorizontalPanel horizontalPanel35 = new HorizontalPanel();
		HorizontalPanel horizontalPanel36 = new HorizontalPanel();
		HorizontalPanel horizontalPanel37 = new HorizontalPanel();
		HorizontalPanel horizontalPanel38 = new HorizontalPanel();
		HorizontalPanel horizontalPanel39 = new HorizontalPanel();
		HorizontalPanel horizontalPanel40 = new HorizontalPanel();
		HorizontalPanel horizontalPanel41 = new HorizontalPanel();
		HorizontalPanel horizontalPanel42 = new HorizontalPanel();
		HorizontalPanel horizontalPanel43 = new HorizontalPanel();
		HorizontalPanel horizontalPanel44 = new HorizontalPanel();
		HorizontalPanel horizontalPanel45 = new HorizontalPanel();
		HorizontalPanel horizontalPanel46 = new HorizontalPanel();
		HorizontalPanel horizontalPanel47 = new HorizontalPanel();
		HorizontalPanel horizontalPanel48 = new HorizontalPanel();
		HorizontalPanel horizontalPanel49 = new HorizontalPanel();
		HorizontalPanel horizontalPanel50 = new HorizontalPanel();
		HorizontalPanel horizontalPanel51 = new HorizontalPanel();
		HorizontalPanel horizontalPanel52 = new HorizontalPanel();
		HorizontalPanel horizontalPanel53 = new HorizontalPanel();
		HorizontalPanel horizontalPanel54 = new HorizontalPanel();
		HorizontalPanel horizontalPanel55 = new HorizontalPanel();
		final HorizontalPanel horizontalPanel56 = new HorizontalPanel();
		final HorizontalPanel horizontalPanel57 = new HorizontalPanel();
		Label imageStarLab02 = new Label("*");
		Label imageStarLab03 = new Label("*");
		Label imageStarLab04 = new Label("*");
		Label imageStarLab05 = new Label("*");
		Label imageStarLab06 = new Label("*");
		Label imageStarLab07 = new Label("*");
		Label imageStarLab08 = new Label("*");
		Label imageStarLab09 = new Label("*");
		Label imageStarLab10 = new Label("*");
		Label imageStarLab11 = new Label("*");
		Label imageStarLab12 = new Label("*");
		Label imageStarLab13 = new Label("*");
		Label imageStarLab14 = new Label("*");
		Label imageStarLab15 = new Label("*");
		Label imageStarLab16 = new Label("*");
		Label imageStarLab17 = new Label("*");
		Label imageStarLab18 = new Label("*");
		Label imageStarLab19 = new Label("*");
		Label imageStarLab20 = new Label("*");
		Label imageStarLab21 = new Label("*");
		Label imageStarLab22 = new Label("*");
		Label imageStarLab23 = new Label("*");
		Label imageStarLab24 = new Label("*");
		Label imageStarLab25 = new Label("*");
		Label imageStarLab26 = new Label("*");
		Label imageStarLab27 = new Label("*");
		Label imageStarLab28 = new Label("*");
		Label imageCalLab = new Label("Calibration information");
		Label imageAcqLab = new Label("Acquisition information");
		Label imageProLab = new Label("Processing information");
		Label imageLayLab = new Label("Data Quality Layers");
		Label imageNameLab = new Label("Name of calibration laboratory");
		Label imageRadLab = new Label("Date of radiometric calibration");
		Label imageSpeLab = new Label("Date of spectral calibration");
		Label imageBanLab = new Label("Number of spectral bands (spectral mode)");
		Label imageDirLab = new Label("Overall heading / fligh direction (dd)");
		Label imageAltLab = new Label("Overall altitude / average height ASL (m)");
		Label imageZenLab = new Label("Solar zenith (dd)");
		Label imageAziLab = new Label("Solar azimuth (dd)");
		Label imageAnoLab = new Label("Report anomalies in data acquisition");
		Label imageLevLab = new Label("Processing level");
		Label imageDCLab = new Label("Dark current (DC) correction ?");
		Label imageCalcorrLab = new Label("Sensor calibration and system correction");
		Label imageErrLab = new Label("Image data artefacts and processing errors");
		Label imageErrcorrLab = new Label("GPS/IMU related errors, geometric correction");
		Label imageCorrconLab = new Label("Atmospheric correction and atmospheric conditions");
		Label imageIntPixel = new Label("Aggregated interpolated pixel mask ('corrected pixels') ?");
		Label imageBadPixel = new Label("Aggregated bad pixel mask ('not corrected pixels') ?");
		Label imageSatPixel = new Label("Saturated pixels / overflow ?");
		Label imageAffPixel = new Label("Pixels affected by saturation in spatial/spectral neighbourhood ?");
		Label imagePosInfo = new Label("Problems with position information / Interpolated position information ?");
		Label imageAttInfo = new Label("Problems with attitude information / Interpolated attitude information ?");
		Label imageSyncProblem = new Label("Synchronization problems ?");
		Label imageIntGeocoding = new Label("Interpolated pixels during geocoding ?");
		Label imageAtmCorrection = new Label("Failure of atmospheric correction ?");
		Label imageCloudMask = new Label("Cloud mask ?");
		Label imageShadMask = new Label("Cloud shadow mask ?");
		Label imageHazeMask = new Label("Haze mask ?");
		Label imageRouMeasure = new Label("Critical terrain correction based on DEM roughness measure ?");
		Label imageIllAngle = new Label("Critical terrain correction based on slope/local illumination angle ?");
		Label imageBRDFGeometry = new Label("Critical BRDF geometry based on sun-sensor-terrain geometry ?");
		Label imageryControlLab = new Label("Tab controls");
		Label imageryInstrumentLab = new Label("The current form is linked to the following instrument");
		Image image01 = new Image(Emc_eufar.resources.transfert().getSafeUri());
		image01.setSize("21px","21px");
		image01.getElement().setAttribute("style", "margin-left: 2px; margin-top: 2px; height: 21px; width: 21px;");
		final Image image02 = new Image(Emc_eufar.resources.forward().getSafeUri());
		SimplePanel imageryControlButton = new SimplePanel(image01);
		TextBox imageCalBox = new TextBox();
		TextBox imageBanBox = new TextBox();
		TextBox imageDirBox = new TextBox();
		TextBox imageAltBox = new TextBox();
		TextBox imageZenBox = new TextBox();
		TextBox imageAziBox = new TextBox();
		TextBox imageAnoBox = new TextBox();
		DateBox imageRadDat = new DateBox();
		DateBox imageSpeDat = new DateBox();
		final ListBox imageLevLst = new ListBox();
		final ListBox imageryControlLst01 = new ListBox();
		final ListBox imageryControlLst02 = new ListBox();
		final ListBox imageryInstrumentLst = new ListBox();
		ArrayList<String> levelList = Materials.levelList();
		ArrayList<String> controlList = new ArrayList<String>();
		ArrayList<String> instrumentList = new ArrayList<String>();
		FlexTable imageCalAcqProTab = new FlexTable();
		FlexTable imageQuaLayTab = new FlexTable();
		HorizontalPanel imageChk10Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "3","Yes");
		HorizontalPanel imageChk10N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "3","No");
		HorizontalPanel imageChk11Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "4","Yes");
		HorizontalPanel imageChk11N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "4","No");
		HorizontalPanel imageChk12Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "5","Yes");
		HorizontalPanel imageChk12N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "5","No");
		HorizontalPanel imageChk13Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "6","Yes");
		HorizontalPanel imageChk13N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "6","No");
		HorizontalPanel imageChk14Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "7","Yes");
		HorizontalPanel imageChk14N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "7","No");
		HorizontalPanel imageChk15Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "8","Yes");
		HorizontalPanel imageChk15N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "8","No");
		HorizontalPanel imageChk16Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "9","Yes");
		HorizontalPanel imageChk16N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "9","No");
		HorizontalPanel imageChk17Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "10","Yes");
		HorizontalPanel imageChk17N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "10","No");
		HorizontalPanel imageChk18Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "11","Yes");
		HorizontalPanel imageChk18N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "11","No");
		HorizontalPanel imageChk19Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "12","Yes");
		HorizontalPanel imageChk19N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "12","No");
		HorizontalPanel imageChk20Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "13","Yes");
		HorizontalPanel imageChk20N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "13","No");
		HorizontalPanel imageChk21Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "14","Yes");
		HorizontalPanel imageChk21N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "14","No");
		HorizontalPanel imageChk22Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "15","Yes");
		HorizontalPanel imageChk22N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "15","No");
		HorizontalPanel imageChk23Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "16","Yes");
		HorizontalPanel imageChk23N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "16","No");
		HorizontalPanel imageChk24Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "17","Yes");
		HorizontalPanel imageChk24N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "17","No");
		HorizontalPanel imageChk25Y = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "18","Yes");
		HorizontalPanel imageChk25N = Elements.radioButton("radioGrp" + Integer.toString(tabNum) + "18","No");
		Utilities.populateListBox(imageLevLst, levelList, 0);
		controlList.add("Make a choice...");
		controlList.add("Copy all form content to a new tab");
		controlList.add("Copy all form content to an existing tab");
		Utilities.populateListBox(imageryControlLst01, controlList, 0);
		instrumentList.add("Make a choice...");
		for (int i = 0; i < Emc_eufar.instrumentTabList.size(); i++) {
			instrumentList.add(Emc_eufar.manufacturerTabList.get(i) + " - " + Emc_eufar.instrumentTabList.get(i));
		}
		Utilities.populateListBox(imageryInstrumentLst, instrumentList, 0);
		imageryControlButton.setPixelSize(25, 25);
		imageryControlButton.setStyleName("infoButton");
		imageRadDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		imageRadDat.setValue(new Date());
		imageSpeDat.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		imageSpeDat.setValue(new Date());
		horizontalPanel01.add(imageCalLab);
		horizontalPanel01.add(qvInfoButton06);
		horizontalPanel02.add(imageBanBox);
		horizontalPanel02.add(qvInfoButton07);
		horizontalPanel03.add(imageChk10Y);
		horizontalPanel03.add(imageChk10N);
		horizontalPanel04.add(horizontalPanel03);
		horizontalPanel04.add(qvInfoButton08);
		horizontalPanel05.add(imageLayLab);
		horizontalPanel05.add(qvInfoButton09);
		horizontalPanel06.add(imageErrcorrLab);
		horizontalPanel06.add(qvInfoButton14);
		horizontalPanel07.add(imageChk11Y);
		horizontalPanel07.add(imageChk11N);
		horizontalPanel08.add(horizontalPanel07);
		horizontalPanel08.add(qvInfoButton10);
		horizontalPanel09.add(imageChk12Y);
		horizontalPanel09.add(imageChk12N);
		horizontalPanel10.add(horizontalPanel09);
		horizontalPanel10.add(qvInfoButton11);
		horizontalPanel11.add(imageChk13Y);
		horizontalPanel11.add(imageChk13N);
		horizontalPanel12.add(horizontalPanel11);
		horizontalPanel12.add(qvInfoButton12);
		horizontalPanel13.add(imageChk14Y);
		horizontalPanel13.add(imageChk14N);
		horizontalPanel14.add(horizontalPanel13);
		horizontalPanel14.add(qvInfoButton13);
		horizontalPanel15.add(imageChk15Y);
		horizontalPanel15.add(imageChk15N);
		horizontalPanel16.add(imageChk16Y);
		horizontalPanel16.add(imageChk16N);
		horizontalPanel17.add(imageChk17Y);
		horizontalPanel17.add(imageChk17N);
		horizontalPanel18.add(imageChk18Y);
		horizontalPanel18.add(imageChk18N);
		horizontalPanel19.add(imageChk19Y);
		horizontalPanel19.add(imageChk19N);
		horizontalPanel20.add(horizontalPanel19);
		horizontalPanel20.add(qvInfoButton15);
		horizontalPanel21.add(imageChk20Y);
		horizontalPanel21.add(imageChk20N);
		horizontalPanel22.add(imageChk21Y);
		horizontalPanel22.add(imageChk21N);
		horizontalPanel23.add(imageChk22Y);
		horizontalPanel23.add(imageChk22N);
		horizontalPanel24.add(imageChk23Y);
		horizontalPanel24.add(imageChk23N);
		horizontalPanel25.add(horizontalPanel24);
		horizontalPanel25.add(qvInfoButton16);
		horizontalPanel26.add(imageChk24Y);
		horizontalPanel26.add(imageChk24N);
		horizontalPanel27.add(horizontalPanel26);
		horizontalPanel27.add(qvInfoButton17);
		horizontalPanel28.add(imageChk25Y);
		horizontalPanel28.add(imageChk25N);
		horizontalPanel29.add(horizontalPanel28);
		horizontalPanel29.add(qvInfoButton18);
		horizontalPanel30.add(imageNameLab);
		horizontalPanel30.add(imageStarLab02);
		horizontalPanel31.add(imageRadLab);
		horizontalPanel31.add(imageStarLab03);
		horizontalPanel32.add(imageSpeLab);
		horizontalPanel32.add(imageStarLab04);
		horizontalPanel33.add(imageBanLab);
		horizontalPanel33.add(imageStarLab05);
		horizontalPanel34.add(imageDirLab);
		horizontalPanel34.add(imageStarLab06);
		horizontalPanel35.add(imageAltLab);
		horizontalPanel35.add(imageStarLab07);
		horizontalPanel36.add(imageZenLab);
		horizontalPanel36.add(imageStarLab08);
		horizontalPanel37.add(imageAziLab);
		horizontalPanel37.add(imageStarLab09);
		horizontalPanel38.add(imageAnoLab);
		horizontalPanel38.add(imageStarLab10);
		horizontalPanel39.add(imageLevLab);
		horizontalPanel39.add(imageStarLab11);
		horizontalPanel40.add(imageDCLab);
		horizontalPanel40.add(imageStarLab12);
		horizontalPanel41.add(imageIntPixel);
		horizontalPanel41.add(imageStarLab13);
		horizontalPanel42.add(imageBadPixel);
		horizontalPanel42.add(imageStarLab14);
		horizontalPanel43.add(imageSatPixel);
		horizontalPanel43.add(imageStarLab15);
		horizontalPanel44.add(imageAffPixel);
		horizontalPanel44.add(imageStarLab16);
		horizontalPanel45.add(imagePosInfo);
		horizontalPanel45.add(imageStarLab17);
		horizontalPanel46.add(imageAttInfo);
		horizontalPanel46.add(imageStarLab18);
		horizontalPanel47.add(imageSyncProblem);
		horizontalPanel47.add(imageStarLab19);
		horizontalPanel48.add(imageIntGeocoding);
		horizontalPanel48.add(imageStarLab20);
		horizontalPanel49.add(imageAtmCorrection);
		horizontalPanel49.add(imageStarLab21);
		horizontalPanel50.add(imageCloudMask);
		horizontalPanel50.add(imageStarLab22);
		horizontalPanel51.add(imageShadMask);
		horizontalPanel51.add(imageStarLab23);
		horizontalPanel52.add(imageHazeMask);
		horizontalPanel52.add(imageStarLab24);
		horizontalPanel53.add(imageRouMeasure);
		horizontalPanel53.add(imageStarLab25);
		horizontalPanel54.add(imageIllAngle);
		horizontalPanel54.add(imageStarLab26);
		horizontalPanel55.add(imageBRDFGeometry);
		horizontalPanel55.add(imageStarLab27);
		horizontalPanel56.add(imageryControlLab);
		horizontalPanel56.add(imageryControlLst01);
		horizontalPanel56.add(image02);
		image02.setStyleName("qv_imageForwardImage");
		horizontalPanel56.add(imageryControlLst02);
		horizontalPanel56.add(imageryControlButton);
		horizontalPanel56.add(qvInfoButton19);
		horizontalPanel57.add(imageryInstrumentLab);
		horizontalPanel57.add(imageStarLab28);
		horizontalPanel57.add(imageryInstrumentLst);
		horizontalPanel57.add(qvInfoButton20);
		imageCalAcqProTab.setWidget(0, 0, horizontalPanel01);
		imageCalAcqProTab.setWidget(1, 0, horizontalPanel30);
		imageCalAcqProTab.setWidget(1, 1, imageCalBox);
		imageCalAcqProTab.setWidget(2, 0, horizontalPanel31);
		imageCalAcqProTab.setWidget(2, 1, imageRadDat);
		imageCalAcqProTab.setWidget(3, 0, horizontalPanel32);
		imageCalAcqProTab.setWidget(3, 1, imageSpeDat);
		imageCalAcqProTab.setWidget(4, 0, imageAcqLab);
		imageCalAcqProTab.setWidget(5, 0, horizontalPanel33);
		imageCalAcqProTab.setWidget(5, 1, horizontalPanel02);
		imageCalAcqProTab.setWidget(6, 0, horizontalPanel34);
		imageCalAcqProTab.setWidget(6, 1, imageDirBox);
		imageCalAcqProTab.setWidget(7, 0, horizontalPanel35);
		imageCalAcqProTab.setWidget(7, 1, imageAltBox);
		imageCalAcqProTab.setWidget(8, 0, horizontalPanel36);
		imageCalAcqProTab.setWidget(8, 1, imageZenBox);
		imageCalAcqProTab.setWidget(9, 0, horizontalPanel37);
		imageCalAcqProTab.setWidget(9, 1, imageAziBox);
		imageCalAcqProTab.setWidget(10, 0, horizontalPanel38);
		imageCalAcqProTab.setWidget(10, 1, imageAnoBox);
		imageCalAcqProTab.setWidget(11, 0, imageProLab);
		imageCalAcqProTab.setWidget(12, 0, horizontalPanel39);
		imageCalAcqProTab.setWidget(12, 1, imageLevLst);
		imageCalAcqProTab.setWidget(13, 0, horizontalPanel40);
		imageCalAcqProTab.setWidget(13, 1, horizontalPanel04);
		imageQuaLayTab.setWidget(0, 0, horizontalPanel05);
		imageQuaLayTab.setWidget(1, 0, imageCalcorrLab);
		imageQuaLayTab.setWidget(2, 0, horizontalPanel41);
		imageQuaLayTab.setWidget(2, 1, horizontalPanel08);
		imageQuaLayTab.setWidget(3, 0, horizontalPanel42);
		imageQuaLayTab.setWidget(3, 1, horizontalPanel10);
		imageQuaLayTab.setWidget(4, 0, imageErrLab);
		imageQuaLayTab.setWidget(5, 0, horizontalPanel43);
		imageQuaLayTab.setWidget(5, 1, horizontalPanel12);
		imageQuaLayTab.setWidget(6, 0, horizontalPanel44);
		imageQuaLayTab.setWidget(6, 1, horizontalPanel14);
		imageQuaLayTab.setWidget(7, 0, horizontalPanel06);
		imageQuaLayTab.setWidget(8, 0, horizontalPanel45);
		imageQuaLayTab.setWidget(8, 1, horizontalPanel15);
		imageQuaLayTab.setWidget(9, 0, horizontalPanel46);
		imageQuaLayTab.setWidget(9, 1, horizontalPanel16);
		imageQuaLayTab.setWidget(10, 0, horizontalPanel47);
		imageQuaLayTab.setWidget(10, 1, horizontalPanel17);
		imageQuaLayTab.setWidget(11, 0, horizontalPanel48);
		imageQuaLayTab.setWidget(11, 1, horizontalPanel18);
		imageQuaLayTab.setWidget(12, 0, imageCorrconLab);
		imageQuaLayTab.setWidget(13, 0, horizontalPanel49);
		imageQuaLayTab.setWidget(13, 1, horizontalPanel20);
		imageQuaLayTab.setWidget(14, 0, horizontalPanel50);
		imageQuaLayTab.setWidget(14, 1, horizontalPanel21);
		imageQuaLayTab.setWidget(15, 0, horizontalPanel51);
		imageQuaLayTab.setWidget(15, 1, horizontalPanel22);
		imageQuaLayTab.setWidget(16, 0, horizontalPanel52);
		imageQuaLayTab.setWidget(16, 1, horizontalPanel23);
		imageQuaLayTab.setWidget(17, 0, horizontalPanel53);
		imageQuaLayTab.setWidget(17, 1, horizontalPanel25);
		imageQuaLayTab.setWidget(18, 0, horizontalPanel54);
		imageQuaLayTab.setWidget(18, 1, horizontalPanel27);
		imageQuaLayTab.setWidget(19, 0, horizontalPanel55);
		imageQuaLayTab.setWidget(19, 1, horizontalPanel29);
		mainPanel.add(horizontalPanel56);
		mainPanel.add(horizontalPanel57);
		mainPanel.add(imageCalAcqProTab);
		mainPanel.add(imageQuaLayTab);
		FlexCellFormatter cellFormatter01 = imageCalAcqProTab.getFlexCellFormatter();
		cellFormatter01.setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter01.setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter01.setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter01.setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter01.setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter01.setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter01.setHorizontalAlignment(8, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter01.setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter01.setHorizontalAlignment(10, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter01.setHorizontalAlignment(12, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter01.setHorizontalAlignment(13, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		FlexCellFormatter cellFormatter02 = imageQuaLayTab.getFlexCellFormatter();
		cellFormatter02.setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter02.setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter02.setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter02.setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter02.setHorizontalAlignment(8, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter02.setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter02.setHorizontalAlignment(10, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter02.setHorizontalAlignment(11, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter02.setHorizontalAlignment(13, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter02.setHorizontalAlignment(14, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter02.setHorizontalAlignment(15, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter02.setHorizontalAlignment(16, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter02.setHorizontalAlignment(17, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter02.setHorizontalAlignment(18, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		cellFormatter02.setHorizontalAlignment(19, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		imageryInstrumentLab.setStyleName("qv_insituLabel3");
		imageryInstrumentLst.setStyleName("qv_insituTextList");
		qvInfoButton20.getElement().setAttribute("style", "margin-top: 0px !important;");
		horizontalPanel57.getElement().setAttribute("style", "margin-top: 20px; margin-bottom: 20px;");
		imageStarLab02.setStyleName("qvStarLabel");
		imageStarLab03.setStyleName("qvStarLabel");
		imageStarLab04.setStyleName("qvStarLabel");
		imageStarLab05.setStyleName("qvStarLabel");
		imageStarLab06.setStyleName("qvStarLabel");
		imageStarLab07.setStyleName("qvStarLabel");
		imageStarLab08.setStyleName("qvStarLabel");
		imageStarLab09.setStyleName("qvStarLabel");
		imageStarLab10.setStyleName("qvStarLabel");
		imageStarLab11.setStyleName("qvStarLabel");
		imageStarLab12.setStyleName("qvStarLabel");
		imageStarLab13.setStyleName("qvStarLabel");
		imageStarLab14.setStyleName("qvStarLabel");
		imageStarLab15.setStyleName("qvStarLabel");
		imageStarLab16.setStyleName("qvStarLabel");
		imageStarLab17.setStyleName("qvStarLabel");
		imageStarLab18.setStyleName("qvStarLabel");
		imageStarLab19.setStyleName("qvStarLabel");
		imageStarLab20.setStyleName("qvStarLabel");
		imageStarLab21.setStyleName("qvStarLabel");
		imageStarLab22.setStyleName("qvStarLabel");
		imageStarLab23.setStyleName("qvStarLabel");
		imageStarLab24.setStyleName("qvStarLabel");
		imageStarLab25.setStyleName("qvStarLabel");
		imageStarLab26.setStyleName("qvStarLabel");
		imageStarLab27.setStyleName("qvStarLabel");
		imageStarLab28.setStyleName("qvStarLabel");
		imageSpeDat.setStyleName("qv_imageDatebox");
		imageRadDat.setStyleName("qv_imageDatebox");
		imageCalLab.setStyleName("qv_imageLabelTitle");
		imageAcqLab.setStyleName("qv_imageLabelTitle");
		imageProLab.setStyleName("qv_imageLabelTitle");
		imageNameLab.setStyleName("qv_imageLabel");
		imageRadLab.setStyleName("qv_imageLabel");
		imageSpeLab.setStyleName("qv_imageLabel");
		imageBanLab.setStyleName("qv_imageLabel");
		imageDirLab.setStyleName("qv_imageLabel");
		imageAltLab.setStyleName("qv_imageLabel");
		imageZenLab.setStyleName("qv_imageLabel");
		imageAziLab.setStyleName("qv_imageLabel");
		imageAnoLab.setStyleName("qv_imageLabel");
		imageLevLab.setStyleName("qv_imageLabel");
		imageDCLab.setStyleName("qv_imageLabel");
		imageCalBox.setStyleName("qv_imageBox");
		imageBanBox.setStyleName("qv_imageBox2");
		imageDirBox.setStyleName("qv_imageBox2");
		imageAltBox.setStyleName("qv_imageBox2");
		imageZenBox.setStyleName("qv_imageBox2");
		imageAziBox.setStyleName("qv_imageBox2");
		imageAnoBox.setStyleName("qv_imageBox");
		imageLevLst.setStyleName("qv_imageList");
		imageLayLab.setStyleName("qv_imageLabelTitle");
		imageCalcorrLab.setStyleName("qv_imageLabelTitle2");
		imageErrLab.setStyleName("qv_imageLabelTitle2");
		imageErrcorrLab.setStyleName("qv_imageLabelTitle2");
		imageCorrconLab.setStyleName("qv_imageLabelTitle2");
		imageIntPixel.setStyleName("qv_imageLabel2");
		imageBadPixel.setStyleName("qv_imageLabel2");
		imageSatPixel.setStyleName("qv_imageLabel2");
		imageAffPixel.setStyleName("qv_imageLabel2");
		imagePosInfo.setStyleName("qv_imageLabel2");
		imageAttInfo.setStyleName("qv_imageLabel2");
		imageSyncProblem.setStyleName("qv_imageLabel2");
		imageIntGeocoding.setStyleName("qv_imageLabel2");
		imageAtmCorrection.setStyleName("qv_imageLabel2");
		imageCloudMask.setStyleName("qv_imageLabel2");
		imageShadMask.setStyleName("qv_imageLabel2");
		imageHazeMask.setStyleName("qv_imageLabel2");
		imageRouMeasure.setStyleName("qv_imageLabel2");
		imageIllAngle.setStyleName("qv_imageLabel2");
		imageBRDFGeometry.setStyleName("qv_imageLabel2");
		horizontalPanel04.getElement().setAttribute("style", "margin-left: 20px;");
		qvInfoButton06.getElement().setAttribute("style", "margin-top: 5px !important;");
		qvInfoButton09.getElement().setAttribute("style", "margin-top: 5px !important;");
		qvInfoButton14.getElement().setAttribute("style", "margin-top: 5px !important;");
		horizontalPanel03.setStyleName("qvHorizontalPanel5");
		horizontalPanel07.setStyleName("qvHorizontalPanel4");
		horizontalPanel09.setStyleName("qvHorizontalPanel4");
		horizontalPanel11.setStyleName("qvHorizontalPanel4");
		horizontalPanel13.setStyleName("qvHorizontalPanel4");
		horizontalPanel15.setStyleName("qvHorizontalPanel4");
		horizontalPanel16.setStyleName("qvHorizontalPanel4");
		horizontalPanel17.setStyleName("qvHorizontalPanel4");
		horizontalPanel18.setStyleName("qvHorizontalPanel4");
		horizontalPanel19.setStyleName("qvHorizontalPanel4");
		horizontalPanel21.setStyleName("qvHorizontalPanel4");
		horizontalPanel22.setStyleName("qvHorizontalPanel4");
		horizontalPanel23.setStyleName("qvHorizontalPanel4");
		horizontalPanel24.setStyleName("qvHorizontalPanel4");
		horizontalPanel26.setStyleName("qvHorizontalPanel4");
		horizontalPanel28.setStyleName("qvHorizontalPanel4");
		horizontalPanel56.getElement().setAttribute("style", "margin-left: 350px; margin-top: 10px; margin-bottom: 15px;");
		imageryControlLst01.setStyleName("qv_insituTextList");
		imageryControlLst02.setStyleName("qv_insituTextList");
		imageryControlLab.setStyleName("qv_insituLabel2");
		qvInfoButton19.getElement().setAttribute("style", "margin-left: 20px !important;");
		image02.setVisible(false);
		imageryControlLst02.setVisible(false);
		simplePanel.getElement().setAttribute("style", "height: 100%; width: 100%;");
		mainPanel.getElement().setAttribute("style", "background: white; height: 100%; width: 100%; margin-top: -6px; margin-left: -6px;");
		ArrayList<ArrayList> imageryAllLists = new ArrayList<ArrayList>();
		ArrayList<TextBoxBase> imageryAllTextBoxes = new ArrayList<TextBoxBase>();
		imageryAllTextBoxes.add(imageCalBox);
		imageryAllTextBoxes.add(imageBanBox);
		imageryAllTextBoxes.add(imageDirBox);
		imageryAllTextBoxes.add(imageAltBox);
		imageryAllTextBoxes.add(imageZenBox);
		imageryAllTextBoxes.add(imageAziBox);
		imageryAllTextBoxes.add(imageAnoBox);
		ArrayList<String> imageryTextBoxFields = new ArrayList<String>();
		imageryTextBoxFields.add("string");
		imageryTextBoxFields.add("number");
		imageryTextBoxFields.add("number");
		imageryTextBoxFields.add("number");
		imageryTextBoxFields.add("number");
		imageryTextBoxFields.add("number");
		imageryTextBoxFields.add("string");
		ArrayList<DateBox> imageryAllDateBoxes = new ArrayList<DateBox>();
		imageryAllDateBoxes.add(imageRadDat);
		imageryAllDateBoxes.add(imageSpeDat);
		ArrayList<HorizontalPanel> imageryAllRadioButtons = new ArrayList<HorizontalPanel>();
		imageryAllRadioButtons.add(horizontalPanel03);
		imageryAllRadioButtons.add(horizontalPanel07);
		imageryAllRadioButtons.add(horizontalPanel09);
		imageryAllRadioButtons.add(horizontalPanel11);
		imageryAllRadioButtons.add(horizontalPanel13);
		imageryAllRadioButtons.add(horizontalPanel15);
		imageryAllRadioButtons.add(horizontalPanel16);
		imageryAllRadioButtons.add(horizontalPanel17);
		imageryAllRadioButtons.add(horizontalPanel18);
		imageryAllRadioButtons.add(horizontalPanel19);
		imageryAllRadioButtons.add(horizontalPanel21);
		imageryAllRadioButtons.add(horizontalPanel22);
		imageryAllRadioButtons.add(horizontalPanel23);
		imageryAllRadioButtons.add(horizontalPanel24);
		imageryAllRadioButtons.add(horizontalPanel26);
		imageryAllRadioButtons.add(horizontalPanel28);
		ArrayList<ListBox> imageryAllListBoxes = new ArrayList<ListBox>();
		imageryAllListBoxes.add(imageryInstrumentLst);
		imageryAllListBoxes.add(imageLevLst);
		imageryAllListBoxes.add(imageryControlLst02);
		imageryAllLists.add(imageryAllTextBoxes);
		imageryAllLists.add(imageryAllRadioButtons);
		imageryAllLists.add(imageryAllListBoxes);
		imageryAllLists.add(imageryAllDateBoxes);
		imageryAllLists.add(imageryTextBoxFields);
		Emc_eufar.qvImageryMap.add(imageryAllLists);
		for (int i = 0; i < imageryAllTextBoxes.size(); i++) {
			imageryAllTextBoxes.get(i).addChangeHandler(new ChangeHandler() {
				@Override
				public void onChange(ChangeEvent event) {
					Utilities.docIsModified();
				}
			});
		}
		for (int i = 0; i < imageryAllRadioButtons.size(); i++) {
			RadioButton radioY = (RadioButton) ((ComplexPanel) imageryAllRadioButtons.get(i).getWidget(0)).getWidget(0);
			RadioButton radioN = (RadioButton) ((ComplexPanel) imageryAllRadioButtons.get(i).getWidget(1)).getWidget(0);
			radioY.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Utilities.docIsModified();
				}
			});
			radioN.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Utilities.docIsModified();
				}
			});
		}
		imageLevLst.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				if (imageLevLst.getSelectedItemText() != "Make a choice...")
				Utilities.docIsModified();
			}
		});
		imageRadDat.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Utilities.docIsModified();
			}
		});
		imageSpeDat.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Utilities.docIsModified();
			}
		});
		image01.addClickHandler(new ClickHandler() {
			@SuppressWarnings("unchecked")
			@Override
			public void onClick(ClickEvent event) {
				if (imageryControlLst01.getSelectedItemText() == "Copy all form content to a new tab") {
					String string = XmlSave.createImageryCode(Emc_eufar.qvImageryMap.get(tabNum - 1), tabNum);	
					GuiModification.addQvTab("imagery");
					XmlOpen.readImageryCode(string.substring(1, string.length() - 1), Emc_eufar.imageryNum - 1);
				} else if (imageryControlLst01.getSelectedItemText() == "Copy all form content to an existing tab") {
					if (imageryControlLst02.getSelectedItemText() != "Make a choice..." && imageryControlLst02.getSelectedItemText() != "") {
						int num = Integer.parseInt(imageryControlLst02.getSelectedItemText().substring(38));
						String string = XmlSave.createImageryCode(Emc_eufar.qvImageryMap.get(tabNum - 1), tabNum);
						XmlOpen.readImageryCode(string.substring(1, string.length() - 1), num - 1);
					}
				}
			}
		});
		imageryControlLst01.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				if (imageryControlLst01.getSelectedItemText() == "Copy all form content to an existing tab") {
					image02.setVisible(true);
					imageryControlLst02.setVisible(true);
					updateControlListbox("imagery");
				} else {
					image02.setVisible(false);
					imageryControlLst02.setVisible(false);
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Imagery QV form built");
		return simplePanel;
	}
	
	
	// update all listboxes in QV section if instrument list is modified
	@SuppressWarnings({ "rawtypes", "unchecked" })
	static void updateInstrumentListbox() {
		Emc_eufar.rootLogger.log(Level.INFO, "Updating of the instrument list in all QV forms invoked");
		ArrayList<ListBox> allQVListboxes = new ArrayList<ListBox>();
		if (Emc_eufar.qvInsituMap.size() > 0) {
			for (int i = 0; i < Emc_eufar.qvInsituMap.size(); i++) {
				ArrayList<ArrayList> insituAllLists = Emc_eufar.qvInsituMap.get(i);
				allQVListboxes.add((ListBox) insituAllLists.get(3).get(0));
			}
		}
		if (Emc_eufar.qvImageryMap.size() > 0) {
			for (int i = 0; i < Emc_eufar.qvImageryMap.size(); i++) {
				ArrayList<ArrayList> imageryAllLists = Emc_eufar.qvImageryMap.get(i);
				allQVListboxes.add((ListBox) imageryAllLists.get(2).get(0));
			}

		}
		for (int i = 0; i < allQVListboxes.size(); i++) {
			ListBox listBox = allQVListboxes.get(i);
			String string = listBox.getSelectedItemText();
			ArrayList<String> list = new ArrayList<String>();
			int index = 0;
			listBox.clear();
			list.add("Make a choice...");
			for (int j = 0; j < Emc_eufar.instrumentTabList.size(); j++) {
				list.add(Emc_eufar.manufacturerTabList.get(j) + " - " + Emc_eufar.instrumentTabList.get(j));
				if (string == Emc_eufar.manufacturerTabList.get(j) + " - " + Emc_eufar.instrumentTabList.get(j)) {
					index = j;
				}
			}
			for (int j = 0; j < list.size(); j++) {
				if (string == list.get(j)) {
					index = j;
					break;
				}
			}
			Utilities.populateListBox(listBox, list, index);
			Emc_eufar.rootLogger.log(Level.INFO, "Updating of the instrument list in all QV forms finished");
		}
	}
	
	
	// update all control listboxes in QV section if a tab has been added or removed - in-situ
	@SuppressWarnings({ "unchecked", "rawtypes"})
	static void updateControlListbox(String domain) {
		Emc_eufar.rootLogger.log(Level.INFO, "Updating of the tab list for control in all QV forms invoked");
		ArrayList<ListBox> allQVListboxes = new ArrayList<ListBox>();
		ArrayList<ArrayList> map = null;
		String tabString = null;
		int listIndex = 0;
		int listboxIndex = 0;
		int tabCount = 0;
		if (domain == "insitu") {
			map = Emc_eufar.qvInsituMap;
			tabString = "Atmospheric";
			listIndex = 3;
			listboxIndex = 1;
		} else if (domain == "imagery") {
			map = Emc_eufar.qvImageryMap;
			tabString = "Earth";
			listIndex = 2;
			listboxIndex = 2;
		}
		for (int i = 0; i < Emc_eufar.qvTabPanel.getWidgetCount(); i++) {
			if (((PushButton) Emc_eufar.qvTabPanel.getTabWidget(i)).getText().contains(tabString)) {
				tabCount++;
			}
		}
		for (int i = 0; i < map.size(); i++) {
			ArrayList<ArrayList> insituAllLists = map.get(i);
			allQVListboxes.add((ListBox) insituAllLists.get(listIndex).get(listboxIndex));
		}
		if (tabCount > 1) {
			ArrayList<String> allInsituTabs = new ArrayList<String>();
			for (int j = 0; j < Emc_eufar.qvTabPanel.getWidgetCount(); j++) {
				if (((PushButton) Emc_eufar.qvTabPanel.getTabWidget(j)).getText().contains(tabString)) {
					allInsituTabs.add(((PushButton) Emc_eufar.qvTabPanel.getTabWidget(j)).getText());
				}
			}
			for (ListBox listBox : allQVListboxes) {
				listBox.setEnabled(true);
				if (listBox.isVisible()) {
					int actualIndex = 0;
					Widget parent = listBox.getParent();
					while (!(parent instanceof SimplePanel)) {
						parent = parent.getParent();
					}
					Iterator<String> listIterator = allInsituTabs.iterator();
					ArrayList<String> tmp = new ArrayList<String>();
					while (listIterator.hasNext()) {
						String string = listIterator.next();
						if (string != ((PushButton) Emc_eufar.qvTabPanel.getTabWidget(
								Emc_eufar.qvTabPanel.getWidgetIndex(parent))).getText()) {
							tmp.add(string);
						}
					}
					tmp.add(0, "Make a choice...");
					for (int j = 0; j < tmp.size(); j++) {
						if (listBox.getSelectedItemText() == tmp.get(j)) {
							actualIndex = j;
						}
					}
					listBox.clear();
					Utilities.populateListBox(listBox, tmp, actualIndex);
				}
			}
		} else {
			allQVListboxes.get(0).clear();
			allQVListboxes.get(0).setEnabled(false);
		}
		Emc_eufar.rootLogger.log(Level.INFO, "Updating of the tab list for control in all QV forms finished");
	}
}
