package com.pearson.projectone.core.support.filters

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import spock.lang.Specification

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SimpleCorsFilterSpec extends Specification {

	def "should set cors headers in the response and set ok response for options"() {
		given:
		def headers = []
		def status
		def filter = new SimpleCorsFilter()
		def request = Stub(HttpServletRequest) {
			getMethod() >> {
				HttpMethod.OPTIONS
			}

			getHeader(_) >> { arguments ->
				def ret
				def name = arguments[0]
				switch (name) {
					case HttpHeaders.ORIGIN:
					case HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD:
						ret = '*'
						break
					default:
						ret = null
						break
				}
				ret
			}
		}

		def response = Stub(HttpServletResponse) {
			setHeader(_, _) >> { name, value ->
				headers << [
						name : name,
						value: value
				]
			}

			setStatus(_) >> { int _status ->
				status = _status
			}
		}
		def filterChain = Stub(FilterChain)

		when:
		filter.doFilterInternal(request, response, filterChain)

		then:
		status == HttpServletResponse.SC_OK
		headers == [
				[name: "Access-Control-Allow-Origin", value: "*"],
				[name: "Access-Control-Allow-Methods", value: "GET, POST, PUT, DELETE, OPTIONS"],
				[name: "Access-Control-Allow-Headers", value: "x-requested-with, authorization, content-type, xsrf-token"],
				[name: "Access-Control-Expose-Headers", value: "xsrf-token"]

		]
	}

	def "should invoke the next filter if its not a pre flight request"() {
		given:
		def headers = []
		def status
		def filter = new SimpleCorsFilter()
		def request = Stub(HttpServletRequest) {
			getMethod() >> {
				HttpMethod.GET
			}

			getHeader(_) >> { arguments ->
				"*" // doesnt matter what the headers are
			}
		}

		def response = Stub(HttpServletResponse) {
			setHeader(_, _) >> { name, value ->
				headers << [
						name : name,
						value: value
				]
			}

			setStatus(_) >> { int _status ->
				status = _status
			}
		}
		def filterChain = Stub(FilterChain) {
			doFilter(_, _) >> { HttpServletRequest _request, HttpServletResponse _response ->
				status = HttpServletResponse.SC_TEMPORARY_REDIRECT // just a dummy
			}
		}

		when:
		filter.doFilterInternal(request, response, filterChain)

		then:
		status == HttpServletResponse.SC_TEMPORARY_REDIRECT
		headers == [
				[name: "Access-Control-Allow-Origin", value: "*"],
				[name: "Access-Control-Allow-Methods", value: "GET, POST, PUT, DELETE, OPTIONS"],
				[name: "Access-Control-Allow-Headers", value: "x-requested-with, authorization, content-type, xsrf-token"],
				[name: "Access-Control-Expose-Headers", value: "xsrf-token"]

		]
	}
}
