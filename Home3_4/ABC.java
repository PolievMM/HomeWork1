package homework3_4;

public class ABC {

    static Object monitor = new Object();
    static volatile char curLet = 'A';

    public static void main(String[] args) {
        new Thread(() -> {

            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor) {
                        while (curLet != 'A') {
                            monitor.wait();
                        }
                        System.out.println("A");
                        curLet = 'B';
                        monitor.notifyAll();
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor) {
                        while (curLet != 'B') {
                            monitor.wait();
                        }
                        System.out.println("B");
                        curLet = 'C';
                        monitor.notifyAll();
                    }
                    
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor) {
                        while (curLet != 'C') {
                            monitor.wait();
                        }
                        System.out.println("C");
                        curLet = 'A';
                        monitor.notifyAll();
                    }

                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
