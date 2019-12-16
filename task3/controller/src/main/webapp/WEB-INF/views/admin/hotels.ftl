<#import "../tags/head.ftl" as hd>
<#import "../tags/body.ftl" as bd>
<#import "../tags/pagination.ftl" as pag>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Hotels"/>
<@bd.body>
    <div class="container">

    <#include "../sidebar.ftl">
    <h2><@spring.message code="button.hotels"/>:</h2>

    <#if message??>
        <div style="color:green;font-style:italic;">
            ${message}
        </div>
    </#if>
    <#if error??>
        <div style="color:red;font-style:italic;">
            ${error}
        </div>
    </#if>

    <form action="<@spring.url '/admin/add-hotel'/>" method="get"
          modelAttribute="hotel" align="right">
        <button class="uui-button medium blue" type="submit">
            <@spring.message code="button.add"/>
        </button>
    </form>

    <br>
        <#if hotels?has_content>
    <table class="uui-table dynamic stripe records-per-page search showing-pages paging">
        <thead>
        <tr>
            <th><@spring.message code="table.id"/></th>
            <th><@spring.message code="table.name"/></th>
            <th><@spring.message code="table.stars"/></th>
            <th><@spring.message code="table.website"/></th>
            <th><@spring.message code="table.latitude"/></th>
            <th><@spring.message code="table.longitude"/></th>
            <th><@spring.message code="table.features"/></th>
            <th><@spring.message code="button.delete"/></th>
        </tr>
        </thead>
        <tbody>
        <#list hotels as hotel>
            <tr>
                <td>${hotel.id}</td>
                <td><a href="<@spring.url'/hotel-${hotel.id}'/>">
                        ${hotel.name}
                    </a></td>
                <td>${hotel.stars}</td>
                <td><a href="${hotel.website}">${hotel.website}</a></td>
                <td>${hotel.latitude}</td>
                <td>${hotel.longitude}</td>
                <td><#list hotel.features as feature>
                        ${feature.value}
                    </#list>
                </td>

                <td>
                    <form action="<@spring.url '/admin/delete-hotel'/>"
                          method="post" modelAttribute="hotel">
                        <input type="hidden" value="${hotel.id}" name="id">
                        <button class="uui-button small raspberry"
                                type="submit">
                            <@spring.message code="button.delete"/>
                        </button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <br>
    <@pag.pagination "hotels"/>
        <#else>
            <h3 align="center"> No hotel was found</h3>
        </#if>
</div>
</@bd.body>
</html>