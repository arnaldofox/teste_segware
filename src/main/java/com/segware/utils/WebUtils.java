package com.segware.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Classe utilitária
 *
 * @author Arnaldo
 */
public class WebUtils {

    public static String getIP(HttpServletRequest request) {
        if (request != null) {
            String ipAddress = request.getHeader("X-Forwarded-For");
            if (ipAddress == null || ipAddress.isEmpty()) {
                ipAddress = request.getRemoteAddr();
            }
            return ipAddress;
        }
        return null;
    }

}
