/*
 * Copyright (c) 2012-2020 Snowplow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at
 * http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and
 * limitations there under.
 */
package com.snowplowanalytics.snowplow.enrich.spark
package good

import org.specs2.mutable.Specification

object IabEnrichmentCfLineSpec {

  import EnrichJobSpec._

  val lines = Lines(
    "2012-05-27  11:35:53  DFW3  3343  216.160.83.56 GET d3gs014xn8p70.cloudfront.net  /ice.png  200 http://www.psychicbazaar.com/oracles/119-psycards-book-and-deck-starter-pack.html?view=print#detail Mozilla/5.0%20(Windows%20NT%206.1;%20WOW64;%20rv:12.0)%20Gecko/20100101%20Firefox/12.0  &e=ue&ue_pr=%7B%22schema%22%3A%22iglu%3Acom.snowplowanalytics.snowplow%2Funstruct_event%2Fjsonschema%2F1-0-0%22%2C%22data%22%3A%7B%22schema%22%3A%22iglu%3Acom.snowplowanalytics.snowplow.input-adapters%2Fsegment_webhook_config%2Fjsonschema%2F1-0-0%22%2C%22data%22%3A%7B%22vendor%22%3A%22%CE%A7%CE%B1%CF%81%CE%B9%CF%84%CE%AF%CE%BD%CE%B7%20NEW%20Unicode%20test%22%2C%22name%22%3A%22alex%2Btest%40snowplowanalytics.com%22%2C%22parameters%22%3A%7B%22mappings%22%3A%7B%22eventsPerMonth%22%3A%22%3C%201%20million%22%2C%22serviceType%22%3A%22unsure%22%7D%7D%7D%7D%7D&dtm=1364230969450&evn=com.acme&tid=598951&vp=2560x934&ds=2543x1420&vid=43&duid=9795bd0203804cd1&p=web&tv=js-0.11.1&fp=2876815413&aid=pbzsite&lang=en-GB&cs=UTF-8&tz=Europe%2FLondon&refr=http%3A%2F%2Fwww.psychicbazaar.com%2F&f_pdf=1&f_qt=0&f_realp=0&f_wma=0&f_dir=0&f_fla=1&f_java=1&f_gears=0&f_ag=1&res=2560x1440&cd=32&cookie=1&url=http%3A%2F%2Fwww.psychicbazaar.com%2F2-tarot-cards", // Browser
    "2012-05-27  11:35:53  DFW3  3343  216.160.83.56 GET d3gs014xn8p70.cloudfront.net  /ice.png  200 http://www.psychicbazaar.com/oracles/119-psycards-book-and-deck-starter-pack.html?view=print#detail Mozilla%2F5.0%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko%3B%20compatible%3B%20Googlebot%2F2.1%3B%20%2Bhttp%3A%2F%2Fwww.google.com%2Fbot.html)%20Safari%2F537.36  &e=ue&ue_pr=%7B%22schema%22%3A%22iglu%3Acom.snowplowanalytics.snowplow%2Funstruct_event%2Fjsonschema%2F1-0-0%22%2C%22data%22%3A%7B%22schema%22%3A%22iglu%3Acom.snowplowanalytics.snowplow.input-adapters%2Fsegment_webhook_config%2Fjsonschema%2F1-0-0%22%2C%22data%22%3A%7B%22vendor%22%3A%22%CE%A7%CE%B1%CF%81%CE%B9%CF%84%CE%AF%CE%BD%CE%B7%20NEW%20Unicode%20test%22%2C%22name%22%3A%22alex%2Btest%40snowplowanalytics.com%22%2C%22parameters%22%3A%7B%22mappings%22%3A%7B%22eventsPerMonth%22%3A%22%3C%201%20million%22%2C%22serviceType%22%3A%22unsure%22%7D%7D%7D%7D%7D&dtm=1364230969450&evn=com.acme&tid=598951&vp=2560x934&ds=2543x1420&vid=43&duid=9795bd0203804cd1&p=web&tv=js-0.11.1&fp=2876815413&aid=pbzsite&lang=en-GB&cs=UTF-8&tz=Europe%2FLondon&refr=http%3A%2F%2Fwww.psychicbazaar.com%2F&f_pdf=1&f_qt=0&f_realp=0&f_wma=0&f_dir=0&f_fla=1&f_java=1&f_gears=0&f_ag=1&res=2560x1440&cd=32&cookie=1&url=http%3A%2F%2Fwww.psychicbazaar.com%2F2-tarot-cards", // Spider or robot
    "2012-05-27  11:35:53  DFW3  3343  216.160.83.56:8080 GET d3gs014xn8p70.cloudfront.net  /ice.png  200 http://www.psychicbazaar.com/oracles/119-psycards-book-and-deck-starter-pack.html?view=print#detail Mozilla%2F5.0%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko%3B%20compatible%3B%20Googlebot%2F2.1%3B%20%2Bhttp%3A%2F%2Fwww.google.com%2Fbot.html)%20Safari%2F537.36  &e=ue&ue_pr=%7B%22schema%22%3A%22iglu%3Acom.snowplowanalytics.snowplow%2Funstruct_event%2Fjsonschema%2F1-0-0%22%2C%22data%22%3A%7B%22schema%22%3A%22iglu%3Acom.snowplowanalytics.snowplow.input-adapters%2Fsegment_webhook_config%2Fjsonschema%2F1-0-0%22%2C%22data%22%3A%7B%22vendor%22%3A%22%CE%A7%CE%B1%CF%81%CE%B9%CF%84%CE%AF%CE%BD%CE%B7%20NEW%20Unicode%20test%22%2C%22name%22%3A%22alex%2Btest%40snowplowanalytics.com%22%2C%22parameters%22%3A%7B%22mappings%22%3A%7B%22eventsPerMonth%22%3A%22%3C%201%20million%22%2C%22serviceType%22%3A%22unsure%22%7D%7D%7D%7D%7D&dtm=1364230969450&evn=com.acme&tid=598951&vp=2560x934&ds=2543x1420&vid=43&duid=9795bd0203804cd1&p=web&tv=js-0.11.1&fp=2876815413&aid=pbzsite&lang=en-GB&cs=UTF-8&tz=Europe%2FLondon&refr=http%3A%2F%2Fwww.psychicbazaar.com%2F&f_pdf=1&f_qt=0&f_realp=0&f_wma=0&f_dir=0&f_fla=1&f_java=1&f_gears=0&f_ag=1&res=2560x1440&cd=32&cookie=1&url=http%3A%2F%2Fwww.psychicbazaar.com%2F2-tarot-cards", // IPv4 with port
    "2012-05-27  11:35:53  DFW3  3343  2001:db8:0:0:0:ff00:42:8329  GET d3gs014xn8p70.cloudfront.net  /ice.png  200 http://www.psychicbazaar.com/oracles/119-psycards-book-and-deck-starter-pack.html?view=print#detail Mozilla%2F5.0%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko%3B%20compatible%3B%20Googlebot%2F2.1%3B%20%2Bhttp%3A%2F%2Fwww.google.com%2Fbot.html)%20Safari%2F537.36  &e=ue&ue_pr=%7B%22schema%22%3A%22iglu%3Acom.snowplowanalytics.snowplow%2Funstruct_event%2Fjsonschema%2F1-0-0%22%2C%22data%22%3A%7B%22schema%22%3A%22iglu%3Acom.snowplowanalytics.snowplow.input-adapters%2Fsegment_webhook_config%2Fjsonschema%2F1-0-0%22%2C%22data%22%3A%7B%22vendor%22%3A%22%CE%A7%CE%B1%CF%81%CE%B9%CF%84%CE%AF%CE%BD%CE%B7%20NEW%20Unicode%20test%22%2C%22name%22%3A%22alex%2Btest%40snowplowanalytics.com%22%2C%22parameters%22%3A%7B%22mappings%22%3A%7B%22eventsPerMonth%22%3A%22%3C%201%20million%22%2C%22serviceType%22%3A%22unsure%22%7D%7D%7D%7D%7D&dtm=1364230969450&evn=com.acme&tid=598951&vp=2560x934&ds=2543x1420&vid=43&duid=9795bd0203804cd1&p=web&tv=js-0.11.1&fp=2876815413&aid=pbzsite&lang=en-GB&cs=UTF-8&tz=Europe%2FLondon&refr=http%3A%2F%2Fwww.psychicbazaar.com%2F&f_pdf=1&f_qt=0&f_realp=0&f_wma=0&f_dir=0&f_fla=1&f_java=1&f_gears=0&f_ag=1&res=2560x1440&cd=32&cookie=1&url=http%3A%2F%2Fwww.psychicbazaar.com%2F2-tarot-cards", // IPv6
    s"2012-05-27  11:35:53  DFW3  3343  216.160.83.56 GET d3gs014xn8p70.cloudfront.net  /ice.png  200 http://www.psychicbazaar.com/oracles/119-psycards-book-and-deck-starter-pack.html?view=print#detail \0 &e=ue&ue_pr=%7B%22schema%22%3A%22iglu%3Acom.snowplowanalytics.snowplow%2Funstruct_event%2Fjsonschema%2F1-0-0%22%2C%22data%22%3A%7B%22schema%22%3A%22iglu%3Acom.snowplowanalytics.snowplow.input-adapters%2Fsegment_webhook_config%2Fjsonschema%2F1-0-0%22%2C%22data%22%3A%7B%22vendor%22%3A%22%CE%A7%CE%B1%CF%81%CE%B9%CF%84%CE%AF%CE%BD%CE%B7%20NEW%20Unicode%20test%22%2C%22name%22%3A%22alex%2Btest%40snowplowanalytics.com%22%2C%22parameters%22%3A%7B%22mappings%22%3A%7B%22eventsPerMonth%22%3A%22%3C%201%20million%22%2C%22serviceType%22%3A%22unsure%22%7D%7D%7D%7D%7D&dtm=1364230969450&evn=com.acme&tid=598951&vp=2560x934&ds=2543x1420&vid=43&duid=9795bd0203804cd1&p=web&tv=js-0.11.1&fp=2876815413&aid=pbzsite&lang=en-GB&cs=UTF-8&tz=Europe%2FLondon&refr=http%3A%2F%2Fwww.psychicbazaar.com%2F&f_pdf=1&f_qt=0&f_realp=0&f_wma=0&f_dir=0&f_fla=1&f_java=1&f_gears=0&f_ag=1&res=2560x1440&cd=32&cookie=1&url=http%3A%2F%2Fwww.psychicbazaar.com%2F2-tarot-cards" // empty useragent
  )
  val expected = List(
    List( // Browser
      "pbzsite",
      "web",
      etlTimestamp,
      "2012-05-27 11:35:53.000",
      "2013-03-25 17:02:49.450",
      "unstruct",
      null, // We can't predict the event_id
      "598951",
      null, // No tracker namespace
      "js-0.11.1",
      "cloudfront",
      etlVersion,
      null, // No user_id set
      "17803d07577023208861c69cbccbdfc0a041b06c",
      "2876815413",
      "9795bd0203804cd1",
      "43",
      null, // No network_userid set
      "US", // US geolocation
      "WA",
      "Milton",
      "98354",
      "47.2513",
      "-122.3149",
      "Washington",
      null,
      null,
      null, // Using the MaxMind domain lookup service
      null,
      "http://www.psychicbazaar.com/2-tarot-cards",
      null, // No page title for events
      "http://www.psychicbazaar.com/",
      "http",
      "www.psychicbazaar.com",
      "80",
      "/2-tarot-cards",
      null,
      null,
      "http",
      "www.psychicbazaar.com",
      "80",
      "/",
      null,
      null,
      "internal", // Internal referer
      null,
      null,
      null, // No marketing campaign info
      null, //
      null, //
      null, //
      null, //
      null, // No custom contexts
      null, // Structured event fields empty
      null, //
      null, //
      null, //
      null, //
      """{"schema":"iglu:com.snowplowanalytics.snowplow/unstruct_event/jsonschema/1-0-0","data":{"schema":"iglu:com.snowplowanalytics.snowplow.input-adapters/segment_webhook_config/jsonschema/1-0-0","data":{"vendor":"Χαριτίνη NEW Unicode test","name":"alex+test@snowplowanalytics.com","parameters":{"mappings":{"eventsPerMonth":"< 1 million","serviceType":"unsure"}}}}}""", // Unstructured event field set
      null, // Transaction fields empty
      null, //
      null, //
      null, //
      null, //
      null, //
      null, //
      null, //
      null, // Transaction item fields empty
      null, //
      null, //
      null, //
      null, //
      null, //
      null, // Page ping fields are empty
      null, //
      null, //
      null, //
      "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:12.0) Gecko/20100101 Firefox/12.0",
      "Firefox 12",
      "Firefox",
      "12.0",
      "Browser",
      "GECKO",
      "en-GB",
      "1",
      "1",
      "1",
      "0",
      "0",
      "0",
      "0",
      "0",
      "1",
      "1",
      "32",
      "2560",
      "934",
      "Windows 7",
      "Windows",
      "Microsoft Corporation",
      "Europe/London",
      "Computer",
      "0",
      "2560",
      "1440",
      "UTF-8",
      "2543",
      "1420",
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      "America/Los_Angeles",
      null,
      null,
      null,
      null,
      null,
      null,
      """{"schema":"iglu:com.snowplowanalytics.snowplow/contexts/jsonschema/1-0-1","data":[{"schema":"iglu:com.snowplowanalytics.snowplow/ua_parser_context/jsonschema/1-0-0","data":{"useragentFamily":"Firefox","useragentMajor":"12","useragentMinor":"0","useragentPatch":null,"useragentVersion":"Firefox 12.0","osFamily":"Windows","osMajor":"7","osMinor":null,"osPatch":null,"osPatchMinor":null,"osVersion":"Windows 7","deviceFamily":"Other"}},{"schema":"iglu:com.iab.snowplow/spiders_and_robots/jsonschema/1-0-0","data":{"spiderOrRobot":false,"category":"BROWSER","reason":"PASSED_ALL","primaryImpact":"NONE"}}]}""",
      null,
      "2012-05-27 11:35:53.000",
      "com.snowplowanalytics.snowplow.input-adapters",
      "segment_webhook_config",
      "jsonschema",
      "1-0-0",
      "6a2f7bbf7669eb1808c79cfe3e1f8e57"
    ),
    List( // Spider or robot
      "pbzsite",
      "web",
      etlTimestamp,
      "2012-05-27 11:35:53.000",
      "2013-03-25 17:02:49.450",
      "unstruct",
      null, // We can't predict the event_id
      "598951",
      null, // No tracker namespace
      "js-0.11.1",
      "cloudfront",
      etlVersion,
      null, // No user_id set
      "17803d07577023208861c69cbccbdfc0a041b06c",
      "2876815413",
      "9795bd0203804cd1",
      "43",
      null, // No network_userid set
      "US", // US geolocation
      "WA",
      "Milton",
      "98354",
      "47.2513",
      "-122.3149",
      "Washington",
      null,
      null,
      null, // Using the MaxMind domain lookup service
      null,
      "http://www.psychicbazaar.com/2-tarot-cards",
      null, // No page title for events
      "http://www.psychicbazaar.com/",
      "http",
      "www.psychicbazaar.com",
      "80",
      "/2-tarot-cards",
      null,
      null,
      "http",
      "www.psychicbazaar.com",
      "80",
      "/",
      null,
      null,
      "internal", // Internal referer
      null,
      null,
      null, // No marketing campaign info
      null, //
      null, //
      null, //
      null, //
      null, // No custom contexts
      null, // Structured event fields empty
      null, //
      null, //
      null, //
      null, //
      """{"schema":"iglu:com.snowplowanalytics.snowplow/unstruct_event/jsonschema/1-0-0","data":{"schema":"iglu:com.snowplowanalytics.snowplow.input-adapters/segment_webhook_config/jsonschema/1-0-0","data":{"vendor":"Χαριτίνη NEW Unicode test","name":"alex+test@snowplowanalytics.com","parameters":{"mappings":{"eventsPerMonth":"< 1 million","serviceType":"unsure"}}}}}""", // Unstructured event field set
      null, // Transaction fields empty
      null, //
      null, //
      null, //
      null, //
      null, //
      null, //
      null, //
      null, // Transaction item fields empty
      null, //
      null, //
      null, //
      null, //
      null, //
      null, // Page ping fields are empty
      null, //
      null, //
      null, //
      "Mozilla/5.0 AppleWebKit/537.36 (KHTML, like Gecko; compatible; Googlebot/2.1; +http://www.google.com/bot.html) Safari/537.36",
      "Robot/Spider",
      "Robot/Spider",
      null,
      "Robot",
      "OTHER",
      "en-GB",
      "1",
      "1",
      "1",
      "0",
      "0",
      "0",
      "0",
      "0",
      "1",
      "1",
      "32",
      "2560",
      "934",
      "Unknown",
      "Unknown",
      "Other",
      "Europe/London",
      "Unknown",
      "0",
      "2560",
      "1440",
      "UTF-8",
      "2543",
      "1420",
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      "America/Los_Angeles",
      null,
      null,
      null,
      null,
      null,
      null,
      """{"schema":"iglu:com.snowplowanalytics.snowplow/contexts/jsonschema/1-0-1","data":[{"schema":"iglu:com.snowplowanalytics.snowplow/ua_parser_context/jsonschema/1-0-0","data":{"useragentFamily":"Googlebot","useragentMajor":"2","useragentMinor":"1","useragentPatch":null,"useragentVersion":"Googlebot 2.1","osFamily":"Other","osMajor":null,"osMinor":null,"osPatch":null,"osPatchMinor":null,"osVersion":"Other","deviceFamily":"Spider"}},{"schema":"iglu:com.iab.snowplow/spiders_and_robots/jsonschema/1-0-0","data":{"spiderOrRobot":true,"category":"SPIDER_OR_ROBOT","reason":"FAILED_UA_INCLUDE","primaryImpact":"UNKNOWN"}}]}""",
      null,
      "2012-05-27 11:35:53.000",
      "com.snowplowanalytics.snowplow.input-adapters",
      "segment_webhook_config",
      "jsonschema",
      "1-0-0",
      "6a2f7bbf7669eb1808c79cfe3e1f8e57"
    ),
    List( // IPv4 with port
      "pbzsite",
      "web",
      etlTimestamp,
      "2012-05-27 11:35:53.000",
      "2013-03-25 17:02:49.450",
      "unstruct",
      null, // We can't predict the event_id
      "598951",
      null, // No tracker namespace
      "js-0.11.1",
      "cloudfront",
      etlVersion,
      null, // No user_id set
      "4ed25a4b97b34bf0dbbe2ba0899e868743b0c5d2",
      "2876815413",
      "9795bd0203804cd1",
      "43",
      null, // No network_userid set
      "US", // US geolocation
      "WA",
      "Milton",
      "98354",
      "47.2513",
      "-122.3149",
      "Washington",
      null,
      null,
      null, // Using the MaxMind domain lookup service
      null,
      "http://www.psychicbazaar.com/2-tarot-cards",
      null, // No page title for events
      "http://www.psychicbazaar.com/",
      "http",
      "www.psychicbazaar.com",
      "80",
      "/2-tarot-cards",
      null,
      null,
      "http",
      "www.psychicbazaar.com",
      "80",
      "/",
      null,
      null,
      "internal", // Internal referer
      null,
      null,
      null, // No marketing campaign info
      null, //
      null, //
      null, //
      null, //
      null, // No custom contexts
      null, // Structured event fields empty
      null, //
      null, //
      null, //
      null, //
      """{"schema":"iglu:com.snowplowanalytics.snowplow/unstruct_event/jsonschema/1-0-0","data":{"schema":"iglu:com.snowplowanalytics.snowplow.input-adapters/segment_webhook_config/jsonschema/1-0-0","data":{"vendor":"Χαριτίνη NEW Unicode test","name":"alex+test@snowplowanalytics.com","parameters":{"mappings":{"eventsPerMonth":"< 1 million","serviceType":"unsure"}}}}}""", // Unstructured event field set
      null, // Transaction fields empty
      null, //
      null, //
      null, //
      null, //
      null, //
      null, //
      null, //
      null, // Transaction item fields empty
      null, //
      null, //
      null, //
      null, //
      null, //
      null, // Page ping fields are empty
      null, //
      null, //
      null, //
      "Mozilla/5.0 AppleWebKit/537.36 (KHTML, like Gecko; compatible; Googlebot/2.1; +http://www.google.com/bot.html) Safari/537.36",
      "Robot/Spider",
      "Robot/Spider",
      null,
      "Robot",
      "OTHER",
      "en-GB",
      "1",
      "1",
      "1",
      "0",
      "0",
      "0",
      "0",
      "0",
      "1",
      "1",
      "32",
      "2560",
      "934",
      "Unknown",
      "Unknown",
      "Other",
      "Europe/London",
      "Unknown",
      "0",
      "2560",
      "1440",
      "UTF-8",
      "2543",
      "1420",
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      "America/Los_Angeles",
      null,
      null,
      null,
      null,
      null,
      null,
      """{"schema":"iglu:com.snowplowanalytics.snowplow/contexts/jsonschema/1-0-1","data":[{"schema":"iglu:com.snowplowanalytics.snowplow/ua_parser_context/jsonschema/1-0-0","data":{"useragentFamily":"Googlebot","useragentMajor":"2","useragentMinor":"1","useragentPatch":null,"useragentVersion":"Googlebot 2.1","osFamily":"Other","osMajor":null,"osMinor":null,"osPatch":null,"osPatchMinor":null,"osVersion":"Other","deviceFamily":"Spider"}},{"schema":"iglu:com.iab.snowplow/spiders_and_robots/jsonschema/1-0-0","data":{"spiderOrRobot":true,"category":"SPIDER_OR_ROBOT","reason":"FAILED_UA_INCLUDE","primaryImpact":"UNKNOWN"}}]}""",
      null,
      "2012-05-27 11:35:53.000",
      "com.snowplowanalytics.snowplow.input-adapters",
      "segment_webhook_config",
      "jsonschema",
      "1-0-0",
      "6a2f7bbf7669eb1808c79cfe3e1f8e57"
    ),
    List( // IPv6
      "pbzsite",
      "web",
      etlTimestamp,
      "2012-05-27 11:35:53.000",
      "2013-03-25 17:02:49.450",
      "unstruct",
      null, // We can't predict the event_id
      "598951",
      null, // No tracker namespace
      "js-0.11.1",
      "cloudfront",
      etlVersion,
      null, // No user_id set
      "64083e4f337efb8e8ca3fb81a360a67836ebafe6",
      "2876815413",
      "9795bd0203804cd1",
      "43",
      null, // No network_userid set
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null, // Using the MaxMind domain lookup service
      null,
      "http://www.psychicbazaar.com/2-tarot-cards",
      null, // No page title for events
      "http://www.psychicbazaar.com/",
      "http",
      "www.psychicbazaar.com",
      "80",
      "/2-tarot-cards",
      null,
      null,
      "http",
      "www.psychicbazaar.com",
      "80",
      "/",
      null,
      null,
      "internal", // Internal referer
      null,
      null,
      null, // No marketing campaign info
      null, //
      null, //
      null, //
      null, //
      null, // No custom contexts
      null, // Structured event fields empty
      null, //
      null, //
      null, //
      null, //
      """{"schema":"iglu:com.snowplowanalytics.snowplow/unstruct_event/jsonschema/1-0-0","data":{"schema":"iglu:com.snowplowanalytics.snowplow.input-adapters/segment_webhook_config/jsonschema/1-0-0","data":{"vendor":"Χαριτίνη NEW Unicode test","name":"alex+test@snowplowanalytics.com","parameters":{"mappings":{"eventsPerMonth":"< 1 million","serviceType":"unsure"}}}}}""", // Unstructured event field set
      null, // Transaction fields empty
      null, //
      null, //
      null, //
      null, //
      null, //
      null, //
      null, //
      null, // Transaction item fields empty
      null, //
      null, //
      null, //
      null, //
      null, //
      null, // Page ping fields are empty
      null, //
      null, //
      null, //
      "Mozilla/5.0 AppleWebKit/537.36 (KHTML, like Gecko; compatible; Googlebot/2.1; +http://www.google.com/bot.html) Safari/537.36",
      "Robot/Spider",
      "Robot/Spider",
      null,
      "Robot",
      "OTHER",
      "en-GB",
      "1",
      "1",
      "1",
      "0",
      "0",
      "0",
      "0",
      "0",
      "1",
      "1",
      "32",
      "2560",
      "934",
      "Unknown",
      "Unknown",
      "Other",
      "Europe/London",
      "Unknown",
      "0",
      "2560",
      "1440",
      "UTF-8",
      "2543",
      "1420",
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      """{"schema":"iglu:com.snowplowanalytics.snowplow/contexts/jsonschema/1-0-1","data":[{"schema":"iglu:com.snowplowanalytics.snowplow/ua_parser_context/jsonschema/1-0-0","data":{"useragentFamily":"Googlebot","useragentMajor":"2","useragentMinor":"1","useragentPatch":null,"useragentVersion":"Googlebot 2.1","osFamily":"Other","osMajor":null,"osMinor":null,"osPatch":null,"osPatchMinor":null,"osVersion":"Other","deviceFamily":"Spider"}}]}""",
      null,
      "2012-05-27 11:35:53.000",
      "com.snowplowanalytics.snowplow.input-adapters",
      "segment_webhook_config",
      "jsonschema",
      "1-0-0",
      "6a2f7bbf7669eb1808c79cfe3e1f8e57"
    ),
    List( // empty user agent
      "pbzsite",
      "web",
      etlTimestamp,
      "2012-05-27 11:35:53.000",
      "2013-03-25 17:02:49.450",
      "unstruct",
      null, // We can't predict the event_id
      "598951",
      null, // No tracker namespace
      "js-0.11.1",
      "cloudfront",
      etlVersion,
      null, // No user_id set
      "17803d07577023208861c69cbccbdfc0a041b06c",
      "2876815413",
      "9795bd0203804cd1",
      "43",
      null, // No network_userid set
      "US", // US geolocation
      "WA",
      "Milton",
      "98354",
      "47.2513",
      "-122.3149",
      "Washington",
      null,
      null,
      null, // Using the MaxMind domain lookup service
      null,
      "http://www.psychicbazaar.com/2-tarot-cards",
      null, // No page title for events
      "http://www.psychicbazaar.com/",
      "http",
      "www.psychicbazaar.com",
      "80",
      "/2-tarot-cards",
      null,
      null,
      "http",
      "www.psychicbazaar.com",
      "80",
      "/",
      null,
      null,
      "internal", // Internal referer
      null,
      null,
      null, // No marketing campaign info
      null, //
      null, //
      null, //
      null, //
      null, // No custom contexts
      null, // Structured event fields empty
      null, //
      null, //
      null, //
      null, //
      """{"schema":"iglu:com.snowplowanalytics.snowplow/unstruct_event/jsonschema/1-0-0","data":{"schema":"iglu:com.snowplowanalytics.snowplow.input-adapters/segment_webhook_config/jsonschema/1-0-0","data":{"vendor":"Χαριτίνη NEW Unicode test","name":"alex+test@snowplowanalytics.com","parameters":{"mappings":{"eventsPerMonth":"< 1 million","serviceType":"unsure"}}}}}""", // Unstructured event field set
      null, // Transaction fields empty
      null, //
      null, //
      null, //
      null, //
      null, //
      null, //
      null, //
      null, // Transaction item fields empty
      null, //
      null, //
      null, //
      null, //
      null, //
      null, // Page ping fields are empty
      null, //
      null, //
      null, //
      null,
      "Unknown",
      "Unknown",
      null,
      "unknown",
      "OTHER",
      "en-GB",
      "1",
      "1",
      "1",
      "0",
      "0",
      "0",
      "0",
      "0",
      "1",
      "1",
      "32",
      "2560",
      "934",
      "Unknown",
      "Unknown",
      "Other",
      "Europe/London",
      "Unknown",
      "0",
      "2560",
      "1440",
      "UTF-8",
      "2543",
      "1420",
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      "America/Los_Angeles",
      null,
      null,
      null,
      null,
      null,
      null,
      """{"schema":"iglu:com.snowplowanalytics.snowplow/contexts/jsonschema/1-0-1","data":[{"schema":"iglu:com.snowplowanalytics.snowplow/ua_parser_context/jsonschema/1-0-0","data":{"useragentFamily":"Other","useragentMajor":null,"useragentMinor":null,"useragentPatch":null,"useragentVersion":"Other","osFamily":"Other","osMajor":null,"osMinor":null,"osPatch":null,"osPatchMinor":null,"osVersion":"Other","deviceFamily":"Other"}}]}""",
      null,
      "2012-05-27 11:35:53.000",
      "com.snowplowanalytics.snowplow.input-adapters",
      "segment_webhook_config",
      "jsonschema",
      "1-0-0",
      "6a2f7bbf7669eb1808c79cfe3e1f8e57"
    )
  )
}

/**
 * Check that the IAB enrichment successfully atttaches a context
 * to an event.
 */
class IabEnrichmentCfLineSpec extends Specification with EnrichJobSpec {

  import EnrichJobSpec._

  override def appName = "iab-enrichment-cf-lines"

  sequential
  "A job which processes a CloudFront file containing 2 custom unstructured events" should {
    runEnrichJob(
      IabEnrichmentCfLineSpec.lines,
      "cloudfront",
      "2",
      false,
      List("geo", "domain"),
      iabEnrichmentEnabled = true)

    "correctly attach a derived context fetched from the IAB enrichment" in {
      val Some(goods) = readPartFile(dirs.output)
      goods.size must_== 5
      val actual = goods.map(evt => evt.split("\t").map(s => if (s.isEmpty()) null else s))
      for {
        evtIdx <- IabEnrichmentCfLineSpec.expected.indices
        idx    <- IabEnrichmentCfLineSpec.expected(evtIdx).indices
      } {
        actual(evtIdx)(idx) must BeFieldEqualTo(IabEnrichmentCfLineSpec.expected(evtIdx)(idx), idx)
      }
    }

    "not write any bad rows" in {
      dirs.badRows must beEmptyDir
    }
  }
}
