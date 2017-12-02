package edu.unsis.recetario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.unsis.recetario.notifications.dao.NotificacionesDAO;
import edu.unsis.recetario.notifications.dao.NotificacionesDAOImpl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificacionesDAO n = new NotificacionesDAOImpl(this);
    }
}
