package organization;

import security.Security;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Organizations {

    private ArrayList<Organization> organizationsList = new ArrayList<>();

    public ArrayList<Organization> getOrganizationsList() {
        return organizationsList;
    }

    public void setOrganizationsList(ArrayList<Organization> organizations) {
        this.organizationsList = organizations;
    }

    public static Organizations parseJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        File test = new File("src/main/resources/Org.json");
        try {
            return objectMapper.readValue(test, Organizations.class);
        } catch (IOException e) {
            System.out.println("Json parsing error!");
        }
        return null;
    }

    //Вывести все имеющиеся компании в формате
    public void printInfo() {
        organizationsList.forEach(System.out::println);
    }

    //Вывести все ценные бумаги (их код, дату истечения и полное название организации-владельца),
    // которые просрочены на текущий день, а также посчитать суммарное число всех таких бумаг;
    public void printSecurity() {

        ArrayList<Long> countList = new ArrayList<>();
        Long count = 0L;

        organizationsList.forEach(s -> {
            List<Security> list = s.getSecurityList().stream()
                    .filter(x -> x.parseDate().isBefore(LocalDate.now()))
                                .collect(Collectors.toList());
            list.forEach(x -> System.out.println(x + " Organization: " + s.getName()));
            countList.add(list.stream().count());
        });

        System.out.println("Number of overdue securities: " + countList.stream().reduce(count, Long::sum));
    }

    //На запрос пользователя в виде даты «ДД.ММ.ГГГГ», «ДД.ММ.ГГ», «ДД/ММ/ГГГГ» и «ДД/ММ/ГГ»
    // вывести название и дату создания всех организаций, основанных после введенной даты;
    public void printOrg(String userDate) {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("dd/MM/")
                .optionalStart()
                .appendPattern("yyyy")
                .optionalEnd()
                .optionalStart()
                .appendValueReduced(ChronoField.YEAR, 2, 2, 1930)
                .optionalEnd()
                .toFormatter();

        LocalDate date;

        if (userDate.matches("\\d{2}\\W\\d{2}\\W\\d{2}")) {
            userDate = userDate.replaceAll("\\W", "/");
            date = LocalDate.parse(userDate, formatter);

        }
        else if (userDate.matches("\\d{2}\\W\\d{2}\\W\\d{4}")) {
            userDate = userDate.replaceAll("\\W", "/");
            date = LocalDate.parse(userDate, formatter);
        }

        else throw new DateTimeParseException("Parse error", userDate, 0);

        organizationsList.stream()
                .filter(s -> s.parseDate()
                        .isAfter(date))
                            .forEach(System.out::println);
    }

    //На запрос пользователя в виде кода валюты, например EU, USD, RUB и пр.
    // выводить id и коды ценных бумаг, использующих заданную валюту.
    public void printByValueCode (String code) {

        organizationsList.forEach(s -> {
            Stream<Security> list = s.getSecurityList().stream()
                        .filter(x -> x.getCurrency().getName().equals(code));

            list.forEach(x ->
                System.out.printf("currency.Currency id: %d, Code: %d%n", x.getCurrency().getId(), x.getCode()));
        });
    }

    @Override
    public String toString() {
        return organizationsList.toString();
    }
}
