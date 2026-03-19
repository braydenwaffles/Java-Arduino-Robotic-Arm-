public class Position {
    private int baseServo; //base rotate
    private int joint1Servo; //joint 1
    private int joint2Servo; //joint 2
    private int wristXServo; //wrist x
    private int clawServo; //claw

    //constructor
    public Position(int baseServo, int joint1Servo, int joint2Servo, int wristXServo, int clawServo) {
        this.baseServo = baseServo;
        this.joint1Servo = joint1Servo;
        this.joint2Servo = joint2Servo;
        this.wristXServo = wristXServo;
        this.clawServo = clawServo;
    }

    //individual servos

    //base
    public int getBaseServo() {
        return baseServo;
    }
    public void setBaseServo(int baseServo) {
        this.baseServo = baseServo;
    }

    //joint 1
    public int getJoint1Servo() {
        return joint1Servo;
    }
    public void setJoint1Servo(int joint1Servo) {
        this.joint1Servo = joint1Servo;
    }

    //joint 2
    public int getJoint2Servo() {
        return joint2Servo;
    }
    public void setJoint2Servo(int joint2Servo) {
        this.joint2Servo = joint2Servo;
    }

    //wrist x
    public int getWristXServo() {
        return wristXServo;
    }
    public void setWristXServo(int wristXServo) {
        this.wristXServo = wristXServo;
    }

    //claw
    public int getClawServo() {
        return clawServo;
    }
    public void setClawServo(int clawServo) {
        this.clawServo = clawServo;
    }


    //return joints?

    public String toString() {
        return "Base(Bottom): " + baseServo + " Joint 1: " + joint1Servo + " Joint 2: " + joint2Servo + " Wrist X: " + wristXServo +  " Claw: " + clawServo;
    }

}
