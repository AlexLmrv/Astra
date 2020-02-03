<#import "parts/common.ftlh" as comm>
<#import "parts/login.ftlh" as log>

<@comm.page>
    <p>
        Залогиньтесь или зарегистрируйтесть
    </p>

    <@log.login "/login" />



<a href="/registration">Зарегистрироваться</a>

</@comm.page>
