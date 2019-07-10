package com.anlu.freemarker.controller;

import com.anlu.freemarker.model.Person;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class HelloController {
    public static void main(String[] args) throws IOException, TemplateException {
        //第一步：实例化Freemarker的配置类
        Configuration conf = new Configuration();
        //第二步：给配置类设置路径
        String dir = "D:\\IDEAPro\\FreemakerDemo-master\\src\\main\\resource\\ftl";

        conf.setDirectoryForTemplateLoading(new File(dir));

        //3.设置字符集
        conf.setDefaultEncoding("utf-8");


        Template template = conf.getTemplate("test.ftl");

        //第三步：处理模板及数据之间将数据与模板合成一个HTML
        //第四步：输出html
        Writer out = new FileWriter(new File(dir+"hello.html"));
        //定义数据
        //  Map root = new HashMap();

        //执行生成


        //定义数据
        Map<String,Object> root = new HashMap<String,Object>();
        Person p = new Person();
        p.setId("111");
        p.setName("哈哈哈");
        root.put("person", p);
        root.put("world", "世界你好");

        root.put("basePath","/root/usr/local");
        //遍历List
        List<String> persons = new ArrayList<String>();
        persons.add("阿灵罗");
        persons.add("罗零");
        persons.add("灵罗");
        root.put("persons",persons);

        root.put("cur_time", new Date());
        //遍历Map
        Map<String,String> mx = new HashMap<String,String>();
        mx.put("alingluo", "阿灵罗");
        mx.put("lling", "罗零");
        root.put("mx", mx);




        //遍历List<Map>
        List<Map<String,String>> maps = new ArrayList<Map<String,String>>();
        Map<String,String> pms1 = new HashMap<String,String>();
        pms1.put("id1", "张三");
        pms1.put("id2", "李四");
        Map<String,String> pms2 = new HashMap<String,String>();
        pms2.put("id1", "张三");
        pms2.put("id2", "李四");
        maps.add(pms1);
        maps.add(pms2);
        root.put("maps", maps);

        //定义数据
        root.put("val", null);

        root.put("success", true);

        root.put("today", new Date());

        root.put("point", 102920122);
        template.process(root, out);
        out.flush();
        out.close();
    }
}
