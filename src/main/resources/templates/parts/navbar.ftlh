<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Learning management system</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/main">Home</a>
            </li>
            <#if isUser>
                <li class="nav-item">
                    <a class="nav-link" href="/main">Home work</a>
                </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">User list</a>
                </li>
            </#if>
            <#if isTeacher>
                <li class="nav-item">
                    <a class="nav-link" href="/teacherRoom">Teacher room</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/groupManagement">Group management</a>
                </li>
            </#if>
            <#if user??&&name!="unknown">
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Profile</a>
                </li>
            </#if>
        </ul>

        <div class="navbar-text mr-3"><#if user??>${name}<#else>Please, login</#if></div>
        <@l.logout />
    </div>
</nav>
