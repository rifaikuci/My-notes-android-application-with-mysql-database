package com.rifaikuci.yeni.yerler.mynotesandroidapplicationwithmysqldatabase.activity.main;

import com.rifaikuci.yeni.yerler.mynotesandroidapplicationwithmysqldatabase.model.Note;

import java.util.List;

public interface MainView {

    void showLoading();
    void hideLoading();
    void onGetResult(List<Note> notes);
    void onErrorLoading(String message);

}
