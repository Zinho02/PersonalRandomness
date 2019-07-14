import java.util.Random;

public class ShiftCipher implements SymmetricCipher<Integer> {
    private Integer key;

    public ShiftCipher() {
        this.key = generateKey();
    }

    @Override
    public Integer generateKey() {
        Random generator = new Random();
        return generator.nextInt(NUM_CHARS);
    }

    @Override
    public int[] encrypt(String data) {
        int[] dataEncrypted = new int[data.length()];
        for (int i = 0; i < data.length(); i++) {
            dataEncrypted[i] = (int) ((data.charAt(i) + this.key) % NUM_CHARS);
        }
        return dataEncrypted;
    }

    @Override
    public String decrypt(int[] data) {
        String dataDecrypted = "";
        for (int i = 0; i < data.length; i++) {
            dataDecrypted += (int) ((data[i] - this.key) % NUM_CHARS);
        }
        return dataDecrypted;
    }
}