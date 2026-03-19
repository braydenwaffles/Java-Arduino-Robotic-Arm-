import com.fazecast.jSerialComm.SerialPort;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

//chatgpt code for linking java to
public class Main {

    // REPLACE THIS WITH YOUR ARUDINO PORT WHEN YOU GET IT
    private static final String ARDUINO_PORT_NAME = "COM3"; // or "/dev/ttyUSB0" on Linux/Mac

    public static void main(String[] args) {
        // List all serial ports to help you find your Arduino later
        System.out.println("Available serial ports:");
        SerialPort[] ports = SerialPort.getCommPorts();
        for (SerialPort port : ports) {
            System.out.println(" - " + port.getSystemPortName());
        }

        // Try to open the port (won't work until Arduino connected, but no crash)
        SerialPort arduinoPort = SerialPort.getCommPort(ARDUINO_PORT_NAME);
        arduinoPort.setBaudRate(9600);

        if (!arduinoPort.openPort()) {
            System.out.println("Failed to open port " + ARDUINO_PORT_NAME + ". Check connection later.");
            return;
        }

        System.out.println("Port opened successfully. Ready to send commands!");

        try {
            OutputStream out = arduinoPort.getOutputStream();

            // Example: send a command to move servo 1 to 90 degrees
            String command = "S1:90\n";  // \n means end of command

            System.out.println("Sending command: " + command.trim());
            out.write(command.getBytes(StandardCharsets.US_ASCII));
            out.flush();

            // Wait a bit to make sure Arduino can process (simulate)
            Thread.sleep(500);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            arduinoPort.closePort();
            System.out.println("Port closed.");
        }
    }
}