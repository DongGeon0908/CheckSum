
// CheckSum
import java.util.Scanner;

public class CHECKSUM5 {
	public static int Num_1[] = new int[8]; // 보수
	public static String Hex[] = new String[40]; // 16진수 변환값 저장
	public static int Num_2[] = new int[8]; // 각 열의 값
	public static String Check[] = new String[20]; // 입력받은 배열값을 하나씩 나누어 저장
	static String NameAndAddress; // 입력받는 값

	public static String Sum[] = new String[8]; // 16진수 각열의 값을 더한 값
	public static String Carry[] = new String[8]; // 올라간 차수를 저장하는 배열
	public static int Num[] = new int[40]; // 16진수 값을 10진수로 저장할때 사용

	public CHECKSUM5() {
		new CHECKSUM5();
	}

	public static void main(String args[]) {
		introduce();// 자기소개
		input(); // 1. 입력 : 영문 이름과 주소(예: HONG KILDONG/YONGIN)
		Padding();// 2. 4 바이트로 Parsing( 0 : Padding)
		Exchange_16(); // 3-1. 16진수로 변환 10진수 --> 16진수
		Sum(); // 3-2. SUM 값을 구하고 + 1
		Complement(); // 4. CHECKSUM(1's Complement) 보수 15- 각 열의 값
		Vertification(); // 5. Verification 검사 F(최대값) = 보수 + 각 열의값
	}

	// 간단한 자기소개
	public static void introduce() {
		System.out.println("************************************************************");
		System.out.println("Checksum Program");
		System.out.println("************************************************************");
		System.out.println("Ex) HONGKILDONGYONGIN");
		System.out.println("************************************************************");
	}

	// 1. 입력 : 영문 이름과 주소(예 : HONG KILDONG/YONGIN)
	public static void input() {
		Scanner Scan = new Scanner(System.in); // 값을 입력받는 함수
		System.out.println("######  1. 입력 ######");
		NameAndAddress = Scan.nextLine(); // string으로 영문 이름과 주소 입력 받기
		System.out.println("************************************************************");

		NameAndAddress = NameAndAddress.replace(" ", "");
		System.out.println(NameAndAddress);
	}

	// 2. 4 바이트로 Parsing( 0 : Padding)
	public static void Padding() {
		System.out.println("######  2. 4 바이트로  Parsing( 0 : Padding)  ######");
		// 빈 공간을 0으로 채움
		for (int i = 0; i < 20; i++) {
			if (i < NameAndAddress.length()) { // 입력 받은 값의 길이
				Check[i] = Character.toString(NameAndAddress.charAt(i)); // 입력받은 값을 char형으로 CHECK 문자형 배열에 쪼개어 삽입한다.
																			// charAT : i번째 값을 읽는다. toString : 값들을 반환받음
			} else {
				Check[i] = "0"; // 입력 받은 문자열을 전부 CHECK[i]에 입력하고 나면 나머지 공백은 0을 입력받는다.
			}
		}
		// 칸띄우기 & 문자열 붙여서 출력하기
		for (int i = 0; i < 20; i++) { // i의 크기를 20으로 정의한 이유 => 문제상의 4*5 행렬구조를 만들기 위해서
			System.out.printf((new StringBuilder(String.valueOf(Check[i]))).append(" | ").toString(), new Object[0]);
			// StringBuilder : 문자를 합친다. Check[i]의 값을 붙인다. 그리고 append로 공백과 |을 삽입한다.
			if (i % 4 == 3) { // 한줄에 4개 이상이 될 경우 다음줄로 넘긴다.
				System.out.println();
			}
		}
		System.out.println("************************************************************");
	} // CHECK에는 입력받은 문자들이 저장된다.
		// 16진수 변환과 변환된 값의 각자리의 합을 더하기

