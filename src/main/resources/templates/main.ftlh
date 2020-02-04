<#import "parts/common.ftlh" as comm>
<#import "parts/login.ftlh" as log>

<@comm.page>

    <@log.logout />

    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Введите сообщение">
            <input type="text" name="tag" placeholder="Тэг">
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Добавить</button>

        </form>
        <form method="get" action="/main">
            <input type="text" name="filter" value="${filter?ifExists}"> <!-- //value="${filter}" не грузится! -->
            <button type="submit">Найти</button>
        </form>
    </div>

    <p>
        <a href="/user">Список пользователей</a>
    </p>

    <p>
        Список сообщений:
    </p>

    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
            <div>
                <#if message.filename??>
                    <img src="/img/${message.filename}">
                </#if>
            </div>
        </div>
    <#else >
        No messages
    </#list>


    <div>
        <p>
            Список пользователей:
        </p>
        <p>
            <#list users as user>
        <div>
            <b>${user.id}</b>
            <span>${user.username}</span>
            <i>${user.password}</i>
        </div>
        </#list>
        </p>
    </div>
</@comm.page>