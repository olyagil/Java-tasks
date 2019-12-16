<#import "../tags/head.ftl" as hd>
<#import "../tags/body.ftl" as bd>
<#import "../tags/pagination.ftl" as pag>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Tours"/>
<@bd.body>
    <div class="container">
        <#include "../sidebar.ftl">
        <h2><@spring.message code="button.tours"/>:</h2>

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

        <form action="<@spring.url '/admin/add-tour'/>" method="get"
              modelAttribute="tour" align="right">
            <button class="uui-button medium blue" type="submit">
                <@spring.message code="button.add"/>
            </button>
        </form>

        <form action="<@spring.url '/admin/find-tours'/>" method="get"
              modelAttribute="tour" align="right">
            <p></p>
            <div class="search-field">
                <label><@spring.message code="button.find"/> tour by
                    type</label>
                <input type="search" class="form-control uui-search"
                       placeholder="Search" name="tourType"/>
                <button class="uui-button medium blue" type="submit">
                    <@spring.message code="button.find"/>
                </button>
            </div>
        </form>
        <br>
        <#if tours?has_content>
            <table class="uui-table dynamic stripe records-per-page search showing-pages paging">
                <thead>
                <tr>
                    <th><@spring.message code="table.id"/></th>
                    <th><@spring.message code="table.photo"/></th>
                    <th><@spring.message code="table.date"/></th>
                    <th><@spring.message code="table.duration"/></th>
                    <th><@spring.message code="table.description"/></th>
                    <th><@spring.message code="table.cost"/></th>
                    <th><@spring.message code="table.tourType"/></th>
                    <th><@spring.message code="table.hotel"/></th>
                    <th><@spring.message code="table.country"/></th>
                    <th><@spring.message code="button.edit"/></th>
                    <th><@spring.message code="button.delete"/></th>
                </tr>
                </thead>
                <tbody>
                <#list tours as tour>
                    <tr>
                        <td>${tour.id}</td>
                        <td style="width:50px">
                            <img src="data:image/png;base64,${tour.photo}"
                                 alt="EPAM" width="100" height="100">
                        </td>
                        <td>${tour.date}</td>
                        <td>${tour.duration}</td>
                        <td>${tour.description}</td>
                        <td>${tour.cost}</td>
                        <td>${tour.tourType.value}</td>
                        <td><a href="<@spring.url'/hotel-${tour.hotel.id}'/>">
                                ${tour.hotel.name} ${tour.hotel.stars} * </a>
                        </td>
                        <td>${tour.country.name}</td>

                        <td>
                            <form action="edit-tour-${tour.id}" method="get"
                                  modelAttribute="tour">
                                <button class="uui-button small lime-green"
                                        type="submit">
                                    <@spring.message code="button.edit"/>
                                </button>
                            </form>
                        </td>
                        <td>
                            <form action="<@spring.url '/admin/delete-tour'/>"
                                  method="post"
                                  modelAttribute="tour">
                                <input type="hidden" name="id"
                                       value="${tour.id}">
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
            <#if  (tours?size < 11)>
                <@pag.pagination "tours"/>
            </#if>
        <#else>
            <h3 align="center"> No tours was found</h3>
        </#if>
    </div>
</@bd.body>
</html>