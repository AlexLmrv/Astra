<#import "parts/common.ftlh" as comm>

<@comm.page>
    <div class="list-group">
        <a href="#" class="list-group-item list-group-item-action active">
            Выберите пользователя, чтобы начать диалог</a>
        <#list users as user>
            <a href="/dialogs/${user.id}" class="list-group-item list-group-item-action">
                <#if user.avatarpath??>
                    <img class="userlist__user_avatar" src="/img/${user.avatarpath}">
                <#else>
                    <img class="userlist__user_avatar" src="https://artifactcards.ru/img/forum/avatars/default/avatar_l.png">
                </#if>
                ${user.username}</a>
        </#list>
        </ul>
    </div>
</@comm.page>