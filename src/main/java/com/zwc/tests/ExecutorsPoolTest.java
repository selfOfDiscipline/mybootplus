package com.zwc.tests;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-08-23 14:51
 * @Description: //TODO 线程池
 **/
public class ExecutorsPoolTest {

    public void test() {

        /**
         * 作用：该方法返回一个固定线程数量的线程池，该线程池中的线程数量始终不变，
         *      即不会再创建新的线程，也不会销毁已经创建好的线程，自始自终都是那几个固定的线程在工作，
         *      所以该线程池可以控制线程的最大并发数。
         * 栗子：假如有一个新任务提交时，线程池中如果有空闲的线程则立即使用空闲线程来处理任务，
         *      如果没有，则会把这个新任务存在一个任务队列中，一旦有线程空闲了，则按FIFO(先进先出)方式处理任务队列中的任务。
         * */
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);

        /**
         * 作用：该方法返回一个可以根据实际情况调整线程池中线程的数量的线程池。
         *      即该线程池中的线程数量不确定，是根据实际情况动态调整的。
         * 栗子：假如该线程池中的所有线程都正在工作，
         *      而此时有新任务提交，那么将会创建新的线程去处理该任务，
         *      而此时假如之前有一些线程完成了任务，现在又有新任务提交，那么将不会创建新线程去处理，而是复用空闲的线程去处理新任务。
         *      那么此时有人有疑问了，那这样来说该线程池的线程岂不是会越集越多？
         *      其实并不会，因为线程池中的线程都有一个“保持活动时间”的参数，
         *      通过配置它，如果线程池中的空闲线程的空闲时间超过该“保存活动时间”则立刻停止该线程，
         *      而该线程池默认的“保持活动时间”为60s。
         * */
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        /**
         * 作用：该方法返回一个只有一个线程的线程池，即每次只能执行一个线程任务，
         *      多余的任务会保存到一个任务队列中，等待这一个线程空闲，
         *      当这个线程空闲了再按FIFO(先进先出)方式顺序执行任务队列中的任务。
         */
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();

        /**
         * 作用：该方法返回一个可以控制线程池内线程定时或周期性执行某任务的线程池。
         */
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(10);

        /**
         * 作用：该方法返回一个可以控制线程池内线程定时或周期性执行某任务的线程池。
         *      只不过和上面的区别是该线程池大小为1，而上面的可以指定线程池的大小。
         */
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        /*//todo invokeAll(...)与 invokeAny(...)类似也是接收一个Callable集合，
             但是前者执行之后会返回一个Future的List，其中对应着每个Callable任务执行后的Future对象*/
        Set<Callable<String>> callables = Sets.newHashSet();
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "callable result task1";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "callable result task2";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "callable result task3";
            }
        });
        try {
            List<Future<String>> futures = executorService.invokeAll(callables);
            for (Future<String> future: futures) {
                System.out.println("future.get = " + future.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }



}