	private static void Exchange_16() {
		String Num_S[] = new String[8]; // 변환된 EXCHANGE 값을 받음
		int Exchange[] = new int[8];
		System.out.println("######  3-1. 16진수로 변환  ######");
		// 16진수로 변환된 각 자리의 값 출력 -----> 출력
		for (int i = 0; i < 20; i++) {
			if (Check[i] != "0") { // Check[]배열의 값이 0이 아닐 경우 HEX의 값을 입력받음
				Hex[(i * 2)] = String.valueOf(Integer.toHexString(Check[i].charAt(0)).charAt(0)); // valueOf 지정된 값으로 받음
				Hex[(i * 2) + 1] = String.valueOf(Integer.toHexString(Check[i].charAt(0)).charAt(1));
			} else { // CHECK[]의 값이 0일 경우 HEX의 값을 0으로 채움
				Hex[i * 2] = "0";
				Hex[(i * 2) + 1] = "0";
			}
		} // HEX에는 입력받은 문자들의 16진수 값이 저장
			// HEX의 값을 출력, HEX의 값이 8개가 넘어가지 않도록 하며, 넘어 가면 다음줄로 출력
		for (int i = 0; i < Hex.length; i++) {
			System.out.printf((new StringBuilder(String.valueOf(Hex[i]).toUpperCase())).append(" | ").toString(),
					new Object[0]);
			if ((i != 0) && ((i % 8) == 7)) {
				System.out.println();
			}
		}
		for (int i = 0; i < Num.length; i++) {
			// 16진수의 값을 비교하여 값을 저장 16진수 변환 ---> 더하기를 진행하기 위해 해당 값을 10진수의 값으로 저장하여 계산
			// HEX의 저장된 값이 문자열일 경우 10진수로 변환하여 NUM에 입력
			if (Hex[i].equals("a")) {
				Num[i] = 10;
			} else if (Hex[i].equals("b")) {
				Num[i] = 11;
			} else if (Hex[i].equals("c")) {
				Num[i] = 12;
			} else if (Hex[i].equals("d")) {
				Num[i] = 13;
			} else if (Hex[i].equals("e")) {
				Num[i] = 14;
			} else if (Hex[i].equals("f")) {
				Num[i] = 15;
			} else {
				Num[i] = Integer.parseInt(Hex[i]);
			}
			// 각자리의 10진수 값
		}
		for (int i = 0; i < Num.length; i++) { // Exchange에 NUM값을 입력 받는다. ---> 각 자리의 합을 구한다.
												// EXChange는 각 열의 합을 더한 값
			if ((i % 8) == 1) {
				Exchange[1] = Exchange[1] + Num[i];
			} else if ((i % 8) == 2) {
				Exchange[2] = Exchange[2] + Num[i];
			} else if ((i % 8) == 3) {
				Exchange[3] = Exchange[3] + Num[i];
			} else if ((i % 8) == 4) {
				Exchange[4] = Exchange[4] + Num[i];
			} else if ((i % 8) == 5) {
				Exchange[5] = Exchange[5] + Num[i];
			} else if ((i % 8) == 6) {
				Exchange[6] = Exchange[6] + Num[i];
			} else if ((i % 8) == 7) {
				Exchange[7] = Exchange[7] + Num[i];
			} else {
				Exchange[0] = Exchange[0] + Num[i];
			}
		}
		for (int i = 0; i < 8; i++) {
			Num_S[i] = Integer.toHexString(Exchange[i]); // 변환된 Exchange값을 NUM_S에 삽입
		}
		// NUM_S의 값을 비교하여 SUM값과 CARRY값을 입력
		for (int i = 7; i >= 0; i--) {
			if (i == 7) {
				if (Num_S[i].length() == 1) {
					Num_S[i] = Integer.toHexString(Exchange[i]);
					Sum[i] = String.valueOf(Num_S[i].charAt(0)); // NUM_S의 값을 받음
					Carry[i] = "0"; // 길이수를 받아서 carry값을 설정
				} else {
					Num_S[i] = Integer.toHexString(Exchange[i]);
					Sum[i] = String.valueOf(Num_S[i].charAt(1));
					Carry[i] = String.valueOf(Num_S[i].charAt(0)); // 값만큼의 carry수
				}
			} else if (Num_S[i].length() == 1) { // parseint 진수 변환
				Num_S[i] = Integer.toHexString(Integer.parseInt(Carry[i + 1]) + Exchange[i]);
				Sum[i] = String.valueOf(Num_S[i].charAt(0));
				Carry[i] = "0"; // 길이수가 일의자리인 경우 캐리값은 0
			} else { // parseint 진수 변환
				Num_S[i] = Integer.toHexString(Integer.parseInt(Carry[i + 1]) + Exchange[i]);
				Sum[i] = String.valueOf(Num_S[i].charAt(1));
				Carry[i] = String.valueOf(Num_S[i].charAt(0)); // 값만큼 캐리수 증가
			}
		}
		for (int i = 0; i < 8; i++) { // 각 열을 더한 값을 16진수로 표현 & 출력
			System.out.printf((new StringBuilder(String.valueOf(Sum[i]).toUpperCase())).append(" | ").toString(),
					new Object[0]);
		}
		System.out.println();
		for (int i = 0; i < 8; i++) { // carry값을 출력
			System.out.print((new StringBuilder(String.valueOf(Carry[i]).toUpperCase())).append(" | ").toString());
		}
		System.out.println("\n************************************************************");
	}

