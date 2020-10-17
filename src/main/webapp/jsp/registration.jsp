
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
    <h1>Регистрация</h1>
    <form method="post" action="/registration">
        <fieldset>
            <legend>Данные пользователя</legend>
            <label>Никнейм<input name="username" type="text" required></label>
            <label>Пароль<input name="password" type="password" required></label>
        </fieldset>
        <input type="submit" value="Сохранить">
    </form>
</body>
</html>
