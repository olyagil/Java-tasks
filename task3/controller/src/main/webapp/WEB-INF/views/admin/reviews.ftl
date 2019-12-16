<#import "../tags/head.ftl" as hd>
<#import "../tags/body.ftl" as bd>
<#import "../tags/pagination.ftl" as pag>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Reviews"/>
<@bd.body>
    <div class="container">

        <#include "../sidebar.ftl">
        <h2><@spring.message code="button.reviews"/>:</h2>

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

        <br>
        <br>
        <#if reviews?has_content>
            <table class="uui-table dynamic stripe records-per-page search showing-pages paging">
                <thead>
                <tr>
                    <th><@spring.message code="table.id"/></th>
                    <th><@spring.message code="table.date"/></th>
                    <th><@spring.message code="table.text"/></th>
                    <th><@spring.message code="table.user"/></th>
                    <th><@spring.message code="table.tour"/></th>
                    <th><@spring.message code="button.delete"/></th>
                </tr>
                </thead>
                <tbody>
                <#list reviews as review>
                    <tr>
                        <td>${review.id}</td>
                        <td>${review.date}</td>
                        <td>${review.text}</td>
                        <td><a href="<@spring.url'/user-${review.user.id}'/>">
                                ${review.user.login}</a></td>
                        <td style="width:200px">
                            <img src="data:image/png;base64,${review.tour.photo}"
                                 width="100" height="100" alt="EPAM"></td>
                        <td>
                            <form action="<@spring.url '/admin/delete-review'/>"
                                  method="post"
                                  modelAttribute="review">
                                <input type="hidden" name="id"
                                       value="${review.id}">
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

            <@pag.pagination "reviews"/>

        <#else>
            <h3 align="center"> No review was found</h3>
        </#if>
    </div>
</@bd.body>

</html>