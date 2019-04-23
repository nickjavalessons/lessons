package multithreading;

import java.util.*;
import java.util.concurrent.*;

public class Library implements Runnable{
    public static void main(String[] args) {
        Library lib = new Library();
        List<Reader> readers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            readers.add(ReaderGenerator.getReader());
        }
        ExecutorService readersPool = Executors.newFixedThreadPool(lib.getReaderCapacity() + 1);
        readersPool.execute(lib);
        for(Reader reader: readers){
            reader.setLib(lib);
            readersPool.execute(reader);
        }
        readersPool.shutdown();
    }



    public static final Book nullBook = new Book("", "", 0, false);
    private final int readerCapacity = 20;
    private final Map<String, Book> libraryFund; //библиотечный фонд
    private final Map<String, String> libraryCatalogue; //библиотечный каталог
    private final ConcurrentLinkedQueue<String> requestQueue;  //очередь запросов
    private final LinkedBlockingQueue<Book> responceQueue; //очередь выдачи
    private final ConcurrentLinkedQueue<Book> refundQueue; //очередь возврата

    public Library(){
        libraryCatalogue = new ConcurrentHashMap<>();
        libraryFund = new HashMap<>();
        requestQueue = new ConcurrentLinkedQueue<>();
        responceQueue = new LinkedBlockingQueue<>();
        refundQueue = new ConcurrentLinkedQueue<>();
        for (Book book: BooksList.bookList) {
            libraryFund.put(book.getISBN(), book);
            libraryCatalogue.put(book.getName(), book.getISBN());
        }
    }

    public void run(){
        System.out.println("Библиотека открыта");
        String request;
        Book refund;
        while(true){
            request = requestQueue.poll(); // проверяем очередь запросов
            if(request != null){
                System.out.println("Lib: Получили запрос на книгу " + request);
                Book book = nullBook;
                try{
                    Thread.sleep(1000); // ищем книгу
                    if(libraryFund.containsKey(request)){
                        System.out.println("Lib: Книга присутствует в хранилище,  удаляем из хранилища");
                        book = libraryFund.get(request);
                        libraryFund.remove(request);
                    }
                } catch (InterruptedException e){}
                System.out.println("Lib: Выдаем читателю");
                responceQueue.add(book); //кладем найденную книгу или отказ в очередь выдачи
            }
            refund = refundQueue.poll(); //проверяем возврат
            if(refund != null){
                System.out.println("Lib: Вернули книгу " + refund.getISBN());
                libraryFund.put(refund.getISBN(), refund);  //возвращаем книгу
            }
        }
    }
    //действия библиотеки:
    //Найти ISBN книги по названию
    public String findABook(String name){
        System.out.println("Lib: Ищем книгу в каталоге " + name);
        if(libraryCatalogue.containsKey(name)){
            return libraryCatalogue.get(name);
        }
        return "";
    }

    public ConcurrentLinkedQueue<String> getRequestQueue() {
        return requestQueue;
    }

    public LinkedBlockingQueue<Book> getResponceQueue() {
        return responceQueue;
    }

    public ConcurrentLinkedQueue<Book> getRefundQueue() {
        return refundQueue;
    }

    //Выдать книгу по ISBN
//    public Book giveABook(String isbn){
//        return nullBook;
//    }
//    public void getBookBack(Book book){
//    }

    public int getReaderCapacity() {
        return readerCapacity;
    }
}

class Book{
    private String name;
    private String ISBN;
    private int pages;
    private boolean forReadingRoomOnly;

    public Book(String name, String ISBN, int pages, boolean forReadingRoomOnly) {
        this.name = name;
        this.ISBN = ISBN;
        this.pages = pages;
        this.forReadingRoomOnly = forReadingRoomOnly;
    }

    public String getName() {
        return name;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getPages() {
        return pages;
    }

    public boolean isForReadingRoomOnly() {
        return forReadingRoomOnly;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", pages=" + pages +
                ", forReadingRoomOnly=" + forReadingRoomOnly +
                '}';
    }
}

class Reader implements Runnable{
    private Library lib;

    private Set<Book> gotBooks;
    private Set<String> booksToGet;
    public int readingSkills;

    private ConcurrentLinkedQueue<String> requestQueue;  //очередь запросов
    private LinkedBlockingQueue<Book> responceQueue; //очередь выдачи
    private ConcurrentLinkedQueue<Book> refundQueue; //очередь возврата





    public Reader(Set<String> booksToGet, int readingSkills) {
        this.booksToGet = booksToGet;
        this.readingSkills = readingSkills;
        gotBooks = new HashSet<>();
    }

