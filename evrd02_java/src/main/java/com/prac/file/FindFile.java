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
// 지정된 디렉토리 내의 모든 파일과 하위 디렉토리를 탐색하고, 이 정보를 파일에 기록하는 기능을 수행
// main() : 유저에게 받은 인자를 경로들로 사용하여 FileOutputStream 객체를 생성하고, find() 메소드를 호출하여 디렉토리 탐색
// find() : File 객체로 디렉토리 내의 파일과 폴더 목록을 가져오고, 이를 리스트에 저장. 파일은 fileList에, 폴더는 folderList에 각각 추가
//			재귀 호출로 하위폴더 탐색하며, 결과를 파일로 기록

	/**
	 * @param args
	 * @throws  
	 */
	// 유저로부터 입력받은 폴더 경로와 결과를 저장할 파일의 경로를 처리
	// folder 변수는 탐색할 디렉토리 경로를, fileName 변수는 결과를 저장할 파일의 경로를 저장
	public static void main(String[] args)  {
		
		String folder = "D:\\assginment\\Favorites";
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
			temp += "\t" ;   // 탭 삽입
			temp += folder ; // temp변수에 위의 folder변수에 받았던 것들을 삽입
			temp += "\n" ;   // 줄바꿈

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

	// 지정된 디렉토리 내의 모든 파일과 폴더를 재귀적으로 탐색
	public static void find ( FileOutputStream fileoutputstream , String folder , int cnt ){ // 메인메소드에서 파라미터 전달받음
		
		File file = new File(folder);   // File 인스턴스 생성 및 파일 경로를 folder에 있는 경로로 지정
		
		String [] list = file.list();   // File객체가 가진 디렉토리에 포함된 모든 파일 및 디렉토리명을 문자열 배열로 반환
		
		List fileList = new ArrayList();	// ArrayList 인스턴스 생성
		List folderList = new ArrayList();	// ArrayList 인스턴스 생성
		
		for ( int i = 0 ; i < list.length ; i++ ){ // 배열 길이만큼 loop 반복
			
			String name = list[i];  // 배열의 각 인덱스 값을 name 변수에 대입
			
			File file2 = new File(folder + "\\" + name); // 폴더 경로에 파일명 추가하여 파일 인스턴스 생성
			
			if ( file2.isFile() ) 	{   // file2가 파일이라면
				fileList.add( name );   // fileList에 i번째 file추가
			}
			else{
				folderList.add( name ); // 파일이 아니라면, folderList에 추가
			}

		}

		for ( int j = 0 ; j < folderList.size() ; j++ ){ // folderList 길이만큼 반복
			String temp = "";
			for ( int k = 0 ; k <= cnt ; k++ )		{ 	 // k가 메인메소드에서 넘겨받은 cnt보다 작아 같아질 때까지 반복
				temp += "\t" ;	// 탭 삽입
			}
			temp += "\t" ;		// 탭 삽입
			temp += folderList.get(j) ; // folderList에서 j번째 인덱스를 temp로 삽입
			temp += "\n" ;		// 줄바꿈

			byte [] barray = temp.getBytes();		// String타입 temp변수의 값을 byte[]으로 캐스팅
			for(int i=0; i<barray.length; i++) {	// barray길이만큼 loop 반복
				try {
					fileoutputstream.write(barray[i]);
				} catch (IOException e) {     // IOException의 예외처리
					e.printStackTrace();  	  // IOException의 에러 원인 추적
				}
			}
			find ( fileoutputstream ,  folder + "\\" +  folderList.get(j) , (cnt+1) ); // find메소드 재귀호출을 통해 하위 폴더 탐색
		}
		
		for ( int j = 0 ; j < fileList.size() ; j++ ){ // folderList 길이만큼 반복
			String temp = "";
			for ( int k = 0 ; k <= cnt ; k++ )		{ 	 // k가 메인메소드에서 넘겨받은 cnt보다 작아 같아질 때까지 반복
				temp += "\t" ;	// 탭 삽입
			}
			temp += "\t" ;		// 탭 삽입
			temp += folderList.get(j) ; // folderList에서 j번째 인덱스를 temp로 삽입
			temp += "\n" ;		// 줄바꿈

			byte [] barray = temp.getBytes();		// String타입 temp변수의 값을 byte[]으로 캐스팅
			for(int i=0; i<barray.length; i++) {	// barray길이만큼 loop 반복
				try {
					fileoutputstream.write(barray[i]);
				} catch (IOException e) {     // IOException의 예외처리
					e.printStackTrace();  	  // IOException의 에러 원인 추적
				}
			}
			
		}	
	}
	
}