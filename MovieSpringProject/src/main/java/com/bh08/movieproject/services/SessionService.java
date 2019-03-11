package com.bh08.movieproject.services;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionService {
	
    private Long currentUserId; 
    
//    public boolean isUserAnAdmin() {
//        return !(this.getCurrentUserId() == null || !userService.findById(this.getCurrentUserId()).isCinemaAdmin());
//    }
}
