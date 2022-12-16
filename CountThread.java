import java.util.concurrent.Semaphore;

import javax.sql.CommonDataSource;

class CountThread implements Runnable {

    CommonResource res;
    Semaphore sem;
    String name;
    private String potion;
    Shelf shelf;

    CountThread(CommonResource res, Semaphore sem, String name, String potion, Shelf shelf) {
        this.res = res;
        this.sem = sem;
        this.name = name;
        this.potion = potion;
        this.shelf = shelf;
    }

    public void run() {

        try {
            System.out.println(name + "ждёт доступа к котлу");
            sem.acquire();
            System.out.println(name + "получает доступ к котлу");
            res.x = 1;
            System.out.println(name + "начинает варить зелье: " + potion);
            for (int i = 1; i < 5; i++) {
                System.out.println(this.name + ": " + res.x);
                res.x++;
                Thread.sleep(100);
            }

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(name + "заканчивает варить зелье: " + potion);
        System.out.println(name + "освобождает доступ к котлу");
        shelf.addPotion(potion);
        sem.release();

    }

}