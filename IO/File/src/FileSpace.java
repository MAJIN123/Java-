/**
 * Created by Maloney on 17-5-24.
 */
import java.io.*;

/**
 * 获取指定　目录下或文件的大小
 */
public class FileSpace {
    public static void main(String args[]) {
        System.out.print(getTotal("/home/maciel/torch"));
    }

    public static long getTotal(String PathName) {
        long total = 0;
        File myfile = new File(PathName);
        if (myfile.exists()) {
            return myfile.length();
        } else {
            String[] childFiles = myfile.list();
            for (int i = 0; i < childFiles.length; i++)
                total += getTotal(PathName + '/' + childFiles[i]);
        }
        return total;
    }
}
