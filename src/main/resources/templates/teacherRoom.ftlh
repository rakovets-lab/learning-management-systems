<#import "parts/common.ftlh" as c>

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
                    <input type="text" class="form-control ${(solutionError??)?string('is-invalid', '')}"
                           value="<#if homeWork??>${homeWork.solution}</#if>" name="solution" placeholder="Введите условие задания"/>
                    <#if solutionError??>
                        <div class="invalid-feedback">
                            ${solutionError}
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
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
</@c.page>