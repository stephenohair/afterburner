package com.ohair.stephen;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import org.junit.Test;

import com.ohair.stephen.jaxb.HardwareMonitorType;

public class MyUnmarshallerTest {
    @Test
    public void testCanCreateUnmarshaller() {
        assertNotNull(new MyUnmarshaller());
    }

    @Test
    public void testUnmarshalIsNotNull() {
        MyUnmarshaller unmarshaller = new MyUnmarshaller();
        InputStream is = MyUnmarshallerTest.class.getClassLoader().getResourceAsStream("sample-dual-gfx.xml");
        HardwareMonitorType hmt = unmarshaller.unmarshal(is);
        assertNotNull(hmt);
    }

}
