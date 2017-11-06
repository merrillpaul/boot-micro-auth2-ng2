package com.pearson.projectone.data.entity.security.oauth2.token

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import org.springframework.data.mongodb.core.mapping.Document
import spock.lang.Specification

class OAuthTokenEntitySpec extends Specification {

	def "oauth token classes should have appropriate annotations"() {
		expect:
		AccessToken.getAnnotation(Document).collection() == 'OauthAccessToken'
		RefreshToken.getAnnotation(Document).collection() == 'OauthRefreshToken'
	}
}
