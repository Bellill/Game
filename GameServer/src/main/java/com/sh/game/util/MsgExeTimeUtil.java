package com.sh.game.util;

import com.sh.commons.tuple.ThreeTuple;
import com.sh.commons.tuple.TwoTuple;
import com.sh.game.world.MessageExeTimeStatisticsHandler;
import com.sh.world.HandlerFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 田小军 on 2016/12/24 0024.
 */
public class MsgExeTimeUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(MsgExeTimeUtil.class);

	public class ThreadExeTime {
		public long allExeTime;
		/**
		 * 单次消息执行最大时间,开始执行的时间
		 */
		public ConcurrentHashMap<Integer, TwoTuple<Long, Long>> timeMapMax = new ConcurrentHashMap<>();
		/**
		 * 某个消息累积执行时间
		 */
		public ConcurrentHashMap<Integer, Long> timeMapAll = new ConcurrentHashMap<>();
	}

	private static final MsgExeTimeUtil INSTANCE = new MsgExeTimeUtil();

	private static final HandlerFilter filer = new MessageExeTimeStatisticsHandler();

	private static ConcurrentHashMap<Long, ThreadExeTime> timeMap = new ConcurrentHashMap<>();

	private static boolean open = false;

	public static HandlerFilter getFiler() {
		return filer;
	}

	public static ConcurrentHashMap<Long, ThreadExeTime> getTimeMap() {
		return timeMap;
	}

	public static boolean isOpen() {
		return open;
	}

	public static void setOpen(boolean open) {
		MsgExeTimeUtil.open = open;
	}

	public static MsgExeTimeUtil getInstance() {
		return INSTANCE;
	}

	public ThreadExeTime newThreadExeTime() {
		return new ThreadExeTime();
	}

	public static void clear() {
		timeMap.clear();
	}

	public static Long getAllExeTime(Long mapKey) {
		ThreadExeTime threadExeTime = timeMap.get(mapKey);
		if (threadExeTime == null) {
			return 0L;
		}
		return threadExeTime.allExeTime;
	}

	/**
	 * 获取所有消息的次数，按照发送量排序
	 *
	 * @param mapKey:mapId + lineId
	 * @param sortType:    1: 消息最大执行时间 2:消息总的执行时间
	 * @param num          获取排序前多少的消息
	 * @return 消息id, 执行时长，开始时间
	 */
	public static List<ThreeTuple<Integer, Long, Long>> getTimeTop(Long mapKey, int sortType, int num) {
		if (sortType != 1 && sortType != 2) {
			return null;
		}

		ThreadExeTime threadExeTime = timeMap.get(mapKey);
		if (threadExeTime == null) {
			return null;
		}

		List<ThreeTuple<Integer, Long, Long>> ret = new ArrayList<>();
		for (Integer id : threadExeTime.timeMapMax.keySet()) {
			if (sortType == 1) {
				TwoTuple<Long, Long> mapMax = threadExeTime.timeMapMax.get(id);
				ret.add(new ThreeTuple<>(id, mapMax.getFirst(), mapMax.getSecond()));
			} else if (sortType == 2) {
				ret.add(new ThreeTuple<>(id, threadExeTime.timeMapAll.get(id), 0L));
			}
		}

		Collections.sort(ret, new Comparator<TwoTuple<Integer, Long>>() {
			@Override
			public int compare(TwoTuple<Integer, Long> o1, TwoTuple<Integer, Long> o2) {
				return Long.compare(o2.second, o1.second);
			}
		});
		if (num >= ret.size()) {
			return ret;
		} else {
			return ret.subList(0, num);
		}
	}

}
