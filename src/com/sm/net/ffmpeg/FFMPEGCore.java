package com.sm.net.ffmpeg;

import java.io.File;

public interface FFMPEGCore {

	/**
	 * Each application must implement this method if it wants to use FFMPEG
	 */
	public abstract File getFfmpegExecutable();

}
