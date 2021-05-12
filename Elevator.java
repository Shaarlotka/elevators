package elevators;
import java.util.ArrayList;

public class Elevator {
    private int count = 1;
    private int number;
    private int current_floor = 0;
    private int direction = 0;
    private ArrayList<Passenger> passengers = new ArrayList<>();

    Elevator() {
        set_number(count);
        count++;
    }

    public void move() {
        set_current_floor(get_current_floor() + get_direction());
        exit_passengers();
    }

    public void exit_passengers() {
        for (Passenger passenger : get_passengers()) {
            if (get_current_floor() == passenger.get_target_floor()) {
                get_passengers().remove(passenger);
            }
        }
    }

    public void set_number(int number) {
        this.number = number;
    }

    public void set_current_floor(int current_floor) {
        this.current_floor = current_floor;
    }

    public void set_direction(int direction) {
        this.direction = direction;
    }

    public void set_passengers(Passenger passenger) {
        this.passengers.add(passenger);
    }

    public int get_number() {
        return number;
    }

    public int get_current_floor() {
        return current_floor;
    }

    public int get_direction() {
        return direction;
    }

    public ArrayList<Passenger> get_passengers() {
        return passengers;
    }
}

