//import java.util.Arrays;
import java.util.Scanner;//импортируем модуль Scanner из пакета java.util

public class HangmanGame {
    public static int attempt;// количество попыток
    public static String word;// загаданное слово
    //public static String maskWord;// маска загаданного слова
    public static String[] correctLetters;// массив названных угаданных букв
    public static String[] incorrectLetters;// массив названных неправильных букв
    public static char[] wordArray;
    public static char[] maskWordArray;

    public static void main(String[] args) {
            //boolean playOrQuit = playOrQuit();
            if (playOrQuit()){ // вызываем метод playOrQuit() "Игра или Выход"
                System.out.println("""
                        Отлично! я загадаю слово,
                        а ты должен называть буквы и отгодать его
                        ты можешь ошибиться 5 раз, а на шестой я тебя повешу)))
                        """);
                drawHangman(6);
            }
            else {
                System.out.println("Жаль, мне так хотелось тебя вздернуть)))");
                drawHangman(6);
                System.out.println("еще увидимся)))");
            }
            word = makeWord();// получаем загаданное слово
        wordArray = word.toCharArray();// преобразуем строку в массив символов
        maskWordArray = wordArray.clone();// клонируем в маску массив с загаданным словом

        for (int i = 0; i < maskWordArray.length; i++) {// меняем все буквы в маске на звездочки
            maskWordArray[i] = '*';
        }

        attempt = numberOfAttempts();// сбрасываем количество попыток
            System.out.printf("у тебя есть %d попыток \n", attempt);

        System.out.println("перед тобой загаданное слово");
        System.out.println(maskWordArray);
        letterProcessing();// обработка введенной буквы
        }

    // обрабатываем написанную пользователем букву
    public static void letterProcessing(){
        System.out.println("введи букву");
        Scanner in = new Scanner(System.in);
        String letter = in.next(); // читаем строку и записываем в переменную
        char singleChar = letter.charAt(0);// преобразовали строку в символ
        //in.close();// если пытаюсь закрыть то ошибка, пока не разобрался
        boolean letterExists = false;// есть буква в слове или нет
        for (int i = 0; i < wordArray.length; i++) {// пробегаемся по массиву

            if (wordArray[i] == singleChar){
                maskWordArray[i] = singleChar;// меняем в маске звездочку на букву
                letterExists = true;
            }
        }
        if (letterExists) {
            System.out.println("ты молодец - угадал");
            System.out.println(maskWordArray);
        } else {
            System.out.println("ты ошибся - нет такой буквы");
            System.out.println(maskWordArray);
        }
        // вызвать метод при не правильной букве
        // вызвать метод при правильной букве

    }
        // вывод маски слова
        public static void showMaskWord(){
            //for (int i = 0; i < nums.length; i++)
            //System.out.println("****");
        }
        // меняем количество попыток, вызываем метод до игры
        public static int numberOfAttempts(){
        return 6;
        }
        // меняем количество попыток, вызываем метод во время игры
        public static int numberOfAttempts(int attempt){
            int att = attempt - 1;
            return att;
        }
        public static boolean playOrQuit(){
            System.out.println("Поиграем в виселицу? Да - поиграем, Нет - выйти");
            Scanner in = new Scanner(System.in);
            String answer = in.nextLine(); // читаем строку и записываем в переменную
            //in.close(); // закрываем Scanner иначе может быть утечка ресурсов

            if (answer.equals("Да") || answer.equals("да")){
                return true;
            }   else if (answer.equals("Нет") || answer.equals("нет")){
                return false;
            }
            else {
                System.out.println("введите Да или Нет");
                return false;
            }

        }
        public static void drawHangman(int step) {
            String draw = "";
            switch (step) {
                case 6:
                     draw =  """
                            _________
                            | /     |
                            |/      |
                            |       |
                            |       O
                            |      V|V
                            |       П
                            |
                            |__________
                            """;
                    System.out.println(draw);
                    break;
                case 5:
                    draw =  """
                            _________
                            | /     |
                            |/      |
                            |       |
                            |
                            |
                            |
                            |
                            |__________
                            """;
                    System.out.println(draw);
                    break;
                case 4:
                    draw =  """
                            _________
                            | /
                            |/
                            |
                            |
                            |
                            |
                            |
                            |__________
                            """;
                    System.out.println(draw);
                    break;
                case 3:
                    draw =  """
                            | /
                            |/
                            |
                            |
                            |
                            |
                            |
                            |__________
                            """;
                    System.out.println(draw);
                    break;
                case 2:
                    draw =  """
                            |
                            |
                            |
                            |
                            |
                            |
                            |
                            |__________
                            """;
                    System.out.println(draw);
                    break;
                case 1:draw =  """
                            |__________
                            """;
                    System.out.println(draw);
                    break;
            }
        }

        // загадываем слово (выбираем из словаря)
        public static String makeWord(){
            return "аавв";
        }
    }

