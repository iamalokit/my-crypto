package com.iamalokit.mycrypto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;

@Configuration
@ConditionalOnClass(akka.stream.javadsl.Source.class)
public class AkkaConfig {
    private final ActorSystem actorSystem;
    private final ActorMaterializer actorMaterializer;

    @Autowired
    public AkkaConfig() {
        this.actorSystem = ActorSystem.create("MongoDBStreamsActorSystem");
        this.actorMaterializer = ActorMaterializer.create(this.actorSystem);
    }

    @Bean
    public ActorSystem actorSystem() {
        return this.actorSystem;
    }

    @Bean(name = "materializer")
    public Materializer materializer() {
        return this.actorMaterializer;
    }
}
