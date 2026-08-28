package resources;

import org.apache.commons.codec.binary.Base64;

public class EncodingString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="@17";
		byte[] encodedString=Base64.encodeBase64(str.getBytes());
		System.out.println("encodedString:"+new String(encodedString));
		
		byte[] decodeString=Base64.decodeBase64(encodedString);
		System.out.println("decodeString:"+new String(decodeString));

	}

}
