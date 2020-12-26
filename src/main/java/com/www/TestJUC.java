package com.www;

public class TestJUC {
    public static void main(String[] args) {
        //获取CPU核数
        //CPU密集型、io密集型
        System.out.println(Runtime.getRuntime().availableProcessors());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
