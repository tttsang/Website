package com.jking.computersite.controller;

import com.jking.computersite.VO.ResultVO;
import com.jking.computersite.constant.UploadConstant;
import com.jking.computersite.utils.FileUtil;
import com.jking.computersite.utils.PoiUtil;
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
        String extension = FileUtil.isDoc(file);
        String filePath = UploadConstant.IMAGEPREVIEW + UUID.randomUUID() + "." +extension;
        try {
            File[] files = new File(UploadConstant.PUBLIC +UploadConstant.IMAGEPREVIEW).listFiles();
            if (files != null){
                for (File file_del : files){
                    file_del.delete();
                }
            }
            FileUtil.saveFile(file,UploadConstant.PUBLIC + filePath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultVOUtil.success(filePath);
    }

    @ResponseBody
    @PostMapping("/previewDoc")
    public ResultVO previewDoc(@RequestParam("file") MultipartFile file){
        FileUtil.isDoc(file);
        return ResultVOUtil.success(PoiUtil.saveDoc(file));
    }

}
