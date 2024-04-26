package ru.kaplaan.consumer.repository.payment

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kaplaan.consumer.domain.entity.payment.PaymentInfo

@Repository
interface PaymentInfoRepository: CrudRepository<PaymentInfo, Long>{

    @Query("select exists (select 1 from payer_info where company_id = :companyId)")
    fun existsByCompanyId(companyId: Long): Boolean

    @Query("select * from payer_info where company_id = :companyId")
    fun findByCompanyId(companyId: Long): PaymentInfo
}