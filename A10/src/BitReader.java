import java.io.FileInputStream;
import java.io.IOException;

public class BitReader {

    int bitCount;
    byte curr;
    int[] bitArray = new int[8];
    FileInputStream input;

    public BitReader(FileInputStream input) throws IOException {
        this.input = input;
        nextByte();
    }

    private int[] getBits(Byte curr) {
        int[] bits = new int[8];

        for (int i = 0; i < 8; i++) {
            int mask = 0x0080 >> i;
            int bit = curr & mask;
            bit = bit >> (7 - i);
            bits[i] = bit;
        }
        return bits;
    }

    public int nextBit() throws IOException {
        int bit = bitArray[bitCount];
        bitCount++;
        if (bitCount == 8) {
            nextByte();
        }
        return bit;
    }

    private void nextByte() throws IOException {
        curr = (byte) input.read();
        this.bitArray = getBits(curr);
        bitCount = 0;
    }

    /**
     * Reads the first 4 bytes and combines them into an int that represents
     * the size of the decoded file. In doing so, the read pointer is positioned
     * at the start of the huffman tree data.
     *
     * @return Size of file that FileInputStream is pointing at
     * @throws IOException
     */
    public int getFileSize() throws IOException {
        int fileSize = 0;
        for (int i = 0; i < 32; i++) {
            int bit = nextBit();
            fileSize = (fileSize << 1) | bit;
        }
        return fileSize;
    }
}
