# Cleanup Service Website

Dockerized website for junk removal, appliance disposal, delivery, cargo services, PostgreSQL requests, and Telegram admin notifications.

## Production Run

Create `.env` on the server. This file is intentionally not committed to git:

```env
TELEGRAM_BOT_TOKEN=your_bot_token_here
DOMAIN=clear-city-dnipro.com,www.clear-city-dnipro.com
LETSENCRYPT_EMAIL=admin@clear-city-dnipro.com
```

You can also copy the template:

```bash
cp .env.example .env
nano .env
```

Start everything:

```bash
docker compose up --build -d
```

Docker Compose starts:

- `nginx-proxy` - reverse proxy on ports `80` and `443`
- `acme-companion` - automatic Let's Encrypt certificates
- `cleanup-site` - Spring Boot website and API
- `postgres` - PostgreSQL database with persistent Docker volume
- `telegram-bot` - Python Telegram bot for admin notifications

After DNS points to the server, open:

```text
https://clear-city-dnipro.com
https://www.clear-city-dnipro.com
```

Server requirements:

- DNS `A` records for `@` and `www` point to the server IP.
- Ports `80` and `443` are open in the firewall.
- No host Nginx/Apache is already using ports `80` or `443`.
- `.env` exists on the server and contains the real `TELEGRAM_BOT_TOKEN`.

## Telegram Bot

If `telegram_admins` is empty, the first person who sends `/start` to the bot becomes the first admin. Existing admins can add more admins:

```text
/add_admin <телеграмм айди> <имя>
```

The bot shows admin keyboard buttons:

- `Добавить админа` - shows the `/add_admin` command example and explanation.
- `Показать ожидающие заявки` - sends every `UNPROCESSED` request as a separate message with a `Пометить как обработанную` button.
- `Выгрузить таблицу заказов` - sends an Excel file with all requests from the `leads` table.

## Local Development

PostgreSQL must be running and available through the datasource settings in `application.properties`.

```powershell
mvn spring-boot:run
```

The contact form posts to `/api/contact`. The backend validates the phone number, rejects suspicious SQL-like input, and saves valid requests to the `leads` table with status `UNPROCESSED`.
