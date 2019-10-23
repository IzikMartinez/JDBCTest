import java.util.Scanner;

public class InsertTest {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        jdbc test = new jdbc();

        System.out.println("Current Database entries\n");
        test.query();

        System.out.println("Enter primary key");
        int index = read.nextInt();

        System.out.println("Enter word to insert");
        String word = read.next();

        int id = test.insert(index, word);
        System.out.println("New entry with id " + id + " has been made\nNew database entries");

        test.query();
    }
}
