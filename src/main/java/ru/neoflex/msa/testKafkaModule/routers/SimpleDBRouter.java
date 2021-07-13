package ru.neoflex.msa.testKafkaModule.routers;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.neoflex.msa.testKafkaModule.utils.ProcessingClass;
import ru.neoflex.msa.testKafkaModule.utils.DBSupport;

@Component
public class SimpleDBRouter extends RouteBuilder {

    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Value("${kafka.topic.toDatabase}")
    private String topic;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Autowired
    DBSupport dbSupport;


    @Override
    public void configure() throws Exception {

        getContext().getRegistry().bind("postgresOne", Class.forName("org.postgresql.Driver"), dbSupport.getDataSource());
        from("kafka://{{kafka.topic.toDatabase}}?"
                + "brokers={{kafka.bootstrap.servers}}&groupId={{kafka.groupid}}")
                .log("Message: ${body}")
                .process(new ProcessingClass())
                .to("jdbc:postgresOne?useHeadersAsParameters=true")
                .end();

    }
}
