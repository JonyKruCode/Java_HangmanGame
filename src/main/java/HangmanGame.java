import java.util.Scanner;

public class HangmanGame {
    public static int attemptCounter;// количество попыток
    //public static String hiddenWord;// загаданное слово
//    public static String[] correctLetters;// массив названных угаданных букв
//    public static String[] incorrectLetters;// массив названных неправильных букв
    // загаданное слово в виде массива символов
    public static char[] hiddenWordArray;
    // маска загаданного слова, вначале состоит из звездочек
    // и по мере угадывания звездочки заменяются на буквы
    public static char[] maskHiddenWordArray;

    public static void main(String[] args) {

    // показываем приветственное сообщение, рисуем виселицу и начинаем игру или выход
        while (playOrQuit()) {
            //hiddenWord = makeAWord();// получаем загаданное слово
            hiddenWordArray = makeAWord().toCharArray();// преобразуем строку в массив символов
            // клонируем массив с загаданным словом
            // ниже заменим все символы на звездочки и будем показывать юзеру именно маску
            maskHiddenWordArray = hiddenWordArray.clone();
            for (int i = 0; i < maskHiddenWordArray.length; i++) {
                maskHiddenWordArray[i] = '*';
            }

            attemptCounter = 0; // обнуляем счетчик
            //attemptCounter = calculateTheNumberOfAttempts(6);// сбрасываем количество попыток
            //System.out.println("у тебя есть 6 попыток");
            System.out.println("перед тобой загаданное слово");
            System.out.println(maskHiddenWordArray);

            // основной цикл работы программы
            while (attemptCounter < 6) {
                if (checkingTheEnteredLetter(readTheEnteredLetter())) {
                    System.out.println("ты молодец - угадал");
                    System.out.println(maskHiddenWordArray);
                } else {
                    System.out.println("ты ошибся - нет такой буквы");
                    System.out.println(maskHiddenWordArray);
                    attemptCounter++;
                    if (checkGameOver()) {// проверяем на проигрыш
                        System.out.println("Ты проиграл и будешь повешен!");
                        drawHangman(attemptCounter);
                        break;

                    }
                    drawHangman(attemptCounter);
                }
                // проверка на выигрыш
                if (checkWin()) {
                    System.out.println("Поздравляю, ты выиграл!");
                    break;
                }
            }
        }
    }

    // проверка победы
    private static boolean checkWin(){
        for (int i = 0; i < maskHiddenWordArray.length; i++) {
            if (maskHiddenWordArray[i] == '*') {
                return false;
            }
        }
        return true;
    }

    // проверка проигрыша
    private static boolean checkGameOver(){
        if (attemptCounter == 6) {
            return true;
        }
        return false;
    }

    // обработка выбора Играть или Выйти
    private static boolean playOrQuit() {
        System.out.println("Поиграем в виселицу? Да - поиграем, Нет - выйти");
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine(); // читаем строку и записываем в переменную
        //in.close(); // закрываем Scanner иначе может быть утечка ресурсов

        if (answer.equals("Да") || answer.equals("да") || answer.equals("y")) {
            System.out.println("""
                Отлично! я загадаю слово,
                а ты должен называть буквы и отгодать его
                ты можешь ошибиться 5 раз, а на шестой я тебя повешу)))
                """);
            drawHangman(6);// метод рисует виселицу
            return true;
        } else if (answer.equals("Нет") || answer.equals("нет") || answer.equals("n")) {
            System.out.println("Жаль, мне так хотелось тебя вздернуть)))");
            drawHangman(6);
            System.out.println("еще увидимся)))");
            return false;
        } else {
            System.out.println("ошибка ввода");
            return false;
        }

    }

    // метод рисует виселицу
    private static void drawHangman(int howManyAttempt) {
        String pictureOfSymbols = "";
        switch (howManyAttempt) {
            case 6:
                pictureOfSymbols = """
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
                System.out.println(pictureOfSymbols);
                break;
            case 5:
                pictureOfSymbols = """
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
                System.out.println(pictureOfSymbols);
                break;
            case 4:
                pictureOfSymbols = """
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
                System.out.println(pictureOfSymbols);
                break;
            case 3:
                pictureOfSymbols = """
                        | /
                        |/
                        |
                        |
                        |
                        |
                        |
                        |__________
                        """;
                System.out.println(pictureOfSymbols);
                break;
            case 2:
                pictureOfSymbols = """
                        |
                        |
                        |
                        |
                        |
                        |
                        |
                        |__________
                        """;
                System.out.println(pictureOfSymbols);
                break;
            case 1:
                pictureOfSymbols = """
                        |__________
                        """;
                System.out.println(pictureOfSymbols);
                break;
        }
    }

    // TODO метод не сделан, поставленна заглушка
    // загадываем слово (выбираем из словаря)
    private static String makeAWord() {
        return "ааввгг";
    }

//    // меняем количество попыток, вызываем метод до игры
//    private static int calculateTheNumberOfAttempts() {
//        return 6;
//    }
    // меняем количество попыток, вызываем метод во время игры
//    private static int calculateTheNumberOfAttempts(int attempt) {
//        if (attempt == null) {
//        }
//        int att = attempt - 1;
//        return att;
//    }

    // считываем введенную юзером букву
    private static char readTheEnteredLetter() {
        System.out.printf("у тебя осталось %d попыток \n", 6 - attemptCounter);
        System.out.println("введи букву");
        Scanner in = new Scanner(System.in);
        String letter = in.next(); // читаем строку и записываем в переменную
        char singleChar = letter.charAt(0);// преобразовали строку в символ
        return singleChar;
    }

    // проверяем правильная ли буква, если правильная то записываем ее в маску
    private static boolean checkingTheEnteredLetter(char singleChar){
        boolean letterExists = false;

        for (int i = 0; i < hiddenWordArray.length; i++) {
            if (hiddenWordArray[i] == singleChar) {
                // записали правильную букву в маску
                writeTheLetterInTheMask(i, singleChar);
                letterExists = true;
            }
        }
        return letterExists;
    }

    // записываем правильную букву в маску нашего слова
    private static void writeTheLetterInTheMask(int positionLetter, char letter){
        maskHiddenWordArray[positionLetter] = letter;
    }


    // обрабатываем написанную пользователем букву
//    private static void letterProcessing() {
//
//
//        if (letterExists) {
//            System.out.println("ты молодец - угадал");
//            System.out.println(maskHiddenWordArray);
//        } else {
//            System.out.println("ты ошибся - нет такой буквы");
//            System.out.println(maskHiddenWordArray);
//        }
//        // вызвать метод при не правильной букве
//        // вызвать метод при правильной букве
//
//    }


    // вывод маски слова
    private static void showMaskWord(){
        //for (int i = 0; i < nums.length; i++)
        //System.out.println("****");
    }
}

