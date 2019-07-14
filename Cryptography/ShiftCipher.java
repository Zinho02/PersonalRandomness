public class ShiftCipher {
    private int modulo, key;

    public ShiftCipher(int modulo, int key) {
        if (key < 0 || key >= modulo) {
            throw new RuntimeException("Choose a key in the interval [0 ; modulo)");
        }
        this.setKey(key);
        this.setModulo(modulo);
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setModulo(int modulo) {
        this.modulo = modulo;
    }

    public int getModulo() {
        return modulo;
    }

    public long[] encrypt(String data) {
        long[] dataEncrypted = new long[data.length()];
        for (int i = 0; i < data.length(); i++) {
            dataEncrypted[i] = (long) ((data.charAt(i) + key) % modulo);
        }
        return dataEncrypted;
    }

    public String decrypt(long[] data) {
        String dataDecrypted = "";
        for (int i = 0; i < data.length; i++) {
            dataDecrypted += (char) ((data[i] - key) % modulo);
        }
        return dataDecrypted;
    }
}