package com.tdy.loadbalancing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 轮询法 
* [简要描述]:<br/>
* [详细描述]:<br/>
* @author  tangdingyi  E-mail: 13913364179@163.com
* @date 创建时间：2016年9月29日 下午2:45:01 
* @version 1.0 *
* @since
 */
public class RoundRobin {
	
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
			String server = testRoundRobin();
			System.out.println(server);
		}
		
	}
	
    static Integer pos = 0;
	
	public static String testRoundRobin(){
		Map<String,Integer> serverMap = new HashMap<String,Integer>();
        serverMap.putAll(serverWeightMap);
        
        Set<String>  keySet = serverMap.keySet();
        List<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);
        String server = null;
        
        synchronized (pos) {
			if(pos >= keySet.size()){
				pos = 0;
			}
			server = keyList.get(pos);
			pos ++;
		}
        return server;
	}
	
	
}
