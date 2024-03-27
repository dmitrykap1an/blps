package ru.kaplaan.vacancy.web.dto

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.URL

data class CompanyDetailsDto(
    @field:NotBlank(message = "Название компании не должно быть пустым!")
    val companyName: String,
    @field:NotBlank(message = "Описание не должно быть пустым!")
    val description: String,
    @field:URL(message = "URL сайта должен подходить под паттерн URL")
    val site: String,
    val contactPerson: ContactPersonDto
)