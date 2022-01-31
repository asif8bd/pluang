package com.ashid.pluang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter {

	private String clientId = "ashid";
	private String clientSecret = "ashid-secret-key";
	private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEowIBAAKCAQEAu5spOUG5HmO3/s1yNzg8e2ikJsF3JDDHu9HvvzyO86K1YzL7\r\n"
			+ "mlOhzryxKHojyyjMkgqZYtLfz6Pe4s/yhIC4UkRc/WRQmMykK6VTp32uS4kCtQk1\r\n"
			+ "s1UyidxZwsPAWYw/h5vea476QjdIOhzb5Ts6h0p492mdCYyBK0BApP100mIUea5Q\r\n"
			+ "QKY0dZd+z/10+Obrz1zysaAdjvaSyRLn+smBGGcfA+gb/5NyYUxu2GUZUBR0EynB\r\n"
			+ "a1/cxFNFroqagU+vSQARUtKiv3lPLmLj7tW0jnwWLosxUSoDHmLLpzBfIWaxxzcO\r\n"
			+ "VCee6UsyzEQLfx8yANiFqGAnKC82XU3W+sOg7QIDAQABAoIBADD7XvUDEuk7UfPm\r\n"
			+ "AhcmyKvT81/nDUtWrR99vaHpls1HwyImUojFoy6OF5cg5K2LdnX+7HNf6W2WNIB1\r\n"
			+ "mLU9wmJAVSMXiO+MJqT6om/Noa85E4re37V13h8eeBUg9NysgH3QlW7xwqd9NY9u\r\n"
			+ "r38XSr2+FP1MBuNSWvtG6Fnrjw/KdqsjvaWzdX5iqmRy3Cp5z0YT+FAw7RVX8ype\r\n"
			+ "hcunNuljK9SV7TK1TVK6RfeDX2nIvRDChKGxf/iO+vGTFXebm5wG9qSMJBMID7nU\r\n"
			+ "hv3BKWKrzhbfuhzTuSZKxcwS3mWE+SkpaHYYd5B1OKu+zU1cKlGPC+BgZn0xSWqH\r\n"
			+ "OfYIeoUCgYEA9clIwKw4jo2FcLLIqCKIAg32hqgsITEKhuGszsdjvjJzwoIfbBC1\r\n"
			+ "DXJTdGE3MZ1OAH9ylHqNZpVwp1mdAgyg5rsL3RQW+o2TR5VQJgtt3KUaQTEv+jp7\r\n"
			+ "uSXerCSCoQBI+emIZSbLzTNnKfj+7/z2WruLLL8vNcLtu62EsBTm5CMCgYEAw2by\r\n"
			+ "RgY0xyM1otN5V8bEQ3qlLZoIrjDfotePRN6kfCNVvhZOiGVxdjaRUTahw+Tc4WY1\r\n"
			+ "wYBqyTdxmDRP+rXOtQz3JDL2PErpWsxmrShUTVRC/FeenYTqu3c5Hx+vk2yqDve4\r\n"
			+ "mcZOtA8Ed+HdbRL58UrRPPjdxqhcHa0Rc6jb768CgYAaAyuH+LIjpCazPrit0rnC\r\n"
			+ "PegRaEsDWEVc8RVw7PlJb92AgHUC4uHGgosCjidhQNy4gMSHolDEwpwhmgJb7So6\r\n"
			+ "QSGITFACRGmJ8euBm0QNTgvxSlthiD7/4VonrdWFZJpUmLquueRn20DX2Fpi28rW\r\n"
			+ "33JjUfiVIAQ5EKwyVoP5MQKBgQCHao64LJDi+Omw3/GV/TACu7pBMiCV51T024J0\r\n"
			+ "bf978Fj5lSfzqqnJDOQEbmIJClBw/I6AfWiD7iKRIp/S1bBdFJEpyw+OlTPHMyRt\r\n"
			+ "GCfkN2L80hQggf7S0DsL8L+S4ZmxKcgK+b2NV2L3WdZEQwRoTjtXj/jSNdcw0cHo\r\n"
			+ "b7uMDQKBgDK4+LEj1bdKP1S5aZsQRg1jkj7yEp5mkdLX5wuJH8ccgT1KRO15KaR/\r\n"
			+ "2mnVD+e5vMqPRnXX4eQO7VOVHRPWwt/T3xfNzO4Jejt+ypTsga2ckYs7iV2w4gC5\r\n"
			+ "O6M8Dwf/qN01nsmd/jRlFkw8G+tKXEALEpjgYMjEQhQWoZMXT88I\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	private String publicKey = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu5spOUG5HmO3/s1yNzg8\r\n"
			+ "e2ikJsF3JDDHu9HvvzyO86K1YzL7mlOhzryxKHojyyjMkgqZYtLfz6Pe4s/yhIC4\r\n"
			+ "UkRc/WRQmMykK6VTp32uS4kCtQk1s1UyidxZwsPAWYw/h5vea476QjdIOhzb5Ts6\r\n"
			+ "h0p492mdCYyBK0BApP100mIUea5QQKY0dZd+z/10+Obrz1zysaAdjvaSyRLn+smB\r\n"
			+ "GGcfA+gb/5NyYUxu2GUZUBR0EynBa1/cxFNFroqagU+vSQARUtKiv3lPLmLj7tW0\r\n"
			+ "jnwWLosxUSoDHmLLpzBfIWaxxzcOVCee6UsyzEQLfx8yANiFqGAnKC82XU3W+sOg\r\n"
			+ "7QIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	public JwtAccessTokenConverter tokenEnhancer() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(privateKey);
		converter.setVerifierKey(publicKey);
		return converter;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(tokenEnhancer());
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(tokenEnhancer());
		  endpoints.pathMapping("/oauth/token", "/login");
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
				.authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
				.refreshTokenValiditySeconds(20000);

	}

}