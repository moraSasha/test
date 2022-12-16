import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Semaphore;

class Main {
//control line for git
    public static String getRandom(String[] potion) {
        int rnd = new Random().nextInt(potion.length);

        return potion[rnd];
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Semaphore sem = new Semaphore(1);
        CommonResource res = new CommonResource();
        Shelf shelf = new Shelf();

        String str = ""; // Это берется из файла
        try (FileInputStream fis = new FileInputStream("D:\\masa\\java\\classwork\\2.12\\potions.txt")) {
            String[] potions = str.split("\n");
            System.out.println("Всего есть " + potions.length + " магов(threads)");

            Thread[] wizards = new Thread[potions.length];
            for (int i = 0; i < potions.length; i++) {
                String[] splitRes = potions[i].split(",");
                String name = splitRes[0];
                String potion = splitRes[1];
                System.out.println("Есть маг по имени: " + name + " и зелье: " + potion);
                wizards[i] = new Thread(new CountThread(res, sem, name, potion, shelf));
            }

            for (int i = 0; i < potions.length; i++) {
                wizards[i].start();
            }

            for (int i = 0; i < potions.length; i++) {
                try {
                    wizards[i].join();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            shelf.showShelf();

        }
    }

}
