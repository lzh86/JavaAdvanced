package com.kaka.oom;

import com.google.common.base.Charsets;
import org.caffinitas.ohc.CacheSerializer;
import org.caffinitas.ohc.OHCache;
import org.caffinitas.ohc.OHCacheBuilder;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class OhcDemo {
    public static void main(String[] args) throws InterruptedException {
        //准备时间，方便观察
        TimeUnit.SECONDS.sleep(10);
        OHCache ohCache = OHCacheBuilder.<String, String>newBuilder()
                .keySerializer(stringSerializer)
                .valueSerializer(stringSerializer)
                .capacity(1024L * 1204L * 1024L * 6)
                .build();
        int num = 0;
        while (true) {
            String big = new String(new byte[1024 * 1024]);
            ohCache.put(num + "", big);
            num++;
        }
    }

    public static final CacheSerializer<String> stringSerializer = new CacheSerializer<String>() {

        @Override
        public void serialize(String s, ByteBuffer buf) {
            // 得到字符串对象UTF-8编码的字节数组
            byte[] bytes = s.getBytes(Charsets.UTF_8);
            // 用前16位记录数组长度
            buf.put((byte) ((bytes.length >>> 8) & 0xFF));
            buf.put((byte) ((bytes.length) & 0xFF));
            buf.put(bytes);
        }

        public String deserialize(ByteBuffer buf) {
            // 获取字节数组的长度
            int length = (((buf.get() & 0xff) << 8) + ((buf.get() & 0xff)));
            byte[] bytes = new byte[length];
            // 读取字节数组
            buf.get(bytes);
            // 返回字符串对象
            return new String(bytes, Charsets.UTF_8);
        }

        public int serializedSize(String s) {
            byte[] bytes = s.getBytes(Charsets.UTF_8);
            // 设置字符串长度限制，2^16 = 65536
//            if (bytes.length > 65535)
//                throw new RuntimeException("encoded string too long: " + bytes.length + " bytes");

            return bytes.length + 2;
        }
    };
}
