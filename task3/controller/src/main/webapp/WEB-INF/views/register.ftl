<#import "tags/head.ftl" as hd>
<#import "tags/body.ftl" as bd>
<#import "tags/pagination.ftl" as pag>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Registration"/>
<@bd.body>

    <div class="container" align="center">
        <br><br><br>
        <h2><@spring.message code="button.signup"/></h2>
        <div class="uui-login-panel">
            <div class="login-panel-body">
                <div class="login-panel-section">

                    <form action="<@spring.url '/register'/>" method="post" class="epam-login">

                        <#if error??>
                            <div style="color:red;font-style:italic;">
                                ${error}
                            </div>
                        </#if>

                        <p><@spring.message code="button.login"/></p>
                        <div class="uui-input-group input-login default">
                            <input type="text" id="login" name="login"
                                   required class="uui-form-element" placeholder="Enter login"
                                   maxlength="50"/>
                        </div>

                        <p><@spring.message code="text.password"/></p>
                        <div class="uui-input-group input-login default">
                            <input type="password" id="password" name="password"
                                   required class="uui-form-element" placeholder="Enter password"
                                   maxlength="50"/>
                        </div>

                        <br> <br>
                        <button class="uui-button large raspberry">
                            <@spring.message code="button.signup"/>
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@bd.body>
</html>