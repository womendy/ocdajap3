package com.oc.rental.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationEntryPoint extends OncePerRequestFilter  {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RentalUserDetailsService rentalUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
      String token = getJWTfromRequest(request);
      if(StringUtils.isNotBlank( token )) {
        if (jwtUtil.validateToken(token)) {

          String username = jwtUtil.getUsernameFromJWT(token);
          UserDetails userDetails = rentalUserDetailsService.loadUserByUsername(username);

          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
            null, userDetails.getAuthorities());

          authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
      }
      filterChain.doFilter(request, response);

    }


    private String getJWTfromRequest(HttpServletRequest request) {
      String bearerToken = request.getHeader("Authorization");
      if(bearerToken!=null &&  bearerToken.startsWith("Bearer ")) {
        return bearerToken.substring(7);
      } else {
        return null;
      }
    }


}

