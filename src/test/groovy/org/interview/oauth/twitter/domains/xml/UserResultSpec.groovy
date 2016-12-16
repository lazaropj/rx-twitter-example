package org.interview.oauth.twitter.domains.xml

import org.interview.oauth.twitter.domains.json.User
import spock.lang.Specification

class UserResultSpec extends Specification {

    def 'Should check user result as equals and the hashcode is the same when the id and name are the same'() {
        given:
        def user = new UserResult(new User(id: 1234, name: 'leonardo', createdAt: Calendar.instance))

        and:
        def newUser = new UserResult(new User(id: 1234, name: 'leonardo'))

        when:
        def equals = user.equals(newUser)

        then:
        equals

        and:
        user.hashCode() == newUser.hashCode()
    }

    def 'Should use toString with all attributes'() {
        given:
        def user = new UserResult(new User(id: 1234, name: 'leonardo', screenName: 'leo'))

        when:
        def str = user.toString()

        then:
        str == "UserResult [id=1234, name=leonardo, screenName=leo, createdAt=null]"
    }
}
