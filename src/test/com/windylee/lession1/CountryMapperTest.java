package com.windylee.lession1;

import com.windylee.lession1.entity.Country;
import com.windylee.lession1.entity.CountryExample;
import com.windylee.lession1.service.CountryMapper;
import com.windylee.lession2.BaseMapperTest;
import com.windylee.lession2.service.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CountryMapperTest extends BaseMapperTest{

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();
        List<Country> countryList = sqlSession.selectList("selectAll");
        for(Country country:countryList){
            System.out.printf("%-4d%4s%4s\n", country.getId(), country.getCountryname(), country.getCountrycode());
        }
    }

    @Test
    public void testExample(){
        SqlSession sqlSession = getSqlSession();
        try{
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            CountryExample countryExample = new CountryExample();
            countryExample.setOrderByClause("id desc, countryname asc");
            countryExample.setDistinct(true);
            CountryExample.Criteria criteria = countryExample.createCriteria();
            criteria.andIdGreaterThanOrEqualTo(1);
            criteria.andIdLessThan(4);
            criteria.andCountrycodeLike("%U%");
            CountryExample.Criteria or = countryExample.or();
            or.andCountrynameEqualTo("中国");
            List<Country> countryList = countryMapper.selectByExample(countryExample);
            printCountryList(countryList);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByExampleSelective(){
        SqlSession sqlSession = getSqlSession();
        try{
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            CountryExample example = new CountryExample();
            CountryExample.Criteria criteria = example.createCriteria();
            criteria.andIdGreaterThan(2);
            Country country = new Country();
            country.setCountryname("China");
            countryMapper.updateByExampleSelective(country, example);
            printCountryList(countryMapper.selectByExample(example));
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteByExample(){
        SqlSession sqlSession = getSqlSession();
        try{
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            CountryExample example = new CountryExample();
            CountryExample.Criteria criteria = example.createCriteria();
            criteria.andIdGreaterThan(2);
            countryMapper.deleteByExample(example);
            Assert.assertEquals(0, countryMapper.selectByExample(example).size());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    private void printCountryList(List<Country> countryList) {
        for (Country country : countryList) {
            System.out.printf("%-4d%4s%4s\n", country.getId(), country.getCountryname(), country.getCountrycode());
        }
    }
}
