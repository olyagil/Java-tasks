<#macro pagination url>
<ul class="uui-pagination" align="center">
    <li class="actions-wrapper">
        <ul class="pagination-items">
            <#if (currentPage>1)>
            <li class="first-page">
                <a href="${url}?currentPage=1">
                    <span>First</span>
                </a>
            </li>
            <#else>
            <li class="first-page disable">
                <a href="#">
                    <span>First</span>
                </a>
            </li>
        </#if>
        <#if (currentPage>1)>
    <li class="prev-page">
        <a href="${url}?currentPage=${currentPage-1}">
            <i class="fa fa-angle-left"></i>
        </a>
    </li>
    <#else>
    <li class="prev-page disable">
        <a href="#">
            <i class="fa fa-angle-left"></i>
        </a>
    </li>
</#if>
</ul>
</li>
<li class="pages-wrapper">
    <ul class="pagination-items">
        <#if (currentPage>1)>
        <li>
            <a href="${url}?currentPage=${currentPage-1}">${currentPage-1}</a>
        </li>
    </#if>
<li class="active">
    <a href="${url}?currentPage=${currentPage}">${currentPage}</a>
</li>
<#if (currentPage<allPages)>
<li>
    <a href="${url}?currentPage=${currentPage+1}">${currentPage+1}</a>
</li>
</#if>
</ul>
</li>
<li class="actions-wrapper">
    <ul class="pagination-items">
        <#if (currentPage<allPages)>
        <li class="next-page">
            <a href="${url}?currentPage=${currentPage+1}">
                <i class="fa fa-angle-right"></i>
            </a>
        </li>
        <#else>
        <li class="next-page disable">
            <a href="#">
                <i class="fa fa-angle-right"></i>
            </a>
        </li>
    </#if>
    <#if (currentPage=allPages)>
<li class="last-page disable">
    <a href="#">
        <span>Last</span>
    </a>
</li>
<#else>
<li class="last-page">
    <a href="${url}?currentPage=${allPages}">
        <span>Last</span>
    </a>
</li>
</#if>
</ul>
</li>
</ul>
</#macro>