	// CHECKSUM 값을 구하고 + 1
	private static void Sum() { // --> 위에서 구한 값에서 1을 더 추가
		String NUM_S[] = new String[8];
		String Carry_1[] = new String[8];
		int Exchange[] = new int[8];
		System.out.println("######  3-2. SUM  ######");
		for (int i = 0; i < 8; i++) { // 16진수로 표현한 각 열의 값을 더함 --->따로 for문을 쓴 이유는 CARRY값이 같이 증가하게 for문을 사용했기 때문이다.
			System.out.printf((new StringBuilder(String.valueOf(Sum[i]).toUpperCase())).append(" ").toString(),
					new Object[0]);
		}
		System.out.print(" + 1(Carry) = ");
		// 캐리가 없을 경우
		if (Carry[0] == "0") {
			for (int i = 0; i < 8; i++) {
				System.out.printf((new StringBuilder(String.valueOf(Sum[i]).toUpperCase())).append(" ").toString(),
						new Object[0]);
			}
		} else { // 캐리가 있는 경우
			for (int i = 0; i < Sum.length; i++) {
				if (Sum[i].equals("a")) {
					Exchange[i] = 10; // 10진수의 값을 저장
				} else if (Sum[i].equals("b")) {
					Exchange[i] = 11;
				} else if (Sum[i].equals("c")) {
					Exchange[i] = 12;
				} else if (Sum[i].equals("d")) {
					Exchange[i] = 13;
				} else if (Sum[i].equals("e")) {
					Exchange[i] = 14;
				} else if (Sum[i].equals("f")) {
					Exchange[i] = 15;
				} else {
					Exchange[i] = Integer.parseInt(Sum[i]);
				}

			}
			Exchange[7] = Exchange[7] + Integer.parseInt(Carry[0]);

			for (int i = 0; i < 8; i++) {
				NUM_S[i] = Integer.toHexString(Exchange[i]);
			}
			for (int i = 7; i >= 0; i--) {
				if (i == 7) {
					if (NUM_S[i].length() == 1) {
						NUM_S[i] = Integer.toHexString(Exchange[i]);

						Carry_1[i] = "0";
						Sum[i] = String.valueOf(NUM_S[i].charAt(0));
					} else {
						NUM_S[i] = Integer.toHexString(Exchange[i]);

						Carry_1[i] = String.valueOf(NUM_S[i].charAt(0));
						Sum[i] = String.valueOf(NUM_S[i].charAt(1));
					}
				} else if (NUM_S[i].length() == 1) {
					NUM_S[i] = Integer.toHexString(Integer.parseInt(Carry_1[i + 1]) + Exchange[i]);

					Carry_1[i] = "0";
					Sum[i] = String.valueOf(NUM_S[i].charAt(0));
				} else {
					NUM_S[i] = Integer.toHexString(Integer.parseInt(Carry_1[i + 1]) + Exchange[i]);

					Carry_1[i] = String.valueOf(NUM_S[i].charAt(0));
					Sum[i] = String.valueOf(NUM_S[i].charAt(1));
				}
			}
			for (int i = 0; i < 8; i++) {
				System.out.printf((new StringBuilder(String.valueOf(Sum[i]).toUpperCase())).append(" ").toString(),
						new Object[0]);
			}

		}
		System.out.println("\n************************************************************");
	}

	// CHECKSUM(1's Complement -> carry 떄문에)

	// 위에서 구한 CHECKSUM 값을 변환시킴
	private static void Complement() { // 보수 15- 각 열의 값
		String Com[] = new String[8];

		System.out.println("######  4. CHECKSUM(1's Complement)  ######");
		int Exchange[] = new int[8];
		for (int i = 0; i < 8; i++) { // 각열을 더한 값 ---> plus_16을 쓸 경우에는 Carry의 값도 같이 증가하므로 따로 함수를 만들어서 사용한다.
			System.out.printf((new StringBuilder(String.valueOf(Sum[i]).toUpperCase())).append(" ").toString(),
					new Object[0]);
		}

		System.out.print(" = ");

		for (int i = 0; i < Sum.length; i++)

			if (Sum[i].equals("a")) {
				Exchange[i] = 10;
				Num_2[i] = Exchange[i];
			} else if (Sum[i].equals("b")) {
				Exchange[i] = 11;
				Num_2[i] = Exchange[i];
			} else if (Sum[i].equals("c")) {
				Exchange[i] = 12;
				Num_2[i] = Exchange[i];
			} else if (Sum[i].equals("d")) {
				Exchange[i] = 13;
				Num_2[i] = Exchange[i];
			} else if (Sum[i].equals("e")) {
				Exchange[i] = 14;
				Num_2[i] = Exchange[i];
			} else if (Sum[i].equals("f")) {
				Exchange[i] = 15;
				Num_2[i] = Exchange[i];
			} else {
				Exchange[i] = Integer.parseInt(Sum[i]);
				Num_2[i] = Exchange[i];
			}

		for (int i = 0; i < 8; i++) {
			Com[i] = Integer.toHexString(Exchange[i] = (15 - Exchange[i])); // 10진수 계산에서 15- Exchage값을 뺀 결과값을 저장한다.
			Num_1[i] = Exchange[i]; // 15에서 입력 받은 값을 뺀다 이를 보수라고 험
			System.out.printf((new StringBuilder(String.valueOf(Com[i]).toUpperCase())).append(" ").toString(),
					new Object[0]);
		}
		System.out.println("\n************************************************************");
	}

