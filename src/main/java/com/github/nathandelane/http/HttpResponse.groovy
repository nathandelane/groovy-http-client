package com.github.nathandelane.http

import groovy.transform.ToString

@ToString(includeNames=true)
class HttpResponse {

	int httpResponseCode
	
	String responseMessage

	String responseBody

	Map<String, List<String>> headers
}
