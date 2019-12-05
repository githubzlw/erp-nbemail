package cerong.erp.controller;

import cerong.erp.entity.EmailUser;
import cerong.erp.entity.MonthlyProfitStatement;
import cerong.erp.service.EmployeeService;
import cerong.erp.service.IEmployeeServiceImpl;
import cerong.erp.service.IInvinceServiceImpl;
import cerong.erp.service.InvinceService;
import java.text.SimpleDateFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.mail.internet.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@Component
@RequestMapping("/QueryProfitController")
public class QueryProfitController {
    IInvinceServiceImpl iservice = new InvinceService();
    IEmployeeServiceImpl eservice = new EmployeeService();

    public  void queryMailInformation(HttpServletRequest request, HttpServletResponse response) {
        show();
    }
    public  void show(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        String time=sdf.format(new Date());
        for(int i=0;i<36;i++){
            try {
                Date date1=sdf.parse(time);
                int num=i+1;
                Calendar theCa = Calendar.getInstance();
                theCa.setTime(date1);
                theCa.add(theCa.MONTH, -num);
                String time1 = sdf.format(theCa.getTime());
                updateQueryProfit(time1);
            } catch (Exception e) {
              e.printStackTrace();
            }

        }

    }

    private  void updateQueryProfit(String time1) {
    List<EmailUser> alllist=eservice.getAllSales();
    List<MonthlyProfitStatement> statelist=new ArrayList<MonthlyProfitStatement>();
    for(int i=0;i<alllist.size();i++){
        EmailUser user=alllist.get(i);
        MonthlyProfitStatement statement=iservice.getOne(user.getUserName(),time1+"-01");
        statement.setTime(time1);
        statelist.add(statement);
   }
    iservice.insertAll(statelist);
    }
}
