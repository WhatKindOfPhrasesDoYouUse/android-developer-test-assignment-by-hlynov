package com.example.khlynovapp.data.api.response.error

enum class ApiError(
    val code: Int,
    val technicalMessage: String,
    val userMessage: String
) {
    INVALID_SERVICE(2, "Invalid service", "Сервис недоступен"),
    INVALID_METHOD(3, "Invalid method", "Метод недоступен"),
    AUTHENTICATION_FAILED(4, "Authentication failed", "Ошибка аутентификации"),
    INVALID_FORMAT(5, "Invalid format", "Неверный формат запроса"),
    INVALID_PARAMETERS(6, "Invalid parameters", "Отсутствует обязательный параметр"),
    INVALID_RESOURCE(7, "Invalid resource", "Неверный ресурс"),
    OPERATION_FAILED(8, "Operation failed", "Операция не удалась"),
    INVALID_SESSION_KEY(9, "Invalid session key", "Неверный ключ сессии"),
    INVALID_API_KEY(10, "Invalid API key", "Неверный API-ключ"),
    SERVICE_OFFLINE(11, "Service offline", "Сервис временно недоступен. Попробуйте позже."),
    INVALID_METHOD_SIGNATURE(13, "Invalid method signature", "Неверная подпись метода"),
    TEMPORARY_ERROR(16, "Temporary error", "Временная ошибка. Попробуйте позже."),
    SUSPENDED_API_KEY(26, "Suspended API key", "API-ключ заблокирован. Обратитесь в поддержку."),
    RATE_LIMIT_EXCEEDED(29, "Rate limit exceeded", "Превышен лимит запросов. Подождите 1 минуту."),
    NETWORK_ERROR(-1, "Network error", "Нет подключения к интернету. Проверьте соединение."),
    UNKNOWN_ERROR(-2, "Unknown error", "Неизвестная ошибка. Попробуйте позже.")
}