<#import "../../tags/head.ftl" as hd>
<#import "../../tags/body.ftl" as bd>
<#import "../../tags/pagination.ftl" as pag>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Edit user ${user.login} "/>
<@bd.body>

    <#include "../../sidebar.ftl">
    <div class="container" align="center">

        <h2><@spring.message code="button.edit"/> ${user.login} </h2>
        <div class="uui-login-panel">
            <div class="login-panel-body">
                <div class="login-panel-section">
                    <form action="<@spring.url '/admin/update-user'/>"
                          method="post" class="epam-login">
                        <#if message??>
                            <div style="color:green;font-style:italic;">
                                ${message}
                            </div>
                        </#if>

                        <p><@spring.message code="text.login"/>: </p>
                        <div class="uui-input-group input-login default">
                            <input type="text" id="login" name="login"
                                   required class="uui-form-element"
                                   placeholder="Enter login"
                                   maxlength="50" value="${user.login}"/>
                        </div>
                        <br>
                        <p><@spring.message code="table.role"/> </p>
                        <select class="selectpicker uui-form-element"
                                name="role" required>
                            <#if user.role.value=='USER'>
                                <option value="USER"
                                        selected><@spring.message code="role.user"/>
                                </option>
                                <option value="ADMIN"><@spring.message code="role.admin"/></option>
                            <#else>
                                <option value="USER"><@spring.message code="role.user"/></option>
                                <option value="ADMIN" selected>
                                    <@spring.message code="role.admin"/>
                                </option>

                            </#if>
                        </select>
                        <p>
                        <p>
                            <input type="hidden" value="${user.id}" name="id">
                            <button class="uui-button large raspberry">
                                <@spring.message code="button.save"/>
                            </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@bd.body>


</html>