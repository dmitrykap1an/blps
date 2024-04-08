package ru.kaplaan.api.service.consumerServer.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.consumerServer.VacancyResponseService
import ru.kaplaan.api.web.dto.consumerServer.vacancyResponse.VacancyResponseDto

@Service
class VacancyResponseServiceImpl(
    private val webClient: WebClient
): VacancyResponseService {

    @Value("\${consumer-server.base-url}")
    lateinit var baseUrl: String

    @Value("\${consumer-server.vacancy-response.url}")
    lateinit var url: String

    @Value("\${consumer-server.vacancy-response.save}")
    lateinit var saveEndpoint: String

    @Value("\${consumer-server.vacancy-response.delete}")
    lateinit var deleteEndpoint: String

    @Value("\${consumer-server.vacancy-response.get-all-user-id-by-company-id}")
    lateinit var getAllUserIdByCompanyIdEndpoint: String

    override fun save(vacancyResponseDto: Mono<VacancyResponseDto>): Mono<ResponseEntity<VacancyResponseDto>> =
        webClient
            .post()
            .uri("$baseUrl$url$saveEndpoint")
            .body(vacancyResponseDto)
            .retrieve()
            .toEntity(VacancyResponseDto::class.java)

    //TODO: проверить, что отклик удаляет компания-владелец вакансии
    override fun delete(vacancyResponseId: Long): Mono<ResponseEntity<Any>> =
        webClient
            .delete()
            .uri("$baseUrl$url$deleteEndpoint$vacancyResponseId")
            .retrieve()
            .toEntity(Any::class.java)

    //TODO: проверить, что пользователей получает компания-владелец вакансии
    override fun getAllUserIdByVacancyId(companyId: Long, pageNumber: Int): Mono<ResponseEntity<Flux<Long>>> =
        webClient
            .get()
            .uri("$baseUrl$url$getAllUserIdByCompanyIdEndpoint$companyId/$pageNumber")
            .retrieve()
            .toEntityFlux(Long::class.java)
}