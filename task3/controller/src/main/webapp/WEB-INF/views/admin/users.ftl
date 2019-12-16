<#import "../tags/head.ftl" as hd>
<#import "../tags/body.ftl" as bd>
<#import "../tags/pagination.ftl" as pag>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Users"/>
<@bd.body>
    <div class="container">

        <#include "../sidebar.ftl">
        <h2><@spring.message code="button.users"/>:</h2>

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

        <form action="<@spring.url '/admin/add-user'/>" method="get"
              modelAttribute="user" align="right">
            <button class="uui-button medium blue" type="submit">
                <@spring.message code="button.add"/>
            </button>
        </form>
        <br>
        <form action="<@spring.url '/admin/users'/>" method="get"
              modelAttribute="user" align="right">

            <div class="search-field">
                <label for="search"><@spring.message code="button.find"/>
                    :</label>
                <input type="search" class="form-control uui-search"
                       placeholder="Search" name="search-login"/>
                <button class="uui-button medium blue" type="submit">
                    <@spring.message code="button.find"/>
                </button>
            </div>
        </form>

        <br>
        <#if users?has_content>
            <table class="uui-table dynamic stripe records-per-page search showing-pages paging">
                <thead>
                <tr>
                    <th><@spring.message code="table.id"/></th>
                    <th><@spring.message code="text.login"/></th>
                    <th><@spring.message code="table.role"/></th>
                    <th><@spring.message code="button.edit"/></th>
                    <th><@spring.message code="button.delete"/></th>
                </tr>
                </thead>
                <tbody>
                <#list users as user>
                    <tr>
                        <td>${user.id}</td>
                        <td><a href="<@spring.url'/user-${user.id}'/>">
                                ${user.login}</a></td>
                        <td>${user.role}</td>
                        <td>
                            <form action="<@spring.url '/admin/edit-user-${user.id}'/>"
                                  method="get"
                                  modelAttribute="user">
                                <button class="uui-button small lime-green"
                                        type="submit">
                                    <@spring.message code="button.edit"/>
                                </button>
                            </form>
                        </td>
                        <td>
                            <form action="<@spring.url '/admin/delete-user'/>"
                                  method="post"
                                  modelAttribute="user">
                                <input type="hidden" value="${user.id}"
                                       name="id">
                                <button class="uui-button small raspberry"
                                        type="submit">
                                    <@spring.message code="button.delete"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>

            <br>
            <@pag.pagination "users"/>
        <#else>
            <h3 align="center"> No user was found</h3>
        </#if>
    </div>
</@bd.body>
</html>