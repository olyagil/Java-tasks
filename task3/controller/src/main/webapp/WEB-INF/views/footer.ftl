<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Footer</title>
    <link rel="stylesheet"
          href="<@spring.url'/static/fonts/font-awesome/css/font-awesome.min.css'/>"/>

</head>
<body>
<footer>
    <div class="uui-promo-footer">
        <div class="footer-logo-copyright">
            <div class="epam-logo">
                <img src="<@spring.url'/static/images/Logo_Epam_Color.svg'/>" alt="EPAM"/>
            </div>
            <p class="copyright">© 2019 EPAM Systems.</p>
        </div>
        <div class="footer-navigation">
            <ul class="uui-footer-navigation">
                <li><a href="<@spring.url '/main'/>"><@spring.message code="button.home"/></a></li>
                <li><a href="<@spring.url '/tourList'/>"><@spring.message code="button.tours"/></a></li>
                <li><a href="<@spring.url '/hotelList'/>"><@spring.message code="button.hotels"/></a></li>
                <li><a href="<@spring.url '/reviewList'/>"><@spring.message code="button.reviews"/></a></li>
            </ul>
        </div>
        <div class="footer-icon-navigation">
            <ul class="uui-footer-navigation icon-navigation">
                <li><a href="https://vk.com/id135844805"><i
                                class="fa fa-vk"></i></a></li>
                <li><a href="https://www.facebook.com/olga.gil.12"><i
                                class="fa fa-facebook-square"></i></a></li>
            </ul>
        </div>
    </div>
</footer>
</body>
</html>
