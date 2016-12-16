package org.interview.oauth.twitter.services

import java.util.concurrent.TimeUnit

import rx.schedulers.Schedulers
import spock.lang.Specification
import spock.util.concurrent.BlockingVariables

class TweetReaderSpec extends Specification {

    def 'Should read tweets from stream'() {
        def times = 0
        def vars = new BlockingVariables(5, TimeUnit.SECONDS)

        given:
        def inputStream = new PipedInputStream()

        and:
        def reader = new TweetReader(inputStream)

        and:
        def outputStream = new PipedOutputStream(inputStream)

        when:
        outputStream.write('{"name":"leonardo", '.bytes)
        outputStream.write('"attr":"test"}\n'.bytes)

        and:
        reader.read().subscribeOn(Schedulers.newThread()).subscribe {
            times++
            if (times == 2) vars.events = true
        }

        and:
        outputStream.write('{"name":"jao", "attr":"test'.bytes)
        outputStream.write('2"}\n'.bytes)

        then:
        vars.events
    }

}
