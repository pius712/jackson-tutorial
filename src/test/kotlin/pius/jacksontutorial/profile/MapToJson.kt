package pius.jacksontutorial.profile

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MapToJson {

    @Test
    fun mapToJson() {
        val map = mapOf("foo" to "bar", "bar" to "baz")

        val jsonString = ObjectMapper().writeValueAsString(map)

        Assertions.assertThat(jsonString).isEqualTo("""{"foo":"bar","bar":"baz"}""")
    }

    @Test
    fun jsonToMap() {
        val map: Map<String, String> = ObjectMapper().readValue<Map<String, String>>("""{"foo":"bar","bar":"baz"}""")

        Assertions.assertThat(map).isEqualTo(mapOf("foo" to "bar", "bar" to "baz"))
    }

}