package cn.usermanage.util;

public class Decrypt {
	
	static final int WELNAVI_KEY_LEN = 16;
	
	public static String decrypt(byte[] content,int len,byte[] key){
		 byte[] sec = new byte[WELNAVI_KEY_LEN];
		    byte tmp;
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
		        content[cnt] = (byte)((sec[0]+sec[1]+sec[7]+sec[WELNAVI_KEY_LEN-1])^tmp);
		        for(int j = 0;j<WELNAVI_KEY_LEN-1;j++)
		        {
		            sec[j]=sec[j+1];
		        }
		        sec[WELNAVI_KEY_LEN-1] = tmp;
		    }
		return content.toString();
	}

}
