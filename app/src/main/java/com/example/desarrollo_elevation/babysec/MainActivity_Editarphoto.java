package com.example.desarrollo_elevation.babysec;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.desarrollo_elevation.babysec.Adapter.Adaptergridrow;
import com.example.desarrollo_elevation.babysec.Adapter.adapterselectframe;
import com.example.desarrollo_elevation.babysec.Adapter.model_sticker;
import com.example.desarrollo_elevation.babysec.DB.DBHome;
import com.example.desarrollo_elevation.babysec.gestures.RotateGestureDetector;
import com.example.desarrollo_elevation.babysec.gestures.ShoveGestureDetector;
import com.example.desarrollo_elevation.babysec.stickers.StickerImageView;
import com.example.desarrollo_elevation.babysec.stickers.StickerTextView;
import com.example.desarrollo_elevation.viveelite.R;
import com.example.desarrollo_elevation.babysec.gestures.MoveGestureDetector;
import com.example.photogesturelibrary.PhotoView;
import com.example.photogesturelibrary.PhotoViewAttacher;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION_CODES.M;
import static com.example.desarrollo_elevation.viveelite.R.id.sticker;
//import static com.example.desarrollo_elevation.viveelite.R.mipmap.marco;


public class  MainActivity_Editarphoto extends AppCompatActivity implements SurfaceHolder.Callback {

    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;
    private SurfaceView SurView;
    private SurfaceHolder camHolder;
    private boolean previewRunning;
    private Button button1;
    final Context context = this;
    // public static Camera camera = null;
    private ImageView camera_image;
    private Bitmap bmp,bmp1;
    private ByteArrayOutputStream bos;
    private BitmapFactory.Options options,o,o2;
    private FileInputStream fis;
    ByteArrayInputStream fis2;
    private FileOutputStream fos;
    private File dir_image2,dir_image;
    private AbsoluteLayout /*CamView,*/ absprimario;
    //private static AbsoluteLayout CamView;
    private static RelativeLayout CamView;
    //private FrameLayout mRlView;
   // ImageView marco;
    private final int MY_PERMISSIONS = 100;
    ImageView img1;
    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean cameraview = false;
    LayoutInflater inflater = null;
    Toolbar toolbar;
    private EditText iconeditext;
    Adaptergridrow adapter;
    InputMethodManager imm;

    private  static String[] imagen_sticker;

     int[] imagen = new int[]{

             R.mipmap.elite_gold_01,
             R.mipmap.elite_gold_02,
             R.mipmap.elite_gold_03,
             R.mipmap.elite_gold_04,
             R.mipmap.elite_gold_05,
             R.mipmap.elite_gold_06,
             R.mipmap.elite_gold_07,
             R.mipmap.elite_gold_08,
             R.mipmap.elite_gold_09,
             R.mipmap.elite_gold_10,
             R.mipmap.elite_gold_11,
             R.mipmap.elite_gold_12,
             R.mipmap.elite_gold_13,
             R.mipmap.elite_gold_14,
             R.mipmap.elite_gold_15,


             R.mipmap.celebracionesmsm_01,
             R.mipmap.celebracionesmsm_02,
             R.mipmap.celebracionesmsm_03,
             R.mipmap.celebracionesmsm_04,
             R.mipmap.celebracionesmsm_05,
             R.mipmap.celebracionesmsm_06,
             R.mipmap.celebracionesmsm_07,
             R.mipmap.celebracionesmsm_08,
             R.mipmap.celebracionesmsm_09,
             R.mipmap.celebracionesmsm_10,
             R.mipmap.celebracionesmsm_11,
             R.mipmap.celebracionesmsm_12,
             R.mipmap.celebracionesmsm_13,
             R.mipmap.celebracionesmsm_14,
             R.mipmap.celebracionesmsm_15,
             R.mipmap.celebracionesmsm_16,
             R.mipmap.celebracionesmsm_17,
             R.mipmap.celebracionesmsm_18,
             R.mipmap.celebracionesmsm_19,
             R.mipmap.celebracionesmsm_20,
             R.mipmap.celebracionesmsm_21,
             R.mipmap.celebracionesmsm_22,
             R.mipmap.celebracionesmsm_23,
             R.mipmap.celebracionesmsm_24,
             R.mipmap.corazones_01,
             R.mipmap.corazones_02,
             R.mipmap.corazones_03,
             R.mipmap.corazones_04,
             R.mipmap.corazones_05,
             R.mipmap.corazones_06,
             R.mipmap.corazones_07,
             R.mipmap.corazones_08,
             R.mipmap.banner_01,
             R.mipmap.banner_02,
             R.mipmap.banner_03,
             R.mipmap.banner_04,
             R.mipmap.banner_05,
             R.mipmap.banner_06,
             R.mipmap.decorativo_01,
             R.mipmap.decorativo_02,
             R.mipmap.decorativo_03,
             R.mipmap.decorativo_04,
             R.mipmap.decorativo_05,
             R.mipmap.decorativo_06,
             R.mipmap.decorativo_07,
             R.mipmap.decorativo_08,
             R.mipmap.decorativo_09,
             R.mipmap.decorativo_10,
             R.mipmap.decorativo_11,
             R.mipmap.decorativo_12,
             R.mipmap.evento_01,
             R.mipmap.evento_02,
             R.mipmap.evento_03,
             R.mipmap.evento_04,
             R.mipmap.evento_05,
             R.mipmap.evento_06,
             R.mipmap.evento_07,
             R.mipmap.figuras_01,
             R.mipmap.figuras_02,
             R.mipmap.figuras_03,
             R.mipmap.figuras_04,
             R.mipmap.figuras_05,
             R.mipmap.figuras_06,
             R.mipmap.figuras_07,
             R.mipmap.figuras_08,
             R.mipmap.figuras_09,
             R.mipmap.figuras_10,
             R.mipmap.figuras_11,
             R.mipmap.figuras_12,
             R.mipmap.figuras_13,
             R.mipmap.figuras_14,
             R.mipmap.figuras_15,
             R.mipmap.figuras_16,
             R.mipmap.hyb_01,
             R.mipmap.hyb_02,
             R.mipmap.hyb_03,
             R.mipmap.hyb_04,
             R.mipmap.hyb_05,
             R.mipmap.hyb_06,
             R.mipmap.hyb_07,
             R.mipmap.hyb_08,
             R.mipmap.hyb_09,
             R.mipmap.hyb_10,
             R.mipmap.globo_01,
             R.mipmap.globo_02,
             R.mipmap.globo_03,
             R.mipmap.globo_04,
             R.mipmap.globo_05,
             R.mipmap.globo_06,
             R.mipmap.globo_07,
             R.mipmap.marco_01,
             R.mipmap.marco_02,
             R.mipmap.marco_03,
             R.mipmap.marco_04,
             R.mipmap.marco_05,
             R.mipmap.marco_06,
             R.mipmap.marco_07,
             R.mipmap.marco_08,
             R.mipmap.marco_09,
             R.mipmap.marco_10,
             R.mipmap.marco_11,
             R.mipmap.marco_12

        /*    R.drawable.botella,
            R.drawable.pastel,
            R.drawable.tiquet,
            R.drawable.oso,
           R.drawable.infinito,
            R.drawable.regalo,
            R.drawable.armarcuadro,
            R.drawable.jugete,
            R.drawable.pelota,
            R.drawable.pato,
             R.drawable.sonaja,
            R.drawable.caballo,
            R.drawable.carrito,
            R.drawable.cuboabc,
            R.drawable.tren,
            R.drawable.globoaniversario,
            R.drawable.globoesnina,
            R.drawable.globoesnino,
            R.drawable.globosanvalentin,
            R.drawable.globo,
            R.drawable.globofeliz,
           R.drawable.feliz_cumpleanos,
            R.drawable.felicidades,
            R.drawable.feliz_aniversario,
            R.drawable.feliz_cumpleanos,
            R.drawable.bannerfelizcumple,
            R.drawable.banerfelizcumple2,
            R.drawable.banerfelizcumple3,
            R.drawable.banerfelzcumple4,
            R.drawable.baneraniversario,
            R.drawable.banerfelizaniver,


/*
            R.drawable.carta,
            R.drawable.marcoboda,
            R.drawable.marcofelizcumple,
            R.drawable.marcofelizcumple2,
            R.drawable.marcofelizcumpleanos,
            R.drawable.marcofelizcumpleanos2,
            R.drawable.marcomiprincesa,
            R.drawable.marcomipricnesa2,
            R.drawable.marcosanvalentin,
            R.drawable.marcosanvalentin2
*/



    };


    MenuItem elimianrsticker;


    private ScaleGestureDetector mScaleDetector;
    private MoveGestureDetector mMoveDetector;
    private ShoveGestureDetector mShoveDetector;
    private RotateGestureDetector mRotateDetector;

    private ScaleGestureDetector mtextoScaleDetector;
    private MoveGestureDetector mtextoMoveDetector;
    private ShoveGestureDetector mtextoShoveDetector;
    private RotateGestureDetector mtextoRotateDetector;

    private float mScaleFactor = 1;

private  static  int position;

private  String c;

    float cordx, cordy;
    private String DEBUG_TAG ="Elite vive debug TAG";
    private float mFocusX = 0.f;
    private float mFocusY = 0.f;
    private int mAlpha = 255;
    // private RotateGestureDetector mRotateDetector;
    public static final String TAG = "Elite vive TAG";
    /* private GestureDetector gestureDetector;
     private View.OnTouchListener gestureListener;*/
    private float this_orgX = -1, this_orgY = -1;
    private float scale_orgX = -1, scale_orgY = -1;
    private double scale_orgWidth = -1, scale_orgHeight = -1;
    // For rotating
    private float rotate_orgX = -1, rotate_orgY = -1, rotate_newX = -1, rotate_newY = -1;
    // For moving
    private float move_orgX =-1, move_orgY = -1;

    private double centerX, centerY;

    private final static int BUTTON_SIZE_DP = 30;
    private final static int SELF_SIZE_DP = 100;
    private final  static  int SELF_SIZE_DP_TEXT= 100;
    private float mRotationDegrees = 0.f;
    StickerImageView iv_sticker;
    StickerTextView tv_sticker;
    int cont;
    int conttexo;
    //AbsoluteLayout canvas;
    RelativeLayout canvas;
   private static ImageView marco;

    //private ArrayList<StickerImageView> iv_sticker  = new  ArrayList<StickerImageView>();
    StickerImageView[] Arreglostick= new StickerImageView[10000];
    StickerTextView[] Arreglossticktexto = new StickerTextView[10000];


   private static PhotoView imageView;
    private  static PhotoViewAttacher a;

    Bitmap bhaltura;

    ImageView mar;
    LinearLayout viewLogo ;


LinearLayout carrusel;
    LinearLayout barracolores;
    LinearLayout  Marcobarracolores;



Button btncolored, btncolorgreen, btncolorpink, btncolorwhite, btncoloblue, btncolorblack;

    Button Marcobtncolored, Marcobtncolorgreen, Marcobtncolorpink, Marcobtncolorwhite, Marcobtncoloblue, Marcobtnblack;

    Button cambio_colro;
    Button cambio_marco;
    Button cambio_color_mc;
    Button cambio_marco_mc;

    MenuItem menuframe;
    MenuItem menuframearrow;
    MenuItem menusticker;
//RecyclerView recyclerVie;


    private BottomNavigationView bottomNavigationView;

    int Marco_Arreglo[]={
            R.drawable.nomarco, R.drawable.marco2,   R.drawable.marco12, R.drawable.marco16, R.drawable.marco14
            //R.drawable.marco14, R.drawable.marco2, R.drawable.marco12,/* R.drawable.marco14,*/ R.drawable.marco16


    };

   private static ViewFlipper viewFlipper;
    private float initialX;
    //ImageView image;
   // private int currImage = 0;
    String TAGA = "El dato de currImage ";

    private static int CurrentItem = 0;

    private  static String palabara;

    private RelativeLayout Relativoinferior;

    private static int heigthview;
    //private static int widhtview;

    private  static int heighttoolbar;
    private  static int heigtfinalrelative;


    private  String name_database_elite= "elite_v15";
    private  Cursor fila;
    private  static Cursor fila_ch;
    private  Cursor fila_sticker;
    private  Cursor fila_no_marco;


    private  static ArrayList<Modelselectframe> list;
    RecyclerView mRecyclerView;
    adapterselectframe adapterview;

    private  static  ArrayList<String> list_sticker;

    private  static  ArrayList<String> marco_arreglo;

    private  static int col_bandera;

