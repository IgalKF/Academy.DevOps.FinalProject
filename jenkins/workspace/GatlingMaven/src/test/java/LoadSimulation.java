import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class LoadSimulation extends Simulation {

        {
                HttpProtocolBuilder httpProtocol = http
                                .baseUrl("http://192.168.31.116:8081")
                                .inferHtmlResources(AllowList(),
                                                DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg",
                                                                ".*\\.ico", ".*\\.woff",
                                                                ".*\\.woff2",
                                                                ".*\\.(t|o)tf", ".*\\.png",
                                                                ".*detectportal\\.firefox\\.com.*"))
                                .acceptHeader(
                                                "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                                .acceptEncodingHeader("gzip, deflate")
                                .acceptLanguageHeader("en-US,en;q=0.9,he-IL;q=0.8,he;q=0.7")
                                .upgradeInsecureRequestsHeader("1")
                                .userAgentHeader(
                                                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, likeGecko) Chrome/109.0.0.0 Safari/537.36");

                Map<CharSequence, String> headers_0 = new HashMap<>();
                headers_0.put("Cache-Control", "max-age=0");
                headers_0.put("If-Modified-Since", "Fri, 20 Jan 2023 08:58:50 GMT");
                headers_0.put("If-None-Match", "W/\"754-1674205130506\"");

                ScenarioBuilder scn = scenario("2mLoadCheck").during(Duration.ofMinutes(2)).on(
                                exec(
                                                http("request_0")
                                                                .get("/IgalNisanDanielNissim/")
                                                                .headers(headers_0)));

                setUp(
                                scn.injectOpen(rampUsers(27).during(10))).protocols(httpProtocol);
        }
}
