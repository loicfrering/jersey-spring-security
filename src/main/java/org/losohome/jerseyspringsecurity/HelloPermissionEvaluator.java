/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.losohome.jerseyspringsecurity;

import java.io.Serializable;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

/**
 *
 * @author Loïc Frering <loic.frering@gmail.com>
 */
public class HelloPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (targetDomainObject.toString().equals("Loïc")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication a, Serializable srlzbl, String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
