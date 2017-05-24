/**
 * Created by Maloney on 17-5-24.
 */
import java.io.*;
public class FileDisplay {
    public static void main(String[] args) throws IOException {
        FileInputStream infile = new FileInputStream("/home/maciel/IdeaProjects/IO/File/src/tmp");
        try {
            byte[] b = new byte[128];
            int i = infile.read(b);
            while (i != -1) {
                System.out.print(new String(b, 0, i));
                i = infile.read(b);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            infile.close();
        }
    }
}
