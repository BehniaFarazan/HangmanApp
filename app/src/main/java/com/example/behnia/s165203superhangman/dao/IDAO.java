package com.example.behnia.s165203superhangman.dao;

import android.content.Context;

import com.example.behnia.s165203superhangman.dto.DTO;

import java.util.List;

/**
 * Created by Behnia on 12-11-2017.
 */

public interface IDAO {
    void add(DTO dto) throws DALException;

    List<DTO> getAll() throws DALException;

    void removeAll() throws DALException;
    void save(Context context) throws DALException;

    class DALException extends Exception {
        public DALException(String msg) {
            super(msg);
        }
    }
}
