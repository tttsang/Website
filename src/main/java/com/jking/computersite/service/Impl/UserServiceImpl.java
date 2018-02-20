package com.jking.computersite.service.Impl;

import com.jking.computersite.enums.CommonEnums;
import com.jking.computersite.service.UserService;
import com.jking.computersite.entity.User;
import com.jking.computersite.enums.UserEnums;
import com.jking.computersite.exception.MyException;
import com.jking.computersite.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public void login(User user) {

        if (user == null || user.getId()==null || user.getPassword()==null){
            throw new MyException(CommonEnums.DATA_UNCOMPLETED);
        }

        String id = user.getId().trim();
        String password = user.getPassword().trim();

        //从数据库中查找
        User user_dao = userMapper.selectByPrimaryKey(id);

        //判断用户是否存在
        if (user_dao == null){
            throw new MyException(CommonEnums.ID_NOT_FOUND);
        }

        //判断密码是否正确
        String password_sql = user_dao.getPassword();
        if (!password_sql.equals(password)){
            throw new MyException(UserEnums.PASSWORD_ERROR);
        }
    }

    @Override
    public void changePassword(User user) {
        if (user == null || user.getId()==null || user.getPassword()==null){
            throw new MyException(CommonEnums.DATA_UNCOMPLETED);
        }

        String password = user.getPassword().trim();

        if (password.equals("")){
            throw new MyException(UserEnums.PASSWORD_NULL);
        }

        //从数据库中查找
        User user_dao = userMapper.selectByPrimaryKey(user.getId());

        //判断用户是否存在
        if (user_dao == null){
            throw new MyException(CommonEnums.ID_NOT_FOUND);
        }

        userMapper.updateByPrimaryKeySelective(user);
    }

}
