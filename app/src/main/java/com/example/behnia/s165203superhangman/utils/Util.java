package com.example.behnia.s165203superhangman.utils;

import android.content.Context;

import com.example.behnia.s165203superhangman.Mylogic;
import com.example.behnia.s165203superhangman.dao.DAO;
import com.example.behnia.s165203superhangman.dao.IDAO;
import com.example.behnia.s165203superhangman.dto.DTO;

/**
 * Created by Behnia on 12-11-2017.
 */

public class Util {
    private static Util instance;


    private final Mylogic mylogic = Mylogic.getInstance();
    private final IDAO highScoreDAO = DAO.getInstance();

    private Util() {

    }

    static {
        try {
            instance = new Util();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized Util getInstance() {
        return instance;
    }

    public void createMatchAndReset(Context context) {

        DTO highScoreDTO = new DTO(mylogic.getHighScore(),mylogic.getRound(),mylogic.getTheWord());

        try {
            highScoreDAO.add(highScoreDTO);
            highScoreDAO.save(context);
        } catch (IDAO.DALException e) {
            e.printStackTrace();
        }

        try {
            mylogic.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
