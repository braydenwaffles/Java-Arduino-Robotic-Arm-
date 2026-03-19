import java.util.Scanner;

public class ArmController {


    //constructor
    public ArmController() {

    }


    //functions that use print instead of updating which will be later
    public void manualMove(Position currentPosition) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which servo would you like to move?");
        System.out.println("Options: base, joint1, joint2, wristX, wristY, claw");
        String servoChoice = scanner.nextLine();

        if (servoChoice.equalsIgnoreCase("base")) {
            int degrees = getValidDegrees("base");
            currentPosition.setBaseServo(degrees);
            
        }

        else if (servoChoice.equalsIgnoreCase("joint1")) {
            int degrees = getValidDegrees("joint1");
            currentPosition.setJoint1Servo(degrees);

        }

        else if (servoChoice.equalsIgnoreCase("joint2")) {
            int degrees = getValidDegrees("joint2");
            currentPosition.setJoint2Servo(degrees);
            
        }

        else if (servoChoice.equalsIgnoreCase("wristX")) {
            int degrees = getValidDegrees("wristX");
            currentPosition.setWristXServo(degrees);
            
        }

        else if (servoChoice.equalsIgnoreCase("wristY")) {
            int degrees = getValidDegrees("wristY");
            currentPosition.setWristYServo(degrees);
            
        }

        else if (servoChoice.equalsIgnoreCase("claw")) {
            int degrees = getValidDegrees("claw");
            currentPosition.setClawServo(degrees);
            
        }

        else {
            System.out.println("Invalid servo choice.");
        }
    }

    public Position getNewPosition(Scanner scanner) {

        int base = getValidDegrees("base");

        int joint1 = getValidDegrees("joint1");

        int joint2 = getValidDegrees("joint2");

        int wristX = getValidDegrees("wristX");

        int wristY = getValidDegrees("wristY");

        int claw = getValidDegrees("claw");

        // Create new position
        Position desiredPosition = new Position(base, joint1, joint2, wristX, wristY, claw);
        return desiredPosition;
    }

    public void moveTo(Position currentPosition, Position desiredPosition) {
        currentPosition.setBaseServo(desiredPosition.getBaseServo());
        currentPosition.setJoint1Servo(desiredPosition.getJoint1Servo());
        currentPosition.setJoint2Servo(desiredPosition.getJoint2Servo());
        currentPosition.setWristXServo(desiredPosition.getWristXServo());
        currentPosition.setWristYServo(desiredPosition.getWristYServo());
        currentPosition.setClawServo(desiredPosition.getClawServo());
    }

    public void resetToBase(Position currentPosition, Position basePosition) {
        //basePosition should be set at the very start of the program to log the very start of the arm's movement
        System.out.println("Base position: " + basePosition.toString());
        currentPosition.setBaseServo(basePosition.getBaseServo());
        currentPosition.setJoint1Servo(basePosition.getJoint1Servo());
        currentPosition.setJoint2Servo(basePosition.getJoint2Servo());
        currentPosition.setWristXServo(basePosition.getWristXServo());
        currentPosition.setWristYServo(basePosition.getWristYServo());
        currentPosition.setClawServo(basePosition.getClawServo());
    }

    public String getCurrentPosition(Position currentPosition) {
        return("Current Position: " + currentPosition.toString());
    }


    public int getValidDegrees(String servoName) {
    Scanner scanner = new Scanner(System.in);
    int degrees = -1;
    while (true) {
        System.out.print("Enter degrees for " + servoName + " (0-180): ");
        if (scanner.hasNextInt()) {
            degrees = scanner.nextInt();
            if (degrees >= 0 && degrees <= 180) {
                return degrees;
            } else {
                System.out.println("Invalid input. Value must be between 0 and 180.");
            }
        } else {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next(); // discard invalid input
        }
    }
}
}