    private static  String fecha_actualizacion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__editarphoto);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);



        CamView = (/*AbsoluteLayout*/RelativeLayout) findViewById(R.id.editafoto);


        ViewTreeObserver vtor = CamView.getViewTreeObserver();
        vtor.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                CamView.getViewTreeObserver().removeOnPreDrawListener(this);
                int finalHeight = CamView.getHeight();
                int finalWidth = CamView.getWidth();
                //
                // CamView.getLayoutParams().height= ;//finalHeight-233;
                //CamView.setScaleY(150);
                Log.v("datos view", "Height: " + finalHeight + " Width: " + finalWidth);
                return true;
            }
        });


        float suma = 7/2;

        Log.v("LINEA 492", "LINEA 492 "+Math.ceil(suma));




        /*
        int heightview = CamView.getLayoutParams().height;
        int widthview = CamView.getLayoutParams().width;
        Log.v("heightview",  ""+heightview+" width "+widthview);*/

        //CamView.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
        viewFlipper=(ViewFlipper)findViewById(R.id.flipper);


        toolbar = (Toolbar) findViewById(R.id.toolbareditordeimagen);
        toolbar.setTitle("");
        toolbar.getBackground().setAlpha(00);
        // toolbar.displ.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.flecha_retorno_black);


        ViewTreeObserver vtore = toolbar.getViewTreeObserver();
        vtore.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                toolbar.getViewTreeObserver().removeOnPreDrawListener(this);
               int finalHeight= toolbar.getHeight();
                int finalWidth = toolbar.getWidth();

                heighttoolbar = finalHeight;

                //
                // CamView.getLayoutParams().height= ;//finalHeight-233;
                //CamView.setScaleY(150);
                Log.v("datos toolbar", "Height: " + finalHeight + " Width: " + finalWidth);
                return true;
            }
        });


        Relativoinferior = (RelativeLayout)findViewById(R.id.relativoinferior);

        ViewTreeObserver vtores = Relativoinferior.getViewTreeObserver();
        vtores.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                Relativoinferior.getViewTreeObserver().removeOnPreDrawListener(this);
                int finalheight = Relativoinferior.getHeight();
                int finalWidth = Relativoinferior.getWidth();

                heigtfinalrelative = finalheight;

                //
                // CamView.getLayoutParams().height= ;//finalHeight-233;
                //CamView.setScaleY(150);
                Log.v("datos relativeinferior", "Height: " + finalheight + " Width: " + finalWidth);
                return true;
            }
        });



        cont = 0;
        conttexo= 0;

        Arreglostick[cont] =  new StickerImageView(MainActivity_Editarphoto.this);
        iv_sticker = Arreglostick[cont];

        Arreglossticktexto[conttexo] = new StickerTextView(MainActivity_Editarphoto.this);
        tv_sticker = Arreglossticktexto[conttexo];


        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView = (SurfaceView) findViewById(R.id.cameraview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        inflater = LayoutInflater.from(getBaseContext());
        View view = inflater.inflate(R.layout.fragmenphoto, null);
        ViewGroup.LayoutParams layoutParamsControl = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        this.addContentView(view, layoutParamsControl);

        camera_image = (ImageView) findViewById(R.id.camera_image);
        mayRequestStoragePermission();

        mScaleDetector = new ScaleGestureDetector(this, new ScaleListener());
        // mMoveDetector 	= new MoveGestureDetector(getApplicationContext(), new MoveListener());
        mShoveDetector 	= new ShoveGestureDetector(getApplicationContext(), new ShoveListener());
        mShoveDetector 	= new ShoveGestureDetector(getApplicationContext(), new ShoveListener());
        mRotateDetector = new RotateGestureDetector(getApplicationContext(), new RotateListener());
        // mScaleDetector = new ScaleGestureDetector(this, new Scalelistenertexto());





        mtextoScaleDetector = new ScaleGestureDetector(this, new Scalelistenertexto());
        mtextoRotateDetector = new RotateGestureDetector(this, new RotateListenerTexto());


        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

        SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        String tiempo = String.valueOf(tiem.format(sq));


        DBHome dbHome = new DBHome(this);
        SQLiteDatabase database = dbHome.getWritableDatabase();


        String sql_tiempo = "select fecha from fecha_actualizacion_sticker ";

        fila = database.rawQuery(sql_tiempo, null);

        if (fila.moveToFirst())
        {
            fecha_actualizacion = fila.getString(0);

        }

        Log.v("LINEA 608", "LINEA 608 fecha "+fecha_actualizacion);
        System.out.println(fecha_actualizacion);

        if(fecha_actualizacion.equals("")) {


            Log.v("LINEA 614", "LINEA 614 paso por primera vez");
            String link = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/stickers-first-babysec/0";
            new JsonTaskstickerfrist().execute(link);

            String link_sticker = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/stickers-first-babysec/1";
            new JsonTasksticker().execute(link_sticker);

        }

        else{
            Log.v("LINEA 624", "LINEA 624 ya esta pasando por aqui");
            String link = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/stickers-babysec/0/"+fecha_actualizacion+"";
            new JsonTaskstickerfrist().execute(link);

    String link_sticker = "http://www.elevation.com.mx/pages/AppElite/web-services/elite/stickers-babysec/1/"+fecha_actualizacion+"";
            new JsonTasksticker().execute(link_sticker);

        }

        ContentValues values = new ContentValues();
        values.put("fecha", tiempo);
        Log.v("LINEA 633", "LINEA 663 fecha actualizacion "+tiempo);
        database.update("fecha_actualizacion_sticker", values, null, null);





        imageView = (PhotoView) findViewById(R.id.imagenviewphoto);

        Bitmap bitmap;
        Bundle bundler = getIntent().getExtras();
        final String input = bundler.getString("path_foto");
        final String inputer = bundler.getString("gridrow_galery");
        final String inputere = bundler.getString("path_getphoto");
        final String inpertu = bundler.getString("htap");
        Uri url;


        menuframe = toolbar.getMenu().findItem(R.id.detenermarcoimagen);
        menuframearrow = toolbar.getMenu().findItem(R.id.idcambiomarcocongesto);

        Marcobarracolores = (LinearLayout)findViewById(R.id.idMarcobarracolores);

        Marcobtncolored =(Button)findViewById(R.id.btn_Marco_color_rojo);
        Marcobtncolorgreen =(Button)findViewById(R.id.btn_Marco_color_verde);
        Marcobtncolorpink =(Button)findViewById(R.id.btn_Marco_color_blanco);
        Marcobtncolorwhite =(Button)findViewById(R.id.btn_Marco_color_rosa);
        Marcobtncoloblue =(Button)findViewById(R.id.btn_Marco_color_azul);
        Marcobtnblack = (Button)findViewById(R.id.btn_Marco_color_negro);

        carrusel =(LinearLayout)findViewById(R.id.idcarruceldemarcos);




        marco = (ImageView) findViewById(R.id.imagenviewmarco);
        viewLogo = (LinearLayout) findViewById(R.id.panelLogo);



        marco.setImageResource(Marco_Arreglo[0]);

     /*   try {


            URL newurl = new URL(marco_arreglo.get(3));
            Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
            marco.setImageBitmap(mIcon_val);
        }

        catch (IOException e)
        {
            Log.v("psao por aqui ","Liena 177");
        }*/

        //marco.setColorFilter(Color.WHITE);
        marco.setEnabled(false);
        final int altura = marco.getHeight();
      //  CamView.getLayoutParams().height = altura;

        ViewTreeObserver vto = marco.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                marco.getViewTreeObserver().removeOnPreDrawListener(this);
                int finalHeight = marco.getMeasuredHeight();
                int finalWidth = marco.getMeasuredWidth();
                //
                // CamView.getLayoutParams().height= ;//finalHeight-233;
                //CamView.setScaleY(150);
                Log.v("datos marco", "Height: " + finalHeight + " Width: " + finalWidth);
                return true;
            }
        });


        final int estirableh = marco.getDrawable().getIntrinsicHeight();
        final int estiableb = marco.getDrawable().getIntrinsicWidth();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;



       // int datos = toolbar.getMeasuredHeight();

        Relativoinferior.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
       int datosrelativos = Relativoinferior.getMeasuredHeight();

        toolbar.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int datostoolbar = toolbar.getMeasuredHeight();

       /* toolbar.post(new Runnable() {
            @Override
            public void run() {

                heighttoolbar = toolbar.getMeasuredHeight();//Height();
            }
        });
*/
        int sumadeheigt = datostoolbar + datosrelativos;

        int restaheigt = height - sumadeheigt;


        heigthview = restaheigt;

        Log.v("toolbarheigt", ""+datostoolbar);
        Log.v("relativeinferior", ""+datosrelativos);
        Log.v("heigt view2", ""+heigthview);
        Log.v("restaheigt", ""+restaheigt);
        Log.v("sumadeheigt", ""+sumadeheigt);






        final float porpocion= (float) estirableh/estiableb;

        int daaltura = (int) (width *  porpocion);

        Log.v("marcro", "height: "+estirableh+" Widht: "+estiableb);
        Log.v("Ancho del dis","height: "+height+" whidth: "+width);
        Log.v("propocion:", ""+porpocion);
        Log.v("Altura dsipo", ""+ daaltura);

        CamView.getLayoutParams().height = daaltura;


        /*ViewTreeObserver vglobal = CamView.getViewTreeObserver();
        vglobal.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                CamView.getViewTreeObserver().removeOnGlobalLayoutListener(this);*/


               /* new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                Log.i("tag", "This'll run 300 milliseconds later");
                            }
                        },
                        500);*/




                if(!(input == null))
                {  //url= Uri.parse(input);
                    // url = Uri.parse(input);
//            bitmap = BitmapFactory.decodeFile(input);

            /*String  der ="el buen amigo";



            Log.i(der, input);*/

                    //  bitmap = BitmapFactory.decodeFile(input);



                    Picasso.with(MainActivity_Editarphoto.this).load(new File(input)).resize(1000,0).into(imageView);




                    ViewTreeObserver vglobal = imageView.getViewTreeObserver();
                    vglobal.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                            final int alturaimagen = imageView.getMeasuredHeight(); /*imageView.getDrawable().getIntrinsicHeight();*/
                            final int anchuraimagen = imageView.getMeasuredWidth();/*imageView.getDrawable().getIntrinsicWidth();*/


                            float proporcion2 = (float)estiableb/estirableh;

                            float proporcionimagen = (float) anchuraimagen/alturaimagen;



                            Log.v("condicion", "imagen "+proporcionimagen+" marco"+proporcion2);

                            if (proporcionimagen < proporcion2) {

                                Log.v("caso 1", "caso 1");
                                float segundaanchira = proporcion2 * alturaimagen;

                                final float scalahorizontal = segundaanchira / anchuraimagen;

                                Log.v("imagen", "heigt " + alturaimagen + " width " + anchuraimagen);
                                Log.v("datos altura", "" + segundaanchira);
                                Log.v("datos altura", "" + scalahorizontal);


                                a = new PhotoViewAttacher(imageView);
                                a.setScale(scalahorizontal, true);



                            }

                            else{
                                Log.v("caso 2", "caso 2");

                                float alturareal= porpocion * anchuraimagen;

                                final float scalvertical = alturareal/alturaimagen;

                                Log.v("altura", ""+alturareal);
                                Log.v("vertical", ""+scalvertical);

                                a = new PhotoViewAttacher(imageView);
                                a.setScale(scalvertical, true);






                            }





                        }
                    });

                    // imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap,200 ,300,false));
                    // Picasso.with(MainActivity_Editarphoto.this).load(input).into(imageView);

                    //  imageView.setImageURI(url);

                    ///         imageView.setImageBitmap(bitmap);

                    //       Picasso.with(this).load(url).into(imageView);

//            Picasso.with(context).load()

                    //imageView.setImageURI(url);
                    //Picasso.with(this).load(input).into(imageView);
                    // imageView.getWidth();
                    //imageView.getHeight();
                    //  imageView.setScale(1.85f);
                    imageView.setEnabled(false);
                    // mAttacher.setScale(2.7f);
                }

                else if(!(inputer == null))
                {
                    //Bitmap bitmap = BitmapFactory.decodeFile(input);


                  //  File f = new File(inputer);



//Uri uri = Uri.parse(inpertu);

                    //imageView.setImageResource(inputer);
                   Picasso.with(MainActivity_Editarphoto.this).load(new File(inputer)).resize(1000,0).into(imageView);
                    // imageView.setImageURI(Uri.fromFile(f));

                    ViewTreeObserver vglobal = imageView.getViewTreeObserver();
                    vglobal.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                            int alturaimagen = imageView.getDrawable().getIntrinsicHeight();
                            int anchuraimagen = imageView.getDrawable().getIntrinsicWidth();

                            float proporcion2 = (float)estiableb/estirableh;

                            float proporcionimagen = (float) anchuraimagen/alturaimagen;



                            Log.v("condicion", "imagen "+proporcionimagen+" marco"+proporcion2);

                            if (proporcionimagen < proporcion2) {

                                Log.v("caso 1", "caso 1");
                                float segundaanchira = proporcion2 * alturaimagen;

                                final float scalahorizontal = segundaanchira / anchuraimagen;

                                Log.v("imagen", "heigt " + alturaimagen + " width " + anchuraimagen);
                                Log.v("datos altura", "" + segundaanchira);
                                Log.v("datos altura", "" + scalahorizontal);


                                 a = new PhotoViewAttacher(imageView);
                                a.setScale(scalahorizontal, true);

                                //  final PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);

                                //attacher.setScale(scalahorizontal, true);
                    /*attacher.setOnScaleChangeListener(new PhotoViewAttacher.OnScaleChangeListener() {
                        @Override
                        public void onScaleChange(float scaleFactor, float focusX, float focusY) {


                        }
                    });*/

//                    attacher.update();

                    /*imageView.post(new Runnable() {
                        @Override
                        public void run() {


                        }
                    });*/







                                //  Log.v("scala attacher", ""+attacher.getScale());

                            }

                            else{
                                Log.v("caso 2", "caso 2");

                                float alturareal= porpocion * anchuraimagen;

                                final float scalvertical = alturareal/alturaimagen;

                                Log.v("altura", ""+alturareal);
                                Log.v("vertical", ""+scalvertical);

                                a = new PhotoViewAttacher(imageView);
                                a.setScale(scalvertical, true);

                    /*attacher.setOnScaleChangeListener(new PhotoViewAttacher.OnScaleChangeListener() {
                        @Override
                        public void onScaleChange(float scaleFactor, float focusX, float focusY) {

                            attacher.setScale(scalvertical);
                        }
                    });*/

                                //attacher.update();

                    /*imageView.post(new Runnable() {
                        @Override
                        public void run() {

                            attacher.setScale(scalvertical);
                        }
                    });*/





                            }





                        }
                    });


                    /*new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {

                    PhotoViewAttacher a = new PhotoViewAttacher(imageView);
                                    a.setScale(2.5f, true);

                                    Log.i("tag", "This'll run 300 milliseconds later");
                                }
                            },
                            1);*/


                    Log.v("e", "galeria de fotos");

                    // mAttacher.setScale(2.85f);
                    //imageView.setImageURI();

                    //imageView.getLayoutParams().height= dpheighte; ;

                    //   imageView.getWidth();
                    //  imageView.getHeight();
                    //PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(imageView);

                    //  float dato = (float) 2.88;
                    //photoViewAttacher.setScale(dato);
                    imageView.setEnabled(false);
                    // mAttacher.setScale(2.7f);


                }


                else  if (!(inputere == null))
                {
                    // imageView = (PhotoView) findViewById(R.id.imagenviewphoto);
                    //imageView.setImageResource(inputer);

                    // final PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);



           /* Picasso.with(this).load(new File(inputere)).resize(1000,1000).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    mAttacher.update();
                }

                @Override
                public void onError() {

                }
            });*/
                    // imageView.setDrawingCacheEnabled(true);
                    // Bitmap b =imageView.getDrawingCache();

         /*    bhaltura = ((BitmapDrawable)imageView.getBackground()).getBitmap();
            int h = bhaltura.getHeight();//b.getHeight();

            Log.v("Altura", ""+h);*/
                    Picasso.with(MainActivity_Editarphoto.this).load(new File(inputere)).resize(1000, 0).into(imageView);
                    a = new PhotoViewAttacher(imageView);
                    ViewTreeObserver vglobal = imageView.getViewTreeObserver();
                    vglobal.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);


                            final int alturaimage = imageView.getMeasuredHeight(); /*imageView.getDrawable().getIntrinsicHeight();*/
                            final int anchuraimagen = imageView.getMeasuredWidth();/*imageView.getDrawable().getIntrinsicWidth();*/

                            float proporcion2 = (float)estiableb/estirableh;

                            float proporcionimagen = (float) anchuraimagen/alturaimage;



                            Log.v("condicion", "imagen "+proporcionimagen+" marco"+proporcion2);

                            if (proporcionimagen < proporcion2) {

                                Log.v("caso 1", "caso 1");
                                float segundaanchira = proporcion2 * alturaimage;

                                final float scalahorizontal = segundaanchira / anchuraimagen;

                                Log.v("imagen", "heigt " + alturaimage + " width " + anchuraimagen);
                                Log.v("datos altura", "" + segundaanchira);
                                Log.v("datos altura", "" + scalahorizontal);



                                a.setScale(scalahorizontal, true);



                            }

                            else{
                                Log.v("caso 2", "caso 2");

                                float alturareal= porpocion * anchuraimagen;

                                final float scalvertical = alturareal/alturaimage;

                                Log.v("altura", ""+alturareal);
                                Log.v("vertical", ""+scalvertical);

                                a = new PhotoViewAttacher(imageView);
                                a.setScale(scalvertical, true);






                            }





                        }
                    });

                    Log.v("scalay" , "escala"+imageView.getScale());



                    //float datos  = imageView.getMaximumScale()- imageView.getMinimumScale();
                    //mAttacher.setScale(2.7f);
                    imageView.setEnabled(false);