	// CHECKSUM 검사 F(최대값) = 보수 + 각 열의값
	private static void Vertification() { //
		String Com[] = new String[8];

		System.out.println("######  5. Verification  ######");
		int CHECK[] = new int[8];
		int Exchange[] = new int[8];
		// -출력화면-
		// 16진수로 변환된 각 자리의 값 출력 ----->
		for (int i = 0; i < 20; i++) {
			if (!Check[i].equals("0")) { // Check[]배열의 값이 0이 아닐 경우 HEX의 값을 입력받음
				Hex[i * 2] = String.valueOf(Integer.toHexString(Check[i].charAt(0)).charAt(0)); // valueOf 지정된 값으로 받음
				Hex[(i * 2) + 1] = String.valueOf(Integer.toHexString(Check[i].charAt(0)).charAt(1));
			} else { // CHECK[]의 값이 0일 경우 HEX의 값을 0으로 채움
				Hex[i * 2] = "0";
				Hex[(i * 2) + 1] = "0";
			}
		}
		// HEX의 값을 축력, HEX의 값이 8개가 넘어가지 않도록 하며, 넘어 가면 다음줄로 출력
		for (int i = 0; i < Hex.length; i++) {
			System.out.printf((new StringBuilder(String.valueOf(Hex[i]).toUpperCase())).append(" | ").toString(),
					new Object[0]);
			if ((i != 0) && ((i % 8) == 7)) {
				System.out.println();
			}
		}
		for (int i = 0; i < Sum.length; i++) // 밑의 문장은 Compliment와 같지만 |를 삽입하기 위해서 추가하였다.
			if (Sum[i].equals("a")) { // 10진수의 값으로 저장
				Exchange[i] = 10;
				Num_2[i] = Exchange[i];
			} else if (Sum[i].equals("b")) {
				Exchange[i] = 11;
				Num_2[i] = Exchange[i];
			} else if (Sum[i].equals("c")) {
				Exchange[i] = 12;
				Num_2[i] = Exchange[i];
			} else if (Sum[i].equals("d")) {
				Exchange[i] = 13;
				Num_2[i] = Exchange[i];
			} else if (Sum[i].equals("e")) {
				Exchange[i] = 14;
				Num_2[i] = Exchange[i];
			} else if (Sum[i].equals("f")) {
				Exchange[i] = 15;
				Num_2[i] = Exchange[i];
			} else {
				Exchange[i] = Integer.parseInt(Sum[i]);
				Num_2[i] = Exchange[i];
			}
		// NUM_2 각열의 값
		for (int i = 0; i < 8; i++) {
			Com[i] = Integer.toHexString(Exchange[i] = (15 - Exchange[i])); // 보수 구하기
			Num_1[i] = Exchange[i]; // 보수
			System.out.printf((new StringBuilder(String.valueOf(Com[i]).toUpperCase())).append(" | ").toString(),
					new Object[0]);
		} // 보수를 출력
		System.out.println();
		for (int i = 0; i < 8; i++) {
			CHECK[i] = (Num_1[i] + Num_2[i]); // 보수와 각 열의 값을 더함
			System.out.printf((new StringBuilder(String.valueOf(Integer.toHexString(CHECK[i]).toUpperCase())))
					.append(" | ").toString(), new Object[0]); // 더한 값을 출력하는데 보수와 각 열의 값을 더한 값이 F이면 CHECKSUM SUCCESS
		} // CHECKSUM이 맞는지 검사
		System.out.printf("\n************************************************************", new Object[0]);
	}
}