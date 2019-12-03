package org.github.nathandelane.http

import spock.lang.*
import org.github.nathandelane.http.HttpRequest

class HttpRequestSpec extends Specification {

	def "Create a simple HttpRequest using with"() {
		when:
		def req = new HttpRequest()
		req.with {
			httpMethod = "GET"
			baseUrl = "https://www.google.com"
			port = 80
			path = "/"
			requestBody = null
			headers = [:]
		}
		
		then:
		assert req != null
		assert req.httpMethod == "GET"
		assert req.baseUrl == "https://www.google.com"
		assert req.port == 80
		assert req.path == "/"
		assert req.requestBody == null
		assert req.headers == [:] 
	}
	
	def "Create a simple HttpRequest using named argument constructor"() {
		when:
		def req = new HttpRequest(
			httpMethod: "GET",
			baseUrl: "https://www.google.com",
			port: 80,
			path: "/",
			requestBody: null,
			headers: [:]
		)
		
		then:
		assert req != null
		assert req.httpMethod == "GET"
		assert req.baseUrl == "https://www.google.com"
		assert req.port == 80
		assert req.path == "/"
		assert req.requestBody == null
		assert req.headers == [:]
	}
	
}
