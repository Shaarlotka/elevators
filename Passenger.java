package elevators;

public class Passenger {
    private int starting_floor;
    private int target_floor;

    Passenger(int starting_floor, int target_floor) {
        set_starting_floor(starting_floor);
        set_target_floor(target_floor);
    }

    public void set_starting_floor(int starting_floor) {
        this.starting_floor = starting_floor;
    }

    public void set_target_floor(int target_floor) {
        this.target_floor = target_floor;
    }

    public int get_starting_floor() {
        return starting_floor;
    }

    public int get_target_floor() {
        return target_floor;
    }
}
