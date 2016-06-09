

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

		//Gather file directory path from Input
		System.out.println("Enter your file directory path: ");
		Scanner scanner = new Scanner(System.in);
		directory = scanner.nextLine();
		
		//Gather Key for cipher from Input 
		System.out.println("Enter the key you would like to use: ");
		Scanner scanner2 = new Scanner(System.in);
		key = scanner2.nextLine();
		
		//Convert Both file contents and key into byte[]
		binaryKey = key.getBytes();
		rawBinary = readBinary(directory);	
		
		//Information of File content in bytes and in normal character set
		System.out.println("Before Manipulation");
		System.out.println(Arrays.toString(rawBinary));
		printByteArray(rawBinary);
		System.out.println();
		
		//Encryption Process Showing resulting file content 
		binaryShiftWithKey(rawBinary, binaryKey);
		System.out.println("Encrypted");
		System.out.println("File Content: ");
		writeToFile(directory, rawBinary);
		printByteArray(readBinary(directory));
		System.out.println(Arrays.toString(rawBinary));
		printByteArray(rawBinary);
		System.out.println();
		
		//Decryption Process Showing resulting file content  
		binaryShiftWithKeyDecrypt(rawBinary, binaryKey);
		System.out.println("Decrypted");
		System.out.println("File Content: ");
		writeToFile(directory, rawBinary);
		printByteArray(readBinary(directory));
		System.out.println(Arrays.toString(rawBinary));
		printByteArray(rawBinary);
		System.out.println();

	}
	//Takes a byte[] and writes the contents of the byte[] in UTF-8 character set within the specified directory 
	private static void writeToFile(String directory, byte[] byteContent ) throws IOException
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
	
	//Takes a byte[] converts it into UTF-8 String and prints the String
	private static void printByteArray(byte[] a) throws UnsupportedEncodingException
	{
		String str = new String(a, "UTF-8");
		System.out.println(str);
	}
	
	//Byte shifting cipher where byte value for each element within the array is added or subtracted
	//relative to the elements of the byte[] key chosen.  
	public static void binaryShiftWithKey(byte[] rawBinary ,byte[] binaryKey)
	{
		for(int i = 0; i <= rawBinary.length-1; i++)
		{
			rawBinary[i] = (byte)(rawBinary[i] + binaryKey[i%binaryKey.length]);
		}
	}
	
	//Byte shifting cipher where byte value for each element within the array is added or subtracted
	//relative to the elements of the byte[] key chosen.  
	public static void binaryShiftWithKeyDecrypt(byte[] rawBinary ,byte[] binaryKey)
	{
		for(int i = 0; i <= rawBinary.length-1; i++)
		{
			rawBinary[i] = (byte)(rawBinary[i] - binaryKey[i%binaryKey.length]);
		}
	}
	
	//Reads the content of the given Directory and returns a byte[] of each character in the file.  
	private static byte[] readBinary(String directory) throws IOException
	{
		
		Path path = Paths.get(directory);
	    return Files.readAllBytes(path);
		
	}

}

