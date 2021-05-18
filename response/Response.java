package response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import configrations.RequestConf;
import handlers.Controller;
import request.Request;
import utils.Logger;
import utils.Utils;

import static configrations.SysConf.WEB_ROOT;

public class Response {

  private static final int BUFFER_SIZE = 1024;
  Request request;
  OutputStream output;

  public Response(OutputStream output) {
    this.output = output;
  }

  public void setRequest(Request request) {
    this.request = request;
  }

  public void sendDataByController() {
    String uri = request.getUri();

    if (Utils.isStaticRequest(uri)) {
      handleStaticRequest(uri);
    } else {
      Controller handler = RequestConf.inst().fetchControllerByUrl(uri);
      handler.process(request, this);
    }
  }

  public String fetchHeader(String content) {
    return "HTTP/1.1 200 OK\n" +
            "Content-Type: text/plain; charset=utf-8\n" +
            "Content-Length: " + content.length() + "\n" +
            "Date: Thu, 18 Apr 2019 08:27:58 GMT\n" +
            "Connection: keep-alive\n" +
            "\n" + content;
  }

  public void handleStaticRequest(String uri) {
    byte[] bytes = new byte[BUFFER_SIZE];
    FileInputStream fis = null;

    try {
      File file = new File(WEB_ROOT, uri);
      if (file.exists()) {
        fis = new FileInputStream(file);
        int ch = fis.read(bytes, 0, BUFFER_SIZE);
        while (ch != -1) {
          output.write(bytes, 0, ch);
          ch = fis.read(bytes, 0, BUFFER_SIZE);
        }
      } else {
        String errorMessage = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type: text/html\r\nContent-Length: 23\r\n" + "\r\n" + "<h1>File Not Found</h1>";
        output.write(errorMessage.getBytes());
      }
    } catch (Exception except) {
      Logger.error(except);
    } finally {
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public void write(String data) {
    try {
      output.write(fetchHeader(data).getBytes());
    } catch (IOException e) {
      Logger.error(e);
    }
  }
}
