import java.util.ArrayList;
import java.util.Scanner;

public class HangmanGame {
    public static int attemptCounter;// количество попыток
    //public static String hiddenWord;// загаданное слово
    //public static char[] voicedLetters = new char[33];// массив названных угаданных букв
    //public static char[] voicedIncorrectLetters = new char[33];// массив названных неправильных букв

    // массив названных букв
    public static ArrayList<Character> voicedLetters = new ArrayList<>();

    // загаданное слово в виде массива символов
    public static char[] hiddenWordArray;
    // маска загаданного слова, вначале состоит из звездочек
    // и по мере угадывания звездочки заменяются на буквы
    public static char[] maskHiddenWordArray;

    public static void main(String[] args) {

    // показываем приветственное сообщение, рисуем виселицу и начинаем игру или выход
        while (playOrQuit()) {
            // очищаем массив с названными буквами
            voicedLetters.clear();

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
                while (!saveLetter(readTheEnteredLetter())) {
                    System.out.println("Ты уже вводил эту букву");
                }
                if (checkingTheEnteredLetterInWord(voicedLetters.get(voicedLetters.size() - 1))) {
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

        if (answer.equals("да")) {
            System.out.println("""
                Отлично! я загадаю слово,
                а ты должен называть буквы и отгодать его
                ты можешь ошибиться 5 раз, а на шестой я тебя повешу)))
                """);
            drawHangman(6);// метод рисует виселицу
            return true;
        } else if (answer.equals("нет")) {
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

    // считываем введенную юзером букву
    private static char readTheEnteredLetter() {
        System.out.printf("у тебя осталось %d попыток \n", 6 - attemptCounter);
        System.out.println("ты называл следующие буквы: " + voicedLetters);
        System.out.println("введи букву");
        Scanner in = new Scanner(System.in);
        String letter = in.next(); // читаем строку и записываем в переменную
        // преобразовали строку в символ
        return letter.charAt(0);
    }

    // сохраняем букву в ArrayList
    public static boolean saveLetter(char letter) {
        if (checkLetterWasCalled(letter)){
            return false;
        }
        voicedLetters.add(letter);
        //System.out.println("ты называл следующие буквы: " + voicedLetters);
        return true;
    }

    // проверяем называлась ли буква
    public static boolean checkLetterWasCalled(char letter) {
//        System.out.println(voicedLetters.contains(letter));
        return voicedLetters.contains(letter);
    }

    // проверяем правильная ли буква, если правильная то записываем ее в маску
    private static boolean checkingTheEnteredLetterInWord(char singleChar){
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

    // вывод маски слова
    private static void showMaskWord(){
        //for (int i = 0; i < nums.length; i++)
        //System.out.println("****");
    }
}

