package com.winio94

import org.junit.jupiter.api.Test

class AppTest {

    @Test
    void "should convert street and house number"() {
        def app = new App()

        def result = app.convert("Winterallee 3")

        assert "Winterallee" == result.street
        assert "3" == result.houseNumber
    }

    @Test
    void "should convert street and alphanumeric house number"() {
        def app = new App()

        def result = app.convert("Blaufeldweg 123B")

        assert "Blaufeldweg" == result.street
        assert "123B" == result.houseNumber
    }

    @Test
    void "should convert multi-word street and house number"() {
        def app = new App()

        def result = app.convert("Am Bächle 23")

        assert "Am Bächle" == result.street
        assert "23" == result.houseNumber
    }

    @Test
    void "should convert multi-word street and multi-word house number"() {
        def app = new App()

        def result = app.convert("Auf der Vogelwiese 23 b")

        assert "Auf der Vogelwiese" == result.street
        assert "23 b" == result.houseNumber
    }

    @Test
    void "should convert multi-word street with house number at the beginning divided by comma"() {
        def app = new App()

        def result = app.convert("4, rue de la revolution")

        assert "rue de la revolution" == result.street
        assert "4" == result.houseNumber
    }

    @Test
    void "should convert multi-word street with house number at the end divided by comma"() {
        def app = new App()

        def result = app.convert("Calle Aduana, 29")

        assert "Calle Aduana" == result.street
        assert "29" == result.houseNumber
    }

    @Test
    void "should convert multi-word street with house number at the beginning"() {
        def app = new App()

        def result = app.convert("200 Broadway Av")

        assert "Broadway Av" == result.street
        assert "200" == result.houseNumber
    }

    @Test
    void "should convert multi-word street containing number and house number with prefix"() {
        def app = new App()

        def result = app.convert("Calle 39 No 1540")

        assert "Calle 39" == result.street
        assert "No 1540" == result.houseNumber
    }
}
