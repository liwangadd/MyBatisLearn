package com.windylee.lession2;

import com.windylee.lession2.entity.SysRole;
import com.windylee.lession2.entity.SysUser;
import com.windylee.lession2.service.SysUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class SysUserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {
        SqlSession sqlSession = getSqlSession();
        SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
        SysUser sysUser = userMapper.selectById(1L);
        Assert.assertNotNull(sysUser);
        Assert.assertEquals("admin", sysUser.getUserName());
        sqlSession.close();
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysUser> userList = userMapper.selectAll();
        Assert.assertNotNull(userList);
        Assert.assertTrue(userList.size() > 0);
        sqlSession.close();
    }

    @Test
    public void testSelectRolesByUserId() {
        SqlSession sqlSession = getSqlSession();
        SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysRole> roleList = userMapper.selectRolesByUserId(1l);
        Assert.assertNotNull(roleList);
        Assert.assertTrue(roleList.size() > 0);
        sqlSession.close();
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            int result = userMapper.insert(user);
            Assert.assertEquals(1, result);
            Assert.assertNull(user.getId());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            int result = userMapper.insert2(user);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(user.getId());
            System.out.println(user.getId());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            int result = userMapper.insert3(user);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(user.getId());
            System.out.println(user.getId());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = userMapper.selectById(1l);
            Assert.assertEquals("admin", user.getUserName());
            user.setUserName("admin_test");
            user.setUserEmail("test@mybatis.tk");
            int result = userMapper.updateById(user);
            Assert.assertEquals(1, result);
            user = userMapper.selectById(1l);
            Assert.assertEquals("admin_test", user.getUserName());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user1 = userMapper.selectById(1l);
            Assert.assertNotNull(user1);
            Assert.assertEquals(1, userMapper.deleteById(1l));
            Assert.assertNull(userMapper.selectById(1l));

            SysUser user2 = userMapper.selectById(1001l);
            Assert.assertNotNull(user2);
            Assert.assertEquals(1, userMapper.deleteById(user2));
            Assert.assertNull(userMapper.selectById(1001l));
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserIdAndRoleEnabled() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysRole> roles = userMapper.selectRolesByUserIdAndRoleEnabled(1l, 1);
            Assert.assertNotNull(roles);
            Assert.assertTrue(roles.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByUser() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("ad");
            List<SysUser> users = userMapper.selectByUser(user);
            Assert.assertTrue(users.size() > 0);
            user = new SysUser();
            user.setUserEmail("test@mybatis.tk");
            users = userMapper.selectByUser(user);
            Assert.assertTrue(users.size() > 0);
            user = new SysUser();
            user.setUserName("ad");
            user.setUserEmail("test@mybatis.tk");
            users = userMapper.selectByUser(user);
            Assert.assertTrue(users.size() == 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByUser() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = new SysUser();
            user.setId(1l);
            user.setUserEmail("test@mybatis.tk");
            int result = userMapper.updateByIdSelective(user);
            Assert.assertEquals(1, result);
            user = userMapper.selectById(1l);
            Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
            Assert.assertEquals("admin", user.getUserName());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2Selective() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test-selective");
            user.setUserPassword("123456");
            user.setUserInfo("test info");
            user.setCreateTime(new Date());
            userMapper.insert2(user);
            user = userMapper.selectById(user.getId());
            Assert.assertEquals("test@mybatis", user.getUserEmail());
        } finally {
            sqlSession.rollback();
            sqlSession.commit();
        }
    }

    @Test
    public void testSelectByIdOrUserName() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser query = new SysUser();
            query.setId(1l);
            query.setUserName("admin");
            SysUser user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            query.setId(null);
            Assert.assertNotNull(userMapper.selectByIdOrUserName(query));
            query.setUserName(null);
            Assert.assertNull(userMapper.selectByIdOrUserName(query));
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdList() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            List<Long> ids = new ArrayList<>();
            ids.add(1l);
            ids.add(1001l);
            List<SysUser> userList = userMapper.selectByIdList(ids);
            Assert.assertEquals(2, userList.size());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertList() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> users = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                SysUser user = new SysUser();
                user.setUserName("test" + i);
                user.setUserPassword("123456");
                user.setUserEmail("test@mybatis.tk");
                users.add(user);
            }
            int result = userMapper.insertList(users);
            users.forEach(user -> System.out.println(user.getId()));
            Assert.assertEquals(2, result);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByMap() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1l);
            map.put("user_email", "test@mybatis.tk");
            map.put("user_password", "123456");
            userMapper.updateByMap(map);
            SysUser sysUser = userMapper.selectById(1l);
            Assert.assertEquals("test@mybatis.tk", sysUser.getUserEmail());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById(){
        SqlSession sqlSession = getSqlSession();
        try{
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = userMapper.selectUserAndRoleById(1001l);
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getRole());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById2(){
        SqlSession sqlSession = getSqlSession();
        try{
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = userMapper.selectUserAndRoleById2(1001l);
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getRole());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById3(){
        SqlSession sqlSession = getSqlSession();
        try{
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = userMapper.selectUserAndRoleById3(1001l);
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getRole());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleByIdSelect(){
        SqlSession sqlSession = getSqlSession();
        try{
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = userMapper.selectUserAndRoleByIdSelect(1001l);
            Assert.assertNotNull(user);
            System.out.println("调用user.getRole()");
            Assert.assertNotNull(user.getRole());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserAndRoles(){
        SqlSession sqlSession = getSqlSession();
        try{
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> users = userMapper.selectAllUserAndRoles();
            System.out.println("用户数：" + users.size());
            users.forEach(user->{
                System.out.println("用户名：" + user.getUserName());
                user.getRoleList().forEach(role->{
                    System.out.println("角色名：" + role.getRoleName());
                    role.getPrivilegeList().forEach(privilege-> System.out.println("权限名：" + privilege.getPrivilegeName()));
                });
            });
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserAndRolesSelect(){
        SqlSession sqlSession = getSqlSession();
        try{
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> users = userMapper.selectAllUserAndRolesSelect();
            System.out.println("用户数：" + users.size());
            users.forEach(user->{
                System.out.println("用户名：" + user.getUserName());
                user.getRoleList().forEach(role->{
                    System.out.println("角色名：" + role.getRoleName());
                    role.getPrivilegeList().forEach(privilege-> System.out.println("权限名：" + privilege.getPrivilegeName()));
                });
            });
        }finally {
            sqlSession.close();
        }
    }

}
