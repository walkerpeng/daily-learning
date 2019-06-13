package me.walker.concurrent.monitorlifecycle;

import java.util.concurrent.TimeUnit;

public class Test2 {

    public static void main(String[] args) {

        final TaskLifecycle<String> lifecycle = new TaskLifecycle<String>() {
            @Override
            public void onStart(Thread thread) {

            }

            @Override
            public void onRunning(Thread thread) {

            }

            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("result = [" + result + "]");
            }

            @Override
            public void onError(Thread thread, Exception e) {

            }
        };

        Observable observableThread = new ObservableThread<>(lifecycle, () ->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("finished done.");
            return "Hello Observer";
        });
        observableThread.start();
    }
}
