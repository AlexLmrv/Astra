<#import "parts/common.ftlh" as comm>
<@comm.page>
    <div>

        <p>
            ID: ${user.id}
        </p>
        <p>
            Name: ${user.username}
        </p>
        <p>
            password: ${user.password}
        </p>


    </div>

    <form method="post" enctype="multipart/form-data" >
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Пароль:</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" name="password" placeholder="password"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Email:</label>
            <div class="col-sm-6">
                <input type="email" class="form-control" name="email" placeholder="Email" value="${user.email!''}"/>
            </div>
        </div>
        <div class="form-group row">
            <div class="custom-file">
                <input type="file" name="file" id="customFile">
                <label class="custom-file-label" for="customFile">Добавить аватар</label>
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <button type="submit" class="btn btn-primary">Сохранить изменения</button>
    </form>

</@comm.page>