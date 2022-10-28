package com.hack.fse.receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class
})
@EnableHypermediaSupport(type= {HypermediaType.HAL})
@Import({Config.class})
public class SkillSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillSearchApplication.class, args);
	}

}
