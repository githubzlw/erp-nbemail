package cerong.erp.entity;

public class MonthlyProfitStatement {
    /** 用户id*/
    private int id;
    /** 用户名*/
    private String name;
    /** 时间*/
    private String time;
    /** 预计总收入*/
    private double income;
    /** 利润*/
    private double profit;
    /** 时间*/
    private String time1;
    /** 预计总收入*/
    private double income1;
    /** 利润*/
    private double profit1;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "MonthlyProfitStatement{" +
                "id=" + id +
                ", name=" + name +
                ", time='" + time + '\'' +
                ", income=" + income +
                ", profit=" + profit +
                 ", time1='" + time1 + '\'' +
                ", income1=" + income1 +
                ", profit1=" + profit1 +
                '}';
    }

    public double getProfit1() {
        return profit1;
    }

    public void setProfit1(double profit1) {
        this.profit1 = profit1;
    }

    public double getIncome1() {
        return income1;
    }

    public void setIncome1(double income1) {
        this.income1 = income1;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }
}

