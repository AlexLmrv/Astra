<#import "parts/common.ftlh" as comm>

<@comm.page>

        <#list users as user>
                <td>${user.username}</td>
                <td><#list user.roles as role> ${role}<#sep>, </#list> </td>
                <td><a href="/user/${user.id}" > Редактировать </a> </td>
        </#list>


</@comm.page>