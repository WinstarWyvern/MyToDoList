package com.example.mytodolist;

import java.util.ArrayList;

public class MyToDoListDB {
    private static MyToDoListDB myToDoListDB = null;

    private ArrayList<MyToDoList> myToDoLists = new ArrayList<>();

    private MyToDoListDB(){

    }

    public static MyToDoListDB getInstance() {
        if (myToDoListDB == null) {
            myToDoListDB = new MyToDoListDB();
        }
        return myToDoListDB;
    }

    public ArrayList<MyToDoList> getAllMyToDoList(){
        return myToDoLists;
    }

    public void addNewToDoList(MyToDoList m){
        myToDoLists.add(m);
    }

    public void loadDummyData(){
        myToDoLists.add(new MyToDoList("Tugas 1", "Masak makan siang",android.R.drawable.ic_dialog_alert));
        myToDoLists.add(new MyToDoList("Tugas 2", "Mandi jam 5 sore",android.R.drawable.ic_dialog_alert));
        myToDoLists.add(new MyToDoList("Tugas 3", "Mengerjakan Tugas",android.R.drawable.ic_dialog_alert));
        myToDoLists.add(new MyToDoList("Tugas 4", "Free Time",android.R.drawable.ic_dialog_alert));
    }

    public boolean checkNullMyToDoLists(){
        return myToDoLists.size() <= 0;
    }

    public void updateToDoList(int position, MyToDoList m){
        myToDoLists.set(position, m);
    }
}