//c            Log.v("scalay" , "escala"+mAttacher.getScale());
                }

                else if(!(inpertu== null))
                {
                    Picasso.with(MainActivity_Editarphoto.this).load(new File(inpertu)).resize(1000, 0).into(imageView);



                    ViewTreeObserver vglobal = imageView.getViewTreeObserver();
                    vglobal.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                          //  imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                            int alturaimagen = imageView.getDrawable().getIntrinsicHeight();
                            int anchuraimagen = imageView.getDrawable().getIntrinsicWidth();

                            float proporcion2 = (float)estiableb/estirableh;

                            float proporcionimagen = (float) anchuraimagen/alturaimagen;



                            Log.v("condicion", "imagen "+proporcionimagen+" marco"+proporcion2);

                            if (proporcionimagen < proporcion2) {

                                Log.v("caso 1", "caso 1");
                                float segundaanchira = proporcion2 * alturaimagen;

                                final float scalahorizontal = segundaanchira / anchuraimagen;

                                Log.v("imagen", "heigt " + alturaimagen + " width " + anchuraimagen);
                                Log.v("datos altura", "" + segundaanchira);
                                Log.v("datos altura", "" + scalahorizontal);


                                a = new PhotoViewAttacher(imageView);
                                a.setScale(scalahorizontal, true);



                            }

                            else{
                                Log.v("caso 2", "caso 2");

                                float alturareal= porpocion * anchuraimagen;

                                final float scalvertical = alturareal/alturaimagen;

                                Log.v("altura", ""+alturareal);
                                Log.v("vertical", ""+scalvertical);

                                a = new PhotoViewAttacher(imageView);
                                a.setScale(scalvertical, true);




                            }





                        }
                    });


                    //imageView.setScale(1.854f);
                    // mAttacher.setScale(2.7f);
                    imageView.setEnabled(false);
                }







                if(imageView.isEnabled() == false) {



                    // menuframearrow.setEnabled(true);
                    imageView.setEnabled(true);
                    // imageView.setScale(1.854f);
                    carrusel.setVisibility(View.VISIBLE);




                 //   recyclerviewer();


                    // Marcobarracolores.setVisibility(View.VISIBLE);
                    //menuframe.setIcon(R.drawable.frame2);


                    Marcobtncolored.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            int color_red = Color.parseColor("#FFFFFF");
                            marco.setColorFilter(color_red);

                        }
                    });

                    Marcobtncolorgreen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int color_green = Color.parseColor("#48C9B0");
                            marco.setColorFilter(color_green);

                        }
                    });


                    Marcobtncolorwhite.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int color_white = Color.parseColor("#F7DC6F");
                            marco.setColorFilter(color_white);

                        }
                    });

                    Marcobtncolorpink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int color_pink = Color.parseColor("#F49AC2");
                            marco.setColorFilter(color_pink);
                        }
                    });

                    Marcobtncoloblue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int color_blue = Color.parseColor("#3498DB");
                            marco.setColorFilter(color_blue);
                        }
                    });

                    Marcobtnblack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            int  color_negro= Color.parseColor("#000000");
                            marco.setColorFilter(color_negro);
                        }
                    });

                    // imageView.setFocusableInTouchMode(false);
                    //iv_sticker.setEnabled(false);

                }






               // }
      //  });





      //  marco.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
       // marco.setScaleType(ImageView.ScaleType.FIT_END);


      /*  BitmapDrawable bd=(BitmapDrawable) MainActivity_Editarphoto.this.getResources().getDrawable(R.drawable.marco3);
        int tamanoheight = bd.getBitmap().getHeight()/2;
        int tamanowidth = bd.getBitmap().getWidth()/2;

        if(tamanoheight > tamanowidth) {


            //desplegable del actividad
            WindowManager w = MainActivity_Editarphoto.this.getWindowManager();
            Display d = w.getDefaultDisplay();
            Display e = w.getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            d.getMetrics(metrics);
// since SDK_INT = 1;
            int widthPixels = metrics.widthPixels;
            int heightPixels = metrics.heightPixels;
// includes window decorations (statusbar bar/menu bar)
            if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
                try {
                    widthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                    heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
                } catch (Exception ignored) {
                }
// includes window decorations (statusbar bar/menu bar)
            if (Build.VERSION.SDK_INT >= 17)
                try {
                    Point realSize = new Point();
                    Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                    widthPixels = realSize.x;
                    heightPixels = realSize.y;
                } catch (Exception ignored) {
                }

            float dpheight = heightPixels / (320 / 240f);
            float dpwidth = widthPixels / (320 / 290f);
// cconfiguracion de las poscicion establecidads
            //viwer.getLayoutParams().height = Math.round(dpheight);
            //  viwer.getLayoutParams().width = Math.round(dpwidth);

            // abs.getLayoutParams().height= Math.round(dpheight);
            //  abs.getLayoutParams().width= Math.round(dpwidth);

            float dpheighte = tamanoheight/(320 / 280f);
            float dpwidthe = tamanowidth / (320 / 240f);

            CamView.getLayoutParams().height = Math.round(dpheighte);
            CamView.getLayoutParams().width = Math.round(dpwidthe);







            //Bundle bundler = getIntent().getExtras();
            //int inpute = bundler.getInt("dato");



               // Picasso.with(this).load(R.drawable.marco3).resize(500, 0).into(marco);




               // Picasso.with(this).load(inpute).resize(500, 0).into(marco);
              //  marco.setImageResource(inpute);


            //Picasso.with(this).load(R.drawable.marco3).resize(500,0).into(marco);


        /*    marco.getLayoutParams().height = Math.round(dpheighte);
            marco.getLayoutParams().width = Math.round(dpwidthe);

*/

          /*  Bundle bundle = getIntent().getExtras();
            int inputer = bundle.getInt("keyimagen");
            imageView = (PhotoView) findViewById(R.id.imagenviewphoto);
            Picasso.with(this).load(inputer).resize(500,0).into(imageView);
            //imageView.setImageResource(inputer);
            imageView.getLayoutParams().height= marco.getLayoutParams().height;
            imageView.getLayoutParams().width=  marco.getLayoutParams().width;

*/

         //   CamView.getLayoutParams().height= marco.getLayoutParams().height;
          //  CamView.getLayoutParams().width = marco.getLayoutParams().width;

            //   imageView.getWidth();
            //  imageView.getHeight();

          //  imageView.setEnabled(false);

        //}


     /*   else{

            WindowManager w = MainActivity_Editarphoto.this.getWindowManager();
            Display d = w.getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            d.getMetrics(metrics);
// since SDK_INT = 1;
            int   widthPixels = metrics.widthPixels;
            int heightPixels = metrics.heightPixels;
// includes window decorations (statusbar bar/menu bar)
            if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
                try {
                    widthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                    heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
                } catch (Exception ignored) {
                }
// includes window decorations (statusbar bar/menu bar)
            if (Build.VERSION.SDK_INT >= 17)
                try {
                    Point realSize = new Point();
                    Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                    widthPixels = realSize.x;
                    heightPixels = realSize.y;
                } catch (Exception ignored) {
                }

            // DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
            float dpheight = heightPixels / (320 / 240f);
            //text.setText("height "+ Math.round(heightPixels) +" width "+ widthPixels );

            float dpwidth = widthPixels / (320 / 290f);
           // text.setText("height "+ Math.round(dpheight) +" width "+ Math.round(dpwidth) );

            int hh= (int)Math.round(dpheight);
            int ww = (int)Math.round(dpwidth);

            CamView.getLayoutParams().height = ww;
            CamView.getLayoutParams().width = hh;

             marco = (ImageView) findViewById(R.id.imagenviewmarco);


            marco.setImageResource(R.drawable.marco2);
            marco.setScaleType(ImageView.ScaleType.FIT_XY);
            marco.getLayoutParams().height = ww;
            marco.getLayoutParams().width = hh;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        }*/

        /*img1 = (ImageView) findViewById(R.id.imageView2);

        img1.setImageResource(R.drawable.marco_18);*/







 //       ImageView canvas = (ImageView) findViewById(R.id.editphoto);

//……

// add a stickerImage to canvas
      /*  StickerImageView iv_sticker = new StickerImageView(MainActivity.this);
        iv_sticker.setImageDrawable(getResources().getDrawable(R.drawable.c10));
        canvas.addView(iv_sticker);

// add a stickerText to canvas
        StickerTextView tv_sticker = new StickerTextView(MainActivity.this);
        tv_sticker.setText("EL BOSS ");
        tv_sticker.setBackgroundColor(33);

        canvas.addView(tv_sticker);*/
        bottomNavigationView= (BottomNavigationView)findViewById(R.id.buttonnavigatorid);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()== R.id.saveimagen){

                   // textView.setText("NO");
                    AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity_Editarphoto.this);
                    builder.setTitle("Advertencia");
                    builder.setIcon(R.drawable.warning_new);
                    builder.setMessage("¿Deseas guardar la foto con los cambios realizados?");
                    builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            TakeScreenshot();


                        }
                    });


                  builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int i) {

                          dialogInterface.dismiss();

                      }
                  });



                    builder.show();


                }

                else  if (item.getItemId()== R.id.shareimagen){

                    comartirphoto();


                }

                /*else  if (item.getItemId()== R.id.idguardar){



                }*/
                return true;
            }
        });






        /*button1 = (Button) findViewById(R.id.btncamaras);
        if(mayRequestStoragePermission())
            button1.setEnabled(true);
        else
            button1.setEnabled(false);



        button1.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {

                button1.setClickable(false);
                button1.setVisibility(View.INVISIBLE);  //<-----HIDE HERE

                //camera.takePicture(null, null, mPicture);

                TakeScreenshot();

            }

        });*/








    //    toolbar = (Toolbar)findViewById(R.id.toolbareditordeimagen);






