package cn.usermanage.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class Encrypt2 {
	static final int WELNAVI_KEY_LEN = 16;

	public static char[] package_encrypt(String content, int len, char[] key) {

		char[] sec = new char[WELNAVI_KEY_LEN];
		char temp = ' ';
		char[] keyChar = key;
		for (int i = 0; i < sec.length; i++) {
			try {
				sec[i] = keyChar[i];
			} catch (RuntimeException e) {
				e.printStackTrace();
				System.out.println("检查Key长度");
			}
		}
		char[] contentChar = content.toCharArray();
		for (int i = 0; i < len; i++) {
			temp = contentChar[i];
			contentChar[i] = (char) ((sec[0] + sec[1] + sec[7] + sec[WELNAVI_KEY_LEN - 1]) ^ temp);
			for (int j = 0; j < WELNAVI_KEY_LEN - 1; j++) {
				sec[j] = sec[j + 1];
			}
			System.out.println(i + ":" + contentChar[i]);
			sec[WELNAVI_KEY_LEN - 1] = contentChar[i];
		}
		// return byte2hex(contentChar);
		return contentChar;
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String tmp = "";
		for (int i = 0; i < b.length; i++) {
			tmp = java.lang.Integer.toHexString(b[i] & 0xFF);
			if (tmp.length() == 1) {
				hs = hs + "0" + tmp;
			} else {
				hs = hs + tmp;
			}
		}
		tmp = null;
		return hs.toUpperCase();
	}
	
	public static String decrypt(char[] content,int len,char[] key){
		 char[] sec = new char[WELNAVI_KEY_LEN];
		    char tmp;
		    for (int i = 0; i < sec.length; i++) {
				try {
					sec[i] = key[i];
				} catch (RuntimeException e) {
					e.printStackTrace();
					System.out.println("检查Key长度");
				}
			}
		    for (int cnt=0;cnt<len;cnt++)
		    {
		        tmp = content[cnt];
		        content[cnt] = (char)((sec[0]+sec[1]+sec[7]+sec[WELNAVI_KEY_LEN-1])^tmp);
		        for(int j = 0;j<WELNAVI_KEY_LEN-1;j++)
		        {
		            sec[j]=sec[j+1];
		        }
		        sec[WELNAVI_KEY_LEN-1] = tmp;
		        System.out.println((char)content[cnt]);
		    }
		return content.toString();
	}
	
	public static void main(String[] args) {
		// System.out.println(toStringHex("5a95a29f34ac4cd5"));
		// System.out.println(package_encrypt("1234567890abcdef",
		// "caishujiecaishuj"));
		//		
		String hid = "hgfdsa0987654321";
		String key = "caishujiecaishuj";
		String sid = "74cf6c39220bd2f2";

		String TmpKey1 = hid;
		char[] TmpKey2 = package_encrypt(TmpKey1, 16, key.toCharArray());
		System.out.println("TmpKey2len:" + TmpKey2.length + "TmpKey2:"
				+ TmpKey2);
		String a = "";
		for (int i = 0; i < TmpKey2.length; i++) {
			a = a + (char) TmpKey2[i];
		}
		System.out.println(a);
		String SessionKey = sid;
		char[] SessionKey2 = package_encrypt(SessionKey, 16, TmpKey2);
		System.out.println("SessionKeylen:" + SessionKey2.length
				+ "SessionKey:" + SessionKey2);
		StringBuffer bf = new StringBuffer();
		InputStream in = null;

		/*
		 * String temp = ""; try { Scanner can = new Scanner(new
		 * FileInputStream("C:\\mingwen.xml"),"utf-8"); while (can.hasNext()) {
		 * temp+=can.next(); } } catch (FileNotFoundException e) {
		 * e.printStackTrace(); }
		 */

		String temp = "";
		File file = new File("C:\\mingwen2.xml");
		if (file.exists()) {
			FileInputStream fi;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] byteContent = new byte[0];
			int iBeRead = -1;
			byte buffer[] = new byte[1024];
			int len = 0;
			try {
				fi = new FileInputStream(file);
				while ((len = fi.read(buffer)) != -1) {
					bos.write(buffer, 0, len);
//					byteContent = arrayAppendArray(byteContent, buffer, 0, iBeRead);
				}
				temp = new String(buffer, "UTF-8");
				System.out.println(temp);
				fi.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		char[] mi = package_encrypt(temp, temp.toCharArray().length, SessionKey2);
		System.out
				.println("加密前：" + temp.getBytes().length + "加密后：" + mi.length);
		String s = decrypt(mi, mi.length, SessionKey2);
		System.out.println(s);
		try {
			FileOutputStream fos = new FileOutputStream("c:/miwen.xml");
//			Writer out = new OutputStreamWriter(fos,"utf-8");
//			System.out.println(mi.toString());
//			fos.write(mi);
//			fos.flush();
//			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public static byte[] arrayAppendArray(byte[] arrayDes, byte[] arraySrc,
//			   int offset, int length) {
//			  byte[] array = new byte[arraySrc.length + length];
//
//			  for (int i = 0; i < arrayDes.length; i++) {
//			   array[i] = arrayDes[i];
//			  }
//			  for (int i = arrayDes.length; i < arrayDes.length + length; i++) {
//			   array[i] = arraySrc[offset++];
//			  }
//			  arrayDes = null;
//			  arraySrc = null;
//			  return array;
//	 }


}
