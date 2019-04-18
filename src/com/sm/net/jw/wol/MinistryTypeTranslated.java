package com.sm.net.jw.wol;

import com.sm.net.project.Language;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MinistryTypeTranslated {

	private ObjectProperty<MinistryType> type;
	private StringProperty name;
	private IntegerProperty ordinal;

	public MinistryTypeTranslated(MinistryType ministryType, Language language) {

		this.type = new SimpleObjectProperty<MinistryType>(ministryType);
		this.name = new SimpleStringProperty(language.getString(ministryType.getName()));
		this.ordinal = ministryType.ordinalProperty();
	}

	public MinistryTypeTranslated(int spInf3, Language language) {

		MinistryType ministryType = MinistryType.getFromOrdinal(spInf3);

		this.type = new SimpleObjectProperty<MinistryType>(ministryType);
		this.name = new SimpleStringProperty(language.getString(ministryType.getName()));
		this.ordinal = ministryType.ordinalProperty();
	}

	public final StringProperty nameProperty() {
		return this.name;
	}

	public final String getName() {
		return this.nameProperty().get();
	}

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}

	public final IntegerProperty ordinalProperty() {
		return this.ordinal;
	}

	public final int getOrdinal() {
		return this.ordinalProperty().get();
	}

	public final void setOrdinal(final int ordinal) {
		this.ordinalProperty().set(ordinal);
	}

	@Override
	public String toString() {
		return getName();
	}

	public final ObjectProperty<MinistryType> typeProperty() {
		return this.type;
	}

	public final MinistryType getType() {
		return this.typeProperty().get();
	}

	public final void setType(final MinistryType type) {
		this.typeProperty().set(type);
	}

}
