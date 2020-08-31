package com.im.chat.annotation;

import com.im.chat.enums.CvsTypeEnum;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SessionViewStrategyAnnotation
{
    CvsTypeEnum cvsType();
}
