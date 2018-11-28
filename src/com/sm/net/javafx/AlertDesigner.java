package com.sm.net.javafx;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlertDesigner extends Alert {

	private String title;
	private Image icon;

	public AlertDesigner(String header, String message, Stage parentStage, AlertType alertType, String title,
			Image icon) {
		super(alertType);

		this.title = title;
		this.icon = icon;

		setDefaultSettings(message);
		this.setHeaderText(header);
		this.initOwner(parentStage);
	}

	public AlertDesigner(String message, Stage parentStage, AlertType alertType, String title, Image icon) {
		super(alertType);

		this.title = title;
		this.icon = icon;

		setDefaultSettings(message);
		this.setHeaderText(null);
		this.initOwner(parentStage);
	}

	public AlertDesigner(String message, AlertType alertType, String title, Image icon) {
		super(alertType);

		this.title = title;
		this.icon = icon;

		this.setHeaderText(null);
		setDefaultSettings(message);
	}

	private void setDefaultSettings(String message) {
		this.setTitle(this.title);
		this.setContentText(message);

		Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
		stage.getIcons().add(this.icon);
	}
}