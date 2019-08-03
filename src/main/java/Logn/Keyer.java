package Logn;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.input.KeyCode;

public class Keyer {

    private Map<KeyCode, Press> keyMapping;
    private Map<Press, Boolean> keyStates;

    public enum Press {
        up(KeyCode.W), down(KeyCode.S), left(KeyCode.A), right(KeyCode.D);

        private KeyCode defaultKey;

        private Press(KeyCode defaultKey) {
            this.defaultKey = defaultKey;
        }

        public KeyCode getDefaultKey() {
            return defaultKey;
        }
    }

    public Keyer() {
        keyMapping = new HashMap<KeyCode, Press>();
        keyStates = new HashMap<Press, Boolean>();

        for (Press press : Press.values()) {
            keyMapping.put(press.getDefaultKey(), press);
            keyStates.put(press, false);
        }
    }

    public void remapKey(KeyCode keyCode, Press press) {
        keyMapping.put(keyCode, press);
    }

    public void reportKeyPressed(KeyCode key) {
        keyStates.put(keyMapping.get(key), true);
    }

    public void reportKeyReleased(KeyCode key) {
        keyStates.put(keyMapping.get(key), false);
    }

    public Map<Press, Boolean> getKeyStates() {
        return this.keyStates;
    }

}