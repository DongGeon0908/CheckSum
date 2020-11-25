
// CheckSum
import java.util.Scanner;

public class CHECKSUM5 {
	public static int Num_1[] = new int[8]; // ����
	public static String Hex[] = new String[40]; // 16���� ��ȯ�� ����
	public static int Num_2[] = new int[8]; // �� ���� ��
	public static String Check[] = new String[20]; // �Է¹��� �迭���� �ϳ��� ������ ����
	static String NameAndAddress; // �Է¹޴� ��

	public static String Sum[] = new String[8]; // 16���� ������ ���� ���� ��
	public static String Carry[] = new String[8]; // �ö� ������ �����ϴ� �迭
	public static int Num[] = new int[40]; // 16���� ���� 10������ �����Ҷ� ���

	public CHECKSUM5() {
		new CHECKSUM5();
	}

	public static void main(String args[]) {
		introduce();// �ڱ�Ұ�
		input(); // 1. �Է� : ���� �̸��� �ּ�(��: HONG KILDONG/YONGIN)
		Padding();// 2. 4 ����Ʈ�� Parsing( 0 : Padding)
		Exchange_16(); // 3-1. 16������ ��ȯ 10���� --> 16����
		Sum(); // 3-2. SUM ���� ���ϰ� + 1
		Complement(); // 4. CHECKSUM(1's Complement) ���� 15- �� ���� ��
		Vertification(); // 5. Verification �˻� F(�ִ밪) = ���� + �� ���ǰ�
	}

	// ������ �ڱ�Ұ�
	public static void introduce() {
		System.out.println("************************************************************");
		System.out.println("Checksum Program");
		System.out.println("************************************************************");
		System.out.println("Ex) HONGKILDONGYONGIN");
		System.out.println("************************************************************");
	}

	// 1. �Է� : ���� �̸��� �ּ�(�� : HONG KILDONG/YONGIN)
	public static void input() {
		Scanner Scan = new Scanner(System.in); // ���� �Է¹޴� �Լ�
		System.out.println("######  1. �Է� ######");
		NameAndAddress = Scan.nextLine(); // string���� ���� �̸��� �ּ� �Է� �ޱ�
		System.out.println("************************************************************");

		NameAndAddress = NameAndAddress.replace(" ", "");
		System.out.println(NameAndAddress);
	}

	// 2. 4 ����Ʈ�� Parsing( 0 : Padding)
	public static void Padding() {
		System.out.println("######  2. 4 ����Ʈ��  Parsing( 0 : Padding)  ######");
		// �� ������ 0���� ä��
		for (int i = 0; i < 20; i++) {
			if (i < NameAndAddress.length()) { // �Է� ���� ���� ����
				Check[i] = Character.toString(NameAndAddress.charAt(i)); // �Է¹��� ���� char������ CHECK ������ �迭�� �ɰ��� �����Ѵ�.
																			// charAT : i��° ���� �д´�. toString : ������ ��ȯ����
			} else {
				Check[i] = "0"; // �Է� ���� ���ڿ��� ���� CHECK[i]�� �Է��ϰ� ���� ������ ������ 0�� �Է¹޴´�.
			}
		}
		// ĭ���� & ���ڿ� �ٿ��� ����ϱ�
		for (int i = 0; i < 20; i++) { // i�� ũ�⸦ 20���� ������ ���� => �������� 4*5 ��ı����� ����� ���ؼ�
			System.out.printf((new StringBuilder(String.valueOf(Check[i]))).append(" | ").toString(), new Object[0]);
			// StringBuilder : ���ڸ� ��ģ��. Check[i]�� ���� ���δ�. �׸��� append�� ����� |�� �����Ѵ�.
			if (i % 4 == 3) { // ���ٿ� 4�� �̻��� �� ��� �����ٷ� �ѱ��.
				System.out.println();
			}
		}
		System.out.println("************************************************************");
	} // CHECK���� �Է¹��� ���ڵ��� ����ȴ�.
		// 16���� ��ȯ�� ��ȯ�� ���� ���ڸ��� ���� ���ϱ�

