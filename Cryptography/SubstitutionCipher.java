import java.util.*;

public class SubstitutionCipher implements SymmetricCipher<Integer[]> {
    private Integer[] key, reverseMap;

    public SubstitutionCipher() {
        this.key = generateKey();
    }

    // Not yet done.
    @Override
    public Integer[] generateKey() {
        Random generator = new Random();
        Integer[] map = new Integer[NUM_CHARS];
        initializeIntVector(map, -1);
        Integer[] reverseMap = new Integer[NUM_CHARS];
        initializeIntVector(reverseMap, -1);
        for (int i = 0; i < NUM_CHARS; i++) {
            int counter = 0;
            int to = generator.nextInt(NUM_CHARS - i);
            for (Integer value : reverseMap) {
                if (value != -1) {
                    if (to < value) {
                        counter++;
                    }
                }
            }
            map[i] = to + counter;
            reverseMap[to+counter] = i;
        }
        this.key = map;
        this.reverseMap = reverseMap;
        return this.key;
    }

    @Override
    public int[] encrypt(String data) {
        int[] dataEncrypted = new int[data.length()];
        for (int i = 0; i < data.length(); i++) {
            dataEncrypted[i] = (int) this.key[data.charAt(i)];
        }
        return dataEncrypted;
    }

    @Override
    public String decrypt(int[] data) {
        String dataDecrypted = "";
        for (int i = 0; i < data.length; i++) {
            dataDecrypted += (int) this.reverseMap[data[i]];
        }
        return dataDecrypted;
    }

    private void initializeIntVector(Integer[] vector, int value) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] = value;
        }
    }

    public String toString(Integer[] vector) {
        String begin = "[" + vector[0];
        String mid = "";
        for (int i = 1; i < vector.length; i++) {
            mid += ", " + vector[i];
        }
        return begin + mid + "]";
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SubstitutionCipher substitutionCipher = new SubstitutionCipher();
        System.out.println(substitutionCipher.toString(substitutionCipher.key));
        System.out.println("\n\n"+substitutionCipher.toString(substitutionCipher.reverseMap));
        //System.out.println("Type your message:");
        //String message = input.next();
        //int[] encryptedData = substitutionCipher.encrypt(message);
        //System.out.println("This is your original message:\n" + substitutionCipher.decrypt(encryptedData));
    }
}