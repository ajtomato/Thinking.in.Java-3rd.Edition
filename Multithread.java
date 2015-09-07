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

class SenderThread extends Thread {

    private java.io.PipedWriter out = new java.io.PipedWriter();

    SenderThread() {
        start();
    }

    java.io.PipedWriter getPipedWriter() {
        return out;
    }

    @Override
    public void run() {
        try {
            sleep(1000);
        } catch (java.lang.InterruptedException e) {
            System.out.println(e);
        }

        for (char i = 'a'; i <= 'z'; ++ i) {
            try {
                out.write(i);
                sleep(100);
            } catch (java.io.IOException e) {
                System.out.println(e);
            } catch (java.lang.InterruptedException e) {
                System.out.println(e);
            }
        }

        try {
            sleep(1000);
        } catch (java.lang.InterruptedException e) {
            System.out.println(e);
        }
    }

}

class ReceiverThread extends Thread {

    private java.io.PipedReader in;

    ReceiverThread(java.io.PipedWriter out) {
        try {
            in = new java.io.PipedReader(out);
        } catch (java.io.IOException e) {
            System.out.println(e);
        }
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.print((char)in.read());
            } catch (java.io.IOException e) {
                System.out.println(e);
            }
        }
    }

}

class LongTimeThread extends Thread {

    @Override
    public void run() {
        try {
            // this should be used, when it is waiting for others to notify.
            synchronized (this) {
                System.out.println("LongTimeThread.wait");
                wait();
                System.out.println("LongTimeThread.run1");
            }
        } catch (InterruptedException e) {
            System.out.println(e);
            System.out.println("LongTimeThread.run2");
        }
        System.out.println("LongTimeThread.run3");
    }

}

class InterruptThread extends Thread {

    private Thread fInterruptedThread;

    InterruptThread(Thread t) {
        fInterruptedThread = t;
        start();
    }

    @Override
    public void run() {
        try {
            sleep(1000);
            fInterruptedThread.interrupt();
        } catch (java.lang.InterruptedException e) {
            System.out.println(e);
        }
    }
}
