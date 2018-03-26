package com.jking.computersite.enums;

public interface SituEnums {
    BaseEnums ID_NOT_FOUND = new BaseEnums(2, "id不存在");
    BaseEnums PIC_NOT_FOUND = new BaseEnums(1, "上传的图片不能为空");
    BaseEnums UNFINISHED= new BaseEnums(3,"未完成上传");
}
