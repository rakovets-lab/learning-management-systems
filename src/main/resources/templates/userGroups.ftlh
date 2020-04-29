<#import "parts/common.ftlh" as c>

<@c.page>
    <h3>${group.getGroupName()}</h3>

    <div class="container">
        <form method="post" action="/group/join" xmlns="http://www.w3.org/1999/html">
            <label for="sel2">Select user to add into the group</label>
            <div class="form-group">
                <select name="userId" class="form-control" id="sel2">
                    <#list users! as user>
                        <option value="${user.userId}" name="id">${user.username}</option>
                    </#list>
                </select>
            </div>
            <input name="groupId" type="hidden" value="${group.groupId}"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div>
                <button type="submit" class="btn btn-primary">Add user to ${group.getGroupName()}</button>
            </div>
        </form>
    </div>

    <div class="container my-3">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <div class="card-title">Students count in current group</div>
                    <h3 class="card-text">
                        <a href="/groupManagement/${group.groupId}/list">${studentCount}</a>
                    </h3>
                </div>
            </div>
        </div>
    </div>
</@c.page>