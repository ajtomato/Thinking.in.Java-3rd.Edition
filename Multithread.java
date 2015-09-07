class Multithread extends Thread {

    private Object syncObject = new Object();

    Multithread(int  i) {
        super("HelloWorld[" + i + "]");
        start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; ++ i) {
        }
        try {
            sleep(1000); // milliseconds
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        
        System.out.println("Multithread: " + getName());
    }

    synchronized void syncMethod1() {
        System.out.println("Multithread.syncMethod1");
    }

    void syncMethod2() {
        synchronized(syncObject) {
            System.out.println("Multithread.syncMethod2");
        }
    }

}

class Daemonthread extends Thread {

    Daemonthread(int i) {
        super("Daemonthread[" + i + "]");
        setDaemon(true);
        System.out.println("Daemonthread[Start]: " + i);
    }

    @Override
    public void run() {
        try {
            sleep(1000); // milliseconds
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        
        System.out.println("Daemonthread[Done]: " + getName());
    }
}

class RunnableThread implements Runnable {

    @Override
    public void run() {
        System.out.println("RunnableThread.run");
    }

}

class ProducerThread extends Thread {
    
    private java.util.LinkedList<ConsumerThread> fConsumerArray = new java.util.LinkedList<ConsumerThread>();

    ProducerThread() {
        setDaemon(true);
        start();
    }

    public void add(ConsumerThread c) {
        synchronized(this) {
            System.out.println("ProducerThread.add: " + c.getConsumerId());
            fConsumerArray.add(c);
        }
    }

    @Override
    public void run() {
        while (true) {
            synchronized(this) {
                if (fConsumerArray.size() > 0) {
                    ConsumerThread c = fConsumerArray.poll();
                    System.out.println("ProducerThread.run: " + c.getConsumerId());
                    // When you want to notify one, you must lock to the same object.
                    synchronized(c) {
                        c.notify();
                    }
                }
            }
        }
    }

}

class ConsumerThread extends Thread {

    private int fConsumerId;

    ConsumerThread(int id) {
        fConsumerId = id;
        start();
    }

    int getConsumerId() {
        return fConsumerId;
    }

    @Override
    public void run() {
        try {
            // this should be used, when it is waiting for others to notify.
            synchronized (this) {
                System.out.println("ConsumerThread.wait: " + fConsumerId);
                wait();
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("ConsumerThread.run: " + fConsumerId);
    }

}