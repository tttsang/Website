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
@CrossOrigin
public class CommonController {

    @ResponseBody
    @PostMapping("/previewPic")
    public ResultVO preview(@RequestParam("file") MultipartFile file){
        FileUtil.isImage(file);
        String filePath = UploadConstant.IMAGEPREVIEW + file.getOriginalFilename();
        try {
            FileUtil.delAllFile(UploadConstant.PUBLIC + UploadConstant.IMAGEPREVIEW);
            FileUtil.saveFile(file,UploadConstant.PUBLIC + filePath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultVOUtil.success(filePath);
    }

    @ResponseBody
    @PostMapping("/previewDoc")
    public ResultVO previewDoc(@RequestParam("file") MultipartFile file){
        return ResultVOUtil.success(PoiUtil.previewDoc(file));
    }

}
