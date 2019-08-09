package com.zwc.tests;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-07 16:44
 * @Description: //TODO 多线程
 **/
public class RunnableTest {

    public final int value = 4;

    public void doIt() {
        int value = 6;
        Runnable runnable = new Runnable() {

            public final int value = 5;

            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        runnable.run();
    }

    public static void main(String[] args) {
        System.out.println("---------------------------");
        RunnableTest runnableTest = new RunnableTest();
        runnableTest.doIt();
    }

}
