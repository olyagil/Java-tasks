package com.epam.task3.annotation;

import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LogExecutionTime {
}