/*cambio_colro =(Button)findViewById(R.id.btn_cambio_colores);
cambio_marco=(Button)findViewById(R.id.btn_cambio_marco);
cambio_color_mc= (Button)findViewById(R.id.btn_cambio_colores_mc);
cambio_marco_mc=(Button)findViewById(R.id.btn_cambio_marco_mc);*/


     /*   cambio_colro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                carrusel.setVisibility(View.INVISIBLE);
                Marcobarracolores.setVisibility(View.VISIBLE);
            }
        });

cambio_marco.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        carrusel.setVisibility(View.VISIBLE);
        Marcobarracolores.setVisibility(View.INVISIBLE);
    }
});

   cambio_color_mc.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           carrusel.setVisibility(View.INVISIBLE);
           Marcobarracolores.setVisibility(View.VISIBLE);


       }
   });


        cambio_marco_mc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carrusel.setVisibility(View.VISIBLE);
                Marcobarracolores.setVisibility(View.INVISIBLE);

            }
        });

*/







       // mAttacher = new PhotoViewAttacher(imageView);



       /* int alturaimagen = imageView.getDrawable().getIntrinsicHeight();
        int anchuraimagen = imageView.getDrawable().getIntrinsicWidth();

        float proporcion2 = (float)estiableb/estirableh;

        float proporcionimagen = (float) anchuraimagen/alturaimagen;



        Log.v("condicion", "imagen "+proporcionimagen+" marco"+proporcion2);

        if (proporcionimagen < proporcion2)
        {

            Log.v("caso 1", "caso 1");
            float segundaanchira= proporcion2 * alturaimagen;

            final float scalahorizontal= segundaanchira / anchuraimagen;

            Log.v("imagen", "heigt "+alturaimagen+" width "+anchuraimagen);
            Log.v("datos altura", ""+segundaanchira);
            Log.v("datos altura", ""+scalahorizontal);


            ViewTreeObserver b = CamView.getViewTreeObserver();
            b.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    //CamView.getViewTreeObserver().removeOnGlobalLayoutListener(this);


                    PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);




                    attacher.setScale(scalahorizontal);

                    Log.v("scala attacher", ""+attacher.getScale());

                }
            });



        }


        else{
            Log.v("caso 2", "caso 2");

            float alturareal= porpocion * anchuraimagen;

            final float scalvertical = alturareal/alturaimagen;

            Log.v("altura", ""+alturareal);
            Log.v("vertical", ""+scalvertical);

            ViewTreeObserver b = CamView.getViewTreeObserver();
            b.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    CamView.getViewTreeObserver().removeOnGlobalLayoutListener(this);


                    PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);




                    attacher.setScale(scalvertical);



                    Log.v("scala attacher", ""+attacher.getScale());

                }
            });

        }*/



        //mAttacher.setScale(1.85f);
        //imageView.setScale(2.0f);
        //Log.v("mattacher" , ""+mAttacher.getScale());

        /*ImageView logo= new ImageView(this);
//        int id = getResources().getIdentifier("yourpackagename:drawable/" + StringGenerated, null, null);
        logo.setImageResource(R.drawable.logo);
        int pad = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10 , getResources().getDisplayMetrics());
        logo.setPadding(pad,pad,pad,pad);
        //logo.setImageDrawable(getResources().getDrawable(R.drawable.logo));
        logo.setScaleType(ImageView.ScaleType.FIT_END);
        int w_logo = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200 , getResources().getDisplayMetrics());
        int h_logo = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50 , getResources().getDisplayMetrics());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(w_logo, h_logo, 1.0f);
        lp.gravity = Gravity.RIGHT;

        logo.setLayoutParams(lp);
//        logo.setForegroundGravity(Gravity.RIGHT);
        viewLogo.addView(logo);*/




    }



    @Override
    public boolean onTouchEvent(MotionEvent event){

      if(marco.isEnabled() == true){


          switch (event.getAction()) {
              case MotionEvent.ACTION_DOWN:

                  // Getting intitial by event action down
                  initialX = event.getX();

                  break;

              case MotionEvent.ACTION_UP:

                  // On action up the flipper will start and showing next item
                  float finalX = event.getX();
                  //   currImage=0;
                  if (initialX > finalX) {


                      // Show items are 4
                  /*  if (viewFlipper.getDisplayedChild() == 2)

                        break;*/
                      Log.d(TAG, "onTouchEvent: " +  CurrentItem + " "+event.getX());




                      if (CurrentItem == /*Marco_Arreglo.length*/marco_arreglo.size()-1) {
                          CurrentItem = 0;
                      }
                      else {
                          CurrentItem++;
                      }
                   //   marco.setImageResource(Marco_Arreglo[CurrentItem]);

                      try {


                          URL newurl = new URL(marco_arreglo.get(CurrentItem));
                          Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
                          marco.setImageBitmap(mIcon_val);
                      }

                      catch (IOException e)
                      {
                          Log.v("psao por aqui ","Liena 177");
                      }

                      final int estirableh = marco.getDrawable().getIntrinsicHeight();
                      final int estiableb = marco.getDrawable().getIntrinsicWidth();

                      DisplayMetrics displayMetrics = new DisplayMetrics();
                      getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                      int height = displayMetrics.heightPixels;
                      int width = displayMetrics.widthPixels;

                      final float porpocion= (float) estirableh/estiableb;

                      int daaltura = (int) (width *  porpocion);

                      Log.v("marcro", "height: "+estirableh+" Widht: "+estiableb);
                      Log.v("Ancho del dis","height: "+height+" whidth: "+width);
                      Log.v("propocion:", ""+porpocion);
                      Log.v("Altura dsipo", ""+ daaltura);

                      final int alturaimagen2 = imageView.getDrawable().getIntrinsicHeight();
                      final int anchuraimagen2 = imageView.getDrawable().getIntrinsicWidth();

                      Log.v("marcos arreglo", ""+ marco_arreglo.get(CurrentItem));

                   /*   DBHome dbHome = new DBHome(MainActivity_Editarphoto.this, name_database_elite, null, 1);
                      SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

                      String sqlquery = "select from sticker where id_catego"*/


                      if (marco_arreglo.get(CurrentItem).equals("http://www.elevation.com.mx/pages/AppElite/admin/assets/images/stickers_babysec/no-marco.png")/*Marco_Arreglo[CurrentItem]4*/) {

                          Log.v("getdawon", "aqui paso al mover esto");
                          //marco.setVisibility(View.GONE);

                          viewFlipper.setVisibility(View.INVISIBLE);
                          float proporcionimage = (float) alturaimagen2/anchuraimagen2;


                          float proporcionview = (float) heigthview/width;

                          float anchoimag = (float) heigthview /proporcionimage;

                          float alturaimagenes = (float) proporcionimage * width;

                          if(proporcionview < proporcionimage)
                          {
                              CamView.getLayoutParams().height = heigthview;
                              imageView.getLayoutParams().height = heigthview;
                              CamView.getLayoutParams().width = (int)anchoimag;
                          }
                          else{
                              CamView.getLayoutParams().height = (int)alturaimagenes;
                              imageView.getLayoutParams().height = (int)alturaimagenes;
                          }

                      }
                      else {


                          viewFlipper.setVisibility(View.VISIBLE);
                          CamView.getLayoutParams().height = daaltura;
                          final int alturaimagen = imageView.getDrawable().getIntrinsicHeight();
                          final int anchuraimagen = imageView.getDrawable().getIntrinsicWidth();

                          final float proporcion2 = (float) estiableb / estirableh;

                          final float proporcionimagen = (float) anchuraimagen / alturaimagen;

                          ViewTreeObserver vglobal = imageView.getViewTreeObserver();
                          vglobal.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                              @Override
                              public void onGlobalLayout() {
                                  imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);


                                  Log.v("condicion", "imagen " + proporcionimagen + " marco" + proporcion2);

                                  if (proporcionimagen < proporcion2) {

                                      Log.v("caso 1", "caso 1");
                                      float segundaanchira = proporcion2 * alturaimagen;

                                      final float scalahorizontal = segundaanchira / anchuraimagen;

                                      Log.v("imagen", "heigt " + alturaimagen + " width " + anchuraimagen);
                                      Log.v("datos altura", "" + segundaanchira);
                                      Log.v("datos altura", "" + scalahorizontal);


                                      // a = new PhotoViewAttacher(imageView);
                                      a.setScale(scalahorizontal);

                                  } else {
                                      Log.v("caso 2", "caso 2");

                                      float alturareal = porpocion * anchuraimagen;

                                      final float scalvertical = alturareal / alturaimagen;

                                      Log.v("altura", "" + alturareal);
                                      Log.v("vertical", "" + scalvertical);

                                      //a = new PhotoViewAttacher(imageView);
                                      a.setScale(scalvertical);


                                  }


                              }
                          });


                      }







                      Log.d(TAG, "on" +CurrentItem);

                      // Flip show next will show next item
                      viewFlipper.setInAnimation(this, R.anim.right_enter);
                      viewFlipper.showNext();
                  } else {

                      // If flip has no items more then it will display previous item
                   /* if (viewFlipper.getDisplayedChild() == 0)
                        break;*/
                      //currImage = currImage;

                      Log.d(TAG, ""+CurrentItem+" "+event.getX()+" "+ /*Marco_Arreglo.length*/marco_arreglo.size());

                      if (CurrentItem == 0) {
                          CurrentItem= /*Marco_Arreglo.length*/marco_arreglo.size()-1;
                      }

                      else{

                          CurrentItem--;
                      }
                     // marco.setImageResource(Marco_Arreglo[CurrentItem]);

                   try{  URL newurl = new URL(marco_arreglo.get(CurrentItem));
                      Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
                      marco.setImageBitmap(mIcon_val);
                  }

                      catch (IOException e)
              {
                  Log.v("psao por aqui ","Liena 177");
              }

                      final int estirableh = marco.getDrawable().getIntrinsicHeight();
                      final int estiableb = marco.getDrawable().getIntrinsicWidth();

                      DisplayMetrics displayMetrics = new DisplayMetrics();
                      getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                      int height = displayMetrics.heightPixels;
                      int width = displayMetrics.widthPixels;

                      final float porpocion= (float) estirableh/estiableb;

                      int daaltura = (int) (width *  porpocion);

                      Log.v("marcro", "height: "+estirableh+" Widht: "+estiableb);
                      Log.v("Ancho del dis","height: "+height+" whidth: "+width);
                      Log.v("propocion:", ""+porpocion);
                      Log.v("Altura dsipo", ""+ daaltura);


                      final int alturaimagen2 = imageView.getDrawable().getIntrinsicHeight();
                      final int anchuraimagen2 = imageView.getDrawable().getIntrinsicWidth();



                      if (/*CurrentItem== 4*/marco_arreglo.get(CurrentItem).equals("http://www.elevation.com.mx/pages/AppElite/admin/assets/images/stickers_babysec/no-marco.png")) {
                          Log.v("getdawon", "aqui paso al mover esto");
                          viewFlipper.setVisibility(View.INVISIBLE);
                          float proporcionimage = (float) alturaimagen2/anchuraimagen2;


                          float proporcionview = (float) heigthview/width;

                          float anchoimag = (float) heigthview /proporcionimage;

                          float alturaimagenes = (float) proporcionimage * width;

                          if(proporcionview<proporcionimage)
                          {
                              CamView.getLayoutParams().height = heigthview;
                              imageView.getLayoutParams().height = heigthview;
                              CamView.getLayoutParams().width = (int)anchoimag;
                          }
                          else{
                              CamView.getLayoutParams().height = (int)alturaimagenes;
                              imageView.getLayoutParams().height = (int)alturaimagenes;
                          }

                      }
                      else {

                          viewFlipper.setVisibility(View.VISIBLE);
                          CamView.getLayoutParams().height = daaltura;


                          ViewTreeObserver vglobal = imageView.getViewTreeObserver();
                          vglobal.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                              @Override
                              public void onGlobalLayout() {
                                  imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                                  int alturaimagen = imageView.getDrawable().getIntrinsicHeight();
                                  int anchuraimagen = imageView.getDrawable().getIntrinsicWidth();

                                  float proporcion2 = (float) estiableb / estirableh;

                                  float proporcionimagen = (float) anchuraimagen / alturaimagen;


                                  Log.v("condicion", "imagen " + proporcionimagen + " marco" + proporcion2);

                                  if (proporcionimagen < proporcion2) {

                                      Log.v("caso 1", "caso 1");
                                      float segundaanchira = proporcion2 * alturaimagen;

                                      final float scalahorizontal = segundaanchira / anchuraimagen;

                                      Log.v("imagen", "heigt " + alturaimagen + " width " + anchuraimagen);
                                      Log.v("datos altura", "" + segundaanchira);
                                      Log.v("datos altura", "" + scalahorizontal);


                                      //  a = new PhotoViewAttacher(imageView);
                                      a.setScale(scalahorizontal);

                                  } else {
                                      Log.v("caso 2", "caso 2");

                                      float alturareal = porpocion * anchuraimagen;

                                      final float scalvertical = alturareal / alturaimagen;

                                      Log.v("altura", "" + alturareal);
                                      Log.v("vertical", "" + scalvertical);

                                      //a = new PhotoViewAttacher(imageView);
                                      a.setScale(scalvertical);


                                  }


                              }
                          });

                      }





                      viewFlipper.setInAnimation(this, R.anim.right_in);
                      viewFlipper.showPrevious();


                  }
                  break;
          }





      }

        else {

        if( iv_sticker.requestFocus()== true)
        {
           mScaleDetector.onTouchEvent(event);
        //    mMoveDetector.onTouchEvent(event);
            mShoveDetector.onTouchEvent(event);
            mRotateDetector.onTouchEvent(event);
        }
        else if(tv_sticker.requestFocus()== true)
        {
            mtextoScaleDetector.onTouchEvent(event);
            mtextoRotateDetector.onTouchEvent(event);
        }
        else {
            mScaleDetector.onTouchEvent(event);
      //      mMoveDetector.onTouchEvent(event);
            mShoveDetector.onTouchEvent(event);
            mRotateDetector.onTouchEvent(event);

        }
      }



        /*else
        {
            mtextoScaleDetector.onTouchEvent(event);
            mtextoRotateDetector.onTouchEvent(event);
        }*/

       return  true;
    }


    public void settings(MenuItem menuItem){

        final GridView lista = (GridView)findViewById(R.id.gridviewicon);
              barracolores=(LinearLayout)findViewById(R.id.idbarracolores);
              Marcobarracolores=(LinearLayout)findViewById(R.id.idMarcobarracolores);
        carrusel =(LinearLayout)findViewById(R.id.idcarruceldemarcos);
        if(lista.getVisibility() == View.VISIBLE){

            //absprimario.setVisibility(View.VISIBLE);
            //ista.setVisibility(View.INVISIBLE);
            return;
        }
        TakeScreenshot();
        /*AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity_Editarphoto.this);
        builder.setTitle("Advertencia");
        builder.setIcon(R.mipmap.exclamationmark);
        builder.setMessage("¿Deseas guardar la foto con los cambios realizados?");
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                TakeScreenshot();


            }
        });


        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();

            }
        });



        builder.show();*/




        /*if(bottomNavigationView.getVisibility() == View.VISIBLE)
        {
            bottomNavigationView.setVisibility(View.INVISIBLE);

         if(imageView.isEnabled()== true)
         {

           //  else {
             carrusel.setVisibility(View.VISIBLE);
             //Marcobarracolores.setVisibility(View.INVISIBLE);
             //}

         }

            else {

             if(marco.isEnabled()== true)
             {
                 carrusel.setVisibility(View.INVISIBLE);
                 Marcobarracolores.setVisibility(View.VISIBLE);
             }

             else {
                // carrusel.setVisibility(View.INVISIBLE);
                 Marcobarracolores.setVisibility(View.INVISIBLE);
             }
         }


        }
        else {
            bottomNavigationView.setVisibility(View.VISIBLE);
            carrusel.setVisibility(View.INVISIBLE);
            Marcobarracolores.setVisibility(View.INVISIBLE);
            barracolores.setVisibility(View.INVISIBLE);
            //barracolores.setVisibility(View.INVISIBLE);
            //Marcobarracolores.setVisibility(View.INVISIBLE);

        }*/
     //   ImageView marco = (ImageView) findViewById(R.id.imagenviewmarco);

     /*   marco.getWidth();
        marco.getWidth();*/
        //CamView.set;
      //  TakeScreenshot();

        //desaparece el toolbar
       // toolbar.animate().translationY(-toolbar.getBottom()).setInterpolator(new AccelerateInterpolator()).start();

    }


    /*public  void sharernetwork(MenuItem  menuItem){


        comartirphoto();



    }*/


    public void eliminarsticker(MenuItem menuItem){

        final GridView lista = (GridView)findViewById(R.id.gridviewicon);

       /* if(bottomNavigationView.getVisibility()== View.VISIBLE){

            return;
        }*/

        if(lista.getVisibility() == View.VISIBLE){

            //absprimario.setVisibility(View.VISIBLE);
            //ista.setVisibility(View.INVISIBLE);
            return;
        }



//        elimianrsticker=(MenuItem)findViewById(R.id.ideliminarsticker);

        barracolores = (LinearLayout) findViewById(R.id.idbarracolores);

        toolbar = (Toolbar) findViewById(R.id.toolbareditordeimagen);
        elimianrsticker = toolbar.getMenu().findItem(R.id.ideliminarsticker);

        if(iv_sticker.requestFocus() == true){
        if(iv_sticker.getParent()!=null){
            ViewGroup myCanvas = ((ViewGroup)iv_sticker.getParent());
            myCanvas.removeView(iv_sticker);
            barracolores.setVisibility(View.INVISIBLE);
            elimianrsticker.setVisible(false);
        }

        }

         else if (tv_sticker.requestFocus() == true){
        if (tv_sticker.getParent()!=null){

            ViewGroup canvas = ((ViewGroup)tv_sticker.getParent());
            canvas.removeView(tv_sticker);
            barracolores.setVisibility(View.INVISIBLE);
            elimianrsticker.setVisible(false);
        }

        }

        /*else{

            Toast.makeText(this, "No se encuentran sticker para eliminar", Toast.LENGTH_SHORT).show();
            //elimianrsticker =
        }*/

  //      elimianrsticker.setVisible(true);



    }
