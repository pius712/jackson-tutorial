package pius.jacksontutorial.profile

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class NullableProfileTest {

    @Test
    @DisplayName("nullable 인 경우, 값이 없으면 null로 매핑된다.")
    fun toObj() {

        val jacksonObjectMapper = jacksonObjectMapper()
        val profile = jacksonObjectMapper.readValue<NullableProfile>("""{"nickname":"kim"}""");
        Assertions.assertThat(profile).isEqualTo(NullableProfile("kim", null, 0))
    }
}