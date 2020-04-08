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

    <div class="container">
        <form>
            <div class="form-group">
                <label for="sel1">Select the group to which you want to add users</label>
                <select class="form-control" id="sel1">
                    <#list groups! as group>
                        <option value="${group.groupId}" name="id">${group.groupName}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label for="sel2">Select user</label>
                <select class="form-control" id="sel2">
                    <#list users! as user>
                        <option value="${user.id}" name="id">${user.username}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</@c.page>