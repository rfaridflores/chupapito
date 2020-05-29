package com.farid.ws.soap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

public class FileWsImpl implements FileWs {

	@Override
	public void upload(DataHandler attacment) {
		InputStream inputStream=null;
		OutputStream outputStream=null;
		// accessing to incoming file from request
		try {
			 inputStream = attacment.getInputStream();// \\
			 outputStream = new FileOutputStream("/Users/fflores/Documents/Courses/Services/files/test.jpg");
			byte[] b = new byte[100000];
			int bytesRead = 0;
			while ((bytesRead = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, bytesRead);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public DataHandler download() {

		return new DataHandler(new FileDataSource(new File("/Usersfflores/Documents/Courses/Services/files/test.jpg")));
	}

}
