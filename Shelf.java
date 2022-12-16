import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Shelf {
    ArrayList<String> shelf;

    Shelf() {
        this.shelf = new ArrayList<>();
    }

    public void addPotion(String potion) {
        this.shelf.add(potion);
    }

    public void showShelf() {
        System.out.println("Полка: ");
        for (String s : this.shelf) {
            System.out.println(s);

        }

        String text = "Сейчас на полке: \n";
        for (String i : this.shelf) {
            text += i + "\n";
        }

        {
            try (FileOutputStream fos = new FileOutputStream("D://masa//java//classwork//2.12//Shelf.txt")) {
                byte[] buffer = text.getBytes();

                fos.write(buffer, 0, buffer.length);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("The file has been written");
        }
    }
}