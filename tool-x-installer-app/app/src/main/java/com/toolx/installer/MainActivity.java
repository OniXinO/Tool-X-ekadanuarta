package com.toolx.installer;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TERMUX_PACKAGE = "com.termux";
    private static final String FDROID_TERMUX_URL = "https://f-droid.org/en/packages/com.termux/";
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCheckTermux = findViewById(R.id.btnCheckTermux);
        Button btnInstallTermux = findViewById(R.id.btnInstallTermux);
        Button btnInstallToolX = findViewById(R.id.btnInstallToolX);
        Button btnLaunchToolX = findViewById(R.id.btnLaunchToolX);
        Button btnInstructions = findViewById(R.id.btnInstructions);
        statusText = findViewById(R.id.statusText);

        btnCheckTermux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTermux();
            }
        });

        btnInstallTermux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                installTermux();
            }
        });

        btnInstallToolX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                installToolX();
            }
        });

        btnLaunchToolX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchToolX();
            }
        });

        btnInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInstructions();
            }
        });

        // Check Termux on startup
        checkTermux();
    }

    private void checkTermux() {
        boolean isTermuxInstalled = isAppInstalled(TERMUX_PACKAGE);
        if (isTermuxInstalled) {
            statusText.setText(getString(R.string.termux_installed));
            Toast.makeText(this, getString(R.string.termux_installed), Toast.LENGTH_SHORT).show();
        } else {
            statusText.setText(getString(R.string.termux_not_installed));
            Toast.makeText(this, getString(R.string.termux_not_installed), Toast.LENGTH_SHORT).show();
        }
    }

    private void installTermux() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(FDROID_TERMUX_URL));
        startActivity(intent);
    }

    private void installToolX() {
        if (!isAppInstalled(TERMUX_PACKAGE)) {
            Toast.makeText(this, getString(R.string.termux_not_installed), Toast.LENGTH_LONG).show();
            return;
        }

        // The installation commands
        String installCommands = 
            "pkg update -y && " +
            "pkg install -y git python && " +
            "git clone https://github.com/OniXinO/Tool-X-ekadanuarta && " +
            "cd Tool-X-ekadanuarta && " +
            "chmod +x install && " +
            "./install";

        // Launch Termux with the installation commands
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setClassName(TERMUX_PACKAGE, TERMUX_PACKAGE + ".app.TermuxActivity");
        intent.putExtra(TERMUX_PACKAGE + ".app.TERMINAL_COMMAND", installCommands);
        
        try {
            startActivity(intent);
            statusText.setText("Встановлення Tool-X запущено у Termux");
        } catch (Exception e) {
            Toast.makeText(this, "Не вдалося запустити Termux", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void launchToolX() {
        if (!isAppInstalled(TERMUX_PACKAGE)) {
            Toast.makeText(this, getString(R.string.termux_not_installed), Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setClassName(TERMUX_PACKAGE, TERMUX_PACKAGE + ".app.TermuxActivity");
        intent.putExtra(TERMUX_PACKAGE + ".app.TERMINAL_COMMAND", "Tool-X");
        
        try {
            startActivity(intent);
            statusText.setText("Запуск Tool-X у Termux");
        } catch (Exception e) {
            Toast.makeText(this, "Не вдалося запустити Termux", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void showInstructions() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_instructions);
        dialog.show();
    }

    private boolean isAppInstalled(String packageName) {
        try {
            getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
