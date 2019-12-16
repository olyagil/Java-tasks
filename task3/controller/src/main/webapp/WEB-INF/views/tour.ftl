<#import "tags/head.ftl" as hd>
<#import "tags/body.ftl" as bd>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Add tour"/>

<@bd.body>
    <div class="container" align="center">
        <br><br><br><br><br>
        <#if tour??>
            <table>
                <tbody>
                <tr>
                    <td style="width:100px">
                        <img src="data:image/png;base64,${tour.photo}"
                             width="100" height="100" alt="EPAM">
                    </td>

                    <td style="width:100px">
                        <h4><@spring.message code="table.duration"/>
                            : ${tour.duration}</h4>

                        <h4><@spring.message code="table.cost"/>
                            : ${tour.cost}</h4>

                        <h4><@spring.message code="table.tourType"/>
                            : ${tour.tourType.value}</h4>

                        <h4><@spring.message code="table.country"/>
                            : ${tour.country.name} </h4>

                        <h4><@spring.message code="table.hotel"/> :
                            <a href="<@spring.url'/hotel-${tour.hotel.id}'/>">
                                ${tour.hotel.name} ${tour.hotel.stars} * </a>
                        </h4>
                    </td>

                    <td style="width:100px">
                        <h4><@spring.message code="table.date"/> : ${tour.date}
                        </h4>
                        <h4><@spring.message code="table.description"/> : </h4>
                        <h5> ${tour.description}</h5>
                    </td>
                </tr>
                </tbody>
            </table>
            <br><br>
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
                                            <@spring.message code="table.date"/>
                                            : ${review.date}
                                            <br><br>
                                            <@spring.message code="text.login"/>
                                            :
                                            <a href="<@spring.url'/user-${review.user.id}'/>">
                                                ${review.user.login}</a>
                                        </td>

                                        <td align="left" style="width: 580px;">
                                            <@spring.message code="table.text"/>
                                            :
                                            <br> ${review.text}<br><br>
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
                <h3>This tour has no reviews</h3>
            </#if>

        <#else>
            <br><br><br>
            <h3>This tour doesn't exist</h3>
            <br><br><br>
        </#if>

    </div>
</@bd.body>
</html>