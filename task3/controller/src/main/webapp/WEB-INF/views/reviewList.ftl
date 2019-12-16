<#import "tags/head.ftl" as hd>
<#import "tags/body.ftl" as bd>
<#import "tags/pagination.ftl" as pag>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Reviews"/>

<div class="container" align="center">
    <@bd.body>
    <br><br><br>
    <h2><@spring.message code="button.reviews"/>:</h2>

    <#list reviews as review>
        <section class="uui-info-panel-vertical bg-gray">
            <div class="info-panel-body">
                <div class="info-panel-section">
                    <table>
                        <tbody>
                        <tr>
                            <td align="left" style="width: 300px;">
                                <@spring.message code="table.hotel"/>:
                                <a href="<@spring.url'/hotel-${review.tour.hotel.id}'/>">
                                    ${review.tour.hotel.name}
                                </a>

                                <br><br>

                                <@spring.message code="text.login"/>
                                :<a href="<@spring.url'/user-${review.user.id}'/>">
                                    ${review.user.login}</a>
                            </td>

                            <td align="left" style="width: 580px;">
                                <@spring.message code="table.text"/>:
                                <br> ${review.text}<br><br>
                            </td>

                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="info-panel-footer">
                <div class="info-panel-section">
                    <@spring.message code="table.date"/>: ${review.date}
                </div>
            </div>
        </section>
        <br>
    </#list>
    <@pag.pagination "reviewList"/>
</div>
<br><br><br><br>
</@bd.body>

</html>
