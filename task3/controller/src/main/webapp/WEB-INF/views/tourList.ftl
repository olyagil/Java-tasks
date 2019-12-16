<#import "tags/head.ftl" as hd>
<#import "tags/body.ftl" as bd>
<#import "tags/pagination.ftl" as pag>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Tours"/>

<div class="container" align="center">
    <@bd.body>
    <br><br><br>
    <h2><@spring.message code="button.tours"/>:</h2>
    <#list tours as tour>
        <section class="uui-info-panel-vertical bg-gray">
            <div class="info-panel-body">
                <div class="info-panel-section">
                    <table>
                        <tbody>
                        <tr>
                            <td width="200px"><img
                                        src="data:image/png;base64,${tour.photo}"
                                        width="100" height="100" alt="EPAM">
                            </td>
                            <td align="left" style="width: 300px;">
                                <@spring.message code="table.tourType"/>
                                : ${tour.tourType.value} <br><br>

                                <@spring.message code="table.hotel"/>
                                :<a href="<@spring.url'/hotel-${tour.hotel.id}'/>">
                                    ${tour.hotel.name}</a>


                                 <br><br>
                                <@spring.message code="table.country"/>
                                : ${tour.country.name}
                            </td>
                            <td align="left" style="width: 580px;">
                                <@spring.message code="table.description"/>:
                                <br> ${tour.description}<br><br>
                                <@spring.message code="table.cost"/>
                                : ${tour.cost}

                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="info-panel-footer">
                <form action="<@spring.url '/tour-${tour.id}'/>"
                      method="get">

                    <button type="submit" class="uui-button">
                        <@spring.message code="button.more"/>
                    </button>
                </form>
            </div>
        </section>
        <br>
    </#list>
    <@pag.pagination "tourList"/>
    <br><br><br><br><br>
</div>
</@bd.body>
</html>
