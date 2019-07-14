public class ShiftCipher {
    private final int NUM_CHARS = 256;
    private int key;

    public ShiftCipher(int key) {
        if (key < 0 || key >= NUM_CHARS) {
            throw new RuntimeException("Choose a key in the interval [0 ; 256)");
        }
        this.setKey(key);
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public int[] encrypt(String data) {
        int[] dataEncrypted = new int[data.length()];
        for (int i = 0; i < data.length(); i++) {
            dataEncrypted[i] = (int) ((data.charAt(i) + key) % NUM_CHARS);
        }
        return dataEncrypted;
    }

    public String decrypt(int[] data) {
        String dataDecrypted = "";
        for (int i = 0; i < data.length; i++) {
            dataDecrypted += (int) ((data[i] - key) % NUM_CHARS);
        }
        return dataDecrypted;
    }
}