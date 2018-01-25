package com.windylee.lession2.service;

import com.windylee.lession2.entity.SysPrivilege;
import com.windylee.lession2.provider.PrivilegeProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public interface PrivilegeMapper {

    //    @InsertProvider()
//    @UpdateProvider()
//    @DeleteProvider()
    @SelectProvider(type = PrivilegeProvider.class, method = "selectById")
    SysPrivilege selectById(Long id);

}
