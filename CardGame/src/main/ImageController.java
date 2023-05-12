package main;

import java.io.File;
import java.util.ArrayList;


/**
 * 
 * 파일 경로를 관리하는 클래스입니다.
 *
 */
public class ImageController {
	
	/**
	 * resources/img 경로에 존재하는 파일의 경로들을 모아놓은 ArrayList 입니다. 
	 */
	public ArrayList<String> filePaths = new ArrayList<>();
	
	public ImageController() {
		// img 폴더 경로
		String folderPath = "resources/img";
		
		// 파일 객체 생성
		File folder = new File(folderPath);
		
		// folder 내의 파일 목록 추출
		File[] files = folder.listFiles();
		
		// files 를 순회하여 이미지 파일들만 저장
		for (File file: files) {
			if (file.isFile() && file.getName().endsWith(".png")) {
				filePaths.add(file.getPath());
			}
		}
	}
}
