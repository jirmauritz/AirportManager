package cz.muni.fi.pa165.airport_manager.security;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter user access
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
public class ProtectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
