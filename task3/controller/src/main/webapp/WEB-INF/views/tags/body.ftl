<#import "head.ftl" as hd>
<#macro body>
<@hd.head/>
    <style>
        .wrapper{
            height: auto;
            min-height: 100%;
            padding: 7% 5% 60px;
            margin-bottom: 0;
        }
    </style>
<body>
    <header>
        <#include "../header.ftl">
    </header>

        <div class="wrapper">
                <#nested>
        </div>

    <footer>
        <#include "../footer.ftl">
    </footer>
</body>
</#macro>