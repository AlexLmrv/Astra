<#include "security.ftlh">
<#import "login.ftlh" as log>

<nav class="navbar navbar-expand-lg navbar-light " style="
background-image: linear-gradient(15deg, #32CD32 0%, #98FF98 100%);
box-shadow: inset 5px 0px 10px 10px rgba(202,255,182,0.4);
">
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