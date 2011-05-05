package org.losohome.jerseyspringsecurity;

/**
 *
 * @author Loïc Frering <loic.frering@gmail.com>
 */
class AbstractResource {
    
    public String sayHello(String name) {
        return "Hello " + name + "!";
    }
    
}
