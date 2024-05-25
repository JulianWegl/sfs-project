package at.fh.sfs.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.apache.commons.lang3.StringUtils;

@ApplicationPath("/sfs")
public class MainResource extends Application {
    public static String defaultUser = "admin";
    public static String defaultPassword = "admin";

    public static boolean checkAdminRights(String userName, String passwd) {
        if(StringUtils.isEmpty(userName)) {
            return false;
        }

        if(StringUtils.isEmpty(passwd)) {
            return false;
        }

        return false; // FIXED by disabling admin account (for this project we simply have no admin account at all)
    }
}
