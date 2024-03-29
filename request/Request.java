package request;

import java.io.IOException;
import java.io.InputStream;

import utils.Logger;

public class Request {


  private InputStream input;
  private String uri;

  public Request(InputStream input) {
    this.input = input;
    parse();
  }

  public void parse() {
    StringBuffer request = new StringBuffer(2048);
    int i;
    byte[] buffer = new byte[2048];
    try {
      i = input.read(buffer);
    } catch (IOException e) {
      Logger.error(e);
      i = -1;
    }
    for (int j = 0; j < i; j++) {
      request.append((char) buffer[j]);
    }
    // System.out.print(request.toString());
    uri = parseUri(request.toString());
  }

  /**
   * requestString形式如下：
   * GET /index.html HTTP/1.1
   * Host: localhost:8080
   * Connection: keep-alive
   * Cache-Control: max-age=0
   * ...
   * 该函数目的就是为了获取/index.html字符串
   */
  private String parseUri(String requestString) {
    int first, second;
    first = requestString.indexOf(' ');
    if (first != -1) {
      second = requestString.indexOf(' ', first + 1);
      if (second > first)
        return requestString.substring(first + 1, second);
    }
    return "";
  }

  public String getUri() {
    return uri;
  }
}
