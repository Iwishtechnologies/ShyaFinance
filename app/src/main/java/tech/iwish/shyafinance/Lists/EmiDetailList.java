package tech.iwish.shyafinance.Lists;

public class EmiDetailList {
    String Emidate, Emiamount;
    public EmiDetailList(String Emidate,String Emiamount){
        this.Emiamount=Emiamount;
        this.Emidate= Emidate;
    }
    public void setEmidate(String emidate){
        this.Emidate=emidate;
    }
    public void setEmiamount(String emiamount){
        this.Emiamount=emiamount;
    }
    public String getEmidate(){
        return Emidate;
    }
    public String getEmiamount(){
        return Emiamount;
    }
}
