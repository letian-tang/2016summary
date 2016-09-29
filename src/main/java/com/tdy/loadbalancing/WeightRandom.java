package com.tdy.loadbalancing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 加权随机
* [简要描述]:<br/>
* [详细描述]:<br/>
* @author  tangdingyi  E-mail: 13913364179@163.com
* @date 创建时间：2016年9月29日 下午2:45:01 
* @version 1.0 *
* @since
 */
public class WeightRandom {
	
	static Map<String,Integer> serverWeightMap = new HashMap<String,Integer>();
	static {
		serverWeightMap.put("192.168.1.100", 1);
		serverWeightMap.put("192.168.1.101", 1);
		serverWeightMap.put("192.168.1.102", 4);
		serverWeightMap.put("192.168.1.103", 1);
		serverWeightMap.put("192.168.1.104", 1);
		
		serverWeightMap.put("192.168.1.105", 3);
		serverWeightMap.put("192.168.1.106", 1);
		
		serverWeightMap.put("192.168.1.107", 2);
		serverWeightMap.put("192.168.1.108", 1);
		serverWeightMap.put("192.168.1.109", 1);
		serverWeightMap.put("192.168.1.110", 1);
	}

	public static void main(String[] args) {
		for(int i=0;i<20;i++){
			String server = testWeightRandom();
			System.out.println(server);
		}
		
	}
	
	
	static Integer pos = 0;
	
	
	/**
	 * 与轮询算法类似，只是在获取服务器地址之前增加了一段权重计算
	 * 根据权重的大小，将地址重复的增加到服务器地址列表中。权重越大
	 * 该服务器每轮所获得的请求数量越多。
	 * [简要描述]:<br/>
	 * [详细描述]:<br/>
	 *
	 * @return
	 * @exception
	 */
	public static String testWeightRandom(){
		Map<String,Integer> serverMap = new HashMap<String,Integer>();
        serverMap.putAll(serverWeightMap);
        
        Set<String>  keySet = serverMap.keySet();
        Iterator<String> it = keySet.iterator();
        
        List<String> serverList = new ArrayList<String>();

        while(it.hasNext()){
        	String server = it.next();
        	Integer weight = serverMap.get(server);
        	for(int i=0;i<weight;i++){
        		serverList.add(server);
        	}
        }
        
        Random random = new Random();
        int randomPos = random.nextInt(serverList.size());
        String server = serverList.get(randomPos);
        return server;
	}
	
	
}
