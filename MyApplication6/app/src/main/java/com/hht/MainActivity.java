package com.hht;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.hht.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.hht.FileInfo;
import com.hht.IFileInfoCallback;
import com.hht.IGetFileInfo;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainExample";

    private Button button;
    // 这里必须是实现IFileInfoCallback.Stub()，而不是IFileInfoCallback这个接口
    private IFileInfoCallback mycallback = new IFileInfoCallback.Stub() {

        @Override
        public void backFileInfo(FileInfo fileInfo) throws RemoteException {
            Log.d(TAG, fileInfo.toString());
        }
    };

    private IGetFileInfo getFileInfoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        connect_service();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getFileInfoService.getFileInfo("/data/local/tmp/test.txt");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void connect_service()  {
        try {
            Class<?> clazz = Class.forName("android.os.ServiceManager");

            Method method = clazz.getMethod("getService",String.class);

            IBinder binder = (IBinder) method.invoke(null,"MyFileService");

            if (binder != null){
                getFileInfoService = IGetFileInfo.Stub.asInterface(binder);
                // 千万不要忘记注册
                getFileInfoService.registerCallback(mycallback);
                Log.d(TAG, "连接成功");
            }

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}