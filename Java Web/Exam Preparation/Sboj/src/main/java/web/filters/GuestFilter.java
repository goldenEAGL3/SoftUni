package web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/faces/home.xhtml",
        "/faces/add-job.xhtml",
        "/faces/job-details.xhtml",
        "/faces/job-delete.xhtml"})
public class GuestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getSession().getAttribute("username") == null) {
            resp.sendRedirect("login.xhtml");
            return;
        }

        filterChain.doFilter(req, resp);
    }
}
