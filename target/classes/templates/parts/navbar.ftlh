<#include "security.ftlh">
<#import "login.ftlh" as log>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
    <a class="navbar-brand" href="/">Astra</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home </a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/main">Блог</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/dialogs">Диалоги</a>
            </li>
            <#if isAdmin>
                <li class="nav-item active">
                    <a class="nav-link" href="/user">Пользователи </a>
                </li>
            </#if>
        </ul>
        <div class="navbar-text mr-3" ><a href="/profile"> ${name}</a></div>
        <@log.logout />
    </div>
</nav>