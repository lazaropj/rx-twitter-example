package org.interview.oauth.twitter.services

import rx.Observable

class Fixture {

    private static tweetsData = [
            '{"id":797530473517809664,"text":"RT @JBCrewdotcom: (6) Another video of Justin Bieber performing at the #PurposeTour in Prague, Czech Republic tonight. (November 12) https:…","user":{"id":315513805,"name":"Tianna","screen_name":"purpleninja178","created_at":"Sun Jun 12 02:42:33 +0200 2011"},"created_at":"Sat Nov 12 21:04:16 +0100 2016"}',
            '{"id":797530471265488896,"text":"RT @JBCrewdotcom: (6) Another video of Justin Bieber performing at the #PurposeTour in Prague, Czech Republic tonight. (November 12) https:…","user":{"id":3060654439,"name":"awhxannie |22 - DAYS","screen_name":"awhxannie","created_at":"Wed Mar 04 01:42:56 +0100 2015"},"created_at":"Sat Nov 12 21:04:16 +0100 2016"}',
            '{"id":797530477699747840,"text":"RT @JBCrewdotcom: Justin Bieber is currently performing Purpose at the #PurposeTour in Krakow, Poland tonight.","user":{"id":125975157,"name":"justin is cupcake","screen_name":"biebursbey","created_at":"Wed Mar 24 14:07:46 +0100 2010"},"created_at":"Sat Nov 12 21:04:17 +0100 2016"}',
            '{"id":797530472670724096,"text":"RT @JBCrewdotcom: (6) Another video of Justin Bieber performing at the #PurposeTour in Prague, Czech Republic tonight. (November 12) https:…","user":{"id":1468413235,"name":"Emma Nieto","screen_name":"browniebear19","created_at":"Thu May 30 00:31:27 +0200 2013"},"created_at":"Sat Nov 12 21:04:16 +0100 2016"}',
            '{"id":797530472293265408,"text":"RT @JBCrewdotcom: (6) Another video of Justin Bieber performing at the #PurposeTour in Prague, Czech Republic tonight. (November 12) https:…","user":{"id":1293260340,"name":"RayleenBieber👑","screen_name":"RayleenJoseph","created_at":"Sun Mar 24 03:22:41 +0100 2013"},"created_at":"Sat Nov 12 21:04:16 +0100 2016"}',
            '{"id":797530471500578816,"text":"RT @biebersmaniacom: FOFO! Vídeo de Justin Bieber dizendo Eu te amo em polonês, e ele ficou bem feliz por conseguir falar! 😂 💕 (11/11)… ","user":{"id":2772368266,"name":"lana loves selena ❤","screen_name":"darlingomz","created_at":"Tue Sep 16 18:26:52 +0200 2014"},"created_at":"Sat Nov 12 21:04:16 +0100 2016"}',
            '{"id":797530472997851136,"text":"RT @JBCrewdotcom: Justin Bieber is currently performing Love Yourself on his acoustic set at the #PurposeTour in Czech Republic, Prague t…","user":{"id":2391843452,"name":"Alexandria","screen_name":"alandgrebe2009","created_at":"Sun Mar 16 01:56:30 +0100 2014"},"created_at":"Sat Nov 12 21:04:16 +0100 2016"}',
            '{"id":797530474021326848,"text":"RT @JBCrewdotcom: (6) Another video of Justin Bieber performing at the #PurposeTour in Prague, Czech Republic tonight. (November 12) https:…","user":{"id":81938932,"name":"Bambi ☪","screen_name":"__simplyb_","created_at":"Mon Oct 12 23:57:25 +0200 2009"},"created_at":"Sat Nov 12 21:04:17 +0100 2016"}',
            '{"id":797530476751810560,"text":"RT @JBCrewdotcom: (6) Another video of Justin Bieber performing at the #PurposeTour in Prague, Czech Republic tonight. (November 12) https:…","user":{"id":4152062495,"name":"❤️Justin Bieber❤️","screen_name":"Yasmin22000033","created_at":"Mon Nov 09 23:01:30 +0100 2015"},"created_at":"Sat Nov 12 21:04:17 +0100 2016"}',
            '{"id":797530477821382656,"text":"RT @JBCrewdotcom: (6) Another video of Justin Bieber performing at the #PurposeTour in Prague, Czech Republic tonight. (November 12) https:…","user":{"id":791727466327007232,"name":"THANK YOU JUSTIN💕","screen_name":"justinjbtime","created_at":"Thu Oct 27 21:45:12 +0200 2016"},"created_at":"Sat Nov 12 21:04:17 +0100 2016"}'
    ]

    static Observable<String> tweets() {
        Observable.from(tweetsData)
    }

}
