package com.example.todolist;

public interface AnimationListener {

    void onMove(int fromPos, int toPos);
    void onSwiped(int direction, int pos);
}
