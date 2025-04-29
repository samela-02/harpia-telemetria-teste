package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongodbConfiguration {
    private final String mondodbDatabaseUrl;
    private final String mongodbDatabaseName;

    public MongodbConfiguration(@Value("${mongodb.database.url}") String mondodbDatabaseUrl,
                                @Value("${mongodb.database.name}") String mongodbDatabaseName) {
        this.mondodbDatabaseUrl = mondodbDatabaseUrl;
        this.mongodbDatabaseName = mongodbDatabaseName;
    }

    @Bean
    MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(mondodbDatabaseUrl);
        MongoClientSettings mongoClientSettings = MongoClientSettings
                .builder()
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    MongoDatabaseFactory mongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(mongoClient(), mongodbDatabaseName);
    }
}
