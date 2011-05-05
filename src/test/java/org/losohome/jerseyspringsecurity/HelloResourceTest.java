package org.losohome.jerseyspringsecurity;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.ContextLoaderListener;
import static junit.framework.Assert.assertEquals;


/**
 *
 * @author Loïc Frering <loic.frering@gmail.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class HelloResourceTest {
    protected static Server server;
    
    @Before
    public void setUpJetty() throws Exception {
        server = new Server(8484);

        // On lance le serveur Jetty
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.setDisplayName("Jetty Spring Security Webapp");
        context.setContextPath("/");
        context.getInitParams().put("contextConfigLocation", "classpath:applicationContext.xml");
        context.addEventListener(new ContextLoaderListener());
        
        FilterHolder springSecurityFilter = new FilterHolder(org.springframework.web.filter.DelegatingFilterProxy.class);
        springSecurityFilter.setName("springSecurityFilterChain");
        context.addFilter(springSecurityFilter, "/*", 1);
        
        context.addServlet(SpringServlet.class, "/*");

        server.setHandler(context);
        server.start();
    }
    
    @After
    public void tearDownJetty() throws Exception {
        if (server != null) {
            server.stop();
        }
    }
    
    @Test
    public void testSayHello() {
        Client client = Client.create();
        
        WebResource r = client.resource("http://localhost:8484").path("/hello");
        ClientResponse cr = r.queryParam("name", "Loïc").get(ClientResponse.class);
        assertEquals(200, cr.getStatus());
        assertEquals("Hello Loïc!", cr.getEntity(String.class));

        r = client.resource("http://localhost:8484").path("/hello");
        cr = r.queryParam("name", "loic").get(ClientResponse.class);
        assertEquals(401, cr.getStatus());
        assertEquals("Access is denied", cr.getEntity(String.class));
    }
}
