import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WordReport {
	public static WebDriver driver;
	public static String documentPath = null;
	public static String documentFolderName = null;
	private static String testcaseID="TEST_001";
	public static Map<String, String> images = new LinkedHashMap<String, String>();

	public static void main(String[] args) throws IOException, InvalidFormatException {
		
		 SimpleDateFormat df =new SimpleDateFormat("dd-M-yyyy-HH-mm-ss");
			Date date =new Date();
			documentFolderName = df.format(date);
			String path = "C:\\Users\\krishna reddy\\eclipse-workspace\\Reportgeneration\\src\\main\\resources\\Screenshots";
			String fileName  = "@ASDFs";
			documentPath = path+"/"+fileName+"/"+documentFolderName;
			File file =new File(path,fileName);
			file.mkdir();
			(new File(path+"//"+fileName, documentFolderName)).mkdir();
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://www.google.com");
		driver.manage().window().maximize();
		takeScreenShot("Google Home Page");
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).sendKeys("krishna reddy");
		takeScreenShot("Data entered in search box");
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).sendKeys(Keys.ENTER);
		takeScreenShot("Search results page");
		driver.quit();
		

	}
	public static void takeScreenShot(String description) throws IOException, InvalidFormatException {
		TakesScreenshot ss = (TakesScreenshot)driver;
		//create folder for images
		String imagespath=documentPath+"/Images";
		System.out.println(documentPath+"/Images");
		 SimpleDateFormat df =new SimpleDateFormat("dd-M-yyyy-HH-mm-ss");
			Date date =new Date();
			String ImageName = df.format(date);
		new File(imagespath).mkdir();
		//String imageFolderPath=documentPath+"//Images";
		File ssfile=ss.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(imagespath+"/"+ImageName+".jpeg");
		FileUtils.copyFile(ssfile, DestFile);
		images.put(description, imagespath+"/"+ImageName+".jpeg");
		System.out.println(images);
		File ssdocument= new File(documentPath,testcaseID+".docx");
		ssdocument.createNewFile();
		System.out.println(documentPath+"/"+testcaseID+".docx");
		FileWriter fw = new FileWriter(documentPath+"/"+testcaseID+".docx");
		   XWPFDocument doc = new XWPFDocument(); 
		   OutputStream os = new FileOutputStream(documentPath+"/"+testcaseID+".docx");
		   XWPFParagraph paragraph = doc.createParagraph();  
           XWPFRun run = paragraph.createRun();  
    
			  for (String Text: images.keySet()) {
				  String key = Text; 
				  String value =images.get(Text); 
				  run.addBreak();
				  run.addBreak();
				  run.addBreak();
				  run.setText(key);
				  run.addBreak();
				  run.addBreak();
				  run.addBreak();
				  run.addPicture(new FileInputStream(new File(value)), XWPFDocument.PICTURE_TYPE_JPEG, value, Units.toEMU(500), Units.toEMU(300));
				  
				  }
			  doc.write(os);  
	           os.close();
	           doc.close();
			 
	

}
}
