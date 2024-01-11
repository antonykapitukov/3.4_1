public class Main {
    static int MAXVALUE = 2;
    private static final Object lock = new Object();
    private static boolean isFirst = false;
    public static void main(String[] args) {
        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    int i = 0;
                    isFirst = true;
                    while (isFirst) {
                        System.out.println("Эхх");
                        i++;
                        if (i == MAXVALUE) {
                            break;
                        }
                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    int i = 0;
                    isFirst = false;
                    while (!isFirst) {
                        System.out.println("Ура");
                        i++;
                        if (i == MAXVALUE) {
                            break;
                        }
                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        first.start();
        second.start();
    }
}