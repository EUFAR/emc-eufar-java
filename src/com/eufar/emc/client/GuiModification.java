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
			if (Emc_eufar.horizontalPanel12.getWidgetCount() == 6) {
				Emc_eufar.horizontalPanel12.remove(2);
			}
		}
	}
}
