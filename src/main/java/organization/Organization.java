package organization;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Organization {

    private String name;
    private String address;
    private String phone;
    private LocalDate date;
    private String OGRN;
    private String INN;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date.toString();
    }

    public LocalDate parseDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getOGRN() {
        return OGRN;
    }

    public void setOGRN(String OGRN) {
        this.OGRN = OGRN;
    }

    public String getINN() {
        return INN;
    }

    public void setINN(String INN) {
        this.INN = INN;
    }

    public ArrayList<Security> getSecurityList() {
        return securityList;
    }

    public void setSecurityList(ArrayList<Security> securityList) {
        this.securityList = securityList;
    }

    private ArrayList<Security> securityList;

    @Override
    public String toString() {
        return String.format("%s - Дата основания %s",name, date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
