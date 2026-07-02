# Cleanup Service Website

Simple Dockerized website for junk removal, appliance disposal, delivery, and cargo services.

The contact form saves requests to PostgreSQL. Each request has an id, name, phone, description, and status.

## Run with Docker

```powershell
docker compose up --build
```

Open http://localhost:8090.

Docker Compose starts two services:

- `cleanup-site` - Spring Boot website and API
- `postgres` - PostgreSQL database with persistent Docker volume
- `telegram-bot` - Python Telegram bot for admin notifications

Create `.env` before starting Docker Compose:

```powershell
TELEGRAM_BOT_TOKEN=your_bot_token_here
```

If `telegram_admins` is empty, the first person who sends `/start` to the bot becomes the first admin. Existing admins can add more admins:

```text
/add_admin <телеграмм айди> <имя>
```

The bot shows admin keyboard buttons:

- `Добавить админа` - shows the `/add_admin` command example and explanation.
- `Показать ожидающие заявки` - sends every `UNPROCESSED` request as a separate message with a `Пометить как обработанную` button.
- `Выгрузить таблицу заказов` - sends an Excel file with all requests from the `leads` table.

## Run locally with Maven

PostgreSQL must be running and available through the datasource settings in `application.properties`.

```powershell
mvn spring-boot:run
```

The contact form posts to `/api/contact`. The backend validates the phone number, rejects suspicious SQL-like input, and saves valid requests to the `leads` table with status `UNPROCESSED`.
