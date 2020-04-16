<#import "parts/common.ftlh" as c>

<@c.page>
    <ul class="list-group">
        <#list usersFromGroup.getUsers() as user>
            <li class="list-group-item">
                <a href="/groupManagement/${user.userId}">${user.username}</a>
            </li>
        </#list>
    </ul>
</@c.page>
