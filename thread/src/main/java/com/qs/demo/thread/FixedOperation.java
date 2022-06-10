package com.qs.demo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class FixedOperation {
    public static void main(String[] args) {
        /*Test1 test1 = new Test1(5);
        new Thread(() -> test1.print("a", "b")).start();
        new Thread(() -> test1.print("b", "c")).start();
        new Thread(() -> test1.print("c", "a")).start();
        test1.start("a");*/

        Test2 test2 = new Test2(5);
        Condition aCondition = test2.getCondition();
        Condition bCondition = test2.getCondition();
        Condition cCondition = test2.getCondition();
        new Thread(() -> test2.print("a", aCondition, bCondition)).start();
        new Thread(() -> test2.print("b", bCondition, cCondition)).start();
        new Thread(() -> test2.print("c", cCondition, aCondition)).start();
        test2.start(aCondition);

        /*Test3 test3 = new Test3(5);
        Thread a = new Thread(() -> test3.print("a", 1));
        Thread b = new Thread(() -> test3.print("b", 2));
        Thread c = new Thread(() -> test3.print("c", 0));
        a.start();
        b.start();
        c.start();
        test3.setThreads(a, b, c);
        test3.start(a);*/
    }

}

class Test1 extends Object {
    private String print;
    private int loopNum;

    public void print(String print, String nextPrint) {

        try {
            for (int i = 0; i < loopNum; i++) {
                synchronized (this) {
                    while (!print.equals(this.print)) {
                        this.wait();
                    }
                    System.out.print(print);
                    this.print = nextPrint;
                    this.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void start(String print) {
        synchronized (this) {
            this.print = print;
            this.notifyAll();
        }
    }

    public Test1(int loopNum) {

        this.loopNum = loopNum;
    }
}

class Test2 {
    private int loop;
    private Lock lock = new ReentrantLock();

    public Condition getCondition() {
        return lock.newCondition();
    }

    public void print(String print, Condition condition, Condition next) {
        lock.lock();
        try {
            for (int i = 0; i < loop; i++) {
                condition.await();
                System.out.print(print);
                next.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public void start(Condition condition) {
        lock.lock();
        condition.signal();
        lock.unlock();
    }

    public Test2(int loop) {
        this.loop = loop;
    }
}

class Test3 {

    private int loop;

    private Thread[] threads;


    public void print(String print, int size) {
        for (int i = 0; i < loop; i++) {
            LockSupport.park();
            System.out.print(print);
            LockSupport.unpark(threads[size]);
        }
    }

    public void start(Thread next) {
        LockSupport.unpark(next);
    }

    public Test3(int loop) {
        this.loop = loop;
    }

    public Test3 setThreads(Thread... threads) {
        this.threads = threads;
        return this;
    }
}