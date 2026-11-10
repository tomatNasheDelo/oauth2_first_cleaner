# OAuth2 GitHub REST API — учебный пример

Минимальный Spring Boot OAuth2 Client без Thymeleaf и без собственного frontend. Spring Security выполняет Authorization Code flow с GitHub, а профиль после входа доступен как JSON через REST API. Пользовательская авторизация хранится в серверной HTTP-сессии; БД не нужна.

## Быстрый старт

Требуются JDK 17+ и Gradle 8.x. Создайте GitHub OAuth App (инструкция в `start.txt`), задайте `GITHUB_CLIENT_ID` и `GITHUB_CLIENT_SECRET`, затем запустите:

```bash
gradle bootRun
```

Откройте authorization URL в браузере: `http://localhost:8080/oauth2/authorization/github`. После согласия GitHub callback вернёт JSON об успешном входе. Используйте ту же браузерную сессию для `GET http://localhost:8080/api/me`.

## API

- `GET /api/auth/login-url` — JSON с адресом начала OAuth2 flow.
- `GET /oauth2/authorization/github` — Spring Security endpoint начала flow; открывайте в браузере.
- `GET /login/oauth2/code/github` — callback, зарегистрированный в GitHub OAuth App.
- `GET /api/me` — профиль GitHub текущего пользователя (нужна сессия).
- `GET /api/csrf` — получить CSRF token для защищённых POST-запросов.
- `POST /api/logout` — завершить сессию (нужны cookie сессии и CSRF token).

Подробные шаги, curl и объяснение потока находятся в `start.txt`.

Демо просит только `read:user`. OAuth 2.0 делегирует доступ к API и сам по себе не является протоколом идентификации. Не помещайте client secret или access token в репозиторий и браузерный код.
