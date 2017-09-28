package pestre.bertrand.path.it;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PathWebServiceIT {

    @LocalServerPort
    int port;

    @Test
    public void testValidPath() throws IOException {
        File file = new ClassPathResource("map1.json").getFile();

        given()
                .port(port)
                .queryParam("begin", 10030)
                .queryParam("end", 10121)
                .body(file)
                .contentType(ContentType.JSON)
                .when()
                .post("/solve.json")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", hasItems(10030, 10012, 10121))
                .body("distance", hasItems(0, 30, 57));
    }

}
