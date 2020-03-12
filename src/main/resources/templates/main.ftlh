<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="card-columns">
        <#list homeWorks as homeWork>
            <div class="card my-3">
                <#if homeWork.filename??>
                    <img src="/img/${homeWork.filename}" class="card-img-top">
                </#if>
                <div class="card-footer text-muted">
                    ${homeWork.authorName}
                </div>
                <div class="m-2">
                    <span>${homeWork.title}</span>
                </div>
                <div class="m-2">
                    <i>${homeWork.solution}</i>
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>