package org.interview.oauth.twitter.services

import org.interview.oauth.twitter.domains.json.Tweet

import rx.observers.TestSubscriber
import spock.lang.Specification

class TweetBackgroundTrackerSpec extends Specification {
	
	def 'Should try to track one status when Redis is up'() {
		def subscriber = new TestSubscriber()
		
		given:
		def tracker = Mock(TweetTracker)
		
		and:
		def backgroundTracker = new TweetBackgroundTracker(tracker)
		
		when:
		1 * tracker.checkConnection() >> true
		
		and:
		1 * tracker.track('bieber', _ as Tweet)
		
		and:
		backgroundTracker.trackWhenPossible('bieber', new Tweet())
		
		and:
		backgroundTracker.track().subscribe(subscriber)
		
		then:
		subscriber.assertNoErrors()
		
		and:
		subscriber.assertCompleted()
	}
	
	def 'Should try to track one status when Redis is down'() {
		def subscriber = new TestSubscriber()
		
		given:
		def tracker = Mock(TweetTracker)
		
		and:
		def backgroundTracker = new TweetBackgroundTracker(tracker)
		
		when:
		1 * tracker.checkConnection() >> false
		
		and:
		0 * tracker.track('bieber', _ as Tweet)
		
		and:
		backgroundTracker.trackWhenPossible('bieber', new Tweet())
		
		and:
		backgroundTracker.track().subscribe(subscriber)
		
		then:
		subscriber.assertNoErrors()
		
		and:
		subscriber.assertCompleted()
	}

}
