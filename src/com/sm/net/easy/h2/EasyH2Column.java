package com.sm.net.easy.h2;

/**
 * 
 * @author SM-Net <http://sm-netzwerk.com>
 * 
 *         Simple H2 Column
 *
 */
public class EasyH2Column {

	private String columnName;
	private H2DataTypes h2DataType;
	private boolean primaryKey;
	private boolean autoIncrement;
	private boolean notNull;
	private Integer charLenght;

	/**
	 * Simple H2 Column Generic constructor
	 * 
	 * @param columnName
	 *            Column name
	 * @param h2DataType
	 *            Column type
	 */
	public EasyH2Column(String columnName, H2DataTypes h2DataType) {
		super();
		this.columnName = columnName;
		this.h2DataType = h2DataType;

		this.primaryKey = false;
		this.autoIncrement = false;
		this.notNull = false;
		this.charLenght = null;
	}

	/**
	 * Simple H2 Column VARCHAR constructor
	 * 
	 * @param columnName
	 *            Column name
	 * @param h2DataType
	 *            Column type
	 * @param charLenght
	 *            VARCHAR Lenght
	 */
	public EasyH2Column(String columnName, H2DataTypes h2DataType, int charLenght) {
		super();
		this.columnName = columnName;
		this.h2DataType = h2DataType;
		this.charLenght = new Integer(charLenght);

		this.primaryKey = false;
		this.autoIncrement = false;
		this.notNull = false;
	}

	/**
	 * Simple H2 Column Primary Key constructor
	 * 
	 * @param columnName
	 *            Column name
	 * @param h2DataType
	 *            Column type
	 * @param primaryKey
	 *            If true: PRIMARY KEY
	 * @param autoIncrement
	 *            If true: AUTO_INCREMENT
	 */
	public EasyH2Column(String columnName, H2DataTypes h2DataType, boolean primaryKey, boolean autoIncrement) {
		super();
		this.columnName = columnName;
		this.h2DataType = h2DataType;
		this.primaryKey = primaryKey;
		this.autoIncrement = autoIncrement;

		this.notNull = false;
		this.charLenght = null;
	}

	/**
	 * Simple H2 Column NOT NULL constructor
	 * 
	 * @param columnName
	 *            Column name
	 * @param h2DataType
	 *            Column type
	 * @param notNull
	 *            If true: NOT NULL
	 */
	public EasyH2Column(String columnName, H2DataTypes h2DataType, boolean notNull) {
		super();
		this.columnName = columnName;
		this.h2DataType = h2DataType;
		this.notNull = notNull;

		this.primaryKey = false;
		this.autoIncrement = false;
		this.charLenght = null;
	}

	/**
	 * Simple H2 Column VARCHAR NOT NULL constructor
	 * 
	 * @param columnName
	 *            Column name
	 * @param h2DataType
	 *            Column type
	 * @param notNull
	 *            If true: NOT NULL
	 * @param charLenght
	 *            VARCHAR Lenght
	 */
	public EasyH2Column(String columnName, H2DataTypes h2DataType, boolean notNull, int charLenght) {
		super();
		this.columnName = columnName;
		this.h2DataType = h2DataType;
		this.notNull = notNull;
		this.charLenght = new Integer(charLenght);

		this.primaryKey = false;
		this.autoIncrement = false;
	}

	public H2DataTypes getH2DataType() {
		return h2DataType;
	}

	public void setH2DataType(H2DataTypes h2DataType) {
		this.h2DataType = h2DataType;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public Integer getCharLenght() {
		return charLenght;
	}

	public void setCharLenght(Integer charLenght) {
		this.charLenght = charLenght;
	}
}
