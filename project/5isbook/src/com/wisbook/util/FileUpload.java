package com.wisbook.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 处理用户上传的文件
 * 将临时文件转换成EXCEL格式
 * @author Ban
 */
public class FileUpload {

	public static File dealWithUploadFile(File sourseFile) throws IOException {
		// 上传的文件对象
		File tempFile = sourseFile;
		// 返回的文件对象
		File file = new File((int) (Math.random() * 100000) + ".xls");
		BufferedInputStream bufferedInput = null;
		BufferedOutputStream bufferedOutput = null;
		FileInputStream fileInput = null;
		FileOutputStream fileOutput = null;
		try {
			fileInput = new FileInputStream(tempFile);
			bufferedInput = new BufferedInputStream(fileInput);

			fileOutput = new FileOutputStream(file);
			bufferedOutput = new BufferedOutputStream(fileOutput);
			byte[] buffer = new byte[1024];
			int length = -1;
			System.out.println("read");
			while ((length = bufferedInput.read(buffer)) != -1) {
				bufferedOutput.write(buffer, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedInput != null)
					bufferedInput.close();
				if (bufferedOutput != null)
					bufferedOutput.close();
			} catch (Exception e) {
			}
		}
		return file;
	}
}
