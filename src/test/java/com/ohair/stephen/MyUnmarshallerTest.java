package com.ohair.stephen;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.ohair.stephen.jaxb.HardwareMonitorEntryType;
import com.ohair.stephen.jaxb.HardwareMonitorGpuEntryType;
import com.ohair.stephen.jaxb.HardwareMonitorType;
import com.ohair.stephen.jaxb.ObjectFactory;

public class MyUnmarshallerTest {
	@Test
	public void testCanCreateUnmarshaller() {
		assertNotNull(new MyUnmarshaller());
	}

	@Test
	public void testUnmarshalIsNotNull() {
		MyUnmarshaller unmarshaller = new MyUnmarshaller();
		InputStream is = MyUnmarshallerTest.class.getClassLoader()
				.getResourceAsStream("sample-dual-gfx.xml");
		HardwareMonitorType hmt = unmarshaller.unmarshal(is);
		assertNotNull(hmt);
	}

}
