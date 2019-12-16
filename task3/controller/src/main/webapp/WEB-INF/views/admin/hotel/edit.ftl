<#import "../../tags/head.ftl" as hd>
<#import "../../tags/body.ftl" as bd>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Hotels"/>
<@bd.body>
<div class="container">
    <#include "../../sidebar.ftl">
    <h2><@spring.message code="button.add"/></h2>
    <form action="<@spring.url '/admin/update-hotel'/>" method="post">
        <table>
            <tbody>
            <tr>
                <td width="200px">
                    <input type="hidden" name="id" value="${hotel.id}">
                    <label><@spring.message code="table.name"/></label>
                    <input id="name" name="name" type="text" maxlength="50"
                           class="uui-form-element" value="${hotel.name}"
                           required/> <br>

                    <label><@spring.message code="table.stars"/></label>
                    <input id="stars" name="stars" type="text" maxlength="1"
                           class="uui-form-element" value="${hotel.stars}"
                           required/> <br>

                    <label><@spring.message code="table.website"/></label> <br>
                    <input id="website" name="website" type="text"
                           value="${hotel.website}" class="uui-form-element"
                           maxlength="100" required/> <br>

                    <label><@spring.message code="table.latitude"/></label>
                    <input id="latitude" name="latitude" type="text"
                           value="${hotel.latitude}" class="uui-form-element"
                           maxlength="8" required/> <br>

                    <label><@spring.message code="table.longitude"/></label>
                    <input id="longitude" name="longitude" type="text"
                           value="${hotel.longitude}" class="uui-form-element"
                           maxlength="8" required/>
                </td>
                <td width="100 px">
                    <label><@spring.message code="table.features"/></label>
                    <#list hotel.features as feature>
                        <p class="uui-checkbox">
                            <input type="checkbox" name="${feature.value}"
                                   id="${feature.value}Cb" ${hotel.features?seq_contains(feature)?string("checked", "")}/>
                            <label for="${feature.value}Cb">${feature.value}</label>
                        </p>
                    </#list>
                </td>
            </tr>
            </tbody>
        </table>
        <br>
        <button type="submit"
                class="uui-button blue"><@spring.message code="button.save"/></button>
    </form>
    </@bd.body>
</div>
</html>