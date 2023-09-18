package com.kaushik.springbootmvn.restapi;

import org.apache.tomcat.jni.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.kaushik.springbootmvn.restapi.topic.Topic;

@SpringBootApplication
public class RestApiApplication {
//	mvnw spring-boot:run --quiet
	
	// mvn clean install 
	// java -jar target/---.jar  -->Run anywhere
	

	// Change <packaging>war</packaging> in pom.xml and mvn clean install 
	// java -war target/---.war  -->Run in servlet container
	
	//Spring Initializr, SpringBoot CLI(groovy), STS(Spring Tools Suite) IDE
	//Look at spring boot dependencies, common application properties
	public static void main(String[] args) {
		ConfigurableApplicationContext context=SpringApplication.run(RestApiApplication.class, args);
	
		
		
		
		
		
		
		
//	SPRING CORE(WITHOUT SPRING BOOT): DEPENDENCY INJECTION CAN BE DONE USING BEAN FACTORY OR APPLICATION CONTEXT
//		1. XML+ANNOTATION BASED CONFIGURATION
//			ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
//			Topic t=(Topic)context.getBean("my_topic"); t.fun();
//					(or)
//			@Autowired Topic t;  &&    @Component class Topic {...}
//		
//			create config.xml inside working package,
//			<?xml version="1.0" encoding="UTF-8" ?>
//			<beans>
//				// TO ENABLE JAVA ANNOTATION CONFIGURATION IN ADDITION TO XML BASED:-
//				<context:component-scan base-package="com.kaushik.springbootmvn.restapi.topic"></context:component-scan> 
//				<bean id="my_topic" class="com.kaushik.springbootmvn.restapi.topic.Topic">
//					//SETTER INJECTION
//					<property name="topicName" value="NodeJS"></property>
//
//					//CONSTRUCTOR INJECTION
//					<constructor-arg value="NodeJS"></constructor0arg>
//				</bean>
//			</beans>
		
		
		
		
//		2. ANNOTATION BASED CONFIGURATION
//		ApplicationContext context=new AnnotationConfigApplicationContext("config.xml");
//		Topic t=context.getBean(Topic.class);
//		t.fun();  -->This throws error, because there is no matching BEAN in spring container

//		To solve it use:-  @Component class Topic {...}
//			(or)
//		@Configuration
//		public class AppConfig {
//			@Bean Topic getTopic() {return new Topic("NodeJS");}
//		}

//		ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
//		Topic t=context.getBean(Topic.class);
//		t.fun();
//			(or)
//		@Component("top1") @Primary class Topic {...} --> This component name will by default be same as className(but decapitalised and non-qualified version of class name)

//		@Configuration @ComponentScan(basePackages="com.kaushik.springbootmvn.restapi.topic")
//		public class AppConfig {}
		
//		class RestApiApplication { 
//			ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
//			Topic t=context.getBean(Topic.class); t.fun();
//		}	
//		(or)
//	@Component("top1") @Primary class Topic {...} --> This component name will by default be same as className(but decapitalised and non-qualified version of class name)

//	class RestApiApplication { 
//		@Autowired @Qualifier("top1") private Topic t; -->Here, top1 is name of the BEAN OBJECT	
//	} 

		
//	NOTE: Each BEAN has a different scope(Singleton,Prototype,Request,Session). By default, it uses Singleton and creates the object of a bean only once and shares the object to all the @Autowired anntations
//	If we use @Scope(value="prototype"), then object of BEAN will only be created, when user calls context.getBean() method
		
//	NOTE: AOP SUPPLEMENTS OOP, AND IS USED TO EXTEACT OUT COMMON LOGIC (CROSS CUTIING CONCERNS)
//	DIFFERENT ASPETS ARE-->LOG,TRANSACTIONS
//	DIFFERENT TYPES OF ADVICE(WHAT METHOD TO CALL)-->BEFORE,AFTER
//	POINTCUT(WHEN/HOW TO CALL fun() method)-->
//		EG: @Aspect @Component @EnableJAutoProxy class Helper{
//			@Before("execution(public void fun())")
//			void fun() {.}	
//		}
		
		
		
		
//		3. JAVA APPLICATION CONTEXT
		
		
		

	}

}
