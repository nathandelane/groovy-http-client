package org.github.nathandelane.http

import groovy.transform.ToString

@ToString(includeNames=true)
class HttpRequest {

	String httpMethod

	String baseUrl

	int port

	String path

	String requestBody

	Map<String, List<String>> headers
}
