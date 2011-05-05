package org.losohome.jerseyspringsecurity;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.springframework.security.access.AccessDeniedException;

/**
 *
 * @author Loïc Frering <loic.frering@gmail.com>
 */
@Provider
public class AccessDeniedMapper implements ExceptionMapper<AccessDeniedException> {

    @Override
    public Response toResponse(AccessDeniedException e) {
        return Response.status(401).entity(e.getMessage()).type("text/plain").build();
    }
    
}
