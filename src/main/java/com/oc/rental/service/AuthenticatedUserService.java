package com.oc.rental.service;
import com.oc.rental.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService {

    public Long getAuthenticatedOwnerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found");
        }

        // Assuming `User` is your user details implementation class
        User user = (User) authentication.getPrincipal();

        // Assuming `getOwnerId()` is a method in `User` that returns the owner's ID
        return user.getId();
    }
}

