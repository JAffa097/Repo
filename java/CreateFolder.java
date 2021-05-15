import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateFolder {
	public String documentPath = null;
	public String documentFolderName = null;
	private String testcaseID="TEST_001";
	
	public void takeScreenshot(String comment, String status) {
		
	}
public static void main(String args[]) {
	CreateFolder createFolder = new CreateFolder();
	SimpleDateFormat df =new SimpleDateFormat("dd-M-yyyy-HH-mm-ss");
	Date date =new Date();
	createFolder.documentFolderName = df.format(date);
	String path = "C:\\Users\\krishna reddy\\eclipse-workspace\\Reportgeneration\\src\\main\\resources\\Screenshots";
	String fileName  = "@ASDF";
	createFolder.documentPath = path+"//"+fileName+"//"+createFolder.documentFolderName;
	File file =new File(path,fileName);
	file.mkdir();
	(new File(path+"//"+fileName, createFolder.documentFolderName)).mkdir();
	
	
	
	
}
}
