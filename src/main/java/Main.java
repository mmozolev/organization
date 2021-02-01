import organization.Organizations;

public class Main {
    public static void main(String[] args) {

        Organizations organizations1 = Organizations.parseJson();
        organizations1.printInfo();
        organizations1.printSecurity();
        organizations1.printOrg("21.11/1990");
        organizations1.printByValueCode("RU");
    }
}