	private static void Exchange_16() {
		String Num_S[] = new String[8]; // ��ȯ�� EXCHANGE ���� ����
		int Exchange[] = new int[8];
		System.out.println("######  3-1. 16������ ��ȯ  ######");
		// 16������ ��ȯ�� �� �ڸ��� �� ��� -----> ���
		for (int i = 0; i < 20; i++) {
			if (Check[i] != "0") { // Check[]�迭�� ���� 0�� �ƴ� ��� HEX�� ���� �Է¹���
				Hex[(i * 2)] = String.valueOf(Integer.toHexString(Check[i].charAt(0)).charAt(0)); // valueOf ������ ������ ����
				Hex[(i * 2) + 1] = String.valueOf(Integer.toHexString(Check[i].charAt(0)).charAt(1));
			} else { // CHECK[]�� ���� 0�� ��� HEX�� ���� 0���� ä��
				Hex[i * 2] = "0";
				Hex[(i * 2) + 1] = "0";
			}
		} // HEX���� �Է¹��� ���ڵ��� 16���� ���� ����
			// HEX�� ���� ���, HEX�� ���� 8���� �Ѿ�� �ʵ��� �ϸ�, �Ѿ� ���� �����ٷ� ���
		for (int i = 0; i < Hex.length; i++) {
			System.out.printf((new StringBuilder(String.valueOf(Hex[i]).toUpperCase())).append(" | ").toString(),
					new Object[0]);
			if ((i != 0) && ((i % 8) == 7)) {
				System.out.println();
			}
		}
		for (int i = 0; i < Num.length; i++) {
			// 16������ ���� ���Ͽ� ���� ���� 16���� ��ȯ ---> ���ϱ⸦ �����ϱ� ���� �ش� ���� 10������ ������ �����Ͽ� ���
			// HEX�� ����� ���� ���ڿ��� ��� 10������ ��ȯ�Ͽ� NUM�� �Է�
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
			// ���ڸ��� 10���� ��
		}
		for (int i = 0; i < Num.length; i++) { // Exchange�� NUM���� �Է� �޴´�. ---> �� �ڸ��� ���� ���Ѵ�.
												// EXChange�� �� ���� ���� ���� ��
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
			Num_S[i] = Integer.toHexString(Exchange[i]); // ��ȯ�� Exchange���� NUM_S�� ����
		}
		// NUM_S�� ���� ���Ͽ� SUM���� CARRY���� �Է�
		for (int i = 7; i >= 0; i--) {
			if (i == 7) {
				if (Num_S[i].length() == 1) {
					Num_S[i] = Integer.toHexString(Exchange[i]);
					Sum[i] = String.valueOf(Num_S[i].charAt(0)); // NUM_S�� ���� ����
					Carry[i] = "0"; // ���̼��� �޾Ƽ� carry���� ����
				} else {
					Num_S[i] = Integer.toHexString(Exchange[i]);
					Sum[i] = String.valueOf(Num_S[i].charAt(1));
					Carry[i] = String.valueOf(Num_S[i].charAt(0)); // ����ŭ�� carry��
				}
			} else if (Num_S[i].length() == 1) { // parseint ���� ��ȯ
				Num_S[i] = Integer.toHexString(Integer.parseInt(Carry[i + 1]) + Exchange[i]);
				Sum[i] = String.valueOf(Num_S[i].charAt(0));
				Carry[i] = "0"; // ���̼��� �����ڸ��� ��� ĳ������ 0
			} else { // parseint ���� ��ȯ
				Num_S[i] = Integer.toHexString(Integer.parseInt(Carry[i + 1]) + Exchange[i]);
				Sum[i] = String.valueOf(Num_S[i].charAt(1));
				Carry[i] = String.valueOf(Num_S[i].charAt(0)); // ����ŭ ĳ���� ����
			}
		}
		for (int i = 0; i < 8; i++) { // �� ���� ���� ���� 16������ ǥ�� & ���
			System.out.printf((new StringBuilder(String.valueOf(Sum[i]).toUpperCase())).append(" | ").toString(),
					new Object[0]);
		}
		System.out.println();
		for (int i = 0; i < 8; i++) { // carry���� ���
			System.out.print((new StringBuilder(String.valueOf(Carry[i]).toUpperCase())).append(" | ").toString());
		}
		System.out.println("\n************************************************************");
	}

