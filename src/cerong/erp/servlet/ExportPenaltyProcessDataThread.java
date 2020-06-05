package cerong.erp.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cerong.erp.entity.ItemCase;
import cerong.erp.util.PathUtil;

public class ExportPenaltyProcessDataThread extends  Thread{
	List<ItemCase> list;
	public ExportPenaltyProcessDataThread(List<ItemCase> list) {
		this.list=list;
	}
public ExportPenaltyProcessDataThread(){
		
	}

@Override
public void run() {
	File storefile = new File(PathUtil.FinancialStatement,"ExportPenaltyProcessData.xlsx");
	for(int i=0;storefile.exists();i++){
		storefile.delete();
	}
	 XSSFWorkbook wb1 = new XSSFWorkbook(); 
	XSSFSheet sheet = wb1.createSheet("Page1");  
    XSSFRow row = sheet.createRow((int) 0);  
    XSSFCellStyle style = wb1.createCellStyle();  
    style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
    XSSFCell cell = row.createCell((short) 0);  
    cell.setCellValue("项目号");
    cell.setCellStyle(style);  
    cell = row.createCell((short) 1);  
    cell.setCellValue("项目名");  
    cell.setCellStyle(style);  
    cell = row.createCell((short) 2);
    cell.setCellValue("跟单");  
    cell.setCellStyle(style);  
    cell = row.createCell((short) 3);
    cell.setCellValue("采购");  
    cell.setCellStyle(style); 
    cell = row.createCell((short) 4); 
    cell.setCellValue("项目等级");
    cell.setCellStyle(style); 
    cell = row.createCell((short) 5);
    cell.setCellValue("客户类型");  
    cell.setCellStyle(style); 
    cell = row.createCell((short) 6);  
    cell.setCellValue("启动会议是否开");  
    cell.setCellStyle(style); 
    cell = row.createCell((short) 7);  
    cell.setCellValue("需求汇总是否上传");  
    cell.setCellStyle(style); 
    cell = row.createCell((short) 8);
    cell.setCellValue("po表是否上传");  
    cell.setCellStyle(style);
    cell = row.createCell((short) 9);
    cell.setCellValue("销售合同是否上传");
    cell.setCellStyle(style);
    cell = row.createCell((short) 10);
    cell.setCellValue("合同是否做出");  
    cell.setCellStyle(style); 
    cell = row.createCell((short) 11);
    cell.setCellValue("A/B类项目计划是否做出");  
    cell.setCellStyle(style); 
    cell = row.createCell((short) 12);
    cell.setCellValue("图纸是否上传");  
    cell.setCellStyle(style); 
    cell = row.createCell((short) 13);
    cell.setCellValue("技术文档上传");  
    cell.setCellStyle(style);
    cell = row.createCell((short) 14);
    cell.setCellValue("项目是否延期");
    cell.setCellStyle(style);
    cell = row.createCell((short) 15);
    cell.setCellValue("检验计划是否上传");  
    cell.setCellStyle(style); 
    cell = row.createCell((short) 16);
    cell.setCellValue("样品交期");  
    cell.setCellStyle(style); 
    cell = row.createCell((short) 17);
    cell.setCellValue("大货交期");  
    cell.setCellStyle(style); 
    cell = row.createCell((short) 18);
    cell.setCellValue("检验报告");  
    cell.setCellStyle(style); 
    cell = row.createCell((short) 19);
    cell.setCellValue("检验计划更新");  
    cell.setCellStyle(style); 
    cell = row.createCell((short) 20);
    cell.setCellValue("样品分析会");  
    cell.setCellStyle(style); 
    cell = row.createCell((short) 21);
    cell.setCellValue("大货分析会");  
    cell.setCellStyle(style); 
    cell = row.createCell((short) 22);
    cell.setCellValue("质量反馈");
    cell.setCellStyle(style);
    cell = row.createCell((short) 23);
    cell.setCellValue("项目状态");
    cell.setCellStyle(style);
    cell = row.createCell((short) 24);
    cell.setCellValue("解释");
    cell.setCellStyle(style);
    int total=0;
    for (int i = 0; i < list.size(); i++)  
    { 
    	total++;
    
    	ItemCase itemCase=list.get(i);
    row = sheet.createRow((int) total); 
    row.createCell((short) 0).setCellValue(itemCase.getCaseNo());  
    row.createCell((short) 1).setCellValue(itemCase.getProjectDescc()+":"+itemCase.getProjectDesce());
    row.createCell((short) 2).setCellValue(itemCase.getMerchandManager1());
    row.createCell((short) 3).setCellValue(itemCase.getMerchandManager2());
    if(itemCase.getProjectLevel()==1){
    row.createCell((short) 4).setCellValue("A");
    }else if(itemCase.getProjectLevel()==2){
    row.createCell((short) 4).setCellValue("B");
    }else if(itemCase.getProjectLevel()==3){
    row.createCell((short) 4).setCellValue("C");
    }else{
    	row.createCell((short) 4).setCellValue("");	
    }
    if(itemCase.getProductState()==1){
    	 row.createCell((short) 5).setCellValue("新客户");
    }else if(itemCase.getProductState()==2){
    	row.createCell((short) 5).setCellValue("老客户");
    }
    if(itemCase.getQpId()!=null){
    	row.createCell((short) 6).setCellValue(itemCase.getQpId()); 
    }else{
    	row.createCell((short) 6).setCellValue("未开"); 
    }
    if(itemCase.getPdId1()!=null){
    	row.createCell((short) 7).setCellValue("已上传");	
    }else{
    	row.createCell((short) 7).setCellValue("未上传");	
    }
    if(itemCase.getPo()!=null){
    	row.createCell((short) 8).setCellValue("已上传");
    }else{
    	row.createCell((short) 8).setCellValue("无");
    }
        if(itemCase.getSalesContract()==0){
            row.createCell((short) 9).setCellValue("进账未超过1万美元,不需要");
        }else if(itemCase.getSalesContract()==1){
            row.createCell((short) 9).setCellValue("已上传");
        }else if(itemCase.getSalesContract()==2){
            row.createCell((short) 9).setCellValue("未上传("+itemCase.getCustomerManager()+")");
        }
   if(itemCase.getBargainNo()!=null){
	   row.createCell((short) 10).setCellValue(itemCase.getBargainNo());
   }else{
	   row.createCell((short) 10).setCellValue("无");
   }

   if(itemCase.getPdId()!=0){
	   row.createCell((short) 11).setCellValue("已上传");
   }else{
	   if(itemCase.getProjectLevel()==1||itemCase.getProjectLevel()==2){
	   row.createCell((short) 11).setCellValue("未上传");
	   }else if(itemCase.getProjectLevel()==3){
		   row.createCell((short) 11).setCellValue("C");
	   }else{
		   row.createCell((short) 11).setCellValue("");
	   }
   }
   if(itemCase.getRemark()!=null){
	   row.createCell((short) 12).setCellValue(itemCase.getRemark());
   }else{
	   row.createCell((short) 12).setCellValue("未上传");
   }
    if(itemCase.getTechnicalDocumentation()!=null){
    	row.createCell((short) 13).setCellValue(itemCase.getTechnicalDocumentation());
    }else{
    	if(itemCase.getProjectLevel()==1||itemCase.getProjectLevel()==2){
    		   row.createCell((short) 13).setCellValue("未上传");
    		   }else if(itemCase.getProjectLevel()==3){
    			   row.createCell((short) 13).setCellValue("C");
    		   }else{
    			   row.createCell((short) 13).setCellValue("");
    		   }
    }
        if(itemCase.getProjectLevel()==1||itemCase.getProjectLevel()==2||itemCase.getProjectLevel()==0){
            if(itemCase.getDelay()==0) {
                row.createCell((short) 14).setCellValue("无延期");
            }else{
                row.createCell((short) 14).setCellValue("项目延期");
            }
        }else{
            row.createCell((short) 14).setCellValue("C");
        }
    if(itemCase.getPoId()!=null){
    	row.createCell((short) 15).setCellValue(itemCase.getPoId());
    }else{
    	row.createCell((short) 15).setCellValue("未上传");
    }
    if(itemCase.getDateSample()!=null){
    	 row.createCell((short) 16).setCellValue(itemCase.getDateSample());
    }else{
    	 row.createCell((short) 16).setCellValue("无");
    }
    if(itemCase.getCompletiontime()!=null){
        String param[]=itemCase.getCompletiontime().split(" ");
    	 row.createCell((short) 17).setCellValue(param[0]);
    }else{
    	 row.createCell((short) 17).setCellValue("无");
    }
   if(itemCase.getIntro()!=null){
	   row.createCell((short) 18).setCellValue(itemCase.getIntro());
   }else{
	   row.createCell((short) 18).setCellValue("无");
   }
   if(itemCase.getPoId2()!=null){
	   row.createCell((short) 19).setCellValue(itemCase.getPoId2());
   }else{
	   row.createCell((short) 19).setCellValue("未更新");
   } 
    if(itemCase.getQpId1()!=null){
    row.createCell((short) 20).setCellValue(itemCase.getQpId1());
    }else{
    	 row.createCell((short) 20).setCellValue("未开");
    }
    if(itemCase.getQpId2()!=null){
    if("0".equalsIgnoreCase(itemCase.getQpId2())){
        row.createCell((short) 21).setCellValue("有问题，大于5000美元，未开");
    }else if("1".equalsIgnoreCase(itemCase.getQpId2())){
        row.createCell((short) 21).setCellValue("大于5000美元，未开");
    } else if("2".equalsIgnoreCase(itemCase.getQpId2())){
        row.createCell((short) 21).setCellValue("有问题，未开");
    }else if("3".equalsIgnoreCase(itemCase.getQpId2())){
        row.createCell((short) 21).setCellValue("无");
    }  else {
        row.createCell((short) 21).setCellValue(itemCase.getQpId2());
    }
    }else{
    	
    	 row.createCell((short) 21).setCellValue("未开");
    }

    if(itemCase.getFeedbacktime()!=null&&!"".equalsIgnoreCase(itemCase.getFeedbacktime())
    &&!itemCase.getFeedbacktime().toLowerCase().contains("1900")){

        row.createCell((short) 22).setCellValue("客户已反馈，时间:"+itemCase.getFeedbacktime());
    }
    if(itemCase.getProject_status()==0){
        row.createCell((short) 23).setCellValue("新项目");
    }else if(itemCase.getProject_status()==1){
        row.createCell((short) 23).setCellValue("进行中项目");
    }else if(itemCase.getProject_status()==2){
        row.createCell((short) 23).setCellValue("大货完结项目");
    }else if(itemCase.getProject_status()==6){
        row.createCell((short) 23).setCellValue("样品完结项目");
    }
        row.createCell((short) 24).setCellValue(itemCase.getRemarks());


    }
    // 第六步，将文件存到指定位置  
    try  
    {  
        FileOutputStream fout = new FileOutputStream(PathUtil.FinancialStatement+File.separator+"ExportPenaltyProcessData.xlsx");  
        wb1.write(fout);  
        fout.close();  
    }  
    catch (Exception e)  
    {  
        e.printStackTrace();  
    }	  
  
	
	super.run();
}
}
