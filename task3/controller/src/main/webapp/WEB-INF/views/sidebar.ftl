<#import "/spring.ftl" as spring />
<#assign security=JspTaglibs["/WEB-INF/tld/security.tld"]/>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Travel Agency</title>
</head>
<body>
<br>
<br>

<div class="uui-input-group horizontal">

    <@security.authorize access="hasRole('ADMIN')">

        <button class="uui-button transparent">
            <a href="<@spring.url'/admin/tours'/>"><@spring.message code="button.tours"/></a>
        </button>

        <button class="uui-button transparent">
            <a href="<@spring.url'/admin/hotels'/>"><@spring.message code="button.hotels"/></a>
        </button>

        <button class="uui-button transparent">
            <a href="<@spring.url'/admin/users'/>"><@spring.message code="button.users"/></a>
        </button>

        <button class="uui-button transparent">
            <a href="<@spring.url'/admin/reviews'/>"><@spring.message code="button.reviews"/></a>
        </button>

    </@security.authorize>

    <@security.authorize access="hasRole('USER')">

        <button class="uui-button transparent">
            <a href="<@spring.url'/user/tours'/>"><@spring.message code="button.tours"/></a>
        </button>

        <button class="uui-button transparent">
            <a href="<@spring.url'/user/reviews'/>"><@spring.message code="button.reviews"/></a>
        </button>

    </@security.authorize>
</div>
</body>
</html>
