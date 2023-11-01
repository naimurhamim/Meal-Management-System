import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;


class MealBazar {
    private int meal, cost, totalMeal, totalCost;

    MealBazar() {
        int couns = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter today's meal: ");
        try {
            meal = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }
        System.out.print("Enter today's cost: ");
        try {
            cost = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }

        try (FileWriter writer = new FileWriter("Meal.txt", true)) {
            writer.write(meal + " " + cost + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        couns++;
        System.out.println("You entered meal and cost " + couns + " times");
    }

    void display() {
        totalMeal = 0;
        totalCost = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("Meal.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(" ");
                int meal1 = Integer.parseInt(values[0]);
                int cost1 = Integer.parseInt(values[1]);
                totalMeal += meal1;
                totalCost += cost1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Total Meal: " + totalMeal);
        System.out.println("Total Cost: " + totalCost);
        System.out.println("Cost per meal: " + (double) totalCost / totalMeal);
    }
}

class MemberInfo {
    String name;

    MemberInfo(String name) {
        this.name = name;
    }

    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Occupation: Student");
        System.out.println("Phone Number: " + getPhoneNumber());
        System.out.println("Home District: " + getHomeDistrict());
    }

    String getPhoneNumber() {
        switch (name) {
            case "Naimur":
                return "01234567895";
            case "Shourav":
                return "015335469727";
            case "Zobayer":
                return "01254896939";
            case "Rohan":
                return "01254896939";
            default:
                return "";
        }
    }

    String getHomeDistrict() {
        switch (name) {
            case "Naimur":
                return "Cumilla";
            case "Shourav":
                return "Feni";
            case "Zobayer":
                return "Tongi";
            case "Rohan":
                return "Rajshahi";
            default:
                return "";
        }
    }
}



public class Main {
    public static void main(String[] args) {
        String password = "BDU2023";
        String inputPassword;

        System.out.println("\n\n\t\t\t\tMEAL MANAGEMENT SYSTEM");
        System.out.println("\t\t\t ^^^^^ ^^^^^ ^^^^^^^^^^ ^^^^^^^^^");

        do {
            inputPassword = JOptionPane.showInputDialog("Enter the password:");

            if (inputPassword == null) {
                System.exit(0);
            } else if (inputPassword.equals(password)) {
                String[] options = { "Exit", "View Member Info", "Enter Meal Cost" };
                int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                switch (choice) {
                    case 0: // Exit
                        System.exit(0);
                        break;
                    case 1: // View Member Info
                        int memberChoice = Integer.parseInt(JOptionPane.showInputDialog("Select a member (1-4):\n1. Naimur\n2. Shourav\n3. Zobayer\n4. Rohan"));
                        String memberName = "";
                        switch (memberChoice) {
                            case 1:
                                memberName = "Naimur";
                                break;
                            case 2:
                                memberName = "Shourav";
                                break;
                            case 3:
                                memberName = "Zobayer";
                                break;
                            case 4:
                                memberName = "Rohan";
                                break;
                        }

                        MemberInfo memberInfo = new MemberInfo(memberName);
                        memberInfo.displayInfo();
                        break;
                    case 2: // Enter Meal Cost
                        MealBazar mealBazar = new MealBazar();
                        mealBazar.display();
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect password. Try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } while (true);
    }
}

