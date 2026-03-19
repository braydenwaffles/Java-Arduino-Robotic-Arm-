import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

//chatgpt carried this part since this is my first time learning this type of stuff
public class SerialComm {

    private SerialPort arduinoPort;
    private OutputStream outputStream;

    //constructor: sets up and opens serial connection
    public SerialComm() {
        // List all serial ports to help you find your Arduino later
        System.out.println("Available serial ports:");
        SerialPort[] ports = SerialPort.getCommPorts();
        for (SerialPort port : ports) {
            System.out.println(" - " + port.getSystemPortName());
        }

        // Try to open the port (won't work until Arduino connected, but no crash)
        //REPLACE ALL OF THE "ARDUINO_PORT_NAME" BLOCKS WHEN THE ARDUINO PORT IS FIGURED OUT
        arduinoPort = SerialPort.getCommPort("COM5");
        arduinoPort.setBaudRate(9600);
        //arduinoPort.setComPortParameters(9600, 8, 1, 0, false);

        if (arduinoPort.openPort()) {
            try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
            System.out.println("Port opened successfully. Ready to send commands!");
            this.outputStream = arduinoPort.getOutputStream();
        } else {
            System.out.println("Failed to open port COM5. Check connection and try again.");
        }
    }
    // Send a string command over the serial port with 
    public void sendCommand(String command) {
        try {
            if (outputStream != null) {
                //depending on formatin for arduino, you might have to add + "\n" or + ">" something like that
                outputStream.write((command).getBytes(StandardCharsets.US_ASCII));
                outputStream.flush();
            } else {
                System.out.println("Output stream is not initialized, check hardware connection, port name, and constructor");
            }
        } catch (IOException e) {
            System.out.println("Error sending command: " + e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public void close() {
        if (arduinoPort != null && arduinoPort.isOpen()) {
            arduinoPort.closePort();
            System.out.println("Serial port closed.");
        }
    }
    
}
