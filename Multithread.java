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