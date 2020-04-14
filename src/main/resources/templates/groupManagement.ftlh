<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data" xmlns="http://www.w3.org/1999/html">
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if group??>${group.groupName}</#if>" name="groupName"
                       placeholder="Введите название группы"/>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>

    <ul class="list-group">
        <#list groups as group>
            <li class="list-group-item">
                <a href="/groupManagement/${group.getGroupId()}">${group.getGroupName()}</a>
            </li>
        </#list>
    </ul>
</@c.page>