class Multithread extends Thread {

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