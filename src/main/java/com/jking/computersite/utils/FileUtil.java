package com.jking.computersite.utils;

import com.jking.computersite.enums.CommonEnums;
import com.jking.computersite.exception.MyException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {

    public static String getExtension(MultipartFile file){
        String extension;
        int i = file.getOriginalFilename().lastIndexOf('.');
        if (i > 0){
            extension = file.getOriginalFilename().substring(i+1);
        }else {
            throw new MyException(CommonEnums.NOT_EXTENSION);
        }
        return extension;
    }

    public static void isImage(MultipartFile file){
        String extension = getExtension(file);
        if (!extension.equalsIgnoreCase("PNG")
                && !extension.equalsIgnoreCase("JPG")
                && !extension.equalsIgnoreCase("GIF")
                && !extension.equalsIgnoreCase("JPEG")
                && !extension.equalsIgnoreCase("BMP")){
            throw new MyException(CommonEnums.ONLY_PICTURE);
        }
    }

    public static void isDoc(MultipartFile file){
        String extension = getExtension(file);
        if (!extension.equalsIgnoreCase("DOC")
                && !extension.equalsIgnoreCase("DOCX")){
            throw new MyException(CommonEnums.ONLY_DOC);
        }
    }

    public static boolean saveFile(MultipartFile file,String filePath){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
            BufferedOutputStream out = new BufferedOutputStream(fileOutputStream);
            out.write(file.getBytes());
            out.flush();
            out.close();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static boolean deleteFile(String filePath){
        try {
            File file = new File(filePath);
            file.delete();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
