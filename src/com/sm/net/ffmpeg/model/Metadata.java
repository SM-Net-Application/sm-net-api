package com.sm.net.ffmpeg.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Metadata {

	private String title;
	private String artist;
	private String album;
	private ObservableList<Chapter> chapters;

	public Metadata() {
		super();
		this.title = "";
		this.artist = "";
		this.album = "";
		this.chapters = FXCollections.observableArrayList();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public ObservableList<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(ObservableList<Chapter> chapters) {
		this.chapters = chapters;
	}

	public void addChapter(Chapter chapter) {
		this.chapters.add(chapter);
	}

}