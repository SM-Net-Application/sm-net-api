package com.sm.net.ffmpeg.util;

import java.io.File;
import java.math.BigDecimal;

import com.sm.net.ffmpeg.model.Chapter;
import com.sm.net.ffmpeg.model.Metadata;
import com.sm.net.util.Strings;

/**
 * 
 * @author SM-Net <http://sm-netzwerk.com>
 * 
 *         Utility Class for FFMPEG
 */
public class Ffmpeg {

	/**
	 * Returns StringsArray for Command Line Windows - command line: ffmpeg.exe
	 * -i video.mp4
	 * 
	 * @param ffmpeg
	 * @param video
	 * @return StringsArray
	 */
	public static String[] buildCommandLineWin(File ffmpeg, File video) {

		String ffmpegPath = "\"" + ffmpeg.getAbsolutePath() + "\"";
		String minusI = "-i";
		String videoPath = "\"" + video.getAbsolutePath() + "\"";

		return new String[] { ffmpegPath, minusI, videoPath };
	}

	/**
	 * 
	 * Analyze the output of the FFMPEG command line and extract the metadata
	 * 
	 * @param output
	 * @return Metadata object
	 */
	public static Metadata getMetadata(String output) {

		Metadata metadata = new Metadata();
		Chapter chapter = new Chapter();

		if (output.length() > 0) {

			if (output.contains("Metadata")) {

				boolean titleFound = false;
				boolean artistFound = false;
				boolean albumFound = false;

				boolean hasChapters = output.contains("Chapter #");
				boolean searchTitleChapter = false;

				String[] lines = output.split("\n");

				for (String line : lines) {

					if (!titleFound) {

						String content = getContent(line, "title");
						if (content.length() > 0) {
							metadata.setTitle(content);
							titleFound = true;
						}
					}

					if (!artistFound) {

						String content = getContent(line, "artist");
						if (content.length() > 0) {
							metadata.setArtist(content);
							artistFound = true;
						}
					}

					if (!albumFound) {

						String content = getContent(line, "album");
						if (content.length() > 0) {
							metadata.setAlbum(content);
							albumFound = true;
						}
					}

					if (hasChapters) {

						if (!searchTitleChapter) {

							if (line.contains("Chapter #")) {

								if (!titleFound) {
									titleFound = true;
								}

								String[] split = line.split(",");
								if (split.length > 0) {

									String[] splitStart = split[0].split("start");
									String[] splitEnd = split[1].split("end");

									chapter.setStart(new BigDecimal(splitStart[1].trim()));
									chapter.setEnd(new BigDecimal(splitEnd[1].trim()));

									searchTitleChapter = true;
								}
							}

						} else {

							String content = getContent(line, "title");

							if (content.length() > 0) {

								chapter.setTitle(content);

								metadata.addChapter(chapter);
								chapter = new Chapter();

								searchTitleChapter = false;
							}
						}
					}
				}
			}
		}

		return metadata;
	}

	/**
	 * Returns the searched content recoded to UTF-8 if present in the parsed
	 * line
	 * 
	 * @param line
	 * @param content
	 * @return
	 */
	private static String getContent(String line, String content) {

		String result = "";

		if (line.contains(content)) {
			result = Strings.recoding(line.substring(line.indexOf(":") + 1, line.length()), "UTF-8");
		}

		return result;
	}
}