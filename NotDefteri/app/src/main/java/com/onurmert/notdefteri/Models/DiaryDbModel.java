package com.onurmert.notdefteri.Models;

public class DiaryDbModel {

    private int _id;

    private String _titlee;

    private String _notess;

    public DiaryDbModel(int id, String title, String notee){

        this.set_id(id);

        this.set_titlee(title);

        this.set_notess(notee);
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_titlee() {
        return _titlee;
    }

    public void set_titlee(String _titlee) {
        this._titlee = _titlee;
    }

    public String get_notess() {
        return _notess;
    }

    public void set_notess(String _notess) {
        this._notess = _notess;
    }
}