package model;

import java.util.*;

public class LoginModel {

    public LoginModel() {
    }

    public int authorize(String uid, String password) {
        ResourceBundle usersResource = ResourceBundle.getBundle("users");
        if(usersResource.containsKey(uid + ".password") &&
                usersResource.containsKey(uid + ".access") &&
                usersResource.getString(uid + ".password").equals(password))
            return Integer.parseInt(usersResource.getString(uid + ".access"));
        return -1;
    }
}
