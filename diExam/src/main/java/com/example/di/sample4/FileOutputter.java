package com.example.di.sample4;

import java.io.FileWriter;
import java.io.IOException;

public class FileOutputter implements Outputter {
	private String filePath;

	// filePath �� ���͸� ���� �ʱ�ȭ
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void output(String message) throws IOException {
		FileWriter writer = new FileWriter(filePath);
		writer.write(message);
		writer.close();
	}

}
