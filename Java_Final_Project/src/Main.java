import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
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
        System.out.println("번호 입력: ");

        int menuChoice = sc.nextInt();
        sc.nextLine();

        // 선택에 따라 출력
        if (menuChoice == 1)
        {
            showLectures();
        } else if (menuChoice == 2)
        {
            saveLectureFile(sc);
        } else
        {
            System.out.println("잘못된 입력");
        }
    }

    public static FileWriter createFileWriter(File file)
    {
        FileWriter writer = null;

        // FileWriter를 생성할 수 있을 때
        if (file != null)
        {
            String path = file.getAbsolutePath();
            // 파일 경로 체크
            if (!path.equals(""))
            {
                try
                {
                    writer = new FileWriter(file);
                } catch (Exception e)
                {
                }
            }
        }
        return writer;
    }

    // 파일에 쓰기
    public static void writeToFile(FileWriter writer, String content)
    {
        if (writer != null)
        {
            try
            {
                writer.write(content);
                writer.close();
            } catch (Exception e)
            {
            }
        }
    }

    //내용 입력 받아 파일에 저장
    public static void saveLectureFile(Scanner scanner)
    {
        String[] daysOfTheWeek = {"월", "화", "수", "목", "금"};
        String content = "";

        for (int i = 0; i < daysOfTheWeek.length; i++)
        {
            System.out.print(daysOfTheWeek[i] + " 강의 입력: ");
            String name = scanner.nextLine();

            System.out.print(daysOfTheWeek[i] + "시작 시간 입력 (예: 09:00): ");
            String time = scanner.nextLine();

            System.out.print(daysOfTheWeek[i] + "강의실 입력 (예: 06-211): ");
            String classroom = scanner.nextLine();

            // 입력 들어온 것이 비어있지 않을 때 내용 저장
            if (!name.equals("") && !time.equals(""))
            {
                content += daysOfTheWeek[i] + "," + name + "," + time + "," + classroom + "\n";
            } else
            {
                System.out.println("저장 실패.");
            }
        }
        File file = new File("lectureList.txt");

        // 파일이 없거나 쓸 수 있을 때
        if (!file.exists() || file.canWrite())
        {
            FileWriter writer = createFileWriter(file);
            // null 아니면 저장
            if (writer != null)
            {
                writeToFile(writer, content);
                System.out.println("저장 성공.");
            } else
            {
                System.out.println("저장 실패.");
            }
        } else
        {
            System.out.println("오류");
        }
    }

    // 강의 목록을 불러와 출력
    public static void showLectures()
    {
        File file = new File("lectureList.txt");

        // 파일이 있고 읽을 수 있을 때
        if (file.exists() && file.canRead())
        {
            FileReader fr = createFileReader(file);

            if (fr != null)
            {
                Scanner reader = new Scanner(fr);
                System.out.println("강의 목록:");

                while (reader.hasNextLine())
                {
                    String line = reader.nextLine();
                    String[] parts = line.split(",");

                    // 내용이 정확히 4개일 때만
                    if (parts.length == 4)
                    {
                        String dOTW = parts[0]; // day of the week 요일
                        String name = parts[1]; // 강의
                        String time = parts[2]; // 강의 시작 시간
                        String classroom = parts[3]; // 강의실
                        System.out.println(dOTW + "요일: " + name + " / 시작 시간: " + time + " / 강의실: " + classroom + "호");
                    }
                }
                reader.close();
            } else
            {
                System.out.println("실행불가");
            }
        } else
        {
            System.out.println("비어있음.");
        }
    }

    // FileReader 생성
    public static FileReader createFileReader(File file)
    {
        FileReader reader = null;

        if (file != null && file.exists() && file.canRead())
        {
            try
            {
                reader = new FileReader(file);
            } catch (Exception e)
            {
            }
        }
        return reader;
    }
}
