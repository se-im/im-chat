package com.im.chat.util;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LocalDateTimeUtilTest
{
    @Test
    public void test01(){
        System.out.println(LocalDateTimeUtil.getDateByLocalDate().getTime());
        System.out.println(System.currentTimeMillis());
    }
}