package com.github.nathandelane.http

import groovy.transform.ToString

@ToString(includeNames=true)
class HttpRequest {

	String httpMethod

	String baseUrl

	Integer port

	String path

	String requestBody

	Map<String, List<String>> headers
}
