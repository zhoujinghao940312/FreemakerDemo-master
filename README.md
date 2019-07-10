# FreemakerDemo
Spring Boot推荐使用Thymeleaf来做页面模板引擎，所以又去瞅了瞅Thymeleaf，突然发现不是用过FreeMarker吗，虽然效率没有Thymeleaf高，但是还是觉得把之前学的FreeMarker相关知识记录下来，mark一下。OK，废话一堆，开始吧。

一，介绍

以下内容来自：http://www.oschina.net/p/freemarker

FreeMarker是一个模板引擎，一个基于模板生成文本输出的通用工具，使用纯Java编写。

FreeMarker被设计用来生成HTML Web页面，特别是基于MVC模式的应用程序。

虽然FreeMarker具有一些编程的能力，但通常由Java程序准备要显示的数据，由FreeMarker生成页面，通过模板显示准备的数据，简单来讲就是模板加数据模型，然后输出页面（如下图）

这里写图片描述

FreeMarker不是一个Web应用框架，而适合作为Web应用框架一个组件

FreeMarker与容器无关，因为它并不知道HTTP或Servlet；FreeMarker同样可以应用于非Web应用程序环境

FreeMarker更适合作为Model2框架（如Struts）的视图组件，你也可以在模板中使用JSP标记库

FreeMarker是免费的

而且你还可以通过Eclipse的插件来编辑FreeMarker，经过验证，FreeMarker 最好的 Eclipse 编辑插件是 JBoss Tools。

在Maven中使用它：

<dependency>
<groupId>freemarker</groupId>
<artifactId>freemarker</artifactId>
<version>2.3.9</version>
</dependency>
1
2
3
4
5
表示不知道怎么把jar上传到MarkDown编辑器，留个网址下吧。下载地址：http://freemarker.sourceforge.net/freemarkerdownload.html （注：官方网站.org的经常打不开）

二，使用环境搭建

创建一个Java项目，这里名为freemarker，在根目录下创建一个名为ftl的文件夹（PS：用来存放freemarker模板），jar啥的就不说了。

创建一个FreeMarkerDemo类，用来生成模板以及处理模板与数据之间的关系并输出：

        //第一步：实例化Freemarker的配置类
        Configuration conf = new Configuration();
        //第二步：给配置类设置路径
        String dir = "D:\\java21\\freemarker\\ftl\\";

        conf.setDirectoryForTemplateLoading(new File(dir));

        Template template = conf.getTemplate("freemarker.html");

        //第三步：处理模板及数据之间将数据与模板合成一个HTML
        //第四步：输出html
        Writer out = new FileWriter(new File(dir+"hello.html"));
1
2
3
4
5
6
7
8
9
10
11
12
定义一个Person对象：

public class Person {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
对象输出例子

定义数据

    //定义数据
        Map<String,Object> root = new HashMap<String,Object>();
        Person p = new Person();
        p.setId("111");
        p.setName("哈哈哈");
        root.put("person", p);
        template.process(root, out);
        out.flush();
        out.close();
1
2
3
4
5
6
7
8
9
模板页面书写

${person.id}<br /> ${person.name}
1
点击hello.html查看结果

遍历List例子

定义数据

        //遍历List
        List<String> persons = new ArrayList<String>();
        persons.add("阿灵罗");
        persons.add("罗零");
        persons.add("灵罗");
        root.put("persons",persons);
        template.process(root, out);
        out.flush();
        out.close();
1
2
3
4
5
6
7
8
9
模板页面书写:

<#list persons as person> ${person} </#list>

<!--获取当前索引  -->
<#list persons as p> ${p_index} </#list>
1
2
3
4
遍历Map

定义数据：

        //遍历Map
        Map<String,String> mx = new HashMap<String,String>();
        mx.put("alingluo", "阿灵罗");
        mx.put("lling", "罗零");
        root.put("mx", mx);
        template.process(root, out);
        out.flush();
        out.close();
1
2
3
4
5
6
7
8
模板页面书写：

<!--Map写法  -->
    ${mx.alingluo}/${mx.lling} <#list mx?keys as k> ${mx[k]} </#list>
1
2
ListMap遍历

定义数据

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
        template.process(root, out);
        out.flush();
        out.close();
1
2
3
4
5
6
7
8
9
10
11
12
13
14
模板页面书写：

<#list maps as m> ${m.id1}/${m.id2} </#list> <#list maps as m> <#list
    m?keys as k> ${m[k]} </#list> </#list>
1
2
在模板中赋值

root.put("world", "世界你好");
1
<!-- 在模板中赋值 -->
    1:<#assign x=0 /> ${x} 
    2:<#assign x="${world}" /> ${x} 
    3:<#assign x>世界太好了</#assign> ${x}
    4:<#assign x>
       <#list ["星期一", "星期二", "星期三",
         "星期四", "星期五", "星期六", "星期天"] as n> ${n} 
       </#list> 
     </#assign>
      ${x}
