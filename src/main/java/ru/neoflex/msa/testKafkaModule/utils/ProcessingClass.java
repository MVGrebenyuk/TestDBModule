package ru.neoflex.msa.testKafkaModule.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.support.DefaultMessage;
import ru.neoflex.msa.testKafkaModule.models.MyMessage;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProcessingClass implements Processor {

    private ObjectMapper mapper;
    private String id;
    private String cardNumber;


    @Override
    public void process(Exchange exchange) throws Exception {

        mapper = new ObjectMapper();
        Message msg = exchange.getIn();
        String body = msg.getBody(String.class);

        System.out.println("PROCESS >>> " + exchange.getIn().getBody());

        MyMessage myMessage = mapper.readValue(body, MyMessage.class);

        id = myMessage.getResult().getCardId();
        cardNumber = myMessage.getResult().getCardId();

        setExchangeMessage(myMessage, exchange);

    }

    private void setExchangeMessage(MyMessage myMessage, Exchange exchange) {

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String myDate = formatter.format(new Date());

        Message message = new DefaultMessage(exchange);
        message.setHeader("key", Integer.parseInt(myMessage.getResult().getCardId()));
        message.setHeader("keyTwo",  myDate);
        message.setHeader("keyThree", myMessage.getResult().getMessage());
        message.setBody("insert into testdb values (:?key , :?keyTwo, :?keyThree)");
        exchange.setMessage(message);
    }
}
