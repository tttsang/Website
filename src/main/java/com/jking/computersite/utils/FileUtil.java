package com.jking.computersite.utils;

import com.jking.computersite.constant.UploadConstant;
import com.jking.computersite.enums.CommonEnums;
import com.jking.computersite.enums.IndexProfessorEums;
import com.jking.computersite.exception.MyException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {

    public static String isImage(MultipartFile file){
        String extension;
        int i = file.getOriginalFilename().lastIndexOf('.');
        if (i > 0) {
            extension = file.getOriginalFilename().substring(i+1);
            if (!extension.equalsIgnoreCase("PNG")
                    && !extension.equalsIgnoreCase("JPG")
                    && !extension.equalsIgnoreCase("GIF")
                    && !extension.equalsIgnoreCase("JPEG")
                    && !extension.equalsIgnoreCase("BMP")){
                throw new MyException(CommonEnums.ONLY_PICTURE);
            }else {
                return extension;
            }
        }else {
            throw new MyException(CommonEnums.ONLY_PICTURE);
        }

    }

    public static String isDoc(MultipartFile file){
        String extension;
        int i = file.getOriginalFilename().lastIndexOf('.');
        if (i > 0) {
            extension = file.getOriginalFilename().substring(i+1);
            if (!extension.equalsIgnoreCase("DOC")
                    && !extension.equalsIgnoreCase("DOCX")){
                throw new MyException(CommonEnums.ONLY_Doc);
            }else {
                return extension;
            }
        }else {
            throw new MyException(CommonEnums.ONLY_Doc);
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

}
