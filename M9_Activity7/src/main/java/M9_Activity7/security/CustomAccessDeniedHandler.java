package M9_Activity7.security;

import jakarta.servlet.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
@Override
public void handle(HttpServletRequest req, HttpServletResponse res,
	                   AccessDeniedException ex) throws IOException {
res.setStatus(HttpServletResponse.SC_FORBIDDEN);
	      res.setContentType("application/json");
	      res.getWriter().write("{\"error\":\"Forbidden\",\"message\":\"" + ex.getMessage() + "\"}");
	 }
}
