package hu.matusek.productalgorithms.domain

import io.hypersistence.utils.hibernate.type.array.ListArrayType
import jakarta.persistence.*
import org.hibernate.annotations.Type
import java.time.OffsetDateTime


@Entity
@Table(name = "calculation_history_entries")
class CalculationHistoryEntry(
    id: Long? = null,
    var comment: String,
    @Enumerated(EnumType.STRING)
    var algorithmType: AlgorithmType,
    @Type(ListArrayType::class)
    @Column(
        name = "input",
        columnDefinition = "integer[]"
    )
    var input: List<Int>,
    @Type(ListArrayType::class)
    @Column(
        name = "result",
        columnDefinition = "integer[]"
    )
    var result: List<Int>,
    var timestamp: OffsetDateTime
) : BaseEntity(id) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        return id != null && id == (other as CalculationHistoryEntry).id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0
}