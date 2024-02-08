package com.website.apnaStore.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.website.apnaStore.service.JwtService;
import com.website.apnaStore.service.UserServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private JwtService jwtService;

    @Autowired
    UserServiceImpl userServiceImpl;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String authHeader = request.getHeader("Authorization");
                if(authHeader == null || !authHeader.startsWith("Bearer ")){
                    filterChain.doFilter(request, response);
                    return;
                }
 
                String token  = authHeader.substring(7);

                String username = jwtService.extractUsername(token);

                 
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

                    UserDetails userDetails = userServiceImpl.loadUserByUsername(username);

                    try {
                        
                        if(jwtService.isValid(token, userDetails)){
    
                            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails,null,userDetails.getAuthorities()
                            );
                            
                        System.out.println(userDetails.getAuthorities());
                            authToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                            );
                            
                            SecurityContextHolder.getContext().setAuthentication(authToken);
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                        logger.error("Authentication failed: " + e.getMessage());
                        // Optionally, send an unauthorized response
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
                        return;
                    }
                }
            filterChain.doFilter(request, response);
    }
    
}
