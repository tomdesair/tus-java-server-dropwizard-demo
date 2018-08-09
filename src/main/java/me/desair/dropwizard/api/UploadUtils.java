package me.desair.dropwizard.api;

import javax.ws.rs.core.SecurityContext;

public class UploadUtils {

    protected UploadUtils() {

    }

    public static String getOwnerKey(SecurityContext securityContext) {
        String owner = null;
        if (securityContext != null && securityContext.getUserPrincipal() != null) {
            owner = securityContext.getUserPrincipal().getName();
        }

        return owner;
    }
}
