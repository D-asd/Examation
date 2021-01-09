package jsu.lnn.net;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {


    //�ж��ļ��Ƿ����
    public static void isExist(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
    }

    //�����ļ�����
    public static boolean addFile(String path,String[] arrs){
            try{
                isExist(path);
                //д�������ַ�ʱ���������������
                FileWriter writer = new FileWriter(path, true);
                for(String arr:arrs){
                	writer.write(arr+"@");
                }
                writer.write("\r\n");
                //ע��رյ��Ⱥ�˳���ȴ򿪵ĺ�رգ���򿪵��ȹر�
                writer.close();

                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
    }

    //����ļ�����
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


    //��ѯ�ļ�����
    public static List<String> getFile(String path){
        File file = new File(path);
        //�洢�ļ�����
        List<String> list = new ArrayList<String>();
        try{
            isExist(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//����һ��BufferedReader������ȡ�ļ�
            String s = null;
            while((s = br.readLine())!=null){//ʹ��readLine������һ�ζ�һ��
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
             //д�������ַ�ʱ���������������
             FileOutputStream fos=new FileOutputStream(new File(path));
             OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
             BufferedWriter  bw=new BufferedWriter(osw);
             
             
             for(String arr:returnUserList){
                 bw.write(arr+"\r");
             }
             //ע��رյ��Ⱥ�˳���ȴ򿪵ĺ�رգ���򿪵��ȹر�
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
