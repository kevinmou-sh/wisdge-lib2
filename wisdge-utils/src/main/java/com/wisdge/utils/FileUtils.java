package com.wisdge.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import java.io.*;
import java.nio.charset.Charset;

/**
 * 文件处理类
 *
 * @version 2.0.0
 * @author KevinMOU
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

	/**
	 * 获得一个自增长序号的文件。<br/>
	 * 该方法通常用于对于已经存在的文件不采用覆盖的方式，而是自动增加序号在文件命中。
	 *
	 * @param filepath
	 *            文件路径
	 * @return File
	 */
	public static File getNoDupFile(String filepath) {
		String path, basename, extension, filename;
		path = FilenameUtils.getFullPath(filepath);
		basename = FilenameUtils.getBaseName(filepath);
		extension = FilenameUtils.getExtension(filepath);
		int i = 0;
		File file = null;
		do {
			if (i > 0) {
				filename = basename + "(" + i + ")" + (StringUtils.isEmpty(extension) ? "" : ("." + extension));
			} else {
				filename = basename + (StringUtils.isEmpty(extension) ? "" : ("." + extension));
			}
			file = new File(path + filename);
			i++;
		} while (file.exists());
		return file;
	}

	public static String read(InputStream is) throws IOException {
		byte[] buffer = new byte[is.available()];
		IOUtils.read(is, buffer);
		return new String(buffer);
	}

	public static FileInputStream openInputStream(String filepath) throws Exception {
		File file = new File(filepath);
		if (file.exists() && !file.isDirectory() && file.canRead()) {
			return openInputStream(file);
		}
		throw new FileNotFoundException();
	}

	public static Charset detect(File file) throws IOException {
		return AutoCharsetReader.quickDetect(file.toURI().toURL().openStream());
	}

	public static Charset detect(InputStream inputStream) throws IOException {
		return AutoCharsetReader.quickDetect(inputStream);
	}

	public static Charset detect(InputStream inputStream, int length) throws IOException {
		return AutoCharsetReader.quickDetect(inputStream, length);
	}
}
