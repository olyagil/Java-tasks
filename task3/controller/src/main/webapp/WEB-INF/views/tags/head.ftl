<#ftl encoding="utf-8">
<#import "/spring.ftl" as spring />
<#macro head tit="">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>Travel Agency : ${tit}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet"
          href="<@spring.url '/static/fonts/font-awesome/css/font-awesome.min.css'/>"/>
    <link rel="stylesheet/less" type="text/css"
          href="<@spring.url'/static/less/uui-core.less'/>"/>
    <link rel="stylesheet"
          href="<@spring.url'/static/bootstrap/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<@spring.url'/static/css/uui-all.css'/>"/>
    <link rel="stylesheet" href="<@spring.url'/static/css/uui-all.min.css'/>"/>

    <link rel="stylesheet"
          href="<@spring.url'/static/css/lib/components/jquery.mCustomScrollbar.min.css'/>"/>
    <link rel="stylesheet"
          href="<@spring.url'/static/js/lib/components/DataTables-1.10.2/css/jquery.dataTables.min.css'/>"/>
    <link rel="stylesheet"
          href="<@spring.url'/static/css/uui-panels.min.css'/>"/>


    <link rel="stylesheet/less" type="text/css"
          href="<@spring.url'/static/less/uui-buttons.less'/>"/>
    <link rel="stylesheet/less" type="text/css"
          href="<@spring.url'/static/less/uui-search.less'/>"/>
    <link rel="stylesheet/less" type="text/css"
          href="<@spring.url'/static/less/uui-tables.less'/>"/>
    <link rel="stylesheet/less" type="text/css"
          href="<@spring.url'/static/less/uui-pagination.less'/>"/>
    <link rel="stylesheet/less" type="text/css"
          href="<@spring.url'/static/less/uui-panels.less'/>"/>
    <link rel="stylesheet/less" type="text/css"
          href="<@spring.url'/static/less/uui-form-elements.less'/>"/>
    <link rel="stylesheet/less" type="text/css"
          href="<@spring.url'/static/less/uui-login-panel.less'/>"/>
    <link rel="stylesheet/less" type="text/css"
          href="<@spring.url'/static/less/uui-accordion.less'/>"/>
    <script type="text/javascript"
            src="<@spring.url'/static/bootstrap/js/bootstrap.min.js'/>"></script>

    <script type="text/javascript"
            src="<@spring.url'/static/js/lib/jquery-1.12.0.min.js'/>"></script>

    <script src="<@spring.url'/static/js/lib/less.js'/>"
            type="text/javascript"></script>
    <script src="<@spring.url'/static/js/uui-core.min.js'/>"
            type="text/javascript"></script>
    <script src="<@spring.url'/static/js/uui-global-search.min.js'/>"
            type="text/javascript"></script>
    <script src="<@spring.url'/static/js/lib/components/jquery.mCustomScrollbar.concat.min.js'/>"></script>
    <script src="<@spring.url'/static/js/uui/tests/uui-global-search-spec.js'/>"
            type="text/javascript"></script>
    <script src="<@spring.url'/static/js/lib/components/DataTables-1.10.2/js/jquery.dataTables.min.js'/>"></script>



    <script src="<@spring.url'/static/highlight/highlight.pack.js'/>"></script>
    <script src="<@spring.url'/static/js/uui/uui-core.js?v=1.2.1.1'/>"></script>
    <script src="<@spring.url'/static/js/uui/uui-core.js'/>"></script>
    <script src="<@spring.url'/static/js/lib/backbone/underscore.min.js'/>"></script>
    <script src="<@spring.url'/static/js/lib/backbone/backbone.min.js'/>"></script>
    <script src="<@spring.url'/static/js/app.js?v=1.2.1.1'/>"></script>

    <#nested>
</head>
</#macro>