//metodo que programa el gesto de movimiento de las manos
    public  void marcogesto(MenuItem menuItem){
        toolbar = (Toolbar)findViewById(R.id.toolbareditordeimagen);
        //menuframearrow = toolbar.getMenu().findItem(R.id.idcambiomarcocongesto);

        Marcobarracolores=(LinearLayout)findViewById(R.id.idMarcobarracolores);
        carrusel=(LinearLayout)findViewById(R.id.idcarruceldemarcos);



        if(marco.isEnabled()== true){
            marco.setEnabled(false);


            carrusel.setVisibility(View.VISIBLE);

            if(bottomNavigationView.getVisibility()== View.VISIBLE) {
                carrusel.setVisibility(View.INVISIBLE);
            }

           // else if(Marcobarracolores.getVisibility()== View.VISIBLE)
            //{
                Marcobarracolores.setVisibility(View.INVISIBLE);
            //}





              imageView.setEnabled(true);
        }

        else if(marco.isEnabled()== false){

            marco.setEnabled(true);
            Marcobarracolores.setVisibility(View.VISIBLE);
            carrusel.setVisibility(View.INVISIBLE);
            imageView.setEnabled(false);



        }




    }

    public  void imagenbandera (MenuItem menuItem ){

        //barracolores =(LinearLayout)findViewById(R.id.idbarracolores);

        toolbar = (Toolbar)findViewById(R.id.toolbareditordeimagen);
       menuframe = toolbar.getMenu().findItem(R.id.detenermarcoimagen);
        menuframearrow = toolbar.getMenu().findItem(R.id.idcambiomarcocongesto);
        elimianrsticker= toolbar.getMenu().findItem(R.id.ideliminarsticker);
        // menuframe.setShowAsAction(Color.GREEN);

       // menuframe.setIcon(R.drawable.crcle_black);
       // menuframe.setShowAsAction();
        //menuframe.setIntent(new Intent(this, MainActivity_Editarphoto.class));


        Marcobarracolores = (LinearLayout)findViewById(R.id.idMarcobarracolores);
        carrusel =(LinearLayout)findViewById(R.id.idcarruceldemarcos);
        barracolores=(LinearLayout)findViewById(R.id.idbarracolores);
        Marcobtncolored =(Button)findViewById(R.id.btn_Marco_color_rojo);
        Marcobtncolorgreen =(Button)findViewById(R.id.btn_Marco_color_verde);
        Marcobtncolorpink =(Button)findViewById(R.id.btn_Marco_color_blanco);
        Marcobtncolorwhite =(Button)findViewById(R.id.btn_Marco_color_rosa);
        Marcobtncoloblue =(Button)findViewById(R.id.btn_Marco_color_azul);
        Marcobtnblack = (Button)findViewById(R.id.btn_Marco_color_negro);

        if (marco.isEnabled()== true)
        { menuframe.setIcon(R.mipmap.iconmarco);
            menuframearrow.setVisible(false);
            marco.setEnabled(false);
            carrusel.setVisibility(View.INVISIBLE);
            Marcobarracolores.setVisibility(View.INVISIBLE);

        }

        else if(imageView.isEnabled() == false) {

            imageView.setEnabled(true);
            barracolores.setVisibility(View.INVISIBLE);


            //Marcobarracolores.setVisibility(View.VISIBLE);

            if(bottomNavigationView.getVisibility() == View.VISIBLE)
            {
                carrusel.setVisibility(View.INVISIBLE);
            }
            else {

                carrusel.setVisibility(View.VISIBLE);
            }


            menuframe.setIcon(R.mipmap.iconmarcocambio);
            elimianrsticker.setVisible(false);
            menuframearrow.setVisible(true);
            Marcobarracolores.setVisibility(View.INVISIBLE);
            Marcobtncolored.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int color_red = Color.parseColor("#FFFFFF");
                    marco.setColorFilter(color_red);

                }
            });

            Marcobtncolorgreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int color_green = Color.parseColor("#48C9B0");
                    marco.setColorFilter(color_green);

                }
            });


            Marcobtncolorwhite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int color_white = Color.parseColor("#F7DC6F");
                    marco.setColorFilter(color_white);

                }
            });

            Marcobtncolorpink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int color_pink = Color.parseColor("#F49AC2");
                    marco.setColorFilter(color_pink);
                }
            });

            Marcobtncoloblue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int color_blue = Color.parseColor("#3498DB");
                    marco.setColorFilter(color_blue);
                }
            });

            Marcobtnblack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int  color_negro= Color.parseColor("#000000");
                            marco.setColorFilter(color_negro);
                }
            });

           // imageView.setFocusableInTouchMode(false);
            //iv_sticker.setEnabled(false);

        }




        else if(imageView.isEnabled() == true){



            imageView.setEnabled(false);
            //imageView.setZoomable(false);
        //    imageView.setFocusableInTouchMode(true);
           // iv_sticker.setEnabled(true);



            //barracolores.setVisibility(View.VISIBLE);

           // }
            menuframe.setIcon(R.mipmap.iconmarco);
            menuframearrow.setVisible(false);
            marco.setEnabled(false);
            carrusel.setVisibility(View.INVISIBLE);
            Marcobarracolores.setVisibility(View.INVISIBLE);
        }





    }

    public void icontext(MenuItem menuItem){

        final GridView lista = (GridView)findViewById(R.id.gridviewicon);


        if(bottomNavigationView.getVisibility()== View.VISIBLE){

            return;
        }

        if(lista.getVisibility() == View.VISIBLE){

            //absprimario.setVisibility(View.VISIBLE);
            //ista.setVisibility(View.INVISIBLE);
            return;
        }

        iconeditext =(EditText)findViewById(R.id.editTexticontext);

        iconeditext.setVisibility(View.VISIBLE);
        iconeditext.setEnabled(true);

        //imageView.setEnabled(false);
        iconeditext.requestFocus();

       // imageView.setZoomable(false);




        imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_NOT_ALWAYS);






        String font = "fonts/SIXTY.TTF";
        String font1 = "fonts/contrast.ttf";
        String font2 = "fonts/leadcoat.ttf";
        String font3 = "fonts/stocky.ttf";
        String font4 = "fonts/ASMAN.TTF";
        //String font5 = "fonts/SIXTY.TTF";
        Typeface face= Typeface.createFromAsset(MainActivity_Editarphoto.this.getAssets(),font4);
      //  iconeditext.setTypeface(face);
        iconeditext.setTextSize(40);

        iconeditext.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                if(iconeditext.getText().toString().equals("")){
                    iconeditext.setVisibility(View.INVISIBLE);
                    iconeditext.setEnabled(false);
                    imm.hideSoftInputFromWindow(iconeditext.getWindowToken(), 0);
                    return;
                }

                else {



                    barracolores = (LinearLayout)findViewById(R.id.idbarracolores);

                     palabara = iconeditext.getText().toString();
                    final RelativeLayout canvas = (RelativeLayout) findViewById(R.id.editafoto);
                   // final AbsoluteLayout canvas = (AbsoluteLayout) findViewById(R.id.editafoto);

                    toolbar = (Toolbar) findViewById(R.id.toolbareditordeimagen);
                    elimianrsticker = toolbar.getMenu().findItem(R.id.ideliminarsticker);

                    menuframearrow = toolbar.getMenu().findItem(R.id.idcambiomarcocongesto);

                    Arreglossticktexto[conttexo] = new StickerTextView(MainActivity_Editarphoto.this);

                    /*TextView img = new TextView(getApplicationContext());
                    img.setText(palabara);
                    canvas.addView(img);*/
                    Arreglossticktexto[conttexo].setText(palabara);
//                    Arreglossticktexto[conttexo].setBackgroundColor(Color.BLUE);
                    //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                    //params.addRule(RelativeLayout.CENTER_VERTICAL);
                    //Arreglossticktexto[conttexo].setLayoutParams(params);

                    final StickerTextView sticktexto = Arreglossticktexto[conttexo];

                    //tv_sticker = new StickerTextView(MainActivity_Editarphoto.this);


                    Arreglossticktexto[conttexo].setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {


                            if(view.getTag().equals("DraggableViewGroup")){
                                switch (motionEvent.getAction()){
                                    case MotionEvent.ACTION_DOWN:
                                        Log.v(TAG, "toque");
                                        // requestFocus();
                                        move_orgX = motionEvent.getRawX();
                                        move_orgY =  motionEvent.getRawY();
                                        Log.v(TAG, "movimiento, "+conttexo+" "+ Aplicafocotexto(sticktexto));
                                        tv_sticker.setFocusableInTouchMode(true);
                                        iv_sticker.setFocusableInTouchMode(false);

                                        //barracolores = (LinearLayout)findViewById(R.id.idbarracolores);
                                     //  barracolores.setVisibility(View.VISIBLE);

                                        if(Marcobarracolores.getVisibility() == View.VISIBLE)
                                        {
                                            barracolores.setVisibility(View.INVISIBLE);
                                        }

                                        else if(carrusel.getVisibility() == View.VISIBLE)
                                        {
                                            barracolores.setVisibility(View.INVISIBLE);


                                        }

                                        else if(bottomNavigationView.getVisibility() == View.VISIBLE){

                                            barracolores.setVisibility(View.INVISIBLE);

                                        }

                                        else {
                                            barracolores.setVisibility(View.VISIBLE);

                                        }


                                      //  if()

                                        if(menuframearrow.isVisible()== true) {
                                            elimianrsticker.setVisible(false);
                                        }
                                        else {
                                            elimianrsticker.setVisible(true);
                                        }

                                        //barracolores = (LinearLayout)findViewById(R.id.idbarracolores);

                                        btncolored = (Button) findViewById(R.id.btn_color_rojo);
                                        btncolorgreen = (Button) findViewById(R.id.btn_color_verde);
                                        btncolorwhite = (Button) findViewById(R.id.btn_color_blanco);
                                        btncolorpink = (Button) findViewById(R.id.btn_color_rosa);
                                        btncoloblue = (Button) findViewById(R.id.btn_color_azul);
                                        btncolorblack =(Button)findViewById(R.id.btn_color_negro);
                                        btncolored.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                int color_texto_red = Color.parseColor("#FFFFFF");
                                                tv_sticker.setTextColor(color_texto_red);

                                            }
                                        });

                                        btncolorgreen.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                int color_texto_verde = Color.parseColor("#48C9B0");
                                                tv_sticker.setTextColor(color_texto_verde);
                                            }
                                        });

                                        btncolorwhite.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                int color_texto_naranjado = Color.parseColor("#F49AC2");
                                                tv_sticker.setTextColor(color_texto_naranjado);

                                            }

                                             });


                                        btncolorpink.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                int color_texto_rosa = Color.parseColor("#F7DC6F");
                                                tv_sticker.setTextColor(color_texto_rosa);
                                            }
                                        });

                                        btncoloblue.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                int color_texto_azul = Color.parseColor("#3498DB");
                                                tv_sticker.setTextColor(color_texto_azul);



                                            }
                                        });
                                        btncolorblack.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                int color_texto_negro = Color.parseColor("#000000");
                                                tv_sticker.setTextColor(color_texto_negro);
                                            }
                                        });



                                        break;
                                    case MotionEvent.ACTION_MOVE:

                                        float offsetX = motionEvent.getRawX() - move_orgX;
                                        float offsetY = motionEvent.getRawY() - move_orgY;

                                        tv_sticker.setX(tv_sticker.getX()+offsetX);
                                        tv_sticker.setY(tv_sticker.getY() + offsetY);

                                        move_orgX = motionEvent.getRawX();
                                        move_orgY = motionEvent.getRawY();

                                        if( iv_sticker.requestFocus()== true)
                                        {
                                            mScaleDetector.onTouchEvent(motionEvent);
                                            //    mMoveDetector.onTouchEvent(event);
                                            mShoveDetector.onTouchEvent(motionEvent);
                                            mRotateDetector.onTouchEvent(motionEvent);
                                        }
                                        else if(tv_sticker.requestFocus()== true)
                                        {
                                            mtextoScaleDetector.onTouchEvent(motionEvent);
                                            mtextoRotateDetector.onTouchEvent(motionEvent);
                                        }
                                        else {
                                            mScaleDetector.onTouchEvent(motionEvent);
                                            //      mMoveDetector.onTouchEvent(event);
                                            mShoveDetector.onTouchEvent(motionEvent);
                                            mRotateDetector.onTouchEvent(motionEvent);

                                        }

                                        break;

                                    case MotionEvent.ACTION_UP:
                                        Log.v(TAG, "termino del movimiento");
                                        break;

                                }



                            }

                            return  true;
                        }
                    });




                    tv_sticker =  Arreglossticktexto[conttexo];
                    tv_sticker.setScaleX(2);
                    tv_sticker.setScaleY(2);

                    //tv_sticker.setText(palabara);




                    //tv_sticker.setBackgroundColor(33);

                    canvas.addView(tv_sticker);

                  //  imageView.setZoomable(true);

                   // elimianrsticker.setVisible(true);


                    if(Marcobarracolores.getVisibility() == View.VISIBLE)
                    {
                        barracolores.setVisibility(View.INVISIBLE);
                    }

                    else if(carrusel.getVisibility() == View.VISIBLE)
                    {
                        barracolores.setVisibility(View.INVISIBLE);


                    }

                    else {
                        barracolores.setVisibility(View.VISIBLE);

                    }



                    btncolored = (Button) findViewById(R.id.btn_color_rojo);
                    btncolorgreen = (Button) findViewById(R.id.btn_color_verde);
                    btncolorwhite = (Button) findViewById(R.id.btn_color_blanco);
                    btncolorpink = (Button) findViewById(R.id.btn_color_rosa);
                    btncoloblue = (Button) findViewById(R.id.btn_color_azul);
                    btncolorblack = (Button)findViewById(R.id.btn_color_negro);



                    btncolored.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            int color_rojo = Color.parseColor("#FFFFFF");
                            tv_sticker.setTextColor(color_rojo);




                        }
                    });


                    btncolorgreen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int color_verde = Color.parseColor("#48C9B0");
                            tv_sticker.setTextColor(color_verde);
                        }
                    });

                    btncolorwhite.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int color_naranjado = Color.parseColor("#F49AC2");

                            tv_sticker.setTextColor(color_naranjado);
                        }
                    });


                    btncolorpink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int color_rosa = Color.parseColor("#F7DC6F");
                            tv_sticker.setTextColor(color_rosa);

                        }
                    });


                    btncoloblue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int color_azul = Color.parseColor("#3498DB");

                            tv_sticker.setTextColor(color_azul);
                        }
                    });


                    btncolorblack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            int color_texto_negro = Color.parseColor("000000");
                            tv_sticker.setTextColor(color_texto_negro);
                        }
                    });






                    conttexo++;
                    iconeditext.setText("");
                    iconeditext.setVisibility(View.INVISIBLE);
                    iconeditext.setEnabled(false);
                    imm.hideSoftInputFromWindow(iconeditext.getWindowToken(), 0);
                }
            }
        });

    }



    public  void iconsticker (final MenuItem menuItem){



        Log.v("LINEA 2730" , "LINEA 2730 list sticker "+ list_sticker);

        if(bottomNavigationView.getVisibility()== View.VISIBLE){

            return;
        }

        toolbar = (Toolbar)findViewById(R.id.toolbareditordeimagen);
        menusticker = toolbar.getMenu().findItem(sticker);
        menusticker.setIcon(R.mipmap.iconstickercambio);


        final GridView lista = (GridView)findViewById(R.id.gridviewicon);
        adapter = new Adaptergridrow(MainActivity_Editarphoto.this, list_sticker);
        lista.setAdapter(adapter);
        //absprimario =(AbsoluteLayout)findViewById(R.id.absoluteprimario);

       /* barracolores = (LinearLayout)findViewById(R.id.idbarracolores);

        barracolores.setVisibility(View.VISIBLE);*/

        marco = (ImageView) findViewById(R.id.imagenviewmarco);
        if(lista.getVisibility() == View.INVISIBLE)
        {
           // absprimario.setVisibility(View.INVISIBLE);
            lista.setVisibility(View.VISIBLE);
            lista.setBackgroundColor(Color.argb(160,138,152,225));
             //lista.getBackground().setAlpha(255);
             lista.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
//            toolbar.getBackground().setAlpha(255);
            //lista.setColumnWidth(100);
            //toolbar.setVisibility(View.INVISIBLE);
           // lista.getBackground().setAlpha(30);

             //marco.setVisibility(View.INVISIBLE);
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {


                     //canvas = (AbsoluteLayout) findViewById(R.id.editafoto);
                    canvas = (RelativeLayout) findViewById(R.id.editafoto);

                    barracolores = (LinearLayout)findViewById(R.id.idbarracolores);
                    toolbar = (Toolbar) findViewById(R.id.toolbareditordeimagen);
                    elimianrsticker = toolbar.getMenu().findItem(R.id.ideliminarsticker);


                    menuframearrow = toolbar.getMenu().findItem(R.id.idcambiomarcocongesto);


                 cont++;




                    // iv_sticker = new StickerImageView(MainActivity_Editarphoto.this);
                    //iv_sticker.setImageDrawable(getResources().getDrawable(R.drawable.pastel));
                    //iv_sticker.setImageResource(imagen[i]);

                    Arreglostick[cont] =  new StickerImageView(MainActivity_Editarphoto.this);

                    //Arreglostick[cont].setImageResource(imagen_sticker[i]);

                  /*  try {
                        URL newurl = new URL(list_sticker.get(i));
                        Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
                        Arreglostick[cont].setImageBitmap(mIcon_val);
                    }

                    catch (IOException e)
                    {

                    }*/

                    Arreglostick[cont].setPicasso(list_sticker.get(i));

                   // Picasso.with(MainActivity_Editarphoto.this).load(imagen_sticker[i]).into(Arreglostick[cont]);

                    DBHome dbHome = new DBHome(MainActivity_Editarphoto.this);

                    SQLiteDatabase database = dbHome.getWritableDatabase();

                    String sql = "select  color_filter from sticker where sticker ='"+list_sticker.get(i)+"'";


                    fila = database.rawQuery(sql, null);

                    Log.v("LINEA 2818","LINEA 2866 col_filt "+col_bandera);

                    if(fila.moveToFirst())
                    {
                        col_bandera = fila.getInt(0);
                    }


                    Log.v("LINEA 2826","LINEA 2826 col_filt "+col_bandera);


                    final StickerImageView StickerActual = Arreglostick [cont];



                    Arreglostick [cont].setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {

                            if(view.getTag().equals("DraggableViewGroup")){
                                switch (motionEvent.getAction()){
                                    case MotionEvent.ACTION_DOWN:
                                        Log.v(TAG, "toque");
                                        // requestFocus();
                                        move_orgX = motionEvent.getRawX();
                                        move_orgY =  motionEvent.getRawY();
                                        Log.v(TAG, "movimiento, "+cont+" "+ Aplicafoco(StickerActual));
                                       /* if(imageView.isEnabled()== true)
                                        {
                                            iv_sticker.setEnabled(false);
                                        }

                                        else if (imageView.isEnabled()== false)

                                        {
                                            iv_sticker.setEnabled(true);

                                        }*/
                                            //movimiento del sticker se controla mediante el modotouch el cual le da el foco de movimiento
                                        DBHome dbHome = new DBHome(MainActivity_Editarphoto.this);

                                        SQLiteDatabase database = dbHome.getWritableDatabase();

                                        String sql = "select  color_filter from sticker where sticker ='"+list_sticker.get(i)+"'";


                                        fila = database.rawQuery(sql, null);

                                        if(fila.moveToFirst())
                                        {
                                            col_bandera = fila.getInt(0);
                                        }


                                        iv_sticker.setFocusableInTouchMode(true);
                                        tv_sticker.setFocusableInTouchMode(false);

                                        Log.v("LINEA 2866","LINEA 2866 col_filt "+col_bandera);

                                        if(carrusel.getVisibility() == View.VISIBLE)
                                        {barracolores.setVisibility(View.INVISIBLE);

                                        }

                                        else if(Marcobarracolores.getVisibility() == View.VISIBLE)
                                        {
                                            barracolores.setVisibility(View.INVISIBLE);
                                        }





                                        else if (col_bandera == 0)
                                        {
                                            barracolores.setVisibility(View.INVISIBLE);

                                        }

                                        else if (bottomNavigationView.getVisibility()== View.VISIBLE)
                                        {
                                            barracolores.setVisibility(View.INVISIBLE);
                                        }
                                        else
                                        {
                                            barracolores.setVisibility(View.VISIBLE);
                                        }


                                        if(menuframearrow.isVisible()== true) {
                                            elimianrsticker.setVisible(false);
                                        }
                                        else
                                        {
                                            elimianrsticker.setVisible(true);
                                        }

                                    /*    if(iv_sticker.isEnabled() == true) {
                                            iv_sticker.setEnabled(false);
                                        }

                                        else if(iv_sticker.isEnabled()== false)
                                        {
                                            iv_sticker.setEnabled(true);
                                        }*/



                                            btncolored = (Button) findViewById(R.id.btn_color_rojo);
                                            btncolorgreen = (Button) findViewById(R.id.btn_color_verde);
                                            btncolorpink = (Button) findViewById(R.id.btn_color_rosa);
                                            btncolorwhite = (Button) findViewById(R.id.btn_color_blanco);
                                            btncoloblue = (Button) findViewById(R.id.btn_color_azul);

                                            btncolorblack =(Button)findViewById(R.id.btn_color_negro);

                                            btncolored.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    int color_red = Color.parseColor("#FFFFFF");

                                                    iv_sticker.setColorFilter(color_red);
                                                }
                                            });

                                            btncolorgreen.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {

                                                    int color_green = Color.parseColor("#48C9B0");

                                                    iv_sticker.setColorFilter(color_green);
                                                }
                                            });

                                            btncolorwhite.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    int color_orange = Color.parseColor("#F49AC2");

                                                    iv_sticker.setColorFilter(color_orange);
                                                }
                                            });

                                            btncolorpink.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    int color_pink = Color.parseColor("#F7DC6F");

                                                    iv_sticker.setColorFilter(color_pink);
                                                }
                                            });


                                            btncoloblue.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    int color_blue = Color.parseColor("#3498DB");
                                                    iv_sticker.setColorFilter(color_blue);

                                                }
                                            });

                                        btncolorblack.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                int color_negro = Color.parseColor("#000000");
                                                iv_sticker.setColorFilter(color_negro);
                                            }
                                        });

                                    //    elimianrsticker.setVisible(true);

                                        mScaleDetector.onTouchEvent(motionEvent);
                                        //      mMoveDetector.onTouchEvent(event);
                                        mShoveDetector.onTouchEvent(motionEvent);
                                        mRotateDetector.onTouchEvent(motionEvent);


                                        break;
                                    case MotionEvent.ACTION_MOVE:

                                  //     mScaleDetector.onTouchEvent(motionEvent);
  //                                      mScaleDetector.onTouchEvent(motionEvent);
