package elevators;
import java.util.ArrayList;

public class Controller implements Runnable {
    private int max_people;
    private int elevators_num;
    private int floors;
    private ArrayList<Elevator> elevators = new ArrayList<>();
    private ArrayList<Passenger> passengers = new ArrayList<>();

    Controller(int max_people, int elevators_num, int floors) {
        set_max_people(max_people);
        set_elevators_num(elevators_num);
        set_floors(floors);
        for (int i = 0; i < get_elevators_num(); i++) {
            Elevator elevator = new Elevator();
            set_elevator(elevator);
        }
    }

    @Override
    public void run() {
        while (true) {
            for (Elevator elevator: get_elevators()) {
                elevator.move();
                if (max_people != elevator.get_passengers().size()) {
                    ArrayList<Passenger> upward_direction = new ArrayList<>();
                    ArrayList<Passenger> downward_direction = new ArrayList<>();
                    int up_num = 0;
                    int down_num = 0;
                    for (Passenger passenger : get_passengers()) {
                        if (passenger.get_starting_floor() == elevator.get_current_floor()) {
                            if (passenger.get_starting_floor() - passenger.get_target_floor())
                                downward_direction.add(passenger);
                            else
                                upward_direction.add(passenger);
                        }
                        else if (passenger.get_starting_floor() < elevator.get_current_floor())
                            down_num++;
                        else
                            up_num++;
                    }

                    // if the elevator is expected to exceed 30% of the carrying capacity,
                    // the standing elevator starts moving
                    if (elevator.get_direction() == 0) {
                        if (down_num > max_people * 0.3)
                            elevator.set_direction(-1);
                        if (up_num > max_people * 0.3)
                            elevator.set_direction(1);
                    }

                    //if the elevator has unloaded all passengers and the load is low, it stops 
                    if (elevator.get_passengers().size() == 0 && down_num < max_people * 0.3 &&
                      up_num < max_people * 0.3)
                        elevator.set_direction(0);

                    //if there are passengers inside and the last floor is reached,
                    //the elevator changes direction to the opposite 
                    if (elevator.get_passengers().size() != 0 && (elevator.get_current_floor() == 1 ||
                      elevator.get_current_floor() == get_floors()))
                        elevator.set_direction(elevator.get_direction() * (-1));
                    
                    if (elevator.get_direction() == -1)
                        entry_passengers(downward_direction, elevator);
                    else
                        entry_passengers(upward_direction, elevator);
                }
                else if (elevator.get_current_floor() == 1 ||
                       elevator.get_current_floor() == get_floors())
                    elevator.set_direction(elevator.get_direction() * (-1));
            }
            try {
                Thread.sleep(3200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void entry_passengers(ArrayList<Passenger> new_passengers, Elevator elevator) {
        while (new_passengers.size() != 0 && elevator.get_passengers().size() < get_max_people()) {
            elevator.set_assenger(new_passengers.get(0));
            get_passengers().remove(new_passengers.get(0));
            new_passengers.remove(0);
        }
    }

    public void set_max_people(int max_people) {
        this.max_people = max_people;
    }

    public void set_elevators_num(int elevators_num) {
        this.elevators_num = elevators_num;
    }

    public void set_elevator(Elevator elevator) {
        this.elevators.add(elevator);
    }

    public void set_floors(int floors) {
        this.floors = floors;
    }

    public void set_passenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    public int get_max_people() {
        return max_people;
    }

    public int get_elevators_num() {
        return elevators_num;
    }

    public int get_floors() {
        return floors;
    }

    public ArrayList<Passenger> get_passengers() {
        return passengers;
    }

    public ArrayList<Elevator> get_elevators() {
        return elevators;
    }
}
