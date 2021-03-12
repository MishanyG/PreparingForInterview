package HomeWork_L3;

import java.util.concurrent.locks.ReentrantLock;

class Counter {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        CountThread countThread = new CountThread(lock, 10, 50);
        Thread t1 = new Thread(countThread);
        Thread t2 = new Thread(countThread);
        t1.start();
        t2.start();
    }
}

class CountThread implements Runnable {
    ReentrantLock lock;
    int count;
    int continueTo;
    int i = 0;

    public CountThread(ReentrantLock lock, int count, int continueTo) {
        this.lock = lock;
        this.count = count;
        this.continueTo = continueTo;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            do {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                i++;
            } while(count >= i);
            count = continueTo;
        } finally {
            lock.unlock();
        }
    }
}