//                                        mMoveDetector.onTouchEvent(motionEvent);
    //                                    mShoveDetector.onTouchEvent(motionEvent);
    //                                   mRotateDetector.onTouchEvent(motionEvent);

                                       float offsetX = motionEvent.getRawX() - move_orgX;
                                        float offsetY = motionEvent.getRawY() - move_orgY;

                                        iv_sticker.setX(iv_sticker.getX()+offsetX);
                                        iv_sticker.setY(iv_sticker.getY() + offsetY);

                                        move_orgX = motionEvent.getRawX();
                                        move_orgY = motionEvent.getRawY();

                                        if( iv_sticker.requestFocus()== true)
                                        {
                                            mScaleDetector.onTouchEvent(motionEvent);
                                            //    mMoveDetector.onTouchEvent(event);
                                            mShoveDetector.onTouchEvent(motionEvent);
                                            mRotateDetector.onTouchEvent(motionEvent);
                                        }
                                        else if(tv_sticker.requestFocus()== true)
                                        {
                                            mtextoScaleDetector.onTouchEvent(motionEvent);
                                            mtextoRotateDetector.onTouchEvent(motionEvent);
                                        }
                                        else {
                                            mScaleDetector.onTouchEvent(motionEvent);
                                            //      mMoveDetector.onTouchEvent(event);
                                            mShoveDetector.onTouchEvent(motionEvent);
                                            mRotateDetector.onTouchEvent(motionEvent);

                                        }

                                           break;

                                    case MotionEvent.ACTION_UP:
                                        Log.v(TAG, "Termina el evento");

                                        break;

                                }

                            }
                            return true;
                        }
                    });


                    iv_sticker = Arreglostick[cont];
                    canvas.addView(iv_sticker);
                    //elimianrsticker.setVisible(true);



                 //   elimianrsticker.setVisible(true);



                    if(carrusel.getVisibility()== View.VISIBLE){
                        barracolores.setVisibility(View.INVISIBLE);
                    }

                    else if (Marcobarracolores.getVisibility() == View.VISIBLE)
                    {
                        barracolores.setVisibility(View.INVISIBLE);
                    }

                    else if(col_bandera == 0)
                    {
                        barracolores.setVisibility(View.INVISIBLE);
                    }

                    else {

                        barracolores.setVisibility(View.VISIBLE);
                    }


                    btncolored = (Button) findViewById(R.id.btn_color_rojo);
                    btncolorgreen = (Button) findViewById(R.id.btn_color_verde);
                    btncolorpink = (Button) findViewById(R.id.btn_color_rosa);
                    btncolorwhite = (Button) findViewById(R.id.btn_color_blanco);
                    btncoloblue = (Button) findViewById(R.id.btn_color_azul);
                    btncolorblack =(Button)findViewById(R.id.btn_color_negro);


                    btncolored.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int color_red = Color.parseColor("#FFFFFF");

                            iv_sticker.setColorFilter(color_red);
                        }
                    });

                    btncolorgreen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            int color_green = Color.parseColor("#48C9B0");

                            iv_sticker.setColorFilter(color_green);
                        }
                    });

                    btncolorwhite.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int color_orange = Color.parseColor("#F49AC2");

                            iv_sticker.setColorFilter(color_orange);
                        }
                    });

                    btncolorpink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int color_pink = Color.parseColor("#F7DC6F");

                            iv_sticker.setColorFilter(color_pink);
                        }
                    });


                    btncoloblue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int color_blue = Color.parseColor("#3498DB");
                            iv_sticker.setColorFilter(color_blue);

                        }
                    });

                    btncolorblack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int color_negro = Color.parseColor("#000000");
                            iv_sticker.setColorFilter(color_negro);
                        }
                    });







                    toolbar.getBackground().setAlpha(00);
                    //marco.setVisibility(View.VISIBLE);
                  //  absprimario.setVisibility(View.VISIBLE);
                    lista.setVisibility(View.INVISIBLE);
                    menusticker.setIcon(R.mipmap.iconstcker);
                    // toolbar.setVisibility(View.VISIBLE);

                }
            });

        }

        else if(lista.getVisibility() == View.VISIBLE){

       //     absprimario.setVisibility(View.VISIBLE);
            lista.setVisibility(View.INVISIBLE);
            toolbar.getBackground().setAlpha(00);
            menusticker.setIcon(R.mipmap.iconstcker);
        }


    }


    public StickerImageView Aplicafoco (StickerImageView sticker){

        iv_sticker = sticker;

        return iv_sticker;
    }

    public StickerTextView Aplicafocotexto (StickerTextView stickertexto){

        tv_sticker = stickertexto;

        return  tv_sticker;
    }




    private class MoveListener extends MoveGestureDetector.SimpleOnMoveGestureListener {
        @Override
        public boolean onMove(MoveGestureDetector detector) {
            PointF d = detector.getFocusDelta();

            // move_orgX = d.x;
            //move_orgY = d.y;

            // iv_sticker.requestFocus();



            mFocusX += d.x;
            mFocusY += d.y;

            //move_orgX = mFocusX; //event.getRawX();
            //move_orgY = mFocusY;//event.getRawY();

            float offsetX = mFocusX - move_orgX;
            float offsetY = mFocusY - move_orgY;

            iv_sticker.setX(iv_sticker.getX()+offsetX);
            iv_sticker.setY(iv_sticker.getY() + offsetY);
            move_orgX =  mFocusX;// event.getRawX();
            move_orgY = mFocusY;//event.getRawY();



            // mFocusX = detector.getFocusX();
            // mFocusY = detector.getFocusY();
            return true;
        }
    }

    private class ShoveListener extends ShoveGestureDetector.SimpleOnShoveGestureListener {
        @Override
        public boolean onShove(ShoveGestureDetector detector) {
            mAlpha += detector.getShovePixelsDelta();
            if (mAlpha > 255)
                mAlpha = 255;
            else if (mAlpha < 0)
                mAlpha = 0;

            return true;
        }
    }

    private class RotateListener extends RotateGestureDetector.SimpleOnRotateGestureListener {
        @Override
        public boolean onRotate(RotateGestureDetector detector) {
            mRotationDegrees -= detector.getRotationDegreesDelta();

            iv_sticker.setRotation(mRotationDegrees);


            return true;
        }
    }


    private  class  RotateListenerTexto extends RotateGestureDetector.SimpleOnRotateGestureListener{
        @Override
        public boolean onRotate(RotateGestureDetector detector) {
            mRotationDegrees -= detector.getRotationDegreesDelta();

            tv_sticker.setRotation(mRotationDegrees);
            return true;
        }

    }



    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return super.onScaleBegin(detector);
                 }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            float cambiox = detector.getCurrentSpanX();
            float span = detector.getCurrentSpan();
            // iv_sticker.requestFocus();

            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(50, Math.min(mScaleFactor, 5000));
            cordx = mScaleFactor; //detector.getPreviousSpanX();//getFocusX();
            cordy = mScaleFactor;  //detector.getPreviousSpanY();//.getFocusY();
            Log.v(DEBUG_TAG, "spanx" + cordx + "spany " + cordy);
            double length1 = getLength(centerX, centerY, scale_orgX, scale_orgY);
           double length2 = getLength(centerX, centerY, cordx, cordy);
           //Tamaño del Cuadrito que tiene la imagen



            int size = convertDpToPixel(SELF_SIZE_DP, MainActivity_Editarphoto.this);
            //   Log.v(TAG, iv_sticker.getLayoutParams().width + "> " + (size / 2));
            //  Log.v(TAG, iv_sticker.getLayoutParams().height + "> " + (size / 2));
            if (length2 > length1) {
                //scale up
                double offsetX = Math.abs(cordx - scale_orgX);
                double offsetY = Math.abs(cordy - scale_orgY);
                double offset = Math.max(offsetX, offsetY);
                offset = Math.round(offset);
                iv_sticker.getLayoutParams().width += offset;
                iv_sticker.getLayoutParams().height += offset;
                onScaling(true);
                //DraggableViewGroup.this.setX((float) (getX() - offset / 2));
                //DraggableViewGroup.this.setY((float) (getY() - offset / 2));
                //ejecucion de la longitud
            } else if (length2 < length1 && iv_sticker.getLayoutParams().width > size / 2
                    && iv_sticker.getLayoutParams().height > size / 2) {
                //scale down

                //mScaleFactor = Math.min(1, Math.max(mScaleFactor, 50));
                double offsetX = Math.abs(cordx - scale_orgX);
                double offsetY = Math.abs(cordy - scale_orgY);
                double offset = Math.max(offsetX, offsetY);
                offset = Math.round(offset);
                iv_sticker.getLayoutParams().width -= offset;
                iv_sticker.getLayoutParams().height -= offset;
                onScaling(false);
            }


            scale_orgX = cordx;
            scale_orgY = cordy;
            iv_sticker.requestLayout();
