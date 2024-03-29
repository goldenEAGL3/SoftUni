package web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/faces/index.xhtml",
        "/faces/login.xhtml",
        "/faces/register.xhtml"})
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       HttpServletRequest req = (HttpServletRequest) servletRequest;
       HttpServletResponse resp = (HttpServletResponse) servletResponse;

       if(req.getSession().getAttribute("username") != null) {
           resp.sendRedirect("home.xhtml");
           return;
       }

       filterChain.doFilter(req, resp);

    }
}