    @Override
    public void run() {
        for(String str: booksToGet){
            String isbn = searchCatalogue(str);
            requestQueue.add(isbn);
            try {
                Book book = responceQueue.take();
                System.out.println("book " + book);
                if (book.isForReadingRoomOnly()) {
                    System.out.println("Читатель " + Thread.currentThread().getName() + " читает книгу " + book.getName());
                    readTheBook(book); //читаем книгу
                    refundBook(book); //возвращаем книгу
                } else {
                    if (book != Library.nullBook)
                    System.out.println("Читатель " + Thread.currentThread().getName() + " забирает домой книгу " + book.getName());
                    gotBooks.add(book);//складываем книжки в рюкзак
                }
            } catch (InterruptedException e){

            }
        }
        for(Book book: gotBooks){ //возвращаем книги из рюкзака
            refundBook(book);
            gotBooks.remove(book);
        }


    }
    //Действия посетителя:
    //Поискать в каталоге по названию
    private String searchCatalogue(String name){
        return lib.findABook(name);
    }
    //прочитать книгу за время: КоличествоСтраниц*СкиллыЧтения
    private void readTheBook(Book book){
        try {
            Thread.sleep(book.getPages()*readingSkills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    //вернуть книгу
    private void refundBook(Book book){
        refundQueue.add(book);
    }

    private void setRequestQueue(ConcurrentLinkedQueue<String> requestQueue) {
        this.requestQueue = requestQueue;
    }

    private void setResponceQueue(LinkedBlockingQueue<Book> responceQueue) {
        this.responceQueue = responceQueue;
    }

    private void setRefundQueue(ConcurrentLinkedQueue<Book> refundQueue) {
        this.refundQueue = refundQueue;
    }

    public void setLib(Library lib) {
        this.lib = lib;
        setRequestQueue(lib.getRequestQueue());
        setRefundQueue(lib.getRefundQueue());
        setResponceQueue(lib.getResponceQueue());
    }
}

class ReaderGenerator{
    private static Random rnd = new Random();
    public static Reader getReader(){
        int booksToGet = rnd.nextInt(5) + 1;
        Set<String> bookSet = new HashSet<>();
        for (int i = 0; i < booksToGet; i++) {
            bookSet.add(BooksList.bookList[rnd.nextInt(BooksList.bookList.length)].getName());
        }
        int readingSkills = rnd.nextInt(8) + 2;
        Reader reader = new Reader(bookSet, readingSkills);
        return reader;
    }
}

class BooksList{
    private static Random rnd = new Random();
    public static final Book[] bookList = new Book[]{
            new Book("Аудит", "978-5-905554-14-8", 512, rnd.nextBoolean()),
            new Book("Аудит. Краткий курс. Учебное пособие.", "978-5-409-00586-3", 126, rnd.nextBoolean()),
            new Book("Аудит. Практикум. Учебное пособие.", "978-5-9916—2408-4", 464, rnd.nextBoolean()),
            new Book("Аудит. Практикум. Учебное пособие.2", "978-5-4468—0815-1", 176, rnd.nextBoolean()),
            new Book("Аудит. Теория и практика.", "978-5-9916—3287-4", 400, rnd.nextBoolean()),
            new Book("Аудит. Учебник", "978-5-4468—0522-8", 176, rnd.nextBoolean()),
            new Book("Аудит. Учебник2", "978-5-9916—2877-8", 544, rnd.nextBoolean()),
            new Book("Аудит. Учебник3","978-5-406-03629-7", 432, rnd.nextBoolean()),
            new Book("Аудит. Учебное пособие", "978-5-9916—1592-1", 640, rnd.nextBoolean()),
            new Book("Аудит2", "978-5-16-005015-7", 272, rnd.nextBoolean()),
            new Book("Бухгалтерский учет и аудит", "978-5-9916—2817-4", 306, rnd.nextBoolean()),
            new Book("Краткий курс лекций", "978-5-9916—2601-9", 208, rnd.nextBoolean()),
            new Book("Международные стандарты аудита", "978-5-9916—3086-3", 464, rnd.nextBoolean()),
            new Book("Основы аудита", "978-5-9765—1320-4", 672, rnd.nextBoolean()),
            new Book("Профессиональные ценности и этика бухгалтеров и аудиторов.", "978-5-9916—3731-2", 318, rnd.nextBoolean()),
            new Book("Ревизия и аудит", "978-985-6954-52-1", 272, rnd.nextBoolean()),
            new Book("Теория и практика.", "978-5-9916—1792-5", 672,rnd.nextBoolean())
    };
}