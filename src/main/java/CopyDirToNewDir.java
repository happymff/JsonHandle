/**
 * Created by mengfeifei on 2017/11/3.
 * 把一个文件夹里的所有文件包括文件夹 一并原样拷贝到另一个目录中；
 *
 * @author sengfeifei
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;


public class CopyDirToNewDir {
    public void IOCopy(String path, String path1) {
        File file = new File(path);
        File file1 = new File(path1);
        if (!file.exists()) {
            System.out.println(file.getName() + "文件不存在");
        } else {
            System.out.println(file.getName());
            System.out.println("存在");
        }
        byte[] b = new byte[(int) file.length()];
        if (file.isFile()) {
            try {
                FileInputStream is = new FileInputStream(file);
                FileOutputStream ps = new FileOutputStream(file1);
                is.read(b);
                ps.write(b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (file.isDirectory()) {
            if (!file.exists())
                file.mkdir();
            String[] list = file.list();
            for (int i = 0; i < list.length; i++) {
                this.IOCopy(path + "/" + list[i], path1 + "/" + list[i]);
            }
        }
    }

    public static void main(String[] args) {

        File fromfile = new File("/Users/mengfeifei/Desktop/picture");// 源文件夹
        File tofile = new File("/Users/mengfeifei/Desktop/temp");// 目标

        new CopyDirToNewDir().IOCopy("/Users/mengfeifei/Desktop/test", "/Users/mengfeifei/Desktop/temp");
        File[] files = tofile.listFiles();
        Arrays.sort(files, new CompratorBySize());
        System.out.println("sort by size: ");
        for (int i = 0; i < files.length; i++) {
            System.out.print(files[i].getName() + " ");
            System.out.println(files[i].length());
        }
    }
}