package com.ford.vehicle.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;

@Configuration
public class MongoConfig {

	@Value("${mongoUrl}")
	private String mongoUrl;

	@Value("${mongoDBName}")
	private String mongoDBName;

	@Bean
	public MongoTemplate mongoTemplate() throws IOException {
		EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
		mongo.setBindIp(mongoUrl);
		MongoClient mongoClient = mongo.getObject();
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, mongoDBName);
		return mongoTemplate;
	}

}