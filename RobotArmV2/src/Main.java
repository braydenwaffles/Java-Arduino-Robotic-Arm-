//import java.io.Serial;
import java.util.Scanner;
import java.io.Serial;
import java.lang.Thread;
//import java.util.HashMap;

//where CLI logic will go
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        //requires 6 ints based on degrees
        ArmController ArmController = new ArmController();
        PositionSaver PositionSaver = new PositionSaver();
        //SerialTranslator SerialTranslator = new SerialTranslator(); because the class is final
        SerialComm SerialComm = new SerialComm();


        Position basePosition = new Position(90, 90, 90, 90, 90);
        //Position basePositionMiniArm = new Position(90, 90 ,90 ,90, 90);
        Position currentPosition = new Position(0, 0, 0, 0, 0);


        //commandlist
        commandList();

        while(true) {
            System.out.print("\nEnter command: ");
            String command = scanner.nextLine();

            //utility

            if (command.equals("stop")) {
                SerialComm.close();
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

                /* in "<>" format
                System.out.println(SerialTranslator.positionToSerial1(currentPosition)); //debug
                SerialComm.sendCommand(SerialTranslator.positionToSerial1(currentPosition));

                // in "S1:90" format
                System.out.println(SerialTranslator.positionToSerial2(currentPosition)); //debug
                SerialComm.sendCommand(SerialTranslator.positionToSerial2(currentPosition));
                */

                // in A90$ format
                System.out.println(SerialTranslator.positionToSerialMiniArm(currentPosition)); //debug
                SerialComm.sendCommand(SerialTranslator.positionToSerialMiniArm(currentPosition));

                //BCDEF
                //System.out.println(SerialTranslator.MiniArmSerial1(currentPosition)); //debug
                //SerialComm.sendCommand(SerialTranslator.MiniArmSerial1(currentPosition));

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
                    System.out.println("Returning to current position");
                }

                
            } else if (command.equals("course")) {
                SerialComm.sendCommand(SerialTranslator.positionToSerialMiniArm(basePosition));
                Thread.sleep(1000);

                ArmController.moveTo(currentPosition, ArmController.getShortestMiddlePosition(currentPosition, PositionSaver.load("saved1")));
                SerialComm.sendCommand(SerialTranslator.positionToSerialMiniArm(currentPosition));
                ArmController.moveTo(currentPosition, PositionSaver.load("saved1"));
                SerialComm.sendCommand(SerialTranslator.positionToSerialMiniArm(currentPosition));
                Thread.sleep(500);

                ArmController.moveTo(currentPosition, ArmController.getShortestMiddlePosition(currentPosition, PositionSaver.load("saved2")));
                SerialComm.sendCommand(SerialTranslator.positionToSerialMiniArm(currentPosition));
                ArmController.moveTo(currentPosition, PositionSaver.load("saved2"));
                SerialComm.sendCommand(SerialTranslator.positionToSerialMiniArm(currentPosition));
                Thread.sleep(500);

                ArmController.moveTo(currentPosition, ArmController.getShortestMiddlePosition(currentPosition, PositionSaver.load("saved3")));
                SerialComm.sendCommand(SerialTranslator.positionToSerialMiniArm(currentPosition));
                ArmController.moveTo(currentPosition, PositionSaver.load("saved3"));
                SerialComm.sendCommand(SerialTranslator.positionToSerialMiniArm(currentPosition));
                Thread.sleep(500);

                ArmController.moveTo(currentPosition, ArmController.getShortestMiddlePosition(currentPosition, PositionSaver.load("saved4")));
                SerialComm.sendCommand(SerialTranslator.positionToSerialMiniArm(currentPosition));
                ArmController.moveTo(currentPosition, PositionSaver.load("saved4"));
                SerialComm.sendCommand(SerialTranslator.positionToSerialMiniArm(currentPosition));
                Thread.sleep(500);

                ArmController.moveTo(currentPosition, ArmController.getShortestMiddlePosition(currentPosition, PositionSaver.load("saved5")));
                SerialComm.sendCommand(SerialTranslator.positionToSerialMiniArm(currentPosition));
                ArmController.moveTo(currentPosition, PositionSaver.load("saved5"));
                SerialComm.sendCommand(SerialTranslator.positionToSerialMiniArm(currentPosition));
                Thread.sleep(500);

                SerialComm.sendCommand(SerialTranslator.positionToSerialMiniArm(basePosition));

                //wave
                //Current Position: Base(Bottom): 90 Joint 1: 150 Joint 2: 100 Wrist X: 90 Claw: 100
                //Current Position: Base(Bottom): 90 Joint 1: 80 Joint 2: 75 Wrist X: 90 Claw: 100
            
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
