<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
    <div class="card-columns">
        <#list homeWorks! as homeWork>
            <div class="card my-3">
                <div class="card-footer text-muted">
                    ${homeWork.authorName}
                </div>
                <div class="m-2">
                    <span>${homeWork.title}</span>
                </div>
                <#if homeWork.filename?? && isUser>
                    <div class="m-2">
                        <a href="/hw/${homeWork.filename}">${homeWork.filename} </a>
                    </div>
                    <form method="post" enctype="multipart/form-data">
                        <div class="custom-file">
                            <input type="file" name="file" id="customFile">
                            <label class="custom-file-label" for="customFile">Choose file</label>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Добавить</button>
                        </div>
                    </form>
                </#if>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>
