<#import "tags/head.ftl" as hd>
<#import "tags/body.ftl" as bd>
<#import "/spring.ftl" as spring>
<html lang="en">
<@hd.head "Profile"/>
<@bd.body>
    <div class="container">
        <br><br><br>
        <h5><@spring.message code="text.welcome"/> ${loggedinuser}</h5>
        <#include "sidebar.ftl">
        <hr>
    </div>
</@bd.body>
</html>