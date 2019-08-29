package com.zwc.tests;


/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-22 16:14
 * @Description: //TODO 启动类
 **/
public class MainTest {

    public static void main(String[] args) {

        String a = new String("abc");

        String b = new String("abc");

        if (a == b )
            System.out.println("a==b");
        else
            System.out.println("a!=b");
        if (a.equals(b))
            System.out.println("a==b");
        else
            System.out.println("a!=b");
        if (a.hashCode() == b.hashCode())
            System.out.println("a==b");
        else
            System.out.println("a!=b");


    }
}
