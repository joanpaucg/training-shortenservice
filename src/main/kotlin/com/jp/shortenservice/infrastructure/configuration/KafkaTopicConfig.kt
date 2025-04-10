import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaTopicConfig {
    @Bean
    fun shortenUrlUsedTopic(): NewTopic {
        return NewTopic("shorten_url.used", 1, 1.toShort())
    }
}