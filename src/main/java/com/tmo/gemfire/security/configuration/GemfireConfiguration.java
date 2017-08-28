//package com.tmo.gemfire.security.configuration;
//
//import java.util.Properties;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.gemfire.client.ClientCacheFactoryBean;
//import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
//import org.springframework.data.gemfire.config.xml.GemfireConstants;
//import org.springframework.data.gemfire.support.ConnectionEndpoint;
//
//import com.gemstone.gemfire.cache.client.ClientCache;
//import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
//import com.gemstone.gemfire.pdx.PdxInstance;
//import com.gemstone.gemfire.pdx.PdxSerializer;
//import com.gemstone.gemfire.pdx.ReflectionBasedAutoSerializer;
//
//@Configuration
//public class GemfireConfiguration {
//
//	private static final String SECURITY_CLIENT = "security-client-auth-init";
//	private static final String SECURITY_USERNAME = "security-username";
//	private static final String SECURITY_PASSWORD = "security-password";
//
//	@Bean
//	Properties gemfireProperties(@Value("${gemfire.log.level:config}") String logLevel,
//			@Value("${application.name:GemFireClientDemo") String applicationName,
//			@Value("${gemfire.username}") String username, @Value("${gemfire.password}") String password) {
//
//		Properties gemfireProperties = new Properties();
//
//		gemfireProperties.setProperty("name", applicationName);
//		gemfireProperties.setProperty("log-level", logLevel);
//		gemfireProperties.setProperty(SECURITY_CLIENT,
//				"com.tmo.gemfire.security.UserAuthInitialize.create");
//		gemfireProperties.setProperty(SECURITY_USERNAME, username);
//		gemfireProperties.setProperty(SECURITY_PASSWORD, password);
//
//		return gemfireProperties;
//	}
//
//	PdxSerializer pdxSerializer() {
//
//		PdxSerializer pdxSerializer = new ReflectionBasedAutoSerializer(".*");
//		return pdxSerializer;
//	}
//
//	@Bean(name = GemfireConstants.DEFAULT_GEMFIRE_CACHE_NAME)
//	ClientCacheFactoryBean gemfireCache(@Qualifier("gemfireProperties") Properties gemfireProperties,
//			@Value("${gemfire.locator.host:localhost}") String locatorHost,
//			@Value("${gemfire.locator.port:10334}") int locatorPort) {
//
//		ClientCacheFactoryBean gemfireCache = new ClientCacheFactoryBean();
//		gemfireCache.addLocators(new ConnectionEndpoint(locatorHost, locatorPort));
//		gemfireCache.setPdxSerializer(pdxSerializer());
//		gemfireCache.setPdxReadSerialized(false);
//		gemfireCache.setSubscriptionEnabled(true);
//
//		return gemfireCache;
//	}
//
//	@Bean(name = "customer")
//    public ClientRegionFactoryBean<String, PdxInstance> customerBackupRegion (@Autowired ClientCache gemfireCache) {
//
//        ClientRegionFactoryBean<String, PdxInstance> customerRegion =
//        		new ClientRegionFactoryBean<String, PdxInstance>();
//
//        customerRegion.setCache(gemfireCache);
//        customerRegion.setClose(false);
//        customerRegion.setShortcut(ClientRegionShortcut.PROXY);
//        customerRegion.setLookupEnabled(true);
//        return customerRegion;
//    }
//}
