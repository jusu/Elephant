package com.pinktwins.elephant.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Logger;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.pinktwins.rtf.RTFEditorKit;

public class RtfUtil {

	private static final Logger LOG = Logger.getLogger(RtfUtil.class.getName());

	private RtfUtil() {
	}

	// http://stackoverflow.com/questions/2725141/java-jtextpane-rtf-save
	public static String getRtf(Document doc) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			RTFEditorKit kit = new RTFEditorKit();
			kit.write(baos, doc, doc.getStartPosition().getOffset(), doc.getLength());

			String rtfContent = baos.toString();
			{
				// replace "Monospaced" by a well-known monospace
				// font
				rtfContent = rtfContent.replaceAll("Monospaced", "Courier New");
				final StringBuffer rtfContentBuffer = new StringBuffer(rtfContent);
				final int endProlog = rtfContentBuffer.indexOf("\n\n");
				// set a good Line Space and no Space Before or
				// Space
				// After each paragraph
				if (endProlog > 0) {
					rtfContentBuffer.insert(endProlog, "\n\\sl240");
					rtfContentBuffer.insert(endProlog, "\n\\sb0\\sa0");
				}
				rtfContent = rtfContentBuffer.toString();
			}

			return rtfContent;
		} catch (Exception e) {
			LOG.severe("Fail: " + e);
		}
		return null;
	}

	public static void putRtf(Document doc, String rtfContents, int position) throws IOException, BadLocationException {
		RTFEditorKit kit = new RTFEditorKit();
		kit.read(new StringReader(rtfContents), doc, position);
	}
}
