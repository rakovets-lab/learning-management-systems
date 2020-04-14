<#import "parts/common.ftlh" as c>

<@c.page>
    <h3>${group.getGroupName()}</h3>

    <div class="container">
        <form method="post" enctype="multipart/form-data" xmlns="http://www.w3.org/1999/html">
            <#list users! as user>
                <div class="form-group">
                    <label for="sel2">Select user</label>
                    <select class="form-control" id="sel2">
                        <option value="${user.userId}" name="id">${user.username}</option>
                    </select>
                </div>
                <div>
                    <a class="btn btn-info" href="/joinInto/${group.groupId}/${user.userId}">Join into
                        the ${group.getGroupName()} group</a>
                </div>
            </#list>
        </form>
    </div>

    <div class="container my-3">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <div class="card-title">Subscribers</div>
                    <h3 class="card-text">
                        <a href="/groupManagement/${group.groupId}/list">${subscribersCount}</a>
                    </h3>
                </div>
            </div>
        </div>
    </div>
</@c.page>