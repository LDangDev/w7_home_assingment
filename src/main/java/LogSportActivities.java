import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class LogSportActivities {
    // Class to hold activity info
    static class Activity {
        String sportName;
        double hours;
        LocalDateTime dateTime;

        Activity(String sportName, double hours, LocalDateTime dateTime) {
            this.sportName = sportName;
            this.hours = hours;
            this.dateTime = dateTime;
        }

        // Make activity info into a string
        public String toString() {
            return dateTime.toString().substring(0, 16) + " - " + sportName + ": " + hours + " hours";
        }
    }

    private ArrayList<Activity> myActivities;
    private Scanner input;

    public LogSportActivities() {
        myActivities = new ArrayList<>();
        input = new Scanner(System.in);
    }

    // Main program loop
    public void start() {
        boolean keepGoing = true;
        while (keepGoing) {
            showMenu();
            int choice = getChoice();

            if (choice == 1) {
                addNewActivity();
            }
            else if (choice == 2) {
                showAllActivities();
            }
            else if (choice == 3) {
                showWeekTotal();
            }
            else if (choice == 4) {
                System.out.println("See ya!");
                input.close();
                keepGoing = false;
            }
            else {
                System.out.println("Oops, wrong number! Try again.");
            }
        }
    }

    // Show the options
    private void showMenu() {
        System.out.println("\nSports Stuff");
        System.out.println("1 - Add activity");
        System.out.println("2 - See all activities");
        System.out.println("3 - Get weekly total");
        System.out.println("4 - Quit");
        System.out.print("What do you want to do? ");
    }

    // Get user's menu choice
    private int getChoice() {
        String line = input.nextLine();
        try {
            return Integer.parseInt(line);
        } catch (Exception e) {
            return 0;
        }
    }

    // Add a new sports activity
    private void addNewActivity() {
        System.out.print("What's the sport? ");
        String name = input.nextLine();

        System.out.print("How many hours? ");
        String hoursStr = input.nextLine();
        double hours = 0;
        try {
            hours = Double.parseDouble(hoursStr);
            if (hours <= 0) {
                System.out.println("Hours should be more than 0!");
                return;
            }
        } catch (Exception e) {
            System.out.println("That's not a good number!");
            return;
        }

        myActivities.add(new Activity(name, hours, LocalDateTime.now()));
        System.out.println("Added it!");
    }

    // Show everything we've logged
    private void showAllActivities() {
        if (myActivities.size() == 0) {
            System.out.println("Nothing here yet!");
            return;
        }

        System.out.println("\nYour Activities:");
        for (int i = 0; i < myActivities.size(); i++) {
            System.out.println((i + 1) + ". " + myActivities.get(i));
        }
    }

    // Add up hours from last week
    private void showWeekTotal() {
        if (myActivities.size() == 0) {
            System.out.println("Nothing to add up!");
            return;
        }

        LocalDateTime rightNow = LocalDateTime.now();
        LocalDateTime lastWeek = rightNow.minusDays(7);
        double total = 0;

        // Go through all activities
        for (Activity a : myActivities) {
            if (a.dateTime.isAfter(lastWeek) && a.dateTime.isBefore(rightNow)) {
                total += a.hours;
            }
        }

        System.out.println("This week's total: " + total + " hours");
    }

    public ArrayList<Activity> getActivities() {
        return myActivities;
    }


    // Start the program
    public static void main(String[] args) {
        LogSportActivities myTracker = new LogSportActivities();
        myTracker.start();
    }
}