import java.util.*;

public class SubstitutionCipher implements SymmetricCipher<Integer[]> {
    private Integer[] key, reverseMap;

    public SubstitutionCipher() {
        this.key = generateKey();
    }

    @Override
    public Integer[] generateKey() {
        Random generator = new Random();
        Integer[] map = new Integer[NUM_CHARS];
        initializeIntVector(map, -1);
        Integer[] reverseMap = new Integer[NUM_CHARS];
        initializeIntVector(reverseMap, -1);
        for (int i = 0; i < NUM_CHARS; i++) {
            int to = generator.nextInt(NUM_CHARS);
            if (reverseMap[to] != -1) {
                to = nearest(reverseMap, to);
            }
            map[i] = to;
            reverseMap[to] = i;
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
            dataDecrypted += (char) ((int) this.reverseMap[data[i]]);
        }
        return dataDecrypted;
    }

    private void initializeIntVector(Integer[] vector, int value) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] = value;
        }
    }

    public int nearest(Integer[] vector, int index) {
        for (int i = 1; i <= vector.length; i++) {
            if (index + i < vector.length) {
                if (vector[index + i] == -1) {
                    return index + i;
                }
            }
            if (index - i >= 0) {
                if (vector[index - i] == -1) {
                    return index - i;
                }
            }
        }
        return -1;
    }

    public String toString(Integer[] vector) {
        String begin = "[" + vector[0];
        String mid = "";
        for (int i = 1; i < vector.length; i++) {
            mid += ", " + vector[i];
        }
        return begin + mid + "]";
    }

    public double randomness() {
        int number = 1000;
        ArrayList<Integer[]> keys = new ArrayList<Integer[]>();
        for (int i = 0; i < number; i++) {
            keys.add(generateKey());
        }
        Integer[] counter = new Integer[NUM_CHARS];
        initializeIntVector(counter, 0);
        for (int i = 0; i < NUM_CHARS; i++) {
            for (int j = 0; j < number - 1; j++) {
                for (int k = j + 1; k < number; k++) {
                    if (keys.get(j)[i] == keys.get(k)[i]) {
                        counter[i]++;
                    }
                }
            }
        }
        double result = 0;
        for (int i = 0; i < NUM_CHARS; i++) {
            result += counter[i]*counter[i];
        }
        result /= NUM_CHARS;
        result = Math.sqrt(result);
        result = result / (number*number);
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SubstitutionCipher substitutionCipher = new SubstitutionCipher();
        System.out.println("Type your message:");
        String message = input.nextLine();
        int[] encryptedData = substitutionCipher.encrypt(message);
        System.out.println("This is your original message:\n" + substitutionCipher.decrypt(encryptedData));

        System.out.printf("%f%%", substitutionCipher.randomness()*100);
        input.close();
    }
}