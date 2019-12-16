<#import "tags/head.ftl" as hd>
<#import "tags/body.ftl" as bd>
<#import "tags/pagination.ftl" as pag>
<#import "/spring.ftl"as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Hotels"/>
<br><br><br>
<div class="container" align="center">
    <@bd.body>
    <h2><@spring.message code="button.hotels"/>:</h2>
    <#list hotels as hotel>
        <section class="uui-info-panel-vertical bg-gray">
            <div class="info-panel-body">
                <div class="info-panel-section">
                    <table>
                        <tbody>
                        <tr>
                            <td align="left" style="width: 300px;">
                                <@spring.message code="table.name"/>
                                : ${hotel.name} <br><br>
                                <@spring.message code="table.stars"/>
                                : ${hotel.stars} *
                            </td>

                            <td align="left">
                                <@spring.message code="table.features"/>:
                                <#list hotel.features as feature>
                                ${feature.value},
                                </#list><br><br>
                                <@spring.message code="table.website"/>: <a
                                        href="${hotel.website}"> ${hotel.website}</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="info-panel-footer">
                <form action="<@spring.url '/hotel-${hotel.id}'/>" method="get">

                    <button type="submit" class="uui-button">
                        <@spring.message code="button.more"/>
                    </button>
                </form>
            </div>
        </section>
        <br>
    </#list>
    <@pag.pagination "hotelList"/>
</div>
<br><br><br><br>
</@bd.body>
</html>
