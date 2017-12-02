package edu.unsis.recetario.notifications.dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import database.Connection;
import edu.unsis.recetario.notifications.model.Notificacion;

/**
 * Created by Meltsan on 02/12/17.
 */
public class NotificacionesDAO {

    protected SQLiteDatabase database;
    private Connection connection;
    private Context context;

    public NotificacionesDAO(Context context){
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
