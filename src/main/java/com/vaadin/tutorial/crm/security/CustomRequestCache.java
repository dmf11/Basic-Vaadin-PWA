package com.vaadin.tutorial.crm.security;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomRequestCache extends HttpSessionRequestCache {

    // Saves authenticated requests so we can redirect the user to the page they were trying to access once logged in
    @Override
    public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
        if(!SecurityUtils.isFrameworkInternalRequest(request)) {
            super.saveRequest(request, response);
        }
    }

}