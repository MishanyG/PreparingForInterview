package HomeWork_L3;

class TwoThreads implements Runnable {

    private static final Object obj = new Object();

    public void run() {
        try {
            while(true) {
                synchronized(obj) {
                    obj.notify();
                    obj.wait();
                    System.out.println(Thread.currentThread().getName());
                    obj.notify();
                    obj.wait();
                }
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MainThreads {
    public static void main(String[] args) {
        TwoThreads ping = new TwoThreads();
        TwoThreads pong = new TwoThreads();
        Thread one = new Thread(ping);
        Thread two = new Thread(pong);
        one.setName("ping");
        two.setName("pong");
        one.start();
        two.start();
    }
}
