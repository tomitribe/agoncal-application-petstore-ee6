/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.agoncal.application.petstore.meta;

import javax.ws.rs.PathParam;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)

@Metatype
public @interface Id {

    public static class $ {
        public void method(@Id @PathParam("id") Object o) {
        }
    }
}
