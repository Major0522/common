package com.seagen.ecc.common.id;

import java.net.NetworkInterface;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seagen.ecc.utils.DateUtils;

/**
 * snowflake java
 */
public class IdWorker {

    private final static Logger log = LoggerFactory.getLogger(IdWorker.class);

    private final long workerId;

    private final long epoch = 1158916014317L; // 时间起始标记点，作为基准

    private final long workerIdBits = 10L; // 机器标识位数

    private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;// 机器ID最大值: 1023

    private long sequence = 0L; // 0，并发控制

    private final long sequenceBits = 12L; // 毫秒内自增位

    private final long workerIdShift = this.sequenceBits; // 12

    private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;// 22

    private final long sequenceMask = -1L ^ -1L << this.sequenceBits; // 4095,111111111111,12位

    private long lastTimestamp = -1L;

    private IdWorker(long workerId) {
        log.info("workerId:" + workerId + ",epoch:" + epoch);
        if (workerId > this.maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) { // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环); 对新的timestamp，sequence从0开始
            this.sequence = this.sequence + 1 & this.sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);// 重新生成timestamp
            }
        } else {
            this.sequence = 0;
        }

        if (timestamp < this.lastTimestamp) {
            log.warn(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
            // throw new Exception(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp -
            // timestamp)));
        }

        this.lastTimestamp = timestamp;
        return timestamp - this.epoch << this.timestampLeftShift | this.workerId << this.workerIdShift | this.sequence;
    }

    private static IdWorker flowIdWorker = new IdWorker(getMacId());

    public static IdWorker getInstance() {
        return flowIdWorker;
    }

    /**
     * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    /**
     * 获得系统当前毫秒数
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    private static long getMacId() {
        try {
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface ni = e.nextElement();
                byte[] mac = ni.getHardwareAddress();
                if (mac == null || mac.length < 6) {
                    continue;
                }
                if (mac[0] == mac[1] && mac[1] == mac[2]) {
                    continue;
                }
                return ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
            }
        } catch (Exception e) {
            log.warn("get macId error", e);
        }
        log.warn("getMacId by random");
        return new Random().nextInt(1024);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(DateUtils.addMonth(new Date(), -10 * 12).getTime());
        System.out.println(DateUtils.addMonth(new Date(), -10 * 12).getTime());
        System.out.println("workId:" + getMacId());
        IdWorker idWorker = IdWorker.getInstance();
        // System.out.println(Long.toBinaryString(idWorker.nextId()));
        System.out.println(idWorker.nextId());
        System.out.println(idWorker.nextId());
    }
}
