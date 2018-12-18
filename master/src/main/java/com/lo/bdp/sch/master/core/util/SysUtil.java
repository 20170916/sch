package com.lo.bdp.sch.master.core.util;


import com.lo.bdp.sch.master.core.exception.SchException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class SysUtil {
	private SysUtil(){}
	static final Properties props = System.getProperties();
	
	public static enum SysType {
		WINDOWS,LINUX,MAC
	}
	
	public static SysType getSystemType(){
		String os = props.getProperty("os.name");
		if(os.toLowerCase().startsWith("win")){
			return SysType.WINDOWS;
		}
		else if(os.toLowerCase().startsWith("linux")){
			return SysType.LINUX;
		}
		else{
			return SysType.MAC;
		}
	}
	
	public static String getSystemCharset(){
		return props.getProperty("sun.jnu.encoding");
	}
	
	public static String getHostName(){
		return getInetAddress().getHostName();
	}
	
	public static InetAddress getInetAddress(){
		InetAddress inetAddress = null;
		try {
			inetAddress = InetAddress.getLocalHost();
			if(inetAddress==null){
				throw new SchException("can't get the hostname");
			}
			return inetAddress;
		} catch (UnknownHostException e) {
			throw new SchException("can't get the hostname");
		}
	}
	
	public static String getSysUser(){
		return props.getProperty("user.name");
	}
	
	public static String getIP(){
		return getInetAddress().getHostAddress();
	}
}
