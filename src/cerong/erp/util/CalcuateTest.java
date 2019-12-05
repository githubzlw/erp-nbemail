package cerong.erp.util;

import java.util.List;



import cerong.erp.entity.ContractApprovalForm;
import cerong.erp.entity.ItemCase;
import cerong.erp.entity.MeetingRecord;
import cerong.erp.service.ContractApprovalFormServiceImpl;
import cerong.erp.service.IContractApprovalFormService;
import cerong.erp.service.ItCaseIdService;
import cerong.erp.service.ItCaseIdServiceImpl;

public class CalcuateTest {
	ItCaseIdServiceImpl service;  
	IContractApprovalFormService service1;  
    //@Test
    public void testAdd() {  
    	service =  new ItCaseIdService();
    	MeetingRecord it=new MeetingRecord();
    	List<MeetingRecord> list=service.viewConferenceRecords(it);  
        System.out.print(list);  
        /* 
         * "加法有问题"：期望值和实际值不一致时，显示的信息 
         * 5 ：期望值 
         * result ：实际值 
         */  
    }  
  
   // @Test
    public void testAdd1() {  
    	service1=new ContractApprovalFormServiceImpl();
    	service =  new ItCaseIdService();
    	ItemCase it=new ItemCase();
    	List<ItemCase> list=service.getprojectContract(it);
    	ContractApprovalForm cp =new ContractApprovalForm();
	    cp.setDealWith(-1);
        cp.setSaleName("fionayan");
		List <ContractApprovalForm> cusList =service1.getAll(cp);
		List <ContractApprovalForm> cusList1 =service1.getContracts(cp);
		 
		 MeetingRecord it1=new MeetingRecord();
		it1.setNewProject(8);
		it1.setNewProjectTime("2017/6/23");
		List<MeetingRecord> list2=service.viewConferenceRecords1(it1);
		
		System.out.print(cusList+"&&&&"+cusList1); 
		 System.out.print(list);
		 System.out.print(list2);
    } 
   

}
