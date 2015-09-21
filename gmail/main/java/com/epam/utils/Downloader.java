
package com.epam.utils;

import java.awt.datatransfer.StringSelection;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
/**
 *
 * @author Nadzeya_Tsahelnik
 */
public class Downloader {
    public static void javaRobotForAttachFile(StringSelection stringSelections)
			throws AWTException {
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelections, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
}
