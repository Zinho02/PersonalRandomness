public interface SymmetricCipher<T> {
    public final int NUM_CHARS = 256;

    public T generateKey();

    public int[] encrypt(String data);

    public String decrypt(int[] data);
}