package pius.jacksontutorial.profile

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ProfileWithGetterTest {


    @Test
    fun ktObjToJson() {
        val jacksonObjectMapper = jacksonObjectMapper()
        val jsonString = jacksonObjectMapper.writeValueAsString(Profile("kim", "안녕하세요", 30))

        Assertions.assertThat(jsonString).isEqualTo("""{"nickname":"kim","bio":"안녕하세요","age":30}""")
    }

}