///            canvas.invalidate();
            return true;

        }

    }


    private  class Scalelistenertexto  extends ScaleGestureDetector.SimpleOnScaleGestureListener{


        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return super.onScaleBegin(detector);
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            float cambiox = detector.getCurrentSpanX();
            float span = detector.getCurrentSpan();
            // iv_sticker.requestFocus();

            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(50, Math.min(mScaleFactor, 5000));
            cordx = mScaleFactor; //detector.getPreviousSpanX();//getFocusX();
            cordy = mScaleFactor;  //detector.getPreviousSpanY();//.getFocusY();
            Log.v(DEBUG_TAG, "spanx" + cordx + "spany " + cordy);
            double length1 = getLength(centerX, centerY, scale_orgX, scale_orgY);
            double length2 = getLength(centerX, centerY, cordx, cordy);
            //Tamaño del Cuadrito que tiene la imagen
            int size = convertDpToPixel(SELF_SIZE_DP_TEXT, MainActivity_Editarphoto.this);
            //   Log.v(TAG, iv_sticker.getLayoutParams().width + "> " + (size / 2));
            //  Log.v(TAG, iv_sticker.getLayoutParams().height + "> " + (size / 2));
            if (length2 > length1) {
                //scale up
                double offsetX = Math.abs(cordx - scale_orgX);
                double offsetY = Math.abs(cordy - scale_orgY);
                double offset = Math.max(offsetX, offsetY);
                offset = Math.round(offset);
                tv_sticker.getLayoutParams().width += offset;
                tv_sticker.getLayoutParams().height += offset;
                onScaling(true);
                //DraggableViewGroup.this.setX((float) (getX() - offset / 2));
                //DraggableViewGroup.this.setY((float) (getY() - offset / 2));

            } else if (length2 < length1 && tv_sticker.getLayoutParams().width > size / 2
                    && tv_sticker.getLayoutParams().height > size / 2) {
                //scale down

                //mScaleFactor = Math.min(1, Math.max(mScaleFactor, 50));
                double offsetX = Math.abs(cordx - scale_orgX);
                double offsetY = Math.abs(cordy - scale_orgY);
                double offset = Math.max(offsetX, offsetY);
                offset = Math.round(offset);
                tv_sticker.getLayoutParams().width -= offset;
                tv_sticker.getLayoutParams().height -= offset;
                onScaling(false);
            }


            scale_orgX = cordx;
            scale_orgY = cordy;
            tv_sticker.requestLayout();
///            canvas.invalidate();
            return true;

        }


    }

   /* public  void recyclerviewer(){



        DBHome dbHome = new DBHome(MainActivity_Editarphoto.this, name_database_elite, null, 1);

        SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();


        list= new ArrayList<>();
        String query = "select id_categoria_sticker, sticker from sticker where id_categoria_sticker=10 ";

        fila_ch = sqLiteDatabase.rawQuery(query, null);



        while (fila_ch.moveToNext())

        {

            int id_categoria_sticker = Integer.parseInt(fila_ch.getString(0));
            String stiker = fila_ch.getString(1);

            Log.v("LINEA 4039", "LINEA 4039 id_categoria "+id_categoria_sticker+" sticker " + stiker);

            Modelselectframe model = new Modelselectframe(Modelselectframe.IMAGE_TYPE, stiker);


            list.add(model);


            //  Log.v("LINEA 4037", "LINEA 4037 imagen_sticker " + imagen_sticker);

        }


        Log.v("LINEA 3315" , "LINEA 3315 HAIKING "+list);

        //list.add(new Model(Model.IMAGE_TYPE, "Ensalada rica para una dieta saludable", "", R.mipmap.ensalada_manzana));


      /*  list.add(new Modelselectframe(Modelselectframe.IMAGE_TYPE, R.drawable.marco12));
        list.add(new Modelselectframe(Modelselectframe.IMAGE_TYPE, R.drawable.marco2));
        list.add(new Modelselectframe(Modelselectframe.IMAGE_TYPE, R.drawable.nomarco));
        list.add(new Modelselectframe(Modelselectframe.IMAGE_TYPE, R.drawable.marco16));
        //list.add(new Modelselectframe(Modelselectframe.IMAGE_TYPE, R.drawable.marco14));
       list.add(new Modelselectframe(Modelselectframe.IMAGE_TYPE, R.drawable.marco14));


        //list.add(new Modelselectframe(Modelselectframe.IMAGE_TYPE, R.drawable.marco14));
        //list.add(new Modelselectframe(Modelselectframe.IMAGE_TYPE, R.drawable.nomarco));
//        list.add(new Modelselectframe(Modelselectframe.IMAGE_TYPE, R.drawable.marco14));


        //list.add(new Modelselectframe(Modelselectframe.IMAGE_TYPE, R.drawable.marco2));



      list = new ArrayList<>();

        list.add(new Modelselectframe(Modelselectframe.IMAGE_TYPE, "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/stickers/59149e2d32f04_13.png"));
*/
      /*   adapterselectframe adapter = new adapterselectframe(list, MainActivity_Editarphoto.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_Editarphoto.this, OrientationHelper.HORIZONTAL, false);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewercarrusel);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
      //  mRecyclerView.setHasFixedSize(true);
       // mRecyclerView.setHasFixedSize(hasWindowFocus());


       /*mRecyclerView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               Toast.makeText(MainActivity_Editarphoto.this, "hola", Toast.LENGTH_LONG ).show();

           }
       });*/

   // }

    private double getLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
    }

    protected void onScaling(boolean scaleUp) {
    }

    protected void onRotating() {
    }

    private int convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }











    private boolean mayRequestStoragePermission() {

        if(Build.VERSION.SDK_INT < M)
            return true;

        if((checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED))
            return true;

        if((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) || (shouldShowRequestPermissionRationale(CAMERA))){
            Snackbar.make(CamView, "Los permisos son necesarios para poder usar la aplicación",
                    Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
                @TargetApi(M)
                @Override
                public void onClick(View v) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
                }
            });
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
        }

        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_PERMISSIONS){
            if(grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity_Editarphoto.this, "Permisos aceptados", Toast.LENGTH_SHORT).show();
               // button1.setEnabled(true);
            }
        }else{
            showExplanation();
        }
    }

    private void showExplanation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_Editarphoto.this);
        builder.setTitle("Permisos denegados");
        builder.setIcon(R.drawable.warning_new);
        builder.setMessage("Para usar las funciones de la app necesitas aceptar los permisos");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        builder.show();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub
        if(cameraview){
            camera.stopPreview();
            cameraview = false;
        }

        if (camera != null){
            try {
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
                cameraview = true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        //Bitmap er = BitmapFactory.decodeResource(getResources(), R.mipmap.navidad);



      /*  Bitmap bitmap;
       Bundle bundler = getIntent().getExtras();
        String input = bundler.getString("path_foto");

        Uri url;

        if(!(input == null))
        {
           // url = Uri.parse(input);
             bitmap = BitmapFactory.decodeFile(input);

            imageView =(PhotoView)findViewById(R.id.imagenviewphoto);

            imageView.setImageBitmap(bitmap);

            //imageView.setImageURI(url);
            //Picasso.with(this).load(input).into(imageView);
           // imageView.getWidth();
            //imageView.getHeight();

            imageView.setEnabled(false);

        }

        else {
                   //Bitmap bitmap = BitmapFactory.decodeFile(input);




        int inputer = bundler.getInt("keyimagen");
            imageView = (PhotoView) findViewById(R.id.imagenviewphoto);
            imageView.setImageResource(inputer);

         //   imageView.getWidth();
          //  imageView.getHeight();

            imageView.setEnabled(false);
   }



        /*imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            // supuestamente aparece el toolbar
                toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
            }
        });*/

       // Canvas canvas = new Canvas();
        //canvas.drawBitmap(er, 0, 0, null);
        //  surfaceView.setBackgroundColor(Color.BLUE);
        //de.draw(canvas);
        //.draw(canvas);
        surfaceView.setBackgroundColor(Color.WHITE);





        //surfaceView.setb

        //.draw(canvas);




        // camera = Camera.open();
    }






    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        /*camera.stopPreview();
        camera.release();
        camera = null;
        cameraview = false;*/
    }


    public void TakeScreenshot(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int nus = preferences.getInt("images_num",0);
        nus++;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("images_num",nus);
        editor.commit();
        CamView.setDrawingCacheEnabled(true);
        CamView.buildDrawingCache(true);
        bmp = Bitmap.createBitmap(CamView.getDrawingCache());
        CamView.setDrawingCacheEnabled(false);
        bos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bitmapdata = bos.toByteArray();
        fis2 = new ByteArrayInputStream(bitmapdata);

        Long timespan = System.currentTimeMillis()/1000;

        String datos = timespan.toString();

        String picId=String.valueOf(nus);
        String myfile="BabysecImage"+picId+""+datos+".jpeg";

        dir_image = new  File(Environment.getExternalStorageDirectory()+
                File.separator+"Babysec");
        dir_image.mkdirs();

        try {
            File tmpFile = new File(dir_image,myfile);
            fos = new FileOutputStream(tmpFile);

            byte[] buf = new byte[1024];
            int len;
            while ((len = fis2.read(buf)) > 0) {
                fos.write(buf, 0, len);
            }
            fis2.close();
            fos.close();

            Toast.makeText(getApplicationContext(),
                    "Imagen guardado en:/Babysec/"+"BabysecImage"+picId+""+datos+".jpeg",Toast.LENGTH_LONG).show();


            String direccion = dir_image+"/"+myfile;

            Bundle bundle = new Bundle();
            bundle.putString("saveimage", direccion);


            Intent intent = new Intent(MainActivity_Editarphoto.this, MainActivity_preview_image.class);
            intent.putExtras(bundle);
            startActivity(intent);
            bmp1 = null;

           // String se="Direccion de imagen guardado";

            Log.d("Direccion", dir_image+"/"+myfile);


            //Log.d(se, dir_image+myfile);

            camera_image.setImageBitmap(bmp1);
            //camera.startPreview();
          // button1.setClickable(true);
          //  button1.setVisibility(View.VISIBLE);//<----UNHIDE HER
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

   /* public AbsoluteLayout getCamView(){
        //CamView = (AbsoluteLayout)findViewById(R.id.editafoto);
        return this.CamView;
    }*/
   public RelativeLayout getCamView(){
       //CamView = (AbsoluteLayout)findViewById(R.id.editafoto);
       return this.CamView;
   }

    public PhotoView getImageView()
    {
        return  this.imageView;
    }
    public PhotoViewAttacher getA(){
        return  this.a;
    }

    public void cambio_imagen(int imagen){

         mar = (ImageView)findViewById(R.id.imagenviewmarco);
        mar.setImageResource(imagen);
    }


    public ViewFlipper getViewFlipper (){
        return  this.viewFlipper;
    }

    public ImageView retornartext()
    {
        return this.marco;
    }

    public  int retornoposicionmarco(int pos)
    {
        CurrentItem = pos;
        return this.CurrentItem;
    }

    public String getPalabara (){
        return this.palabara;
    }

    public  int getposition (int positioner)
    {
        position = positioner;
        return this.position;
    }

    public int getHeigthview (){
        return this.heigthview;
    }

    private void comartirphoto(){



        CamView.buildDrawingCache();
        Bitmap bitmap = CamView.getDrawingCache();

        try {
            // creacion del archvo de bitmap que se va enviar a compartir
            File file = new File(CamView.getContext().getCacheDir(), bitmap + ".png");
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(file);
           //convierte el bitma a un formato png
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            // se cierra el el archvio que esta entrado para luego realizar la conversion
            fOut.flush();
            fOut.close();

            file.setReadable(true, false);

            // se crea la accion para realizar el proceso de  comaparti
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            // se selecciona el tipo de dato que se convertira
            intent.setType("image/png");
            // realiza el envio
            context.startActivity(Intent.createChooser(intent, "Compartir con"));
        } catch (Exception e) {
            e.printStackTrace();
        }


     /*   SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int rus = preferences.getInt("images_num",0);
        rus++;
        SharedPreferences.Editor editor =  preferences.edit();
        editor.putInt("images_num", rus);
        editor.commit();
        CamView.setDrawingCacheEnabled(true);
        CamView.buildDrawingCache();
        bmp = Bitmap.createBitmap(CamView.getDrawingCache());
        CamView.setDrawingCacheEnabled(false);
        bos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bitmapdata = bos.toByteArray();
        fis2 = new ByteArrayInputStream(bitmapdata);

        String picId=String.valueOf(rus);
        String myfile="MysImagetemp"+picId+".jpeg";

        dir_image = new  File(Environment.getExternalStorageDirectory()+
                File.separator+"Vive_temp");
        dir_image.mkdirs();
        try {
            File tempf = new File(dir_image ,myfile);
            fos = new FileOutputStream(tempf);
            byte[] buf = new byte[1024];

            int lent;

           while ((lent = fis2.read(buf)) > 0)
            {
                fos.write(buf, 0, lent);
            }

            fos.close();
            fis2.close();



        }

    catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }*/





    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            dir_image2 = new  File(Environment.getExternalStorageDirectory()+
                    File.separator+"Babysec");
            dir_image2.mkdirs();


            File tmpFile = new File(dir_image2,"TempImage.jpg");
            try {
                fos = new FileOutputStream(tmpFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            }
            options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            bmp1 = decodeFile(tmpFile);
            bmp=Bitmap.createScaledBitmap(bmp1,CamView.getWidth(), CamView.getHeight(),true);
            camera_image.setImageBitmap(bmp);
            tmpFile.delete();
            TakeScreenshot();

        }
    };


    public Bitmap decodeFile(File f) {
        Bitmap b = null;
        try {
            // Decode image size
            o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;

            fis = new FileInputStream(f);
            BitmapFactory.decodeStream(fis, null, o);
            fis.close();
            int IMAGE_MAX_SIZE = 1000;
            int scale = 1;
            if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
                scale = (int) Math.pow(
                        2,
                        (int) Math.round(Math.log(IMAGE_MAX_SIZE
                                / (double) Math.max(o.outHeight, o.outWidth))
                                / Math.log(0.5)));
            }

            // Decode with inSampleSize
            o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            fis = new FileInputStream(f);
            b = BitmapFactory.decodeStream(fis, null, o2);
            fis.close();

           /* FileOutputStream se = new FileOutputStream("txt");
            {
                String Path = databaseList("recore");
                f.mkdir();

            }*/


        } catch (IOException e) {
            e.printStackTrace();
        }

        return b;
    }




    public class JsonTaskstickerfrist extends AsyncTask<String, String, ArrayList<Model>> {

        @Override
        protected ArrayList<Model> doInBackground(String... urls) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();


                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                    //tvjsom.setText(buffer.toString());
                }

                String finaljson = buffer.toString();
                JSONObject parentjson = new JSONObject(finaljson);


               JSONArray jsarreglo = parentjson.getJSONArray("DATA");

                Log.v("parentjson", ""+parentjson);
                Log.v("DATA", ""+jsarreglo);

                DBHome db = new DBHome(MainActivity_Editarphoto.this);

                SQLiteDatabase database = db.getWritableDatabase();

                java.util.Date utilDate = new java.util.Date();
                java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

                SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");



                String tiempo = String.valueOf(tiem.format(sq));

                ContentValues contentValues = new ContentValues();
                ContentValues contentValues_sticker = new ContentValues();

                ArrayList<Model> list = new ArrayList<>();


                for (int i = 0; i < jsarreglo.length(); i++) {

                    JSONObject ob = jsarreglo.getJSONObject(i);

                    int stickers_categoria_id = ob.getInt("STICKERS_CATEGORIA_ID");
                    String nombre = ob.getString("NOMBRE");


                    String query_categirua = "select count(*) from categoria_sticker  where id_categoria_sticker="+stickers_categoria_id+" ";

                    fila = database.rawQuery(query_categirua, null);

                    if (fila.moveToFirst())
                    {
                        int contador= fila.getInt(0);

                        if (contador == 1)
                        {




                        }

                        else
                        {

                            contentValues.put("id_categoria_sticker", stickers_categoria_id);
                            contentValues.put("nombre", nombre);

                            database.insert("categoria_sticker", null, contentValues);


                        }


                    }





                    JSONArray jsonArrayvideo = ob.getJSONArray("STICKERS");
                    for (int j = 0; j < jsonArrayvideo.length();/*.length();*/ j++) {

                        Log.v("LINEA 4152", "LINEA 4152 For "+jsonArrayvideo.length());

                        float opracion = jsonArrayvideo.length() /2;
                        double resultado  = Math.round(opracion);

                        if (j == resultado )
                        {

                            Log.v("LINEA 4157", "LINEA 4157 pasa por el numerio 2");

                            ContentValues content_no_marco = new ContentValues();

                            String  no_marco = "select count(*) from sticker where id_categoria_sticker= 33 and sticker ='http://www.elevation.com.mx/pages/AppElite/admin/assets/images/stickers_babysec/no-marco.png'";

                            fila_no_marco = database.rawQuery(no_marco, null);

                            if (fila_no_marco.moveToFirst())
                            {
                                int no_marco_contador = fila_no_marco.getInt(0);


                                if(no_marco_contador == 1)
                                {

                                }

                                else{
                                    content_no_marco.put("id_categoria_sticker", 33);
                                    content_no_marco.put("sticker", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/stickers_babysec/no-marco.png");
                                    content_no_marco.put("color_filter", 1);

                                    database.insert("sticker", null, content_no_marco);

                                }


                            }



                        }


                        JSONObject object = jsonArrayvideo.getJSONObject(j);
                        String stickers = object.getString("NOMBRE");
                        int col_filter = object.getInt("FILTRO_COLOR");


                       // Log.v("LINEA 3951", "LINEA 3951  id_categoria "+ stickers_categoria_id  +" sticker "+stickers);


                        String query_sticker ="select count(*) from sticker where id_categoria_sticker="+stickers_categoria_id+" and sticker ='http://www.elevation.com.mx/pages/AppElite/admin/assets/images/stickers_babysec/"+stickers+"'";

                        fila_sticker= database.rawQuery(query_sticker, null);


                        if (fila_sticker.moveToFirst()) {

                            int contador = fila_sticker.getInt(0);
                           // Log.v("LINEA 3963", "LINEA 3963 contador "+contador);

                            if (contador ==1)
                            {

                            }
                            else {
                                Log.v("LINEA MARCO", "LINEA MARCO STICKER: "+stickers_categoria_id+" COLOR: "+col_filter);
                                contentValues_sticker.put("id_categoria_sticker", stickers_categoria_id);
                                contentValues_sticker.put("color_filter", col_filter);
                                contentValues_sticker.put("sticker", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/stickers_babysec/" + stickers);



                                database.insert("sticker", null, contentValues_sticker);
                            }

                        }
                            // break;
                    }


                }



                return list;





            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }




            return null;//"entro en esta condicion";
        }


        @Override
        protected void onPostExecute(ArrayList<Model> result) {
            super.onPostExecute(result);









            DBHome dbHome = new DBHome(MainActivity_Editarphoto.this);

            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

            list= new ArrayList<>();
            marco_arreglo = new ArrayList<>();

            String query = "select id_categoria_sticker, sticker from sticker where id_categoria_sticker=33 ";

            fila = sqLiteDatabase.rawQuery(query, null);



            while (fila.moveToNext())

            {

                int id_categoria_sticker = Integer.parseInt(fila.getString(0));
                String stiker = fila.getString(1);

                Log.v("LINEA 4039", "LINEA 4039 id_categoria "+id_categoria_sticker+" sticker " + stiker);

                    Modelselectframe model = new Modelselectframe(0, stiker);


                    list.add(model);
                marco_arreglo.add(stiker);
                Log.v("LINEA 4161", "LINEA 4161 Sticker "+ stiker);

        }

           mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewercarrusel);
           adapterview = new adapterselectframe(list, MainActivity_Editarphoto.this);
           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity_Editarphoto.this, OrientationHelper.HORIZONTAL, false);
           mRecyclerView.setLayoutManager(linearLayoutManager);
           mRecyclerView.setItemAnimator(new DefaultItemAnimator());
           mRecyclerView.setAdapter(adapterview);







        }

    }



    public class JsonTasksticker extends AsyncTask<String, String, ArrayList<model_sticker>> {

        @Override
        protected ArrayList<model_sticker> doInBackground(String... urls) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();


                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                    //tvjsom.setText(buffer.toString());
                }

                String finaljson = buffer.toString();
                JSONObject parentjson = new JSONObject(finaljson);


                JSONArray jsarreglo = parentjson.getJSONArray("DATA");

                Log.v("parentjson", ""+parentjson);
                Log.v("DATA", ""+jsarreglo);

                DBHome db = new DBHome(MainActivity_Editarphoto.this);

                SQLiteDatabase database = db.getWritableDatabase();

                java.util.Date utilDate = new java.util.Date();
                java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());

                SimpleDateFormat tiem = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");



                String tiempo = String.valueOf(tiem.format(sq));

                ContentValues contentValues = new ContentValues();
                ContentValues contentValues_sticker = new ContentValues();

                ArrayList<model_sticker> list = new ArrayList<>();


                for (int i = 0; i < jsarreglo.length(); i++) {

                    JSONObject ob = jsarreglo.getJSONObject(i);

                    int stickers_categoria_id = ob.getInt("STICKERS_CATEGORIA_ID");
                    String nombre = ob.getString("NOMBRE");


                    String query_categirua = "select count(*) from categoria_sticker  where id_categoria_sticker="+stickers_categoria_id+" ";

                    fila = database.rawQuery(query_categirua, null);

                    if (fila.moveToFirst())
                    {
                        int contador= fila.getInt(0);

                        if (contador == 1)
                        {




                        }

                        else
                        {

                            contentValues.put("id_categoria_sticker", stickers_categoria_id);
                            contentValues.put("nombre", nombre);

                            database.insert("categoria_sticker", null, contentValues);


                        }


                    }





                    JSONArray jsonArrayvideo = ob.getJSONArray("STICKERS");
                    for (int j = 0; j < jsonArrayvideo.length();/*.length();*/ j++) {

                        JSONObject object = jsonArrayvideo.getJSONObject(j);
                        String stickers = object.getString("NOMBRE");
                        int col_filter = object.getInt("FILTRO_COLOR");


                     Log.v("LINEA 4235", "LINEA 4235  id_categoria "+ stickers_categoria_id  +" sticker "+stickers);


                        String query_sticker ="select count(*) from sticker where id_categoria_sticker="+stickers_categoria_id+" and sticker ='http://www.elevation.com.mx/pages/AppElite/admin/assets/images/stickers_babysec/"+stickers+"'";

                        fila_sticker= database.rawQuery(query_sticker, null);


                        if (fila_sticker.moveToFirst()) {

                            int contador = fila_sticker.getInt(0);


                            if (contador ==1)
                            {

                            }
                            else {
                                Log.v("LINEA MARCO", "LINEA MARCO STICKER: "+stickers_categoria_id+" COLOR: "+col_filter);
                                contentValues_sticker.put("id_categoria_sticker", stickers_categoria_id);
                                contentValues_sticker.put("color_filter", col_filter);
                                contentValues_sticker.put("sticker", "http://www.elevation.com.mx/pages/AppElite/admin/assets/images/stickers_babysec/" + stickers);

                                database.insert("sticker", null, contentValues_sticker);
                            }

                        }
                        // break;
                    }



                    Log.v("LINEA 4264", "LINEA 4264 ");


                }

                Log.v("LINEA 4269", "LINEA 4269 ");

                list_sticker= new ArrayList<>();
                Log.v("LINEA 4272", "LINEA 4269 ");

                String query_sticker = "select sticker.sticker from sticker where id_categoria_sticker != 33  ";

                Log.v("LINEA 4276", "LINEA 4269 ");

                fila_ch= database.rawQuery(query_sticker, null);
                Log.v("LINEA 4279", "LINEA 4269 ");

              //  String stik = fila_ch.getString(0);

