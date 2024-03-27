package ru.kaplaan.vacancy.web.controller

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.kaplaan.vacancy.service.DetailsService
import ru.kaplaan.vacancy.web.dto.CompanyDetailsDto
import ru.kaplaan.vacancy.web.dto.UserDetailsDto
import ru.kaplaan.vacancy.web.mapper.toDto
import ru.kaplaan.vacancy.web.mapper.toEntity

@RestController
@RequestMapping("/details")
class DetailsController(
    private val detailsService: DetailsService
) {


    @PostMapping("/company")
    fun saveCompanyDetails(
        @RequestBody @Validated
        companyDetailsDto: CompanyDetailsDto
    ){
        detailsService.saveCompanyDetails(companyDetailsDto.toEntity())
    }

    @GetMapping("/company/{companyName}")
    fun getCompanyDetailsByCompanyName(
        @Validated @NotBlank(message = "Название компании не должно быть пустым!")
        @PathVariable companyName: String
    ): CompanyDetailsDto =
        detailsService.getCompanyDetailsByCompanyName(companyName).toDto()

    @PostMapping("/user")
    fun saveUserDetails(
        @RequestBody
        userDetailsDto: UserDetailsDto
    ){
        detailsService.saveUserDetails(userDetailsDto.toEntity())
    }

    @GetMapping("/user/{username}")
    fun getUserDetailsByUsername(
        @Validated @Length(min = 4, message = "Название компании должно быть минимум из 4 символов!")
        @PathVariable username: String
    ): UserDetailsDto =
        detailsService.getUserDetailsByUsername(username).toDto()
}