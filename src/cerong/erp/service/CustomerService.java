package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.CustomerDao;
import cerong.erp.dao.ICustomerDaoImpl;
import cerong.erp.entity.Customer;
import cerong.erp.entity.CustomerInfo;

public  class CustomerService implements ICustomerServiceImpl {
	ICustomerDaoImpl dao = new  CustomerDao();

	@Override
	public int updateInfo(CustomerInfo cus,int erpcid) {
		
		return dao.updateInf(cus,erpcid);
	}

	@Override
	public int getID(String firstName, int country, String ddlDgree) {
		
		return dao.getID(firstName,country,ddlDgree);
	}

	@Override
	public int getfirstName(String firstName) {
		
		return dao.getfirstName(firstName);
	}

	@Override
	public int getId(String firstName) {
		
		return dao.getId(firstName);
	}

	@Override
	public List<CustomerInfo> getCus(CustomerInfo cus) {
		
		return dao.getCus(cus);
	}

	@Override
	public CustomerInfo getCUS(int cid) {
		
		return dao.getCUS( cid);
	}

	@Override
	public int update(String name, String potential, String explain, int cid) {
		
		return dao.update(name,potential,explain,cid);
	}

	@Override
	public List<CustomerInfo> getCus1(CustomerInfo info) {
		
		return dao.getCus1(info);
	}

	@Override
	public List<CustomerInfo> getCus2(CustomerInfo info,int id,int id1,int id2,int id3) {
		
		return dao.getCus2(info,id,id1,id2,id3);
	}

	@Override
	public List<CustomerInfo> getCus3(CustomerInfo info,int id,int id1,int id2,int id3) {
		// TODO Auto-generated method stub
		return dao.getCus3(info,id,id1,id2,id3);
	}

	@Override
	public int getid(CustomerInfo info) {
		
		return dao.getid(info);
	}

	@Override
	public int getid1(CustomerInfo info) {
		
		return dao.getid1(info);
	}

	@Override
	public int getid2(CustomerInfo info) {
		
		return dao.getid2(info);
	}

	@Override
	public int getid3(CustomerInfo info) {
		
		return dao.getid3(info);
	}

	@Override
	public int getid3(Long millionSeconds1) {
		
		return dao.getid3(millionSeconds1);
	}

	@Override
	public List<CustomerInfo> getCu(CustomerInfo info, int id1,int id2) {
		
		return dao.getCu(info,id1,id2);
	}

	@Override
	public int getId(CustomerInfo info) {
		
		return dao.getId(info);
	}

	@Override
	public int getId1(CustomerInfo info) {
		// TODO Auto-generated method stub
		return dao.getId1(info);
	}

	@Override
	public int getId2(CustomerInfo info) {
		// TODO Auto-generated method stub
		return dao.getId2(info);
	}

	@Override
	public int getId3(CustomerInfo info) {
		// TODO Auto-generated method stub
		return dao.getId3(info);
	}

	@Override
	public int getId3(Long millionSeconds1) {
		
		return dao.getId3(millionSeconds1);
	}

	@Override
	public int updateCstatus(String cstatus, int id) {
		
		return dao.updateCstatus(cstatus,id);
	}

	@Override
	public List<Customer> getAll() {
		
		return dao.getAll();
	}

	@Override
	public int getCusTotal(CustomerInfo cus) {
		
		return dao.getCusTotal(cus);
	}

	@Override
	public int getCus1Total(CustomerInfo info) {
		
		return dao.getCus1Total(info);
	}



	@Override
	public int getCuTotal(CustomerInfo info,int id1, int id2) {
		
		return dao.getCuTotal(info,id1,id2);
	}

	@Override
	public int getCus2Total(CustomerInfo info,int id, int id1, int id2, int id3) {
		
		return dao.getCus2Total(info,id,id1,id2,id3);
	}

	@Override
	public int getCus3Total(CustomerInfo info,int id, int id1, int id2, int id3) {
		return dao.getCus3Total(info,id,id1,id2,id3);
	}

	@Override
	public List<CustomerInfo> getCusb(CustomerInfo info, int id, int id1, int id2, int id3) {
		
		return dao.getCusb(info,id,id1,id2,id3);
	}

	@Override
	public int getCusbTotal(CustomerInfo info, int id, int id1, int id2, int id3) {
		
		return dao.getCusbTotal(info,id,id1,id2,id3);
	}

	@Override
	public int geid(CustomerInfo info, Long millionSeconds3) {
		
		return dao.geid(info,millionSeconds3);
	}

	@Override
	public int geid1(CustomerInfo info, Long millionSeconds2) {
		return dao.geid1(info,millionSeconds2);
	}

	@Override
	public int geId(CustomerInfo info, Long millionSeconds3) {
		return dao.geId(info,millionSeconds3);
	}

	@Override
	public int geId1(CustomerInfo info, Long millionSeconds2) {
		return dao.geId1(info,millionSeconds2);
	}

	@Override
	public int geida(CustomerInfo info, Long millionSeconds3) {
		
		return dao.geida(info,millionSeconds3);
	}

	@Override
	public int geidb(CustomerInfo info, Long millionSeconds2) {
		return dao.geidb(info,millionSeconds2);
	}

	@Override
	public int geIdc(CustomerInfo info, Long millionSeconds3) {
		return dao.geIdc(info,millionSeconds3);
	}

	@Override
	public int geIdd(CustomerInfo info, Long millionSeconds2) {
		return dao.geIdd(info,millionSeconds2);
	}

	@Override
	public List<CustomerInfo> getCusc(CustomerInfo info, int id, int id1,
			int id2, int id3) {
		return dao.getCusc(info,id,id1,id2,id3);
	}

	@Override
	public List<CustomerInfo> getCusd(CustomerInfo info, int id, int id1,
			int id2, int id3) {
		return dao.getCusd(info,id,id1,id2,id3);
	}

	@Override
	public List<CustomerInfo> getCuse(CustomerInfo info, int id, int id1,
			int id2, int id3) {
		return dao.getCuse(info,id,id1,id2,id3);
	}

	@Override
	public List<CustomerInfo> getCusf(CustomerInfo info, int id, int id1,
			int id2, int id3) {
		return dao.getCusf(info,id,id1,id2,id3);
	}

	@Override
	public List<CustomerInfo> getCu2(CustomerInfo info, int id1, int id2) {
		return dao.getCu2(info,id1,id2);
	}

	@Override
	public List<CustomerInfo> getCu1(CustomerInfo info, int id1, int id2) {
		return dao.getCu1(info,id1,id2);
	}

	@Override
	public int updateremarks(int eid, String customerremarks) {
		
		return dao.updateremarks(eid,customerremarks);
	}

	@Override
	public List<Customer> getall() {
		
		return dao.getall();
	}

	

	
}
