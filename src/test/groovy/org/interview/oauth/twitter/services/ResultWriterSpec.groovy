package org.interview.oauth.twitter.services

import spock.lang.Specification

class ResultWriterSpec extends Specification {

	def 'Should write a text to a file'() {
		given:
		def filename = 'test_result.txt'

		and:
		def writer = new ResultWriter(filename)

		and:
		def content = '''
			First line
			Line 2
			Last Line
		'''

		when:
		def obs = writer.write(content)

		and:
		obs.subscribe()

		then:
		def file = new File(filename)
		file.text == content

		cleanup:
		file.delete()
	}
}
