<#import "parts/common.ftlh" as comm>

<@comm.page>
Редактирование пользователя

    <form method="post" action="/user">
        <input type="text" value="${user.username}" name="username">
        <input type="text" value="${user.id}" name="userID" />
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}"  ${user.roles?seq_contains(role) ? string("checked","") }>${role}</label>
            </div>
        </#list>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Сохранить изменения</button>
    </form>
</@comm.page>