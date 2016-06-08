

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class binaryEncryption 
{
	public static void main(String[] args) throws IOException
	{
		String directory;
		byte[] rawBinary;
		String key;
		byte[] binaryKey;

		System.out.println("Enter your file directory path: ");
		Scanner scanner = new Scanner(System.in);
		directory = scanner.nextLine();
		
		System.out.println("Enter the key you would like to use: ");
		Scanner scanner2 = new Scanner(System.in);
		key = scanner2.nextLine();
		binaryKey = key.getBytes();
		
		rawBinary = readBinary(directory);	
		System.out.println(Arrays.toString(rawBinary));
		printByteArray(rawBinary);
		
		binaryShiftWithKey(rawBinary, binaryKey);
		System.out.println(Arrays.toString(rawBinary));
		printByteArray(rawBinary);
		
		binaryShiftWithKeyDecrypt(rawBinary, binaryKey);
		System.out.println(Arrays.toString(rawBinary));
		printByteArray(rawBinary);

	}
	
	public static void printByteArray(byte[] a) throws UnsupportedEncodingException
	{
		String str = new String(a, "UTF-8");
		System.out.println(a);
	}
	
	public static void binaryShiftWithKey(byte[] rawBinary ,byte[] binaryKey)
	{
		for(int i = 0; i <= rawBinary.length-1; i++)
		{
			rawBinary[i] = (byte)(rawBinary[i] + binaryKey[i%binaryKey.length]);
		}
	}
	
	public static void binaryShiftWithKeyDecrypt(byte[] rawBinary ,byte[] binaryKey)
	{
		for(int i = 0; i <= rawBinary.length-1; i++)
		{
			rawBinary[i] = (byte)(rawBinary[i] - binaryKey[i%binaryKey.length]);
		}
	}
	
	private static byte[] readBinary(String directory) throws IOException
	{
		
		Path path = Paths.get(directory);
	    return Files.readAllBytes(path);
		
	}
}

