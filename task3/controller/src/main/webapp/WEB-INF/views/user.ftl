<#import "tags/head.ftl" as hd>
<#import "tags/body.ftl" as bd>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Add tour"/>
<div class="container" align="center">
    <@bd.body>

    <br><br><br>
    <#if user??>
        <table>
            <tbody>
            <tr>
                <td>
                    <h4><@spring.message code="text.login"/>
                        : ${user.login}</h4>
                </td>
                <td style="width:100px">
                    <img src="<@spring.url'/static/images/avatar.png'/>"
                         width="200" height="200" alt="EPAM">
                </td>
            </tr>
            </tbody>
        </table>
        <#if reviews?has_content>
            <h3><@spring.message code="button.reviews"/> : </h3>

            <#list reviews as review>
                <section class="uui-info-panel-vertical bg-gray">
                    <div class="info-panel-body">
                        <div class="info-panel-section">
                            <table>
                                <tbody>
                                <tr>
                                    <td align="left" style="width: 300px;">
                                        <@spring.message code="table.date"/> :
                                        ${review.date}
                                        <br><br>

                                        <@spring.message code="table.hotel"/> :
                                        <a href="<@spring.url'/hotel-${review.tour.hotel.id}'/>">
                                            ${review.tour.hotel.name}
                                        </a>
                                    </td>

                                    <td align="left" style="width: 580px;">
                                        <@spring.message code="table.text"/>:
                                        <br> ${review.text}
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </section>
                <br>
            </#list>
        <#else>
            <h3>This user has no reviews</h3>
        </#if>
        <br><br>

        <#if tours?has_content>
            <h3><@spring.message code="button.tours"/> : </h3>
            <#list tours as tour>
                <section class="uui-info-panel-vertical bg-gray">
                    <div class="info-panel-body">
                        <div class="info-panel-section">
                            <table>
                                <tbody>
                                <tr>
                                    <td width="200px"><img
                                                src="data:image/png;base64,${tour.photo}"
                                                width="100" height="100"
                                                alt="EPAM">
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
                                        <@spring.message code="table.description"/>
                                        :
                                        <br> ${tour.description}<br><br>
                                        <@spring.message code="table.cost"/>
                                        : ${tour.cost}

                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </section>
                <br>
            </#list>
        <#else>
            <h3>This user has no tours</h3>
        </#if>
    <#else>
        <h3>This user doesn't exist</h3>
    </#if>
    <br><br><br>
</div>
</@bd.body>
</html>