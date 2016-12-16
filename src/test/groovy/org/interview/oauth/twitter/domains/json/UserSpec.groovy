package org.interview.oauth.twitter.domains.json

import org.interview.oauth.twitter.domains.json.User
import spock.lang.Specification

class UserSpec extends Specification {

    def 'Should check user result as equals and the hashcode is the same when the id and name are the same'() {
        given:
        def user = new User(id: 1234, name: 'leonardo', createdAt: Calendar.instance)

        and:
        def newUser = new User(id: 1234, name: 'leonardo')

        when:
        def equals = user.equals(newUser)

        then:
        equals

        and:
        user.hashCode() == newUser.hashCode()
    }

    def 'Should use toString with all attributes'() {
        given:
        def user = new User(id: 1234, name: 'leonardo', screenName: 'leo')

        when:
        def str = user.toString()

        then:
        str == "User [id=1234, name=leonardo, screenName=leo, createdAt=null]"
    }
}
