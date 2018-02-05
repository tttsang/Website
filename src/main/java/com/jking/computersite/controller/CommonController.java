package com.jking.computersite.controller;

import com.jking.computersite.VO.ResultVO;
import com.jking.computersite.constant.UploadConstant;
import com.jking.computersite.enums.IndexProfessorEums;
import com.jking.computersite.exception.MyException;
import com.jking.computersite.utils.FileUtil;
import com.jking.computersite.utils.ResultVOUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
public class CommonController {

    @ResponseBody
    @PostMapping("/preview")
    public ResultVO preview(@RequestParam("file") MultipartFile file){
        String extension = FileUtil.isImage(file);
        String filePath = UploadConstant.IMAGEPREVIEW + UUID.randomUUID() + "." +extension;
        try {
            File[] files = new File(UploadConstant.TYPE+UploadConstant.IMAGEPREVIEW).listFiles();
            if (files != null){
                for (File file_del : files){
                    file_del.delete();
                }
            }
            FileUtil.saveFile(file,UploadConstant.TYPE + filePath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultVOUtil.success(filePath);
    }

}
