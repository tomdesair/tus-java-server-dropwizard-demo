package me.desair.dropwizard.api;

import javax.ws.rs.core.SecurityContext;

public class UploadUtils {

    protected UploadUtils() {

    }

    /**
     * Build a unique owner key for the current authenticated user.
     * @param securityContext The security context that provides information on the authenticated user.
     * @return A unique key for this user
     */
    public static String getOwnerKey(SecurityContext securityContext) {
        String owner = null;
        if (securityContext != null && securityContext.getUserPrincipal() != null) {
            owner = securityContext.getUserPrincipal().getName();
        }

        return owner;
    }
}
