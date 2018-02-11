package com.jking.computersite.controller;

import com.jking.computersite.Service.IndexProfessorService;
import com.jking.computersite.VO.ResultVO;
import com.jking.computersite.constant.UploadConstant;
import com.jking.computersite.entity.IndexProfessor;
import com.jking.computersite.enums.IndexProfessorEums;
import com.jking.computersite.exception.MyException;
import com.jking.computersite.utils.FileUtil;
import com.jking.computersite.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("indexProfessor")
public class IndexProfessorController {

    @Autowired
    private IndexProfessorService indexProfessorService;

    @ResponseBody
    @GetMapping("getAll")
    public ResultVO getAll(){
        return ResultVOUtil.success(indexProfessorService.getAll());
    }

    @ResponseBody
    @PostMapping("/")
    public ResultVO add(MultipartFile file,String name){
        if (name.trim().equals("")){
            throw new MyException(IndexProfessorEums.NAME_NOT_FOUND);
        }
        System.out.println(name);
        String extension = FileUtil.isImage(file);
        String filePath = UploadConstant.INDEXPROFESSOR + UUID.randomUUID() + "." +extension;
        //保存图片
        FileUtil.saveFile(file,UploadConstant.PUBLIC + filePath);
        //保存到数据库
        IndexProfessor indexProfessor = new IndexProfessor();
        indexProfessor.setName(name);
        indexProfessor.setPictureurl(filePath);
        indexProfessorService.add(indexProfessor);
        return ResultVOUtil.success();
    }

    @ResponseBody
    @PutMapping("/{id}")
    public ResultVO update(@RequestParam(required = false) MultipartFile file,
                           @RequestParam(required = false) String name,
                           @PathVariable Integer id){
        IndexProfessor indexProfessor_sql = indexProfessorService.find(id);
        if (name.trim().equals("")){
            name = null;
        }
        if (file!=null && !file.isEmpty()){
            FileUtil.isImage(file);
            String filePath = indexProfessor_sql.getPictureurl();
            FileUtil.deleteFile(UploadConstant.PUBLIC + filePath);
            FileUtil.saveFile(file,UploadConstant.PUBLIC + filePath);
        }
        IndexProfessor indexProfessor = new IndexProfessor();
        indexProfessor.setName(name);
        indexProfessor.setId(id);
        indexProfessor.setPictureurl(indexProfessor_sql.getPictureurl());
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
