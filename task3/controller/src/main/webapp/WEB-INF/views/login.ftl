<#import "tags/head.ftl" as hd>
<#import "tags/body.ftl" as bd>
<#import "tags/pagination.ftl" as pag>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Login"/>
<@bd.body>
    <div class="container" align="center">
        <br><br><br>
        <h2><@spring.message code="button.login"/></h2>

        <div class="uui-login-panel">
            <div class="login-panel-body">
                <div class="login-panel-section">
                    <form action="loginCheck" method="post" class="epam-login">

                        <#if error??>
                            <div style="color:red;font-style:italic;">
                                ${error}
                            </div>
                        </#if>

                        <#if logout??>
                            <div style="color:green;font-style:italic;">
                                ${logout}
                            </div>
                        </#if>

                        <#if message??>
                            <div style="color:green;font-style:italic;">
                                ${message}
                            </div>
                        </#if>


                        <p><@spring.message code="button.login"/></p>
                        <div class="uui-input-group input-login default">
                            <input type="text" id="username" name="username"
                                   required class="uui-form-element"
                                   maxlength="50"/>
                        </div>

                        <p><@spring.message code="text.password"/></p>
                        <div class="uui-input-group input-login default">
                            <input type="password" id="password" name="password"
                                   required class="uui-form-element"
                                   maxlength="50"/>
                        </div>
                        <p>
                        <p>
                        <td><@spring.message code="button.remember"/></td>
                        <td><input type="checkbox" name="remember-me"/></td>

                        <button class="uui-button large raspberry">
                            <@spring.message code="button.login"/>
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@bd.body>
</html>