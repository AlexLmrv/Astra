<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Astra</title>


    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/style.css">
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>

<div class="container-grid" >

    <div class="header"><#include "parts/navbar.ftlh"></div>

    <div class="message-header"><#if userTo.avatarpath??>
            <img class="userlist__user_avatar" src="/img/${userTo.avatarpath}">
        <#else>
            <img class="userlist__user_avatar" src="https://artifactcards.ru/img/forum/avatars/default/avatar_l.png">
        </#if>
        ${userTo.username}</div>


    <div class="sender">
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



    <div class="content">
        <#list messages as message>
                <div>
                    <#if message.filename??>
                        <img class="card-img-top" src="/img/${message.filename}">
                    </#if>
                </div>
                <div>
                    <span>${message.text}</span>
                    <i>${message.author.username}</i>
                </div>
                <div class="">
                    ${message.datetime}
                </div>
        <#else >
            No messages
        </#list>

    </div>

    <div class="menu">
        <div class="list-group">
            <a href="#" class="list-group-item list-group-item-action active">
                Диалоги</a>
            <#list users as user>
                <a href="/dialogs/${user.id}" class="list-group-item list-group-item-action">
                    <#if user.avatarpath??>
                        <img class="userlist__user_avatar" src="/img/${user.avatarpath}">
                    <#else>
                        <img class="userlist__user_avatar" src="https://artifactcards.ru/img/forum/avatars/default/avatar_l.png">
                    </#if>
                    ${user.username}</a>
            </#list>
            </ul>
        </div>

    </div>

</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


</body>
</html>