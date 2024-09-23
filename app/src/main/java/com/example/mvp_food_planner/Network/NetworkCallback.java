package com.example.mvp_food_planner.Network;

import java.util.List;

public interface NetworkCallback<T> {
    void onSuccess(List<T> response); // Generic type T for any type
    void onError(String error);
}
