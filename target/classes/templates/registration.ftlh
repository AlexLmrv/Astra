<#import "parts/common.ftlh" as comm>
<#import "parts/login.ftlh" as log>

<@comm.page>
    <@log.login "/registration" />

    ${message}
</@comm.page>