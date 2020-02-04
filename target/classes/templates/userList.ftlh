<#import "parts/common.ftlh" as comm>

<@comm.page>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Пользователь</th>
            <th>Группа</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td><#list user.roles as role> ${role}<#sep>, </#list> </td>
                <td><a href="/user/${user.id}" > Редактировать </a> </td>
            </tr>
        </#list>
        </tbody>
    </table>

</@comm.page>