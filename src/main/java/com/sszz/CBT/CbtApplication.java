package com.sszz.CBT;

import com.sszz.CBT.domain.WrittenTestVO;
import com.sszz.CBT.service.CbtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CbtApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CbtApplication.class, args);
	}

	@Autowired
	CbtService cbtService;

	@Override
	public void run(String... args) throws Exception {

		/*cbtService.save(new WrittenTestVO(
				"s1","hc1","다음은 운영체제의 5계층을 계층별로 설명한 내용이다. 하위계층에서 상위계층으로 바르게 나열된것은?",
				"ㄱ. 동기화 및 프로세서 스케줄링 담당 ㄴ. 프로세스의 생성, 제거, 메시지전달, 시작과 정지 등의 작업 ㄷ. 메모리의 할당 및 회수 기능 담당 ㄹ. 주변장치의 상태파악과 입출력 장치의 스케줄링 ㅁ. 파일의 생성과 소멸, 파일의 열기/닫기, 파일의 유지 및 관리 담당",
				"㉠-㉢-㉡-㉣-㉤",
				"㉠-㉡-㉢-㉣-㉤",
				"㉡-㉠-㉢-㉤-㉣",
				"㉡-㉠-㉤-㉢-㉣",
				"X"
		));

		cbtService.save(new WrittenTestVO(
				"s1",
				"hc1",
				"사용자가 작성한 프로그램을 운영체제에 실행하도록 제출하면 운영체제는 이를 받아 프로세스를 생성한다. 이 때 생성된 프로세스의 주소 영역(Address Space)을 옳게 열거한 것은?",
				"",
				"메모리 영역, 데이터 영역, 정적영역",
				"텍스트 영역, 스택 영역, 코드 영역",
				"코드 영역, 텍스트 영역, 데이터 영역",
				"코드 영역, 데이터 영역, 스택 영역",
				"X"
		));

		cbtService.save(new WrittenTestVO(
				"s1",
				"hc1",
				"유닉스계열의 시스템에서 핵심부분인 커널(Kernel)에 대한 설명으로 옳지 않은 것은?",
				"",
				"유닉스 시스템의 구성은 커널(Kernel), 셀(Shell), 파일시스템(File System)으로 구성되며, 커널은 프로세스 관리, 메모리 관리, 입출력 관리를 수행한다.",
				"커널(Kernel)은 데몬(Daemon) 프로세스를 실행하고 관리한다.",
				"유닉스계열의 시스템이 부팅될 때 가장 먼저 읽혀지는 핵심 부분으로 보조기억장치에 상주한다.",
				"커널(Kernel)은 셀(Shell)과 상호 연관되며 작업을 수행한다.",
				"X"
		));

		cbtService.save(new WrittenTestVO(
				"s1",
				"hc1",
				"유닉스계열의 시스템에서 일반 계정의 비밀번호를 저장할 때 암호화하여 저장한다. 일반적으로 어떤 알고리즘을 이용하여 저장하는가?",
				"",
				"DES",
				"MD5",
				"SHA",
				"RSA",
				"X"
		));*/

	}
}
