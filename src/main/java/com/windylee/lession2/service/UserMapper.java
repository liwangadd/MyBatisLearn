package com.windylee.lession2.service;

import com.windylee.lession2.entity.SysRole;
import com.windylee.lession2.entity.SysUser;

import java.util.List;

public interface UserMapper {

    SysUser selectById(Long id);

    List<SysUser> selectAll();

    List<SysRole> selectRolesByUserId(Long userId);

    int insert(SysUser sysUser);

    int insert2(SysUser sysUser);

    int insert3(SysUser sysUser);

}
