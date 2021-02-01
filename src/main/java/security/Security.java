package security;

import currency.Currency;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Security {

    private Currency currency;
    private int code;
    private LocalDate date;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public LocalDate parseDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        return String.format("Code: %s,  Date: %s", code, getDate());
    }
}
