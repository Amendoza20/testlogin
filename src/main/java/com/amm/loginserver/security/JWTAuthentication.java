package com.amm.loginserver.security;

import com.amm.loginserver.service.JWTService;
import com.amm.loginserver.service.UserDetailService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthentication extends OncePerRequestFilter {

    JWTService jwtService;
    UserDetailService userDetailService;

   public JWTAuthentication(JWTService jwtService, UserDetailService userDetailService){
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse reponse, FilterChain filterChain)throws ServletException, IOException{
       String jwt = getJwtFromRequest(request);
       try{
           if(StringUtils.hasText(jwt) && jwtService.validateToken(jwt)){
               String username = jwtService.getUsernameFromJWT(jwt);

               UserDetails userDetails = userDetailService.loadUserByUsername(username);
               UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
               authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       filterChain.doFilter(request, reponse);
    }

    private String getJwtFromRequest(HttpServletRequest request){
       String bearerToken = request.getHeader("Authentication");

       if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) return bearerToken.substring(7);

       return bearerToken;
    }
}
