<#import "../tags/head.ftl" as hd>
<#import "../tags/body.ftl" as bd>
<#import "../tags/pagination.ftl" as pag>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head " Your Reviews"/>
<@bd.body>
    <div class="container">

        <#include "../sidebar.ftl">
        <h2><@spring.message code="button.reviews"/></h2>

        <#if message??>
            <div style="color:green;font-style:italic;">
                ${message}
            </div>
        </#if>
        <br>
        <#if reviews?has_content>
            <table class="uui-table dynamic stripe records-per-page search showing-pages paging">
                <thead>
                <tr>
                    <th><@spring.message code="table.date"/></th>
                    <th><@spring.message code="table.text"/></th>
                    <th><@spring.message code="table.id"/></th>
                    <th><@spring.message code="table.photo"/></th>
                    <th><@spring.message code="button.edit"/></th>
                    <th><@spring.message code="button.delete"/></th>
                </tr>
                </thead>
                <tbody>
                <#list reviews as review>
                    <tr>
                        <td>${review.date}</td>
                        <td>${review.text}</td>
                        <td><a href="<@spring.url'/tour-${review.tour.id}'/>">
                                ${review.tour.id}</a></td>
                        <td style="width:100px">
                            <img src="data:image/png;base64,${review.tour.photo}"
                                 width="100" height="100" alt="EPAM">
                        </td>
                        <td>
                            <form action="<@spring.url '/user/edit-review'/>"
                                  method="post" modelAttribute="review">
                                <input type="hidden" name="review_id"
                                       value="${review.id}">
                                <button class="uui-button small lime-green"
                                        type="submit">
                                    <@spring.message code="button.edit"/>
                                </button>
                            </form>

                        </td>
                        <td>
                            <form action="<@spring.url '/user/delete-review'/>"
                                  method="post" modelAttribute="review">
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
        <#else>
            <h3>You don't have any reviews just yet.</h3>
        </#if>
    </div>
</@bd.body>
</html>