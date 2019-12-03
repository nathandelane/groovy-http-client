package org.github.nathandelane.http

import javax.net.ssl.HttpsURLConnection
import org.github.nathandelane.http.HttpRequest
import org.github.nathandelane.http.HttpResponse

class HttpWebClient {

	def handleRequest = { HttpRequest request, readTimeout = 15_000, connectTimeout = 15_000, followRedirects = false ->
		assert request != null
		
		HttpURLConnection.setFollowRedirects followRedirects
		HttpsURLConnection.setFollowRedirects followRedirects

		request.with {
			def strUrl = new StringBuilder(baseUrl).append((port ? ":${port}" : "")).append(path)
			def url = new URL("${strUrl}")
			def connection = null
			
			if (strUrl.startsWithAny("https")) {
				connection = (HttpsURLConnection) url.openConnection()
			}
			else {
				connection = (HttpURLConnection) url.openConnection()
			}
			
			connection.setRequestMethod httpMethod
			connection.setConnectTimeout connectTimeout
			connection.setReadTimeout readTimeout

			if (headers) {
				headers.each { String key, List<String> value ->
					value.each { nextValue ->
						connection.setRequestProperty(key, nextValue)
					}
				}
			}

			if (requestBody) {
				connection.setRequestProperty "Content-Length", "${requestBody.getBytes().length}"
				connection.setDoInput true
				connection.setDoOutput true

				def bodyWriter = new OutputStreamWriter(connection.getOutputStream())
				bodyWriter.write requestBody
				bodyWriter.flush()
			}

			def response = new HttpResponse()

			response.with {
				httpResponseCode = connection.getResponseCode()
				responseMessage = connection.getResponseMessage()
				headers = connection.getHeaderFields()

				def inputStream = connection.getErrorStream()

				if (!inputStream) {
					inputStream = connection.getInputStream()
				}

				def reader = new BufferedReader(new InputStreamReader(inputStream))
				def inputLine = ""
				def content = new StringBuilder("")

				while ((inputLine = reader.readLine())) {
					content.append(inputLine)
				}

				reader.close()

				responseBody = content.toString()
			}

			response
		}
	}
}
