<#import "tags/head.ftl" as hd>
<#import "tags/body.ftl" as bd>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head 'Travel Agency "WorkStreet"'/>
<@bd.body>
    <br><br><br>
    <div class="container" align="center">
        <#if message??>
            <h3>${message}</h3>
        <#else >
            <h3>Error has occurred</h3>
        </#if>

    </div>
</@bd.body>
</html>
