package com.kkulpa.moviesservice.backend.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;


@SpringBootTest
class ExchangeClientTest {



    @Test
    void shouldReturnExchangeResult() throws IOException {
        //given
        ExchangeClient exchangeClient = new ExchangeClient();

        //when
        double result = exchangeClient.convertCurrencies("USD", "PLN",  "1000");

        //then
        Assertions.assertTrue(result>0);



    }

}