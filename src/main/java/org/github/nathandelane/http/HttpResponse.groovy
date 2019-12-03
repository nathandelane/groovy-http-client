package org.github.nathandelane.http

import groovy.transform.ToString

@ToString(includeNames=true)
class HttpResponse {

	int httpResponseCode

	String responseBody

	Map<String, List<String>> headers
}
