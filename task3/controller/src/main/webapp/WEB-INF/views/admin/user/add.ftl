<#import "../../tags/head.ftl" as hd>
<#import "../../tags/body.ftl" as bd>
<#import "../../tags/pagination.ftl" as pag>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Add user "/>
<@bd.body>
    <#include "../../sidebar.ftl">
    <div class="container" align="center">

        <h2><@spring.message code="button.add"/></h2>
        <div class="uui-login-panel">
            <div class="login-panel-body">
                <div class="login-panel-section">
                    <#if error??>
                        <div style="color:red;font-style:italic;">
                            ${error}
                        </div>
                    </#if>
                    <form action="<@spring.url '/admin/save-user'/>"
                          method="post" class="epam-login">

                        <p><@spring.message code="text.login"/></p>
                        <div class="uui-input-group input-login default">
                            <input type="text" id="login" name="login"
                                   required class="uui-form-element"
                                   placeholder="Enter login"
                                   maxlength="50"/>
                        </div>
                        <p><@spring.message code="text.password"/></p>
                        <div class="uui-input-group input-login default">
                            <input type="password" id="password" name="password"
                                   required class="uui-form-element"
                                   placeholder="Enter password"
                                   maxlength="50"/>
                        </div>
                        <br>
                        <p><@spring.message code="table.role"/> </p>
                        <select class="selectpicker uui-form-element"
                                name="role"
                                required>
                            <option value="USER"><@spring.message code="role.user"/></option>
                            <option value="ADMIN"><@spring.message code="role.admin"/></option>
                        </select>
                        <p>
                        <p>

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