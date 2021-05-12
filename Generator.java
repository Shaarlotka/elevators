package elevators;
import java.util.Random;

public class Generator implements Runnable {
    private Controller controller;

    Generator(Controller controller) {
        set_controller(controller);
    }

    public void run() {
        while(true) {
            Random random = new Random();
            int number_of_people = random.nextInt(get_controller().get_max_people() + 1);
            for (int i = 0; i < number_of_people; i++) {
                int starting_floor = random.nextInt(controller.get_floors() + 1);
                int target_floor = random.nextInt(controller.get_floors() + 1);
                Passenger passenger = new Passenger(starting_floor, target_floor);
                get_controller().set_passenger(passenger);
            }
            try {
                Thread.sleep(3200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void set_controller(Controller controller) {
        this.controller = controller;
    }

    public Controller get_controller() {
        return controller;
    }
}
