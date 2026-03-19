public final class SerialTranslator {

    //translator
    public static String positionToSerial1(Position p) {
        String translated = ("<" + p.getBaseServo() + "," + p.getJoint1Servo() + "," + p.getJoint2Servo() + "," + p.getWristXServo() + "," + p.getWristYServo() + "," + p.getClawServo() + ">");
        return translated;
    }

    public static String positionToSerial2(Position p) {
        String translated = ("B" + p.getBaseServo() + " J1" + p.getJoint1Servo() + " J2" + p.getJoint2Servo() + " X" + p.getWristXServo() + " Y" + p.getWristYServo() + " C" + p.getClawServo() + "\n");
        return translated;
    }
}
