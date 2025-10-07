# Android Test App - Банк Хлынов

##### Описание проекта

Тестовое задание на позицию **Android-разработчика** в АО КБ "Хлынов".

Мобильное приложение, написанное на **Kotlin**, позволяет искать информацию об артистах через **Last.fm API**.

В проекте реализовано:
- Поиск артиста по имени с отображением его фото (заглушки от API), названия и биографии
- Поиск треков артиста с выводом трёх случайных треков, включающих в себя название трека, ранг и его изображение (заглушку от API)
- Навигация между экранами
- Верстка по предоставленному макету из Figma

##### Макет

Макет реализован на основе предоставленного шаблона Figma с одним улучшением. Добавлен скроллинг текста биографии, в следствии длинных текстов приходящих от API.

##### API

Для выполнения проекта был использован открытый API: **Last.fm API**, из которого были выбраны два эндпоинта:

http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=YOUR_INPUT_ARTIST&api_key=YOUR_API_KEY&format=YOUR_CHOICE_FORMAT - Для поиска информации об артисте.

http://ws.audioscrobbler.com/2.0/?method=artist.gettoptracks&artist=YOUR_INPUT_ARTIST&api_key=YOUR_API_KEY&format=YOUR_CHOICE_FORMAT - Для получения страниц треков.


##### Технологии и стек

Используемые технологии в ходе разработки проекта:

| Категория                  | Технологии        |
| -------------------------- | ----------------- |
| **Язык разработки**        | Kotlin            |
| **Сетевое взаимодействие** | Retrofit          |
| **Загрузка изображений**   | Coil              |
| **Работа с API-ключами**   | local.properties  |
| **Минимальная SDK**        | 36 _(Android 15)_ |
| **Целевая SDK**            | 36 _(Android 15)_ |
| **Сборка проекта**         | Gradle            |

##### Как запустить проект:

1. Клонировать репозиторий:
`git clone https://github.com/WhatKindOfPhrasesDoYouUse/android-developer-test-assignment-by-khlynov.git`
2. Открыть проект в Android Studio.
3. Получить API ключ на: https://www.last.fm/home
4. Создать файл `local.properties` в корне проекта и скопировать в него полученный API ключ.
5. Собрать и запустить приложение.

##### Скриншоты работающего приложения:

Главный экран приложения: https://github.com/WhatKindOfPhrasesDoYouUse/android-developer-test-assignment-by-khlynov/blob/dev/screenshots/main_window.png
Экран поиска биографии артиста: https://github.com/WhatKindOfPhrasesDoYouUse/android-developer-test-assignment-by-khlynov/blob/dev/screenshots/bio.png
Экран поиска треков артиста: https://github.com/WhatKindOfPhrasesDoYouUse/android-developer-test-assignment-by-khlynov/blob/dev/screenshots/top_tracks.png


