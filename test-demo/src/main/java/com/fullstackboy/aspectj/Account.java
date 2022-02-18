package com.fullstackboy.aspectj;

public class Account {
    int balance = 20;
    
    // 提款操作
    public boolean withDraw(int amount) {
        if (balance < amount) return false;
        balance -= amount;

        return true;
    }
}