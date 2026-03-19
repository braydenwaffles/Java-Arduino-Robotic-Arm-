import java.util.HashMap;

public class PositionSaver {
    private HashMap<String, Position> savedPositions;
    private int saveCounter = 1;
    ArmController ArmController = new ArmController();

    public PositionSaver() { // constructor
        this.savedPositions = new HashMap<>();
        this.saveCounter = 1;
    }

    public String createKey(int saveNumber) {
        return("saved" + saveNumber);
    }

    //use hashmap of <String, Position (object)> for saved position
    public void savePosition(Position currentPosition) {
        //new key
        String newKey = createKey(saveCounter);
        System.out.println("Key for saved position: " + newKey);

        //i like to move it move it
        Position savedPosition = new Position(0, 0, 0, 0, 0);
        ArmController.moveTo(savedPosition, currentPosition);

        //saving position
        savedPositions.put(newKey, savedPosition);
        System.out.println("Saved Position: " + currentPosition.toString());
        saveCounter++;
    }

    public Position load(String key) {
        Position retrieved = savedPositions.get(key);
        if (retrieved != null) {
            System.out.println("Loaded Position: " + retrieved);
        } else {
            System.out.println("No saved position for key: " + key);
        }
        return retrieved;
    }

    public String loadString(String key) {
        String retrieved = savedPositions.get(key).toString();
        System.out.println(retrieved); // debug
        return(retrieved);

    }

}
