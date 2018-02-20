package com.jking.computersite.controller;

import com.jking.computersite.enums.CommonEnums;
import com.jking.computersite.enums.UploadEnums;
import com.jking.computersite.service.IndexProfessorService;
import com.jking.computersite.VO.ResultVO;
import com.jking.computersite.constant.UploadConstant;
import com.jking.computersite.entity.IndexProfessor;
import com.jking.computersite.exception.MyException;
import com.jking.computersite.utils.FileUtil;
import com.jking.computersite.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("indexProfessor")
@CrossOrigin
public class IndexProfessorController {

    @Autowired
    private IndexProfessorService indexProfessorService;

    @ResponseBody
    @GetMapping("/")
    public ResultVO getAll(){
        return ResultVOUtil.success(indexProfessorService.getAll());
    }

    @ResponseBody
    @PostMapping("/")
    public ResultVO add(IndexProfessor indexProfessor,MultipartFile file){
        System.out.println(indexProfessor);
        if (indexProfessor.getName().trim().equals("")){
            throw new MyException(CommonEnums.DATA_UNCOMPLETED);
        }
        if (FileUtil.isNull(file)){
            throw new MyException(UploadEnums.FILE_IS_NULL);
        }
        FileUtil.isImage(file);
        String extension = FileUtil.getExtension(file);
        String filePath = UploadConstant.INDEXPROFESSOR + UUID.randomUUID() + "." +extension;
        //保存图片
        FileUtil.saveFile(file,UploadConstant.PUBLIC + filePath);
        //保存到数据库
        indexProfessor.setPictureurl(filePath);
        indexProfessorService.add(indexProfessor);
        return ResultVOUtil.success();
    }

    @ResponseBody
    @PostMapping("/update/{id}")
    public ResultVO update(IndexProfessor indexProfessor,
                           @RequestParam(required = false) MultipartFile file,
                           @PathVariable Integer id){
        System.out.println(indexProfessor);
        IndexProfessor indexProfessor_sql = indexProfessorService.find(id);
        if (indexProfessor.getName()!=null && indexProfessor.getName().trim().equals("")){
            indexProfessor.setName(null);
        }
        if (file!=null && !file.isEmpty()){
            FileUtil.isImage(file);
            String filePath = indexProfessor_sql.getPictureurl();
            FileUtil.deleteFile(UploadConstant.PUBLIC + filePath);
            FileUtil.saveFile(file,UploadConstant.PUBLIC + filePath);
        }
        indexProfessor.setId(id);
        indexProfessor.setPictureurl(indexProfessor_sql.getPictureurl());
        System.out.println(indexProfessor.getName());
        indexProfessorService.update(indexProfessor);
        return ResultVOUtil.success();
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id){
        IndexProfessor indexProfessor_sql = indexProfessorService.find(id);
        FileUtil.deleteFile(UploadConstant.PUBLIC + indexProfessor_sql.getPictureurl());
        indexProfessorService.delete(id);
        return ResultVOUtil.success();
    }

}
