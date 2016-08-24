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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FocusWidget;
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
	
	
	// add a new period in the Temporal Reference panel
	public static void addRefPlus() {
		Emc_eufar.rootLogger.log(Level.INFO, "Temporal ref add in progress...");
		int row = Emc_eufar.refPhaseTab.getRowCount();
		final Image image = new Image("icons/del_icon_v2.png");
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
		final Image image = new Image("icons/del_icon_v2.png");
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
	public static void addUsePlus(final FlexTable table, final ArrayList<TextArea> arrayList, final Image firstImage) {
		Emc_eufar.rootLogger.log(Level.INFO, "Constraints A/L add in progress...");
		int row = table.getRowCount();
		final Image image = new Image("icons/del_icon_v2.png");
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
		arrayList.add(textArea);
		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = table.getCellForEvent(event).getRowIndex();
				table.removeRow(rowIndex);
				arrayList.remove(rowIndex);
				int row = table.getRowCount();
				if (row == 1) {
					firstImage.setVisible(false);
				}
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Constraints A/L ref added.");
	}


	// allow the program to create new text areas based on the reading of an xml file
	public static void addUseRead(final FlexTable table, final ArrayList<TextArea> arrayList, final String string, final Image firstImage) {
		Emc_eufar.rootLogger.log(Level.INFO, "Constraints A/L add in progress...");
		int row = table.getRowCount();
		final Image image = new Image("icons/del_icon_v2.png");
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
		arrayList.add(textArea);
		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = table.getCellForEvent(event).getRowIndex();
				table.removeRow(rowIndex);
				arrayList.remove(rowIndex);
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
		final Image image = new Image("icons/del_icon_v2.png");
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
		final Image image = new Image("icons/del_icon_v2.png");
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
		verticalPanel01.add(new HTML("<hr  style=\"width:482px;height:10px;background:#0098d9;border:0px;margin-top:30px;"
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
		final Image image = new Image("icons/del_icon_v2.png");
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
		final Image image = new Image("icons/del_icon_v2.png");
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
		final Image image = new Image("icons/del_icon_v2.png");
		final SimplePanel delButton = new SimplePanel(image);
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
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Instrument added.");
	}
	
	
	// same as above but dedicated to the read function
	public static void addInstRead(final String nameStr, final String manufacturerStr) {
		Emc_eufar.rootLogger.log(Level.INFO, "Instrument add in progress...");
		int row = Emc_eufar.airInstrumentTable.getRowCount();
		final Image image = new Image("icons/del_icon_v2.png");
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
			}
		});
		Emc_eufar.rootLogger.log(Level.INFO, "Instrument added.");
	}
	
	
	// add an instrument
	public static void addAircraftPlus() {
		Emc_eufar.rootLogger.log(Level.INFO, "Aircraft add in progress...");
		Utilities.docIsModified();
		int row = Emc_eufar.airAircraftTable.getRowCount();
		final Image image = new Image("icons/del_icon_v2.png");
		final SimplePanel delButton = new SimplePanel(image);
		final Label name = new Label();
		final Label operator = new Label();
		final Label manufacturer = new Label();
		final Label country = new Label();
		final Label identification = new Label();
		if (Emc_eufar.airAircraftLst.getSelectedItemText() == "Other...") {
			manufacturer.setText(Emc_eufar.airManufacturerBox.getText());
			name.setText(Emc_eufar.airTypeBox.getText());
			operator.setText(Emc_eufar.airOperatorBox.getText());
			country.setText(Emc_eufar.airCountryLst.getSelectedItemText());
			identification.setText(Emc_eufar.airRegistrationBox.getText());
		} else {
			name.setText(Emc_eufar.airTypeInfo.getText());
			operator.setText(Emc_eufar.airOperatorInfo.getText());
			manufacturer.setText(Emc_eufar.airManufacturerInfo.getText());
			country.setText(Emc_eufar.airCountryInfo.getText());
			identification.setText(Emc_eufar.airRegistrationInfo.getText());
		}
		for (int i = 0; i < Emc_eufar.aircraftTabList.size(); i++) {
			if (name.getText() == Emc_eufar.aircraftTabList.get(i) && operator.getText() == Emc_eufar.operatorTabList.get(i)) {
				Window.alert("It is not possible to add more than once the same aircraft.");
				return;
			}
		}
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
	
	
	// add an instrument
	public static void addAircraftRead(final String nameStr, final String manufacturerStr, final String operatorStr,
			final String countryStr, final String registrationStr) {
		Emc_eufar.rootLogger.log(Level.INFO, "Aircraft add in progress...");
		int row = Emc_eufar.airAircraftTable.getRowCount();
		final Image image = new Image("icons/del_icon_v2.png");
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
			if (Emc_eufar.horizontalPanel60.getWidgetCount() > 2) {
				Emc_eufar.horizontalPanel60.remove(2);
				Emc_eufar.horizontalPanel61.remove(2);
				Emc_eufar.horizontalPanel62.remove(2);
				Emc_eufar.horizontalPanel63.remove(2);
				Emc_eufar.horizontalPanel64.remove(2);
			}
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
			Emc_eufar.airAircraftImg.setUrl("eufar_aircraft/logo_eufar_emc_v2.png");
			Emc_eufar.airCopyrightInfo.setText("EUFAR");
			Emc_eufar.rootLogger.log(Level.INFO, "Make a choice... loaded.");
		} else if (Emc_eufar.airAircraftLst.getSelectedItemText() == "Other...") {
			Utilities.populateListBox(Emc_eufar.airCountryLst, Emc_eufar.countryList, 0);
			Emc_eufar.airAircraftImg.setUrl("eufar_aircraft/logo_eufar_emc_v2.png");
			Emc_eufar.airCopyrightInfo.setText("EUFAR");
			Emc_eufar.airManufacturerInfo.setText("");
			Emc_eufar.airTypeInfo.setText("");
			Emc_eufar.airOperatorInfo.setText("");
			Emc_eufar.airCountryInfo.setText("");
			Emc_eufar.airRegistrationInfo.setText("");
			if (Emc_eufar.horizontalPanel60.getWidgetCount() > 2) {
				Emc_eufar.horizontalPanel60.remove(2);
				Emc_eufar.horizontalPanel61.remove(2);
				Emc_eufar.horizontalPanel62.remove(2);
				Emc_eufar.horizontalPanel63.remove(2);
				Emc_eufar.horizontalPanel64.remove(2);
			}
			Emc_eufar.horizontalPanel60.remove(1);
			Emc_eufar.horizontalPanel61.remove(1);
			Emc_eufar.horizontalPanel62.remove(1);
			Emc_eufar.horizontalPanel63.remove(1);
			Emc_eufar.horizontalPanel64.remove(1);
			Emc_eufar.horizontalPanel60.add(Emc_eufar.aiStarLab03);
			Emc_eufar.horizontalPanel61.add(Emc_eufar.aiStarLab04);
			Emc_eufar.horizontalPanel62.add(Emc_eufar.aiStarLab05);
			Emc_eufar.horizontalPanel63.add(Emc_eufar.aiStarLab06);
			Emc_eufar.horizontalPanel64.add(Emc_eufar.aiStarLab07);
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
			Emc_eufar.aiStarLab03.setStyleName("airStarLabel2");
			Emc_eufar.aiStarLab04.setStyleName("airStarLabel2");
			Emc_eufar.aiStarLab05.setStyleName("airStarLabel2");
			Emc_eufar.aiStarLab06.setStyleName("airStarLabel2");
			Emc_eufar.aiStarLab07.setStyleName("airStarLabel2");
		} else {
			if (Emc_eufar.horizontalPanel60.getWidgetCount() > 2) {
				Emc_eufar.horizontalPanel60.remove(2);
				Emc_eufar.horizontalPanel61.remove(2);
				Emc_eufar.horizontalPanel62.remove(2);
				Emc_eufar.horizontalPanel63.remove(2);
				Emc_eufar.horizontalPanel64.remove(2);
			}
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
	
	
	// change quality and validity target
	public static void changeTarget(final String string) {
		if (string == "Earth observation/Remote sensing data") {
			Emc_eufar.verticalPanel17.clear();
			Emc_eufar.horizontalPanel39.add(Emc_eufar.imageCalLab);
			Emc_eufar.horizontalPanel39.add(Emc_eufar.qvInfoButton06);
			Emc_eufar.horizontalPanel40.add(Emc_eufar.imageBanBox);
			Emc_eufar.horizontalPanel40.add(Emc_eufar.qvInfoButton07);
			Emc_eufar.horizontalPanel148.add(Emc_eufar.imageChk10Y);
			Emc_eufar.horizontalPanel148.add(Emc_eufar.imageChk10N);
			Emc_eufar.horizontalPanel41.add(Emc_eufar.horizontalPanel148);
			Emc_eufar.horizontalPanel41.add(Emc_eufar.qvInfoButton08);
			Emc_eufar.horizontalPanel42.add(Emc_eufar.imageLayLab);
			Emc_eufar.horizontalPanel42.add(Emc_eufar.qvInfoButton09);
			Emc_eufar.horizontalPanel43.add(Emc_eufar.imageErrcorrLab);
			Emc_eufar.horizontalPanel43.add(Emc_eufar.qvInfoButton14);
			Emc_eufar.horizontalPanel149.add(Emc_eufar.imageChk11Y);
			Emc_eufar.horizontalPanel149.add(Emc_eufar.imageChk11N);
			Emc_eufar.horizontalPanel44.add(Emc_eufar.horizontalPanel149);
			Emc_eufar.horizontalPanel44.add(Emc_eufar.qvInfoButton10);
			Emc_eufar.horizontalPanel150.add(Emc_eufar.imageChk12Y);
			Emc_eufar.horizontalPanel150.add(Emc_eufar.imageChk12N);
			Emc_eufar.horizontalPanel45.add(Emc_eufar.horizontalPanel150);
			Emc_eufar.horizontalPanel45.add(Emc_eufar.qvInfoButton11);
			Emc_eufar.horizontalPanel151.add(Emc_eufar.imageChk13Y);
			Emc_eufar.horizontalPanel151.add(Emc_eufar.imageChk13N);
			Emc_eufar.horizontalPanel46.add(Emc_eufar.horizontalPanel151);
			Emc_eufar.horizontalPanel46.add(Emc_eufar.qvInfoButton12);
			Emc_eufar.horizontalPanel152.add(Emc_eufar.imageChk14Y);
			Emc_eufar.horizontalPanel152.add(Emc_eufar.imageChk14N);
			Emc_eufar.horizontalPanel47.add(Emc_eufar.horizontalPanel152);
			Emc_eufar.horizontalPanel47.add(Emc_eufar.qvInfoButton13);
			Emc_eufar.horizontalPanel48.add(Emc_eufar.imageChk15Y);
			Emc_eufar.horizontalPanel48.add(Emc_eufar.imageChk15N);
			Emc_eufar.horizontalPanel49.add(Emc_eufar.imageChk16Y);
			Emc_eufar.horizontalPanel49.add(Emc_eufar.imageChk16N);
			Emc_eufar.horizontalPanel50.add(Emc_eufar.imageChk17Y);
			Emc_eufar.horizontalPanel50.add(Emc_eufar.imageChk17N);
			Emc_eufar.horizontalPanel51.add(Emc_eufar.imageChk18Y);
			Emc_eufar.horizontalPanel51.add(Emc_eufar.imageChk18N);
			Emc_eufar.horizontalPanel153.add(Emc_eufar.imageChk19Y);
			Emc_eufar.horizontalPanel153.add(Emc_eufar.imageChk19N);
			Emc_eufar.horizontalPanel52.add(Emc_eufar.horizontalPanel153);
			Emc_eufar.horizontalPanel52.add(Emc_eufar.qvInfoButton15);
			Emc_eufar.horizontalPanel53.add(Emc_eufar.imageChk20Y);
			Emc_eufar.horizontalPanel53.add(Emc_eufar.imageChk20N);
			Emc_eufar.horizontalPanel54.add(Emc_eufar.imageChk21Y);
			Emc_eufar.horizontalPanel54.add(Emc_eufar.imageChk21N);
			Emc_eufar.horizontalPanel55.add(Emc_eufar.imageChk22Y);
			Emc_eufar.horizontalPanel55.add(Emc_eufar.imageChk22N);
			Emc_eufar.horizontalPanel154.add(Emc_eufar.imageChk23Y);
			Emc_eufar.horizontalPanel154.add(Emc_eufar.imageChk23N);
			Emc_eufar.horizontalPanel56.add(Emc_eufar.horizontalPanel154);
			Emc_eufar.horizontalPanel56.add(Emc_eufar.qvInfoButton16);
			Emc_eufar.horizontalPanel155.add(Emc_eufar.imageChk24Y);
			Emc_eufar.horizontalPanel155.add(Emc_eufar.imageChk24N);
			Emc_eufar.horizontalPanel57.add(Emc_eufar.horizontalPanel155);
			Emc_eufar.horizontalPanel57.add(Emc_eufar.qvInfoButton17);
			Emc_eufar.horizontalPanel156.add(Emc_eufar.imageChk25Y);
			Emc_eufar.horizontalPanel156.add(Emc_eufar.imageChk25N);
			Emc_eufar.horizontalPanel58.add(Emc_eufar.horizontalPanel156);
			Emc_eufar.horizontalPanel58.add(Emc_eufar.qvInfoButton18);
			Emc_eufar.horizontalPanel122.add(Emc_eufar.imageNameLab);
			Emc_eufar.horizontalPanel122.add(Emc_eufar.imageStarLab02);
			Emc_eufar.horizontalPanel123.add(Emc_eufar.imageRadLab);
			Emc_eufar.horizontalPanel123.add(Emc_eufar.imageStarLab03);
			Emc_eufar.horizontalPanel124.add(Emc_eufar.imageSpeLab);
			Emc_eufar.horizontalPanel124.add(Emc_eufar.imageStarLab04);
			Emc_eufar.horizontalPanel125.add(Emc_eufar.imageBanLab);
			Emc_eufar.horizontalPanel125.add(Emc_eufar.imageStarLab05);
			Emc_eufar.horizontalPanel126.add(Emc_eufar.imageDirLab);
			Emc_eufar.horizontalPanel126.add(Emc_eufar.imageStarLab06);
			Emc_eufar.horizontalPanel127.add(Emc_eufar.imageAltLab);
			Emc_eufar.horizontalPanel127.add(Emc_eufar.imageStarLab07);
			Emc_eufar.horizontalPanel128.add(Emc_eufar.imageZenLab);
			Emc_eufar.horizontalPanel128.add(Emc_eufar.imageStarLab08);
			Emc_eufar.horizontalPanel129.add(Emc_eufar.imageAziLab);
			Emc_eufar.horizontalPanel129.add(Emc_eufar.imageStarLab09);
			Emc_eufar.horizontalPanel130.add(Emc_eufar.imageAnoLab);
			Emc_eufar.horizontalPanel130.add(Emc_eufar.imageStarLab10);
			Emc_eufar.horizontalPanel131.add(Emc_eufar.imageLevLab);
			Emc_eufar.horizontalPanel131.add(Emc_eufar.imageStarLab11);
			Emc_eufar.horizontalPanel132.add(Emc_eufar.imageDCLab);
			Emc_eufar.horizontalPanel132.add(Emc_eufar.imageStarLab12);
			Emc_eufar.horizontalPanel133.add(Emc_eufar.imageIntPixel);
			Emc_eufar.horizontalPanel133.add(Emc_eufar.imageStarLab13);
			Emc_eufar.horizontalPanel134.add(Emc_eufar.imageBadPixel);
			Emc_eufar.horizontalPanel134.add(Emc_eufar.imageStarLab14);
			Emc_eufar.horizontalPanel135.add(Emc_eufar.imageSatPixel);
			Emc_eufar.horizontalPanel135.add(Emc_eufar.imageStarLab15);
			Emc_eufar.horizontalPanel136.add(Emc_eufar.imageAffPixel);
			Emc_eufar.horizontalPanel136.add(Emc_eufar.imageStarLab16);
			Emc_eufar.horizontalPanel137.add(Emc_eufar.imagePosInfo);
			Emc_eufar.horizontalPanel137.add(Emc_eufar.imageStarLab17);
			Emc_eufar.horizontalPanel138.add(Emc_eufar.imageAttInfo);
			Emc_eufar.horizontalPanel138.add(Emc_eufar.imageStarLab18);
			Emc_eufar.horizontalPanel139.add(Emc_eufar.imageSyncProblem);
			Emc_eufar.horizontalPanel139.add(Emc_eufar.imageStarLab19);
			Emc_eufar.horizontalPanel140.add(Emc_eufar.imageIntGeocoding);
			Emc_eufar.horizontalPanel140.add(Emc_eufar.imageStarLab20);
			Emc_eufar.horizontalPanel141.add(Emc_eufar.imageAtmCorrection);
			Emc_eufar.horizontalPanel141.add(Emc_eufar.imageStarLab21);
			Emc_eufar.horizontalPanel142.add(Emc_eufar.imageCloudMask);
			Emc_eufar.horizontalPanel142.add(Emc_eufar.imageStarLab22);
			Emc_eufar.horizontalPanel143.add(Emc_eufar.imageShadMask);
			Emc_eufar.horizontalPanel143.add(Emc_eufar.imageStarLab23);
			Emc_eufar.horizontalPanel144.add(Emc_eufar.imageHazeMask);
			Emc_eufar.horizontalPanel144.add(Emc_eufar.imageStarLab24);
			Emc_eufar.horizontalPanel145.add(Emc_eufar.imageRouMeasure);
			Emc_eufar.horizontalPanel145.add(Emc_eufar.imageStarLab25);
			Emc_eufar.horizontalPanel146.add(Emc_eufar.imageIllAngle);
			Emc_eufar.horizontalPanel146.add(Emc_eufar.imageStarLab26);
			Emc_eufar.horizontalPanel147.add(Emc_eufar.imageBRDFGeometry);
			Emc_eufar.horizontalPanel147.add(Emc_eufar.imageStarLab27);
			Emc_eufar.imageCalAcqProTab.setWidget(0, 0, Emc_eufar.horizontalPanel39);
			Emc_eufar.imageCalAcqProTab.setWidget(1, 0, Emc_eufar.horizontalPanel122);
			Emc_eufar.imageCalAcqProTab.setWidget(1, 1, Emc_eufar.imageCalBox);
			Emc_eufar.imageCalAcqProTab.setWidget(2, 0, Emc_eufar.horizontalPanel123);
			Emc_eufar.imageCalAcqProTab.setWidget(2, 1, Emc_eufar.imageRadDat);
			Emc_eufar.imageCalAcqProTab.setWidget(3, 0, Emc_eufar.horizontalPanel124);
			Emc_eufar.imageCalAcqProTab.setWidget(3, 1, Emc_eufar.imageSpeDat);
			Emc_eufar.imageCalAcqProTab.setWidget(4, 0, Emc_eufar.imageAcqLab);
			Emc_eufar.imageCalAcqProTab.setWidget(5, 0, Emc_eufar.horizontalPanel125);
			Emc_eufar.imageCalAcqProTab.setWidget(5, 1, Emc_eufar.horizontalPanel40);
			Emc_eufar.imageCalAcqProTab.setWidget(6, 0, Emc_eufar.horizontalPanel126);
			Emc_eufar.imageCalAcqProTab.setWidget(6, 1, Emc_eufar.imageDirBox);
			Emc_eufar.imageCalAcqProTab.setWidget(7, 0, Emc_eufar.horizontalPanel127);
			Emc_eufar.imageCalAcqProTab.setWidget(7, 1, Emc_eufar.imageAltBox);
			Emc_eufar.imageCalAcqProTab.setWidget(8, 0, Emc_eufar.horizontalPanel128);
			Emc_eufar.imageCalAcqProTab.setWidget(8, 1, Emc_eufar.imageZenBox);
			Emc_eufar.imageCalAcqProTab.setWidget(9, 0, Emc_eufar.horizontalPanel129);
			Emc_eufar.imageCalAcqProTab.setWidget(9, 1, Emc_eufar.imageAziBox);
			Emc_eufar.imageCalAcqProTab.setWidget(10, 0, Emc_eufar.horizontalPanel130);
			Emc_eufar.imageCalAcqProTab.setWidget(10, 1, Emc_eufar.imageAnoBox);
			Emc_eufar.imageCalAcqProTab.setWidget(11, 0, Emc_eufar.imageProLab);
			Emc_eufar.imageCalAcqProTab.setWidget(12, 0, Emc_eufar.horizontalPanel131);
			Emc_eufar.imageCalAcqProTab.setWidget(12, 1, Emc_eufar.imageLevLst);
			Emc_eufar.imageCalAcqProTab.setWidget(13, 0, Emc_eufar.horizontalPanel132);
			Emc_eufar.imageCalAcqProTab.setWidget(13, 1, Emc_eufar.horizontalPanel41);
			Emc_eufar.imageQuaLayTab.setWidget(0, 0, Emc_eufar.horizontalPanel42);
			Emc_eufar.imageQuaLayTab.setWidget(1, 0, Emc_eufar.imageCalcorrLab);
			Emc_eufar.imageQuaLayTab.setWidget(2, 0, Emc_eufar.horizontalPanel133);
			Emc_eufar.imageQuaLayTab.setWidget(2, 1, Emc_eufar.horizontalPanel44);
			Emc_eufar.imageQuaLayTab.setWidget(3, 0, Emc_eufar.horizontalPanel134);
			Emc_eufar.imageQuaLayTab.setWidget(3, 1, Emc_eufar.horizontalPanel45);
			Emc_eufar.imageQuaLayTab.setWidget(4, 0, Emc_eufar.imageErrLab);
			Emc_eufar.imageQuaLayTab.setWidget(5, 0, Emc_eufar.horizontalPanel135);
			Emc_eufar.imageQuaLayTab.setWidget(5, 1, Emc_eufar.horizontalPanel46);
			Emc_eufar.imageQuaLayTab.setWidget(6, 0, Emc_eufar.horizontalPanel136);
			Emc_eufar.imageQuaLayTab.setWidget(6, 1, Emc_eufar.horizontalPanel47);
			Emc_eufar.imageQuaLayTab.setWidget(7, 0, Emc_eufar.horizontalPanel43);
			Emc_eufar.imageQuaLayTab.setWidget(8, 0, Emc_eufar.horizontalPanel137);
			Emc_eufar.imageQuaLayTab.setWidget(8, 1, Emc_eufar.horizontalPanel48);
			Emc_eufar.imageQuaLayTab.setWidget(9, 0, Emc_eufar.horizontalPanel138);
			Emc_eufar.imageQuaLayTab.setWidget(9, 1, Emc_eufar.horizontalPanel49);
			Emc_eufar.imageQuaLayTab.setWidget(10, 0, Emc_eufar.horizontalPanel139);
			Emc_eufar.imageQuaLayTab.setWidget(10, 1, Emc_eufar.horizontalPanel50);
			Emc_eufar.imageQuaLayTab.setWidget(11, 0, Emc_eufar.horizontalPanel140);
			Emc_eufar.imageQuaLayTab.setWidget(11, 1, Emc_eufar.horizontalPanel51);
			Emc_eufar.imageQuaLayTab.setWidget(12, 0, Emc_eufar.imageCorrconLab);
			Emc_eufar.imageQuaLayTab.setWidget(13, 0, Emc_eufar.horizontalPanel141);
			Emc_eufar.imageQuaLayTab.setWidget(13, 1, Emc_eufar.horizontalPanel52);
			Emc_eufar.imageQuaLayTab.setWidget(14, 0, Emc_eufar.horizontalPanel142);
			Emc_eufar.imageQuaLayTab.setWidget(14, 1, Emc_eufar.horizontalPanel53);
			Emc_eufar.imageQuaLayTab.setWidget(15, 0, Emc_eufar.horizontalPanel143);
			Emc_eufar.imageQuaLayTab.setWidget(15, 1, Emc_eufar.horizontalPanel54);
			Emc_eufar.imageQuaLayTab.setWidget(16, 0, Emc_eufar.horizontalPanel144);
			Emc_eufar.imageQuaLayTab.setWidget(16, 1, Emc_eufar.horizontalPanel55);
			Emc_eufar.imageQuaLayTab.setWidget(17, 0, Emc_eufar.horizontalPanel145);
			Emc_eufar.imageQuaLayTab.setWidget(17, 1, Emc_eufar.horizontalPanel56);
			Emc_eufar.imageQuaLayTab.setWidget(18, 0, Emc_eufar.horizontalPanel146);
			Emc_eufar.imageQuaLayTab.setWidget(18, 1, Emc_eufar.horizontalPanel57);
			Emc_eufar.imageQuaLayTab.setWidget(19, 0, Emc_eufar.horizontalPanel147);
			Emc_eufar.imageQuaLayTab.setWidget(19, 1, Emc_eufar.horizontalPanel58);
			FlexCellFormatter cellFormatter01 = Emc_eufar.imageCalAcqProTab.getFlexCellFormatter();
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
			FlexCellFormatter cellFormatter02 = Emc_eufar.imageQuaLayTab.getFlexCellFormatter();
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
			Emc_eufar.verticalPanel17.add(Emc_eufar.imageCalAcqProTab);
			Emc_eufar.verticalPanel17.add(Emc_eufar.imageQuaLayTab);
			Emc_eufar.imageStarLab02.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab03.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab04.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab05.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab06.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab07.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab08.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab09.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab10.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab11.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab12.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab13.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab14.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab15.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab16.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab17.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab18.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab19.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab20.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab21.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab22.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab23.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab24.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab25.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab26.setStyleName("qvStarLabel");
			Emc_eufar.imageStarLab27.setStyleName("qvStarLabel");
			Emc_eufar.imageSpeDat.setStyleName("qv_imageDatebox");
			Emc_eufar.imageRadDat.setStyleName("qv_imageDatebox");
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
			Emc_eufar.horizontalPanel41.getElement().setAttribute("style", "margin-left: 20px;");
			Emc_eufar.qvInfoButton06.getElement().setAttribute("style", "margin-top: 5px !important;");
			Emc_eufar.qvInfoButton09.getElement().setAttribute("style", "margin-top: 5px !important;");
			Emc_eufar.qvInfoButton14.getElement().setAttribute("style", "margin-top: 5px !important;");
			Emc_eufar.horizontalPanel48.setStyleName("qvHorizontalPanel4");
			Emc_eufar.horizontalPanel49.setStyleName("qvHorizontalPanel4");
			Emc_eufar.horizontalPanel50.setStyleName("qvHorizontalPanel4");
			Emc_eufar.horizontalPanel51.setStyleName("qvHorizontalPanel4");
			Emc_eufar.horizontalPanel53.setStyleName("qvHorizontalPanel4");
			Emc_eufar.horizontalPanel54.setStyleName("qvHorizontalPanel4");
			Emc_eufar.horizontalPanel55.setStyleName("qvHorizontalPanel4");
			Emc_eufar.horizontalPanel148.setStyleName("qvHorizontalPanel4");
			Emc_eufar.horizontalPanel149.setStyleName("qvHorizontalPanel4");
			Emc_eufar.horizontalPanel150.setStyleName("qvHorizontalPanel4");
			Emc_eufar.horizontalPanel151.setStyleName("qvHorizontalPanel4");
			Emc_eufar.horizontalPanel152.setStyleName("qvHorizontalPanel4");
			Emc_eufar.horizontalPanel153.setStyleName("qvHorizontalPanel4");
			Emc_eufar.horizontalPanel154.setStyleName("qvHorizontalPanel4");
			Emc_eufar.horizontalPanel155.setStyleName("qvHorizontalPanel4");
			Emc_eufar.horizontalPanel156.setStyleName("qvHorizontalPanel4");
			Emc_eufar.rootLogger.log(Level.INFO, "RadioButton: imageRad");
		} else if (string == "Atmospheric/In-situ data") {
			Emc_eufar.verticalPanel17.clear();
			Emc_eufar.horizontalPanel35.add(Emc_eufar.insituCalLab);
			Emc_eufar.horizontalPanel35.add(Emc_eufar.qvInfoButton02);
			Emc_eufar.horizontalPanel36.add(Emc_eufar.insituOutLab);
			Emc_eufar.horizontalPanel36.add(Emc_eufar.insituStarLab06);
			Emc_eufar.horizontalPanel36.add(Emc_eufar.qvInfoButton03);
			Emc_eufar.horizontalPanel37.add(Emc_eufar.insituFlaLab);
			Emc_eufar.horizontalPanel37.add(Emc_eufar.insituStarLab07);
			Emc_eufar.horizontalPanel37.add(Emc_eufar.qvInfoButton04);
			Emc_eufar.horizontalPanel38.add(Emc_eufar.insituAssLab);
			Emc_eufar.horizontalPanel38.add(Emc_eufar.insituStarLab08);
			Emc_eufar.horizontalPanel38.add(Emc_eufar.qvInfoButton05);
			Emc_eufar.horizontalPanel59.add(Emc_eufar.insituGeoLab);
			Emc_eufar.horizontalPanel59.add(Emc_eufar.insituStarLab05);
			Emc_eufar.horizontalPanel121.add(Emc_eufar.insituChk01Y);
			Emc_eufar.horizontalPanel121.add(Emc_eufar.insituChk01N);
			Emc_eufar.verticalPanel17.add(Emc_eufar.horizontalPanel35);
			Emc_eufar.horizontalPanel118.add(Emc_eufar.insituLinkLab);
			Emc_eufar.horizontalPanel118.add(Emc_eufar.insituStarLab02);
			Emc_eufar.horizontalPanel119.add(Emc_eufar.insituConstLab);
			Emc_eufar.horizontalPanel119.add(Emc_eufar.insituStarLab03);
			Emc_eufar.horizontalPanel120.add(Emc_eufar.insituMatLab);
			Emc_eufar.horizontalPanel120.add(Emc_eufar.insituStarLab04);
			Emc_eufar.insituCalTab.setWidget(0, 0, Emc_eufar.horizontalPanel118);
			Emc_eufar.insituCalTab.setWidget(0, 1, Emc_eufar.insituLinkBox);
			Emc_eufar.insituCalTab.setWidget(1, 0, Emc_eufar.horizontalPanel119);
			Emc_eufar.insituCalTab.setWidget(1, 1, Emc_eufar.insituConstBox);
			Emc_eufar.insituCalTab.setWidget(2, 0, Emc_eufar.horizontalPanel120);
			Emc_eufar.insituCalTab.setWidget(2, 1, Emc_eufar.insituMatBox);
			FlexCellFormatter cellFormatter = Emc_eufar.insituCalTab.getFlexCellFormatter();
			cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
			cellFormatter.setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
			cellFormatter.setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
			Emc_eufar.verticalPanel17.add(Emc_eufar.insituCalTab);
			Emc_eufar.verticalPanel17.add(Emc_eufar.horizontalPanel59);
			Emc_eufar.verticalPanel17.add(Emc_eufar.horizontalPanel121);
			Emc_eufar.verticalPanel17.add(Emc_eufar.horizontalPanel36);
			Emc_eufar.verticalPanel18.add(Emc_eufar.insituChk04);
			Emc_eufar.verticalPanel18.add(Emc_eufar.insituChk05);
			Emc_eufar.verticalPanel19.add(Emc_eufar.insituChk06);
			Emc_eufar.verticalPanel19.add(Emc_eufar.horizontalPanel32);
			Emc_eufar.horizontalPanel34.add(Emc_eufar.verticalPanel18);
			Emc_eufar.horizontalPanel34.add(Emc_eufar.verticalPanel19);
			Emc_eufar.verticalPanel17.add(Emc_eufar.horizontalPanel34);
			Emc_eufar.horizontalPanel32.add(Emc_eufar.insituChk07);
			((FocusWidget) Emc_eufar.insituChk07.getWidget(0)).addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {GuiModification.otherFormat();}
			});
			Emc_eufar.horizontalPanel32.add(Emc_eufar.insituImage);
			Emc_eufar.horizontalPanel32.add(Emc_eufar.insituOtherBox);
			Emc_eufar.verticalPanel17.add(Emc_eufar.horizontalPanel37);
			Emc_eufar.verticalPanel17.add(Emc_eufar.insituFlagAre);
			Emc_eufar.verticalPanel17.add(Emc_eufar.horizontalPanel38);
			Emc_eufar.verticalPanel17.add(Emc_eufar.insituAssumptionAre);
			Emc_eufar.horizontalPanel59.setStyleName("qvHorizontalPanel1");
			Emc_eufar.horizontalPanel121.setStyleName("qvHorizontalPanel2");
			Emc_eufar.horizontalPanel36.setStyleName("qvHorizontalPanel1");
			Emc_eufar.horizontalPanel34.setStyleName("qvHorizontalPanel3");
			Emc_eufar.horizontalPanel37.setStyleName("qvHorizontalPanel1");
			Emc_eufar.horizontalPanel38.setStyleName("qvHorizontalPanel1");
			Emc_eufar.insituStarLab02.setStyleName("qvStarLabel");
			Emc_eufar.insituStarLab03.setStyleName("qvStarLabel");
			Emc_eufar.insituStarLab04.setStyleName("qvStarLabel");
			Emc_eufar.insituStarLab05.setStyleName("qvStarLabel2");
			Emc_eufar.insituStarLab06.setStyleName("qvStarLabel");
			Emc_eufar.insituStarLab07.setStyleName("qvStarLabel");
			Emc_eufar.insituStarLab08.setStyleName("qvStarLabel2");
			Emc_eufar.insituLinkLab.setStyleName("qv_insituLabel");
			Emc_eufar.insituConstLab.setStyleName("qv_insituLabel");
			Emc_eufar.insituMatLab.setStyleName("qv_insituLabel");
			Emc_eufar.insituLinkBox.setStyleName("qv_insituTextAre2");
			Emc_eufar.insituConstBox.setStyleName("qv_insituTextAre2");
			Emc_eufar.insituMatBox.setStyleName("qv_insituTextAre2");
			Emc_eufar.insituImage.getElement().setAttribute("style", "margin-left: 23px; margin-top: -5px;");
			Emc_eufar.insituOtherBox.setStyleName("qv_otherBox");
			Emc_eufar.insituFlagAre.setStyleName("qv_insituTextAre");
			Emc_eufar.insituAssumptionAre.setStyleName("qv_insituTextAre");
			Emc_eufar.insituCalLab.setStyleName("qv_insituLabelTitle");
			Emc_eufar.insituOutLab.setStyleName("qv_insituLabelTitle2");
			Emc_eufar.insituFlaLab.setStyleName("qv_insituLabelTitle2");
			Emc_eufar.insituAssLab.setStyleName("qv_insituLabelTitle2");
			Emc_eufar.insituGeoLab.setStyleName("qv_insituLabelTitle2");
			Emc_eufar.insituChk06.getElement().setAttribute("style","margin-left: 40px !important;");
			Emc_eufar.insituChk07.getElement().setAttribute("style","margin-left: 40px !important;");
			Emc_eufar.qvInfoButton02.getElement().setAttribute("style", "margin-top: 5px !important;");
			Emc_eufar.qvInfoButton03.getElement().setAttribute("style", "margin-top: -5px !important;");
			Emc_eufar.qvInfoButton04.getElement().setAttribute("style", "margin-top: -5px !important;");
			Emc_eufar.qvInfoButton05.getElement().setAttribute("style", "margin-top: -5px !important;");
			Emc_eufar.insituImage.setVisible(false);
			Emc_eufar.insituOtherBox.setVisible(false);
			Emc_eufar.rootLogger.log(Level.INFO, "RadioButton: insituRad");
		}
	}
	
	
	// add a textbox to the format when "other" is selected in the QV section
	public static void otherFormat() {
		if (((CheckBox) Emc_eufar.insituChk07.getWidget(0)).getValue() == true) {
			Emc_eufar.insituImage.setVisible(true);
			Emc_eufar.insituOtherBox.setVisible(true);
		} else {
			Emc_eufar.insituImage.setVisible(false);
			Emc_eufar.insituOtherBox.setVisible(false);
		}
	}
}
