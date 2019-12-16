<#import "../../tags/head.ftl" as hd>
<#import "../../tags/body.ftl" as bd>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Add hotel"/>
<@bd.body>
    <div class="container">
        <form action="<@spring.url '/admin/save-hotel'/>" method="post">
            <#include "../../sidebar.ftl">
            <h2><@spring.message code="button.add"/></h2>
            <table>
                <tbody>
                <tr>
                    <td style="width:200px">
                        <label><@spring.message code="table.name"/></label>
                        <input id="name" name="name" type="text"
                               class="uui-form-element" maxlength="50"
                               required/>
                        <br>
                        <label><@spring.message code="table.stars"/></label>
                        <input id="stars" name="stars" type="text"
                               class="uui-form-element" maxlength="1" required/>
                        <br>
                        <label><@spring.message code="table.website"/></label>
                        <br>
                        <input id="website" name="website" type="text"
                               class="uui-form-element" maxlength="100"
                               required/>
                        <br>
                        <label><@spring.message code="table.latitude"/></label>
                        <input id="latitude" name="latitude" type="text"
                               class="uui-form-element" maxlength="8" required/>
                        <br>
                        <label><@spring.message code="table.longitude"/></label>
                        <input id="longitude" name="longitude" type="text"
                               class="uui-form-element" maxlength="8" required/>
                    </td>
                    <td style="width:100px"></td>
                    <td>
                        <label><@spring.message code="table.features"/></label>
                        <#list features as feature>
                            <p class="uui-checkbox">
                                <input type="checkbox" name="${feature.value}"
                                       value="${feature.value}"/>
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
        <br><br><br>
    </div>
</@bd.body>
</html>