package com.github.nathandelane.http

import spock.lang.*
import com.github.nathandelane.http.HttpRequest
import com.github.nathandelane.http.HttpWebClient
import com.github.nathandelane.http.HttpResponse

class HttpWebClientSpec extends Specification {

	def "Create a simple HttpWebClient"() {
		when:
		def req = new HttpRequest()
		req.with {
			httpMethod = "GET"
			baseUrl = "http://www.google.com"
			port = 80
			path = "/"
			requestBody = null
			headers = [:]
		}
		def client = new HttpWebClient()
		def response = client.handleRequest req
		
		then:
		assert req != null
		assert req.httpMethod == "GET"
		assert req.baseUrl == "http://www.google.com"
		assert req.port == 80
		assert req.path == "/"
		assert req.requestBody == null
		assert req.headers == [:] 
	}
	
}
