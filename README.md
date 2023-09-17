# Тестовое задание.
---
## Запуск проекта

Требования: Java SE 17, git

Шаг 1. Склонировать репозиторий в локальное хранилище.
```bash
git clone https://github.com/lilkhalil/test.git
```
Шаг 2. Перейти в директорию с проектом.
```bash
cd test
```
Шаг 3. Собрать проект.
```bash
./mvnw clean package
```
Шаг 4. Запустить приложение.
```bash
java -jar target/test-0.0.1-SNAPSHOT.jar
```
---
## Формат входящих / исходящих данных

Входные данные, представленные строкой, передаваемой через форму данных по ключу `str`,
представляют собой часть данных, отправленных клиентом на сервер с использованием
формы данных HTTP (HTTP Form Data).

```
'str: "aaaaabcccc"'
```

Выходные данные, представленные в виде JSON, где ключами являются символы из
изначальной строки, а значениями является количество их вхождений в строку.

```json
{
    "a": 5,
    "c": 4,
    "b": 1
}
```
Пример отправки запроса с приведенным выше параметром (aaaaabcccc) представлен ниже (cURL):
```bash
curl --location 'http://localhost:8080/api/v1/characters' --form 'str="aaaaabcccc"'
```
