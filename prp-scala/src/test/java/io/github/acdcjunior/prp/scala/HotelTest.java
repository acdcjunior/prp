package io.github.acdcjunior.prp.scala;

import org.junit.Test;

import static org.junit.Assert.*;

public class HotelTest {

    @Test
    public void name() {
        Hotel hotel = new Hotel();
        hotel.setName("aew");
        assertEquals("aew", hotel.getName());
        assertEquals("aew", hotel.name());
        hotel.go();
        assertEquals("ok", hotel.zip());
    }

}