	// CHECKSUM ���� ���ϰ� + 1
	private static void Sum() { // --> ������ ���� ������ 1�� �� �߰�
		String NUM_S[] = new String[8];
		String Carry_1[] = new String[8];
		int Exchange[] = new int[8];
		System.out.println("######  3-2. SUM  ######");
		for (int i = 0; i < 8; i++) { // 16������ ǥ���� �� ���� ���� ���� --->���� for���� �� ������ CARRY���� ���� �����ϰ� for���� ����߱� �����̴�.
			System.out.printf((new StringBuilder(String.valueOf(Sum[i]).toUpperCase())).append(" ").toString(),
					new Object[0]);
		}
		System.out.print(" + 1(Carry) = ");
		// ĳ���� ���� ���
		if (Carry[0] == "0") {
			for (int i = 0; i < 8; i++) {
				System.out.printf((new StringBuilder(String.valueOf(Sum[i]).toUpperCase())).append(" ").toString(),
						new Object[0]);
			}
		} else { // ĳ���� �ִ� ���
			for (int i = 0; i < Sum.length; i++) {
				if (Sum[i].equals("a")) {
					Exchange[i] = 10; // 10������ ���� ����
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

	// CHECKSUM(1's Complement -> carry ������)

	// ������ ���� CHECKSUM ���� ��ȯ��Ŵ
	private static void Complement() { // ���� 15- �� ���� ��
		String Com[] = new String[8];

		System.out.println("######  4. CHECKSUM(1's Complement)  ######");
		int Exchange[] = new int[8];
		for (int i = 0; i < 8; i++) { // ������ ���� �� ---> plus_16�� �� ��쿡�� Carry�� ���� ���� �����ϹǷ� ���� �Լ��� ���� ����Ѵ�.
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
			Com[i] = Integer.toHexString(Exchange[i] = (15 - Exchange[i])); // 10���� ��꿡�� 15- Exchage���� �� ������� �����Ѵ�.
			Num_1[i] = Exchange[i]; // 15���� �Է� ���� ���� ���� �̸� ������� ��
			System.out.printf((new StringBuilder(String.valueOf(Com[i]).toUpperCase())).append(" ").toString(),
					new Object[0]);
		}
		System.out.println("\n************************************************************");
	}

	// CHECKSUM �˻� F(�ִ밪) = ���� + �� ���ǰ�
	private static void Vertification() { //
		String Com[] = new String[8];

		System.out.println("######  5. Verification  ######");
		int CHECK[] = new int[8];
		int Exchange[] = new int[8];
		// -���ȭ��-
		// 16������ ��ȯ�� �� �ڸ��� �� ��� ----->
		for (int i = 0; i < 20; i++) {
			if (!Check[i].equals("0")) { // Check[]�迭�� ���� 0�� �ƴ� ��� HEX�� ���� �Է¹���
				Hex[i * 2] = String.valueOf(Integer.toHexString(Check[i].charAt(0)).charAt(0)); // valueOf ������ ������ ����
				Hex[(i * 2) + 1] = String.valueOf(Integer.toHexString(Check[i].charAt(0)).charAt(1));
			} else { // CHECK[]�� ���� 0�� ��� HEX�� ���� 0���� ä��
				Hex[i * 2] = "0";
				Hex[(i * 2) + 1] = "0";
			}
		}
		// HEX�� ���� ���, HEX�� ���� 8���� �Ѿ�� �ʵ��� �ϸ�, �Ѿ� ���� �����ٷ� ���
		for (int i = 0; i < Hex.length; i++) {
			System.out.printf((new StringBuilder(String.valueOf(Hex[i]).toUpperCase())).append(" | ").toString(),
					new Object[0]);
			if ((i != 0) && ((i % 8) == 7)) {
				System.out.println();
			}
		}
		for (int i = 0; i < Sum.length; i++) // ���� ������ Compliment�� ������ |�� �����ϱ� ���ؼ� �߰��Ͽ���.
			if (Sum[i].equals("a")) { // 10������ ������ ����
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
		// NUM_2 ������ ��
		for (int i = 0; i < 8; i++) {
			Com[i] = Integer.toHexString(Exchange[i] = (15 - Exchange[i])); // ���� ���ϱ�
			Num_1[i] = Exchange[i]; // ����
			System.out.printf((new StringBuilder(String.valueOf(Com[i]).toUpperCase())).append(" | ").toString(),
					new Object[0]);
		} // ������ ���
		System.out.println();
		for (int i = 0; i < 8; i++) {
			CHECK[i] = (Num_1[i] + Num_2[i]); // ������ �� ���� ���� ����
			System.out.printf((new StringBuilder(String.valueOf(Integer.toHexString(CHECK[i]).toUpperCase())))
					.append(" | ").toString(), new Object[0]); // ���� ���� ����ϴµ� ������ �� ���� ���� ���� ���� F�̸� CHECKSUM SUCCESS
		} // CHECKSUM�� �´��� �˻�
		System.out.printf("\n************************************************************", new Object[0]);
	}
}