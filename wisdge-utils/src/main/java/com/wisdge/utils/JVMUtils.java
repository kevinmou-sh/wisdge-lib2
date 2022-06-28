package com.wisdge.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Jvm信息收集类
 *
 * @version 2.0.0
 * @author KevinMOU
 */
public class JVMUtils {

	public static List<InfoBean> getJVMInfo() {
		List<InfoBean> infos = new ArrayList<>();

		// Java 虚拟机线程系统的管理接口 ThreadMXBean
		ThreadMXBean th = ManagementFactory.getThreadMXBean();
		infos.add(new InfoBean("ThreadCount", th.getThreadCount(), "活动线程的当前数目"));
		infos.add(new InfoBean("DaemonThreadCount", th.getDaemonThreadCount(), "活动守护线程的当前数目"));
		infos.add(new InfoBean("PeakThreadCount", th.getPeakThreadCount(), "虚拟机启动或峰值重置以来峰值活动线程计数"));
		infos.add(new InfoBean("CurrentThreadUserTime", th.getCurrentThreadUserTime(), "当前线程的总CPU时间（毫秒）"));
		infos.add(new InfoBean("CurrentThreadUserTime", th.getCurrentThreadUserTime(), "当前线程在用户模式中执行的CPU时间"));

		// Java 虚拟机的运行时系统的管理接口。 RuntimeMXBean
		RuntimeMXBean run = ManagementFactory.getRuntimeMXBean();
		infos.add(new InfoBean("JvmSpecName", run.getSpecName(), "虚拟机规范名称"));
		infos.add(new InfoBean("JvmName", run.getName(), "当前虚拟机的名称"));
		infos.add(new InfoBean("ClassPath", run.getClassPath(), "系统类加载器用于搜索类文件的Java类路径"));
		infos.add(new InfoBean("LibraryPath", run.getLibraryPath(), "JAVA库路径"));

		// Java 虚拟机内存系统的管理接口 MemoryMXBean
		MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
		infos.add(new InfoBean("HeapMemoryUsage", mem.getHeapMemoryUsage(), "用于对象分配的堆的当前内存使用量"));
		infos.add(new InfoBean("NonHeapMemoryUsage", mem.getNonHeapMemoryUsage(), "虚拟机使用的非堆内存的当前内存使用量"));
		// Java 虚拟机的编译系统的管理接口 CompilationMXBean
		CompilationMXBean com = ManagementFactory.getCompilationMXBean();
		infos.add(new InfoBean("ComName", com.getName(), "即时 (JIT)编译器的名称"));
		infos.add(new InfoBean("TotalCompilationTime", com.getTotalCompilationTime(), "编译花费的累积耗费时间的近似值(毫秒)"));

		// Java 虚拟机的类加载系统的管理接口 ClassLoadingMXBean
		ClassLoadingMXBean cl = ManagementFactory.getClassLoadingMXBean();
		infos.add(new InfoBean("LoadedClassCount", cl.getLoadedClassCount(), "当前加载到虚拟机中的类的数量"));
		infos.add(new InfoBean("TotalLoadedClassCount", cl.getTotalLoadedClassCount(), "虚拟机开始执行到目前已经加载的类的总数"));
		infos.add(new InfoBean("UnloadedClassCount", cl.getUnloadedClassCount(), "虚拟机开始执行到目前已经卸载的类的总数"));

		// 用于操作系统的管理接口，Java 虚拟机在此操作系统上运行 OperatingSystemMXBean
		OperatingSystemMXBean op = ManagementFactory.getOperatingSystemMXBean();
		infos.add(new InfoBean("Arch", op.getArch(), "操作系统的架构"));
		infos.add(new InfoBean("OpName", op.getName(), "操作系统名称"));
		infos.add(new InfoBean("OpVersion", op.getVersion(), "操作系统的版本"));
		infos.add(new InfoBean("AvailableProcessors", op.getAvailableProcessors(), "虚拟机可以使用的处理器数目"));

		infos.add(new InfoBean("file.encoding", System.getProperty("file.encoding"), "文件编码"));
		infos.add(new InfoBean("file.encoding.pkg", System.getProperty("file.encoding.pkg"), "文件编码类包"));
		infos.add(new InfoBean("file.separator", System.getProperty("file.separator"), "文件路径分割符"));
		infos.add(new InfoBean("user.dir", System.getProperty("user.dir"), "当前用户路径"));

		// 内存池的管理接口。内存池表示由 Java 虚拟机管理的内存资源，
		// 由一个或多个内存管理器对内存池进行管理 MemoryPoolMXBean
		List<MemoryPoolMXBean> list = ManagementFactory.getMemoryPoolMXBeans();
		for (MemoryPoolMXBean mp : list) {
			infos.add(new InfoBean("PeakUsage", mp.getPeakUsage(), "虚拟机启动以来或自峰值重置以来此内存池的峰值内存使用量"));
			infos.add(new InfoBean("MpType", mp.getType(), "内存池的类型"));
			infos.add(new InfoBean("MpUsage", mp.getUsage(), "内存使用量超过其阈值的次数"));
		}

		return infos;
	}

	public static String getThrowableTrace(Throwable t) {
		StringWriter writer = new StringWriter();
		String message = "";
		try {
			t.printStackTrace(new PrintWriter(writer));
			message = writer.getBuffer().toString();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return message;
	}
}

@Data
@AllArgsConstructor
class InfoBean {
	private String key;
	private Object value;
	private String description;
}