//                Log.v("LINEA 4283", "LINEA 4283 stick " + fila_ch.getString(0));

                while (fila_ch.moveToNext())
                {Log.v("LINEA 4283", "LINEA 4269 ");

                    String stik = fila_ch.getString(0);

                    Log.v("LINEA 4283", "LINEA 4283 stick " + stik);

                  // model_sticker model_stick = new model_sticker(model_sticker.sticker_type, stik);
                    list_sticker.add(stik);

                    Log.v("LINEA 4295", "LINEA 4295 LINEAR STICK" + list_sticker.toString());


                }






                return list;





            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }




            return null;//"entro en esta condicion";
        }


        @Override
        protected void onPostExecute(ArrayList<model_sticker> result) {
            super.onPostExecute(result);



           /* DBHome dbHome = new DBHome(MainActivity_Editarphoto.this, name_database_elite, null, 1);

            SQLiteDatabase sqLiteDatabase = dbHome.getWritableDatabase();

    list_sticker= new ArrayList<>();

           String query_sticker = "select sticker from sticker where id_categoria_sticker < 10 and id_categoria_sticker > 10  ";


            fila_sticker = sqLiteDatabase.rawQuery(query_sticker, null);


            while (fila_sticker.moveToNext())
            {
                String stik = fila_sticker.getString(0);


                //model_sticker model_stick = new model_sticker(model_sticker.sticker_type, stik);
                list_sticker.add(stik);


            }*/


        }

    }























    @Override
    public boolean onSupportNavigateUp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_Editarphoto.this);

       // String aler = "¿Descartar foto?";


        builder.setTitle("¿Descartar foto?").setIcon(R.mipmap.alert);

        builder.setMessage("Deseas salir cualquier cambio que haya realizado se perdera");

        builder.setIcon(R.drawable.warning_new);
        builder.setPositiveButton("Descartar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent(MainActivity_Editarphoto.this, MainActivity_galeria_imagen.class);
                startActivity(intent);

                //onBackPressed();

            }
        });
builder.setNegativeButton("Mantener", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        //finish();
    }
});

        builder.show();

        return  true;

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

       if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){

           AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_Editarphoto.this);
           builder.setTitle("¿Descartar foto?");
           builder.setMessage("Deseas salir cualquier cambio que haya realizado se perdera");
//           builder.setIcon(R.mipmap.alert);

           builder.setIcon(R.drawable.warning_new);
           builder.setPositiveButton("Descartar", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {

                   Intent intent = new Intent(MainActivity_Editarphoto.this, MainActivity_galeria_imagen.class);
                   startActivity(intent);

               }
           });

           builder.setNegativeButton("Mantener", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   dialogInterface.dismiss();
               }
           });

           builder.show();

           return  true;
       }




        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor_imagen, menu);
//        getMenuInflater().inflate(R.menu.main, menu);

       /*elimianrsticker = menu.findItem(R.id.ideliminarsticker);
         elimianrsticker.setVisible(true);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.saveimagen) {


            return true;
        }
        if(id == R.id.ideliminarsticker){



            return  true;
        }
        return super.onOptionsItemSelected(item);
    }


}