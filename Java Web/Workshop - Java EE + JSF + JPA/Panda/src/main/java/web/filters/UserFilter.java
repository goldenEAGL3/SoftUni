package web.filters;

import domain.entity.enums.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/faces/pending.xhtml",
        "/faces/shipped.xhtml",
        "/faces/delivered.xhtml",
        "/faces/create-package.xhtml"})
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getSession().getAttribute("role") == Role.USER) {
            resp.sendRedirect("home.xhtml");
        } else {
            filterChain.doFilter(req, resp);
        }
    }
}
