import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class JonA10 {

    public static void main(String[] args) throws IOException {
        //a file must be specified
        if (args.length == 0) {
            dieGracefully();
        }

        String fileName = args[0];
        //specified file must end in .huff
        if (!fileName.endsWith(".huff")) {
            dieGracefully();
        }

        //file must exist
        File huffFile = new File(fileName);
        if (!huffFile.isFile()) {
            throw new FileNotFoundException("File not found.");
        }

        try {
            FileInputStream in = new FileInputStream(huffFile);
            FileOutputStream out = new FileOutputStream(new File(fileName.substring(0, fileName.lastIndexOf(".huff"))));
            Huffman.decompress(in, out);
        } catch (IOException e) {
            System.out.println("Could not read from file: " + e.getMessage());
        }


    }

    private static void dieGracefully() {
        System.err.println("Usage: java JonA10 filename.extension.huff");
        System.exit(1);
    }
}
