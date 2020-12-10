package com.example.login_JWT_Token.filter;

import com.example.login_JWT_Token.Utils.JwtUtils;
import com.example.login_JWT_Token.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try{
            final String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

            if(Objects.isNull(header) || !header.startsWith("Bearer ")){
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }

            final String jwt = header.substring(7);
            final String username = jwtUtils.getUsernameFromToken(jwt);
            if(!Objects.isNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication()) ){
                UserDetails userDetails = userService.loadUserByUsername(username);
                if(jwtUtils.validateToken(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }catch(Exception e){
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid Token");
        }
    }
}
