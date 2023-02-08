package com.sh.game.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 服务器配置
 *
 * @author Administrator
 */
@Slf4j
public final class IDUtil {

	/**
	 * 2019年4月1日0点整
	 */
	public static final long TIME_MILLS_2019_4_1_0_0_0_0 = 1483200000000L;
	public static final ConcurrentHashMap<Integer, Creator> CREATOR_MAP = new ConcurrentHashMap<>();
	private static final AtomicInteger BUFFER_ID = new AtomicInteger(1);

	private static final AtomicInteger DUPLICATE_LINE = new AtomicInteger(100000);
	private static Map<String, String> AREA_CODE = null;
	private static int sid = 0;

	private static int pid = 0;

	public static void init(int sid, int pid) {
		init(sid, pid, 0);
	}

	public static void init(int sid, int pid, int duplicateId) {
		if (sid > 16383 || pid > 511) {
			throw new RuntimeException("sid最大支持16383，平台id最大支持511，请合理配置.");
		}
		IDUtil.sid = sid;
		IDUtil.pid = pid;
		//初始化一个用于默认生成器，用于不需要存储的id生成
		CREATOR_MAP.put(0, new Creator(0, 0, 0));
		if (duplicateId > 0) {
			DUPLICATE_LINE.set(duplicateId);
		}
	}

	/**
	 * 获取id
	 */
	public static long getId(int type) {
		Creator creator = CREATOR_MAP.get(type);
		if (creator == null) {
			creator = new Creator(type, sid, pid);
			Creator existCreator = CREATOR_MAP.putIfAbsent(type, creator);
			if (existCreator != null) {
				creator = existCreator;
			}
		}
		return creator.get();
	}

	/**
	 * 获取基于2017年1月1日0点整的时间秒数
	 *
	 * @return long 秒数
	 */
	static long getTimeStampFrom20190401() {
		return (System.currentTimeMillis() - TIME_MILLS_2019_4_1_0_0_0_0) / 1000L;
	}

	public static int getBuffId() {
		return BUFFER_ID.incrementAndGet();
	}

	public static int getDuplicateLine() {
		return DUPLICATE_LINE.incrementAndGet();
	}

	public static void main(String[] args) {
       /* System.out.println(IDUtil.getId());
        System.out.println(TimeUtil.getNowOfSeconds());*/
		int pidMax = 1000;
		int sidMax = 50000;
		//long  count = Long.MAX_VALUE / (pidMax);
		//1 = 0;
		//int start  = Long.MAX_VALUE / pidMax * (Pid - 1) + count / sidMax * (sid - 1);
        /*AtomicLong inc = new AtomicLong(start);
        int max = queryCurMaxid();
        inc.set(max);*/
		System.out.println(Long.MAX_VALUE / (pidMax * sidMax));
	}

	static class Creator {

		private int type;

		private int sid;

		private int pid;

		private int id = 0;
		/**
		 * 基于2017年1月1日0点整的时间秒数
		 */
		private long lastSecond = getTimeStampFrom20190401();

		Creator(int type, int sid, int pid) {
			this.sid = sid;
			this.pid = pid;
			this.type = type;
		}

		//9(平台-511） + 14(服务器id-16383) + 29（时间戳） + 11（自增-2047）
		public long get() {
			synchronized (this) {
				long second = getTimeStampFrom20190401();
				// ID增1
				id += 1;
				long max = 2047;
				if (id > max) {
					// 如果ID大于2047 这里其实是 2的11次方 = 2047 因为自增ID只能占11位，所以不能超过2047
					// ID大于2047后id复位，如果时间不增1，那么将会产生重复
					id = 0;
					// 每过2047当前秒数就增1
					lastSecond += 1L;
					log.info("每秒生成id超过上限,type->{}, stack={}", type, Utils.getStackTrace());
				}
				if (second > lastSecond) {
					// 当前时间大于自增时间，那么就更新自增时间为当前时间
					lastSecond = second;
					//log.info("更新自增时间：{}",lastSecond);
					//id重新从0开始
					id = 0;
				} else if (second < lastSecond) {
					// ID获取速度过快，1秒内获取了额超过2047个id的时候，时间需要使用自增时间
					second = lastSecond;
					//log.info("使用自增时间：{}",second);
				}
				return (pid & 0B1111_1111_1L) << 54 |
						(sid & 0B1111_1111_1111_11L) << 40 |
						(second & 0B1111_1111_1111_1111_1111_1111_1111_1L) << 11 |
						(id & 0B1111_1111_111L);
			}
		}
	}
}