package pius.jacksontutorial.profile

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ProfileTest {

    @Test
    @DisplayName("jacksonObjectMapper로 코틀린 확장함수 사용가능")
    fun ktObjToJson() {
        val jacksonObjectMapper = jacksonObjectMapper()
        val jsonString = jacksonObjectMapper.writeValueAsString(Profile("kim", "안녕하세요", 30))

        Assertions.assertThat(jsonString).isEqualTo("""{"nickname":"kim","bio":"안녕하세요","age":30}""")

    }


    @Test
    fun ktJsonToObj() {
        val jacksonObjectMapper = jacksonObjectMapper()
        val profile:Profile = jacksonObjectMapper.readValue<Profile>("""{"nickname":"kim","bio":"안녕하세요","age":30}""");
        Assertions.assertThat(profile).isEqualTo(Profile("kim", "안녕하세요", 30))
    }

    // java 랑 동작이 다른데, 이거는 java는 nullable 이라서 그런거 같음.
    // kotlin은 기본적으로 nullable 아니면, 에러가 남.
    @Test
    @DisplayName("field가 비어있는 경우 에러가 난다")
    fun ktJsonToObj2() {
        val jacksonObjectMapper = jacksonObjectMapper()

        Assertions.assertThatThrownBy {
            jacksonObjectMapper.readValue<Profile>("""{"nickname":"kim","age":30}""")
        }.isInstanceOf(JsonMappingException::class.java)


        Assertions.assertThatThrownBy{
            jacksonObjectMapper.readValue<Profile>("""{"bio":"안녕하세요","age":30}""")
        }.isInstanceOf(JsonMappingException::class.java)

    }


    // int, Integer를 kt 에서 구분은 안하고, 박싱 언박싱 알아서 해주는데,
    // 그래서 그런듯?
    @Test
    @DisplayName("근데 Int 는 본질적으로는 primitive type이라 그런거같음.")
    fun ktJsonToObj3() {
        val jacksonObjectMapper = jacksonObjectMapper()
        val profile:Profile = jacksonObjectMapper.readValue<Profile>("""{"nickname":"kim","bio":"안녕하세요"}""");
        Assertions.assertThat(profile).isEqualTo(Profile("kim", "안녕하세요", 0))
    }

    @Test
    @DisplayName("더 많은 필드가 있다면?, 기본적으로는 예외, 설정을 통해바꿀 수 있음.")
    fun ktJsonWithExtraToObj() {
        val noConfigMapper = jacksonObjectMapper()
        Assertions.assertThatThrownBy { noConfigMapper.readValue<Profile>("""{"nickname":"kim","bio":"안녕하세요","age":30,"greet":"hello"}"""); }
                .isInstanceOf(UnrecognizedPropertyException::class.java)


        val configMapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        val profile:Profile = configMapper.readValue<Profile>("""{"nickname":"kim","bio":"안녕하세요","age":30,"greet":"hello"}""");
        Assertions.assertThat(profile).isEqualTo(Profile("kim", "안녕하세요", 30))
    }
}