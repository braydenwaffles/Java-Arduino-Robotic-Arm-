public final class SerialTranslator {

    //translator
    public static String positionToSerial1(Position p) {
        String translated = ("<" + p.getBaseServo() + "," + p.getJoint1Servo() + "," + p.getJoint2Servo() + "," + p.getWristXServo() + "," + p.getClawServo() + ">");
        return translated;
    }

    public static String positionToSerial2(Position p) {
        String translated = ("B" + p.getBaseServo() + " J1" + p.getJoint1Servo() + " J2" + p.getJoint2Servo() + " X" + p.getWristXServo() + " C" + p.getClawServo() + "\n");
        return translated;
    }

    public static String positionToSerialMiniArm(Position p) {
        String translated = (
            "A" + p.getClawServo() + "$" +
            "B" + p.getWristXServo() + "$" +
            "C" + p.getJoint2Servo() + "$" +
            "D" + p.getJoint1Servo() + "$" +
            "E" + p.getBaseServo() + "$"
        );
        return translated;
    }

    public static String MiniArmSerial1(Position p) {
        String translated = (
            "B" + p.getClawServo() + "$" +
            "C" + p.getWristXServo() + "$" +
            "D" + p.getJoint2Servo() + "$" +
            "E" + p.getJoint1Servo() + "$" +
            "F" + p.getBaseServo() + "$"
        );
        return translated;
    }

    
}
