import java.util.Scanner;
//import java.util.HashMap;

//where CLI logic will go
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        //requires 6 ints based on degrees
        ArmController ArmController = new ArmController();
        PositionSaver PositionSaver = new PositionSaver();
        //SerialTranslator SerialTranslator = new SerialTranslator();


        Position basePosition = new Position(0, 0, 0, 0, 0, 0);
        Position currentPosition = new Position(0, 0, 0, 0, 0, 0);


        //commandlist
        commandList();

        while(true) {
            System.out.print("\nEnter command: ");
            String command = scanner.nextLine();

            //utility

            if (command.equals("stop")) {
                scanner.close();
                break;

            } else if (command.equals("reset")) {
                System.out.println(ArmController.getCurrentPosition(currentPosition));
                ArmController.resetToBase(currentPosition, basePosition);
                System.out.println(ArmController.getCurrentPosition(currentPosition));

            } else if (command.equals("current")) {
                System.out.println(ArmController.getCurrentPosition(currentPosition));

            } else if (command.equals("cmdlist")) {
                commandList();

            //moving

            } else if (command.equals("manual")) {
                System.out.println(ArmController.getCurrentPosition(currentPosition));
                ArmController.manualMove(currentPosition);
                System.out.println(ArmController.getCurrentPosition(currentPosition));

            } else if (command.equals("move")) {
                System.out.println(ArmController.getCurrentPosition(currentPosition));
                ArmController.moveTo(currentPosition, ArmController.getNewPosition(scanner));
                System.out.println(ArmController.getCurrentPosition(currentPosition));

            } else if (command.equals("commit")) {
                System.out.println("Moving to current position: ");
                System.out.println(SerialTranslator.positionToSerial1(currentPosition));
                System.out.println(SerialTranslator.positionToSerial2(currentPosition));

            //saving and loading
            
            } else if (command.equals("save")) {
                System.out.println(ArmController.getCurrentPosition(currentPosition));
                PositionSaver.savePosition(currentPosition);
                System.out.println(ArmController.getCurrentPosition(currentPosition));

            } else if (command.equals("load")) {
                System.out.println(ArmController.getCurrentPosition(currentPosition));
                System.out.println("Key to load:");
                String loadKey = scanner.nextLine();
                PositionSaver.load(loadKey);
                //PositionSaver.loadString(loadKey); debug

                System.out.println("Would you like to update the current position?");
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("yes")) {
                    ArmController.moveTo(currentPosition, PositionSaver.load(loadKey));
                    System.out.println(ArmController.getCurrentPosition(currentPosition));
                } else {
                    
                }
            }  


        }

    }
    public static void commandList() {
        System.out.println("\nCommands:");

        System.out.println("\nMoving:");
        System.out.println("manual - input which servo and how many degrees to rotate, does not move the arm");
        System.out.println("move - input 6 ints for the 6 servos and how many degrees to move each, does not move the arm");
        System.out.println("commit - commits the current position in java to the arm, moves the arm");
        
        System.out.println("\nSaving: ");
        System.out.println("save - saves the state of all current servos");
        System.out.println("load - moves the arm to the saved state");

        System.out.println("\nUtility: ");
        System.out.println("cmdlist - lists all of the commands again");
        System.out.println("current - returns the current state of all servos");
        System.out.println("reset - resets the state of all servos to their original positions");
        System.out.println("stop - stops the program");
    }
}
