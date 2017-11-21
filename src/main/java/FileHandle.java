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
        File[] files3 = new File[files1.length+files2.length];

        for(int i =0;i<files1.length;i++){
           files3[i]=files1[i];
        }
        for (int j= 0;j<files2.length; j++){
            files3[j+files1.length]= files2[j];
        }
        for (int k=0; k<files3.length;k++){
            System.out.println(files3[k]);
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        Arrays.sort(files3, new CompratorBySize());
        System.out.println("sort by size: ");
        for (int i = 0; i < files3.length; i++) {
            System.out.print(files3[i].getName() + " ");
            System.out.println(files3[i].length());
            System.out.println();
        }

        }
}
