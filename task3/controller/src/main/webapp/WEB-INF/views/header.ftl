<#import "tags/head.ftl" as hd>
<#import "/spring.ftl" as spring />
<#assign security=JspTaglibs["/WEB-INF/tld/security.tld"]/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><@spring.message code="button.name"/></title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
</head>
<body>

<header>
    <div class="uui-header sidebar-header">
        <nav>
            <a href="<@spring.url'/main'/>" class="brand-logo">
                <span class="logo">
                    <img src="<@spring.url'/static/images/ic_logo_UUi.svg'/>"
                         alt=""/>
                </span><@spring.message code="button.name"/>
            </a>
            <ul class="uui-navigation nav navbar-nav">
                <li><a href="<@spring.url'/tourList'/>"><@spring.message code="button.tours"/></a></li>
                <li><a href="<@spring.url'/hotelList'/>"><@spring.message code="button.hotels"/></a></li>
                <li><a href="<@spring.url'/reviewList'/>"><@spring.message code="button.reviews"/></a></li>
            </ul>

            <ul class="uui-header-tools nav navbar-nav">
                <li class="uui-header-button">

                    <button type="button" class="uui-button dropdown-toggle lime-green"
                            data-toggle="dropdown">
                        <@spring.message code="button.lang"/>
                        <i class="fa fa-angle-down"></i>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="?lang=en_US">English</a></li>
                        <li><a href="?lang=ru_RU">Русский</a></li>
                    </ul>
                </li>
                <@security.authorize access="isAuthenticated()">
                    <li class="uui-header-button">
                        <a href="<@spring.url'/profile'/>"
                           class="uui-button"><@spring.message code="button.profile"/></a>
                    </li>
                    <li class="uui-header-button">
                        <a href="<@spring.url'/perform_logout'/>"
                           class="uui-button"><@spring.message code="button.logout"/></a>
                    </li>
                </@security.authorize>
                <@security.authorize access="!isAuthenticated()">
                    <li class="uui-header-button">
                        <a href="<@spring.url'/register'/>"
                           class="uui-button"><@spring.message code="button.signup"/></a>
                    </li>
                    <li class="uui-header-button">
                        <a href="<@spring.url'/login'/>" class="uui-button"><@spring.message code="button.login"/></a>
                    </li>
                </@security.authorize>
            </ul>
        </nav>
    </div>
</header>

</body>
</html>
