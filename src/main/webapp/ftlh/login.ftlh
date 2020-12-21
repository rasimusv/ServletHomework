<html lang="ru">
<head>
    <title>Вход</title>
</head>
<body>
<h1>Вход</h1>
<form method="post" action="/login">
    <fieldset>
        <legend>Данные пользователя</legend>
        <label>Никнейм<input name="username" type="text" required></label>
        <label>Пароль<input name="password" type="password" required></label>
    </fieldset>
    <input type="hidden" value="${_csrf_token}" name="_csrf_token">
    <input type="submit" value="Войти">
</form>
</body>
</html>