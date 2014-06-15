package com.ohair.stephen;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

import com.ohair.stephen.jaxb.HardwareMonitorType;

public class MessageWriterTest {

	private static final String DUAL_CARD_EXPECTED_MESSAGE = "FPS:0   GPU1:1  CPU:16  GPU2:3  ";
	private static final String SINGLE_CARD_EXPECTED_MESSAGE = "FPS:0   GPU1:1  CPU:16  RAM:2802";

	@Test
	public void testGetStatsInfoGivenValidDataFromDualGfxCardReturnsValidMessage() {
		MyUnmarshaller unmarshaller = new MyUnmarshaller();
		InputStream is = MyUnmarshallerTest.class.getClassLoader()
				.getResourceAsStream("sample-dual-gfx.xml");
		HardwareMonitorType hmt = unmarshaller.unmarshal(is);
		Configuration config = new Configuration();
		String message = MessageWriter.getStatsInfo(hmt
				.getHardwareMonitorEntries().getHardwareMonitorEntry(), config);
		System.out.println(message);
		assertEquals(DUAL_CARD_EXPECTED_MESSAGE, message);
	}

	@Test
	public void testGetStatsInfoGivenValidDataFromSingleGfxCardReturnsValidMessage() {
		MyUnmarshaller unmarshaller = new MyUnmarshaller();
		InputStream is = MyUnmarshallerTest.class.getClassLoader()
				.getResourceAsStream("sample-single-gfx.xml");
		HardwareMonitorType hmt = unmarshaller.unmarshal(is);
		Configuration config = new Configuration();
		String message = MessageWriter.getStatsInfo(hmt
				.getHardwareMonitorEntries().getHardwareMonitorEntry(), config);
		System.out.println(message);
		assertEquals(SINGLE_CARD_EXPECTED_MESSAGE, message);
	}
}
