package com.wisbook.service.goods.detail.providers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 接受文件的输入
 * @author volador
 *
 */
public interface FileAware {
	void setFile(File file)  throws FileNotFoundException;
	void setFile(FileInputStream file);
}
