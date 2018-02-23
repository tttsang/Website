package com.jking.computersite.enums;

public interface CommonEnums {
    BaseEnums ID_NOT_FOUND = new BaseEnums(1, "id不存在");
    BaseEnums DATA_UNCOMPLETED = new BaseEnums(2, "请补全要填写的内容");
    BaseEnums DATA_REPEAT = new BaseEnums(3, "请不要插入重复数据");
}
