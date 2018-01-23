package com.windylee.generator;

import org.apache.ibatis.io.Resources;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Generator {

    public static void main(String[] args) throws Exception {
        InputStream is = Resources.getResourceAsStream("generator/generatorConfig.xml");
        ConfigurationParser parser = new ConfigurationParser(new ArrayList<>());
        Configuration configuration = parser.parseConfiguration(is);
        is.close();
        DefaultShellCallback shellCallback = new DefaultShellCallback(true);
        MyBatisGenerator generator = new MyBatisGenerator(configuration, shellCallback, new ArrayList<>());
        generator.generate(null);
    }
}
