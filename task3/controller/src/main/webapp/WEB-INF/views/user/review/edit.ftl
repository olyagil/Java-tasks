<#import "../../tags/head.ftl" as hd>
<#import "../../tags/body.ftl" as bd>
<#import "../../tags/pagination.ftl" as pag>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Edit review"/>
<@bd.body>
    <div class="container">
        <#include "../../sidebar.ftl">
        <h2><@spring.message code="button.edit"/> </h2>
        <div class="uui-login-panel">
            <div class="login-panel-body">
                <div class="login-panel-section">

                    <#if message??>
                        <div style="color:green;font-style:italic;">
                            ${message}
                        </div>
                    </#if>

                    <form action="<@spring.url '/user/update-review'/>"
                          method="post"
                          class="epam-login">

                        <p><@spring.message code="table.tour"/> </p>
                        <select class="selectpicker uui-form-element"
                                name="tour_id"
                                modelattribute="tour" required>
                            <option value="${review.tour.id}">
                                ${review.tour.tourType} ${review.tour.hotel.name}
                                ${review.tour.country.name}
                            </option>
                        </select>

                        <p><@spring.message code="table.text"/> :</p>
                        <textarea class="form-control" rows="10" cols="15"
                                  id="text" placeholder="Enter text"
                                  name="text" required>${review.text}
                    </textarea>

                        <br>

                        <input type="hidden" value="${review.id}" name="review_id">
                        <button class="uui-button large raspberry">
                            <@spring.message code="button.save"/>
                        </button>
                        <p>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@bd.body>
</html>