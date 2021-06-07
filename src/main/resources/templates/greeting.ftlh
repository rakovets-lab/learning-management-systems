<#import "parts/common.ftlh" as c>

<@c.page>
<h5>Hello, <#if user??>${user.username}<#else>guest</#if></h5>
    <div>This is a simple system to management learning</div>
</@c.page>
