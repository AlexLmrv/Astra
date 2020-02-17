<#import "parts/common.ftlh" as comm>
<#import "parts/login.ftlh" as log>

<@comm.page>
    <div style="padding: 10px ">

    <p>
        Залогиньтесь или зарегистрируйтесь
    </p>
   <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
    <div class="alert alert-danger" role="alert">
        ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
    </div>
</#if>
<#if message??>
    <div class="alert " role="alert"> <!-- ${messageType} -->
        ${message}
    </div>
</#if>
    <@log.login "/login" false/>
    </div>
</@comm.page>
