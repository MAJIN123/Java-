/**
 * Created by Maloney on 17-5-24.
 */
import java.io.*;

/**
 * 显示文件信息,有文件就读信息，没有就创建新的文件
 */
public class FileDemo {
    public static void main(String args[]){
        File myfile=new File("/home/maciel/IdeaProjects/IO/File/src/tmp");
        if(myfile.exists()){
            System.out.println("文件名："+myfile.getName());
            System.out.println("文件名："+myfile.getParent());
        }
        else
            try{
            System.out.println("Sorry,can not find the file ");
            myfile.createNewFile();
            System.out.println("Creat a new file");
            }catch (IOException e){
            System.out.println("Exception:"+e);
            }
    }
}
