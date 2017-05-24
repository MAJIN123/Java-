/**
 * Created by Maloney on 17-5-24.
 */
import java.io.*;

/**
 * 文件复制,字节流
 */
public class CopyFile {
    public static void main(String[] args) throws IOException {
        long len = 0l;
        byte[] b = new byte[1024];
        FileInputStream fin = new FileInputStream("/home/maciel/IdeaProjects/IO/File/src/tmp");
        FileOutputStream fout = new FileOutputStream("/home/maciel/IdeaProjects/IO/File/src/tmp1");
        try {
            int i = fin.read(b);
            while (i != -1) {
                fout.write(b, 0, i);
                len += i;
                i = fin.read(b);
            }
            System.out.println("从源文件复制了" + len + "字节到目标文件");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            fin.close();
            fout.close();
        }
    }
}
