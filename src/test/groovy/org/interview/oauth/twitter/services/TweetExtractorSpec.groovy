package org.interview.oauth.twitter.services

import java.util.concurrent.TimeUnit

import org.interview.oauth.twitter.domains.xml.TweetsResults
import org.interview.oauth.twitter.rx.TweetStream

import rx.observers.TestSubscriber
import rx.schedulers.Schedulers
import spock.lang.Specification

class TweetExtractorSpec extends Specification {

	def 'Should extract the tweets'() {
		given:
		def tweets = Fixture.tweets()
		def scheduler = Schedulers.test()

		and:
		def stream = Mock(TweetStream)

		and:
		def extractor = new TweetExtractor(stream)

		and:
		def subscriber = new TestSubscriber<>()

		when:
		1 * stream.getObservable() >> tweets

		and:
		extractor.extractAsXml()
				.subscribeOn(scheduler)
				.subscribe(subscriber)


		and:
		scheduler.advanceTimeBy(5, TimeUnit.SECONDS)

		then:
		subscriber.assertNoErrors()

		and:
		subscriber.onNextEvents.size() == 1

		and:
		def xml = subscriber.onNextEvents.first()
		def tweetsResult = TweetMapper.newXmlInstance().readValue(xml, TweetsResults)

		then:
		subscriber.assertCompleted()

		and:
		tweetsResult.results*.actions.size() == 10
	}
}
