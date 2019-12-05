package cerong.erp.lucene.bean;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.pdfbox.PDFReader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;




public class FileUtil {

	/**读取文件信息和下属文件夹
	 * @param folder
	 * @return
	 * @throws IOException
	 * @throws OpenXML4JException 
	 * @throws XmlException 
	 */
	public static List<FileBean> getFolderFiles(String folder) throws Exception {
		List<FileBean> fileBeans = new LinkedList<FileBean>();
		File file = new File(folder);
		if(file.isDirectory()){
			File[] files = file.listFiles();
			if(files != null){
				for (File file2 : files) {
					fileBeans.addAll(getFolderFiles(file2.getAbsolutePath()));
				}
			}
		}else{
			FileBean bean = new FileBean();
			String filePath = file.getAbsolutePath();
			bean.setPath(file.getAbsolutePath());
			bean.setModified(file.lastModified());
			String content = "";
			if(filePath.endsWith(".doc") || filePath.endsWith(".docx")){
				content = readDoc(file);
			}else if(filePath.endsWith(".xls") || filePath.endsWith(".xlsx")){
				content = readExcel(file);
			}else if(filePath.endsWith(".pdf")){
				content = readPdf(file);
			}else{
				content = new String(Files.readAllBytes(Paths.get(folder)));
			}
			bean.setContent(content);
			fileBeans.add(bean);
		}
		
		return fileBeans;
	}
	/**讀取excel文件
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 */
	private static String readExcel(File file) throws Exception {
		String filePath = file.getAbsolutePath();
		StringBuffer content = new StringBuffer("");
		if(filePath.endsWith(".xls")){
			InputStream inp = new FileInputStream(filePath);
		    Workbook wb = WorkbookFactory.create(inp);   
		    Sheet sheet = wb.getSheetAt(0);
		    for(int i = sheet.getFirstRowNum();i<= sheet.getPhysicalNumberOfRows();i++){  
		    	HSSFRow row = (HSSFRow) sheet.getRow(i);  
		    	if (row == null) {  
		    		  continue;  
		    	}
		    	for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) { 
		    		HSSFCell cell = row.getCell(j);  
		    		if (cell == null) {  
			    		  continue;  
			    	}
		    		content.append(cell.getStringCellValue());
		    	}
		    }
		    wb.close();
		    inp.close();
		}else{
			XSSFWorkbook xwb = new XSSFWorkbook(file.getAbsolutePath());
			XSSFSheet sheet = xwb.getSheetAt(0);  
			// 定义 row、cell  
			XSSFRow row;  
			String cell;  
			// 循环输出表格中的内容  
			for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {  
			    row = sheet.getRow(i);  
			    if(row == null){
			    	continue;
			    }
			    for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {  
			        // 通过 row.getCell(j).toString() 获取单元格内容，
			    	if(j < 0) continue;
			    	XSSFCell xfcell = row.getCell(j);
			    	if(xfcell == null){
			    		continue;
			    	}
			    	xfcell.setCellType(Cell.CELL_TYPE_STRING);
			        cell = xfcell.getStringCellValue();
			        //cell = xfcell.getRawValue();
			        content.append(cell+" ");
			    }  
			}  
		}
		return content.toString();
	}
	/**讀取word內容
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws OpenXML4JException 
	 * @throws XmlException 
	 */
	private static String readDoc(File file) throws IOException, XmlException, OpenXML4JException {
		String filePath = file.getAbsolutePath();
		if(filePath.endsWith(".doc")){
			InputStream is = new FileInputStream(file);
			WordExtractor ex = new WordExtractor(is);  
			String text2003 = ex.getText();  
			ex.close();
			is.close();
			return text2003;
		}else{
			OPCPackage opcPackage = POIXMLDocument.openPackage(filePath);  
			POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);  
			String text2007 = extractor.getText();  
			extractor.close();
			return text2007;
		}
	}
	/**讀取pdf內容
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String readPdf(File file) throws IOException{
		PDDocument doc = PDDocument.load(file.getAbsolutePath());
		PDFTextStripper stripper = new PDFTextStripper();
		String content = stripper.getText(doc);
		doc.close();
		return content;
	}
	
	
    /**
     * 方法描述:读取PPT内容
     * author:lzj
     * date:2015年5月28日
     * @param file
     * @throws IOException
     */
    public static String readPPT(File file) throws IOException{
    	FileInputStream is = new  FileInputStream(file);
        SlideShow ss=new SlideShow(new HSLFSlideShow(is));
        Slide[] slides = ss.getSlides();
        StringBuffer content = new StringBuffer();
        for(int i=0;i<slides.length;i++){
            //读取一张幻灯片的标题
            String title=slides[i].getTitle();
            System.out.println("标题:"+title);
            //读取一张幻灯片的内容(包括标题)
            TextRun[] runs=slides[i].getTextRuns();
            for(int j=0;j<runs.length;j++){
            	content.append(runs[j].getText());
                //System.out.println(runs[j].getText());
            }
        }
        return content.toString();
    }

	

	/**
	 * 方法描述:将邮件标题和内容写入到文件中(目的方便建立索引查询，提高效率)
	 * author: lzj
	 * date:2015年5月28日
	 */
	public static String createFile(String path,String content) {
		Date date = new Date();
		String time = date.getTime()+"".toString();
		String fname = time+".txt";
		File file = new File(path,fname);
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			if(file.exists()) {
				file.delete();
				file.createNewFile();
			}
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}
				if(fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fname;
	}
	
	
		
		/*FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			if(file.exists()) {
				file.delete();
				file.createNewFile();
			}
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}
				if(fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
	
}
