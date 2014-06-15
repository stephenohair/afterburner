package com.ohair.stephen;

import java.util.List;

import com.ohair.stephen.jaxb.HardwareMonitorEntryType;
import com.ohair.stephen.jaxb.HardwareMonitorGpuEntryType;
import com.ohair.stephen.jaxb.HardwareMonitorType;

public class Util {

	public static void printHardwareMonitor(HardwareMonitorType hmt) {
		List<HardwareMonitorGpuEntryType> gpuEntries = hmt
				.getHardwareMonitorGpuEntries().getHardwareMonitorGpuEntry();
		for (HardwareMonitorGpuEntryType gpu : gpuEntries) {
			System.out.println("gpu.BIOS=" + gpu.getBIOS());
			System.out.println("gpu.Device=" + gpu.getDevice());
			System.out.println("gpu.Family=" + gpu.getFamily());
			System.out.println("gpu.Driver=" + gpu.getDriver());
			System.out.println("gpu.ID=" + gpu.getGpuId());
			System.out.println("gpu.Memory.MB=" + gpu.getMemAmount());
		}

		List<HardwareMonitorEntryType> entries = hmt
				.getHardwareMonitorEntries().getHardwareMonitorEntry();
		for (HardwareMonitorEntryType entry : entries) {
			System.out.println(entry.getSrcName() + "=" + entry.getData());
		}
	}
}
