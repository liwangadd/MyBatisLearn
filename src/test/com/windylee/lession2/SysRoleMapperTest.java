package com.windylee.lession2;

import com.windylee.lession2.entity.SysRole;
import com.windylee.lession2.service.RoleMapper;
import com.windylee.lession2.service.SysRoleMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class SysRoleMapperTest extends BaseMapperTest{

    @Test
    public void testSelectById(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById(1l);
            Assert.assertNotNull(role);
            Assert.assertEquals("管理员", role.getRoleName());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById2(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById2(1l);
            Assert.assertNotNull(role);
            Assert.assertEquals("管理员", role.getRoleName());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roles = roleMapper.selectAll();
            Assert.assertNotNull(roles);
            Assert.assertTrue(roles.size()>0);
            Assert.assertNotNull(roles.get(0).getRoleName());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = new SysRole();
            role.setRoleName("普通员工");
            role.setCreateBy(1l);
            role.setCreateTime(new Date());
            role.setEnabled(1);
            int result = roleMapper.insert(role);
            Assert.assertEquals(1, result);
            Assert.assertNull(role.getId());
        }finally {
            sqlSession.rollback();
            sqlSession.commit();
        }
    }

    @Test
    public void testInsert2(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = new SysRole();
            role.setRoleName("普通员工");
            role.setCreateBy(1l);
            role.setCreateTime(new Date());
            role.setEnabled(1);
            int result = roleMapper.insert2(role);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(role.getId());
            System.out.println(role.getId());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = new SysRole();
            role.setRoleName("普通员工");
            role.setCreateBy(1l);
            role.setCreateTime(new Date());
            role.setEnabled(1);
            int result = roleMapper.insert3(role);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(role.getId());
            System.out.println(role.getId());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById(1l);
            role.setRoleName("普通用户");
            int result = roleMapper.updateById(role);
            Assert.assertEquals(1, result);
            role = roleMapper.selectById(1l);
            Assert.assertNotNull(role);
            Assert.assertEquals("普通用户", role.getRoleName());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Assert.assertEquals(1, roleMapper.deleteById(1l));
            Assert.assertNull(roleMapper.selectById(1l));
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllRoleAndPrivileges(){
        SqlSession sqlSession = getSqlSession();
        try{
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);
            List<SysRole> roleList = roleMapper.selectAllRoleAndPrivileges();
            System.out.println("角色数：" + roleList.size());
            roleList.forEach(role->{
                System.out.println("角色名：" + role.getRoleName());
                role.getPrivilegeList().forEach(privilege-> System.out.println("权限名：" + privilege.getPrivilegeName()));
            });
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserIdChoose(){
        SqlSession sqlSession = getSqlSession();
        try{
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);
            SysRole record = roleMapper.selectByPrimaryKey(2l);
            record.setEnabled(0);
            roleMapper.updateByPrimaryKey(record);
            List<SysRole> roleList = roleMapper.selectRoleByUserIdChoose();
            roleList.forEach(role->{
                System.out.println("角色名：" + role.getRoleName());
                if(role.getPrivilegeList() != null){
                    role.getPrivilegeList().forEach(privilege-> System.out.println("权限名：" + privilege.getPrivilegeName()));
                }else{
                    System.out.println("角色已被禁用");
                }
            });
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

}
