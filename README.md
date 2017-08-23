# GemFireClientAuthentication

In order for client applications to work with GemFire 9 clusters, we need to upgrade the GemFire client from 8.2.1 to 8.2.6. 

Another Important change to note, GemFire 9 Clusters have security enabled and client require to implement security related changes for connecting to new clusters.


### Following steps should be followed for migrating GemFire 8.2.1 clients onto GemFire 8.2.6 - 

Step 1: Upgrade the GemFire client dependencies. 

```
<dependency>
	<groupId>com.gemstone.gemfire</groupId>
	<artifactId>gemfire</artifactId>
	<version>8.2.6</version>
</dependency>

<repository>
	<id>gemfire-release-repo</id>
	<name>Pivotal GemFire Release Repository</name>
	<url>https://commercial-repo.pivotal.io/data3/gemfire-release-repo/gemfire</url>
</repository>
```

creating an account on https://commercial-repo.pivotal.io/login/auth, follow the docs
http://gemfire.docs.pivotal.io/gemfire/getting_started/installation/obtain_gemfire_maven.html

Step 2: setting up sample cups

```
{
 "locators": [
  "10.1.0.17[10334]",
  "10.1.0.15[10334]"
 ],
 "users": [
  {
   "password": "1234567",
   "username": "gemfire"
  }
 ]
}

```

Step 3: Security implementation on clients require us to set following properties, 

security-client-auth-init, security-username, security-password

example repo: 

JAVA Configuration: com.tmo.gemfire.security.configuration.GemfireConfiguration

XML Configuratoin: GemFireClientAuthentication/src/main/resources/tmoClientCache.xml