import java.io.File;
import java.util.Arrays;

/**
 * Created by mengfeifei on 2017/11/2.
 */
public class FileHandle {

    public static void main(String[] args) {

        File dir1 = new File("/Users/mengfeifei/Desktop/Personal");

        File dir2 = new File("/Users/mengfeifei/Desktop/appium");

        File[] files1 = dir1.listFiles();
        File[] files2 = dir2.listFiles();
        File[] files3 =null;

        for(int i =0;i<files1.length+files2.length;i++){

        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        Arrays.sort(files1, new CompratorBySize());
        System.out.println("sort by size: ");
        for (int i = 0; i < files1.length; i++) {
            System.out.print(files1[i].getName() + " ");
            System.out.println(files1[i].length());
            System.out.println();
        }

        }
}
