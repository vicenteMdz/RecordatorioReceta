package edu.unsis.recetario.treatements.dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import database.Connection;

/**
 * Created by Octavio on 03/12/2017.
 */

public class TratamientoDAO {

    protected SQLiteDatabase database;
    private Connection connection;
    private Context context;

    public TratamientoDAO(Context context){
        this.context = context;
        connection = Connection.getConnection(context);
    }

    public void openRead() throws SQLException {
        if(connection == null){
            connection = Connection.getConnection(this.context);
        }
        database = connection.getReadableDatabase();
    }

    public void openWrite() throws SQLException{
        if(connection == null){
            connection = Connection.getConnection(this.context);
        }
        database = connection.getReadableDatabase();
    }
}
