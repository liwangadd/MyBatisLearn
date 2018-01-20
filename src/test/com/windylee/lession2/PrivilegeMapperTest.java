package com.windylee.lession2;

import com.windylee.lession2.entity.SysPrivilege;
import com.windylee.lession2.service.PrivilegeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

public class PrivilegeMapperTest extends BaseMapperTest{

    @Test
    public void testSelectById(){
        SqlSession sqlSession = getSqlSession();
        try{
            PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
            SysPrivilege privilege = privilegeMapper.selectById(1l);
            Assert.assertNotNull(privilege);
        }finally {
            sqlSession.close();
        }
    }

}
