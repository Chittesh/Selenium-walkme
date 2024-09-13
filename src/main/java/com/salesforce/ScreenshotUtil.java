package com.salesforce;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        // Take screenshot and store it as a file format
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
        
        try {
            // Copy the screenshot to the desired location using copyFile method
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
        
        return path; // Return the screenshot path
    }
}
