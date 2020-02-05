<#macro login path isRegisterForm>

    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Имя пользователя:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" name="username" placeholder="Login"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Пароль:</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" name="password" placeholder="password"/>
            </div>
        </div>
        <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Email:</label>
            <div class="col-sm-6">
                <input type="email" class="form-control" name="email" placeholder="Email"/>
            </div>
        </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#if !isRegisterForm><a href="/registration">Зарегистрироваться</a></#if>
        <button type="submit" class="btn btn-primary">Войти</button>
    </form>

</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <button type="submit" class="btn btn-primary m-3">Выйти</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

    </form>
</#macro>