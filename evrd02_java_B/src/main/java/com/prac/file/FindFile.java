/**
 * @ �� �� �� : FindFile.java
 * @ ���ϼ��� : Ư����ġ�� ��� ���������� ���ϵ��� txt���Ϸ� ���
 * @ �� �� �� : �����
 * @ �� �� �� : 2008.03.13
 */
package com.jaewon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindFile {

	/**
	 * @param args
	 * @throws  
	 */
	public static void main(String[] args)  {
		
		String folder = "D:\\�̰�����\\Favorites";
		try		{
		
			if ( args[0] != "" || args[0] != null )		{ // args[0]이 null이 아니고 공백값이 아닐 때, folder변수에 값을 대입
				folder = args[0];
			}
	
		}catch ( ArrayIndexOutOfBoundsException e )	{ // 배열 길이 초과 예외처리

		}catch ( Exception e ){ // 전체 예외 처리

		}
		String fileName = "C:\\result.xls";
		try		{
			if ( args[1] != "" || args[1] != null )		{  // args[0]이 null이 아니고 공백값이 아닐 때, fileName변수에 값을 대입
				fileName = args[1];
			}	
		}
		catch ( ArrayIndexOutOfBoundsException e )		{ // 배열 길이 초과 예외처리

		}
		catch ( Exception e )		{ // 전체 예외 처리

		}

        try {
			
			FileOutputStream fileoutputstream;
			fileoutputstream = new FileOutputStream(new File(fileName)); // 파일출력스트림 객체생성

			int cnt = 0;
			
			String temp = "";			
			temp += "\t" ;   // temp변수에 \t 추가
			temp += folder ; // temp변수에 위의 folder변수에 받았던 것들을 삽입
			temp += "\n" ;   // temp변수에 \n 추가

			byte [] barray = temp.getBytes();	 // String타입 temp변수의 값을 byte[]으로 캐스팅
			for(int i=0; i<barray.length; i++) { // barray길이만큼 loop 반복
				try {
					fileoutputstream.write(barray[i]); // barray[i]의 각 내용을 fileoutputstream 변수에 삽입
				} catch (IOException e) {
					e.printStackTrace(); // IO예외처리 시, 예외발생 원인 로그 출력
				}
			}

			find ( fileoutputstream , folder , cnt ); // 각 파라미터를 아래의 find메소드로 전달

			fileoutputstream.close(); // 파일쓰기 객체 리소스 클로즈

		} catch (IOException e) {     // IOException의 예외처리
			e.printStackTrace();  	  // IOException의 에러 원인 추적
		}
        
	}
	
	public static void find ( FileOutputStream fileoutputstream , String folder , int cnt ){ // 메인메소드에서 파라미터 전달받음
		
		File file = new File(folder);   // File 인스턴스 생성 및 파일 경로를 folder에 있는 경로로 지정
		
		String [] list = file.list();   // File객체가 가진 디렉토리에 포함된 모든 파일 및 디렉토리명을 문자열 배열로 반환
		
		List fileList = new ArrayList();	// ArrayList 인스턴스 생성
		List folderList = new ArrayList();	// ArrayList 인스턴스 생성
		
		for ( int i = 0 ; i < list.length ; i++ ){ // 배열 길이만큼 loop 반복
			
			String name = list[i];  // 배열의 각 인덱스 값을 name 변수에 대입
			
			File file2 = new File(folder + "\\" + name); //
			
			if ( file2.isFile() ) 	{
				fileList.add( name );
			}
			else	{
				folderList.add( name );
			}

		}

		for ( int j = 0 ; j < folderList.size() ; j++ ){
			String temp = "";
			for ( int k = 0 ; k <= cnt ; k++ )		{
				temp += "\t" ;
			}
			temp += "\t" ;
			temp += folderList.get(j) ;
			temp += "\n" ;

			byte [] barray = temp.getBytes();			
			for(int i=0; i<barray.length; i++) {
				try {
					fileoutputstream.write(barray[i]);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			find ( fileoutputstream ,  folder + "\\" +  folderList.get(j) , (cnt+1) );
		}
		
		for ( int j = 0 ; j < fileList.size() ; j++ ){
			String temp = "";
			for ( int k = 0 ; k <= cnt ; k++ )		{
				temp += "\t" ;
			}
			temp += "\t" ;
			temp += fileList.get(j) ;
			temp += "\n" ;

			byte [] barray = temp.getBytes();			
			for(int i=0; i<barray.length; i++) {
				try {
					fileoutputstream.write(barray[i]);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}	
	}
	
}