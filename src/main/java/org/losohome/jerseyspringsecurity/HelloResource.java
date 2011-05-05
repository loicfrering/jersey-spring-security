package org.losohome.jerseyspringsecurity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Loïc Frering <loic.frering@gmail.com>
 */
@Path("/hello")
@Transactional
public class HelloResource extends AbstractResource {
    
    @GET
    @Produces("text/plain")
    @PreAuthorize("hasPermission(#name, 'hello')")
    public String say(@QueryParam("name") String name) {
        return sayHello(name);
    }
    
}
