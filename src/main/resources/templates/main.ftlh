<#import "parts/common.ftlh" as comm>


<@comm.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" value="${filter?ifExists}" placeholder="Поиск по тэгу" class="form-control">
                <button type="submit" class="btn btn-primary ml-2">Найти</button>
            </form>
        </div>
    </div>

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
                    <input type="text" name="tag" placeholder="Тэг" class="form-control ${(textError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.tag}</#if>">
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
            <i>${message.tag}</i>
        </div>
        <div class="card-footer text-muted">
            ${message.authorName}
        </div>

        </div>
    <#else >
        No messages
    </#list>

    </div>



</@comm.page>