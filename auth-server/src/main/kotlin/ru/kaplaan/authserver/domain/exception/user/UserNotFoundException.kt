package ru.kaplaan.authserver.domain.exception.user

class UserNotFoundException(message: String? = null)
    : UserException(message ?: "Пользователь не найден"){

    override val message: String =
        super.message
}