1
2
3
4
5
6
7
8
9
10
生成页面对应的值： 
这里写图片描述

判断

 <!-- 判断 -->
  <#list ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"] as n>
      <#if n != "星期一">
         ${n}
      </#if>
   </#list>

   <#list ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"] as n>
      <#if n_index != 0>
      ${n}
    </#if>
   </#list>

   <#list ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"] as n>
       <#if (n_index == 1) || (n_index == 3)>
          ${n}
       </#if>
    </#list>

   <!-- else -->
   <#list ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"] as n>
         <#if (n_index == 1) || (n_index == 3)>
             ${n} --红色
         <#else>
            ${n} --绿色
         </#if>
     </#list>
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
生成页面效果

这里写图片描述

时间格式

模板页面效果如下：

 <!--时间格式  -->
   1:${cur_time?date}
   2:${cur_time?datetime}
   3:${cur_time?time}
1
2
3
4
生成页面效果如下：

这里写图片描述

FreeMarkerDemo全部代码：

public class FreeMarkerDemo {
    public static void main(String[] args) throws IOException, TemplateException {
        //第一步：实例化Freemarker的配置类
        Configuration conf = new Configuration();
        //第二步：给配置类设置路径
        String dir = "D:\\java21\\freemarker\\ftl\\";

        conf.setDirectoryForTemplateLoading(new File(dir));

        Template template = conf.getTemplate("freemarker.html");

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


        template.process(root, out);
        out.flush();
        out.close();
    }
}
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
freemarker.html全部代码：

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


    ${person.id}
    <br /> ${person.name} <#list persons as person> ${person} </#list>

    <!--Map写法  -->
    ${mx.alingluo}/${mx.lling} <#list mx?keys as k> ${mx[k]} </#list>

    <!--List<Map>写法  -->
    <#list maps as m> ${m.id1}/${m.id2} </#list> <#list maps as m> <#list
    m?keys as k> ${m[k]} </#list> </#list>

    <!--获取当前索引  -->
    <#list persons as p> ${p_index} </#list>

    <!-- 在模板中赋值 -->
    1:<#assign x=0 /> ${x} 
    2:<#assign x="${world}" /> ${x} 
    3:<#assign x>世界太好了</#assign> ${x}
    4:<#assign x>
       <#list ["星期一", "星期二", "星期三",
         "星期四", "星期五", "星期六", "星期天"] as n> ${n} 
       </#list> 
     </#assign>
      ${x}

   <!-- 判断 -->
  <#list ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"] as n>
      <#if n != "星期一">
         ${n}
      </#if>
   </#list>

   <#list ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"] as n>
      <#if n_index != 0>
      ${n}
    </#if>
   </#list>

   <#list ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"] as n>
       <#if (n_index == 1) || (n_index == 3)>
          ${n}
       </#if>
    </#list>

   <!-- else -->
   <#list ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"] as n>
         <#if (n_index == 1) || (n_index == 3)>
             ${n} --红色
         <#else>
            ${n} --绿色
         </#if>
     </#list>
   <!--时间格式  -->
   1:${cur_time?date}
   2:${cur_time?datetime}
   3:${cur_time?time}
   <!-- null -->
   ${val!}

   <!-- 宏定义 -->
   1:
    <#macro table u>
        ${u} 
        </#macro>
    <@table u=8 />
    2:
    <#macro table u>
        ${u}
    <#nested/>
    </#macro>
    <@table u=8 >这是8</@table>

</body>
</html>
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
71
72
73
74
75
76
77
78
79
80
81
82
生成的HTML页面内容如下：

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


    111
    <br /> 哈哈哈  阿灵罗  罗零  灵罗 

    <!--Map写法  -->
    阿灵罗/罗零  阿灵罗  罗零 

    <!--List<Map>写法  -->
     张三/李四  张三/李四    李四  张三    李四  张三  

    <!--获取当前索引  -->
     0  1  2 

    <!-- 在模板中赋值 -->
    1: 0 
    2: 世界你好 
    3: 世界太好了
    4:    
 星期一 
 星期二 
 星期三 
 星期四 
 星期五 
 星期六 
 星期天 


   <!-- 判断 -->
         星期二
         星期三
         星期四
         星期五
         星期六
         星期天

      星期二
      星期三
      星期四
      星期五
      星期六
      星期天

          星期二
          星期四

   <!-- else -->
            星期一 --绿色
             星期二 --红色
            星期三 --绿色
             星期四 --红色
            星期五 --绿色
            星期六 --绿色
            星期天 --绿色
   <!--时间格式  -->
   1:2017-5-8
   2:2017-5-8 10:56:56
   3:10:56:56
   <!-- null -->


   <!-- 宏定义 -->
   1:
        8 
    2:
            8
这是8

</body>
</html>
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
71
72
73
74
75
76
77
这是一些FreeMarker的基础知识，希望会有用
