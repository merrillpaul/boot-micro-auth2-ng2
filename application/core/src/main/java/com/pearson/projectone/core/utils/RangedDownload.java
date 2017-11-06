package com.pearson.projectone.core.utils;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * From QI Central's ranged download
 */
public class RangedDownload {
	@SuppressWarnings("EmptyCatchBlock")
	public static void sendFile(File file, String contentType, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType(contentType);
		response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
		response.setHeader("Accept-Ranges", "bytes");


		OutputStream outputStream = response.getOutputStream();

		//Requested range.  In the format of "bytes={from}-{to}
		String range = request.getHeader("range");

		// Close the stream when interrupted otherwise we leak stream connection
		try(InputStream inputStream = new FileInputStream(file)) {
			if (ObjectUtils.isEmpty(range) || range.equals("bytes=0-")) { //Full content response
				response.setStatus(200); //ok
				response.setHeader("Content-length", Long.toString(file.length()));
				StreamUtils.copy(inputStream, outputStream);
			} else { // Partial content request
				response.setStatus(206); //partial response

				//grab the request ranges (from, to, length) from the header
				String[] rangeText = request.getHeader("range").split("=")[1].split("-");
				Long from = Long.getLong(rangeText[0]);

				// The range may be of the form X-Y or X-. In the latter case we'll only have one element.
				Long to = rangeText.length == 1 ? file.length() - 1 : Long.getLong(rangeText[1]);
				//range is inclusive i.e. a 0-1 range is 2 bytes (1-0+1=2)
				Long len = to - from + 1;

				if (from >= to || to >= file.length()) {
					response.setStatus(416);
					outputStream.write("Requested Range Not Satisfiable".getBytes());
					return;
				}

				response.setHeader("Content-Range", String.format("bytes %d-%d/%d", from, to, file.length()));
				response.setHeader("Content-length", len.toString());

				//buffer is used so we don't parse everything to an array then sync it.  This is important because
				// 1. it is less stressful to the server
				// 2. Partial requests stop/start so often that this computation often goes to waste
				// 3. The client would have to wait for the content to start streaming
				byte[] buf = new byte[4096];

				//skip to the point we want to start from
				inputStream.skip(from);

				while (len > 0) {
					int read = inputStream.read(buf, 0, len >= buf.length ? buf.length : len.intValue());
					if (read == -1) {
						//just in case something goes wrong with the read.  Otherwise would get stuck in the loop
						break;
					}

					outputStream.write(buf, 0, read);
					len -= read;
				}
			}

		} catch (org.apache.catalina.connector.ClientAbortException ex) {
			// The client disconnected before we finished sending data. This is routine, especially
			// for streaming video, and shouldn't be treated as an error.
		}
	}
}
