package com.danielfreitassc.backend.services.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public String username() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return name;
	}

    public boolean authenticated() {
        if(username().equals("anonymousUser")) {
            return false;
        }
        
        var context = SecurityContextHolder.getContext();
        return context.getAuthentication().isAuthenticated();
    }

    public boolean isNotAuthenticated() {
        return !authenticated();
    }
}
