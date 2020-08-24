package tech.iwish.shyafinance.model;

public class ClientLoanList  {

    String sno;
    String client_id;
    String account_no;
    String client_type;
    String name;

    public ClientLoanList(String sno, String client_id, String account_no, String client_type, String name) {
        this.sno = sno;
        this.client_id = client_id;
        this.account_no = account_no;
        this.client_type = client_type;
        this.name = name;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String client_no) {
        this.account_no = client_no;
    }

    public String getClient_type() {
        return client_type;
    }

    public void setClient_type(String client_type) {
        this.client_type = client_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
