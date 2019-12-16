<#import "../../tags/head.ftl" as hd>
<#import "../../tags/body.ftl" as bd>
<#import "../../tags/pagination.ftl" as pag>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Add review "/>
<@bd.body>
    <div class="container">
        <#include "../../sidebar.ftl">

    <h2><@spring.message code="button.add"/> </h2>
    <div class="uui-login-panel">
        <div class="login-panel-body">
            <div class="login-panel-section">

                <#if message??>
                    <div style="color:green;font-style:italic;">
                        ${message}
                    </div>
                </#if>

                <form action="<@spring.url '/user/save-review'/>" method="post"
                      class="epam-login">

                    <p><@spring.message code="table.tour"/></p>
                    <select class="selectpicker uui-form-element" name="tour_id"
                            required>
                        <option value="${tour.id}">${tour.tourType} ${tour.hotel.name} ${tour.country.name}</option>
                    </select>

                    <p><@spring.message code="table.text"/>:</p>
                    <textarea class="form-control" rows="10" cols="15"
                              id="text" placeholder="Enter text"
                              name="text" required></textarea>
                    <br>

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