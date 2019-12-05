package cerong.erp.util;



	import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
	 
	/**
	 * 读取Excel
	 * 
	 * @author zengwendong
	 */
	public class ReadExcelUtils {
	    private Logger logger = LoggerFactory.getLogger(ReadExcelUtils.class);
	    private Workbook wb;
	    private Sheet sheet;
	    private Row row;
	    private String gen;
	    
	    public ReadExcelUtils(String filepath) {
	        if(filepath==null){
	            return;
	        }
	        String ext = filepath.substring(filepath.lastIndexOf("."));
	        try {
	            InputStream is = new FileInputStream(filepath);
	            if(".xls".equals(ext)){
	                wb = new HSSFWorkbook(is);
	                gen = "xls";
	            }else if(".xlsx".equals(ext)){
	                wb = new XSSFWorkbook(is);
	                gen = "xlsx";
	            }else{
	                wb=null;
	            }
	        } catch (FileNotFoundException e) {
	            logger.error("FileNotFoundException", e);
	        } catch (IOException e) {
	            logger.error("IOException", e);
	        }
	    }
	     
	    /**
	     * 读取Excel表格表头的内容
	     * 
	     * @param InputStream
	     * @return String 表头内容的数组
	     * @author zengwendong
	     */
	    public String[] readExcelTitle() throws Exception{
	        if(wb==null){
	            throw new Exception("Workbook对象为空！");
	        }
	        sheet = wb.getSheetAt(0);
	        row = sheet.getRow(0);
	        // 标题总列数
	        int colNum = row.getPhysicalNumberOfCells();
	        System.out.println("colNum:" + colNum);
	        String[] title = new String[colNum];
	        for (int i = 0; i < colNum; i++) {
	            // title[i] = getStringCellValue(row.getCell((short) i));
	            title[i] = row.getCell(i).getCellFormula();
	        }
	        return title;
	    }
	 
	    /**
	     * 读取Excel数据内容
	     * 
	     * @param InputStream
	     * @return Map 包含单元格数据内容的Map对象
	     * @author zengwendong
	     */
	    public Map<Integer, Map<Integer,Object>> readExcelContent() throws Exception{
	        if(wb==null){
	            throw new Exception("Workbook对象为空！");
	        }
	        Map<Integer, Map<Integer,Object>> content = new HashMap<Integer, Map<Integer,Object>>();
	         
	        sheet = wb.getSheetAt(0);
	        // 得到总行数
	        int rowNum = sheet.getLastRowNum();
	        row = sheet.getRow(0);
	       // int colNum = row.getPhysicalNumberOfCells();
	        String picName = null;
	        // 正文内容应该从第三行开始,第一行工厂,第二行为表头的标题
	        for (int i = 0; i <= rowNum; i++) {
	            row = sheet.getRow(i);
	            Object str = getCellFormatValue(row.getCell(6));
	            if(str == null || "".equals(str)||"收款人开户行行号[ Account holding bank number of beneficiary ]".equals(str)){
	            	continue;
	            }
	            	
	            int j = 0;
	            int num=0;
	            Map<Integer,Object> cellValue = new HashMap<Integer, Object>();
	            
	            while (j < 39) {
                      
		            	Object obj=null ;
		            	try 
		            	{
		            	obj = getCellFormatValue(row.getCell(j));
		            	}
		            	catch(Exception ex)
		            	{
		            	}
		            	
		            	
		                if(!"".equals(obj)&&obj!=null){
		                cellValue.put(j, obj);
		                j++;
		                }else{
		                	cellValue.put(j, null);
			                j++;	
		                }
		            	
	        }
	            
	            content.put(i, cellValue);
	        }
	        return content;
	    }
	    /**
	     * 读取Excel数据内容
	     * 
	     * @param InputStream
	     * @return Map 包含单元格数据内容的Map对象
	     * @author zengwendong
	     */
	    public Map<Integer, Map<Integer,Object>> readExcelContent1() throws Exception{
	    	if(wb==null){
	    		throw new Exception("Workbook对象为空！");
	    	}
	    	Map<Integer, Map<Integer,Object>> content = new HashMap<Integer, Map<Integer,Object>>();
	    	
	    	sheet = wb.getSheetAt(0);
	    	// 得到总行数
	    	int rowNum = sheet.getLastRowNum();
	    	row = sheet.getRow(0);
	    	int colNum = row.getPhysicalNumberOfCells();
	    	// 正文内容应该从第三行开始,第一行工厂,第二行为表头的标题
	    	for (int i = 0; i <= rowNum; i++) {
	    		row = sheet.getRow(i);
	    		//int j = 0;
	    		int num=0;
	    		Map<Integer,Object> cellValue = new HashMap<Integer, Object>();
	    		
	    		for (int j = 1; j<= colNum; j++) {
	    			
	    			Object obj=null ;
	    			try 
	    			{
	    				obj = getCellFormatValue(row.getCell(j));
	    			}
	    			catch(Exception ex)
	    			{
	    				
	    			}
	    			if(i>1&&j==1&&obj==""){
	    				num=1;
	    			}
	    			if(num==0){
	    				if(!"".equals(obj)&&obj!=null){
	    					cellValue.put(j, obj);
	    					
	    				}else{
	    					cellValue.put(j, null);
	    						
	    				}
	    			}else{
	    				cellValue.put(j, null);
	    				
	    			}
	    			obj=null;
	    		}
	    		
	    		content.put(i, cellValue);
	    	}
	    	return content;
	    }
	 
	    /**
	     * 
	     * 根据Cell类型设置数据
	     * 
	     * @param cell
	     * @return
	     * @author zengwendong
	     */
	    private Object getCellFormatValue(Cell cell) {
	        Object cellvalue = "";
	        if (cell != null) {
	            // 判断当前Cell的Type
	            switch (cell.getCellType()) {
	            case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
	            case Cell.CELL_TYPE_FORMULA: {
	                // 判断当前的cell是否为Date
	                if (DateUtil.isCellDateFormatted(cell)) {
	                    // 如果是Date类型则，转化为Data格式
	                    // data格式是带时分秒的：2013-7-10 0:00:00
	                    // cellvalue = cell.getDateCellValue().toLocaleString();
	                    // data格式是不带带时分秒的：2013-7-10
	                    Date date = cell.getDateCellValue();
	                    cellvalue = date;
	                } else {// 如果是纯数字
	 
	                    // 取得当前Cell的数值
	                    cellvalue = String.valueOf(cell.getNumericCellValue());
	                }
	                break;
	            }
	            case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
	                // 取得当前的Cell字符串
	                cellvalue = cell.getRichStringCellValue().getString();
	                break;
	            default:// 默认的Cell值
	                cellvalue = "";
	            }
	        } else {
	            cellvalue = "";
	        }
	        return cellvalue;
	    }
	
	

	    
	    /**
	     * 保存图片返回图片名
	     * @Title savePic 
	     * @Description TODO
	     * @param pic
	     * @return
	     * @throws Exception
	     * @return String
	     */
	    private static String savePic(PictureData pic) throws Exception {  
	    	  
	    	String picName = null;
	        String ext = pic.suggestFileExtension();  	  
	        byte[] data = pic.getData(); 	        
	        long time = System.currentTimeMillis();
	    	Date date = new Date(time);    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	    	String fileName = "";
	    	String filePath = "";
	        if (ext.equals("jpeg")|| (ext.equals("jpg"))) {

	        	picName = sdf.format(date) + ".jpg";
	        	fileName = sdf.format(date);
	        	filePath = "D:\\soft\\tomcat7FH\\webapps\\picture\\" + sdf.format(date) + ".jpg";
	            
				 int num = 1;
				 File storefile = new File(filePath);
				 String sb3=null;
				 for(int i=0;storefile.exists();i++){//如果存在同名的附件，则在后面添加数字区分
					 //获取文件名称后面的文件组后一个.的下标（后缀名）
					num++;
		            int index = filePath.lastIndexOf(".");
		            sb3 = FilenameUtils.removeExtension(filePath)+num;
		            filePath = sb3+filePath.substring(index);
					storefile = new File(filePath);
				 }
	            
				 FileOutputStream out = new FileOutputStream(  
						 filePath );  
		        	 
	            out.write(data);  
	            out.close();  
	            return FilenameUtils.getName(filePath);
	        }  
	        if (ext.equals("png")) {	        	

	        	picName = sdf.format(date) + ".png";
	              
	            filePath = "D:\\soft\\tomcat7FH\\webapps\\picture\\" + sdf.format(date) + ".jpg";
	            
				 int num = 1;
				 File storefile = new File(filePath);
				 String sb3=null;
				 for(int i=0;storefile.exists();i++){//如果存在同名的附件，则在后面添加数字区分
					 //获取文件名称后面的文件组后一个.的下标（后缀名）
					num++;
		            int index = filePath.lastIndexOf(".");
		            sb3 = FilenameUtils.removeExtension(filePath)+num;
		            filePath = sb3+filePath.substring(index);
					storefile = new File(filePath);
				 }
				 FileOutputStream out = new FileOutputStream(  
						 filePath);

	            out.write(data);  
	            out.close(); 
	            return FilenameUtils.getName(filePath);
	        }  
	        return picName;
	    }  
	    
	    
	    /**
	     * 读取Excel图片并且存储(2003版本)
	     * @Title savePic 
	     * @Description TODO
	     * @param i
	     * @param pic
	     * @throws Exception
	     * @return void
	     */
	    private static String readPicHssf(HSSFWorkbook wb,int pictureIndex) throws Exception {  
	    	
	    	HSSFSheet sheet = (HSSFSheet) wb.getSheetAt(0);	    	
	    	Map<Integer, PictureData> map = getSheetPictrues03(0, sheet, wb);
	    	PictureData picData = map.get(pictureIndex);
	    	String picName = savePic(picData);  
	    	return picName;
	    }
	    
	    
	    /**
	     * 读取Excel图片并且存储(2007版本以上)
	     * @Title savePic 
	     * @Description TODO
	     * @param i
	     * @param pic
	     * @throws Exception
	     * @return void
	     */
	    private static String readPicXssf(XSSFWorkbook wb,int pictureIndex) throws Exception {  
	    	
	    	XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(0);	    	
	    	Map<Integer, PictureData> map = getSheetPictrues07(0, sheet, wb);
	    	PictureData picData = map.get(pictureIndex);
	    	String picName = savePic(picData);  
	    	return picName;
	    
	    }
	    
	    
	    /** 
	     * 获取Excel2003图片 
	     * @param sheetNum 当前sheet编号 
	     * @param sheet 当前sheet对象 
	     * @param workbook 工作簿对象 
	     * @return Map key:图片单元格索引（0_1_1）String，value:图片流PictureData 
	     * @throws IOException 
	     */  
	    public static Map<Integer, PictureData> getSheetPictrues03(int sheetNum,  
	            HSSFSheet sheet, HSSFWorkbook workbook) {  
	  
	        Map<Integer, PictureData> sheetIndexPicMap = new HashMap<Integer, PictureData>();  
	        List<HSSFPictureData> pictures = workbook.getAllPictures();  
	        if (pictures.size() != 0) {  
	            for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {  
	                HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();  
	                if (shape instanceof HSSFPicture) {  
	                    HSSFPicture pic = (HSSFPicture) shape;  
	                    int pictureIndex = pic.getPictureIndex() - 1;  
	                    HSSFPictureData picData = pictures.get(pictureIndex);  
//	                    String picIndex = String.valueOf(sheetNum) + "_"  
//	                            + String.valueOf(anchor.getRow1()) + "_"  
//	                            + String.valueOf(anchor.getCol1());  
	                    int picIndex = anchor.getRow1();
	                    sheetIndexPicMap.put(picIndex, picData);  
	                }  
	            }  
	            return sheetIndexPicMap;  
	        } else {  
	            return null;  
	        }  
	    }  
	    
	    
	    
	    
	    /** 
	     * 获取Excel2007图片 
	     * @param sheetNum 当前sheet编号 
	     * @param sheet 当前sheet对象 
	     * @param workbook 工作簿对象 
	     * @return Map key:图片单元格索引（0_1_1）String，value:图片流PictureData 
	     */  
	    public static  Map<Integer, PictureData> getSheetPictrues07(int sheetNum,  
	            XSSFSheet sheet, XSSFWorkbook workbook) {  
	        Map<Integer, PictureData> sheetIndexPicMap = new HashMap<Integer, PictureData>();  
	  
	        for (POIXMLDocumentPart dr : sheet.getRelations()) {  
	            if (dr instanceof XSSFDrawing) {  
	                XSSFDrawing drawing = (XSSFDrawing) dr;  
	                List<XSSFShape> shapes = drawing.getShapes();  
	                for (XSSFShape shape : shapes) {  
	                    XSSFPicture pic = (XSSFPicture) shape;  
	                    XSSFClientAnchor anchor = pic.getPreferredSize();  
	                    CTMarker ctMarker = anchor.getFrom();  
//	                    String picIndex = String.valueOf(sheetNum) + "_"  
//	                            + ctMarker.getRow() + "_" + ctMarker.getCol();  	                    
 
	                    int picIndex = ctMarker.getRow();
			            //int picIndex = shapes.indexOf(shape);  
	                    
	                    sheetIndexPicMap.put(picIndex, pic.getPictureData());  
	                }  
	            }  
	        }  
	        System.out.println("sheetIndexPicMap"+sheetIndexPicMap);
	        return sheetIndexPicMap; 
	  
	    } 
	    
	    
	    
	    
	    public static void main(String[] args) throws InvalidFormatException,Exception {  

		    InputStream inp = new FileInputStream(  
		            "E:\\ocr_pic\\_20180308_18969.xlsx");  
		    XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(inp);  
		    XSSFSheet sheet = workbook.getSheetAt(0);		
//	        Map<Integer, PictureData> sheetIndexPicMap = new HashMap<Integer, PictureData>();  
	  	  
	        for (POIXMLDocumentPart dr : sheet.getRelations()) {  
	            if (dr instanceof XSSFDrawing) {  
	                XSSFDrawing drawing = (XSSFDrawing) dr;  
	                List<XSSFShape> shapes = drawing.getShapes();  
	                for (XSSFShape shape : shapes) {  
	                    XSSFPicture pic = (XSSFPicture) shape;  
	                    XSSFClientAnchor anchor = pic.getPreferredSize();  
	                    CTMarker ctMarker = anchor.getFrom();  
	                    String str = ctMarker.getRow() + "_" + ctMarker.getCol();  	                    
                        System.out.println(str);
//	                    int picIndex = ctMarker.getRow();
//			            int picIndex = shapes.indexOf(shape);  	                    
	                    savePic(pic.getPictureData());
	                }  
	            }  
	        }  
	    }
	    public Map<Integer, Map<Integer,Object>> readExcelContent2() throws Exception{
	        if(wb==null){
	            throw new Exception("Workbook对象为空！");
	        }
	        Map<Integer, Map<Integer,Object>> content = new HashMap<Integer, Map<Integer,Object>>();
	         
	        sheet = wb.getSheetAt(0);
	        // 得到总行数
	        int rowNum = sheet.getLastRowNum();
	        row = sheet.getRow(0);
	       // int colNum = row.getPhysicalNumberOfCells();
	        String picName = null;
	        // 正文内容应该从第三行开始,第一行工厂,第二行为表头的标题
	        for (int i = 0; i <= rowNum; i++) {
	            row = sheet.getRow(i);
	            Object str = getCellFormatValue(row.getCell(0));
	            if(str == null ){
	            	continue;
	            }
	            	
	            int j = 0;
	            int num=0;
	            Map<Integer,Object> cellValue = new HashMap<Integer, Object>();
	            
	            while (j < 4) {
                      
		            	Object obj=null ;
		            	try 
		            	{
		            	obj = getCellFormatValue(row.getCell(j));
		            	}
		            	catch(Exception ex)
		            	{
		            	}
		            	
		            	
		                if(!"".equals(obj)&&obj!=null){
		                cellValue.put(j, obj);
		                j++;
		                }else{
		                	cellValue.put(j, null);
			                j++;	
		                }
		            	
	        }
	            
	            content.put(i, cellValue);
	        }
	        return content;
	    }
	}

