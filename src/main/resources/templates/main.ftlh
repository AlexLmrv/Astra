<#import "parts/common.ftlh" as comm>
<#import "parts/login.ftlh" as log>

<@comm.page>

    <@log.logout />

    <div>
        <form method="post" >
            <input type="text" name="text" placeholder="Введите сообщение">
            <input type="text" name="tag" placeholder="Тэг">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Добавить</button>

        </form>
        <form method="get" action="/main">
            <input type="text" name="filter" value=""> <!-- //value="${filter}" не грузится! -->
            <button type="submit">Найти</button>
        </form>
    </div>

        <p>
            Список сообщений:
        </p>

            <#list messages as message>
                <div>
                    <b>${message.id}</b>
                    <span>${message.text}</span>
                    <i>${message.tag}</i>
                    <strong>${message.authorName}</strong>
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