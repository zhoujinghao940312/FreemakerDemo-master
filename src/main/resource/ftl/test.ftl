<html>
<head>
    <meta charset="utf-8">
    <title>Freemarker入门小DEMO </title>
</head>
<body>
<#--我只是一个注释，我不会有任何输出  -->
<#assign linkman="周先生">
联系人：${linkman}

<#assign info={"mobile":"13301231212",'address':'北京市昌平区王府街'} >
电话：${info.mobile}  地址：${info.address}
<#include "head.ftl">

<#if success=true>
  你已通过实名认证
<#else>
  你未通过实名认证
</#if>


${person.id}
<br /> ${person.name}
<#list persons as person> ${person}
</#list>

<!--Map写法  -->
${mx.alingluo}/${mx.lling}
<#list mx?keys as k> ${mx[k]}
</#list>

<!--List<Map>写法  -->
<#list maps as m> ${m.id1}/${m.id2}
</#list> <#list maps as m>
    <#list m?keys as k> ${m[k]}
    </#list>
</#list>

<!--获取当前索引  -->
<#list persons as p> ${p_index} </#list>
${persons?size}  条记录
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


转换JSON字符串为对象
<#assign text="{'bank':'工商银行','account':'10101920201920212'}" />
	<#assign data=text?eval />
开户行：${data.bank}  账号：${data.account}


当前日期：${today?date} <br>
当前时间：${today?time} <br>
当前日期+时间：${today?datetime} <br>
日期格式化：  ${today?string("yyyy年MM月")}


数字转换为字符串
累计积分：${point}<br>
累计积分：${point?c}


判断某变量是否存在:“??”
<#if aaa??>
  aaa变量存在
<#else>
  aaa变量不存在
</#if>


失变量默认值:“!”
${aaa!'-'}
</body>
</html>
