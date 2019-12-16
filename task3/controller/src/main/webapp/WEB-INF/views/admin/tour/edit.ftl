<#import "../../tags/head.ftl" as hd>
<#import "../../tags/body.ftl" as bd>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Edit tour ${tour.id} "/>
<@bd.body>
    <#include "../../sidebar.ftl">

    <div class="container">

        <h2><@spring.message code="button.edit"/> #${tour.id} </h2>
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

        <form action="<@spring.url '/admin/update-tour'/>" method="post">
            <table>
                <tbody>
                <tr>
                    <td style="width:100px">
                        <img src="data:image/png;base64,${tour.photo}"
                             class="avatar img-circle img-thumbnail img-responsive"
                             width="200" height="200" alt="avatar">
                    </td>

                    <td style="width:100px">
                        <p><@spring.message code="table.duration"/>:</p>
                        <input type="text" id="duration" name="duration"
                               required class="uui-form-element"
                               placeholder="Enter duration"
                               value="${tour.duration}"
                               maxlength="50"/>

                        <p><@spring.message code="table.cost"/>:</p>
                        <input type="text" id="cost" name="tour_cost"
                               required class="uui-form-element"
                               placeholder="Enter cost"
                               value="${tour.cost}"
                               maxlength="50"/>

                        <p><@spring.message code="table.tourType"/></p>
                        <select class="selectpicker uui-form-element"
                                id="tourType"
                                name="tourType" required>
                            <#list types as type>
                                <#if tour.tourType.value==type.value>
                                    <option selected value="${type}">
                                        ${type.value} </option>
                                </#if>
                                <#if tour.tourType.value!=type.value>
                                    <option value="${type}"> ${type.value} </option>
                                </#if>
                            </#list>
                        </select>

                        <p><@spring.message code="table.country"/></p>
                        <select class="selectpicker uui-form-element"
                                name="country_id" required>
                            <#list countries as country>
                                <#if tour.country.name==country.name>
                                    <option selected value="${country.id}">
                                        ${country.name} </option>
                                </#if>
                                <#if tour.country.name!=country.name>
                                    <option value="${country.id}">${country.name} </option>
                                </#if>
                            </#list>
                        </select>

                        <p><@spring.message code="table.hotel"/> </p>
                        <select class="selectpicker uui-form-element"
                                name="hotel_id" required>
                            <#list hotels as hotel>
                                <#if tour.hotel.name==hotel.name>
                                    <option selected value="${hotel.id}">
                                        ${hotel.name} ${hotel.stars}</option>
                                </#if>
                                <#if tour.hotel.name!=hotel.name>
                                    <option value="${hotel.name}">${hotel.name}
                                        ${hotel.stars} *
                                    </option>
                                </#if>
                            </#list>
                        </select>

                    </td>

                    <td style="width:100px">
                        <@spring.message code="table.date"/>
                        <#--TODO-->
                        <input id="date" type="date" class="form-control"
                               name="date" value="${tour.date}" required>
                        <#--                        <input id="date" type="date" class="form-control"  name="date" value="2017-06-01" required>-->
                        <p><@spring.message code="table.description"/></p>
                        <textarea class="form-control" rows="10" cols="50"
                                  id="description"
                                  placeholder="Enter description"
                                  name="description"
                                  required>${tour.description}</textarea>
                        <br>
                        <input type="hidden" name="tour_id" value="${tour.id}">
                        <button type="submit"
                                class="uui-button large raspberry">
                            <@spring.message code="button.save"/>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</@bd.body>
</html>
