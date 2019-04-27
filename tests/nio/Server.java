package tests.nio;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

import response.Response;

public class Server {

    static int PORT = 8000;
    static int BUFFER_SIZE = 1024;


    static String CHARSET = "utf-8";
    CharsetDecoder decoder;

    private final int port;
    private ServerSocketChannel channel;
    private Selector selector;
    private final ByteBuffer buffer;


    public Server(int port) throws IOException {
        this.port = port;
        this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
        this.decoder = Charset.forName(CHARSET).newDecoder();

        this.selector = Selector.open();
    }


    public void listen() throws IOException {
        this.channel = ServerSocketChannel.open();

        ServerSocket socket = channel.socket();
        socket.bind(new InetSocketAddress(port));

        channel.configureBlocking(false);
        while (true) {
            SocketChannel client = channel.accept();
            if (client != null) {
                registerClient(client);
            }

            service();
        }
    }


    public void registerClient(SocketChannel client) throws IOException {

        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }


    public void service() throws IOException {
        if (selector.selectNow() > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                SocketChannel client = (SocketChannel) key.channel();

                if (key.isReadable()) {
                    read(key);
                } else if (key.isWritable()) {
                    write(key);
                }
            }
        }
    }


    public void read(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        buffer.clear();

        int c = client.read(buffer);
        if (c > 0) {
            buffer.flip();
            CharBuffer charBuffer = decoder.decode(buffer);

            key.attach("ack sync");
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        } else {
            client.close();
        }
        buffer.clear();

    }


    public void write(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        String handle = (String) key.attachment();

        String res = Response.getMsg();
        if (handle != null) {
            res += "\r\n" + handle;
        }
        ByteBuffer block = ByteBuffer.wrap(res.getBytes());
        client.write(block);
        client.close();
    }


    static class Response {
        public static String getMsg() {
            return "HTTP/1.1 200 OK" + "\r\n";
        }
    }
}























