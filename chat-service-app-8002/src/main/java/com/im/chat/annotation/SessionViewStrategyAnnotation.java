package com.im.chat.annotation;

import com.im.chat.enums.CvsTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SessionViewStrategyAnnotation
{
    CvsTypeEnum cvsType();
}
