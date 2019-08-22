package com.zwc.tests;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-22 09:57
 * @Description: //TODO 多线程的创建方式
 **/
public class ThreadTest {

    /*//todo 1. 通过集成Thread类*/
    class MyThread extends Thread {

        public void run() {
            // doSomeThing there.
        }
    }

    public void doMyThread() {
        // 定义类对象
        MyThread myThread = new MyThread();
        // 调用线程，使线程进入就绪状态，等待CPU分配资源进行调度
        myThread.start();
    }

    /*//todo 2. 通过实现Runnable接口，重写run方法*/
    class MyRunnable implements Runnable {

        @Override
        public void run() {
            // doSomeThing there.
        }
    }

    public void doMyRunnable() {
        // 定义类对象
        MyRunnable myRunnable = new MyRunnable();
        // 创建一个Thread线程
        Thread thread = new Thread(myRunnable);
        // 调用线程，使线程进入就绪状态，等待CPU分配资源进行调度
        thread.start();
    }

    /*//todo 3. 通过实现Callable接口，重写call方法，结合FutureTask，该FutureTask对象封装了该Callable对象的call()方法的返回值*/
    class MyCallable implements Callable {

        @Override
        public Object call() throws Exception {
            // doSomeThing there.
            return null;
        }
    }

    public void doMyCallable() {
        // 定义类对象
        MyCallable myCallable = new MyCallable();
        // 由myCallable创建一个FutureTask对象
        // FutureTask是一个包装器，它通过接受Callable来创建，它同时实现了Future和Runnable接口
        FutureTask futureTask = new FutureTask<>(myCallable);
        // 由FutureTask创建一个Thread对象
        Thread thread = new Thread(futureTask);
        // 调用线程，使线程进入就绪状态，等待CPU分配资源进行调度
        thread.start();
    }
}
