create extension if not exists pgcrypto; /*устанавливаем расширение для постгрес, если ещё его нет*/

update usr set password = crypt(password, gen_salt('bf', 8));
