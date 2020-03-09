<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>
        <@l.logout />
        <span><a href="/user">User list</a></span>
    </div>
    <div>
    <form method="post">
        <label>
            <input type="text" name="solution" placeholder="Введите условие задания"/>
        </label>
        <label>
            <input type="text" name="title" placeholder="Введите название"/>
        </label>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Добавить</button>
    </form>
    <div>Список</div>
    <form method="get" action="/main">
        <label>
            <input type="text" name="filter" value="${filter!}" >
        </label>
        <button type="submit">Найти</button>
    </form>
    <#list hw as message>
        <div>
            <b>${message.id}</b>
            <span>${message.title}</span>
            <i>${message.solution}</i>
            <strong>${message.authorName}</strong>
        </div>
    <#else>
        No message
    </#list>
    <div/>
</@c.page>