package com.ohair.stephen;

import java.util.List;

import com.ohair.stephen.jaxb.HardwareMonitorEntryType;
import com.ohair.stephen.jaxb.HardwareMonitorGpuEntryType;
import com.ohair.stephen.jaxb.HardwareMonitorType;

public class Util {

	public static void printHardwareMonitor(HardwareMonitorType hmt) {
		MainController mc = App.getController();
		List<HardwareMonitorGpuEntryType> gpuEntries = hmt
				.getHardwareMonitorGpuEntries().getHardwareMonitorGpuEntry();
		for (HardwareMonitorGpuEntryType gpu : gpuEntries) {

			mc.appendTxtAreaLogOutput("gpu.BIOS=" + gpu.getBIOS());
			mc.appendTxtAreaLogOutput("gpu.Device=" + gpu.getDevice());
			mc.appendTxtAreaLogOutput("gpu.Family=" + gpu.getFamily());
			mc.appendTxtAreaLogOutput("gpu.Driver=" + gpu.getDriver());
			mc.appendTxtAreaLogOutput("gpu.ID=" + gpu.getGpuId());
			mc.appendTxtAreaLogOutput("gpu.Memory.MB=" + gpu.getMemAmount());
		}

		List<HardwareMonitorEntryType> entries = hmt
				.getHardwareMonitorEntries().getHardwareMonitorEntry();
		for (HardwareMonitorEntryType entry : entries) {
			mc.appendTxtAreaLogOutput(entry.getSrcName() + "="
					+ entry.getData());
		}
	}
}
