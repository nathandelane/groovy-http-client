# groovy-http-client
Groovy based HTTP Web Client, with no fancy features, just pure Groovy and Java.

## Example Usage

```
@Grab(group = 'com.github.nathandelane.http', module = 'groovy-http-client', version = '0.1-SNAPSHOT')

import com.github.nathandelane.http.HttpWebClient
import com.github.nathandelane.http.HttpRequest
import com.github.nathandelane.http.HttpResponse

def client = {
    httpMethod, baseUrl, port, path, requestBody = '', headers = [:] ->

    def request = new HttpRequest(
        httpMethod: httpMethod,
        baseUrl: baseUrl,
        port: port,
        path: path,
        requestBody: requestBody,
        headers: headers
    )
    def internalClient = new HttpWebClient()
    def response = internalClient.handleRequest request

    response
}

github_search = {
    query ->

    assert query != null

    query = query.replace(" ", "+")

    def response = client(
        "GET",
        "https://github.com",
        443,
        "/search?q=${query}",
        null,
        [
            "Accept": ["text/html", "application/xhtml+xml", "application/xml;q=0.9","*/*;q=0.8"],
            "Accept-Language": ["en-US","en;q=0.5"]
        ]
    )
}

res = github_search "unit testing"

println "${res}"
```
