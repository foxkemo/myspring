package org.unomi.myspring.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/user/*")

public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws ServletException, IOException {
        
        chain.doFilter(request,response);


    }


}
