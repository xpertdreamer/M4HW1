//если потоки не синхронизированы, результат может быть разный , тк 2 потока одновременно обращаются к одному ресурсу
//возникает race condition
//чтобы это предотвратить нужно синхронизировать методы класса Counter


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread thread1 = new Thread(new Increment(counter));
        Thread thread2 = new Thread(new Increment(counter));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Count: " + counter.getValue());
    }
}

class Counter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int getValue() {
        return count;
    }
}


class Increment implements Runnable {
    private Counter counter;

    public Increment(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}