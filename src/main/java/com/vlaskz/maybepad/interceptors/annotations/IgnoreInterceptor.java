package com.vlaskz.maybepad.interceptors.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) // Use ElementType.METHOD se quiser anotar métodos individuais em vez de classes inteiras

public @interface IgnoreInterceptor {}
