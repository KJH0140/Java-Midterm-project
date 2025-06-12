import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {

        Scanner sc = new Scanner(System.in);

        // 메뉴 출력하고 선택 받기
        System.out.println("메뉴");
        System.out.println("1. 강의목록 확인");
        System.out.println("2. 강의목록 수정");
        System.out.println("번호를 입력하시오: ");

        int menuChoice = sc.nextInt();

        // 선택에 따라 출력
        if (menuChoice == 1)
        {
            // 강의 목록 확인 들어갈 자리
            System.out.println("임시로 들어간 문구 1");
        } else if (menuChoice == 2)
        {
            // 강의 목록 수정 들어갈 자리
            System.out.println("임시로 들어간 문구 2");
        } else
        {
            // 예외
            System.out.println("잘못된 입력");
        }
    }
}