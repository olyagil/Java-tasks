<#import "../../tags/head.ftl" as hd>
<#import "../../tags/body.ftl" as bd>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Add tour"/>
<@bd.body>
    <div class="container">
        <#include "../../sidebar.ftl"> <br>
        <h2><@spring.message code="button.add"/></h2>

        <#if error??>
            <div style="color:red;font-style:italic;">
                ${error}
            </div>
        </#if>

        <form action="<@spring.url '/admin/save-tour'/>" method="post"
              enctype="multipart/form-data">
            <table>
                <tbody>
                <tr>
                    <td style="width:100px">
                        <p><@spring.message code="table.duration"/> </p>
                        <input type="text" id="duration" name="duration"
                               required class="uui-form-element"
                               maxlength="50"/>

                        <p><@spring.message code="table.cost"/></p>
                        <input type="text" id="cost" name="cost"
                               required class="uui-form-element"
                               maxlength="50"/>

                        <p><@spring.message code="table.tourType"/> </p>
                        <select class="selectpicker uui-form-element"
                                name="tourType"
                                required>
                            <#list types as type>
                                <option value="${type}">${type.value}</option>
                            </#list>
                        </select>

                        <p><@spring.message code="table.country"/>: </p>
                        <select class="selectpicker uui-form-element"
                                name="country_id"
                                required>
                            <#list countries as country>
                                <option value="${country.id}">${country.name}</option>
                            </#list>
                        </select>
                        <p><@spring.message code="table.hotel"/>: </p>
                        <select class="selectpicker uui-form-element"
                                name="hotel_id"
                                required>
                            <#list hotels as hotel>
                                <option value="${hotel.id}">
                                    ${hotel.name} ${hotel.stars} *
                                </option>
                            </#list>
                        </select>
                        <p><@spring.message code="table.photo"/></p>
                        <input type="file" name="img" id="photo" required>
                    </td>

                    <td style="width:100px">
                        <label for="date"><@spring.message code="table.date"/>
                        </label>
                        <input type="date" id="date" class="form-control"
                               name="date" required>

                        <p><@spring.message code="table.description"/>:</p>
                        <textarea class="form-control" rows="8" cols="7"
                                  id="description" name="description"
                                  required>
                        </textarea>

                        <br><br>
                        <button class="uui-button large raspberry">
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