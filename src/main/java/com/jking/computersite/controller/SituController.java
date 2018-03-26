package com.jking.computersite.controller;

import com.jking.computersite.VO.ResultVO;
import com.jking.computersite.constant.UploadConstant;
import com.jking.computersite.entity.Situ;
import com.jking.computersite.service.SituService;
import com.jking.computersite.utils.FileUtil;
import com.jking.computersite.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/situ")
@CrossOrigin
public class SituController {
    @Autowired
    SituService situService;

    @ResponseBody
    @GetMapping("/All")
    public ResultVO getAll(@RequestParam int p)
    {
        return ResultVOUtil.success(situService.selectAll(p,6));
    }

    @ResponseBody
    @PostMapping("/")
    public ResultVO add(Situ situ, MultipartFile file)
    {
        String extension = FileUtil.getExtension(file);
        FileUtil.isImage(file);
        String filePath = UploadConstant.INDEXPROFESSOR + UUID.randomUUID() + "." +extension;
        //保存图片
        FileUtil.saveFile(file, UploadConstant.PUBLIC + filePath);
        //保存到数据库
        situ.setPictureurl(filePath);
        situService.add(situ);
        return  ResultVOUtil.success();
    }

    @ResponseBody
    @PostMapping("/update/{id}")
    public ResultVO update(@RequestParam(value = "name",required = false) String name, @RequestParam(required = false)  String telephone,
                           @RequestParam(required = false) String work, @RequestParam(required = false) String location,
                           @RequestParam(required = false) String email , @RequestParam(required = false) MultipartFile file,
                           @PathVariable Integer id)
    {     System.out.print(name);
        Situ situ_sql =situService.find(id);
        if (file!=null && !file.isEmpty()){
            FileUtil.isImage(file);
            String filePath = situ_sql.getPictureurl();
            FileUtil.deleteFile(UploadConstant.PUBLIC + filePath);
            FileUtil.saveFile(file, UploadConstant.PUBLIC + filePath);
            situ_sql.setPictureurl(filePath);
        }
        System.out.print(name+"aa");
        Situ situ =new Situ();
         situ.setId(id);
         situ.setName(name);
         situ.setTelephone(telephone);
         situ.setWork(work);
         situ.setLocation(location);
          situ.setEmail(email);
       situ.setPictureurl(situ_sql.getPictureurl());
        situService.update(situ);
        return ResultVOUtil.success();
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResultVO find(@PathVariable Integer id)
    {   System.out.println(id);
        return ResultVOUtil.success(situService.find(id));

    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id){
      situService.delete(id);
        return ResultVOUtil.success();
    }
}
