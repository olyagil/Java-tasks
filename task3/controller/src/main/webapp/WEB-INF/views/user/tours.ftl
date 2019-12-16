<#import "../tags/head.ftl" as hd>
<#import "../tags/body.ftl" as bd>
<#import "../tags/pagination.ftl" as pag>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head " Your Tours"/>

<div class="container" align="center">
    <@bd.body>
    <br><br>
    <#include "../sidebar.ftl">
    <h2><@spring.message code="button.tours"/></h2>
    <#if message??>
        <div style="color:green;font-style:italic;">
            ${message}
        </div>
    </#if>
    <#if tours?has_content>
        <table class="uui-table dynamic stripe records-per-page search showing-pages paging">
            <thead>
            <tr>
                <th><@spring.message code="table.photo"/></th>
                <th><@spring.message code="table.date"/></th>
                <th><@spring.message code="table.duration"/></th>
                <th><@spring.message code="table.description"/></th>
                <th><@spring.message code="table.cost"/></th>
                <th><@spring.message code="table.tourType"/></th>
                <th><@spring.message code="table.hotel"/></th>
                <th><@spring.message code="table.country"/></th>
                <th><@spring.message code="table.add.review"/></th>
            </tr>
            </thead>
            <tbody>
            <#list tours as tour>
                <tr>
                    <td width="100px">
                        <img src="data:image/png;base64,${tour.photo}"
                             width="100" height="100" alt="EPAM"></td>
                    <td>${tour.date}</td>
                    <td>${tour.duration}</td>
                    <td>${tour.description}</td>
                    <td>${tour.cost}</td>
                    <td>${tour.tourType.value}</td>
                    <td><a href="<@spring.url'/hotel-${tour.hotel.id}'/>">
                            ${tour.hotel.name}</a></td>
                    <td>${tour.country.name}</td>
                    <td>
                        <form action="<@spring.url '/user/add-review'/>"
                              method="post" modelAttribute="review">
                            <input type="hidden" name="id" value="${tour.id}">
                            <button class="uui-button small lime-green"
                                    type="submit">
                                <@spring.message code="button.add"/>
                            </button>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <h3>You don't have  any tours just yet.</h3>
    </#if>
    <br><br><br><br><br>
</div>
</@bd.body>
</html>