<#import "parts/common.ftlh" as comm>


<@comm.page>

    <div>Отладочная строка  <#if user.avatarpath??>
            <img class="userlist__user_avatar" src="/img/${userTo.avatarpath}">
        <#else>
            <img class="userlist__user_avatar" src="https://artifactcards.ru/img/forum/avatars/default/avatar_l.png">
        </#if>
        ${userTo.username}</div>


    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Добавить сообщение
    </a>
    <div class="collapse <#if message??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data" >
                <div class="form-group">
                    <input type="text" name="text" placeholder="Введите сообщение" class="form-control ${(textError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.text}</#if>">
                    <#if textError??>
                        <div class="invalid-feedback">
                            Ошибка: ${textError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Прикрепить изображение</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn-primary">Добавить</button>

            </form>
        </div>
    </div>



    <div class="card-columns">
        <#list messages as message>
            <div class="card my-3" >
                <div>
                    <#if message.filename??>
                        <img class="card-img-top" src="/img/${message.filename}">
                    </#if>
                </div>
                <div class="m-2">
                    <span>${message.text}</span>
                    <i>${message.author.username}</i>
                </div>
                <div class="card-footer text-muted">
                    ${message.datetime}
                </div>

            </div>
        <#else >
            No messages
        </#list>

    </div>



</@comm.page>