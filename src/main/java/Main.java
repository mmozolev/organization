import com.fasterxml.jackson.databind.ObjectMapper;
import organization.Organizations;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File test = new File("src/main/java/Org.json");
        Organizations organizations = (objectMapper.readValue(test, Organizations.class));

        organizations.printInfo();
        organizations.printSecurity();
        organizations.printOrg("21.11/1990");
        organizations.printByValueCode("RU");
    }
}
