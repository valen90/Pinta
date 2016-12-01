package com.example.valen.pinta;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MotionEventCompat;
import android.text.Layout;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CanvasView customCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);
    }

    public void clearCanvas(View v) {
        customCanvas.clearCanvas();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_borrar) {
            customCanvas.clearCanvas();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_aumentar) {
            customCanvas.aumentarAnchoTrazo();
        } else if (id == R.id.nav_disminuir) {
            customCanvas.disminuirAnchoTrazo();
        } else if (id == R.id.nav_negro) {
            customCanvas.cambiarColor(Color.BLACK);
        } else if (id == R.id.nav_rojo) {
            customCanvas.cambiarColor(Color.RED);
        }else if (id == R.id.nav_azul) {
            customCanvas.cambiarColor(Color.BLUE);
        }else if (id == R.id.nav_verde) {
            customCanvas.cambiarColor(Color.GREEN);
        }else if (id == R.id.nav_gris) {
            customCanvas.cambiarColor(Color.GRAY);
        }else if (id == R.id.nav_cian) {
            customCanvas.cambiarColor(Color.CYAN);
        }else if (id == R.id.nav_magenta) {
            customCanvas.cambiarColor(Color.MAGENTA);
        }else if (id == R.id.nav_amarillo) {
            customCanvas.cambiarColor(Color.YELLOW);
        }else if (id == R.id.nav_cuadrado) {
            customCanvas.cambiarTipo("cuadrado");
        }else if (id == R.id.nav_trazo) {
            customCanvas.cambiarTipo("lapiz");
        }else if (id == R.id.nav_circulo) {
            customCanvas.cambiarTipo("circulo");
        }else if (id == R.id.nav_relleno) {
            customCanvas.cambiarEstilo("lleno");
        }else if (id == R.id.nav_vacio) {
            customCanvas.cambiarEstilo("vacio");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

