package com.example.myapplication;

public class Book {
    private String bookname;
    private String bookid;
    private String bookRank;
    private String bookPages;
    private String bookMemo;

    public Book(String name, String id, String rank, String memo, String pages) {
        this.bookname = name;
        this.bookid = id;
        this.bookRank = rank;
        this.bookMemo = memo;
        this.bookPages = pages;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBookRank() {
        return bookRank;
    }

    public void setBookRank(String bookRank) {
        this.bookRank = bookRank;
    }

    public String getBookPages() {
        return bookPages;
    }

    public void setBookPages(String bookPages) {
        this.bookPages = bookPages;
    }

    public String getBookMemo() {
        return bookMemo;
    }

    public void setBookMemo(String bookMemo) {
        this.bookMemo = bookMemo;
    }
}
