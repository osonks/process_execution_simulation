import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private static Writer instance = null;
    String filename = "output.txt";
    boolean flag = false;
    private FileWriter fw;

    private Writer() throws IOException {
        File file = new File("output.txt");
        if (!file.canRead()) file.setReadable(true);
        if (!file.canWrite()) file.setWritable(true);
        this.fw = new FileWriter(this.filename, false);
    }

    public static Writer getInstance() throws IOException {
        if (instance == null) {
            instance = new Writer();
        }
        return instance;
    }


    public void write(String s) {
        try {
            this.fw.append(s + "\n");
            this.fw.flush();
        }
        catch (IOException ioe) {

            System.err.println("IOException: " + ioe.getMessage());
        }
        this.flag = true;
    }

    public void closeFile() throws IOException {
        this.fw.close();
    }
}
