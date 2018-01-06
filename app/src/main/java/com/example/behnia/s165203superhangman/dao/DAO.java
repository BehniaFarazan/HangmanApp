package com.example.behnia.s165203superhangman.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.behnia.s165203superhangman.dto.DTO;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Behnia on 12-11-2017.
 */

public class DAO implements IDAO {

    private static DAO inst = new DAO();

    private List<DTO> List;

    private DAO() {
        List = new ArrayList<>();
    }

    static {
        try {
            inst = new DAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized DAO getInstance() {
        return inst;
    }

    @Override
    public void add(DTO dto) throws DALException {
        List.add(dto);
    }



    @Override
    public List<DTO> getAll() throws DALException {
        if (List == null) throw new DALException("List is null!");
        return List;
    }


    @Override
    public void removeAll() throws DALException {
        List = new ArrayList<>();
    }


    @Override
    public void save(Context context) throws DALException {

        SharedPreferences sharedP = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedP.edit();


        Gson gson = new Gson();
        String Json = gson.toJson(List);

        editor.putString(TAG, Json);
        editor.apply();
    }


}
