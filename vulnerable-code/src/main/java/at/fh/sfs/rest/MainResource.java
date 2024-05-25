package at.fh.sfs.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

@ApplicationPath("/sfs")
public class MainResource extends Application {

    public static boolean checkAdminRights(String userName, String passwd) {
        if(StringUtils.isEmpty(userName)) {
            return false;
        }

        if(StringUtils.isEmpty(passwd)) {
            return false;
        }

        return userName.equals("admin") && passwd.equals("admin");
    }

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }
}
