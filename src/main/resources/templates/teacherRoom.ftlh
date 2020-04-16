<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
    Teacher room
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new Message
    </a>
    <div class="collapse <#if homeWork??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control ${(titleError??)?string('is-invalid', '')}"
                           value="<#if homeWork??>${homeWork.title}</#if>" name="title" placeholder="Введите название"/>
                    <#if titleError??>
                        <div class="invalid-feedback">
                            ${titleError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add home work</button>
                </div>
            </form>
        </div>
    </div>

    <#list solutions! as solution>
        <div class="card my-3">
            <div class="card-footer text-muted">
                ${solution.authorName}
            </div>
            <#if solution.filename?? && isTeacher>
                <div class="m-2">
                    <a href="/solution/${solution.filename}">${solution.filename} </a>
                </div>
            </#if>
        </div>
    <#else>
        No files with solution
    </#list>
</@c.page>
