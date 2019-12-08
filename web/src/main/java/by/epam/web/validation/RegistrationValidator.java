package by.epam.web.validation;

import by.epam.web.content.AttributeName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class RegistrationValidator {
    private static final Logger logger = LogManager.getLogger(RegistrationValidator.class);
    private static final String EMAIL_REGEX = "^\\w[\\d\\w]{2,}@\\w+\\.\\p{Lower}{2,4}";
    private static final String NAME_REGEX = "[A-Z][[a-z][а-я]]+";

    public Map<String,Object> validate(String email,String pass,String repeatedPass,  String firstName, String lastName){
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        if (validateEmail(email)) {
            map.put(AttributeName.INPUT_EMAIL, email);
        } else {
            flag = true;
            map.put(AttributeName.INCORRECT_EMAIL, true);
        }
        if (validatePass(pass)) {
            map.put(AttributeName.INPUT_PASSWORD, pass);
        } else {
            flag = true;
            map.put(AttributeName.INCORRECT_PASSWORD, true);
        }
        if (validateRepeatedPass(pass, repeatedPass)) {
            map.put(AttributeName.INPUT_REPEATED_PASSWORD, repeatedPass);
        } else {
            flag = true;
            map.put(AttributeName.INCORRECT_REPEATED_PASSWORD, true);
        }
        if (validateFirstName(firstName)) {
            map.put(AttributeName.INPUT_FIRST_NAME, firstName);
        } else {
            flag = true;
            map.put(AttributeName.INCORRECT_FIRST_NAME, true);
        }
        if (validateFirstName(lastName)) {
            map.put(AttributeName.INPUT_LAST_NAME, lastName);
        } else {
            flag = true;
            map.put(AttributeName.INCORRECT_LAST_NAME, true);
        }
        if(flag) {
            map.put("flag", flag);
        }
        return map;
    }

    public boolean validateEmail(String email){
        return !email.isEmpty() && email.matches(EMAIL_REGEX) && email.length() < 50;
    }

    public boolean validatePass(String pass){
        return pass.length()>=6 && pass.length()< 40;
    }

    public boolean validateRepeatedPass(String pass, String repPass){
        return pass.equals(repPass);
    }

    public boolean validateLastName(String lastName){
        return lastName.matches(NAME_REGEX);
    }

    public boolean validateFirstName(String firstName){
        return firstName.matches(NAME_REGEX);
    }
}
