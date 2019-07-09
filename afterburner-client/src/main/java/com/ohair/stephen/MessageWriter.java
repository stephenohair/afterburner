package com.ohair.stephen;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ohair.stephen.jaxb.HardwareMonitorEntryType;

public class MessageWriter {

	public static String getStatsInfo(List<HardwareMonitorEntryType> entries,
			Configuration config) {

		String item1 = "";
		String item2 = "";
		String item3 = "";
		String item4 = "";
		String item5 = "";
		String item6 = "";
		float cpuTotal = 0;
		int cpuCoreCount = 0;
		float cpuTempTotal = 0;
		int cpuTempCount = 0;

		for (HardwareMonitorEntryType entry : entries) {
			switch (entry.getSrcName()) {
			case "Framerate":
				item1 = StringUtils.rightPad("FPS:" + (int) entry.getData(), 8,
						" ");
				break;
			case "GPU1 usage":
				item2 = StringUtils.rightPad("GPU1:" + (int) entry.getData(),
						8, " ");
				break;
			case "GPU2 usage":
				item4 = StringUtils.rightPad("GPU2:" + (int) entry.getData(),
						8, " ");
				break;
			case "RAM usage":
				item5 = StringUtils.rightPad("RAM:" + (int) entry.getData(), 8,
						" ");
			default:
				break;
			}

			if (entry.getSrcName().contains("CPU")
					&& entry.getSrcName().contains("usage")) {
				cpuCoreCount++;
				cpuTotal += entry.getData();
			}

			if (entry.getSrcName().contains("CPU")
					&& entry.getSrcName().contains("temperature")) {
				cpuTempCount++;
				cpuTempTotal += entry.getData();
			}
		}
		// calucalte the average cpu usage of all cores
		item3 = StringUtils.rightPad("CPU:" + (int) (cpuTotal / cpuCoreCount),
				8, " ");
		// calucalte the average cpu usage of all cores
		item6 = StringUtils.rightPad("TEMP:"
				+ (int) (cpuTempTotal / cpuTempCount), 8, " ");

		if (item4.isEmpty() && !item5.isEmpty())
			item4 = item5;

		if (item1.isEmpty() && !item6.isEmpty())
			item1 = item6;

		return item1 + item2 + item3 + item4;

	}
}
