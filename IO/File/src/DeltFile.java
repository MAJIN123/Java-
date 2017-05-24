/**
 * Created by Maloney on 17-5-24.
 */
import java.io.*;

/**
 * 删除指定文件或者目录
 */
public class DeltFile {
    public boolean del(String PathName){
        File file=new File(PathName);
        boolean result=false;
        if(file.isFile()){
            result=file.delete();
        }
        else if(file.isDirectory()){
            File[] childFiles=file.listFiles();
            for(int i=0;i<childFiles.length;i++)
                childFiles[i].delete();
            result=file.delete();
        }
        return result;
    }
    public static void main(String[] args){
        DeltFile df=new DeltFile();
        if(df.del("/home/maciel/IdeaProjects/IO/File/src/tmp")){
            System.out.println("删除成功");
        }
        else
            System.out.println("删除失败");
    }
}
