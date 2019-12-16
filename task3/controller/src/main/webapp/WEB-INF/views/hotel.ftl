<#import "tags/head.ftl" as hd>
<#import "tags/body.ftl" as bd>
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<@hd.head "Add tour"/>
<@bd.body>
    <div class="container" align="center">

        <#if hotel??>
            <table>
                <tbody>
                <tr>
                    <td style="width:150px">
                        <h4><@spring.message code="table.name"/>
                            : ${hotel.name}</h4>

                        <h4><@spring.message code="table.stars"/>
                            : ${hotel.stars}</h4>

                        <h4><@spring.message code="table.features"/>:
                            <ul>
                                <#list hotel.features as feature>
                                    <li>  ${feature.value}</li>
                                </#list>
                            </ul>
                        </h4>

                        <h4><@spring.message code="table.website"/>:</h4>
                        <a href="${hotel.website}"> ${hotel.website}</a>


                    </td>

                    <td>
                        <div id="map"
                             style="width:100%;height:400px;"></div>
                    </td>
                </tr>
                </tbody>
            </table>
        <#else>
            <h3>This hotel doesn't exist</h3>
        </#if>

    </div>
    <script>
        function myMap() {
            var coordinates = {lat: ${hotel.latitude}, lng: ${hotel.longitude}};
            var map = new google.maps.Map(document.getElementById('map'), {
                center: coordinates,
                zoom: 5
            });
            var marker = new google.maps.Marker({
                position: coordinates,
                map: map
            });
        }
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD4HzIzLnfByDAS3egdwM8TOl0fMAjjSFE&callback=myMap"></script>
</@bd.body>
</html>


