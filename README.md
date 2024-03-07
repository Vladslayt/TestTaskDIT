---
Тестовое задание по автоматизации
---

## Задание
1. Поднять Docker контейнер https://bit.ly/3Tpwt4R
2. Написать на языке Java ***e2e тест*** по автоматизации и добавления товара в корзину.
3. Проверить, что сумма цены товаров в корзине соответствует сумме цен заказанных товаров.
4. В проекте использовать стек ***Java***, ***Gradle***, ***JUnit5***, ***Playwright***, ***Faker*** или любой другой генератор данных, ***Allure2***.
5. После прохождения тестов должен формироваться ***Allure-отчет***.

## Запуск проекта
1. Импортировать [репозиторий](https://github.com/microservices-demo/microservices-demo) проекта себе на ПК
   ```bash
   git clone https://github.com/microservices-demo/microservices-demo.git
    ```
2. Поднять [Docker контейнер](https://bit.ly/3Tpwt4R) проекта
   ```bash
    cd microservices-demo
   ```
   ```bash
    docker-compose -f deploy/docker-compose/docker-compose.yml up -d
   ```
3. Импортировать [репозиторий](https://github.com/Vladslayt/TestTaskDIT) данного проекта
   ```bash
    git clone https://github.com/Vladslayt/TestTaskDIT.git
   ```
4. Раскрыть папку **"build"** и запустить **"build"**
5. Раскрыть папку **"verification"** и запустить **"allureServe"**
