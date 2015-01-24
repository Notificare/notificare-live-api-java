# Notificare Live API SDK for Java

[![Build Status](https://travis-ci.org/Notificare/notificare-live-api-java.png?branch=master)](https://travis-ci.org/Notificare/notificare-live-api-java)

## Installing

Use Maven

```xml
	<repositories>
		<repository>
			<id>notificare-mvn-repo</id>
			<url>https://github.com/Notificare/notificare-mvn-repo/raw/master/releases</url>
		</repository>
	</repositories>
	<dependencies>
		<dependency>
			<groupId>re.notifica</groupId>
			<artifactId>notificare-live-api</artifactId>
			<version>0.2</version>
		</dependency>
	</dependencies>
```

## Usage

```java
var privateKey = 'xxxxxx',
    publicKey = 'yyyyyy';

LiveApi liveApi = new LiveApi(privateKey, publicKey);

String verificationResponse = liveApi.httpGateway().verify(incomingPublicKey, challenge);
```


## License

This SDK is distributed under the
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0),
see LICENSE.txt and NOTICE.txt for more information.