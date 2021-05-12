package elevators;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.prin("Enter the number of floors in the building:");
        int floors = Integer.parseInt(in.nextLine());
        System.out.print("Enter the number of lifts:");
        int elevators_num = Integer.parseInt(in.nextLine());
        System.out.print("Enter the maximum number of people in one elevator:");
        int max_people = Integer.parseInt(in.nextLine());
        if (elevators_num > 1 && floors > 1 && max_people > 0) {
            Controller controller = new Controller(max_people, elevators_num, floors);
            Generator generator = new Generator(controller);
            Thread generator_thread = new Thread(generator);
            Thread controller_thread = new Thread(controller);
            generator_thread.start();
            controller_thread.start();
        }
        else {
            System.out.print("Smth went wrong...");
        }
    }
}
