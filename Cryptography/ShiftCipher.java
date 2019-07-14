public class ShiftCipher {
    private final short NUM_CHARS = 256;
    private short key;

    public ShiftCipher(short key) {
        if (key < 0 || key >= NUM_CHARS) {
            throw new RuntimeException("Choose a key in the interval [0 ; 256)");
        }
        this.setKey(key);
    }

    public void setKey(short key) {
        this.key = key;
    }

    public short getKey() {
        return key;
    }

    public short[] encrypt(String data) {
        short[] dataEncrypted = new short[data.length()];
        for (int i = 0; i < data.length(); i++) {
            dataEncrypted[i] = (short) ((data.charAt(i) + key) % NUM_CHARS);
        }
        return dataEncrypted;
    }

    public String decrypt(short[] data) {
        String dataDecrypted = "";
        for (int i = 0; i < data.length; i++) {
            dataDecrypted += (short) ((data[i] - key) % NUM_CHARS);
        }
        return dataDecrypted;
    }
}