package jeilm.api.cmm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsConfig {

	@Value("${global.ncloud.access.key}")
	private String accessKey;

	@Value("${global.ncloud.secret.key}")
	private String secretKey;

	@Value("${global.ncloud.storage.endpoint}")
	private String endPoint;

	@Value("${global.ncloud.storage.region.name}")
	private String regionName;
		
	@Bean(name = "amazonS3")
	AmazonS3 amazonS3() {
		return AmazonS3ClientBuilder
				.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.build();
	}
	
}
