<#import "parts/common.ftlh" as comm>
<#import "parts/login.ftlh" as log>

<@comm.page>
    <p>
        Залогиньтесь или зарегистрируйтесь
    </p>
    ${message?ifExists}
    <@log.login "/login" false/>

</@comm.page>
