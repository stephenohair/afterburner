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
			App.getController().appendTxtAreaLogOutput(
					"gpu.BIOS=" + gpu.getBIOS());
			App.getController().appendTxtAreaLogOutput(
					"gpu.Device=" + gpu.getDevice());
			App.getController().appendTxtAreaLogOutput(
					"gpu.Family=" + gpu.getFamily());
			App.getController().appendTxtAreaLogOutput(
					"gpu.Driver=" + gpu.getDriver());
			App.getController().appendTxtAreaLogOutput(
					"gpu.ID=" + gpu.getGpuId());
			App.getController().appendTxtAreaLogOutput(
					"gpu.Memory.MB=" + gpu.getMemAmount());
		}

		List<HardwareMonitorEntryType> entries = hmt
				.getHardwareMonitorEntries().getHardwareMonitorEntry();
		for (HardwareMonitorEntryType entry : entries) {
			App.getController().appendTxtAreaLogOutput(
					entry.getSrcName() + "=" + entry.getData());
		}
	}
}
