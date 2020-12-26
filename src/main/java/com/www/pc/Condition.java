package com.www.pc;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 */
public class Condition {
    public static void main(String[] args) {
        Data3 data = new Data3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.print1();
            }
            },"AAA").start();
        new Thread(()->{for (int i = 0; i < 10; i++) {
            data.print2();
        }},"BBB").start();
        new Thread(()->{for (int i = 0; i < 10; i++) {
            data.print3();
        }},"CCC").start();

    }
}

/**
 * 判断等待，业务，通知
 */
class Data3 { //资源类，Lock
    private Lock lock = new ReentrantLock();
    private java.util.concurrent.locks.Condition condition1 = lock.newCondition();
    private java.util.concurrent.locks.Condition condition2 = lock.newCondition();
    private java.util.concurrent.locks.Condition condition3 = lock.newCondition();
    private int num = 1;

    public void print1() {
        lock.lock();
        try {
            //业务：判断-》执行-》通知
            while (num != 1) {
                //等待
                condition1.await();
            }
            //唤醒：唤醒指定的人B
            num++;
            System.out.println(Thread.currentThread().getName() + "===>AAA");
            condition2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print2() {
        lock.lock();
        try {
            //业务：判断-》执行-》通知
            while (num != 2) {
                //等待
                condition2.await();
            }
            //唤醒：唤醒指定的人B
            num++;
            System.out.println(Thread.currentThread().getName() + "===>BBB");
            condition3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void print3() {
        lock.lock();
        try {
            //业务：判断-》执行-》通知
            while (num != 3) {
                //等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "===>CCC");
            //唤醒：唤醒指定的人B
            num = 1;
            condition1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
