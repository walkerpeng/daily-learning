package me.walker.concurrent.monitorlifecycle;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
        Observable observableThread = new ObservableThread<>(() ->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("finished done.");
            return null;
        });
        observableThread.start();
    }
}
