package tpe.maintenanceMS.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
//@EnableMongoRepositories(basePackages = "tpe.maintenance.authservice.repository.mongodb")
public class MongoDbConfig {

	@Bean
    MongoClient mongo() {
		ConnectionString connectionString = new ConnectionString(
				//"mongodb://root:root@localhost:27017/maintenance?authSource=manager");
				//url provista en mongodb para conectarse
				//"mongodb+srv://somartinez:YK8nMub94Jhh7o90@cluster0.ccilnwp.mongodb.net/?retryWrites=true&w=majority");
				"mongodb://somartinez:YK8nMub94Jhh7o90@localhost:27017/maintenance");
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.build();

		return MongoClients.create(mongoClientSettings);
	}

	@Bean
	MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), "maintenance");
	}
}
