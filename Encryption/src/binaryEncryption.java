

import java.io.BufferedWriter;
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
		System.out.println("Before Manipulation");
		System.out.println(Arrays.toString(rawBinary));
		printByteArray(rawBinary);
		System.out.println();
		
		binaryShiftWithKey(rawBinary, binaryKey);
		System.out.println("Encrypted");
			System.out.println("File Content: ");
			writeToFile(directory, rawBinary);
			printByteArray(readBinary(directory));
		System.out.println(Arrays.toString(rawBinary));
		printByteArray(rawBinary);
		System.out.println();
		
		binaryShiftWithKeyDecrypt(rawBinary, binaryKey);
		System.out.println("Decrypted");
		System.out.println("File Content: ");
		writeToFile(directory, rawBinary);
		printByteArray(readBinary(directory));
		System.out.println(Arrays.toString(rawBinary));
		printByteArray(rawBinary);
		System.out.println();

	}
	
	public static void writeToFile(String directory, byte[] byteContent ) throws IOException
	{
		File file = new File(directory);
		
		if (!file.exists()) 
		{
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(new String(byteContent, "UTF-8"));
		bw.close();

		
	}
	
	public static void printByteArray(byte[] a) throws UnsupportedEncodingException
	{
		String str = new String(a, "UTF-8");
		System.out.println(str);
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

