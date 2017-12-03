package database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Meltsan on 30/11/17.
 */
public class Connection extends SQLiteOpenHelper {

    private static Connection connection;
    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BD = "medirecord.db";

    public Connection (Context context){
        super(context, NOMBRE_BD, null, VERSION_BASEDATOS);
    }

    //Singleton para la clase Connection para manejar las instancias de la Base de datos
    public static synchronized Connection getConnection(Context context){
        if(connection == null){
            connection = new Connection(context);
        }
        return connection;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            //crear tabla pacientes
            db.execSQL(SchemaDB.getQryCreateTablePacientes());
            //crear tabla tratamientos
          //  db.execSQL(SchemaDB.getQryCreateTableTratamientos());
            //crear tabla medicamentos
            //db.execSQL(SchemaDB.getQryCreateTableMedicamentos());
            //crear tabla notificaciones
            //db.execSQL(SchemaDB.getQryCreateTableNotificaciones());
            //crear tabla cuentas
            //db.execSQL(SchemaDB.getQryCreateTableAccounts());
        }catch (Exception e){
            Log.d("ErrorCreateSchemaDB",e.getCause().getMessage().toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
