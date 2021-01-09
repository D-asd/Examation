package jsu.lnn.net;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {


    //判断文件是否存在
    public static void isExist(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
    }

    //新增文件内容
    public static boolean addFile(String path,String[] arrs){
            try{
                isExist(path);
                //写入中文字符时解决中文乱码问题
                FileWriter writer = new FileWriter(path, true);
                for(String arr:arrs){
                	writer.write(arr+"@");
                }
                writer.write("\r\n");
                //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
                writer.close();

                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
    }

    //清空文件内容
    public static void clearFile(String path){
        try{
            isExist(path);
            File file = new File(path);
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    //查询文件内容
    public static List<String> getFile(String path){
        File file = new File(path);
        //存储文件内容
        List<String> list = new ArrayList<String>();
        try{
            isExist(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                list.add(s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

	public static boolean addFile(String path, List<String> returnUserList) {
		// TODO Auto-generated method stub
		 try{
             isExist(path);
             //写入中文字符时解决中文乱码问题
             FileOutputStream fos=new FileOutputStream(new File(path));
             OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
             BufferedWriter  bw=new BufferedWriter(osw);
             
             
             for(String arr:returnUserList){
                 bw.write(arr+"\r");
             }
             //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
             bw.close();
             osw.close();
             fos.close();
             return true;
         }catch (Exception e){
             e.printStackTrace();
             return false;
         }
	}



}
