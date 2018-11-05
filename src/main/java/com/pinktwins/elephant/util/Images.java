package com.pinktwins.elephant.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;

public class Images {

	private static final Logger LOG = Logger.getLogger(Images.class.getName());

	private Images() {
	}

	public static Iterator<Image> iterator(String[] names) {
		ArrayList<Image> list = new ArrayList<Image>();
		for (int n = 0; n < names.length; n++) {
			Image img = null;
			try {
				String resource = "/images/" + names[n] + ".png";
				InputStream is = Images.class.getResourceAsStream(resource);
				img = ImageIO.read(is);
			} catch (IOException e) {
				LOG.severe("Fail: " + e);
			}
			list.add(img);
		}
		return list.iterator();
	}

	public static boolean isImage(File f) {
		if (!f.exists()) {
			return false;
		}

		String s = FilenameUtils.getExtension(f.getName()).toLowerCase();
		return "png tif jpg jpeg bmp gif".indexOf(s) >= 0;
	}
}
